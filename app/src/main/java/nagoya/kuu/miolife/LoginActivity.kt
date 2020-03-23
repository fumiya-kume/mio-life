package nagoya.kuu.miolife

import android.content.Intent
import android.os.Bundle
import androidx.core.net.toUri
import androidx.fragment.app.FragmentActivity
import nagoya.kuu.miolife.credential.AccessTokenRepository
import org.koin.android.ext.android.inject

internal class LoginActivity : FragmentActivity(R.layout.login_activity) {

    private val accessTokenRepository: AccessTokenRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        accessTokenRepository.storeAccessToken(
            intent.data.toString().replace("#", "?").toUri().getQueryParameter("access_token")
        )

        startActivity(
            Intent(
                applicationContext,
                MainActivity::class.java
            )
        )
    }
}
