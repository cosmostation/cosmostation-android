package wannabit.io.cosmostaion.ui.main

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.toMoveAnimation
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentDashboardBinding
import wannabit.io.cosmostaion.ui.main.chain.CosmosActivity
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import java.math.BigDecimal


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding: FragmentDashboardBinding? get() = _binding

    private lateinit var dashAdapter: DashboardAdapter

    private var baseAccount: BaseAccount? = null

    private val walletViewModel: WalletViewModel by activityViewModels()

    private var totalChainValue: BigDecimal = BigDecimal.ZERO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModels()
        initView()
        updateViewWithLoadedData()
        setupHideButton()
        refreshData()
    }

    private fun initView() {
        binding?.apply {
            baseAccount = BaseData.baseAccount
            if (Prefs.hideValue) {
                totalValue.text = "✱✱✱✱✱"
                totalValue.textSize = 20f
                btnHide.setImageResource(R.drawable.icon_hide)

            } else {
                totalValue.text = formatAssetValue(totalChainValue)
                totalValue.textSize = 24f
                btnHide.setImageResource(R.drawable.icon_not_hide)
            }
            btnHide.setColorFilter(
                ContextCompat.getColor(requireContext(), R.color.color_base03),
                PorterDuff.Mode.SRC_IN
            )
        }
    }

    private fun updateViewWithLoadedData() {
        initDisplayData()
        initRecyclerView()
        setupLoadedData()
    }

    private fun initRecyclerView() {
        baseAccount?.let { account ->
            dashAdapter = DashboardAdapter(requireContext())

            binding?.recycler?.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = dashAdapter
                dashAdapter.submitList(account.sortedDisplayCosmosLines())
                itemAnimator = null

                dashAdapter.setOnItemClickListener {
                    Intent(requireContext(), CosmosActivity::class.java).apply {
                        putExtra("selectPosition", it)
                        startActivity(this)
                        requireActivity().toMoveAnimation()
                    }
                }
            }
        }
    }

    private fun initDisplayData() {
        baseAccount?.let { account ->
            account.apply {
                CoroutineScope(Dispatchers.IO).launch {
                    if (type == BaseAccountType.MNEMONIC) {
                        sortedDisplayCosmosLines().forEach { line ->
                            if (line.address?.isEmpty() == true) {
                                line.setInfoWithSeed(seed, line.setParentPath, lastHDPath)
                            }
                            if (!line.fetched) {
                                walletViewModel.loadChainData(line, id)
                            }
                        }

                    } else if (type == BaseAccountType.PRIVATE_KEY) {
                        sortedDisplayCosmosLines().forEach { line ->
                            if (line.address?.isEmpty() == true) {
                                line.setInfoWithPrivateKey(privateKey)
                            }
                            if (!line.fetched) {
                                walletViewModel.loadChainData(line, id)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupLoadedData() {
        walletViewModel.fetchedResult.observe(viewLifecycleOwner) {
            CoroutineScope(Dispatchers.IO).launch {
                baseAccount?.let { account ->
                    for (i in 0 until account.sortedDisplayCosmosLines().size) {
                        if (account.sortedDisplayCosmosLines()[i].fetched) {
                            withContext(Dispatchers.Main) {
                                dashAdapter.notifyItemChanged(i + 1)
                            }
                        }
                    }
                }
            }
            updateTotalValue()
        }

        walletViewModel.chainDataErrorMessage.observe(viewLifecycleOwner) {
            return@observe
        }
    }

    private fun updateTotalValue() {
        var totalSum = BigDecimal.ZERO

        baseAccount?.let { account ->
            account.sortedDisplayCosmosLines().forEach { line ->
                totalSum = totalSum.add(line.allValue())
            }

            if (isAdded) {
                requireActivity().runOnUiThread {
                    totalChainValue = totalSum

                    val totalValueTxt = binding?.totalValue
                    totalValueTxt?.text = if (Prefs.hideValue) "✱✱✱✱✱" else formatAssetValue(totalSum)
                    totalValueTxt?.textSize = if (Prefs.hideValue) 20f else 24f
                }
            }
        }
    }

    private fun setupHideButton() {
        binding?.btnHide?.setOnClickListener {
            Prefs.hideValue = !Prefs.hideValue
            ApplicationViewModel.shared.hideValue()
        }
    }

    private fun refreshData() {
        binding?.apply {
            refresher.setOnRefreshListener {
                baseAccount?.let { account ->
                    if (account.sortedDisplayCosmosLines().any { !it.fetched }) {
                        refresher.isRefreshing = false

                    } else {
                        walletViewModel.price(BaseData.currencyName().lowercase())
                        CoroutineScope(Dispatchers.IO).launch {
                            account.sortedDisplayCosmosLines().forEach { line ->
                                line.fetched = false
                            }
                            withContext(Dispatchers.Main) {
                                updateViewWithLoadedData()
                                refresher.isRefreshing = false
                            }
                        }
                    }
                }
            }
        }
    }

    private fun observeViewModels() {
        ApplicationViewModel.shared.hideValueResult.observe(viewLifecycleOwner) {
            binding?.apply {
                if (Prefs.hideValue) {
                    totalValue.text = "✱✱✱✱✱"
                    totalValue.textSize = 20f
                    btnHide.setImageResource(R.drawable.icon_hide)
                } else {
                    totalValue.text = formatAssetValue(totalChainValue)
                    totalValue.textSize = 24f
                    btnHide.setImageResource(R.drawable.icon_not_hide)
                }
            }
            dashAdapter.notifyDataSetChanged()
        }

        ApplicationViewModel.shared.fetchedTokenResult.observe(viewLifecycleOwner) {
            updateTotalValue()
            dashAdapter.notifyDataSetChanged()
        }

        ApplicationViewModel.shared.currentAccountResult.observe(viewLifecycleOwner) { account ->
            baseAccount = account
            CoroutineScope(Dispatchers.IO).launch {
                baseAccount?.initAccount()
                withContext(Dispatchers.Main) {
                    updateViewWithLoadedData()
                }
            }
        }

        ApplicationViewModel.shared.walletEditResult.observe(viewLifecycleOwner) {
            CoroutineScope(Dispatchers.IO).launch {
                baseAccount?.sortCosmosLine()
                initDisplayData()
                withContext(Dispatchers.Main) {
                    dashAdapter.submitList(baseAccount?.sortedDisplayCosmosLines())
                    dashAdapter.notifyDataSetChanged()
                    setupLoadedData()
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}