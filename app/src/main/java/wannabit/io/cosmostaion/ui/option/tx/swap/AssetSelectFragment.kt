package wannabit.io.cosmostaion.ui.option.tx.swap

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
import wannabit.io.cosmostaion.ui.option.tx.general.AssetSelectListener

class AssetSelectFragment(
    private val swapAssets: MutableList<Asset>,
    private val swapBalance: MutableList<CoinProto.Coin>?,
    private val assetSelectType: AssetSelectType,
    val listener: AssetSelectListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentCommonBottomBinding? = null
    private val binding get() = _binding!!

    private lateinit var assetSelectAdapter: AssetSelectAdapter

    private var searchAssets: MutableList<Asset> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommonBottomBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initSearchView()
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

            swapAssets.sortWith { o1, o2 ->
                when {
                    o1.symbol == "ATOM" -> -1
                    o2.symbol == "ATOM" -> 1
                    o1.symbol.toString() < o2.symbol.toString() -> -1
                    o1.symbol.toString() > o2.symbol.toString() -> 1
                    else -> 0
                }
            }
            searchAssets.addAll(swapAssets)
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
                listener.select(it)
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
                    searchAssets.clear()
                    if (StringUtils.isEmpty(newText)) {
                        searchAssets.addAll(swapAssets)
                    } else {
                        newText?.let { searchTxt ->
                            searchAssets.addAll(swapAssets.filter { asset ->
                                asset.symbol?.contains(searchTxt, ignoreCase = true) ?: false
                            })
                        }
                    }
                    assetSelectAdapter.notifyDataSetChanged()
                    return true
                }
            })
        }
    }
}

enum class AssetSelectType { SWAP_INPUT, SWAP_OUTPUT }