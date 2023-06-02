package wannabit.io.cosmostaion.widget.mainWallet

import android.content.Intent
import android.view.View
import com.google.zxing.integration.android.IntentIntegrator
import wannabit.io.cosmostaion.activities.MainActivity
import wannabit.io.cosmostaion.activities.QRcodeActivity
import wannabit.io.cosmostaion.activities.txs.neutron.dao.DaoListActivity
import wannabit.io.cosmostaion.activities.txs.neutron.defi.NeutronDefiActivity
import wannabit.io.cosmostaion.activities.txs.neutron.vault.VaultListActivity
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ItemWalletNeutronBinding
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.visibleOrGone
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
        val vestingAmount = baseData.neutronVestingAmount
        val bondAmount = baseData.vaultAmount
        val totalAmount = availableAmount.add(bondAmount).add(vestingAmount)

        binding.apply {
            chainAmount.text = WDp.getDpAmount2(totalAmount, decimal, 6)
            chainValue.text = WDp.dpAssetValue(baseData, WDp.getGeckoId(baseData, chainConfig), totalAmount, decimal)
            chainAvailable.text = WDp.getDpAmount2(availableAmount, decimal, 6)
            chainVesting.text = WDp.getDpAmount2(vestingAmount, decimal, 6)
            chainBond.text = WDp.getDpAmount2(bondAmount, decimal, 6)

            vestingLayer.visibleOrGone(vestingAmount != BigDecimal.ZERO)
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
                if (!mainActivity.mAccount.hasPrivateKey) {
                    mainActivity.onInsertKeyDialog()
                    return@setOnClickListener
                } else {
                    val integrator = IntentIntegrator(mainActivity)
                    integrator.setOrientationLocked(true)
                    integrator.captureActivity = QRcodeActivity::class.java
                    mainActivity.walletConnectResultLauncher.launch(integrator.createScanIntent())
                }
            }
        }
    }
}