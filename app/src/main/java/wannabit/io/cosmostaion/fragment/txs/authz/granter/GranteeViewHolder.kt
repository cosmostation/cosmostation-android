package wannabit.io.cosmostaion.fragment.txs.authz.granter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import cosmos.authz.v1beta1.Authz.GrantAuthorization
import wannabit.io.cosmostaion.base.BaseData
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.databinding.ItemGranteeBinding
import wannabit.io.cosmostaion.model.type.Coin
import wannabit.io.cosmostaion.utils.WDp

class GranteeViewHolder(
    private val binding: ItemGranteeBinding,
    private val context: Context,
    private val baseDao: BaseData,
    private val chainConfig: ChainConfig
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(grant: Pair<GrantAuthorization, Coin>) {
        binding.apply {
            granter.text = grant.first.granter
            WDp.setDpCoin(context, baseDao, chainConfig, grant.second, granterDenom, granterAvailable)
        }
    }
}