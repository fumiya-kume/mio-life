package nagoya.kuu.miolife.iijmio.accesstoken

interface AccessTokenRepository {
    fun loadAccessToken(): String?

    fun storeAccessToken(accessToken: String?)
}