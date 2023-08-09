package wannabit.io.cosmostaion.activities.txs.authz

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import cosmos.authz.v1beta1.Authz.GrantAuthorization
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseBroadCastActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivityAuthzRevokeBinding
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment
import wannabit.io.cosmostaion.fragment.StepMemoFragment
import wannabit.io.cosmostaion.fragment.txs.authz.grantee.RevokeStep0Fragment
import wannabit.io.cosmostaion.fragment.txs.authz.grantee.RevokeStep3Fragment
import wannabit.io.cosmostaion.utils.WLog
import wannabit.io.cosmostaion.utils.intentSerializable
import java.util.ArrayList

class AuthzRevokeActivity : BaseBroadCastActivity() {

    private lateinit var binding: ActivityAuthzRevokeBinding

    private lateinit var mPageAdapter: RevokePageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthzRevokeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()
        initData()
    }

    private fun initView() {
        mPageAdapter = RevokePageAdapter(this)
        binding.apply {
            viewPager.adapter = mPageAdapter
            viewPager.isUserInputEnabled = false
            viewPager.setCurrentItem(0, false)
            viewPager.offscreenPageLimit = 3
            rootView.setOnClickListener {
                onHideKeyboard()
            }
            onSetPageSelected()
        }
    }

    private fun initData() {
        mAccount = baseDao.onSelectAccount(baseDao.lastUser)
        mBaseChain = BaseChain.getChain(mAccount.baseChain)
        mChainConfig = ChainFactory.getChain(mBaseChain)

        mGrantees = intent.intentSerializable("selectedItems", T::class.java) as ArrayList<GrantAuthorization>
    }

    private fun onSetPageSelected() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                mPageAdapter.fragmentList[position].onRefreshTab()
                val stepDrawables = arrayOf(
                    R.drawable.step_4_img_1,
                    R.drawable.step_4_img_2,
                    R.drawable.step_4_img_3,
                    R.drawable.step_4_img_4
                )

                val stepMessages = arrayOf(
                    R.string.str_vote_step_0,
                    R.string.str_tx_step_memo,
                    R.string.str_tx_step_fee,
                    R.string.str_tx_step_confirm
                )

                val drawableRes = if (position in 0 until stepDrawables.size) {
                    stepDrawables[position]
                } else {
                    R.drawable.step_4_img_4
                }

                val messageRes = if (position in 0 until stepMessages.size) {
                    stepMessages[position]
                } else {
                    R.string.str_tx_step_confirm
                }

                binding.step.setImageDrawable(ContextCompat.getDrawable(this@AuthzRevokeActivity, drawableRes))
                binding.stepMsg.text = getString(messageRes)
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

    class RevokePageAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        val fragmentList = listOf(
            RevokeStep0Fragment(),
            StepMemoFragment.newInstance(),
            StepFeeSetFragment.newInstance(),
            RevokeStep3Fragment()
        )

        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): BaseFragment {
            return fragmentList[position]
        }
    }
}