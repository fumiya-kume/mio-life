package nagoya.kuu.miolife.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nagoya.kuu.miolife.databinding.SimItemBinding
import nagoya.kuu.miolife.ui.main.viewentity.SimViewEntity

class SimListViewHolder private constructor(
    private val binding: SimItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(
            context: Context,
            container: ViewGroup
        ): SimListViewHolder {
            return SimListViewHolder(
                SimItemBinding.inflate(
                    LayoutInflater.from(context),
                    container,
                    false
                )
            )
        }
    }

    fun bindTo(simViewEntity: SimViewEntity) {
        binding.viewentity = simViewEntity
    }
}