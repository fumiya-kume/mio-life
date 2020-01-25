package nagoya.kuu.miolife.ui.main.setting

import nagoya.kuu.miolife.iijmio.accesstoken.AccessTokenRepository

internal class AccessTokenLiveDataFactory(
    private val accessTokenRepository: AccessTokenRepository
) {
    fun create(): AccessTokenLiveData {
        return AccessTokenLiveData(accessTokenRepository)
    }
}