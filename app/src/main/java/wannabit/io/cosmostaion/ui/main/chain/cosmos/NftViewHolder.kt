package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.fetcher.suiNftUrl
import wannabit.io.cosmostaion.data.model.req.Cw721TokenModel
import wannabit.io.cosmostaion.databinding.ItemNftBinding

class NftViewHolder(
    val context: Context,
    private val binding: ItemNftBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(info: JsonObject, nft: Cw721TokenModel) {
        binding.apply {
            nftImg.clipToOutline = true
            nft.tokenDetail?.let {
                Glide.with(context).load(it.asJsonObject["url"].asString).diskCacheStrategy(
                    DiskCacheStrategy.ALL
                ).placeholder(R.drawable.icon_nft_default).error(R.drawable.icon_nft_default)
                    .into(nftImg)

            } ?: run {
                nftImg.setImageResource(R.drawable.icon_nft_default_alpha)
            }
            nftTitle.text = info["name"].asString + " #" + nft.tokenId
        }
    }

    fun suiBind(suiNft: JsonObject) {
        binding.apply {
            nftImg.clipToOutline = true
            suiNft["data"].asJsonObject.suiNftUrl()?.let { url ->
                Glide.with(context).load(url).diskCacheStrategy(
                    DiskCacheStrategy.ALL
                ).placeholder(R.drawable.icon_nft_default).error(R.drawable.icon_nft_default)
                    .into(nftImg)
            } ?: run {
                nftImg.setImageResource(R.drawable.icon_nft_default_alpha)
            }

            val name = try {
                suiNft["data"].asJsonObject["display"].asJsonObject["data"].asJsonObject["name"].asString
            } catch (e: Exception) {
                ""
            }
            val objectId = try {
                suiNft["data"].asJsonObject["objectId"].asString
            } catch (e: Exception) {
                ""
            }
            nftTitle.text = name.ifEmpty {
                objectId
            }
        }
    }
}