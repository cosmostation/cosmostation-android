package wannabit.io.cosmostaion.ui.main

import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.ItemDashBinding


class DashboardViewHolder(
    private val binding: ItemDashBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.apply {
            dashView.setBackgroundResource(R.drawable.item_dash_bg)
        }
    }
}









