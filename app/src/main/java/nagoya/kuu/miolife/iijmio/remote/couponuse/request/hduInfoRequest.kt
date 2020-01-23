package nagoya.kuu.miolife.iijmio.remote.couponuse.request

import kotlinx.serialization.Serializable

@Serializable
data class hduInfoRequest(
    val hduServiceCode: String,
    override val couponUse: Boolean
) : HdRequest