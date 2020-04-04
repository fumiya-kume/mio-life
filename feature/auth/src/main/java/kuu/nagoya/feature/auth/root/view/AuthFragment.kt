package kuu.nagoya.feature.auth.root.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kuu.nagoya.feature.auth.AuthModuleNavigation
import kuu.nagoya.feature.auth.R
import kuu.nagoya.feature.auth.databinding.AuthFragmentAuthBinding
import kuu.nagoya.feature.auth.root.domain.AccessTokenRepository
import kuu.nagoya.feature.auth.root.domain.HasAuthUsecaseResponse
import kuu.nagoya.feature.auth.root.domain.LaunchLoginPageUsecase
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class AuthFragment : Fragment(R.layout.auth_fragment_auth) {

    private val authFragmentViewModel: AuthFragmentViewModel by viewModel()
    private val launchLoginPageUsecase: LaunchLoginPageUsecase by inject()
    private val authModuleNavigation: AuthModuleNavigation by inject()
    private val accessTokenRepository: AccessTokenRepository by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = AuthFragmentAuthBinding.inflate(
            layoutInflater,
            container,
            false
        )

        val intentScheme = requireActivity().intent.scheme
        intentScheme?.let {
            if (it == "mio-life") {

                fun Uri?.toAccessToken(): String? {
                    if (this == null) {
                        return null
                    }
                    return this.toString().replace("#", "?").toUri()
                        .getQueryParameter("access_token")
                }

                requireActivity().intent.data.toAccessToken()?.let {
                    this.lifecycleScope.launch {
                        accessTokenRepository.storeAccessToken(it)
                    }
                }
            }
        }

        authFragmentViewModel
            .hasAuthLiveData
            .observeForever {
                when (it) {
                    HasAuthUsecaseResponse.Loading -> {
                        binding.loadingProgressBar.visibility = View.VISIBLE
                        binding.loadingTextView.visibility = View.VISIBLE
                        binding.reloadButton.visibility = View.GONE
                    }
                    HasAuthUsecaseResponse.HasAuth -> {
                        binding.loadingProgressBar.visibility = View.GONE
                        binding.loadingTextView.visibility = View.GONE
                        binding.reloadButton.visibility = View.GONE

                        authModuleNavigation.navigateToDashboard(this)
                    }
                    HasAuthUsecaseResponse.NotHasAuth -> {
                        binding.loadingProgressBar.visibility = View.GONE
                        binding.loadingTextView.visibility = View.GONE
                        binding.reloadButton.visibility = View.GONE

                        launchLoginPageUsecase.execute(this)
                    }
                    is HasAuthUsecaseResponse.Error -> {
                        binding.loadingProgressBar.visibility = View.GONE
                        binding.loadingTextView.visibility = View.GONE
                        binding.reloadButton.visibility = View.VISIBLE

                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }

        binding.reloadButton.setOnClickListener {
            authFragmentViewModel.reloadAuthStatus()
        }

        return binding.root
    }
}
