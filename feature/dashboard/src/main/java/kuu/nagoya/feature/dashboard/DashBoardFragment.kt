package kuu.nagoya.feature.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kuu.nagoya.feature.dashboard.databinding.DashboardFragmentBinding

class DashBoardFragment : Fragment(R.layout.dashboard_fragment) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DashboardFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }
}