package nagoya.kuu.miolife.iijmio.remote

import nagoya.kuu.miolife.iijmio.entity.ContractListModel

interface APIService {
    suspend fun getCouponRemainData(): CouponRemainStatus
}