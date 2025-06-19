package wannabit.io.cosmostaion.ui.main.dapp

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.HorizontalScrollView
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.viewmodel.dapp.DappViewModel
import wannabit.io.cosmostaion.data.viewmodel.dapp.DappViewModelProviderFactory
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.CustomButtonBinding
import wannabit.io.cosmostaion.databinding.FragmentDappBinding
import wannabit.io.cosmostaion.ui.main.SettingType
import wannabit.io.cosmostaion.ui.main.setting.SettingBottomFragment


class DappStartFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDappBinding? = null
    private val binding get() = _binding!!

    private lateinit var dappViewModel: DappViewModel

    private lateinit var dappListAdapter: DappListAdapter

    private var ecosystems: MutableList<JsonObject> = mutableListOf()
    private var supportChains: MutableList<String>? = mutableListOf()
    private var selectedIndex: Int = 0
    private var selectedType: String = "Favorite"
    private var selectedChain: String = "All Network"

    private var searchTxt: String? = ""
    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDappBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initButtonView()
        initSearchView()
        setUpClickAction()
        setUpObserve()
    }

    private fun initView() {
        dappViewModel =
            ViewModelProvider(this, DappViewModelProviderFactory())[DappViewModel::class.java]

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

                btnFilter.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_base04
                    ), PorterDuff.Mode.SRC_IN
                )

                btnDownImg.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_base04
                    ), PorterDuff.Mode.SRC_IN
                )
            }
        }
    }

    private fun initButtonView() {
        binding.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                val manualEntries = listOf("Favorite", "All")
                val extractedTypes =
                    BaseData.ecosystems?.map { it["type"].asString }?.distinct() ?: emptyList()
                val dappTypes = manualEntries + extractedTypes

                val dappChains = BaseData.ecosystems?.flatMap { ecosystem ->
                    ecosystem["chains"].asJsonArray.map { it.asString }
                }?.toSet() ?: emptySet()
                val filteredChains =
                    allChains().filter { it.apiName in dappChains }.map { it.apiName }.toSet()
                supportChains = filteredChains.toMutableList()
                supportChains?.sortWith { o1, o2 ->
                    val o1IsTestnet = o1.contains("testnet", ignoreCase = true)
                    val o2IsTestnet = o2.contains("testnet", ignoreCase = true)

                    when {
                        o1IsTestnet && !o2IsTestnet -> 1
                        !o1IsTestnet && o2IsTestnet -> -1
                        else -> o1.compareTo(o2, ignoreCase = true)
                    }
                }
                supportChains?.add(0, "All Network")

                dappViewModel.fetchDappList()

                withContext(Dispatchers.Main) {
                    val inflater = LayoutInflater.from(requireContext())
                    dappTypes.forEachIndexed { index, type ->
                        val view = CustomButtonBinding.inflate(inflater, buttonContainer, false)
                        view.popularImg.visibleOrGone(index == 0)
                        view.popularImg.colorFilter = PorterDuffColorFilter(
                            ContextCompat.getColor(
                                requireContext(), R.color.color_base01
                            ), PorterDuff.Mode.SRC_IN
                        )
                        view.type.text = type
                        if (index == selectedIndex) {
                            view.root.setBackgroundResource(R.drawable.button_dapp_bg)
                            view.type.setTextColor(
                                ContextCompat.getColorStateList(
                                    requireContext(), R.color.color_base01
                                )
                            )
                        } else {
                            view.root.setBackgroundResource(R.drawable.button_default_bg)
                            view.type.setTextColor(
                                ContextCompat.getColorStateList(
                                    requireContext(), R.color.color_base04
                                )
                            )
                        }

                        view.root.setOnClickListener {
                            val previousView = buttonContainer.getChildAt(selectedIndex)
                            val previousBinding = CustomButtonBinding.bind(previousView)
                            previousView.setBackgroundResource(R.drawable.button_default_bg)
                            previousBinding.type.setTextColor(
                                ContextCompat.getColor(
                                    requireContext(), R.color.color_base04
                                )
                            )
                            previousBinding.popularImg.colorFilter = PorterDuffColorFilter(
                                ContextCompat.getColor(
                                    requireContext(), R.color.color_base04
                                ), PorterDuff.Mode.SRC_IN
                            )

                            view.root.setBackgroundResource(R.drawable.button_dapp_bg)
                            view.popularImg.colorFilter = PorterDuffColorFilter(
                                ContextCompat.getColor(
                                    requireContext(), R.color.color_base01
                                ), PorterDuff.Mode.SRC_IN
                            )
                            view.type.setTextColor(
                                ContextCompat.getColor(
                                    requireContext(), R.color.color_base01
                                )
                            )

                            selectedIndex = index
                            selectedType = type
                            centerButtonInScrollView(buttonScroll, view.root)
                            dappViewModel.sortByType(
                                ecosystems, selectedType, selectedChain, searchTxt
                            )
                        }
                        buttonContainer.addView(view.root)
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            binding.emptyLayout.visibility = View.GONE
            dappListAdapter = DappListAdapter(requireContext(), selectPinAction)
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = dappListAdapter

            val favorite = ecosystems.filter { ecosystem ->
                Prefs.getPinnedDapps().contains(ecosystem["id"].asInt)
            }

            if (favorite.isNotEmpty()) {
                binding.emptyLayout.visibility = View.GONE
                visibility = View.VISIBLE
                dappListAdapter.submitList(favorite)
            } else {
                binding.emptyLayout.visibility = View.VISIBLE
                visibility = View.GONE
            }

            dappListAdapter.setOnItemClickListener { ecosystem ->
                val savedTime = Prefs.getDappHideTime(ecosystem["id"].asInt)
                val currentTime = System.currentTimeMillis()
                if (currentTime >= savedTime) {
                    handleOneClickWithDelay(
                        DappDetailFragment.newInstance(
                            selectedChain,
                            ecosystem.toString(),
                            object : DappPinSelectListener {
                                override fun pinned(id: Int) {
                                    dappViewModel.pinnedByDetail(
                                        ecosystems,
                                        selectedType,
                                        selectedChain,
                                        searchTxt,
                                        id
                                    )
                                }
                            })
                    )

                } else {
                    Intent(requireActivity(), DappActivity::class.java).apply {
                        val chain = if (ecosystem["chains"].asJsonArray.size() == 1) {
                            allChains().first()
                        } else if (selectedChain.uppercase() != "All Network".uppercase()) {
                            allChains().firstOrNull { it.apiName == selectedChain }
                        } else {
                            allChains().firstOrNull { chain -> chain.apiName == ecosystem["chains"].asJsonArray.first().asString }
                        }

                        putExtra("selectedChain", chain as Parcelable)
                        putExtra("dapp", ecosystem["link"].asString)
                        startActivity(this)
                    }
                }
            }
        }
    }

    private val selectPinAction = object : DappListAdapter.PinnedListener {
        override fun select(id: Int) {
            dappViewModel.pinnedByDetail(
                ecosystems, selectedType, selectedChain, searchTxt, id
            )
        }
    }

    private fun setUpObserve() {
        dappViewModel.dappList.observe(viewLifecycleOwner) {
            ecosystems.addAll(it)
            initRecyclerView()
        }

        dappViewModel.pinnedByDetail.observe(viewLifecycleOwner) { pinnedList ->
            binding.apply {
                if (pinnedList.isNotEmpty()) {
                    dappListAdapter.submitList(pinnedList)
                    emptyLayout.visibility = View.GONE
                    recycler.visibility = View.VISIBLE

                } else {
                    emptyLayout.visibility = View.VISIBLE
                    recycler.visibility = View.GONE
                }
            }
        }

        dappViewModel.sortedBy.observe(viewLifecycleOwner) { sortList ->
            binding.apply {
                if (sortList.isNotEmpty()) {
                    dappListAdapter.submitList(sortList)
                    emptyLayout.visibility = View.GONE
                    recycler.visibility = View.VISIBLE

                } else {
                    emptyLayout.visibility = View.VISIBLE
                    recycler.visibility = View.GONE
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnFilter.setOnClickListener {
                handleOneClickWithDelay(
                    SettingBottomFragment.newInstance(null, SettingType.DAPP_SORT_OPTION)
                )

                parentFragmentManager.setFragmentResultListener(
                    "sort", this@DappStartFragment
                ) { _, bundle ->
                    Prefs.dappFilter = bundle.getInt("sort")
                    dappViewModel.sortByType(
                        ecosystems, selectedType, selectedChain, searchTxt
                    )
                }
            }

            btnChainSelect.setOnClickListener {
                handleOneClickWithDelay(
                    DappChainFragment.newInstance(supportChains,
                        selectedChain,
                        object : DappChainSelectListener {
                            override fun select(chain: String) {
                                binding.apply {
                                    if (chain == "All Network") {
                                        chainImg.setImageResource(R.drawable.icon_all_network)
                                        chainNetwork.text = chain
                                    } else {
                                        allChains().first { it.apiName == chain }
                                            .let { supportChain ->
                                                chainImg.setChainLogo(supportChain)
                                                chainNetwork.text = chain.uppercase()
                                            }
                                    }
                                    selectedChain = chain
                                    dappViewModel.sortByType(
                                        ecosystems, selectedType, selectedChain, searchTxt
                                    )
                                }
                            }
                        })
                )
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
                    searchTxt = newText
                    dappViewModel.sortByType(
                        ecosystems, selectedType, selectedChain, newText
                    )
                    return true
                }
            })
        }
    }

    private fun centerButtonInScrollView(scrollView: HorizontalScrollView, button: View) {
        val scrollViewWidth = scrollView.width
        val scrollViewHeight = scrollView.height

        val buttonX = button.left
        val buttonY = button.top
        val buttonWidth = button.width
        val buttonHeight = button.height

        val scrollToX = buttonX - (scrollViewWidth - buttonWidth) / 2
        val scrollToY = buttonY - (scrollViewHeight - buttonHeight) / 2

        scrollView.post {
            scrollView.smoothScrollTo(scrollToX, scrollToY)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupRatio(bottomSheetDialog)
        }
        return dialog
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as View
        val behavior = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = bottomSheetDialogDefaultHeight(windowHeight())
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun bottomSheetDialogDefaultHeight(windowHeight: Int): Int {
        return windowHeight * 19 / 20
    }

    private fun windowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    override fun onStart() {
        super.onStart()

        val bottomSheetDialog = dialog as BottomSheetDialog
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

        bottomSheet?.let { sheet ->
            val behavior = BottomSheetBehavior.from(sheet)
            behavior.isHideable = true

            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_HIDDEN -> dismiss()
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            behavior.state = BottomSheetBehavior.STATE_EXPANDED
                        }

                        else -> {}
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })
        }
    }

    private fun handleOneClickWithDelay(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        if (isClickable) {
            isClickable = false

            bottomSheetDialogFragment.show(
                requireActivity().supportFragmentManager, bottomSheetDialogFragment::class.java.name
            )

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 300)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}