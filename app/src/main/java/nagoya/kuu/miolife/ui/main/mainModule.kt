package nagoya.kuu.miolife.ui.main

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory { ContractListLiveDataFactory(get(), get()) }

    viewModel { MainViewModel(get(), get()) }
}