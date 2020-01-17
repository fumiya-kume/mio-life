package nagoya.kuu.miolife.iijmio

import nagoya.kuu.miolife.iijmio.accesstoken.AccessTokenRepository
import nagoya.kuu.miolife.iijmio.accesstoken.AccessTokenRepositoryImpl
import nagoya.kuu.miolife.iijmio.local.APILocalService
import nagoya.kuu.miolife.iijmio.local.APILocalServiceImpl
import nagoya.kuu.miolife.iijmio.remote.APIService
import nagoya.kuu.miolife.iijmio.remote.APIServiceImpl
import nagoya.kuu.miolife.iijmio.remote.packetlog.domain.LoadPacketlogUsecase
import nagoya.kuu.miolife.iijmio.remote.packetlog.infra.LoadPacketlogUsecaseImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val iijmioModule = module {
    factory { APIServiceImpl(get()) as APIService }
    factory { APILocalServiceImpl(androidContext()) as APILocalService }
    factory { LoginServiceImpl() as LoginService }
    factory { AccessTokenRepositoryImpl(androidContext()) as AccessTokenRepository }
    factory { LoadPacketlogUsecaseImpl(get()) as LoadPacketlogUsecase }
}