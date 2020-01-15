package nagoya.kuu.miolife.iijmio.local

import androidx.room.*

@Dao
interface CouponRemainDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCouponRemainDao(couponRemainEntity: CouponRemainEntity)

    @Update
    fun updateCouponRemainDao(couponRemainEntity: CouponRemainEntity)

    @Transaction
    @Query("select * from coupon_remain where hdd_service_code=:hddServiceCode")
    fun getCouponRemainDao(hddServiceCode: String): List<CouponAndHdo>

    @Transaction
    @Query("select * from coupon_remain")
    fun getAllCouponRemainDao(): List<CouponAndHdo>

}