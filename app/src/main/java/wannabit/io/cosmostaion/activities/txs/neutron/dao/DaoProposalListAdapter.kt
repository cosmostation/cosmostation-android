package wannabit.io.cosmostaion.activities.txs.neutron.dao

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.databinding.ItemDaoProposalListBinding
import wannabit.io.cosmostaion.network.res.neutron.ProposalData
import wannabit.io.cosmostaion.network.res.neutron.ProposalModule
import wannabit.io.cosmostaion.network.res.neutron.ResMyVoteStatus
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.makeToast

class DaoProposalListAdapter(
    private val context: Context,
    val chainConfig: ChainConfig,
    var proposalModuleList: List<ProposalModule?> = listOf(),
    var pairs: List<Pair<String?, ProposalData?>> = listOf(),
    var proposalMyVoteStatus: List<ResMyVoteStatus> = listOf(),
    var listener: ClickListener
) : RecyclerView.Adapter<DaoProposalListAdapter.DaoProposalHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): DaoProposalHolder {
        val binding = ItemDaoProposalListBinding.inflate(LayoutInflater.from(context), parent, false)
        return DaoProposalHolder(binding)
    }

    override fun onBindViewHolder(holder: DaoProposalHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return pairs.size
    }

    inner class DaoProposalHolder(private val itemDaoProposalListBinding: ItemDaoProposalListBinding) : RecyclerView.ViewHolder(itemDaoProposalListBinding.root) {
        fun bind(position: Int) {
            itemDaoProposalListBinding.apply {
                pairs[position].let { pairData ->
                    pairData.second?.let { proposalData ->
                        proposalTitle.text = "# ${proposalData.id} ${proposalData.proposal?.title}"

                        if ("open" == proposalData.proposal?.status) {
                            proposalData.proposal.expiration?.at_time?.toLong()?.let { expiration ->
                                proposalStatusImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_voting_img))
                                proposalExpiration.text = WDp.getGapTime(expiration.div(1000000))
                            } ?: run {
                                proposalData.proposal.expiration?.at_height?.let {
                                    proposalStatusImg.visibility = View.GONE
                                    proposalStatus.text = "Expiration at : ${it} Block"
                                    proposalStatus.textColors
                                    proposalStatus.setTextColor(ContextCompat.getColor(context, R.color.colorGray1))
                                }
                            }

                        } else {
                            when (proposalData.proposal?.status) {
                                "executed", "passed" -> proposalStatusImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_passed_img))
                                "rejected", "failed" -> proposalStatusImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_rejected_img))
                            }
                            proposalStatus.text = proposalData.proposal?.status?.capitalize()
                        }

                        pairData.first?.let { address ->
                            val myStatus = proposalMyVoteStatus.find { item -> item.contract_address == address && item.proposal_id == proposalData.id?.toInt() }?.option
                            if (myStatus != null) {
                                if (isInteger(myStatus)) {
                                    voteStatus.visibility = View.GONE
                                    multiVoteStatus.text = "Option $myStatus"
                                } else {
                                    voteStatus.visibility = View.VISIBLE
                                    multiVoteStatus.text = ""
                                    when (myStatus) {
                                        "yes" -> voteStatus.setImageResource(R.drawable.icon_vote_yes)
                                        "no" -> voteStatus.setImageResource(R.drawable.icon_vote_no)
                                        "abstain" -> voteStatus.setImageResource(R.drawable.icon_vote_abstain)
                                    }
                                }
                            } else {
                                voteStatus.visibility = View.VISIBLE
                                multiVoteStatus.text = ""
                                voteStatus.setImageResource(R.drawable.icon_vote_not_voted)
                            }

                            cardRoot.setOnClickListener {
                                proposalModuleList.find { it?.address.equals(address) }?.let {
                                    if ("open" != proposalData.proposal?.status) {
                                        context.makeToast(R.string.error_not_voting_period)
                                        return@setOnClickListener

                                    } else {
                                        if (!proposalData.proposal.allow_revoting && myStatus != null) {
                                            context.makeToast(R.string.error_no_revote)
                                            return@setOnClickListener
                                        } else {
                                            listener.voteClickAction(proposalData, it)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun isInteger(option: String?): Boolean {
        return try {
            option?.toInt()
            true
        } catch (e: java.lang.NumberFormatException) {
            false
        }
    }

    interface ClickListener {
        fun voteClickAction(item: ProposalData, proposalModule: ProposalModule)
    }
}