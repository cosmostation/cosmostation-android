package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_ADD_LIQUIDITY;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_AUTHZ_CLAIM_COMMISSION;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_AUTHZ_CLAIM_REWARD;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_AUTHZ_DELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_AUTHZ_REDELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_AUTHZ_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_AUTHZ_UNDELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_AUTHZ_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_BORROW_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_CLAIM_INCENTIVE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_CREATE_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_DELETE_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_DELETE_DOMAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_DEPOSIT_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_DEPOSIT_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_DRAW_DEBT_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_EVM_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_EXECUTE_CONTRACT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_IBC_CONTRACT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_IBC_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_KAVA_EXIT_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_KAVA_JOIN_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_KAVA_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_MINT_NFT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OSMOSIS_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_PERSIS_LIQUID_REDEEM;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_PERSIS_LIQUID_STAKING;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REGISTER_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REGISTER_DOMAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REINVEST;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REMOVE_LIQUIDITY;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_RENEW_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_RENEW_DOMAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REPAY_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REPAY_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REPLACE_STARNAME;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SEND_NFT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIF_EXIT_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIF_JOIN_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIF_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_DELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_REDELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_REWARD;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_UNDELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_STRIDE_LIQUID_STAKING;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_STRIDE_LIQUID_UNSTAKING;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_WITHDRAW_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_WITHDRAW_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.NFT_INFURA;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.addisonelliott.segmentedbutton.SegmentedButton;
import com.addisonelliott.segmentedbutton.SegmentedButtonGroup;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import cosmos.base.abci.v1beta1.Abci;
import cosmos.tx.v1beta1.ServiceGrpc;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Asset;
import wannabit.io.cosmostaion.dao.FeeInfo;
import wannabit.io.cosmostaion.dao.StationNFTData;
import wannabit.io.cosmostaion.dialog.SelectChainListDialog;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulAuthzClaimCommissionGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulAuthzClaimRewardGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulAuthzDelegateGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulAuthzRedelegateGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulAuthzSendGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulAuthzUndelegateGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulAuthzVoteGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulChangeRewardAddressGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulClaimRewardsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulCw20IbcSendGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulCw20SendGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulDelegateGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulDeleteAccountGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulDeleteDomainGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulErc20SendGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulIBCTransferGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaBorrowHardGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaClaimIncentiveAllGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaCreateCdpGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaDepositCdpGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaDepositGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaDepositHardGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaDrawDebtCdpGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaLiquidityGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaRepayCdpGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaRepayHardGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaSwapGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaWithDrawCdpGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaWithdrawGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaWithdrawHardGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulLiquidStakingGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulLiquidUnStakingGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulMintNFTGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulOsmosisSwaplnGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulPersisLiquidGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulReInvestGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulRedelegateGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulRegisterAccountGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulRegisterDomainGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulRenewAccountGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulRenewDomainGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulReplaceStarNameGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulSendGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulSifDepositGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulSifSwapGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulSifWithdrawGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulTransferNFTGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulUndelegateGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulVoteGrpcTask;
import wannabit.io.cosmostaion.utils.DisplayUtils;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;

public class StepFeeSetFragment extends BaseFragment implements View.OnClickListener, TaskListener {

    private CardView mFeeTotalCard;
    private RelativeLayout mBtnSelectFeeCoin;
    private ImageView mFeeCoinImg;
    private TextView mFeeCoinDenom;
    private TextView mGasAmount, mGasDenom, mGasValue;
    private SegmentedButtonGroup mButtonGroup;

    private TextView mSpeedTxt;
    private Button mBtnBefore, mBtnNext;

    private Account mAccount;
    private BaseChain mBaseChain;
    private ChainConfig mChainConfig;

    private boolean mSimulPassed = false;
    private Fee mFee;
    private Coin mFeeCoin;
    private BigDecimal mFeeGasAmount = new BigDecimal("500000");

    private ArrayList<FeeInfo> mFeeInfo = new ArrayList<>();
    private FeeInfo.FeeData mFeeData;
    private Integer mSelectedFeeInfo = 1;
    private Integer mSelectedFeeData = 0;

    private ServiceGrpc.ServiceBlockingStub txService;
    public static StepFeeSetFragment newInstance() {
        return new StepFeeSetFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tx_step_fee_grpc, container, false);
        mFeeTotalCard = rootView.findViewById(R.id.card_fee_total);
        mBtnSelectFeeCoin = rootView.findViewById(R.id.btn_select_fee_coin);
        mFeeCoinImg = rootView.findViewById(R.id.fee_coin_img);
        mFeeCoinDenom = rootView.findViewById(R.id.fee_coin_denom);

        mGasAmount = rootView.findViewById(R.id.total_fee_amount);
        mGasDenom = rootView.findViewById(R.id.total_fee_denom);
        mGasValue = rootView.findViewById(R.id.total_fee_value);

        mButtonGroup = rootView.findViewById(R.id.btns_segmented);
        mSpeedTxt = rootView.findViewById(R.id.speed_txt);

        mBtnBefore = rootView.findViewById(R.id.btn_before);
        mBtnNext = rootView.findViewById(R.id.btn_next);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mFeeInfo = WDp.getFeeInfos(getActivity(), getBaseDao());
        txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));

        mFeeTotalCard.setCardBackgroundColor(ContextCompat.getColor(getActivity(), mChainConfig.chainBgColor()));
        WDp.setDpSymbolImg(getBaseDao(), mChainConfig, mChainConfig.mainDenom(), mFeeCoinImg);
        WDp.setDpSymbol(getActivity(), getBaseDao(), mChainConfig, mChainConfig.mainDenom(), mFeeCoinDenom);

        mButtonGroup.setSelectedBackground(ContextCompat.getColor(getActivity(), mChainConfig.chainColor()));
        mButtonGroup.setRipple(ContextCompat.getColor(getActivity(), mChainConfig.chainColor()));

        for (int i = 0; i < mFeeInfo.size(); i++) {
            View segmentView = inflater.inflate(R.layout.item_segmented_fee, null);
            mButtonGroup.addView(segmentView, i, new LinearLayout.LayoutParams(0, DisplayUtils.dpToPx(getActivity(), 32), 1));

            SegmentedButton btnTxt = segmentView.findViewById(R.id.btn_title);
            btnTxt.setText(mFeeInfo.get(i).title);
        }

        if (getBaseDao().mParam != null && getBaseDao().mParam.mGasPrice != null && getBaseDao().mParam.mGasPrice.base != null) {
            mSelectedFeeInfo = Integer.parseInt(getBaseDao().mParam.mGasPrice.base);
        }

        mButtonGroup.setPosition(mSelectedFeeInfo, false);
        mButtonGroup.setOnPositionChangedListener(position -> {
            mSelectedFeeInfo = position;
            onCalculateFees();
            onCheckTxType();
        });
        onUpdateView();

        mBtnSelectFeeCoin.setOnClickListener(this);
        mBtnBefore.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        return rootView;
    }

    private void onCalculateFees() {
        if (mFeeInfo != null && mSelectedFeeInfo != null && mFeeInfo.get(mSelectedFeeInfo) != null && mFeeInfo.get(mSelectedFeeInfo).feeDatas != null) {
            mFeeData = mFeeInfo.get(mSelectedFeeInfo).feeDatas.get(mSelectedFeeData);
            if (mFeeData != null) {
                if (mBaseChain.equals(BaseChain.SIF_MAIN)) {
                    mFeeCoin = new Coin(mFeeData.denom, "100000000000000000");
                } else if (mBaseChain.equals(BaseChain.CHIHUAHUA_MAIN)) {
                    if (mSelectedFeeInfo == 0) mFeeCoin = new Coin(mFeeData.denom, "1000000");
                    else if (mSelectedFeeInfo == 1) mFeeCoin = new Coin(mFeeData.denom, "5000000");
                    else mFeeCoin = new Coin(mFeeData.denom, "10000000");
                } else {
                    BigDecimal amount = mFeeData.gasRate.multiply(mFeeGasAmount).setScale(0, RoundingMode.UP);
                    mFeeCoin = new Coin(mFeeData.denom, amount.toPlainString());
                }
                mFee = new Fee(mFeeGasAmount.toPlainString(), Lists.newArrayList(mFeeCoin));
            }
        }
    }

    private void onUpdateView() {
        if (getSActivity().mTxType != CONST_PW_TX_EVM_TRANSFER) {
            onCalculateFees();
        }
        WDp.setDpSymbolImg(getBaseDao(), mChainConfig, mFeeData.denom, mFeeCoinImg);
        WDp.setDpSymbol(getActivity(), getBaseDao(), mChainConfig, mFeeData.denom, mFeeCoinDenom);
        mGasAmount.setVisibility(View.VISIBLE);
        mGasDenom.setVisibility(View.VISIBLE);
        mGasValue.setVisibility(View.VISIBLE);
        mSpeedTxt.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefreshTab() {
        onCheckTxType();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnSelectFeeCoin) && !getSActivity().isFinishing()) {
            Bundle bundleData = new Bundle();
            bundleData.putSerializable(SelectChainListDialog.FEE_DATA_LIST_BUNDLE_KEY, mFeeInfo.get(mSelectedFeeInfo).feeDatas);
            bundleData.putInt(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, SelectChainListDialog.SELECT_FEE_DENOM_VALUE);
            SelectChainListDialog dialog = SelectChainListDialog.newInstance(bundleData);
            dialog.show(getParentFragmentManager(), SelectChainListDialog.class.getName());
            getParentFragmentManager().setFragmentResultListener(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, this, (requestKey, bundle) -> {
                mSelectedFeeData = bundle.getInt(BaseConstant.POSITION);
                onCalculateFees();
                onCheckTxType();
                onUpdateView();
            });

        } else if (v.equals(mBtnBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            if (!mSimulPassed) {
                Toast.makeText(getActivity(), getString(R.string.error_simul_error), Toast.LENGTH_SHORT).show();
                return;
            }
            getSActivity().mTxFee = mFee;
            getSActivity().onNextStep();
        }
    }

    private BaseBroadCastActivity getSActivity() {
        return (BaseBroadCastActivity) getBaseActivity();
    }

    public void onCheckTxType() {
        getSActivity().onShowWaitDialog();
        if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_SEND) {
            new SimulSendGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mToAddress, getSActivity().mAmounts,
                    getSActivity().mTxMemo, mFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_DELEGATE) {
            new SimulDelegateGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mValAddress, getSActivity().mAmount,
                    getSActivity().mTxMemo, mFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
            new SimulUndelegateGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mValAddress, getSActivity().mAmount,
                    getSActivity().mTxMemo, mFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_REDELEGATE) {
            new SimulRedelegateGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mValAddress, getSActivity().mToValAddress, getSActivity().mAmount,
                    getSActivity().mTxMemo, mFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_REWARD) {
            new SimulClaimRewardsGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mValAddresses,
                    getSActivity().mTxMemo, mFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_REINVEST) {
            new SimulReInvestGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mValAddress, getSActivity().mAmount,
                    getSActivity().mTxMemo, mFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
            new SimulChangeRewardAddressGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mNewRewardAddress,
                    getSActivity().mTxMemo, mFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_VOTE) {
            new SimulVoteGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mSelectedOpinion,
                    getSActivity().mTxMemo, mFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_REGISTER_DOMAIN) {
            new SimulRegisterDomainGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain,
                    getSActivity().mStarNameDomain, getSActivity().mStarNameDomainType,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        } else if (getSActivity().mTxType == CONST_PW_TX_REGISTER_ACCOUNT) {
            new SimulRegisterAccountGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain,
                    getSActivity().mStarNameDomain, getSActivity().mStarNameAccount, getSActivity().mStarNameResources,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_DELETE_DOMAIN) {
            new SimulDeleteDomainGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain,
                    getSActivity().mStarNameDomain,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_DELETE_ACCOUNT) {
            new SimulDeleteAccountGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain,
                    getSActivity().mStarNameDomain, getSActivity().mStarNameAccount,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_RENEW_DOMAIN) {
            new SimulRenewDomainGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain,
                    getSActivity().mStarNameDomain,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_RENEW_ACCOUNT) {
            new SimulRenewAccountGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain,
                    getSActivity().mStarNameDomain, getSActivity().mStarNameAccount,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_REPLACE_STARNAME) {
            if (getSActivity().mIsDomain) {
                new SimulReplaceStarNameGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain,
                        getSActivity().mStarNameDomain, "", getSActivity().mStarNameResources,
                        getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                new SimulReplaceStarNameGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain,
                        getSActivity().mStarNameDomain, getSActivity().mStarNameAccount, getSActivity().mStarNameResources,
                        getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        } else if (getSActivity().mTxType == CONST_PW_TX_OSMOSIS_SWAP) {
            new SimulOsmosisSwaplnGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain,
                    getSActivity().mOsmosisSwapAmountInRoute, getSActivity().mSwapInCoin, getSActivity().mSwapOutCoin,
                    getSActivity().mTxMemo, mFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        } else if (getSActivity().mTxType == CONST_PW_TX_IBC_TRANSFER) {
            new SimulIBCTransferGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mToAddress,
                    getSActivity().mAmounts.get(0).denom, getSActivity().mAmounts.get(0).amount, getSActivity().mAssetPath, mFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_SIF_SWAP) {
            new SimulSifSwapGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address,
                    getSActivity().mSifSwapInCoin, getSActivity().mSifSwapOutCoin, getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_SIF_JOIN_POOL) {
            new SimulSifDepositGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address,
                    getSActivity().mSifDepositCoin1.denom, getSActivity().mSifDepositCoin0.amount, getSActivity().mSifDepositCoin1.amount,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_SIF_EXIT_POOL) {
            BigDecimal myShareAllAmount = new BigDecimal(getSActivity().mMyProvider.getLiquidityProvider().getLiquidityProviderUnits());
            BigDecimal myShareWithdrawAmount = new BigDecimal(getSActivity().mSifWithdrawCoin.amount);
            String basisPoint = myShareWithdrawAmount.movePointRight(4).divide(myShareAllAmount, 0, RoundingMode.DOWN).toPlainString();
            new SimulSifWithdrawGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address,
                    getSActivity().mSifWithdrawCoin.denom, basisPoint, getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_MINT_NFT) {
            StationNFTData nftData = new StationNFTData(getSActivity().mAccount.address, getSActivity().mNftName, getSActivity().mNftDescription, getSActivity().mNftDenomId, NFT_INFURA + getSActivity().mNftHash);
            Gson gson = new Gson();
            String jsonData = gson.toJson(nftData);
            new SimulMintNFTGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address,
                    getSActivity().mNftDenomId, getSActivity().mNftDenomName, getSActivity().mNftHash.toLowerCase(), getSActivity().mNftName, NFT_INFURA + getSActivity().mNftHash, jsonData,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_SEND_NFT) {
            new SimulTransferNFTGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address,
                    getSActivity().mToAddress, getSActivity().mNftDenomId, getSActivity().mNftTokenId, getSActivity().mIrisResponse, getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_KAVA_SWAP) {
            new SimulKavaSwapGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mKavaSwapIn, getSActivity().mKavaSwapOut,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_KAVA_JOIN_POOL) {
            new SimulKavaDepositGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mKavaPoolTokenA, getSActivity().mKavaPoolTokenB,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_KAVA_EXIT_POOL) {
            new SimulKavaWithdrawGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mKavaShareAmount.toPlainString(), getSActivity().mKavaPoolTokenA, getSActivity().mKavaPoolTokenB,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_CREATE_CDP) {
            new SimulKavaCreateCdpGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mCollateral, getSActivity().mPrincipal,
                    getSActivity().mCollateralType, getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_DEPOSIT_CDP) {
            new SimulKavaDepositCdpGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mAccount.address, getSActivity().mCollateral,
                    getSActivity().mCollateralType, getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_WITHDRAW_CDP) {
            new SimulKavaWithDrawCdpGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mAccount.address, getSActivity().mCollateral,
                    getSActivity().mCollateralType, getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_DRAW_DEBT_CDP) {
            new SimulKavaDrawDebtCdpGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mPrincipal,
                    getSActivity().mCollateralType, getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_REPAY_CDP) {
            new SimulKavaRepayCdpGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mPayment,
                    getSActivity().mCollateralType, getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_DEPOSIT_HARD) {
            new SimulKavaDepositHardGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mHardPoolCoins,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_WITHDRAW_HARD) {
            new SimulKavaWithdrawHardGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mHardPoolCoins,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_BORROW_HARD) {
            new SimulKavaBorrowHardGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mHardPoolCoins,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_REPAY_HARD) {
            new SimulKavaRepayHardGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mAccount.address, getSActivity().mHardPoolCoins,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_CLAIM_INCENTIVE) {
            new SimulKavaClaimIncentiveAllGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().getBaseDao(),
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_EXECUTE_CONTRACT) {
            new SimulCw20SendGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mToAddress, getSActivity().mMintscanToken.address, getSActivity().mAmounts,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_IBC_CONTRACT) {
            new SimulCw20IbcSendGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mToAddress, getSActivity().mMintscanToken.address, getSActivity().mAssetPath, getSActivity().mAmounts,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_AUTHZ_DELEGATE) {
            new SimulAuthzDelegateGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mGranter, getSActivity().mValAddress, getSActivity().mAmount,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_AUTHZ_UNDELEGATE) {
            new SimulAuthzUndelegateGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mGranter, getSActivity().mValAddress, getSActivity().mAmount,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_AUTHZ_REDELEGATE) {
            new SimulAuthzRedelegateGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mGranter, getSActivity().mValAddress, getSActivity().mToValAddress, getSActivity().mAmount,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_AUTHZ_SEND) {
            new SimulAuthzSendGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mGranter, getSActivity().mToAddress, getSActivity().mAmount,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_AUTHZ_VOTE) {
            new SimulAuthzVoteGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mGranter, getSActivity().mSelectedOpinion,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_AUTHZ_CLAIM_REWARD) {
            new SimulAuthzClaimRewardGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mGranter, getSActivity().mValAddresses,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_AUTHZ_CLAIM_COMMISSION) {
            new SimulAuthzClaimCommissionGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, WKey.convertDpAddressToDpOpAddress(getSActivity().mGranter, getSActivity().mChainConfig),
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_ADD_LIQUIDITY || getSActivity().mTxType == CONST_PW_TX_REMOVE_LIQUIDITY) {
            new SimulKavaLiquidityGrpcTask(getBaseApplication(), this, getSActivity().mTxType, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mToValAddress, getSActivity().mAmount,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_EVM_TRANSFER) {
            new SimulErc20SendGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mMintscanToken, getSActivity().mToAddress, getSActivity().mAmounts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_STRIDE_LIQUID_STAKING) {
            new SimulLiquidStakingGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mSwapInCoin.amount, getSActivity().mHostZone.getHostDenom(),
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_STRIDE_LIQUID_UNSTAKING) {
            new SimulLiquidUnStakingGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mSwapInCoin.amount, getSActivity().mHostZone.getChainId(), getSActivity().mToAddress,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_PERSIS_LIQUID_STAKING || getSActivity().mTxType == CONST_PW_TX_PERSIS_LIQUID_REDEEM) {
            new SimulPersisLiquidGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mSwapInCoin,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc(), getSActivity().mTxType).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (result.isSuccess && result.resultData != null) {
            if ((mChainConfig.baseChain().equals(BaseChain.EVMOS_MAIN) && getSActivity().mTxType == CONST_PW_TX_EVM_TRANSFER) ||
                mChainConfig.baseChain().equals(BaseChain.CANTO_MAIN) && getSActivity().mTxType == CONST_PW_TX_EVM_TRANSFER) {
                BigDecimal gasLimit = new BigDecimal((String) result.resultData);
                BigDecimal gasPrice = new BigDecimal(result.resultData2);
                getSActivity().mHexValue = result.resultData3;
                mFee = new Fee(gasLimit.toPlainString(), Lists.newArrayList(new Coin(mChainConfig.mainDenom(), gasLimit.multiply(gasPrice).toPlainString())));

            } else {
                Abci.GasInfo gasInfo = ((Abci.GasInfo) result.resultData);
                long gasused = gasInfo.getGasUsed();
                if (mBaseChain.equals(BaseChain.PROVENANCE_MAIN) || mBaseChain.equals(BaseChain.TERITORI_MAIN))
                    gasused = (long) ((double) gasused * 1.3d);
                else gasused = (long) ((double) gasused * 1.15d);
                mFeeGasAmount = new BigDecimal(gasused);
            }
            mSimulPassed = true;
            Toast.makeText(getContext(), getString(R.string.str_gas_checked), Toast.LENGTH_SHORT).show();

        } else {
            mSimulPassed = false;
            View layout = getLayoutInflater().inflate(R.layout.item_toast_msg, getView().findViewById(R.id.toast_layout));
            TextView textView = layout.findViewById(R.id.toast_msg);
            textView.setText(result.errorMsg);

            Toast toast = new Toast(getContext());
            toast.setView(layout);
            toast.show();
        }

        onUpdateView();
        WDp.setDpCoin(getActivity(), getBaseDao(), mChainConfig, mFee.amount.get(0), mGasDenom, mGasAmount);
        int denomDecimal = WDp.getDenomDecimal(getBaseDao(), mChainConfig, mFeeData.denom);
        Asset asset = getBaseDao().getAsset(mChainConfig, mFeeData.denom);
        if (asset != null) {
            mGasValue.setText(WDp.dpAssetValue(getBaseDao(), asset.coinGeckoId, new BigDecimal(mFee.amount.get(0).amount), denomDecimal));
        }
        mSpeedTxt.setText(mFeeInfo.get(mSelectedFeeInfo).msg);
        getSActivity().onHideWaitDialog();
    }
}
