package kuu.nagoya.feature.dashboard.viewentity

internal data class SimViewEntity(
    val id: Int,
    val phoneNumber: String,
    val couponUse: Boolean,
    val simCode: String
)