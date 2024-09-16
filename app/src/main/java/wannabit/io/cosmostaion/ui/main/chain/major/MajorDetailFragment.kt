package wannabit.io.cosmostaion.ui.main.chain.major

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
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.toMoveFragment
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentMajorDetailBinding
import wannabit.io.cosmostaion.ui.main.CosmostationApp
import wannabit.io.cosmostaion.ui.qr.QrCodeEvmFragment
import wannabit.io.cosmostaion.ui.tx.info.major.SuiStakingInfoFragment
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel

class MajorDetailFragment : Fragment() {

    private var _binding: FragmentMajorDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var detailPagerAdapter: DetailPagerAdapter

    private lateinit var selectedChain: BaseChain

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): MajorDetailFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = MajorDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMajorDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initTab()
        setUpClickAction()
    }

    private fun initData() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedChain", BaseChain::class.java)
                    ?.let { selectedChain = it }
            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
            }

            BaseData.baseAccount?.let { account ->
                accountName.text = account.name
                accountAddress.text = selectedChain.mainAddress

                if (Prefs.hideValue) {
                    accountValue.text = "✱✱✱✱✱"
                    accountValue.textSize = 18f
                    btnHide.setImageResource(R.drawable.icon_hide)
                } else {
                    accountValue.text = formatAssetValue(selectedChain.allValue(false))
                    accountValue.textSize = 24f
                    btnHide.setImageResource(R.drawable.icon_not_hide)
                }
                btnHide.setColorFilter(
                    ContextCompat.getColor(requireContext(), R.color.color_base03),
                    PorterDuff.Mode.SRC_IN
                )
            }

            fabMenu.menuIconView.setImageResource(R.drawable.icon_floating)
            fabMenu.isIconAnimated = false
            fabMenu.visibleOrGone(selectedChain.supportStaking)
        }
    }

    private fun initTab() {
        binding.apply {
            val tableTitles = mutableListOf<String>()
            tableTitles.add("Crypto")

            if (selectedChain is ChainSui) tableTitles.add("NFTs")

            tableTitles.add("Receive")
            tableTitles.add("History")

            if (selectedChain.isEcosystem()) tableTitles.add("Ecosystem")

            tableTitles.add("About")

            detailPagerAdapter = DetailPagerAdapter(
                requireActivity(), tableTitles, selectedChain
            )
            viewPager.adapter = detailPagerAdapter
            viewPager.offscreenPageLimit = 1
            viewPager.isUserInputEnabled = false
            tabLayout.bringToFront()

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tableTitles[position]
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

            btnAccount.setOnClickListener {
                selectedChain.explorerAccount(selectedChain.mainAddress)?.let { url ->
                    startActivity(Intent(Intent.ACTION_VIEW, url))
                    Prefs.foreToBack = false

                } ?: run {
                    return@setOnClickListener
                }
            }

            accountLayout.setOnClickListener {
                QrCodeEvmFragment.newInstance(selectedChain).show(
                    requireActivity().supportFragmentManager, QrCodeEvmFragment::class.java.name
                )
            }

            accountValueLayout.setOnClickListener {
                QrCodeEvmFragment.newInstance(selectedChain).show(
                    requireActivity().supportFragmentManager, QrCodeEvmFragment::class.java.name
                )
            }

            btnHide.setOnClickListener {
                Prefs.hideValue = !Prefs.hideValue
                if (Prefs.hideValue) {
                    accountValue.text = "✱✱✱✱✱"
                    accountValue.textSize = 18f
                    btnHide.setImageResource(R.drawable.icon_hide)
                } else {
                    accountValue.text = formatAssetValue(selectedChain.allValue(false))
                    accountValue.textSize = 24f
                    btnHide.setImageResource(R.drawable.icon_not_hide)
                }
                ApplicationViewModel.shared.hideValue()
            }

            fabMenu.setOnMenuButtonClickListener {
                handleOneClickWithDelay(SuiStakingInfoFragment.newInstance(selectedChain), null)
            }
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
                    this@MajorDetailFragment, fragment, fragment::class.java.name
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
        fragmentActivity: FragmentActivity,
        private val tabTitles: List<String>,
        private val selectedChain: BaseChain
    ) : FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int {
            return tabTitles.size
        }

        override fun createFragment(position: Int): Fragment {
            return when (tabTitles[position]) {
                "Crypto" -> MajorCryptoFragment.newInstance(selectedChain)
                "NFTs" -> MajorNftFragment.newInstance(selectedChain)
                "Receive" -> MajorReceiveFragment.newInstance(selectedChain)
                "History" -> MajorHistoryFragment.newInstance(selectedChain)
                "Ecosystem" -> MajorEcosystemFragment.newInstance(selectedChain)
                "About" -> MajorAboutFragment.newInstance(selectedChain)
                else -> throw IllegalArgumentException("Invalid tab position")
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        ApplicationViewModel.shared.fetchedTotalResult.removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }
}