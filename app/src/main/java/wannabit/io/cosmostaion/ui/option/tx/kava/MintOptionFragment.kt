package wannabit.io.cosmostaion.ui.option.tx.kava

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.databinding.FragmentMintOptionBinding
import wannabit.io.cosmostaion.ui.tx.info.kava.LendClickListener
import wannabit.io.cosmostaion.ui.tx.info.kava.MintClickListener
import wannabit.io.cosmostaion.ui.tx.step.kava.LendActionType
import wannabit.io.cosmostaion.ui.tx.step.kava.MintActionType

class MintOptionFragment(
    val selectedChain: CosmosLine,
    private val mintType: String?,
    private val denom: String?,
    private val mintClickListener: MintClickListener?,
    private val lendClickListener: LendClickListener?
) : BottomSheetDialogFragment() {

    private var _binding: FragmentMintOptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMintOptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            mintType?.let { type ->
                deposit.text = getString(
                    R.string.str_deposit_denom, type.split("-").firstOrNull()?.uppercase() ?: ""
                )
                withdraw.text = getString(
                    R.string.str_withdraw_denom, type.split("-").firstOrNull()?.uppercase() ?: ""
                )
                borrow.text = getString(R.string.str_borrow_denom, "USDX")
                repay.text = getString(R.string.str_repay_denom, "USDX")

            } ?: run {
                BaseData.assets?.firstOrNull { it.denom == denom }?.let { asset ->
                    deposit.text = getString(R.string.str_deposit_denom, asset.symbol)
                    withdraw.text = getString(R.string.str_withdraw_denom, asset.symbol)
                    borrow.text = getString(R.string.str_borrow_denom, asset.symbol)
                    repay.text = getString(R.string.str_repay_denom, asset.symbol)
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            depositLayout.setOnClickListener {
                mintType?.let {
                    mintClickListener?.mintAction(mintType, MintActionType.DEPOSIT)
                } ?: run {
                    lendClickListener?.lendAction(denom, LendActionType.DEPOSIT)
                }
                dismiss()
            }

            withdrawLayout.setOnClickListener {
                mintType?.let {
                    mintClickListener?.mintAction(mintType, MintActionType.WITHDRAW)
                } ?: run {
                    lendClickListener?.lendAction(denom, LendActionType.WITHDRAW)
                }
                dismiss()
            }

            borrowLayout.setOnClickListener {
                mintType?.let {
                    mintClickListener?.mintAction(mintType, MintActionType.BORROW)
                } ?: run {
                    lendClickListener?.lendAction(denom, LendActionType.BORROW)
                }
                dismiss()
            }

            repayLayout.setOnClickListener {
                mintType?.let {
                    mintClickListener?.mintAction(mintType, MintActionType.REPAY)
                } ?: run {
                    lendClickListener?.lendAction(denom, LendActionType.REPAY)
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