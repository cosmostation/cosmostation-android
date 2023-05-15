package wannabit.io.cosmostaion.activities.txs.neutron.defi

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseBroadCastActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivityNeutronSwapBinding
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment
import wannabit.io.cosmostaion.fragment.StepMemoFragment
import wannabit.io.cosmostaion.fragment.txs.neutron.defi.swap.NeutronSwapStep0Fragment
import wannabit.io.cosmostaion.fragment.txs.neutron.defi.swap.NeutronSwapStep3Fragment
import wannabit.io.cosmostaion.network.res.neutron.Pair
import wannabit.io.cosmostaion.network.res.neutron.ResPairData
import java.math.BigDecimal

class NeutronSwapActivity : BaseBroadCastActivity() {

    private lateinit var binding: ActivityNeutronSwapBinding

    private lateinit var mPageAdapter: ProposalPageAdapter

    var selectedPool: ResPairData? = null
    var inputCoin: Pair? = null
    var outputCoin: Pair? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNeutronSwapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        loadData()
    }

    private fun initView() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mPageAdapter = ProposalPageAdapter(this)
        binding.viewPager.adapter = mPageAdapter
        binding.viewPager.isUserInputEnabled = false

        binding.viewPager.setCurrentItem(0, false)
        binding.viewPager.offscreenPageLimit = 3
        onSetPageSelected()
        binding.rootView.setOnClickListener { onHideKeyboard() }
    }

    private fun loadData() {
        mAccount = baseDao.onSelectAccount(baseDao.lastUser)
        mBaseChain = BaseChain.getChain(mAccount.baseChain)
        mChainConfig = ChainFactory.getChain(mBaseChain)
        mTxType = BaseConstant.CONST_PW_TX_NEUTRON_SWAP

        intent.apply {
            selectedPool = getParcelableExtra("selectedPool")
            inputCoin = getParcelableExtra("inputCoin")
            outputCoin = getParcelableExtra("outputCoin")
        }
    }

    private fun onSetPageSelected() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                mPageAdapter.fragmentList[position].onRefreshTab()
                when (position) {
                    0 -> {
                        binding.step.setImageDrawable(ContextCompat.getDrawable(this@NeutronSwapActivity, R.drawable.step_4_img_1))
                        binding.stepMsg.text = getString(R.string.str_swap_step_0)
                    }
                    1 -> {
                        binding.step.setImageDrawable(ContextCompat.getDrawable(this@NeutronSwapActivity, R.drawable.step_4_img_2))
                        binding.stepMsg.text = getString(R.string.str_tx_step_memo)
                    }
                    2 -> {
                        binding.step.setImageDrawable(ContextCompat.getDrawable(this@NeutronSwapActivity, R.drawable.step_4_img_3))
                        binding.stepMsg.text = getString(R.string.str_tx_step_fee)
                    }
                    else -> {
                        binding.step.setImageDrawable(ContextCompat.getDrawable(this@NeutronSwapActivity, R.drawable.step_4_img_4))
                        binding.stepMsg.text = getString(R.string.str_tx_step_confirm)
                    }
                }
                super.onPageSelected(position)
            }
        })
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

    class ProposalPageAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        val fragmentList = listOf(
            NeutronSwapStep0Fragment(), StepMemoFragment.newInstance(), StepFeeSetFragment.newInstance(), NeutronSwapStep3Fragment()
        )

        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): BaseFragment {
            return fragmentList[position]
        }
    }
}