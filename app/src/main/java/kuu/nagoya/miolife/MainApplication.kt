package kuu.nagoya.miolife

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class MainApplication : Application() {
    private val modules = listOf(
        appModule
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