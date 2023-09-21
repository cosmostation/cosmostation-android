package wannabit.io.cosmostaion.fragment.txs.authz.grantee

import androidx.recyclerview.widget.RecyclerView
import cosmos.authz.v1beta1.Authz
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.ItemRevokeBinding
import wannabit.io.cosmostaion.utils.setAuthzType

class RevokeViewHolder(
    private val binding: ItemRevokeBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(grant: Authz.GrantAuthorization) {
        binding.apply {
            cardRoot.setBackgroundResource(R.drawable.box_white2_border)
            grantee.text = grant.grantee
            granteeType.text = setAuthzType(grant)
        }
    }
}