package nagoya.kuu.miolife.ui.main

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import nagoya.kuu.miolife.ui.main.viewentity.ContractViewEntity

internal class ContractAdapter(
    private val context: Context
) : ListAdapter<ContractViewEntity, ContractViewHolder>(DIFF_UTIL) {
    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<ContractViewEntity>() {
            override fun areItemsTheSame(
                oldItem: ContractViewEntity,
                newItem: ContractViewEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ContractViewEntity,
                newItem: ContractViewEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    var simOnclikedListener: SimOnclikedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContractViewHolder {
        return ContractViewHolder.create(context, parent)
    }

    override fun onBindViewHolder(holder: ContractViewHolder, position: Int) {
        holder.bindTo(getItem(position), simOnclikedListener, context)
    }
}
