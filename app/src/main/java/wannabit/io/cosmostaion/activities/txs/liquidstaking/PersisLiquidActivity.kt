package wannabit.io.cosmostaion.activities.txs.liquidstaking

import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseBroadCastActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivityTxStepBinding
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment
import wannabit.io.cosmostaion.fragment.StepMemoFragment
import wannabit.io.cosmostaion.fragment.txs.liquidstaking.PersisLiquidStep0Fragment
import wannabit.io.cosmostaion.fragment.txs.liquidstaking.PersisLiquidStep3Fragment

class PersisLiquidActivity : BaseBroadCastActivity() {

    private lateinit var binding: ActivityTxStepBinding

    private lateinit var mPageAdapter: PersisLiquidPageAdapter
    val mInputDenom: String
        get() {
            return intent.getStringExtra("inputDenom") ?: ""
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTxStepBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarTitle.text = getText(R.string.str_liquid_staking)
        mAccount = baseDao.onSelectAccount(baseDao.lastUser)
        mBaseChain = BaseChain.getChain(mAccount.baseChain)
        mChainConfig = ChainFactory.getChain(mBaseChain)
        mTxType = intent.getIntExtra("txType", -1)

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mPageAdapter = PersisLiquidPageAdapter(this)
        binding.viewPager.adapter = mPageAdapter

        binding.viewPager.setCurrentItem(0, false)
        binding.viewPager.offscreenPageLimit = 3
        onSetPageSelected()
        binding.rootView.setOnClickListener { onHideKeyboard() }
    }

    private fun onSetPageSelected() {
        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                mPageAdapter.fragmentList[position].onRefreshTab()
                when (position) {
                    0 -> {
                        binding.sendStep.setImageDrawable(ContextCompat.getDrawable(this@PersisLiquidActivity, R.drawable.step_4_img_1))
                        binding.sendStepMsg.text = getString(R.string.str_authz_send_step_1)
                    }
                    1 -> {
                        binding.sendStep.setImageDrawable(ContextCompat.getDrawable(this@PersisLiquidActivity, R.drawable.step_4_img_2))
                        binding.sendStepMsg.text = getString(R.string.str_tx_step_memo)
                    }
                    2 -> {
                        binding.sendStep.setImageDrawable(ContextCompat.getDrawable(this@PersisLiquidActivity, R.drawable.step_4_img_3))
                        binding.sendStepMsg.text = getString(R.string.str_tx_step_fee)
                    }
                    else -> {
                        binding.sendStep.setImageDrawable(ContextCompat.getDrawable(this@PersisLiquidActivity, R.drawable.step_4_img_4))
                        binding.sendStepMsg.text = getString(R.string.str_tx_step_confirm)
                    }
                }
                super.onPageSelected(position)
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

    override fun onBackPressed() {
        onHideKeyboard()
        if (binding.viewPager.currentItem > 0) {
            binding.viewPager.setCurrentItem(binding.viewPager.currentItem - 1, true)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNextStep() {
        if (binding.viewPager.currentItem < mPageAdapter.itemCount - 1) {
            onHideKeyboard()
            binding.viewPager.setCurrentItem(binding.viewPager.currentItem + 1, true)
        }
    }

    override fun onBeforeStep() {
        if (binding.viewPager.currentItem > 0) {
            onHideKeyboard()
            binding.viewPager.setCurrentItem(binding.viewPager.currentItem - 1, true)
        } else {
            onBackPressed()
        }
    }

    class PersisLiquidPageAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        val fragmentList = listOf(
            PersisLiquidStep0Fragment(),
            StepMemoFragment.newInstance(),
            StepFeeSetFragment.newInstance(),
            PersisLiquidStep3Fragment()
        )

        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): BaseFragment {
            return fragmentList[position]
        }
    }
}