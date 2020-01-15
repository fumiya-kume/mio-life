package nagoya.kuu.miolife.iijmio.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "hdo_info",
    foreignKeys = [ForeignKey(
        entity = CouponRemainEntity::class,
        parentColumns = ["hdd_service_code"],
        childColumns = ["hdd_service_code"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class HdoInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "hdo_service_code")
    val hdoServiceCode: String,
    @ColumnInfo(name = "hdd_service_code")
    val hddServiceCode: String,
    @ColumnInfo(name = "coupon_use")
    val couponUse: Boolean,
    @ColumnInfo(name = "sim_volume")
    val simVolume: Int,
    val sms: Boolean,
    val number: String,
    val regulation: Boolean,
    @ColumnInfo(name = "icc_id")
    val iccId: String,
    val voice: Boolean
)