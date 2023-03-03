package wannabit.io.cosmostaion.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.databinding.DialogTemplateRecyclerBinding
import wannabit.io.cosmostaion.databinding.ItemDialogSwapCoinBinding
import wannabit.io.cosmostaion.dialog.HtlcSendCoinDialog.ToSwapCoinListAdapter.ToSwapCoinHolder
import wannabit.io.cosmostaion.utils.WDp

class HtlcSendCoinDialog : DialogFragment() {
    private var dialogTemplateRecyclerBinding: DialogTemplateRecyclerBinding? = null
    private var mBaseChain: BaseChain? = null
    private var mSwappableCoinList: ArrayList<String>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.drawable.layout_trans_with_border)
        dialogTemplateRecyclerBinding =
            DialogTemplateRecyclerBinding.inflate(inflater, container, false)
        mBaseChain = BaseChain.getChain(arguments!!.getString("chainName"))
        mSwappableCoinList = BaseChain.getHtlcSwappableCoin(mBaseChain)
        dialogTemplateRecyclerBinding!!.dialogTitle.setText(R.string.str_select_to_send_coin)
        dialogTemplateRecyclerBinding!!.recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        dialogTemplateRecyclerBinding!!.recycler.setHasFixedSize(true)
        dialogTemplateRecyclerBinding!!.recycler.adapter = ToSwapCoinListAdapter()
        return dialogTemplateRecyclerBinding!!.root
    }

    private inner class ToSwapCoinListAdapter : RecyclerView.Adapter<ToSwapCoinHolder>() {
        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ToSwapCoinHolder {
            return ToSwapCoinHolder(
                ItemDialogSwapCoinBinding.inflate(
                    layoutInflater
                )
            )
        }

        override fun onBindViewHolder(holder: ToSwapCoinHolder, position: Int) {
            onBindBep3CoinItemViewHolder(holder, position)
        }

        private fun onBindBep3CoinItemViewHolder(holder: ToSwapCoinHolder, position: Int) {
            val tosendCoin = mSwappableCoinList!![position]
            if (mBaseChain == BaseChain.BNB_MAIN) {
                if (tosendCoin == BaseConstant.TOKEN_HTLC_BINANCE_BNB) {
                    holder.itemDialogSwapCoinBinding.coinImg.setImageResource(sActivity!!.mChainConfig.mainDenomImg())
                    holder.itemDialogSwapCoinBinding.coinName.text = "BNB"
                } else {
                    WDp.setDpSymbolImg(
                        sActivity!!.baseDao,
                        sActivity!!.mChainConfig,
                        tosendCoin,
                        holder.itemDialogSwapCoinBinding.coinImg
                    )
                    WDp.setDpSymbol(
                        sActivity,
                        sActivity!!.baseDao,
                        sActivity!!.mChainConfig,
                        tosendCoin,
                        holder.itemDialogSwapCoinBinding.coinName
                    )
                }
            } else if (mBaseChain == BaseChain.KAVA_MAIN) {
                if (tosendCoin == BaseConstant.TOKEN_HTLC_KAVA_BNB) {
                    holder.itemDialogSwapCoinBinding.coinImg.setImageResource(R.drawable.token_binance)
                    holder.itemDialogSwapCoinBinding.coinName.text = "BNB"
                } else {
                    WDp.setDpSymbolImg(
                        sActivity!!.baseDao,
                        sActivity!!.mChainConfig,
                        tosendCoin,
                        holder.itemDialogSwapCoinBinding.coinImg
                    )
                    WDp.setDpSymbol(
                        sActivity,
                        sActivity!!.baseDao,
                        sActivity!!.mChainConfig,
                        tosendCoin,
                        holder.itemDialogSwapCoinBinding.coinName
                    )
                }
            }
            holder.itemDialogSwapCoinBinding.rootLayer.setOnClickListener {
                val result = Bundle()
                result.putInt(BaseConstant.POSITION, position)
                parentFragmentManager.setFragmentResult(HTLC_LIST_BUNDLE_KEY, result)
                dismiss()
            }
        }

        override fun getItemCount(): Int {
            return mSwappableCoinList!!.size
        }

        inner class ToSwapCoinHolder(val itemDialogSwapCoinBinding: ItemDialogSwapCoinBinding) :
            RecyclerView.ViewHolder(
                itemDialogSwapCoinBinding.root
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogTemplateRecyclerBinding = null
    }

    private val sActivity: BaseActivity?
        get() = activity as BaseActivity?

    companion object {
        const val HTLC_LIST_BUNDLE_KEY = "htlclist"

        @JvmStatic
        fun newInstance(bundle: Bundle?): HtlcSendCoinDialog {
            val frag = HtlcSendCoinDialog()
            frag.arguments = bundle
            return frag
        }
    }
}