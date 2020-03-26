package nagoya.kuu.miolife.ui.main.sim

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nagoya.kuu.miolife.iijmio.local.APILocalService
import nagoya.kuu.miolife.iijmio.remote.couponuse.UpdateCouponseSwitchUsecase
import nagoya.kuu.miolife.iijmio.remote.couponuse.request.*

internal class CouponSwitchLiveData(
    private val hdoServiceCode: String,
    private val apiLocalService: APILocalService,
    private val coroutineScope: CoroutineScope,
    private val updateCouponseSwitchUsecase: UpdateCouponseSwitchUsecase
) : LiveData<CouponSwitchStatus>() {
    init {
        postValue(CouponSwitchStatus.Loading)
    }

    fun refreshSwitchData() {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                val result = apiLocalService.getAllCouponRemainList()
                val simData = result
                    .contractList
                    .flatMap { it.hdoInfoList }
                    .firstOrNull { it.hdoServiceCode == hdoServiceCode }
                    ?: return@launch

                postValue(
                    CouponSwitchStatus.Success(simData.couponUse)
                )
            } catch (e: Exception) {
                postValue(
                    CouponSwitchStatus.Failed("エラーが発生しました")
                )
            }
        }
    }

    fun updateSwitchValue(newValue: Boolean, serviceCode: String) {
        kotlin.runCatching {
            coroutineScope.launch(Dispatchers.IO) {
                val hdoInfoList = if (serviceCode.startsWith("hdo")) {
                    listOf(
                        hdoInfoRequest(
                            hdoServiceCode = serviceCode,
                            couponUse = newValue
                        )
                    )
                } else {
                    emptyList()
                }

                val hduInfoList = if (serviceCode.startsWith("hdu")) {
                    listOf(
                        hduInfoRequest(
                            hduServiceCode = serviceCode,
                            couponUse = newValue
                        )
                    )
                } else {
                    emptyList()
                }

                val hdxInfoList = if (serviceCode.startsWith("hdx")) {
                    listOf(
                        hdxInfoRequest(
                            hdxServiceCode = serviceCode,
                            couponUse = newValue
                        )
                    )
                } else {
                    emptyList()
                }

                updateCouponseSwitchUsecase.execute(
                    CouponUseRequest(
                        couponInfoList = listOf(
                            CouponInfoRequest(
                                hdoInfoList = hdoInfoList,
                                hduInfoList = hduInfoList,
                                hdxInfoList = hdxInfoList
                            )
                        )
                    )
                )
            }
        }.onFailure()
        {
            CouponSwitchStatus.Failed("問題が発生しました ${it.localizedMessage}")
        }
    }
}