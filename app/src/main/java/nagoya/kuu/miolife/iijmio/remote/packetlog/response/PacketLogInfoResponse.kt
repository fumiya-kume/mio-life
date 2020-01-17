package nagoya.kuu.miolife.iijmio.remote.packetlog.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PacketLogInfoResponse(
    val hddServiceCode: String,
    val plan: String,
    @SerialName("hdoInfo")
    val hdoInfoResponseList: List<HdoInfoResponse> = emptyList()
)

