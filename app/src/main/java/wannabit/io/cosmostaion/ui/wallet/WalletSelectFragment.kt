package wannabit.io.cosmostaion.ui.wallet

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.i2p.crypto.eddsa.Utils
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentWalletSelectBinding
import wannabit.io.cosmostaion.ui.main.MainActivity
import wannabit.io.cosmostaion.ui.main.setting.general.PushManager
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.account.AccountViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel

class WalletSelectFragment(
    val mnemonic: String?, private val pKey: String?, val initType: Int
) : Fragment() {

    private var _binding: FragmentWalletSelectBinding? = null
    private val binding get() = _binding!!

    private lateinit var walletSelectAdapter: WalletSelectAdapter

    private val walletViewModel: WalletViewModel by activityViewModels()
    private val accountViewModel: AccountViewModel by activityViewModels()

    private var toAddAccount: BaseAccount? = null
    private var selectHdPath = 0

    private var selectedCosmosTags: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalletSelectBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setupLoadedData()
        setUpClickAction()
        checkRestore()
    }

    private fun initView() {
        binding.btnRestoreWallet.updateButtonView(false)
        selectedCosmosTags.add("cosmos118")
        CoroutineScope(Dispatchers.IO).launch {
            if (mnemonic?.isNotEmpty() == true) {
                val wordList = mnemonic.split(" ")
                val seed = BaseKey.getByteSeedFromWords(wordList)
                toAddAccount =
                    BaseAccount("", "", "", "", BaseAccountType.MNEMONIC, selectHdPath.toString())

                initMnemonicAllDataWithSeed(seed)

            } else {
                binding.pathLayout.visibility = View.GONE
                toAddAccount = BaseAccount("", "", "", "", BaseAccountType.PRIVATE_KEY, "0")

                initPKeyAllData()
            }
        }
    }

    private fun initMnemonicAllDataWithSeed(seed: ByteArray?) {
        CoroutineScope(Dispatchers.IO).launch {
            toAddAccount?.let { account ->
                account.apply {
                    allCosmosLineChains = allCosmosLines()
                    allCosmosLineChains.forEach { line ->
                        if (line.address?.isEmpty() == true) {
                            line.setInfoWithSeed(seed, line.setParentPath, lastHDPath)
                        }
                        if (!line.fetched) {
                            walletViewModel.balance(line)
                        }
                    }
                }
                withContext(Dispatchers.Main) {
                    updateView()
                }
            }
        }
    }

    private fun initPKeyAllData() {
        CoroutineScope(Dispatchers.IO).launch {
            toAddAccount?.let { account ->
                account.apply {
                    allCosmosLineChains =
                        allCosmosLines().filter { it.isDefault || it.tag == "okt996_Secp" }
                            .toMutableList()
                    allCosmosLineChains.forEach { line ->
                        if (line.address?.isEmpty() == true) {
                            line.setInfoWithPrivateKey(Utils.hexToBytes(pKey))
                        }
                        if (!line.fetched) {
                            walletViewModel.balance(line)
                        }
                    }
                    withContext(Dispatchers.Main) {
                        updateView()
                    }
                }
            }
        }
    }

    private fun updateView() {
        binding.apply {
            toAddAccount?.let { account ->
                if (account.allCosmosLineChains.isNotEmpty()) {
                    loading.visibility = View.GONE
                    recycler.visibility = View.VISIBLE
                    btnRestoreWallet.updateButtonView(true)

                    hdPath.text = selectHdPath.toString()

                    initRecyclerView(account)
                }
            }
        }
    }

    private fun initRecyclerView(account: BaseAccount) {
        binding.apply {
            recycler.itemAnimator = null
            walletSelectAdapter = WalletSelectAdapter(
                requireContext(), account, selectedCosmosTags, selectClickAction
            )
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = walletSelectAdapter
            walletSelectAdapter.submitList(account.allCosmosLineChains)
        }
    }

    private fun setupLoadedData() {
        walletViewModel.balanceResult.observe(viewLifecycleOwner) {
            CoroutineScope(Dispatchers.IO).launch {
                toAddAccount?.let { account ->
                    for (i in 0 until account.allCosmosLineChains.size) {
                        if (account.allCosmosLineChains[i].fetched) {
                            withContext(Dispatchers.Main) {
                                if (::walletSelectAdapter.isInitialized) {
                                    walletSelectAdapter.notifyItemChanged(i + 1)
                                }
                            }
                        }
                    }
                }
            }
        }

        walletViewModel.chainDataErrorMessage.observe(viewLifecycleOwner) {
            return@observe
        }
    }

    private val selectClickAction = object : WalletSelectAdapter.SelectListener {
        override fun select(selectTags: MutableList<String>) {
            selectedCosmosTags = selectTags
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            var isClickable = true
            btnPath.setOnClickListener {
                if (isClickable) {
                    isClickable = false

                    val setHdPath = HdPathSelectFragment {
                        selectHdPath = it
                        updateView()
                        onInitDataWithSelectedHdPath()
                    }
                    setHdPath.selectedNumber = selectHdPath
                    setHdPath.show(
                        requireActivity().supportFragmentManager,
                        HdPathSelectFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            btnRestoreWallet.setOnClickListener {
                CreateNameFragment().show(
                    parentFragmentManager, CreateNameFragment::class.java.name
                )
                parentFragmentManager.setFragmentResultListener(
                    "create", this@WalletSelectFragment
                ) { _, bundle ->
                    bundle.getString("create")?.let { name ->
                        requireActivity().runOnUiThread {
                            if (toAddAccount?.type == BaseAccountType.MNEMONIC) {
                                mnemonic?.split(" ")?.let { wordList ->
                                    val entropy = Utils.bytesToHex(BaseKey.toEntropy(wordList))
                                    accountViewModel.createByMnemonic(
                                        name, entropy, selectHdPath.toString()
                                    )
                                }

                            } else {
                                pKey?.let { privateKey ->
                                    accountViewModel.createByPrivate(name, privateKey)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onInitDataWithSelectedHdPath() {
        selectedCosmosTags.clear()
        selectedCosmosTags.add("cosmos118")
        binding.btnRestoreWallet.updateButtonView(false)

        CoroutineScope(Dispatchers.IO).launch {
            toAddAccount =
                BaseAccount("", "", "", "", BaseAccountType.MNEMONIC, selectHdPath.toString())
            mnemonic?.split(" ")?.let { wordList ->
                val seed = BaseKey.getByteSeedFromWords(wordList)
                initMnemonicAllDataWithSeed(seed)
            }
        }
    }

    private fun checkRestore() {
        accountViewModel.create.observe(viewLifecycleOwner) {
            BaseData.baseAccount?.let { account ->
                Prefs.setDisplayChains(account, selectedCosmosTags)
                ApplicationViewModel.shared.currentAccount(account)

                startToActivity()
            }
        }
    }

    private fun startToActivity() {
        if (initType == BaseConstant.CONST_RESTORE_MNEMONIC_ACCOUNT || initType == BaseConstant.CONST_RESTORE_PRIVATE_ACCOUNT) {
            requireActivity().overridePendingTransition(0, 0)
            requireActivity().finish()
        } else {
            Intent(requireActivity(), MainActivity::class.java).apply {
                startActivity(this)
            }
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}