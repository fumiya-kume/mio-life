package nagoya.kuu.miolife.iijmio.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(CouponRemainEntity::class, Coupon::class, HdoInfo::class),
    version = 1,
    exportSchema = false
)
abstract class CouponRemainDatabase : RoomDatabase() {
    abstract fun couponRemainDao(): CouponRemainDao
    abstract fun CouponDao(): CouponDao
    abstract fun HdoInfoDao(): HdoInfoDao

    companion object {
        @Volatile
        private var INSTANCE: CouponRemainDatabase? = null

        fun getDatabase(context: Context): CouponRemainDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CouponRemainDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}