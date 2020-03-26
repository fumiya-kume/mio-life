package nagoya.kuu.miolife.iijmio.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HdoInfoDao {
    @Insert
    fun addHdoInfo(hdoInfo: HdoInfo)

    @Update
    fun updateHdoInfo(hdoInfo: HdoInfo)

    @Query("select * from hdo_info where hdd_service_code=:hddServiceCode")
    fun getHdoInfo(hddServiceCode: String): List<HdoInfo>
}