package nagoya.kuu.miolife.iijmio.model.contract

data class HdxInfoModel(
    val couponUse: Boolean,
    val couponResponseList: List<CouponModel>,
    val hdxServiceCode: String,
    val sms: Boolean,
    val number: String,
    val regulation: Boolean,
    val iccid: String,
    val voice: Boolean
)