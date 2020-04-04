package kuu.nagoya.feature.dashboard

import kotlinx.coroutines.CoroutineScope
import kuu.nagoya.feature.dashboard.domain.SimInfoRepository

internal class SimListLiveDataFactory(
    private val simInfoRepository: SimInfoRepository
) {
    fun create(
        coroutineScope: CoroutineScope
    ): SImListLiveData {
        return SImListLiveData(
            simInfoRepository,
            coroutineScope
        )
    }
}