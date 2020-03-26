package nagoya.kuu.miolife.iijmio.model.volumelog

data class HdoInfoModel(
    val hdoServiceCode: String,
    val packetLogList: List<PacketLogModel>
)