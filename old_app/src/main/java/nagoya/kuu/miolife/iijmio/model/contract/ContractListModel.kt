package nagoya.kuu.miolife.iijmio.model.contract

data class ContractListModel(
    val contractList: List<ContractModel>
)

fun List<ContractModel>.convert(): ContractListModel {
    return ContractListModel(
        this
    )
}