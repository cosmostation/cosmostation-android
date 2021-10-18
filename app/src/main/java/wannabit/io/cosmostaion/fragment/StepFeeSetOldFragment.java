package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.addisonelliott.segmentedbutton.SegmentedButtonGroup;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_KAVA_JOIN_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_KAVA_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REINVEST;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_DELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_REWARD;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;

public class StepFeeSetOldFragment extends BaseFragment implements View.OnClickListener {

    private CardView                mFeeTotalCard;
    private TextView                mFeeDenom, mFeeAmount, mFeeValue;

    private CardView                mRateControlCard;
    private TextView                mGasAmount, mGasRate, mGasFee;
    private SegmentedButtonGroup    mButtonGroup;

    private LinearLayout            mSpeedLayer;
    private ImageView               mSpeedImg;
    private TextView                mSpeedTxt;

    private LinearLayout            mBottomControlCard;
    private Button                  mBtnBefore, mBtnNext;

    private int                     mSelectedGasPosition    = 1;
    private BigDecimal              mSelectedGasRate        = BigDecimal.ZERO;
    private BigDecimal              mEstimateGasAmount      = BigDecimal.ZERO;
    private BigDecimal              mFee                    = BigDecimal.ZERO;

    public static StepFeeSetOldFragment newInstance(Bundle bundle) {
        StepFeeSetOldFragment fragment = new StepFeeSetOldFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tx_step_fee_old, container, false);
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
        mBtnBefore = rootView.findViewById(R.id.btn_before);
        mBtnNext = rootView.findViewById(R.id.btn_next);

        WDp.DpMainDenom(getContext(), getSActivity().mBaseChain, mFeeDenom);
        mFeeTotalCard.setCardBackgroundColor(WDp.getChainBgColor(getContext(), getSActivity().mBaseChain));
        mButtonGroup.setSelectedBackground(WDp.getChainColor(getContext(), getSActivity().mBaseChain));
        mButtonGroup.setRipple(WDp.getChainColor(getContext(), getSActivity().mBaseChain));

        if (getSActivity().mBaseChain.equals(OKEX_MAIN) || getSActivity().mBaseChain.equals(OK_TEST)) {
            int myValidatorCnt = 0;
            if (getBaseDao().mOkStaking != null && getBaseDao().mOkStaking.validator_address != null) {
                myValidatorCnt = getBaseDao().mOkStaking.validator_address.size();
            }
            mEstimateGasAmount = WUtil.getEstimateGasAmount(getContext(), getSActivity().mBaseChain, getSActivity().mTxType, myValidatorCnt);
        } else {
            mEstimateGasAmount = WUtil.getEstimateGasAmount(getContext(), getSActivity().mBaseChain, getSActivity().mTxType, (getSActivity().mValidators.size()));
        }
        onUpdateView();

        mButtonGroup.setOnPositionChangedListener(new SegmentedButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(int position) {
                mSelectedGasPosition = position;
                onUpdateView();
            }
        });
        mBtnBefore.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        super.onRefreshTab();
        mFeeTotalCard.setVisibility(View.VISIBLE);
        if (getSActivity().mBaseChain.equals(KAVA_MAIN) || getSActivity().mBaseChain.equals(BAND_MAIN) || getSActivity().mBaseChain.equals(KAVA_TEST)) {
            mRateControlCard.setVisibility(View.VISIBLE);
        }
        mSpeedLayer.setVisibility(View.VISIBLE);
        mBottomControlCard.setVisibility(View.VISIBLE);
    }

    private void onCalculateFees() {
        mSelectedGasRate = WUtil.getGasRate(getSActivity().mBaseChain, mSelectedGasPosition);
        if (getSActivity().mBaseChain.equals(BNB_MAIN) || getSActivity().mBaseChain.equals(BNB_TEST)) {
            mFee = new BigDecimal(FEE_BNB_SEND);
        } else if (getSActivity().mBaseChain.equals(OKEX_MAIN) || getSActivity().mBaseChain.equals(OK_TEST)) {
            mFee = mSelectedGasRate.multiply(mEstimateGasAmount).setScale(18, RoundingMode.UP);
        } else {
            mFee = mSelectedGasRate.multiply(mEstimateGasAmount).setScale(0, RoundingMode.UP);
        }

    }

    private void onUpdateView() {
        onCalculateFees();

        mFeeAmount.setText(WDp.getDpAmount2(getContext(), mFee, WDp.mainDivideDecimal(getSActivity().mBaseChain), WDp.mainDisplayDecimal(getSActivity().mBaseChain)));
        mFeeValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), WDp.mainDenom(getSActivity().mBaseChain), mFee, WDp.mainDivideDecimal(getSActivity().mBaseChain)));

        mGasRate.setText(WDp.getDpGasRate(mSelectedGasRate.toPlainString()));
        mGasAmount.setText(mEstimateGasAmount.toPlainString());
        mGasFee.setText(mFee.toPlainString());

        if (getSActivity().mBaseChain.equals(KAVA_MAIN) || getSActivity().mBaseChain.equals(BAND_MAIN) || getSActivity().mBaseChain.equals(KAVA_TEST)) {
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

        } else {
            mSpeedImg.setImageDrawable(getResources().getDrawable(R.drawable.rocket_img));
            mSpeedTxt.setText(getString(R.string.str_fee_speed_title_2));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            if (onCheckValidate()) {
                onSetFee();
                getSActivity().onNextStep();
            }
        }
    }


    private boolean onCheckValidate() {
        String mainDenom = WDp.mainDenom(getSActivity().mBaseChain);
        if (getSActivity().mBaseChain.equals(KAVA_MAIN) || getSActivity().mBaseChain.equals(BAND_MAIN) || getSActivity().mBaseChain.equals(KAVA_TEST)) {
            BigDecimal available = getBaseDao().availableAmount(getSActivity().mDenom);
            if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_SEND) {
                if (getSActivity().mDenom.equals(mainDenom)) {
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

            } else if(getSActivity().mTxType == CONST_PW_TX_SIMPLE_DELEGATE) {
                BigDecimal delegatable = getBaseDao().delegatableAmount(mainDenom);
                BigDecimal todelegate = new BigDecimal(getSActivity().mAmount.amount);
                if ((todelegate.add(mFee)).compareTo(delegatable) > 0) {
                    Toast.makeText(getContext(), getString(R.string.error_not_enough_fee), Toast.LENGTH_SHORT).show();
                    return false;
                }

            } else if(getSActivity().mTxType == CONST_PW_TX_SIMPLE_REWARD) {
                BigDecimal rewardable = getBaseDao().availableAmount(mainDenom);
                if (mFee.compareTo(rewardable) > 0) {
                    Toast.makeText(getContext(), getString(R.string.error_not_enough_fee), Toast.LENGTH_SHORT).show();
                    return false;
                }

                BigDecimal rewardSum = BigDecimal.ZERO;
                for (Coin coin:getSActivity().mRewards) {
                    rewardSum = rewardSum.add(new BigDecimal(coin.amount));
                }

                if (mFee.compareTo(rewardSum) > 0) {
                    Toast.makeText(getContext(), getString(R.string.error_waste_fee), Toast.LENGTH_SHORT).show();
                    return false;
                }

            } else if (getSActivity().mTxType == CONST_PW_TX_REINVEST) {
                BigDecimal reinvestable = getBaseDao().availableAmount(mainDenom);
                if (mFee.compareTo(reinvestable) > 0) {
                    Toast.makeText(getContext(), getString(R.string.error_not_enough_fee), Toast.LENGTH_SHORT).show();
                    return false;
                }

                BigDecimal reinvest = new BigDecimal(getSActivity().mAmount.amount);
                if (mFee.compareTo(reinvest) > 0) {
                    Toast.makeText(getContext(), getString(R.string.error_waste_fee), Toast.LENGTH_SHORT).show();
                    return false;
                }

            } else if (getSActivity().mTxType == CONST_PW_TX_KAVA_SWAP) {
                BigDecimal swapable = getBaseDao().availableAmount(mainDenom);
                if (getSActivity().mSwapInCoin.denom.equalsIgnoreCase(mainDenom)) {
                    BigDecimal spend = new BigDecimal(getSActivity().mSwapInCoin.amount).add(mFee);
                    if (swapable.compareTo(spend) < 0) {
                        Toast.makeText(getContext(), getString(R.string.error_not_enough_fee), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }

            } else if (getSActivity().mTxType == CONST_PW_TX_KAVA_JOIN_POOL) {
                BigDecimal depositable = getBaseDao().availableAmount(mainDenom);
                if (getSActivity().mPoolCoin0.denom.equalsIgnoreCase(mainDenom)) {
                    BigDecimal spend = new BigDecimal(getSActivity().mPoolCoin0.amount).add(mFee);
                    if (depositable.compareTo(spend) < 0) {
                        Toast.makeText(getContext(), getString(R.string.error_not_enough_fee), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
                if (getSActivity().mPoolCoin1.denom.equalsIgnoreCase(mainDenom)) {
                    BigDecimal spend = new BigDecimal(getSActivity().mPoolCoin1.amount).add(mFee);
                    if (depositable.compareTo(spend) < 0) {
                        Toast.makeText(getContext(), getString(R.string.error_not_enough_fee), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            }
        }
        return true;
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


    private BaseBroadCastActivity getSActivity() {
        return (BaseBroadCastActivity)getBaseActivity();
    }
}
