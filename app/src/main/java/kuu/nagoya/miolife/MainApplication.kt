package kuu.nagoya.miolife

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import kuu.nagoya.feature.auth.authModule
import kuu.nagoya.feature.dashboard.dashboardModule
import kuu.nagoya.feature.edit_coupon_use.editCouponUseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class MainApplication : Application() {
    private val modules = listOf(
        authModule,
        appModule,
        dashboardModule,
        editCouponUseModule
    )

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(applicationContext)

        startKoin {
            androidContext(this@MainApplication)
            modules(modules)
        }
    }

    override fun onTerminate() {
        super.onTerminate()

        stopKoin()
    }
}