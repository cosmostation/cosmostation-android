package wannabit.io.cosmostaion.ui.main.dapp.option

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.tx.v1beta1.TxProto
import com.cosmos.tx.v1beta1.TxProto.Fee
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.i2p.crypto.eddsa.Utils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.databinding.FragmentWcSignBinding
import java.math.BigDecimal
import java.math.RoundingMode

class PopUpCosmosSignFragment(
    private val selectedChain: CosmosLine?,
    private val id: Long,
    private val data: String,
    private val url: String?,
    private val method: String?,
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
            when (method) {
                "trust_sign" -> initTrustDataView(data)
                "sign_direct" -> initDirectDataView(data)
                else -> initAminoDataView(data)
            }
        }
        isCancelable = false
    }

    private fun initAminoDataView(data: String) {
        binding.apply {
            selectedChain?.let { chain ->
                lifecycleScope.launch(Dispatchers.IO) {
                    val txJsonObject = JsonParser.parseString(data).asJsonObject
                    val updateJsonData = updateFeeInfoInAminoMessage(txJsonObject)
                    val fee = updateJsonData.get("fee").asJsonObject
                    val feeAmount =
                        fee.get("amount").asJsonArray.get(0).asJsonObject.get("amount").asString
                    val feeDenom =
                        fee.get("amount").asJsonArray.get(0).asJsonObject.get("denom").asString

                    BaseData.getAsset(chain.apiName, feeDenom ?: "")?.let { asset ->
                        val dpFeeAmount =
                            feeAmount.toBigDecimal().movePointLeft(asset.decimals ?: 6)
                                .setScale(asset.decimals ?: 6, RoundingMode.DOWN)

                        withContext(Dispatchers.Main) {
                            signData.text =
                                GsonBuilder().setPrettyPrinting().create().toJson(updateJsonData)
                            dappUrl.text = url
                            dappAddress.text = chain.address
                            dappFeeAmount.text = formatAmount(
                                dpFeeAmount.toPlainString(), asset.decimals ?: 6
                            )
                            dappFeeDenom.text = asset.symbol
                        }
                    }
                    updateData = updateJsonData.toString()
                }
            }
        }
    }

    private fun initTrustDataView(data: String) {
        binding.apply {
            selectedChain?.let { chain ->
                val txJsonObject = JsonParser.parseString(data).asJsonObject
                signData.text = GsonBuilder().setPrettyPrinting().create().toJson(txJsonObject)
                dappUrl.text = url
                dappAddress.text = chain.address

                val fee = txJsonObject.get("fee").asJsonObject
                val amounts = fee.get("amounts").asJsonArray
                val feeAmount = amounts.get(0).asJsonObject.get("amount").asString
                val feeDenom = amounts.get(0).asJsonObject.get("denom").asString

                BaseData.getAsset(chain.apiName, feeDenom ?: "")?.let { asset ->
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

    private fun initDirectDataView(data: String) {
        binding.apply {
            signView.setBackgroundResource(R.drawable.cell_bg)
            loading.visibility = View.VISIBLE
            selectedChain?.let { chain ->
                lifecycleScope.launch(Dispatchers.IO) {
                    val txJsonObject = JsonParser.parseString(data).asJsonObject
                    val updateJsonData = updateFeeInfoInDirectMessage(txJsonObject)
                    val authInfo =
                        TxProto.AuthInfo.parseFrom(Utils.hexToBytes(updateJsonData["auth_info_bytes"].asString))

                    BaseData.getAsset(chain.apiName, authInfo.fee.getAmount(0).denom ?: "")
                        ?.let { asset ->
                            val dpFeeAmount = authInfo.fee.getAmount(0).amount.toBigDecimal()
                                .movePointLeft(asset.decimals ?: 6)
                                .setScale(asset.decimals ?: 6, RoundingMode.DOWN)

                            withContext(Dispatchers.Main) {
                                loading.visibility = View.GONE
                                signData.text = GsonBuilder().setPrettyPrinting().create()
                                    .toJson(updateJsonData)
                                dappUrl.text = url
                                dappAddress.text = chain.address
                                dappFeeAmount.text = formatAmount(
                                    dpFeeAmount.toPlainString(), asset.decimals ?: 6
                                )
                                dappFeeDenom.text = asset.symbol
                            }
                        }
                    updateData = updateJsonData.toString()
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnCancel.setOnClickListener {
                if (!loading.isVisible) {
                    listener.cancel(id)
                    dismiss()
                }
            }

            btnConfirm.setOnClickListener {
                if (!loading.isVisible) {
                    listener.sign(id, updateData.toString())
                    dismiss()
                }
            }
        }
    }

    private fun updateFeeInfoInAminoMessage(txJsonObject: JsonObject): JsonObject {
        val txJsonSignDoc =
            txJsonObject.getAsJsonObject("signDoc") ?: txJsonObject.getAsJsonObject("doc")
        val isEditFee: Boolean = txJsonObject.getAsJsonPrimitive("isEditFee")?.asBoolean ?: true
        val fee = txJsonSignDoc.get("fee").asJsonObject
        val gas = fee.get("gas").asString
        val amounts = fee.get("amount").asJsonArray

        if (isEditFee || amounts.size() <= 0) {
            val chainId =
                txJsonSignDoc.get("chain_id").asString ?: txJsonObject.get("chainName").asString
            BaseData.baseAccount?.let { account ->
                val selectChain =
                    account.allEvmLineChains.firstOrNull { it.chainIdCosmos.lowercase() == chainId.lowercase() }
                        ?: account.allCosmosLineChains.firstOrNull { it.chainIdCosmos.lowercase() == chainId.lowercase() }
                selectChain?.let { chain ->
                    chain.getFeeInfos(requireContext())
                        .first().feeDatas.firstOrNull { it.denom == chain.stakeDenom }
                        ?.let { gasRate ->
                            val gasLimit =
                                (gas.toDouble() * chain.gasMultiply()).toLong().toBigDecimal()
                            val feeCoinAmount = gasRate.gasRate?.multiply(gasLimit)
                                ?.setScale(0, RoundingMode.UP)

                            if (amounts.size() == 0) {
                                val jsonObject = JsonObject()
                                jsonObject.addProperty(
                                    "amount", feeCoinAmount.toString()
                                )
                                jsonObject.addProperty("denom", chain.stakeDenom)
                                amounts.add(jsonObject)

                            } else {
                                val mainDenomFee =
                                    amounts.firstOrNull { it.asJsonObject["denom"].asString == chain.stakeDenom }
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
        val doc = txJsonObject["doc"].asJsonObject
        var authInfo = TxProto.AuthInfo.parseFrom(Utils.hexToBytes(doc["auth_info_bytes"].asString))
        val txBody = TxProto.TxBody.parseFrom(Utils.hexToBytes(doc["body_bytes"].asString))
        val isEditFee: Boolean = txJsonObject.getAsJsonPrimitive("isEditFee")?.asBoolean ?: true
        val fee = authInfo.fee

        if (isEditFee || fee.amountList.isEmpty()) {
            val chainId = doc["chain_id"].asString
            BaseData.baseAccount?.let { account ->
                account.allEvmLineChains.firstOrNull { it.chainIdCosmos.lowercase() == chainId.lowercase() }
                    ?: run {
                        account.allCosmosLineChains.firstOrNull { it.chainIdCosmos.lowercase() == chainId.lowercase() }
                    }?.let { chain ->
                        val simulateGas = Signer.dAppSimulateGas(chain, txBody, authInfo)
                        val simulateGasLimit =
                            (simulateGas.gasUsed.toDouble() * chain.gasMultiply()).toLong()
                        val updateFee = updateFeeWithSimulate(
                            chain, simulateGasLimit.toBigDecimal(), fee.gasLimit.toBigDecimal(), fee
                        )
                        authInfo = authInfo.toBuilder().setFee(updateFee).build()
                    }
            }
        }
        return txJsonObject["doc"].asJsonObject.apply {
            addProperty("auth_info_bytes", Utils.bytesToHex(authInfo.toByteArray()))
        }
    }

    private fun updateFeeWithSimulate(
        chain: CosmosLine, simulateGasLimit: BigDecimal, originalGasLimit: BigDecimal, fee: Fee
    ): Fee? {
        chain.getFeeInfos(requireContext())[chain.getFeeBasePosition()].feeDatas.firstOrNull { it.denom == chain.stakeDenom }
            ?.let { gasRate ->
                if (simulateGasLimit > originalGasLimit) {
                    val feeCoinAmount =
                        gasRate.gasRate?.multiply(simulateGasLimit)?.setScale(0, RoundingMode.UP)
                    val updateFeeCoin = CoinProto.Coin.newBuilder().setDenom(chain.stakeDenom)
                        .setAmount(feeCoinAmount.toString()).build()
                    return Fee.newBuilder().setGasLimit(simulateGasLimit.toLong())
                        .addAmount(updateFeeCoin).setPayer(fee.payer).build()

                } else {
                    val feeCoinAmount =
                        gasRate.gasRate?.multiply(originalGasLimit)?.setScale(0, RoundingMode.UP)
                    val updateFeeCoin = CoinProto.Coin.newBuilder().setDenom(chain.stakeDenom)
                        .setAmount(feeCoinAmount.toString()).build()
                    return Fee.newBuilder().setGasLimit(fee.gasLimit).addAmount(updateFeeCoin)
                        .setPayer(fee.payer).build()
                }
            }
        return null
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