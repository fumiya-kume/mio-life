package kuu.nagoya.feature.auth

import kotlinx.coroutines.CoroutineScope
import kuu.nagoya.feature.auth.domain.HasAuthUsecase

internal class HasAuthLiveDataFactory(
    private val hasAuthUsecase: HasAuthUsecase
) {
    fun create(
        coroutineScope: CoroutineScope
    ): HasAuthLiveData {
        return HasAuthLiveData(hasAuthUsecase, coroutineScope)
    }
}