package wannabit.io.cosmostaion.activities.txs.authz;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_AUTHZ_VOTE;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.ArrayList;

import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment;
import wannabit.io.cosmostaion.fragment.StepMemoFragment;
import wannabit.io.cosmostaion.fragment.txs.authz.AuthzVoteStep0Fragment;
import wannabit.io.cosmostaion.fragment.txs.authz.AuthzVoteStep1Fragment;
import wannabit.io.cosmostaion.fragment.txs.authz.AuthzVoteStep4Fragment;
import wannabit.io.cosmostaion.network.res.ResProposal;

public class AuthzVoteActivity extends BaseBroadCastActivity {

    private RelativeLayout mRootView;
    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mIvStep;
    private TextView mTvStep;
    private ViewPager mViewPager;
    private AuthzVotePageAdapter mPageAdapter;

    public ArrayList<ResProposal> mProposalsList = Lists.newArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        mRootView = findViewById(R.id.root_view);
        mToolbar = findViewById(R.id.tool_bar);
        mTitle = findViewById(R.id.toolbar_title);
        mIvStep = findViewById(R.id.send_step);
        mTvStep = findViewById(R.id.send_step_msg);
        mViewPager = findViewById(R.id.view_pager);
        mTitle.setText(getString(R.string.str_authz_vote_title));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mIvStep.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.step_1_img));
        mTvStep.setText(getString(R.string.str_authz_vote_step_0));

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mTxType = CONST_PW_TX_AUTHZ_VOTE;

        mGranter = getIntent().getStringExtra("granter");

        mPageAdapter = new AuthzVotePageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(AuthzVoteActivity.this, R.drawable.step_1_img));
                    mTvStep.setText(getString(R.string.str_authz_vote_step_0));
                } else if (i == 1) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(AuthzVoteActivity.this, R.drawable.step_2_img));
                    mTvStep.setText(getString(R.string.str_authz_vote_step_1));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 2) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(AuthzVoteActivity.this, R.drawable.step_3_img));
                    mTvStep.setText(getString(R.string.str_tx_step_memo));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 3) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(AuthzVoteActivity.this, R.drawable.step_4_img));
                    mTvStep.setText(getString(R.string.str_tx_step_fee));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 4) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(AuthzVoteActivity.this, R.drawable.step_5_img));
                    mTvStep.setText(getString(R.string.str_tx_step_confirm));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mViewPager.setCurrentItem(0);

        mRootView.setOnClickListener(v -> onHideKeyboard());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAccount == null) finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        onHideKeyboard();
        if (mViewPager.getCurrentItem() > 0) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        } else {
            super.onBackPressed();
        }
    }

    public void onNextStep() {
        if (mViewPager.getCurrentItem() < mViewPager.getChildCount()) {
            onHideKeyboard();
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
        }
    }

    public void onBeforeStep() {
        if (mViewPager.getCurrentItem() > 0) {
            onHideKeyboard();
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        } else {
            onBackPressed();
        }
    }

    public void onAuthzVote() {
        if (getBaseDao().isAutoPass()) {
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcAuthzVoteReq(getAuthResponse(mBaseChain, mAccount), mAccount.address, mGranter, mSelectedOpinion,
                    mTxFee, mTxMemo, getEcKey(mAccount), getBaseDao().getChainIdGrpc());
            onBroadcastGrpcTx(mBaseChain, broadcastTxRequest);

        } else {
            Intent intent = new Intent(AuthzVoteActivity.this, PasswordCheckActivity.class);
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, mTxType);
            intent.putExtra("granter", mGranter);
            intent.putExtra("selectedProposals", (Serializable) mSelectedOpinion);
            intent.putExtra("memo", mTxMemo);
            intent.putExtra("fee", mTxFee);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    private class AuthzVotePageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public AuthzVotePageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(AuthzVoteStep0Fragment.newInstance());
            mFragments.add(AuthzVoteStep1Fragment.newInstance());
            mFragments.add(StepMemoFragment.newInstance());
            mFragments.add(StepFeeSetFragment.newInstance());
            mFragments.add(AuthzVoteStep4Fragment.newInstance());
        }

        @Override
        public BaseFragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            if (getCurrentFragment() != object) {
                mCurrentFragment = ((BaseFragment) object);
            }
            super.setPrimaryItem(container, position, object);
        }

        public BaseFragment getCurrentFragment() {
            return mCurrentFragment;
        }

        public ArrayList<BaseFragment> getFragments() {
            return mFragments;
        }
    }
}
