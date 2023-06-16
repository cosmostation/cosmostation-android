package wannabit.io.cosmostaion.ui.chain

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.Line
import wannabit.io.cosmostaion.databinding.ItemSelectorBinding

class ChainSwitchAdapter(private val context: Context, private val items: List<Line>) : RecyclerView.Adapter<ChainSwitchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChainSwitchViewHolder {
        val binding = ItemSelectorBinding.inflate(LayoutInflater.from(context), parent, false)
        return ChainSwitchViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ChainSwitchViewHolder, position: Int) {
        val item = items[position]
        viewHolder.binding.item.text = item.chainName
        viewHolder.binding.wrap.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}