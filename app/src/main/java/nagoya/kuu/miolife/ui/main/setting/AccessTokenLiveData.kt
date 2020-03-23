package nagoya.kuu.miolife.ui.main.setting

import androidx.lifecycle.LiveData
import nagoya.kuu.miolife.credential.AccessTokenRepository

internal class AccessTokenLiveData(
    private val accessTokenRepository: AccessTokenRepository
) : LiveData<AccessTokenLiveDataStatus>() {
    init {
        refreshAccessToken()
    }

    fun clearAccessToken() {
        accessTokenRepository.storeAccessToken("")
        refreshAccessToken()
    }

    private fun refreshAccessToken() {
        postValue(
            AccessTokenLiveDataStatus.Loading
        )

        kotlin.runCatching {
            accessTokenRepository.loadAccessToken()
        }.onSuccess {
            if (it.isNullOrEmpty()) {
                postValue(
                    AccessTokenLiveDataStatus.Failed("アクセストークンがセットされていません")
                )
            } else {
                postValue(
                    AccessTokenLiveDataStatus.Success(it)
                )
            }
        }.onFailure {
            postValue(
                AccessTokenLiveDataStatus.Failed(it.localizedMessage ?: "")
            )
        }
    }
}