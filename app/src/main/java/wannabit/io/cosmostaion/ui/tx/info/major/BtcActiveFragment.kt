package wannabit.io.cosmostaion.ui.tx.info.major

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.fetcher.FinalityProvider
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.toHex
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.databinding.FragmentSuiActiveBinding

class BtcActiveFragment : Fragment() {

    private var _binding: FragmentSuiActiveBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var btcActiveInfoAdapter: BtcActiveInfoAdapter

    private var activeList: MutableList<JsonObject> = mutableListOf()

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): BtcActiveFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = BtcActiveFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuiActiveBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        refreshData()
        observeViewModels()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", BaseChain::class.java)
                ?.let { selectedChain = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                selectedChain = it
            }
        }

        updateView()
    }

    private fun updateView() {
        binding.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                refresher.isRefreshing = false
                activeList.clear()
                (selectedChain as ChainBitCoin86).btcFetcher?.btcStakingData?.get("data")?.asJsonArray?.forEach { data ->
                    if (data.asJsonObject["state"].asString == "ACTIVE") {
                        activeList.add(data.asJsonObject)
                    }
                }
                val providers = (selectedChain as ChainBitCoin86).btcFetcher?.btcFinalityProviders
                    ?: mutableListOf()

                val matchedPairs: List<Pair<JsonObject, FinalityProvider>> =
                    activeList.mapNotNull { active ->
                        val pkHex =
                            active["finality_provider_btc_pks_hex"]?.asJsonArray?.firstOrNull()?.asString

                        val matchedProvider = providers.firstOrNull { provider ->
                            provider.provider.btcPk.toByteArray().toHex() == pkHex
                        }

                        if (matchedProvider != null) {
                            Pair(active, matchedProvider)
                        } else {
                            null
                        }
                    }

                withContext(Dispatchers.Main) {
                    if (activeList.isEmpty()) {
                        emptyStake.visibility = View.VISIBLE
                        recycler.visibility = View.GONE

                    } else {
                        btcActiveInfoAdapter = BtcActiveInfoAdapter(selectedChain)
                        recycler.setHasFixedSize(true)
                        recycler.layoutManager = LinearLayoutManager(requireContext())
                        recycler.adapter = btcActiveInfoAdapter

                        emptyStake.visibility = View.GONE
                        recycler.visibility = View.VISIBLE

                        btcActiveInfoAdapter.submitList(matchedPairs)
                        if (::btcActiveInfoAdapter.isInitialized) {
                            btcActiveInfoAdapter.setOnItemClickListener { staked ->
//                                handleOneClickWithDelay(
//                                    SuiUnStakingFragment(
//                                        selectedChain, staked
//                                    )
//                                )
                            }
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
                    ApplicationViewModel.shared.loadChainData(
                        selectedChain, account.id, isRefresh = true
                    )
                }
            }
        }
    }

    private fun observeViewModels() {
        ApplicationViewModel.shared.refreshStakingInfoFetchedResult.observe(viewLifecycleOwner) { tag ->
            if (selectedChain.tag == tag) {
                ApplicationViewModel.shared.notifyRefreshEvent()
                updateView()
            }
        }

//        ApplicationViewModel.shared.txFetchedResult.observe(viewLifecycleOwner) { tag ->
//            if (selectedChain.tag == tag) {
//                ApplicationViewModel.shared.notifySuiTxEvent()
//                updateView()
//            }
//        }
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