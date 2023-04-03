package wannabit.io.cosmostaion.network.res

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.google.gson.annotations.SerializedName
import wannabit.io.cosmostaion.R
import java.math.BigDecimal
import java.math.RoundingMode

class ResV1Proposal {
    @SerializedName("id") var id = 0
    @SerializedName("title") var title: String? = null
    @SerializedName("description") var description: String? = null
    @SerializedName("proposal_status") var proposal_status: String? = null
    @SerializedName("yes") var yes: String? = null
    @SerializedName("no") var no: String? = null
    @SerializedName("no_with_veto") var no_with_veto: String? = null
    @SerializedName("abstain") var abstain: String? = null
    @SerializedName("voting_end_time") var voting_end_time: String? = null
    @SerializedName("is_expedited") var is_expedited = false

    private fun getSum(): BigDecimal {
        return BigDecimal(yes).add(BigDecimal(no)).add(BigDecimal(abstain)).add(BigDecimal(no_with_veto))
    }

    fun isVotingPeriod(): Boolean {
        proposal_status?.let { status ->
            if (status.contains("VOTING")) {
                return true
            }
        }
        return false
    }

    fun isDepositPeriod(): Boolean {
        proposal_status?.let { status ->
            if (status.contains("DEPOSIT")) {
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

    fun onProposalStatusTxt(): String {
        proposal_status?.let { status ->
            if (status.contains("DEPOSIT")) return "DepositPeriod"
            else if (status.contains("VOTING")) return "VotingPeriod"
            else if (status.contains("PASSED")) return "Passed"
            else if (status.contains("REJECTED")) return "Rejected"
            else if (status.contains("FAILED")) return "Failed"
        }
        return "UnKnown"
    }

    fun onProposalStatusImg(c: Context): Drawable? {
        proposal_status?.let { status ->
            if (status.contains("DEPOSIT")) return ContextCompat.getDrawable(c, R.drawable.ic_deposit_img)
            else if (status.contains("VOTING")) return ContextCompat.getDrawable(c, R.drawable.ic_voting_img)
            else if (status.contains("PASSED")) return ContextCompat.getDrawable(c, R.drawable.ic_passed_img)
            else if (status.contains("REJECTED")) return ContextCompat.getDrawable(c, R.drawable.ic_rejected_img)
        }
        return ContextCompat.getDrawable(c, R.drawable.ic_failed_img)
    }
}
