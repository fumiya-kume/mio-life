package nagoya.kuu.miolife.iijmio.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HdoInfoResponse(
    val couponUse: Boolean,
    @SerialName("coupon")
    val couponResponseList: List<CouponResponse>,
    val hdoServiceCode: String,
    val sms: Boolean,
    val number: String,
    val regulation: Boolean,
    val iccid: String,
    val voice: Boolean
)