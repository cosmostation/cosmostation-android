package wannabit.io.cosmostaion.widget.mainWallet

import android.view.View
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.MainActivity
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ItemWalletNeutronBinding
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.makeToast
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
        val bondAmount = BigDecimal.ZERO
        val totalAmount = availableAmount.add(bondAmount)

        binding.apply {
            chainAmount.text = WDp.getDpAmount2(totalAmount, decimal, 6)
            chainValue.text = WDp.dpAssetValue(baseData, WDp.getGeckoId(baseData, chainConfig), totalAmount, decimal)
            chainAvailable.text = WDp.getDpAmount2(totalAmount, decimal, 6)
            chainBond.text = WDp.getDpAmount2(bondAmount, decimal, 6)

            baseData.onUpdateLastTotalAccount(mainActivity.mAccount, totalAmount.toPlainString())

            btnValut.setOnClickListener {
//                Intent(mainActivity, VaultListActivity::class.java).apply {
//                    mainActivity.startActivity(this)
//                }
                mainActivity.makeToast(R.string.error_prepare)
                return@setOnClickListener
            }

            btnDao.setOnClickListener {
                mainActivity.makeToast(R.string.error_prepare)
                return@setOnClickListener
            }

            btnDefi.setOnClickListener {
                mainActivity.makeToast(R.string.error_prepare)
                return@setOnClickListener
            }

            btnWalletConnect.setOnClickListener {
                mainActivity.makeToast(R.string.error_prepare)
                return@setOnClickListener
            }
        }
    }
}