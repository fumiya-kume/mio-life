package nagoya.kuu.miolife.iijmio.remote.couponinfo.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HdxInfoResponse(
    val couponUse: Boolean = false,
    @SerialName("coupon")
    val couponResponseList: List<CouponResponse> = emptyList(),
    val hdxServiceCode: String = "",
    val sms: Boolean = false,
    val number: String = "",
    val regulation: Boolean = false,
    val iccid: String = "",
    val voice: Boolean = false
)