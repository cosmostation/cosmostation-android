package wannabit.io.cosmostaion.ui.tx.info.kava

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kava.cdp.v1beta1.GenesisProto
import com.kava.cdp.v1beta1.QueryProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.ItemMyMintBinding
import wannabit.io.cosmostaion.databinding.ItemOtherMintBinding
import wannabit.io.cosmostaion.databinding.ItemStickyHeaderBinding

class MintListAdapter(
    val context: Context,
    private val priceFeed: com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse?,
    private val myCollateralParams: MutableList<GenesisProto.CollateralParam>,
    private val otherCollateralParams: MutableList<GenesisProto.CollateralParam>,
    private val cdps: MutableList<QueryProto.CDPResponse>?,
    var listener: ClickListener
) : ListAdapter<GenesisProto.CollateralParam, RecyclerView.ViewHolder>(MintListDiffCallback()) {

    companion object {
        const val VIEW_TYPE_MY_MINT_HEADER = 0
        const val VIEW_TYPE_MY_MINT_ITEM = 1
        const val VIEW_TYPE_OTHER_MINT_HEADER = 2
        const val VIEW_TYPE_OTHER_MINT_ITEM = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MY_MINT_HEADER, VIEW_TYPE_OTHER_MINT_HEADER -> {
                val binding = ItemStickyHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                MintListHeaderViewHolder(binding)
            }

            VIEW_TYPE_MY_MINT_ITEM -> {
                val binding = ItemMyMintBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                MintMyViewHolder(context, binding)
            }

            VIEW_TYPE_OTHER_MINT_ITEM -> {
                val binding = ItemOtherMintBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                MintOtherViewHolder(context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MintListHeaderViewHolder -> {
                holder.bind(holder.itemViewType)
            }

            is MintMyViewHolder -> {
                if (myCollateralParams.isNotEmpty()) {
                    val collateralParam = myCollateralParams[myCollateralParams.size - 1]
                    val myCdp = cdps?.firstOrNull { it.type == collateralParam.type }
                    holder.bind(collateralParam, priceFeed, myCdp, listener)
                }
            }

            is MintOtherViewHolder -> {
                val collateralParam: GenesisProto.CollateralParam?
                if (myCollateralParams.isNotEmpty()) {
                    collateralParam = otherCollateralParams[position - myCollateralParams.size - 2]
                    holder.bind(collateralParam, listener)

                } else {
                    collateralParam = otherCollateralParams[position - 1]
                    holder.bind(collateralParam, listener)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (myCollateralParams.isNotEmpty()) {
            return if (position == 0) {
                VIEW_TYPE_MY_MINT_HEADER
            } else if (position < myCollateralParams.size + 1) {
                VIEW_TYPE_MY_MINT_ITEM
            } else if (position < myCollateralParams.size + 2) {
                VIEW_TYPE_OTHER_MINT_HEADER
            } else {
                VIEW_TYPE_OTHER_MINT_ITEM
            }

        } else {
            return if (position == 0) {
                VIEW_TYPE_OTHER_MINT_HEADER
            } else {
                VIEW_TYPE_OTHER_MINT_ITEM
            }
        }
    }

    override fun getItemCount(): Int {
        val allCount = myCollateralParams.size + otherCollateralParams.size
        return if (myCollateralParams.isNotEmpty()) {
            allCount + 2
        } else {
            allCount + 1
        }
    }

    private class MintListDiffCallback : DiffUtil.ItemCallback<GenesisProto.CollateralParam>() {

        override fun areItemsTheSame(oldItem: GenesisProto.CollateralParam, newItem: GenesisProto.CollateralParam): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: GenesisProto.CollateralParam, newItem: GenesisProto.CollateralParam): Boolean {
            return oldItem == newItem
        }
    }

    inner class MintListHeaderViewHolder(
        private val binding: ItemStickyHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewType: Int) {
            binding.apply {
                if (viewType == VIEW_TYPE_MY_MINT_HEADER) {
                    headerTitle.text = context.getString(R.string.str_my_market_list)
                    headerCnt.text = myCollateralParams.size.toString()

                } else {
                    headerTitle.text = context.getString(R.string.str_market_list)
                    headerCnt.text = otherCollateralParams.size.toString()
                }
            }
        }
    }

    interface ClickListener {
        fun myMintClick(mintType: String?)

        fun otherMintClick(mintType: String?)
    }
}