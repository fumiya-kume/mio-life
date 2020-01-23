package nagoya.kuu.miolife.iijmio.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HduInfoDao {
    @Insert
    fun addHdoInfo(hduInfo: HduInfo)

    @Update
    fun updateHdoInfo(hduInfo: HduInfo)

    @Query("select * from hdu_info where hdd_service_code=:hddServiceCode")
    fun getHdoInfo(hddServiceCode: String): List<HduInfo>
}