package wannabit.io.cosmostaion.ui.main

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.databinding.ActivityMainBinding
import wannabit.io.cosmostaion.ui.dialog.account.AccountSelectFragment
import wannabit.io.cosmostaion.ui.main.edit.ChainEditFragment
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModelProviderFactory

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var walletViewModel: WalletViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initView()
        setupViewModels()
        clickAction()
    }

    override fun onResume() {
        super.onResume()
        recreateView()
    }

    private fun initViewModel() {
        val walletRepository = WalletRepositoryImpl()
        val walletViewModelProviderFactory = WalletViewModelProviderFactory(walletRepository)
        walletViewModel =
            ViewModelProvider(this, walletViewModelProviderFactory)[WalletViewModel::class.java]
    }

    private fun updateView() {
        binding.accountName.text = BaseData.baseAccount?.name
    }

    private fun setupViewModels() {
        ApplicationViewModel.shared.currentAccountResult.observe(this) {
            updateView()
        }
    }

    private fun initView() {
        CoroutineScope(Dispatchers.IO).launch {
            BaseData.baseAccount?.initAccount()
        }
        updateView()
        val mainViewPagerAdapter = MainViewPageAdapter(this)
        binding.apply {
            mainViewPager.adapter = mainViewPagerAdapter
            mainViewPager.setCurrentItem(intent.getIntExtra("page", 0), false)
            mainViewPager.offscreenPageLimit = 2
            mainViewPager.isUserInputEnabled = false

            val tabLayoutMediator = TabLayoutMediator(tabLayout, mainViewPager) { tab, position ->
                when (position) {
                    0 -> { tab.setIcon(R.drawable.icon_wallet) }
                    1 -> { tab.setIcon(R.drawable.icon_service) }
                    2 -> { tab.setIcon(R.drawable.icon_setting) }
                }
            }
            tabLayoutMediator.attach()

            for (i in 0 until tabLayout.tabCount) {
                val tab = tabLayout.getTabAt(i)
                if (i == 0) {
                    tabIconSetColor(tab, true)
                } else {
                    tabIconSetColor(tab, false)
                }
            }

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val position = tab?.position ?: 0
                    mainViewPager.setCurrentItem(position, false)
                    tabIconSetColor(tab, true)

                    if (position == 1 || position == 2) btnEdit.visibility = View.GONE
                    else btnEdit.visibility = View.VISIBLE
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tabIconSetColor(tab, false)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun tabIconSetColor(tab: TabLayout.Tab?, isSelected: Boolean) {
        if (isSelected) {
            tab?.icon?.colorFilter = PorterDuffColorFilter(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.color_base01
                ), PorterDuff.Mode.SRC_IN
            )
        } else {
            tab?.icon?.colorFilter = PorterDuffColorFilter(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.color_base03
                ), PorterDuff.Mode.SRC_IN
            )
        }
    }

    private fun clickAction() {
        var isClickable = true
        binding.apply {
            btnEdit.setOnClickListener {
                val bottomSheet = ChainEditFragment()
                if (isClickable) {
                    isClickable = false
                    bottomSheet.show(supportFragmentManager, ChainEditFragment::class.java.name)

                    Handler().postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            accountLayout.setOnClickListener {
                val bottomSheet = AccountSelectFragment()
                if (isClickable) {
                    isClickable = false
                    bottomSheet.show(supportFragmentManager, AccountSelectFragment::class.java.name)

                    Handler().postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }
        }
    }

    private fun recreateView() {
        ApplicationViewModel.shared.txRecreateResult.observe(this) { response ->
            BaseData.baseAccount?.displayCosmosLineChains?.forEach {
                it.fetched = false
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        val mainViewPagerAdapter = MainViewPageAdapter(this)
        binding.apply {
            mainViewPager.adapter = mainViewPagerAdapter
            mainViewPager.setCurrentItem(intent.getIntExtra("page", 0), false)
            mainViewPager.offscreenPageLimit = 2
            mainViewPager.isUserInputEnabled = false
            mainViewPagerAdapter.notifyDataSetChanged()
        }
    }

    class MainViewPageAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        private val mainFragments =
            mutableListOf(DashboardFragment(), ServiceFragment(), SettingFragment())

        override fun getItemCount(): Int {
            return mainFragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return mainFragments[position]
        }
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount <= 0) {
            moveTaskToBack(true)
            return
        }
        super.onBackPressed()
    }
}