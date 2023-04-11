package wannabit.io.cosmostaion.widget.mainWallet

import android.content.Intent
import android.view.View
import wannabit.io.cosmostaion.activities.MainActivity
import wannabit.io.cosmostaion.activities.txs.neutron.VaultListActivity
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ItemWalletNeutronBinding
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.widget.BaseHolder
import java.math.BigDecimal

class WalletNeutronHolder(itemView: View) : BaseHolder(itemView) {

    private lateinit var binding: ItemWalletNeutronBinding

    override fun onBindHolder(mainActivity: MainActivity) {
        super.onBindHolder(mainActivity)
        binding = ItemWalletNeutronBinding.bind(itemView)

        val baseData = mainActivity.baseDao
        val chainConfig = ChainFactory.getChain(mainActivity.mBaseChain)
        val denom = chainConfig.mainDenom()
        val decimal = WDp.getDenomDecimal(baseData, chainConfig, denom)

        val availableAmount = baseData.getAvailable(denom)
        val lockAmount = BigDecimal.ZERO
        val vaultAmount = BigDecimal.ZERO
        val totalAmount = availableAmount.add(lockAmount).add(vaultAmount)

        binding.apply {
            chainAmount.text = WDp.getDpAmount2(totalAmount, decimal, 6)
            chainValue.text = WDp.dpAssetValue(baseData, WDp.getGeckoId(baseData, chainConfig), totalAmount, decimal)
            chainAvailable.text = WDp.getDpAmount2(totalAmount, decimal, 6)
            chainLock.text = WDp.getDpAmount2(lockAmount, decimal, 6)
            chainVault.text = WDp.getDpAmount2(vaultAmount, decimal, 6)
//            chainLockLayer.visibleOrGone(lockAmount != BigDecimal.ZERO)

            baseData.onUpdateLastTotalAccount(mainActivity.mAccount, totalAmount.toPlainString())

            btnValut.setOnClickListener {
                Intent(mainActivity, VaultListActivity::class.java).apply {
                    mainActivity.startActivity(this)
                }
            }
        }
    }
}