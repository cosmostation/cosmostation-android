package wannabit.io.cosmostaion.ui.main.dapp

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.R
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
    private var selectedChain: String? = "All Network"

    companion object {
        @JvmStatic
        fun newInstance(
            dappChains: MutableList<String>?,
            selectedChain: String,
            listener: DappChainSelectListener
        ): DappChainFragment {
            val args = Bundle().apply {
                putStringArrayList("dappChains", dappChains?.let { ArrayList(it) })
                putString("selectedChain", selectedChain)
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
            arguments?.apply {
                dappChains = getStringArrayList("dappChains")
                selectedChain = getString("selectedChain")
                dappChains?.let { searchDappChains?.addAll(it) }
            }

            searchView.post {
                val searchHint =
                    searchView.findViewById<AutoCompleteTextView>(androidx.appcompat.R.id.search_src_text)
                searchHint.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
                searchHint.setHintTextColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_base04
                    )
                )

                val searchNewSizeInPx = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 20f, resources.displayMetrics
                ).toInt()

                val searchBtn =
                    searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
                val searchLayoutParams = searchBtn.layoutParams
                searchLayoutParams.width = searchNewSizeInPx
                searchLayoutParams.height = searchNewSizeInPx
                searchBtn.layoutParams = searchLayoutParams
                searchBtn.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_base04
                    ), PorterDuff.Mode.SRC_IN
                )

                val closeNewSizeInPx = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 35f, resources.displayMetrics
                ).toInt()

                val closeBtn =
                    searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
                val closeLayoutParams = closeBtn.layoutParams
                closeLayoutParams.width = closeNewSizeInPx
                closeLayoutParams.height = closeNewSizeInPx
                closeBtn.layoutParams = closeLayoutParams
                closeBtn.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_base04
                    ), PorterDuff.Mode.SRC_IN
                )
            }

            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            dappChainAdapter = DappChainAdapter(selectedChain)
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
                    val updateDappChains = if (StringUtils.isEmpty(newText)) {
                        searchDappChains
                    } else {
                        searchDappChains?.filter { chain ->
                            chain.contains(
                                newText.toString(), true
                            )
                        }
                    }
                    dappChainAdapter.submitList(updateDappChains)
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