package wannabit.io.cosmostaion.ui.main.setting.wallet.account

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.reown.util.bytesToHex
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.toMoveFragment
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.data.viewmodel.account.AccountViewModel
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentAccountListBinding
import wannabit.io.cosmostaion.ui.init.CreateMnemonicFragment
import wannabit.io.cosmostaion.ui.init.RestoreMnemonicConfirmFragment
import wannabit.io.cosmostaion.ui.init.RestoreMnemonicFragment
import wannabit.io.cosmostaion.ui.init.RestorePrivateFragment
import wannabit.io.cosmostaion.ui.main.AccountManageListener
import wannabit.io.cosmostaion.ui.main.AccountManageOptionFragment
import wannabit.io.cosmostaion.ui.main.setting.wallet.importQR.ImportBarcodeActivity
import wannabit.io.cosmostaion.ui.main.setting.wallet.importQR.ImportCheckKeyFragment
import wannabit.io.cosmostaion.ui.main.setting.wallet.importQR.QrImportConfirmListener
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity

class AccountListFragment : Fragment() {

    private var _binding: FragmentAccountListBinding? = null
    private val binding get() = _binding!!

    private lateinit var accountListAdapter: AccountListAdapter

    private val accountViewModel: AccountViewModel by activityViewModels()

    private var manageAccount: BaseAccount? = null
    private var mnemonicAccounts: MutableList<BaseAccount> = mutableListOf()
    private var searchMnemonicAccounts: MutableList<BaseAccount> = mutableListOf()
    private var privateAccounts: MutableList<BaseAccount> = mutableListOf()
    private var searchPrivateAccounts: MutableList<BaseAccount> = mutableListOf()

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initSearchView()
        setUpClickAction()
        checkAccountData()
        checkChangeNameData()
    }

    private fun initData() {
        binding.recycler.apply {
            CoroutineScope(Dispatchers.IO).launch {
                val appDatabase = AppDatabase.getInstance()
                mnemonicAccounts =
                    appDatabase.baseAccountDao().selectsAccount(BaseAccountType.MNEMONIC)
                        .toMutableList()
                searchMnemonicAccounts.addAll(mnemonicAccounts)

                privateAccounts =
                    appDatabase.baseAccountDao().selectsAccount(BaseAccountType.PRIVATE_KEY)
                        .toMutableList()
                searchPrivateAccounts.addAll(privateAccounts)

                withContext(Dispatchers.Main) {
                    initRecyclerView()
                }
            }
        }
    }

    private val updateOrderAction = object : AccountListAdapter.AccountSortListener {
        override fun updateMnemonicOrder(mnemonicAccounts: List<BaseAccount>) {
            CoroutineScope(Dispatchers.IO).launch {
                for (i in mnemonicAccounts.indices) {
                    mnemonicAccounts[i].sortOrder = i.toLong()
                }
                mnemonicAccounts.forEach { account ->
                    AppDatabase.getInstance().baseAccountDao()
                        .updateAccount(account.id, account.name, account.sortOrder)
                }
            }
        }

        override fun updatePrivateOrder(privateAccounts: List<BaseAccount>) {
            CoroutineScope(Dispatchers.IO).launch {
                for (i in privateAccounts.indices) {
                    privateAccounts[i].sortOrder = i.toLong() + 9000
                }
                privateAccounts.forEach { account ->
                    AppDatabase.getInstance().baseAccountDao()
                        .updateAccount(account.id, account.name, account.sortOrder)
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            accountListAdapter = AccountListAdapter(
                requireContext(), searchMnemonicAccounts, searchPrivateAccounts, updateOrderAction
            )
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = accountListAdapter
            ItemTouchHelper(
                AccountListTouchAdapter(
                    accountListAdapter, binding.searchView.query
                )
            ).apply {
                attachToRecyclerView(binding.recycler)
            }

            accountListAdapter.setOnItemClickListener { account ->
                handleOneClickWithDelay(
                    AccountManageOptionFragment.newInstance(
                        account, accountManageSelectAction
                    )
                )
            }
        }
    }

    private fun initSearchView() {
        binding.apply {
            searchView.setQuery("", false)
            searchView.clearFocus()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchMnemonicAccounts.clear()
                    searchPrivateAccounts.clear()

                    if (StringUtils.isEmpty(newText)) {
                        searchMnemonicAccounts.addAll(mnemonicAccounts)
                        searchPrivateAccounts.addAll(privateAccounts)

                    } else {
                        newText?.let { searchTxt ->
                            searchMnemonicAccounts.addAll(mnemonicAccounts.filter { account ->
                                account.name.contains(searchTxt, ignoreCase = true)
                            })

                            searchPrivateAccounts.addAll(privateAccounts.filter { account ->
                                account.name.contains(searchTxt, ignoreCase = true)
                            })
                        }
                    }

                    if (searchMnemonicAccounts.isEmpty() && searchPrivateAccounts.isEmpty()) {
                        emptyLayout.visibility = View.VISIBLE
                        recycler.visibility = View.GONE
                    } else {
                        emptyLayout.visibility = View.GONE
                        recycler.visibility = View.VISIBLE
                        accountListAdapter.notifyDataSetChanged()
                    }
                    return true
                }
            })
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }

            btnAddAccount.setOnClickListener {
                handleOneClickWithDelay(
                    AccountInitSelectFragment.newInstance(
                        accountInitSelectAction
                    )
                )
            }
        }
    }

    private val accountInitSelectAction = object : AccountInitListener {
        override fun initAction(initType: Int) {
            when (initType) {
                BaseConstant.CONST_NEW_ACCOUNT -> {
                    val intent = Intent(requireContext(), PasswordCheckActivity::class.java)
                    createAccountResultLauncher.launch(intent)
                    pendingTransition()
                }

                BaseConstant.CONST_RESTORE_MNEMONIC_ACCOUNT -> {
                    val intent = Intent(requireContext(), PasswordCheckActivity::class.java)
                    restoreMnemonicResultLauncher.launch(intent)
                    pendingTransition()
                }

                BaseConstant.CONST_RESTORE_PRIVATE_ACCOUNT -> {
                    val intent = Intent(requireContext(), PasswordCheckActivity::class.java)
                    restorePrivateResultLauncher.launch(intent)
                    pendingTransition()
                }

                else -> {
                    if (ActivityCompat.checkSelfPermission(
                            requireActivity(), Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_DENIED
                    ) {
                        cameraPermissionRequest.launch(Manifest.permission.CAMERA)

                    } else {
                        startImportBarcodeActivity()
                    }
                }
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
                            this@AccountListFragment,
                            CreateMnemonicFragment.newInstance(BaseConstant.CONST_NEW_ACCOUNT),
                            "CreateMnemonic"
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
                            this@AccountListFragment,
                            RestoreMnemonicFragment(BaseConstant.CONST_RESTORE_MNEMONIC_ACCOUNT),
                            "RestoreMnemonic"
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
                            this@AccountListFragment,
                            RestorePrivateFragment(BaseConstant.CONST_RESTORE_PRIVATE_ACCOUNT),
                            "RestorePrivate"
                        )
                    }
                }
            }
        }

    private val accountManageSelectAction = object : AccountManageListener {
        override fun checkMnemonic(account: BaseAccount) {
            val intent = Intent(requireContext(), PasswordCheckActivity::class.java)
            manageAccount = account
            mnemonicCheckResultLauncher.launch(intent)
            pendingTransition()
        }

        override fun checkPrivate(account: BaseAccount) {
            val intent = Intent(requireContext(), PasswordCheckActivity::class.java)
            manageAccount = account
            pKeyCheckResultLauncher.launch(intent)
            pendingTransition()
        }
    }

    private val mnemonicCheckResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                lifecycleScope.launch {
                    delay(300)
                    dismissManageFragment()
                    withContext(Dispatchers.Main) {
                        manageAccount?.let { account ->
                            requireActivity().toMoveFragment(
                                this@AccountListFragment,
                                MnemonicCheckFragment.newInstance(account),
                                MnemonicCheckFragment::class.java.name
                            )
                        }
                    }
                }
            }
        }

    private val pKeyCheckResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                lifecycleScope.launch {
                    delay(300)
                    dismissManageFragment()
                    withContext(Dispatchers.Main) {
                        manageAccount?.let { account ->
                            requireActivity().toMoveFragment(
                                this@AccountListFragment,
                                PrivateCheckFragment.newInstance(account),
                                PrivateCheckFragment::class.java.name
                            )
                        }
                    }
                }
            }
        }

    private val cameraPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startImportBarcodeActivity()
            } else {
                return@registerForActivityResult
            }
        }

    private fun startImportBarcodeActivity() {
        val intent = Intent(requireContext(), ImportBarcodeActivity::class.java)
        qrCodeResultLauncher.launch(intent)
        if (Build.VERSION.SDK_INT >= 34) {
            requireActivity().overrideActivityTransition(
                Activity.OVERRIDE_TRANSITION_OPEN, R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
            )
        } else {
            requireActivity().overridePendingTransition(
                R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
            )
        }
    }

    private val qrCodeResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.getStringExtra("import")?.let { scanStr ->
                    lifecycleScope.launch {
                        delay(300)
                        dismissInitSelectFragment()
                        val scanHexData = scanStr.toByteArray(Charsets.UTF_8).bytesToHex()
                        withContext(Dispatchers.Main) {
                            if (scanHexData.startsWith("55324673")) {
                                ImportCheckKeyFragment.newInstance(scanStr, qrImportConfirmAction)
                                    .show(
                                        requireActivity().supportFragmentManager,
                                        ImportCheckKeyFragment::class.java.name
                                    )
                            } else {
                                requireActivity().makeToast(R.string.error_unknown_qr_code)
                                return@withContext
                            }
                        }
                    }
                }
            }
        }

    private val qrImportConfirmAction = object : QrImportConfirmListener {
        override fun qrImportConfirm(mnemonic: String) {
            requireActivity().toMoveFragment(
                this@AccountListFragment, RestoreMnemonicConfirmFragment(
                    mnemonic, BaseConstant.CONST_RESTORE_QR_ACCOUNT
                ), "RestorePath"
            )
        }
    }

    @SuppressLint("WrongConstant")
    private fun pendingTransition() {
        if (Build.VERSION.SDK_INT >= 34) {
            requireActivity().overrideActivityTransition(
                Activity.OVERRIDE_TRANSITION_OPEN, R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
            )
        } else {
            requireActivity().overridePendingTransition(
                R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
            )
        }
    }

    private fun dismissInitSelectFragment() {
        val accountInitSelectFragment =
            parentFragmentManager.findFragmentByTag(AccountInitSelectFragment::class.java.name)
        if (accountInitSelectFragment is BottomSheetDialogFragment) {
            accountInitSelectFragment.dismiss()
        }
    }

    private fun dismissManageFragment() {
        val accountManageOptionFragment =
            parentFragmentManager.findFragmentByTag(AccountManageOptionFragment::class.java.name)
        if (accountManageOptionFragment is BottomSheetDialogFragment) {
            accountManageOptionFragment.dismiss()
        }
    }

    private fun checkAccountData() {
        accountViewModel.baseAccounts.observe(viewLifecycleOwner) { result ->
            mnemonicAccounts = result.filter { it.type == BaseAccountType.MNEMONIC }.toMutableList()
            privateAccounts =
                result.filter { it.type == BaseAccountType.PRIVATE_KEY }.toMutableList()

            searchMnemonicAccounts.clear()
            searchPrivateAccounts.clear()

            searchMnemonicAccounts.addAll(mnemonicAccounts)
            searchPrivateAccounts.addAll(privateAccounts)

            initRecyclerView()
        }
    }

    private fun checkChangeNameData() {
        ApplicationViewModel.shared.changeNameResult.observe(viewLifecycleOwner) {
            if (::accountListAdapter.isInitialized) {
                accountListAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun handleOneClickWithDelay(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        if (isClickable) {
            isClickable = false

            bottomSheetDialogFragment.show(
                requireActivity().supportFragmentManager, bottomSheetDialogFragment::class.java.name
            )

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 300)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}