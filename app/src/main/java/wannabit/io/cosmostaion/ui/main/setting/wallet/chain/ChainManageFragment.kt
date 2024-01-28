package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.databinding.FragmentChainManageBinding

class ChainManageFragment : Fragment() {

    private var _binding: FragmentChainManageBinding? = null
    private val binding get() = _binding!!

    private lateinit var chainManageAdapter: ChainManageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChainManageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        setUpClickAction()
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            val allCosmosLines: MutableList<CosmosLine> = mutableListOf()
            var searchCosmosLines: MutableList<CosmosLine> = mutableListOf()
            CoroutineScope(Dispatchers.IO).launch {
                allCosmosLines.clear()

                allCosmosLines.addAll(allCosmosLines().filter { it.isDefault }
                    .distinctBy { it.name })
                searchCosmosLines = allCosmosLines
                binding.headerCnt.text = allCosmosLines.count().toString()

                withContext(Dispatchers.Main) {
                    chainManageAdapter = ChainManageAdapter()
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = chainManageAdapter
                    chainManageAdapter.submitList(searchCosmosLines)
                }
            }

            initSearchView(allCosmosLines, searchCosmosLines)
        }
    }

    private fun initSearchView(
        allCosmosLines: MutableList<CosmosLine>, searchCosmosLines: MutableList<CosmosLine>
    ) {
        binding.apply {
            searchView.setQuery("", false)
            searchView.clearFocus()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchCosmosLines.clear()
                    if (StringUtils.isEmpty(newText)) {
                        searchCosmosLines.addAll(allCosmosLines)
                    } else {
                        newText?.let { searchTxt ->
                            searchCosmosLines.addAll(allCosmosLines.filter { chain ->
                                chain.name.contains(searchTxt, ignoreCase = true)
                            })
                        }
                    }
                    chainManageAdapter.submitList(searchCosmosLines)
                    chainManageAdapter.notifyDataSetChanged()
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