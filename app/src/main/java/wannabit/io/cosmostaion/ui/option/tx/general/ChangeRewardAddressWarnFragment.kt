package wannabit.io.cosmostaion.ui.option.tx.general

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.FragmentChangeRewardAddressWarnBinding
import wannabit.io.cosmostaion.ui.tx.step.ChangeRewardAddressFragment

class ChangeRewardAddressWarnFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentChangeRewardAddressWarnBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: CosmosLine

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: CosmosLine
        ): ChangeRewardAddressWarnFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = ChangeRewardAddressWarnFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeRewardAddressWarnBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setUpClickAction()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", CosmosLine::class.java)
                ?.let { selectedChain = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? CosmosLine)?.let {
                selectedChain = it
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnCancel.setOnClickListener {
                dismiss()
            }

            btnConfirm.setOnClickListener {
                if (isClickable) {
                    isClickable = false

                    ChangeRewardAddressFragment.newInstance(selectedChain).show(
                        requireActivity().supportFragmentManager,
                        ChangeRewardAddressFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}