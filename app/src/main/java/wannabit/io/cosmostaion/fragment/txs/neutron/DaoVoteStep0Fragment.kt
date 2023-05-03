package wannabit.io.cosmostaion.fragment.txs.neutron

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.txs.neutron.dao.DaoProposalActivity
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentDaoVoteStep0Binding
import wannabit.io.cosmostaion.utils.WDp

class DaoVoteStep0Fragment : BaseFragment() {

    private var _binding: FragmentDaoVoteStep0Binding? = null
    private val binding get() = _binding!!

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
        binding.apply {
            getSActivity()?.let {
                if (it.mTxType == BaseConstant.CONST_PW_TX_DAO_SINGLE_PROPOSAL) {
                    singleLayout.visibility = View.VISIBLE
                    recycler.visibility = View.GONE
                } else if (it.mTxType == BaseConstant.CONST_PW_TX_DAO_MULTI_PROPOSAL) {
                    singleLayout.visibility = View.GONE
                    recycler.visibility = View.VISIBLE

                    recycler.layoutManager = LinearLayoutManager(requireContext())
                    recycler.adapter = MultiChoiceListAdapter(requireContext(), it.mProposalData.proposal?.choices, choiceClickAction)
                }
                it.mProposalData?.let { proposalData ->
                    proposalId.text = "# ${proposalData.id}"
                    proposalTitle.text = proposalData.proposal?.title
                    proposalDescription.text = proposalData.proposal?.description
                    proposalData.proposal?.expiration?.at_time?.toLong()?.let { expiration ->
                        proposalDeadline.text = WDp.getDpTime(requireContext(), expiration.div(1000000)) + " " + WDp.getGapTime(expiration.div(1000000))
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
        var mMyVote: String? = null
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

    private val choiceClickAction = object : MultiChoiceListAdapter.ClickListener {
        override fun onClickChoice(optionId: Int) {
            getSActivity()?.mOptionId = optionId
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