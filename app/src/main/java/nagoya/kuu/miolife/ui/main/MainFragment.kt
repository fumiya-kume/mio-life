package nagoya.kuu.miolife.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import nagoya.kuu.miolife.R
import nagoya.kuu.miolife.databinding.MainFragmentBinding
import nagoya.kuu.miolife.ui.main.sim.SimDetailDialog
import nagoya.kuu.miolife.ui.main.viewentity.SimViewEntity
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = MainFragmentBinding.inflate(
            layoutInflater,
            container,
            false
        )

        val contractAdapter = ContractAdapter(requireContext())

        binding.contractRecyclerView.adapter = contractAdapter

        contractAdapter.simOnclikedListener = object : SimOnclikedListener {
            override fun onclick(simViewEntity: SimViewEntity) {
                val dialog = SimDetailDialog(simViewEntity.hdoServiceCode)
                dialog.show(requireFragmentManager(), "")
            }
        }

        viewModel
            .contractList
            .observeForever {
                binding.mainSwipeToRefresh.isRefreshing = false
                when (it) {
                    is ContractListStatus.Success -> contractAdapter.submitList(it.contractList)
                    ContractListStatus.LoginRequired -> viewModel.login(this)
                    is ContractListStatus.ErrorHappened -> {
                        Toast.makeText(
                            requireContext(),
                            it.errorMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        binding.mainSwipeToRefresh.setOnRefreshListener {
            viewModel.fetchCouponData()
        }

        binding
            .settingButton
            .setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_settingRootFragment)
            }

        return binding.root
    }
}
