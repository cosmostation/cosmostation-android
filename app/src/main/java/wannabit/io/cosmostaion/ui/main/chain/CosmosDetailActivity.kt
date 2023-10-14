package wannabit.io.cosmostaion.ui.main.chain

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.ActivityCosmosDetailBinding
import wannabit.io.cosmostaion.ui.dialog.qr.QrCodeFragment

class CosmosDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityCosmosDetailBinding

    private lateinit var pagerAdapter: AccountPageAdapter

    private var selectedPosition: Int = -1
    private lateinit var selectedChain: CosmosLine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCosmosDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initTab()
        onClickAction()
    }

    private fun initData() {
        binding.apply {
            fabMenu.menuIconView.setImageResource(R.drawable.icon_fab)
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
            pagerAdapter = AccountPageAdapter(this@CosmosDetailActivity, selectedChain, selectedPosition)
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
        }
    }

    private fun onClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }

            btnAccount.setOnClickListener {
                val accountUrl = CosmostationConstants.EXPLORER_BASE_URL + "/" + selectedChain.apiName + "/" + selectedChain.address
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
                    window.statusBarColor = ContextCompat.getColor(this@CosmosDetailActivity, R.color.color_background_dialog)
                } else {
                    tabLayout.elevation = 0f
                    window.statusBarColor = ContextCompat.getColor(this@CosmosDetailActivity, R.color.color_transparent)
                }
            }

            backdropLayout.setOnClickListener {
                fabMenu.close(true)
                backdropLayout.visibility = View.GONE
                tabLayout.elevation = 0f
                window.statusBarColor = ContextCompat.getColor(this@CosmosDetailActivity, R.color.color_transparent)
            }

            fabVote.setOnClickListener {

            }

            fabClaimReward.setOnClickListener {

            }

            fabStake.setOnClickListener {

            }

            fabReceive.setOnClickListener {
                val bottomSheet = QrCodeFragment(selectedChain)
                bottomSheet.show(supportFragmentManager, QrCodeFragment::class.java.name)
                fabMenu.close(true)
            }

            fabSend.setOnClickListener {

            }
        }
    }

    class AccountPageAdapter(fragmentActivity: FragmentActivity, selectedChain: CosmosLine, selectedPosition: Int) : FragmentStateAdapter(fragmentActivity) {
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
}