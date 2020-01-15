package nagoya.kuu.miolife.ui.main

import kotlinx.coroutines.CoroutineScope
import nagoya.kuu.miolife.iijmio.local.APILocalService
import nagoya.kuu.miolife.iijmio.remote.APIService

internal class ContractListLiveDataFactory(
    private val apiService: APIService,
    private val apiLocalService: APILocalService
) {
    fun create(coroutineScope: CoroutineScope): ContractListLiveData {
        return ContractListLiveData(
            apiLocalService,
            apiService,
            coroutineScope
        )
    }
}