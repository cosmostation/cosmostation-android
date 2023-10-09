package wannabit.io.cosmostaion.ui.dialog.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.FragmentChangeNameBinding
import wannabit.io.cosmostaion.ui.viewmodel.account.AccountViewModel

class ChangeNameFragment(val baseAccount: BaseAccount) : BottomSheetDialogFragment() {

    private var _binding: FragmentChangeNameBinding? = null
    private val binding get() = _binding!!

    private val accountViewModel: AccountViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeNameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        clickAction()
    }

    private fun initView() {
        binding.apply {
            val inputText = baseAccount.name
            if (inputText.isNotEmpty()) {
                accountName.setText(baseAccount.name)
            } else {
                accountName.setText(getString(R.string.str_wallet))
            }
        }
    }

    private fun clickAction() {
        binding.apply {
            btnConfirm.setOnClickListener {
                val inputText = accountName.text.toString().trim()
                if (inputText.equals(baseAccount.name, true)) {
                    dismiss()
                    return@setOnClickListener
                } else if (inputText.isBlank()) {
                    Toast.makeText(context, getString(R.string.str_empty_account_name), Toast.LENGTH_LONG).show()
                } else {
                    baseAccount.name = inputText
                    accountViewModel.insertAccount(baseAccount)
                    dismiss()
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}