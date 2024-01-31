package wannabit.io.cosmostaion.data.model.res

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

data class VestingData(
    val allocated_amount: String?,
    val withdrawn_amount: String?,
)

data class Vault(
    val name: String?,
    val description: String?,
    val address: String?,
)

data class Dao(
    val name: String?,
    val description: String?,
    val dao_uri: String?,
    val address: String?,
    val voting_module: String?,
    val proposal_modules: MutableList<ProposalModule?>
)

data class ProposalModule(
    val name: String?,
    val description: String?,
    val address: String?,
    val prefix: String?,
    val status: String?
)


data class ResProposalData(val proposals: MutableList<ProposalData?>)

@Parcelize
data class ProposalData(
    val id: String?, val proposal: Proposal?, var isSwitchChecked: Boolean = false
) : Parcelable {
    var myVote: String? = ""

    companion object : Parceler<ProposalData> {
        override fun ProposalData.write(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeParcelable(proposal, flags)
            parcel.writeByte(if (isSwitchChecked) 1 else 0)
            parcel.writeString(myVote)
        }

        override fun create(parcel: Parcel): ProposalData = TODO()
    }
}

@Parcelize
data class Proposal(
    val title: String?,
    val expiration: Expiration?,
    val status: String?,
    val choices: MutableList<Choice?>,
    val allow_revoting: Boolean
) : Parcelable {
    companion object : Parceler<Proposal> {
        override fun Proposal.write(parcel: Parcel, flags: Int) {
            parcel.writeString(title)
            parcel.writeParcelable(expiration, flags)
            parcel.writeString(status)
            parcel.writeList(choices)
            parcel.writeByte(if (allow_revoting) 1 else 0)
        }

        override fun create(parcel: Parcel): Proposal = TODO()
    }
}

@Parcelize
data class Expiration(val at_time: String?, val at_height: String?) : Parcelable

@Parcelize
data class Choice(val index: Int?, val option_type: String?, val description: String?) : Parcelable

@Parcelize
data class ResDaoVoteStatus(
    val id: Int?,
    val chain: String?,
    val chain_id: String?,
    val height: Long?,
    val tx_hash: String?,
    val contract_address: String?,
    val address: String?,
    val proposal_id: Int?,
    val power: String?,
    val option: String?,
    val voted_at: String?
) : Parcelable {
    companion object : Parceler<ResDaoVoteStatus> {
        override fun ResDaoVoteStatus.write(parcel: Parcel, flags: Int) {
            parcel.writeInt(id ?: -1)
            parcel.writeString(chain)
            parcel.writeString(chain_id)
            parcel.writeLong(height ?: -1L)
            parcel.writeString(tx_hash)
            parcel.writeString(contract_address)
            parcel.writeString(address)
            parcel.writeInt(proposal_id ?: -1)
            parcel.writeString(power)
            parcel.writeString(option)
            parcel.writeString(voted_at)
        }

        override fun create(parcel: Parcel): ResDaoVoteStatus = TODO()
    }
}