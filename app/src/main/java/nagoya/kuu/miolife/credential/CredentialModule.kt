package nagoya.kuu.miolife.credential

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val credentialModule = module {
    factory { AccessTokenRepositoryImpl(androidContext()) as AccessTokenRepository }
}