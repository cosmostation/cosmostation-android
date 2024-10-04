package wannabit.io.cosmostaion.ui.main

import android.app.Activity
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentTokenEditBinding

interface TokenEditListener {
    fun edit(displayErc20Tokens: MutableList<String>)
}

class TokenEditFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentTokenEditBinding? = null
    private val binding get() = _binding!!

    private lateinit var fromChain: BaseChain
    private var allErc20Tokens: MutableList<Token>? = mutableListOf()
    private var displayErc20Tokens: MutableList<String>? = mutableListOf()

    private var searchErc20Tokens: MutableList<Token> = mutableListOf()

    private lateinit var tokenEditAdapter: TokenEditAdapter

    companion object {
        @JvmStatic
        fun newInstance(
            fromChain: BaseChain,
            allErc20Tokens: MutableList<Token>,
            displayErc20Tokens: MutableList<String>,
            listener: TokenEditListener
        ): TokenEditFragment {
            val args = Bundle().apply {
                putParcelable("fromChain", fromChain)
                putParcelableArrayList("allErc20Tokens", ArrayList(allErc20Tokens))
                putStringArrayList("displayErc20Tokens", ArrayList(displayErc20Tokens))
            }
            val fragment = TokenEditFragment()
            fragment.arguments = args
            fragment.tokenEditListener = listener
            return fragment
        }
    }

    private var tokenEditListener: TokenEditListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTokenEditBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupRatio(bottomSheetDialog)
        }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
        initSearchView()
//        view.viewTreeObserver.addOnGlobalLayoutListener(object :
//            ViewTreeObserver.OnGlobalLayoutListener {
//            override fun onGlobalLayout() {
//                calculateBottomSheetMargin()
//                view.viewTreeObserver.removeOnGlobalLayoutListener(this)
//            }
//        })
    }

//    private fun calculateBottomSheetMargin() {
//        dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
//            ?.let { bottomSheet ->
//                val location = intArrayOf(0, 0)
//                bottomSheet.getLocationOnScreen(location)
//
//                val params = binding.root.layoutParams as? FrameLayout.LayoutParams
//                params?.let {
//                    it.bottomMargin = location[1] - 90
//                    binding.root.layoutParams = it
//                }
//            }
//    }

    private fun initView() {
//        val behavior =
//            BottomSheetBehavior.from(dialog!!.findViewById(com.google.android.material.R.id.design_bottom_sheet))
//        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//            override fun onStateChanged(bottomSheet: View, newState: Int) {}
//
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                calculateBottomSheetMargin()
//            }
//        })

        binding.apply {
            selectTitle.text = getString(R.string.title_edit_token_list)
            arguments?.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getParcelable("fromChain", BaseChain::class.java)?.let {
                        fromChain = it
                    }

                } else {
                    (getParcelable("fromChain") as? BaseChain)?.let {
                        fromChain = it
                    }
                }
                allErc20Tokens = getParcelableArrayList("allErc20Tokens")
                displayErc20Tokens = getStringArrayList("displayErc20Tokens")
                allErc20Tokens?.let { searchErc20Tokens.addAll(it) }
            }
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.apply {
            tokenEditAdapter = TokenEditAdapter(displayErc20Tokens)
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = tokenEditAdapter
            tokenEditAdapter.submitList(searchErc20Tokens)

            tokenEditAdapter.setOnItemClickListener { selectDisplayTokens ->
                displayErc20Tokens = selectDisplayTokens
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
                    searchErc20Tokens.clear()

                    if (StringUtils.isEmpty(newText)) {
                        allErc20Tokens?.let { searchErc20Tokens.addAll(it) }

                    } else {
                        newText?.let { searchTxt ->
                            allErc20Tokens?.let { tokens ->
                                searchErc20Tokens.addAll(tokens.filter {
                                    it.symbol.contains(searchTxt, ignoreCase = true)
                                })
                            }
                        }
                    }
                    tokenEditAdapter.notifyDataSetChanged()
                    return true
                }
            })
        }
    }

    private fun setUpClickAction() {
        binding.btnConfirm.setOnClickListener {
            BaseData.baseAccount?.let { account ->
                displayErc20Tokens?.let { tokenList ->
                    Prefs.setDisplayErc20s(account.id, fromChain.tag, tokenList)
                    tokenEditListener?.edit(tokenList)
                }
            }
            dismiss()
        }
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as View
        val behavior = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = getBottomSheetDialogDefaultHeight()
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 13 / 16
    }

    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}