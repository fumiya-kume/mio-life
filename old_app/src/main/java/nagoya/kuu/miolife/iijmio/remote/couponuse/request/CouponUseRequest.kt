package nagoya.kuu.miolife.iijmio.remote.couponuse.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CouponUseRequest(
    @SerialName("couponInfo")
    val couponInfoList: List<CouponInfoRequest> = emptyList()
)