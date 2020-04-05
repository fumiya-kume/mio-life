package kuu.nagoya.feature.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kuu.nagoya.core.config.LiveDataResponse
import kuu.nagoya.feature.dashboard.databinding.FragmentDashboardBinding
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

internal class DashBoardFragment : Fragment(R.layout.fragment_dashboard) {

    private val dashBoardFragmentViewModel: DashBoardFragmentViewModel by viewModel()
    private val dashBoardModuleNavigation: DashboardModuleNavigation by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDashboardBinding.inflate(
            inflater,
            container,
            false
        )

        val simListAdapter = SimListAdapter(requireContext())


        dashBoardFragmentViewModel.simViewEntityLiveData.observeForever {
            when (it) {
                LiveDataResponse.Loading -> {
                    binding.simListProgressRing.visibility = View.VISIBLE
                }
                is LiveDataResponse.Success -> {
                    simListAdapter.submitList(it.value)
                    binding.simListProgressRing.visibility = View.GONE
                }
                is LiveDataResponse.Error -> {
                    Toast.makeText(
                        requireContext(),
                        it.exception.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                    binding.simListProgressRing.visibility = View.GONE
                }
            }
        }

        dashBoardFragmentViewModel
            .contactLiveData
            .observeForever {
                when (it) {
                    LiveDataResponse.Loading -> {
                        binding.loadingContractProgressRing.visibility = View.VISIBLE
                    }
                    is LiveDataResponse.Success -> {
                        binding.viewEntity = it.value

                        binding.loadingContractProgressRing.visibility = View.GONE
                    }
                    is LiveDataResponse.Error -> {
                        Toast.makeText(
                            requireContext(),
                            it.exception.localizedMessage,
                            Toast.LENGTH_LONG
                        ).show()

                        binding.loadingContractProgressRing.visibility = View.GONE
                    }
                }
            }

        binding.simListRecyclerView.adapter = simListAdapter

        binding.navigateSettingPageButton.setOnClickListener {
            dashBoardModuleNavigation.navigateToSetting(this)
        }

        binding.editCouponButton.setOnClickListener {
            dashBoardModuleNavigation.navigateToEditCouponUse(this)
        }

        return binding.root
    }
}