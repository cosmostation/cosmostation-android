package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.chain.allEvmLines
import wannabit.io.cosmostaion.databinding.FragmentChainManageBinding
import wannabit.io.cosmostaion.ui.main.SettingType
import wannabit.io.cosmostaion.ui.main.setting.SettingBottomFragment

class ChainManageFragment : Fragment() {

    private var _binding: FragmentChainManageBinding? = null
    private val binding get() = _binding!!

    private lateinit var chainManageAdapter: ChainManageAdapter

    private val allEvmLines: MutableList<EthereumLine> = mutableListOf()
    private var searchEvmLines: MutableList<EthereumLine> = mutableListOf()
    private val allCosmosLines: MutableList<CosmosLine> = mutableListOf()
    private var searchCosmosLines: MutableList<CosmosLine> = mutableListOf()

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChainManageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initSearchView()
        setUpClickAction()
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                for (evmChain in allEvmLines()) {
                    if (!allEvmLines.any { it.name == evmChain.name }) {
                        allEvmLines.add(evmChain)
                    }
                }
                for (chain in allCosmosLines().filter { it.isDefault }) {
                    if (!allCosmosLines.any { it.name == chain.name } && !allEvmLines.any { it.name == chain.name }) {
                        allCosmosLines.add(chain)
                    }
                }
                searchEvmLines.addAll(allEvmLines)
                searchCosmosLines.addAll(allCosmosLines)

                withContext(Dispatchers.Main) {
                    chainManageAdapter = ChainManageAdapter(searchEvmLines, searchCosmosLines)
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = chainManageAdapter

                    chainManageAdapter.setOnItemClickListener { chain ->
                        selectEndPoint(chain)
                    }
                }
            }
        }
    }

    private fun selectEndPoint(chain: CosmosLine) {
        if (isClickable) {
            isClickable = false

            val settingType = if (chain is EthereumLine) {
                if (chain.supportCosmos) {
                    SettingType.END_POINT_COSMOS
                } else {
                    SettingType.END_POINT_EVM
                }
            } else {
                SettingType.END_POINT_COSMOS
            }
            SettingBottomFragment.newInstance(chain, settingType).show(
                parentFragmentManager, SettingBottomFragment::class.java.name
            )

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 300)

            parentFragmentManager.setFragmentResultListener(
                "endpoint", this@ChainManageFragment
            ) { _, _ ->
                chainManageAdapter.notifyDataSetChanged()
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
                    searchEvmLines.clear()
                    searchCosmosLines.clear()

                    if (StringUtils.isEmpty(newText)) {
                        searchEvmLines.addAll(allEvmLines)
                        searchCosmosLines.addAll(allCosmosLines)

                    } else {
                        newText?.let { searchTxt ->
                            searchEvmLines.addAll(allEvmLines.filter { chain ->
                                chain.name.contains(searchTxt, ignoreCase = true)
                            })

                            searchCosmosLines.addAll(allCosmosLines.filter { chain ->
                                chain.name.contains(searchTxt, ignoreCase = true)
                            })
                        }
                    }
                    if (searchEvmLines.isEmpty() && searchCosmosLines.isEmpty()) {
                        emptyLayout.visibility = View.VISIBLE
                        recycler.visibility = View.GONE
                    } else {
                        emptyLayout.visibility = View.GONE
                        recycler.visibility = View.VISIBLE
                        chainManageAdapter.notifyDataSetChanged()
                    }
                    return true
                }
            })
        }
    }

    private fun setUpClickAction() {
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}