package nagoya.kuu.miolife.iijmio.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HdxInfoDao {
    @Insert
    fun addHdoInfo(hdxInfo: HdxInfo)

    @Update
    fun updateHdoInfo(hdxInfo: HdxInfo)

    @Query("select * from hdx_info where hdd_service_code=:hddServiceCode")
    fun getHdoInfo(hddServiceCode: String): List<HdxInfo>
}