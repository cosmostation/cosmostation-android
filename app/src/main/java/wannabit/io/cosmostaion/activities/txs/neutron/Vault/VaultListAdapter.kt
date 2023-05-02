package wannabit.io.cosmostaion.activities.txs.neutron.Vault

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseData
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.databinding.ItemVaultListBinding
import wannabit.io.cosmostaion.network.res.neutron.ResVaultData
import wannabit.io.cosmostaion.network.res.neutron.ResVotingData
import wannabit.io.cosmostaion.utils.WDp
import java.math.BigDecimal

class VaultListAdapter(
    private val context: Context,
    val chainConfig: ChainConfig,
    val baseDao: BaseData,
    var vaultDataList: List<ResVaultData?> = listOf(),
    var voteData: ResVotingData? = null,
    var listener: ClickListener
) : RecyclerView.Adapter<VaultListAdapter.VaultHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): VaultListAdapter.VaultHolder {
        val binding = ItemVaultListBinding.inflate(LayoutInflater.from(context), parent, false)
        return VaultHolder(binding)
    }

    override fun onBindViewHolder(
        holder: VaultListAdapter.VaultHolder, position: Int
    ) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return vaultDataList.size
    }

    inner class VaultHolder(
        private val itemVaultListBinding: ItemVaultListBinding
    ) : RecyclerView.ViewHolder(itemVaultListBinding.root) {
        fun bind(position: Int) {
            itemVaultListBinding.apply {
                when (position) {
                    0 -> {
                        cardRoot.setCardBackgroundColor(ContextCompat.getColor(context, chainConfig.chainBgColor()))
                        vaultImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_main_vault))
                    }
                    else -> {
                        cardRoot.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorTransBg))
                        vaultImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_sub_vault))
                    }
                }
                vaultDataList[position]?.let { vaultInfo ->
                    vaultName.text = vaultInfo.name?.uppercase()
                    vaultDescription.text = vaultInfo.description?.capitalize()
                    totalVoting.text = WDp.getDpAmount2(BigDecimal(voteData?.power), chainConfig.decimal(), chainConfig.decimal())
                    myVoting.text = WDp.getDpAmount2(BigDecimal(baseDao.mVaultAmount), chainConfig.decimal(), chainConfig.decimal())

                    btnDeposit.setOnClickListener { listener.depositClickAction(vaultInfo) }
                    btnWithdraw.setOnClickListener { listener.withDrawClickAction(vaultInfo) }
                }
            }
        }
    }

    interface ClickListener {
        fun depositClickAction(item: ResVaultData)
        fun withDrawClickAction(item: ResVaultData)
    }
}