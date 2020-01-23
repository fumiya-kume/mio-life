package nagoya.kuu.miolife.iijmio.remote.couponuse.request

import kotlinx.serialization.Serializable

@Serializable
data class hdoInfoRequest(
    val hdoServiceCode: String,
    override val couponUse: Boolean
) : HdRequest