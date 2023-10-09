package wannabit.io.cosmostaion.ui.dialog.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.FragmentDeleteBinding
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
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
        checkPwResult()
    }

    private fun initView() {
        binding.title.text = getString(R.string.str_delete_name, baseAccount.name)
    }

    private fun clickAction() {
        binding.apply {
            btnDelete.setOnClickListener {
                val intent = Intent(requireContext(), PasswordCheckActivity::class.java)
                intent.putExtra("checkPwType", BaseConstant.CONST_PW_DELETE_ACCOUNT)
                startActivity(intent)
                requireActivity().overridePendingTransition(
                    R.anim.anim_slide_in_bottom,
                    R.anim.anim_fade_out
                )
            }
        }
    }

    private fun checkPwResult() {
        ApplicationViewModel.shared.checkPwDeleteResult.observe(viewLifecycleOwner) {
            dismiss()
            accountViewModel.deleteAccount(baseAccount)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}