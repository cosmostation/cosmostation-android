package wannabit.io.cosmostaion.ui.tx.info.babylon

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.BabylonFetcher
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.data.viewmodel.chain.BabylonViewModel
import wannabit.io.cosmostaion.data.viewmodel.chain.BabylonViewModelProviderFactory
import wannabit.io.cosmostaion.databinding.FragmentBabylonStakeInfoBinding
import wannabit.io.cosmostaion.ui.tx.genTx.StakingFragment
import java.util.Timer
import java.util.TimerTask

class BabylonStakeInfoFragment : Fragment() {

    private var _binding: FragmentBabylonStakeInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var babylonViewModel: BabylonViewModel

    private lateinit var selectedChain: BaseChain

    private lateinit var babylonStakePagerAdapter: BabylonStakePagerAdapter

    private var babylonEpochData: BabylonFetcher.BabylonEpochData? = null
    private var babylonEpochTxTypes: MutableList<BabylonFetcher.BabylonEpochTxType>? =
        mutableListOf()

    private var timer: Timer? = null

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): BabylonStakeInfoFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = BabylonStakeInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBabylonStakeInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
        setUpObserve()
        setUpClickAction()
    }

    private fun initViewModel() {
        val walletRepository = WalletRepositoryImpl()
        val babylonViewModelProviderFactory = BabylonViewModelProviderFactory(walletRepository)
        babylonViewModel =
            ViewModelProvider(this, babylonViewModelProviderFactory)[BabylonViewModel::class.java]
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

            val denom = if (selectedChain.isTestnet) {
                "tBABY"
            } else {
                "BABY"
            }
            titleManageStake.text = getString(R.string.title_manage_stake, denom)

            babylonViewModel.epochData(selectedChain)
            epochView.setBackgroundResource(R.drawable.item_bg)
        }
    }

    private fun setUpObserve() {
        binding.apply {
            babylonViewModel.epochData.observe(viewLifecycleOwner) { response ->
                babylonEpochData = response
                babylonViewModel.epochMessageType(selectedChain, babylonEpochData?.epochMsg)
            }

            babylonViewModel.epochMessageTypeResult.observe(viewLifecycleOwner) { response ->
                babylonEpochTxTypes = response
                loading.visibility = View.GONE
                val delegations = selectedChain.cosmosFetcher?.cosmosDelegations
                val unBondings = selectedChain.cosmosFetcher?.cosmosUnbondings

                if (delegations?.isEmpty() == true && unBondings?.isEmpty() == true && babylonEpochTxTypes?.isEmpty() == true) {
                    emptyStake.visibility = View.VISIBLE
                    epochView.visibility = View.GONE
                    stakingDataView.visibility = View.GONE

                } else {
                    emptyStake.visibility = View.GONE
                    epochView.visibility = View.VISIBLE
                    stakingDataView.visibility = View.VISIBLE
                    initData()
                }
            }

            babylonViewModel.errorMessage.observe(viewLifecycleOwner) {
                loading.visibility = View.GONE
            }
        }
    }

    private fun initData() {
        binding.apply {
            currentEpoch.text = "#${babylonEpochData?.currentEpoch?.currentEpoch}"
            updateTime()

            val currentPage = viewPager.currentItem

            babylonStakePagerAdapter = BabylonStakePagerAdapter(
                this@BabylonStakeInfoFragment,
                selectedChain,
                babylonEpochData,
                babylonEpochTxTypes ?: mutableListOf(),
                babylonViewModel
            )
            viewPager.adapter = babylonStakePagerAdapter
            viewPager.offscreenPageLimit = 1
            viewPager.isUserInputEnabled = false
            tabLayout.bringToFront()

            viewPager.setCurrentItem(currentPage, false)

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "Staking"
                    else -> "Unstaking"
                }
            }.attach()
        }
    }

    private fun updateTime() {
        timer?.cancel()
        timer?.purge()

        timer = Timer()

        val endEpoch = babylonEpochData?.currentEpoch?.epochBoundary?.minus(
            babylonEpochData?.currentHeight ?: 0L
        )?.toBigDecimal()
        val blockTime = selectedChain.getChainParam()?.get("block_time")?.asDouble?.toBigDecimal()

        var remainTime = endEpoch?.multiply(blockTime)?.toDouble() ?: 0.00

        timer?.schedule(object : TimerTask() {
            override fun run() {
                if (isAdded) {
                    requireActivity().runOnUiThread {
                        if (remainTime <= 0) {
                            timer?.cancel()
                            return@runOnUiThread
                        }

                        remainTime -= 1.0

                        val hour = (remainTime / 3600).toInt()
                        val minute = ((remainTime % 3600) / 60).toInt()
                        val second = (remainTime % 60).toInt()
                        binding.nextEpoch.text =
                            String.format("%02d : %02d : %02d", hour, minute, second)
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
                handleOneClickWithDelay(BabylonInfoFragment())
            }

            btnStake.setOnClickListener {
                handleOneClickWithDelay(
                    StakingFragment.newInstance(selectedChain)
                )
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

    class BabylonStakePagerAdapter(
        fragment: Fragment,
        selectedChain: BaseChain,
        babylonEpochData: BabylonFetcher.BabylonEpochData?,
        babylonEpochTxTypes: MutableList<BabylonFetcher.BabylonEpochTxType>,
        babylonViewModel: BabylonViewModel
    ) : FragmentStateAdapter(fragment) {
        private val fragments = mutableListOf<Fragment>()

        init {
            fragments.add(
                BabylonStakingFragment(
                    selectedChain, babylonEpochData, babylonEpochTxTypes, babylonViewModel
                )
            )
            fragments.add(
                BabylonUnstakingFragment(
                    selectedChain, babylonEpochData, babylonEpochTxTypes, babylonViewModel
                )
            )
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