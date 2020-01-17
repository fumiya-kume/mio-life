package nagoya.kuu.miolife.iijmio.remote.packetlog.response

import nagoya.kuu.miolife.iijmio.model.volumelog.PacketLogRootModel

fun PacketLogRootResponse.convert(): PacketLogRootModel {
    return PacketLogRootModel(
        this.packetLogInfoList.convert()
    )
}