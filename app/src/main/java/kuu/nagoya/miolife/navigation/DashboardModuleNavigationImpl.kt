package kuu.nagoya.miolife.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kuu.nagoya.feature.dashboard.DashboardModuleNavigation
import kuu.nagoya.miolife.R

class DashboardModuleNavigationImpl : DashboardModuleNavigation {
    override fun navigateToSetting(fragment: Fragment) {
        fragment.findNavController().navigate(R.id.action_dashBoardFragment_to_settingFragment)
    }

    override fun navigateToEditCouponUse(fragment: Fragment) {
        fragment.findNavController()
            .navigate(R.id.action_dashBoardFragment_to_editCouponUseFragment)
    }
}