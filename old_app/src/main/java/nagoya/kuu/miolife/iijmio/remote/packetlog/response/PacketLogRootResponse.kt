package nagoya.kuu.miolife.iijmio.remote.packetlog.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PacketLogRootResponse(
    @SerialName("packetLogInfo")
    val packetLogInfoList: List<PacketLogInfoResponse> = emptyList(),
    val returnCode: String
)