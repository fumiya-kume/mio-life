package nagoya.kuu.miolife.iijmio.model.volumelog


data class PacketLogInfoModel(
    val hdoInfoList: List<HdoInfoModel>,
    val hddServiceCode: String
)