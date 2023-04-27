package wannabit.io.cosmostaion.activities.txs.neutron

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.gson.Gson
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivityVaultListBinding
import wannabit.io.cosmostaion.databinding.ItemVaultListBinding
import wannabit.io.cosmostaion.model.viewModel.NeutronViewModel
import wannabit.io.cosmostaion.network.res.neutron.ResConfigData
import wannabit.io.cosmostaion.network.res.neutron.ResVotingData
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.makeToast
import java.math.BigDecimal

class VaultListActivity : BaseActivity() {

    private lateinit var binding: ActivityVaultListBinding

    private val neutronViewModel: NeutronViewModel by viewModels()

    private var mVaultList = mutableListOf<ResConfigData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaultListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
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

            recycler.layoutManager = LinearLayoutManager(this@VaultListActivity)
            recycler.adapter = VaultListAdapter()

            neutronViewModel.loadNeutronData(mChainConfig)
            loadDataObserve()
        }
    }

    private fun onSwipeRefresh() {
        binding.layerRefresher.setOnRefreshListener {
            loadDataObserve()
        }
    }

    private fun onUpdateView() {
        onHideWaitDialog()
        with(binding) {
            layerRefresher.isRefreshing = false
            recycler.adapter?.notifyDataSetChanged()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadDataObserve() {
        mVaultList.clear()
        neutronViewModel.neutronData.observe(this) { response ->
            response?.let {
                mVaultList = response as MutableList<ResConfigData>
                neutronViewModel.loadNeutronDepositData(mChainConfig, mAccount, it[0]?.address)
            } ?: run {
                // github disconnect
            }
        }

        neutronViewModel.depositData.observe(this) {
            onUpdateView()
        }
    }

    private fun onStartVaultDeposit() {
        if (!mAccount.hasPrivateKey) {
            onInsertKeyDialog()
            return
        }
        if (!WDp.isTxFeePayable(this, baseDao, mChainConfig)) {
            this.makeToast(R.string.error_not_enough_fee)
        }
        val availableMaxAmount = baseDao.getAvailable(mChainConfig.mainDenom())
        if (BigDecimal.ZERO >= availableMaxAmount) {
            this.makeToast(R.string.error_not_enough_to_deposit_pool)
            return
        }

        Intent(baseContext, VaultActivity::class.java).apply {
            putExtra("txType", BaseConstant.CONST_PW_TX_VAULT_DEPOSIT)
            startActivity(this)
        }
    }

    private fun onStartVaultWithdraw() {
        if (!mAccount.hasPrivateKey) {
            onInsertKeyDialog()
            return
        }
        if (!WDp.isTxFeePayable(this, baseDao, mChainConfig)) {
            this.makeToast(R.string.error_not_enough_fee)
        }

        var myVotingData: ResVotingData? = null
        neutronViewModel.depositData.value?.let {
            myVotingData = Gson().fromJson(it[1].toString(), ResVotingData::class.java)
        }

        neutronViewModel.data.value?.let {
            myVotingData = Gson().fromJson(it[2].toString(), ResVotingData::class.java)
        }

        if (BigDecimal(myVotingData?.power) <= BigDecimal.ZERO) {
            this.makeToast(R.string.error_no_withdraw_vault)
            return
        }
        Intent(baseContext, VaultActivity::class.java).apply {
            putExtra("txType", BaseConstant.CONST_PW_TX_VAULT_WITHDRAW)
            putExtra("depositAmount", myVotingData?.power)
            startActivity(this)
        }
    }

    private inner class VaultListAdapter : RecyclerView.Adapter<VaultListAdapter.VaultHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaultListAdapter.VaultHolder {
            val binding = ItemVaultListBinding.inflate(layoutInflater, parent, false)
            return VaultHolder(binding)
        }

        override fun getItemCount(): Int {
            return mVaultList.size
        }

        override fun onBindViewHolder(holder: VaultHolder, position: Int) {
            holder.bind(position)
        }

        inner class VaultHolder(val vaultBinding: ItemVaultListBinding) : ViewHolder(vaultBinding.root) {
            fun bind(position: Int) {
                with(vaultBinding) {
                    when(position) {
                        0 -> {
                            cardRoot.setCardBackgroundColor(ContextCompat.getColor(this@VaultListActivity, mChainConfig.chainBgColor()))
                            vaultImg.setImageDrawable(ContextCompat.getDrawable(this@VaultListActivity, R.drawable.icon_main_vault))
                        }
                        else -> {
                            cardRoot.setCardBackgroundColor(ContextCompat.getColor(this@VaultListActivity, R.color.colorTransBg))
                            vaultImg.setImageDrawable(ContextCompat.getDrawable(this@VaultListActivity, R.drawable.icon_sub_vault))
                        }
                    }
                    val vaultInfo = mVaultList[position]
                    vaultName.text = vaultInfo.name?.uppercase()
                    vaultDescription.text = vaultInfo.description?.capitalize()

                    neutronViewModel.depositData.value?.let {
                        Gson().fromJson(it[0].toString(), ResVotingData::class.java)?.let { totalVotingData ->
                            totalVoting.text = WDp.getDpAmount2(BigDecimal(totalVotingData.power), mChainConfig.decimal(), mChainConfig.decimal())
                        }

                        Gson().fromJson(it[1].toString(), ResVotingData::class.java)?.let { myVotingData ->
                            myVoting.text = WDp.getDpAmount2(BigDecimal(myVotingData.power), mChainConfig.decimal(), mChainConfig.decimal())
                        }
                    }
                    btnDeposit.setOnClickListener { onStartVaultDeposit() }
                    btnWithdraw.setOnClickListener { onStartVaultWithdraw() }
                }
            }
        }
    }
}