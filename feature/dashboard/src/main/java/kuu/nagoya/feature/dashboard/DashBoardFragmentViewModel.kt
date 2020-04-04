package kuu.nagoya.feature.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kuu.nagoya.core.config.LiveDataResponse
import kuu.nagoya.feature.dashboard.viewentity.SimViewEntity

internal class DashBoardFragmentViewModel(
    private val simListLiveDataFactory: SimListLiveDataFactory,
    contractLiveDataFactory: ContractLiveDataFactory
) : ViewModel() {
    private val simListMutableLiveData = simListLiveDataFactory.create(viewModelScope)
    val simViewEntityLiveData: LiveData<LiveDataResponse<List<SimViewEntity>, Throwable>> =
        simListMutableLiveData

    private val _contractLiveData = contractLiveDataFactory.create(viewModelScope)
    val contactLiveData = _contractLiveData
}