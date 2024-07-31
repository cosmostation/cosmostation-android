package wannabit.io.cosmostaion.ui.tx.info.kava

import android.os.Build
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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kava.incentive.v1beta1.QueryProto
import com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.allIncentiveCoins
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.toMoveFragment
import wannabit.io.cosmostaion.data.repository.chain.KavaRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentKavaDefiBinding
import wannabit.io.cosmostaion.ui.tx.step.kava.ClaimIncentiveFragment
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModelProviderFactory

class KavaDefiFragment : Fragment() {

    private var _binding: FragmentKavaDefiBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var kavaViewModel: KavaViewModel

    private var incentive: QueryProto.QueryRewardsResponse? = null
    private var priceFeed: QueryPricesResponse? = null

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): KavaDefiFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = KavaDefiFragment()
            fragment.arguments = args
            return fragment
        }
    }

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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedChain", BaseChain::class.java)
                    ?.let { selectedChain = it }
            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
            }

            selectedChain.cosmosFetcher?.getChannel()?.let { kavaViewModel.priceFeed(it) }

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
            kavaViewModel.incentive(selectedChain)
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

                setUpDisplayIncentive(allIncentives, "ukava", kavaLayout, kavaAmount, kavaDenom)
                setUpDisplayIncentive(allIncentives, "hard", hardLayout, hardAmount, hardDenom)
                setUpDisplayIncentive(allIncentives, "usdx", usdxLayout, usdxAmount, usdxDenom)
                setUpDisplayIncentive(allIncentives, "swp", swpLayout, swpAmount, swpDenom)
            }
        }
    }

    private fun setUpDisplayIncentive(
        allIncentives: MutableList<CoinProto.Coin>?,
        denom: String,
        layout: ConstraintLayout,
        amountTxt: TextView,
        denomTxt: TextView
    ) {
        binding.apply {
            allIncentives?.firstOrNull { it.denom == denom }?.let { incentive ->
                BaseData.getAsset(selectedChain.apiName, incentive.denom)?.let { asset ->
                    layout.visibility = View.VISIBLE
                    amountTxt.text = formatAmount(
                        incentive.amount.toBigDecimal().movePointLeft(asset.decimals ?: 6)
                            .toPlainString(), asset.decimals ?: 6
                    )
                    denomTxt.text = asset.symbol
                    denomTxt.setTextColor(asset.assetColor())
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
                    handleOneClickWithDelay(
                        null, ClaimIncentiveFragment.newInstance(selectedChain, incentive)
                    )
                }
            }

            mintView.setOnClickListener {
                handleOneClickWithDelay(
                    MintListFragment.newInstance(selectedChain, priceFeed), null
                )
            }

            lendView.setOnClickListener {
                handleOneClickWithDelay(
                    LendListFragment.newInstance(selectedChain, priceFeed), null
                )
            }

            poolView.setOnClickListener {
                handleOneClickWithDelay(
                    PoolListFragment.newInstance(selectedChain), null
                )
            }

            earnView.setOnClickListener {
                handleOneClickWithDelay(
                    EarnListFragment.newInstance(selectedChain), null
                )
            }
        }
    }

    private fun handleOneClickWithDelay(
        fragment: Fragment?, bottomSheetDialogFragment: BottomSheetDialogFragment?
    ) {
        if (isClickable) {
            isClickable = false

            if (fragment != null) {
                requireActivity().toMoveFragment(
                    this@KavaDefiFragment, fragment, fragment::class.java.name
                )
            } else {
                bottomSheetDialogFragment?.show(
                    requireActivity().supportFragmentManager,
                    bottomSheetDialogFragment::class.java.name
                )
            }

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