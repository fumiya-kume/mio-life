package kuu.nagoya.miolife

import kuu.nagoya.resources.AppConfig

class AppConfigImpl() : AppConfig {

    override val iijmioDeveloperId: String = BuildConfig.DEVELOPER_ID
}