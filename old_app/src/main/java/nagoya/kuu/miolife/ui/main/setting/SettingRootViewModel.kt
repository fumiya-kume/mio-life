package nagoya.kuu.miolife.ui.main.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

internal class SettingRootViewModel(
    private val accessTokenLiveDataFactory: AccessTokenLiveDataFactory
) : ViewModel() {
    private val accessTokenLivedata = accessTokenLiveDataFactory.create()
    val accessToken:LiveData<AccessTokenLiveDataStatus> = accessTokenLivedata

    fun clearAccessToken(){
        accessTokenLivedata.clearAccessToken()
    }
}
