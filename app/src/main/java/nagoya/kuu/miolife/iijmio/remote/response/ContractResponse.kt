package nagoya.kuu.miolife.iijmio.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable()
data class ContractResponse(
    val hddServiceCode: String,
    @SerialName("coupon")
    val couponResponseList: List<CouponResponse>,
    @SerialName("hdoInfo")
    val hdoInfoResponseList: List<HdoInfoResponse>,
    @SerialName("plan")
    val planName: String
)