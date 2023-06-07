package wannabit.io.cosmostaion.activities.txs.neutron.dao

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivityDaoProposalListBinding
import wannabit.io.cosmostaion.model.factory.neutron.DaoViewModelProviderFactory
import wannabit.io.cosmostaion.model.repository.neutron.DaoRepository
import wannabit.io.cosmostaion.model.viewModel.neutron.DaoViewModel
import wannabit.io.cosmostaion.network.res.neutron.ProposalData
import wannabit.io.cosmostaion.network.res.neutron.ProposalModule
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.makeToast
import wannabit.io.cosmostaion.utils.visibleOrGone
import java.math.BigDecimal

class DaoProposalListActivity : BaseActivity() {

    private lateinit var binding: ActivityDaoProposalListBinding

    private lateinit var daoViewModel: DaoViewModel

    private lateinit var adapter: DaoProposalListAdapter
    private lateinit var header: DaoProposalListHeader

    private var isShowAll: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaoProposalListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val daoViewModelFactory = DaoViewModelProviderFactory(DaoRepository())
        daoViewModel = ViewModelProvider(this, daoViewModelFactory)[DaoViewModel::class.java]

        initView()
        onShowWaitDialog()
        onCheckShowAll()
    }

    fun initView() {
        binding.apply {
            mAccount = baseDao.onSelectAccount(baseDao.lastUser)
            mChainConfig = ChainFactory.getChain(BaseChain.getChain(mAccount.baseChain))

            adapter = DaoProposalListAdapter(this@DaoProposalListActivity, mChainConfig, mAccount, listener = voteClickAction)
            header = DaoProposalListHeader(this@DaoProposalListActivity, sectionCallback = getSectionCall())

            setSupportActionBar(toolBar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            loadDataObserve()
            daoViewModel.loadDaoListData(mChainConfig)
        }
    }

    private fun loadDataObserve() {
        daoViewModel.daoListData.observe(this) { response ->
            response?.let { daoData ->
                val contractAddressList = mutableListOf<String?>()
                adapter.proposalModuleList = daoData[intent.getIntExtra("position", -1)]?.proposal_modules!!
                adapter.proposalModuleList.forEach {
                    contractAddressList.add(it?.address)
                }
                daoViewModel.loadDaoProposalListData(mChainConfig, contractAddressList)
                val groupContractAddress = daoData[intent.getIntExtra("position", -1)]?.group_contract_address
                if (groupContractAddress != null) {
                    daoViewModel.loadMemberList(mChainConfig, groupContractAddress)
                }
            }
        }

        daoViewModel.daoProposalListData.observe(this) { response ->
            response?.let {
                adapter.pairs = getProposals(it)
                header.pairs = getProposals(it)
                daoViewModel.loadDaoProposalMyVoteData(this, mChainConfig, mAccount)
            }
        }

        daoViewModel.daoMyVoteStatusData.observe(this) { response ->
            response?.let {
                adapter.proposalMyVoteStatus = it
                onHideWaitDialog()
                onUpdateView()
            }
        }

        daoViewModel.daoMemberStatus.observe(this) { response ->
            response?.let {
                val daoMemberList: MutableList<String?> = mutableListOf()
                it.members.forEach { member ->
                    if (!daoMemberList.contains(member.addr)) {
                        daoMemberList.add(member.addr)
                    }
                }
                adapter.daoMemberList = daoMemberList
            }
        }
    }

    private fun onUpdateView() {
        binding.apply {
            daoViewModel.daoProposalListData.value?.let { pairs ->
                recycler.layoutManager = LinearLayoutManager(this@DaoProposalListActivity)
                recycler.adapter = adapter
                recycler.addItemDecoration(header)

                emptyProposal.visibleOrGone(getProposals(pairs).isEmpty())
                recycler.adapter?.notifyDataSetChanged()
            }
        }
    }

    private val voteClickAction = object : DaoProposalListAdapter.ClickListener {
        override fun voteClickAction(item: ProposalData, proposalModule: ProposalModule) {
            onStartVote(item, proposalModule)
        }
    }

    private fun onStartVote(proposalData: ProposalData, proposalModule: ProposalModule) {
        if (!mAccount.hasPrivateKey) {
            onInsertKeyDialog()
            return
        }
        if (!WDp.isTxFeePayable(this, baseDao, mChainConfig)) {
            makeToast(R.string.error_not_enough_fee)
        }
        val availableMaxAmount = baseDao.getAvailable(mChainConfig.mainDenom())
        if (BigDecimal.ZERO >= availableMaxAmount) {
            makeToast(R.string.error_not_enough_balance_to_vote)
            return
        }

        Intent(baseContext, DaoProposalActivity::class.java).apply {
            when (proposalModule.prefix) {
                "A" -> putExtra("txType", BaseConstant.CONST_PW_TX_DAO_SINGLE_PROPOSAL)
                "B" -> putExtra("txType", BaseConstant.CONST_PW_TX_DAO_MULTI_PROPOSAL)
                "C" -> putExtra("txType", BaseConstant.CONST_PW_TX_DAO_OVERRULE_PROPOSAL)
            }
            putExtra("proposalData", Gson().toJson(proposalData))
            putExtra("proposalModule", Gson().toJson(proposalModule))
            startActivity(this)
        }
    }

    private fun onCheckShowAll() {
        binding.apply {
            checkShowAll.setOnCheckedChangeListener { _, _ ->
                daoViewModel.daoProposalListData.value?.let {
                    isShowAll = !isShowAll
                    adapter.pairs = getProposals(it)
                    header.pairs = getProposals(it)
                    emptyProposal.visibleOrGone(getProposals(it).size <= 0)
                }
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun getSectionCall(): DaoProposalListHeader.SectionCallback {
        return object : DaoProposalListHeader.SectionCallback {
            override fun isSection(pairs: List<Pair<String?, ProposalData?>>, position: Int): Boolean {
                return position == 0 || pairs[position].first?.equals(pairs[position - 1].first) == false
            }

            override fun sectionHeader(pairs: List<Pair<String?, ProposalData?>>, position: Int): String {
                daoViewModel.daoListData.value?.let { resDaoData ->
                    resDaoData[intent.getIntExtra("position", -1)]?.proposal_modules?.forEach { proposalModule ->
                        if (proposalModule?.address.equals(pairs[position].first)) {
                            return proposalModule?.name!!
                        }
                    }
                }
                return ""
            }

            override fun sectionCnt(pairs: List<Pair<String?, ProposalData?>>, position: Int): String {
                daoViewModel.daoListData.value?.let { resDaoData ->
                    resDaoData[intent.getIntExtra("position", -1)]?.proposal_modules?.forEach { proposalModule ->
                        if (proposalModule?.address.equals(pairs[position].first)) {
                            return (pairs.filter { item -> item.first.equals(proposalModule?.address) }.lastIndex + 1).toString()
                        }
                    }
                }
                return ""
            }
        }
    }

    private fun getProposals(pairs: List<Pair<String?, ProposalData?>>) : MutableList<Pair<String?, ProposalData?>> {
        var proposalPairs = mutableListOf<Pair<String?, ProposalData?>>()
        if (isShowAll) {
            proposalPairs = pairs.toMutableList()
        } else {
            pairs.forEach {
                if ("open" == it.second?.proposal?.status) {
                    proposalPairs.add(Pair(it.first, it.second))
                }
            }
        }
        return proposalPairs
    }
}