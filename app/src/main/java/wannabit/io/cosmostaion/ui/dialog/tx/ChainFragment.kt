package wannabit.io.cosmostaion.ui.dialog.tx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.FragmentCommonBottomBinding

class ChainFragment(
    private val recipientAbleChains: MutableList<CosmosLine>,
    private val chainListType: ChainListType,
    val listener: ChainSelectListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentCommonBottomBinding? = null
    private val binding get() = _binding!!

    private lateinit var chainAdapter: ChainAdapter

    private var searchRecipientAbleChains: MutableList<CosmosLine> = mutableListOf()

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
            when (chainListType) {
                ChainListType.SELECT_INPUT_SWAP -> {
                    selectTitle.text = getString(R.string.title_select_input_chain)
                    searchView.queryHint = getString(R.string.title_select_input_chain)
                    recipientAbleChains.sortWith { o1, o2 ->
                        when {
                            o1.tag == "cosmos118" -> -1
                            o2.tag == "cosmos118" -> 1
                            o1.name < o2.name -> -1
                            o1.name > o2.name -> 1
                            else -> 0
                        }
                    }
                }

                ChainListType.SELECT_OUTPUT_SWAP -> {
                    selectTitle.text = getString(R.string.title_select_output_chain)
                    searchView.queryHint = getString(R.string.title_select_output_chain)
                    recipientAbleChains.sortWith { o1, o2 ->
                        when {
                            o1.tag == "cosmos118" -> -1
                            o2.tag == "cosmos118" -> 1
                            o1.name < o2.name -> -1
                            o1.name > o2.name -> 1
                            else -> 0
                        }
                    }
                }

                else -> {
                    selectTitle.text = getString(R.string.title_select_recipient_chain)
                }
            }
            searchRecipientAbleChains.addAll(recipientAbleChains)
            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            chainAdapter = ChainAdapter()
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chainAdapter
            chainAdapter.submitList(searchRecipientAbleChains)

            chainAdapter.setOnItemClickListener {
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
                    searchRecipientAbleChains.clear()
                    if (StringUtils.isEmpty(newText)) {
                        searchRecipientAbleChains.addAll(recipientAbleChains)
                    } else {
                        newText?.let { searchTxt ->
                            searchRecipientAbleChains.addAll(recipientAbleChains.filter { line ->
                                line.name.contains(searchTxt, ignoreCase = true)
                            })
                        }
                    }
                    chainAdapter.notifyDataSetChanged()
                    return true
                }
            })
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

interface ChainSelectListener {
    fun select(chainId: String)
}

enum class ChainListType { SELECT_TRANSFER, SELECT_INPUT_SWAP, SELECT_OUTPUT_SWAP }