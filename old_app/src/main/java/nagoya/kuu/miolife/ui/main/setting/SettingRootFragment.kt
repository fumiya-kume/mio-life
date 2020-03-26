package nagoya.kuu.miolife.ui.main.setting

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import nagoya.kuu.miolife.R
import org.koin.android.viewmodel.ext.android.viewModel

class SettingRootFragment : PreferenceFragmentCompat() {

    private val viewModel: SettingRootViewModel by viewModel()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        // Load empty preference resources
        addPreferencesFromResource(R.xml.root)

        // Auth Preference
        PreferenceCategory(preferenceScreen.context)
            .apply {
                title = "認証情報"
            }.also {
                preferenceScreen.addPreference(
                    it
                )
                it.addPreference(
                    // preference for Access token
                    Preference(preferenceScreen.context).apply {
                        val preference = this
                        title = "アクセストークン"

                        viewModel.accessToken.observeForever {
                            preference.summary = when (it) {
                                AccessTokenLiveDataStatus.Loading -> "読み込み中"
                                is AccessTokenLiveDataStatus.Success -> it.accessToken
                                is AccessTokenLiveDataStatus.Failed -> it.errorMessage
                            }
                        }

                        setOnPreferenceClickListener {
                            // Confirm clear Accesstoken
                            AlertDialog
                                .Builder(context)
                                .setTitle("認証情報を消去しますか？")
                                .setMessage("再度ログインすることで認証情報を再度取得することができます")
                                .setPositiveButton("クリア") { _, _ ->
                                    viewModel.clearAccessToken()
                                }.setNegativeButton("キャンセル") { _, _ ->
                                    // Nothing
                                }
                                .show()

                            true
                        }
                    }
                )
            }

        // App Prefernece
        PreferenceCategory(preferenceScreen.context)
            .apply {
                title = "アプリ情報"
            }.also {
                preferenceScreen.addPreference(it)


                it.addPreference(
                    Preference(preferenceScreen.context).apply {
                        title = "ライセンス情報"
                        setOnPreferenceClickListener {
                            val intent = Intent(context, OssLicensesMenuActivity::class.java)
                            intent.putExtra("title", "ライセンス情報")
                            startActivity(intent)

                            true
                        }
                    }
                )

                it.addPreference(
                    Preference(preferenceScreen.context).apply {
                        title = "アプリについて"
                        setOnPreferenceClickListener {
                            Toast.makeText(context, "//TODO アプリのページを表示する", Toast.LENGTH_SHORT)
                                .show()

                            true
                        }
                    }
                )
            }
    }
}
