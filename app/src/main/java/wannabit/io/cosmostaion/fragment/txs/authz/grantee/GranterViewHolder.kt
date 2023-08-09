package wannabit.io.cosmostaion.fragment.txs.authz.grantee

import androidx.recyclerview.widget.RecyclerView
import cosmos.authz.v1beta1.Authz
import cosmos.authz.v1beta1.Authz.GrantAuthorization
import cosmos.bank.v1beta1.Authz.SendAuthorization
import cosmos.staking.v1beta1.Authz.AuthorizationType
import cosmos.staking.v1beta1.Authz.StakeAuthorization
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.ItemGranterBinding
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.setAuthzType

class GranterViewHolder(
    private val binding: ItemGranterBinding,
    private val selectedItems: MutableList<GrantAuthorization>
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(grant: GrantAuthorization) {
        binding.apply {
            cardRoot.apply {
                setBackgroundResource(R.drawable.box_white2_border)
                setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        toggleItemSelection(selectedItems, grant)
                        if (selectedItems.contains(grant)) {
                            setBackgroundResource(R.drawable.box_round_white)
                        } else {
                            setBackgroundResource(R.drawable.box_white2_border)
                        }
                    }
                }
            }

            grantee.text = grant.grantee
            grantType.text = setAuthzType(grant)
            expiration.text = WDp.getTimeWithoutTransVerse(grant.expiration.seconds * 1000)
        }
    }
}

private fun toggleItemSelection(selectedItems: MutableList<GrantAuthorization>, granter: GrantAuthorization) {
    if (selectedItems.contains(granter)) {
        selectedItems.remove(granter)
    } else {
        selectedItems.add(granter)
    }
}
