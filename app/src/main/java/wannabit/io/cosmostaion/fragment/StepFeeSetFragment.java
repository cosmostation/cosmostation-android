package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_BORROW_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_CLAIM_INCENTIVE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_CREATE_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_DELETE_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_DELETE_DOMAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_DEPOSIT_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_DEPOSIT_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_DRAW_DEBT_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_EXECUTE_CONTRACT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_IBC_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_KAVA_EXIT_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_KAVA_JOIN_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_KAVA_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_MINT_NFT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OSMOSIS_BEGIN_UNBONDING;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OSMOSIS_EARNING;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OSMOSIS_EXIT_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OSMOSIS_JOIN_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OSMOSIS_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_PROFILE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REGISTER_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REGISTER_DOMAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REINVEST;
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
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_WITHDRAW_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_WITHDRAW_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.NFT_INFURA;

import android.app.Activity;
import android.content.Intent;
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
import osmosis.lockup.Lock;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.FeeInfo;
import wannabit.io.cosmostaion.dao.StationNFTData;
import wannabit.io.cosmostaion.dialog.SwapCoinListDialog;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulChangeRewardAddressGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulClaimRewardsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulCreateProfileGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulCw20SendGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulDelegateGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulDeleteAccountGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulDeleteDomainGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulIBCTransferGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaBorrowHardGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaClaimIncentiveAllGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaCreateCdpGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaDepositCdpGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaDepositGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaDepositHardGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaDrawDebtCdpGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaRepayCdpGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaRepayHardGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaSwapGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaWithDrawCdpGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaWithdrawGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulKavaWithdrawHardGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulMintNFTGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulOsmosisBeginUnbondingGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulOsmosisExitPoolGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulOsmosisJoinPoolGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulOsmosisStartLockGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulOsmosisSwaplnGrpcTask;
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

public class StepFeeSetFragment extends BaseFragment implements View.OnClickListener, TaskListener {
    public final static int SELECT_FEE_DENOM = 8502;

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
    private int mSelectedFeeInfo = 1;
    private int mSelectedFeeData = 0;

    public static StepFeeSetFragment newInstance(Bundle bundle) {
        StepFeeSetFragment fragment = new StepFeeSetFragment();
        fragment.setArguments(bundle);
        return fragment;
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
        mFeeInfo = WDp.getFeeInfos(getActivity(), mChainConfig);

        mFeeTotalCard.setCardBackgroundColor(ContextCompat.getColor(getActivity(), mChainConfig.chainBgColor()));
        WDp.setDpSymbolImg(getBaseDao(), mChainConfig, mChainConfig.mainDenom(), mFeeCoinImg);
        WDp.setDpSymbol(getActivity(), getBaseDao(), mChainConfig, mChainConfig.mainDenom(), mFeeCoinDenom);

        mButtonGroup.setSelectedBackground(ContextCompat.getColor(getActivity(), mChainConfig.chainColor()));
        mButtonGroup.setRipple(ContextCompat.getColor(getActivity(), mChainConfig.chainColor()));

        for (int i = 0; i < mFeeInfo.size(); i++) {
            View segmentView = inflater.inflate(R.layout.item_segmented_fee, null);
            mButtonGroup.addView(segmentView, i, new LinearLayout.LayoutParams(0, DisplayUtils.dpToPx(getActivity(), 32), 1));

            SegmentedButton btnTxt = segmentView.findViewById(R.id.btn_title);
            btnTxt.setText(mFeeInfo.get(i).tiile);
        }

        mSelectedFeeInfo = mChainConfig.gasDefault();
        mButtonGroup.setPosition(mSelectedFeeInfo, false);
        mButtonGroup.setOnPositionChangedListener(new SegmentedButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(int position) {
                mSelectedFeeInfo = position;
                onCheckTxType();
            }
        });
        onUpdateView();

        mBtnSelectFeeCoin.setOnClickListener(this);
        mBtnBefore.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        return rootView;
    }

    private void onCalculateFees() {
        mFeeData = mFeeInfo.get(mSelectedFeeInfo).feeDatas.get(mSelectedFeeData);
        if (mBaseChain.equals(BaseChain.SIF_MAIN)) {
            mFeeCoin = new Coin(mFeeData.denom, "100000000000000000");
        } else {
            BigDecimal amount = mFeeData.gasRate.multiply(mFeeGasAmount).setScale(0, RoundingMode.UP);
            mFeeCoin = new Coin(mFeeData.denom, amount.toPlainString());
        }
        mFee = new Fee(mFeeGasAmount.toPlainString(), Lists.newArrayList(mFeeCoin));
    }

    private void onUpdateView() {
        onCalculateFees();
        WDp.setDpSymbolImg(getBaseDao(), mChainConfig, mFeeData.denom, mFeeCoinImg);
        WDp.setDpSymbol(getActivity(), getBaseDao(), mChainConfig, mFeeData.denom, mFeeCoinDenom);
        WDp.setDpCoin(getActivity(), getBaseDao(), mChainConfig, mFee.amount.get(0), mGasDenom, mGasAmount);

        int denomDecimal = WDp.getDenomDecimal(getBaseDao(), mChainConfig, mFeeData.denom);
        mGasValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), mFeeData.denom, new BigDecimal(mFee.amount.get(0).amount), denomDecimal));

        mSpeedTxt.setText(mFeeInfo.get(mSelectedFeeInfo).msg);
    }

    @Override
    public void onRefreshTab() {
        onCheckTxType();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnSelectFeeCoin)) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("feeDatas", mFeeInfo.get(mSelectedFeeInfo).feeDatas);
            SwapCoinListDialog dialog = SwapCoinListDialog.newInstance(bundle);
            dialog.setTargetFragment(this, SELECT_FEE_DENOM);
            getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

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
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_DELEGATE) {
            new SimulDelegateGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mValAddress, getSActivity().mAmount,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
            new SimulUndelegateGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mValAddress, getSActivity().mAmount,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_REDELEGATE) {
            new SimulRedelegateGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mValAddress, getSActivity().mToValAddress, getSActivity().mAmount,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_REWARD) {
            new SimulClaimRewardsGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mValAddresses,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_REINVEST) {
            new SimulReInvestGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mValAddress, getSActivity().mAmount,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
            new SimulChangeRewardAddressGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mNewRewardAddress,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_VOTE) {
            new SimulVoteGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mSelectedOpinion,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

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
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        } else if (getSActivity().mTxType == CONST_PW_TX_OSMOSIS_JOIN_POOL) {
            new SimulOsmosisJoinPoolGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain,
                    getSActivity().mOsmosisPoolId, getSActivity().mPoolCoin0, getSActivity().mPoolCoin1, getSActivity().mLpToken.amount,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_OSMOSIS_EXIT_POOL) {
            new SimulOsmosisExitPoolGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain,
                    getSActivity().mOsmosisPoolId, getSActivity().mPoolCoin0, getSActivity().mPoolCoin1, getSActivity().mLpToken.amount,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_OSMOSIS_EARNING) {
            new SimulOsmosisStartLockGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain,
                    getSActivity().mOsmosisLockupDuration, getSActivity().mLpToken,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_OSMOSIS_BEGIN_UNBONDING) {
            ArrayList<Long> tempList = new ArrayList<>();
            for (Lock.PeriodLock lockup : getSActivity().mOsmosisLockups) {
                tempList.add(lockup.getID());
            }
            new SimulOsmosisBeginUnbondingGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain,
                    tempList, getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_IBC_TRANSFER) {
            new SimulIBCTransferGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mToAddress,
                    getSActivity().mAmounts.get(0).denom, getSActivity().mAmounts.get(0).amount, getSActivity().mPath.port_id, getSActivity().mPath.channel_id, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_SIF_SWAP) {
            new SimulSifSwapGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address,
                    getSActivity().mSifSwapInCoin.denom, getSActivity().mSifSwapInCoin.amount, getSActivity().mSifSwapOutCoin.denom, getSActivity().mSifSwapOutCoin.amount,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

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
        } else if (getSActivity().mTxType == CONST_PW_TX_PROFILE) {
            String profileUri = "";
            String coverUri = "";
            if (getSActivity().mProfileImg != null) {
                profileUri = "https://ipfs.infura.io/ipfs/" + getSActivity().mProfileImg;
            } else {
                profileUri = "";
            }
            if (getSActivity().mCoverImg != null) {
                coverUri = "https://ipfs.infura.io/ipfs/" + getSActivity().mCoverImg;
            } else {
                coverUri = "";
            }
            new SimulCreateProfileGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mDtag, getSActivity().mNickname, getSActivity().mBio,
                    profileUri, coverUri, getSActivity().mAccount.address, getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

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
            new SimulKavaClaimIncentiveAllGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mIncentiveMultiplier, getSActivity().getBaseDao(),
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getSActivity().mTxType == CONST_PW_TX_EXECUTE_CONTRACT) {
            new SimulCw20SendGrpcTask(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mAccount.address, getSActivity().mToAddress, getSActivity().mContractAddress, getSActivity().mAmounts,
                    getSActivity().mTxMemo, mFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_FEE_DENOM && resultCode == Activity.RESULT_OK) {
            mSelectedFeeData = data.getIntExtra("position", -1);
            onCheckTxType();
            onUpdateView();
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (result.isSuccess && result.resultData != null) {
            Abci.GasInfo gasInfo = ((Abci.GasInfo) result.resultData);
            long gasused = gasInfo.getGasUsed();
            gasused = (long) ((double) gasused * 1.1d);
            mFeeGasAmount = new BigDecimal(gasused);
            onUpdateView();
            mSimulPassed = true;
            Toast.makeText(getContext(), getString(R.string.str_gas_checked), Toast.LENGTH_SHORT).show();
        } else {
            mSimulPassed = false;
            Toast.makeText(getContext(), getString(R.string.str_network_error_title), Toast.LENGTH_SHORT).show();
        }
        getSActivity().onHideWaitDialog();
    }
}
