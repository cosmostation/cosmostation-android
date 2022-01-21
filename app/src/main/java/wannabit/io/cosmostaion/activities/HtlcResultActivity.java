package wannabit.io.cosmostaion.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;

import java.math.BigDecimal;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_Htlc_Error;
import wannabit.io.cosmostaion.dialog.Dialog_MoreSwapWait;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBnbSwapInfo;
import wannabit.io.cosmostaion.network.res.ResBnbTxInfo;
import wannabit.io.cosmostaion.network.res.ResKavaSwapInfo;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.HtlcClaimTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.HtlcCreateTask;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.KavaClaimHTLCGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.KavaCreateHTLCGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.VoteGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GEN_TX_HTLC_CLAIM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GEN_TX_HTLC_CREATE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_GEN_TX_KAVA_CREATE_HTLC;

public class HtlcResultActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar             mToolbar;
    private NestedScrollView    mTxScrollView;
    private CardView            mErrorCardView;
    private TextView            mErrorMsgTv;
    private RelativeLayout      mLoadingLayer;
    private TextView            mLoadingProgress;
    private LinearLayout        mControlLayer;
    private Button              mSenderBtn, mReceiverBtn;

    private ArrayList<Coin>     mTargetCoins;
    private BaseChain           mRecipientChain;
    private Account             mRecipientAccount;
    private Fee                 mSendFee;
    private Fee                 mClaimFee;


    private String              mExpectedSwapId;
    private String              mRandomNumber;
    private String              mCreateTxHash;
    private String              mClaimTxHash;
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
        mLoadingProgress = findViewById(R.id.loadingProgress);
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

        mLoadingProgress.setText(getString(R.string.str_htlc_loading_progress_0));
        onCreateHTLC();
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

    public void onFinishWithError() {
        onStartMainActivity(0);

    }

    @Override
    public void onClick(View v) {
        if (v.equals(mSenderBtn)) {
            onStartMainActivity(0);

        } else if (v.equals(mReceiverBtn)) {
            if (getBaseDao().dpSortedChains().contains(BaseChain.getChain(mRecipientAccount.baseChain))) {
                getBaseDao().setLastUser(mRecipientAccount.id);
                onStartMainActivity(1);
            } else {
                Toast.makeText(HtlcResultActivity.this, "error_hided_chain", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private void onUpdateView() {
        onUpdateSendView();
        onUpdateClaimView();
        mLoadingLayer.setVisibility(View.GONE);
        mControlLayer.setVisibility(View.VISIBLE);
        mTxScrollView.setVisibility(View.VISIBLE);
    }

    private void onUpdateProgress(int progress) {
        if (progress == 1) {
            mLoadingProgress.setText(getString(R.string.str_htlc_loading_progress_1));
        } else if (progress == 2) {
            mLoadingProgress.setText(getString(R.string.str_htlc_loading_progress_2));
        } else if (progress == 3) {
            mLoadingProgress.setText(getString(R.string.str_htlc_loading_progress_3));
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

            Coin sendCoin = WDp.getCoins(msg.value.amount).get(0);
            WDp.showCoinDp(getBaseContext(), getBaseDao(), sendCoin, sendDenom, sendAmount, mBaseChain);

            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), feeDenom);
            feeAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(FEE_BNB_SEND), 0, 8));

            senderTv.setText(msg.value.from);
            relayRecipientTv.setText(msg.value.to);
            relaySenderTv.setText(msg.value.sender_other_chain);
            recipientTv.setText(msg.value.recipient_other_chain);
            randomHashTv.setText(msg.value.random_number_hash);

        } else if ((mBaseChain.equals(BaseChain.KAVA_MAIN)) && mResSendTxInfo != null) {
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

            Coin sendCoin = WDp.getCoins(msg.value.amount).get(0);
            sendDenom.setText(sendCoin.denom.toUpperCase());
            sendAmount.setText(WDp.getDpAmount2(this, new BigDecimal(sendCoin.amount), WUtil.getKavaCoinDecimal(getBaseDao(), sendCoin.denom), WUtil.getKavaCoinDecimal(getBaseDao(), sendCoin.denom)));

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
                        WDp.showCoinDp(getBaseContext(), getBaseDao(), receiveCoin, claimDenom, claimAmount, mRecipientChain);
                    } else {
                        claimDenom.setText("");
                        claimAmount.setText("");
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
            ApiClient.getBnbChain(getBaseContext()).getSearchTx(hash, "json").enqueue(new Callback<ResBnbTxInfo>() {
                @Override
                public void onResponse(Call<ResBnbTxInfo> call, Response<ResBnbTxInfo> response) {
                    if(isFinishing()) return;
                    WLog.w("onFetchSendTx " + response.toString());
                    if(response.isSuccessful() && response.body() != null) {
                        mResSendBnbTxInfo = response.body();
                    }
                    onUpdateSendView();
                }

                @Override
                public void onFailure(Call<ResBnbTxInfo> call, Throwable t) {
                    WLog.w("onFetchSendTx BNB onFailure");
                    if(BuildConfig.DEBUG) t.printStackTrace();
                }
            });

        } else if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            ApiClient.getKavaChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
                @Override
                public void onResponse(Call<ResTxInfo> call, Response<ResTxInfo> response) {
                    if(isFinishing()) return;
                    WLog.w("onFetchSendTx " + response.toString());
                    if(response.isSuccessful() && response.body() != null) {
                        mResSendTxInfo = response.body();
                    }
                    onUpdateSendView();
                }

                @Override
                public void onFailure(Call<ResTxInfo> call, Throwable t) {
                    WLog.w("onFetchSendTx KAVA onFailure");
                    if(BuildConfig.DEBUG) t.printStackTrace();
                }
            });
        }
    }

    private int ClaimFetchCnt = 0;
    private void onFetchClaimTx(String hash) {
        WLog.w("onFetchClaimTx " + hash);
        if (mRecipientChain.equals(BaseChain.BNB_MAIN)) {
            ApiClient.getBnbChain(getBaseContext()).getSearchTx(hash, "json").enqueue(new Callback<ResBnbTxInfo>() {
                @Override
                public void onResponse(Call<ResBnbTxInfo> call, Response<ResBnbTxInfo> response) {
                    if(isFinishing()) return;
                    WLog.w("onFetchClaimTx " + response.toString());
                    if(response.isSuccessful() && response.body() != null) {
                        mResReceiveBnbTxInfo = response.body();
                        onUpdateView();
                    } else {
                        if (ClaimFetchCnt < 50) {
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
                    if(BuildConfig.DEBUG) t.printStackTrace();
                    onUpdateView();
                }
            });

        } else if (mRecipientChain.equals(BaseChain.KAVA_MAIN)) {
            ApiClient.getKavaChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
                @Override
                public void onResponse(Call<ResTxInfo> call, Response<ResTxInfo> response) {
                    if(isFinishing()) return;
                    WLog.w("onFetchClaimTx " + response.toString());
                    if(response.isSuccessful() && response.body() != null) {
                        mResReceiveTxInfo = response.body();
                        onUpdateView();
                    } else {
                        if (ClaimFetchCnt < 20) {
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
                    if(BuildConfig.DEBUG) t.printStackTrace();
                    onUpdateView();
                }
            });
        }
    }

    private void onCreateHTLC() {
        if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            new KavaCreateHTLCGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mRecipientAccount.address, mTargetCoins, mSendFee,
                    getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            new HtlcCreateTask(getBaseApplication(), this, mAccount, mRecipientAccount, mBaseChain, mRecipientChain, mTargetCoins, mSendFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }

    }

    //Check HTLC SWAP ID
    private int SwapFetchCnt = 0;
    private void onCheckSwapId(String expectedSwapId) {
//        WLog.w("onCheckSwapId " + SwapFetchCnt + " " + expectedSwapId);
        if (mRecipientChain.equals(BaseChain.KAVA_MAIN)) {
            ApiClient.getKavaChain(this).getSwapById(expectedSwapId).enqueue(new Callback<ResKavaSwapInfo>() {
                @Override
                public void onResponse(Call<ResKavaSwapInfo> call, Response<ResKavaSwapInfo> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().result != null) {
                        onClaimHTLC();
                    } else {
                        onHandleNotfound(expectedSwapId);
                    }
                }

                @Override
                public void onFailure(Call<ResKavaSwapInfo> call, Throwable t) {
                    onHandleNotfound(expectedSwapId);
                }
            });

        } else if (mRecipientChain.equals(BaseChain.BNB_MAIN)) {
            ApiClient.getBnbChain(this).getSwapById(expectedSwapId).enqueue(new Callback<ResBnbSwapInfo>() {
                @Override
                public void onResponse(Call<ResBnbSwapInfo> call, Response<ResBnbSwapInfo> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().swapId != null) {
                        onClaimHTLC();
                    } else {
                        onHandleNotfound(expectedSwapId);
                    }
                }

                @Override
                public void onFailure(Call<ResBnbSwapInfo> call, Throwable t) {
                    onHandleNotfound(expectedSwapId);

                }
            });
        }
    }

    //Claim HTLC TX
    private void onClaimHTLC() {
        onUpdateProgress(2);
        if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            new KavaClaimHTLCGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mExpectedSwapId, mRandomNumber, mClaimFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            new HtlcClaimTask(getBaseApplication(), this, mRecipientAccount, mRecipientChain, mClaimFee, mExpectedSwapId, mRandomNumber).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }


    }

    private void onHandleNotfound(String expectedSwapId) {
        if (SwapFetchCnt < 10) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SwapFetchCnt++;
                    onCheckSwapId(expectedSwapId);
                }
            }, 6000);
        } else {
            onShowMoreSwapWait();
        }
    }

    //SWAP ID LOOP CHECK
    private void onShowMoreSwapWait() {
        Dialog_MoreSwapWait waitSwapMore = Dialog_MoreSwapWait.newInstance(null);
        waitSwapMore.setCancelable(false);
        getSupportFragmentManager().beginTransaction().add(waitSwapMore, "dialog").commitNowAllowingStateLoss();

    }

    public void onWaitSwapMore() {
        SwapFetchCnt = 0;
        onCheckSwapId(mExpectedSwapId);

    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (result.taskType == TASK_GRPC_GEN_TX_KAVA_CREATE_HTLC) {
            if (result.isSuccess) {
                mCreateTxHash = result.resultData.toString();
                mExpectedSwapId = result.resultData2;
                mRandomNumber = result.resultData3;
                onUpdateProgress(1);
                onCheckSwapId(mExpectedSwapId);

            } else {
                Bundle bundle = new Bundle();
                bundle.putString("msg", getString(R.string.str_swap_error_msg_create));
                bundle.putString("error", result.errorMsg);
                Dialog_Htlc_Error swapError = Dialog_Htlc_Error.newInstance(bundle);
                swapError.setCancelable(false);
                getSupportFragmentManager().beginTransaction().add(swapError, "dialog").commitNowAllowingStateLoss();

            }

        } else if (result.taskType == TASK_GEN_TX_HTLC_CLAIM) {
            if (result.isSuccess) {
                WLog.w("CLAIM HTLC HASH " + result.resultData.toString());
                mClaimTxHash = result.resultData.toString();
                onUpdateProgress(3);
                onFetchSendTx(mCreateTxHash);
                onFetchClaimTx(mClaimTxHash);

            } else {
                Bundle bundle = new Bundle();
                bundle.putString("msg", getString(R.string.str_swap_error_msg_claim));
                bundle.putString("error", result.errorMsg);
                Dialog_Htlc_Error swapError = Dialog_Htlc_Error.newInstance(bundle);
                swapError.setCancelable(false);
                getSupportFragmentManager().beginTransaction().add(swapError, "dialog").commitNowAllowingStateLoss();

            }
        }
    }

}
