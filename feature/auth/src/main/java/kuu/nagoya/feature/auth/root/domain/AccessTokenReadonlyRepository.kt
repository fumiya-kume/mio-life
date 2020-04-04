package kuu.nagoya.feature.auth.root.domain

internal interface AccessTokenReadonlyRepository {
    suspend fun loadAccessToken(): String
}