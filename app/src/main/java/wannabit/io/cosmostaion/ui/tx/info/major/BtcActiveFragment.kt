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
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.fetcher.FinalityProvider
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.databinding.FragmentSuiActiveBinding
import wannabit.io.cosmostaion.ui.tx.option.general.BtcStakeOptionFragment

class BtcActiveFragment : Fragment() {

    private var _binding: FragmentSuiActiveBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var btcActiveInfoAdapter: BtcActiveInfoAdapter

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
            refresher.isRefreshing = false
            if ((selectedChain as ChainBitCoin86).btcFetcher?.btcActiveStakingData?.isEmpty() == true) {
                emptyLayout.visibility = View.VISIBLE
                recycler.visibility = View.GONE

            } else {
                btcActiveInfoAdapter = BtcActiveInfoAdapter(selectedChain, selectClickAction)
                recycler.setHasFixedSize(true)
                recycler.layoutManager = LinearLayoutManager(requireContext())
                recycler.adapter = btcActiveInfoAdapter

                emptyLayout.visibility = View.GONE
                recycler.visibility = View.VISIBLE

                btcActiveInfoAdapter.submitList((selectedChain as ChainBitCoin86).btcFetcher?.btcActiveStakingData)
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
                    ApplicationViewModel.shared.loadBtcData(
                        account.id, selectedChain as ChainBitCoin86, isRefresh = true
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

        ApplicationViewModel.shared.txFetchedResult.observe(viewLifecycleOwner) { tag ->
            if (selectedChain.tag == tag) {
                ApplicationViewModel.shared.notifySuiTxEvent()
                updateView()
            }
        }
    }

    private val selectClickAction = object : BtcActiveInfoAdapter.ClickListener {
        override fun selectStakingAction(staked: Pair<JsonObject, FinalityProvider>) {
            handleOneClickWithDelay(
                BtcStakeOptionFragment(selectedChain, staked)
            )
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