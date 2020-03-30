package kuu.nagoya.feature.auth

import kuu.nagoya.feature.auth.domain.AccessTokenReadonlyRepository
import kuu.nagoya.feature.auth.domain.HasAuthUsecase
import kuu.nagoya.feature.auth.domain.LaunchLoginPageUsecase
import kuu.nagoya.feature.auth.domain.impl.HasAuthUsecaseImpl
import kuu.nagoya.feature.auth.domain.impl.LaunchLoginPageUsecaseImpl
import kuu.nagoya.feature.auth.infra.AccessTokenReadonlyRepositoryImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    // Domain Layer
    factory { HasAuthUsecaseImpl(get()) as HasAuthUsecase }
    factory { LaunchLoginPageUsecaseImpl(get()) as LaunchLoginPageUsecase }

    // infra layer
    factory { AccessTokenReadonlyRepositoryImpl() as AccessTokenReadonlyRepository }

    // View Layer
    factory { HasAuthLiveDataFactory(get()) }
    viewModel { AuthFragmentViewModel(get()) }
}