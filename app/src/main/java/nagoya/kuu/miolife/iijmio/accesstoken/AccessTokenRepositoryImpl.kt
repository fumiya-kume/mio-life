package nagoya.kuu.miolife.iijmio.accesstoken

import android.content.Context
import android.content.SharedPreferences

class AccessTokenRepositoryImpl(
    private val context: Context
) : AccessTokenRepository {
    private val preference: SharedPreferences =
        context.getSharedPreferences("access_Token", Context.MODE_PRIVATE)

    private val preferenceKey = "access_token"

    override fun loadAccessToken(): String? {
        return preference.getString(preferenceKey, "")
    }

    override fun storeAccessToken(accessToken: String?) {
        accessToken ?: return
        preference.edit().apply {
            putString(preferenceKey, accessToken)
            apply()
        }
    }
}