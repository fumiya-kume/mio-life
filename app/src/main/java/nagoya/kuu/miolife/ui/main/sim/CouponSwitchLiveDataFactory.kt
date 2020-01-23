package nagoya.kuu.miolife.ui.main.sim

import kotlinx.coroutines.CoroutineScope
import nagoya.kuu.miolife.iijmio.local.APILocalService
import nagoya.kuu.miolife.iijmio.remote.couponuse.UpdateCouponseSwitchUsecase

internal class CouponSwitchLiveDataFactory(
    private val apiLocalService: APILocalService,
    private val updateCouponseSwitchUsecase: UpdateCouponseSwitchUsecase
) {
    fun create(
        hdoServiceCode: String,
        coroutineScope: CoroutineScope
    ): CouponSwitchLiveData {
        return CouponSwitchLiveData(
            hdoServiceCode = hdoServiceCode,
            apiLocalService = apiLocalService,
            coroutineScope = coroutineScope,
            updateCouponseSwitchUsecase = updateCouponseSwitchUsecase
        )
    }
}