package nagoya.kuu.miolife.iijmio.local

import androidx.room.*

@Dao
interface CouponDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCoupon(coupon: Coupon)

    @Update
    fun updateCoupon(coupon: Coupon)

    @Query("select * from coupon where hdd_service_code=:hddServiceCode")
    fun getCoupon(hddServiceCode: String): List<Coupon>
}