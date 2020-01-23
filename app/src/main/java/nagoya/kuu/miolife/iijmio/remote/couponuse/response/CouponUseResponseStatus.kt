package nagoya.kuu.miolife.iijmio.remote.couponuse.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed class CouponUseResponseStatus {

    @Serializable
    data class Success(
        val returnCode: String
    ) : CouponUseResponseStatus()

    @Serializable
    data class Error(
        @SerialName("returnCode")
        val errorMessage: String
    ) : CouponUseResponseStatus()

    @Serializable
    data class RequestLimited(
        @SerialName("returnCode")
        val errorMessage: String
    ) : CouponUseResponseStatus()


    @Serializable
    data class ServerError(
        @SerialName("returnCode")
        val errorMessage: String
    ) : CouponUseResponseStatus()

    @Serializable
    data class ServerMaintenance(
        @SerialName("returnCode")
        val errorMessage: String
    ) : CouponUseResponseStatus()

}