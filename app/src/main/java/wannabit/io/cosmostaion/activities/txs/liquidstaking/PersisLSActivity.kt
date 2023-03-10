package wannabit.io.cosmostaion.activities.txs.liquidstaking

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayoutMediator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivityLiquidListBinding
import wannabit.io.cosmostaion.databinding.ViewTabMyvalidatorBinding
import wannabit.io.cosmostaion.fragment.txs.liquidstaking.PersisLSFragment
import wannabit.io.cosmostaion.fragment.txs.liquidstaking.PersisLUSFragment
import wannabit.io.cosmostaion.fragment.txs.liquidstaking.PersisViewModel

class PersisLSActivity : BaseActivity() {

    private lateinit var binding: ActivityLiquidListBinding

    private lateinit var mPageAdapter: PersisLSPageAdapter
    private val persisViewModel: PersisViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiquidListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarTitle.text = getText(R.string.str_liquid_staking)
        mAccount = baseDao.onSelectAccount(baseDao.lastUser)
        mChainConfig = ChainFactory.getChain(BaseChain.getChain(mAccount.baseChain))

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mPageAdapter = PersisLSPageAdapter(this)
        binding.labViewPager.adapter = mPageAdapter

        binding.labViewPager.setCurrentItem(0, false)
        binding.labViewPager.offscreenPageLimit = 1

        createTab()
        onSetPageSelected()

        persisViewModel.loadCValue(mChainConfig.baseChain())
    }

    private fun onSetPageSelected() {
        binding.labViewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mPageAdapter.fragments[position].onRefreshTab()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun createTab() {
        TabLayoutMediator(binding.labTab, binding.labViewPager) { tab, position ->
            val tabBinding = ViewTabMyvalidatorBinding.inflate(layoutInflater)
            when (position) {
                0 -> tabBinding.tabItemText.setText(R.string.title_liquid_staking)
                else -> tabBinding.tabItemText.setText(R.string.title_liquid_unstaking)
            }
            tabBinding.tabItemText.setTextColor(
                ContextCompat.getColorStateList(
                    this, mChainConfig.chainTabColor()
                )
            )
            tab.customView = tabBinding.root
            binding.labTab.setSelectedTabIndicatorColor(
                ContextCompat.getColor(
                    this, mChainConfig.chainColor()
                )
            )
        }.attach()
    }

    class PersisLSPageAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        val fragments = listOf(PersisLSFragment(), PersisLUSFragment())

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): BaseFragment {
            return fragments[position]
        }
    }
}