package wannabit.io.cosmostaion.activities.txs.kava;

import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GEN_TX_HTLC_CLAIM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GEN_TX_HTLC_CREATE;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
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
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.google.protobuf.InvalidProtocolBufferException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.tx.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
import io.grpc.stub.StreamObserver;
import kava.bep3.v1beta1.Tx;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.network.res.ResBnbSwapInfo;
import wannabit.io.cosmostaion.network.res.ResBnbTxInfo;
import wannabit.io.cosmostaion.network.res.ResKavaSwapInfo;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.HtlcClaimTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.HtlcCreateTask;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class HtlcResultActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar mToolbar;
    private NestedScrollView mTxScrollView;
    private RelativeLayout mLoadingLayer;
    private TextView mLoadingProgress;
    private LinearLayout mControlLayer;
    private Button mSenderBtn, mReceiverBtn;

    private ArrayList<Coin> mTargetCoins;
    private BaseChain mRecipientChain;
    private Account mRecipientAccount;
    private Fee mSendFee;
    private Fee mClaimFee;

    private String mExpectedSwapId;
    private String mRandomNumber;
    private String mCreateTxHash;
    private String mClaimTxHash;
    private ResBnbTxInfo mResSendBnbTxInfo;
    private ResBnbTxInfo mResReceiveBnbTxInfo;
    private ServiceOuterClass.GetTxResponse mResponse;

    private ChainConfig mFromChainConfig, mToChainConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htls_result);
        mToolbar = findViewById(R.id.tool_bar);
        mTxScrollView = findViewById(R.id.scroll_layer);
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

        mFromChainConfig = ChainFactory.getChain(mBaseChain);
        mToChainConfig = ChainFactory.getChain(mRecipientChain);

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

        iconImg.setColorFilter(ContextCompat.getColor(this, mFromChainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);
        if (mBaseChain.equals(BaseChain.BNB_MAIN) && mResSendBnbTxInfo != null) {
            final Msg msg = mResSendBnbTxInfo.tx.value.msg.get(0);
            if (mResSendBnbTxInfo.ok) {
                statusImg.setImageResource(R.drawable.success_ic);
                statusTv.setText(R.string.str_success_c);
            } else {
                statusImg.setImageResource(R.drawable.fail_ic);
                statusTv.setText(R.string.str_failed_c);
                errorTv.setText(mResSendBnbTxInfo.log);
                errorTv.setVisibility(View.VISIBLE);
            }

            blockHeightTv.setText(mResSendBnbTxInfo.height);
            txHashTv.setText(mResSendBnbTxInfo.hash);
            memoTv.setText(mResSendBnbTxInfo.tx.value.memo);

            Coin sendCoin = WDp.getCoins(msg.value.amount).get(0);
            sendAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(sendCoin.amount), 8, 8));
            sendDenom.setText(sendCoin.denom.toUpperCase());
            WDp.setDpCoin(this, getBaseDao(), mFromChainConfig, mSendFee.amount.get(0), feeDenom, feeAmount);

            senderTv.setText(msg.value.from);
            relayRecipientTv.setText(msg.value.to);
            relaySenderTv.setText(msg.value.sender_other_chain);
            recipientTv.setText(msg.value.recipient_other_chain);
            randomHashTv.setText(msg.value.random_number_hash);

        } else if (mBaseChain.equals(BaseChain.KAVA_MAIN) && mResponse != null) {
            if (mResponse.getTxResponse().getCode() != 0) {
                statusImg.setImageResource(R.drawable.fail_ic);
                statusTv.setText(R.string.str_failed_c);
                errorTv.setVisibility(View.VISIBLE);
                errorTv.setText(mResponse.getTxResponse().getRawLog());
            } else {
                statusImg.setImageResource(R.drawable.success_ic);
                statusTv.setText(R.string.str_success_c);
            }

            blockHeightTv.setText("" + mResponse.getTxResponse().getHeight());
            txHashTv.setText(mResponse.getTxResponse().getTxhash());
            memoTv.setText(mResponse.getTx().getBody().getMemo());

            try {
                Tx.MsgCreateAtomicSwap msg = Tx.MsgCreateAtomicSwap.parseFrom(mResponse.getTx().getBody().getMessages(0).getValue());
                List<Coin> coins = new ArrayList<>();
                for (CoinOuterClass.Coin coin : msg.getAmountList()) {
                    coins.add(new Coin(coin.getDenom(), coin.getAmount()));
                }

                Coin sendCoin = new Coin(msg.getAmount(0).getDenom(), msg.getAmount(0).getAmount());
                sendDenom.setText(sendCoin.denom.toUpperCase());
                sendAmount.setText(WDp.getDpAmount2(this, new BigDecimal(sendCoin.amount), 8, 8));
                WDp.setDpCoin(this, getBaseDao(), mFromChainConfig, mSendFee.amount.get(0), feeDenom, feeAmount);

                senderTv.setText(msg.getFrom());
                relayRecipientTv.setText(msg.getTo());
                relaySenderTv.setText(msg.getSenderOtherChain());
                recipientTv.setText(msg.getRecipientOtherChain());
                randomHashTv.setText(msg.getRandomNumberHash());
            } catch (InvalidProtocolBufferException e) {
            }
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

        iconImg.setColorFilter(ContextCompat.getColor(this, mToChainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);
        if (mRecipientChain.equals(BaseChain.BNB_MAIN) && mResReceiveBnbTxInfo != null) {
            final Msg msg = mResReceiveBnbTxInfo.tx.value.msg.get(0);
            if (mResReceiveBnbTxInfo.ok) {
                statusImg.setImageResource(R.drawable.success_ic);
                statusTv.setText(R.string.str_success_c);
            } else {
                statusImg.setImageResource(R.drawable.fail_ic);
                statusTv.setText(R.string.str_failed_c);
                errorTv.setText(mResReceiveBnbTxInfo.log);
                errorTv.setVisibility(View.VISIBLE);
            }

            blockHeightTv.setText(mResReceiveBnbTxInfo.height);
            txHashTv.setText(mResReceiveBnbTxInfo.hash);
            memoTv.setText(mResReceiveBnbTxInfo.tx.value.memo);

            claimDenom.setText("");
            claimAmount.setText("");

            WDp.setDpCoin(this, getBaseDao(), mToChainConfig, mToChainConfig.mainDenom(), FEE_BNB_SEND, feeDenom, feeAmount);

            claimerTv.setText(msg.value.from);
            randomNumberTv.setText(msg.value.random_number);
            swapIdTv.setText(msg.value.swap_id);

        } else if (mResponse != null && mRecipientChain.equals(BaseChain.KAVA_MAIN)) {
            if (mResponse.getTxResponse().getCode() != 0) {
                statusImg.setImageResource(R.drawable.fail_ic);
                statusTv.setText(R.string.str_failed_c);
                errorTv.setVisibility(View.VISIBLE);
                errorTv.setText(mResponse.getTxResponse().getRawLog());
            } else {
                statusImg.setImageResource(R.drawable.success_ic);
                statusTv.setText(R.string.str_success_c);
            }

            blockHeightTv.setText("" + mResponse.getTxResponse().getHeight());
            txHashTv.setText(mResponse.getTxResponse().getTxhash());
            memoTv.setText(mResponse.getTx().getBody().getMemo());

            try {
                Tx.MsgClaimAtomicSwap msg = Tx.MsgClaimAtomicSwap.parseFrom(mResponse.getTx().getBody().getMessages(0).getValue());
                claimDenom.setText("");
                claimAmount.setText("");
                feeDenom.setText("");
                feeAmount.setText("");

                claimerTv.setText(msg.getFrom());
                randomNumberTv.setText(msg.getRandomNumber());
                swapIdTv.setText(msg.getSwapId());

            } catch (InvalidProtocolBufferException e) {
            }
        }
    }

    private void onFetchSendTx(String hash) {
        if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
            ApiClient.getBnbChain().getSearchTx(hash, "json").enqueue(new Callback<ResBnbTxInfo>() {
                @Override
                public void onResponse(Call<ResBnbTxInfo> call, Response<ResBnbTxInfo> response) {
                    if (isFinishing()) return;
                    if (response.isSuccessful() && response.body() != null) {
                        mResSendBnbTxInfo = response.body();
                    }
                    onUpdateSendView();
                }

                @Override
                public void onFailure(Call<ResBnbTxInfo> call, Throwable t) {
                    if (BuildConfig.DEBUG) t.printStackTrace();
                }
            });

        } else if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            ServiceGrpc.ServiceStub mStub = ServiceGrpc.newStub(ChannelBuilder.getChain(BaseChain.KAVA_MAIN));
            ServiceOuterClass.GetTxRequest request = ServiceOuterClass.GetTxRequest.newBuilder().setHash(hash).build();
            mStub.getTx(request, new StreamObserver<ServiceOuterClass.GetTxResponse>() {
                @Override
                public void onNext(ServiceOuterClass.GetTxResponse response) {
                    runOnUiThread(() -> {
                        if (response != null && response.hasTxResponse()) {
                            mResponse = response;
                        }
                        onUpdateSendView();
                    });
                }

                @Override
                public void onError(Throwable t) {
                    if (BuildConfig.DEBUG) t.printStackTrace();
                }

                @Override
                public void onCompleted() {
                }
            });
        }
    }

    private int ClaimFetchCnt = 0;

    private void onFetchClaimTx(String hash) {
        if (mRecipientChain.equals(BaseChain.BNB_MAIN)) {
            ApiClient.getBnbChain().getSearchTx(hash, "json").enqueue(new Callback<ResBnbTxInfo>() {
                @Override
                public void onResponse(Call<ResBnbTxInfo> call, Response<ResBnbTxInfo> response) {
                    if (isFinishing()) return;
                    if (response.isSuccessful() && response.body() != null) {
                        mResReceiveBnbTxInfo = response.body();
                        onUpdateView();
                    } else {
                        if (ClaimFetchCnt < 20) {
                            new Handler().postDelayed(() -> {
                                ClaimFetchCnt++;
                                onFetchClaimTx(hash);
                            }, 3000);

                        } else {
                            onUpdateView();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResBnbTxInfo> call, Throwable t) {
                    WLog.w("onFetchClaimTx BNB onFailure");
                    if (BuildConfig.DEBUG) t.printStackTrace();
                    onUpdateView();
                }
            });

        } else if (mRecipientChain.equals(BaseChain.KAVA_MAIN)) {
            ServiceGrpc.ServiceStub mStub = ServiceGrpc.newStub(ChannelBuilder.getChain(BaseChain.KAVA_MAIN));
            ServiceOuterClass.GetTxRequest request = ServiceOuterClass.GetTxRequest.newBuilder().setHash(hash).build();
            mStub.getTx(request, new StreamObserver<ServiceOuterClass.GetTxResponse>() {
                @Override
                public void onNext(ServiceOuterClass.GetTxResponse response) {
                    runOnUiThread(() -> {
                        mResponse = response;
                        onUpdateView();
                    });
                }

                @Override
                public void onError(Throwable t) {
                    if (ClaimFetchCnt < 15) {
                        new Handler(Looper.getMainLooper()).postDelayed(() -> {
                            ClaimFetchCnt++;
                            onFetchClaimTx(hash);
                        }, 3000);
                    }
                }

                @Override
                public void onCompleted() {
                }
            });
        }
    }

    private void onCreateHTLC() {
        new HtlcCreateTask(getBaseApplication(), this, mAccount, mRecipientAccount, mBaseChain, mRecipientChain, mTargetCoins, mSendFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    //Check HTLC SWAP ID
    private int SwapFetchCnt = 0;

    private void onCheckSwapId(String expectedSwapId) {
        if (mRecipientChain.equals(BaseChain.KAVA_MAIN)) {
            ApiClient.getKavaChain().getSwapById(expectedSwapId).enqueue(new Callback<ResKavaSwapInfo>() {
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
            ApiClient.getBnbChain().getSwapById(expectedSwapId).enqueue(new Callback<ResBnbSwapInfo>() {
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
        new HtlcClaimTask(getBaseApplication(), this, mRecipientAccount, mRecipientChain, mClaimFee, mExpectedSwapId, mRandomNumber).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void onHandleNotfound(String expectedSwapId) {
        if (SwapFetchCnt < 10) {
            new Handler().postDelayed(() -> {
                SwapFetchCnt++;
                onCheckSwapId(expectedSwapId);
            }, 6000);
        } else {
            onShowMoreSwapWait();
        }
    }

    //SWAP ID LOOP CHECK
    private void onShowMoreSwapWait() {
        CommonAlertDialog.showDoubleButton(this, getString(R.string.str_more_wait_swap_title), getString(R.string.str_more_wait_swap_msg),
                getString(R.string.str_wait), view -> onWaitSwapMore(), getString(R.string.str_close), view -> onFinishWithError(), false);
    }

    public void onWaitSwapMore() {
        SwapFetchCnt = 0;
        onCheckSwapId(mExpectedSwapId);

    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (result.taskType == TASK_GEN_TX_HTLC_CREATE) {
            if (result.isSuccess) {
                mCreateTxHash = result.resultData.toString();
                mExpectedSwapId = result.resultData2;
                mRandomNumber = result.resultData3;
                onUpdateProgress(1);
                onCheckSwapId(mExpectedSwapId);

            } else {
                CommonAlertDialog.showSingleButton(this, getString(R.string.str_swap_error_title),
                        Html.fromHtml(getString(R.string.str_swap_error_msg_create) + "<br/><br/><font color=\"#ff0000\">" + CommonAlertDialog.highlightingText(result.errorMsg) + "</font>"),
                        getString(R.string.str_confirm), view -> onFinishWithError(), false);
            }

        } else if (result.taskType == TASK_GEN_TX_HTLC_CLAIM) {
            if (result.isSuccess) {
                mClaimTxHash = result.resultData.toString();
                onUpdateProgress(3);
                onFetchSendTx(mCreateTxHash);
                onFetchClaimTx(mClaimTxHash);

            } else {
                CommonAlertDialog.showSingleButton(this, getString(R.string.str_swap_error_title),
                        Html.fromHtml(getString(R.string.str_swap_error_msg_claim) + "<br/><br/><font color=\"#ff0000\">" + CommonAlertDialog.highlightingText(result.errorMsg) + "</font>"),
                        getString(R.string.str_confirm), view -> onFinishWithError(), false);
            }
        }
    }
}
