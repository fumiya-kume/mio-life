package nagoya.kuu.miolife.ui.main.sim

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val simModule = module {
    factory { UseVolumeLogListLiveDataFactory(get()) }
    factory { CouponSwitchLiveDataFactory(get(), get()) }

    viewModel { (hdoServiceCode: String) -> SimDetailDialogViewModel(hdoServiceCode, get(), get()) }
}