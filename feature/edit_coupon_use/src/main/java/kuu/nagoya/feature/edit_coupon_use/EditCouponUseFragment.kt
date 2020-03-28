package kuu.nagoya.feature.edit_coupon_use

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kuu.nagoya.feature.edit_coupon_use.databinding.FragmentEditCouponUseBinding
import org.koin.android.viewmodel.ext.android.viewModel

class EditCouponUseFragment : Fragment(R.layout.fragment_edit_coupon_use) {

    private val editCouponFragmentViewModel: EditCouponUseFragmentViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEditCouponUseBinding.inflate(
            layoutInflater,
            container,
            false
        )

        val simListAdapter = SimListAdapter(requireContext())

        editCouponFragmentViewModel.simListLiveData.observeForever {
            simListAdapter.submitList(it)
        }

        binding.simListRecyclerView.adapter = simListAdapter

        binding.saveAndCloseButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}