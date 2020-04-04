package kuu.nagoya.feature.dashboard.domain

import kotlinx.coroutines.flow.Flow
import kuu.nagoya.core.config.RepositoryResponse
import kuu.nagoya.feature.dashboard.domain.entity.ContractEntity

internal interface ContractRepository {
    suspend fun loadContractInformation(): Flow<RepositoryResponse<ContractEntity, Throwable>>
}