package kuu.nagoya.feature.auth.domain.impl

import android.content.Intent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import kuu.nagoya.feature.auth.domain.LaunchLoginPageUsecase
import kuu.nagoya.resources.AppConfig

class LaunchLoginPageUsecaseImpl(
    private val appConfig: AppConfig
) : LaunchLoginPageUsecase {
    override fun execute(fragment: Fragment) {
        fun getLoginUrl(): String {
            val developerId = appConfig.iijmioDeveloperId
            return "https://api.iijmio.jp/mobile/d/v1/authorization/?response_type=token&client_id=${developerId}&state=example_state&redirect_uri=mio-life://login"
        }

        val customTabsIntent = CustomTabsIntent.Builder().enableUrlBarHiding().build()

        customTabsIntent.launchUrl(fragment.requireContext(), getLoginUrl().toUri())
        fragment.requireContext().startActivity(
            customTabsIntent.intent.apply {
                data = getLoginUrl().toUri()
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
        )
    }
}