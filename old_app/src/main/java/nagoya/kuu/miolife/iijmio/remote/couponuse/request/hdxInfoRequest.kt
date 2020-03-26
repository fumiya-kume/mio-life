package nagoya.kuu.miolife.iijmio.remote.couponuse.request

import kotlinx.serialization.Serializable

@Serializable
data class hdxInfoRequest(
    val hdxServiceCode: String,
    override val couponUse: Boolean
) : HdRequest