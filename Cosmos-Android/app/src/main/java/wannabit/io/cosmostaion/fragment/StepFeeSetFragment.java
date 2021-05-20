package wannabit.io.cosmostaion.fragment;

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

import com.addisonelliott.segmentedbutton.SegmentedButtonGroup;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import cosmos.base.abci.v1beta1.Abci;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulChangeRewardAddressGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulClaimRewardsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulDelegateGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulReInvestGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulRedelegateGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulSendGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulUndelegateGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulVoteGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REINVEST;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_DELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_REDELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_REWARD;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_UNDELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_VOTE;

public class StepFeeSetFragment extends BaseFragment implements View.OnClickListener, TaskListener {

    private CardView                mFeeTotalCard;
    private TextView                mFeeDenom, mFeeAmount, mFeeValue;

    private CardView                mRateControlCard;
    private TextView                mGasAmount, mGasRate, mGasFee;
    private SegmentedButtonGroup    mButtonGroup;

    private LinearLayout            mSpeedLayer;
    private ImageView               mSpeedImg;
    private TextView                mSpeedTxt;

    private LinearLayout            mBottomControlCard;
    private RelativeLayout          mBtnGasCheck;
    private Button                  mBtnBefore, mBtnNext;


    private int                     mSelectedGasPosition    = 1;
    private BigDecimal              mSelectedGasRate        = BigDecimal.ZERO;
    private BigDecimal              mEstimateGasAmount      = BigDecimal.ZERO;
    private BigDecimal              mFee                    = BigDecimal.ZERO;

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
        mFeeDenom = rootView.findViewById(R.id.fee_denom);
        mFeeAmount = rootView.findViewById(R.id.fee_amount);
        mFeeValue = rootView.findViewById(R.id.fee_value);

        mRateControlCard = rootView.findViewById(R.id.rate_control_layer);
        mGasAmount = rootView.findViewById(R.id.gas_amount);
        mGasRate = rootView.findViewById(R.id.gas_rate);
        mGasFee = rootView.findViewById(R.id.gas_fee);
        mButtonGroup = rootView.findViewById(R.id.btns_segmented);

        mSpeedLayer = rootView.findViewById(R.id.speed_layer);
        mSpeedImg = rootView.findViewById(R.id.speed_img);
        mSpeedTxt = rootView.findViewById(R.id.speed_txt);

        mBottomControlCard = rootView.findViewById(R.id.bottom_control_layer);
        mBtnGasCheck = rootView.findViewById(R.id.btn_gas_check);
        mBtnBefore = rootView.findViewById(R.id.btn_before);
        mBtnNext = rootView.findViewById(R.id.btn_next);

        WDp.DpMainDenom(getContext(), getSActivity().mBaseChain, mFeeDenom);
        mFeeTotalCard.setCardBackgroundColor(WDp.getChainBgColor(getContext(), getSActivity().mBaseChain));
        mButtonGroup.setSelectedBackground(WDp.getChainColor(getContext(), getSActivity().mBaseChain));
        mButtonGroup.setRipple(WDp.getChainColor(getContext(), getSActivity().mBaseChain));
        mEstimateGasAmount = WUtil.getEstimateGasAmount(getContext(), getSActivity().mBaseChain, getSActivity().mTxType, (getSActivity().mValAddresses.size()));
        onUpdateView();

        mButtonGroup.setOnPositionChangedListener(new SegmentedButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(int position) {
                mSelectedGasPosition = position;
                onUpdateView();
            }
        });
        mBtnGasCheck.setOnClickListener(this);
        mBtnBefore.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        super.onRefreshTab();
        mFeeTotalCard.setVisibility(View.VISIBLE);
        mRateControlCard.setVisibility(View.VISIBLE);
        mSpeedLayer.setVisibility(View.VISIBLE);
        mBottomControlCard.setVisibility(View.VISIBLE);
    }

    private void onCalculateFees() {
        mSelectedGasRate = WUtil.getGasRate(getSActivity().mBaseChain, mSelectedGasPosition);
        mFee = mSelectedGasRate.multiply(mEstimateGasAmount).setScale(0, RoundingMode.UP);
    }

    private void onUpdateView() {
        onCalculateFees();

        mFeeAmount.setText(WDp.getDpAmount2(getContext(), mFee, WDp.mainDivideDecimal(getSActivity().mBaseChain), WDp.mainDivideDecimal(getSActivity().mBaseChain)));
        mFeeValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), WDp.mainDenom(getSActivity().mBaseChain), mFee, WDp.mainDivideDecimal(getSActivity().mBaseChain)));

        mGasRate.setText(WDp.getDpGasRate(mSelectedGasRate.toPlainString()));
        mGasAmount.setText(mEstimateGasAmount.toPlainString());
        mGasFee.setText(mFee.toPlainString());

        if (mSelectedGasPosition == 0) {
            mSpeedImg.setImageDrawable(getResources().getDrawable(R.drawable.bycicle_img));
            mSpeedTxt.setText(getString(R.string.str_fee_speed_title_0));
        } else if (mSelectedGasPosition == 1) {
            mSpeedImg.setImageDrawable(getResources().getDrawable(R.drawable.car_img));
            mSpeedTxt.setText(getString(R.string.str_fee_speed_title_1));
        } else {
            mSpeedImg.setImageDrawable(getResources().getDrawable(R.drawable.rocket_img));
            mSpeedTxt.setText(getString(R.string.str_fee_speed_title_2));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnGasCheck)) {
            onSetFee();
            onSimulateTx();

        } else if (v.equals(mBtnBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            if (onCheckValidate()) {
                onSetFee();
                getSActivity().onNextStep();
            }
        }
    }

    private void onSetFee() {
        Fee fee = new Fee();
        Coin gasCoin = new Coin();
        gasCoin.denom = WDp.mainDenom(getSActivity().mBaseChain);
        gasCoin.amount = mFee.toPlainString();
        ArrayList<Coin> amount = new ArrayList<>();
        amount.add(gasCoin);
        fee.amount = amount;
        fee.gas = mEstimateGasAmount.toPlainString();
        getSActivity().mTxFee = fee;

    }

    private boolean onCheckValidate() {
        if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_SEND) {
            BigDecimal available = getBaseDao().getAvailable(getSActivity().mDenom);
            if (getSActivity().mDenom.equals(WDp.mainDenom(getSActivity().mBaseChain))) {
                BigDecimal toSend = new BigDecimal(getSActivity().mAmounts.get(0).amount);
                if ((toSend.add(mFee)).compareTo(available) > 0) {
                    Toast.makeText(getContext(), getString(R.string.error_not_enough_fee), Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                if (mFee.compareTo(available) > 0) {
                    Toast.makeText(getContext(), getString(R.string.error_not_enough_fee), Toast.LENGTH_SHORT).show();
                    return false;
                }
            }

        } else if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_DELEGATE) {
            BigDecimal delegatable = getBaseDao().getDelegatable(WDp.mainDenom(getSActivity().mBaseChain));
            BigDecimal todelegate = new BigDecimal(getSActivity().mAmount.amount);
            if ((todelegate.add(mFee)).compareTo(delegatable) > 0) {
                Toast.makeText(getContext(), getString(R.string.error_not_enough_fee), Toast.LENGTH_SHORT).show();
                return false;
            }

        } else if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_REWARD) {
            BigDecimal available = getBaseDao().getAvailable(WDp.mainDenom(getSActivity().mBaseChain));
            if (mFee.compareTo(available) > 0) {
                Toast.makeText(getContext(), getString(R.string.error_not_enough_fee), Toast.LENGTH_SHORT).show();
                return false;
            }
            BigDecimal rewardSum = BigDecimal.ZERO;
            for (String opAddress: getSActivity().mValAddresses) {
                rewardSum = rewardSum.add(getSActivity().getBaseDao().getReward(WDp.mainDenom(getSActivity().mBaseChain), opAddress));
            }

            if (mFee.compareTo(rewardSum) > 0) {
                Toast.makeText(getContext(), getString(R.string.error_waste_fee), Toast.LENGTH_SHORT).show();
                return false;
            }

        } else if (getSActivity().mTxType == CONST_PW_TX_REINVEST) {
            BigDecimal available = getBaseDao().getAvailable(WDp.mainDenom(getSActivity().mBaseChain));
            if (mFee.compareTo(available) > 0) {
                Toast.makeText(getContext(), getString(R.string.error_not_enough_fee), Toast.LENGTH_SHORT).show();
                return false;
            }

            BigDecimal reinvest = new BigDecimal(getSActivity().mAmount.amount);
            if (mFee.compareTo(reinvest) > 0) {
                Toast.makeText(getContext(), getString(R.string.error_waste_fee), Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        return true;
    }

    private BaseBroadCastActivity getSActivity() {
        return (BaseBroadCastActivity)getBaseActivity();
    }

    private void onSimulateTx() {
        Intent intent = new Intent(getSActivity(), PasswordCheckActivity.class);
        intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_SIMPLE_CHECK);
        startActivityForResult(intent, BaseConstant.CONST_PW_SIMPLE_CHECK);
        getSActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            getSActivity().onShowWaitDialog();
            if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_SEND) {
                new SimulSendGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount,  getSActivity().mToAddress,  getSActivity().mAmounts,
                        getSActivity().mTxMemo, getSActivity().mTxFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            } else if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_DELEGATE) {
                new SimulDelegateGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mValAddress, getSActivity().mAmount,
                        getSActivity().mTxMemo, getSActivity().mTxFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            } else if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                new SimulUndelegateGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mValAddress, getSActivity().mAmount,
                        getSActivity().mTxMemo, getSActivity().mTxFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            } else if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                new SimulRedelegateGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mValAddress, getSActivity().mToValAddress, getSActivity().mAmount,
                        getSActivity().mTxMemo, getSActivity().mTxFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            } else if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_REWARD) {
                new SimulClaimRewardsGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mValAddresses,
                        getSActivity().mTxMemo, getSActivity().mTxFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            } else if (getSActivity().mTxType == CONST_PW_TX_REINVEST) {
                new SimulReInvestGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mValAddress, getSActivity().mAmount,
                        getSActivity().mTxMemo, getSActivity().mTxFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            } else if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                new SimulChangeRewardAddressGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mNewRewardAddress,
                        getSActivity().mTxMemo, getSActivity().mTxFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            } else if (getSActivity().mTxType == CONST_PW_TX_VOTE) {
                new SimulVoteGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount, getSActivity().mProposalId, getSActivity().mOpinion,
                        getSActivity().mTxMemo, getSActivity().mTxFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (result.isSuccess && result.resultData != null) {
            Abci.GasInfo gasInfo = ((Abci.GasInfo)result.resultData);
            long gasused = gasInfo.getGasUsed();
            gasused = (long)((double)gasused * 1.1d);
//            WLog.w("padding gasused " +  gasused);
            mEstimateGasAmount = new BigDecimal(gasused);
            onUpdateView();
            Toast.makeText(getContext(), getString(R.string.str_apply_estimate_gas_amount), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.str_network_error_title), Toast.LENGTH_SHORT).show();
        }
        getSActivity().onHideWaitDialog();
    }
}
