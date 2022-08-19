package wannabit.io.cosmostaion.activities.txs.common;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_IBC_TRANSFER;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import cosmos.tx.v1beta1.ServiceOuterClass;
import ibc.core.channel.v1.QueryGrpc;
import ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest;
import ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateResponse;
import ibc.lightclients.tendermint.v1.Tendermint;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.IbcPath;
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment;
import wannabit.io.cosmostaion.fragment.txs.common.IBCSendStep0Fragment;
import wannabit.io.cosmostaion.fragment.txs.common.IBCSendStep1Fragment;
import wannabit.io.cosmostaion.fragment.txs.common.IBCSendStep2Fragment;
import wannabit.io.cosmostaion.fragment.txs.common.IBCSendStep4Fragment;
import wannabit.io.cosmostaion.network.ChannelBuilder;

public class IBCSendActivity extends BaseBroadCastActivity {

    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mIvStep;
    private TextView mTvStep;
    private ViewPager mViewPager;
    private IbcSendPageAdapter mPageAdapter;

    public String mToIbcDenom;

    public Account mRecipientAccount;
    public IbcPath mIbcSelectedRelayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        mToolbar = findViewById(R.id.tool_bar);
        mTitle = findViewById(R.id.toolbar_title);
        mIvStep = findViewById(R.id.send_step);
        mTvStep = findViewById(R.id.send_step_msg);
        mViewPager = findViewById(R.id.view_pager);
        mTitle.setText(getString(R.string.str_ibc_send_c));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTvStep.setText(getString(R.string.str_ibc_transfer_step_0));
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mToIbcDenom = getIntent().getStringExtra("sendTokenDenom");
        mTxType = CONST_PW_TX_IBC_TRANSFER;

        mPageAdapter = new IbcSendPageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(IBCSendActivity.this, R.drawable.step_1_img));
                    mTvStep.setText(getString(R.string.str_ibc_transfer_step_0));
                } else if (i == 1) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(IBCSendActivity.this, R.drawable.step_2_img));
                    mTvStep.setText(getString(R.string.str_ibc_transfer_step_1));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 2) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(IBCSendActivity.this, R.drawable.step_3_img));
                    mTvStep.setText(getString(R.string.str_ibc_transfer_step_2));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 3) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(IBCSendActivity.this, R.drawable.step_4_img));
                    mTvStep.setText(getString(R.string.str_ibc_transfer_step_3));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 4) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(IBCSendActivity.this, R.drawable.step_5_img));
                    mTvStep.setText(getString(R.string.str_ibc_transfer_step_4));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mViewPager.setCurrentItem(0);
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
        onHideKeyboard();
        if (mViewPager.getCurrentItem() < 4) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
        }
    }

    public void onBeforeStep() {
        onHideKeyboard();
        if (mViewPager.getCurrentItem() > 0) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        } else {
            onBackPressed();
        }
    }

    public void onStartIbcSend() {
        if (getBaseDao().isAutoPass()) {
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcIbcTransferReq(getAuthResponse(mBaseChain, mAccount), mAccount.address, mToAddress,
                    mAmounts.get(0).denom, mAmounts.get(0).amount, mPath.port_id, mPath.channel_id, getClientState().getLatestHeight(), mTxFee, "", getEcKey(mAccount), getBaseDao().getChainIdGrpc());
            onBroadcastGrpcTx(mBaseChain, broadcastTxRequest);

        } else {
            Intent intent = new Intent(IBCSendActivity.this, PasswordCheckActivity.class);
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, mTxType);
            intent.putExtra("toAddress", mToAddress);
            intent.putParcelableArrayListExtra("amount", mAmounts);
            intent.putExtra("channelId", mPath.channel_id);
            intent.putExtra("portId", mPath.port_id);
            intent.putExtra("memo", mTxMemo);
            intent.putExtra("fee", mTxFee);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    private class IbcSendPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public IbcSendPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(IBCSendStep0Fragment.newInstance(null));
            mFragments.add(IBCSendStep1Fragment.newInstance(null));
            mFragments.add(IBCSendStep2Fragment.newInstance(null));
            mFragments.add(StepFeeSetFragment.newInstance());
            mFragments.add(IBCSendStep4Fragment.newInstance(null));
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

    private Tendermint.ClientState getClientState() {
        try {
            QueryGrpc.QueryBlockingStub channelStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);;
            QueryChannelClientStateRequest request = ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest.newBuilder().setChannelId(mPath.channel_id).setPortId(mPath.port_id).build();
            QueryChannelClientStateResponse response = channelStub.channelClientState(request);

            if (response != null) {
                return Tendermint.ClientState.parseFrom(response.getIdentifiedClientState().getClientState().getValue());
            }
        } catch (InvalidProtocolBufferException e) { e.printStackTrace(); }
        return null;
    }
}
