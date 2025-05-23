package wannabit.io.cosmostaion.ui.tx.info.major

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.databinding.FragmentSuiInfoBinding

class MoveInfoFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSuiInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): MoveInfoFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = MoveInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuiInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setUpClickAction()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", BaseChain::class.java)
                ?.let { selectedChain = it }

        } else {
            (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                selectedChain = it
            }
        }

        binding.apply {
            val epoch = if (selectedChain is ChainSui) {
                (selectedChain as ChainSui).suiFetcher?.let { fetcher ->
                    fetcher.suiSystem["result"].asJsonObject["epoch"].asLong
                }

            } else {
                (selectedChain as ChainIota).iotaFetcher?.let { fetcher ->
                    fetcher.iotaSystem["result"].asJsonObject["epoch"].asLong
                }
            }

            dialogMsg0.text = getString(R.string.str_sui_guide_msg0)
            dialogMsg1.text = getString(R.string.str_sui_guide_msg1, "#$epoch")
            dialogMsg2.text = getString(R.string.str_sui_guide_msg2, "#" + ((epoch ?: 0) + 1))
            dialogMsg3.text = getString(R.string.str_sui_guide_msg3)
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnConfirm.setOnClickListener {
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}