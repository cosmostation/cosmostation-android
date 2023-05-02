package wannabit.io.cosmostaion.activities.txs.neutron

import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivitySubDaoProposalListBinding
import wannabit.io.cosmostaion.databinding.ItemDaoProposalListBinding
import wannabit.io.cosmostaion.model.viewModel.NeutronViewModel

class SubDaoProposalListActivity : BaseActivity() {

    private lateinit var binding: ActivitySubDaoProposalListBinding

    private val neutronViewModel: NeutronViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubDaoProposalListBinding.inflate(layoutInflater)
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

            recycler.layoutManager = LinearLayoutManager(this@SubDaoProposalListActivity)
//            recycler.adapter = ProposalListAdapter()
//            recycler.addItemDecoration(proposalHeaderRecyclerView(this@SubDaoProposalListActivity, true, getSectionCall()))

            neutronViewModel.loadDaoData(mChainConfig)
            loadDataObserve()
            onCheckShowAll()
        }
    }

    fun onSwipeRefresh() {
        binding.layerRefresher.setOnRefreshListener {
            loadDataObserve()
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
//        mProposalSingleList.clear()
//        mProposalMultiList.clear()
//        mProposalOverruleList.clear()

//        neutronViewModel.daoData.observe(this) { response ->
//            response?.let {
//                response[0]?.let {
//                    neutronViewModel.loadDaoProposalListData(mChainConfig, it.proposal_modules)
//                }
//            }
//        }

//        neutronViewModel.data.observe(this) { response ->
//            response?.let { proposals ->
//                Gson().fromJson(proposals[0].toString(), ResProposalData::class.java).let { data ->
//                    data.proposals.forEach {
//                        if ("open" == it?.proposal?.status) mProposalSingleList.add(it)
//                    }
//                }
//
//                Gson().fromJson(proposals[1].toString(), ResProposalData::class.java).let { data ->
//                    data.proposals.forEach {
//                        if ("open" == it?.proposal?.status) mProposalMultiList.add(it)
//                    }
//                }
//
//                Gson().fromJson(proposals[2].toString(), ResProposalData::class.java).let { data ->
//                    data.proposals.forEach {
//                        if ("open" == it?.proposal?.status) mProposalOverruleList.add(it)
//                    }
//                }
//                onUpdateView()
//            }
//        }
    }

    private fun onUpdateView() {
        with(binding) {
            neutronViewModel.data.value?.let {
                onHideWaitDialog()
                layerRefresher.isRefreshing = false
                recycler.adapter?.notifyDataSetChanged()
            }
        }
    }

    private fun onCheckShowAll() {
        with(binding) {
            checkShowAll.setOnCheckedChangeListener { buttonView, isChecked ->
                recycler.adapter?.notifyDataSetChanged()
            }
        }
    }

//    private inner class ProposalListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//           return null
//        }
//
//        override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
//
//        }
//
//        override fun getItemCount(): Int {
//
//        }
//
//        inner class ProposalSingleHolder(val proposalListBinding: ItemDaoProposalListBinding) : RecyclerView.ViewHolder(proposalListBinding.root) {
//            fun bind(position: Int) {
//
//            }
//        }
//    }
}