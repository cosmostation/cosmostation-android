package wannabit.io.cosmostaion.activities.txs.neutron.dao

import android.content.Intent
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
import wannabit.io.cosmostaion.databinding.ActivityDaoProposalListBinding
import wannabit.io.cosmostaion.model.factory.neutron.DaoViewModelProviderFactory
import wannabit.io.cosmostaion.model.repository.neutron.DaoRepository
import wannabit.io.cosmostaion.model.viewModel.neutron.DaoViewModel
import wannabit.io.cosmostaion.network.res.neutron.ProposalData
import wannabit.io.cosmostaion.network.res.neutron.ProposalModule
import wannabit.io.cosmostaion.network.res.neutron.ResMyVoteStatus
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.WLog
import wannabit.io.cosmostaion.utils.makeToast
import wannabit.io.cosmostaion.utils.visibleOrGone
import java.math.BigDecimal

class DaoProposalListActivity : BaseActivity() {

    private lateinit var binding: ActivityDaoProposalListBinding

    private lateinit var daoViewModel: DaoViewModel

    private var proposalModules = listOf<ProposalModule?>()
    private var proposalMyVoteStatus = listOf<ResMyVoteStatus>()

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

    private fun loadDataObserve() {
        daoViewModel.daoListData.observe(this) { response ->
            response?.let { daoData ->
                val contractAddressList = mutableListOf<String?>()
                proposalModules = daoData[intent.getIntExtra("position", -1)]?.proposal_modules!!
                proposalModules.forEach {
                    contractAddressList.add(it?.address)
                }
                daoViewModel.loadDaoProposalListData(mChainConfig, contractAddressList)
            }
            onHideWaitDialog()
        }

        daoViewModel.daoProposalListData.observe(this) { response ->
            response?.let {
                daoViewModel.loadDaoProposalMyVoteData(mChainConfig, mAccount)
            }
        }

        daoViewModel.daoMyVoteStatusData.observe(this) { response ->
            onHideWaitDialog()
            response?.let {
                proposalMyVoteStatus = it
                onUpdateView()
            }
        }
    }

    private fun onUpdateView() {
        binding.apply {
            daoViewModel.daoProposalListData.value?.let { pairs ->
                recycler.layoutManager = LinearLayoutManager(this@DaoProposalListActivity)
                recycler.adapter = DaoProposalListAdapter(this@DaoProposalListActivity, mChainConfig, proposalModules, getProposals(pairs), proposalMyVoteStatus, voteClickAction)
                recycler.addItemDecoration(DaoProposalListHeader(this@DaoProposalListActivity, true, getProposals(pairs), getSectionCall()))

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
                }
                recycler.adapter?.notifyDataSetChanged()
            }
        }
    }

//    private fun onCheckShowAll() {
//        with(binding) {
//            checkShowAll.setOnCheckedChangeListener { buttonView, isChecked ->
//                neutronViewModel.data.value?.let { response ->
//                    response.let { proposals ->
//                        Gson().fromJson(proposals[0].toString(), ResProposalData::class.java).let { data ->
//                            if (isChecked) {
//                                isShowAll = !isShowAll
//                                mProposalSingleList = data.proposals as MutableList<ProposalData?>
//                            } else {
//                                mProposalSingleList.clear()
//                                data.proposals.forEach {
//                                    if ("open" == it?.proposal?.status) mProposalSingleList.add(it)
//                                }
//                            }
//                        }
//
//                        Gson().fromJson(proposals[1].toString(), ResProposalData::class.java).let { data ->
//                            if (isChecked) {
//                                isShowAll = !isShowAll
//                                mProposalMultiList = data.proposals as MutableList<ProposalData?>
//                            } else {
//                                mProposalMultiList.clear()
//                                data.proposals.forEach {
//                                    if ("open" == it?.proposal?.status) mProposalMultiList.add(it)
//                                }
//                            }
//                        }
//
//                        Gson().fromJson(proposals[2].toString(), ResProposalData::class.java).let { data ->
//                            if (isChecked) {
//                                isShowAll = !isShowAll
//                                mProposalOverruleList = data.proposals as MutableList<ProposalData?>
//                            } else {
//                                mProposalOverruleList.clear()
//                                data.proposals.forEach {
//                                    if ("open" == it?.proposal?.status) mProposalOverruleList.add(it)
//                                }
//                            }
//                        }
//                    }
//                }
//                recycler.adapter?.notifyDataSetChanged()
//            }
//        }
//    }

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
                if ("executed" == it.second?.proposal?.status) {
                    proposalPairs.add(Pair(it.first, it.second))
                }
            }
        }
        return proposalPairs
    }
}