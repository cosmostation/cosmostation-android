package wannabit.io.cosmostaion.ui.tx.info

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.data.model.res.ProposalData
import wannabit.io.cosmostaion.data.model.res.ProposalModule
import wannabit.io.cosmostaion.data.model.res.ResDaoVoteStatus
import wannabit.io.cosmostaion.databinding.ItemDaoProposalBinding

class DaoListAdapter(
    val context: Context,
    val selectedChain: CosmosLine,
    private val proposalModules: MutableList<ProposalModule?>? = mutableListOf(),
    private val proposalPair: List<Pair<String?, ProposalData?>> = mutableListOf(),
    private val voteStatus: MutableList<ResDaoVoteStatus> = mutableListOf(),
    var listener: CheckListener
) : RecyclerView.Adapter<DaoListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaoListViewHolder {
        val binding = ItemDaoProposalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DaoListViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: DaoListViewHolder, position: Int) {
        val proposal = proposalPair[position]
        val module = proposalModules?.firstOrNull { it?.address == proposal.first }
        val isHeader = proposalPair.indexOfFirst { it.first == proposal.first } == position
        val headerCnt = proposalPair.filter { it.first == proposal.first }.size
        holder.bind(module, proposal, voteStatus, headerCnt, isHeader, listener)
    }


    override fun getItemCount(): Int {
        return proposalPair.size
    }

    interface CheckListener {
        fun daoProposalCheck(isChecked: Boolean, contAddress: String?, module: String?, proposalId: String?)
    }
}