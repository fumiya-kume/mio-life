package kuu.nagoya.core.config

import com.chibatching.kotpref.KotprefModel

object AccessTokenObject : KotprefModel() {
    var accessToken by stringPref()
}