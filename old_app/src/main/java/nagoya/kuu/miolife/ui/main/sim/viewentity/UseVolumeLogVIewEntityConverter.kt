package nagoya.kuu.miolife.ui.main.sim.viewentity

import nagoya.kuu.miolife.iijmio.model.volumelog.PacketLogRootModel

internal fun PacketLogRootModel.convert(hdoServiceCode: String): List<UseVolumeLogViewEntity> {
    val simInfo = this.packetLogInfoList
        .flatMap { it.hdoInfoList }
        .firstOrNull { it.hdoServiceCode == hdoServiceCode }
        ?: throw Exception("Sim card is not found")

    return simInfo.packetLogList.map {
        UseVolumeLogViewEntity(
            id = it.date,
            date = it.date,
            withCouponVolume = it.withCoupon.toString(),
            withoutCouponVolume = it.withoutCoupon.toString()
        )
    }
}