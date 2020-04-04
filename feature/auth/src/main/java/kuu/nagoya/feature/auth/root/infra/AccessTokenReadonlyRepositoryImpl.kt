package kuu.nagoya.feature.auth.root.infra

import kuu.nagoya.core.config.AccessTokenObject
import kuu.nagoya.feature.auth.root.domain.AccessTokenReadonlyRepository

class AccessTokenReadonlyRepositoryImpl : AccessTokenReadonlyRepository {
    override suspend fun loadAccessToken(): String {
        return AccessTokenObject.accessToken
    }
}