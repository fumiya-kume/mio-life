package nagoya.kuu.miolife.ui.main.viewentity

data class ContractViewEntity(
    val id: String,
    val planName: String,
    val hddServiceId: String,
    val remainVolume: String,
    val simList: List<SimViewEntity>
)