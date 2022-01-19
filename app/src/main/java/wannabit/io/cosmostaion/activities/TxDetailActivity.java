package wannabit.io.cosmostaion.activities;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
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
import wannabit.io.cosmostaion.dialog.Dialog_MoreWait;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBnbSwapInfo;
import wannabit.io.cosmostaion.network.res.ResBnbTxInfo;
import wannabit.io.cosmostaion.network.res.ResKavaSwapInfo;
import wannabit.io.cosmostaion.network.res.ResNodeInfo;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.BNB_MSG_TYPE_HTLC;
import static wannabit.io.cosmostaion.base.BaseConstant.BNB_MSG_TYPE_HTLC_CLIAM;
import static wannabit.io.cosmostaion.base.BaseConstant.BNB_MSG_TYPE_HTLC_REFUND;
import static wannabit.io.cosmostaion.base.BaseConstant.CERTIK_MSG_TYPE_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_TRANSFER2;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_TRANSFER3;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_UNJAIL;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_VAL;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_BROADCAST;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_UNKNOWN;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_COMMISSION;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_WITHDRAW_ALL;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_BEP3_CLAM_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_BEP3_CREATE_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_BEP3_REFUND_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_POST_PRICE;
import static wannabit.io.cosmostaion.base.BaseConstant.OK_MSG_TYPE_DEPOSIT;
import static wannabit.io.cosmostaion.base.BaseConstant.OK_MSG_TYPE_DIRECT_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.OK_MSG_TYPE_MULTI_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.OK_MSG_TYPE_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.OK_MSG_TYPE_WITHDRAW;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.network.res.ResBnbSwapInfo.BNB_STATUS_OPEN;
import static wannabit.io.cosmostaion.network.res.ResKavaSwapInfo.STATUS_EXPIRED;

public class TxDetailActivity extends BaseActivity implements View.OnClickListener, Dialog_MoreWait.OnTxWaitListener {

    private Toolbar mToolbar;
    private RecyclerView mTxRecyclerView;
    private CardView mErrorCardView;
    private TextView mErrorMsgTv;
    private RelativeLayout mLoadingLayer;
    private TextView mLoadingMsgTv;
    private LinearLayout mControlLayer;
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
    private ResKavaSwapInfo mResKavaSwapInfo;
    private ResBnbTxInfo mResBnbTxInfo;
    private ResBnbSwapInfo mResBnbSwapInfo;
    private ResNodeInfo mResBnbNodeInfo;
    private String mBnbTime;
    private String mSwapId = "";

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
        mRefundBtn = findViewById(R.id.btn_refund);
        mControlLayer2 = findViewById(R.id.control_after);
        mShareBtn = findViewById(R.id.btn_share);
        mExplorerBtn = findViewById(R.id.btn_explorer);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = getChain(mAccount.baseChain);
        mIsGen = getIntent().getBooleanExtra("isGen", false);
        mIsSuccess = getIntent().getBooleanExtra("isSuccess", false);
        mErrorCode = getIntent().getIntExtra("errorCode", ERROR_CODE_UNKNOWN);
        mErrorMsg = getIntent().getStringExtra("errorMsg");
        mTxHash = getIntent().getStringExtra("txHash");
        mBnbTime = getIntent().getStringExtra("bnbTime");

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
        mRefundBtn.setOnClickListener(this);
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

        if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
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
            if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
                hash = mResBnbTxInfo.hash;
            } else {
                hash = mResTxInfo.txhash;
            }
            if (TextUtils.isEmpty(hash)) {
                return;
            }

            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, WUtil.getTxExplorer(mBaseChain, hash));
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "send"));

        } else if(v.equals(mExplorerBtn)) {
            if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
                String url  = WUtil.getTxExplorer(mBaseChain, mResBnbTxInfo.hash);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            } else if (mResTxInfo != null && !TextUtils.isEmpty(mResTxInfo.txhash)){
                String url  = WUtil.getTxExplorer(mBaseChain, mResTxInfo.txhash);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            } else {
                return;
            }

        } else if (v.equals(mRefundBtn)) {
            if (!mAccount.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                add.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                return;
            }

            if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
                if ((getBaseDao().availableAmount(TOKEN_BNB)).compareTo(new BigDecimal(FEE_BNB_SEND)) < 0) {
                    Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            Intent refundIntent = new Intent(TxDetailActivity.this, HtlcRefundActivity.class);
            refundIntent.putExtra("swapId", mSwapId);
            startActivity(refundIntent);

        }

    }


    private class TxDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_TX_COMMON = 0;
        private static final int TYPE_TX_TRANSFER = 1;
        private static final int TYPE_TX_COMMISSION = 8;
        private static final int TYPE_TX_MULTI_SEND = 9;
        private static final int TYPE_TX_UNJAIL = 10;

        // kava msg types
        private static final int TYPE_TX_POST_PRICE = 200;
        private static final int TYPE_TX_HTLC_CREATE = 208;
        private static final int TYPE_TX_HTLC_CLAIM = 209;
        private static final int TYPE_TX_HTLC_REFUND = 210;

        private static final int TYPE_TX_REWARD_ALL = 21;
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
            } else if (viewType == TYPE_TX_COMMISSION) {
                return new TxCommissionHolder(getLayoutInflater().inflate(R.layout.item_tx_commission, viewGroup, false));
            } else if (viewType == TYPE_TX_MULTI_SEND) {
                return new TxMultiSendHolder(getLayoutInflater().inflate(R.layout.item_tx_multisend, viewGroup, false));
            } else if (viewType == TYPE_TX_REWARD_ALL) {
                return new TxRewardAllHolder(getLayoutInflater().inflate(R.layout.item_tx_reward_all, viewGroup, false));
            } else if (viewType == TYPE_TX_UNJAIL) {
                return new TxUnjailHolder(getLayoutInflater().inflate(R.layout.item_tx_unjail, viewGroup, false));
            } else if (viewType == TYPE_TX_POST_PRICE) {
                return new TxPostPriceHolder(getLayoutInflater().inflate(R.layout.item_tx_post_price, viewGroup, false));
            } else if (viewType == TYPE_TX_HTLC_CREATE) {
                return new TxCreateHtlcHolder(getLayoutInflater().inflate(R.layout.item_tx_htlc_create, viewGroup, false));
            } else if (viewType == TYPE_TX_HTLC_CLAIM) {
                return new TxClaimHtlcHolder(getLayoutInflater().inflate(R.layout.item_tx_htlc_claim, viewGroup, false));
            } else if (viewType == TYPE_TX_HTLC_REFUND) {
                return new TxRefundHtlcHolder(getLayoutInflater().inflate(R.layout.item_tx_htlc_refund, viewGroup, false));
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
            } else if (getItemViewType(position) == TYPE_TX_COMMISSION) {
                onBindCommission(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_MULTI_SEND) {
                onBindMultiSend(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_REWARD_ALL) {
                onBindRewardAll(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_UNJAIL) {
                onBindUnjail(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_POST_PRICE) {
                onBindPostPrice(viewHolder, position);

            } else if (getItemViewType(position) == TYPE_TX_HTLC_CREATE) {
                onBindCreateHTLC(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_HTLC_CLAIM) {
                onBindClaimHTLC(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_HTLC_REFUND) {
                onBindRefundHTLC(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_OK_STAKE) {
                onBindOkStake(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_OK_DIRECT_VOTE) {
                onBindOkDirectVote(viewHolder, position);
            } else {
                onBindUnKnown(viewHolder, position);
            }
        }

        @Override
        public int getItemCount() {
            if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
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
                if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
                    if (mResBnbTxInfo.getMsgType(position - 1).equals(BNB_MSG_TYPE_HTLC)) {
                        return TYPE_TX_HTLC_CREATE;
                    } else if (mResBnbTxInfo.getMsgType(position - 1).equals(BNB_MSG_TYPE_HTLC_CLIAM)) {
                        return TYPE_TX_HTLC_CLAIM;
                    } else if (mResBnbTxInfo.getMsgType(position - 1).equals(BNB_MSG_TYPE_HTLC_REFUND)) {
                        return TYPE_TX_HTLC_REFUND;
                    } else if (mResBnbTxInfo.getMsgType(position - 1).equals(COSMOS_MSG_TYPE_TRANSFER)) {
                        return TYPE_TX_TRANSFER;
                    }
                    return TYPE_TX_UNKNOWN;

                } else {
                    if (mResTxInfo.getMsgType(position - 1).equals(COSMOS_MSG_TYPE_TRANSFER2) ||
                            mResTxInfo.getMsgType(position - 1).equals(OK_MSG_TYPE_TRANSFER) ||
                            mResTxInfo.getMsgType(position - 1).equals(OK_MSG_TYPE_MULTI_TRANSFER) ||
                            mResTxInfo.getMsgType(position - 1).equals(CERTIK_MSG_TYPE_TRANSFER)) {
                        return TYPE_TX_TRANSFER;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(COSMOS_MSG_TYPE_TRANSFER3) ||
                            mResTxInfo.getMsgType(position - 1).equals(IRIS_MSG_TYPE_TRANSFER)) {
                        if (mResTxInfo.getMsg(position - 1).value.inputs.size() == 1 &&
                                mResTxInfo.getMsg(position - 1).value.outputs.size() == 1) {
                            return TYPE_TX_TRANSFER;
                        }
                        return TYPE_TX_MULTI_SEND;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(COSMOS_MSG_TYPE_WITHDRAW_VAL) ||
                            mResTxInfo.getMsgType(position - 1).equals(IRIS_MSG_TYPE_COMMISSION)) {
                        return TYPE_TX_COMMISSION;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(IRIS_MSG_TYPE_WITHDRAW_ALL)) {
                        return TYPE_TX_REWARD_ALL;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(COSMOS_MSG_TYPE_UNJAIL)) {
                        return TYPE_TX_UNJAIL;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_POST_PRICE)) {
                        return TYPE_TX_POST_PRICE;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_BEP3_CREATE_SWAP)) {
                        return TYPE_TX_HTLC_CREATE;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_BEP3_CLAM_SWAP)) {
                        return TYPE_TX_HTLC_CLAIM;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_BEP3_REFUND_SWAP)) {
                        return TYPE_TX_HTLC_REFUND;

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
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemFeeDenom);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemFeeUsedDenom);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemFeeLimitDenom);
            if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
                holder.itemStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.success_ic));
                holder.itemStatusTxt.setText(R.string.str_success_c);
                holder.itemHeight.setText(mResBnbTxInfo.height);
                holder.itemMsgCnt.setText(String.valueOf(mResBnbTxInfo.tx.value.msg.size()));
                holder.itemGas.setText("-");
                holder.itemFee.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(FEE_BNB_SEND), 0, 8));
                holder.itemFeeLayer.setVisibility(View.VISIBLE);
                holder.itemTime.setText(WDp.getTimeformat(getBaseContext(), mBnbTime));
                holder.itemTimeGap.setText(WDp.getTimeGap(getBaseContext(), mBnbTime));
                holder.itemHash.setText(mResBnbTxInfo.hash);
                holder.itemMemo.setText(mResBnbTxInfo.tx.value.memo);

            } else if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
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
                holder.itemMsgCnt.setText(String.valueOf(mResTxInfo.getMsgs().size()));
                holder.itemGas.setText(String.format("%s / %s", mResTxInfo.gas_used, mResTxInfo.gas_wanted));
                holder.itemFee.setText(WDp.getDpAmount2(getBaseContext(), mResTxInfo.simpleFee(), 0, 18));
                holder.itemFeeLayer.setVisibility(View.VISIBLE);
                holder.itemTime.setText(WDp.getTimeTxformat(getBaseContext(), mResTxInfo.timestamp));
                holder.itemTimeGap.setText(WDp.getTimeTxGap(getBaseContext(), mResTxInfo.timestamp));
                holder.itemHash.setText("0x"+mResTxInfo.txhash);
                holder.itemMemo.setText(mResTxInfo.tx.value.memo);

            } else {
                final int dpDecimal = WDp.mainDivideDecimal(mBaseChain);
                if (mResTxInfo.isSuccess()) {
                    holder.itemStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.success_ic));
                    holder.itemStatusTxt.setText(R.string.str_success_c);
                } else {
                    holder.itemStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.fail_ic));
                    holder.itemStatusTxt.setText(R.string.str_failed_c);
                    if (mResTxInfo.failMessage().replace("\u00A0", "").startsWith("atomicswapnotfound")) {
                        holder.itemFailTxt.setText("atomic swap not found");
                    } else {
                        holder.itemFailTxt.setText(mResTxInfo.failMessage());
                    }
                    holder.itemFailTxt.setVisibility(View.VISIBLE);
                }
                holder.itemHeight.setText(mResTxInfo.height);
                holder.itemMsgCnt.setText(String.valueOf(mResTxInfo.getMsgs().size()));
                holder.itemGas.setText(String.format("%s / %s", mResTxInfo.gas_used, mResTxInfo.gas_wanted));
                holder.itemFee.setText(WDp.getDpAmount2(getBaseContext(), mResTxInfo.simpleFee(), dpDecimal, dpDecimal));
                holder.itemFeeLayer.setVisibility(View.VISIBLE);
                holder.itemTime.setText(WDp.getTimeTxformat(getBaseContext(), mResTxInfo.timestamp));
                holder.itemTimeGap.setText(WDp.getTimeTxGap(getBaseContext(), mResTxInfo.timestamp));
                holder.itemHash.setText(mResTxInfo.txhash);
                holder.itemMemo.setText(mResTxInfo.tx.value.memo);

            }

        }

        private void onBindTransfer(RecyclerView.ViewHolder viewHolder, int position) {
            final TxTransferHolder holder = (TxTransferHolder) viewHolder;
            holder.itemSendReceiveImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
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
                holder.itemSingleCoinLayer.setVisibility(View.VISIBLE);
                WDp.showCoinDp(getBaseContext(), getBaseDao(), toDpCoin.get(0), holder.itemAmountDenom, holder.itemAmount, mBaseChain);

            } else {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                ArrayList<Coin> toDpCoin = new ArrayList<>();
                if (msg.type.equals(COSMOS_MSG_TYPE_TRANSFER2) || msg.type.equals(CERTIK_MSG_TYPE_TRANSFER)) {
                    holder.itemFromAddress.setText(msg.value.from_address);
                    holder.itemToAddress.setText(msg.value.to_address);
                    if (mAccount.address.equals(msg.value.from_address)) {
                        holder.itemSendRecieveTv.setText(R.string.tx_send);
                    }
                    if (mAccount.address.equals(msg.value.to_address)) {
                        holder.itemSendRecieveTv.setText(R.string.tx_receive);
                    }
                    toDpCoin = WDp.getCoins(msg.value.amount);
                    WUtil.onSortingCoins(toDpCoin, mBaseChain);

                } else if (msg.type.equals(OK_MSG_TYPE_TRANSFER)) {
                    try {
                        holder.itemFromAddress.setText(WKey.convertAddressOkexToEth(msg.value.from_address));
                        holder.itemToAddress.setText(WKey.convertAddressOkexToEth(msg.value.to_address));
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

                } else if (msg.type.equals(COSMOS_MSG_TYPE_TRANSFER3) && (msg.value.inputs != null && msg.value.outputs != null)) {
                    holder.itemFromAddress.setText(msg.value.inputs.get(0).address);
                    holder.itemToAddress.setText(msg.value.outputs.get(0).address);
                    if (mAccount.address.equals(msg.value.inputs.get(0).address)) {
                        holder.itemSendRecieveTv.setText(R.string.tx_send);
                    }
                    if (mAccount.address.equals(msg.value.outputs.get(0).address)) {
                        holder.itemSendRecieveTv.setText(R.string.tx_receive);
                    }
                    toDpCoin = msg.value.inputs.get(0).coins;

                } else if (msg.type.equals(OK_MSG_TYPE_MULTI_TRANSFER)) {
                    try {
                        holder.itemFromAddress.setText(WKey.convertAddressOkexToEth(msg.value.from));
                        holder.itemToAddress.setText(WKey.convertAddressOkexToEth(msg.value.transfers.get(0).to));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (mAccount.address.equals(msg.value.from)) {
                        holder.itemSendRecieveTv.setText(R.string.tx_send);
                    }
                    if (mAccount.address.equals(msg.value.transfers.get(0).to)) {
                        holder.itemSendRecieveTv.setText(R.string.tx_receive);
                    }
                    toDpCoin = msg.value.transfers.get(0).coins;
                    WUtil.onSortingCoins(toDpCoin, mBaseChain);

                }

                //support multi type(denom) coins transfer
                if (toDpCoin.size() == 1) {
                    holder.itemSingleCoinLayer.setVisibility(View.VISIBLE);
                    WDp.showCoinDp(getBaseContext(), getBaseDao(), toDpCoin.get(0), holder.itemAmountDenom, holder.itemAmount, mBaseChain);
                } else {
                    holder.itemMultiCoinLayer.setVisibility(View.VISIBLE);
                    if (toDpCoin.size() > 0) {
                        WDp.showCoinDp(getBaseContext(), getBaseDao(), toDpCoin.get(0), holder.itemAmountDenom0, holder.itemAmount0, mBaseChain);
                    }
                    if (toDpCoin.size() > 1) {
                        holder.itemAmountLayer1.setVisibility(View.VISIBLE);
                        WDp.showCoinDp(getBaseContext(), getBaseDao(), toDpCoin.get(1), holder.itemAmountDenom1, holder.itemAmount1, mBaseChain);
                    }
                    if (toDpCoin.size() > 2) {
                        holder.itemAmountLayer2.setVisibility(View.VISIBLE);
                        WDp.showCoinDp(getBaseContext(), getBaseDao(), toDpCoin.get(2), holder.itemAmountDenom2, holder.itemAmount2, mBaseChain);
                    }
                    if (toDpCoin.size() > 3) {
                        holder.itemAmountLayer3.setVisibility(View.VISIBLE);
                        WDp.showCoinDp(getBaseContext(), getBaseDao(), toDpCoin.get(3), holder.itemAmountDenom3, holder.itemAmount3, mBaseChain);
                    }
                    if (toDpCoin.size() > 4) {
                        holder.itemAmountLayer4.setVisibility(View.VISIBLE);
                        WDp.showCoinDp(getBaseContext(), getBaseDao(), toDpCoin.get(4), holder.itemAmountDenom4, holder.itemAmount4, mBaseChain);
                    }
                }
            }
        }

        //TODO now not perfect support with multi transfer with multi coins!!
        private void onBindMultiSend(RecyclerView.ViewHolder viewHolder, int position) {
//            final TxMultiSendHolder holder = (TxMultiSendHolder)viewHolder;
//            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemInputDenom0);
//            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemInputDenom1);
//            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemInputDenom2);
//            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemInputDenom3);
//            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemOutputDenom0);
//            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemOutputDenom1);
//            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemOutputDenom2);
//            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemOutputDenom3);
//            holder.itemSendReceiveImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
//
//            final Msg msg = mResTxInfo.getMsg(position - 1);
//            holder.itemInputAddress0.setText(msg.value.inputs.get(0).address);
//            holder.itemInputAmount0.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.inputs.get(0).coins.get(0).amount), 6, mBaseChain));
//            holder.itemOutputAddress0.setText(msg.value.outputs.get(0).address);
//            holder.itemOutputAmount0.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.outputs.get(0).coins.get(0).amount), 6, mBaseChain));
//            if (msg.value.inputs.size() > 1) {
//                holder.itemInputLayer1.setVisibility(View.VISIBLE);
//                holder.itemInputAddress1.setText(msg.value.inputs.get(1).address);
//                holder.itemInputAmount1.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.inputs.get(1).coins.get(0).amount), 6, mBaseChain));
//            }
//            if (msg.value.inputs.size() > 2) {
//                holder.itemInputLayer2.setVisibility(View.VISIBLE);
//                holder.itemInputAddress2.setText(msg.value.inputs.get(2).address);
//                holder.itemInputAmount2.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.inputs.get(2).coins.get(0).amount), 6, mBaseChain));
//            }
//            if (msg.value.inputs.size() > 3) {
//                holder.itemInputLayer3.setVisibility(View.VISIBLE);
//                holder.itemInputAddress3.setText(msg.value.inputs.get(3).address);
//                holder.itemInputAmount3.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.inputs.get(3).coins.get(0).amount), 6, mBaseChain));
//            }
//            if (msg.value.outputs.size() > 1) {
//                holder.itemOutputLayer1.setVisibility(View.VISIBLE);
//                holder.itemOutputAddress1.setText(msg.value.outputs.get(1).address);
//                holder.itemOutputAmount1.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.outputs.get(1).coins.get(0).amount), 6, mBaseChain));
//            }
//            if (msg.value.outputs.size() > 2) {
//                holder.itemOutputLayer2.setVisibility(View.VISIBLE);
//                holder.itemOutputAddress2.setText(msg.value.outputs.get(2).address);
//                holder.itemOutputAmount2.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.outputs.get(2).coins.get(0).amount), 6, mBaseChain));
//            }
//            if (msg.value.outputs.size() > 3) {
//                holder.itemOutputLayer3.setVisibility(View.VISIBLE);
//                holder.itemOutputAddress3.setText(msg.value.outputs.get(3).address);
//                holder.itemOutputAmount3.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.outputs.get(3).coins.get(0).amount), 6, mBaseChain));
//            }
//            for (Input input:msg.value.inputs) {
//                if (mAccount.address.equals(input.address)) {
//                    holder.itemSendRecieveTv.setText(R.string.tx_send);
//                }
//            }
//            for (Output output:msg.value.outputs) {
//                if (mAccount.address.equals(output.address)) {
//                    holder.itemSendRecieveTv.setText(R.string.tx_receive);
//                }
//            }
        }

        private void onBindRewardAll(RecyclerView.ViewHolder viewHolder, int position) {
            final TxRewardAllHolder holder = (TxRewardAllHolder) viewHolder;
        }

        private void onBindUnjail(RecyclerView.ViewHolder viewHolder, int position) {
            final TxUnjailHolder holder = (TxUnjailHolder) viewHolder;
            holder.itemUnjailImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            final Msg msg = mResTxInfo.getMsg(position - 1);
            holder.itemMoniker.setText(WUtil.getMonikerName(msg.value.address, getBaseDao().mAllValidators, true));
            holder.itemValidator.setText(msg.value.address);
        }

        private void onBindCommission(RecyclerView.ViewHolder viewHolder, int position) {
            final TxCommissionHolder holder = (TxCommissionHolder) viewHolder;
            final int dpDecimal = WDp.mainDivideDecimal(mBaseChain);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemCommissionAmountDenom);
            holder.itemCommissionImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            final Msg msg = mResTxInfo.getMsg(position - 1);
            holder.itemCommissionValidator.setText(msg.value.validator_address);
            holder.itemCommissionValidatorMoniker.setText(WUtil.getMonikerName(msg.value.validator_address, getBaseDao().mAllValidators, true));
            holder.itemCommissionAmount.setText(WDp.getDpAmount2(getBaseContext(), mResTxInfo.simpleCommission(position - 1), dpDecimal, dpDecimal));
        }

        //Bind KAVA msgs
        private void onBindPostPrice(RecyclerView.ViewHolder viewHolder, int position) {
            final TxPostPriceHolder holder = (TxPostPriceHolder) viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemPoster.setText(msg.value.from);
                holder.itemMakerId.setText(msg.value.market_id);
                holder.itemPostPrice.setText(msg.value.price);
                holder.itemTime.setText(WDp.getTimeTxformat(getBaseContext(), msg.value.expiry) + " (" + msg.value.expiry + ")");
            }

        }

        private void onBindCreateHTLC(RecyclerView.ViewHolder viewHolder, int position) {
            final TxCreateHtlcHolder holder = (TxCreateHtlcHolder) viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                Coin sendCoin = WDp.getCoins(msg.value.amount).get(0);
                WDp.showCoinDp(getBaseContext(), getBaseDao(), sendCoin, holder.itemSendDenom, holder.itemSendAmount, mBaseChain);
                holder.itemSender.setText(msg.value.from);
                holder.itemRecipient.setText(msg.value.recipient_other_chain);
                holder.itemRandomHash.setText(msg.value.random_number_hash);
                holder.itemExpectIncome.setText(msg.value.expected_income);
                holder.itemStatus.setText(WDp.getKavaHtlcStatus(getBaseContext(), mResTxInfo, mResKavaSwapInfo));
                if (mResKavaSwapInfo != null && mResKavaSwapInfo.result.status.equals(STATUS_EXPIRED)) {
                    mRefundBtn.setVisibility(View.VISIBLE);
                    mSwapId = mResTxInfo.simpleSwapId();
                }
            } else if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
                final Msg msg = mResBnbTxInfo.getMsg(position - 1);
                if (mAccount.address.equals(msg.value.from)) {
                    holder.itemMsgTitle.setText(getString(R.string.tx_send_htlc2));
                    holder.itemSender.setText(msg.value.from);
                    holder.itemRecipient.setText(msg.value.recipient_other_chain);

                } else if (mAccount.address.equals(msg.value.to)) {
                    holder.itemMsgTitle.setText(getString(R.string.tx_receive_htlc2));
                    holder.itemSender.setText(msg.value.sender_other_chain);
                    holder.itemRecipient.setText(msg.value.to);
                } else {
                    holder.itemMsgTitle.setText(getString(R.string.tx_create_htlc2));
                    holder.itemSender.setText(msg.value.from);
                    holder.itemRecipient.setText(msg.value.to);
                }
                Coin sendCoin = WDp.getCoins(msg.value.amount).get(0);
                WDp.showCoinDp(getBaseContext(), getBaseDao(), sendCoin, holder.itemSendDenom, holder.itemSendAmount, mBaseChain);
                holder.itemRandomHash.setText(msg.value.random_number_hash);
                holder.itemExpectIncome.setText(msg.value.expected_income);
                holder.itemStatus.setText(WDp.getBnbHtlcStatus(getBaseContext(), mResBnbSwapInfo, mResBnbNodeInfo));

                if (mResBnbSwapInfo != null &&
                        mResBnbNodeInfo != null &&
                        mResBnbSwapInfo.status == BNB_STATUS_OPEN &&
                        mResBnbSwapInfo.expireHeight < mResBnbNodeInfo.getCHeight()) {
                    mRefundBtn.setVisibility(View.VISIBLE);
                    mSwapId = mResBnbTxInfo.simpleSwapId();
                }
            }
        }

        private void onBindClaimHTLC(RecyclerView.ViewHolder viewHolder, int position) {
            final TxClaimHtlcHolder holder = (TxClaimHtlcHolder) viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                Coin receiveCoin = mResTxInfo.simpleSwapCoin();
                try {
                    if (!TextUtils.isEmpty(receiveCoin.denom)) {
                        WDp.showCoinDp(getBaseContext(), getBaseDao(), receiveCoin, holder.itemReceiveDenom, holder.itemReceiveAmount, mBaseChain);
                    }
                } catch (Exception e) {
                }

                holder.itemClaimer.setText(msg.value.from);
                holder.itemRandomNumber.setText(msg.value.random_number);
                holder.itemSwapId.setText(msg.value.swap_id);

            } else if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
                final Msg msg = mResBnbTxInfo.getMsg(position - 1);
                holder.itemAmountLayer.setVisibility(View.GONE);
                holder.itemClaimer.setText(msg.value.from);
                holder.itemRandomNumber.setText(msg.value.random_number);
                holder.itemSwapId.setText(msg.value.swap_id);

            }
        }

        private void onBindRefundHTLC(RecyclerView.ViewHolder viewHolder, int position) {
            final TxRefundHtlcHolder holder = (TxRefundHtlcHolder) viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                Coin refundCoin = mResTxInfo.simpleRefund();
                try {
                    if (!TextUtils.isEmpty(refundCoin.denom)) {
                        WDp.showCoinDp(getBaseContext(), getBaseDao(), refundCoin, holder.itemRefundDenom, holder.itemRefundAmount, mBaseChain);
                    }
                } catch (Exception e) {
                }
                holder.itemFromAddr.setText(msg.value.from);
                holder.itemSwapId.setText(msg.value.swap_id);

            } else if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
                final Msg msg = mResBnbTxInfo.getMsg(position - 1);
                holder.itemAmountLayer.setVisibility(View.GONE);
                holder.itemFromAddr.setText(msg.value.from);
                holder.itemSwapId.setText(msg.value.swap_id);
            }
        }


        //OKex msg type
        private void onBindOkStake(RecyclerView.ViewHolder viewHolder, int position) {
            final TxOkStakeHolder holder = (TxOkStakeHolder) viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                if (msg.type.equals(OK_MSG_TYPE_DEPOSIT)) {
                    holder.itemMsgTitle.setText(R.string.str_staking);
                    holder.itemMsgImg.setImageDrawable(getResources().getDrawable(R.drawable.deposit_ic));
                } else if (msg.type.equals(OK_MSG_TYPE_WITHDRAW)) {
                    holder.itemMsgTitle.setText(R.string.str_to_unbonding);
                    holder.itemMsgImg.setImageDrawable(getResources().getDrawable(R.drawable.withdraw_ic));
                }
                try {
                    holder.itemDeleagtor.setText(WKey.convertAddressOkexToEth(msg.value.delegator_address));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                WDp.showCoinDp(getBaseContext(), getBaseDao(), msg.value.quantity, holder.itemAmountDenom, holder.itemAmount, mBaseChain);
            }
        }

        private void onBindOkDirectVote(RecyclerView.ViewHolder viewHolder, int position) {
            final TxOkVoteHolder holder = (TxOkVoteHolder) viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                try {
                    holder.itemVoter.setText(WKey.convertAddressOkexToEth(msg.value.delegator_address));
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
        }

        private void onBindUnKnown(RecyclerView.ViewHolder viewHolder, int position) {
            final TxUnKnownHolder holder = (TxUnKnownHolder) viewHolder;
            holder.itemUnknownImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        }


        public class TxCommonHolder extends RecyclerView.ViewHolder {
            ImageView itemStatusImg;
            TextView itemStatusTxt, itemFailTxt, itemHash, itemHeight, itemMsgCnt, itemGas,
                    itemTime, itemTimeGap, itemMemo, itemFee, itemFeeDenom, itemFeeUsed, itemFeeUsedDenom,
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
            TextView itemFromAddress, itemToAddress;
            RelativeLayout itemSingleCoinLayer;
            TextView itemAmount, itemAmountDenom;
            LinearLayout itemMultiCoinLayer;
            RelativeLayout itemAmountLayer0, itemAmountLayer1, itemAmountLayer2, itemAmountLayer3, itemAmountLayer4;
            TextView itemAmount0, itemAmountDenom0, itemAmount1, itemAmountDenom1, itemAmount2, itemAmountDenom2,
                    itemAmount3, itemAmountDenom3, itemAmount4, itemAmountDenom4;


            public TxTransferHolder(@NonNull View itemView) {
                super(itemView);
                itemSendReceiveImg = itemView.findViewById(R.id.tx_send_icon);
                itemSendRecieveTv = itemView.findViewById(R.id.tx_send_text);
                itemFromAddress = itemView.findViewById(R.id.tx_send_from_address);
                itemToAddress = itemView.findViewById(R.id.tx_send_to_address);

                itemSingleCoinLayer = itemView.findViewById(R.id.tx_send_single_coin_layer);
                itemAmount = itemView.findViewById(R.id.tx_transfer_amount);
                itemAmountDenom = itemView.findViewById(R.id.tx_transfer_amount_symbol);

                itemMultiCoinLayer = itemView.findViewById(R.id.tx_send_multi_coin_layer);
                itemAmountLayer0 = itemView.findViewById(R.id.tx_transfer_amount_layer0);
                itemAmountLayer1 = itemView.findViewById(R.id.tx_transfer_amount_layer1);
                itemAmountLayer2 = itemView.findViewById(R.id.tx_transfer_amount_layer2);
                itemAmountLayer3 = itemView.findViewById(R.id.tx_transfer_amount_layer3);
                itemAmountLayer4 = itemView.findViewById(R.id.tx_transfer_amount_layer4);
                itemAmount0 = itemView.findViewById(R.id.tx_transfer_amount0);
                itemAmount1 = itemView.findViewById(R.id.tx_transfer_amount1);
                itemAmount2 = itemView.findViewById(R.id.tx_transfer_amount2);
                itemAmount3 = itemView.findViewById(R.id.tx_transfer_amount3);
                itemAmount4 = itemView.findViewById(R.id.tx_transfer_amount4);
                itemAmountDenom0 = itemView.findViewById(R.id.tx_transfer_amount_symbol0);
                itemAmountDenom1 = itemView.findViewById(R.id.tx_transfer_amount_symbol1);
                itemAmountDenom2 = itemView.findViewById(R.id.tx_transfer_amount_symbol2);
                itemAmountDenom3 = itemView.findViewById(R.id.tx_transfer_amount_symbol3);
                itemAmountDenom4 = itemView.findViewById(R.id.tx_transfer_amount_symbol4);
            }
        }

        public class TxMultiSendHolder extends RecyclerView.ViewHolder {
            ImageView itemSendReceiveImg;
            TextView itemSendRecieveTv;
            RelativeLayout itemInputLayer0, itemInputLayer1, itemInputLayer2, itemInputLayer3;
            TextView itemInputAddress0, itemInputAddress1, itemInputAddress2, itemInputAddress3;
            TextView itemInputAmount0, itemInputAmount1, itemInputAmount2, itemInputAmount3;
            TextView itemInputDenom0, itemInputDenom1, itemInputDenom2, itemInputDenom3;
            RelativeLayout itemOutputLayer0, itemOutputLayer1, itemOutputLayer2, itemOutputLayer3;
            TextView itemOutputAddress0, itemOutputAddress1, itemOutputAddress2, itemOutputAddress3;
            TextView itemOutputAmount0, itemOutputAmount1, itemOutputAmount2, itemOutputAmount3;
            TextView itemOutputDenom0, itemOutputDenom1, itemOutputDenom2, itemOutputDenom3;

            public TxMultiSendHolder(@NonNull View itemView) {
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
            }
        }

        public class TxRewardAllHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemDelegator;
            TextView itemRewardValidatorCnt;
            TextView itemRewardValidator0, itemRewardValidator1, itemRewardValidator2, itemRewardValidator3, itemRewardValidator4, itemRewardValidator5, itemRewardValidator6, itemRewardValidator7;
            TextView itemRewardMoniker0, itemRewardMoniker1, itemRewardMoniker2, itemRewardMoniker3, itemRewardMoniker4, itemRewardMoniker5, itemRewardMoniker6, itemRewardMoniker7;
            TextView itemRewardAllAmount, itemRewardAllAmountDenom;

            public TxRewardAllHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemDelegator = itemView.findViewById(R.id.tx_reward_all_delegator);
                itemRewardValidatorCnt = itemView.findViewById(R.id.validator_count);
                itemRewardValidator0 = itemView.findViewById(R.id.tx_reward_validator0);
                itemRewardValidator1 = itemView.findViewById(R.id.tx_reward_validator1);
                itemRewardValidator2 = itemView.findViewById(R.id.tx_reward_validator2);
                itemRewardValidator3 = itemView.findViewById(R.id.tx_reward_validator3);
                itemRewardValidator4 = itemView.findViewById(R.id.tx_reward_validator4);
                itemRewardValidator5 = itemView.findViewById(R.id.tx_reward_validator5);
                itemRewardValidator6 = itemView.findViewById(R.id.tx_reward_validator6);
                itemRewardValidator7 = itemView.findViewById(R.id.tx_reward_validator7);
                itemRewardMoniker0 = itemView.findViewById(R.id.tx_reward_moniker0);
                itemRewardMoniker1 = itemView.findViewById(R.id.tx_reward_moniker1);
                itemRewardMoniker2 = itemView.findViewById(R.id.tx_reward_moniker2);
                itemRewardMoniker3 = itemView.findViewById(R.id.tx_reward_moniker3);
                itemRewardMoniker4 = itemView.findViewById(R.id.tx_reward_moniker4);
                itemRewardMoniker5 = itemView.findViewById(R.id.tx_reward_moniker5);
                itemRewardMoniker6 = itemView.findViewById(R.id.tx_reward_moniker6);
                itemRewardMoniker7 = itemView.findViewById(R.id.tx_reward_moniker7);
                itemRewardAllAmount = itemView.findViewById(R.id.tx_reward_all_sum_amount);
                itemRewardAllAmountDenom = itemView.findViewById(R.id.tx_reward_all_sum_symbol);
            }
        }

        public class TxUnjailHolder extends RecyclerView.ViewHolder {
            ImageView itemUnjailImg;
            TextView itemUnjailTitle;
            TextView itemValidator, itemMoniker;


            public TxUnjailHolder(@NonNull View itemView) {
                super(itemView);
                itemUnjailImg = itemView.findViewById(R.id.tx_unjail_icon);
                itemUnjailTitle = itemView.findViewById(R.id.tx_unjail_text);
                itemValidator = itemView.findViewById(R.id.tx_unjail_validator);
                itemMoniker = itemView.findViewById(R.id.tx_unjail_moniker);
            }
        }

        public class TxCommissionHolder extends RecyclerView.ViewHolder {
            ImageView itemCommissionImg;
            TextView itemCommissionTitle;
            TextView itemCommissionValidator, itemCommissionValidatorMoniker, itemCommissionAmount, itemCommissionAmountDenom;

            public TxCommissionHolder(@NonNull View itemView) {
                super(itemView);
                itemCommissionImg = itemView.findViewById(R.id.tx_commission_icon);
                itemCommissionTitle = itemView.findViewById(R.id.tx_commission_text);
                itemCommissionValidator = itemView.findViewById(R.id.tx_commission_validator);
                itemCommissionValidatorMoniker = itemView.findViewById(R.id.tx_commission_moniker);
                itemCommissionAmount = itemView.findViewById(R.id.tx_commission_amount);
                itemCommissionAmountDenom = itemView.findViewById(R.id.tx_commission_amount_symbol);
            }
        }

        public class TxPostPriceHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            TextView itemPoster, itemMakerId, itemPostPrice, itemTime;

            public TxPostPriceHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemPoster = itemView.findViewById(R.id.tx_price_poster);
                itemMakerId = itemView.findViewById(R.id.tx_market_id);
                itemPostPrice = itemView.findViewById(R.id.tx_post_price);
                itemTime = itemView.findViewById(R.id.tx_validity_time);
            }
        }

        public class TxCreateHtlcHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            LinearLayout itemExpectedLayer;
            TextView itemSendAmount, itemSendDenom, itemSender, itemRecipient, itemRandomHash, itemExpectIncome, itemStatus;

            public TxCreateHtlcHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemExpectedLayer = itemView.findViewById(R.id.expected_layer);
                itemSendAmount = itemView.findViewById(R.id.send_amount);
                itemSendDenom = itemView.findViewById(R.id.send_amount_denom);
                itemSender = itemView.findViewById(R.id.sender_addr);
                itemRecipient = itemView.findViewById(R.id.recipient_addr);
                itemRandomHash = itemView.findViewById(R.id.random_hash);
                itemExpectIncome = itemView.findViewById(R.id.expected_income);
                itemStatus = itemView.findViewById(R.id.status_txt);
            }
        }

        public class TxClaimHtlcHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            RelativeLayout itemAmountLayer;
            TextView itemReceiveAmount, itemReceiveDenom, itemClaimer, itemRandomNumber, itemSwapId;

            public TxClaimHtlcHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemAmountLayer = itemView.findViewById(R.id.claim_amount_layer);
                itemReceiveAmount = itemView.findViewById(R.id.claim_amount);
                itemReceiveDenom = itemView.findViewById(R.id.claim_amount_denom);
                itemClaimer = itemView.findViewById(R.id.claimer_addr);
                itemRandomNumber = itemView.findViewById(R.id.claim_random_number);
                itemSwapId = itemView.findViewById(R.id.claim_swap_id);
            }
        }

        public class TxRefundHtlcHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            RelativeLayout itemAmountLayer;
            TextView itemRefundAmount, itemRefundDenom, itemFromAddr, itemSwapId;

            public TxRefundHtlcHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemAmountLayer = itemView.findViewById(R.id.refund_amount_layer);
                itemRefundAmount = itemView.findViewById(R.id.refund_amount);
                itemRefundDenom = itemView.findViewById(R.id.refund_amount_denom);
                itemFromAddr = itemView.findViewById(R.id.refund_addr);
                itemSwapId = itemView.findViewById(R.id.refund_swap_id);
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
        Dialog_MoreWait waitMore = Dialog_MoreWait.newInstance(null);
        waitMore.setCancelable(false);
        waitMore.show(getSupportFragmentManager(), "dialog");
    }

    public void onWaitMore() {
        FetchCnt = 0;
        onFetchTx(mTxHash);
    }

    private int FetchCnt = 0;

    private void onFetchTx(String hash) {
        WLog.w("hash " + hash);
        if (mBaseChain.equals(BNB_MAIN)) {
            ApiClient.getBnbChain(getBaseContext()).getSearchTx(hash, "json").enqueue(new Callback<ResBnbTxInfo>() {
                @Override
                public void onResponse(Call<ResBnbTxInfo> call, Response<ResBnbTxInfo> response) {
                    if (isFinishing()) return;
                    WLog.w("onFetchTx " + response.toString());
                    if (response.isSuccessful() && response.body() != null) {
                        mResBnbTxInfo = response.body();
                        mResBnbTxInfo = response.body();
                        if (mResBnbTxInfo.getMsg(0).type.equals(BNB_MSG_TYPE_HTLC) &&
                                mAccount.address.equals(mResBnbTxInfo.getMsg(0).value.from)) {
                            onFetchHtlcStatus(mResBnbTxInfo.simpleSwapId());
                        } else {
                            onUpdateView();
                        }
                    } else {
                        if (mIsSuccess && FetchCnt < 10) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FetchCnt++;
                                    onFetchTx(mTxHash);
                                }
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
                    WLog.w("BNB onFailure");
                    if (BuildConfig.DEBUG) t.printStackTrace();
                    if (isFinishing()) return;
                }
            });

        } else if (mBaseChain.equals(BNB_TEST)) {
            ApiClient.getBnbTestChain(getBaseContext()).getSearchTx(hash, "json").enqueue(new Callback<ResBnbTxInfo>() {
                @Override
                public void onResponse(Call<ResBnbTxInfo> call, Response<ResBnbTxInfo> response) {
                    if (isFinishing()) return;
                    WLog.w("onFetchTx " + response.toString());
                    if (response.isSuccessful() && response.body() != null) {
                        mResBnbTxInfo = response.body();
                        if (mResBnbTxInfo.getMsg(0).type.equals(BNB_MSG_TYPE_HTLC) &&
                                mAccount.address.equals(mResBnbTxInfo.getMsg(0).value.from)) {
                            onFetchHtlcStatus(mResBnbTxInfo.simpleSwapId());
                        } else {
                            onUpdateView();
                        }
                    } else {
                        if (mIsSuccess && FetchCnt < 10) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FetchCnt++;
                                    onFetchTx(mTxHash);
                                }
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
                    WLog.w("BNB onFailure");
                    if (BuildConfig.DEBUG) t.printStackTrace();
                    if (isFinishing()) return;
                }
            });

        } else if (mBaseChain.equals(OKEX_MAIN)) {
            ApiClient.getOkexChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
                @Override
                public void onResponse(Call<ResTxInfo> call, Response<ResTxInfo> response) {
                    if (isFinishing()) return;
                    WLog.w("onFetchTx " + response.toString());
                    if (response.isSuccessful() && response.body() != null) {
                        mResTxInfo = response.body();
                        onUpdateView();
                    } else {
                        if (mIsSuccess && FetchCnt < 10) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FetchCnt++;
                                    onFetchTx(mTxHash);
                                }
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

        } else if (mBaseChain.equals(OK_TEST)) {
            ApiClient.getOkTestChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
                @Override
                public void onResponse(Call<ResTxInfo> call, Response<ResTxInfo> response) {
                    if (isFinishing()) return;
                    WLog.w("onFetchTx " + response.toString());
                    if (response.isSuccessful() && response.body() != null) {
                        mResTxInfo = response.body();
                        onUpdateView();
                    } else {
                        if (mIsSuccess && FetchCnt < 10) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FetchCnt++;
                                    onFetchTx(mTxHash);
                                }
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


    private void onFetchHtlcStatus(String swapId) {
//        WLog.w("onFetchHtlcStatus "  +swapId);
        if (!TextUtils.isEmpty(swapId)) {
            if (mBaseChain.equals(BNB_MAIN)) {
                ApiClient.getBnbChain(getBaseContext()).getSwapById(swapId).enqueue(new Callback<ResBnbSwapInfo>() {
                    @Override
                    public void onResponse(Call<ResBnbSwapInfo> call, Response<ResBnbSwapInfo> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            WLog.w("onFetchHtlcStatus url " + call.request().url());
                            mResBnbSwapInfo = response.body();
                        }
                        onFetchBnbNodeInfo();
                    }

                    @Override
                    public void onFailure(Call<ResBnbSwapInfo> call, Throwable t) {
                        WLog.w("onFetchHtlcStatus " + t.getMessage());
                        onUpdateView();
                    }
                });

            } else if (mBaseChain.equals(BNB_TEST)) {
                ApiClient.getBnbTestChain(getBaseContext()).getSwapById(swapId).enqueue(new Callback<ResBnbSwapInfo>() {
                    @Override
                    public void onResponse(Call<ResBnbSwapInfo> call, Response<ResBnbSwapInfo> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            WLog.w("onFetchHtlcStatus url " + call.request().url());
                            mResBnbSwapInfo = response.body();
                        }
                        onFetchBnbNodeInfo();
                    }

                    @Override
                    public void onFailure(Call<ResBnbSwapInfo> call, Throwable t) {
                        WLog.w("onFetchHtlcStatus " + t.getMessage());
                        onUpdateView();
                    }
                });

            }

        } else {
            onUpdateView();
        }
    }

    private void onFetchBnbNodeInfo() {
        if (mBaseChain.equals(BNB_MAIN)) {
            ApiClient.getBnbChain(getBaseContext()).getNodeInfo().enqueue(new Callback<ResNodeInfo>() {
                @Override
                public void onResponse(Call<ResNodeInfo> call, Response<ResNodeInfo> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        mResBnbNodeInfo = response.body();
                    }
                    onUpdateView();
                }

                @Override
                public void onFailure(Call<ResNodeInfo> call, Throwable t) {
                    WLog.w("onFetchBnbNodeInfo " + t.getMessage());
                    onUpdateView();
                }
            });

        } else if (mBaseChain.equals(BNB_TEST)) {
            ApiClient.getBnbTestChain(getBaseContext()).getNodeInfo().enqueue(new Callback<ResNodeInfo>() {
                @Override
                public void onResponse(Call<ResNodeInfo> call, Response<ResNodeInfo> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        mResBnbNodeInfo = response.body();
                    }
                    onUpdateView();
                }

                @Override
                public void onFailure(Call<ResNodeInfo> call, Throwable t) {
                    WLog.w("onFetchBnbNodeInfo " + t.getMessage());
                    onUpdateView();
                }
            });
        }
    }
}
