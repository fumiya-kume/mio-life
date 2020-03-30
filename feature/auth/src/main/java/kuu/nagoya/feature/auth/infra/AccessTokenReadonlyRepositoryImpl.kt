package kuu.nagoya.feature.auth.infra

import kuu.nagoya.feature.auth.domain.AccessTokenReadonlyRepository

class AccessTokenReadonlyRepositoryImpl :
    AccessTokenReadonlyRepository {
    override suspend fun loadAccessToken(): String {
        //TODO 実装を追加する
        return ""
    }
}