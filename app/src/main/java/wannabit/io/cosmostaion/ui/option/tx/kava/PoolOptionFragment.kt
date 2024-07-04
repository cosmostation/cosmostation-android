package wannabit.io.cosmostaion.ui.option.tx.kava

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kava.swap.v1beta1.QueryProto
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.FragmentPoolOptionBinding
import wannabit.io.cosmostaion.ui.tx.info.kava.PoolClickListener

class PoolOptionFragment(
    val selectedChain: BaseChain,
    private val swapPool: QueryProto.PoolResponse,
    private val deposit: QueryProto.DepositResponse,
    val listener: PoolClickListener,
) : BottomSheetDialogFragment() {

    private var _binding: FragmentPoolOptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPoolOptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClickAction()
    }

    private fun setUpClickAction() {
        binding.apply {
            depositLayout.setOnClickListener {
                listener.poolDeposit(swapPool)
                dismiss()
            }

            withdrawLayout.setOnClickListener {
                listener.poolWithdraw(swapPool, deposit)
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}