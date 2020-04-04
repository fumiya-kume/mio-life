package kuu.nagoya.feature.dashboard

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kuu.nagoya.core.config.LiveDataResponse
import kuu.nagoya.feature.dashboard.domain.ContractRepository
import kuu.nagoya.feature.dashboard.domain.entity.ContractEntity
import kuu.nagoya.feature.dashboard.viewentity.ContractViewEntity

internal class ContractLiveData(
    private val contractRepository: ContractRepository,
    private val coroutineScope: CoroutineScope
) : LiveData<LiveDataResponse<ContractViewEntity, Throwable>>() {
    init {

        fun ContractEntity.toContractViewEntity(): ContractViewEntity {
            return ContractViewEntity(
                0,
                this.serviceCode,
                this.planName,
                this.remainVolume.toString()
            )
        }

        coroutineScope.launch {
            contractRepository
                .loadContractInformation()
                .map {
                    LiveDataResponse.from(it) {
                        it.toContractViewEntity()
                    }
                }.collect {
                    postValue(it)
                }
        }
    }
}