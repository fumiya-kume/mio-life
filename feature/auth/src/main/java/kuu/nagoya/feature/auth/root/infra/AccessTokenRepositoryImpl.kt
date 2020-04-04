package kuu.nagoya.feature.auth.root.infra

import kuu.nagoya.core.config.AccessTokenObject
import kuu.nagoya.feature.auth.root.domain.AccessTokenRepository

internal class AccessTokenRepositoryImpl :
    AccessTokenRepository {
    override suspend fun storeAccessToken(accessToken: String) {
        AccessTokenObject.accessToken = accessToken
    }
}