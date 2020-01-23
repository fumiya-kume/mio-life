package nagoya.kuu.miolife.iijmio.model.contract

data class ContractModel(
    val hddServoceCode: String,
    val couponList: List<CouponModel>,
    val hdoInfoList: List<HdoInfoModel> = emptyList(),
    val hduInfoList: List<HduInfoModel> = emptyList(),
    val hdxInfoList: List<HdxInfoModel> = emptyList(),
    val planName: String
)

