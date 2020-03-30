package kuu.nagoya.feature.auth

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kuu.nagoya.feature.auth.domain.HasAuthUsecase
import kuu.nagoya.feature.auth.domain.HasAuthUsecaseResponse

internal class HasAuthLiveData(
    private val hasAuthUsecase: HasAuthUsecase,
    private val coroutineScope: CoroutineScope
) : LiveData<HasAuthUsecaseResponse>() {
    init {
        loadHasAuthValue()
    }

    fun reload() {
        loadHasAuthValue()
    }

    private fun loadHasAuthValue() {
        coroutineScope.launch {
            hasAuthUsecase.execute().collect {
                postValue(it)
            }
        }
    }
}