package kuu.nagoya.feature.auth.root.domain

interface AccessTokenRepository {
    suspend fun storeAccessToken(accessToken: String)
}