package nagoya.kuu.miolife.iijmio.remote.packetlog.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HdoInfoResponse(
    val hdoServiceCode: String,
    @SerialName("packetLog")
    val packetLogList: List<PacketLogResponse> = emptyList()
)