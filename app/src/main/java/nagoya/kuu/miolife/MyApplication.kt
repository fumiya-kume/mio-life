package nagoya.kuu.miolife

import android.app.Application
import nagoya.kuu.miolife.credential.credentialModule
import nagoya.kuu.miolife.iijmio.iijmioModule
import nagoya.kuu.miolife.ui.main.mainModule
import nagoya.kuu.miolife.ui.main.setting.settingModule
import nagoya.kuu.miolife.ui.main.sim.simModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    val modules = listOf(
        mainModule,
        iijmioModule,
        simModule,
        settingModule,
        credentialModule
    )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(modules)
        }
    }
}