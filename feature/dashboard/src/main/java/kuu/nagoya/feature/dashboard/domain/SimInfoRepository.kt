package kuu.nagoya.feature.dashboard.domain

import kotlinx.coroutines.flow.Flow
import kuu.nagoya.core.config.RepositoryResponse
import kuu.nagoya.feature.dashboard.domain.entity.SimEntity

internal interface SimInfoRepository {
    suspend fun loadSimInfo(): Flow<RepositoryResponse<List<SimEntity>, Throwable>>
}