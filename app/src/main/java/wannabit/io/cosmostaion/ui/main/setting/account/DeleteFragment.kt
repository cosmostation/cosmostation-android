package wannabit.io.cosmostaion.ui.main.setting.account

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.FragmentDeleteBinding
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.viewmodel.account.AccountViewModel

class DeleteFragment(val baseAccount: BaseAccount) : BottomSheetDialogFragment() {

    private var _binding: FragmentDeleteBinding? = null
    private val binding get() = _binding!!

    private val accountViewModel: AccountViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        clickAction()
    }

    private fun initView() {
        binding.title.text = getString(R.string.str_delete_name, baseAccount.name)
    }

    private fun clickAction() {
        binding.apply {
            btnDelete.setOnClickListener {
                val intent = Intent(requireContext(), PasswordCheckActivity::class.java)
                deleteAccountResultLauncher.launch(intent)
                requireActivity().overridePendingTransition(
                    R.anim.anim_slide_in_bottom,
                    R.anim.anim_fade_out
                )
            }
        }
    }

    private val deleteAccountResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                dismiss()
                accountViewModel.deleteAccount(baseAccount)
            }
        }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}