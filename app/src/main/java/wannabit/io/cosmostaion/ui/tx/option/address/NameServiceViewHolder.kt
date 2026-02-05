package wannabit.io.cosmostaion.ui.tx.option.address

import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.data.model.res.NameService
import wannabit.io.cosmostaion.databinding.ItemNameServiceBinding

class NameServiceViewHolder(
    private val binding: ItemNameServiceBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(nameService: NameService) {
        binding.apply {
            when (nameService.type) {
                NameService.NameServiceType.ENS -> {
                    nsType.text = "Ethereum Name Service"
                    nsImg.setImageResource(R.drawable.icon_ens)
                }
                NameService.NameServiceType.SUI -> {
                    nsType.text = "Sui Name Service"
                    nsImg.setImageResource(R.drawable.icon_suins)
                }
                NameService.NameServiceType.IOTA -> {
                    nsType.text = "Iota Name Service"
                    nsImg.setImageResource(R.drawable.icon_iotans)
                }
                NameService.NameServiceType.MOVE -> {
                    nsType.text = "Aptos Name Service"
                    nsImg.setImageResource(R.drawable.icon_movens)
                }
                NameService.NameServiceType.SOL -> {
                    nsType.text = "Solana Name Service"
                    nsImg.setImageResource(R.drawable.icon_solanans)
                }
                NameService.NameServiceType.STARGAZE -> {
                    nsType.text = "Stargaze Name Service"
                    nsImg.setImageResource(R.drawable.icon_stargaze_ns)
                }
                NameService.NameServiceType.ARCHWAY -> {
                    nsType.text = "Archway ARCH ID"
                    nsImg.setImageResource(R.drawable.icon_archway_ns)
                }
                else -> {
                    nsType.text = "Osmosis ICNS"
                    nsImg.setImageResource(R.drawable.icon_icns)
                }
            }
            accountAddress.text = nameService.address
        }
    }
}