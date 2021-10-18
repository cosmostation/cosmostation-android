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
import wannabit.io.cosmostaion.widget.txDetail.TxCdpLiquidate;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxDelegatorIncentive;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxHardPoolIncentive;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxHardPoolLiquidate;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxSwapDeposit;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxSwapIncentive;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxSwapToken;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxSwapWithdraw;

import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
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
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_UNJAIL;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_DEL;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_MIDIFY;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_VAL;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_BROADCAST;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_UNKNOWN;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_COMMISSION;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_DELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_REDELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_UNDELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_WITHDRAW;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_WITHDRAW_ALL;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_MSG_TYPE_WITHDRAW_MIDIFY;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_BEP3_CLAM_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_BEP3_CREATE_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_BEP3_REFUND_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_BORROW_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_CLAIM_HAVEST;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_CREATE_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_DELEGATOR_INCENTIVE;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_DEPOSIT;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_DEPOSIT_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_DEPOSIT_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_DEPOSIT_HAVEST;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_DRAWDEBT_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_INCENTIVE_REWARD;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_LIQUIDATE_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_LIQUIDATE_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_POST_PRICE;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_REPAYDEBT_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_REPAY_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_SWAP_INCENTIVE;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_SWAP_TOKEN;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_SWAP_TOKEN2;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_USDX_MINT_INCENTIVE;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_WITHDRAW;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_WITHDRAW_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_WITHDRAW_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_WITHDRAW_HAVEST;
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
        private static final int TYPE_TX_DELEGATE = 2;
        private static final int TYPE_TX_UNDELEGATE = 3;
        private static final int TYPE_TX_REDELEGATE = 4;
        private static final int TYPE_TX_REWARD = 5;
        private static final int TYPE_TX_ADDRESS_CHANGE = 6;
        private static final int TYPE_TX_VOTE = 7;
        private static final int TYPE_TX_COMMISSION = 8;
        private static final int TYPE_TX_MULTI_SEND = 9;
        private static final int TYPE_TX_UNJAIL = 10;

        // kava msg types
        private static final int TYPE_TX_POST_PRICE = 200;
        private static final int TYPE_TX_CREATE_CDP = 202;
        private static final int TYPE_TX_CDP_DEPOSIT = 203;
        private static final int TYPE_TX_CDP_WITHDRAW = 204;
        private static final int TYPE_TX_CDP_BORROW = 205;
        private static final int TYPE_TX_CDP_REPAY = 206;
        private static final int TYPE_TX_CDP_LIQUIDATE = 207;
        private static final int TYPE_TX_HTLC_CREATE = 208;
        private static final int TYPE_TX_HTLC_CLAIM = 209;
        private static final int TYPE_TX_HTLC_REFUND = 210;
        private static final int TYPE_TX_HARD_DEPOSIT = 211;
        private static final int TYPE_TX_HARD_WITHDRAW = 212;
        //        private static final int TYPE_TX_HARD_CLIAM         = 213;
        private static final int TYPE_TX_HARD_BORROW = 214;
        private static final int TYPE_TX_HARD_REPAY = 215;
        private static final int TYPE_TX_HARD_LIQUIDATE = 216;
        private static final int TYPE_TX_INCENTIVE_MINT = 217;
        private static final int TYPE_TX_INCENTIVE_HARD = 218;
        private static final int TYPE_TX_INCENTIVE_SWAP = 219;
        private static final int TYPE_TX_INCENTIVE_DELEGATOR = 220;
        private static final int TYPE_TX_SWAP_TOKEN = 221;
        private static final int TYPE_TX_SWAP_DEPOSIT = 222;
        private static final int TYPE_TX_SWAP_WITHDRAW = 223;


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
            } else if (viewType == TYPE_TX_REWARD_ALL) {
                return new TxRewardAllHolder(getLayoutInflater().inflate(R.layout.item_tx_reward_all, viewGroup, false));
            } else if (viewType == TYPE_TX_UNJAIL) {
                return new TxUnjailHolder(getLayoutInflater().inflate(R.layout.item_tx_unjail, viewGroup, false));
            } else if (viewType == TYPE_TX_POST_PRICE) {
                return new TxPostPriceHolder(getLayoutInflater().inflate(R.layout.item_tx_post_price, viewGroup, false));
            } else if (viewType == TYPE_TX_CREATE_CDP) {
                return new TxCreateCdpHolder(getLayoutInflater().inflate(R.layout.item_tx_create_cdp, viewGroup, false));
            } else if (viewType == TYPE_TX_CDP_DEPOSIT) {
                return new TxDepositCdpHolder(getLayoutInflater().inflate(R.layout.item_tx_deposit_cdp, viewGroup, false));
            } else if (viewType == TYPE_TX_CDP_WITHDRAW) {
                return new TxWithdrawCdpHolder(getLayoutInflater().inflate(R.layout.item_tx_withdraw_cdp, viewGroup, false));
            } else if (viewType == TYPE_TX_CDP_BORROW) {
                return new TxDrawDebtCdpHolder(getLayoutInflater().inflate(R.layout.item_tx_drawdebt_cdp, viewGroup, false));
            } else if (viewType == TYPE_TX_CDP_REPAY) {
                return new TxRepayDebtCdpHolder(getLayoutInflater().inflate(R.layout.item_tx_repaydebt_cdp, viewGroup, false));
            } else if (viewType == TYPE_TX_CDP_LIQUIDATE) {
                return new TxCdpLiquidate(getLayoutInflater().inflate(R.layout.item_tx_liquidate_cdp, viewGroup, false));
            } else if (viewType == TYPE_TX_HARD_DEPOSIT) {
                return new TxHardPoolDepositHolder(getLayoutInflater().inflate(R.layout.item_tx_deposit_harvest, viewGroup, false));
            } else if (viewType == TYPE_TX_HARD_WITHDRAW) {
                return new TxHardPoolWithdrawHolder(getLayoutInflater().inflate(R.layout.item_tx_withdraw_harvest, viewGroup, false));
            } else if (viewType == TYPE_TX_HARD_BORROW) {
                return new TxHardPoolBorrowHolder(getLayoutInflater().inflate(R.layout.item_tx_borrow_hard, viewGroup, false));
            } else if (viewType == TYPE_TX_HARD_REPAY) {
                return new TxHardPoolRepaywHolder(getLayoutInflater().inflate(R.layout.item_tx_repay_hard, viewGroup, false));
            } else if (viewType == TYPE_TX_HARD_LIQUIDATE) {
                return new TxHardPoolLiquidate(getLayoutInflater().inflate(R.layout.item_tx_liquidate_hard, viewGroup, false));
            } else if (viewType == TYPE_TX_INCENTIVE_MINT) {
                return new TxMintingIncentiveHolder(getLayoutInflater().inflate(R.layout.item_tx_incentive_reward, viewGroup, false));
            } else if (viewType == TYPE_TX_INCENTIVE_HARD) {
                return new TxHardPoolIncentive(getLayoutInflater().inflate(R.layout.item_tx_incentive_hard, viewGroup, false));
            } else if (viewType == TYPE_TX_INCENTIVE_SWAP) {
                return new TxSwapIncentive(getLayoutInflater().inflate(R.layout.item_tx_kava_swap_incentive, viewGroup, false));
            } else if (viewType == TYPE_TX_INCENTIVE_DELEGATOR) {
                return new TxDelegatorIncentive(getLayoutInflater().inflate(R.layout.item_tx_kava_delegator_incentive, viewGroup, false));
            } else if (viewType == TYPE_TX_SWAP_TOKEN) {
                return new TxSwapToken(getLayoutInflater().inflate(R.layout.item_tx_kava_swap_token, viewGroup, false));
            } else if (viewType == TYPE_TX_SWAP_DEPOSIT) {
                return new TxSwapDeposit(getLayoutInflater().inflate(R.layout.item_tx_kava_swap_deposit, viewGroup, false));
            } else if (viewType == TYPE_TX_SWAP_WITHDRAW) {
                return new TxSwapWithdraw(getLayoutInflater().inflate(R.layout.item_tx_kava_swap_withdraw, viewGroup, false));
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
            } else if (getItemViewType(position) == TYPE_TX_REWARD_ALL) {
                onBindRewardAll(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_UNJAIL) {
                onBindUnjail(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_POST_PRICE) {
                onBindPostPrice(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_CREATE_CDP) {
                onBindCdpCreate(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_CDP_DEPOSIT) {
                onBindCdpDeposit(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_CDP_WITHDRAW) {
                onBindCdpWithdraw(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_CDP_BORROW) {
                onBindCdpBorrow(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_CDP_REPAY) {
                onBindCdpRepay(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_CDP_LIQUIDATE) {
                onBindCdpLiquidate(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_HTLC_CREATE) {
                onBindCreateHTLC(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_HTLC_CLAIM) {
                onBindClaimHTLC(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_HTLC_REFUND) {
                onBindRefundHTLC(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_HARD_DEPOSIT) {
                onBindHardDeposit(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_HARD_WITHDRAW) {
                onBindHardWithdraw(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_HARD_BORROW) {
                onBindHardBorrow(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_HARD_REPAY) {
                onBindHardRepay(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_HARD_LIQUIDATE) {
                onBindHardLiquidate(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_INCENTIVE_MINT) {
                onBindIncentiveMint(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_INCENTIVE_HARD) {
                onBindIncentiveHard(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_INCENTIVE_SWAP) {
                onBindIncentiveSwap(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_INCENTIVE_DELEGATOR) {
                onBindIncentiveDelegator(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_SWAP_TOKEN) {
                onBindSwapToken(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_SWAP_DEPOSIT) {
                onBindSwapDeposit(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_TX_SWAP_WITHDRAW) {
                onBindSwapWithdraw(viewHolder, position);
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

                    } else if (mResTxInfo.getMsgType(position - 1).equals(COSMOS_MSG_TYPE_DELEGATE) ||
                            mResTxInfo.getMsgType(position - 1).equals(IRIS_MSG_TYPE_DELEGATE)) {
                        return TYPE_TX_DELEGATE;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(COSMOS_MSG_TYPE_UNDELEGATE2) ||
                            mResTxInfo.getMsgType(position - 1).equals(IRIS_MSG_TYPE_UNDELEGATE)) {
                        return TYPE_TX_UNDELEGATE;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(COSMOS_MSG_TYPE_REDELEGATE2) ||
                            mResTxInfo.getMsgType(position - 1).equals(IRIS_MSG_TYPE_REDELEGATE)) {
                        return TYPE_TX_REDELEGATE;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(COSMOS_MSG_TYPE_WITHDRAW_DEL) ||
                            mResTxInfo.getMsgType(position - 1).equals(IRIS_MSG_TYPE_WITHDRAW)) {
                        return TYPE_TX_REWARD;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) ||
                            mResTxInfo.getMsgType(position - 1).equals(IRIS_MSG_TYPE_WITHDRAW_MIDIFY)) {
                        return TYPE_TX_ADDRESS_CHANGE;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(COSMOS_MSG_TYPE_VOTE) ||
                            mResTxInfo.getMsgType(position - 1).equals(IRIS_MSG_TYPE_VOTE)) {
                        return TYPE_TX_VOTE;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(COSMOS_MSG_TYPE_WITHDRAW_VAL) ||
                            mResTxInfo.getMsgType(position - 1).equals(IRIS_MSG_TYPE_COMMISSION)) {
                        return TYPE_TX_COMMISSION;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(IRIS_MSG_TYPE_WITHDRAW_ALL)) {
                        return TYPE_TX_REWARD_ALL;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(COSMOS_MSG_TYPE_UNJAIL)) {
                        return TYPE_TX_UNJAIL;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_POST_PRICE)) {
                        return TYPE_TX_POST_PRICE;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_CREATE_CDP)) {
                        return TYPE_TX_CREATE_CDP;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_DEPOSIT_CDP)) {
                        return TYPE_TX_CDP_DEPOSIT;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_WITHDRAW_CDP)) {
                        return TYPE_TX_CDP_WITHDRAW;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_DRAWDEBT_CDP)) {
                        return TYPE_TX_CDP_BORROW;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_REPAYDEBT_CDP)) {
                        return TYPE_TX_CDP_REPAY;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_LIQUIDATE_CDP)) {
                        return TYPE_TX_CDP_LIQUIDATE;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_DEPOSIT_HAVEST) || mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_DEPOSIT_HARD)) {
                        return TYPE_TX_HARD_DEPOSIT;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_WITHDRAW_HAVEST) || mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_WITHDRAW_HARD)) {
                        return TYPE_TX_HARD_WITHDRAW;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_BORROW_HARD)) {
                        return TYPE_TX_HARD_BORROW;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_REPAY_HARD)) {
                        return TYPE_TX_HARD_REPAY;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_LIQUIDATE_HARD)) {
                        return TYPE_TX_HARD_LIQUIDATE;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_INCENTIVE_REWARD) || mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_USDX_MINT_INCENTIVE)) {
                        return TYPE_TX_INCENTIVE_MINT;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_CLAIM_HAVEST) || mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE)) {
                        return TYPE_TX_INCENTIVE_HARD;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_SWAP_INCENTIVE)) {
                        return TYPE_TX_INCENTIVE_SWAP;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_DELEGATOR_INCENTIVE)) {
                        return TYPE_TX_INCENTIVE_DELEGATOR;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_SWAP_TOKEN) || mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_SWAP_TOKEN2)) {
                        return TYPE_TX_SWAP_TOKEN;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_DEPOSIT)) {
                        return TYPE_TX_SWAP_DEPOSIT;

                    } else if (mResTxInfo.getMsgType(position - 1).equals(KAVA_MSG_TYPE_WITHDRAW)) {
                        return TYPE_TX_SWAP_WITHDRAW;

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
                WDp.showCoinDp(getBaseContext(), toDpCoin.get(0), holder.itemAmountDenom, holder.itemAmount, mBaseChain);

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

        private void onBindDelegate(RecyclerView.ViewHolder viewHolder, int position) {
            final TxDelegateHolder holder = (TxDelegateHolder) viewHolder;
            final int dpDecimal = WDp.mainDivideDecimal(mBaseChain);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemDelegateAmountDenom);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemAutoRewardAmountDenom);
            holder.itemDelegateImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            final Msg msg = mResTxInfo.getMsg(position - 1);
            holder.itemDelegator.setText(msg.value.delegator_address);
            holder.itemMoniker.setText(WUtil.getMonikerName(msg.value.validator_address, getBaseDao().mAllValidators, true));
            holder.itemValidator.setText(msg.value.validator_address);
            holder.itemDelegateAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(WDp.getCoins(msg.value.amount).get(0).amount), dpDecimal, dpDecimal));
            holder.itemAutoRewardAmount.setText(WDp.getDpAmount2(getBaseContext(), mResTxInfo.simpleAutoReward(mAccount.address, position - 1), dpDecimal, dpDecimal));
            if (mResTxInfo.getMsgs().size() == 1) {
                holder.itemAutoRewardLayer.setVisibility(View.VISIBLE);
            }
        }

        private void onBindUndelegate(RecyclerView.ViewHolder viewHolder, int position) {
            final TxUndelegateHolder holder = (TxUndelegateHolder) viewHolder;
            final int dpDecimal = WDp.mainDivideDecimal(mBaseChain);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemUnDelegateAmountDenom);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemAutoRewardAmountDenom);
            holder.itemUndelegateImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            final Msg msg = mResTxInfo.getMsg(position - 1);
            holder.itemUnDelegator.setText(msg.value.delegator_address);
            holder.itemMoniker.setText(WUtil.getMonikerName(msg.value.validator_address, getBaseDao().mAllValidators, true));
            holder.itemValidator.setText(msg.value.validator_address);
            holder.itemUndelegateAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(WDp.getCoins(msg.value.amount).get(0).amount), dpDecimal, dpDecimal));
            holder.itemAutoRewardAmount.setText(WDp.getDpAmount2(getBaseContext(), mResTxInfo.simpleAutoReward(mAccount.address, position - 1), dpDecimal, dpDecimal));
            if (mResTxInfo.getMsgs().size() == 1) {
                holder.itemAutoRewardLayer.setVisibility(View.VISIBLE);
            }
        }

        private void onBindRedelegate(RecyclerView.ViewHolder viewHolder, int position) {
            final TxRedelegateHolder holder = (TxRedelegateHolder) viewHolder;
            final int dpDecimal = WDp.mainDivideDecimal(mBaseChain);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemReDelegateAmountDenom);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemAutoRewardAmountDenom);
            holder.itemRedelegateImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            final Msg msg = mResTxInfo.getMsg(position - 1);
            holder.itemReDelegator.setText(msg.value.delegator_address);
            holder.itemFromValidator.setText(msg.value.validator_src_address);
            holder.itemFromMoniker.setText(WUtil.getMonikerName(msg.value.validator_src_address, getBaseDao().mAllValidators, true));
            holder.itemToValidator.setText(msg.value.validator_dst_address);
            holder.itemToMoniker.setText(WUtil.getMonikerName(msg.value.validator_dst_address, getBaseDao().mAllValidators, true));
            holder.itemRedelegateAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(WDp.getCoins(msg.value.amount).get(0).amount), dpDecimal, dpDecimal));
            holder.itemAutoRewardAmount.setText(WDp.getDpAmount2(getBaseContext(), mResTxInfo.simpleAutoReward(mAccount.address, position - 1), dpDecimal, dpDecimal));
            if (mResTxInfo.getMsgs().size() == 1) {
                holder.itemAutoRewardLayer.setVisibility(View.VISIBLE);
            }
        }

        private void onBindReward(RecyclerView.ViewHolder viewHolder, int position) {
            final TxRewardHolder holder = (TxRewardHolder) viewHolder;
            final int dpDecimal = WDp.mainDivideDecimal(mBaseChain);
            WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), holder.itemRewardAmountDenom);
            holder.itemRewardImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            final Msg msg = mResTxInfo.getMsg(position - 1);
            holder.itemDelegator.setText(msg.value.delegator_address);
            holder.itemMoniker.setText(WUtil.getMonikerName(msg.value.validator_address, getBaseDao().mAllValidators, true));
            holder.itemValidator.setText(msg.value.validator_address);
            holder.itemRewardAmount.setText(WDp.getDpAmount2(getBaseContext(), mResTxInfo.simpleReward(msg.value.validator_address, position - 1), dpDecimal, dpDecimal));
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

        private void onBindAddress(RecyclerView.ViewHolder viewHolder, int position) {
            final TxAddressHolder holder = (TxAddressHolder) viewHolder;
            holder.itemAddressImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            final Msg msg = mResTxInfo.getMsg(position - 1);
            holder.itemDelegator.setText(msg.value.delegator_address);
            holder.itemWithdrawAddress.setText(msg.value.withdraw_address);
        }

        private void onBindVote(RecyclerView.ViewHolder viewHolder, int position) {
            final TxVoteHolder holder = (TxVoteHolder) viewHolder;
            holder.itemVoteImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            final Msg msg = mResTxInfo.getMsg(position - 1);
            holder.itemDelegator.setText(msg.value.voter);
            holder.itemProposalId.setText(msg.value.proposal_id);
            holder.itemOpinion.setText(msg.value.getOption());
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

        private void onBindCdpCreate(RecyclerView.ViewHolder viewHolder, int position) {
            final TxCreateCdpHolder holder = (TxCreateCdpHolder) viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemSender.setText(msg.value.sender);
                WDp.showCoinDp(getBaseContext(), msg.value.collateral, holder.itemCollateralDenom, holder.itemCollateralAmount, mBaseChain);
                WDp.showCoinDp(getBaseContext(), msg.value.principal, holder.itemPrincipalDenom, holder.itemPrincipalAmount, mBaseChain);

            }

        }

        private void onBindCdpDeposit(RecyclerView.ViewHolder viewHolder, int position) {
            final TxDepositCdpHolder holder = (TxDepositCdpHolder) viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemOwner.setText(msg.value.owner);
                holder.itemDepositor.setText(msg.value.depositor);
                WDp.showCoinDp(getBaseContext(), msg.value.collateral, holder.itemCollateralDenom, holder.itemCollateralAmount, mBaseChain);
            }

        }

        private void onBindCdpWithdraw(RecyclerView.ViewHolder viewHolder, int position) {
            final TxWithdrawCdpHolder holder = (TxWithdrawCdpHolder) viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemOwner.setText(msg.value.owner);
                holder.itemDepositor.setText(msg.value.depositor);
                WDp.showCoinDp(getBaseContext(), msg.value.collateral, holder.itemCollateralDenom, holder.itemCollateralAmount, mBaseChain);
            }
        }

        private void onBindCdpBorrow(RecyclerView.ViewHolder viewHolder, int position) {
            final TxDrawDebtCdpHolder holder = (TxDrawDebtCdpHolder) viewHolder;
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

        private void onBindCdpRepay(RecyclerView.ViewHolder viewHolder, int position) {
            final TxRepayDebtCdpHolder holder = (TxRepayDebtCdpHolder) viewHolder;
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

        private void onBindCdpLiquidate(RecyclerView.ViewHolder viewHolder, int position) {
            final TxCdpLiquidate holder = (TxCdpLiquidate) viewHolder;
            holder.onBind(getBaseContext(), mBaseChain, mResTxInfo.getMsg(position - 1));
        }

        private void onBindHardDeposit(RecyclerView.ViewHolder viewHolder, int position) {
            final TxHardPoolDepositHolder holder = (TxHardPoolDepositHolder) viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            final Msg msg = mResTxInfo.getMsg(position - 1);
            holder.itemDepositor.setText(msg.value.depositor);
            holder.itemDepositType.setText(msg.value.deposit_type);
            WDp.showCoinDp(getBaseContext(), WDp.getCoins(msg.value.amount).get(0), holder.itemDepositAmountDenom, holder.itemDepositAmount, mBaseChain);
        }

        private void onBindHardWithdraw(RecyclerView.ViewHolder viewHolder, int position) {
            final TxHardPoolWithdrawHolder holder = (TxHardPoolWithdrawHolder) viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            final Msg msg = mResTxInfo.getMsg(position - 1);
            holder.itemDepositor.setText(msg.value.depositor);
            holder.itemDepositType.setText(msg.value.deposit_type);
            WDp.showCoinDp(getBaseContext(), WDp.getCoins(msg.value.amount).get(0), holder.itemWithdrawAmountDenom, holder.itemWithdrawAmount, mBaseChain);
        }

        private void onBindHardBorrow(RecyclerView.ViewHolder viewHolder, int position) {
            final TxHardPoolBorrowHolder holder = (TxHardPoolBorrowHolder) viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            final Msg msg = mResTxInfo.getMsg(position - 1);
            holder.itemBorrower.setText(msg.value.borrower);
            ArrayList<Coin> coins = WDp.getCoins(msg.value.amount);
            WDp.showCoinDp(getBaseContext(), coins.get(0), holder.itemBorrowDenom, holder.itemBorrowAmount, mBaseChain);

        }

        private void onBindHardRepay(RecyclerView.ViewHolder viewHolder, int position) {
            final TxHardPoolRepaywHolder holder = (TxHardPoolRepaywHolder) viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            final Msg msg = mResTxInfo.getMsg(position - 1);
            holder.itemSender.setText(msg.value.sender);
            holder.itemOwener.setText(msg.value.owner);
            ArrayList<Coin> coins = WDp.getCoins(msg.value.amount);
            WDp.showCoinDp(getBaseContext(), coins.get(0), holder.itemRepayDenom, holder.itemRepayAmount, mBaseChain);

        }

        private void onBindHardLiquidate(RecyclerView.ViewHolder viewHolder, int position) {
            final TxHardPoolLiquidate holder = (TxHardPoolLiquidate) viewHolder;
            holder.onBind(getBaseContext(), mBaseChain, mResTxInfo.getMsg(position - 1));

        }

        private void onBindIncentiveMint(RecyclerView.ViewHolder viewHolder, int position) {
            final TxMintingIncentiveHolder holder = (TxMintingIncentiveHolder) viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                holder.itemSender.setText(msg.value.sender);
                holder.itemMultiplier.setText(msg.value.multiplier_name);
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

        private void onBindIncentiveHard(RecyclerView.ViewHolder viewHolder, int position) {
            final TxHardPoolIncentive holder = (TxHardPoolIncentive) viewHolder;
            holder.onBind(getBaseContext(), mBaseChain, mResTxInfo, mResTxInfo.getMsg(position - 1), position - 1);
        }

        private void onBindIncentiveSwap(RecyclerView.ViewHolder viewHolder, int position) {
            final TxSwapIncentive holder = (TxSwapIncentive) viewHolder;
            holder.onBind(getBaseContext(), mBaseChain, mResTxInfo, mResTxInfo.getMsg(position - 1), position - 1);
        }

        private void onBindIncentiveDelegator(RecyclerView.ViewHolder viewHolder, int position) {
            final TxDelegatorIncentive holder = (TxDelegatorIncentive) viewHolder;
            holder.onBind(getBaseContext(), mBaseChain, mResTxInfo, mResTxInfo.getMsg(position - 1), position - 1);
        }

        private void onBindSwapToken(RecyclerView.ViewHolder viewHolder, int position) {
            final TxSwapToken holder = (TxSwapToken) viewHolder;
            holder.onBind(getBaseContext(), mBaseChain, mResTxInfo, mResTxInfo.getMsg(position - 1));
        }

        private void onBindSwapDeposit(RecyclerView.ViewHolder viewHolder, int position) {
            final TxSwapDeposit holder = (TxSwapDeposit) viewHolder;
            holder.onBind(getBaseContext(), mBaseChain, mResTxInfo, mResTxInfo.getMsg(position - 1));
        }

        private void onBindSwapWithdraw(RecyclerView.ViewHolder viewHolder, int position) {
            final TxSwapWithdraw holder = (TxSwapWithdraw) viewHolder;
            holder.onBind(getBaseContext(), mBaseChain, mResTxInfo, mResTxInfo.getMsg(position - 1));
        }

        private void onBindCreateHTLC(RecyclerView.ViewHolder viewHolder, int position) {
            final TxCreateHtlcHolder holder = (TxCreateHtlcHolder) viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                Coin sendCoin = WDp.getCoins(msg.value.amount).get(0);
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
                Coin sendCoin = WDp.getCoins(msg.value.amount).get(0);
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
            final TxClaimHtlcHolder holder = (TxClaimHtlcHolder) viewHolder;
            holder.itemMsgImg.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final Msg msg = mResTxInfo.getMsg(position - 1);
                Coin receiveCoin = mResTxInfo.simpleSwapCoin();
                try {
                    if (!TextUtils.isEmpty(receiveCoin.denom)) {
                        WDp.showCoinDp(getBaseContext(), receiveCoin, holder.itemReceiveDenom, holder.itemReceiveAmount, mBaseChain);
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
                        WDp.showCoinDp(getBaseContext(), refundCoin, holder.itemRefundDenom, holder.itemRefundAmount, mBaseChain);
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
                WDp.showCoinDp(getBaseContext(), msg.value.quantity, holder.itemAmountDenom, holder.itemAmount, mBaseChain);
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

        public class TxHardPoolDepositHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            TextView itemDepositor, itemDepositType, itemDepositAmount, itemDepositAmountDenom;

            public TxHardPoolDepositHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemDepositor = itemView.findViewById(R.id.tx_depositor);
                itemDepositType = itemView.findViewById(R.id.tx_deposit_type);
                itemDepositAmount = itemView.findViewById(R.id.tx_deposit_amount);
                itemDepositAmountDenom = itemView.findViewById(R.id.tx_deposit_symbol);
            }
        }

        public class TxHardPoolWithdrawHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            TextView itemDepositor, itemDepositType, itemWithdrawAmount, itemWithdrawAmountDenom;

            public TxHardPoolWithdrawHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemDepositor = itemView.findViewById(R.id.tx_depositor);
                itemDepositType = itemView.findViewById(R.id.tx_deposit_type);
                itemWithdrawAmount = itemView.findViewById(R.id.tx_withdraw_amount);
                itemWithdrawAmountDenom = itemView.findViewById(R.id.tx_withdraw_symbol);
            }
        }

        public class TxHardPoolBorrowHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            TextView itemBorrower, itemBorrowAmount, itemBorrowDenom;

            public TxHardPoolBorrowHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemBorrower = itemView.findViewById(R.id.tx_hard_borrower);
                itemBorrowAmount = itemView.findViewById(R.id.borrow_amount);
                itemBorrowDenom = itemView.findViewById(R.id.borrow_symbol);
            }
        }

        public class TxHardPoolRepaywHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            TextView itemSender, itemOwener, itemRepayAmount, itemRepayDenom;

            public TxHardPoolRepaywHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemSender = itemView.findViewById(R.id.tx_sender);
                itemOwener = itemView.findViewById(R.id.tx_owener);
                itemRepayAmount = itemView.findViewById(R.id.repay_amount);
                itemRepayDenom = itemView.findViewById(R.id.repay_symbol);
            }
        }

        public class TxMintingIncentiveHolder extends RecyclerView.ViewHolder {
            ImageView itemMsgImg;
            TextView itemMsgTitle;
            TextView itemIncentiveAmount, itemIncentiveDenom, itemSender, itemDenom, itemMultiplier;

            public TxMintingIncentiveHolder(@NonNull View itemView) {
                super(itemView);
                itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
                itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
                itemIncentiveAmount = itemView.findViewById(R.id.tx_incentive_amount);
                itemIncentiveDenom = itemView.findViewById(R.id.tx_incentive_symbol);
                itemSender = itemView.findViewById(R.id.tx_incentive_sender);
                itemDenom = itemView.findViewById(R.id.tx_incentive_denom);
                itemMultiplier = itemView.findViewById(R.id.tx_multiplier_name);
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

        } else if (mBaseChain.equals(SECRET_MAIN)) {
            ApiClient.getSecretChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
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

        } else if (mBaseChain.equals(KI_MAIN)) {
            ApiClient.getKiChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
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
