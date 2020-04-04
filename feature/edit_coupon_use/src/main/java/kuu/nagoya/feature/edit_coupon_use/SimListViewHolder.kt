package kuu.nagoya.feature.edit_coupon_use

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kuu.nagoya.feature.edit_coupon_use.databinding.EditCouponUseItemSimBinding
import kuu.nagoya.feature.edit_coupon_use.entity.SimEntity

internal class SimListViewHolder private constructor(
    private val binding: EditCouponUseItemSimBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(
            context: Context,
            container: ViewGroup
        ): SimListViewHolder {
            return SimListViewHolder(
                EditCouponUseItemSimBinding.inflate(
                    LayoutInflater.from(context),
                    container,
                    false
                )
            )
        }
    }

    fun bindTo(simEntity: SimEntity) {
        binding.entity = simEntity
    }
}