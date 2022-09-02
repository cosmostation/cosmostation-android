package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_UNKNOWN;

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

import cosmos.tx.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
import desmos.profiles.v1beta1.MsgsProfile;
import ibc.applications.transfer.v1.Tx;
import io.grpc.stub.StreamObserver;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.widget.txDetail.Starname.TxStarnameDeleteAccountHolder;
import wannabit.io.cosmostaion.widget.txDetail.Starname.TxStarnameDeleteDomainHolder;
import wannabit.io.cosmostaion.widget.txDetail.Starname.TxStarnameRegisterAccountHolder;
import wannabit.io.cosmostaion.widget.txDetail.Starname.TxStarnameRegisterDomainHolder;
import wannabit.io.cosmostaion.widget.txDetail.Starname.TxStarnameRenewHolder;
import wannabit.io.cosmostaion.widget.txDetail.Starname.TxStarnameReplaceResourceHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxAuthzExecHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxCommissionHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxCommonHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxDelegateHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxIBCSendHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxReDelegateHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxSetAddressHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxStakingRewardHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxTransferHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxUnDelegateHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxUnknownHolder;
import wannabit.io.cosmostaion.widget.txDetail.TxVoterHolder;
import wannabit.io.cosmostaion.widget.txDetail.airdrop.TxSaveProfileHolder;
import wannabit.io.cosmostaion.widget.txDetail.contract.TxExecuteContractHolder;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxBorrowHardHolder;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxCdpLiquidateHolder;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxCreateCdpHolder;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxDelegatorIncentiveHolder;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxDepositCdpHolder;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxDepositHardHolder;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxDrawDebtCdpHolder;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxHardIncentiveHolder;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxHardPoolLiquidateHolder;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxKavaDepositPoolHolder;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxKavaSwapHolder;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxKavaWithdrawPoolHolder;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxMintingIncentiveHolder;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxRepayCdpHolder;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxRepayHardHolder;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxSwapIncentiveHolder;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxWithdrawCdpHolder;
import wannabit.io.cosmostaion.widget.txDetail.kava.TxWithdrawHardHolder;
import wannabit.io.cosmostaion.widget.txDetail.nft.TxIssueDenomHolder;
import wannabit.io.cosmostaion.widget.txDetail.nft.TxMintNFTHolder;
import wannabit.io.cosmostaion.widget.txDetail.nft.TxTransferNFTHolder;
import wannabit.io.cosmostaion.widget.txDetail.osmosis.TxBeginUnlockTokenHolder;
import wannabit.io.cosmostaion.widget.txDetail.osmosis.TxExitPoolHolder;
import wannabit.io.cosmostaion.widget.txDetail.osmosis.TxJoinPoolHolder;
import wannabit.io.cosmostaion.widget.txDetail.osmosis.TxLockTokenHolder;
import wannabit.io.cosmostaion.widget.txDetail.osmosis.TxTokenSwapHolder;
import wannabit.io.cosmostaion.widget.txDetail.sif.TxAddLiquidityHolder;
import wannabit.io.cosmostaion.widget.txDetail.sif.TxRemoveLiquidityHolder;
import wannabit.io.cosmostaion.widget.txDetail.sif.TxSwapHolder;

public class TxDetailgRPCActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar         mToolbar;
    private RecyclerView    mTxRecyclerView;
    private CardView        mErrorCardView;
    private TextView        mErrorMsgTv;
    private RelativeLayout  mLoadingLayer;

    private LinearLayout    mControlLayer;
    private Button          mDismissBtn;
    private Button          mShareBtn;
    private Button          mExplorerBtn;

    private boolean         mIsGen = true;
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

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mIsSuccess = getIntent().getBooleanExtra("isSuccess", false);
        mErrorCode = getIntent().getIntExtra("errorCode", ERROR_CODE_UNKNOWN);
        mErrorMsg = getIntent().getStringExtra("errorMsg");
        mTxHash = getIntent().getStringExtra("txHash");

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
            shareIntent.putExtra(Intent.EXTRA_TEXT, mChainConfig.explorerHistoryLink(mResponse.getTxResponse().getTxhash()));
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "send"));

        } else if (v.equals(mExplorerBtn)) {
            String url = mChainConfig.explorerHistoryLink(mResponse.getTxResponse().getTxhash());
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
        private static final int TYPE_TX_IBC_SEND = 9;

        private static final int TYPE_STARNAME_REGISTER_DOMAIN = 30;
        private static final int TYPE_STARNAME_REGISTER_ACCOUNT = 31;
        private static final int TYPE_STARNAME_DELETE_ACCOUNT = 32;
        private static final int TYPE_STARNAME_DELETE_DOMAIN = 33;
        private static final int TYPE_STARNAME_REPLACE_ACCOUNT_RESOURCE = 34;
        private static final int TYPE_STARNAME_TX_RENEW_ACCOUNT_STARNAME = 35;
        private static final int TYPE_STARNAME_TX_RENEW_DOMAIN_STARNAME = 36;

        private static final int TYPE_TX_JOIN_POOL = 41;
        private static final int TYPE_TX_EXIT_POOL = 42;
        private static final int TYPE_TX_SWAP_COIN = 43;
        private static final int TYPE_TX_LOCK_TOKEN = 44;
        private static final int TYPE_TX_BEGIN_UNLOCK_TOKEN = 45;

        private static final int TYPE_TX_ADD_LIQUIDITY = 70;
        private static final int TYPE_TX_REMOVE_LIQUIDITY = 71;
        private static final int TYPE_TX_SWAP = 72;

        private static final int TYPE_TX_ISSUE_DENOM = 90;
        private static final int TYPE_TX_MINT_NFT = 91;
        private static final int TYPE_TX_TRANSFER_NFT = 92;

        private static final int TYPE_TX_SAVE_PROFILE = 100;

        private static final int TYPE_TX_KAVA_SWAP = 110;
        private static final int TYPE_TX_KAVA_DEPOSIT_POOL = 111;
        private static final int TYPE_TX_KAVA_WITHDRAW_POOL = 112;
        private static final int TYPE_TX_KAVA_CREATE_CDP = 113;
        private static final int TYPE_TX_KAVA_DEPOSIT_CDP = 114;
        private static final int TYPE_TX_KAVA_WITHDRAW_CDP = 115;
        private static final int TYPE_TX_KAVA_DRAW_DEBT_CDP = 116;
        private static final int TYPE_TX_KAVA_REPAY_CDP = 117;
        private static final int TYPE_TX_KAVA_DEPOSIT_HARD = 118;
        private static final int TYPE_TX_KAVA_WITHDRAW_HARD = 119;
        private static final int TYPE_TX_KAVA_BORROW_HARD = 121;
        private static final int TYPE_TX_KAVA_REPAY_HARD = 122;
        private static final int TYPE_TX_KAVA_INCENTIVE_MINT = 123;
        private static final int TYPE_TX_KAVA_INCENTIVE_HARD = 124;
        private static final int TYPE_TX_KAVA_INCENTIVE_DELEGATOR = 125;
        private static final int TYPE_TX_KAVA_INCENTIVE_SWAP = 126;
        private static final int TYPE_TX_KAVA_CDP_LIQUIDATE = 127;
        private static final int TYPE_TX_KAVA_HARD_LIQUIDATE = 128;

        private static final int TYPE_TX_EXECUTE_CONTRACT = 140;

        private static final int TYPE_TX_AUTHZ_EXEC = 150;

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

            }

            else if (viewType == TYPE_TX_IBC_SEND) {
                return new TxIBCSendHolder(getLayoutInflater().inflate(R.layout.item_tx_ibc_send, viewGroup, false));

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

            else if (viewType == TYPE_TX_JOIN_POOL) {
                return new TxJoinPoolHolder(getLayoutInflater().inflate(R.layout.item_tx_join_pool, viewGroup, false));

            } else if (viewType == TYPE_TX_EXIT_POOL) {
                return new TxExitPoolHolder(getLayoutInflater().inflate(R.layout.item_tx_exit_pool, viewGroup, false));

            } else if (viewType == TYPE_TX_SWAP_COIN) {
                return new TxTokenSwapHolder(getLayoutInflater().inflate(R.layout.item_tx_token_swap, viewGroup, false));

            } else if (viewType == TYPE_TX_LOCK_TOKEN) {
                return new TxLockTokenHolder(getLayoutInflater().inflate(R.layout.item_tx_lock_token, viewGroup, false));

            } else if (viewType == TYPE_TX_BEGIN_UNLOCK_TOKEN) {
                return new TxBeginUnlockTokenHolder(getLayoutInflater().inflate(R.layout.item_tx_begin_unlock_token, viewGroup, false));

            }

            // sifchain
            else if (viewType == TYPE_TX_ADD_LIQUIDITY) {
                return new TxAddLiquidityHolder(getLayoutInflater().inflate(R.layout.item_tx_add_liquidity, viewGroup, false));

            } else if (viewType == TYPE_TX_REMOVE_LIQUIDITY) {
                return new TxRemoveLiquidityHolder(getLayoutInflater().inflate(R.layout.item_tx_remove_liquidity, viewGroup, false));

            } else if (viewType == TYPE_TX_SWAP) {
                return new TxSwapHolder(getLayoutInflater().inflate(R.layout.item_tx_swap, viewGroup, false));

            }

            // nft
            else if (viewType == TYPE_TX_ISSUE_DENOM) {
                return new TxIssueDenomHolder(getLayoutInflater().inflate(R.layout.item_tx_issue_denom, viewGroup, false));

            } else if (viewType == TYPE_TX_MINT_NFT) {
                return new TxMintNFTHolder(getLayoutInflater().inflate(R.layout.item_tx_mint_nft, viewGroup, false));

            } else if (viewType == TYPE_TX_TRANSFER_NFT) {
                return new TxTransferNFTHolder(getLayoutInflater().inflate(R.layout.item_tx_send_nft, viewGroup, false));

            }

            // desmos
            else if (viewType == TYPE_TX_SAVE_PROFILE) {
                return new TxSaveProfileHolder(getLayoutInflater().inflate(R.layout.item_tx_save_profile, viewGroup, false));

            }

            // kava
            else if (viewType == TYPE_TX_KAVA_SWAP) {
                return new TxKavaSwapHolder(getLayoutInflater().inflate(R.layout.item_tx_kava_swap_token, viewGroup, false));

            } else if (viewType == TYPE_TX_KAVA_DEPOSIT_POOL) {
                return new TxKavaDepositPoolHolder(getLayoutInflater().inflate(R.layout.item_tx_kava_swap_deposit, viewGroup, false));

            } else if (viewType == TYPE_TX_KAVA_WITHDRAW_POOL) {
                return new TxKavaWithdrawPoolHolder(getLayoutInflater().inflate(R.layout.item_tx_kava_swap_withdraw, viewGroup, false));

            } else if (viewType == TYPE_TX_KAVA_CREATE_CDP) {
                return new TxCreateCdpHolder(getLayoutInflater().inflate(R.layout.item_tx_create_cdp, viewGroup, false));

            } else if (viewType == TYPE_TX_KAVA_DEPOSIT_CDP) {
                return new TxDepositCdpHolder(getLayoutInflater().inflate(R.layout.item_tx_deposit_cdp, viewGroup, false));

            } else if (viewType == TYPE_TX_KAVA_WITHDRAW_CDP) {
                return new TxWithdrawCdpHolder(getLayoutInflater().inflate(R.layout.item_tx_withdraw_cdp, viewGroup, false));

            } else if (viewType == TYPE_TX_KAVA_DRAW_DEBT_CDP) {
                return new TxDrawDebtCdpHolder(getLayoutInflater().inflate(R.layout.item_tx_drawdebt_cdp, viewGroup, false));

            } else if (viewType == TYPE_TX_KAVA_REPAY_CDP) {
                return new TxRepayCdpHolder(getLayoutInflater().inflate(R.layout.item_tx_repaydebt_cdp, viewGroup, false));

            } else if (viewType == TYPE_TX_KAVA_DEPOSIT_HARD) {
                return new TxDepositHardHolder(getLayoutInflater().inflate(R.layout.item_tx_deposit_harvest, viewGroup, false));

            } else if (viewType == TYPE_TX_KAVA_WITHDRAW_HARD) {
                return new TxWithdrawHardHolder(getLayoutInflater().inflate(R.layout.item_tx_withdraw_harvest, viewGroup, false));

            } else if (viewType == TYPE_TX_KAVA_BORROW_HARD) {
                return new TxBorrowHardHolder(getLayoutInflater().inflate(R.layout.item_tx_borrow_hard, viewGroup, false));

            } else if (viewType == TYPE_TX_KAVA_REPAY_HARD) {
                return new TxRepayHardHolder(getLayoutInflater().inflate(R.layout.item_tx_repay_hard, viewGroup, false));

            } else if (viewType == TYPE_TX_KAVA_INCENTIVE_MINT) {
                return new TxMintingIncentiveHolder(getLayoutInflater().inflate(R.layout.item_tx_incentive_reward, viewGroup, false));

            } else if (viewType == TYPE_TX_KAVA_INCENTIVE_HARD) {
                return new TxHardIncentiveHolder(getLayoutInflater().inflate(R.layout.item_tx_incentive_hard, viewGroup, false));

            } else if (viewType == TYPE_TX_KAVA_INCENTIVE_DELEGATOR) {
                return new TxDelegatorIncentiveHolder(getLayoutInflater().inflate(R.layout.item_tx_kava_delegator_incentive, viewGroup, false));

            } else if (viewType == TYPE_TX_KAVA_INCENTIVE_SWAP) {
                return new TxSwapIncentiveHolder(getLayoutInflater().inflate(R.layout.item_tx_kava_swap_incentive, viewGroup, false));

            } else if (viewType == TYPE_TX_KAVA_CDP_LIQUIDATE) {
                return new TxCdpLiquidateHolder(getLayoutInflater().inflate(R.layout.item_tx_liquidate_cdp, viewGroup, false));

            } else if (viewType == TYPE_TX_KAVA_HARD_LIQUIDATE) {
                return new TxHardPoolLiquidateHolder(getLayoutInflater().inflate(R.layout.item_tx_liquidate_hard, viewGroup, false));

            }

            // wasm
            else if (viewType == TYPE_TX_EXECUTE_CONTRACT) {
                return new TxExecuteContractHolder(getLayoutInflater().inflate(R.layout.item_tx_execute_contract, viewGroup, false));

            }

            // authz
            else if (viewType == TYPE_TX_AUTHZ_EXEC) {
                return new TxAuthzExecHolder(getLayoutInflater().inflate(R.layout.item_tx_authz_exec, viewGroup, false));

            }
            return new TxUnknownHolder(getLayoutInflater().inflate(R.layout.item_tx_unknown, viewGroup, false));

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (position == 0) {
                final TxCommonHolder viewHolder = (TxCommonHolder)holder;
                viewHolder.onBindCommon(TxDetailgRPCActivity.this, getBaseDao(), mChainConfig, mResponse);

            } else {
                final TxHolder viewHolder = (TxHolder)holder;
                viewHolder.onBindMsg(getBaseContext(), getBaseDao(), mChainConfig, mResponse, position - 1, mAccount.address);
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
                }

                else if (msg.getTypeUrl().contains(Tx.MsgTransfer.getDescriptor().getFullName())) {
                    return TYPE_TX_IBC_SEND;
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

                else if (msg.getTypeUrl().contains(osmosis.gamm.v1beta1.Tx.MsgJoinPool.getDescriptor().getFullName()) ||
                           msg.getTypeUrl().contains(osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountIn.getDescriptor().getFullName())) {
                    return TYPE_TX_JOIN_POOL;
                } else if (msg.getTypeUrl().contains(osmosis.gamm.v1beta1.Tx.MsgExitPool.getDescriptor().getFullName())) {
                    return TYPE_TX_EXIT_POOL;
                } else if (msg.getTypeUrl().contains(osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn.getDescriptor().getFullName()) ||
                           msg.getTypeUrl().contains(osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOut.getDescriptor().getFullName())) {
                    return TYPE_TX_SWAP_COIN;
                } else if (msg.getTypeUrl().contains(osmosis.lockup.Tx.MsgLockTokens.getDescriptor().getFullName())) {
                    return TYPE_TX_LOCK_TOKEN;
                } else if (msg.getTypeUrl().equals("/" + osmosis.lockup.Tx.MsgBeginUnlocking.getDescriptor().getFullName())) {
                    return TYPE_TX_BEGIN_UNLOCK_TOKEN;
                }

                // sifchain msg
                else if (msg.getTypeUrl().contains(sifnode.clp.v1.Tx.MsgAddLiquidity.getDescriptor().getFullName())) {
                    return TYPE_TX_ADD_LIQUIDITY;
                } else if (msg.getTypeUrl().contains(sifnode.clp.v1.Tx.MsgRemoveLiquidity.getDescriptor().getFullName())) {
                    return TYPE_TX_REMOVE_LIQUIDITY;
                } else if (msg.getTypeUrl().contains(sifnode.clp.v1.Tx.MsgSwap.getDescriptor().getFullName())) {
                    return TYPE_TX_SWAP;
                }

                // nft msg
                else if (msg.getTypeUrl().contains(irismod.nft.Tx.MsgIssueDenom.getDescriptor().getFullName()) ||
                            msg.getTypeUrl().contains(chainmain.nft.v1.Tx.MsgIssueDenom.getDescriptor().getFullName())) {
                    return TYPE_TX_ISSUE_DENOM;
                } else if (msg.getTypeUrl().contains(irismod.nft.Tx.MsgMintNFT.getDescriptor().getFullName()) ||
                        msg.getTypeUrl().contains(chainmain.nft.v1.Tx.MsgMintNFT.getDescriptor().getFullName())) {
                    return TYPE_TX_MINT_NFT;
                } else if (msg.getTypeUrl().contains(irismod.nft.Tx.MsgTransferNFT.getDescriptor().getFullName()) ||
                        msg.getTypeUrl().contains(chainmain.nft.v1.Tx.MsgTransferNFT.getDescriptor().getFullName())) {
                    return TYPE_TX_TRANSFER_NFT;
                }

                // desmos msg
                else if (msg.getTypeUrl().contains(MsgsProfile.MsgSaveProfile.getDescriptor().getFullName())) {
                    return TYPE_TX_SAVE_PROFILE;
                }

                //kava msg
                else if (msg.getTypeUrl().contains(kava.swap.v1beta1.Tx.MsgSwapExactForTokens.getDescriptor().getFullName()) ||
                            msg.getTypeUrl().contains(kava.swap.v1beta1.Tx.MsgSwapForExactTokens.getDescriptor().getFullName())) {
                    return TYPE_TX_KAVA_SWAP;
                } else if (msg.getTypeUrl().contains(kava.swap.v1beta1.Tx.MsgDeposit.getDescriptor().getFullName())) {
                    return TYPE_TX_KAVA_DEPOSIT_POOL;
                } else if (msg.getTypeUrl().contains(kava.swap.v1beta1.Tx.MsgWithdraw.getDescriptor().getFullName())) {
                    return TYPE_TX_KAVA_WITHDRAW_POOL;
                } else if (msg.getTypeUrl().contains(kava.cdp.v1beta1.Tx.MsgCreateCDP.getDescriptor().getFullName())) {
                    return TYPE_TX_KAVA_CREATE_CDP;
                } else if (msg.getTypeUrl().contains(kava.cdp.v1beta1.Tx.MsgWithdraw.getDescriptor().getFullName())) {
                    return TYPE_TX_KAVA_WITHDRAW_CDP;
                } else if (msg.getTypeUrl().contains(kava.cdp.v1beta1.Tx.MsgDeposit.getDescriptor().getFullName())) {
                    return TYPE_TX_KAVA_DEPOSIT_CDP;
                } else if (msg.getTypeUrl().contains(kava.cdp.v1beta1.Tx.MsgDrawDebt.getDescriptor().getFullName())) {
                    return TYPE_TX_KAVA_DRAW_DEBT_CDP;
                } else if (msg.getTypeUrl().contains(kava.cdp.v1beta1.Tx.MsgRepayDebt.getDescriptor().getFullName())) {
                    return TYPE_TX_KAVA_REPAY_CDP;
                } else if (msg.getTypeUrl().contains(kava.hard.v1beta1.Tx.MsgDeposit.getDescriptor().getFullName())) {
                    return TYPE_TX_KAVA_DEPOSIT_HARD;
                } else if (msg.getTypeUrl().contains(kava.hard.v1beta1.Tx.MsgWithdraw.getDescriptor().getFullName())) {
                    return TYPE_TX_KAVA_WITHDRAW_HARD;
                } else if (msg.getTypeUrl().contains(kava.hard.v1beta1.Tx.MsgBorrow.getDescriptor().getFullName())) {
                    return TYPE_TX_KAVA_BORROW_HARD;
                } else if (msg.getTypeUrl().contains(kava.hard.v1beta1.Tx.MsgRepay.getDescriptor().getFullName())) {
                    return TYPE_TX_KAVA_REPAY_HARD;
                } else if (msg.getTypeUrl().contains(kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingReward.getDescriptor().getFullName())) {
                    return TYPE_TX_KAVA_INCENTIVE_MINT;
                } else if (msg.getTypeUrl().contains(kava.incentive.v1beta1.Tx.MsgClaimHardReward.getDescriptor().getFullName())) {
                    return TYPE_TX_KAVA_INCENTIVE_HARD;
                } else if (msg.getTypeUrl().contains(kava.incentive.v1beta1.Tx.MsgClaimDelegatorReward.getDescriptor().getFullName())) {
                    return TYPE_TX_KAVA_INCENTIVE_DELEGATOR;
                } else if (msg.getTypeUrl().contains(kava.incentive.v1beta1.Tx.MsgClaimSwapReward.getDescriptor().getFullName())) {
                    return TYPE_TX_KAVA_INCENTIVE_SWAP;
                } else if (msg.getTypeUrl().contains(kava.cdp.v1beta1.Tx.MsgLiquidate.getDescriptor().getFullName())) {
                    return TYPE_TX_KAVA_CDP_LIQUIDATE;
                } else if (msg.getTypeUrl().contains(kava.hard.v1beta1.Tx.MsgLiquidate.getDescriptor().getFullName())) {
                    return TYPE_TX_KAVA_HARD_LIQUIDATE;
                }

                else if (msg.getTypeUrl().contains(cosmwasm.wasm.v1.Tx.MsgExecuteContract.getDescriptor().getFullName())) {
                    return TYPE_TX_EXECUTE_CONTRACT;
                }

                else if (msg.getTypeUrl().contains(cosmos.authz.v1beta1.Tx.MsgExec.getDescriptor().getFullName())) {
                    return TYPE_TX_AUTHZ_EXEC;
                }
                return TYPE_TX_UNKNOWN;
            }
        }
    }


    private int FetchCnt = 0;
    private void onFetchTx(String hash) {
        ServiceGrpc.ServiceStub mStub = ServiceGrpc.newStub(ChannelBuilder.getChain(mBaseChain));
        ServiceOuterClass.GetTxRequest request = ServiceOuterClass.GetTxRequest.newBuilder().setHash(hash).build();
        mStub.getTx(request, new StreamObserver<ServiceOuterClass.GetTxResponse>() {
            @Override
            public void onNext(ServiceOuterClass.GetTxResponse value) {
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    mResponse = value;
                    onUpdateView();
                }, 0);
            }

            @Override
            public void onError(Throwable t) {
                if (mIsSuccess && FetchCnt < 10) {
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        FetchCnt++;
                        onFetchTx(hash);
                    }, 6000);
                } else if (!mIsGen) {
                    new Handler(Looper.getMainLooper()).postDelayed(() -> onBackPressed(), 0);

                } else {
                    new Handler(Looper.getMainLooper()).postDelayed(() -> onShowMoreWait(), 0);
                }
            }

            @Override
            public void onCompleted() { }
        });

    }

    private void onShowMoreWait() {
        AlertDialogUtils.showDoubleButtonDialog(this, getString(R.string.str_more_wait_title), getString(R.string.str_more_wait_msg),
                getString(R.string.str_close), view -> onBackPressed(),
                getString(R.string.str_wait), view -> onWaitMore(), false);
    }

    public void onWaitMore() {
        FetchCnt = 0;
        onFetchTx(mTxHash);
    }
}
