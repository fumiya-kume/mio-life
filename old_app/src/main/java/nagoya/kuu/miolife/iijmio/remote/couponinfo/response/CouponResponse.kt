package nagoya.kuu.miolife.iijmio.remote.couponinfo.response

import kotlinx.serialization.Serializable

@Serializable
data class CouponResponse(
    val volume: Int = 0,
    val expire: String? = "",
    val type: String = ""
)