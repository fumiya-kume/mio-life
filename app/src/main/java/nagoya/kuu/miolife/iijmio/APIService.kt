package nagoya.kuu.miolife.iijmio

interface APIService {
    suspend fun getCouponRemainData(): CouponRemainStatus
}