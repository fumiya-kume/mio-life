package nagoya.kuu.miolife.credential

interface AccessTokenRepository {
    fun loadAccessToken(): String?

    fun storeAccessToken(accessToken: String?)
}