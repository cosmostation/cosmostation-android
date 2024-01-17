package wannabit.io.cosmostaion.ui.main.setting.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.databinding.FragmentAccountInitBinding

class AccountInitSelectFragment(
    private val listener: AccountInitListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentAccountInitBinding? = null
    private val binding get() = _binding!!

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
                listener.initAction(BaseConstant.CONST_NEW_ACCOUNT)
            }

            restoreWalletLayout.setOnClickListener {
                listener.initAction(BaseConstant.CONST_RESTORE_MNEMONIC_ACCOUNT)
            }

            privateWalletLayout.setOnClickListener {
                listener.initAction(BaseConstant.CONST_RESTORE_PRIVATE_ACCOUNT)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}