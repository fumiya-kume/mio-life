package kuu.nagoya.miolife

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import kuu.nagoya.miolife.navigation.AppModuleNavigation
import kuu.nagoya.miolife.navigation.AppModuleNavigationImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class MainApplication : Application() {
    val modules = listOf(
        module {
            factory { AppModuleNavigationImpl() as AppModuleNavigation }
        }
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