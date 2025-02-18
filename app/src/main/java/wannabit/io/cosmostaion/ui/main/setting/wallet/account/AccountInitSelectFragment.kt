package wannabit.io.cosmostaion.ui.main.setting.wallet.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.databinding.FragmentAccountInitBinding

interface AccountInitListener {
    fun initAction(initType: Int)
}

class AccountInitSelectFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAccountInitBinding? = null
    private val binding get() = _binding!!

    companion object {
        @JvmStatic
        fun newInstance(
            listener: AccountInitListener
        ): AccountInitSelectFragment {
            val fragment = AccountInitSelectFragment()
            fragment.accountInitListener = listener
            return fragment
        }
    }

    private var accountInitListener: AccountInitListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountInitBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClickAction()
    }

    private fun setUpClickAction() {
        binding.apply {
            createWalletLayout.setOnClickListener {
                accountInitListener?.initAction(BaseConstant.CONST_NEW_ACCOUNT)
            }

            restoreWalletLayout.setOnClickListener {
                accountInitListener?.initAction(BaseConstant.CONST_RESTORE_MNEMONIC_ACCOUNT)
            }

            privateWalletLayout.setOnClickListener {
                accountInitListener?.initAction(BaseConstant.CONST_RESTORE_PRIVATE_ACCOUNT)
            }

            qrWalletLayout.setOnClickListener {
                accountInitListener?.initAction(BaseConstant.CONST_RESTORE_QR_ACCOUNT)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}