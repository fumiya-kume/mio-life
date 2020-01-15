package nagoya.kuu.miolife.ui.main.viewentity

import nagoya.kuu.miolife.iijmio.CouponRemainStatus
import nagoya.kuu.miolife.iijmio.local.CouponAndHdo

private fun Int.toSimVolumeString(): String {
    val remainString = if (this < 1_000) {
        return "${this}"
    } else if (this < 1_000_000) {
        "$this MB"
    } else {
        this.toString()
    }

    return "残容量: $remainString"
}

fun CouponAndHdo.convert(): ContractViewEntity {
    val couponRemainEntity = this.couponRemainEntity
    val volume = this.couponList.map { it.volume }.fold(0) { i, i2 -> i + i2 }

    return ContractViewEntity(
        couponRemainEntity.hddServiceCode,
        couponRemainEntity.planName,
        couponRemainEntity.hddServiceCode,
        volume.toSimVolumeString(),
        this.hdoInfoList.map { it.converter() }
    )
}

fun List<CouponAndHdo>.convert(): List<ContractViewEntity> = this.map { it.convert() }

fun CouponRemainStatus.Success.convert(): List<ContractViewEntity> {
    return this
        .couponInfoResponse

        .map {
            val remainVolume =
                it.couponResponseList.map { it.volume }.foldRight(0, { i, i2 -> i + i2 })

            return@map ContractViewEntity(
                it.hddServiceCode,
                it.planName,
                it.hddServiceCode,
                remainVolume.toSimVolumeString(),
                it.hdoInfoResponseList.map { it.converter() }
            )
        }
}
