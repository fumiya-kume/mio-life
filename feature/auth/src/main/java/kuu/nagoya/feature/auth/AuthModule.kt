package kuu.nagoya.feature.auth

import kuu.nagoya.feature.auth.root.domain.AccessTokenReadonlyRepository
import kuu.nagoya.feature.auth.root.domain.AccessTokenRepository
import kuu.nagoya.feature.auth.root.domain.HasAuthUsecase
import kuu.nagoya.feature.auth.root.domain.LaunchLoginPageUsecase
import kuu.nagoya.feature.auth.root.domain.impl.HasAuthUsecaseImpl
import kuu.nagoya.feature.auth.root.domain.impl.LaunchLoginPageUsecaseImpl
import kuu.nagoya.feature.auth.root.infra.AccessTokenReadonlyRepositoryImpl
import kuu.nagoya.feature.auth.root.infra.AccessTokenRepositoryImpl
import kuu.nagoya.feature.auth.root.view.AuthFragmentViewModel
import kuu.nagoya.feature.auth.root.view.HasAuthLiveDataFactory
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    // Domain Layer
    factory { HasAuthUsecaseImpl(get()) as HasAuthUsecase }
    factory { LaunchLoginPageUsecaseImpl(get()) as LaunchLoginPageUsecase }

    // infra layer
    factory { AccessTokenReadonlyRepositoryImpl() as AccessTokenReadonlyRepository }
    factory { AccessTokenRepositoryImpl() as AccessTokenRepository }

    // View Layer
    factory { HasAuthLiveDataFactory(get()) }
    viewModel { AuthFragmentViewModel(get()) }

}