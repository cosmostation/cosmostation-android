package wannabit.io.cosmostaion.ui.main.setting.account

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.toMoveFragment
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentAccountListBinding
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.account.AccountViewModel
import wannabit.io.cosmostaion.ui.wallet.CreateMnemonicFragment
import wannabit.io.cosmostaion.ui.wallet.RestoreMnemonicFragment
import wannabit.io.cosmostaion.ui.wallet.RestorePrivateFragment

class AccountListFragment : Fragment() {

    private var _binding: FragmentAccountListBinding? = null
    private val binding get() = _binding!!

    private lateinit var accountListAdapter: AccountListAdapter

    private val accountViewModel: AccountViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        setUpClickAction()
        checkAccountData()
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            CoroutineScope(Dispatchers.IO).launch {
                val appDatabase = AppDatabase.getInstance()
                val selectAllAccount = appDatabase.baseAccountDao().selectAll()
                val mnemonicAccounts =
                    selectAllAccount.filter { it.type == BaseAccountType.MNEMONIC }
                val privateAccounts =
                    selectAllAccount.filter { it.type == BaseAccountType.PRIVATE_KEY }

                withContext(Dispatchers.Main) {
                    accountListAdapter = AccountListAdapter(
                        requireContext(), mnemonicAccounts, privateAccounts, popupClickAction
                    )
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = accountListAdapter
                    accountListAdapter.submitList(selectAllAccount)
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }

            var isClickable = true
            btnAddAccount.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    AccountInitSelectFragment(accountInitSelectAction).show(
                        parentFragmentManager, AccountInitSelectFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }
        }
    }

    private val accountInitSelectAction = object : AccountInitListener {
        override fun initAction(initType: Int) {
            if (initType == BaseConstant.CONST_NEW_ACCOUNT) {
                val intent = Intent(requireContext(), PasswordCheckActivity::class.java)
                createAccountResultLauncher.launch(intent)
                pendingTransition()

            } else if (initType == BaseConstant.CONST_RESTORE_MNEMONIC_ACCOUNT) {
                val intent = Intent(requireContext(), PasswordCheckActivity::class.java)
                restoreMnemonicResultLauncher.launch(intent)
                pendingTransition()

            } else {
                val intent = Intent(requireContext(), PasswordCheckActivity::class.java)
                restorePrivateResultLauncher.launch(intent)
                pendingTransition()
            }
        }
    }

    private val createAccountResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                lifecycleScope.launch {
                    delay(300)
                    dismissInitSelectFragment()
                    withContext(Dispatchers.Main) {
                        requireActivity().toMoveFragment(
                            this@AccountListFragment, CreateMnemonicFragment(), "CreateMnemonic"
                        )
                    }
                }
            }
        }

    private val restoreMnemonicResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                lifecycleScope.launch {
                    delay(300)
                    dismissInitSelectFragment()
                    withContext(Dispatchers.Main) {
                        requireActivity().toMoveFragment(
                            this@AccountListFragment, RestoreMnemonicFragment(), "RestoreMnemonic"
                        )
                    }
                }
            }
        }

    private val restorePrivateResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                lifecycleScope.launch {
                    delay(300)
                    dismissInitSelectFragment()
                    withContext(Dispatchers.Main) {
                        requireActivity().toMoveFragment(
                            this@AccountListFragment, RestorePrivateFragment(), "RestorePrivate"
                        )
                    }
                }
            }
        }

    private fun pendingTransition() {
        requireActivity().overridePendingTransition(
            R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
        )
    }

    private fun dismissInitSelectFragment() {
        val accountInitSelectFragment =
            parentFragmentManager.findFragmentByTag(AccountInitSelectFragment::class.java.name)
        if (accountInitSelectFragment is BottomSheetDialogFragment) {
            accountInitSelectFragment.dismiss()
        }
    }

    private fun checkAccountData() {
        accountViewModel.baseAccounts.observe(viewLifecycleOwner) { result ->
            val mnemonicAccounts = result.filter { it.type == BaseAccountType.MNEMONIC }
            val privateAccounts = result.filter { it.type == BaseAccountType.PRIVATE_KEY }

            accountListAdapter = AccountListAdapter(
                requireContext(), mnemonicAccounts, privateAccounts, popupClickAction
            )
            binding.recycler.setHasFixedSize(true)
            binding.recycler.layoutManager = LinearLayoutManager(requireContext())
            binding.recycler.adapter = accountListAdapter
            accountListAdapter.submitList(result)
        }
    }

    private val popupClickAction = object : AccountListAdapter.ClickListener {
        override fun checkMnemonicAction(account: BaseAccount) {
            ApplicationViewModel.shared.checkPwMnemonicResult.observe(viewLifecycleOwner) {
                lifecycleScope.launch {
                    delay(300)
                    withContext(Dispatchers.Main) {
                        requireActivity().toMoveFragment(
                            this@AccountListFragment,
                            MnemonicCheckFragment(account),
                            "MnemonicCheck"
                        )
                    }
                }
            }
        }

        override fun checkPrivateAction(account: BaseAccount) {
            ApplicationViewModel.shared.checkPwPrivateResult.observe(viewLifecycleOwner) {
                lifecycleScope.launch {
                    delay(300)
                    withContext(Dispatchers.Main) {
                        requireActivity().toMoveFragment(
                            this@AccountListFragment, PrivateCheckFragment(account), "PrivateCheck"
                        )
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

interface AccountInitListener {
    fun initAction(initType: Int)
}