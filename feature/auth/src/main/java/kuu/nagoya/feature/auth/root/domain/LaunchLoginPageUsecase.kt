package kuu.nagoya.feature.auth.root.domain

import androidx.fragment.app.Fragment

internal interface LaunchLoginPageUsecase {
    fun execute(fragment: Fragment)
}