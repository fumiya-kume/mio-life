package kuu.nagoya.feature.auth

import androidx.fragment.app.Fragment

interface AuthModuleNavigation {
    fun navigateToDashboard(fragment: Fragment)

    fun navigateAuthCallbackToDashboard(fragment: Fragment)
}