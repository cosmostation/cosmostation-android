package wannabit.io.cosmostaion.ui.main.dapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.databinding.FragmentDappChainBinding

interface DappChainSelectListener {
    fun select(chain: String)
}

class DappChainFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDappChainBinding? = null
    private val binding get() = _binding!!

    private lateinit var dappChainAdapter: DappChainAdapter

    private var dappChains: MutableList<String>? = mutableListOf()
    private var searchDappChains: MutableList<String>? = mutableListOf()

    companion object {
        @JvmStatic
        fun newInstance(
            dappChains: MutableList<String>?, listener: DappChainSelectListener
        ): DappChainFragment {
            val args = Bundle().apply {
                putStringArrayList("dappChains", dappChains?.let { ArrayList(it) })
            }
            val fragment = DappChainFragment()
            fragment.arguments = args
            fragment.dappChainSelectListener = listener
            return fragment
        }
    }

    private var dappChainSelectListener: DappChainSelectListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDappChainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initSearchView()
    }

    private fun initView() {
        binding.apply {
            dappChains = arguments?.getStringArrayList("dappChains")
            dappChains?.let { searchDappChains?.addAll(it) }
            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            dappChainAdapter = DappChainAdapter()
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = dappChainAdapter
            dappChainAdapter.submitList(searchDappChains)

            dappChainAdapter.setOnItemClickListener {
                dappChainSelectListener?.select(it)
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
//                    searchRecipientAbleChains.clear()
//                    recipientAbleChains?.let {
//                        if (StringUtils.isEmpty(newText)) {
//                            searchRecipientAbleChains.addAll(it)
//                        } else {
//                            newText?.let { searchTxt ->
//                                searchRecipientAbleChains.addAll(it.filter { line ->
//                                    line.name.contains(searchTxt, ignoreCase = true)
//                                })
//                            }
//                        }
//                        chainAdapter.notifyDataSetChanged()
//                    }
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