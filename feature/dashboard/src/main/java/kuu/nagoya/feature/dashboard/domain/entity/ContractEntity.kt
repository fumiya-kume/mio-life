package kuu.nagoya.feature.dashboard.domain.entity

internal data class ContractEntity(
    val serviceCode: String,
    val planName: String,
    val remainVolume: Int
)