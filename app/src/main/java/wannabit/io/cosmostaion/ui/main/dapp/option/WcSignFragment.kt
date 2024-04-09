package wannabit.io.cosmostaion.ui.main.dapp.option

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.tx.v1beta1.TxProto
import com.cosmos.tx.v1beta1.TxProto.Fee
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import net.i2p.crypto.eddsa.Utils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava459
import wannabit.io.cosmostaion.chain.evmClass.ChainKavaEvm
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.databinding.FragmentWcSignBinding
import java.math.RoundingMode

class WcSignFragment(
    private val selectedChain: CosmosLine?,
    private val id: Long,
    private val data: String,
    private val url: String?,
    val listener: WcSignRawDataListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentWcSignBinding? = null
    private val binding get() = _binding!!

    private var updateData: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWcSignBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            signView.setBackgroundResource(R.drawable.cell_bg)
            if (selectedChain is ChainKavaEvm || selectedChain is ChainKava459) {
                initWc1DataView(data)
            } else {
                try {
                    initAminoDataView(data)
                } catch (e: Exception) {
                    initInjectionDataView(data)
                }
            }
            isCancelable = false
        }
    }

    private fun initAminoDataView(data: String) {
        binding.apply {
            selectedChain?.let { line ->
                val txJsonObject = JsonParser.parseString(data).asJsonObject
                val updateJsonData = updateFeeInfoInAminoMessage(txJsonObject)
                signData.text = GsonBuilder().setPrettyPrinting().create().toJson(updateJsonData)
                dappUrl.text = url
                dappAddress.text = line.address

                val fee = updateJsonData.get("fee").asJsonObject
                val feeAmount =
                    fee.get("amount").asJsonArray.get(0).asJsonObject.get("amount").asString
                val feeDenom =
                    fee.get("amount").asJsonArray.get(0).asJsonObject.get("denom").asString

                BaseData.getAsset(line.apiName, feeDenom ?: "")?.let { asset ->
                    val dpFeeAmount = feeAmount.toBigDecimal().movePointLeft(asset.decimals ?: 6)
                        .setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                    dappFeeAmount.text = formatAmount(
                        dpFeeAmount.toPlainString(), asset.decimals ?: 6
                    )
                    dappFeeDenom.text = asset.symbol
                }
                updateData = updateJsonData.toString()
            }
        }
    }

    private fun initWc1DataView(data: String) {
        binding.apply {
            selectedChain?.let { line ->
                val txJsonObject = JsonParser.parseString(data).asJsonObject
                signData.text = GsonBuilder().setPrettyPrinting().create().toJson(txJsonObject)
                dappUrl.text = url
                dappAddress.text = line.address

                val fee = txJsonObject.get("fee").asJsonObject
                val amounts = fee.get("amounts").asJsonArray
                val feeAmount = amounts.get(0).asJsonObject.get("amount").asString
                val feeDenom = amounts.get(0).asJsonObject.get("denom").asString

                BaseData.getAsset(line.apiName, feeDenom ?: "")?.let { asset ->
                    val dpFeeAmount = feeAmount.toBigDecimal().movePointLeft(asset.decimals ?: 6)
                        .setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                    dappFeeAmount.text = formatAmount(
                        dpFeeAmount.toPlainString(), asset.decimals ?: 6
                    )
                    dappFeeDenom.text = asset.symbol
                }
                updateData = data
            }
        }
    }

    private fun initInjectionDataView(data: String) {
        binding.apply {
            signView.setBackgroundResource(R.drawable.cell_bg)
            selectedChain?.let { line ->
                val txJsonObject = JsonParser.parseString(data).asJsonObject
                val updateJsonData = updateFeeInfoInDirectMessage(txJsonObject)
                signData.text = GsonBuilder().setPrettyPrinting().create().toJson(txJsonObject)
                dappUrl.text = url
                dappAddress.text = line.address

                val authInfo =
                    TxProto.AuthInfo.parseFrom(Utils.hexToBytes(updateJsonData["auth_info_bytes"].asString))

                BaseData.getAsset(line.apiName, authInfo.fee.getAmount(0).denom ?: "")
                    ?.let { asset ->
                        val dpFeeAmount = authInfo.fee.getAmount(0).amount.toBigDecimal()
                            .movePointLeft(asset.decimals ?: 6)
                            .setScale(asset.decimals ?: 6, RoundingMode.DOWN)
                        dappFeeAmount.text = formatAmount(
                            dpFeeAmount.toPlainString(), asset.decimals ?: 6
                        )
                        dappFeeDenom.text = asset.symbol
                    }
                updateData = updateJsonData.toString()
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            binding.btnCancel.setOnClickListener {
                listener.cancel(id)
                dismiss()
            }

            binding.btnConfirm.setOnClickListener {
                listener.sign(id, updateData.toString())
                dismiss()
            }
        }
    }

    private fun updateFeeInfoInAminoMessage(txJsonObject: JsonObject): JsonObject {
        val txJsonSignDoc =
            txJsonObject.getAsJsonObject("signDoc") ?: txJsonObject.getAsJsonObject("doc")
        val isEditFee: Boolean = txJsonObject.getAsJsonPrimitive("isEditFee")?.asBoolean
            ?: txJsonObject.get("params")?.asJsonObject?.get("isEditFee")?.asBoolean ?: true
        val fee = txJsonSignDoc.get("fee").asJsonObject
        val gas = fee.get("gas").asString
        val amounts = fee.get("amount").asJsonArray

        if (!isEditFee && (amounts.size() <= 0 || gas == "0") || isEditFee) {
            val chainId = txJsonSignDoc.get("chain_id").asString
            BaseData.baseAccount?.let { account ->
                val targetChain =
                    account.allEvmLineChains.firstOrNull { it.chainIdCosmos.lowercase() == chainId.lowercase() }
                        ?: run {
                            account.allCosmosLineChains.firstOrNull { it.chainIdCosmos.lowercase() == chainId.lowercase() }
                        }
                targetChain?.let { chain ->
                    chain.getFeeInfos(requireContext())
                        .first().feeDatas.firstOrNull { it.denom == chain.stakeDenom }
                        ?.let { gasRate ->
                            val gasLimit =
                                (gas.toDouble() * chain.gasMultiply()).toLong().toBigDecimal()
                            val feeCoinAmount =
                                gasRate.gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)

                            if (amounts.size() == 0) {
                                val jsonObject = JsonObject()
                                jsonObject.addProperty(
                                    "amount", feeCoinAmount.toString()
                                )
                                jsonObject.addProperty("denom", chain.stakeDenom)
                                amounts.add(jsonObject)

                            } else {
                                val mainDenomFee =
                                    amounts.firstOrNull { it.asJsonObject["denom"].asString == chain.stakeDenom && it.asJsonObject["amount"].asString == "0" }
                                mainDenomFee?.asJsonObject?.addProperty(
                                    "amount", feeCoinAmount.toString()
                                )
                            }
                        }
                }
            }
        }
        return txJsonSignDoc
    }

    private fun updateFeeInfoInDirectMessage(txJsonObject: JsonObject): JsonObject {
        var authInfo =
            TxProto.AuthInfo.parseFrom(Utils.hexToBytes(txJsonObject["auth_info_bytes"].asString))
        val isEditFee: Boolean = txJsonObject.getAsJsonPrimitive("isEditFee")?.asBoolean
            ?: txJsonObject.get("params")?.asJsonObject?.get("isEditFee")?.asBoolean ?: true
        val fee = authInfo.fee

        if (!isEditFee && (fee.amountList.isEmpty() || fee.gasLimit.toString() == "0") || isEditFee) {
            val chainId = txJsonObject["chain_id"].asString
            BaseData.baseAccount?.let { account ->
                val targetChain =
                    account.allEvmLineChains.firstOrNull { it.chainIdCosmos.lowercase() == chainId.lowercase() }
                        ?: run {
                            account.allCosmosLineChains.firstOrNull { it.chainIdCosmos.lowercase() == chainId.lowercase() }
                        }
                targetChain?.let { chain ->
                    chain.getFeeInfos(requireContext())
                        .first().feeDatas.firstOrNull { it.denom == chain.stakeDenom }
                        ?.let { gasRate ->
                            val gasLimit = (fee.gasLimit.toDouble() * chain.gasMultiply()).toLong()
                                .toBigDecimal()
                            val feeCoinAmount =
                                gasRate.gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)
                            val updateFeeCoin =
                                CoinProto.Coin.newBuilder().setDenom(chain.stakeDenom)
                                    .setAmount(feeCoinAmount.toString()).build()
                            val updateFee =
                                Fee.newBuilder().setGasLimit(fee.gasLimit).addAmount(updateFeeCoin)
                                    .build()

                            authInfo = authInfo.toBuilder().setFee(updateFee).build()
                        }
                }
            }
        }
        return txJsonObject.apply {
            addProperty("auth_info_bytes", Utils.bytesToHex(authInfo.toByteArray()))
        }
    }

    interface WcSignRawDataListener {
        fun sign(id: Long, data: String)
        fun cancel(id: Long)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}