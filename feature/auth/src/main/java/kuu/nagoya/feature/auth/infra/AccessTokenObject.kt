package kuu.nagoya.feature.auth.infra

import com.chibatching.kotpref.KotprefModel

object AccessTokenObject : KotprefModel() {
    var accessToken by stringPref()
}