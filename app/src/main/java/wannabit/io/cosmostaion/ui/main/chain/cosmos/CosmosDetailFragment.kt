package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Intent
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainGovgen
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava459
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainKavaEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.toMoveFragment
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentCosmosDetailBinding
import wannabit.io.cosmostaion.ui.option.notice.NoticeInfoFragment
import wannabit.io.cosmostaion.ui.option.tx.general.VaultSelectFragment
import wannabit.io.cosmostaion.ui.qr.QrCodeEvmFragment
import wannabit.io.cosmostaion.ui.qr.QrCodeFragment
import wannabit.io.cosmostaion.ui.tx.info.ProposalListFragment
import wannabit.io.cosmostaion.ui.tx.info.StakeInfoFragment
import wannabit.io.cosmostaion.ui.tx.info.kava.KavaDefiFragment
import wannabit.io.cosmostaion.ui.tx.info.neutron.DaoProposalListFragment
import wannabit.io.cosmostaion.ui.tx.step.ClaimRewardFragment
import wannabit.io.cosmostaion.ui.tx.step.CommonTransferFragment
import wannabit.io.cosmostaion.ui.tx.step.CompoundingFragment
import wannabit.io.cosmostaion.ui.tx.step.LegacyTransferFragment
import wannabit.io.cosmostaion.ui.tx.step.SendAssetType
import wannabit.io.cosmostaion.ui.tx.step.okt.OktDepositFragment
import wannabit.io.cosmostaion.ui.tx.step.okt.OktSelectValidatorFragment
import wannabit.io.cosmostaion.ui.tx.step.okt.OktWithdrawFragment
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import java.math.BigDecimal

class CosmosDetailFragment : Fragment() {

    private var _binding: FragmentCosmosDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var detailPagerAdapter: DetailPageAdapter

    private lateinit var selectedChain: CosmosLine

    private val walletViewModel: WalletViewModel by activityViewModels()

    private var isClickable = true

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: CosmosLine): CosmosDetailFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = CosmosDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

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
        setUpClickAction()
        setFabMenuClickAction()
        setUpObserve()
    }

    private fun initData() {
        binding.apply {
            fabMenu.menuIconView.setImageResource(R.drawable.icon_fab)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedChain", CosmosLine::class.java)
                    ?.let { selectedChain = it }
            } else {
                (arguments?.getParcelable("selectedChain") as? CosmosLine)?.let {
                    selectedChain = it
                }
            }

            BaseData.baseAccount?.let { account ->
                accountName.text = account.name

                if (selectedChain is EthereumLine && (selectedChain as EthereumLine).supportCosmos) {
                    accountAddress.text = selectedChain.address
                    selectedChain.address?.let {
                        accountEvmAddress.text = ByteUtils.convertBech32ToEvm(it)
                    }
                    accountAddress.visibility = View.INVISIBLE
                    accountEvmAddress.visibility = View.VISIBLE

                    handler.postDelayed(starEvmAddressAnimation, 5000)
                } else {
                    accountEvmAddress.visibility = View.INVISIBLE
                    accountAddress.text = selectedChain.address
                }

                updateAccountValue()
                btnHide.setColorFilter(
                    ContextCompat.getColor(requireContext(), R.color.color_base03),
                    PorterDuff.Mode.SRC_IN
                )
            }

            if (selectedChain.supportStaking) {
                walletViewModel.loadGrpcStakeData(selectedChain)
            }
            if (selectedChain is ChainOkt996Keccak) {
                (selectedChain as ChainOkt996Keccak).loadValidators()
            }
        }
    }

    private val starEvmAddressAnimation = object : Runnable {
        override fun run() {
            binding.apply {
                if (accountAddress.visibility == View.VISIBLE) {
                    fadeOutAnimation(accountAddress)
                    fadeInAnimation(accountEvmAddress)
                } else {
                    fadeOutAnimation(accountEvmAddress)
                    fadeInAnimation(accountAddress)
                }
            }
            handler.postDelayed(this, 5000)
        }
    }

    private fun fadeInAnimation(view: View) {
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = 1000
        view.startAnimation(fadeIn)
        view.visibility = View.VISIBLE
    }

    private fun fadeOutAnimation(view: View) {
        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.duration = 800
        view.startAnimation(fadeOut)
        view.visibility = View.INVISIBLE
    }

    private fun initTab() {
        binding.apply {
            fabStake.visibleOrGone(selectedChain.supportStaking)
            fabClaimReward.visibleOrGone(selectedChain.supportStaking)
            fabCompounding.visibleOrGone(selectedChain.supportStaking)
            fabVote.visibleOrGone(selectedChain.supportStaking)

            fabReceive.visibleOrGone(!selectedChain.supportStaking)

            when (selectedChain) {
                is ChainNeutron -> {
                    fabDao.visibility = View.VISIBLE
                    fabVault.visibility = View.VISIBLE
                }

                is ChainKava459 -> {
                    fabDefi.visibility = View.VISIBLE
                }

                is ChainOkt996Keccak -> {
                    fabDeposit.visibility = View.VISIBLE
                    fabWithdraw.visibility = View.VISIBLE
                    fabSelectValidator.visibility = View.VISIBLE
                }
            }

            detailPagerAdapter = DetailPageAdapter(
                requireActivity(), selectedChain
            )
            viewPager.adapter = detailPagerAdapter
            viewPager.offscreenPageLimit = 2
            viewPager.isUserInputEnabled = false
            tabLayout.bringToFront()

            val supportToken =
                selectedChain is EthereumLine || selectedChain.supportCw20 || selectedChain.supportErc20

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when {
                    position == 0 -> getString(R.string.title_coin)
                    supportToken && position == 1 -> getString(R.string.title_token)
                    !supportToken && position == 1 || supportToken && position == 2 -> getString(R.string.title_history)
                    else -> "About"
                }
            }.attach()

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val position = tab?.position ?: 0
                    viewPager.setCurrentItem(position, false)

                    btnAddToken.visibleOrGone(supportToken && position == 1)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun updateAccountValue() {
        if (isAdded) {
            requireActivity().runOnUiThread {
                binding.apply {
                    if (Prefs.hideValue) {
                        accountValue.text = "✱✱✱✱✱"
                        accountValue.textSize = 18f
                        btnHide.setImageResource(R.drawable.icon_hide)
                    } else {
                        accountValue.text = formatAssetValue(selectedChain.allValue(false))
                        accountValue.textSize = 24f
                        btnHide.setImageResource(R.drawable.icon_not_hide)
                    }
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }

            btnAddToken.setOnClickListener {
                NoticeInfoFragment.newInstance(selectedChain).show(
                    requireActivity().supportFragmentManager, NoticeInfoFragment::class.java.name
                )
            }

            btnAccount.setOnClickListener {
                val accountUrl = if (selectedChain is EthereumLine) {
                    if (selectedChain is ChainKavaEvm) {
                        (selectedChain as EthereumLine).addressURL + ByteUtils.convertBech32ToEvm(
                            selectedChain.address
                        )
                    } else {
                        (selectedChain as EthereumLine).addressURL + selectedChain.address
                    }
                } else {
                    CosmostationConstants.EXPLORER_BASE_URL + "/" + selectedChain.apiName + "/address/" + selectedChain.address
                }
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(accountUrl)))
                Prefs.foreToBack = false
            }

            accountAddress.setOnClickListener {
                if (selectedChain is EthereumLine) {
                    QrCodeEvmFragment.newInstance(selectedChain as EthereumLine).show(
                        requireActivity().supportFragmentManager, QrCodeFragment::class.java.name
                    )

                } else {
                    QrCodeFragment.newInstance(selectedChain).show(
                        requireActivity().supportFragmentManager, QrCodeFragment::class.java.name
                    )
                }
            }

            accountEvmAddress.setOnClickListener {
                if (selectedChain is EthereumLine) {
                    QrCodeEvmFragment.newInstance(selectedChain as EthereumLine).show(
                        requireActivity().supportFragmentManager, QrCodeFragment::class.java.name
                    )
                }
            }

            btnHide.setOnClickListener {
                Prefs.hideValue = !Prefs.hideValue
                updateAccountValue()
                ApplicationViewModel.shared.hideValue()
            }

            fabMenu.setOnMenuToggleListener { opened ->
                fabMenu.bringToFront()
                backdropLayout.visibleOrGone(opened)
                if (opened) {
                    tabLayout.elevation = 0.1f
                    requireActivity().window.statusBarColor = ContextCompat.getColor(
                        requireContext(), R.color.color_background_dialog
                    )
                } else {
                    tabLayout.elevation = 0f
                    requireActivity().window.statusBarColor = ContextCompat.getColor(
                        requireContext(), R.color.color_transparent
                    )
                }
            }

            backdropLayout.setOnClickListener {
                fabMenu.close(true)
                backdropLayout.visibility = View.GONE
                tabLayout.elevation = 0f
                requireActivity().window.statusBarColor = ContextCompat.getColor(
                    requireContext(), R.color.color_transparent
                )
            }
        }
    }


    private fun setFabMenuClickAction() {
        binding.apply {
            fabSend.setOnClickListener {
                selectedChain.stakeDenom?.let { denom ->
                    val sendAssetType = if (selectedChain is EthereumLine) {
                        if (selectedChain is ChainOktEvm) {
                            SendAssetType.ONLY_EVM_COIN
                        } else if ((selectedChain as EthereumLine).supportCosmos) {
                            SendAssetType.COSMOS_EVM_COIN
                        } else {
                            SendAssetType.ONLY_EVM_COIN
                        }

                    } else {
                        SendAssetType.ONLY_COSMOS_COIN
                    }

                    if (selectedChain is ChainGovgen) {
                        requireActivity().makeToast(R.string.error_tranfer_disabled)
                        return@setOnClickListener
                    }

                    if (selectedChain is ChainBinanceBeacon || selectedChain is ChainOkt996Keccak) {
                        handleOneClickWithDelay(
                            null, LegacyTransferFragment.newInstance(selectedChain, denom)
                        )
                    } else {
                        handleOneClickWithDelay(
                            null,
                            CommonTransferFragment.newInstance(selectedChain, denom, sendAssetType)
                        )
                    }
                }
            }

            fabReceive.setOnClickListener {
                if (selectedChain is ChainOktEvm) {
                    handleOneClickWithDelay(
                        null, QrCodeEvmFragment.newInstance(selectedChain as ChainOktEvm)
                    )
                } else {
                    handleOneClickWithDelay(null, QrCodeFragment.newInstance(selectedChain))
                }
            }

            fabStake.setOnClickListener {
                if (selectedChain.cosmosValidators.size > 0) {
                    handleOneClickWithDelay(StakeInfoFragment.newInstance(selectedChain), null)

                } else {
                    requireContext().makeToast(R.string.error_wait_moment)
                    fabMenu.close(true)
                    return@setOnClickListener
                }
            }

            fabClaimReward.setOnClickListener {
                if (selectedChain.cosmosValidators.size > 0) {
                    if (selectedChain.rewardAllCoins().isEmpty()) {
                        requireContext().makeToast(R.string.error_not_reward)
                        return@setOnClickListener
                    }
                    if (selectedChain.claimableRewards().isEmpty()) {
                        requireContext().showToast(view, R.string.error_wasting_fee, false)
                        return@setOnClickListener
                    }
                    if (!selectedChain.isTxFeePayable(requireContext())) {
                        requireContext().showToast(view, R.string.error_not_enough_fee, false)
                        return@setOnClickListener
                    }
                    handleOneClickWithDelay(
                        null, ClaimRewardFragment.newInstance(
                            selectedChain, selectedChain.claimableRewards()
                        )
                    )

                } else {
                    requireContext().makeToast(R.string.error_wait_moment)
                    fabMenu.close(true)
                    return@setOnClickListener
                }
            }

            fabCompounding.setOnClickListener {
                if (selectedChain.cosmosValidators.size > 0) {
                    if (selectedChain.claimableRewards().size == 0) {
                        requireContext().makeToast(R.string.error_not_reward)
                        return@setOnClickListener
                    }
                    if (selectedChain.rewardAddress != selectedChain.address) {
                        requireContext().showToast(
                            view, R.string.error_reward_address_changed_msg, false
                        )
                        return@setOnClickListener
                    }
                    if (!selectedChain.isTxFeePayable(requireContext())) {
                        requireContext().showToast(view, R.string.error_not_enough_fee, false)
                        return@setOnClickListener
                    }
                    handleOneClickWithDelay(
                        null, CompoundingFragment.newInstance(
                            selectedChain, selectedChain.claimableRewards()
                        )
                    )

                } else {
                    requireContext().makeToast(R.string.error_wait_moment)
                    fabMenu.close(true)
                    return@setOnClickListener
                }
            }

            fabVote.setOnClickListener {
                handleOneClickWithDelay(ProposalListFragment.newInstance(selectedChain), null)
            }

            fabDefi.setOnClickListener {
                handleOneClickWithDelay(
                    KavaDefiFragment.newInstance(selectedChain as ChainKava459), null
                )
            }

            fabDao.setOnClickListener {
                handleOneClickWithDelay(
                    DaoProposalListFragment.newInstance(selectedChain as ChainNeutron), null
                )
            }

            fabVault.setOnClickListener {
                handleOneClickWithDelay(null, VaultSelectFragment.newInstance(selectedChain))
            }

            fabDeposit.setOnClickListener {
                handleOneClickWithDelay(
                    null, OktDepositFragment.newInstance(selectedChain as ChainOkt996Keccak)
                )
            }

            fabWithdraw.setOnClickListener {
                if ((selectedChain as ChainOkt996Keccak).lcdOktDepositAmount() <= BigDecimal.ZERO) {
                    requireContext().makeToast(R.string.error_no_deposited_asset)
                    return@setOnClickListener
                }
                handleOneClickWithDelay(
                    null, OktWithdrawFragment.newInstance(selectedChain as ChainOkt996Keccak)
                )
            }

            fabSelectValidator.setOnClickListener {
                if ((selectedChain as ChainOkt996Keccak).lcdOktDepositAmount() <= BigDecimal.ZERO) {
                    requireContext().makeToast(R.string.error_no_deposited_asset)
                    return@setOnClickListener
                }
                handleOneClickWithDelay(
                    null, OktSelectValidatorFragment.newInstance(selectedChain as ChainOkt996Keccak)
                )
            }
        }
    }

    private fun setUpObserve() {
        ApplicationViewModel.shared.changeNameResult.observe(viewLifecycleOwner) { account ->
            if (BaseData.baseAccount?.id == account?.id) {
                binding.accountName.text = account?.name
            }
        }

        ApplicationViewModel.shared.fetchedTotalResult.observe(viewLifecycleOwner) {
            updateAccountValue()
        }
    }

    private fun handleOneClickWithDelay(
        fragment: Fragment?, bottomSheetDialogFragment: BottomSheetDialogFragment?
    ) {
        binding.fabMenu.close(true)
        if (isClickable) {
            isClickable = false

            if (fragment != null) {
                requireActivity().toMoveFragment(
                    this@CosmosDetailFragment, fragment, fragment::class.java.name
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

    class DetailPageAdapter(
        fragmentActivity: FragmentActivity, selectedChain: CosmosLine
    ) : FragmentStateAdapter(fragmentActivity) {
        private val fragments = mutableListOf<Fragment>()

        init {
            if (selectedChain is EthereumLine) {
                fragments.add(CoinFragment.newInstance(selectedChain))
                fragments.add(TokenFragment.newInstance(selectedChain))
                fragments.add(HistoryFragment.newInstance(selectedChain))

                if (selectedChain.supportCosmos && !selectedChain.tag.contains("okt60_Keccak")) {
                    fragments.add(AboutFragment.newInstance(selectedChain))
                }

            } else if (selectedChain is ChainBinanceBeacon) {
                fragments.add(CoinFragment.newInstance(selectedChain))
                fragments.add(HistoryFragment.newInstance(selectedChain))

            } else if (selectedChain is ChainOkt996Keccak) {
                fragments.add(CoinFragment.newInstance(selectedChain))
                fragments.add(TokenFragment.newInstance(selectedChain))
                fragments.add(HistoryFragment.newInstance(selectedChain))

            } else {
                fragments.add(CoinFragment.newInstance(selectedChain))
                fragments.add(HistoryFragment.newInstance(selectedChain))
                fragments.add(AboutFragment.newInstance(selectedChain))

                if (selectedChain.supportCw20 || selectedChain.supportErc20) {
                    fragments.add(1, TokenFragment.newInstance(selectedChain))
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
        ApplicationViewModel.shared.fetchedTotalResult.removeObservers(viewLifecycleOwner)
        handler.removeCallbacks(starEvmAddressAnimation)
    }
}