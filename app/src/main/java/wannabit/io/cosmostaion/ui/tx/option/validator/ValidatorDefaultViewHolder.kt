package wannabit.io.cosmostaion.ui.tx.option.validator

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.staking.v1beta1.StakingProto
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.suiValidatorCommission
import wannabit.io.cosmostaion.chain.fetcher.suiValidatorImg
import wannabit.io.cosmostaion.chain.fetcher.suiValidatorName
import wannabit.io.cosmostaion.chain.fetcher.suiValidatorVp
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.isActiveValidator
import wannabit.io.cosmostaion.common.setImageFromSvg
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.databinding.ItemValidatorDefaultBinding
import java.math.RoundingMode

class ValidatorDefaultViewHolder(
    val context: Context, private val binding: ItemValidatorDefaultBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain, validator: StakingProto.Validator) {
        binding.apply {
            validator.let { validator ->
                monikerImg.setMonikerImg(chain, validator.operatorAddress)
                monikerName.text = validator.description?.moniker
                if (validator.jailed) {
                    jailedImg.visibility = View.VISIBLE
                    jailedImg.setImageResource(R.drawable.icon_jailed)
                } else if (!validator.isActiveValidator(chain)) {
                    jailedImg.visibility = View.VISIBLE
                    jailedImg.setImageResource(R.drawable.icon_inactive)
                } else {
                    jailedImg.visibility = View.GONE
                }
            }

            BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                asset.decimals?.let { decimal ->
                    val vpAmount = validator.tokens?.toBigDecimal()?.movePointLeft(decimal)
                    votingPower.text = formatAmount(vpAmount.toString(), 0)

                    val commissionRate = validator.commission?.commissionRates?.rate?.toBigDecimal()
                        ?.movePointLeft(16)?.setScale(2, RoundingMode.DOWN)
                    commission.text = formatString("$commissionRate%", 3)
                }
            }
        }
    }

    fun suiBind(toValidator: JsonObject) {
        binding.apply {
            jailedImg.visibility = View.GONE
            monikerImg.setImageFromSvg(
                toValidator.suiValidatorImg(), R.drawable.icon_default_vaildator
            )
            monikerName.text = toValidator.suiValidatorName()

            votingPower.text = formatAmount(toValidator.suiValidatorVp().toString(), 0)
            commission.text = formatString("${toValidator.suiValidatorCommission()}%", 3)
        }
    }
}