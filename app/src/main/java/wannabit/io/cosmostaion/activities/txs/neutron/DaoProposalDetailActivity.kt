package wannabit.io.cosmostaion.activities.txs.neutron

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.txs.common.VoteActivity
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivityDaoProposalDetailBinding
import wannabit.io.cosmostaion.databinding.ItemDaoProposalInfoBinding
import wannabit.io.cosmostaion.databinding.ItemDaoPrposalTallyBinding
import wannabit.io.cosmostaion.databinding.ItemVoteMessageBinding
import wannabit.io.cosmostaion.model.viewModel.NeutronViewModel
import wannabit.io.cosmostaion.network.res.neutron.ProposalData
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.makeToast
import java.math.BigDecimal

class DaoProposalDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDaoProposalDetailBinding

    private val neutronViewModel: NeutronViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaoProposalDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onShowWaitDialog()
        initView()
        onClick()
    }

    fun initView() {
        binding.toolbarTitle.text = "Proposal Detail"
        mAccount = baseDao.onSelectAccount(baseDao.lastUser)
        mChainConfig = ChainFactory.getChain(BaseChain.getChain(mAccount.baseChain))

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = ProposalDetailAdapter()

        intent.getStringExtra("proposal_id")?.let {
            neutronViewModel.loadDaoSingleProposalData(mChainConfig, it.toInt())
        }
        loadDataObserve()
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
        with(binding) {
            neutronViewModel.data.observe(this@DaoProposalDetailActivity) { response ->
                response?.let {
                    btnVote.visibility = View.VISIBLE
                    onHideWaitDialog()
                    recycler.adapter?.notifyDataSetChanged()
                }
            }
        }
    }

    private fun onClick() {
        with(binding) {
            btnVote.setOnClickListener {
                if (!mAccount.hasPrivateKey) {
                    onInsertKeyDialog()
                    return@setOnClickListener
                }
                if (!WDp.isTxFeePayable(this@DaoProposalDetailActivity, baseDao, mChainConfig)) {
                    this@DaoProposalDetailActivity.makeToast(R.string.error_not_enough_fee)
                    return@setOnClickListener
                }

                //vault 양 체크 필요
//                if (BigDecimal.ZERO >= baseDao.delegationSum) {
//                    Toast.makeText(baseContext, getString(R.string.error_no_bonding_no_vote), Toast.LENGTH_SHORT).show()
//                    return@setOnClickListener
//                }
                Intent(this@DaoProposalDetailActivity, DaoProposalActivity::class.java).apply {
                    putExtra("proposal_id", intent.getStringExtra("proposal_id"))
                    startActivity(this)
                }
            }
        }
    }

    private inner class ProposalDetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (viewType) {
                proposalViewType.TYPE_VOTE_INFO.ordinal -> VoteInfoHolder(ItemDaoProposalInfoBinding.inflate(layoutInflater, parent, false))
                proposalViewType.TYPE_VOTE_TALLY.ordinal -> VoteTallyHolder(ItemDaoPrposalTallyBinding.inflate(layoutInflater, parent, false))
                else -> VoteMessageHolder(ItemVoteMessageBinding.inflate(layoutInflater, parent, false))
            }
        }

        override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
            when (position) {
                proposalViewType.TYPE_VOTE_INFO.ordinal -> (viewHolder as VoteInfoHolder).bind()
                proposalViewType.TYPE_VOTE_TALLY.ordinal -> (viewHolder as VoteTallyHolder).bind()
                else -> (viewHolder as VoteMessageHolder).bind(position - 2)
            }
        }

        override fun getItemCount(): Int {
            return 3
        }

        override fun getItemViewType(position: Int): Int {
            return when (position) {
                0 -> proposalViewType.TYPE_VOTE_INFO.ordinal
                1 -> proposalViewType.TYPE_VOTE_TALLY.ordinal
                else -> proposalViewType.TYPE_VOTE_MESSAGE.ordinal
            }
        }

        inner class VoteInfoHolder(val daoProposalInfoBinding: ItemDaoProposalInfoBinding) : RecyclerView.ViewHolder(daoProposalInfoBinding.root) {
            fun bind() {
                with(daoProposalInfoBinding) {
                    neutronViewModel.data.value?.let {
                        Gson().fromJson(it[0].toString(), ProposalData::class.java)?.let { proposalData ->
                            proposalId.text = "# " + proposalData.id
                            proposalTitle.text = proposalData.proposal?.title

                            proposalData.proposal?.expiration?.at_time?.toLong()?.let { expiration ->
                                proposalRemainTime.text = getGapTime(expiration.div(1000000))
                            }
                        }
                    }
                }
            }
        }

        inner class VoteTallyHolder(val daoProposalTallyBinding: ItemDaoPrposalTallyBinding) : RecyclerView.ViewHolder(daoProposalTallyBinding.root) {
            fun bind() {
                with(daoProposalTallyBinding) {
                    neutronViewModel.data.value?.let {
                        Gson().fromJson(it[0].toString(), ProposalData::class.java)?.let { proposalData ->
                            quorum.text = WDp.getPercentDp(BigDecimal(proposalData.proposal?.threshold?.threshold_quorum?.quorum?.percent))
                        }
                    }
                }
            }
        }

        inner class VoteMessageHolder(val voteMessageBinding: ItemVoteMessageBinding) : RecyclerView.ViewHolder(voteMessageBinding.root) {
            fun bind(position: Int) {

            }
        }
    }

    enum class proposalViewType{ TYPE_VOTE_INFO, TYPE_VOTE_TALLY, TYPE_VOTE_MESSAGE }

    private fun getGapTime(finishTime: Long): String {
        var result = "??"
        try {
            val now = Calendar.getInstance().timeInMillis
            val left = finishTime - now
            result = when {
                left >= BaseConstant.CONSTANT_D -> "D-" + left / BaseConstant.CONSTANT_D
                left >= BaseConstant.CONSTANT_H -> "H-" + left / BaseConstant.CONSTANT_H
                else -> "Soon"
            }
        } catch (_: Exception) { }
        return result
    }
}

//    private fun onExplorerLink() {
//        val url = mChainConfig.explorerUrl() + "proposals/" + mProposalId
//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//        startActivity(intent)
//    }
//
//    private fun voteOptionConvert(option: String): String {
//        return when (option) {
//            "VOTE_OPTION_YES" -> resources.getString(R.string.str_vote_yes).uppercase()
//            "VOTE_OPTION_NO" -> resources.getString(R.string.str_vote_no).uppercase()
//            "VOTE_OPTION_NO_WITH_VETO" -> resources.getString(R.string.str_vote_veto).uppercase()
//            else -> resources.getString(R.string.str_vote_abstain).uppercase()
//        }
//    }
//
//    private fun voteOptionColor(option: String): Int {
//        return when (option) {
//            "VOTE_OPTION_YES" -> ContextCompat.getColor(this@VoteDetailActivity, R.color.colorVoteYes)
//            "VOTE_OPTION_NO" -> ContextCompat.getColor(this@VoteDetailActivity, R.color.colorVoteNo)
//            "VOTE_OPTION_NO_WITH_VETO" -> ContextCompat.getColor(this@VoteDetailActivity, R.color.colorVoteVeto)
//            else -> ContextCompat.getColor(this@VoteDetailActivity, R.color.colorVoteAbstain)
//        }
//    }
//
//    private fun voteOptionImage(option: String): Int {
//        return when (option) {
//            "VOTE_OPTION_YES" -> R.drawable.icon_my_vote_yes
//            "VOTE_OPTION_NO" -> R.drawable.icon_my_vote_no
//            "VOTE_OPTION_NO_WITH_VETO" -> R.drawable.icon_my_vote_veto
//            else -> R.drawable.icon_my_vote_abstain
//        }
//    }