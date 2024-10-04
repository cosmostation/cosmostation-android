package wannabit.io.cosmostaion.ui.tx.option.swap

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmos.base.v1beta1.CoinProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.databinding.FragmentCommonBottomBinding
import wannabit.io.cosmostaion.ui.tx.genTx.TargetAsset
import java.math.BigDecimal

interface AssetListener {
    fun select(denom: String)
}

class AssetSelectFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCommonBottomBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain
    private var swapAssets: MutableList<TargetAsset>? = mutableListOf()
    private var swapBalance: MutableList<CoinProto.Coin>? = mutableListOf()
    private lateinit var assetSelectType: AssetSelectType

    private lateinit var assetSelectAdapter: AssetSelectAdapter

    private var searchAssets: MutableList<TargetAsset> = mutableListOf()

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: BaseChain?,
            swapAssets: MutableList<TargetAsset>?,
            swapBalance: MutableList<CoinProto.Coin>?,
            assetSelectType: AssetSelectType,
            listener: AssetListener
        ): AssetSelectFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putParcelableArrayList("swapAssets", swapAssets?.let { ArrayList(it) })
                putSerializable("swapBalance", swapBalance?.toHashSet())
                putSerializable("assetSelectType", assetSelectType)
            }
            val fragment = AssetSelectFragment()
            fragment.arguments = args
            fragment.assetListener = listener
            return fragment
        }
    }

    private var assetListener: AssetListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommonBottomBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initView()
        initSearchView()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", BaseChain::class.java)?.let {
                selectedChain = it
            }
            arguments?.getSerializable(
                "assetSelectType", AssetSelectType::class.java
            )?.let { assetSelectType = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                selectedChain = it
            }
            (arguments?.getSerializable("assetSelectType") as? AssetSelectType)?.let {
                assetSelectType = it
            }
        }
        swapAssets = arguments?.getParcelableArrayList("swapAssets")
        val serializableSwapBalance = arguments?.getSerializable("swapBalance") as? HashSet<*>
        if (serializableSwapBalance?.isNotEmpty() == true) {
            swapBalance = serializableSwapBalance.toList() as MutableList<CoinProto.Coin>
        }
    }

    private fun initView() {
        binding.apply {
            searchBar.visibility = View.VISIBLE
            if (assetSelectType == AssetSelectType.SWAP_INPUT) {
                selectTitle.text = getString(R.string.title_select_input_asset)
                searchView.queryHint = getString(R.string.title_select_input_asset)
            } else {
                selectTitle.text = getString(R.string.title_select_output_asset)
                searchView.queryHint = getString(R.string.title_select_output_asset)
            }
            searchAssets = swapAssets!!
            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            assetSelectAdapter = AssetSelectAdapter(selectedChain, swapBalance)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = assetSelectAdapter
            assetSelectAdapter.submitList(searchAssets)

            assetSelectAdapter.setOnItemClickListener {
                assetListener?.select(it)
                dismiss()
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
                    val filteredAssets = if (newText.isNullOrEmpty()) {
                        swapAssets
                    } else {
                        swapAssets?.filter { asset ->
                            asset.symbol?.contains(newText, ignoreCase = true) ?: false
                        }
                    }
                    lifecycleScope.launch(Dispatchers.Default) {
                        val assetValues = mutableMapOf<String, BigDecimal>()
                        val assetAmounts = mutableMapOf<String, BigDecimal>()

                        filteredAssets?.forEach { asset ->
                            selectedChain.cosmosFetcher?.balanceValue(asset.denom)?.let { value ->
                                assetValues[asset.denom] = value
                            }
                            selectedChain.cosmosFetcher?.balanceAmount(asset.denom)?.let { amount->
                                assetAmounts[asset.denom] = amount
                            }
                        }

                        val sortedAssets =
                            filteredAssets?.sortedWith(compareBy<TargetAsset> { it.denom != "ATOM" }
                                .thenByDescending { asset -> assetValues[asset.denom] ?: 0.0 }
                                .thenByDescending { asset -> assetAmounts[asset.denom] ?: 0.0 }
                                .thenBy { it.symbol })

                        searchAssets.clear()
                        sortedAssets?.let { searchAssets.addAll(it) }

                        withContext(Dispatchers.Main) {
                            assetSelectAdapter.notifyDataSetChanged()
                        }
                    }
                    return true
                }
            })
        }
    }
}

enum class AssetSelectType { SWAP_INPUT, SWAP_OUTPUT }