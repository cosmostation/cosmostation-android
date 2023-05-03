package wannabit.io.cosmostaion.activities.txs.neutron.dao

import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseBroadCastActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivityDaoProposalBinding
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment
import wannabit.io.cosmostaion.fragment.StepMemoFragment
import wannabit.io.cosmostaion.fragment.txs.neutron.DaoVoteStep0Fragment
import wannabit.io.cosmostaion.fragment.txs.neutron.DaoVoteStep3Fragment
import wannabit.io.cosmostaion.network.res.neutron.ProposalData
import wannabit.io.cosmostaion.network.res.neutron.ProposalModule

class DaoProposalActivity : BaseBroadCastActivity() {

    private lateinit var binding: ActivityDaoProposalBinding

    private lateinit var mPageAdapter: ProposalPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaoProposalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mTxType = intent.getIntExtra("txType", -1)
        mProposalModule = Gson().fromJson(intent.getStringExtra("proposalModule"), ProposalModule::class.java)
        mProposalData = Gson().fromJson(intent.getStringExtra("proposalData"), ProposalData::class.java)

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

        initView()
    }

    private fun initView() {
        mAccount = baseDao.onSelectAccount(baseDao.lastUser)
        mBaseChain = BaseChain.getChain(mAccount.baseChain)
        mChainConfig = ChainFactory.getChain(mBaseChain)

        binding.apply {
            when (mTxType) {
                BaseConstant.CONST_PW_TX_DAO_SINGLE_PROPOSAL -> toolbarTitle.text = getString(R.string.str_dao_single_vote)
                BaseConstant.CONST_PW_TX_DAO_MULTI_PROPOSAL -> toolbarTitle.text = getString(R.string.str_dao_multi_vote)
                BaseConstant.CONST_PW_TX_DAO_OVERRULE_PROPOSAL -> toolbarTitle.text = getString(R.string.str_dao_overrule_vote)
            }
        }
    }

    private fun onSetPageSelected() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                mPageAdapter.fragmentList[position].onRefreshTab()
                when (position) {
                    0 -> {
                        binding.sendStep.setImageDrawable(ContextCompat.getDrawable(this@DaoProposalActivity, R.drawable.step_4_img_1))
                        binding.sendStepMsg.text = getString(R.string.str_vote_step_0)
                    }
                    1 -> {
                        binding.sendStep.setImageDrawable(ContextCompat.getDrawable(this@DaoProposalActivity, R.drawable.step_4_img_2))
                        binding.sendStepMsg.text = getString(R.string.str_tx_step_memo)
                    }
                    2 -> {
                        binding.sendStep.setImageDrawable(ContextCompat.getDrawable(this@DaoProposalActivity, R.drawable.step_4_img_3))
                        binding.sendStepMsg.text = getString(R.string.str_tx_step_fee)
                    }
                    else -> {
                        binding.sendStep.setImageDrawable(ContextCompat.getDrawable(this@DaoProposalActivity, R.drawable.step_4_img_4))
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

    class ProposalPageAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        val fragmentList = listOf(
            DaoVoteStep0Fragment(), StepMemoFragment.newInstance(), StepFeeSetFragment.newInstance(), DaoVoteStep3Fragment()
        )

        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): BaseFragment {
            return fragmentList[position]
        }
    }
}