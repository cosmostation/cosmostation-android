package wannabit.io.cosmostaion.ui.tx.info.major

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.SUI_FEE_STAKE
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.chain.majorClass.SUI_MIN_STAKE
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.databinding.FragmentSuiStakingInfoBinding
import wannabit.io.cosmostaion.ui.tx.step.major.SuiStakingFragment
import wannabit.io.cosmostaion.ui.tx.step.major.SuiUnStakingFragment
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import java.math.BigDecimal
import java.util.Date
import java.util.Timer
import java.util.TimerTask

class SuiStakingInfoFragment : Fragment() {

    private var _binding: FragmentSuiStakingInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var suiStakingInfoAdapter: SuiStakingInfoAdapter

    private lateinit var selectedChain: BaseChain

    private var epoch: Long = 0
    private var epochStartTimestampMs: Long = 0
    private var epochDurationMs: Long = 0
    private val timer: Timer = Timer()
    private var stakedList: MutableList<Pair<String, JsonObject>> = mutableListOf()
    private var displayStakedList: MutableList<Pair<String, JsonObject>> = mutableListOf()

    private var isClickable = true
    private var isActiveSelected = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): SuiStakingInfoFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = SuiStakingInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuiStakingInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        refreshData()
        observeViewModels()
        setUpClickAction()
    }

    private fun initData() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedChain", BaseChain::class.java)
                    ?.let { selectedChain = it }

            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
            }

            (selectedChain as ChainSui).suiFetcher()?.let { fetcher ->
                epoch = fetcher.suiSystem["result"].asJsonObject["epoch"].asLong
                epochStartTimestampMs =
                    fetcher.suiSystem["result"].asJsonObject["epochStartTimestampMs"].asLong
                epochDurationMs = fetcher.suiSystem["result"].asJsonObject["epochDurationMs"].asLong
                currentEpoch.text = "#$epoch"
            }

            epochView.setBackgroundResource(R.drawable.item_bg)
            tabLayout.addTab(tabLayout.newTab().setText("Active"))
            tabLayout.addTab(tabLayout.newTab().setText("Pending"))

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.text.toString()) {
                        "Active" -> {
                            isActiveSelected = true
                            val tempDisplayStakedList =
                                displayStakedList.filter { it.second["status"].asString != "Pending" }
                            if (tempDisplayStakedList.isEmpty()) {
                                emptyStake.visibility = View.VISIBLE
                                recycler.visibility = View.GONE
                            } else {
                                emptyStake.visibility = View.GONE
                                recycler.visibility = View.VISIBLE
                                suiStakingInfoAdapter.submitList(tempDisplayStakedList)

                                if (::suiStakingInfoAdapter.isInitialized) {
                                    suiStakingInfoAdapter.setOnItemClickListener { staked ->
                                        handleOneClickWithDelay(
                                            SuiUnStakingFragment(
                                                selectedChain, staked
                                            )
                                        )
                                    }
                                }
                            }
                        }

                        else -> {
                            isActiveSelected = false
                            val tempDisplayStakedList =
                                displayStakedList.filter { it.second["status"].asString == "Pending" }
                            if (tempDisplayStakedList.isEmpty()) {
                                emptyStake.visibility = View.VISIBLE
                                recycler.visibility = View.GONE
                            } else {
                                emptyStake.visibility = View.GONE
                                recycler.visibility = View.VISIBLE
                                suiStakingInfoAdapter.submitList(tempDisplayStakedList)

                                if (::suiStakingInfoAdapter.isInitialized) {
                                    suiStakingInfoAdapter.setOnItemClickListener { _ ->
                                        requireActivity().makeToast(R.string.error_pending)
                                        return@setOnItemClickListener
                                    }
                                }
                            }
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })

            updateTime()
            updateView()
        }
    }

    private fun updateTime() {
        timer.schedule(object : TimerTask() {
            override fun run() {
                if (isAdded) {
                    requireActivity().runOnUiThread {
                        val endEpoch = epochStartTimestampMs + epochDurationMs
                        val current = Date().time
                        if (endEpoch > current) {
                            val remain = (endEpoch - current) / 1000
                            val hour = remain / 3600
                            val minute = remain % 3600 / 60
                            val second = remain % 60
                            binding.nextReward.text =
                                String.format("%02d : %02d : %02d", hour, minute, second)

                        } else {
                            timer.cancel()
                        }
                    }
                }
            }
        }, 0, 1000)
    }

    private fun updateView() {
        binding.apply {
            displayStakedList.clear()
            stakedList.clear()
            (selectedChain as ChainSui).suiFetcher()?.let { fetcher ->
                fetcher.suiStakedList.forEach { suiStaked ->
                    suiStaked["stakes"].asJsonArray.forEach { stakes ->
                        stakedList.add(
                            Pair(
                                suiStaked["validatorAddress"].asString, stakes.asJsonObject
                            )
                        )
                    }
                }
            }
            stakedList.sortByDescending {
                it.second["stakeRequestEpoch"].asLong
            }
            displayStakedList.addAll(stakedList)
            refresher.isRefreshing = false

            if (stakedList.isEmpty()) {
                emptyStake.visibility = View.VISIBLE
                recycler.visibility = View.GONE
            } else {
                emptyStake.visibility = View.GONE
                recycler.visibility = View.VISIBLE

                suiStakingInfoAdapter = SuiStakingInfoAdapter(selectedChain)
                recycler.setHasFixedSize(true)
                recycler.layoutManager = LinearLayoutManager(requireContext())
                recycler.adapter = suiStakingInfoAdapter

                if (isActiveSelected) {
                    suiStakingInfoAdapter.submitList(displayStakedList.filter { it.second["status"].asString != "Pending" })
                    if (::suiStakingInfoAdapter.isInitialized) {
                        suiStakingInfoAdapter.setOnItemClickListener { staked ->
                            handleOneClickWithDelay(
                                SuiUnStakingFragment(
                                    selectedChain, staked
                                )
                            )
                        }
                    }

                } else {
                    suiStakingInfoAdapter.submitList(displayStakedList.filter { it.second["status"].asString == "Pending" })
                    if (::suiStakingInfoAdapter.isInitialized) {
                        suiStakingInfoAdapter.setOnItemClickListener { _ ->
                            requireActivity().makeToast(R.string.error_pending)
                            return@setOnItemClickListener
                        }
                    }
                }
            }
        }
    }

    private fun refreshData() {
        binding.refresher.setOnRefreshListener {
            if (selectedChain.fetchState == FetchState.BUSY) {
                binding.refresher.isRefreshing = false
            } else {
                BaseData.baseAccount?.let { account ->
                    selectedChain.fetchState = FetchState.IDLE
                    ApplicationViewModel.shared.loadSuiData(account.id, selectedChain, true, true)
                }
            }
        }
    }

    private fun observeViewModels() {
        ApplicationViewModel.shared.refreshStakingInfoFetchedResult.observe(viewLifecycleOwner) { tag ->
            if (selectedChain.tag == tag) {
                updateView()
            }
        }

        ApplicationViewModel.shared.txFetchedResult.observe(viewLifecycleOwner) { tag ->
            if (selectedChain.tag == tag) {
                updateView()
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            btnInfo.setOnClickListener {
                handleOneClickWithDelay(SuiInfoFragment.newInstance(selectedChain))
            }

            btnStake.setOnClickListener {
                (selectedChain as ChainSui).suiFetcher()?.let { fetcher ->
                    val suiBalance = fetcher.suiBalanceAmount(SUI_MAIN_DENOM) ?: BigDecimal.ZERO
                    if (suiBalance < SUI_MIN_STAKE.toBigDecimal()
                            .add(SUI_FEE_STAKE.toBigDecimal())
                    ) {
                        requireActivity().showToast(
                            view, R.string.error_not_enough_sui_stake, false
                        )
                        return@setOnClickListener
                    }
                }
                handleOneClickWithDelay(SuiStakingFragment.newInstance(selectedChain))
            }
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