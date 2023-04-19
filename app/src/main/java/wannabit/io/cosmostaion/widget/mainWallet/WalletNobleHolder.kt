package wannabit.io.cosmostaion.widget.mainWallet

import android.view.View
import wannabit.io.cosmostaion.activities.MainActivity
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ItemWalletNobleBinding
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.widget.BaseHolder

class WalletNobleHolder(itemView: View) : BaseHolder(itemView) {

    private lateinit var binding: ItemWalletNobleBinding

    override fun onBindHolder(mainActivity: MainActivity) {
        super.onBindHolder(mainActivity)
        binding = ItemWalletNobleBinding.bind(itemView)

        val baseData = mainActivity.baseDao
        val chainConfig = ChainFactory.getChain(mainActivity.mBaseChain)
        val denom = chainConfig.mainDenom()
        val decimal = WDp.getDenomDecimal(baseData, chainConfig, denom)

        val availableAmount = baseData.getAvailable(denom)

        binding.apply {
            chainAmount.text = WDp.getDpAmount2(availableAmount, decimal, 6)
            chainValue.text = WDp.dpAssetValue(baseData, WDp.getGeckoId(baseData, chainConfig), availableAmount, decimal)

            baseData.onUpdateLastTotalAccount(mainActivity.mAccount, availableAmount.toPlainString())
        }
    }
}