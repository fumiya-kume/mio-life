package nagoya.kuu.miolife.ui.main

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nagoya.kuu.miolife.iijmio.APIService
import nagoya.kuu.miolife.iijmio.CouponRemainStatus
import nagoya.kuu.miolife.iijmio.local.APILocalService
import nagoya.kuu.miolife.ui.main.viewentity.convert

internal class ContractListLiveData(
    private val apiLocalServiceImpl: APILocalService,
    private val apiService: APIService,
    private val coroutineScope: CoroutineScope
) : LiveData<ContractListStatus>() {
    init {
        val liveData = this

        coroutineScope.launch(Dispatchers.IO) {
            
            liveData.postValue(ContractListStatus.Success(apiLocalServiceImpl.getAllCouponRemainList().convert()))

            val couponRemainStatus = apiService.getCouponRemainData()
            liveData.postValue(
                when (couponRemainStatus) {
                    is CouponRemainStatus.Success -> ContractListStatus.Success(couponRemainStatus.convert())
                    is CouponRemainStatus.Error -> ContractListStatus.LoginRequired
                    is CouponRemainStatus.RequestLimited -> ContractListStatus.ErrorHappened("リクエスト制限中です。")
                    CouponRemainStatus.ServerError -> ContractListStatus.ErrorHappened("サーバーエラーです。")
                    CouponRemainStatus.ServerMaintenance -> ContractListStatus.ErrorHappened("サーバーメンテナンス中です")
                    else -> ContractListStatus.LoginRequired
                }
            )
        }
    }
}

