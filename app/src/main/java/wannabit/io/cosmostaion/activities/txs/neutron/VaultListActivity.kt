package wannabit.io.cosmostaion.activities.txs.neutron

import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.gson.Gson
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
import java.math.BigDecimal

class VaultListActivity : BaseActivity() {

    private lateinit var binding: ActivityVaultListBinding

    private val neutronViewModel: NeutronViewModel by viewModels()

    private val mVaultList = mutableListOf<ResConfigData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaultListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        onSwipeRefresh()
        onShowWaitDialog()
    }

    fun initView() {
        binding.toolbarTitle.text = "Vault List"
        mAccount = baseDao.onSelectAccount(baseDao.lastUser)
        mChainConfig = ChainFactory.getChain(BaseChain.getChain(mAccount.baseChain))

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = VaultListAdapter()

        neutronViewModel.loadVaultData(mAccount, mChainConfig, BaseConstant.NEUTRON_NTRN_VAULT_ADDRESS)
        loadDataObserve()
    }

    fun onSwipeRefresh() {
        binding.layerRefresher.setOnRefreshListener {
            loadDataObserve()
        }
    }

    private fun onUpdateView() {
        onHideWaitDialog()
        binding.apply {
            neutronViewModel.data.value?.let {
                val myVotingData = Gson().fromJson(it[2].toString(), ResVotingData::class.java)
                myTotalVoting.text = WDp.getDpAmount2(BigDecimal(myVotingData.power), mChainConfig.decimal(), mChainConfig.decimal())
            }
            layerRefresher.isRefreshing = false
            binding.recycler.adapter?.notifyDataSetChanged()
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

    fun loadDataObserve() {
        neutronViewModel.data.observe(this) { response ->
            response?.let {
                Gson().fromJson(it[0].toString(), ResConfigData::class.java).let { data ->
                    mVaultList.add(data)
                }
                onUpdateView()
            }
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
                val vaultInfo = mVaultList[position]
                neutronViewModel.data.value?.let {
                    vaultBinding.apply {
                        vaultName.text = vaultInfo.name?.uppercase()

                        val totalVotingData = Gson().fromJson(it[1].toString(), ResVotingData::class.java)
                        totalVotingValue.text = WDp.dpAssetValue(baseDao, WDp.getGeckoId(baseDao, mChainConfig), BigDecimal(totalVotingData.power), mChainConfig.decimal())
                        totalVoting.text = WDp.getDpAmount2(BigDecimal(totalVotingData.power), mChainConfig.decimal(), mChainConfig.decimal())
                        totalVotingDenom.text = mChainConfig.mainSymbol()

                        val myVotingData = Gson().fromJson(it[2].toString(), ResVotingData::class.java)
                        myVoting.text = WDp.getDpAmount2(BigDecimal(myVotingData.power), mChainConfig.decimal(), mChainConfig.decimal())
                        myVotingDenom.text = mChainConfig.mainSymbol()
                        myAvailable.text = WDp.getDpAmount2(baseDao.getAvailable(mChainConfig.mainDenom()), mChainConfig.decimal(), mChainConfig.decimal())

                        cardRoot.setOnClickListener {

                        }
                    }
                }
            }
        }
    }
}