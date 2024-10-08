package wannabit.io.cosmostaion.ui.tx.info.major

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.SUI_FEE_STAKE
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.chain.majorClass.SUI_MIN_STAKE
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.databinding.FragmentSuiStakeInfoBinding
import wannabit.io.cosmostaion.ui.tx.genTx.major.SuiStakingFragment
import java.math.BigDecimal
import java.util.Date
import java.util.Timer
import java.util.TimerTask

class SuiStakeInfoFragment : Fragment() {

    private var _binding: FragmentSuiStakeInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var suiStakePagerAdapter: SuiStakePagerAdapter

    private var epoch: Long = 0
    private var epochStartTimestampMs: Long = 0
    private var epochDurationMs: Long = 0
    private val timer: Timer = Timer()

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): SuiStakeInfoFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = SuiStakeInfoFragment()
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

            suiStakePagerAdapter = SuiStakePagerAdapter(
                requireActivity(), selectedChain
            )
            viewPager.adapter = suiStakePagerAdapter
            viewPager.offscreenPageLimit = 1
            viewPager.isUserInputEnabled = false
            tabLayout.bringToFront()

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "Active"
                    else -> "Pending"
                }
            }.attach()
        }

        initData()
    }

    private fun initData() {
        binding.apply {
            epochView.setBackgroundResource(R.drawable.item_bg)
            updateTime()

            (selectedChain as ChainSui).suiFetcher()?.let { fetcher ->
                epoch = fetcher.suiSystem["result"].asJsonObject["epoch"].asLong
                epochStartTimestampMs =
                    fetcher.suiSystem["result"].asJsonObject["epochStartTimestampMs"].asLong
                epochDurationMs = fetcher.suiSystem["result"].asJsonObject["epochDurationMs"].asLong
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
                handleOneClickWithDelay(SuiInfoFragment.newInstance(selectedChain))
            }

            btnStake.setOnClickListener {
                (selectedChain as ChainSui).suiFetcher()?.let { fetcher ->
                    val suiBalance = fetcher.suiBalanceAmount(SUI_MAIN_DENOM) ?: BigDecimal.ZERO
                    if (suiBalance < SUI_MIN_STAKE.toBigDecimal()
                            .add(SUI_FEE_STAKE.toBigDecimal())
                    ) {
                        requireActivity().showToast(
                            view, R.string.error_not_enough_sui_stake, false
                        )
                        return@setOnClickListener
                    }
                }
                handleOneClickWithDelay(SuiStakingFragment.newInstance(selectedChain))
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

    class SuiStakePagerAdapter(
        fragmentActivity: FragmentActivity, selectedChain: BaseChain
    ) : FragmentStateAdapter(fragmentActivity) {
        private val fragments = mutableListOf<Fragment>()

        init {
            fragments.add(SuiActiveFragment.newInstance(selectedChain))
            fragments.add(SuiPendingFragment.newInstance(selectedChain))
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