package kuu.nagoya.feature.dashboard.entity

internal data class SimEntity(
    val id: Int,
    val simCode: String,
    val phoneNumber: String,
    val couponUse: Boolean
)