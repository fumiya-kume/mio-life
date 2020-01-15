package nagoya.kuu.miolife.iijmio.local

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface HdoInfoDao {
    @Insert
    fun addHdoInfo(hdoInfo: HdoInfo)
}