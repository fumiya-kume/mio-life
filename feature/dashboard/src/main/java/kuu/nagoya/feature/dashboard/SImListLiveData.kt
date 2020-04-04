package kuu.nagoya.feature.dashboard

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kuu.nagoya.core.config.LiveDataResponse
import kuu.nagoya.feature.dashboard.domain.SimInfoRepository
import kuu.nagoya.feature.dashboard.domain.entity.SimEntity
import kuu.nagoya.feature.dashboard.viewentity.SimViewEntity

internal class SImListLiveData(
    private val simInfoRepository: SimInfoRepository,
    private val coroutineScope: CoroutineScope
) : LiveData<LiveDataResponse<List<SimViewEntity>, Throwable>>() {
    init {
        fun SimEntity.toSimViewEntity(): SimViewEntity {
            return SimViewEntity(
                this.id,
                this.phoneNumber,
                this.couponUse,
                this.simCode
            )
        }

        fun List<SimEntity>.toSimViewEntity(): List<SimViewEntity> {
            return this.map { it.toSimViewEntity() }
        }

        coroutineScope.launch {
            simInfoRepository.loadSimInfo()
                .map {
                    LiveDataResponse.from(it) {  it.toSimViewEntity() }
                }
                .collect {
                    postValue(it)
                }
        }
    }
}