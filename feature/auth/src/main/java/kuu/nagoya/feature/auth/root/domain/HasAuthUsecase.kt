package kuu.nagoya.feature.auth.root.domain

import kotlinx.coroutines.flow.Flow

internal interface HasAuthUsecase {
    suspend fun execute(): Flow<HasAuthUsecaseResponse>
}