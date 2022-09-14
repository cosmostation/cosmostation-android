package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_TRANSFER2;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_BROADCAST;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_UNKNOWN;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.OK_MSG_TYPE_DEPOSIT;
import static wannabit.io.cosmostaion.base.BaseConstant.OK_MSG_TYPE_DIRECT_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.OK_MSG_TYPE_MULTI_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.OK_MSG_TYPE_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.OK_MSG_TYPE_WITHDRAW;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBnbTxInfo;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

public class TxDetailActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private RecyclerView mTxRecyclerView;
    private CardView mErrorCardView;
    private TextView mErrorMsgTv;
    private RelativeLayout mLoadingLayer;
    private TextView mLoadingMsgTv;
    private Button mDismissBtn;
    private RelativeLayout mRefundBtn;
    private LinearLayout mControlLayer2;
    private Button mShareBtn;
    private Button mExplorerBtn;

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
        mDismissBtn = findViewById(R.id.btn_dismiss);
        mControlLayer2 = findViewById(R.id.control_after);
        mShareBtn = findViewById(R.id.btn_share);
        mExplorerBtn = findViewById(R.id.btn_explorer);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mIsGen = getIntent().getBooleanExtra("isGen", false);
        mIsSuccess = getIntent().getBooleanExtra("isSuccess", false);
        mErrorCode = getIntent().getIntExtra("errorCode", ERROR_CODE_UNKNOWN);
        mErrorMsg = getIntent().getStringExtra("errorMsg");
        mTxHash = getIntent().getStringExtra("txHash");

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
            if (mErrorCode == ERROR_CODE_BROADCAST) {
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
        mShareBtn.setOnClickListener(this);
        mExplorerBtn.setOnClickListener(this);
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
            onStartMainActivity(0);
        }
    }

    private void onUpdateView() {
        mLoadingLayer.setVisibility(View.GONE);
        mControlLayer2.setVisibility(View.VISIBLE);

        if (mBaseChain.equals(BNB_MAIN)) {
            if (mResBnbTxInfo != null) {
                mTxDetailAdapter.notifyDataSetChanged();
                mTxRecyclerView.setVisibility(View.VISIBLE);
            }

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
            onBackPressed();

        } else if (v.equals(mShareBtn)) {
            String hash = "";
            if (mBaseChain.equals(BNB_MAIN)) hash = mResBnbTxInfo.hash;
            else hash = mResTxInfo.txhash;
            if (TextUtils.isEmpty(hash)) {
                return;
            }

            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            String url = mChainConfig.explorerHistoryLink(hash);
            shareIntent.putExtra(Intent.EXTRA_TEXT, url);
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "send"));

        } else if (v.equals(mExplorerBtn)) {
            String hash = "";
            if (mBaseChain.equals(BNB_MAIN)) hash = mResBnbTxInfo.hash;
            else hash = mResTxInfo.txhash;

            String url = mChainConfig.explorerHistoryLink(hash);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
    }


    private class TxDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_TX_COMMON = 0;
        private static final int TYPE_TX_TRANSFER = 1;

        private static final int TYPE_TX_OK_STAKE = 22;
        private static final int TYPE_TX_OK_DIRECT_VOTE = 23;

        private static final int TYPE_TX_UNKNOWN = 999;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_TX_COMMON) {
                return new TxCommonHolder(getLayoutInflater().inflate(R.layout.item_tx_common, viewGroup, false));
            } else if (viewType == TYPE_TX_TRANSFER) {
                return new TxTransferHolder(getLayoutInflater().inflate(R.layout.item_tx_transfer, viewGroup, false));
            } else if (viewType == TYPE_TX_OK_STAKE) {
                return new TxOkStakeHolder(getLayoutInflater().inflate(R.layout.item_tx_ok_stake, viewGroup, false));
            } else if (viewType == TYPE_TX_OK_DIRECT_VOTE) {
                return new TxOkVoteHolder(getLayoutInflater().inflate(R.layout.item_tx_ok_vote_validator, viewGroup, false));
            }
            return new TxUnKnownHolder(getLayoutInflater().inflate(R.layout.item_tx_unknown, viewGroup, false));

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_TX_COMMON) {
                onBindCommon(viewHolder);
            } else if (getItemViewType(position) == TYPE_TX_TRANSFER) {
                onBindTransfer(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_OK_STAKE) {
                onBindOkStake(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_OK_DIRECT_VOTE) {
                onBindOkDirectVote(viewHolder, position);
            } else {
                onBindUnKnown(viewHolder);
            }
        }

        @Override
        public int getItemCount() {
            if (mBaseChain.equals(BNB_MAIN)) {
                if (mResBnbTxInfo != null && mResBnbTxInfo.tx.value.msg != null) {
                    return mResBnbTxInfo.tx.value.msg.size() + 1;
                }
                return 0;
            } else {
                if (mResTxInfo != null && mResTxInfo.tx.value.msg != null) {
                    return mResTxInfo.tx.value.msg.size() + 1;
                }
                return 0;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_TX_COMMON;

            } else {
                if (mBaseChain.equals(BNB_MAIN)) {
                    if (mResBnbTxInfo.getMsgType(position - 1).equals(COSMOS_MSG_TYPE_TRANSFER)) {
                        return TYPE_TX_TRANSFER;
                    }
                    return TYPE_TX_UNKNOWN;

                } else {
                    if (mResTxInfo.getMsgType(position - 1).equals(COSMOS_MSG_TYPE_TRANSFER2) ||
                            mResTxInfo.getMsgType(position - 1).equals(OK_MSG_TYPE_TRANSFER) ||
                            mResTxInfo.getMsgType(position - 1).equals(OK_MSG_TYPE_MULTI_TRANSFER)) {
                        return TYPE_TX_TRANSFER;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(OK_MSG_TYPE_DEPOSIT) ||
                            mResTxInfo.getMsgType(position - 1).equals(OK_MSG_TYPE_WITHDRAW)) {
                        return TYPE_TX_OK_STAKE;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(OK_MSG_TYPE_DIRECT_VOTE)) {
                        return TYPE_TX_OK_DIRECT_VOTE;

                    }
                    return TYPE_TX_UNKNOWN;
                }
            }
        }


        private void onBindCommon(RecyclerView.ViewHolder viewHolder) {
            final TxCommonHolder holder = (TxCommonHolder) viewHolder;
            WDp.setDpSymbol(getBaseContext(), getBaseDao(), mChainConfig, mChainConfig.mainDenom(), holder.itemFeeDenom);
            WDp.setDpSymbol(getBaseContext(), getBaseDao(), mChainConfig, mChainConfig.mainDenom(), holder.itemFeeUsedDenom);

            if (mBaseChain.equals(BNB_MAIN)) {
                holder.itemStatusImg.setImageResource(R.drawable.success_ic);
                holder.itemStatusTxt.setText(R.string.str_success_c);
                holder.itemHeight.setText(mResBnbTxInfo.height);
                holder.itemMsgCnt.setText(String.valueOf(mResBnbTxInfo.tx.value.msg.size()));
                holder.itemGas.setText("-");
                holder.itemFee.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(FEE_BNB_SEND), 0, 8));
                holder.itemFeeLayer.setVisibility(View.VISIBLE);
                holder.itemblockLayer.setVisibility(View.GONE);
                holder.itemHash.setText(mResBnbTxInfo.hash);
                holder.itemMemo.setText(mResBnbTxInfo.tx.value.memo);

            } else {
                if (mResTxInfo.isSuccess()) {
                    holder.itemStatusImg.setImageResource(R.drawable.success_ic);
                    holder.itemStatusTxt.setText(R.string.str_success_c);
                } else {
                    holder.itemStatusImg.setImageResource(R.drawable.fail_ic);
                    holder.itemStatusTxt.setText(R.string.str_failed_c);
                    holder.itemFailTxt.setText(mResTxInfo.failMessage());
                    holder.itemFailTxt.setVisibility(View.VISIBLE);
                }
                holder.itemHeight.setText(mResTxInfo.height);
                holder.itemMsgCnt.setText(String.valueOf(mResTxInfo.getMsgs().size()));
                holder.itemGas.setText(String.format("%s / %s", mResTxInfo.gas_used, mResTxInfo.gas_wanted));
                holder.itemFee.setText(WDp.getDpAmount2(getBaseContext(), mResTxInfo.simpleFee(), 0, 18));
                holder.itemFeeLayer.setVisibility(View.VISIBLE);
                holder.itemTime.setText(WDp.getTimeTxformat(getBaseContext(), mResTxInfo.timestamp));
                holder.itemTimeGap.setText(WDp.getTimeTxGap(getBaseContext(), mResTxInfo.timestamp));
                holder.itemHash.setText("0x" + mResTxInfo.txhash);
                holder.itemMemo.setText(mResTxInfo.tx.value.memo);
            }
        }

        private void onBindTransfer(RecyclerView.ViewHolder viewHolder, int position) {
            final TxTransferHolder holder = (TxTransferHolder) viewHolder;
            holder.itemSendReceiveImg.setColorFilter(ContextCompat.getColor(TxDetailActivity.this, mChainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);

            if (mBaseChain.equals(BNB_MAIN)) {
                final Msg msg = mResBnbTxInfo.getMsg(position - 1);
                holder.itemFromAddress.setText(msg.value.inputs.get(0).address);
                holder.itemToAddress.setText(msg.value.outputs.get(0).address);
                if (mAccount.address.equals(msg.value.inputs.get(0).address)) {
                    holder.itemSendRecieveTv.setText(R.string.tx_send);
                }
                if (mAccount.address.equals(msg.value.outputs.get(0).address)) {
                    holder.itemSendRecieveTv.setText(R.string.tx_receive);
                }
                ArrayList<Coin> toDpCoin = msg.value.inputs.get(0).coins;
                WDp.setDpSymbol(getBaseContext(), getBaseDao(), mChainConfig, toDpCoin.get(0).denom, holder.itemAmountDenom);
                holder.itemAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(toDpCoin.get(0).amount), 8, 8));

            } else {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                ArrayList<Coin> toDpCoin = new ArrayList<>();
                try {
                    holder.itemFromAddress.setText(WKey.convertAddressToEth(msg.value.from_address));
                    holder.itemToAddress.setText(WKey.convertAddressToEth(msg.value.to_address));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (mAccount.address.equals(msg.value.from_address)) {
                    holder.itemSendRecieveTv.setText(R.string.tx_send);
                }
                if (mAccount.address.equals(msg.value.to_address)) {
                    holder.itemSendRecieveTv.setText(R.string.tx_receive);
                }

                toDpCoin = WDp.getCoins(msg.value.amount);
                WUtil.onSortingCoins(toDpCoin, mBaseChain);
                WDp.setDpCoin(getBaseContext(), getBaseDao(), mChainConfig, toDpCoin.get(0), holder.itemAmountDenom, holder.itemAmount);
            }
        }

        //OKex msg type
        private void onBindOkStake(RecyclerView.ViewHolder viewHolder, int position) {
            final TxOkStakeHolder holder = (TxOkStakeHolder) viewHolder;
            holder.itemMsgImg.setColorFilter(ContextCompat.getColor(getBaseContext(), mChainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);
            final Msg msg = mResTxInfo.getMsg(position - 1);

            if (msg.type.equals(OK_MSG_TYPE_DEPOSIT)) {
                holder.itemMsgTitle.setText(R.string.str_staking);
                holder.itemMsgImg.setImageResource(R.drawable.deposit_ic);
            } else if (msg.type.equals(OK_MSG_TYPE_WITHDRAW)) {
                holder.itemMsgTitle.setText(R.string.str_to_unbonding);
                holder.itemMsgImg.setImageResource(R.drawable.withdraw_ic);
            }
            try {
                holder.itemDeleagtor.setText(WKey.convertAddressToEth(msg.value.delegator_address));
            } catch (Exception e) {
                e.printStackTrace();
            }
            WDp.setDpCoin(getBaseContext(), getBaseDao(), mChainConfig, msg.value.quantity, holder.itemAmountDenom, holder.itemAmount);
        }

        private void onBindOkDirectVote(RecyclerView.ViewHolder viewHolder, int position) {
            final TxOkVoteHolder holder = (TxOkVoteHolder) viewHolder;
            holder.itemMsgImg.setColorFilter(ContextCompat.getColor(getBaseContext(), mChainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);

            final Msg msg = mResTxInfo.getMsg(position - 1);
            try {
                holder.itemVoter.setText(WKey.convertAddressToEth(msg.value.delegator_address));
            } catch (Exception e) {
                e.printStackTrace();
            }

            ArrayList<String> toValAdd = msg.value.validator_addresses;
            String monikers = "";
            for (String valOp : toValAdd) {
                for (Validator validator : getBaseDao().mAllValidators) {
                    if (validator.operator_address.equals(valOp)) {
                        monikers = monikers + validator.description.moniker + "\n";
                    }
                }
            }
            holder.itemValList.setText(monikers);
        }

        private void onBindUnKnown(RecyclerView.ViewHolder viewHolder) {
            final TxUnKnownHolder holder = (TxUnKnownHolder) viewHolder;
            holder.itemUnknownImg.setColorFilter(ContextCompat.getColor(TxDetailActivity.this, mChainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);
        }


        public class TxCommonHolder extends RecyclerView.ViewHolder {
            ImageView itemStatusImg;
            TextView itemStatusTxt, itemFailTxt, itemHash, itemHeight, itemMsgCnt, itemGas, itemTime, itemTimeGap,
                    itemMemo, itemFee, itemFeeDenom, itemFeeUsed, itemFeeUsedDenom;
            RelativeLayout itemFeeLayer, itemFeeUsedLayer, itemblockLayer;

            public TxCommonHolder(@NonNull View itemView) {
                super(itemView);
                itemStatusImg = itemView.findViewById(R.id.tx_status_img);
                itemStatusTxt = itemView.findViewById(R.id.tx_status);
                itemFailTxt = itemView.findViewById(R.id.tx_fail_msg);
                itemHeight = itemView.findViewById(R.id.tx_block_height);
                itemMsgCnt = itemView.findViewById(R.id.tx_msg_cnt);
                itemGas = itemView.findViewById(R.id.tx_gas_info);
                itemblockLayer = itemView.findViewById(R.id.tx_block_time_layer);
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
            }
        }

        public class TxTransferHolder extends RecyclerView.ViewHolder {
            ImageView itemSendReceiveImg;
            TextView itemSendRecieveTv;
            TextView itemFromAddress, itemToAddress;
            TextView itemAmount, itemAmountDenom;

            public TxTransferHolder(@NonNull View itemView) {
                super(itemView);
                itemSendReceiveImg = itemView.findViewById(R.id.tx_send_icon);
                itemSendRecieveTv = itemView.findViewById(R.id.tx_send_text);
                itemFromAddress = itemView.findViewById(R.id.tx_send_from_address);
                itemToAddress = itemView.findViewById(R.id.tx_send_to_address);

                itemAmount = itemView.findViewById(R.id.tx_transfer_amount);
                itemAmountDenom = itemView.findViewById(R.id.tx_transfer_amount_symbol);
            }
        }

        public class TxOkStakeHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            TextView itemDeleagtor, itemAmountDenom, itemAmount;

            public TxOkStakeHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemDeleagtor = itemView.findViewById(R.id.tx_delegator);
                itemAmount = itemView.findViewById(R.id.tx_amount);
                itemAmountDenom = itemView.findViewById(R.id.tx_amount_symbol);
            }
        }

        public class TxOkVoteHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            TextView itemVoter, itemValList;

            public TxOkVoteHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemVoter = itemView.findViewById(R.id.tx_vote_voter);
                itemValList = itemView.findViewById(R.id.tx_vote_val_list);
            }

        }

        public class TxUnKnownHolder extends RecyclerView.ViewHolder {
            ImageView itemUnknownImg;
            TextView itemUnknownTitle;

            public TxUnKnownHolder(@NonNull View itemView) {
                super(itemView);
                itemUnknownImg = itemView.findViewById(R.id.tx_unknown_icon);
                itemUnknownTitle = itemView.findViewById(R.id.tx_unknown_text);
            }
        }
    }


    private void onShowMoreWait() {
        CommonAlertDialog.showDoubleButton(this, getString(R.string.str_more_wait_title), getString(R.string.str_more_wait_msg),
                getString(R.string.str_close), view -> onBackPressed(), getString(R.string.str_wait), view -> onWaitMore(), false);
    }

    public void onWaitMore() {
        FetchCnt = 0;
        onFetchTx(mTxHash);
    }

    private int FetchCnt = 0;

    private void onFetchTx(String hash) {
        if (mBaseChain.equals(BNB_MAIN)) {
            ApiClient.getBnbChain().getSearchTx(hash, "json").enqueue(new Callback<ResBnbTxInfo>() {
                @Override
                public void onResponse(Call<ResBnbTxInfo> call, Response<ResBnbTxInfo> response) {
                    if (isFinishing()) return;
                    if (response.isSuccessful() && response.body() != null) {
                        mResBnbTxInfo = response.body();
                        onUpdateView();
                    } else {
                        if (mIsSuccess && FetchCnt < 10) {
                            new Handler().postDelayed(() -> {
                                FetchCnt++;
                                onFetchTx(mTxHash);
                            }, 6000);
                        } else if (!mIsGen) {
                            onBackPressed();
                        } else {
                            onShowMoreWait();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResBnbTxInfo> call, Throwable t) {
                    if (BuildConfig.DEBUG) t.printStackTrace();
                    if (isFinishing()) return;
                }
            });

        } else if (mBaseChain.equals(OKEX_MAIN)) {
            ApiClient.getOkexChain().getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
                @Override
                public void onResponse(Call<ResTxInfo> call, Response<ResTxInfo> response) {
                    if (isFinishing()) return;
                    if (response.isSuccessful() && response.body() != null) {
                        mResTxInfo = response.body();
                        onUpdateView();
                    } else {
                        if (mIsSuccess && FetchCnt < 10) {
                            new Handler().postDelayed(() -> {
                                FetchCnt++;
                                onFetchTx(mTxHash);
                            }, 6000);
                        } else if (!mIsGen) {
                            onBackPressed();
                        } else {
                            onShowMoreWait();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResTxInfo> call, Throwable t) {
                    if (BuildConfig.DEBUG) t.printStackTrace();
                    if (isFinishing()) return;
                }
            });

        }
    }
}
