package wannabit.io.cosmostaion.data.model.res

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.cosmos.gov.v1beta1.GovProto.VoteOption
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import wannabit.io.cosmostaion.R
import java.math.BigDecimal
import java.math.RoundingMode

@JsonClass(generateAdapter = true)
data class CosmosProposal(
    val id: String?,
    val title: String?,
    val description: String?,
    @Json(name = "proposal_type") val proposalType: String?,
    @Json(name = "proposal_status") val proposalStatus: String?,
    @Json(name = "voting_start_time") val votingStartTime: String?,
    @Json(name = "voting_end_time") val votingEndTime: String?,
    @Json(name = "is_expedited") val isExpedited: Boolean = false,
    val yes: String?,
    val no: String?,
    @Json(name = "no_with_veto") val veto: String?,
    val abstain: String?,
) {

    fun getSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        sum = sum.add(yes?.toBigDecimal())
        sum = sum.add(no?.toBigDecimal())
        sum = sum.add(veto?.toBigDecimal())
        sum = sum.add(abstain?.toBigDecimal())
        return sum
    }

    fun isVotingPeriod(): Boolean {
        proposalStatus?.let { status ->
            if (status.contains("VOTING")) {
                return true
            }
        }
        return false
    }

    fun isScam(): Boolean {
        if (BigDecimal(yes) == BigDecimal.ZERO || getSum() == BigDecimal.ZERO) return true
        if (BigDecimal(yes).divide(getSum(), 6, RoundingMode.DOWN) > BigDecimal("0.1")) return false
        return true
    }

    fun proposalStatusTxt(): String {
        proposalStatus?.let { status ->
            if (status.contains("DEPOSIT")) return "DepositPeriod"
            else if (status.contains("VOTING")) return "VotingPeriod"
            else if (status.contains("PASSED")) return "Passed"
            else if (status.contains("REJECTED")) return "Rejected"
            else if (status.contains("FAILED")) return "Failed"
        }
        return "UnKnown"
    }

    fun proposalStatusImg(c: Context): Drawable? {
        proposalStatus?.let { status ->
            if (status.contains("DEPOSIT")) return ContextCompat.getDrawable(c, R.drawable.icon_vote_deposit)
            else if (status.contains("VOTING")) return ContextCompat.getDrawable(c, R.drawable.icon_vote_passed)
            else if (status.contains("PASSED")) return ContextCompat.getDrawable(c, R.drawable.icon_vote_passed)
            else if (status.contains("REJECTED")) return ContextCompat.getDrawable(c, R.drawable.icon_vote_rejected)
        }
        return ContextCompat.getDrawable(c, R.drawable.icon_vote_fail)
    }

    var toVoteOption: VoteOption? = null
}

@JsonClass(generateAdapter = true)
data class VoteStatus(var votes: MutableList<VoteData> = mutableListOf())

data class VoteData(
    @Json(name = "proposal_id") val proposalId: String?,
    val votes: MutableList<Vote> = mutableListOf()
)

data class Vote(
    val voter: String?,
    val option: String?,
    @Json(name = "tx_hash") val txHash: String?,
    val timestamp: String?
)