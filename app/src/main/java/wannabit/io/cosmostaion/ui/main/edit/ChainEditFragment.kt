package wannabit.io.cosmostaion.ui.main.edit

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.FragmentChainEditBinding
import wannabit.io.cosmostaion.ui.main.DashboardFragment

class ChainEditFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentChainEditBinding? = null
    private val binding get() = _binding!!

    private lateinit var chainEditAdapter: ChainEditAdapter

    private var baseAccount: BaseAccount? = null
    private var displayChainLines: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChainEditBinding.inflate(layoutInflater, container, false)
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
        initData()
        clickAction()
    }

    private fun initView() {
        baseAccount = BaseData.baseAccount
        baseAccount?.let { account ->
            displayChainLines = Prefs.getDisplayChains(account)

            binding.apply {
                lottieAnimationView.speed = 1.3f
                checkingCnt.text = "(0 / " + account.allCosmosLineChains.size + ")"
            }
            account.initAllData()
        }
    }

    private fun initData() {
        var fetchedCnt = 0
        baseAccount?.let { account ->
            binding.apply {
                account.allCosmosLineChains.forEach { line ->
                    if (line.fetched) fetchedCnt ++
                    line.setLoadDataCallBack(object : CosmosLine.LoadDataCallback {
                        override fun onDataLoaded(isLoaded: Boolean) {
                            lifecycleScope.launch {
                                withContext(Dispatchers.Main) {
                                    if (isLoaded) {
                                        fetchedCnt++
                                        checkingCnt.text = "($fetchedCnt / ${account.allCosmosLineChains.size})"
                                    }
                                }

                                if (fetchedCnt == account.allCosmosLineChains.size) {
                                    account.sortCosmosLine()
                                    lottieAnimationView.visibility = View.GONE
                                    checkingLayout.visibility = View.GONE
                                    checkMsg.visibility = View.GONE
                                    checkPower.visibility = View.GONE

                                    editLayout.visibility = View.VISIBLE
                                    chainEditAdapter = ChainEditAdapter(requireContext(), account, displayChainLines)

                                   recycler.apply {
                                       setHasFixedSize(true)
                                       layoutManager = LinearLayoutManager(requireContext())
                                       adapter = chainEditAdapter
                                       chainEditAdapter.submitList(account.allCosmosLineChains as List<Any>?)
                                   }
                                }
                            }
                        }
                    })
                }
            }
        }
    }

    private fun clickAction() {
        val saveChainLine = mutableListOf<String>()
        binding.btnConfirm.setOnClickListener {
            baseAccount?.let { account ->
                account.allCosmosLineChains.sortWith { o1, o2 ->
                    when {
                        o1.id == "cosmos118" -> -1
                        o2.id == "cosmos118" -> 1
                        else -> {
                            when {
                                o1.allAssetValue() > o2.allAssetValue() -> -1
                                o1.allAssetValue() < o2.allAssetValue() -> 1
                                else -> 0
                            }
                        }
                    }
                }
                account.allCosmosLineChains.forEach { line ->
                    if (displayChainLines.contains(line.id)) {
                        saveChainLine.add(line.id)
                    }
                }
                Prefs.setDisplayChains(account, saveChainLine)
            }
            dialog?.dismiss()

            val transaction = parentFragmentManager.beginTransaction()
            val dashboardFragment = DashboardFragment()
            transaction.replace(R.id.fragment_container, dashboardFragment)
            transaction.commit()
        }
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as View
        val behavior = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = getBottomSheetDialogDefaultHeight()
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 19 / 20
    }

    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    override fun onStart() {
        super.onStart()

        val bottomSheetDialog = dialog as BottomSheetDialog
        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

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
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }
            })
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}