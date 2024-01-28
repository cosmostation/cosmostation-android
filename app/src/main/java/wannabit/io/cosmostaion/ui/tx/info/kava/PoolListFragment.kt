package wannabit.io.cosmostaion.ui.tx.info.kava

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kava.swap.v1beta1.QueryProto
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.usdxAmount
import wannabit.io.cosmostaion.data.repository.chain.KavaRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentPoolListBinding
import wannabit.io.cosmostaion.ui.option.tx.kava.PoolOptionFragment
import wannabit.io.cosmostaion.ui.tx.step.kava.PoolActionFragment
import wannabit.io.cosmostaion.ui.tx.step.kava.PoolActionType
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModelProviderFactory

class PoolListFragment(
    private val selectedChain: CosmosLine,
) : Fragment() {

    private var _binding: FragmentPoolListBinding? = null
    private val binding get() = _binding!!

    private lateinit var kavaViewModel: KavaViewModel

    private lateinit var poolListAdapter: PoolListAdapter

    private var swapMyList: MutableList<QueryProto.PoolResponse> = mutableListOf()
    private var swapOtherList: MutableList<QueryProto.PoolResponse> = mutableListOf()
    private var swapMyDeposit: MutableList<QueryProto.DepositResponse>? = mutableListOf()

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPoolListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        setUpSwapDataObserve()
        setUpClickAction()
    }

    private fun initViewModel() {
        val kavaRepository = KavaRepositoryImpl()
        val kavaViewModelProviderFactory = KavaViewModelProviderFactory(kavaRepository)
        kavaViewModel =
            ViewModelProvider(this, kavaViewModelProviderFactory)[KavaViewModel::class.java]

        kavaViewModel.swapData(getChannel(selectedChain), selectedChain.address)
    }

    private fun setUpSwapDataObserve() {
        binding.apply {
            kavaViewModel.swapData.observe(viewLifecycleOwner) { response ->
                response?.let { swapData ->
                    loading.visibility = View.GONE

                    val tempSwapMyDeposits = swapData.swapMyDeposits?.toMutableList()
                    tempSwapMyDeposits?.sortWith { o1, o2 ->
                        when {
                            o1.usdxAmount() > o2.usdxAmount() -> -1
                            else -> 1
                        }
                    }
                    swapMyDeposit = tempSwapMyDeposits

                    val tempSwapPools = swapData.swapPools?.toMutableList()
                    tempSwapPools?.sortWith { o1, o2 ->
                        when {
                            o1.usdxAmount() > o2.usdxAmount() -> -1
                            else -> 1
                        }
                    }
                    tempSwapPools?.forEach { pool ->
                        if (!pool.name.contains("B448C0CA358B958301D328CCDC5D5AD642FC30A6D3AE106FF721DB315F3DDE5C") && !pool.name.contains(
                                "B8AF5D92165F35AB31F3FC7C7B444B9D240760FA5D406C49D24862BD0284E395"
                            )
                        ) {
                            if ((swapMyDeposit?.filter { it.poolId == pool.name }?.size ?: 0) > 0) {
                                swapMyList.add(pool)
                            } else {
                                swapOtherList.add(pool)
                            }
                        }
                    }

                    initRecyclerView()
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            poolListAdapter = PoolListAdapter(
                requireContext(),
                selectedChain,
                swapMyList,
                swapOtherList,
                swapMyDeposit,
                poolClickAction
            )
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = poolListAdapter
            poolListAdapter.submitList(swapMyList + swapOtherList)
        }
    }

    private fun setUpClickAction() {
        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private val poolClickAction = object : PoolListAdapter.ClickListener {
        override fun myPoolSelect(poolId: String, deposit: QueryProto.DepositResponse) {
            swapMyList.firstOrNull { it.name == poolId }?.let { swapPool ->
                if (isClickable) {
                    isClickable = false

                    PoolOptionFragment(
                        selectedChain, swapPool, deposit, poolOptionClickAction
                    ).show(
                        requireActivity().supportFragmentManager,
                        PoolOptionFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 300)
                }
            }
        }

        override fun otherPoolSelect(name: String) {
            swapOtherList.firstOrNull { it.name == name }?.let { swapPool ->
                PoolActionFragment(
                    selectedChain, PoolActionType.DEPOSIT, swapPool, null
                ).show(
                    requireActivity().supportFragmentManager, PoolActionFragment::class.java.name
                )
            }
        }
    }

    private val poolOptionClickAction = object : PoolClickListener {
        override fun poolDeposit(swapPool: QueryProto.PoolResponse) {
            handleOneClickWithDelay(
                PoolActionFragment(
                    selectedChain, PoolActionType.DEPOSIT, swapPool, null
                )
            )
        }

        override fun poolWithdraw(
            swapPool: QueryProto.PoolResponse, deposit: QueryProto.DepositResponse
        ) {
            PoolActionFragment(
                selectedChain, PoolActionType.WITHDRAW, swapPool, deposit
            ).show(
                requireActivity().supportFragmentManager, PoolActionFragment::class.java.name
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
            }, 1000)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

interface PoolClickListener {
    fun poolDeposit(swapPool: QueryProto.PoolResponse)
    fun poolWithdraw(swapPool: QueryProto.PoolResponse, deposit: QueryProto.DepositResponse)
}