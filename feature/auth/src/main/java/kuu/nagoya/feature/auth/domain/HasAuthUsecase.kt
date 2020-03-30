package kuu.nagoya.feature.auth.domain

import kotlinx.coroutines.flow.Flow

internal interface HasAuthUsecase {
    suspend fun execute(): Flow<HasAuthUsecaseResponse>
}