package nagoya.kuu.miolife.iijmio.local

interface APILocalService {
    fun addCouponRemainEntitiy(couponRemainEntity: CouponRemainEntity)
    fun insertOrUpdateCouponRemainEntity(couponRemainEntity: CouponRemainEntity)
    fun getCouponList(couponRemainEntity: CouponRemainEntity): List<Coupon>
    fun getAllCouponRemainList(): List<CouponAndHdo>
}