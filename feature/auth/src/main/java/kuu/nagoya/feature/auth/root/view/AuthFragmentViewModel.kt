package kuu.nagoya.feature.auth.root.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kuu.nagoya.feature.auth.root.domain.HasAuthUsecaseResponse

internal class AuthFragmentViewModel(
    private val hasAuthLiveDataFactory: HasAuthLiveDataFactory
) : ViewModel() {

    private var _hasAuthLiveData: HasAuthLiveData = hasAuthLiveDataFactory.create(viewModelScope)

    val hasAuthLiveData: LiveData<HasAuthUsecaseResponse> = _hasAuthLiveData

    fun reloadAuthStatus() {
        _hasAuthLiveData.reload()
    }

    override fun onCleared() {
        super.onCleared()

        _hasAuthLiveData = hasAuthLiveDataFactory.create(viewModelScope)
    }
}