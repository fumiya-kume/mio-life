package nagoya.kuu.miolife.iijmio.model.contract

data class HduInfoModel(
    val couponUse: Boolean,
    val couponResponseList: List<CouponModel>,
    val hduServiceCode: String,
    val sms: Boolean,
    val number: String,
    val regulation: Boolean,
    val iccid: String,
    val voice: Boolean
)