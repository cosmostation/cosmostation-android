package wannabit.io.cosmostaion.ui.main.chain.major

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.databinding.ItemMajorCryptoBinding

class BitCryptoAdapter(
    val selectedChain: BaseChain,
) : RecyclerView.Adapter<BitAssetViewHolder>() {

    private var onItemClickListener: ((BaseChain, String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BitAssetViewHolder {
        val binding = ItemMajorCryptoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BitAssetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BitAssetViewHolder, position: Int) {
        holder.bind(selectedChain as ChainBitCoin86)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(selectedChain, "")
            }
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    fun setOnItemClickListener(listener: (BaseChain, String) -> Unit) {
        onItemClickListener = listener
    }
}