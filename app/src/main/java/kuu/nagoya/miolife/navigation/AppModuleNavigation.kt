package kuu.nagoya.miolife.navigation

import androidx.fragment.app.Fragment

interface AppModuleNavigation {
    fun navigateToDashboard(fragment: Fragment)
    fun navigateToAuth(fragment: Fragment)
}