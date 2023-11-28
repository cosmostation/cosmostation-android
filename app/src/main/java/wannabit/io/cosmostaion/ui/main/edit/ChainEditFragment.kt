package wannabit.io.cosmostaion.ui.main.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.updateSelectButtonView
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.FragmentChainEditBinding
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import java.math.BigDecimal

class ChainEditFragment : BaseTxFragment() {

    private var _binding: FragmentChainEditBinding? = null
    private val binding get() = _binding!!

    private lateinit var chainEditAdapter: ChainEditAdapter

    private var baseAccount: BaseAccount? = null
    private var allCosmosChains: MutableList<CosmosLine> = mutableListOf()
    private var searchCosmosChains: MutableList<CosmosLine> = mutableListOf()
    private var toDisplayChainLines: MutableList<String> = mutableListOf()

    private var job = Job()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChainEditBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        clickAction()
    }

    private fun initView() {
        binding.apply {
            baseAccount = BaseData.baseAccount
            baseAccount?.let { account ->
                CoroutineScope(Dispatchers.IO).launch {
                    account.sortCosmosLine()
                    account.initAllData()

                    withContext(Dispatchers.Main) {
                        loading.visibility = View.GONE
                        allCosmosChains = account.allCosmosLineChains
                        searchCosmosChains = allCosmosChains

                        toDisplayChainLines = Prefs.getDisplayChains(account)

                        chainEditAdapter = ChainEditAdapter(account, searchCosmosChains, toDisplayChainLines, editClickAction)
                        recycler.setHasFixedSize(true)
                        recycler.layoutManager = LinearLayoutManager(requireContext())
                        recycler.adapter = chainEditAdapter

                        binding.btnSelect.updateSelectButtonView(allCosmosChains.none { !it.fetched })
                        initData()
                    }
                }
            }
        }
    }

    private fun initData() {
        binding.apply {
            for (i in 0 until searchCosmosChains.size) {
                searchCosmosChains[i].setLoadDataCallBack(object : CosmosLine.LoadDataCallback {
                    override fun onDataLoaded(isLoaded: Boolean) {
                        lifecycleScope.launch {
                            withContext(Dispatchers.Main) {
                                if (isLoaded) {
                                    chainEditAdapter.notifyItemChanged(i)
                                }
                                btnSelect.updateSelectButtonView(allCosmosChains.none { !it.fetched })
                            }
                        }
                    }
                })
            }
        }
    }

    private val editClickAction = object : ChainEditAdapter.SelectListener {
        override fun select(displayChainLines: MutableList<String>) {
            toDisplayChainLines = displayChainLines
        }
    }

    private fun clickAction() {
        binding.apply {
            btnSelect.setOnClickListener {
                baseAccount?.let { account ->
                    account.reSortCosmosChains()
                    allCosmosChains = account.allCosmosLineChains

                    toDisplayChainLines.clear()
                    toDisplayChainLines.add("cosmos118")
                    allCosmosChains.forEach { line ->
                        if (line.allAssetValue() > BigDecimal.ONE && line.tag != "cosmos118") {
                            toDisplayChainLines.add(line.tag)
                        }
                    }

                    searchCosmosChains.sortWith { o1, o2 ->
                        when {
                            o1.tag == "cosmos118" -> -1
                            o2.tag == "cosmos118" -> 1
                            else -> {
                                when {
                                    o1.allValue() > o2.allValue() -> -1
                                    o1.allValue() < o2.allValue() -> 1
                                    else -> 0
                                }
                            }
                        }
                    }
                    searchCosmosChains.sortWith { o1, o2 ->
                        when {
                            o1.tag == "cosmos118" -> -1
                            o2.tag == "cosmos118" -> 1
                            Prefs.getDisplayChains(account).contains(o1.tag) && !Prefs.getDisplayChains(account).contains(o2.tag) -> -1
                            else -> 1
                        }
                    }
                    chainEditAdapter.notifyDataSetChanged()
                }
            }

            btnConfirm.setOnClickListener {
                baseAccount?.let { baseAccount ->
                    Prefs.setDisplayChains(baseAccount, toDisplayChainLines)
                    ApplicationViewModel.shared.walletEdit()
                    dismiss()
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        job.cancel()
    }
}