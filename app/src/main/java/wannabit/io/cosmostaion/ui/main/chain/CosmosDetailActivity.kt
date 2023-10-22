package wannabit.io.cosmostaion.ui.main.chain

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.toMoveBack
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.repository.tx.SendRepositoryImpl
import wannabit.io.cosmostaion.databinding.ActivityCosmosDetailBinding
import wannabit.io.cosmostaion.ui.dialog.qr.QrCodeFragment
import wannabit.io.cosmostaion.ui.viewmodel.tx.SendViewModel
import wannabit.io.cosmostaion.ui.viewmodel.tx.SendViewModelProviderFactory

class CosmosDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityCosmosDetailBinding

    private lateinit var pagerAdapter: AccountPageAdapter

    private var selectedPosition: Int = -1
    private lateinit var selectedChain: CosmosLine

    private lateinit var sendViewModel: SendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCosmosDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initData()
        initTab()
        initViewModel()
        clickAction()
    }

    private fun initView() {
        binding.apply {
            fabMenu.menuIconView.setImageResource(R.drawable.icon_fab)
            ContextCompat.getDrawable(this@CosmosDetailActivity, R.drawable.icon_governance)?.let {
                it.setBounds(100, 100, 100, 100)
                fabVote.setImageDrawable(it)
            }
        }
    }

    private fun initData() {
        binding.apply {
            val baseAccount = BaseData.baseAccount
            selectedPosition = intent.getIntExtra("selectPosition", -1)

            baseAccount?.let {
                accountName.text = baseAccount.name
                selectedChain = baseAccount.displayCosmosLineChains[selectedPosition]
                accountAddress.text = selectedChain.address
                accountValue.text = formatAssetValue(selectedChain.allAssetValue())
            }
        }
    }

    private fun initTab() {
        binding.apply {
            pagerAdapter =
                AccountPageAdapter(this@CosmosDetailActivity, selectedChain, selectedPosition)
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

    private fun initViewModel() {
        val sendRepository = SendRepositoryImpl()
        val sendViewModelProviderFactory = SendViewModelProviderFactory(sendRepository)
        sendViewModel = ViewModelProvider(
            this,
            sendViewModelProviderFactory
        )[SendViewModel::class.java]
    }

    private fun clickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }

            btnAccount.setOnClickListener {
                val accountUrl =
                    CosmostationConstants.EXPLORER_BASE_URL + "/" + selectedChain.apiName + "/" + selectedChain.address
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(accountUrl)))
            }

            accountAddress.setOnClickListener {
                val bottomSheet = QrCodeFragment(selectedChain)
                bottomSheet.show(supportFragmentManager, QrCodeFragment::class.java.name)
            }

            fabMenu.setOnMenuToggleListener { opened ->
                fabMenu.bringToFront()
                backdropLayout.visibleOrGone(opened)
                if (opened) {
                    tabLayout.elevation = 0.1f
                    window.statusBarColor = ContextCompat.getColor(
                        this@CosmosDetailActivity,
                        R.color.color_background_dialog
                    )
                } else {
                    tabLayout.elevation = 0f
                    window.statusBarColor =
                        ContextCompat.getColor(this@CosmosDetailActivity, R.color.color_transparent)
                }
            }

            backdropLayout.setOnClickListener {
                fabMenu.close(true)
                backdropLayout.visibility = View.GONE
                tabLayout.elevation = 0f
                window.statusBarColor =
                    ContextCompat.getColor(this@CosmosDetailActivity, R.color.color_transparent)
            }

            fabVote.setOnClickListener {
                fabMenu.close(true)
            }

            fabClaimReward.setOnClickListener {
                fabMenu.close(true)
            }

            fabCompounding.setOnClickListener {
                fabMenu.close(true)
            }

            fabStake.setOnClickListener {
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

    override fun onBackPressed() {
        super.onBackPressed()
        toMoveBack()
    }
}