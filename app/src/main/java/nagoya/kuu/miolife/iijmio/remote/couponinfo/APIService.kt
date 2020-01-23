package nagoya.kuu.miolife.iijmio.remote.couponinfo

interface APIService {
    suspend fun getCouponRemainData(): CouponRemainStatus
}