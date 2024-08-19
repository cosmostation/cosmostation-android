package wannabit.io.cosmostaion.ui.main.setting.wallet.account

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.FragmentDeleteBinding
import wannabit.io.cosmostaion.ui.intro.IntroActivity
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.account.AccountViewModel

class DeleteFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDeleteBinding? = null
    private val binding get() = _binding!!

    private lateinit var account: BaseAccount

    private val accountViewModel: AccountViewModel by activityViewModels()

    companion object {
        @JvmStatic
        fun newInstance(baseAccount: BaseAccount): DeleteFragment {
            val args = Bundle().apply {
                putParcelable("baseAccount", baseAccount)
            }
            val fragment = DeleteFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    private fun initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("baseAccount", BaseAccount::class.java)?.let { account = it }

        } else {
            (arguments?.getParcelable("baseAccount") as? BaseAccount)?.let {
                account = it
            }
        }

        binding.title.text = getString(R.string.str_delete_name, account.name)
    }

    @SuppressLint("WrongConstant")
    private fun setUpClickAction() {
        binding.apply {
            btnDelete.setOnClickListener {
                val intent = Intent(requireContext(), PasswordCheckActivity::class.java)
                deleteAccountResultLauncher.launch(intent)
                if (Build.VERSION.SDK_INT >= 34) {
                    requireActivity().overrideActivityTransition(
                        Activity.OVERRIDE_TRANSITION_OPEN,
                        R.anim.anim_slide_in_bottom,
                        R.anim.anim_fade_out
                    )
                } else {
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom,
                        R.anim.anim_fade_out
                    )
                }
            }
        }
    }

    private val deleteAccountResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                accountViewModel.deleteAccount(account)

                lifecycleScope.launch(Dispatchers.IO) {
                    if (AppDatabase.getInstance().baseAccountDao().selectAll().isNotEmpty()) {
                        if (BaseData.baseAccount?.id == account.id) {
                            AppDatabase.getInstance().baseAccountDao().selectAll()
                                .firstOrNull { it.id != account.id }?.let { lastAccount ->
                                    Prefs.lastAccountId = lastAccount.id
                                    BaseData.baseAccount =
                                        AppDatabase.getInstance().baseAccountDao()
                                            .selectAccount(Prefs.lastAccountId)
                                    ApplicationViewModel.shared.currentAccount(
                                        BaseData.baseAccount, true
                                    )
                                }
                        }

                    } else {
                        Prefs.lastAccountId = -1
                        withContext(Dispatchers.Main) {
                            Intent(requireContext(), IntroActivity::class.java).apply {
                                flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(this)
                            }
                        }
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