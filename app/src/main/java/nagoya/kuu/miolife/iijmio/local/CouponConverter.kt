package nagoya.kuu.miolife.iijmio.local

import nagoya.kuu.miolife.iijmio.model.contract.CouponModel

fun Coupon.convertBack(): CouponModel {
    return CouponModel(
        this.volume,
        this.hddServiceCode,
        this.type
    )
}

fun CouponModel.convert(hddServiceCode: String): Coupon {
    return Coupon(
        0,
        hddServiceCode,
        this.volume,
        this.expire?.toIntOrNull() ?: 0,
        this.type
    )
}