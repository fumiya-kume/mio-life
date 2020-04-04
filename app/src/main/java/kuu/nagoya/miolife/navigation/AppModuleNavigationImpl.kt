package kuu.nagoya.miolife.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kuu.nagoya.miolife.R

class AppModuleNavigationImpl : AppModuleNavigation {
    override fun navigateToDashboard(fragment: Fragment) {
        fragment.findNavController().navigate(R.id.action_mainFragment_to_dashBoardFragment)
    }

    override fun navigateToAuth(fragment: Fragment) {
        fragment.findNavController().navigate(R.id.action_mainFragment_to_authFragment)
    }
}