package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava118
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava459
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainKavaEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.fadeInAnimation
import wannabit.io.cosmostaion.common.fadeOutAnimation
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.toMoveFragment
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentCosmosDetailBinding
import wannabit.io.cosmostaion.ui.intro.IntroActivity
import wannabit.io.cosmostaion.ui.main.CosmostationApp
import wannabit.io.cosmostaion.ui.option.notice.NoticeInfoFragment
import wannabit.io.cosmostaion.ui.option.notice.NoticeType
import wannabit.io.cosmostaion.ui.option.tx.general.VaultSelectFragment
import wannabit.io.cosmostaion.ui.qr.QrCodeEvmFragment
import wannabit.io.cosmostaion.ui.qr.QrCodeFragment
import wannabit.io.cosmostaion.ui.tx.info.ProposalListFragment
import wannabit.io.cosmostaion.ui.tx.info.StakeInfoFragment
import wannabit.io.cosmostaion.ui.tx.info.kava.KavaDefiFragment
import wannabit.io.cosmostaion.ui.tx.info.neutron.DaoProposalListFragment
import wannabit.io.cosmostaion.ui.tx.step.ClaimRewardFragment
import wannabit.io.cosmostaion.ui.tx.step.CompoundingFragment
import wannabit.io.cosmostaion.ui.tx.step.okt.OktDepositFragment
import wannabit.io.cosmostaion.ui.tx.step.okt.OktSelectValidatorFragment
import wannabit.io.cosmostaion.ui.tx.step.okt.OktWithdrawFragment
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModelProviderFactory
import java.math.BigDecimal


class CosmosDetailFragment : Fragment() {

    private var _binding: FragmentCosmosDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var detailPagerAdapter: DetailPagerAdapter

    private lateinit var selectedChain: BaseChain

    private lateinit var walletViewModel: WalletViewModel

    private var isClickable = true

    private val handler = Handler(Looper.getMainLooper())

    private var noticeType = NoticeType.TOKEN_GITHUB

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): CosmosDetailFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = CosmosDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("selectedChain", selectedChain)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            initData(it)
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

        initViewModel()
        initData(savedInstanceState)
        initTab()
        setUpClickAction()
        setFabMenuClickAction()
        setUpObserve()
    }

    override fun onResume() {
        super.onResume()
        if (!::selectedChain.isInitialized) {
            Intent(requireContext(), IntroActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(this)
            }
        }
    }

    private fun initViewModel() {
        val walletRepository = WalletRepositoryImpl()
        val walletViewModelProviderFactory = WalletViewModelProviderFactory(walletRepository)
        walletViewModel =
            ViewModelProvider(this, walletViewModelProviderFactory)[WalletViewModel::class.java]
    }

    private fun initData(savedInstanceState: Bundle?) {
        binding.apply {
            savedInstanceState?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.getParcelable("selectedChain", BaseChain::class.java)
                        ?.let { chain -> selectedChain = chain }
                } else {
                    (it.getParcelable("selectedChain") as? BaseChain)?.let { chain ->
                        selectedChain = chain
                    }
                }

            } ?: run {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    arguments?.getParcelable("selectedChain", BaseChain::class.java)
                        ?.let { selectedChain = it }
                } else {
                    (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                        selectedChain = it
                    }
                }
            }

            fabMenu.menuIconView.setImageResource(R.drawable.icon_floating)

            BaseData.baseAccount?.let { account ->
                accountName.text = account.name

                accountEvmAddress.visibility = View.INVISIBLE
                accountAddress.text = selectedChain.address

                if (selectedChain.supportEvm) {
                    accountAddress.text = selectedChain.address
                    accountEvmAddress.text = selectedChain.evmAddress
                    accountAddress.visibility = View.INVISIBLE
                    accountEvmAddress.visibility = View.VISIBLE

                    handler.postDelayed(starEvmAddressAnimation, 5000)

                } else {
                    if (selectedChain.supportEvm) {
                        accountAddress.text = selectedChain.evmAddress
                    } else {
                        accountAddress.text = selectedChain.address
                    }
                    accountEvmAddress.visibility = View.INVISIBLE
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
            when (selectedChain) {
                is ChainOkt996Keccak -> (selectedChain as ChainOkt996Keccak).oktFetcher?.loadValidators()
                is ChainOktEvm -> (selectedChain as ChainOktEvm).oktFetcher?.loadValidators()
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

    private fun initTab() {
        binding.apply {
            fabStake.visibleOrGone(selectedChain.supportStaking)
            fabClaimReward.visibleOrGone(selectedChain.supportStaking)
            fabCompounding.visibleOrGone(selectedChain.supportStaking)
            fabVote.visibleOrGone(selectedChain.supportStaking)

            when (selectedChain) {
                is ChainNeutron -> {
                    fabDao.visibility = View.VISIBLE
                    fabVault.visibility = View.VISIBLE
                }

                is ChainKavaEvm, is ChainKava459, is ChainKava118 -> {
                    fabDefi.visibility = View.VISIBLE
                }

                is ChainOkt996Keccak, is ChainOktEvm -> {
                    fabDeposit.visibility = View.VISIBLE
                    fabWithdraw.visibility = View.VISIBLE
                    fabSelectValidator.visibility = View.VISIBLE
                }
            }

            val supportToken = selectedChain.supportCw20 || selectedChain.supportEvm
            btnAddToken.visibleOrGone(supportToken)
            val supportNft = selectedChain.supportNft

            val tableTitles = mutableListOf<String>()
            tableTitles.add("Crypto")

            if (supportNft) tableTitles.add("NFTs")

            tableTitles.add("Receive")
            tableTitles.add("History")

            if (selectedChain.isEcosystem()) tableTitles.add("Ecosystem")

            tableTitles.add("About")

            detailPagerAdapter = DetailPagerAdapter(
                this@CosmosDetailFragment, tableTitles, selectedChain
            )
            viewPager.adapter = detailPagerAdapter
            viewPager.offscreenPageLimit = 4
            viewPager.isUserInputEnabled = false
            tabLayout.bringToFront()

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tableTitles[position]
            }.attach()

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val position = tab?.position ?: 0
                    viewPager.setCurrentItem(position, false)
                    when (tab?.text.toString()) {
                        "Crypto" -> {
                            btnAddToken.visibleOrGone(supportToken)
                            btnAddToken.setImageResource(R.drawable.icon_add_token_explain)
                            noticeType = NoticeType.TOKEN_GITHUB
                        }

                        "NFTs" -> {
                            btnAddToken.setImageResource(R.drawable.icon_nft_request)
                            btnAddToken.visibility = View.VISIBLE
                            noticeType = NoticeType.TOKEN_NFT_GITHUB
                        }

                        else -> {
                            btnAddToken.visibility = View.GONE
                        }
                    }
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

    private fun showNotice(noticeType: NoticeType) {
        NoticeInfoFragment.newInstance(selectedChain, noticeType, null).show(
            requireActivity().supportFragmentManager, NoticeInfoFragment::class.java.name
        )
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }

            accountName.setOnClickListener {
                requireActivity().onBackPressed()
            }

            btnRandom.setOnClickListener {
                CosmostationApp.instance.setRandomBackgroundImage()
                ApplicationViewModel.shared.changeBg(Prefs.background)
            }

            btnAddToken.setOnClickListener {
                showNotice(noticeType)
            }

            btnAccount.setOnClickListener {
                selectedChain.explorerAccount(selectedChain.address)?.let { url ->
                    startActivity(Intent(Intent.ACTION_VIEW, url))
                    Prefs.foreToBack = false
                } ?: run {
                    return@setOnClickListener
                }
            }

            accountLayout.setOnClickListener {
                if (selectedChain is ChainOkt996Keccak) {
                    QrCodeFragment.newInstance(selectedChain).show(
                        requireActivity().supportFragmentManager, QrCodeFragment::class.java.name
                    )

                } else {
                    if (selectedChain.supportEvm) {
                        QrCodeEvmFragment.newInstance(selectedChain).show(
                            requireActivity().supportFragmentManager,
                            QrCodeEvmFragment::class.java.name
                        )

                    } else {
                        QrCodeFragment.newInstance(selectedChain).show(
                            requireActivity().supportFragmentManager,
                            QrCodeFragment::class.java.name
                        )
                    }
                }
            }

            accountValueLayout.setOnClickListener {
                if (selectedChain.supportEvm) {
                    QrCodeEvmFragment.newInstance(selectedChain).show(
                        requireActivity().supportFragmentManager, QrCodeEvmFragment::class.java.name
                    )

                } else {
                    QrCodeFragment.newInstance(selectedChain).show(
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
                        requireContext(), R.color.color_fab_background_dialog
                    )
                } else {
                    tabLayout.elevation = 0f
                    requireActivity().window.statusBarColor = ContextCompat.getColor(
                        requireContext(), R.color.color_transparent
                    )
                }
            }

            fabMenu.isIconAnimated = false

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
            fabStake.setOnClickListener {
                if (selectedChain.cosmosFetcher?.cosmosValidators?.isNotEmpty() == true) {
                    handleOneClickWithDelay(StakeInfoFragment.newInstance(selectedChain), null)

                } else {
                    requireContext().makeToast(R.string.error_wait_moment)
                    fabMenu.close(true)
                    return@setOnClickListener
                }
            }

            fabClaimReward.setOnClickListener {
                if (selectedChain.cosmosFetcher?.cosmosValidators?.isNotEmpty() == true) {
                    if (selectedChain.cosmosFetcher?.rewardAllCoins()?.isEmpty() == true) {
                        requireContext().makeToast(R.string.error_not_reward)
                        return@setOnClickListener
                    }
                    if (selectedChain.cosmosFetcher?.claimableRewards()?.isEmpty() == true) {
                        requireContext().showToast(view, R.string.error_wasting_fee, false)
                        return@setOnClickListener
                    }
                    if (!selectedChain.isTxFeePayable(requireContext())) {
                        requireContext().showToast(view, R.string.error_not_enough_fee, false)
                        return@setOnClickListener
                    }
                    handleOneClickWithDelay(
                        null, ClaimRewardFragment.newInstance(
                            selectedChain, selectedChain.cosmosFetcher?.claimableRewards()
                        )
                    )

                } else {
                    requireContext().makeToast(R.string.error_wait_moment)
                    fabMenu.close(true)
                    return@setOnClickListener
                }
            }

            fabCompounding.setOnClickListener {
                if ((selectedChain.cosmosFetcher?.cosmosValidators?.size ?: 0) > 0) {
                    if (selectedChain.cosmosFetcher?.claimableRewards()?.size == 0) {
                        requireContext().makeToast(R.string.error_not_reward)
                        return@setOnClickListener
                    }
                    if (selectedChain.cosmosFetcher?.rewardAddress != selectedChain.address) {
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
                            selectedChain, selectedChain.cosmosFetcher?.claimableRewards()
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
                    KavaDefiFragment.newInstance(selectedChain), null
                )
            }

            fabDao.setOnClickListener {
                handleOneClickWithDelay(
                    DaoProposalListFragment.newInstance(selectedChain as ChainNeutron), null
                )
            }

            fabVault.setOnClickListener {
                handleOneClickWithDelay(
                    null, VaultSelectFragment.newInstance(selectedChain as ChainNeutron)
                )
            }

            fabDeposit.setOnClickListener {
                handleOneClickWithDelay(
                    null, OktDepositFragment.newInstance(selectedChain)
                )
            }

            fabWithdraw.setOnClickListener {
                if (selectedChain is ChainOkt996Keccak) {
                    if (BigDecimal.ZERO >= (selectedChain as ChainOkt996Keccak).oktFetcher?.oktDepositAmount()) {
                        requireContext().makeToast(R.string.error_no_deposited_asset)
                        return@setOnClickListener
                    }

                } else {
                    if (BigDecimal.ZERO >= (selectedChain as ChainOktEvm).oktFetcher?.oktDepositAmount()) {
                        requireContext().makeToast(R.string.error_no_deposited_asset)
                        return@setOnClickListener
                    }
                }

                handleOneClickWithDelay(
                    null, OktWithdrawFragment.newInstance(selectedChain)
                )
            }

            fabSelectValidator.setOnClickListener {
                if (selectedChain is ChainOkt996Keccak) {
                    if (BigDecimal.ZERO >= (selectedChain as ChainOkt996Keccak).oktFetcher?.oktDepositAmount()) {
                        requireContext().makeToast(R.string.error_no_deposited_asset)
                        return@setOnClickListener
                    }

                } else {
                    if (BigDecimal.ZERO >= (selectedChain as ChainOktEvm).oktFetcher?.oktDepositAmount()) {
                        requireContext().makeToast(R.string.error_no_deposited_asset)
                        return@setOnClickListener
                    }
                }

                handleOneClickWithDelay(
                    null, OktSelectValidatorFragment.newInstance(selectedChain)
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

    class DetailPagerAdapter(
        fragment: Fragment,
        private val tabTitles: List<String>,
        private val selectedChain: BaseChain
    ) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int {
            return tabTitles.size
        }

        override fun createFragment(position: Int): Fragment {
            return when (tabTitles[position]) {
                "Crypto" -> CoinFragment.newInstance(selectedChain)
                "NFTs" -> NftFragment.newInstance(selectedChain)
                "Receive" -> ReceiveFragment.newInstance(selectedChain)
                "History" -> HistoryFragment.newInstance(selectedChain)
                "Ecosystem" -> EcoSystemFragment.newInstance(selectedChain)
                "About" -> AboutFragment.newInstance(selectedChain)
                else -> throw IllegalArgumentException("Invalid tab position")
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        ApplicationViewModel.shared.fetchedTotalResult.removeObservers(viewLifecycleOwner)
        handler.removeCallbacks(starEvmAddressAnimation)
    }
}