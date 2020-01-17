package nagoya.kuu.miolife.iijmio.model.contract

data class ContractModel(
    val hddServoceCode: String,
    val couponList: List<CouponModel>,
    val hdoInfoList: List<HdoInfoModel>,
    val planName: String
)

