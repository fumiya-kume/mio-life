package kuu.nagoya.feature.edit_coupon_use

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kuu.nagoya.feature.edit_coupon_use.entity.SimEntity

internal class SimListAdapter(
    private val context: Context
) : ListAdapter<SimEntity, SimListViewHolder>(DIFF_UTIL) {
    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<SimEntity>() {
            override fun areItemsTheSame(oldItem: SimEntity, newItem: SimEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SimEntity, newItem: SimEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimListViewHolder {
        return SimListViewHolder.create(
            context = context,
            container = parent
        )
    }

    override fun onBindViewHolder(holder: SimListViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}
