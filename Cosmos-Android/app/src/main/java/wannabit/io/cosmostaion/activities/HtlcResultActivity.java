package wannabit.io.cosmostaion.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBnbTxInfo;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.HtlcSwapTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_BROADCAST;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GEN_TX_HTLC_SWAP;

public class HtlcResultActivity extends BaseActivity implements View.OnClickListener, TaskListener {
    private Toolbar             mToolbar;
    private NestedScrollView    mTxScrollView;
    private CardView            mErrorCardView;
    private TextView            mErrorMsgTv;
    private RelativeLayout      mLoadingLayer;
    private LinearLayout        mControlLayer;
    private Button              mSenderBtn, mReceiverBtn;

    private ArrayList<Coin>     mTargetCoins;
    private BaseChain           mRecipientChain;
    private Account             mRecipientAccount;
    private Fee                 mSendFee;
    private Fee                 mClaimFee;

    private TaskResult          mResult;
    private ResBnbTxInfo        mResSendBnbTxInfo;
    private ResBnbTxInfo        mResReceiveBnbTxInfo;
    private ResTxInfo           mResSendTxInfo;
    private ResTxInfo           mResReceiveTxInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htls_result);
        mToolbar = findViewById(R.id.tool_bar);
        mTxScrollView = findViewById(R.id.scroll_layer);
        mErrorCardView = findViewById(R.id.error_Card);
        mErrorMsgTv = findViewById(R.id.error_details);
        mLoadingLayer = findViewById(R.id.loadingLayer);
        mControlLayer = findViewById(R.id.bottom_control);
        mSenderBtn = findViewById(R.id.btn_sent);
        mReceiverBtn = findViewById(R.id.btn_received);
        mSenderBtn.setOnClickListener(this);
        mReceiverBtn.setOnClickListener(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mTargetCoins = getIntent().getParcelableArrayListExtra("amount");
        mRecipientChain = BaseChain.getChain(getIntent().getStringExtra("toChain"));
        mRecipientAccount = getBaseDao().onSelectAccount(getIntent().getStringExtra("recipientId"));
        mSendFee = getIntent().getParcelableExtra("sendFee");
        mClaimFee = getIntent().getParcelableExtra("claimFee");

        new HtlcSwapTask(getBaseApplication(), this,
                mAccount, mRecipientAccount, mTargetCoins, mSendFee,
                mClaimFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


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
        if (mLoadingLayer.getVisibility() == View.VISIBLE) {
            return;
        } else {
            onStartMainActivity(0);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mSendFee)) {
            onStartMainActivity(0);

        } else if (v.equals(mReceiverBtn)) {
            getBaseDao().setLastUser(mRecipientAccount.id);
            onStartMainActivity(1);
        }

    }

    private void onUpdateView() {
        mTaskCount--;
        if (mTaskCount <= 0) {
            mLoadingLayer.setVisibility(View.GONE);
            mControlLayer.setVisibility(View.VISIBLE);
            if (mResult.isSuccess) {
                onUpdateSendView();
                onUpdateClaimView();
                mTxScrollView.setVisibility(View.VISIBLE);


            } else {
                if(mResult.errorCode == ERROR_CODE_BROADCAST) {
                    mErrorMsgTv.setText(getString(R.string.error_network));
                } else {
                    mErrorMsgTv.setText("error code : " + mResult.errorCode + "\n" + mResult.errorMsg);
                }
                mErrorCardView.setVisibility(View.VISIBLE);

            }
        }

    }

    private void onUpdateSendView() {
        CardView cardSend = findViewById(R.id.card_send);
        ImageView iconImg = cardSend.findViewById(R.id.send_icon);
        ImageView statusImg = cardSend.findViewById(R.id.send_status_img);
        TextView statusTv = cardSend.findViewById(R.id.send_status);
        TextView errorTv = cardSend.findViewById(R.id.send_fail_msg);
        TextView blockHeightTv = cardSend.findViewById(R.id.send_block_height);
        TextView txHashTv = cardSend.findViewById(R.id.send_hash);
        TextView memoTv = cardSend.findViewById(R.id.send_tx_memo);
        TextView sendAmount = cardSend.findViewById(R.id.send_amount);
        TextView sendDenom = cardSend.findViewById(R.id.send_amount_denom);
        TextView feeAmount = cardSend.findViewById(R.id.send_fee);
        TextView feeDenom = cardSend.findViewById(R.id.send_fee_denom);
        TextView senderTv = cardSend.findViewById(R.id.sender_addr);
        TextView relayRecipientTv = cardSend.findViewById(R.id.relay_recipient_addr);
        TextView relaySenderTv = cardSend.findViewById(R.id.relay_sender_addr);
        TextView recipientTv = cardSend.findViewById(R.id.recipient_addr);
        TextView randomHashTv = cardSend.findViewById(R.id.random_hash);

        iconImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        if ((mBaseChain.equals(BaseChain.BNB_MAIN) || mBaseChain.equals(BaseChain.BNB_TEST)) && mResSendBnbTxInfo != null) {
            final Msg msg = mResSendBnbTxInfo.tx.value.msg.get(0);

            if (mResSendBnbTxInfo.ok) {
                statusImg.setImageDrawable(getResources().getDrawable(R.drawable.success_ic));
                statusTv.setText(R.string.str_success_c);
            } else {
                statusImg.setImageDrawable(getResources().getDrawable(R.drawable.fail_ic));
                statusTv.setText(R.string.str_failed_c);
                errorTv.setText(mResSendBnbTxInfo.log);
                errorTv.setVisibility(View.VISIBLE);
            }

            blockHeightTv.setText(mResSendBnbTxInfo.height);
            txHashTv.setText(mResSendBnbTxInfo.hash);
            memoTv.setText(mResSendBnbTxInfo.tx.value.memo);

            Coin sendCoin = msg.value.getCoins().get(0);
            WDp.showCoinDp(getBaseContext(), sendCoin, sendDenom, sendAmount, mBaseChain);

            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), feeDenom);
            feeAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(FEE_BNB_SEND), 0, 8));

            senderTv.setText(msg.value.from);
            relayRecipientTv.setText(msg.value.to);
            relaySenderTv.setText(msg.value.sender_other_chain);
            recipientTv.setText(msg.value.recipient_other_chain);
            randomHashTv.setText(msg.value.random_number_hash);

        } else if ((mBaseChain.equals(BaseChain.KAVA_MAIN) || mBaseChain.equals(BaseChain.KAVA_TEST)) && mResSendTxInfo != null) {
            final Msg msg = mResSendTxInfo.tx.value.msg.get(0);

            if (mResSendTxInfo.isSuccess()) {
                statusImg.setImageDrawable(getResources().getDrawable(R.drawable.success_ic));
                statusTv.setText(R.string.str_success_c);
            } else {
                statusImg.setImageDrawable(getResources().getDrawable(R.drawable.fail_ic));
                statusTv.setText(R.string.str_failed_c);
                errorTv.setText(mResSendTxInfo.failMessage());
                errorTv.setVisibility(View.VISIBLE);
            }

            blockHeightTv.setText(mResSendTxInfo.height);
            txHashTv.setText(mResSendTxInfo.txhash);
            memoTv.setText(mResSendTxInfo.tx.value.memo);

            Coin sendCoin = msg.value.getCoins().get(0);
            WDp.showCoinDp(getBaseContext(), sendCoin, sendDenom, sendAmount, mBaseChain);
            sendDenom.setText(sendCoin.denom.toUpperCase());
            sendAmount.setText(WDp.getDpAmount2(this, new BigDecimal(sendCoin.amount), WUtil.getKavaCoinDecimal(sendCoin.denom), WUtil.getKavaCoinDecimal(sendCoin.denom)));

            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), feeDenom);
            feeAmount.setText(WDp.getDpAmount2(getBaseContext(), mResSendTxInfo.simpleFee(), 6, 6));

            senderTv.setText(msg.value.from);
            relayRecipientTv.setText(msg.value.to);
            relaySenderTv.setText(msg.value.sender_other_chain);
            recipientTv.setText(msg.value.recipient_other_chain);
            randomHashTv.setText(msg.value.random_number_hash);
        }


    }

    private void onUpdateClaimView() {
        CardView cardClaim = findViewById(R.id.card_claim);
        ImageView iconImg = cardClaim.findViewById(R.id.claim_icon);
        ImageView statusImg = cardClaim.findViewById(R.id.claim_status_img);
        TextView statusTv = cardClaim.findViewById(R.id.claim_status);
        TextView errorTv = cardClaim.findViewById(R.id.claim_fail_msg);
        TextView blockHeightTv = cardClaim.findViewById(R.id.claim_block_height);
        TextView txHashTv = cardClaim.findViewById(R.id.claim_hash);
        TextView memoTv = cardClaim.findViewById(R.id.claim_tx_memo);
        TextView claimAmount = cardClaim.findViewById(R.id.claim_amount);
        TextView claimDenom = cardClaim.findViewById(R.id.claim_amount_denom);
        TextView feeAmount = cardClaim.findViewById(R.id.claim_fee);
        TextView feeDenom = cardClaim.findViewById(R.id.claim_fee_denom);
        TextView claimerTv = cardClaim.findViewById(R.id.claimer_addr);
        TextView randomNumberTv = cardClaim.findViewById(R.id.claim_random_number);
        TextView swapIdTv = cardClaim.findViewById(R.id.claim_swap_id);

        iconImg.setColorFilter(WDp.getChainColor(getBaseContext(), mRecipientChain), android.graphics.PorterDuff.Mode.SRC_IN);

        if ((mRecipientChain.equals(BaseChain.BNB_MAIN) || mRecipientChain.equals(BaseChain.BNB_TEST)) && mResReceiveBnbTxInfo != null) {
            final Msg msg = mResReceiveBnbTxInfo.tx.value.msg.get(0);
            if (mResReceiveBnbTxInfo.ok) {
                statusImg.setImageDrawable(getResources().getDrawable(R.drawable.success_ic));
                statusTv.setText(R.string.str_success_c);
            } else {
                statusImg.setImageDrawable(getResources().getDrawable(R.drawable.fail_ic));
                statusTv.setText(R.string.str_failed_c);
                errorTv.setText(mResReceiveBnbTxInfo.log);
                errorTv.setVisibility(View.VISIBLE);
            }

            blockHeightTv.setText(mResReceiveBnbTxInfo.height);
            txHashTv.setText(mResReceiveBnbTxInfo.hash);
            memoTv.setText(mResReceiveBnbTxInfo.tx.value.memo);

            claimDenom.setText("");
            claimAmount.setText("");

            WDp.DpMainDenom(getBaseContext(), mRecipientChain.getChain(), feeDenom);
            feeAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(FEE_BNB_SEND), 0, 8));

            claimerTv.setText(msg.value.from);
            randomNumberTv.setText(msg.value.random_number);
            swapIdTv.setText(msg.value.swap_id);

        } else if (mResReceiveTxInfo != null) {
            if (mRecipientChain.equals(BaseChain.KAVA_MAIN) || mRecipientChain.equals(BaseChain.KAVA_TEST)) {
                final Msg msg = mResReceiveTxInfo.tx.value.msg.get(0);
                if (mResReceiveTxInfo.isSuccess()) {
                    statusImg.setImageDrawable(getResources().getDrawable(R.drawable.success_ic));
                    statusTv.setText(R.string.str_success_c);
                } else {
                    statusImg.setImageDrawable(getResources().getDrawable(R.drawable.fail_ic));
                    statusTv.setText(R.string.str_failed_c);
                    errorTv.setText(mResReceiveTxInfo.failMessage());
                    errorTv.setVisibility(View.VISIBLE);
                }

                blockHeightTv.setText(mResReceiveTxInfo.height);
                txHashTv.setText(mResReceiveTxInfo.txhash);
                memoTv.setText(mResReceiveTxInfo.tx.value.memo);

                Coin receiveCoin = mResReceiveTxInfo.simpleSwapCoin();
                try {
                    if (!TextUtils.isEmpty(receiveCoin.denom)) {
                        WDp.showCoinDp(getBaseContext(), receiveCoin, claimDenom, claimAmount, mRecipientChain);
                    }

                } catch (Exception e) {
                    claimDenom.setText("");
                    claimAmount.setText("");
                }

                WDp.DpMainDenom(getBaseContext(), mRecipientChain.getChain(), feeDenom);
                feeAmount.setText(WDp.getDpAmount2(getBaseContext(), mResReceiveTxInfo.simpleFee(), 6, 6));

                claimerTv.setText(msg.value.from);
                randomNumberTv.setText(msg.value.random_number);
                swapIdTv.setText(msg.value.swap_id);
            }

        }
    }




    private void onFetchSendTx(String hash) {
        WLog.w("onFetchSendTx " + hash);
        if (mBaseChain.equals(BaseChain.BNB_MAIN)) {

        } else if (mBaseChain.equals(BaseChain.BNB_TEST)) {
            ApiClient.getBnbTestChain(getBaseContext()).getSearchTx(hash, "json").enqueue(new Callback<ResBnbTxInfo>() {
                @Override
                public void onResponse(Call<ResBnbTxInfo> call, Response<ResBnbTxInfo> response) {
                    if(isFinishing()) return;
                    WLog.w("onFetchSendTx " + response.toString());
                    if(response.isSuccessful() && response.body() != null) {
                        mResSendBnbTxInfo = response.body();
                    }
                    onUpdateView();
                }

                @Override
                public void onFailure(Call<ResBnbTxInfo> call, Throwable t) {
                    WLog.w("onFetchSendTx BNB onFailure");
                    if(BaseConstant.IS_SHOWLOG) t.printStackTrace();
                    onUpdateView();
                }
            });

        } else if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {

        } else if (mBaseChain.equals(BaseChain.KAVA_TEST)) {
            ApiClient.getKavaTestChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
                @Override
                public void onResponse(Call<ResTxInfo> call, Response<ResTxInfo> response) {
                    if(isFinishing()) return;
                    WLog.w("onFetchSendTx " + response.toString());
                    if(response.isSuccessful() && response.body() != null) {
                        mResSendTxInfo = response.body();
                        onUpdateView();
                    }
                    onUpdateView();
                }

                @Override
                public void onFailure(Call<ResTxInfo> call, Throwable t) {
                    WLog.w("onFetchSendTx KAVA onFailure");
                    if(BaseConstant.IS_SHOWLOG) t.printStackTrace();
                    onUpdateView();
                }
            });

        }
    }

    private int ClaimFetchCnt = 0;
    private void onFetchClaimTx(String hash) {
        WLog.w("onFetchClaimTx " + hash);
        if (mRecipientChain.equals(BaseChain.BNB_MAIN)) {

        } else if (mRecipientChain.equals(BaseChain.BNB_TEST)) {
            ApiClient.getBnbTestChain(getBaseContext()).getSearchTx(hash, "json").enqueue(new Callback<ResBnbTxInfo>() {
                @Override
                public void onResponse(Call<ResBnbTxInfo> call, Response<ResBnbTxInfo> response) {
                    if(isFinishing()) return;
                    WLog.w("onFetchClaimTx " + response.toString());
                    if(response.isSuccessful() && response.body() != null) {
                        mResReceiveBnbTxInfo = response.body();
                        onUpdateView();
                    } else {
                        if (ClaimFetchCnt < 5) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ClaimFetchCnt++;
                                    onFetchClaimTx(hash);
                                }
                            }, 3000);

                        } else {
                            onUpdateView();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResBnbTxInfo> call, Throwable t) {
                    WLog.w("onFetchClaimTx BNB onFailure");
                    if(BaseConstant.IS_SHOWLOG) t.printStackTrace();
                    onUpdateView();
                }
            });

        } else if (mRecipientChain.equals(BaseChain.KAVA_MAIN)) {

        } else if (mRecipientChain.equals(BaseChain.KAVA_TEST)) {
            ApiClient.getKavaTestChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
                @Override
                public void onResponse(Call<ResTxInfo> call, Response<ResTxInfo> response) {
                    if(isFinishing()) return;
                    WLog.w("onFetchClaimTx " + response.toString());
                    if(response.isSuccessful() && response.body() != null) {
                        mResReceiveTxInfo = response.body();
                        onUpdateView();
                    } else {
                        if (ClaimFetchCnt < 5) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ClaimFetchCnt++;
                                    onFetchClaimTx(hash);
                                }
                            }, 3000);

                        } else {
                            onUpdateView();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResTxInfo> call, Throwable t) {
                    WLog.w("onFetchClaimTx KAVA onFailure");
                    if(BaseConstant.IS_SHOWLOG) t.printStackTrace();
                    onUpdateView();
                }
            });

        }
    }



    private int mTaskCount = 0;
    @Override
    public void onTaskResponse(TaskResult result) {
        if (result.taskType == TASK_GEN_TX_HTLC_SWAP) {
            mResult = result;
            if (mResult.isSuccess) {
                mTaskCount = 2;
                onFetchSendTx(result.resultData2);
                onFetchClaimTx((String)result.resultData);

            } else {
                onUpdateView();
            }
        }
    }

}
