package nagoya.kuu.miolife.iijmio.entity

data class ContractModel(
    val hddServoceCode: String,
    val couponList: List<CouponModel>,
    val hdoInfoList: List<HdoInfoModel>,
    val planName: String
)

