package nagoya.kuu.miolife.credential

import android.content.Context
import android.content.SharedPreferences

internal class AccessTokenRepositoryImpl(
    context: Context
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
