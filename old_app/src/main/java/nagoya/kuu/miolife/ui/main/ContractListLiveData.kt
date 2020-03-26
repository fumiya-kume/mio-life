package nagoya.kuu.miolife.ui.main

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nagoya.kuu.miolife.iijmio.local.APILocalService
import nagoya.kuu.miolife.iijmio.remote.couponinfo.APIService
import nagoya.kuu.miolife.iijmio.remote.couponinfo.CouponRemainStatus
import nagoya.kuu.miolife.ui.main.viewentity.convert

internal class ContractListLiveData(
    private val apiLocalServiceImpl: APILocalService,
    private val apiService: APIService,
    private val coroutineScope: CoroutineScope
) : LiveData<ContractListStatus>() {
    init {
        refreshData()
    }

    fun refreshData() {
        val liveData = this
        coroutineScope.launch(Dispatchers.IO) {
            liveData.postValue(ContractListStatus.Success(apiLocalServiceImpl.getAllCouponRemainList().convert()))
            kotlin.runCatching { apiService.getCouponRemainData() }
                .onSuccess {
                    val couponRemainStatus = it
                    liveData.postValue(
                        when (couponRemainStatus) {
                            is CouponRemainStatus.Success -> {

                                couponRemainStatus.contractListModel.contractList.forEach {
                                    apiLocalServiceImpl.insertOrUpdateCouponRemainEntity(it)
                                }

                                ContractListStatus.Success(couponRemainStatus.contractListModel.convert())
                            }
                            is CouponRemainStatus.Error -> ContractListStatus.LoginRequired
                            is CouponRemainStatus.RequestLimited -> ContractListStatus.ErrorHappened(
                                "リクエスト制限中です。"
                            )
                            CouponRemainStatus.ServerError -> ContractListStatus.ErrorHappened("サーバーエラーです。")
                            CouponRemainStatus.ServerMaintenance -> ContractListStatus.ErrorHappened(
                                "サーバーメンテナンス中です"
                            )
                        }
                    )
                }.onFailure {
                    liveData.postValue(
                        ContractListStatus.ErrorHappened("例外が発生しました ${it.message}")
                    )
                }
        }
    }

}

