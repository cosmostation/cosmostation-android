package wannabit.io.cosmostaion.ui.main.setting.wallet.book

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Build
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
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.databinding.FragmentSetChainTypeBookBinding

interface ChainSelectAddressListener {
    fun select(chainTag: String)
}

class SetChainTypeBookFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSetChainTypeBookBinding? = null
    private val binding get() = _binding!!

    private lateinit var setChainBookAdapter: SetChainBookAdapter

    private var toChain: BaseChain? = null

    private var recipientAbleChains: MutableList<BaseChain?>? = mutableListOf()
    private var searchRecipientAbleChains: MutableList<BaseChain?>? = mutableListOf()

    companion object {
        @JvmStatic
        fun newInstance(
            toChain: BaseChain?, listener: ChainSelectAddressListener
        ): SetChainTypeBookFragment {
            val args = Bundle().apply {
                putParcelable("toChain", toChain)
            }
            val fragment = SetChainTypeBookFragment()
            fragment.arguments = args
            fragment.chainSelectAddressListener = listener
            return fragment
        }
    }

    private var chainSelectAddressListener: ChainSelectAddressListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetChainTypeBookBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initSearchView()
    }

    private fun initView() {
        binding.apply {
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

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("toChain", BaseChain::class.java)?.let { toChain = it }
            } else {
                (arguments?.getParcelable("toChain") as? BaseChain)?.let {
                    toChain = it
                }
            }

            recipientAbleChains = allChains().filter { it.isDefault }.toMutableList()
            recipientAbleChains?.add(0, null)
            recipientAbleChains?.let { searchRecipientAbleChains?.addAll(it) }
            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            setChainBookAdapter = SetChainBookAdapter(toChain)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = setChainBookAdapter
            setChainBookAdapter.submitList(searchRecipientAbleChains)

            setChainBookAdapter.setOnItemClickListener {
                chainSelectAddressListener?.select(it)
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
                    searchRecipientAbleChains?.clear()
                    recipientAbleChains?.let {
                        if (StringUtils.isEmpty(newText)) {
                            searchRecipientAbleChains?.addAll(it)
                        } else {
                            newText?.let { searchTxt ->
                                searchRecipientAbleChains?.addAll(it.filter { chain ->
                                    chain?.getChainName()
                                        ?.contains(searchTxt, ignoreCase = true) == true
                                })
                            }
                        }
                        setChainBookAdapter.notifyDataSetChanged()
                    }
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