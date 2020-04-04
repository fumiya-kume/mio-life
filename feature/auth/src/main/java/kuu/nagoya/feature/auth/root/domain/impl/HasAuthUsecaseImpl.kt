package kuu.nagoya.feature.auth.root.domain.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kuu.nagoya.feature.auth.root.domain.AccessTokenReadonlyRepository
import kuu.nagoya.feature.auth.root.domain.HasAuthUsecase
import kuu.nagoya.feature.auth.root.domain.HasAuthUsecaseResponse

internal class HasAuthUsecaseImpl(
    private val accessTokenReadonlyRepository: AccessTokenReadonlyRepository
) : HasAuthUsecase {
    override suspend fun execute(): Flow<HasAuthUsecaseResponse> = flow {
        this.emit(HasAuthUsecaseResponse.Loading)

        runCatching {
            accessTokenReadonlyRepository.loadAccessToken()
        }.fold(
            onSuccess = {
                this.emit(
                    if (it.isEmpty()) {
                        HasAuthUsecaseResponse.NotHasAuth
                    } else {
                        HasAuthUsecaseResponse.HasAuth
                    }
                )
            },
            onFailure = {
                this.emit(HasAuthUsecaseResponse.Error(it.message ?: ""))
            }
        )
    }
}