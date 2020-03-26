package nagoya.kuu.miolife.iijmio.remote.packetlog.response

import nagoya.kuu.miolife.iijmio.model.volumelog.PacketLogInfoModel



fun List<PacketLogInfoResponse>.convert(): List<PacketLogInfoModel> = this.map { it.convert() }

fun PacketLogInfoResponse.convert(): PacketLogInfoModel {
    return PacketLogInfoModel(
        hdoInfoList = this.hdoInfoResponseList.convert(),
        hddServiceCode = this.hddServiceCode
    )
}


