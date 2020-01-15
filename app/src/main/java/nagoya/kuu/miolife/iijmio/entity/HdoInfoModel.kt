package nagoya.kuu.miolife.iijmio.entity

data class HdoInfoModel(
    val couponUse: Boolean,
    val couponResponseList: List<CouponModel>,
    val hdoServiceCode: String,
    val sms: Boolean,
    val number: String,
    val regulation: Boolean,
    val iccid: String,
    val voice: Boolean
)