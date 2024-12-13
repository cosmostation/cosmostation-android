package wannabit.io.cosmostaion.ui.main.chain.evm

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
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentEvmDetailBinding
import wannabit.io.cosmostaion.ui.main.CosmostationApp
import wannabit.io.cosmostaion.ui.main.chain.cosmos.CosmosDetailFragment.DetailPagerAdapter
import wannabit.io.cosmostaion.ui.qr.QrCodeEvmFragment

class EvmDetailFragment : Fragment() {

    private var _binding: FragmentEvmDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var detailPagerAdapter: DetailPagerAdapter

    private lateinit var selectedEvmChain: BaseChain

    companion object {
        @JvmStatic
        fun newInstance(selectedEvmChain: BaseChain): EvmDetailFragment {
            val args = Bundle().apply {
                putParcelable("selectedEvmChain", selectedEvmChain)
            }
            val fragment = EvmDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEvmDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        updateTokenValue()
        initTab()
        setUpClickAction()
        setUpObserve()
    }

    private fun initData() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedEvmChain", BaseChain::class.java)
                    ?.let { selectedEvmChain = it }
            } else {
                (arguments?.getParcelable("selectedEvmChain") as? BaseChain)?.let {
                    selectedEvmChain = it
                }
            }

            BaseData.baseAccount?.let { account ->
                accountName.text = account.name
                accountAddress.text = selectedEvmChain.evmAddress

                if (Prefs.hideValue) {
                    accountValue.text = "✱✱✱✱✱"
                    accountValue.textSize = 18f
                    btnHide.setImageResource(R.drawable.icon_hide)
                } else {
                    accountValue.text = formatAssetValue(selectedEvmChain.allValue(false))
                    accountValue.textSize = 24f
                    btnHide.setImageResource(R.drawable.icon_not_hide)
                }
                btnHide.setColorFilter(
                    ContextCompat.getColor(requireContext(), R.color.color_base03),
                    PorterDuff.Mode.SRC_IN
                )
            }
        }
    }

    private fun updateTokenValue() {
        if (isAdded) {
            requireActivity().runOnUiThread {
                binding.apply {
                    if (Prefs.hideValue) {
                        accountValue.text = "✱✱✱✱✱"
                        accountValue.textSize = 18f
                        btnHide.setImageResource(R.drawable.icon_hide)
                    } else {
                        accountValue.text = formatAssetValue(selectedEvmChain.allValue(false))
                        accountValue.textSize = 24f
                        btnHide.setImageResource(R.drawable.icon_not_hide)
                    }
                }
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
            updateTokenValue()
        }
    }

    private fun initTab() {
        binding.apply {
            btnAddToken.visibility = View.VISIBLE
            val tableTitles = mutableListOf<String>()
            tableTitles.add("Assets")
            tableTitles.add("Receive")
            if (!selectedEvmChain.isTestnet) tableTitles.add("History")
            if (selectedEvmChain.isSupportMobileDapp()) tableTitles.add("Ecosystem")
            tableTitles.add("About")

            detailPagerAdapter = DetailPagerAdapter(
                this@EvmDetailFragment, tableTitles, selectedEvmChain
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
                    when (tab?.text.toString()) {
                        "Assets" -> {
                            btnAddToken.visibility = View.VISIBLE
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

            var isClickable = true
            btnAddToken.setOnClickListener {
                if (isClickable) {
                    isClickable = false

                    val assetFragment = detailPagerAdapter.getAssetFragmentInstance()
                    assetFragment?.showTokenList()
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 300)
            }

            btnAccount.setOnClickListener {
                selectedEvmChain.explorerAccount(selectedEvmChain.evmAddress)?.let { url ->
                    startActivity(Intent(Intent.ACTION_VIEW, url))
                    Prefs.foreToBack = false

                } ?: run {
                    return@setOnClickListener
                }
            }

            accountLayout.setOnClickListener {
                QrCodeEvmFragment.newInstance(selectedEvmChain).show(
                    requireActivity().supportFragmentManager, QrCodeEvmFragment::class.java.name
                )
            }

            accountValueLayout.setOnClickListener {
                QrCodeEvmFragment.newInstance(selectedEvmChain).show(
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
                    accountValue.text = formatAssetValue(selectedEvmChain.allValue(false))
                    accountValue.textSize = 24f
                    btnHide.setImageResource(R.drawable.icon_not_hide)
                }
                ApplicationViewModel.shared.hideValue()
            }
        }
    }

    class DetailPagerAdapter(
        fragment: Fragment,
        private val tabTitles: List<String>,
        private val selectedEvmChain: BaseChain
    ) : FragmentStateAdapter(fragment) {

        private val fragmentCache = mutableMapOf<Int, Fragment>()

        override fun getItemCount(): Int {
            return tabTitles.size
        }

        override fun createFragment(position: Int): Fragment {
            val fragment = when (tabTitles[position]) {
                "Assets" -> AssetFragment.newInstance(selectedEvmChain)
                "Receive" -> EvmReceiveFragment.newInstance(selectedEvmChain)
                "History" -> EvmHistoryFragment.newInstance(selectedEvmChain)
                "Ecosystem" -> EvmEcoSystemFragment.newInstance(selectedEvmChain)
                "About" -> EvmAboutFragment.newInstance(selectedEvmChain)
                else -> throw IllegalArgumentException("Invalid tab position")
            }
            fragmentCache[position] = fragment
            return fragment
        }

        fun getAssetFragmentInstance(): AssetFragment? {
            val position = tabTitles.indexOf("Assets")
            return fragmentCache[position] as? AssetFragment
        }
    }

    override fun onDestroyView() {
        _binding = null
        ApplicationViewModel.shared.fetchedTotalResult.removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }
}