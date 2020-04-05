package kuu.nagoya.feature.dashboard

import kotlinx.coroutines.CoroutineScope
import kuu.nagoya.feature.dashboard.domain.ContractRepository

internal class ContractLiveDataFactory(
    private val contractRepository: ContractRepository
) {
    fun create(
        coroutineScope: CoroutineScope
    ): ContractLiveData {
        return ContractLiveData(
            contractRepository,
            coroutineScope
        )
    }
}