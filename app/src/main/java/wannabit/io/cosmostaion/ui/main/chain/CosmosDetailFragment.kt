package wannabit.io.cosmostaion.ui.main.chain

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.toMoveFragment
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.FragmentCosmosDetailBinding
import wannabit.io.cosmostaion.ui.dialog.qr.QrCodeFragment
import wannabit.io.cosmostaion.ui.tx.info.StakeInfoFragment
import wannabit.io.cosmostaion.ui.tx.step.ClaimRewardFragment
import wannabit.io.cosmostaion.ui.tx.step.CompoundingFragment

class CosmosDetailFragment(private val selectedPosition: Int) : Fragment() {

    private var _binding: FragmentCosmosDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var pagerAdapter: AccountPageAdapter

    private lateinit var selectedChain: CosmosLine

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCosmosDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initTab()
        clickAction()
        clickFabMenu()
    }

    private fun initData() {
        binding.apply {
            fabMenu.menuIconView.setImageResource(R.drawable.icon_fab)
            val baseAccount = BaseData.baseAccount

            baseAccount?.let {
                accountName.text = baseAccount.name
                selectedChain = baseAccount.displayCosmosLineChains[selectedPosition]
                accountAddress.text = selectedChain.address
                accountValue.text = formatAssetValue(selectedChain.allAssetValue())
            }
            selectedChain.loadStakeData()
        }
    }

    private fun initTab() {
        binding.apply {
            pagerAdapter = AccountPageAdapter(
                requireActivity(), selectedChain, selectedPosition
            )
            viewPager.adapter = pagerAdapter
            viewPager.isUserInputEnabled = false
            tabLayout.bringToFront()

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                val supportToken = selectedChain.supportCw20 || selectedChain.supportErc20

                tab.text = when {
                    position == 0 -> getString(R.string.title_coin)
                    supportToken && position == 1 -> getString(R.string.title_token)
                    !supportToken && position == 1 || supportToken && position == 2 -> getString(R.string.title_history)
                    else -> getString(R.string.title_about)
                }
            }.attach()

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val position = tab?.position ?: 0
                    viewPager.setCurrentItem(position, false)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun clickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }

            btnAccount.setOnClickListener {
                val accountUrl =
                    CosmostationConstants.EXPLORER_BASE_URL + "/" + selectedChain.apiName + "/" + selectedChain.address
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(accountUrl)))
            }

            accountAddress.setOnClickListener {
                val bottomSheet = QrCodeFragment(selectedChain)
                bottomSheet.show(requireActivity().supportFragmentManager, QrCodeFragment::class.java.name)
            }

            fabMenu.setOnMenuToggleListener { opened ->
                fabMenu.bringToFront()
                backdropLayout.visibleOrGone(opened)
                if (opened) {
                    tabLayout.elevation = 0.1f
                    requireActivity().window.statusBarColor = ContextCompat.getColor(
                        requireContext(),
                        R.color.color_background_dialog
                    )
                } else {
                    tabLayout.elevation = 0f
                    requireActivity().window.statusBarColor = ContextCompat.getColor(
                        requireContext(),
                        R.color.color_transparent)
                }
            }

            backdropLayout.setOnClickListener {
                fabMenu.close(true)
                backdropLayout.visibility = View.GONE
                tabLayout.elevation = 0f
                requireActivity().window.statusBarColor = ContextCompat.getColor(
                    requireContext(),
                    R.color.color_transparent)
            }
        }
    }

    private fun clickFabMenu() {
        var isClickable = true
        binding.apply {
            fabStake.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    if (selectedChain.cosmosValidators.size > 0) {
                        requireActivity().toMoveFragment(this@CosmosDetailFragment,
                            StakeInfoFragment(selectedChain), "StakeInfo")
                    }
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)

                fabMenu.close(true)
            }

            fabClaimReward.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    if (selectedChain.cosmosValidators.size > 0) {
                        if (selectedChain.claimableRewards().size == 0) {
                            requireContext().makeToast(R.string.error_not_reward)
                            return@setOnClickListener
                        }
                        if (!selectedChain.isTxFeePayable(requireContext())) {
                            requireContext().makeToast(R.string.error_not_enough_fee)
                            return@setOnClickListener
                        }
                        val bottomSheet = ClaimRewardFragment(selectedChain, selectedChain.claimableRewards())
                        bottomSheet.show(requireActivity().supportFragmentManager, ClaimRewardFragment::class.java.name)
                    }
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)

                fabMenu.close(true)
            }

            fabCompounding.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    if (selectedChain.cosmosValidators.size > 0) {
                        if (selectedChain.claimableRewards().size == 0) {
                            requireContext().makeToast(R.string.error_not_reward)
                            return@setOnClickListener
                        }
                        if (!selectedChain.isTxFeePayable(requireContext())) {
                            requireContext().makeToast(R.string.error_not_enough_fee)
                            return@setOnClickListener
                        }
                        if (selectedChain.rewardAddress != selectedChain.address) {
                            requireContext().makeToast(R.string.error_reward_address_changed_msg)
                            return@setOnClickListener
                        }

                        val bottomSheet = CompoundingFragment(selectedChain, selectedChain.claimableRewards())
                        bottomSheet.show(requireActivity().supportFragmentManager, ClaimRewardFragment::class.java.name)
                    }
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)

                fabMenu.close(true)
            }
        }
    }

    class AccountPageAdapter(
        fragmentActivity: FragmentActivity,
        selectedChain: CosmosLine,
        selectedPosition: Int
    ) : FragmentStateAdapter(fragmentActivity) {
        private val fragments = mutableListOf<Fragment>()

        init {
            fragments.add(CoinFragment(selectedPosition))
            fragments.add(HistoryFragment(selectedPosition))
            fragments.add(AboutFragment(selectedPosition))

            if (selectedChain.supportCw20 || selectedChain.supportErc20) {
                fragments.add(1, TokenFragment(selectedPosition))
            }
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