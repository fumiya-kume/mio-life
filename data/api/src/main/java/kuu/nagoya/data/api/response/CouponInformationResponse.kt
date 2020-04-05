package kuu.nagoya.data.api.response

data class CouponInformationResponse(
    val returnCode: String,
    val couponInfo: List<CouponInfo>
)
