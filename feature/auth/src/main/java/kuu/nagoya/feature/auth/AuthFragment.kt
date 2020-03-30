package kuu.nagoya.feature.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kuu.nagoya.feature.auth.databinding.AuthFragmentAuthBinding
import kuu.nagoya.feature.auth.domain.HasAuthUsecaseResponse
import kuu.nagoya.feature.auth.domain.LaunchLoginPageUsecase
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class AuthFragment : Fragment(R.layout.auth_fragment_auth) {

    private val authFragmentViewModel: AuthFragmentViewModel by viewModel()
    private val launchLoginPageUsecase: LaunchLoginPageUsecase by inject()
    private val authModuleNavigation: AuthModuleNavigation by inject()

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

        return binding.root
    }
}
