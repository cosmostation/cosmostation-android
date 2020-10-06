package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.dialog.Dialog_MoreWait;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.model.StarNameResource;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Input;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Output;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBnbNodeInfo;
import wannabit.io.cosmostaion.network.res.ResBnbSwapInfo;
import wannabit.io.cosmostaion.network.res.ResBnbTxInfo;
import wannabit.io.cosmostaion.network.res.ResKavaSwapInfo;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.BNB_MSG_TYPE_HTLC;
import static wannabit.io.cosmostaion.base.BaseConstant.BNB_MSG_TYPE_HTLC_CLIAM;
import static wannabit.io.cosmostaion.base.BaseConstant.BNB_MSG_TYPE_HTLC_REFUND;
import static wannabit.io.cosmostaion.base.BaseConstant.CERTIK_MSG_TYPE_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_DELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_REDELEGATE2;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_TRANSFER2;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_TRANSFER3;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_UNDELEGATE2;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_DEL;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_MIDIFY;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_VAL;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_BROADCAST;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_UNKNOWN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BINANCE_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BINANCE_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_OKEX_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.IOV_MSG_TYPE_DELETE_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.IOV_MSG_TYPE_DELETE_DOMAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.IOV_MSG_TYPE_REGISTER_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.IOV_MSG_TYPE_REGISTER_DOMAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.IOV_MSG_TYPE_REPLACE_ACCOUNT_RESOURCE;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_COMMISSION;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_DELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_REDELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_UNDELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_WITHDRAW;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_WITHDRAW_ALL;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_WITHDRAW_MIDIFY;
import static wannabit.io.cosmostaion.base.BaseConstant.IS_SHOWLOG;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_BEP3_CLAM_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_BEP3_CREATE_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_BEP3_REFUND_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_CREATE_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_DEPOSIT_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_DRAWDEBT_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_INCENTIVE_REWARD;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_POST_PRICE;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_REPAYDEBT_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_WITHDRAW_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.OK_MSG_TYPE_DEPOSIT;
import static wannabit.io.cosmostaion.base.BaseConstant.OK_MSG_TYPE_DIRECT_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.OK_MSG_TYPE_MULTI_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.OK_MSG_TYPE_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.OK_MSG_TYPE_WITHDRAW;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.network.res.ResBnbSwapInfo.BNB_STATUS_OPEN;
import static wannabit.io.cosmostaion.network.res.ResKavaSwapInfo.STATUS_EXPIRED;

public class TxDetailActivity extends BaseActivity implements View.OnClickListener {

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

    private boolean mIsGen;
    private boolean mIsSuccess;
    private int mErrorCode;
    private String mErrorMsg;
    private String mTxHash;

    private TxDetailAdapter mTxDetailAdapter;
    private ResTxInfo       mResTxInfo;
    private ResKavaSwapInfo mResKavaSwapInfo;
    private ResBnbTxInfo    mResBnbTxInfo;
    private ResBnbSwapInfo  mResBnbSwapInfo;
    private ResBnbNodeInfo  mResBnbNodeInfo;
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

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount    = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain  = getChain(mAccount.baseChain);
        mIsGen      = getIntent().getBooleanExtra("isGen", false);
        mIsSuccess  = getIntent().getBooleanExtra("isSuccess", false);
        mErrorCode  = getIntent().getIntExtra("errorCode", ERROR_CODE_UNKNOWN);
        mErrorMsg   = getIntent().getStringExtra("errorMsg");
        mTxHash     = getIntent().getStringExtra("txHash");
        mBnbTime    = getIntent().getStringExtra("bnbTime");

        mAllValidators = getBaseDao().mAllValidators;
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
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            if (mBaseChain.equals(COSMOS_MAIN)) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, EXPLORER_COSMOS_MAIN + "txs/" + mResTxInfo.txhash);
            } else if (mBaseChain.equals(IRIS_MAIN)) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, EXPLORER_IRIS_MAIN + "txs/" + mResTxInfo.hash);
            } else if (mBaseChain.equals(BNB_MAIN)) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, EXPLORER_BINANCE_MAIN + "txs/" + mResBnbTxInfo.hash);
            } else if (mBaseChain.equals(BNB_TEST)) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, EXPLORER_BINANCE_TEST + "tx/" + mResBnbTxInfo.hash);
            } else if (mBaseChain.equals(KAVA_MAIN)) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, EXPLORER_KAVA_MAIN + "txs/" + mResTxInfo.txhash);
            } else if (mBaseChain.equals(KAVA_TEST)) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, EXPLORER_KAVA_TEST + "txs/" + mResTxInfo.txhash);
            } else if (mBaseChain.equals(OK_TEST)) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, EXPLORER_OKEX_TEST  + "tx/" + mResTxInfo.txhash);
            } else if (mBaseChain.equals(BAND_MAIN)) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, EXPLORER_BAND_MAIN + "tx/" + mResTxInfo.txhash);
            } else if (mBaseChain.equals(IOV_MAIN)) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, EXPLORER_IOV_MAIN + "txs/" + mResTxInfo.txhash);
            } else if (mBaseChain.equals(CERTIK_TEST)) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, EXPLORER_CERTIK_TEST + "Transactions/" + mResTxInfo.txhash + "?net=" + mBaseChain.getChain());

            }
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "send"));

        } else if (v.equals(mRefundBtn)) {
            if (!mAccount.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                add.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                return;
            }

            if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
                if (WDp.getAvailableCoin(mAccount.balances, TOKEN_BNB).compareTo(new BigDecimal(FEE_BNB_SEND)) < 0) {
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
        private static final int TYPE_TX_DELEGATE = 2;
        private static final int TYPE_TX_UNDELEGATE = 3;
        private static final int TYPE_TX_REDELEGATE = 4;
        private static final int TYPE_TX_REWARD = 5;
        private static final int TYPE_TX_ADDRESS_CHANGE = 6;
        private static final int TYPE_TX_VOTE = 7;
        private static final int TYPE_TX_COMMISSION = 8;
        private static final int TYPE_TX_MULTI_SEND = 9;
        private static final int TYPE_TX_POST_PRICE = 10;
        private static final int TYPE_TX_CREATE_CDP = 11;
        private static final int TYPE_TX_DEPOSIT_CDP = 12;
        private static final int TYPE_TX_WITHDRAW_CDP = 13;
        private static final int TYPE_TX_DRAW_DEBT_CDP = 14;
        private static final int TYPE_TX_REPAY_CDP = 15;
        private static final int TYPE_TX_HTLC_CREATE = 16;
        private static final int TYPE_TX_HTLC_CLAIM = 17;
        private static final int TYPE_TX_HTLC_REFUND = 18;
        private static final int TYPE_TX_INCENTIVE_REWARD = 19;
        private static final int TYPE_TX_REWARD_ALL = 20;
        private static final int TYPE_TX_OK_STAKE = 21;
        private static final int TYPE_TX_OK_DIRECT_VOTE = 22;

        private static final int TYPE_REGISTER_DOMAIN = 23;
        private static final int TYPE_REGISTER_ACCOUNT = 24;
        private static final int TYPE_DELETE_ACCOUNT = 25;
        private static final int TYPE_DELETE_DOMAIN = 26;
        private static final int TYPE_REPLACE_ACCOUNT_RESOURCE = 27;

        private static final int TYPE_TX_UNKNOWN = 999;

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
            } else if (viewType == TYPE_TX_COMMISSION) {
                return new TxCommissionHolder(getLayoutInflater().inflate(R.layout.item_tx_commission, viewGroup, false));
            } else if (viewType == TYPE_TX_MULTI_SEND) {
                return new TxMultiSendHolder(getLayoutInflater().inflate(R.layout.item_tx_multisend, viewGroup, false));
            } else if (viewType == TYPE_TX_POST_PRICE) {
                return new TxPostPriceHolder(getLayoutInflater().inflate(R.layout.item_tx_post_price, viewGroup, false));
            } else if (viewType == TYPE_TX_CREATE_CDP) {
                return new TxCreateCdpHolder(getLayoutInflater().inflate(R.layout.item_tx_create_cdp, viewGroup, false));
            } else if (viewType == TYPE_TX_DEPOSIT_CDP) {
                return new TxDepositCdpHolder(getLayoutInflater().inflate(R.layout.item_tx_deposit_cdp, viewGroup, false));
            } else if (viewType == TYPE_TX_WITHDRAW_CDP) {
                return new TxWithdrawCdpHolder(getLayoutInflater().inflate(R.layout.item_tx_withdraw_cdp, viewGroup, false));
            } else if (viewType == TYPE_TX_DRAW_DEBT_CDP) {
                return new TxDrawDebtCdpHolder(getLayoutInflater().inflate(R.layout.item_tx_drawdebt_cdp, viewGroup, false));
            } else if (viewType == TYPE_TX_REPAY_CDP) {
                return new TxRepayDebtCdpHolder(getLayoutInflater().inflate(R.layout.item_tx_repaydebt_cdp, viewGroup, false));
            } else if (viewType == TYPE_TX_HTLC_CREATE) {
                return new TxCreateHtlcHolder(getLayoutInflater().inflate(R.layout.item_tx_htlc_create, viewGroup, false));
            } else if (viewType == TYPE_TX_HTLC_CLAIM) {
                return new TxClaimHtlcHolder(getLayoutInflater().inflate(R.layout.item_tx_htlc_claim, viewGroup, false));
            } else if (viewType == TYPE_TX_HTLC_REFUND) {
                return new TxRefundHtlcHolder(getLayoutInflater().inflate(R.layout.item_tx_htlc_refund, viewGroup, false));
            } else if (viewType == TYPE_TX_INCENTIVE_REWARD) {
                return new TxIncentiveHolder(getLayoutInflater().inflate(R.layout.item_tx_incentive_reward, viewGroup, false));
            } else if (viewType == TYPE_TX_OK_STAKE) {
                return new TxOkStakeHolder(getLayoutInflater().inflate(R.layout.item_tx_ok_stake, viewGroup, false));
            } else if (viewType == TYPE_TX_REWARD_ALL) {
                return new TxRewardAllHolder(getLayoutInflater().inflate(R.layout.item_tx_reward_all, viewGroup, false));
            } else if (viewType == TYPE_REGISTER_DOMAIN) {
                return new TxRegisterDomainHolder(getLayoutInflater().inflate(R.layout.item_tx_starname_register_domain, viewGroup, false));
            } else if (viewType == TYPE_REGISTER_ACCOUNT) {
                return new TxRegisterAccountHolder(getLayoutInflater().inflate(R.layout.item_tx_starname_register_account, viewGroup, false));
            } else if (viewType == TYPE_DELETE_ACCOUNT) {
                return new TxDeleteAccountHolder(getLayoutInflater().inflate(R.layout.item_tx_starname_delete_account, viewGroup, false));
            } else if (viewType == TYPE_DELETE_DOMAIN) {
                return new TxDeleteDomainHolder(getLayoutInflater().inflate(R.layout.item_tx_starname_delete_domain, viewGroup, false));
            } else if (viewType == TYPE_REPLACE_ACCOUNT_RESOURCE) {
                return new TxReplaceResourceHolder(getLayoutInflater().inflate(R.layout.item_tx_starname_resource, viewGroup, false));
            }
            return new TxUnKnownHolder(getLayoutInflater().inflate(R.layout.item_tx_unknown, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_TX_COMMON) {
                onBindCommon(viewHolder);
            } else if (getItemViewType(position) == TYPE_TX_TRANSFER) {
                onBindTransfer(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_DELEGATE) {
                onBindDelegate(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_UNDELEGATE) {
                onBindUndelegate(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_REDELEGATE) {
                onBindRedelegate(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_REWARD) {
                onBindReward(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_ADDRESS_CHANGE) {
                onBindAddress(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_VOTE) {
                onBindVote(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_COMMISSION) {
                onBindCommission(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_MULTI_SEND) {
                onBindMultiSend(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_POST_PRICE) {
                onBindPostPrice(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_CREATE_CDP) {
                onBindCreateCdp(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_DEPOSIT_CDP) {
                onBindDepositCdp(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_WITHDRAW_CDP) {
                onBindWithdrawCdp(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_DRAW_DEBT_CDP) {
                onBindDrawDebtCdp(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_REPAY_CDP) {
                onBindRepayDebtCdp(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_HTLC_CREATE) {
                onBindCreateHTLC(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_HTLC_CLAIM) {
                onBindClaimHTLC(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_HTLC_REFUND) {
                onBindRefundHTLC(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_INCENTIVE_REWARD) {
                onBindIncentive(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_REWARD_ALL) {
                onBindRewardAll(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_OK_STAKE) {
                onBindOkStake(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_OK_DIRECT_VOTE) {
                onBindOkDirectVote(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_REGISTER_DOMAIN) {
                onBindRegisterDomain(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_REGISTER_ACCOUNT) {
                onBindRegisterAccount(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_DELETE_DOMAIN) {
                onBindDeleteDomain(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_DELETE_ACCOUNT) {
                onBindDeleteAccount(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_REPLACE_ACCOUNT_RESOURCE) {
                onBindReplaceResource(viewHolder, position);
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

                    } else if (mResTxInfo.getMsgType(position - 1) .equals(KAVA_MSG_TYPE_POST_PRICE)) {
                        return TYPE_TX_POST_PRICE;

                    } else if (mResTxInfo.getMsgType(position - 1) .equals(KAVA_MSG_TYPE_CREATE_CDP)) {
                        return TYPE_TX_CREATE_CDP;

                    } else if (mResTxInfo.getMsgType(position - 1) .equals(KAVA_MSG_TYPE_DEPOSIT_CDP)) {
                        return TYPE_TX_DEPOSIT_CDP;

                    } else if (mResTxInfo.getMsgType(position - 1) .equals(KAVA_MSG_TYPE_WITHDRAW_CDP)) {
                        return TYPE_TX_WITHDRAW_CDP;

                    } else if (mResTxInfo.getMsgType(position - 1) .equals(KAVA_MSG_TYPE_DRAWDEBT_CDP)) {
                        return TYPE_TX_DRAW_DEBT_CDP;

                    } else if (mResTxInfo.getMsgType(position - 1) .equals(KAVA_MSG_TYPE_REPAYDEBT_CDP)) {
                        return TYPE_TX_REPAY_CDP;

                    } else if (mResTxInfo.getMsgType(position - 1) .equals(COSMOS_MSG_TYPE_WITHDRAW_VAL) ||
                            mResTxInfo.getMsgType(position - 1) .equals(IRIS_MSG_TYPE_COMMISSION)) {
                        return TYPE_TX_COMMISSION;

                    } else if (mResTxInfo.getMsgType(position - 1) .equals(KAVA_MSG_TYPE_BEP3_CREATE_SWAP)) {
                        return TYPE_TX_HTLC_CREATE;

                    } else if (mResTxInfo.getMsgType(position - 1) .equals(KAVA_MSG_TYPE_BEP3_CLAM_SWAP)) {
                        return TYPE_TX_HTLC_CLAIM;

                    } else if (mResTxInfo.getMsgType(position - 1) .equals(KAVA_MSG_TYPE_BEP3_REFUND_SWAP)) {
                        return TYPE_TX_HTLC_REFUND;

                    } else if (mResTxInfo.getMsgType(position - 1) .equals(KAVA_MSG_TYPE_INCENTIVE_REWARD)) {
                        return TYPE_TX_INCENTIVE_REWARD;

                    } else if (mResTxInfo.getMsgType(position - 1) .equals(IRIS_MSG_TYPE_WITHDRAW_ALL)) {
                        return TYPE_TX_REWARD_ALL;

                    } else if (mResTxInfo.getMsgType(position - 1) .equals(OK_MSG_TYPE_DEPOSIT) ||
                            mResTxInfo.getMsgType(position - 1) .equals(OK_MSG_TYPE_WITHDRAW)) {
                        return TYPE_TX_OK_STAKE;

                    } else if (mResTxInfo.getMsgType(position - 1) .equals(OK_MSG_TYPE_DIRECT_VOTE)) {
                        return TYPE_TX_OK_DIRECT_VOTE;
                    }

                    else if (mResTxInfo.getMsgType(position - 1) .equals(IOV_MSG_TYPE_REGISTER_DOMAIN)) {
                        return TYPE_REGISTER_DOMAIN;
                    } else if (mResTxInfo.getMsgType(position - 1) .equals(IOV_MSG_TYPE_REGISTER_ACCOUNT)) {
                        return TYPE_REGISTER_ACCOUNT ;
                    } else if (mResTxInfo.getMsgType(position - 1) .equals(IOV_MSG_TYPE_DELETE_DOMAIN)) {
                        return TYPE_DELETE_DOMAIN ;
                    } else if (mResTxInfo.getMsgType(position - 1) .equals(IOV_MSG_TYPE_DELETE_ACCOUNT)) {
                        return TYPE_DELETE_ACCOUNT ;
                    } else if (mResTxInfo.getMsgType(position - 1) .equals(IOV_MSG_TYPE_REPLACE_ACCOUNT_RESOURCE)) {
                        return TYPE_REPLACE_ACCOUNT_RESOURCE ;
                    }

                    return TYPE_TX_UNKNOWN;
                }
            }
        }


        private void onBindCommon(RecyclerView.ViewHolder viewHolder) {
            final TxCommonHolder holder = (TxCommonHolder)viewHolder;
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemFeeDenom);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemFeeUsedDenom);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemFeeLimitDenom);
            if (mBaseChain.equals(COSMOS_MAIN) || mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST) ||
                    mBaseChain.equals(BAND_MAIN) || mBaseChain.equals(IOV_MAIN) || mBaseChain.equals(IOV_TEST) || mBaseChain.equals(CERTIK_TEST)) {
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
                holder.itemFee.setText(WDp.getDpAmount2(getBaseContext(), mResTxInfo.simpleFee(), 6, 6));
                holder.itemFeeLayer.setVisibility(View.VISIBLE);
                holder.itemTime.setText(WDp.getTimeTxformat(getBaseContext(), mResTxInfo.timestamp));
                holder.itemTimeGap.setText(WDp.getTimeTxGap(getBaseContext(), mResTxInfo.timestamp));
                holder.itemHash.setText(mResTxInfo.txhash);
                holder.itemMemo.setText(mResTxInfo.tx.value.memo);

            } else if (mBaseChain.equals(IRIS_MAIN)) {
                if (mResTxInfo.isIrisSuccess()) {
                    holder.itemStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.success_ic));
                    holder.itemStatusTxt.setText(R.string.str_success_c);
                } else {
                    holder.itemStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.fail_ic));
                    holder.itemStatusTxt.setText(R.string.str_failed_c);
                    holder.itemFailTxt.setText(mResTxInfo.failMsgIris());
                    holder.itemFailTxt.setVisibility(View.VISIBLE);
                }
                holder.itemHeight.setText(mResTxInfo.height);
                holder.itemMsgCnt.setText(String.valueOf(mResTxInfo.getMsgs().size()));
                holder.itemGas.setText(String.format("%s / %s", mResTxInfo.result.GasUsed, mResTxInfo.result.GasWanted));

                holder.itemFeeUsed.setText(WDp.getDpAmount2(getBaseContext(), mResTxInfo.simpleUsedFeeIris(), 18, 18));
                holder.itemFeeUsedLayer.setVisibility(View.VISIBLE);
                holder.itemFeeLimit.setText(WDp.getDpAmount2(getBaseContext(), mResTxInfo.simpleFee(), 18, 18));
                holder.itemFeeLimitLayer.setVisibility(View.VISIBLE);
                holder.itemTime.setText(WDp.getTimeTxformat(getBaseContext(), mResTxInfo.timestamp));
                holder.itemTimeGap.setText(WDp.getTimeTxGap(getBaseContext(), mResTxInfo.timestamp));
                holder.itemHash.setText(mResTxInfo.hash);
                holder.itemMemo.setText(mResTxInfo.tx.value.memo);

            } else if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
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

            } else if (mBaseChain.equals(OK_TEST)) {
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
                holder.itemFee.setText(WDp.getDpAmount2(getBaseContext(), mResTxInfo.simpleFee(), 0, 8));
                holder.itemFeeLayer.setVisibility(View.VISIBLE);
                holder.itemTime.setText(WDp.getTimeTxformat(getBaseContext(), mResTxInfo.timestamp));
                holder.itemTimeGap.setText(WDp.getTimeTxGap(getBaseContext(), mResTxInfo.timestamp));
                holder.itemHash.setText(mResTxInfo.txhash);
                holder.itemMemo.setText(mResTxInfo.tx.value.memo);

            }

            holder.itemBtnHashLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent webintent = new Intent(getBaseContext(), WebActivity.class);
                    if (mBaseChain.equals(COSMOS_MAIN) || mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(BAND_MAIN) || mBaseChain.equals(OK_TEST) || mBaseChain.equals(IOV_MAIN) || mBaseChain.equals(KAVA_TEST) || mBaseChain.equals(CERTIK_TEST) ) {
                        webintent.putExtra("txid", mResTxInfo.txhash);
                    } else if (mBaseChain.equals(IRIS_MAIN)) {
                        webintent.putExtra("txid", mResTxInfo.hash);
                    } else if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
                        webintent.putExtra("txid", mResBnbTxInfo.hash);
                    } else {
                        return;
                    }
                    webintent.putExtra("chain", mBaseChain.getChain());
                    webintent.putExtra("goMain", mIsGen);
                    startActivity(webintent);
                }
            });

        }

        private void onBindTransfer(RecyclerView.ViewHolder viewHolder, int position) {
            final TxTransferHolder holder = (TxTransferHolder)viewHolder;
            holder.itemSendReceiveImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(COSMOS_MAIN) || mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST) ||
                    mBaseChain.equals(BAND_MAIN) || mBaseChain.equals(IOV_MAIN) || mBaseChain.equals(IOV_TEST) || mBaseChain.equals(OK_TEST) || mBaseChain.equals(CERTIK_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                ArrayList<Coin> toDpCoin = new ArrayList<>();
                if (msg.type.equals(COSMOS_MSG_TYPE_TRANSFER2) || msg.type.equals(OK_MSG_TYPE_TRANSFER) || msg.type.equals(CERTIK_MSG_TYPE_TRANSFER))  {
                    holder.itemFromAddress.setText(msg.value.from_address);
                    holder.itemToAddress.setText(msg.value.to_address);
                    if (mAccount.address.equals(msg.value.from_address)) {
                        holder.itemSendRecieveTv.setText(R.string.tx_send);
                    }
                    if (mAccount.address.equals(msg.value.to_address)) {
                        holder.itemSendRecieveTv.setText(R.string.tx_receive);
                    }
                    toDpCoin = msg.value.getCoins();
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
                    holder.itemFromAddress.setText(msg.value.from);
                    holder.itemToAddress.setText(msg.value.transfers.get(0).to);
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
                    WDp.showCoinDp(getBaseContext(), toDpCoin.get(0), holder.itemAmountDenom, holder.itemAmount, mBaseChain);
                } else {
                    holder.itemMultiCoinLayer.setVisibility(View.VISIBLE);
                    if (toDpCoin.size() > 0) {
                        WDp.showCoinDp(getBaseContext(), toDpCoin.get(0), holder.itemAmountDenom0, holder.itemAmount0, mBaseChain);
                    }
                    if (toDpCoin.size() > 1) {
                        holder.itemAmountLayer1.setVisibility(View.VISIBLE);
                        WDp.showCoinDp(getBaseContext(), toDpCoin.get(1), holder.itemAmountDenom1, holder.itemAmount1, mBaseChain);
                    }
                    if (toDpCoin.size() > 2) {
                        holder.itemAmountLayer2.setVisibility(View.VISIBLE);
                        WDp.showCoinDp(getBaseContext(), toDpCoin.get(2), holder.itemAmountDenom2, holder.itemAmount2, mBaseChain);
                    }
                    if (toDpCoin.size() > 3) {
                        holder.itemAmountLayer3.setVisibility(View.VISIBLE);
                        WDp.showCoinDp(getBaseContext(), toDpCoin.get(3), holder.itemAmountDenom3, holder.itemAmount3, mBaseChain);
                    }
                    if (toDpCoin.size() > 4) {
                        holder.itemAmountLayer4.setVisibility(View.VISIBLE);
                        WDp.showCoinDp(getBaseContext(), toDpCoin.get(4), holder.itemAmountDenom4, holder.itemAmount4, mBaseChain);
                    }
                }

            } else if (mBaseChain.equals(IRIS_MAIN)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemFromAddress.setText(msg.value.inputs.get(0).address);
                holder.itemToAddress.setText(msg.value.outputs.get(0).address);
                if (mAccount.address.equals(msg.value.inputs.get(0).address)) {
                    holder.itemSendRecieveTv.setText(R.string.tx_send);
                }
                if (mAccount.address.equals(msg.value.outputs.get(0).address)) {
                    holder.itemSendRecieveTv.setText(R.string.tx_receive);
                }

                ArrayList<Coin> toDpCoin = msg.value.inputs.get(0).coins;
                if (toDpCoin.size() == 1) {
                    holder.itemSingleCoinLayer.setVisibility(View.VISIBLE);
                    WDp.showCoinDp(getBaseContext(), toDpCoin.get(0), holder.itemAmountDenom, holder.itemAmount, mBaseChain);
                } else {
                    holder.itemMultiCoinLayer.setVisibility(View.VISIBLE);
                    if (toDpCoin.size() > 0) {
                        WDp.showCoinDp(getBaseContext(), toDpCoin.get(0), holder.itemAmountDenom0, holder.itemAmount0, mBaseChain);
                    }
                    if (toDpCoin.size() > 1) {
                        holder.itemAmountLayer1.setVisibility(View.VISIBLE);
                        WDp.showCoinDp(getBaseContext(), toDpCoin.get(1), holder.itemAmountDenom1, holder.itemAmount1, mBaseChain);
                    }
                    if (toDpCoin.size() > 2) {
                        holder.itemAmountLayer2.setVisibility(View.VISIBLE);
                        WDp.showCoinDp(getBaseContext(), toDpCoin.get(2), holder.itemAmountDenom2, holder.itemAmount2, mBaseChain);
                    }
                    if (toDpCoin.size() > 3) {
                        holder.itemAmountLayer3.setVisibility(View.VISIBLE);
                        WDp.showCoinDp(getBaseContext(), toDpCoin.get(3), holder.itemAmountDenom3, holder.itemAmount3, mBaseChain);
                    }
                    if (toDpCoin.size() > 4) {
                        holder.itemAmountLayer4.setVisibility(View.VISIBLE);
                        WDp.showCoinDp(getBaseContext(), toDpCoin.get(4), holder.itemAmountDenom4, holder.itemAmount4, mBaseChain);
                    }
                }

            } else if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
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
                WDp.showCoinDp(getBaseContext(), toDpCoin.get(0), holder.itemAmountDenom, holder.itemAmount, mBaseChain);

            }
        }

        //TODO now not perfect support with multi transfer with multi coins!!
        private void onBindMultiSend(RecyclerView.ViewHolder viewHolder, int position) {
            final TxMultiSendHolder holder = (TxMultiSendHolder)viewHolder;
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemInputDenom0);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemInputDenom1);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemInputDenom2);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemInputDenom3);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemOutputDenom0);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemOutputDenom1);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemOutputDenom2);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemOutputDenom3);
            holder.itemSendReceiveImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(COSMOS_MAIN) || mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST) ||
                    mBaseChain.equals(BAND_MAIN) || mBaseChain.equals(IOV_MAIN) || mBaseChain.equals(IOV_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
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

            } else if (mBaseChain.equals(IRIS_MAIN)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemInputAddress0.setText(msg.value.inputs.get(0).address);
                holder.itemInputAmount0.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(msg.value.inputs.get(0).coins.get(0).amount), 18, 18));
                holder.itemOutputAddress0.setText(msg.value.outputs.get(0).address);
                holder.itemOutputAmount0.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(msg.value.outputs.get(0).coins.get(0).amount), 18, 18));

            }
        }

        private void onBindDelegate(RecyclerView.ViewHolder viewHolder, int position) {
            final TxDelegateHolder holder = (TxDelegateHolder)viewHolder;
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemDelegateAmountDenom);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemAutoRewardAmountDenom);
            holder.itemDelegateImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(COSMOS_MAIN) || mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST) ||
                    mBaseChain.equals(BAND_MAIN) || mBaseChain.equals(IOV_MAIN) || mBaseChain.equals(IOV_TEST) || mBaseChain.equals(CERTIK_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemDelegator.setText(msg.value.delegator_address);
                holder.itemMoniker.setText(WUtil.getMonikerName(msg.value.validator_address, mAllValidators, true));
                holder.itemValidator.setText(msg.value.validator_address);
                holder.itemDelegateAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(msg.value.getCoins().get(0).amount), 6, 6));
                holder.itemAutoRewardAmount.setText(WDp.getDpAmount2(getBaseContext(), mResTxInfo.simpleAutoReward(mAccount.address, position - 1), 6, 6));
                if (mResTxInfo.getMsgs().size() == 1) {
                    holder.itemAutoRewardLayer.setVisibility(View.VISIBLE);
                }

            } else if (mBaseChain.equals(IRIS_MAIN)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemDelegator.setText(msg.value.delegator_addr);
                holder.itemMoniker.setText(WUtil.getMonikerName(msg.value.validator_addr, mAllValidators, true));
                holder.itemValidator.setText(msg.value.validator_addr);
                holder.itemDelegateAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(msg.value.delegation.amount), 18, 18));

            }
        }

        private void onBindUndelegate(RecyclerView.ViewHolder viewHolder, int position) {
            final TxUndelegateHolder holder = (TxUndelegateHolder)viewHolder;
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemUnDelegateAmountDenom);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemAutoRewardAmountDenom);
            holder.itemUndelegateImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(COSMOS_MAIN) || mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST) ||
                    mBaseChain.equals(BAND_MAIN) || mBaseChain.equals(IOV_MAIN) || mBaseChain.equals(IOV_TEST) || mBaseChain.equals(CERTIK_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemUnDelegator.setText(msg.value.delegator_address);
                holder.itemMoniker.setText(WUtil.getMonikerName(msg.value.validator_address, mAllValidators, true));
                holder.itemValidator.setText(msg.value.validator_address);
                holder.itemUndelegateAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.getCoins().get(0).amount), 6, mBaseChain));
                holder.itemAutoRewardAmount.setText(WDp.getDpAmount(getBaseContext(), mResTxInfo.simpleAutoReward(mAccount.address, position - 1), 6, mBaseChain));
                if (mResTxInfo.getMsgs().size() == 1) {
                    holder.itemAutoRewardLayer.setVisibility(View.VISIBLE);
                }

            } else if (mBaseChain.equals(IRIS_MAIN)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemUnDelegator.setText(msg.value.delegator_addr);
                holder.itemMoniker.setText(WUtil.getMonikerName(msg.value.validator_addr, mAllValidators, true));
                holder.itemValidator.setText(msg.value.validator_addr);
                holder.itemUndelegateAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(msg.value.shares_amount), 18, 18));

            }
        }

        private void onBindRedelegate(RecyclerView.ViewHolder viewHolder, int position) {
            final TxRedelegateHolder holder = (TxRedelegateHolder)viewHolder;
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemReDelegateAmountDenom);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemAutoRewardAmountDenom);
            holder.itemRedelegateImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(COSMOS_MAIN) || mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST) ||
                    mBaseChain.equals(BAND_MAIN) || mBaseChain.equals(IOV_MAIN) || mBaseChain.equals(IOV_TEST) || mBaseChain.equals(CERTIK_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemReDelegator.setText(msg.value.delegator_address);
                holder.itemFromValidator.setText(msg.value.validator_src_address);
                holder.itemFromMoniker.setText(WUtil.getMonikerName(msg.value.validator_src_address, mAllValidators, true));
                holder.itemToValidator.setText(msg.value.validator_dst_address);
                holder.itemToMoniker.setText(WUtil.getMonikerName(msg.value.validator_dst_address, mAllValidators, true));
                holder.itemRedelegateAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(msg.value.getCoins().get(0).amount), 6, mBaseChain));
                holder.itemAutoRewardAmount.setText(WDp.getDpAmount(getBaseContext(), mResTxInfo.simpleAutoReward(mAccount.address, position - 1), 6, mBaseChain));
                if (mResTxInfo.getMsgs().size() == 1) {
                    holder.itemAutoRewardLayer.setVisibility(View.VISIBLE);
                }

            } else if (mBaseChain.equals(IRIS_MAIN)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemReDelegator.setText(msg.value.delegator_addr);
                holder.itemFromValidator.setText(msg.value.validator_src_addr);
                holder.itemFromMoniker.setText(WUtil.getMonikerName(msg.value.validator_src_addr, mAllValidators, true));
                holder.itemToValidator.setText(msg.value.validator_dst_addr);
                holder.itemToMoniker.setText(WUtil.getMonikerName(msg.value.validator_dst_addr, mAllValidators, true));
                holder.itemRedelegateAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(msg.value.shares_amount), 18, 18));

            }
        }

        private void onBindReward(RecyclerView.ViewHolder viewHolder, int position) {
            final TxRewardHolder holder = (TxRewardHolder)viewHolder;
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemRewardAmountDenom);
            holder.itemRewardImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(COSMOS_MAIN) || mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST) ||
                    mBaseChain.equals(BAND_MAIN) || mBaseChain.equals(IOV_MAIN) || mBaseChain.equals(IOV_TEST) || mBaseChain.equals(CERTIK_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemDelegator.setText(msg.value.delegator_address);
                holder.itemMoniker.setText(WUtil.getMonikerName(msg.value.validator_address, mAllValidators, true));
                holder.itemValidator.setText(msg.value.validator_address);
                holder.itemRewardAmount.setText(WDp.getDpAmount(getBaseContext(), mResTxInfo.simpleReward(msg.value.validator_address, position - 1), 6, mBaseChain));

            } else if (mBaseChain.equals(IRIS_MAIN)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemDelegator.setText(msg.value.delegator_addr);
                holder.itemMoniker.setText(WUtil.getMonikerName(msg.value.validator_addr, mAllValidators, true));
                holder.itemValidator.setText(msg.value.validator_addr);
                holder.itemRewardAmount.setText(WDp.getDpAmount2(getBaseContext(), mResTxInfo.simpleRewardIris(), 18, 18));


            }
        }

        private void onBindRewardAll(RecyclerView.ViewHolder viewHolder, int position) {
            final TxRewardAllHolder holder = (TxRewardAllHolder)viewHolder;
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemRewardAllAmountDenom);
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(IRIS_MAIN)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemDelegator.setText(msg.value.delegator_addr);
                holder.itemRewardValidatorCnt.setText( " (" + String.valueOf(mResTxInfo.rewardValidatorsIris().size()) + ")");
                holder.itemRewardValidator0.setText(mResTxInfo.rewardValidatorIris(0));
                holder.itemRewardMoniker0.setText(WUtil.getMonikerName(mResTxInfo.rewardValidatorIris(0), mAllValidators, true));
                if (mResTxInfo.rewardValidatorsIris().size() > 1) {
                    holder.itemRewardValidator1.setVisibility(View.VISIBLE);
                    holder.itemRewardMoniker1.setVisibility(View.VISIBLE);
                    holder.itemRewardValidator1.setText(mResTxInfo.rewardValidatorIris(1));
                    holder.itemRewardMoniker1.setText(WUtil.getMonikerName(mResTxInfo.rewardValidatorIris(1), mAllValidators, true));
                }
                if (mResTxInfo.rewardValidatorsIris().size() > 2) {
                    holder.itemRewardValidator2.setVisibility(View.VISIBLE);
                    holder.itemRewardMoniker2.setVisibility(View.VISIBLE);
                    holder.itemRewardValidator2.setText(mResTxInfo.rewardValidatorIris(2));
                    holder.itemRewardMoniker2.setText(WUtil.getMonikerName(mResTxInfo.rewardValidatorIris(2), mAllValidators, true));
                }
                if (mResTxInfo.rewardValidatorsIris().size() > 3) {
                    holder.itemRewardValidator3.setVisibility(View.VISIBLE);
                    holder.itemRewardMoniker3.setVisibility(View.VISIBLE);
                    holder.itemRewardValidator3.setText(mResTxInfo.rewardValidatorIris(3));
                    holder.itemRewardMoniker3.setText(WUtil.getMonikerName(mResTxInfo.rewardValidatorIris(3), mAllValidators, true));
                }
                if (mResTxInfo.rewardValidatorsIris().size() > 4) {
                    holder.itemRewardValidator4.setVisibility(View.VISIBLE);
                    holder.itemRewardMoniker4.setVisibility(View.VISIBLE);
                    holder.itemRewardValidator4.setText(mResTxInfo.rewardValidatorIris(4));
                    holder.itemRewardMoniker4.setText(WUtil.getMonikerName(mResTxInfo.rewardValidatorIris(4), mAllValidators, true));
                }
                if (mResTxInfo.rewardValidatorsIris().size() > 5) {
                    holder.itemRewardValidator5.setVisibility(View.VISIBLE);
                    holder.itemRewardMoniker5.setVisibility(View.VISIBLE);
                    holder.itemRewardValidator5.setText(mResTxInfo.rewardValidatorIris(5));
                    holder.itemRewardMoniker5.setText(WUtil.getMonikerName(mResTxInfo.rewardValidatorIris(5), mAllValidators, true));
                }
                if (mResTxInfo.rewardValidatorsIris().size() > 6) {
                    holder.itemRewardValidator6.setVisibility(View.VISIBLE);
                    holder.itemRewardMoniker6.setVisibility(View.VISIBLE);
                    holder.itemRewardValidator6.setText(mResTxInfo.rewardValidatorIris(6));
                    holder.itemRewardMoniker6.setText(WUtil.getMonikerName(mResTxInfo.rewardValidatorIris(6), mAllValidators, true));
                }
                if (mResTxInfo.rewardValidatorsIris().size() > 7) {
                    holder.itemRewardValidator7.setVisibility(View.VISIBLE);
                    holder.itemRewardMoniker7.setVisibility(View.VISIBLE);
                    holder.itemRewardValidator7.setText(mResTxInfo.rewardValidatorIris(7));
                    holder.itemRewardMoniker7.setText(WUtil.getMonikerName(mResTxInfo.rewardValidatorIris(7), mAllValidators, true));
                }
                holder.itemRewardAllAmount.setText(WDp.getDpAmount2(getBaseContext(), mResTxInfo.simpleRewardIris(), 18, 18));

            }
        }

        private void onBindAddress(RecyclerView.ViewHolder viewHolder, int position) {
            final TxAddressHolder holder = (TxAddressHolder)viewHolder;
            holder.itemAddressImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(COSMOS_MAIN) || mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST) ||
                    mBaseChain.equals(BAND_MAIN) || mBaseChain.equals(IOV_MAIN) || mBaseChain.equals(IOV_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemDelegator.setText(msg.value.delegator_address);
                holder.itemWithdrawAddress.setText(msg.value.withdraw_address);

            } else if (mBaseChain.equals(IRIS_MAIN)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemDelegator.setText(msg.value.delegator_addr);
                holder.itemWithdrawAddress.setText(msg.value.withdraw_addr);

            }
        }

        private void onBindVote(RecyclerView.ViewHolder viewHolder, int position) {
            final TxVoteHolder holder = (TxVoteHolder)viewHolder;
            holder.itemVoteImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(COSMOS_MAIN) || mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST) ||
                    mBaseChain.equals(BAND_MAIN) || mBaseChain.equals(IOV_MAIN) || mBaseChain.equals(IOV_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemDelegator.setText(msg.value.voter);
                holder.itemProposalId.setText(msg.value.proposal_id);
                holder.itemOpinion.setText(msg.value.option);

            } else if (mBaseChain.equals(IRIS_MAIN)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemDelegator.setText(msg.value.voter);
                holder.itemProposalId.setText(msg.value.proposal_id);
                holder.itemOpinion.setText(msg.value.option);

            }
        }

        private void onBindCommission(RecyclerView.ViewHolder viewHolder, int position) {
            final TxCommissionHolder holder = (TxCommissionHolder)viewHolder;
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemCommissionAmountDenom);
            holder.itemCommissionImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(COSMOS_MAIN) || mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST) ||
                    mBaseChain.equals(BAND_MAIN) || mBaseChain.equals(IOV_MAIN) || mBaseChain.equals(IOV_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemCommissionValidator.setText(msg.value.validator_address);
                holder.itemCommissionValidatorMoniker.setText(WUtil.getMonikerName(msg.value.validator_address, mAllValidators, true));
                holder.itemCommissionAmount.setText(WDp.getDpAmount2(getBaseContext(), mResTxInfo.simpleCommission(position - 1), 6, 6));

            } else if (mBaseChain.equals(IRIS_MAIN)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemCommissionValidator.setText(msg.value.validator_addr);
                holder.itemCommissionValidatorMoniker.setText(WUtil.getMonikerName(msg.value.validator_addr, mAllValidators, true));
                holder.itemCommissionAmount.setText(WDp.getDpAmount2(getBaseContext(), mResTxInfo.simpleCommissionIris(), 18, 18));
            }
        }

        private void onBindPostPrice(RecyclerView.ViewHolder viewHolder, int position) {
            final TxPostPriceHolder holder = (TxPostPriceHolder)viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemPoster.setText(msg.value.from);
                holder.itemMakerId.setText(msg.value.market_id);
                holder.itemPostPrice.setText(msg.value.price);
                holder.itemTime.setText(WDp.getTimeTxformat(getBaseContext(), msg.value.expiry) +  " (" + msg.value.expiry + ")");
            }

        }

        private void onBindCreateCdp(RecyclerView.ViewHolder viewHolder, int position) {
            final TxCreateCdpHolder holder = (TxCreateCdpHolder)viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemSender.setText(msg.value.sender);
                WDp.showCoinDp(getBaseContext(), msg.value.collateral, holder.itemCollateralDenom, holder.itemCollateralAmount, mBaseChain);
                WDp.showCoinDp(getBaseContext(), msg.value.principal, holder.itemPrincipalDenom, holder.itemPrincipalAmount, mBaseChain);

            }

        }

        private void onBindDepositCdp(RecyclerView.ViewHolder viewHolder, int position) {
            final TxDepositCdpHolder holder = (TxDepositCdpHolder)viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemOwner.setText(msg.value.owner);
                holder.itemDepositor.setText(msg.value.depositor);
                WDp.showCoinDp(getBaseContext(), msg.value.collateral, holder.itemCollateralDenom, holder.itemCollateralAmount, mBaseChain);
            }

        }

        private void onBindWithdrawCdp(RecyclerView.ViewHolder viewHolder, int position) {
            final TxWithdrawCdpHolder holder = (TxWithdrawCdpHolder)viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemOwner.setText(msg.value.owner);
                holder.itemDepositor.setText(msg.value.depositor);
                WDp.showCoinDp(getBaseContext(), msg.value.collateral, holder.itemCollateralDenom, holder.itemCollateralAmount, mBaseChain);
            }
        }

        private void onBindDrawDebtCdp(RecyclerView.ViewHolder viewHolder, int position) {
            final TxDrawDebtCdpHolder holder = (TxDrawDebtCdpHolder)viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemSender.setText(msg.value.sender);
                if (!TextUtils.isEmpty(msg.value.cdp_denom)) {
                    holder.itemCdpDenom.setText(msg.value.cdp_denom.toUpperCase());
                } else if (!TextUtils.isEmpty(msg.value.collateral_type)) {
                    holder.itemCdpDenom.setText(msg.value.collateral_type.toUpperCase());
                }
                WDp.showCoinDp(getBaseContext(), msg.value.principal, holder.itemPrincipalDenom, holder.itemPrincipalAmount, mBaseChain);
            }
        }

        private void onBindRepayDebtCdp(RecyclerView.ViewHolder viewHolder, int position) {
            final TxRepayDebtCdpHolder holder = (TxRepayDebtCdpHolder)viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemSender.setText(msg.value.sender);
                if (!TextUtils.isEmpty(msg.value.cdp_denom)) {
                    holder.itemCdpDenom.setText(msg.value.cdp_denom.toUpperCase());
                } else if (!TextUtils.isEmpty(msg.value.collateral_type)) {
                    holder.itemCdpDenom.setText(msg.value.collateral_type.toUpperCase());
                }
                WDp.showCoinDp(getBaseContext(), msg.value.payment, holder.itemPaymentDenom, holder.itemPaymentAmount, mBaseChain);
            }
        }

        private void onBindCreateHTLC(RecyclerView.ViewHolder viewHolder, int position) {
            final TxCreateHtlcHolder holder = (TxCreateHtlcHolder)viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                Coin sendCoin = msg.value.getCoins().get(0);
                WDp.showCoinDp(getBaseContext(), sendCoin, holder.itemSendDenom, holder.itemSendAmount, mBaseChain);
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
                Coin sendCoin = msg.value.getCoins().get(0);
                WDp.showCoinDp(getBaseContext(), sendCoin, holder.itemSendDenom, holder.itemSendAmount, mBaseChain);
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
            final TxClaimHtlcHolder holder = (TxClaimHtlcHolder)viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                Coin receiveCoin = mResTxInfo.simpleSwapCoin();
                try {
                    if (!TextUtils.isEmpty(receiveCoin.denom)) {
                        WDp.showCoinDp(getBaseContext(), receiveCoin, holder.itemReceiveDenom, holder.itemReceiveAmount, mBaseChain);
                    }
                } catch (Exception e) {}

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
            final TxRefundHtlcHolder holder = (TxRefundHtlcHolder)viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                Coin refundCoin = mResTxInfo.simpleRefund();
                try {
                    if (!TextUtils.isEmpty(refundCoin.denom)) {
                        WDp.showCoinDp(getBaseContext(), refundCoin, holder.itemRefundDenom, holder.itemRefundAmount, mBaseChain);
                    }
                } catch (Exception e) { }
                holder.itemFromAddr.setText(msg.value.from);
                holder.itemSwapId.setText(msg.value.swap_id);

            } else if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
                final Msg msg = mResBnbTxInfo.getMsg(position - 1);
                holder.itemAmountLayer.setVisibility(View.GONE);
                holder.itemFromAddr.setText(msg.value.from);
                holder.itemSwapId.setText(msg.value.swap_id);
            }
        }

        private void onBindIncentive(RecyclerView.ViewHolder viewHolder, int position) {
            final TxIncentiveHolder holder = (TxIncentiveHolder)viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemSender.setText(msg.value.sender);
                holder.itemDenom.setText(msg.value.denom.toUpperCase());
                Coin incentiveCoin = mResTxInfo.simpleIncentive(position - 1);
                try {
                    if (!TextUtils.isEmpty(incentiveCoin.denom)) {
                        WDp.showCoinDp(getBaseContext(), incentiveCoin, holder.itemIncentiveDenom, holder.itemIncentiveAmount, mBaseChain);
                    } else {
                        holder.itemIncentiveDenom.setText("");
                        holder.itemIncentiveAmount.setText("");
                    }
                } catch (Exception e) {
                    holder.itemIncentiveDenom.setText("");
                    holder.itemIncentiveAmount.setText("");
                }
            }
        }

        private void onBindOkStake(RecyclerView.ViewHolder viewHolder, int position) {
            final TxOkStakeHolder holder = (TxOkStakeHolder)viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(OK_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                if (msg.type.equals(OK_MSG_TYPE_DEPOSIT)) {
                    holder.itemMsgTitle.setText(R.string.str_deposit);
                    holder.itemMsgImg.setImageDrawable(getResources().getDrawable(R.drawable.deposit_ic));
                } else if (msg.type.equals(OK_MSG_TYPE_WITHDRAW)) {
                    holder.itemMsgTitle.setText(R.string.str_withdraw);
                    holder.itemMsgImg.setImageDrawable(getResources().getDrawable(R.drawable.withdraw_ic));
                }
                holder.itemDeleagtor.setText(msg.value.delegator_address);
                WDp.showCoinDp(getBaseContext(), msg.value.quantity, holder.itemAmountDenom, holder.itemAmount, mBaseChain);
            }
        }

        private void onBindOkDirectVote(RecyclerView.ViewHolder viewHolder, int position) {
            final TxOkVoteHolder holder = (TxOkVoteHolder)viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(OK_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemVoter.setText(msg.value.delegator_address);

                mAllValidators.clear();
                mAllValidators.addAll(getBaseDao().mTopValidators);
                mAllValidators.addAll(getBaseDao().mOtherValidators);

                ArrayList<String> toValAdd = msg.value.validator_addresses;

                String monikers = "";
                for (String valOp:toValAdd) {
                    for (Validator validator:mAllValidators) {
                        if (validator.operator_address.equals(valOp)) {
                            monikers = monikers + validator.description.moniker + "\n";
                        }
                    }
                }
                holder.itemValList.setText(monikers);

//                ArrayList<String> toValAdd = msg.value.validator_addresses;
//                String monikers = "";
//                for (String valOp:toValAdd) {
//                    monikers = monikers + valOp + "\n";
//                }
//                WLog.w("toValAdd " + toValAdd);

            }

        }

        private void onBindRegisterDomain(RecyclerView.ViewHolder viewHolder, int position) {
            final TxRegisterDomainHolder holder = (TxRegisterDomainHolder)viewHolder;
            if (mBaseChain.equals(IOV_MAIN) || mBaseChain.equals(IOV_TEST) ) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemDomain.setText("*" + msg.value.domain);
                holder.itemAdmin.setText( msg.value.admin);
                holder.itemType.setText( msg.value.type);

                BigDecimal starnameFee = getBaseDao().mStarNameFee.getDomainFee(msg.value.domain.trim(), msg.value.type);
                holder.itemStarnameFee.setText(WDp.getDpAmount2(getBaseContext(), starnameFee, 6, 6));
            }

        }

        private void onBindRegisterAccount(RecyclerView.ViewHolder viewHolder, int position) {
            final TxRegisterAccountHolder holder = (TxRegisterAccountHolder)viewHolder;
            if (mBaseChain.equals(IOV_MAIN) || mBaseChain.equals(IOV_TEST) ) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemStarname.setText(msg.value.name + "*" + msg.value.domain);
                holder.itemOwner.setText( msg.value.owner);
                holder.itemRegister.setText( msg.value.registerer);

                BigDecimal starnameFee = getBaseDao().mStarNameFee.getAccountFee(true);
                holder.itemStarnameFee.setText(WDp.getDpAmount2(getBaseContext(), starnameFee, 6, 6));

                ArrayList<StarNameResource> resources = msg.value.resources;
                if (resources != null && resources.size() > 0) {
                    holder.itemResBar.setVisibility(View.VISIBLE);
                    holder.itemResLayer.setVisibility(View.VISIBLE);
                    holder.itemAddressCnt.setText("" + resources.size());
                    for(int i = 0; i < resources.size(); i++) {
                        holder.itemAddessLayer[i].setVisibility(View.VISIBLE);
//                        holder.itemChain[i].setText(resources.get(i).getChainName());
                        holder.itemChain[i].setText(WUtil.getStarNameChainName(resources.get(i)));
                        holder.itemAddess[i].setText(resources.get(i).resource);
//                        holder.itemAddressImg[i].setImageDrawable(resources.get(i).getChainImg(getBaseContext()));
                        holder.itemAddressImg[i].setImageDrawable(WUtil.getStarNameChainImg(getBaseContext(), resources.get(i)));
                    }
                }
            }

        }

        private void onBindDeleteDomain(RecyclerView.ViewHolder viewHolder, int position) {
            final TxDeleteDomainHolder holder = (TxDeleteDomainHolder)viewHolder;
            if (mBaseChain.equals(IOV_MAIN) || mBaseChain.equals(IOV_TEST) ) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemDomain.setText("*" + msg.value.domain);
                holder.itemOwner.setText( msg.value.owner);
            }

        }

        private void onBindDeleteAccount(RecyclerView.ViewHolder viewHolder, int position) {
            final TxDeleteAccountHolder holder = (TxDeleteAccountHolder)viewHolder;
            if (mBaseChain.equals(IOV_MAIN) || mBaseChain.equals(IOV_TEST) ) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemAccount.setText(msg.value.name + "*" + msg.value.domain);
                holder.itemOwner.setText( msg.value.owner);
            }
        }

        private void onBindReplaceResource(RecyclerView.ViewHolder viewHolder, int position) {
            final TxReplaceResourceHolder holder = (TxReplaceResourceHolder)viewHolder;
            if (mBaseChain.equals(IOV_MAIN) || mBaseChain.equals(IOV_TEST) ) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemStarname.setText(msg.value.name + "*" + msg.value.domain);

                BigDecimal starnameFee = getBaseDao().mStarNameFee.getReplaceFee();
                holder.itemStarnameFee.setText(WDp.getDpAmount2(getBaseContext(), starnameFee, 6, 6));

                ArrayList<StarNameResource> resources = msg.value.new_resources;
                if (resources == null || resources.size() == 0) {
                    holder.itemAddressCnt.setText("0");
                } else {
                    holder.itemAddressCnt.setText("" + resources.size());
                    for(int i = 0; i < resources.size(); i++) {
                        holder.itemAddessLayer[i].setVisibility(View.VISIBLE);
                        holder.itemChain[i].setText(WUtil.getStarNameChainName(resources.get(i)));
                        holder.itemAddess[i].setText(resources.get(i).resource);
                        holder.itemAddressImg[i].setImageDrawable(WUtil.getStarNameChainImg(getBaseContext(), resources.get(i)));
                    }
                }
            }

        }


        private void onBindUnKnown(RecyclerView.ViewHolder viewHolder, int position) {
            final TxUnKnownHolder holder = (TxUnKnownHolder)viewHolder;
            holder.itemUnknownImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        }


        public class TxCommonHolder extends RecyclerView.ViewHolder {
            ImageView itemStatusImg;
            TextView itemStatusTxt, itemFailTxt, itemHash, itemHeight, itemMsgCnt, itemGas,
                    itemTime, itemTimeGap, itemMemo, itemFee, itemFeeDenom , itemFeeUsed, itemFeeUsedDenom,
                    itemFeeLimit, itemFeeLimitDenom;
            RelativeLayout itemFeeLayer, itemFeeUsedLayer, itemFeeLimitLayer;
            ImageView itemBtnHashLink;

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
                itemBtnHashLink = itemView.findViewById(R.id.tx_hash_link);

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
            RelativeLayout itemInputLayer0, itemInputLayer1, itemInputLayer2,itemInputLayer3;
            TextView itemInputAddress0, itemInputAddress1, itemInputAddress2, itemInputAddress3;
            TextView itemInputAmount0, itemInputAmount1, itemInputAmount2, itemInputAmount3;
            TextView itemInputDenom0, itemInputDenom1, itemInputDenom2, itemInputDenom3;
            RelativeLayout itemOutputLayer0, itemOutputLayer1, itemOutputLayer2,itemOutputLayer3;
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

        public class TxCreateCdpHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            TextView itemSender, itemCollateralAmount, itemCollateralDenom,
                    itemPrincipalAmount, itemPrincipalDenom;

            public TxCreateCdpHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemSender = itemView.findViewById(R.id.tx_cdp_sender);
                itemCollateralAmount = itemView.findViewById(R.id.tx_collateral_amount);
                itemCollateralDenom = itemView.findViewById(R.id.tx_collateral_symbol);
                itemPrincipalAmount = itemView.findViewById(R.id.tx_principal_amount);
                itemPrincipalDenom = itemView.findViewById(R.id.tx_principal_symbol);
            }
        }

        public class TxDepositCdpHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            TextView itemOwner, itemDepositor, itemCollateralAmount, itemCollateralDenom;

            public TxDepositCdpHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemOwner = itemView.findViewById(R.id.tx_cdp_owner);
                itemDepositor = itemView.findViewById(R.id.tx_cdp_depositor);
                itemCollateralAmount = itemView.findViewById(R.id.tx_collateral_amount);
                itemCollateralDenom = itemView.findViewById(R.id.tx_collateral_symbol);
            }
        }

        public class TxWithdrawCdpHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            TextView itemOwner, itemDepositor, itemCollateralAmount, itemCollateralDenom;

            public TxWithdrawCdpHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemOwner = itemView.findViewById(R.id.tx_cdp_owner);
                itemDepositor = itemView.findViewById(R.id.tx_cdp_depositor);
                itemCollateralAmount = itemView.findViewById(R.id.tx_collateral_amount);
                itemCollateralDenom = itemView.findViewById(R.id.tx_collateral_symbol);
            }

        }

        public class TxDrawDebtCdpHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            TextView itemSender, itemCdpDenom, itemPrincipalAmount, itemPrincipalDenom;

            public TxDrawDebtCdpHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemSender = itemView.findViewById(R.id.tx_cdp_sender);
                itemCdpDenom = itemView.findViewById(R.id.tx_cdp_denom);
                itemPrincipalAmount = itemView.findViewById(R.id.tx_principal_amount);
                itemPrincipalDenom = itemView.findViewById(R.id.tx_principal_symbol);
            }

        }

        public class TxRepayDebtCdpHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            TextView itemSender, itemCdpDenom, itemPaymentAmount, itemPaymentDenom;

            public TxRepayDebtCdpHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemSender = itemView.findViewById(R.id.tx_cdp_sender);
                itemCdpDenom = itemView.findViewById(R.id.tx_cdp_denom);
                itemPaymentAmount = itemView.findViewById(R.id.tx_payment_amount);
                itemPaymentDenom = itemView.findViewById(R.id.tx_payment_symbol);
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

        public class TxIncentiveHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            TextView itemIncentiveAmount, itemIncentiveDenom, itemSender, itemDenom;

            public TxIncentiveHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemIncentiveAmount = itemView.findViewById(R.id.tx_incentive_amount);
                itemIncentiveDenom = itemView.findViewById(R.id.tx_incentive_symbol);
                itemSender = itemView.findViewById(R.id.tx_incentive_sender);
                itemDenom = itemView.findViewById(R.id.tx_incentive_denom);
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


        public class TxRegisterDomainHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            TextView itemDomain, itemAdmin, itemType, itemStarnameFee;

            public TxRegisterDomainHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemDomain = itemView.findViewById(R.id.tx_domain);
                itemAdmin = itemView.findViewById(R.id.tx_admin_address);
                itemType = itemView.findViewById(R.id.tx_domain_type);
                itemStarnameFee = itemView.findViewById(R.id.tx_starname_fee_amount);
            }

        }

        public class TxRegisterAccountHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            TextView itemStarname, itemOwner, itemRegister, itemStarnameFee, itemAddressCnt;
            View itemResBar;
            LinearLayout itemResLayer;
            LinearLayout[] itemAddessLayer = new LinearLayout[11];
            ImageView[] itemAddressImg = new ImageView[11];
            TextView[] itemChain = new TextView[11];
            TextView[] itemAddess = new TextView[11];

            public TxRegisterAccountHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemStarname = itemView.findViewById(R.id.tx_starname);
                itemOwner = itemView.findViewById(R.id.tx_owner);
                itemRegister = itemView.findViewById(R.id.tx_register);
                itemStarnameFee = itemView.findViewById(R.id.tx_starname_fee_amount);
                itemResBar = itemView.findViewById(R.id.tx_resource_bar);
                itemResLayer = itemView.findViewById(R.id.tx_resource_layer);
                itemAddressCnt = itemView.findViewById(R.id.tx_address_cnt);
                for(int i = 0; i < itemAddessLayer.length; i++) {
                    itemAddessLayer[i] = itemView.findViewById(getResources().getIdentifier("tx_resource_layer_" + i , "id", getPackageName()));
                    itemAddressImg[i] = itemView.findViewById(getResources().getIdentifier("tx_resource_icon_" + i , "id", getPackageName()));
                    itemChain[i] = itemView.findViewById(getResources().getIdentifier("tx_resource_chain_" + i , "id", getPackageName()));
                    itemAddess[i] = itemView.findViewById(getResources().getIdentifier("tx_resource_address_" + i , "id", getPackageName()));
                }
            }
        }

        public class TxDeleteDomainHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            TextView itemDomain, itemOwner;

            public TxDeleteDomainHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemDomain = itemView.findViewById(R.id.tx_domain);
                itemOwner = itemView.findViewById(R.id.tx_owner);
            }
        }

        public class TxDeleteAccountHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            TextView itemAccount, itemOwner;


            public TxDeleteAccountHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemAccount = itemView.findViewById(R.id.tx_account);
                itemOwner = itemView.findViewById(R.id.tx_owner);
            }
        }

        public class TxReplaceResourceHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            TextView itemStarname, itemStarnameFee, itemAddressCnt;
            LinearLayout[] itemAddessLayer = new LinearLayout[11];
            ImageView[] itemAddressImg = new ImageView[11];
            TextView[] itemChain = new TextView[11];
            TextView[] itemAddess = new TextView[11];

            public TxReplaceResourceHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemStarname = itemView.findViewById(R.id.tx_starname);
                itemStarnameFee = itemView.findViewById(R.id.tx_starname_fee_amount);
                itemAddressCnt = itemView.findViewById(R.id.tx_address_cnt);
                for(int i = 0; i < itemAddessLayer.length; i++) {
                    itemAddessLayer[i] = itemView.findViewById(getResources().getIdentifier("tx_resource_layers_" + i , "id", getPackageName()));
                    itemAddressImg[i] = itemView.findViewById(getResources().getIdentifier("tx_resource_icons_" + i , "id", getPackageName()));
                    itemChain[i] = itemView.findViewById(getResources().getIdentifier("tx_resource_chains_" + i , "id", getPackageName()));
                    itemAddess[i] = itemView.findViewById(getResources().getIdentifier("tx_resource_addresses_" + i , "id", getPackageName()));
                }
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
        getSupportFragmentManager().beginTransaction().add(waitMore, "dialog").commitNowAllowingStateLoss();
    }

    public void onWaitMore() {
        FetchCnt = 0;
        onFetchTx(mTxHash);
    }

    private int FetchCnt = 0;
    private void onFetchTx(String hash) {
        WLog.w("hash "+ hash);
        if (mBaseChain.equals(COSMOS_MAIN)) {
            ApiClient.getCosmosChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
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
                    if (IS_SHOWLOG) t.printStackTrace();
                    if (isFinishing()) return;
                }
            });

        } else if (mBaseChain.equals(IRIS_MAIN)) {
            ApiClient.getIrisChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
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
                    if (IS_SHOWLOG) t.printStackTrace();
                    if (isFinishing()) return;
                }
            });

        } else if (mBaseChain.equals(BNB_MAIN)) {
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
                    if (IS_SHOWLOG) t.printStackTrace();
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
                    if (IS_SHOWLOG) t.printStackTrace();
                    if (isFinishing()) return;
                }
            });

        } else if (mBaseChain.equals(KAVA_MAIN)) {
            ApiClient.getKavaChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
                @Override
                public void onResponse(Call<ResTxInfo> call, Response<ResTxInfo> response) {
                    if (isFinishing()) return;
                    WLog.w("onFetchTx " + response.toString());
                    if (response.isSuccessful() && response.body() != null) {
                        mResTxInfo = response.body();
                        if (mResTxInfo.getMsgType(0).equals(KAVA_MSG_TYPE_BEP3_CREATE_SWAP)) {
                            onFetchHtlcStatus(mResTxInfo.simpleSwapId());
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
                public void onFailure(Call<ResTxInfo> call, Throwable t) {
                    if (IS_SHOWLOG) t.printStackTrace();
                    if (isFinishing()) return;
                }
            });

        } else if (mBaseChain.equals(KAVA_TEST)) {
            ApiClient.getKavaTestChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
                @Override
                public void onResponse(Call<ResTxInfo> call, Response<ResTxInfo> response) {
                    if (isFinishing()) return;
                    WLog.w("onFetchTx " + response.toString());
                    if (response.isSuccessful() && response.body() != null) {
                        mResTxInfo = response.body();
                        if (mResTxInfo.getMsgType(0).equals(KAVA_MSG_TYPE_BEP3_CREATE_SWAP)) {
                            onFetchHtlcStatus(mResTxInfo.simpleSwapId());
                        } else {
                            onUpdateView();
                        }

                    } else {
                        if(mIsSuccess && FetchCnt < 10) {
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
                    if (IS_SHOWLOG) t.printStackTrace();
                    if (isFinishing()) return;
                }
            });

        } else if (mBaseChain.equals(BAND_MAIN)) {
            ApiClient.getBandChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
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
                    if (IS_SHOWLOG) t.printStackTrace();
                    if (isFinishing()) return;
                }
            });

        } else if (mBaseChain.equals(IOV_MAIN)) {
            ApiClient.getIovChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
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
                    if (IS_SHOWLOG) t.printStackTrace();
                    if (isFinishing()) return;
                }
            });

        } else if (mBaseChain.equals(IOV_TEST)) {
            ApiClient.getIovTestChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
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
                    if (IS_SHOWLOG) t.printStackTrace();
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
                    if (IS_SHOWLOG) t.printStackTrace();
                    if (isFinishing()) return;
                }
            });

        } else if (mBaseChain.equals(CERTIK_TEST)) {
            ApiClient.getCertikTestChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
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
                    if (IS_SHOWLOG) t.printStackTrace();
                    if (isFinishing()) return;
                }
            });

        }


    }


    private void onFetchHtlcStatus(String swapId) {
//        WLog.w("onFetchHtlcStatus "  +swapId);
        if (!TextUtils.isEmpty(swapId)) {
            if (mBaseChain.equals(KAVA_MAIN)) {
                ApiClient.getKavaChain(getBaseContext()).getSwapById(swapId).enqueue(new Callback<ResKavaSwapInfo>() {
                    @Override
                    public void onResponse(Call<ResKavaSwapInfo> call, Response<ResKavaSwapInfo> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            mResKavaSwapInfo = response.body();
                        }
                        onUpdateView();
                    }

                    @Override
                    public void onFailure(Call<ResKavaSwapInfo> call, Throwable t) {
                        WLog.w("onFetchHtlcStatus " + t.getMessage());
                        onUpdateView();
                    }
                });

            } else if (mBaseChain.equals(KAVA_TEST)) {
                ApiClient.getKavaTestChain(getBaseContext()).getSwapById(swapId).enqueue(new Callback<ResKavaSwapInfo>() {
                    @Override
                    public void onResponse(Call<ResKavaSwapInfo> call, Response<ResKavaSwapInfo> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            mResKavaSwapInfo = response.body();
                        }
                        onUpdateView();
                    }

                    @Override
                    public void onFailure(Call<ResKavaSwapInfo> call, Throwable t) {
                        WLog.w("onFetchHtlcStatus " + t.getMessage());
                        onUpdateView();
                    }
                });

            } else if (mBaseChain.equals(BNB_MAIN)) {
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
            ApiClient.getBnbChain(getBaseContext()).getNodeInfo().enqueue(new Callback<ResBnbNodeInfo>() {
                @Override
                public void onResponse(Call<ResBnbNodeInfo> call, Response<ResBnbNodeInfo> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        mResBnbNodeInfo = response.body();
                    }
                    onUpdateView();
                }

                @Override
                public void onFailure(Call<ResBnbNodeInfo> call, Throwable t) {
                    WLog.w("onFetchBnbNodeInfo " + t.getMessage());
                    onUpdateView();
                }
            });

        } else if (mBaseChain.equals(BNB_TEST)) {
            ApiClient.getBnbTestChain(getBaseContext()).getNodeInfo().enqueue(new Callback<ResBnbNodeInfo>() {
                @Override
                public void onResponse(Call<ResBnbNodeInfo> call, Response<ResBnbNodeInfo> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        mResBnbNodeInfo = response.body();
                    }
                    onUpdateView();
                }

                @Override
                public void onFailure(Call<ResBnbNodeInfo> call, Throwable t) {
                    WLog.w("onFetchBnbNodeInfo " + t.getMessage());
                    onUpdateView();
                }
            });
        }
    }
}
