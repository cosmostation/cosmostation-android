package wannabit.io.cosmostaion.ui.main.edit

import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.databinding.ItemTokenEditBinding

class TokenEditViewHolder(
    val binding: ItemTokenEditBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(token: Token) {
        binding.apply {
            tokenImg.setTokenImg(token.assetImg())
            tokenName.text = token.symbol
            tokenDescription.text = token.description
        }
    }
}