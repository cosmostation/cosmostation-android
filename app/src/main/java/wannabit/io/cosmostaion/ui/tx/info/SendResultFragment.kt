package wannabit.io.cosmostaion.ui.tx.info

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.gson.Gson
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dateToLong
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.gapPastTime
import wannabit.io.cosmostaion.common.historyToMintscan
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.txDpTime
import wannabit.io.cosmostaion.data.model.res.CosmosHistory
import wannabit.io.cosmostaion.databinding.FragmentSendResultBinding
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import java.math.RoundingMode

class SendResultFragment(val selectedChain: BaseChain, private val history: CosmosHistory) :
    BaseTxFragment() {

    private var _binding: FragmentSendResultBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSendResultBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setUpClickAction()
    }

    private fun initData() {
        binding?.apply {
            informationView.setBackgroundResource(R.drawable.cell_bg)
            initChainImage(selectedChain)

            if (history.isSuccess()) {
                transferImg.setImageResource(R.drawable.icon_success)
            } else {
                transferImg.setImageResource(R.drawable.icon_fail)
            }
            transferHash.text = history.data?.txhash

            history.getDpCoin(selectedChain)[0].let { coin ->
                BaseData.getAsset(selectedChain.apiName, coin.denom)?.let { asset ->
                    tokenImg.setTokenImg(asset)
                    tokenName.text = asset.symbol
                    val dpAmount = coin.amount.toBigDecimal().movePointLeft(asset.decimals ?: 6)
                        .setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                    total.text = formatAmount(
                        dpAmount.toPlainString(), asset.decimals ?: 6
                    )
                }
            }

            history.getMsgs()?.get(0)?.let { msg ->
                val msgType = msg.asJsonObject["@type"]?.asString
                val msgValue = msg.asJsonObject[msgType?.replace(".", "-")]
                fromAddressTxt.text = msgValue.asJsonObject["from_address"].asString
                toAddressTxt.text = msgValue.asJsonObject["to_address"].asString

                val json = Gson().toJson(history.data?.tx)
                val jsonObject = Gson().fromJson(json, JsonObject::class.java)
                memoTxt.text =
                    if (jsonObject["/cosmos-tx-v1beta1-Tx"].asJsonObject["body"].asJsonObject["memo"].asString.isEmpty()) {
                        "-"
                    } else {
                        jsonObject["/cosmos-tx-v1beta1-Tx"].asJsonObject["body"].asJsonObject["memo"].asString
                    }
                heightTxt.text = history.data?.height
                history.header?.timestamp?.let {
                    gapTimeTxt.text =
                        gapPastTime(dateToLong(getString(R.string.str_tx_time_grpc_format), it))
                    timeTxt.text = "(" + txDpTime(requireContext(), it) + ")"
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding?.apply {
            btnChrome.setOnClickListener {
                requireActivity().historyToMintscan(selectedChain, history.data?.txhash)
            }

            btnConfirm.setOnClickListener {
                dismiss()
            }
        }
    }

    private fun initChainImage(chain: BaseChain) {
        try {
            binding?.chainLogo?.apply {
                val width = resources.displayMetrics.widthPixels
                val height = resources.displayMetrics.heightPixels
                val x = (0..width - 150 - 150).random().toFloat()
                val y = (800..height - 150).random().toFloat()

                setImageResource(chain.logo)
                alpha = 0f
                this.x = x
                this.y = y
                layoutParams = FrameLayout.LayoutParams(800, 800)

                ObjectAnimator.ofFloat(this, "alpha", 0f, 0.1f).apply {
                    duration = 3000
                    start()
                }
                ObjectAnimator.ofFloat(this, "scaleX", 1.05f).apply {
                    duration = 3000
                    start()
                }
                ObjectAnimator.ofFloat(this, "scaleY", 1.05f).apply {
                    duration = 3000
                    start()
                }
            }

        } catch (e: NoSuchElementException) {
            binding?.chainLogo?.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}