package wannabit.io.cosmostaion.fragment.txs.neutron

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.google.gson.Gson
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.txs.neutron.DaoProposalActivity
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentDaoVoteStep0Binding
import wannabit.io.cosmostaion.model.viewModel.NeutronViewModel
import wannabit.io.cosmostaion.network.res.neutron.ProposalData
import wannabit.io.cosmostaion.utils.WDp

class DaoVoteStep0Fragment : BaseFragment() {

    private var _binding: FragmentDaoVoteStep0Binding? = null
    private val binding get() = _binding!!

    private val neutronViewModel: NeutronViewModel by activityViewModels()

    private var mMyVote: String? = null

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
            neutronViewModel.data.observe(viewLifecycleOwner) {
                Gson().fromJson(it[0].toString(), ProposalData::class.java)?.let { proposalData ->
                    proposalId.text = "# " + proposalData.id
                    proposalTitle.text = proposalData.proposal?.title
                    proposalData.proposal?.expiration?.at_time?.toLong()?.let { expiration ->
                        proposalDeadline.text = WDp.getDpTime(requireContext(), expiration.div(1000000)) + " " + WDp.getGapTime(expiration.div(1000000))
                    }
                }
            }
        }
    }

    private fun onCheckUpdateView(myVote: String?) {
        with(binding) {
            checkBtnYes.background = resources.getDrawable(R.drawable.box_vote_quorum)
            checkBtnNo.background = resources.getDrawable(R.drawable.box_vote_quorum)
            checkBtnAbstain.background = resources.getDrawable(R.drawable.box_vote_quorum)
            checkImgSelectedYes.clearColorFilter()
            checkImgSelectedNo.clearColorFilter()
            checkImgSelectedAbstain.clearColorFilter()

            when (myVote) {
                "yes" -> {
                    checkBtnYes.background = resources.getDrawable(R.drawable.box_vote_selected)
                    checkImgSelectedYes.setColorFilter(ContextCompat.getColor(requireContext(), R.color.colorWhite), android.graphics.PorterDuff.Mode.SRC_IN);
                }
                "no" -> {
                    checkBtnNo.background = resources.getDrawable(R.drawable.box_vote_selected)
                    checkImgSelectedNo.setColorFilter(ContextCompat.getColor(requireContext(), R.color.colorWhite), android.graphics.PorterDuff.Mode.SRC_IN);
                }
                else -> {
                    checkBtnAbstain.background = resources.getDrawable(R.drawable.box_vote_selected)
                    checkImgSelectedAbstain.setColorFilter(ContextCompat.getColor(requireContext(), R.color.colorWhite), android.graphics.PorterDuff.Mode.SRC_IN);
                }
            }
        }
    }

    fun onClick() {
        with(binding) {
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
                getSActivity()?.onBeforeStep()
            }

            btnNext.setOnClickListener {
                getSActivity()?.mOpinion = mMyVote
                getSActivity()?.onNextStep()
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