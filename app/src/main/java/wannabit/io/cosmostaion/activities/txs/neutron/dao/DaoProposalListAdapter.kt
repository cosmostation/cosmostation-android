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

class DaoProposalListAdapter(
    private val context: Context,
    val chainConfig: ChainConfig,
    private val proposalModuleList: List<ProposalModule?>,
    private val pairs: List<Pair<String?, ProposalData?>> = listOf(),
    private val proposalMyVoteStatus: List<ResMyVoteStatus>,
    var listener: ClickListener
) : RecyclerView.Adapter<DaoProposalListAdapter.DaoProposalHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): DaoProposalHolder {
        val binding = ItemDaoProposalListBinding.inflate(LayoutInflater.from(context), parent, false)
        return DaoProposalHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DaoProposalHolder, position: Int
    ) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return pairs.size
    }

    inner class DaoProposalHolder(
        private val itemDaoProposalListBinding: ItemDaoProposalListBinding
    ) : RecyclerView.ViewHolder(itemDaoProposalListBinding.root) {
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
                                "rejected, failed" -> proposalStatusImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_rejected_img))
                            }
                            proposalStatus.text = proposalData.proposal?.status?.capitalize()
                        }

                        pairData.first?.let { address ->
                            proposalMyVoteStatus.forEach {
                                if (address == it.contract_address && proposalData.id?.toInt() == it.proposal_id) {

                                }
                            }


                            cardRoot.setOnClickListener {
                                proposalModuleList.find { it?.address.equals(address) }?.let {
                                    listener.voteClickAction(proposalData, it)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    interface ClickListener {
        fun voteClickAction(item: ProposalData, proposalModule: ProposalModule)
    }
}