package nagoya.kuu.miolife

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import nagoya.kuu.miolife.credential.AccessTokenRepository
import nagoya.kuu.miolife.credential.toAccessToken
import org.koin.android.ext.android.inject

internal class LoginActivity : FragmentActivity(R.layout.login_activity) {

    private val accessTokenRepository: AccessTokenRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        accessTokenRepository.storeAccessToken(
            intent.data?.toAccessToken()
        )

        startActivity(
            Intent(
                applicationContext,
                MainActivity::class.java
            )
        )
    }
}
