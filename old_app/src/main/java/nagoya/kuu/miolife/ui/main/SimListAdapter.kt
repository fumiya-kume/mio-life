package nagoya.kuu.miolife.ui.main

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import nagoya.kuu.miolife.ui.main.viewentity.SimViewEntity

class SimListAdapter(
    private val context: Context
) : ListAdapter<SimViewEntity, SimListViewHolder>(DIFF_UTIL) {
    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<SimViewEntity>() {
            override fun areItemsTheSame(oldItem: SimViewEntity, newItem: SimViewEntity): Boolean {
                return oldItem.phoneNumber == newItem.phoneNumber
            }

            override fun areContentsTheSame(
                oldItem: SimViewEntity,
                newItem: SimViewEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    var simOnclikedListener: SimOnclikedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimListViewHolder {
        return SimListViewHolder.create(context, parent)
    }

    override fun onBindViewHolder(holder: SimListViewHolder, position: Int) {
        holder.bindTo(getItem(position), simOnclikedListener)
    }
}