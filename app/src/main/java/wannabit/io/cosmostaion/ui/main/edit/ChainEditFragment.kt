package wannabit.io.cosmostaion.ui.main.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.updateSelectButtonView
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentChainEditBinding
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import java.math.BigDecimal

class ChainEditFragment : BaseTxFragment() {

    private var _binding: FragmentChainEditBinding? = null
    private val binding get() = _binding!!

    private lateinit var chainEditAdapter: ChainEditAdapter

    private val walletViewModel: WalletViewModel by activityViewModels()

    private var allCosmosChains: MutableList<CosmosLine> = mutableListOf()
    private var toDisplayChainLines: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChainEditBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLoadData()
        setupLoadedView()
        setUpClickAction()
    }

    private fun initLoadData() {
        binding.apply {
            BaseData.baseAccount?.let { account ->
                account.apply {
                    CoroutineScope(Dispatchers.IO).launch {
                        sortCosmosLine()
                        toDisplayChainLines.addAll(Prefs.getDisplayChains(account))

                        withContext(Dispatchers.Main) {
                            recycler.setHasFixedSize(true)
                            recycler.layoutManager = LinearLayoutManager(requireContext())
                            chainEditAdapter = ChainEditAdapter(
                                account, allCosmosLineChains, toDisplayChainLines, editClickAction
                            )
                            recycler.adapter = chainEditAdapter

                            btnSelect.updateSelectButtonView(allCosmosLineChains.none { !it.fetched })
                            progress.goneOrVisible(allCosmosLineChains.none { !it.fetched })
                        }

                        initAllData(account)
                    }
                }
            }
        }
    }

    private fun initAllData(account: BaseAccount) {
        account.apply {
            if (type == BaseAccountType.MNEMONIC) {
                allCosmosLineChains.forEach { line ->
                    if (line.address?.isEmpty() == true) {
                        line.setInfoWithSeed(seed, line.setParentPath, lastHDPath)
                    }
                    if (!line.fetched) {
                        walletViewModel.loadChainData(line, id, true)
                    }
                }

            } else if (type == BaseAccountType.PRIVATE_KEY) {
                allCosmosLineChains.forEach { line ->
                    if (line.address?.isEmpty() == true) {
                        line.setInfoWithPrivateKey(privateKey)
                    }
                    if (!line.fetched) {
                        walletViewModel.loadChainData(line, id, true)
                    }
                }
            }
        }
    }

    private fun setupLoadedView() {
        walletViewModel.editFetchedResult.observe(viewLifecycleOwner) {
            CoroutineScope(Dispatchers.IO).launch {
                BaseData.baseAccount?.let { account ->
                    val fetchedLine =
                        account.allCosmosLineChains.firstOrNull { line -> line.tag == it }
                    val index = account.allCosmosLineChains.indexOf(fetchedLine)
                    withContext(Dispatchers.Main) {
                        account.allCosmosLineChains.forEach { _ ->
                            chainEditAdapter.notifyItemChanged(index)
                        }
                        binding.btnSelect.updateSelectButtonView(account.allCosmosLineChains.none { line -> !line.fetched })
                        binding.progress.goneOrVisible(account.allCosmosLineChains.none { line -> !line.fetched })
                    }
                }
            }
        }

        walletViewModel.chainDataErrorMessage.observe(viewLifecycleOwner) {
            return@observe
        }
    }

    private val editClickAction = object : ChainEditAdapter.SelectListener {
        override fun select(displayChainLines: MutableList<String>) {
            toDisplayChainLines = displayChainLines
        }
    }

    private fun reSortCosmosChains(): MutableList<CosmosLine> {
        BaseData.baseAccount?.let { account ->
            account.apply {
                allCosmosLineChains.sortWith { o1, o2 ->
                    when {
                        o1.tag == "cosmos118" -> -1
                        o2.tag == "cosmos118" -> 1
                        else -> {
                            when {
                                o1.allAssetValue() > o2.allAssetValue() -> -1
                                else -> 1
                            }
                        }
                    }
                }
                return allCosmosLineChains
            }
        }
        return mutableListOf()
    }

    private fun valuableSortCosmosChains() {
        BaseData.baseAccount?.let { account ->
            allCosmosChains.sortWith { o1, o2 ->
                when {
                    o1.tag == "cosmos118" -> -1
                    o2.tag == "cosmos118" -> 1
                    o1.allValue() > o2.allValue() -> -1
                    o1.allValue() < o2.allValue() -> 1
                    else -> 0
                }
            }

            allCosmosChains.sortWith { o1, o2 ->
                when {
                    o1.tag == "cosmos118" -> -1
                    o2.tag == "cosmos118" -> 1
                    Prefs.getDisplayChains(account).contains(o1.tag) && !Prefs.getDisplayChains(
                        account
                    ).contains(o2.tag) -> -1

                    else -> 1
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            BaseData.baseAccount?.let { account ->
                btnSelect.setOnClickListener {
                    allCosmosChains = reSortCosmosChains()

                    val hasValueChains =
                        allCosmosChains.filter { line -> line.allAssetValue() > BigDecimal.ONE && line.tag != "cosmos118" }
                            .toMutableList()

                    if (hasValueChains.none { line -> !toDisplayChainLines.contains(line.tag) }) {
                        return@setOnClickListener
                    }

                    toDisplayChainLines.clear()
                    toDisplayChainLines.add("cosmos118")

                    allCosmosChains.filter { it.allAssetValue() > BigDecimal.ONE && it.tag != "cosmos118" }
                        .forEach { toDisplayChainLines.add(it.tag) }
                    valuableSortCosmosChains()

                    toDisplayChainLines.forEach { tag ->
                        val index = allCosmosChains.indexOfFirst { it.tag == tag }
                        if (index != -1) {
                            chainEditAdapter.notifyItemChanged(index)
                        }
                    }
                }

                btnConfirm.setOnClickListener {
                    Prefs.setDisplayChains(account, toDisplayChainLines)
                    ApplicationViewModel.shared.walletEdit(toDisplayChainLines)
                    dismiss()
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        walletViewModel.editFetchedResult.removeObservers(viewLifecycleOwner)
    }
}