package kuu.nagoya.feature.dashboard

import kuu.nagoya.feature.dashboard.domain.ContractRepository
import kuu.nagoya.feature.dashboard.domain.SimInfoRepository
import kuu.nagoya.feature.dashboard.infra.ContractRepositoryImpl
import kuu.nagoya.feature.dashboard.infra.SimInfoRepositoryImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashboardModule = module {
    viewModel { DashBoardFragmentViewModel(get(), get()) }

    // livedata
    factory { SimListLiveDataFactory(get()) }
    factory { ContractLiveDataFactory(get()) }

    // Repository
    factory { SimInfoRepositoryImpl(get()) as SimInfoRepository }
    factory { ContractRepositoryImpl(get()) as ContractRepository }
}