package wannabit.io.cosmostaion.fragment.txs.neutron.dao

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
import wannabit.io.cosmostaion.utils.makeToast

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
                    } ?: run {
                        proposalData.proposal?.expiration?.at_height?.let {
                            proposalDeadline.text = "Expiration at : ${it} Block"
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
        var myVote: String? = null
        with(binding) {
            getSActivity()?.let { extend ->
                checkBtnYes.setOnClickListener {
                    myVote = "yes"
                    onCheckUpdateView(myVote)
                }

                checkBtnNo.setOnClickListener {
                    myVote = "no"
                    onCheckUpdateView(myVote)
                }

                checkBtnAbstain.setOnClickListener {
                    myVote = "abstain"
                    onCheckUpdateView(myVote)
                }

                btnCancel.setOnClickListener {
                    extend.onBeforeStep()
                }

                btnNext.setOnClickListener {
                    if (extend.mTxType == BaseConstant.CONST_PW_TX_DAO_SINGLE_PROPOSAL) {
                        if (myVote != null) {
                            extend.mOpinion = myVote
                            extend.onNextStep()
                        } else {
                            requireActivity().makeToast(R.string.error_not_selected_vote)
                            return@setOnClickListener
                        }

                    } else if (extend.mTxType == BaseConstant.CONST_PW_TX_DAO_MULTI_PROPOSAL) {
                        if (extend.mOptionId != -1) {
                            extend.onNextStep()
                        } else {
                            requireActivity().makeToast(R.string.error_not_selected_vote)
                            return@setOnClickListener
                        }
                    } else {

                    }
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