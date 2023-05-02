package wannabit.io.cosmostaion.activities.txs.neutron

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import wannabit.io.cosmostaion.activities.txs.neutron.dao.DaoListAdapter
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