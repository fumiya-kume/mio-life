package nagoya.kuu.miolife.iijmio.remote.couponuse

import nagoya.kuu.miolife.iijmio.remote.couponuse.request.CouponUseRequest
import nagoya.kuu.miolife.iijmio.remote.couponuse.response.CouponUseResponseStatus

interface UpdateCouponseSwitchUsecase {
    suspend fun execute(couponUseRequest: CouponUseRequest):CouponUseResponseStatus
}