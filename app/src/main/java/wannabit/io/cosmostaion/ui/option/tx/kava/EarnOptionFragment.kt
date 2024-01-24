package wannabit.io.cosmostaion.ui.option.tx.kava

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cosmos.base.v1beta1.CoinProto.Coin
import com.cosmos.staking.v1beta1.StakingProto.Validator
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.FragmentPoolOptionBinding

interface EarnClickListener {
    fun earnDeposit(toValidator: Validator)

    fun earnWithdraw(withdrawCoin: Coin)
}

class EarnOptionFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentPoolOptionBinding? = null
    private val binding get() = _binding!!

    private var toValidator: Validator? = null
    private var withdrawCoin: Coin? = null

    companion object {
        @JvmStatic
        fun newInstance(
            validator: Validator, coin: Coin, listener: EarnClickListener
        ): EarnOptionFragment {
            val args = Bundle().apply {
                putSerializable("toValidator", validator)
                putSerializable("withdrawCoin", coin)
            }
            val fragment = EarnOptionFragment()
            fragment.arguments = args
            fragment.earnClickListener = listener
            return fragment
        }
    }

    private var earnClickListener: EarnClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPoolOptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setUpClickAction()
    }

    private fun initData() {
        binding.apply {
            title.text = getString(R.string.title_earning_select)
            depositTitle.text = getString(R.string.str_deposit_liquidity)
            withdrawTitle.text = getString(R.string.str_remove_liquidity)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            toValidator = arguments?.getSerializable("toValidator", Validator::class.java)
            withdrawCoin = arguments?.getSerializable("withdrawCoin", Coin::class.java)
        } else {
            toValidator = arguments?.getSerializable("toValidator") as? Validator
            withdrawCoin = arguments?.getSerializable("withdrawCoin") as? Coin
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            depositLayout.setOnClickListener {
                toValidator?.let {
                    earnClickListener?.earnDeposit(it)
                    dismiss()
                }
            }

            withdrawLayout.setOnClickListener {
                withdrawCoin?.let {
                    earnClickListener?.earnWithdraw(it)
                    dismiss()
                }
            }
        }
    }
}