package nagoya.kuu.miolife.ui.main.setting

sealed class AccessTokenLiveDataStatus {
    object Loading : AccessTokenLiveDataStatus()
    data class Success(val accessToken: String) : AccessTokenLiveDataStatus()
    data class Failed(val errorMessage: String) : AccessTokenLiveDataStatus()
}