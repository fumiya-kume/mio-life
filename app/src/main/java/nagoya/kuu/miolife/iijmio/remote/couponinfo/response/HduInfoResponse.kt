package nagoya.kuu.miolife.iijmio.remote.couponinfo.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HduInfoResponse(
    val couponUse: Boolean,
    @SerialName("coupon")
    val couponResponseList: List<CouponResponse>,
    val hduServiceCode: String,
    val sms: Boolean,
    val number: String,
    val regulation: Boolean,
    val iccid: String,
    val voice: Boolean
)