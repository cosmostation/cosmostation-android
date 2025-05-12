package wannabit.io.cosmostaion.ui.tx.info.major.iota

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.IOTA_FEE_STAKE
import wannabit.io.cosmostaion.chain.majorClass.IOTA_MAIN_DENOM
import wannabit.io.cosmostaion.chain.majorClass.IOTA_MIN_STAKE
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.databinding.FragmentSuiStakeInfoBinding
import wannabit.io.cosmostaion.ui.tx.genTx.major.iota.IotaStakingFragment
import wannabit.io.cosmostaion.ui.tx.info.major.MoveInfoFragment
import java.math.BigDecimal
import java.util.Date
import java.util.Timer
import java.util.TimerTask

class IotaStakeInfoFragment : Fragment() {

    private var _binding: FragmentSuiStakeInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var iotaStakePagerAdapter: IotaStakePagerAdapter

    private var epoch: Long = 0
    private var epochStartTimestampMs: Long = 0
    private var epochDurationMs: Long = 0
    private val timer: Timer = Timer()

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): IotaStakeInfoFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = IotaStakeInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuiStakeInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
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

            titleManageStake.text = getString(R.string.title_manage_stake, "IOTA")

            iotaStakePagerAdapter = IotaStakePagerAdapter(
                this@IotaStakeInfoFragment, selectedChain
            )
            viewPager.adapter = iotaStakePagerAdapter
            viewPager.offscreenPageLimit = 1
            viewPager.isUserInputEnabled = false
            tabLayout.bringToFront()

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "Active"
                    else -> "Pending"
                }
            }.attach()

            (selectedChain as ChainIota).iotaFetcher?.let { fetcher ->
                if (fetcher.iotaStakedList.isNotEmpty()) {
                    emptyStake.visibility = View.GONE
                    stakingDataView.visibility = View.VISIBLE
                    epochView.visibility = View.VISIBLE

                } else {
                    emptyStake.visibility = View.VISIBLE
                    stakingDataView.visibility = View.GONE
                    epochView.visibility = View.GONE
                }
            }
        }

        initData()
    }

    private fun initData() {
        binding.apply {
            epochView.setBackgroundResource(R.drawable.item_bg)
            updateTime()

            (selectedChain as ChainIota).iotaFetcher?.let { fetcher ->
                epoch = fetcher.iotaSystem["result"].asJsonObject["epoch"].asLong
                epochStartTimestampMs =
                    fetcher.iotaSystem["result"].asJsonObject["epochStartTimestampMs"].asLong
                epochDurationMs =
                    fetcher.iotaSystem["result"].asJsonObject["epochDurationMs"].asLong
                currentEpoch.text = "#$epoch"
            }
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

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            btnInfo.setOnClickListener {
                handleOneClickWithDelay(MoveInfoFragment.newInstance(selectedChain))
            }

            btnStake.setOnClickListener {
                (selectedChain as ChainIota).iotaFetcher?.let { fetcher ->
                    val iotaBalance = fetcher.iotaBalanceAmount(IOTA_MAIN_DENOM) ?: BigDecimal.ZERO
                    if (iotaBalance < IOTA_MIN_STAKE.toBigDecimal()
                            .add(IOTA_FEE_STAKE.toBigDecimal())
                    ) {
                        requireActivity().showToast(
                            view, R.string.error_not_enough_iota_stake, false
                        )
                        return@setOnClickListener
                    }
                }
                handleOneClickWithDelay(IotaStakingFragment.newInstance(selectedChain))
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

    class IotaStakePagerAdapter(
        fragment: Fragment, selectedChain: BaseChain
    ) : FragmentStateAdapter(fragment) {
        private val fragments = mutableListOf<Fragment>()

        init {
            fragments.add(IotaActiveFragment.newInstance(selectedChain))
            fragments.add(IotaPendingFragment.newInstance(selectedChain))
        }

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}