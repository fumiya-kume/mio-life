package nagoya.kuu.miolife.iijmio.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CouponInfoResponse(
    @SerialName("couponInfo")
    val contractListResponse: List<ContractResponse>,
    val returnCode: String
)