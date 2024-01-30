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

interface BridgeClickListener {
    fun bep3Transfer(line: CosmosLine, denom: String)
    fun simpleTransfer(line: CosmosLine, denom: String)
}

class BridgeOptionFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBridgeOptionBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: CosmosLine
    private lateinit var denom: String

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: CosmosLine, denom: String, listener: BridgeClickListener
        ): BridgeOptionFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putString("denom", denom)
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
            }
        } else {
            arguments?.apply {
                (getParcelable("selectedChain") as? CosmosLine)?.let {
                    selectedChain = it
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
                bridgeClickListener?.simpleTransfer(selectedChain, denom)
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}