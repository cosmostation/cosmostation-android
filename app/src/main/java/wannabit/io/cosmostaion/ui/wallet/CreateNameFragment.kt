package wannabit.io.cosmostaion.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseConstant.CONST_NEW_ACCOUNT
import wannabit.io.cosmostaion.common.BaseConstant.CONST_RESTORE_MNEMONIC_ACCOUNT
import wannabit.io.cosmostaion.common.BaseConstant.CONST_RESTORE_PRIVATE_ACCOUNT
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.toMoveFragment
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.databinding.FragmentCreateNameBinding

class CreateNameFragment : Fragment() {

    private var _binding: FragmentCreateNameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateNameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickAction()
    }

    private fun clickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            btnNext.setOnClickListener {
                accountName.text.toString().trim().let { name ->
                    if (name.isEmpty()) {
                        requireActivity().makeToast(R.string.error_account_name)
                        return@setOnClickListener
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        val appDatabase = AppDatabase.getInstance()
                        val selectAllAccount = appDatabase.baseAccountDao().selectAll()
                        if (selectAllAccount.firstOrNull {it.name.equals(name, true)} != null) {
                            withContext(Dispatchers.Main) {
                                requireActivity().makeToast(R.string.error_exist_account_name)
                                return@withContext
                            }
                        } else {
                            val args = arguments
                            if (args != null) {
                                when (arguments?.getInt("newType") ?: -1) {
                                    CONST_NEW_ACCOUNT -> {
                                        requireActivity().toMoveFragment(this@CreateNameFragment, CreateMnemonicFragment(name), "CreateMnemonic")
                                    }

                                    CONST_RESTORE_MNEMONIC_ACCOUNT -> {
                                        requireActivity().toMoveFragment(this@CreateNameFragment, RestoreMnemonicFragment(name), "RestoreMnemonic")
                                    }

                                    CONST_RESTORE_PRIVATE_ACCOUNT -> {
                                        requireActivity().toMoveFragment(this@CreateNameFragment, RestorePrivateFragment(name), "RestorePrivate")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}