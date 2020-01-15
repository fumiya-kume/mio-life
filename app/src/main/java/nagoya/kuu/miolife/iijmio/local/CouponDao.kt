package nagoya.kuu.miolife.iijmio.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CouponDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCoupon(coupon: Coupon)

    @Query("select * from coupon where hdd_service_code=:hddServiceCode")
    fun getCoupon(hddServiceCode: String): List<Coupon>
}