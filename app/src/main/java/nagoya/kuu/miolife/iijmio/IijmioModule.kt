package nagoya.kuu.miolife.iijmio

import nagoya.kuu.miolife.iijmio.local.APILocalService
import nagoya.kuu.miolife.iijmio.local.APILocalServiceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val iijmioModule = module {
    factory { APIServiceImpl(androidContext()) as APIService }
    factory { APILocalServiceImpl(androidContext()) as APILocalService }
    factory { LoginServiceImpl() as LoginService }
}