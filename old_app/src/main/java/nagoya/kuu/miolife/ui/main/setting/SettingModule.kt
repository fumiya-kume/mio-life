package nagoya.kuu.miolife.ui.main.setting

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingModule = module {

    // LiveDatas
    factory { AccessTokenLiveDataFactory(get()) }

    // ViewModels
    viewModel { SettingRootViewModel(get()) }
}