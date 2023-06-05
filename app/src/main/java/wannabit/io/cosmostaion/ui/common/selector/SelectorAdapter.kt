package wannabit.io.cosmostaion.ui.common.selector

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.databinding.ItemSelectorBinding

class SelectorAdapter(private val context: Context, private val items: List<String>, val onSelect: (item: String) -> Unit) : RecyclerView.Adapter<SelectorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectorViewHolder {
        val binding = ItemSelectorBinding.inflate(LayoutInflater.from(context), parent, false)
        return SelectorViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: SelectorViewHolder, position: Int) {
        val item = items[position]
        viewHolder.binding.item.text = item
        viewHolder.binding.wrap.setOnClickListener {
            onSelect(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}