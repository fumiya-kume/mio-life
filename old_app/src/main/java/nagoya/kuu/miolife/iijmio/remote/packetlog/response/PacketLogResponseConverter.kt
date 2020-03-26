package nagoya.kuu.miolife.iijmio.remote.packetlog.response

import nagoya.kuu.miolife.iijmio.model.volumelog.PacketLogModel


fun List<PacketLogResponse>.convert(): List<PacketLogModel> = this.map { it.convert() }

fun PacketLogResponse.convert(): PacketLogModel {
    return PacketLogModel(
        date = this.date,
        withCoupon = this.withCoupon,
        withoutCoupon = this.withoutCoupon
    )
}