package nagoya.kuu.miolife.iijmio.remote.packetlog.response

import kotlinx.serialization.Serializable

@Serializable
data class PacketLogResponse(
    val date: String = "",
    val withCoupon: Int = 0,
    val withoutCoupon: Int = 0
)