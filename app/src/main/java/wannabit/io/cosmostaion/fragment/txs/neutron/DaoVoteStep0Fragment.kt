package wannabit.io.cosmostaion.fragment.txs.neutron

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.txs.neutron.DaoProposalActivity
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentDaoVoteStep0Binding
import wannabit.io.cosmostaion.databinding.ItemMultiChoiceBinding
import wannabit.io.cosmostaion.model.viewModel.NeutronViewModel
import wannabit.io.cosmostaion.network.res.neutron.Choice
import wannabit.io.cosmostaion.network.res.neutron.ProposalData
import wannabit.io.cosmostaion.utils.WDp

class DaoVoteStep0Fragment : BaseFragment() {

    private var _binding: FragmentDaoVoteStep0Binding? = null
    private val binding get() = _binding!!

    private val neutronViewModel: NeutronViewModel by activityViewModels()

    private var mMyVote: String? = null
    private var mChoiceList = mutableListOf<Choice>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDaoVoteStep0Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onUpdateView()
        onClick()
    }

    fun onUpdateView() {
        with(binding) {
            neutronViewModel.data.observe(requireActivity()) {
                getSActivity()?.let { extend ->
                    if (extend.mTxType == BaseConstant.CONST_PW_TX_DAO_SINGLE_PROPOSAL) {
                        singleLayout.visibility = View.VISIBLE
                        recycler.visibility = View.GONE
                    } else {
                        singleLayout.visibility = View.GONE
                        recycler.visibility = View.VISIBLE

                        recycler.layoutManager = LinearLayoutManager(requireContext())
                        recycler.adapter = MultiChoiceListAdapter()
                    }

                    Gson().fromJson(it[0].toString(), ProposalData::class.java)?.let { data ->
                        proposalId.text = "# ${data.id}"
                        proposalTitle.text = data.proposal?.title
                        data.proposal?.expiration?.at_time?.toLong()?.let { expiration ->
                            proposalDeadline.text = WDp.getDpTime(requireContext(), expiration.div(1000000)) + " " + WDp.getGapTime(expiration.div(1000000))
                        }

                        data.proposal?.choices?.let {
                            mChoiceList = it as MutableList<Choice>
                        }
                    }
                }
            }
        }
    }

    private fun onCheckUpdateView(myVote: String?) {
        with(binding) {
            voteYesTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGrayDayNight))
            voteNoTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGrayDayNight))
            voteAbstainTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGrayDayNight))
            checkBtnYes.background = resources.getDrawable(R.drawable.box_vote_quorum)
            checkBtnNo.background = resources.getDrawable(R.drawable.box_vote_quorum)
            checkBtnAbstain.background = resources.getDrawable(R.drawable.box_vote_quorum)
            checkImgSelectedYes.clearColorFilter()
            checkImgSelectedNo.clearColorFilter()
            checkImgSelectedAbstain.clearColorFilter()

            when (myVote) {
                "yes" -> {
                    voteYesTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlackDayNight))
                    checkBtnYes.background = resources.getDrawable(R.drawable.box_vote_selected)
                    checkImgSelectedYes.setColorFilter(ContextCompat.getColor(requireContext(), R.color.colorBlackDayNight), android.graphics.PorterDuff.Mode.SRC_IN);
                }
                "no" -> {
                    voteNoTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlackDayNight))
                    checkBtnNo.background = resources.getDrawable(R.drawable.box_vote_selected)
                    checkImgSelectedNo.setColorFilter(ContextCompat.getColor(requireContext(), R.color.colorBlackDayNight), android.graphics.PorterDuff.Mode.SRC_IN);
                }
                else -> {
                    voteAbstainTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlackDayNight))
                    checkBtnAbstain.background = resources.getDrawable(R.drawable.box_vote_selected)
                    checkImgSelectedAbstain.setColorFilter(ContextCompat.getColor(requireContext(), R.color.colorBlackDayNight), android.graphics.PorterDuff.Mode.SRC_IN);
                }
            }
        }
    }

    fun onClick() {
        with(binding) {
            getSActivity()?.let { extend ->
                checkBtnYes.setOnClickListener {
                    mMyVote = "yes"
                    onCheckUpdateView(mMyVote)
                }

                checkBtnNo.setOnClickListener {
                    mMyVote = "no"
                    onCheckUpdateView(mMyVote)
                }

                checkBtnAbstain.setOnClickListener {
                    mMyVote = "abstain"
                    onCheckUpdateView(mMyVote)
                }

                btnCancel.setOnClickListener {
                    extend.onBeforeStep()
                }

                btnNext.setOnClickListener {
                    if (extend.mTxType == BaseConstant.CONST_PW_TX_DAO_SINGLE_PROPOSAL) {
                        extend.mOpinion = mMyVote
                    } else {

                    }
                    extend.onNextStep()
                }
            }
        }
    }

    private inner class MultiChoiceListAdapter : RecyclerView.Adapter<MultiChoiceListAdapter.ChoiceHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiChoiceListAdapter.ChoiceHolder {
            val binding = ItemMultiChoiceBinding.inflate(layoutInflater, parent, false)
            return ChoiceHolder(binding)
        }

        override fun getItemCount(): Int {
            return mChoiceList.size
        }

        override fun onBindViewHolder(holder: ChoiceHolder, position: Int) {
            holder.bind(position)
        }

        inner class ChoiceHolder(val multiChoiceBinding: ItemMultiChoiceBinding) : RecyclerView.ViewHolder(multiChoiceBinding.root) {
            fun bind(position: Int) {
                with(multiChoiceBinding) {
                    val choiceInfo = mChoiceList[position]
                    choiceTitle.text = choiceInfo.index.toString() + ". " + choiceInfo.option_type

                    choiceLayout.setOnClickListener {
                        getSActivity()?.mOptionId = choiceInfo.index
                        notifyDataSetChanged()
                    }
                    if (getSActivity()?.mOptionId == choiceInfo.index) {
                        choiceTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlackDayNight))
                        choiceLayout.background = resources.getDrawable(R.drawable.box_vote_selected)
                        choiceImg.setColorFilter(ContextCompat.getColor(requireContext(), R.color.colorBlackDayNight), android.graphics.PorterDuff.Mode.SRC_IN)
                    } else {
                        choiceTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGrayDayNight))
                        choiceLayout.background = resources.getDrawable(R.drawable.box_vote_quorum)
                        choiceImg.setColorFilter(ContextCompat.getColor(requireContext(), R.color.colorGrayDayNight), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun getSActivity(): DaoProposalActivity? {
        return baseActivity as? DaoProposalActivity
    }
}