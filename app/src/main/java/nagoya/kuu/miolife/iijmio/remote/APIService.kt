package nagoya.kuu.miolife.iijmio.remote

interface APIService {
    suspend fun getCouponRemainData(): CouponRemainStatus
}