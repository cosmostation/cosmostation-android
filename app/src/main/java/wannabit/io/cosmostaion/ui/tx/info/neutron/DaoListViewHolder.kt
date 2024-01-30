package wannabit.io.cosmostaion.ui.tx.info.neutron

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.common.gapTime
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.common.voteDpTime
import wannabit.io.cosmostaion.data.model.res.ProposalData
import wannabit.io.cosmostaion.data.model.res.ProposalModule
import wannabit.io.cosmostaion.data.model.res.ResDaoVoteStatus
import wannabit.io.cosmostaion.databinding.ItemDaoProposalBinding

class DaoListViewHolder(
    val context: Context,
    private val binding: ItemDaoProposalBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        module: ProposalModule?,
        proposal: Pair<String?, ProposalData?>,
        voteStatus: MutableList<ResDaoVoteStatus>,
        headerCnt: Int,
        isHeader: Boolean,
        checkListener: DaoListAdapter.CheckListener
    ) {
        binding.apply {
            proposalView.setBackgroundResource(R.drawable.item_bg)
            headerLayout.visibleOrGone(isHeader)

            module?.let { module ->
                val moduleName = if (module.name?.isNotEmpty() == true) {
                    module.name.lowercase().substring(0, 1).uppercase() + module.name.substring(1)
                } else {
                    module.name
                }
                headerTitle.text = moduleName
            }
            cnt.text = headerCnt.toString()

            proposalView.setOnClickListener {
                val moduleType = if (module?.name == "Single Module") "single" else "multiple"
                val url: String =
                    CosmostationConstants.EXPLORER_BASE_URL + "neutron/dao/proposals/${proposal.second?.id}/ $moduleType/${module?.address}"
                Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                    context.startActivity(this)
                }
            }

            proposal.second?.let { proposalData ->
                voteId.text = "# ${proposalData.id}."
                voteTitle.text = proposalData.proposal?.title

                if ("open" == proposalData.proposal?.status) {
                    proposalData.proposal.expiration?.at_time?.toLong()?.let { expiration ->
                        voteRemainTime.text = voteDpTime(expiration.div(1000000)) + " (" + gapTime(
                            expiration.div(1000000)
                        ) + ")"
                        voteRemainTime.setTextColor(
                            ContextCompat.getColorStateList(
                                context,
                                R.color.color_base02
                            )
                        )
                        selectSwitch.visibility = View.VISIBLE
                        selectSwitch.thumbDrawable =
                            ContextCompat.getDrawable(context, R.drawable.switch_thumb_off)
                    }

                } else {
                    when (proposalData.proposal?.status) {
                        "executed", "passed" -> voteStatusImg.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.icon_vote_passed
                            )
                        )

                        "rejected", "failed" -> voteStatusImg.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.icon_vote_rejected
                            )
                        )
                    }
                    voteRemainTime.text = proposalData.proposal?.status?.uppercase()
                    voteRemainTime.setTextColor(
                        ContextCompat.getColorStateList(
                            context,
                            R.color.color_base01
                        )
                    )
                    selectSwitch.visibility = View.GONE
                }

                voteStatus.firstOrNull { it.contract_address == module?.address && it.proposal_id.toString() == proposal.second?.id }
                    ?.let { myVote ->
                        myVote.option?.let { option ->
                            if (isOptionInteger(option)) {
                                statusImg.visibility = View.GONE
                                statusTxt.visibility = View.VISIBLE
                                statusTxt.text = "OPTION $option"

                            } else {
                                statusImg.visibility = View.VISIBLE
                                statusTxt.visibility = View.GONE
                                when (option) {
                                    "yes" -> statusImg.setImageResource(R.drawable.icon_yes)
                                    "no" -> statusImg.setImageResource(R.drawable.icon_no)
                                    "abstain" -> statusImg.setImageResource(R.drawable.icon_abstain)
                                    else -> statusImg.setImageResource(R.drawable.icon_not_voted)
                                }
                            }
                        } ?: run {
                            statusImg.visibility = View.VISIBLE
                            statusTxt.visibility = View.GONE
                            statusImg.setImageResource(R.drawable.icon_not_voted)
                        }

                    } ?: run {
                    statusImg.visibility = View.VISIBLE
                    statusTxt.visibility = View.GONE
                    statusImg.setImageResource(R.drawable.icon_not_voted)
                }

                selectSwitch.setOnCheckedChangeListener(null)
                selectSwitch.isChecked = proposalData.isSwitchChecked
                selectSwitch.thumbDrawable = ContextCompat.getDrawable(
                    context,
                    if (proposalData.isSwitchChecked) R.drawable.switch_thumb_on else R.drawable.switch_thumb_off
                )

                selectSwitch.setOnCheckedChangeListener { _, isChecked ->
                    proposalData.isSwitchChecked = isChecked
                    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(100, 50))
                    } else {
                        vibrator.vibrate(100)
                    }

                    checkListener.daoProposalCheck(
                        isChecked, proposal.first, module?.name, proposalData.id
                    )
                }
            }
        }
    }

    private fun isOptionInteger(option: String?): Boolean {
        return try {
            option?.toInt()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
}