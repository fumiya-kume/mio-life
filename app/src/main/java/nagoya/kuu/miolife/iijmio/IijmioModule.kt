package nagoya.kuu.miolife.iijmio

import nagoya.kuu.miolife.iijmio.accesstoken.AccessTokenRepository
import nagoya.kuu.miolife.iijmio.accesstoken.AccessTokenRepositoryImpl
import nagoya.kuu.miolife.iijmio.local.APILocalService
import nagoya.kuu.miolife.iijmio.local.APILocalServiceImpl
import nagoya.kuu.miolife.iijmio.remote.APIService
import nagoya.kuu.miolife.iijmio.remote.APIServiceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val iijmioModule = module {
    factory { APIServiceImpl(androidContext(), get()) as APIService }
    factory { APILocalServiceImpl(androidContext()) as APILocalService }
    factory { LoginServiceImpl() as LoginService }
    factory { AccessTokenRepositoryImpl(androidContext()) as AccessTokenRepository }
}