package wannabit.io.cosmostaion.ui.dialog.tx

import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.data.model.res.NameService
import wannabit.io.cosmostaion.databinding.ItemNameServiceBinding

class NameServiceViewHolder(
    private val binding: ItemNameServiceBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(nameService: NameService) {
        binding.apply {
            accountAddress.text = nameService.address
        }
    }
}