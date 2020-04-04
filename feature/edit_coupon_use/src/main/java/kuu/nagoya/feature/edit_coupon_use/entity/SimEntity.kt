package kuu.nagoya.feature.edit_coupon_use.entity

internal data class SimEntity(
    val id: Int,
    val simCode: String,
    val phoneNumber: String,
    val couponUse: Boolean
)