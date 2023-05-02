package wannabit.io.cosmostaion.widget.mainWallet

import android.content.Intent
import android.view.View
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.MainActivity
import wannabit.io.cosmostaion.activities.txs.neutron.DaoListActivity
import wannabit.io.cosmostaion.activities.txs.neutron.NeutronDefiActivity
import wannabit.io.cosmostaion.activities.txs.neutron.Vault.VaultListActivity
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ItemWalletNeutronBinding
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.makeToast
import wannabit.io.cosmostaion.widget.BaseHolder

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
        val bondAmount = baseData.vaultAmount
        val totalAmount = availableAmount.add(bondAmount)

        binding.apply {
            chainAmount.text = WDp.getDpAmount2(totalAmount, decimal, 6)
            chainValue.text = WDp.dpAssetValue(baseData, WDp.getGeckoId(baseData, chainConfig), totalAmount, decimal)
            chainAvailable.text = WDp.getDpAmount2(availableAmount, decimal, 6)
            chainBond.text = WDp.getDpAmount2(bondAmount, decimal, 6)

            baseData.onUpdateLastTotalAccount(mainActivity.mAccount, totalAmount.toPlainString())

            btnValut.setOnClickListener {
                Intent(mainActivity, VaultListActivity::class.java).apply {
                    mainActivity.startActivity(this)
                }
            }

            btnDao.setOnClickListener {
                Intent(mainActivity, DaoListActivity::class.java).apply {
                    mainActivity.startActivity(this)
                }
            }

            btnDefi.setOnClickListener {
                Intent(mainActivity, NeutronDefiActivity::class.java).apply {
                    mainActivity.startActivity(this)
                }
            }

            btnWalletConnect.setOnClickListener {
                mainActivity.makeToast(R.string.error_prepare)
                return@setOnClickListener
            }
        }
    }
}