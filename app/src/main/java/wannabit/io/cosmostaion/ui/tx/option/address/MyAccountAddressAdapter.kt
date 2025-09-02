package wannabit.io.cosmostaion.ui.tx.option.address

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.databinding.ItemImageHeaderBinding
import wannabit.io.cosmostaion.databinding.ItemMyAccountBinding
import wannabit.io.cosmostaion.ui.tx.genTx.SendAssetType

class MyAccountAddressAdapter(
    private val myMnemonic: MutableList<RefAddress>,
    private val myPrivate: MutableList<RefAddress>,
    private val sendAssetType: SendAssetType
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onItemClickListener: ((String, String) -> Unit)? = null

    companion object {
        const val VIEW_TYPE_MNEMONIC_HEADER = 0
        const val VIEW_TYPE_MNEMONIC_ITEM = 1
        const val VIEW_TYPE_PRIVATE_HEADER = 2
        const val VIEW_TYPE_PRIVATE_ITEM = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MNEMONIC_HEADER, VIEW_TYPE_PRIVATE_HEADER -> {
                val binding = ItemImageHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                AddressBookHeaderViewHolder(parent.context, binding)
            }

            VIEW_TYPE_MNEMONIC_ITEM, VIEW_TYPE_PRIVATE_ITEM -> {
                val binding =
                    ItemMyAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MyAccountViewHolder(parent.context, binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AddressBookHeaderViewHolder -> {
                holder.bind(getItemViewType(position))
            }

            is MyAccountViewHolder -> {
                if (holder.itemViewType == VIEW_TYPE_MNEMONIC_ITEM) {
                    val mnemonic = myMnemonic[position - 1]
                    val mnemonicCnt = if (myPrivate.isNotEmpty()) {
                        myMnemonic.size
                    } else {
                        0
                    }
                    holder.myAccountBind(mnemonic, sendAssetType, mnemonicCnt)

                    val address =
                        if (sendAssetType == SendAssetType.ONLY_EVM_COIN || sendAssetType == SendAssetType.ONLY_EVM_ERC20) {
                            mnemonic.evmAddress
                        } else {
                            mnemonic.dpAddress
                        }
                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let {
                            it(address ?: "", "")
                        }
                    }

                } else {
                    val headerCount = if (myMnemonic.isNotEmpty()) {
                        position - 2 - myMnemonic.size
                    } else {
                        position - 1
                    }
                    val privateKey = myPrivate[headerCount]
                    holder.myAccountBind(privateKey, sendAssetType)

                    val address =
                        if (sendAssetType == SendAssetType.ONLY_EVM_COIN || sendAssetType == SendAssetType.ONLY_EVM_ERC20) {
                            privateKey.evmAddress
                        } else {
                            privateKey.dpAddress
                        }
                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let {
                            it(address ?: "", "")
                        }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (myMnemonic.isNotEmpty()) {
            if (myPrivate.isNotEmpty()) {
                if (position == 0) VIEW_TYPE_MNEMONIC_HEADER
                else if (position < myMnemonic.size + 1) VIEW_TYPE_MNEMONIC_ITEM
                else if (position < myMnemonic.size + 2) VIEW_TYPE_PRIVATE_HEADER
                else VIEW_TYPE_PRIVATE_ITEM

            } else {
                when (position) {
                    0 -> VIEW_TYPE_MNEMONIC_HEADER
                    else -> VIEW_TYPE_MNEMONIC_ITEM
                }
            }

        } else {
            if (myPrivate.isNotEmpty()) {
                if (position == 0) VIEW_TYPE_PRIVATE_HEADER
                else VIEW_TYPE_PRIVATE_ITEM
            } else {
                -1
            }
        }
    }

    override fun getItemCount(): Int {
        return if (myMnemonic.isNotEmpty()) {
            if (myPrivate.isNotEmpty()) {
                myMnemonic.size + myPrivate.size + 2
            } else {
                myMnemonic.size + 1
            }

        } else {
            if (myPrivate.isNotEmpty()) {
                myPrivate.size + 1
            } else {
                0
            }
        }
    }

    inner class AddressBookHeaderViewHolder(
        private val context: Context,
        private val binding: ItemImageHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewType: Int) {
            binding.apply {
                accountImg.setColorFilter(
                    ContextCompat.getColor(context, R.color.color_base03),
                    PorterDuff.Mode.SRC_IN
                )

                if (viewType == VIEW_TYPE_MNEMONIC_HEADER) {
                    headerTitle.text = "Mnemonic"
                    headerCnt.text = myMnemonic.size.toString()
                    accountImg.setImageResource(R.drawable.icon_mnemonic)
                } else {
                    headerTitle.text = "Private Key"
                    headerCnt.text = myPrivate.size.toString()
                    accountImg.setImageResource(R.drawable.icon_private)
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (String, String) -> Unit) {
        onItemClickListener = listener
    }
}