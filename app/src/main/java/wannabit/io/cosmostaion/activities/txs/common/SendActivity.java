package wannabit.io.cosmostaion.activities.txs.common;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.binance.dex.api.client.BinanceDexApiClientFactory;
import com.binance.dex.api.client.BinanceDexApiRestClient;
import com.binance.dex.api.client.BinanceDexEnvironment;
import com.binance.dex.api.client.Wallet;
import com.binance.dex.api.client.domain.TransactionMetadata;
import com.binance.dex.api.client.domain.broadcast.TransactionOption;
import com.binance.dex.api.client.domain.broadcast.Transfer;
import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cosmos.tx.v1beta1.ServiceOuterClass;
import ibc.core.channel.v1.QueryGrpc;
import ibc.core.channel.v1.QueryOuterClass;
import ibc.lightclients.tendermint.v1.Tendermint;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.activities.TxDetailActivity;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.dao.Asset;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment;
import wannabit.io.cosmostaion.fragment.StepFeeSetOldFragment;
import wannabit.io.cosmostaion.fragment.StepMemoFragment;
import wannabit.io.cosmostaion.fragment.txs.common.SendStep0Fragment;
import wannabit.io.cosmostaion.fragment.txs.common.SendStep1Fragment;
import wannabit.io.cosmostaion.fragment.txs.common.SendStep4Fragment;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResBroadTx;

public class SendActivity extends BaseBroadCastActivity {

    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mIvStep;
    private TextView mTvStep;
    private ViewPager mViewPager;
    private SendPageAdapter mPageAdapter;

    public BnbToken mBnbToken;

    public Asset mAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        mToolbar = findViewById(R.id.tool_bar);
        mTitle = findViewById(R.id.toolbar_title);
        mIvStep = findViewById(R.id.send_step);
        mTvStep = findViewById(R.id.send_step_msg);
        mViewPager = findViewById(R.id.view_pager);
        mTitle.setText(getString(R.string.str_send_c));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTvStep.setText(getString(R.string.str_send_step_0));
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);

        mDenom = getIntent().getStringExtra("sendTokenDenom");
        if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
            mBnbToken = getBaseDao().getBnbToken(mDenom);
        }

        mPageAdapter = new SendPageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(SendActivity.this, R.drawable.step_1_img));
                    mTvStep.setText(getString(R.string.str_send_step_0));
                } else if (i == 1) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(SendActivity.this, R.drawable.step_2_img));
                    mTvStep.setText(getString(R.string.str_send_step_1));
                } else if (i == 2) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(SendActivity.this, R.drawable.step_3_img));
                    mTvStep.setText(getString(R.string.str_send_step_2));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 3) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(SendActivity.this, R.drawable.step_4_img));
                    mTvStep.setText(getString(R.string.str_send_step_3));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 4) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(SendActivity.this, R.drawable.step_5_img));
                    mTvStep.setText(getString(R.string.str_send_step_4));
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

    public void onStartSend() {
        if (getBaseDao().isAutoPass()) {
            onAutoStartSend();

        } else {
            Intent intent = new Intent(this, PasswordCheckActivity.class);
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, mTxType);
            intent.putExtra("toAddress", mToAddress);
            intent.putParcelableArrayListExtra("amount", mAmounts);
            intent.putExtra("memo", mTxMemo);
            intent.putExtra("fee", mTxFee);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    public void onStartIbcSend() {
        if (getBaseDao().isAutoPass()) {
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcIbcTransferReq(getAuthResponse(mBaseChain, mAccount), mAccount.address, mToAddress,
                    mAmounts.get(0).denom, mAmounts.get(0).amount, mAssetPath, getClientState().getLatestHeight(), mTxFee, "", getEcKey(mAccount), getBaseDao().getChainIdGrpc());
            onBroadcastGrpcTx(mBaseChain, broadcastTxRequest);

        } else {
            Intent intent = new Intent(this, PasswordCheckActivity.class);
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, mTxType);
            intent.putExtra("toAddress", mToAddress);
            intent.putParcelableArrayListExtra("amount", mAmounts);
            intent.putExtra("assetPath", mAssetPath);
            intent.putExtra("memo", mTxMemo);
            intent.putExtra("fee", mTxFee);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    public void onStartSendContract() {
        if (getBaseDao().isAutoPass()) {
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcCw20SendReq(getAuthResponse(mBaseChain, mAccount), mAccount.address, mToAddress, mCw20Asset.contract_address,
                    mAmounts, mTxFee, mTxMemo, getEcKey(mAccount), getBaseDao().getChainIdGrpc());
            onBroadcastGrpcTx(mBaseChain, broadcastTxRequest);

        } else {
            Intent intent = new Intent(this, PasswordCheckActivity.class);
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, mTxType);
            intent.putExtra("toAddress", mToAddress);
            intent.putExtra("contractAddress", mCw20Asset.contract_address);
            intent.putParcelableArrayListExtra("amount", mAmounts);
            intent.putExtra("memo", mTxMemo);
            intent.putExtra("fee", mTxFee);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    public void onStartIBCContract() {
        if (getBaseDao().isAutoPass()) {
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcCw20IbcTransferReq(getAuthResponse(mBaseChain, mAccount), mAccount.address, mToAddress, mCw20Asset.contract_address, mAssetPath,
                    mAmounts, mTxFee, mTxMemo, getEcKey(mAccount), getBaseDao().getChainIdGrpc());
            onBroadcastGrpcTx(mBaseChain, broadcastTxRequest);

        } else {
            Intent intent = new Intent(this, PasswordCheckActivity.class);
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, mTxType);
            intent.putExtra("toAddress", mToAddress);
            intent.putExtra("contractAddress", mCw20Asset.contract_address);
            intent.putExtra("assetPath", mAssetPath);
            intent.putParcelableArrayListExtra("amount", mAmounts);
            intent.putExtra("memo", mTxMemo);
            intent.putExtra("fee", mTxFee);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    private void onAutoStartSend() {
        if (isGRPC(mBaseChain)) {
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcSendReq(getAuthResponse(mBaseChain, mAccount), mToAddress, mAmounts, mTxFee, mTxMemo, getEcKey(mAccount), getBaseDao().getChainIdGrpc());
            onBroadcastGrpcTx(mBaseChain, broadcastTxRequest);

        } else if (mBaseChain.equals(BNB_MAIN)) {
            new Thread(() -> {
                try {
                    Wallet wallet = new Wallet(getEcKey(mAccount).getPrivateKeyAsHex(), BinanceDexEnvironment.PROD);
                    wallet.setAccountNumber(mAccount.accountNumber);
                    wallet.setSequence(Long.valueOf(mAccount.sequenceNumber));

                    Transfer transfer = new Transfer();
                    transfer.setCoin(mAmounts.get(0).denom);
                    transfer.setFromAddress(mAccount.address);
                    transfer.setToAddress(mToAddress);
                    transfer.setAmount(mAmounts.get(0).amount);

                    TransactionOption options = new TransactionOption(mTxMemo, 82, null);

                    BinanceDexApiRestClient client = BinanceDexApiClientFactory.newInstance().newRestClient(BinanceDexEnvironment.PROD.getBaseUrl());
                    List<TransactionMetadata> resp = client.transfer(transfer, wallet, options, true);

                    if (resp.get(0).isOk()) {
                        Intent txIntent = new Intent(SendActivity.this, TxDetailActivity.class);
                        txIntent.putExtra("isGen", true);
                        txIntent.putExtra("isSuccess", true);
                        txIntent.putExtra("errorCode",  resp.get(0).getCode());
                        txIntent.putExtra("errorMsg", resp.get(0).getLog());
                        String hash = resp.get(0).getHash();
                        if (!TextUtils.isEmpty(hash))
                            txIntent.putExtra("txHash", hash);
                        startActivity(txIntent);
                    }
                } catch (Exception e) { }
            }).start();

        } else {
            new Thread(() -> {
                try {
                    Msg singleSendMsg = MsgGenerator.genTransferMsg(mAccount.address, mToAddress, mAmounts, mBaseChain);
                    ArrayList<Msg> msgs= new ArrayList<>();
                    msgs.add(singleSendMsg);

                    ReqBroadCast reqBroadCast = MsgGenerator.getOKexBroadcaseReq(mAccount, msgs, mTxFee, mTxMemo, getEcKey(mAccount), getBaseDao().getChainId());
                    Response<ResBroadTx> response = ApiClient.getOkexChain().broadTx(reqBroadCast).execute();

                    if (response.isSuccessful() && response.body() != null) {
                        Intent txIntent = new Intent(SendActivity.this, TxDetailActivity.class);
                        if (response.body().txhash != null) {
                            String hash = response.body().txhash;
                            if (!TextUtils.isEmpty(hash))
                                txIntent.putExtra("txHash", hash);
                        }
                        if (response.body().code != null) {
                            txIntent.putExtra("errorCode", response.body().code);
                            txIntent.putExtra("errorMsg", response.body().raw_log);
                        }
                        txIntent.putExtra("isGen", true);
                        txIntent.putExtra("isSuccess", true);
                        startActivity(txIntent);
                    }
                } catch (Exception e) { }
            }).start();
        }
    }

    private Tendermint.ClientState getClientState() {
        try {
            QueryGrpc.QueryBlockingStub channelStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);;
            QueryOuterClass.QueryChannelClientStateRequest request = ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest.newBuilder().setChannelId(mAssetPath.channel).setPortId(mAssetPath.port).build();
            QueryOuterClass.QueryChannelClientStateResponse response = channelStub.channelClientState(request);

            if (response != null) {
                return Tendermint.ClientState.parseFrom(response.getIdentifiedClientState().getClientState().getValue());
            }
        } catch (InvalidProtocolBufferException e) { e.printStackTrace(); }
        return null;
    }

    private class SendPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public SendPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(SendStep0Fragment.newInstance());
            mFragments.add(SendStep1Fragment.newInstance());
            mFragments.add(StepMemoFragment.newInstance());
            if (isGRPC(mBaseChain)) {
                mFragments.add(StepFeeSetFragment.newInstance());
            } else {
                mFragments.add(StepFeeSetOldFragment.newInstance(null));
            }
            mFragments.add(SendStep4Fragment.newInstance());
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
