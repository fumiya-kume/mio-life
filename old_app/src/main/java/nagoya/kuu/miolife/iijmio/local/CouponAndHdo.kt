package nagoya.kuu.miolife.iijmio.local

import androidx.room.Embedded
import androidx.room.Relation

class CouponAndHdo {
    @Embedded
    lateinit var couponRemainEntity: CouponRemainEntity

    @Relation(
        entity = HdoInfo::class,
        parentColumn = "hdd_service_code",
        entityColumn = "hdd_service_code"
    )
    lateinit var hdoInfoList: List<HdoInfo>

    @Relation(
        entity = HduInfo::class,
        parentColumn = "hdd_service_code",
        entityColumn = "hdd_service_code"
    )
    lateinit var hduInfoList: List<HduInfo>

    @Relation(
        entity = HdxInfo::class,
        parentColumn = "hdd_service_code",
        entityColumn = "hdd_service_code"
    )
    lateinit var hdxInfoList: List<HdxInfo>

    @Relation(
        entity = Coupon::class,
        parentColumn = "hdd_service_code",
        entityColumn = "hdd_service_code"
    )
    lateinit var couponList: List<Coupon>
}