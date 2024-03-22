package wannabit.io.cosmostaion.data.model.res

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcelable
import androidx.core.content.ContextCompat
import com.cosmos.gov.v1beta1.GovProto.VoteOption
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.R
import java.math.BigDecimal
import java.math.RoundingMode

@JsonClass(generateAdapter = true)
@Parcelize
data class CosmosProposal(
    val id: String?,
    val title: String?,
    val description: String?,
    val proposal_status: String?,
    val voting_end_time: String?,
    val is_expedited: Boolean = false,
    val yes: String?,
    val no: String?,
    val no_with_veto: String?,
    val abstain: String?,
) : Parcelable {

    private fun getSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        sum = sum.add(if (yes.isNullOrEmpty()) BigDecimal.ZERO else yes.toBigDecimal())
        sum = sum.add(if (no.isNullOrEmpty()) BigDecimal.ZERO else no.toBigDecimal())
        sum = sum.add(if (no_with_veto.isNullOrEmpty()) BigDecimal.ZERO else no_with_veto.toBigDecimal())
        sum = sum.add(if (abstain.isNullOrEmpty()) BigDecimal.ZERO else abstain.toBigDecimal())
        return sum
    }

    fun isVotingPeriod(): Boolean {
        proposal_status?.let { status ->
            if (status.contains("VOTING")) {
                return true
            }
        }
        return false
    }

    fun isScam(): Boolean {
        if ((if (yes.isNullOrEmpty()) BigDecimal.ZERO else yes.toBigDecimal()) == BigDecimal.ZERO || getSum() == BigDecimal.ZERO) return true
        if (BigDecimal(yes).divide(getSum(), 6, RoundingMode.DOWN) > BigDecimal("0.1")) return false
        return true
    }

    fun proposalStatusTxt(): String {
        proposal_status?.let { status ->
            if (status.contains("DEPOSIT")) return "DepositPeriod"
            else if (status.contains("VOTING")) return "VotingPeriod"
            else if (status.contains("PASSED")) return "Passed"
            else if (status.contains("REJECTED")) return "Rejected"
            else if (status.contains("FAILED")) return "Failed"
        }
        return "UnKnown"
    }

    fun proposalStatusImg(c: Context): Drawable? {
        proposal_status?.let { status ->
            if (status.contains("DEPOSIT")) return ContextCompat.getDrawable(
                c, R.drawable.icon_vote_deposit
            )
            else if (status.contains("VOTING")) return ContextCompat.getDrawable(
                c, R.drawable.icon_vote_passed
            )
            else if (status.contains("PASSED")) return ContextCompat.getDrawable(
                c, R.drawable.icon_vote_passed
            )
            else if (status.contains("REJECTED")) return ContextCompat.getDrawable(
                c, R.drawable.icon_vote_rejected
            )
        }
        return ContextCompat.getDrawable(c, R.drawable.icon_vote_fail)
    }

    var toVoteOption: VoteOption? = null
}

@JsonClass(generateAdapter = true)
data class VoteStatus(var votes: MutableList<VoteData> = mutableListOf())

data class VoteData(
    val proposal_id: String?,
    val votes: MutableList<Vote> = mutableListOf()
)

data class Vote(
    val voter: String?,
    val option: String?,
    val timestamp: String?
)