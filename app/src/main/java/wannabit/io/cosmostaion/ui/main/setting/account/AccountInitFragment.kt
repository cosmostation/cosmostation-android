package wannabit.io.cosmostaion.ui.main.setting.account

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseConstant.CONST_NEW_ACCOUNT
import wannabit.io.cosmostaion.common.BaseConstant.CONST_RESTORE_MNEMONIC_ACCOUNT
import wannabit.io.cosmostaion.common.BaseConstant.CONST_RESTORE_PRIVATE_ACCOUNT
import wannabit.io.cosmostaion.common.toMoveFragment
import wannabit.io.cosmostaion.databinding.FragmentAccountInitBinding
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.wallet.CreateNameFragment

class AccountInitFragment(private val accountListFragment: AccountListFragment) : BottomSheetDialogFragment() {

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

        clickAction()
    }

    private fun clickAction() {
        binding.apply {
            createWalletLayout.setOnClickListener {
                val intent = Intent(requireContext(), PasswordCheckActivity::class.java)
                createAccountResultLauncher.launch(intent)
                pendingTransition()
            }

            restoreWalletLayout.setOnClickListener {
                val intent = Intent(requireContext(), PasswordCheckActivity::class.java)
                restoreWalletResultLauncher.launch(intent)
                pendingTransition()
            }

            privateWalletLayout.setOnClickListener {
                val intent = Intent(requireContext(), PasswordCheckActivity::class.java)
                privateWalletResultLauncher.launch(intent)
                pendingTransition()
            }
        }
    }

    private fun pendingTransition() {
        requireActivity().overridePendingTransition(
            R.anim.anim_slide_in_bottom,
            R.anim.anim_fade_out
        )
    }

    private fun toMoveNameFragment(type: Int) {
        val bundle = Bundle()
        bundle.putInt("newType", type)
        val fragment = CreateNameFragment()
        fragment.arguments = bundle
        requireActivity().toMoveFragment(accountListFragment, fragment, "CreateName")

    }

    private val createAccountResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                lifecycleScope.launch {
                    delay(300)
                    dismiss()
                    withContext(Dispatchers.Main) {
                        toMoveNameFragment(CONST_NEW_ACCOUNT)
                    }
                }
            }
        }

    private val restoreWalletResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                lifecycleScope.launch {
                    delay(300)
                    dismiss()
                    withContext(Dispatchers.Main) {
                        toMoveNameFragment(CONST_RESTORE_MNEMONIC_ACCOUNT)
                    }
                }
            }
        }

    private val privateWalletResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                lifecycleScope.launch {
                    delay(300)
                    dismiss()
                    withContext(Dispatchers.Main) {
                        toMoveNameFragment(CONST_RESTORE_PRIVATE_ACCOUNT)
                    }
                }
            }
        }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}