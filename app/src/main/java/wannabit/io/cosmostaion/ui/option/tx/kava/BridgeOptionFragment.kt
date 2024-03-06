package wannabit.io.cosmostaion.ui.option.tx.kava

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cosmos.base.v1beta1.CoinProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.FragmentBridgeOptionBinding
import wannabit.io.cosmostaion.ui.tx.step.SendAssetType

interface BridgeClickListener {
    fun bep3Transfer(line: CosmosLine, denom: String)
    fun simpleTransfer(line: CosmosLine, denom: String, sendAssetType: SendAssetType)
}

class BridgeOptionFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBridgeOptionBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: CosmosLine
    private lateinit var denom: String
    private lateinit var sendAssetType: SendAssetType

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: CosmosLine,
            denom: String,
            sendAssetType: SendAssetType,
            listener: BridgeClickListener
        ): BridgeOptionFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putString("denom", denom)
                putSerializable("sendAssetType", sendAssetType)
            }
            val fragment = BridgeOptionFragment()
            fragment.arguments = args
            fragment.bridgeClickListener = listener
            return fragment
        }
    }

    private var bridgeClickListener: BridgeClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBridgeOptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setUpClickAction()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.apply {
                getParcelable("selectedChain", CosmosLine::class.java)?.let {
                    selectedChain = it
                }
                getSerializable("withdrawCoin", CoinProto.Coin::class.java)
                getSerializable(
                    "sendAssetType", SendAssetType::class.java
                )?.let { sendAssetType = it }
            }
        } else {
            arguments?.apply {
                (getParcelable("selectedChain") as? CosmosLine)?.let {
                    selectedChain = it
                }
                (getSerializable("sendAssetType") as? SendAssetType)?.let {
                    sendAssetType = it
                }
            }
        }
        arguments?.getString("denom")?.let { denom = it }
    }

    private fun setUpClickAction() {
        binding.apply {
            bep3Layout.setOnClickListener {
                bridgeClickListener?.bep3Transfer(selectedChain, denom)
                dismiss()
            }

            transferLayout.setOnClickListener {
                bridgeClickListener?.simpleTransfer(selectedChain, denom, sendAssetType)
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}