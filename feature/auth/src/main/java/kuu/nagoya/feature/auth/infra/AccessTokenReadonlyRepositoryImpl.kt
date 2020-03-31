package kuu.nagoya.feature.auth.infra

import kuu.nagoya.core.config.AccessTokenObject
import kuu.nagoya.feature.auth.domain.AccessTokenReadonlyRepository

class AccessTokenReadonlyRepositoryImpl : AccessTokenReadonlyRepository {
    override suspend fun loadAccessToken(): String {
        return AccessTokenObject.accessToken
    }
}