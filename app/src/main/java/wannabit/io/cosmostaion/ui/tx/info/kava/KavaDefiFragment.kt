package wannabit.io.cosmostaion.ui.tx.info.kava

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cosmos.base.v1beta1.CoinProto
import com.kava.incentive.v1beta1.QueryProto
import com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.allIncentiveCoins
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.toMoveFragment
import wannabit.io.cosmostaion.data.repository.chain.KavaRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentKavaDefiBinding
import wannabit.io.cosmostaion.ui.tx.step.kava.ClaimIncentiveFragment
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModelProviderFactory

class KavaDefiFragment(private val selectedChain: CosmosLine) : Fragment() {

    private var _binding: FragmentKavaDefiBinding? = null
    private val binding get() = _binding!!

    private lateinit var kavaViewModel: KavaViewModel

    private var incentive: QueryProto.QueryRewardsResponse? = null
    private var priceFeed: QueryPricesResponse? = null

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKavaDefiBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
        setUpClickAction()
    }

    private fun initViewModel() {
        val kavaRepository = KavaRepositoryImpl()
        val kavaViewModelProviderFactory = KavaViewModelProviderFactory(kavaRepository)
        kavaViewModel =
            ViewModelProvider(this, kavaViewModelProviderFactory)[KavaViewModel::class.java]
    }

    private fun initView() {
        binding.apply {
            kavaViewModel.priceFeed(getChannel(selectedChain))

            loading.visibility = View.VISIBLE
            defiLayout.visibility = View.GONE
            listOf(
                incentiveView, mintView, lendView, poolView, earnView
            ).forEach { it.setBackgroundResource(R.drawable.item_bg) }

            setUpPriceFeedObserve()
            setUpIncentiveObserve()
        }
    }

    private fun setUpPriceFeedObserve() {
        kavaViewModel.priceFeedResult.observe(viewLifecycleOwner) { response ->
            priceFeed = response
            kavaViewModel.incentive(getChannel(selectedChain), selectedChain.address)
        }
    }

    private fun setUpIncentiveObserve() {
        binding.apply {
            kavaViewModel.incentiveResult.observe(viewLifecycleOwner) { response ->
                loading.visibility = View.GONE
                incentive = response

                if (response == null || response.allIncentiveCoins().size == 0) {
                    incentiveView.visibility = View.GONE
                    val layoutParams = defiListTitle.layoutParams as ViewGroup.MarginLayoutParams
                    layoutParams.topMargin = 0
                    defiListTitle.layoutParams = layoutParams
                }
                defiLayout.visibility = View.VISIBLE

                val allIncentives = response?.allIncentiveCoins()

                setUpDisplayIncentive(allIncentives, "ukava", kavaLayout, kavaAmount)
                setUpDisplayIncentive(allIncentives, "hard", hardLayout, hardAmount)
                setUpDisplayIncentive(allIncentives, "usdx", usdxLayout, usdxAmount)
                setUpDisplayIncentive(allIncentives, "swp", swpLayout, swpAmount)
            }
        }
    }

    private fun setUpDisplayIncentive(
        allIncentives: MutableList<CoinProto.Coin>?,
        denom: String,
        layout: ConstraintLayout,
        amountTxt: TextView
    ) {
        binding.apply {
            allIncentives?.firstOrNull { it.denom == denom }?.let { incentive ->
                BaseData.getAsset(selectedChain.apiName, incentive.denom)?.let { asset ->
                    layout.visibility = View.VISIBLE
                    amountTxt.text = formatAmount(
                        incentive.amount.toBigDecimal().movePointLeft(asset.decimals ?: 6)
                            .toPlainString(), asset.decimals ?: 6
                    )
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            incentiveView.setOnClickListener {
                incentive?.let { incentive ->
                    ClaimIncentiveFragment(selectedChain, incentive).show(
                        requireActivity().supportFragmentManager,
                        ClaimIncentiveFragment::class.java.name
                    )
                    setClickableOnce(isClickable)
                }
            }

            mintView.setOnClickListener {
                requireActivity().toMoveFragment(
                    this@KavaDefiFragment, MintListFragment(selectedChain, priceFeed), "MintList"
                )
                setClickableOnce(isClickable)
            }

            lendView.setOnClickListener {
                requireActivity().toMoveFragment(
                    this@KavaDefiFragment, LendListFragment(selectedChain, priceFeed), "LendList"
                )
                setClickableOnce(isClickable)
            }

            poolView.setOnClickListener {
                requireActivity().toMoveFragment(
                    this@KavaDefiFragment, PoolListFragment(selectedChain), "PoolList"
                )
                setClickableOnce(isClickable)
            }

            earnView.setOnClickListener {
                requireActivity().toMoveFragment(
                    this@KavaDefiFragment, EarnListFragment.newInstance(selectedChain), "EarnList"
                )
                setClickableOnce(isClickable)
            }
        }
    }

    private fun setClickableOnce(clickable: Boolean) {
        if (clickable) {
            isClickable = false

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