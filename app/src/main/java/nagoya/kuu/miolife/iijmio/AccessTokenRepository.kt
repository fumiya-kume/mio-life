package nagoya.kuu.miolife.iijmio

import android.content.Context
import android.content.SharedPreferences

class AccessTokenRepository(
    private val context: Context
) {
    private val preference: SharedPreferences =
        context.getSharedPreferences("access_Token", Context.MODE_PRIVATE)

    private val preferenceKey = "access_token"

    fun loadAccessToken(): String? {
        return preference.getString(preferenceKey, "")
    }

    fun storeAccessToken(accessToken: String?) {
        accessToken ?: return
        preference.edit().apply {
            putString(preferenceKey, accessToken)
            apply()
        }
    }
}
