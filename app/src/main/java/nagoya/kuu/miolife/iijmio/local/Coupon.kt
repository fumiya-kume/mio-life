package nagoya.kuu.miolife.iijmio.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "coupon",
    foreignKeys = [ForeignKey(
        entity = CouponRemainEntity::class,
        parentColumns = ["hdd_service_code"],
        childColumns = ["hdd_service_code"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Coupon(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "hdd_service_code")
    val hddServiceCode: String,
    val volume: Int,
    val expire: Int?,
    val type: String
)