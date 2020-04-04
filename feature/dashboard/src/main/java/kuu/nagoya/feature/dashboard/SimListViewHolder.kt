package kuu.nagoya.feature.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kuu.nagoya.feature.dashboard.databinding.DashboardItemSim
import kuu.nagoya.feature.dashboard.viewentity.SimViewEntity

internal class SimListViewHolder private constructor(
    private val binding: DashboardItemSim
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(
            context: Context,
            container: ViewGroup
        ): SimListViewHolder {
            return SimListViewHolder(
                DashboardItemSim.inflate(
                    LayoutInflater.from(context),
                    container,
                    false
                )
            )
        }
    }

    fun bindTo(simViewEntity: SimViewEntity) {
        binding.entity = simViewEntity

        binding.root.setOnClickListener { }
    }
}
