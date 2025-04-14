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
import wannabit.io.cosmostaion.ui.tx.genTx.major.BtcWithdrawFragment

class BtcNotActiveFragment : Fragment() {

    private var _binding: FragmentSuiActiveBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var btcNotActiveInfoAdapter: BtcNotActiveInfoAdapter

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): BtcNotActiveFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = BtcNotActiveFragment()
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
            (selectedChain as ChainBitCoin86).apply {
                refresher.isRefreshing = false
                if (btcFetcher?.btcUnBondingStakingData?.isEmpty() == true && btcFetcher?.btcWithdrawAbleStakingData?.isEmpty() == true) {
                    emptyLayout.visibility = View.VISIBLE
                    recycler.visibility = View.GONE

                } else {
                    btcNotActiveInfoAdapter = BtcNotActiveInfoAdapter(
                        selectedChain,
                        btcFetcher?.btcUnBondingStakingData,
                        btcFetcher?.btcWithdrawAbleStakingData,
                        selectClickAction
                    )
                    recycler.setHasFixedSize(true)
                    recycler.layoutManager = LinearLayoutManager(requireContext())
                    recycler.adapter = btcNotActiveInfoAdapter

                    emptyLayout.visibility = View.GONE
                    recycler.visibility = View.VISIBLE

                    btcNotActiveInfoAdapter.submitList(
                        (btcFetcher?.btcUnBondingStakingData
                            ?: mutableListOf()) + (btcFetcher?.btcWithdrawAbleStakingData
                            ?: mutableListOf())
                    )
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
                    ApplicationViewModel.shared.loadBtcData(
                        account.id, selectedChain as ChainBitCoin86, isRefresh = true
                    )
                }
            }
        }
    }

    private val selectClickAction = object : BtcNotActiveInfoAdapter.ClickListener {
        override fun selectWithdrawAction(staked: Pair<JsonObject, FinalityProvider>?) {
            handleOneClickWithDelay(
                BtcWithdrawFragment(selectedChain, staked)
            )
        }
    }

    private fun observeViewModels() {
        ApplicationViewModel.shared.notifySuiTxResult.observe(viewLifecycleOwner) {
            updateView()
        }

        ApplicationViewModel.shared.notifyRefreshResult.observe(viewLifecycleOwner) {
            updateView()
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