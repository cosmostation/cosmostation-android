package wannabit.io.cosmostaion.activities.txs.neutron

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
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
import wannabit.io.cosmostaion.databinding.ActivityDaoProposalBinding
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment
import wannabit.io.cosmostaion.fragment.StepMemoFragment
import wannabit.io.cosmostaion.fragment.txs.neutron.DaoVoteStep0Fragment
import wannabit.io.cosmostaion.fragment.txs.neutron.DaoVoteStep3Fragment
import wannabit.io.cosmostaion.model.viewModel.NeutronViewModel
import wannabit.io.cosmostaion.model.viewModel.PersisViewModel
import wannabit.io.cosmostaion.network.res.ResV1Proposal
import wannabit.io.cosmostaion.utils.WLog

class DaoProposalActivity : BaseBroadCastActivity() {

    private lateinit var binding: ActivityDaoProposalBinding

    private lateinit var mPageAdapter: ProposalPageAdapter

    private val neutronViewModel: NeutronViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaoProposalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mTxType = BaseConstant.CONST_PW_TX_DAO_PROPOSAL

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

        binding.toolbarTitle.text = "Vote"

        intent.getStringExtra("proposal_id")?.let {
            neutronViewModel.loadDaoSingleProposalData(mChainConfig, it.toInt())
            mProposal_id = it.toInt()
        }
    }

    private fun onSetPageSelected() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                mPageAdapter.fragmentList[position].onRefreshTab()
                when (position) {
                    0 -> {
                        binding.sendStep.setImageDrawable(ContextCompat.getDrawable(this@DaoProposalActivity, R.drawable.step_4_img_1))
                        binding.sendStepMsg.text = getString(R.string.str_authz_send_step_1)
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
            DaoVoteStep0Fragment(),
            StepMemoFragment.newInstance(),
            StepFeeSetFragment.newInstance(),
            DaoVoteStep3Fragment()
        )

        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): BaseFragment {
            return fragmentList[position]
        }
    }
}