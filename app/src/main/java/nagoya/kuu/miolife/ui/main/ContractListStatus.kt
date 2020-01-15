package nagoya.kuu.miolife.ui.main

import nagoya.kuu.miolife.ui.main.viewentity.ContractViewEntity

sealed class ContractListStatus {
    data class Success(val contractList: List<ContractViewEntity>) : ContractListStatus()
    object LoginRequired : ContractListStatus()
    data class ErrorHappened(val errorMessage: String) : ContractListStatus()
}