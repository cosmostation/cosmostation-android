package wannabit.io.cosmostaion.activities.txs.neutron.defi

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayoutMediator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivityNeutronDefiBinding
import wannabit.io.cosmostaion.databinding.ViewTabMyvalidatorBinding
import wannabit.io.cosmostaion.fragment.txs.neutron.defi.NeutronPoolFragment
import wannabit.io.cosmostaion.fragment.txs.neutron.defi.NeutronSwapFragment
import wannabit.io.cosmostaion.model.viewModel.NeutronViewModel

class NeutronDefiActivity : BaseActivity() {

    private lateinit var binding: ActivityNeutronDefiBinding

    private lateinit var mPageAdapter: NeutronDefiPageAdapter

    private val neutronViewModel: NeutronViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNeutronDefiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAccount = baseDao.onSelectAccount(baseDao.lastUser)
        mChainConfig = ChainFactory.getChain(BaseChain.getChain(mAccount.baseChain))

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mPageAdapter = NeutronDefiPageAdapter(this)
        binding.labViewPager.adapter = mPageAdapter

        binding.labViewPager.setCurrentItem(0, false)
        binding.labViewPager.offscreenPageLimit = 1

        createTab()
        onSetPageSelected()
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
        binding.apply {
            TabLayoutMediator(labTab, labViewPager) { tab, position ->
                val tabBinding = ViewTabMyvalidatorBinding.inflate(layoutInflater)
                when (position) {
                    0 -> tabBinding.tabItemText.setText(R.string.str_swap)
                    else -> tabBinding.tabItemText.setText(R.string.str_pool)
                }
                tabBinding.tabItemText.setTextColor(
                    ContextCompat.getColorStateList(
                        this@NeutronDefiActivity, mChainConfig.chainTabColor()
                    )
                )
                tab.customView = tabBinding.root
                labTab.setSelectedTabIndicatorColor(
                    ContextCompat.getColor(
                        this@NeutronDefiActivity, mChainConfig.chainColor()
                    )
                )
            }.attach()
        }
    }

    class NeutronDefiPageAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        val fragments = listOf(NeutronSwapFragment(), NeutronPoolFragment())

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): BaseFragment {
            return fragments[position]
        }
    }
}