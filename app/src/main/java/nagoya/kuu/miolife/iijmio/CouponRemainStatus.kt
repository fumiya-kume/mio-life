package nagoya.kuu.miolife.iijmio

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed class CouponRemainStatus {

    @Serializable
    data class CouponResponse(
        val volume: Int = 0,
        val expire: String? = "",
        val type: String = ""
    )

    @Serializable
    data class HdoInfoResponse(
        val couponUse: Boolean,
        @SerialName("coupon")
        val couponResponseList: List<CouponResponse>,
        val hdoServiceCode: String,
        val sms: Boolean,
        val number: String,
        val regulation: Boolean,
        val iccid: String,
        val voice: Boolean
    )

    @Serializable()
    data class CouponInfoResponse(
        val hddServiceCode: String,
        @SerialName("coupon")
        val couponResponseList: List<CouponResponse>,
        @SerialName("hdoInfo")
        val hdoInfoResponseList: List<HdoInfoResponse>,
        @SerialName("plan")
        val planName: String
    )

    @Serializable
    data class Success(
        @SerialName("couponInfo")
        val couponInfoResponse: List<CouponInfoResponse>,
        val returnCode: String
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