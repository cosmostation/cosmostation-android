package wannabit.io.cosmostaion.ui.tx.info

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
import com.cosmos.staking.v1beta1.StakingProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zrchain.validation.HybridValidationProto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.cosmosClass.ChainZenrock
import wannabit.io.cosmostaion.chain.testnetClass.ChainInitiaTestnet
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.databinding.FragmentStakingInfoBinding
import wannabit.io.cosmostaion.ui.tx.option.general.StakingOptionFragment

class UnStakingInfoFragment : Fragment() {

    private var _binding: FragmentStakingInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var stakingInfoAdapter: StakingInfoAdapter

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): UnStakingInfoFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = UnStakingInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStakingInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setUpStakeInfo()
        refreshData()
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

        binding.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                when (selectedChain) {
                    is ChainInitiaTestnet -> {
                        val validators =
                            (selectedChain as ChainInitiaTestnet).initiaFetcher()?.initiaValidators
                                ?: mutableListOf()
                        val unBondings =
                            (selectedChain as ChainInitiaTestnet).initiaFetcher()?.initiaUnbondings?.flatMap { unBonding ->
                                unBonding.entriesList.map { entry ->
                                    InitiaUnBondingEntry(unBonding.validatorAddress, entry)
                                }
                            }?.sortedBy { it.entry?.creationHeight }?.toMutableList()
                                ?: mutableListOf()

                        withContext(Dispatchers.Main) {
                            refresher.isRefreshing = false
                            if (unBondings.isNotEmpty()) {
                                recycler.visibility = View.VISIBLE
                                emptyLayout.visibility = View.GONE
                                stakingInfoAdapter = StakingInfoAdapter(
                                    selectedChain,
                                    initiaValidators = validators,
                                    initiaUnBondings = unBondings,
                                    optionType = OptionType.UNSTAKE,
                                    listener = selectClickAction
                                )
                                recycler.setHasFixedSize(true)
                                recycler.layoutManager = LinearLayoutManager(requireContext())
                                recycler.adapter = stakingInfoAdapter

                            } else {
                                recycler.visibility = View.GONE
                                emptyLayout.visibility = View.VISIBLE
                            }
                        }
                    }

                    is ChainZenrock -> {
                        val validators =
                            (selectedChain as ChainZenrock).zenrockFetcher()?.zenrockValidators
                                ?: mutableListOf()
                        val unBondings =
                            (selectedChain as ChainZenrock).zenrockFetcher()?.zenrockUnbondings?.flatMap { unBonding ->
                                unBonding.entriesList.map { entry ->
                                    ZenrockUnBondingEntry(unBonding.validatorAddress, entry)
                                }
                            }?.sortedBy { it.entry?.creationHeight }?.toMutableList()
                                ?: mutableListOf()

                        withContext(Dispatchers.Main) {
                            refresher.isRefreshing = false
                            if (unBondings.isNotEmpty()) {
                                recycler.visibility = View.VISIBLE
                                emptyLayout.visibility = View.GONE
                                stakingInfoAdapter = StakingInfoAdapter(
                                    selectedChain,
                                    zenrockValidators = validators,
                                    zenrockUnBondings = unBondings,
                                    optionType = OptionType.UNSTAKE,
                                    listener = selectClickAction
                                )
                                recycler.setHasFixedSize(true)
                                recycler.layoutManager = LinearLayoutManager(requireContext())
                                recycler.adapter = stakingInfoAdapter

                            } else {
                                recycler.visibility = View.GONE
                                emptyLayout.visibility = View.VISIBLE
                            }
                        }
                    }

                    else -> {
                        val validators =
                            selectedChain.cosmosFetcher?.cosmosValidators ?: mutableListOf()
                        val unBondings =
                            selectedChain.cosmosFetcher?.cosmosUnbondings?.flatMap { unBonding ->
                                unBonding.entriesList.map { entry ->
                                    UnBondingEntry(unBonding.validatorAddress, entry)
                                }
                            }?.sortedBy { it.entry?.creationHeight }?.toMutableList()
                                ?: mutableListOf()

                        withContext(Dispatchers.Main) {
                            refresher.isRefreshing = false
                            if (unBondings.isNotEmpty()) {
                                recycler.visibility = View.VISIBLE
                                emptyLayout.visibility = View.GONE
                                stakingInfoAdapter = StakingInfoAdapter(
                                    selectedChain,
                                    validators = validators,
                                    unBondings = unBondings,
                                    optionType = OptionType.UNSTAKE,
                                    listener = selectClickAction
                                )
                                recycler.setHasFixedSize(true)
                                recycler.layoutManager = LinearLayoutManager(requireContext())
                                recycler.adapter = stakingInfoAdapter

                            } else {
                                recycler.visibility = View.GONE
                                emptyLayout.visibility = View.VISIBLE
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
                        selectedChain,
                        account.id,
                        isRefresh = true
                    )
                }
            }
        }
    }

    private val selectClickAction = object : StakingInfoAdapter.ClickListener {
        override fun selectStakingAction(validator: StakingProto.Validator?) {
            handleOneClickWithDelay(
                StakingOptionFragment.newInstance(
                    selectedChain, validator = validator, optionType = OptionType.STAKE
                )
            )
        }

        override fun selectInitiaStakingAction(validator: com.initia.mstaking.v1.StakingProto.Validator?) {
            handleOneClickWithDelay(
                StakingOptionFragment.newInstance(
                    selectedChain, initiaValidator = validator, optionType = OptionType.STAKE
                )
            )
        }

        override fun selectZenrockStakingAction(validator: HybridValidationProto.ValidatorHV?) {
            handleOneClickWithDelay(
                StakingOptionFragment.newInstance(
                    selectedChain, zenrockValidator = validator, optionType = OptionType.STAKE
                )
            )
        }

        override fun selectUnStakingCancelAction(unBondingEntry: UnBondingEntry?) {
            handleOneClickWithDelay(
                StakingOptionFragment.newInstance(
                    selectedChain, unBondingEntry = unBondingEntry, optionType = OptionType.UNSTAKE
                )
            )
        }

        override fun selectInitiaUnStakingCancelAction(initiaUnBondingEntry: InitiaUnBondingEntry?) {
            handleOneClickWithDelay(
                StakingOptionFragment.newInstance(
                    selectedChain,
                    initiaUnBondingEntry = initiaUnBondingEntry,
                    optionType = OptionType.UNSTAKE
                )
            )
        }

        override fun selectZenrockUnStakingCancelAction(zenrockUnBondingEntry: ZenrockUnBondingEntry?) {
            handleOneClickWithDelay(
                StakingOptionFragment.newInstance(
                    selectedChain,
                    zenrockUnBondingEntry = zenrockUnBondingEntry,
                    optionType = OptionType.UNSTAKE
                )
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

    private fun setUpStakeInfo() {
        ApplicationViewModel.shared.notifyTxResult.observe(viewLifecycleOwner) {
            initData()
        }

        ApplicationViewModel.shared.notifyRefreshResult.observe(viewLifecycleOwner) {
            initData()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}