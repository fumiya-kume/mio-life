package kuu.nagoya.miolife.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kuu.nagoya.feature.auth.AuthModuleNavigation
import kuu.nagoya.miolife.R

internal class AuthModuleNavigationImpl : AuthModuleNavigation {
    override fun navigateToDashboard(fragment: Fragment) {
        fragment.findNavController().navigate(R.id.action_authFragment_to_dashBoardFragment)
    }

    override fun navigateAuthCallbackToDashboard(fragment: Fragment) {
        fragment.findNavController().navigate(R.id.action_authCallBackFragment_to_dashBoardFragment)
    }
}