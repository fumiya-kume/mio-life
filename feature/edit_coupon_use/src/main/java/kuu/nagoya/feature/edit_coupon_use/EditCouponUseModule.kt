package kuu.nagoya.feature.edit_coupon_use

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val editCouponUseModule = module {
    viewModel { EditCouponUseFragmentViewModel() }
}