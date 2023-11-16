package wannabit.io.cosmostaion.ui.tx.info.kava

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kava.incentive.v1beta1.QueryProto
import com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.allIncentiveCoins
import wannabit.io.cosmostaion.common.formatString
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKavaDefiBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        kavaViewModel.priceFeed(getChannel(selectedChain))
        initView()
        clickAction()
    }

    private fun initViewModel() {
        val kavaRepository = KavaRepositoryImpl()
        val kavaViewModelProviderFactory = KavaViewModelProviderFactory(kavaRepository)
        kavaViewModel = ViewModelProvider(this, kavaViewModelProviderFactory)[KavaViewModel::class.java]
    }

    private fun initView() {
        binding.apply {
            loading.visibility = View.VISIBLE
            defiLayout.visibility = View.GONE
            incentiveView.setBackgroundResource(R.drawable.item_bg)
            mintView.setBackgroundResource(R.drawable.item_bg)
            lendView.setBackgroundResource(R.drawable.item_bg)
            poolView.setBackgroundResource(R.drawable.item_bg)

            kavaViewModel.priceFeedResult.observe(viewLifecycleOwner) { response ->
                priceFeed = response
                kavaViewModel.incentive(getChannel(selectedChain), selectedChain.address)
            }

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
                allIncentives?.firstOrNull { it.denom == "ukava" }?.let { kavaIncentive ->
                    BaseData.getAsset(selectedChain.apiName, kavaIncentive.denom)?.let { asset ->
                        asset.decimals?.let { decimal ->
                            kavaLayout.visibility = View.VISIBLE
                            kavaAmount.text = formatString(
                                kavaIncentive.amount.toBigDecimal()
                                    .movePointLeft(decimal).toPlainString(), decimal)
                        }
                    }
                }

                allIncentives?.firstOrNull { it.denom == "hard" }?.let { hardIncentive ->
                    BaseData.getAsset(selectedChain.apiName, hardIncentive.denom)?.let { asset ->
                        asset.decimals?.let { decimal ->
                            hardLayout.visibility = View.VISIBLE
                            hardAmount.text = formatString(
                                hardIncentive.amount.toBigDecimal()
                                    .movePointLeft(decimal).toPlainString(), decimal)
                        }
                    }
                }

                allIncentives?.firstOrNull { it.denom == "usdx" }?.let { usdxIncentive ->
                    BaseData.getAsset(selectedChain.apiName, usdxIncentive.denom)?.let { asset ->
                        asset.decimals?.let { decimal ->
                            usdxLayout.visibility = View.VISIBLE
                            usdxAmount.text = formatString(
                                usdxIncentive.amount.toBigDecimal()
                                    .movePointLeft(decimal).toPlainString(), decimal)
                        }
                    }
                }

                allIncentives?.firstOrNull { it.denom == "swp" }?.let { swpIncentive ->
                    BaseData.getAsset(selectedChain.apiName, swpIncentive.denom)?.let { asset ->
                        asset.decimals?.let { decimal ->
                            swpLayout.visibility = View.VISIBLE
                            swpAmount.text = formatString(
                                swpIncentive.amount.toBigDecimal()
                                    .movePointLeft(decimal).toPlainString(), decimal)
                        }
                    }
                }
            }
        }
    }

    private fun clickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            var isClickable = true
            incentiveView.setOnClickListener {
                incentive?.let {
                    val bottomSheet = ClaimIncentiveFragment(selectedChain, it)
                    if (isClickable) {
                        isClickable = false
                        bottomSheet.show(requireActivity().supportFragmentManager, ClaimIncentiveFragment::class.java.name)

                        Handler(Looper.getMainLooper()).postDelayed({
                            isClickable = true
                        }, 1000)
                    }
                }
            }

            mintView.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    requireActivity().toMoveFragment(this@KavaDefiFragment,
                        MintListFragment(selectedChain, priceFeed), "MintList")
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)
            }

            lendView.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    requireActivity().toMoveFragment(this@KavaDefiFragment,
                        LendListFragment(selectedChain, priceFeed), "LendList")
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}