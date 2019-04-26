package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.math.BigDecimal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBlockInfo;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.network.res.ResStakeTxInfo;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class TxResultActivity extends BaseActivity implements View.OnClickListener {

    private Account                     mAccount;
    private int                         mTxType;
    private boolean                     mIsSuccess;
    private int                         mErrorCode;
    private String                      mErrorMsg;
    private String                      mTxHash;

    private ResTxInfo                   mResTxInfo;
    private ResStakeTxInfo              mResStakeTxInfo;


    private TextView                    mToolbarTitle;
    private ImageView                   mToolbarClose;

    private Button                      mBtnDismiss;
    private LinearLayout                mBottomAfterLayer;
    private Button                      mBtnScan, mBtnShare, mBtnOk;

    private NestedScrollView            mScrollLayer;

    private TextView                    mTvtxType, mTvTxHash, mTxTime, mTxBlockHeight;

    private LinearLayout                mSendLayer;
    private TextView                    mSendAtom, mSendAtomFee, mRecipientAddress, mSendMemo;

    private LinearLayout                mDelegateLayer;
    private TextView                    mDelegateAtom, mDelegateFee, mDelegateValidator, mDelegateMemo;

    private LinearLayout                mUndelegateLayer;
    private TextView                    mUndelegateAtom, mUndelegateFee, mUndelegateFrom, mExpectedDate, mUndelegateMemo;

    private LinearLayout                mRewardLayer;
    private TextView                    mRewardFee, mRewardFrom, mRewardMemo;

    private CardView                    mErrorCard;
    private TextView                    mErrorDetails;

    private RelativeLayout              mLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tx_result);

        mToolbarTitle           = findViewById(R.id.toolbar_title);
        mToolbarClose           = findViewById(R.id.toolbar_close);

        mBtnDismiss             = findViewById(R.id.btn_dismiss);
        mBottomAfterLayer       = findViewById(R.id.control_after);
        mBtnScan                = findViewById(R.id.btn_scan);
        mBtnShare               = findViewById(R.id.btn_share);
        mBtnOk                  = findViewById(R.id.btn_ok);

        mScrollLayer            = findViewById(R.id.scroll_layer);

        mTvtxType               = findViewById(R.id.tx_type);
        mTvTxHash               = findViewById(R.id.tx_hash);
        mTxTime                 = findViewById(R.id.tx_time);
        mTxBlockHeight          = findViewById(R.id.tx_block_height);

        mSendLayer              = findViewById(R.id.send_layer);
        mSendAtom               = findViewById(R.id.send_atom);
        mSendAtomFee            = findViewById(R.id.send_fees);
        mRecipientAddress       = findViewById(R.id.send_recipient_address);
        mSendMemo               = findViewById(R.id.send_memo);

        mDelegateLayer          = findViewById(R.id.delegate_layer);
        mDelegateAtom           = findViewById(R.id.delegate_atom);
        mDelegateFee            = findViewById(R.id.delegate_fees);
        mDelegateValidator      = findViewById(R.id.to_delegate_moniker);
        mDelegateMemo           = findViewById(R.id.delegate_memo);

        mUndelegateLayer        = findViewById(R.id.undelegate_layer);
        mUndelegateAtom         = findViewById(R.id.undelegate_atom);
        mUndelegateFee          = findViewById(R.id.undelegate_fees);
        mUndelegateFrom         = findViewById(R.id.undelegate_moniker);
        mExpectedDate           = findViewById(R.id.undelegate_time);
        mUndelegateMemo         = findViewById(R.id.undelegate_memo);

        mRewardLayer            = findViewById(R.id.reward_layer);
        mRewardFee              = findViewById(R.id.reward_fees);
        mRewardFrom             = findViewById(R.id.reward_moniker);
        mRewardMemo             = findViewById(R.id.reward_memo);

        mErrorCard              = findViewById(R.id.error_Card);
        mErrorDetails           = findViewById(R.id.error_details);

        mLoading                = findViewById(R.id.loading_layer);

        mToolbarClose.setOnClickListener(this);
        mBtnDismiss.setOnClickListener(this);
        mBtnScan.setOnClickListener(this);
        mBtnShare.setOnClickListener(this);
        mBtnOk.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAccount    = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mTxType     = getIntent().getIntExtra("txType", BaseConstant.TASK_GEN_TX_SIMPLE_SEND);
        mIsSuccess  = getIntent().getBooleanExtra("isSuccess", false);
        mErrorCode  = getIntent().getIntExtra("errorCode", BaseConstant.ERROR_CODE_UNKNOWN);
        mErrorMsg   = getIntent().getStringExtra("errorMsg");
        mTxHash     = getIntent().getStringExtra("txHash");

        WLog.w("mTxType : " + mTxType);
        WLog.w("mIsSuccess : " + mIsSuccess);
        WLog.w("mErrorCode : " + mErrorCode);


        if(TextUtils.isEmpty(mTxHash)) {
            WLog.w("Empty TXHash error");
            onBackPressed();
            return;
        }

        if(mIsSuccess) {
            if(mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_DELEGATE || mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_UNDELEGATE) {
                onStakeFetchTx(mTxHash);
            } else {
                onFetchTx(mTxHash);
            }
            mToolbarTitle.setText(getString(R.string.str_tx_success));

        } else {
            mLoading.setVisibility(View.GONE);
            mErrorDetails.setText("error code : " + mErrorCode + "\n" + mErrorMsg);
            mErrorCard.setVisibility(View.VISIBLE);
            mToolbarTitle.setText(getString(R.string.str_tx_failed));
        }

    }

    @Override
    public void onBackPressed() {
        onStartMainActivity();
    }


    private void onUpdateView() {
        WLog.w("onUpdateTx");

        if(mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_SEND && mResTxInfo != null) {
            mLoading.setVisibility(View.GONE);
            mScrollLayer.setVisibility(View.VISIBLE);
            mSendLayer.setVisibility(View.VISIBLE);

            mTvtxType.setText(R.string.tx_send);
            mTvTxHash.setText(mResTxInfo.txhash);
            mTxTime.setText(WDp.getTimeTxformat(getBaseContext(), mResTxInfo.timestamp));
            mTxBlockHeight.setText(mResTxInfo.height);

            for(Coin coin: mResTxInfo.tx.value.msg.get(0).value.amount) {
                if(coin.denom.equals(BaseConstant.COSMOS_ATOM)) {
                    mSendAtom.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                }
            }

            for(Coin coin: mResTxInfo.tx.value.fee.amount) {
                if(coin.denom.equals(BaseConstant.COSMOS_ATOM)) {
                    mSendAtomFee.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                }
            }

            mRecipientAddress.setText(mResTxInfo.tx.value.msg.get(0).value.to_address);
            mSendMemo.setText(mResTxInfo.tx.value.memo);

            mBtnDismiss.setVisibility(View.GONE);
            mBottomAfterLayer.setVisibility(View.VISIBLE);


        } else if (mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_DELEGATE && mResStakeTxInfo != null){
            mLoading.setVisibility(View.GONE);
            mScrollLayer.setVisibility(View.VISIBLE);
            mDelegateLayer.setVisibility(View.VISIBLE);

            mTvtxType.setText(R.string.tx_delegate);
            mTvTxHash.setText(mResStakeTxInfo.txhash);
            mTxTime.setText(WDp.getTimeTxformat(getBaseContext(), mResStakeTxInfo.timestamp));
            mTxBlockHeight.setText(mResStakeTxInfo.height);

            mDelegateAtom.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(mResStakeTxInfo.tx.value.msg.get(0).value.amount.amount), 6, BaseChain.getChain(mAccount.baseChain)));
            for(Coin coin: mResStakeTxInfo.tx.value.fee.amount) {
                if(coin.denom.equals(BaseConstant.COSMOS_ATOM)) {
                    mDelegateFee.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                }
            }
            mDelegateValidator.setText(mResStakeTxInfo.tx.value.msg.get(0).value.validator_address);
            mDelegateMemo.setText(mResStakeTxInfo.tx.value.memo);

            mBtnDismiss.setVisibility(View.GONE);
            mBottomAfterLayer.setVisibility(View.VISIBLE);


        } else if (mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_UNDELEGATE && mResStakeTxInfo != null){
            mLoading.setVisibility(View.GONE);
            mScrollLayer.setVisibility(View.VISIBLE);
            mUndelegateLayer.setVisibility(View.VISIBLE);

            mTvtxType.setText(R.string.tx_undelegate);
            mTvTxHash.setText(mResStakeTxInfo.txhash);
            mTxTime.setText(WDp.getTimeTxformat(getBaseContext(), mResStakeTxInfo.timestamp));
            mTxBlockHeight.setText(mResStakeTxInfo.height);

            mUndelegateAtom.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(mResStakeTxInfo.tx.value.msg.get(0).value.amount.amount), 6, BaseChain.getChain(mAccount.baseChain)));
            for(Coin coin: mResStakeTxInfo.tx.value.fee.amount) {
                if(coin.denom.equals(BaseConstant.COSMOS_ATOM)) {
                    mUndelegateFee.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                }
            }
            mUndelegateFrom.setText(mResStakeTxInfo.tx.value.msg.get(0).value.validator_address);
            mUndelegateMemo.setText(mResStakeTxInfo.tx.value.memo);

            mBtnDismiss.setVisibility(View.GONE);
            mBottomAfterLayer.setVisibility(View.VISIBLE);


        } else if (mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_REWARD && mResTxInfo != null){
            mLoading.setVisibility(View.GONE);
            mScrollLayer.setVisibility(View.VISIBLE);
            mRewardLayer.setVisibility(View.VISIBLE);

            mTvtxType.setText(R.string.tx_get_reward);
            mTvTxHash.setText(mResTxInfo.txhash);
            mTxTime.setText(WDp.getTimeTxformat(getBaseContext(), mResTxInfo.timestamp));
            mTxBlockHeight.setText(mResTxInfo.height);

            for(Coin coin: mResTxInfo.tx.value.fee.amount) {
                if(coin.denom.equals(BaseConstant.COSMOS_ATOM)) {
                    mRewardFee.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                }
            }
            String from = "";
            for(Msg msg: mResTxInfo.tx.value.msg) {
                if(TextUtils.isEmpty(from)) {
                    from = msg.value.validator_address;
                } else {
                    from = from + "\n" + msg.value.validator_address;
                }
            }
            mRewardFrom.setText(from);
            mRewardMemo.setText(mResTxInfo.tx.value.memo);

            mBtnDismiss.setVisibility(View.GONE);
            mBottomAfterLayer.setVisibility(View.VISIBLE);
        }
    }


    private int FetchCnt = 0;
    private void onFetchTx(String hash) {
        ApiClient.getWannabitChain(getBaseContext(), BaseChain.getChain(mAccount.baseChain)).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
            @Override
            public void onResponse(Call<ResTxInfo> call, Response<ResTxInfo> response) {
                if(isFinishing()) return;
                WLog.w("onFetchTx " + response.toString());
                if(response.isSuccessful()) {
                    if(response.body() == null) {
                        if(mIsSuccess && FetchCnt < 10) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FetchCnt++;
                                    onFetchTx(mTxHash);
                                }
                            }, 3000);
                        } else {
                            //TODO finish
                            WLog.w("Looop");
                        }
                        return;
                    }
                    WLog.w("getSearchTx : " + response.body().height);
                    mResTxInfo = response.body();
                    onUpdateView();

                } else {
                    if(mIsSuccess && FetchCnt < 10) {
                        WLog.w("retry : " + FetchCnt + " " + mIsSuccess);
                        if(mIsSuccess && FetchCnt < 10) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FetchCnt++;
                                    onFetchTx(mTxHash);
                                }
                            }, 3000);
                        } else {
                            //TODO finish
                            WLog.w("Looop");
                        }
                        return;
                    } else {
                        WLog.w("retry : " + FetchCnt + " " + mIsSuccess);
                        onBackPressed();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResTxInfo> call, Throwable t) {
                WLog.w("onFailure " + t.getMessage());
                t.printStackTrace();
                if(isFinishing()) return;
            }
        });
    }

    private void onStakeFetchTx(String hash) {
        ApiClient.getWannabitChain(getBaseContext(), BaseChain.getChain(mAccount.baseChain)).getStakeSearchTx(hash).enqueue(new Callback<ResStakeTxInfo>() {
            @Override
            public void onResponse(Call<ResStakeTxInfo> call, Response<ResStakeTxInfo> response) {
                if(isFinishing()) return;
                WLog.w("onStakeFetchTx " + response.toString());
                if(response.isSuccessful()) {
                    if(response.body() == null) {
                        if(mIsSuccess && FetchCnt < 10) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FetchCnt++;
                                    onStakeFetchTx(mTxHash);
                                }
                            }, 3000);
                        } else {
                            //TODO finish
                            WLog.w("Looop");
                        }
                        return;
                    }
                    WLog.w("getSearchTx : " + response.body().height);
                    mResStakeTxInfo = response.body();
                    onUpdateView();

                } else {
                    if(mIsSuccess && FetchCnt < 10) {
                        WLog.w("retry : " + FetchCnt + " " + mIsSuccess);
                        if(mIsSuccess && FetchCnt < 10) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FetchCnt++;
                                    onStakeFetchTx(mTxHash);
                                }
                            }, 3000);
                        } else {
                            //TODO finish
                            WLog.w("Looop");
                        }
                        return;
                    } else {
                        WLog.w("retry : " + FetchCnt + " " + mIsSuccess);
                        onBackPressed();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResStakeTxInfo> call, Throwable t) {
                WLog.w("onFailure " + t.getMessage());
                t.printStackTrace();
                if(isFinishing()) return;
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mToolbarClose) || v.equals(mBtnOk) || v.equals(mBtnDismiss)) {
            onBackPressed();

        } else if (v.equals(mBtnScan)) {
            if(mResStakeTxInfo == null && mResTxInfo == null) {
                return;
            }

            if (mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_UNDELEGATE || mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_DELEGATE){
                Intent webintent = new Intent(this, WebActivity.class);
                webintent.putExtra("txid", mResStakeTxInfo.txhash);
                webintent.putExtra("goMain", true);
                startActivity(webintent);
            } else {
                Intent webintent = new Intent(this, WebActivity.class);
                webintent.putExtra("txid", mResTxInfo.txhash);
                webintent.putExtra("goMain", true);
                startActivity(webintent);
            }

        } else if (v.equals(mBtnShare)) {
            if(mResStakeTxInfo == null && mResTxInfo == null) {
                return;
            }
            if (mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_UNDELEGATE || mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_DELEGATE){
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://www.mintscan.io/txs/" + mResStakeTxInfo.txhash);
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "send"));
            } else {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://www.mintscan.io/txs/" + mResTxInfo.txhash);
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "send"));
            }
        }
    }

}
