package nagoya.kuu.miolife.ui.main.viewentity

import nagoya.kuu.miolife.iijmio.entity.ContractListModel

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

fun ContractListModel.convert(): List<ContractViewEntity> {
    return this
        .contractList
        .map {
            val remainVolume =
                it.couponList.map { it.volume }.foldRight(0, { i, i2 -> i + i2 })

            return@map ContractViewEntity(
                it.hddServoceCode,
                it.planName,
                it.hddServoceCode,
                remainVolume.toSimVolumeString(),
                it.hdoInfoList.map { it.convert() }
            )
        }
}
