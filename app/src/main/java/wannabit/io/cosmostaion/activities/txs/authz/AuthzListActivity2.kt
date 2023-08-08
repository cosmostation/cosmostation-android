package wannabit.io.cosmostaion.activities.txs.authz

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivityAuthzList2Binding
import wannabit.io.cosmostaion.databinding.ViewTabMyvalidatorBinding
import wannabit.io.cosmostaion.fragment.txs.authz.grantee.AuthzGranteeFragment
import wannabit.io.cosmostaion.fragment.txs.authz.granter.AuthzGranterFragment
import wannabit.io.cosmostaion.model.factory.authz.AuthzViewModelProviderFactory
import wannabit.io.cosmostaion.model.repository.authz.AuthzRepositoryImpl
import wannabit.io.cosmostaion.model.viewModel.authz.AuthzViewModel

class AuthzListActivity2 : BaseActivity() {

    private lateinit var binding: ActivityAuthzList2Binding
    private lateinit var mPageAdapter: AuthzListPageAdapter

    private lateinit var authzViewModel: AuthzViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthzList2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        mAccount = baseDao.onSelectAccount(baseDao.lastUser)
        mChainConfig = ChainFactory.getChain(BaseChain.getChain(mAccount.baseChain))

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mPageAdapter = AuthzListPageAdapter(this)
        binding.labViewPager.adapter = mPageAdapter

        binding.labViewPager.setCurrentItem(0, false)
        binding.labViewPager.offscreenPageLimit = 1
        binding.labViewPager.isUserInputEnabled = false

        val authzRepository = AuthzRepositoryImpl()
        val authzViewModelProviderFactory = AuthzViewModelProviderFactory(authzRepository)
        authzViewModel = ViewModelProvider(this, authzViewModelProviderFactory)[AuthzViewModel::class.java]

        onSetPageSelected()
        createTab()
    }

    private fun onSetPageSelected() {
        binding.labViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mPageAdapter.fragments[position].onRefreshTab()
            }
        })
    }

    private fun createTab() {
        binding.apply {
            TabLayoutMediator(labTab, labViewPager) { tab, position ->
                val tabBinding = ViewTabMyvalidatorBinding.inflate(layoutInflater)
                when (position) {
                    0 -> tabBinding.tabItemText.text = "Master"
                    else -> tabBinding.tabItemText.text = "Slave"
                }
                tabBinding.tabItemText.setTextColor(
                    ContextCompat.getColorStateList(
                        this@AuthzListActivity2, mChainConfig.chainTabColor()
                    )
                )
                tab.customView = tabBinding.root
                labTab.setSelectedTabIndicatorColor(
                    ContextCompat.getColor(
                        this@AuthzListActivity2, mChainConfig.chainColor()
                    )
                )
            }.attach()
        }
    }

    class AuthzListPageAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        val fragments = listOf(AuthzGranterFragment(), AuthzGranteeFragment())

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): BaseFragment {
            return fragments[position]
        }
    }
}