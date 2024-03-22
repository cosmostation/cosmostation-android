package wannabit.io.cosmostaion.ui.tx.info.neutron

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.NEUTRON_MULTI_MODULE
import wannabit.io.cosmostaion.chain.cosmosClass.NEUTRON_SINGLE_MODULE
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.common.gapTime
import wannabit.io.cosmostaion.common.voteDpTime
import wannabit.io.cosmostaion.data.model.res.ProposalData
import wannabit.io.cosmostaion.data.model.res.ResDaoVoteStatus
import wannabit.io.cosmostaion.databinding.ItemDaoProposalBinding

class DaoViewHolder(
    val context: Context,
    private val binding: ItemDaoProposalBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        selectedChain: ChainNeutron,
        type: Int,
        proposal: ProposalData?,
        neutronMyVotes: MutableList<ResDaoVoteStatus>?,
        checkListener: DaoProposalListAdapter.CheckListener
    ) {
        binding.apply {
            proposalView.setBackgroundResource(R.drawable.item_bg)
            headerLayout.visibility = View.GONE

            voteId.text = "# ${proposal?.id}."
            voteTitle.text = proposal?.proposal?.title

            proposal?.let { proposalData ->
                if ("open" == proposalData.proposal?.status) {
                    proposalData.proposal.expiration?.at_time?.toLong()?.let { expiration ->
                        voteRemainTime.text = voteDpTime(expiration.div(1000000)) + " (" + gapTime(
                            expiration.div(1000000)
                        ) + ")"
                        voteRemainTime.setTextColor(
                            ContextCompat.getColorStateList(
                                context, R.color.color_base02
                            )
                        )

                    } ?: run {
                        proposalData.proposal.expiration?.at_height?.let {
                            voteRemainTime.text = "Expiration at : ${it} Block"
                            voteRemainTime.setTextColor(
                                ContextCompat.getColorStateList(
                                    context, R.color.color_base02
                                )
                            )
                        }
                    }
                    selectSwitch.visibility = View.VISIBLE
                    switchView.visibility = View.VISIBLE
                    selectSwitch.thumbDrawable =
                        ContextCompat.getDrawable(context, R.drawable.switch_thumb_off)

                } else {
                    when (proposalData.proposal?.status) {
                        "executed", "passed" -> voteStatusImg.setImageDrawable(
                            ContextCompat.getDrawable(
                                context, R.drawable.icon_vote_passed
                            )
                        )

                        "rejected", "failed", "execution_failed" -> voteStatusImg.setImageDrawable(
                            ContextCompat.getDrawable(
                                context, R.drawable.icon_vote_rejected
                            )
                        )
                    }
                    voteRemainTime.text = proposalData.proposal?.status?.uppercase()
                    voteRemainTime.setTextColor(
                        ContextCompat.getColorStateList(
                            context, R.color.color_base01
                        )
                    )
                    selectSwitch.visibility = View.GONE
                    switchView.visibility = View.GONE
                }

                val module = when (type) {
                    NEUTRON_SINGLE_MODULE -> {
                        selectedChain.getChainListParam()?.getAsJsonArray("daos")
                            ?.get(0)?.asJsonObject?.getAsJsonArray("proposal_modules")
                            ?.get(0)?.asJsonObject
                    }

                    NEUTRON_MULTI_MODULE -> {
                        selectedChain.getChainListParam()?.getAsJsonArray("daos")
                            ?.get(0)?.asJsonObject?.getAsJsonArray("proposal_modules")
                            ?.get(1)?.asJsonObject
                    }

                    else -> {
                        selectedChain.getChainListParam()?.getAsJsonArray("daos")
                            ?.get(0)?.asJsonObject?.getAsJsonArray("proposal_modules")
                            ?.get(2)?.asJsonObject
                    }
                }

                neutronMyVotes?.firstOrNull { it.contract_address == module?.get("address")?.asString && it.proposal_id.toString() == proposal.id }
                    ?.let { myVote ->
                        myVote.option?.let { option ->
                            if (option.isOptionInteger()) {
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

                    val thumbDrawable: Drawable? = if (isChecked) {
                        ContextCompat.getDrawable(context, R.drawable.switch_thumb_on)
                    } else {
                        ContextCompat.getDrawable(context, R.drawable.switch_thumb_off)
                    }
                    selectSwitch.thumbDrawable = thumbDrawable

                    checkListener.daoProposalCheck(
                        isChecked, proposalData.id
                    )
                }

                proposalView.setOnClickListener {
                    val url: String = when (type) {
                        NEUTRON_SINGLE_MODULE -> {
                            CosmostationConstants.EXPLORER_BASE_URL + "neutron/dao/proposals/" + proposal.id + "/single/" + module?.get(
                                "address"
                            )?.asString
                        }

                        NEUTRON_MULTI_MODULE -> {
                            CosmostationConstants.EXPLORER_BASE_URL + "neutron/dao/proposals/" + proposal.id + "/multiple/" + module?.get(
                                "address"
                            )?.asString
                        }

                        else -> {
                            CosmostationConstants.EXPLORER_BASE_URL + "neutron/dao/proposals/" + proposal.id + "/overrule/" + module?.get(
                                "address"
                            )?.asString
                        }
                    }
                    Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                        context.startActivity(this)
                    }
                }
            }
        }
    }
}

private fun String?.isOptionInteger(): Boolean {
    return try {
        this?.toInt()
        true
    } catch (e: NumberFormatException) {
        false
    }
}