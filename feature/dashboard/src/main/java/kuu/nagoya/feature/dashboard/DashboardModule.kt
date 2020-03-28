package kuu.nagoya.feature.dashboard

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashboardModule = module {
    viewModel { DashBoardFragmentViewModel() }
}