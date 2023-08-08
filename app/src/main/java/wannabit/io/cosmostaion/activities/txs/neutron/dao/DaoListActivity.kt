package wannabit.io.cosmostaion.activities.txs.neutron.dao

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivityDaoListBinding
import wannabit.io.cosmostaion.model.factory.neutron.DaoViewModelProviderFactory
import wannabit.io.cosmostaion.model.repository.neutron.DaoRepository
import wannabit.io.cosmostaion.model.viewModel.neutron.DaoViewModel

class DaoListActivity : BaseActivity() {

    private lateinit var binding: ActivityDaoListBinding

    private lateinit var daoViewModel: DaoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val daoViewModelFactory = DaoViewModelProviderFactory(DaoRepository())
        daoViewModel = ViewModelProvider(this, daoViewModelFactory)[DaoViewModel::class.java]

        initView()
        onSwipeRefresh()
        onClick()
        onShowWaitDialog()
    }

    fun initView() {
        binding.apply {
            mAccount = baseDao.onSelectAccount(baseDao.lastUser)
            mChainConfig = ChainFactory.getChain(BaseChain.getChain(mAccount.baseChain))

            setSupportActionBar(toolBar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            loadDataObserve()
            daoViewModel.loadDaoListData(mChainConfig)
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
            layerRefresher.isRefreshing = false
            recycler.adapter?.notifyDataSetChanged()
        }
    }

    private fun onClick() {
        binding.btnExplorer.setOnClickListener {
            val url = mChainConfig.explorerUrl() + "dao/proposals"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun loadDataObserve() {
        daoViewModel.daoListData.observe(this) { response ->
            response?.let { daoData ->
                binding.recycler.layoutManager = LinearLayoutManager(this@DaoListActivity)
                binding.recycler.adapter = DaoListAdapter(this@DaoListActivity, baseDao, mChainConfig, daoData)
                onUpdateView()
            } ?: run {
                // github disconncet
            }
        }
    }
}