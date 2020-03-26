package nagoya.kuu.miolife.iijmio.remote.couponinfo

import kotlinx.serialization.Serializable
import nagoya.kuu.miolife.iijmio.model.contract.ContractListModel

sealed class CouponRemainStatus {

    data class Success(
        val contractListModel: ContractListModel
    ) : CouponRemainStatus()

    @Serializable
    data class Error(
        val returnCode: String
    ) : CouponRemainStatus()

    @Serializable
    data class RequestLimited(
        val returnCode: String
    ) : CouponRemainStatus()

    object ServerError : CouponRemainStatus()
    object ServerMaintenance : CouponRemainStatus()
}