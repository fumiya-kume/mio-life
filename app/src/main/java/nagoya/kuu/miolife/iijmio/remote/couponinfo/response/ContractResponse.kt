package nagoya.kuu.miolife.iijmio.remote.couponinfo.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable()
data class ContractResponse(
    val hddServiceCode: String,
    @SerialName("coupon")
    val couponResponseList: List<CouponResponse>,
    @SerialName("hdoInfo")
    val hdoInfoResponseList: List<HdoInfoResponse> = emptyList(),
    @SerialName("hduInfo")
    val hduInfoResponseList: List<HduInfoResponse> = emptyList(),
    @SerialName("hdxInfo")
    val hdxInfoResponseList: List<HdxInfoResponse> = emptyList(),
    @SerialName("plan")
    val planName: String
)