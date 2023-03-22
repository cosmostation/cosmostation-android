package wannabit.io.cosmostaion.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.google.common.collect.Lists
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.DialogDappSignBinding
import wannabit.io.cosmostaion.utils.WDp
import java.math.BigDecimal

class DappSignDialog(private val id: Long, private val data: String, private val url: String?, val listener: WcSignRawDataListener) : DialogFragment() {
    private var _binding: DialogDappSignBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogDappSignBinding.inflate(inflater, container, false)
        val view = binding.root
        settingViews()
        fillData()
        return view
    }

    private fun fillData() {
        try {
            fillTxData(data)
        } catch (e: Exception) {
            defaultTxView(data)
        }
    }

    private fun settingViews() {
        dialog?.window?.setBackgroundDrawableResource(R.color.colorTrans)
        binding.chainUrl.text = url
        binding.btnDetail.setOnClickListener { enableTab(0) }
        binding.btnData.setOnClickListener { enableTab(1) }
        binding.btnPosi.setOnClickListener {
            listener.sign(id, data)
            dismiss()
        }
        binding.btnNega.setOnClickListener {
            listener.cancel(id)
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun defaultTxView(transaction: String?) {
        binding.chainName.text = getString(R.string.str_wc_sign_title)
        enableTab(1)
        binding.tabWrap.visibility = View.GONE
        val prettierTxString = GsonBuilder().setPrettyPrinting().create().toJson(JsonParser.parseString(transaction))
        binding.wcRawData.text = prettierTxString
    }

    private fun fillTxData(transaction: String?) {
        val txJsonArray = JsonParser.parseString(transaction).asJsonArray
        val chainId = txJsonArray.get(0).asString
        val address = txJsonArray.get(1).asString
        val txJsonObject = txJsonArray.get(2).asJsonObject
        val msgJsonArray = txJsonObject.getAsJsonArray("msgs")
        binding.chainName.text = if (StringUtils.isNotEmpty(chainId)) chainId else getString(R.string.str_wc_sign_title)
        binding.addressDetail.text = address
        binding.memoDetail.text = txJsonObject["memo"].asString
        binding.totalFeeAmount.text = makeFeeString(chainId, txJsonObject)
        binding.wcRawData.text = GsonBuilder().setPrettyPrinting().create().toJson(txJsonObject)
        binding.wcDetail.text = GsonBuilder().setPrettyPrinting().create().toJson(msgJsonArray)
        enableTab(0)
    }

    private fun makeFeeString(chainId: String, txJsonObject: JsonObject): String {
        val feeJson = txJsonObject.getAsJsonObject("fee")
        val amountArray = feeJson.getAsJsonArray("amount")
        val fees: MutableList<String> = Lists.newArrayList()
        for (element in amountArray) {
            val elementObject = element.asJsonObject
            val amount = elementObject["amount"].asString
            val denom = elementObject["denom"].asString
            val baseChain = WDp.getChainTypeByChainId(chainId)
            val chainConfig = ChainFactory.getChain(baseChain)
            (activity as? BaseActivity)?.let {
                val decimal = WDp.getDenomDecimal(it.baseDao, chainConfig, denom)
                val mAmount = WDp.getDpAmount2(it, BigDecimal(amount), decimal, 6)
                fees.add("$mAmount ${chainConfig.mainSymbol()}")
            }
        }
        return java.lang.String.join("\n", fees)
    }

    private fun enableTab(index: Int) {
        settingTab(index == 0, binding.btnDetail, binding.layoutWcDetail)
        settingTab(index == 1, binding.btnData, binding.layoutWcRawData)
    }

    private fun settingTab(enable: Boolean, button: Button, textLayout: LinearLayout) {
        textLayout.visibility = if (enable) View.VISIBLE else View.GONE
        button.background = ContextCompat.getDrawable(requireContext(), if (enable) R.drawable.box_sign_selected else R.drawable.box_sign_unselected)
        button.setTextColor(ContextCompat.getColor(requireContext(), if (enable) R.color.colorPhotonDayNight else R.color.colorGrayDayNight))
    }

    interface WcSignRawDataListener {
        fun sign(id: Long, transaction: String)
        fun cancel(id: Long)
    }

    companion object {
        fun newInstance(id: Long, data: String, url: String?, listener: WcSignRawDataListener): DappSignDialog {
            val dialog = DappSignDialog(id, data, url, listener)
            dialog.isCancelable = false
            return dialog
        }
    }
}