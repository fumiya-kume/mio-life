package kuu.nagoya.feature.auth.domain

internal interface AccessTokenReadonlyRepository {
    suspend fun loadAccessToken(): String
}