package kuu.nagoya.miolife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import kuu.nagoya.miolife.databinding.FragmentMainBinding
import kuu.nagoya.miolife.navigation.AppModuleNavigation
import org.koin.android.ext.android.inject

class MainFragment : Fragment(R.layout.fragment_main) {

    @VisibleForTesting
    private val appModuleNavigation: AppModuleNavigation by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(
            layoutInflater,
            container,
            false
        )

        binding.navigateToDashboardButton
            .setOnClickListener {
                appModuleNavigation.navigateToDashboard(this)
            }

        return binding.root
    }
}