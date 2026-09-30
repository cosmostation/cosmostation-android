package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.JsonObject
import com.sui.rpc.v2.ObjectProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.fetcher.getStringField
import wannabit.io.cosmostaion.chain.fetcher.moveNftUrl
import wannabit.io.cosmostaion.chain.fetcher.suiNftUrl
import wannabit.io.cosmostaion.data.model.req.Cw721TokenModel
import wannabit.io.cosmostaion.data.model.res.Cw721
import wannabit.io.cosmostaion.databinding.ItemNftBinding

class NftViewHolder(
    val context: Context,
    private val binding: ItemNftBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(info: Cw721, nft: Cw721TokenModel) {
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
            nftTitle.text = info.name + " #" + nft.tokenId
        }
    }

    fun suiBind(suiNft: ObjectProto.Object) {
        binding.apply {
            nftImg.clipToOutline = true
            val imageUrl = if (suiNft.hasDisplay()) suiNft.display.output.suiNftUrl() else null
            imageUrl?.let { url ->
                Glide.with(context).load(url).diskCacheStrategy(
                    DiskCacheStrategy.ALL
                ).placeholder(R.drawable.icon_nft_default).error(R.drawable.icon_nft_default)
                    .into(nftImg)
            } ?: run {
                nftImg.setImageResource(R.drawable.icon_nft_default_alpha)
            }

            val name = if (suiNft.hasDisplay()) suiNft.display.output.getStringField("name") ?: "" else ""
            val objectId = suiNft.objectId

            nftTitle.text = name.ifEmpty { objectId }
        }
    }

    fun iotaBind(iotaNft: JsonObject) {
        binding.apply {
            nftImg.clipToOutline = true
            iotaNft["data"].asJsonObject.moveNftUrl()?.let { url ->
                Glide.with(context).load(url).diskCacheStrategy(
                    DiskCacheStrategy.ALL
                ).placeholder(R.drawable.icon_nft_default).error(R.drawable.icon_nft_default)
                    .into(nftImg)
            } ?: run {
                nftImg.setImageResource(R.drawable.icon_nft_default_alpha)
            }

            val name = try {
                iotaNft["data"].asJsonObject["display"].asJsonObject["data"].asJsonObject["name"].asString
            } catch (e: Exception) {
                ""
            }
            val objectId = try {
                iotaNft["data"].asJsonObject["objectId"].asString
            } catch (e: Exception) {
                ""
            }
            nftTitle.text = name.ifEmpty {
                objectId
            }
        }
    }
}