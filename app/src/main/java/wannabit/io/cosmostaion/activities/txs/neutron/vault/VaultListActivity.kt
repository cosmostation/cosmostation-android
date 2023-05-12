package wannabit.io.cosmostaion.activities.txs.neutron.vault

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivityVaultListBinding
import wannabit.io.cosmostaion.model.factory.neutron.VaultViewModelProviderFactory
import wannabit.io.cosmostaion.model.repository.neutron.VaultRepository
import wannabit.io.cosmostaion.model.viewModel.neutron.VaultViewModel
import wannabit.io.cosmostaion.network.res.neutron.ResVaultData
import wannabit.io.cosmostaion.network.res.neutron.ResVotingData
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.makeToast
import java.math.BigDecimal

class VaultListActivity : BaseActivity() {

    private lateinit var binding: ActivityVaultListBinding

    private lateinit var vaultViewModel: VaultViewModel

    private lateinit var adapter: VaultListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaultListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vaultViewModelFactory = VaultViewModelProviderFactory(VaultRepository())
        vaultViewModel = ViewModelProvider(this, vaultViewModelFactory)[VaultViewModel::class.java]

        initView()
        onClick()
        onSwipeRefresh()
        onShowWaitDialog()
    }

    fun initView() {
        with(binding) {
            mAccount = baseDao.onSelectAccount(baseDao.lastUser)
            mChainConfig = ChainFactory.getChain(BaseChain.getChain(mAccount.baseChain))

            setSupportActionBar(toolBar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            adapter = VaultListAdapter(this@VaultListActivity, mChainConfig, baseDao, listener = vaultClickAction)

            loadDataObserve()
        }
    }

    private fun onClick() {
        binding.btnExplorer.setOnClickListener {
            val url = mChainConfig.explorerUrl() + "dao/vault"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun onSwipeRefresh() {
        binding.layerRefresher.setOnRefreshListener {
            loadDataObserve()
        }
    }

    private fun onUpdateView() {
        onHideWaitDialog()
        binding.apply {
            recycler.layoutManager = LinearLayoutManager(this@VaultListActivity)
            recycler.adapter = adapter
            layerRefresher.isRefreshing = false
            recycler.adapter?.notifyDataSetChanged()
        }
    }

    private fun loadDataObserve() {
        adapter.vaultDataList = baseDao.mVaultDatas
        baseDao.mVaultDatas.forEach { vaultData ->
            vaultData?.let {
                vaultViewModel.loadVaultDepositData(mChainConfig, it.address)

            } ?: run {
                // github disconncet
            }
        }

        vaultViewModel.vaultDepositData.observe(this) { response ->
            response?.let {
                adapter.voteData = Gson().fromJson(it, ResVotingData::class.java)
                onUpdateView()
            }
        }
    }

    private val vaultClickAction = object : VaultListAdapter.ClickListener {
        override fun depositClickAction(item: ResVaultData) {
            onStartVaultDeposit(item)
        }

        override fun withDrawClickAction(item: ResVaultData) {
            onStartVaultWithdraw(item)
        }
    }

    private fun onStartVaultDeposit(resVaultData: ResVaultData) {
        if (!mAccount.hasPrivateKey) {
            onInsertKeyDialog()
            return
        }
        if (!WDp.isTxFeePayable(this, baseDao, mChainConfig)) {
            makeToast(R.string.error_not_enough_fee)
        }
        val availableMaxAmount = baseDao.getAvailable(mChainConfig.mainDenom())
        if (BigDecimal.ZERO >= availableMaxAmount) {
            makeToast(R.string.error_not_enough_to_deposit_pool)
            return
        }

        Intent(baseContext, VaultActivity::class.java).apply {
            putExtra("txType", BaseConstant.CONST_PW_TX_VAULT_DEPOSIT)
            putExtra("contractAddress", resVaultData.address)
            startActivity(this)
        }
    }

    private fun onStartVaultWithdraw(resVaultData: ResVaultData) {
        if (!mAccount.hasPrivateKey) {
            onInsertKeyDialog()
            return
        }
        if (!WDp.isTxFeePayable(this, baseDao, mChainConfig)) {
            makeToast(R.string.error_not_enough_fee)
        }
        if (BigDecimal(baseDao.mVaultAmount) <= BigDecimal.ZERO) {
            makeToast(R.string.error_no_withdraw_vault)
            return
        }

        Intent(baseContext, VaultActivity::class.java).apply {
            putExtra("txType", BaseConstant.CONST_PW_TX_VAULT_WITHDRAW)
            putExtra("contractAddress", resVaultData.address)
            startActivity(this)
        }
    }
}