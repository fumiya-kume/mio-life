package kuu.nagoya.feature.auth.domain

import androidx.fragment.app.Fragment

internal interface LaunchLoginPageUsecase {
    fun execute(fragment: Fragment)
}