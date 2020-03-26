package nagoya.kuu.miolife.credential

import android.net.Uri
import androidx.core.net.toUri

fun Uri?.toAccessToken(): String? {
    if (this == null) {
        return null
    }
    return this.toString().replace("#", "?").toUri().getQueryParameter("access_token")
}