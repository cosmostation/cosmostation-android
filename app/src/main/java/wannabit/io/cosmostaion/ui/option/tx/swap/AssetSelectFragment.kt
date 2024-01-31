package wannabit.io.cosmostaion.ui.option.tx.swap

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmos.base.v1beta1.CoinProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.databinding.FragmentCommonBottomBinding

interface AssetListener {
    fun select(denom: String)
}

class AssetSelectFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCommonBottomBinding? = null
    private val binding get() = _binding!!

    private var swapAssets: MutableList<Asset>? = mutableListOf()
    private lateinit var swapBalance: MutableList<CoinProto.Coin>
    private lateinit var assetSelectType: AssetSelectType

    private lateinit var assetSelectAdapter: AssetSelectAdapter

    private var searchAssets: MutableList<Asset> = mutableListOf()

    companion object {
        @JvmStatic
        fun newInstance(
            swapAssets: MutableList<Asset>?,
            swapBalance: MutableList<CoinProto.Coin>?,
            assetSelectType: AssetSelectType,
            listener: AssetListener
        ): AssetSelectFragment {
            val args = Bundle().apply {
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
            arguments?.getSerializable(
                "assetSelectType", AssetSelectType::class.java
            )?.let { assetSelectType = it }
        } else {
            (arguments?.getSerializable("assetSelectType") as? AssetSelectType)?.let {
                assetSelectType = it
            }
        }
        swapAssets = arguments?.getParcelableArrayList("swapAssets")
        val serializableSwapBalance = arguments?.getSerializable("swapBalance") as? HashSet<*>
        swapBalance = serializableSwapBalance?.toList() as MutableList<CoinProto.Coin>
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

            swapAssets?.sortWith { o1, o2 ->
                when {
                    o1.symbol == "ATOM" -> -1
                    o2.symbol == "ATOM" -> 1
                    o1.symbol.toString() < o2.symbol.toString() -> -1
                    o1.symbol.toString() > o2.symbol.toString() -> 1
                    else -> 0
                }
            }
            swapAssets?.let { searchAssets.addAll(it) }
            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            assetSelectAdapter = AssetSelectAdapter(swapBalance)
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
                    swapAssets?.let { assets ->
                        searchAssets.clear()
                        if (StringUtils.isEmpty(newText)) {
                            searchAssets.addAll(assets)
                        } else {
                            newText?.let { searchTxt ->
                                searchAssets.addAll(assets.filter { asset ->
                                    asset.symbol?.contains(searchTxt, ignoreCase = true) ?: false
                                })
                            }
                        }
                        assetSelectAdapter.notifyDataSetChanged()
                    }
                    return true
                }
            })
        }
    }
}

enum class AssetSelectType { SWAP_INPUT, SWAP_OUTPUT }