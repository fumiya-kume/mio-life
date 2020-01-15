package nagoya.kuu.miolife.iijmio.local

import android.content.Context

class APILocalServiceImpl(
    private val context: Context
) : APILocalService {
    private val couponRemainDatabase: CouponRemainDatabase =
        CouponRemainDatabase.getDatabase(context)

    override fun addCouponRemainEntitiy(couponRemainEntity: CouponRemainEntity) {
        return couponRemainDatabase.couponRemainDao().insertCouponRemainDao(couponRemainEntity)
    }

    override fun insertOrUpdateCouponRemainEntity(couponRemainEntity: CouponRemainEntity) {
        val couponRemainList = couponRemainDatabase.couponRemainDao()
            .getCouponRemainDao(couponRemainEntity.hddServiceCode)
        val hasCouponRemainList = couponRemainList.isNotEmpty()
        if (hasCouponRemainList) {
            couponRemainDatabase.couponRemainDao().updateCouponRemainDao(couponRemainEntity)
        } else {
            couponRemainDatabase.couponRemainDao().insertCouponRemainDao(couponRemainEntity)
        }
    }

    override fun getCouponList(couponRemainEntity: CouponRemainEntity): List<Coupon> {
        val hddServiceCode = couponRemainEntity.hddServiceCode
        return couponRemainDatabase.CouponDao().getCoupon(hddServiceCode)
    }

    override fun getAllCouponRemainList(): List<CouponAndHdo> {
        return couponRemainDatabase.couponRemainDao().getAllCouponRemainDao()
    }
}