package kuu.nagoya.feature.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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


        dashBoardFragmentViewModel.simListLiveData.observeForever {
            simListAdapter.submitList(it)
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