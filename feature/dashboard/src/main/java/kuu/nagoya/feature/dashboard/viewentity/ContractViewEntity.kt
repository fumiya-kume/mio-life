package kuu.nagoya.feature.dashboard.viewentity

internal data class ContractViewEntity(
    val id: Int,
    val serviceCode: String,
    val planName: String,
    val remainVolume: String
)