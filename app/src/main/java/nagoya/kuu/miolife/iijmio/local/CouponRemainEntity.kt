package nagoya.kuu.miolife.iijmio.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "coupon_remain"
)
data class CouponRemainEntity(
    @PrimaryKey
    @ColumnInfo(name = "hdd_service_code")
    val hddServiceCode: String,
    @ColumnInfo(name = "plan_name")
    val planName: String

)