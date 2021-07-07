package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.protobuf2.Any;

import akash.deployment.v1beta1.DeploymentOuterClass;
import akash.market.v1beta1.BidOuterClass;
import akash.market.v1beta1.LeaseOuterClass;
import cosmos.tx.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
import ibc.applications.transfer.v1.Tx;
import io.grpc.stub.StreamObserver;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.dialog.Dialog_MoreWait;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.txDetail.TxCloseBidHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxCloseDeploymentHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxCommissionHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxCommonHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxCreateBidHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxCreateDeploymentHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxCreateLeaseHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxDelegateHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;
import wannabit.io.cosmostaion.widget.txDetail.ibc.TxIBCAcknowledgeHolder;
import wannabit.io.cosmostaion.widget.txDetail.ibc.TxIBCReceiveHolder;
import wannabit.io.cosmostaion.widget.txDetail.ibc.TxIBCSendHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxReDelegateHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxSetAddressHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxStakingRewardHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxStarnameDeleteAccountHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxStarnameDeleteDomainHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxStarnameRegisterAccountHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxStarnameRegisterDomainHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxStarnameRenewHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxStarnameReplaceResourceHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxTransferHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxUnDelegateHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxUnjailHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxUnknownHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxVoterHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxWithdrawLeaseHolder;
import wannabit.io.cosmostaion.widget.txDetail.ibc.TxIBCUpdateClientHolder;
import wannabit.io.cosmostaion.widget.txDetail.osmosis.TxCreatePoolHolder;
import wannabit.io.cosmostaion.widget.txDetail.osmosis.TxExitPoolHolder;
import wannabit.io.cosmostaion.widget.txDetail.osmosis.TxJoinPoolHolder;
import wannabit.io.cosmostaion.widget.txDetail.osmosis.TxLockTokenHolder;
import wannabit.io.cosmostaion.widget.txDetail.osmosis.TxTokenSwapHolder;
import wannabit.io.cosmostaion.widget.txDetail.osmosis.TxUnlockAllTokensHolder;
import wannabit.io.cosmostaion.widget.txDetail.osmosis.TxUnlockPeriodHolder;
import wannabit.io.cosmostaion.widget.txDetail.osmosis.TxUnlockTokenHolder;

import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_UNKNOWN;

public class TxDetailgRPCActivity extends BaseActivity implements View.OnClickListener, Dialog_MoreWait.OnTxWaitListener {
    private Toolbar         mToolbar;
    private RecyclerView    mTxRecyclerView;
    private CardView        mErrorCardView;
    private TextView        mErrorMsgTv;
    private RelativeLayout  mLoadingLayer;

    private LinearLayout    mControlLayer;
    private Button          mDismissBtn;
    private Button          mShareBtn;
    private Button          mExplorerBtn;

    private boolean         mIsGen;
    private boolean         mIsSuccess;
    private int             mErrorCode;
    private String          mErrorMsg;
    private String          mTxHash;

    private TxDetailgRPCAdapter mAdapter;
    private ServiceOuterClass.GetTxResponse mResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tx_detail_grpc);
        mToolbar        = findViewById(R.id.tool_bar);
        mTxRecyclerView = findViewById(R.id.tx_recycler);
        mErrorCardView  = findViewById(R.id.error_Card);
        mErrorMsgTv     = findViewById(R.id.error_details);
        mLoadingLayer   = findViewById(R.id.loadingLayer);

        mControlLayer = findViewById(R.id.control_layer);
        mShareBtn = findViewById(R.id.btn_share);
        mDismissBtn = findViewById(R.id.btn_dismiss);
        mExplorerBtn = findViewById(R.id.btn_explorer);

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

        mShareBtn.setOnClickListener(this);
        mDismissBtn.setOnClickListener(this);
        mExplorerBtn.setOnClickListener(this);

        mTxRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mTxRecyclerView.setHasFixedSize(true);
        mAdapter = new TxDetailgRPCAdapter();
        mTxRecyclerView.setAdapter(mAdapter);

        if (!mIsSuccess || TextUtils.isEmpty(mTxHash)) {
            onShowError();
        } else {
            onFetchTx(mTxHash);
        }
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

    private void onShowError() {
        mLoadingLayer.setVisibility(View.GONE);
        mControlLayer.setVisibility(View.VISIBLE);
        mErrorCardView.setVisibility(View.VISIBLE);
        mErrorMsgTv.setText("error code : " + mErrorCode + "\n" + mErrorMsg);
    }

    private void onUpdateView() {
        mLoadingLayer.setVisibility(View.GONE);
        mControlLayer.setVisibility(View.VISIBLE);
        mAdapter.notifyDataSetChanged();
        mTxRecyclerView.setVisibility(View.VISIBLE);
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mDismissBtn)) {
            onBackPressed();

        } else if (v.equals(mShareBtn)) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, WUtil.getTxExplorer(mBaseChain, mResponse.getTxResponse().getTxhash()));
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "send"));

        } else if (v.equals(mExplorerBtn)) {
                String url  = WUtil.getTxExplorer(mBaseChain, mResponse.getTxResponse().getTxhash());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
        }

    }


    private class TxDetailgRPCAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_TX_COMMON = 0;
        private static final int TYPE_TX_TRANSFER = 1;
        private static final int TYPE_TX_DELEGATE = 2;
        private static final int TYPE_TX_UNDELEGATE = 3;
        private static final int TYPE_TX_REDELEGATE = 4;
        private static final int TYPE_TX_STAKING_REWARD = 5;
        private static final int TYPE_TX_ADDRESS_CHANGE = 6;
        private static final int TYPE_TX_VOTE = 7;
        private static final int TYPE_TX_COMMISSION = 8;
        private static final int TYPE_TX_UNJAIL = 9;
        private static final int TYPE_TX_CREATE_BID = 10;
        private static final int TYPE_TX_CLOSE_BID = 11;
        private static final int TYPE_TX_CREATE_LEASE = 12;
        private static final int TYPE_TX_WITHDRAW_LEASE = 13;
        private static final int TYPE_TX_CREATE_DEPLOYMENT = 14;
        private static final int TYPE_TX_CLOSE_DEPLOYMENT = 15;
        private static final int TYPE_TX_IBC_SEND = 16;
        private static final int TYPE_TX_IBC_RECEIVE = 17;
        private static final int TYPE_TX_IBC_UPDATE_CLIENT = 18;
        private static final int TYPE_TX_IBC_ACKNOWLEDGE = 19;

        private static final int TYPE_STARNAME_REGISTER_DOMAIN = 30;
        private static final int TYPE_STARNAME_REGISTER_ACCOUNT = 31;
        private static final int TYPE_STARNAME_DELETE_ACCOUNT = 32;
        private static final int TYPE_STARNAME_DELETE_DOMAIN = 33;
        private static final int TYPE_STARNAME_REPLACE_ACCOUNT_RESOURCE = 34;
        private static final int TYPE_STARNAME_TX_RENEW_ACCOUNT_STARNAME = 35;
        private static final int TYPE_STARNAME_TX_RENEW_DOMAIN_STARNAME = 36;

        private static final int TYPE_TX_CREATE_POOL = 40;
        private static final int TYPE_TX_JOIN_POOL = 41;
        private static final int TYPE_TX_EXIT_POOL = 42;
        private static final int TYPE_TX_SWAP_COIN = 43;
        private static final int TYPE_TX_LOCK_TOKEN = 44;
        private static final int TYPE_TX_UNLOCK_TOKEN = 45;
        private static final int TYPE_TX_UNLOCK_TOKEN_ALL = 46;
        private static final int TYPE_TX_UNLOCK_PERIOD_LOCK = 47;

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
                return new TxUnDelegateHolder(getLayoutInflater().inflate(R.layout.item_tx_undelegate, viewGroup, false));

            } else if (viewType == TYPE_TX_REDELEGATE) {
                return new TxReDelegateHolder(getLayoutInflater().inflate(R.layout.item_tx_redelegate, viewGroup, false));

            } else if (viewType == TYPE_TX_STAKING_REWARD) {
                return new TxStakingRewardHolder(getLayoutInflater().inflate(R.layout.item_tx_reward, viewGroup, false));

            } else if (viewType == TYPE_TX_ADDRESS_CHANGE) {
                return new TxSetAddressHolder(getLayoutInflater().inflate(R.layout.item_tx_reward_address, viewGroup, false));

            } else if (viewType == TYPE_TX_VOTE) {
                return new TxVoterHolder(getLayoutInflater().inflate(R.layout.item_tx_vote, viewGroup, false));

            } else if (viewType == TYPE_TX_COMMISSION) {
                return new TxCommissionHolder(getLayoutInflater().inflate(R.layout.item_tx_commission, viewGroup, false));

            } else if (viewType == TYPE_TX_UNJAIL) {
                return new TxUnjailHolder(getLayoutInflater().inflate(R.layout.item_tx_unjail, viewGroup, false));

            } else if (viewType == TYPE_TX_CREATE_BID) {
                return new TxCreateBidHolder(getLayoutInflater().inflate(R.layout.item_tx_create_bid, viewGroup, false));

            } else if (viewType == TYPE_TX_CLOSE_BID) {
                return new TxCloseBidHolder(getLayoutInflater().inflate(R.layout.item_tx_close_bid, viewGroup, false));

            } else if (viewType == TYPE_TX_CREATE_LEASE) {
                return new TxCreateLeaseHolder(getLayoutInflater().inflate(R.layout.item_tx_create_lease, viewGroup, false));

            } else if (viewType == TYPE_TX_WITHDRAW_LEASE) {
                return new TxWithdrawLeaseHolder(getLayoutInflater().inflate(R.layout.item_tx_withdraw_lease, viewGroup, false));

            } else if (viewType == TYPE_TX_CREATE_DEPLOYMENT) {
                return new TxCreateDeploymentHolder(getLayoutInflater().inflate(R.layout.item_tx_create_deployment, viewGroup, false));

            } else if (viewType == TYPE_TX_CLOSE_DEPLOYMENT) {
                return new TxCloseDeploymentHolder(getLayoutInflater().inflate(R.layout.item_tx_close_deployment, viewGroup, false));

            } else if (viewType == TYPE_TX_IBC_SEND) {
                return new TxIBCSendHolder(getLayoutInflater().inflate(R.layout.item_tx_ibc_send, viewGroup, false));

            } else if (viewType == TYPE_TX_IBC_RECEIVE) {
                return new TxIBCReceiveHolder(getLayoutInflater().inflate(R.layout.item_tx_ibc_receive, viewGroup, false));

            } else if (viewType == TYPE_TX_IBC_UPDATE_CLIENT) {
                return new TxIBCUpdateClientHolder(getLayoutInflater().inflate(R.layout.item_tx_ibc_update_client, viewGroup, false));

            } else if (viewType == TYPE_TX_IBC_ACKNOWLEDGE) {
                return new TxIBCAcknowledgeHolder(getLayoutInflater().inflate(R.layout.item_tx_ibc_acknowledge, viewGroup, false));

            }

            else if (viewType == TYPE_STARNAME_REGISTER_DOMAIN) {
                return new TxStarnameRegisterDomainHolder(getLayoutInflater().inflate(R.layout.item_tx_starname_register_domain, viewGroup, false));

            } else if (viewType == TYPE_STARNAME_REGISTER_ACCOUNT) {
                return new TxStarnameRegisterAccountHolder(getLayoutInflater().inflate(R.layout.item_tx_starname_register_account, viewGroup, false));

            } else if (viewType == TYPE_STARNAME_DELETE_ACCOUNT) {
                return new TxStarnameDeleteAccountHolder(getLayoutInflater().inflate(R.layout.item_tx_starname_delete_account, viewGroup, false));

            } else if (viewType == TYPE_STARNAME_DELETE_DOMAIN) {
                return new TxStarnameDeleteDomainHolder(getLayoutInflater().inflate(R.layout.item_tx_starname_delete_domain, viewGroup, false));

            } else if (viewType == TYPE_STARNAME_REPLACE_ACCOUNT_RESOURCE) {
                return new TxStarnameReplaceResourceHolder(getLayoutInflater().inflate(R.layout.item_tx_starname_resource, viewGroup, false));

            } else if (viewType == TYPE_STARNAME_TX_RENEW_ACCOUNT_STARNAME || viewType == TYPE_STARNAME_TX_RENEW_DOMAIN_STARNAME) {
                return new TxStarnameRenewHolder(getLayoutInflater().inflate(R.layout.item_tx_starname_renew, viewGroup, false));

            }

            else if (viewType == TYPE_TX_CREATE_POOL) {
                return new TxCreatePoolHolder(getLayoutInflater().inflate(R.layout.item_tx_create_pool, viewGroup, false));

            } else if (viewType == TYPE_TX_JOIN_POOL) {
                return new TxJoinPoolHolder(getLayoutInflater().inflate(R.layout.item_tx_join_pool, viewGroup, false));

            } else if (viewType == TYPE_TX_EXIT_POOL) {
                return new TxExitPoolHolder(getLayoutInflater().inflate(R.layout.item_tx_exit_pool, viewGroup, false));

            } else if (viewType == TYPE_TX_SWAP_COIN) {
                return new TxTokenSwapHolder(getLayoutInflater().inflate(R.layout.item_tx_token_swap, viewGroup, false));

            } else if (viewType == TYPE_TX_LOCK_TOKEN) {
                return new TxLockTokenHolder(getLayoutInflater().inflate(R.layout.item_tx_lock_token, viewGroup, false));

            } else if (viewType == TYPE_TX_UNLOCK_TOKEN) {
                return new TxUnlockTokenHolder(getLayoutInflater().inflate(R.layout.item_tx_unlock_token, viewGroup, false));

            } else if (viewType == TYPE_TX_UNLOCK_TOKEN_ALL) {
                return new TxUnlockAllTokensHolder(getLayoutInflater().inflate(R.layout.item_tx_unlock_all_token, viewGroup, false));

            } else if (viewType == TYPE_TX_UNLOCK_PERIOD_LOCK) {
                return new TxUnlockPeriodHolder(getLayoutInflater().inflate(R.layout.item_tx_unlock_period, viewGroup, false));
            }
            return new TxUnknownHolder(getLayoutInflater().inflate(R.layout.item_tx_unknown, viewGroup, false));

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (position == 0) {
                final TxCommonHolder viewHolder = (TxCommonHolder)holder;
                viewHolder.onBindCommon(TxDetailgRPCActivity.this, getBaseDao(),  mBaseChain, mResponse, mIsGen);

            } else {
                final TxHolder viewHolder = (TxHolder)holder;
                viewHolder.onBindMsg(getBaseContext(), getBaseDao(),  mBaseChain, mResponse, position - 1, mAccount.address, mIsGen);
            }
        }

        @Override
        public int getItemCount() {
            if (mResponse == null || mResponse.getTx().getBody().getMessagesCount() <= 0) {
                return 0;
            } else {
                return mResponse.getTx().getBody().getMessagesCount() + 1;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_TX_COMMON;
            } else {
                Any msg = mResponse.getTx().getBody().getMessages(position - 1);
                if (msg.getTypeUrl().contains(cosmos.bank.v1beta1.Tx.MsgSend.getDescriptor().getFullName())) {
                    return TYPE_TX_TRANSFER;
                } else if (msg.getTypeUrl().contains(cosmos.staking.v1beta1.Tx.MsgDelegate.getDescriptor().getFullName())) {
                    return TYPE_TX_DELEGATE;
                } else if (msg.getTypeUrl().contains(cosmos.staking.v1beta1.Tx.MsgUndelegate.getDescriptor().getFullName())) {
                    return TYPE_TX_UNDELEGATE;
                } else if (msg.getTypeUrl().contains(cosmos.staking.v1beta1.Tx.MsgBeginRedelegate.getDescriptor().getFullName())) {
                    return TYPE_TX_REDELEGATE;
                } else if (msg.getTypeUrl().contains(cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward.getDescriptor().getFullName())) {
                    return TYPE_TX_STAKING_REWARD;
                } else if (msg.getTypeUrl().contains(cosmos.distribution.v1beta1.Tx.MsgSetWithdrawAddress.getDescriptor().getFullName())) {
                    return TYPE_TX_ADDRESS_CHANGE;
                } else if (msg.getTypeUrl().contains(cosmos.gov.v1beta1.Tx.MsgVote.getDescriptor().getFullName())) {
                    return TYPE_TX_VOTE;
                } else if (msg.getTypeUrl().contains(cosmos.distribution.v1beta1.Tx.MsgWithdrawValidatorCommission.getDescriptor().getFullName())) {
                    return TYPE_TX_COMMISSION;
                } else if (msg.getTypeUrl().contains(cosmos.slashing.v1beta1.Tx.MsgUnjail.getDescriptor().getFullName())) {
                    return TYPE_TX_UNJAIL;
                } else if (msg.getTypeUrl().contains(BidOuterClass.MsgCreateBid.getDescriptor().getFullName())) {
                    return TYPE_TX_CREATE_BID;
                } else if (msg.getTypeUrl().contains(BidOuterClass.MsgCloseBid.getDescriptor().getFullName())) {
                    return TYPE_TX_CLOSE_BID;
                } else if (msg.getTypeUrl().contains(LeaseOuterClass.MsgCreateLease.getDescriptor().getFullName())) {
                    return TYPE_TX_CREATE_LEASE;
                } else if (msg.getTypeUrl().contains(LeaseOuterClass.MsgWithdrawLease.getDescriptor().getFullName())) {
                    return TYPE_TX_WITHDRAW_LEASE;
                } else if (msg.getTypeUrl().contains(DeploymentOuterClass.MsgCreateDeployment.getDescriptor().getFullName())) {
                    return TYPE_TX_CREATE_DEPLOYMENT;
                } else if (msg.getTypeUrl().contains(DeploymentOuterClass.MsgCloseDeployment.getDescriptor().getFullName())) {
                    return TYPE_TX_CLOSE_DEPLOYMENT;
                }

                else if (msg.getTypeUrl().contains(Tx.MsgTransfer.getDescriptor().getFullName())) {
                    return TYPE_TX_IBC_SEND;
                } else if (msg.getTypeUrl().contains(ibc.core.channel.v1.Tx.MsgRecvPacket.getDescriptor().getFullName())) {
                    return TYPE_TX_IBC_RECEIVE;
                } else if (msg.getTypeUrl().contains(ibc.core.client.v1.Tx.MsgUpdateClient.getDescriptor().getFullName())) {
                    return TYPE_TX_IBC_UPDATE_CLIENT;
                } else if (msg.getTypeUrl().contains(ibc.core.channel.v1.Tx.MsgAcknowledgement.getDescriptor().getFullName())) {
                    return TYPE_TX_IBC_ACKNOWLEDGE;
                }

                else if (msg.getTypeUrl().contains(starnamed.x.starname.v1beta1.Tx.MsgRegisterDomain.getDescriptor().getFullName())) {
                    return TYPE_STARNAME_REGISTER_DOMAIN;
                } else if (msg.getTypeUrl().contains(starnamed.x.starname.v1beta1.Tx.MsgRegisterAccount.getDescriptor().getFullName())) {
                    return TYPE_STARNAME_REGISTER_ACCOUNT;
                } else if (msg.getTypeUrl().contains(starnamed.x.starname.v1beta1.Tx.MsgDeleteAccount.getDescriptor().getFullName())) {
                    return TYPE_STARNAME_DELETE_ACCOUNT;
                } else if (msg.getTypeUrl().contains(starnamed.x.starname.v1beta1.Tx.MsgDeleteDomain.getDescriptor().getFullName())) {
                    return TYPE_STARNAME_DELETE_DOMAIN;
                } else if (msg.getTypeUrl().contains(starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResources.getDescriptor().getFullName())) {
                    return TYPE_STARNAME_REPLACE_ACCOUNT_RESOURCE;
                } else if (msg.getTypeUrl().contains(starnamed.x.starname.v1beta1.Tx.MsgRenewAccount.getDescriptor().getFullName())) {
                    return TYPE_STARNAME_TX_RENEW_ACCOUNT_STARNAME;
                } else if (msg.getTypeUrl().contains(starnamed.x.starname.v1beta1.Tx.MsgRenewDomain.getDescriptor().getFullName())) {
                    return TYPE_STARNAME_TX_RENEW_DOMAIN_STARNAME;
                }

                else if (msg.getTypeUrl().contains(osmosis.gamm.v1beta1.Tx.MsgCreatePool.getDescriptor().getFullName())) {
                    return TYPE_TX_CREATE_POOL;
                } else if (msg.getTypeUrl().contains(osmosis.gamm.v1beta1.Tx.MsgJoinPool.getDescriptor().getFullName())) {
                    return TYPE_TX_JOIN_POOL;
                } else if (msg.getTypeUrl().contains(osmosis.gamm.v1beta1.Tx.MsgExitPool.getDescriptor().getFullName())) {
                    return TYPE_TX_EXIT_POOL;
                } else if (msg.getTypeUrl().contains(osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn.getDescriptor().getFullName()) ||
                           msg.getTypeUrl().contains(osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOut.getDescriptor().getFullName())) {
                    return TYPE_TX_SWAP_COIN;
                } else if (msg.getTypeUrl().contains(osmosis.lockup.Tx.MsgLockTokens.getDescriptor().getFullName())) {
                    return TYPE_TX_LOCK_TOKEN;
                } else if (msg.getTypeUrl().equals("/" + osmosis.lockup.Tx.MsgBeginUnlocking.getDescriptor().getFullName())) {
                    return TYPE_TX_UNLOCK_TOKEN;
                } else if (msg.getTypeUrl().equals("/" + osmosis.lockup.Tx.MsgBeginUnlockingAll.getDescriptor().getFullName())) {
                    return TYPE_TX_UNLOCK_TOKEN_ALL;
                } else if (msg.getTypeUrl().contains(osmosis.lockup.Tx.MsgUnlockPeriodLock.getDescriptor().getFullName())) {
                    return TYPE_TX_UNLOCK_PERIOD_LOCK;
                }
                return TYPE_TX_UNKNOWN;
            }
        }
    }




    private int FetchCnt = 0;
    private void onFetchTx(String hash) {
//        WLog.w("onFetchTx " + hash);
        ServiceGrpc.ServiceStub mStub = ServiceGrpc.newStub(ChannelBuilder.getChain(mBaseChain));
        ServiceOuterClass.GetTxRequest request = ServiceOuterClass.GetTxRequest.newBuilder().setHash(mTxHash).build();
        mStub.getTx(request, new StreamObserver<ServiceOuterClass.GetTxResponse>() {
            @Override
            public void onNext(ServiceOuterClass.GetTxResponse value) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mResponse = value;
                        onUpdateView();
                    }
                }, 0);
            }

            @Override
            public void onError(Throwable t) {
                WLog.w("onError " + t.getLocalizedMessage());
                if (mIsSuccess && FetchCnt < 10) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            FetchCnt++;
                            onFetchTx(mTxHash);
                        }
                    }, 6000);
                } else if (!mIsGen) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onBackPressed();
                        }
                    }, 0);

                } else {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onShowMoreWait();
                        }
                    }, 0);
                }
            }

            @Override
            public void onCompleted() { }
        });

    }

    private void onShowMoreWait() {
        Dialog_MoreWait waitMore = Dialog_MoreWait.newInstance(null);
        waitMore.setCancelable(false);
        waitMore.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onWaitMore() {
        FetchCnt = 0;
        onFetchTx(mTxHash);
    }


}
