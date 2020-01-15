package nagoya.kuu.miolife

import android.content.Intent
import android.os.Bundle
import androidx.core.net.toUri
import androidx.fragment.app.FragmentActivity
import nagoya.kuu.miolife.iijmio.AccessTokenRepository

internal class LoginActivity : FragmentActivity(R.layout.login_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val accessTokenRepository: AccessTokenRepository =
            AccessTokenRepository(applicationContext)

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
