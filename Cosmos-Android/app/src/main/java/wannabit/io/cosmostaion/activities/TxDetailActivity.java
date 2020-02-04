package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import wannabit.io.cosmostaion.dialog.Dialog_MoreWait;
import wannabit.io.cosmostaion.model.type.Input;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Output;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBnbTxInfo;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_DELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_REDELEGATE2;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_TRANSFER2;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_TRANSFER3;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_UNDELEGATE2;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_DEL;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_MIDIFY;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_BROADCAST;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_DELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_REDELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_UNDELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_WITHDRAW;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_WITHDRAW_MIDIFY;

public class TxDetailActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private RecyclerView mTxRecyclerView;
    private CardView mErrorCardView;
    private TextView mErrorMsgTv;
    private RelativeLayout mLoadingLayer;
    private TextView mLoadingMsgTv;
    private LinearLayout mControlLayer;
    private Button mDismissBtn;
    private LinearLayout mControlLayer2;
    private Button mExplorerBtn, mShareBtn;

    private boolean mIsGen;
    private boolean mIsSuccess;
    private int mErrorCode;
    private String mErrorMsg;
    private String mTxHash;

    private TxDetailAdapter mTxDetailAdapter;
    private ResTxInfo mResTxInfo;
    private ResBnbTxInfo mResBnbTxInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tx_detail);
        mToolbar = findViewById(R.id.tool_bar);
        mTxRecyclerView = findViewById(R.id.tx_recycler);
        mErrorCardView = findViewById(R.id.error_Card);
        mErrorMsgTv = findViewById(R.id.error_details);
        mLoadingLayer = findViewById(R.id.loadingLayer);
        mLoadingMsgTv = findViewById(R.id.tx_loading_msg);
        mControlLayer = findViewById(R.id.bottom_control);
        mDismissBtn = findViewById(R.id.btn_dismiss);
        mControlLayer2 = findViewById(R.id.control_after);
        mExplorerBtn = findViewById(R.id.btn_scan);
        mShareBtn = findViewById(R.id.btn_share);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mAccount    = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain  = BaseChain.getChain(mAccount.baseChain);
        mIsGen      = getIntent().getBooleanExtra("isGen", false);
        mIsSuccess  = getIntent().getBooleanExtra("isSuccess", false);
        mErrorCode  = getIntent().getIntExtra("errorCode", BaseConstant.ERROR_CODE_UNKNOWN);
        mErrorMsg   = getIntent().getStringExtra("errorMsg");
        mTxHash     = getIntent().getStringExtra("txHash");
        mAllValidators = getBaseDao().getAllValidators();
        if (mIsGen) {
            mLoadingMsgTv.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(mTxHash)) {
            mLoadingLayer.setVisibility(View.GONE);
            mErrorCardView.setVisibility(View.VISIBLE);
            mErrorMsgTv.setText(getString(R.string.error_network));
            return;
        }

        if (mIsSuccess) {
            onFetchTx(mTxHash);

        } else {
            mLoadingLayer.setVisibility(View.GONE);
            mErrorCardView.setVisibility(View.VISIBLE);
            if(mErrorCode == ERROR_CODE_BROADCAST) {
                mErrorMsgTv.setText(getString(R.string.error_network));
            } else {
                mErrorMsgTv.setText("error code : " + mErrorCode + "\n" + mErrorMsg);
            }
        }

        mTxRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mTxRecyclerView.setHasFixedSize(true);
        mTxDetailAdapter = new TxDetailAdapter();
        mTxRecyclerView.setAdapter(mTxDetailAdapter);

        mDismissBtn.setOnClickListener(this);
        mExplorerBtn.setOnClickListener(this);
        mShareBtn.setOnClickListener(this);
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
        if (!mIsGen) {
            super.onBackPressed();
        } else {
            //TODO
        }
    }

    private void onUpdateView() {
        mLoadingLayer.setVisibility(View.GONE);
        mDismissBtn.setVisibility(View.GONE);
        mControlLayer2.setVisibility(View.VISIBLE);
        if (mBaseChain.equals(BaseChain.BNB_MAIN)) {

        } else {
            if (mResTxInfo != null) {
                mTxDetailAdapter.notifyDataSetChanged();
                mTxRecyclerView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mDismissBtn)) {

        } else if (v.equals(mExplorerBtn)) {
            Intent webintent = new Intent(this, WebActivity.class);
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN) || mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                webintent.putExtra("txid", mResTxInfo.txhash);
            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                webintent.putExtra("txid", mResTxInfo.hash);
            } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
                webintent.putExtra("txid", mResBnbTxInfo.hash);
            }
            webintent.putExtra("chain", mBaseChain.getChain());
            webintent.putExtra("goMain", mIsGen);
            startActivity(webintent);

        } else if (v.equals(mShareBtn)) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://www.mintscan.io/txs/" + mResTxInfo.txhash);
            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://irishub.mintscan.io/txs/" + mResTxInfo.hash);
            } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://explorer.binance.org/tx/" + mResBnbTxInfo.hash);
            } else if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://kava.mintscan.io/txs/" + mResTxInfo.txhash);
            }
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "send"));
        }

    }


    private class TxDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_TX_COMMON = 0;
        private static final int TYPE_TX_TRANSFER = 1;
        private static final int TYPE_TX_DELEGATE = 2;
        private static final int TYPE_TX_UNDELEGATE = 3;
        private static final int TYPE_TX_REDELEGATE = 4;
        private static final int TYPE_TX_REWARD = 5;
        private static final int TYPE_TX_ADDRESS_CHANGE = 6;
        private static final int TYPE_TX_VOTE = 7;
        private static final int TYPE_TX_MESSAGE = 10;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_TX_COMMON) {
                return new TxCommonHolder(getLayoutInflater().inflate(R.layout.item_tx_common, viewGroup, false));
            } else if (viewType == TYPE_TX_TRANSFER) {
                return new TxTransferHolder(getLayoutInflater().inflate(R.layout.item_tx_transfer, viewGroup, false));
            } else if (viewType == TYPE_TX_DELEGATE) {
                return new TxDelegateHolder(getLayoutInflater().inflate(R.layout.item_tx_delegate, viewGroup, false));
            } else if (viewType == TYPE_TX_UNDELEGATE) {
                return new TxUndelegateHolder(getLayoutInflater().inflate(R.layout.item_tx_undelegate, viewGroup, false));
            } else if (viewType == TYPE_TX_REDELEGATE) {
                return new TxRedelegateHolder(getLayoutInflater().inflate(R.layout.item_tx_redelegate, viewGroup, false));
            } else if (viewType == TYPE_TX_REWARD) {
                return new TxRewardHolder(getLayoutInflater().inflate(R.layout.item_tx_reward, viewGroup, false));
            } else if (viewType == TYPE_TX_ADDRESS_CHANGE) {
                return new TxAddressHolder(getLayoutInflater().inflate(R.layout.item_tx_reward_address, viewGroup, false));
            } else if (viewType == TYPE_TX_VOTE) {
                return new TxVoteHolder(getLayoutInflater().inflate(R.layout.item_tx_vote, viewGroup, false));
            }

            else {
//                return null;
                return new TxCommonHolder(getLayoutInflater().inflate(R.layout.item_tx_common, viewGroup, false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_TX_COMMON) {
                onBindCommon(viewHolder);
            } else if (getItemViewType(position) == TYPE_TX_TRANSFER) {
                onBindTransfer(viewHolder, mResTxInfo.getMsg(position - 1));
            } else if (getItemViewType(position) == TYPE_TX_DELEGATE) {
                onBindDelegate(viewHolder, mResTxInfo.getMsg(position - 1));
            } else if (getItemViewType(position) == TYPE_TX_UNDELEGATE) {
                onBindUndelegate(viewHolder, mResTxInfo.getMsg(position - 1));
            } else if (getItemViewType(position) == TYPE_TX_REDELEGATE) {
                onBindRedelegate(viewHolder, mResTxInfo.getMsg(position - 1));
            } else if (getItemViewType(position) == TYPE_TX_REWARD) {
                onBindReward(viewHolder, mResTxInfo.getMsg(position - 1));
            } else if (getItemViewType(position) == TYPE_TX_ADDRESS_CHANGE) {
                onBindAddress(viewHolder, mResTxInfo.getMsg(position - 1));
            } else if (getItemViewType(position) == TYPE_TX_VOTE) {
                onBindVote(viewHolder, mResTxInfo.getMsg(position - 1));
            }
        }

        @Override
        public int getItemCount() {
            if (mResTxInfo != null && mResTxInfo.tx.value.msg != null) {
                return mResTxInfo.tx.value.msg.size() + 1;
            }
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_TX_COMMON;

            } else {
                if (mResTxInfo.getMsgType(position - 1) .equals(COSMOS_MSG_TYPE_TRANSFER2) ||
                        mResTxInfo.getMsgType(position - 1) .equals(COSMOS_MSG_TYPE_TRANSFER3) ||
                        mResTxInfo.getMsgType(position - 1) .equals(IRIS_MSG_TYPE_TRANSFER)) {
                    return TYPE_TX_TRANSFER;

                } else if (mResTxInfo.getMsgType(position - 1) .equals(COSMOS_MSG_TYPE_DELEGATE) ||
                        mResTxInfo.getMsgType(position - 1) .equals(IRIS_MSG_TYPE_DELEGATE)) {
                    return TYPE_TX_DELEGATE;

                } else if (mResTxInfo.getMsgType(position - 1) .equals(COSMOS_MSG_TYPE_UNDELEGATE2) ||
                        mResTxInfo.getMsgType(position - 1) .equals(IRIS_MSG_TYPE_UNDELEGATE)) {
                    return TYPE_TX_UNDELEGATE;

                } else if (mResTxInfo.getMsgType(position - 1) .equals(COSMOS_MSG_TYPE_REDELEGATE2) ||
                        mResTxInfo.getMsgType(position - 1) .equals(IRIS_MSG_TYPE_REDELEGATE)) {
                    return TYPE_TX_REDELEGATE;

                } else if (mResTxInfo.getMsgType(position - 1) .equals(COSMOS_MSG_TYPE_WITHDRAW_DEL) ||
                        mResTxInfo.getMsgType(position - 1) .equals(IRIS_MSG_TYPE_WITHDRAW)) {
                    return TYPE_TX_REWARD;

                } else if (mResTxInfo.getMsgType(position - 1) .equals(COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) ||
                        mResTxInfo.getMsgType(position - 1) .equals(IRIS_MSG_TYPE_WITHDRAW_MIDIFY)) {
                    return TYPE_TX_ADDRESS_CHANGE;

                } else if (mResTxInfo.getMsgType(position - 1) .equals(COSMOS_MSG_TYPE_VOTE) ||
                        mResTxInfo.getMsgType(position - 1) .equals(IRIS_MSG_TYPE_VOTE)) {
                    return TYPE_TX_VOTE;

                } else {
                    return TYPE_TX_MESSAGE;
                }

            }
        }



        private void onBindCommon(RecyclerView.ViewHolder viewHolder) {
            final TxCommonHolder holder = (TxCommonHolder)viewHolder;
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemFeeDenom);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemFeeUsedDenom);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemFeeLimitDenom);
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN) || mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                if (mResTxInfo.isSuccess()) {
                    holder.itemStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.success_ic));
                    holder.itemStatusTxt.setText(R.string.str_success_c);
                } else {
                    holder.itemStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.fail_ic));
                    holder.itemStatusTxt.setText(R.string.str_failed_c);
                    holder.itemFailTxt.setText(mResTxInfo.failMessage());
                    holder.itemFailTxt.setVisibility(View.VISIBLE);
                }
                holder.itemHeight.setText(mResTxInfo.height);
                holder.itemMsgCnt.setText(String.valueOf(mResTxInfo.tx.value.msg.size()));
                holder.itemGas.setText(String.format("%s / %s", mResTxInfo.gas_used, mResTxInfo.gas_wanted));
                holder.itemFee.setText(WDp.getDpAmount(getBaseContext(), mResTxInfo.simpleFee(), 6, mBaseChain));
                holder.itemFeeLayer.setVisibility(View.VISIBLE);
                holder.itemTime.setText(WDp.getTimeTxformat(getBaseContext(), mResTxInfo.timestamp));
                holder.itemTimeGap.setText(WDp.getTimeTxGap(getBaseContext(), mResTxInfo.timestamp));
                holder.itemHash.setText(mResTxInfo.txhash);
                holder.itemMemo.setText(mResTxInfo.tx.value.memo);

            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {

            } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {

            }

        }

        private void onBindTransfer(RecyclerView.ViewHolder viewHolder, Msg msg) {
            final TxTransferHolder holder = (TxTransferHolder)viewHolder;
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemInputDenom0);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemInputDenom1);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemInputDenom2);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemInputDenom3);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemOutputDenom0);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemOutputDenom1);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemOutputDenom2);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemOutputDenom3);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemTransferAmountDenom);
            holder.itemSendReceiveImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            holder.itemSendRecieveTv.setTextColor(WDp.getChainColor(getBaseContext(), mBaseChain));
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN) || mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                if (msg.type.equals(COSMOS_MSG_TYPE_TRANSFER2))  {
                    holder.itemInputAddress0.setText(msg.value.from_address);
                    holder.itemOutputAddress0.setText(msg.value.to_address);
                    holder.itemTransferAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.getCoins().get(0).amount), 6, mBaseChain));
                    if (mAccount.address.equals(msg.value.from_address)) {
                        holder.itemSendRecieveTv.setText(R.string.tx_send);
                    }
                    if (mAccount.address.equals(msg.value.to_address)) {
                        holder.itemSendRecieveTv.setText(R.string.tx_receive);
                    }

                } else if (msg.type.equals(COSMOS_MSG_TYPE_TRANSFER3) && (msg.value.inputs != null && msg.value.outputs != null)) {
                    if (msg.value.inputs.size() == 1 && msg.value.outputs.size() == 1) {
                        holder.itemInputAddress0.setText(msg.value.inputs.get(0).address);
                        holder.itemOutputAddress0.setText(msg.value.outputs.get(0).address);
                        holder.itemTransferAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.inputs.get(0).coins.get(0).amount), 6, mBaseChain));
                        if (mAccount.address.equals(msg.value.inputs.get(0).address)) {
                            holder.itemSendRecieveTv.setText(R.string.tx_send);
                        }
                        if (mAccount.address.equals(msg.value.outputs.get(0).address)) {
                            holder.itemSendRecieveTv.setText(R.string.tx_receive);
                        }

                    } else {
                        holder.itemTransferAmountLayer.setVisibility(View.GONE);
                        holder.itemInputAmount0.setVisibility(View.VISIBLE);
                        holder.itemInputDenom0.setVisibility(View.VISIBLE);
                        holder.itemOutputAmount0.setVisibility(View.VISIBLE);
                        holder.itemOutputDenom0.setVisibility(View.VISIBLE);
                        holder.itemInputAddress0.setText(msg.value.inputs.get(0).address);
                        holder.itemInputAmount0.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.inputs.get(0).coins.get(0).amount), 6, mBaseChain));
                        holder.itemOutputAddress0.setText(msg.value.outputs.get(0).address);
                        holder.itemOutputAmount0.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.outputs.get(0).coins.get(0).amount), 6, mBaseChain));
                        if (msg.value.inputs.size() > 1) {
                            holder.itemInputLayer1.setVisibility(View.VISIBLE);
                            holder.itemInputAddress1.setText(msg.value.inputs.get(1).address);
                            holder.itemInputAmount1.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.inputs.get(1).coins.get(0).amount), 6, mBaseChain));
                        }
                        if (msg.value.inputs.size() > 2) {
                            holder.itemInputLayer2.setVisibility(View.VISIBLE);
                            holder.itemInputAddress2.setText(msg.value.inputs.get(2).address);
                            holder.itemInputAmount2.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.inputs.get(2).coins.get(0).amount), 6, mBaseChain));
                        }
                        if (msg.value.inputs.size() > 3) {
                            holder.itemInputLayer3.setVisibility(View.VISIBLE);
                            holder.itemInputAddress3.setText(msg.value.inputs.get(3).address);
                            holder.itemInputAmount3.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.inputs.get(3).coins.get(0).amount), 6, mBaseChain));
                        }
                        if (msg.value.outputs.size() > 1) {
                            holder.itemOutputLayer1.setVisibility(View.VISIBLE);
                            holder.itemOutputAddress1.setText(msg.value.outputs.get(1).address);
                            holder.itemOutputAmount1.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.outputs.get(1).coins.get(0).amount), 6, mBaseChain));
                        }
                        if (msg.value.outputs.size() > 2) {
                            holder.itemOutputLayer2.setVisibility(View.VISIBLE);
                            holder.itemOutputAddress2.setText(msg.value.outputs.get(2).address);
                            holder.itemOutputAmount2.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.outputs.get(2).coins.get(0).amount), 6, mBaseChain));
                        }
                        if (msg.value.outputs.size() > 3) {
                            holder.itemOutputLayer3.setVisibility(View.VISIBLE);
                            holder.itemOutputAddress3.setText(msg.value.outputs.get(3).address);
                            holder.itemOutputAmount3.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.outputs.get(3).coins.get(0).amount), 6, mBaseChain));
                        }
                        for (Input input:msg.value.inputs) {
                            if (mAccount.address.equals(input.address)) {
                                holder.itemSendRecieveTv.setText(R.string.tx_send);
                            }
                        }
                        for (Output output:msg.value.outputs) {
                            if (mAccount.address.equals(output.address)) {
                                holder.itemSendRecieveTv.setText(R.string.tx_receive);
                            }
                        }
                    }
                }

            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {

            } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {

            }
        }

        private void onBindDelegate(RecyclerView.ViewHolder viewHolder, Msg msg) {
            final TxDelegateHolder holder = (TxDelegateHolder)viewHolder;
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemDelegateAmountDenom);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemAutoRewardAmountDenom);
            holder.itemDelegateImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            holder.itemDelegateTitle.setTextColor(WDp.getChainColor(getBaseContext(), mBaseChain));
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN) || mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                holder.itemDelegator.setText(msg.value.delegator_address);
                holder.itemMoniker.setText(WUtil.getMonikerName(msg.value.validator_address, mAllValidators));
                holder.itemValidator.setText(msg.value.validator_address);
                holder.itemDelegateAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.getCoins().get(0).amount), 6, mBaseChain));
                holder.itemAutoRewardAmount.setText(WDp.getDpAmount(getBaseContext(), mResTxInfo.simpleAutoReward(), 6, mBaseChain));
                if (mResTxInfo.getMsgs().size() == 1) {
                    holder.itemAutoRewardLayer.setVisibility(View.VISIBLE);
                }

            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {

            } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {

            }
        }

        private void onBindUndelegate(RecyclerView.ViewHolder viewHolder, Msg msg) {
            final TxUndelegateHolder holder = (TxUndelegateHolder)viewHolder;
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemUnDelegateAmountDenom);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemAutoRewardAmountDenom);
            holder.itemUndelegateImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            holder.itemUndelegateTitle.setTextColor(WDp.getChainColor(getBaseContext(), mBaseChain));
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN) || mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                holder.itemUnDelegator.setText(msg.value.delegator_address);
                holder.itemMoniker.setText(WUtil.getMonikerName(msg.value.validator_address, mAllValidators));
                holder.itemValidator.setText(msg.value.validator_address);
                holder.itemUndelegateAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.getCoins().get(0).amount), 6, mBaseChain));
                holder.itemAutoRewardAmount.setText(WDp.getDpAmount(getBaseContext(), mResTxInfo.simpleAutoReward(), 6, mBaseChain));
                if (mResTxInfo.getMsgs().size() == 1) {
                    holder.itemAutoRewardLayer.setVisibility(View.VISIBLE);
                }

            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {

            } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {

            }
        }

        private void onBindRedelegate(RecyclerView.ViewHolder viewHolder, Msg msg) {
            final TxRedelegateHolder holder = (TxRedelegateHolder)viewHolder;
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemReDelegateAmountDenom);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemAutoRewardAmountDenom);
            holder.itemRedelegateImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            holder.itemRedelegateTitle.setTextColor(WDp.getChainColor(getBaseContext(), mBaseChain));
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN) || mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                holder.itemReDelegator.setText(msg.value.delegator_address);
                holder.itemFromValidator.setText(msg.value.validator_src_address);
                holder.itemFromMoniker.setText(WUtil.getMonikerName(msg.value.validator_src_address, mAllValidators));
                holder.itemToValidator.setText(msg.value.validator_dst_address);
                holder.itemToMoniker.setText(WUtil.getMonikerName(msg.value.validator_dst_address, mAllValidators));
                holder.itemRedelegateAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.getCoins().get(0).amount), 6, mBaseChain));
                holder.itemAutoRewardAmount.setText(WDp.getDpAmount(getBaseContext(), mResTxInfo.simpleAutoReward(), 6, mBaseChain));
                if (mResTxInfo.getMsgs().size() == 1) {
                    holder.itemAutoRewardLayer.setVisibility(View.VISIBLE);
                }

            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {

            } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {

            }
        }

        private void onBindReward(RecyclerView.ViewHolder viewHolder, Msg msg) {
            final TxRewardHolder holder = (TxRewardHolder)viewHolder;
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemRewardAmountDenom);
            holder.itemRewardImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            holder.itemRewardTitle.setTextColor(WDp.getChainColor(getBaseContext(), mBaseChain));
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN) || mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                holder.itemDelegator.setText(msg.value.delegator_address);
                holder.itemMoniker.setText(WUtil.getMonikerName(msg.value.validator_address, mAllValidators));
                holder.itemValidator.setText(msg.value.validator_address);
                holder.itemRewardAmount.setText(WDp.getDpAmount(getBaseContext(), mResTxInfo.simpleAutoReward(), 6, mBaseChain));

            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {

            } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {

            }
        }

        private void onBindAddress(RecyclerView.ViewHolder viewHolder, Msg msg) {
            final TxAddressHolder holder = (TxAddressHolder)viewHolder;
            holder.itemAddressImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            holder.itemAddressTitle.setTextColor(WDp.getChainColor(getBaseContext(), mBaseChain));
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN) || mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                holder.itemDelegator.setText(msg.value.delegator_address);
                holder.itemWithdrawAddress.setText(msg.value.withdraw_address);

            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {

            } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {

            }
        }

        private void onBindVote(RecyclerView.ViewHolder viewHolder, Msg msg) {
            final TxVoteHolder holder = (TxVoteHolder)viewHolder;
            holder.itemVoteImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            holder.itemVoteTitle.setTextColor(WDp.getChainColor(getBaseContext(), mBaseChain));
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN) || mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                holder.itemDelegator.setText(msg.value.voter);
                holder.itemProposalId.setText(msg.value.proposal_id);
                holder.itemOpinion.setText(msg.value.option);

            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {

            } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {

            }
        }



        public class TxCommonHolder extends RecyclerView.ViewHolder {
            ImageView itemStatusImg;
            TextView itemStatusTxt, itemFailTxt, itemHash, itemHeight, itemMsgCnt, itemGas,
                    itemTime, itemTimeGap, itemMemo, itemFee, itemFeeDenom , itemFeeUsed, itemFeeUsedDenom,
                    itemFeeLimit, itemFeeLimitDenom;
            RelativeLayout itemFeeLayer, itemFeeUsedLayer, itemFeeLimitLayer;

            public TxCommonHolder(@NonNull View itemView) {
                super(itemView);
                itemStatusImg = itemView.findViewById(R.id.tx_status_img);
                itemStatusTxt = itemView.findViewById(R.id.tx_status);
                itemFailTxt = itemView.findViewById(R.id.tx_fail_msg);
                itemHeight = itemView.findViewById(R.id.tx_block_height);
                itemMsgCnt = itemView.findViewById(R.id.tx_msg_cnt);
                itemGas = itemView.findViewById(R.id.tx_gas_info);
                itemTime = itemView.findViewById(R.id.tx_block_time);
                itemTimeGap = itemView.findViewById(R.id.tx_block_time_gap);
                itemHash = itemView.findViewById(R.id.tx_hash);
                itemMemo = itemView.findViewById(R.id.str_tx_memo);

                itemFeeLayer = itemView.findViewById(R.id.tx_fee_layer);
                itemFee = itemView.findViewById(R.id.tx_fee);
                itemFeeDenom = itemView.findViewById(R.id.tx_fee_symbol);
                itemFeeUsedLayer = itemView.findViewById(R.id.tx_fee_used_layer);
                itemFeeUsed = itemView.findViewById(R.id.tx_used_fee);
                itemFeeUsedDenom = itemView.findViewById(R.id.tx_fee_used_symbol);
                itemFeeLimitLayer = itemView.findViewById(R.id.tx_fee_limit_layer);
                itemFeeLimit = itemView.findViewById(R.id.tx_limit_fee);
                itemFeeLimitDenom = itemView.findViewById(R.id.tx_fee_limit_symbol);
            }
        }

        public class TxTransferHolder extends RecyclerView.ViewHolder {
            ImageView itemSendReceiveImg;
            TextView itemSendRecieveTv;
            RelativeLayout itemInputLayer0, itemInputLayer1, itemInputLayer2,itemInputLayer3;
            TextView itemInputAddress0, itemInputAddress1, itemInputAddress2, itemInputAddress3;
            TextView itemInputAmount0, itemInputAmount1, itemInputAmount2, itemInputAmount3;
            TextView itemInputDenom0, itemInputDenom1, itemInputDenom2, itemInputDenom3;
            RelativeLayout itemOutputLayer0, itemOutputLayer1, itemOutputLayer2,itemOutputLayer3;
            TextView itemOutputAddress0, itemOutputAddress1, itemOutputAddress2, itemOutputAddress3;
            TextView itemOutputAmount0, itemOutputAmount1, itemOutputAmount2, itemOutputAmount3;
            TextView itemOutputDenom0, itemOutputDenom1, itemOutputDenom2, itemOutputDenom3;
            RelativeLayout itemTransferAmountLayer;
            TextView itemTransferAmount, itemTransferAmountDenom;

            public TxTransferHolder(@NonNull View itemView) {
                super(itemView);
                itemSendReceiveImg = itemView.findViewById(R.id.tx_send_icon);
                itemSendRecieveTv = itemView.findViewById(R.id.tx_send_text);

                itemInputLayer0 = itemView.findViewById(R.id.tx_send_from0);
                itemInputLayer1 = itemView.findViewById(R.id.tx_send_from1);
                itemInputLayer2 = itemView.findViewById(R.id.tx_send_from2);
                itemInputLayer3 = itemView.findViewById(R.id.tx_send_from3);
                itemInputAddress0 = itemView.findViewById(R.id.tx_send_from0_address);
                itemInputAddress1 = itemView.findViewById(R.id.tx_send_from1_address);
                itemInputAddress2 = itemView.findViewById(R.id.tx_send_from2_address);
                itemInputAddress3 = itemView.findViewById(R.id.tx_send_from3_address);
                itemInputAmount0 = itemView.findViewById(R.id.tx_send_from0_amount);
                itemInputAmount1 = itemView.findViewById(R.id.tx_send_from1_amount);
                itemInputAmount2 = itemView.findViewById(R.id.tx_send_from2_amount);
                itemInputAmount3 = itemView.findViewById(R.id.tx_send_from3_amount);
                itemInputDenom0 = itemView.findViewById(R.id.tx_send_from0_symbol);
                itemInputDenom1 = itemView.findViewById(R.id.tx_send_from1_symbol);
                itemInputDenom2 = itemView.findViewById(R.id.tx_send_from2_symbol);
                itemInputDenom3 = itemView.findViewById(R.id.tx_send_from3_symbol);

                itemOutputLayer0 = itemView.findViewById(R.id.tx_send_to0);
                itemOutputLayer1 = itemView.findViewById(R.id.tx_send_to1);
                itemOutputLayer2 = itemView.findViewById(R.id.tx_send_to2);
                itemOutputLayer3 = itemView.findViewById(R.id.tx_send_to3);
                itemOutputAddress0 = itemView.findViewById(R.id.tx_send_to0_address);
                itemOutputAddress1 = itemView.findViewById(R.id.tx_send_to1_address);
                itemOutputAddress2 = itemView.findViewById(R.id.tx_send_to2_address);
                itemOutputAddress3 = itemView.findViewById(R.id.tx_send_to3_address);
                itemOutputAmount0 = itemView.findViewById(R.id.tx_send_to0_amount);
                itemOutputAmount1 = itemView.findViewById(R.id.tx_send_to1_amount);
                itemOutputAmount2 = itemView.findViewById(R.id.tx_send_to2_amount);
                itemOutputAmount3 = itemView.findViewById(R.id.tx_send_to3_amount);
                itemOutputDenom0 = itemView.findViewById(R.id.tx_send_to0_symbol);
                itemOutputDenom1 = itemView.findViewById(R.id.tx_send_to1_symbol);
                itemOutputDenom2 = itemView.findViewById(R.id.tx_send_to2_symbol);
                itemOutputDenom3 = itemView.findViewById(R.id.tx_send_to3_symbol);

                itemTransferAmountLayer = itemView.findViewById(R.id.tx_transfer_amount_layer);
                itemTransferAmount = itemView.findViewById(R.id.tx_transfer_amount);
                itemTransferAmountDenom = itemView.findViewById(R.id.tx_transfer_amount_symbol);
            }
        }

        public class TxDelegateHolder extends RecyclerView.ViewHolder {
            ImageView itemDelegateImg;
            TextView itemDelegateTitle;
            TextView itemDelegator, itemValidator, itemMoniker, itemDelegateAmount, itemDelegateAmountDenom,
                    itemAutoRewardAmount, itemAutoRewardAmountDenom;
            RelativeLayout itemAutoRewardLayer;

            public TxDelegateHolder(@NonNull View itemView) {
                super(itemView);
                itemDelegateImg = itemView.findViewById(R.id.tx_delegate_icon);
                itemDelegateTitle = itemView.findViewById(R.id.tx_delegate_text);
                itemDelegator = itemView.findViewById(R.id.tx_delegate_delegator);
                itemValidator = itemView.findViewById(R.id.tx_delegate_validator);
                itemMoniker = itemView.findViewById(R.id.tx_delegate_moniker);
                itemDelegateAmount = itemView.findViewById(R.id.tx_delegate_amount);
                itemDelegateAmountDenom = itemView.findViewById(R.id.tx_delegate_amount_symbol);
                itemAutoRewardAmount = itemView.findViewById(R.id.tx_auto_claimed);
                itemAutoRewardAmountDenom = itemView.findViewById(R.id.tx_auto_claimed_symbol);
                itemAutoRewardLayer = itemView.findViewById(R.id.tx_delegate_auto_reward);
            }
        }

        public class TxUndelegateHolder extends RecyclerView.ViewHolder {
            ImageView itemUndelegateImg;
            TextView itemUndelegateTitle;
            TextView itemUnDelegator, itemValidator, itemMoniker, itemUndelegateAmount, itemUnDelegateAmountDenom,
                    itemAutoRewardAmount, itemAutoRewardAmountDenom;
            RelativeLayout itemAutoRewardLayer;

            public TxUndelegateHolder(@NonNull View itemView) {
                super(itemView);
                itemUndelegateImg = itemView.findViewById(R.id.tx_undelegate_icon);
                itemUndelegateTitle = itemView.findViewById(R.id.tx_undelegate_text);
                itemUnDelegator = itemView.findViewById(R.id.tx_undelegate_undelegator);
                itemValidator = itemView.findViewById(R.id.tx_undelegate_validator);
                itemMoniker = itemView.findViewById(R.id.tx_undelegate_moniker);
                itemUndelegateAmount = itemView.findViewById(R.id.tx_undelegate_amount);
                itemUnDelegateAmountDenom = itemView.findViewById(R.id.tx_undelegate_amount_symbol);
                itemAutoRewardAmount = itemView.findViewById(R.id.tx_auto_claimed);
                itemAutoRewardAmountDenom = itemView.findViewById(R.id.tx_auto_claimed_symbol);
                itemAutoRewardLayer = itemView.findViewById(R.id.tx_undelegate_auto_reward);
            }
        }

        public class TxRedelegateHolder extends RecyclerView.ViewHolder {
            ImageView itemRedelegateImg;
            TextView itemRedelegateTitle;
            TextView itemReDelegator, itemFromValidator, itemFromMoniker, itemToValidator, itemToMoniker, itemRedelegateAmount, itemReDelegateAmountDenom,
                    itemAutoRewardAmount, itemAutoRewardAmountDenom;
            RelativeLayout itemAutoRewardLayer;

            public TxRedelegateHolder(@NonNull View itemView) {
                super(itemView);
                itemRedelegateImg = itemView.findViewById(R.id.tx_redelegate_icon);
                itemRedelegateTitle = itemView.findViewById(R.id.tx_undelegate_text);
                itemReDelegator = itemView.findViewById(R.id.tx_redelegate_redelegator);
                itemFromValidator = itemView.findViewById(R.id.tx_redelegate_from_validator);
                itemFromMoniker = itemView.findViewById(R.id.tx_redelegate_from_moniker);
                itemToValidator = itemView.findViewById(R.id.tx_redelegate_to_validator);
                itemToMoniker = itemView.findViewById(R.id.tx_redelegate_to_moniker);
                itemRedelegateAmount = itemView.findViewById(R.id.tx_redelegate_amount);
                itemReDelegateAmountDenom = itemView.findViewById(R.id.tx_redelegate_amount_symbol);
                itemAutoRewardAmount = itemView.findViewById(R.id.tx_auto_claimed);
                itemAutoRewardAmountDenom = itemView.findViewById(R.id.tx_auto_claimed_symbol);
                itemAutoRewardLayer = itemView.findViewById(R.id.tx_redelegate_auto_reward);
            }
        }

        public class TxRewardHolder extends RecyclerView.ViewHolder {
            ImageView itemRewardImg;
            TextView itemRewardTitle;
            TextView itemDelegator, itemValidator, itemMoniker, itemRewardAmount, itemRewardAmountDenom;

            public TxRewardHolder(@NonNull View itemView) {
                super(itemView);
                itemRewardImg = itemView.findViewById(R.id.tx_reward_icon);
                itemRewardTitle = itemView.findViewById(R.id.tx_reward_text);
                itemDelegator = itemView.findViewById(R.id.tx_reward_delegator);
                itemValidator = itemView.findViewById(R.id.tx_reward_validator);
                itemMoniker = itemView.findViewById(R.id.tx_reward_moniker);
                itemRewardAmount = itemView.findViewById(R.id.tx_reward_amount);
                itemRewardAmountDenom = itemView.findViewById(R.id.tx_reward_amount_symbol);
            }
        }

        public class TxAddressHolder extends RecyclerView.ViewHolder {
            ImageView itemAddressImg;
            TextView itemAddressTitle;
            TextView itemDelegator, itemWithdrawAddress;

            public TxAddressHolder(@NonNull View itemView) {
                super(itemView);
                itemAddressImg = itemView.findViewById(R.id.tx_address_icon);
                itemAddressTitle = itemView.findViewById(R.id.tx_address_text);
                itemDelegator = itemView.findViewById(R.id.tx_address_delegator);
                itemWithdrawAddress = itemView.findViewById(R.id.tx_address_withdraw);
            }
        }

        public class TxVoteHolder extends RecyclerView.ViewHolder {
            ImageView itemVoteImg;
            TextView itemVoteTitle;
            TextView itemDelegator, itemProposalId, itemOpinion;

            public TxVoteHolder(@NonNull View itemView) {
                super(itemView);
                itemVoteImg = itemView.findViewById(R.id.tx_vote_icon);
                itemVoteTitle = itemView.findViewById(R.id.tx_vote_text);
                itemDelegator = itemView.findViewById(R.id.tx_vote_voter);
                itemProposalId = itemView.findViewById(R.id.tx_vote_proposal_id);
                itemOpinion = itemView.findViewById(R.id.tx_vote_proposal_opinion);
            }
        }
    }











    private void onShowMoreWait() {
        Dialog_MoreWait waitMore = Dialog_MoreWait.newInstance(null);
        waitMore.setCancelable(false);
        getSupportFragmentManager().beginTransaction().add(waitMore, "dialog").commitNowAllowingStateLoss();
    }

    public void onWaitMore() {
        FetchCnt = 0;
        onFetchTx(mTxHash);
    }

    private int FetchCnt = 0;
    private void onFetchTx(String hash) {
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            ApiClient.getCosmosChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
                @Override
                public void onResponse(Call<ResTxInfo> call, Response<ResTxInfo> response) {
                    if(isFinishing()) return;
                    WLog.w("onFetchTx " + response.toString());
                    if(response.isSuccessful() && response.body() != null) {
                        mResTxInfo = response.body();
                        onUpdateView();
                    } else {
                        if(mIsSuccess && FetchCnt < 10) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FetchCnt++;
                                    onFetchTx(mTxHash);
                                }
                            }, 6000);
                        } else {
                            onShowMoreWait();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResTxInfo> call, Throwable t) {
                    if(BaseConstant.IS_SHOWLOG) t.printStackTrace();
                    if(isFinishing()) return;
                }
            });

        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            ApiClient.getIrisChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
                @Override
                public void onResponse(Call<ResTxInfo> call, Response<ResTxInfo> response) {
                    if(isFinishing()) return;
                    WLog.w("onFetchTx " + response.toString());
                    if(response.isSuccessful() && response.body() != null) {
                        mResTxInfo = response.body();
                        onUpdateView();
                    } else {
                        if(mIsSuccess && FetchCnt < 10) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FetchCnt++;
                                    onFetchTx(mTxHash);
                                }
                            }, 6000);
                        } else {
                            onShowMoreWait();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResTxInfo> call, Throwable t) {
                    if(BaseConstant.IS_SHOWLOG) t.printStackTrace();
                    if(isFinishing()) return;
                }
            });

        } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
            ApiClient.getBnbChain(getBaseContext()).getSearchTx(hash, "json").enqueue(new Callback<ResBnbTxInfo>() {
                @Override
                public void onResponse(Call<ResBnbTxInfo> call, Response<ResBnbTxInfo> response) {
                    if(isFinishing()) return;
                    WLog.w("onFetchTx " + response.toString());
                    if(response.isSuccessful() && response.body() != null) {
                        mResBnbTxInfo = response.body();
                        onUpdateView();
                    } else {
                        if(mIsSuccess && FetchCnt < 10) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FetchCnt++;
                                    onFetchTx(mTxHash);
                                }
                            }, 6000);
                        } else {
                            onShowMoreWait();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResBnbTxInfo> call, Throwable t) {
                    WLog.w("BNB onFailure");
                    if(BaseConstant.IS_SHOWLOG) t.printStackTrace();
                    if(isFinishing()) return;
                }
            });

        } else if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            ApiClient.getKavaChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
                @Override
                public void onResponse(Call<ResTxInfo> call, Response<ResTxInfo> response) {
                    if(isFinishing()) return;
                    WLog.w("onFetchTx " + response.toString());
                    if(response.isSuccessful() && response.body() != null) {
                        mResTxInfo = response.body();
                        onUpdateView();
                    } else {
                        if(mIsSuccess && FetchCnt < 10) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FetchCnt++;
                                    onFetchTx(mTxHash);
                                }
                            }, 6000);
                        } else {
                            onShowMoreWait();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResTxInfo> call, Throwable t) {
                    if(BaseConstant.IS_SHOWLOG) t.printStackTrace();
                    if(isFinishing()) return;
                }
            });
        }
    }
}
