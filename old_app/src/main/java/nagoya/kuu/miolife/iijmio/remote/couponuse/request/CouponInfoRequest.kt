package nagoya.kuu.miolife.iijmio.remote.couponuse.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CouponInfoRequest(
    @SerialName("hdoInfo")
    val hdoInfoList: List<hdoInfoRequest> = emptyList(),

    @SerialName("hduInfo")
    val hduInfoList: List<hduInfoRequest> = emptyList(),

    @SerialName("hdxInfo")
    val hdxInfoList: List<hdxInfoRequest> = emptyList()
)

