package nagoya.kuu.miolife.ui.main.viewentity

import nagoya.kuu.miolife.iijmio.CouponRemainStatus
import nagoya.kuu.miolife.iijmio.local.CouponAndHdo

fun CouponAndHdo.convert(): ContractViewEntity {
    val couponRemainEntity = this.couponRemainEntity
    val volume = this.couponList.map { it.volume }.fold(0) { i, i2 -> i + i2 }

    return ContractViewEntity(
        couponRemainEntity.hddServiceCode,
        couponRemainEntity.planName,
        couponRemainEntity.hddServiceCode,
        volume.toString(),
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
                remainVolume.toString(),
                it.hdoInfoResponseList.map { it.converter() }
            )
        }
}
