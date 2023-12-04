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
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava459
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.toMoveFragment
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.FragmentCosmosDetailBinding
import wannabit.io.cosmostaion.ui.dialog.qr.QrCodeFragment
import wannabit.io.cosmostaion.ui.dialog.tx.select.VaultSelectFragment
import wannabit.io.cosmostaion.ui.tx.info.ProposalListFragment
import wannabit.io.cosmostaion.ui.tx.info.StakeInfoFragment
import wannabit.io.cosmostaion.ui.tx.info.kava.KavaDefiFragment
import wannabit.io.cosmostaion.ui.tx.info.neutron.DaoListFragment
import wannabit.io.cosmostaion.ui.tx.step.ClaimRewardFragment
import wannabit.io.cosmostaion.ui.tx.step.CompoundingFragment
import wannabit.io.cosmostaion.ui.tx.step.LegacyTransferFragment
import wannabit.io.cosmostaion.ui.tx.step.TransferFragment

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
        updateTokenValue()
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
                if (selectedChain is ChainOkt60) {
                    accountAddress.text = ByteUtils.convertBech32ToEvm(selectedChain.address)
                } else {
                    accountAddress.text = selectedChain.address
                }
                accountValue.text = formatAssetValue(selectedChain.allValue())
            }
            if (selectedChain.supportStaking) {
                selectedChain.loadStakeData()
            }
            if (selectedChain is ChainOkt60) {

            }
        }
    }

    private fun updateTokenValue() {
        selectedChain.setLoadTokenCallBack(object : CosmosLine.LoadTokenCallback {
            override fun onTokenLoaded(isLoaded: Boolean) {
                if (isAdded) {
                    requireActivity().runOnUiThread {
                        binding.accountValue.text = formatAssetValue(selectedChain.allValue())
                    }
                }
            }
        })
    }

    private fun initTab() {
        binding.apply {
            if (selectedChain is ChainNeutron) {
                fabStake.visibility = View.GONE
                fabClaimReward.setImageResource(R.drawable.icon_receive)
                fabClaimReward.labelText = "Receive"

                fabCompounding.setImageResource(R.drawable.icon_neutron_dao)
                fabCompounding.labelText = "Dao"
                fabVote.setImageResource(R.drawable.icon_neutron_vault)
                fabVote.labelText = "Vault"

            } else if (selectedChain is ChainKava459) {
                fabDefi.visibility = View.VISIBLE

            } else if (selectedChain is ChainBinanceBeacon) {
                fabVote.visibility = View.GONE
                fabCompounding.visibility = View.GONE
                fabStake.visibility = View.GONE
                fabClaimReward.setImageResource(R.drawable.icon_receive)
                fabClaimReward.labelText = "Receive"

            } else if (selectedChain is ChainOkt60) {
                fabVote.setImageResource(R.drawable.icon_select_validators)
                fabVote.labelText = "Select validators"
                fabCompounding.setImageResource(R.drawable.icon_withdraw)
                fabCompounding.labelText = "Withdraw"
                fabClaimReward.setImageResource(R.drawable.icon_deposit)
                fabClaimReward.labelText = "Deposit"
                fabStake.setImageResource(R.drawable.icon_receive)
                fabStake.labelText = "Receive"
            }

            pagerAdapter = AccountPageAdapter(
                requireActivity(), selectedChain, selectedPosition
            )
            viewPager.adapter = pagerAdapter
            viewPager.offscreenPageLimit = 2
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
            fabSend.setOnClickListener {
                if (isClickable) {
                    isClickable = false

                    selectedChain.stakeDenom?.let {
                        if (selectedChain is ChainBinanceBeacon) {
                            val bottomSheet = LegacyTransferFragment(selectedChain, it)
                            bottomSheet.show(requireActivity().supportFragmentManager, LegacyTransferFragment::class.java.name)

                        } else {
                            val bottomSheet = TransferFragment(selectedChain, it)
                            bottomSheet.show(requireActivity().supportFragmentManager, TransferFragment::class.java.name)
                        }
                    }
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)

                fabMenu.close(true)
            }


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

                    if (selectedChain is ChainNeutron || selectedChain is ChainBinanceBeacon) {
                        val bottomSheet = QrCodeFragment(selectedChain)
                        bottomSheet.show(requireActivity().supportFragmentManager, QrCodeFragment::class.java.name)

                    } else {
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
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)

                fabMenu.close(true)
            }

            fabCompounding.setOnClickListener {
                if (isClickable) {
                    isClickable = false

                    if (selectedChain is ChainNeutron) {
                        requireActivity().toMoveFragment(this@CosmosDetailFragment,
                            DaoListFragment(selectedChain as ChainNeutron), "DaoList")

                    } else {
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
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)

                fabMenu.close(true)
            }

            fabVote.setOnClickListener {
                if (isClickable) {
                    isClickable = false

                    if (selectedChain is ChainNeutron) {
                        if (!selectedChain.isTxFeePayable(requireContext())) {
                            requireContext().makeToast(R.string.error_not_enough_fee)
                            return@setOnClickListener
                        }
                        val bottomSheet = VaultSelectFragment(selectedChain)
                        bottomSheet.show(requireActivity().supportFragmentManager, VaultSelectFragment::class.java.name)

                    } else {
                        requireActivity().toMoveFragment(this@CosmosDetailFragment,
                            ProposalListFragment(selectedChain), "ProposalList")
                    }
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)

                fabMenu.close(true)
            }

            fabDefi.setOnClickListener {
                if (isClickable) {
                    isClickable = false

                    requireActivity().toMoveFragment(this@CosmosDetailFragment,
                        KavaDefiFragment(selectedChain as ChainKava459), "KavaDefi")
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
            if (selectedChain is ChainBinanceBeacon) {
                fragments.add(CoinFragment(selectedPosition))
                fragments.add(HistoryFragment(selectedPosition))

            } else if (selectedChain is ChainOkt60) {
                fragments.add(CoinFragment(selectedPosition))
                fragments.add(TokenFragment(selectedPosition))
                fragments.add(HistoryFragment(selectedPosition))

            } else {
                fragments.add(CoinFragment(selectedPosition))
                fragments.add(HistoryFragment(selectedPosition))
                fragments.add(AboutFragment(selectedPosition))

                if (selectedChain.supportCw20 || selectedChain.supportErc20) {
                    fragments.add(1, TokenFragment(selectedPosition))
                }
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