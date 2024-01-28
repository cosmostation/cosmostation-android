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
import com.cosmos.base.v1beta1.CoinProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kava.hard.v1beta1.HardProto
import com.kava.pricefeed.v1beta1.QueryProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.getLTV
import wannabit.io.cosmostaion.common.hardMoneyMarket
import wannabit.io.cosmostaion.common.kavaOraclePrice
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.spotMarketId
import wannabit.io.cosmostaion.data.repository.chain.KavaRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentLendListBinding
import wannabit.io.cosmostaion.ui.option.tx.kava.MintOptionFragment
import wannabit.io.cosmostaion.ui.tx.step.kava.LendActionFragment
import wannabit.io.cosmostaion.ui.tx.step.kava.LendActionType
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModelProviderFactory
import wannabit.io.cosmostaion.ui.viewmodel.chain.LendingData
import java.math.BigDecimal
import java.math.RoundingMode

class LendListFragment(
    private val selectedChain: CosmosLine, private val priceFeed: QueryProto.QueryPricesResponse?
) : Fragment() {

    private var _binding: FragmentLendListBinding? = null
    private val binding get() = _binding!!

    private lateinit var kavaViewModel: KavaViewModel

    private lateinit var lendListAdapter: LendListAdapter

    private var lendMoneyMarkets: MutableList<HardProto.MoneyMarket> = mutableListOf()
    private var lendMyDeposits: MutableList<CoinProto.Coin> = mutableListOf()
    private var lendMyBorrows: MutableList<CoinProto.Coin> = mutableListOf()
    private var lendReserve: MutableList<CoinProto.Coin>? = mutableListOf()

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLendListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        setUpLendingDataObserve()
        setUpClickAction()
    }

    private fun initViewModel() {
        val kavaRepository = KavaRepositoryImpl()
        val kavaViewModelProviderFactory = KavaViewModelProviderFactory(kavaRepository)
        kavaViewModel =
            ViewModelProvider(this, kavaViewModelProviderFactory)[KavaViewModel::class.java]

        kavaViewModel.lendingData(getChannel(selectedChain), selectedChain.address)
    }

    private fun setUpLendingDataObserve() {
        binding.apply {
            kavaViewModel.lendingData.observe(viewLifecycleOwner) { response ->
                response?.let { lendingData ->
                    loading.visibility = View.GONE

                    if (lendingData.lendingMyDeposits?.isNotEmpty() == true) {
                        lendMyDeposits = lendingData.lendingMyDeposits[0].amountList
                    }
                    if (lendingData.lendingMyBorrows?.isNotEmpty() == true) {
                        lendMyBorrows = lendingData.lendingMyBorrows[0].amountList
                    }
                    lendReserve = lendingData.lendingReserve

                    val tempMoneyMarketList =
                        lendingData.lendingParam?.moneyMarketsList?.toMutableList()
                    tempMoneyMarketList?.sortWith { o1, o2 ->
                        val denom0 = o1.denom
                        val denom1 = o2.denom
                        when {
                            lendMyDeposits.any { it.denom == denom0 } || lendMyBorrows.any { it.denom == denom0 } -> -1
                            lendMyDeposits.any { it.denom == denom1 } || lendMyBorrows.any { it.denom == denom1 } -> 1
                            else -> 1
                        }
                    }
                    tempMoneyMarketList?.let { lendMoneyMarkets = it }

                    initRecyclerView(lendingData)
                }
            }
        }
    }

    private fun initRecyclerView(lendingData: LendingData) {
        binding.recycler.apply {
            lendListAdapter = LendListAdapter(
                requireContext(),
                lendMoneyMarkets,
                priceFeed,
                lendMyDeposits,
                lendMyBorrows,
                lendClickAction
            )
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = lendListAdapter
            lendListAdapter.submitList(lendingData.lendingRates)
        }
    }

    private fun setUpClickAction() {
        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private val lendClickAction = object : LendListAdapter.ClickListener {
        override fun lendOption(denom: String?) {
            if (isClickable) {
                isClickable = false

                MintOptionFragment(selectedChain, null, denom, null, lendOptionClickAction).show(
                    requireActivity().supportFragmentManager, MintOptionFragment::class.java.name
                )

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 300)
            }
        }
    }

    private val lendOptionClickAction = object : LendClickListener {
        override fun lendAction(denom: String?, lendActionType: LendActionType) {
            if (lendActionType == LendActionType.WITHDRAW) {
                val depositAmount =
                    lendMyDeposits.firstOrNull { it.denom == denom }?.amount?.toBigDecimal()
                        ?: BigDecimal.ZERO
                if (depositAmount <= BigDecimal.ZERO) {
                    requireContext().makeToast(R.string.error_not_enough_to_withdraw)
                    return
                }

            } else if (lendActionType == LendActionType.BORROW) {
                val borrowableAmount = remainBorrowAbleAmount(denom)
                if (borrowableAmount < BigDecimal.ZERO) {
                    requireContext().makeToast(R.string.error_no_borrowable_asset)
                    return
                }

            } else if (lendActionType == LendActionType.REPAY) {
                val borrowedAmount =
                    lendMyBorrows.firstOrNull { it.denom == denom }?.amount?.toBigDecimal()
                        ?: BigDecimal.ZERO
                if (borrowedAmount <= BigDecimal.ZERO) {
                    requireContext().makeToast(R.string.error_no_repay_asset)
                    return
                }
            }

            handleOneClickWithDelay(
                LendActionFragment(
                    selectedChain,
                    lendActionType,
                    lendMyDeposits,
                    lendMyBorrows,
                    lendMoneyMarkets.hardMoneyMarket(denom),
                    BigDecimal.ZERO
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
            }, 1000)
        }
    }

    private fun remainBorrowAbleAmount(denom: String?): BigDecimal {
        var totalLTVValue = BigDecimal.ZERO
        var totalBorrowedValue = BigDecimal.ZERO
        lendMyDeposits.forEach { coin ->
            val decimal = BaseData.assets?.firstOrNull { it.denom == coin.denom }?.decimals ?: 6
            val LTV = lendMoneyMarkets.getLTV(coin.denom)
            val marketIdPrice =
                priceFeed?.kavaOraclePrice(lendMoneyMarkets.spotMarketId(coin.denom))
            val depositValue =
                coin.amount.toBigDecimal().movePointLeft(decimal).multiply(marketIdPrice)
                    .setScale(12, RoundingMode.DOWN)
            val ltvValue = depositValue.multiply(LTV)
            totalLTVValue = totalLTVValue.add(ltvValue)
        }
        lendMyBorrows.forEach { coin ->
            val decimal = BaseData.assets?.firstOrNull { it.denom == coin.denom }?.decimals ?: 6
            val marketIdPrice =
                priceFeed?.kavaOraclePrice(lendMoneyMarkets.spotMarketId(coin.denom))
            val borrowValue =
                coin.amount.toBigDecimal().movePointLeft(decimal).multiply(marketIdPrice)
                    .setScale(12, RoundingMode.DOWN)
            totalBorrowedValue = totalBorrowedValue.add(borrowValue)
        }

        val oraclePrice = priceFeed?.kavaOraclePrice(lendMoneyMarkets.spotMarketId(denom))
        val decimal = BaseData.assets?.firstOrNull { it.denom == denom }?.decimals ?: 6
        val totalBorrowAbleValue = totalLTVValue.subtract(totalBorrowedValue).max(BigDecimal.ZERO)
        val totalBorrowAbleAmount = totalBorrowAbleValue.movePointRight(decimal)
            .divide(oraclePrice, decimal, RoundingMode.DOWN)

        denom?.let {
            var reserveAmount = BigDecimal.ZERO
            val moduleAmount = selectedChain.balanceAmount(denom)
            lendReserve?.forEach { reserve ->
                if (reserve.denom == denom) {
                    reserveAmount = reserve.amount.toBigDecimal()
                }
            }
            val systemBorrowableAmount: BigDecimal?
            val moduleBorrowable = moduleAmount.subtract(reserveAmount)
            systemBorrowableAmount =
                if (lendMoneyMarkets.hardMoneyMarket(denom)?.borrowLimit?.hasMaxLimit == true) {
                    lendMoneyMarkets.hardMoneyMarket(denom)?.borrowLimit?.maximumLimit?.toBigDecimal()
                } else {
                    moduleBorrowable
                }
            return totalBorrowAbleAmount.min(systemBorrowableAmount)

        } ?: run {
            return BigDecimal.ZERO
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

interface LendClickListener {
    fun lendAction(denom: String?, lendActionType: LendActionType)
}