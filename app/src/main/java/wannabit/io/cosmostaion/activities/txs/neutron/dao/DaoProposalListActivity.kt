package wannabit.io.cosmostaion.activities.txs.neutron.dao

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivityDaoProposalListBinding
import wannabit.io.cosmostaion.model.NetworkResult
import wannabit.io.cosmostaion.model.viewModel.neutron.DaoViewModel
import wannabit.io.cosmostaion.network.res.neutron.ProposalData
import wannabit.io.cosmostaion.network.res.neutron.ProposalModule
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.makeToast
import wannabit.io.cosmostaion.utils.visibleOrGone
import java.math.BigDecimal

@AndroidEntryPoint
class DaoProposalListActivity : BaseActivity() {

    private lateinit var binding: ActivityDaoProposalListBinding

    private val daoViewModel: DaoViewModel by viewModels()

    private lateinit var adapter: DaoProposalListAdapter
    private lateinit var header: DaoProposalListHeader

    private var isShowAll: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaoProposalListBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            response?.let {
                when (response) {
                    is NetworkResult.Success -> {
                        response.data?.let { daoData ->
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
                    is NetworkResult.Error -> {
                        makeToast(response.message ?: "Unknown error message")
                        finish()
                    }
                }
            }
        }

        daoViewModel.daoProposalListData.observe(this) { response ->
            response?.let {
                when (response) {
                    is NetworkResult.Success -> {
                        adapter.pairs = getProposals(response.data)
                        header.pairs = getProposals(response.data)
                        daoViewModel.loadDaoProposalMyVoteData(mChainConfig, mAccount)
                    }
                    is NetworkResult.Error -> {
                        makeToast(response.message ?: "Unknown error message")
                        finish()
                    }
                }
            }
        }

        daoViewModel.daoMyVoteStatusData.observe(this) { response ->
            response?.let {
                when (response) {
                    is NetworkResult.Success -> {
                        response.data?.let {
                            adapter.proposalMyVoteStatus = it
                            onHideWaitDialog()
                            onUpdateView()
                        }
                    }
                    is NetworkResult.Error -> {
                        makeToast(response.message ?: "Unknown error message")
                        finish()
                    }
                }
            }
        }

        daoViewModel.daoMemberStatus.observe(this) { response ->
            response?.let {
                when (response) {
                    is NetworkResult.Success -> {
                        val daoMemberList: MutableList<String?> = mutableListOf()
                        response.data?.let {
                            it.members.forEach { member ->
                                if (!daoMemberList.contains(member.addr)) {
                                    daoMemberList.add(member.addr)
                                }
                            }
                            adapter.daoMemberList = daoMemberList
                        }
                    }
                    is NetworkResult.Error -> {
                        makeToast(response.message ?: "Unknown error message")
                        finish()
                    }
                }
            }
        }
    }

    private fun onUpdateView() {
        binding.apply {
            daoViewModel.daoProposalListData.value?.let { value ->
                when (value) {
                    is NetworkResult.Success -> {
                        recycler.layoutManager = LinearLayoutManager(this@DaoProposalListActivity)
                        recycler.adapter = adapter
                        recycler.addItemDecoration(header)

                        emptyProposal.visibleOrGone(getProposals(value.data).isEmpty())
                        recycler.adapter?.notifyDataSetChanged()
                    }
                }
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
                daoViewModel.daoProposalListData.value?.let { value ->
                    when (value) {
                        is NetworkResult.Success -> {
                            isShowAll = !isShowAll
                            adapter.pairs = getProposals(value.data)
                            header.pairs = getProposals(value.data)
                        }
                    }
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
                daoViewModel.daoListData.value?.let { value ->
                    when (value) {
                        is NetworkResult.Success -> {
                            value.data?.let { resDaoData ->
                                resDaoData[intent.getIntExtra("position", -1)]?.proposal_modules?.forEach { proposalModule ->
                                    if (proposalModule?.address.equals(pairs[position].first)) {
                                        return proposalModule?.name!!
                                    }
                                }
                            }
                        }
                        is NetworkResult.Error -> {
                            return ""
                        }
                    }
                }
                return ""
            }

            override fun sectionCnt(pairs: List<Pair<String?, ProposalData?>>, position: Int): String {
                daoViewModel.daoListData.value?.let { value ->
                    when (value) {
                        is NetworkResult.Success -> {
                            value.data?.let { resDaoData ->
                                resDaoData[intent.getIntExtra("position", -1)]?.proposal_modules?.forEach { proposalModule ->
                                    if (proposalModule?.address.equals(pairs[position].first)) {
                                        return (pairs.filter { item -> item.first.equals(proposalModule?.address) }.lastIndex + 1).toString()
                                    }
                                }
                            }
                        }
                        is NetworkResult.Error -> {
                            return ""
                        }
                    }
                }
                return ""
            }
        }
    }

    private fun getProposals(pairs: List<Pair<String?, ProposalData?>>): MutableList<Pair<String?, ProposalData?>> {
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