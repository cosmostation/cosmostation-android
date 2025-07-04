package wannabit.io.cosmostaion.ui.init

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.i2p.crypto.eddsa.Utils
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainSolana
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.testnetClass.ChainGnoTestnet
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.concurrentForEach
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.data.viewmodel.account.AccountViewModel
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentWalletSelectBinding
import wannabit.io.cosmostaion.ui.main.MainActivity

class WalletSelectFragment : Fragment() {

    private var _binding: FragmentWalletSelectBinding? = null
    private val binding get() = _binding!!

    private lateinit var mnemonic: String
    private lateinit var pKey: String
    private var initType: Int? = 0

    private lateinit var walletSelectAdapter: WalletSelectAdapter

    private val walletViewModel: WalletViewModel by activityViewModels()
    private val accountViewModel: AccountViewModel by activityViewModels()

    private var toAddAccount: BaseAccount? = null
    private var selectHdPath = 0

    private var mainnetChains = mutableListOf<BaseChain>()
    private var testnetChains = mutableListOf<BaseChain>()
    private var selectedTags: MutableList<String> = mutableListOf()

    companion object {
        @JvmStatic
        fun newInstance(mnemonic: String?, pKey: String?, initType: Int): WalletSelectFragment {
            val args = Bundle().apply {
                putString("mnemonic", mnemonic)
                putString("pKey", pKey)
                putInt("initType", initType)
            }
            val fragment = WalletSelectFragment()
            fragment.arguments = args
            return fragment
        }
    }

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
        arguments?.apply {
            mnemonic = getString("mnemonic").toString()
            pKey = getString("pKey").toString()
            initType = getInt("initType", -1)
        }

        binding.btnRestoreWallet.updateButtonView(false)
        selectedTags.add("cosmos118")
        lifecycleScope.launch(Dispatchers.IO) {
            if (mnemonic.isNotEmpty()) {
                toAddAccount =
                    BaseAccount("", "", "", "", BaseAccountType.MNEMONIC, selectHdPath.toString())

                initMnemonicView()

            } else {
                binding.pathLayout.visibility = View.GONE
                toAddAccount = BaseAccount("", "", "", "", BaseAccountType.PRIVATE_KEY, "0")

                initPKeyAllData()
            }
        }
    }

    private fun initMnemonicView() {
        lifecycleScope.launch(Dispatchers.IO) {
            toAddAccount?.let { account ->
                account.apply {
                    allChains = allChains()
                    mainnetChains = allChains.filter { !it.isTestnet }.toMutableList()
                    mainnetChains.sortWith(compareBy(
                        { if (it.tag == "cosmos118") 0 else 1 },
                        { it.name.lowercase() }
                    ))
                    testnetChains = allChains.filter { it.isTestnet }.toMutableList()
                    testnetChains.sortedBy { it.name }
                }
                withContext(Dispatchers.Main) {
                    updateView()
                }
            }
        }
    }

    private fun initPKeyAllData() {
        lifecycleScope.launch(Dispatchers.IO) {
            toAddAccount?.let { account ->
                account.apply {
                    allChains = allChains()
                    mainnetChains =
                        allChains.filter { !it.isTestnet && it.isDefault || it.tag == "kava459" || it.apiName == "bitcoin" }
                            .toMutableList()
                    mainnetChains.sortWith(compareBy(
                        { if (it.tag == "cosmos118") 0 else 1 },
                        { it.name.lowercase() }
                    ))
                    testnetChains = allChains.filter { it.isTestnet }.toMutableList()
                    testnetChains.sortedBy { it.name }
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
                if (mainnetChains.isNotEmpty()) {
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
                mainnetChains, testnetChains, selectedTags, selectClickAction
            )
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = walletSelectAdapter
            walletSelectAdapter.submitList(mainnetChains + testnetChains)

            loadBalanceData(account)
        }
    }

    private fun loadBalanceData(account: BaseAccount) {
        lifecycleScope.launch(Dispatchers.IO) {
            account.apply {
                if (mnemonic.isNotEmpty()) {
                    val wordList = mnemonic.split(" ")
                    val seed = BaseKey.getHDSeed(BaseKey.toEntropy(wordList))
                    allChains.asSequence().concurrentForEach { chain ->
                        val safeContext = context ?: return@concurrentForEach
                        if (chain.publicKey == null) {
                            chain.setInfoWithSeed(
                                safeContext, seed, chain.setParentPath, lastHDPath
                            )
                        }
                        if (chain.address.isNotEmpty()) {
                            withContext(Dispatchers.Main) {
                                updateRowData(chain.tag)
                            }
                        }

                        if (chain.fetchState == FetchState.IDLE || chain.fetchState == FetchState.BUSY) {
                            if (chain.supportCosmos() || chain is ChainSui || chain is ChainIota || chain is ChainBitCoin86 || chain is ChainSolana || chain is ChainGnoTestnet) {
                                walletViewModel.balance(chain)
                            } else {
                                walletViewModel.evmBalance(chain)
                            }
                        }
                    }

                } else {
                    allChains.asSequence().concurrentForEach { chain ->
                        val safeContext = context ?: return@concurrentForEach
                        if (chain.publicKey == null) {
                            chain.setInfoWithPrivateKey(safeContext, Utils.hexToBytes(pKey))
                        }
                        if (chain.address.isNotEmpty()) {
                            withContext(Dispatchers.Main) {
                                updateRowData(chain.tag)
                            }
                        }

                        if (chain.fetchState == FetchState.IDLE || chain.fetchState == FetchState.BUSY) {
                            if (chain.supportCosmos() || chain is ChainSui || chain is ChainBitCoin86 || chain is ChainSolana || chain is ChainGnoTestnet) {
                                walletViewModel.balance(chain)
                            } else {
                                walletViewModel.evmBalance(chain)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun updateRowData(tag: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val mainnetResult = mainnetChains.filter { it.tag == tag }
            val mainIterator = mainnetResult.iterator()
            while (mainIterator.hasNext()) {
                val chain = mainIterator.next()
                val index = mainnetChains.indexOf(chain)
                if (::walletSelectAdapter.isInitialized) {
                    withContext(Dispatchers.Main) {
                        walletSelectAdapter.notifyItemChanged(index + 1)
                    }
                }
            }
        }
    }

    private fun setupLoadedData() {
        walletViewModel.balanceResult.observe(viewLifecycleOwner) { tag ->
            lifecycleScope.launch(Dispatchers.IO) {
                for (i in 0 until mainnetChains.size) {
                    if (mainnetChains[i].tag == tag) {
                        withContext(Dispatchers.Main) {
                            if (::walletSelectAdapter.isInitialized) {
                                walletSelectAdapter.notifyItemChanged(i + 1)
                            }
                        }
                    }
                }

                for (i in 0 until testnetChains.size) {
                    if (testnetChains[i].tag == tag) {
                        withContext(Dispatchers.Main) {
                            if (::walletSelectAdapter.isInitialized) {
                                walletSelectAdapter.notifyItemChanged(i + (mainnetChains.size + 2))
                            }
                        }
                    }
                }
            }
        }
    }

    private val selectClickAction = object : WalletSelectAdapter.SelectListener {
        override fun select(selectTags: MutableList<String>) {
            selectedTags = selectTags
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            var isClickable = true
            pathLayout.setOnClickListener {
                if (isClickable) {
                    isClickable = false

                    val setHdPath = HdPathSelectFragment {
                        selectHdPath = it
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
                                mnemonic.split(" ").let { wordList ->
                                    val entropy = Utils.bytesToHex(BaseKey.toEntropy(wordList))
                                    accountViewModel.createByMnemonic(
                                        name, entropy, selectHdPath.toString()
                                    )
                                }

                            } else {
                                pKey.let { privateKey ->
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
        selectedTags.clear()
        selectedTags.add("cosmos118")
        binding.btnRestoreWallet.updateButtonView(false)

        lifecycleScope.launch(Dispatchers.IO) {
            toAddAccount =
                BaseAccount("", "", "", "", BaseAccountType.MNEMONIC, selectHdPath.toString())
            initMnemonicView()
        }
    }

    private fun checkRestore() {
        accountViewModel.create.observe(viewLifecycleOwner) {
            BaseData.baseAccount?.let { account ->
                Prefs.setDisplayChains(account, selectedTags)
                ApplicationViewModel.shared.currentAccount(account, true)

                startToActivity()
            }
        }
    }

    @SuppressLint("WrongConstant")
    private fun startToActivity() {
        if (initType == BaseConstant.CONST_RESTORE_MNEMONIC_ACCOUNT || initType == BaseConstant.CONST_RESTORE_PRIVATE_ACCOUNT || initType == BaseConstant.CONST_RESTORE_QR_ACCOUNT) {
            if (Build.VERSION.SDK_INT >= 34) {
                requireActivity().overrideActivityTransition(
                    Activity.OVERRIDE_TRANSITION_CLOSE, 0, 0
                )
            } else {
                requireActivity().overridePendingTransition(0, 0)
            }
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