package kuu.nagoya.feature.dashboard

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kuu.nagoya.feature.dashboard.viewentity.SimViewEntity

internal class SimListAdapter(private val context: Context) :
    ListAdapter<SimViewEntity, SimListViewHolder>(DIFF_UTIL) {

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<SimViewEntity>() {
            override fun areItemsTheSame(oldItem: SimViewEntity, newItem: SimViewEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: SimViewEntity,
                newItem: SimViewEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimListViewHolder {
        return SimListViewHolder.create(context, parent)
    }

    override fun onBindViewHolder(holder: SimListViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}