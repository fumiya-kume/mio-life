package nagoya.kuu.miolife.iijmio.remote.packetlog.response

import nagoya.kuu.miolife.iijmio.model.volumelog.HdoInfoModel

fun List<HdoInfoResponse>.convert(): List<HdoInfoModel> = this.map { it.convert() }

fun HdoInfoResponse.convert(): HdoInfoModel {
    return HdoInfoModel(
        hdoServiceCode = this.hdoServiceCode,
        packetLogList = this.packetLogList.convert()
    )
}
