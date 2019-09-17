package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_BNB;

public class SendStep4Fragment extends BaseFragment implements View.OnClickListener {

    private TextView        mSendAmount;
    private TextView        mFeeAmount;
    private RelativeLayout  mTotalSpendLayer;
    private TextView        mTotalSpendAmount, mTotalPrice;
    private TextView        mCurrentBalance, mRemainingBalance, mRemainingPrice;
    private TextView        mRecipientAddress, mMemo;
    private Button          mBeforeBtn, mConfirmBtn;
    private TextView        mDenomSendAmount, mDenomFeeType, mDenomTotalSpend, mDenomCurrentAmount, mDenomRemainAmount;

    public static SendStep4Fragment newInstance(Bundle bundle) {
        SendStep4Fragment fragment = new SendStep4Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView       = inflater.inflate(R.layout.fragment_send_step4, container, false);
        mSendAmount         = rootView.findViewById(R.id.send_amount);
        mFeeAmount          = rootView.findViewById(R.id.send_fees);
        mTotalSpendLayer    = rootView.findViewById(R.id.spend_total_layer);
        mTotalSpendAmount   = rootView.findViewById(R.id.spend_total);
        mTotalPrice         = rootView.findViewById(R.id.spend_total_price);
        mCurrentBalance     = rootView.findViewById(R.id.current_available_atom);
        mRemainingBalance   = rootView.findViewById(R.id.remaining_available_atom);
        mRemainingPrice     = rootView.findViewById(R.id.remaining_price);
        mRecipientAddress   = rootView.findViewById(R.id.recipient_address);
        mMemo               = rootView.findViewById(R.id.memo);
        mBeforeBtn          = rootView.findViewById(R.id.btn_before);
        mConfirmBtn         = rootView.findViewById(R.id.btn_confirm);
        mDenomSendAmount    = rootView.findViewById(R.id.send_amount_title);
        mDenomFeeType       = rootView.findViewById(R.id.send_fees_type);
        mDenomTotalSpend    = rootView.findViewById(R.id.spend_total_type);
        mDenomCurrentAmount = rootView.findViewById(R.id.current_available_title);
        mDenomRemainAmount  = rootView.findViewById(R.id.remaining_available_title);

//        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomSendAmount);
        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomFeeType);
        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomTotalSpend);
//        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomCurrentAmount);
//        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomRemainAmount);


        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onRefreshTab() {
        BigDecimal toSendAmount   = new BigDecimal(getSActivity().mTargetCoins.get(0).amount);
        BigDecimal feeAmount      = new BigDecimal(getSActivity().mTargetFee.amount.get(0).amount);
        if (getSActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            BigDecimal currentAvai  = getSActivity().mAccount.getAtomBalance();
            mSendAmount.setText(WDp.getDpAmount(getContext(), toSendAmount, 6, getSActivity().mBaseChain));
            mFeeAmount.setText(WDp.getDpAmount(getContext(), feeAmount, 6, getSActivity().mBaseChain));
            mTotalSpendAmount.setText(WDp.getDpAmount(getContext(), feeAmount.add(toSendAmount), 6, getSActivity().mBaseChain));
            BigDecimal spendTotal = BigDecimal.ZERO;
            if(getBaseDao().getCurrency() != 5) {
                spendTotal = feeAmount.add(toSendAmount).multiply(new BigDecimal(""+getBaseDao().getLastAtomTic())).divide(new BigDecimal("1000000"), 2, RoundingMode.DOWN);
            } else {
                spendTotal = feeAmount.add(toSendAmount).multiply(new BigDecimal(""+getBaseDao().getLastAtomTic())).divide(new BigDecimal("1000000"), 8, RoundingMode.DOWN);
            }
            mTotalPrice.setText(WDp.getPriceApproximatelyDp(getSActivity(), spendTotal, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));

            mCurrentBalance.setText(WDp.getDpAmount(getContext(), currentAvai, 6, getSActivity().mBaseChain));
            mRemainingBalance.setText(WDp.getDpAmount(getContext(), currentAvai.subtract(toSendAmount).subtract(feeAmount), 6, getSActivity().mBaseChain));
            BigDecimal remainTotal = BigDecimal.ZERO;
            if(getBaseDao().getCurrency() != 5) {
                remainTotal = currentAvai.subtract(toSendAmount).subtract(feeAmount).multiply(new BigDecimal(""+getBaseDao().getLastAtomTic())).divide(new BigDecimal("1000000"), 2, RoundingMode.DOWN);
            } else {
                remainTotal = currentAvai.subtract(toSendAmount).subtract(feeAmount).multiply(new BigDecimal(""+getBaseDao().getLastAtomTic())).divide(new BigDecimal("1000000"), 8, RoundingMode.DOWN);
            }
            mRemainingPrice.setText(WDp.getPriceApproximatelyDp(getSActivity(), remainTotal, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));

        } else if (getSActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            BigDecimal currentAvai  = getSActivity().mAccount.getIrisBalance();
            mSendAmount.setText(WDp.getDpAmount(getContext(), toSendAmount, 18, getSActivity().mBaseChain));
            mFeeAmount.setText(WDp.getDpAmount(getContext(), feeAmount, 18, getSActivity().mBaseChain));
            mTotalSpendAmount.setText(WDp.getDpAmount(getContext(), feeAmount.add(toSendAmount), 18, getSActivity().mBaseChain));
            BigDecimal spendTotal = BigDecimal.ZERO;
            if(getBaseDao().getCurrency() != 5) {
                spendTotal = feeAmount.add(toSendAmount).multiply(new BigDecimal(""+getBaseDao().getLastIrisTic())).divide(new BigDecimal("1000000000000000000"), 2, RoundingMode.DOWN);
            } else {
                spendTotal = feeAmount.add(toSendAmount).multiply(new BigDecimal(""+getBaseDao().getLastIrisTic())).divide(new BigDecimal("1000000000000000000"), 8, RoundingMode.DOWN);
            }
            mTotalPrice.setText(WDp.getPriceApproximatelyDp(getSActivity(), spendTotal, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));

            mCurrentBalance.setText(WDp.getDpAmount(getContext(), currentAvai, 18, getSActivity().mBaseChain));
            mRemainingBalance.setText(WDp.getDpAmount(getContext(), currentAvai.subtract(toSendAmount).subtract(feeAmount), 18, getSActivity().mBaseChain));

            BigDecimal remainTotal = BigDecimal.ZERO;
            if(getBaseDao().getCurrency() != 5) {
                remainTotal = currentAvai.subtract(toSendAmount).subtract(feeAmount).multiply(new BigDecimal(""+getBaseDao().getLastIrisTic())).divide(new BigDecimal("1000000000000000000"), 2, RoundingMode.DOWN);
            } else {
                remainTotal = currentAvai.subtract(toSendAmount).subtract(feeAmount).multiply(new BigDecimal(""+getBaseDao().getLastIrisTic())).divide(new BigDecimal("1000000000000000000"), 8, RoundingMode.DOWN);
            }
            mRemainingPrice.setText(WDp.getPriceApproximatelyDp(getSActivity(), remainTotal, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));

        } else if (getSActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
            mDenomSendAmount.setText(getSActivity().mBnbToken.original_symbol.toUpperCase());
            mDenomCurrentAmount.setText(getSActivity().mBnbToken.original_symbol.toUpperCase());
            mDenomRemainAmount.setText(getSActivity().mBnbToken.original_symbol.toUpperCase());

            mSendAmount.setText(WDp.getDpAmount(getContext(), toSendAmount, 8, getSActivity().mBaseChain));
            mFeeAmount.setText(WDp.getDpAmount(getContext(), feeAmount, 8, getSActivity().mBaseChain));

            if (getSActivity().mBnbToken.symbol.equals(COSMOS_BNB)) {
                mDenomSendAmount.setTextColor(getResources().getColor(R.color.colorBnb));
                mDenomCurrentAmount.setTextColor(getResources().getColor(R.color.colorBnb));
                mDenomRemainAmount.setTextColor(getResources().getColor(R.color.colorBnb));

                mTotalSpendAmount.setText(WDp.getDpAmount(getContext(), feeAmount.add(toSendAmount), 8, getSActivity().mBaseChain));
                BigDecimal spendTotal = BigDecimal.ZERO;
                if(getBaseDao().getCurrency() != 5) {
                    spendTotal = feeAmount.add(toSendAmount).multiply(new BigDecimal(""+getBaseDao().getLastBnbTic()));
                } else {
                    spendTotal = feeAmount.add(toSendAmount).multiply(new BigDecimal(""+getBaseDao().getLastBnbTic()));
                }
                mTotalPrice.setText(WDp.getPriceApproximatelyDp(getSActivity(), spendTotal, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));

                BigDecimal currentAvai  = getSActivity().mAccount.getBnbBalance();
                mCurrentBalance.setText(WDp.getDpAmount(getContext(), currentAvai, 8, getSActivity().mBaseChain));
                mRemainingBalance.setText(WDp.getDpAmount(getContext(), currentAvai.subtract(toSendAmount).subtract(feeAmount), 8, getSActivity().mBaseChain));

                BigDecimal remainTotal = BigDecimal.ZERO;
                if(getBaseDao().getCurrency() != 5) {
                    remainTotal = currentAvai.subtract(toSendAmount).subtract(feeAmount).multiply(new BigDecimal(""+getBaseDao().getLastBnbTic()));
                } else {
                    remainTotal = currentAvai.subtract(toSendAmount).subtract(feeAmount).multiply(new BigDecimal(""+getBaseDao().getLastBnbTic()));
                }
                mRemainingPrice.setText(WDp.getPriceApproximatelyDp(getSActivity(), remainTotal, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));

            } else {
                mTotalSpendLayer.setVisibility(View.GONE);
                mTotalPrice.setVisibility(View.GONE);
                mRemainingPrice.setVisibility(View.GONE);

                BigDecimal currentAvai  = getSActivity().mAccount.getTokenBalance(getSActivity().mBnbToken.symbol);
                mCurrentBalance.setText(WDp.getDpAmount(getContext(), currentAvai, 8, getSActivity().mBaseChain));
                mRemainingBalance.setText(WDp.getDpAmount(getContext(), currentAvai.subtract(toSendAmount), 8, getSActivity().mBaseChain));
            }

        }
        mRecipientAddress.setText(getSActivity().mTagetAddress);
        mMemo.setText(getSActivity().mTargetMemo);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartSend();

        }

    }

    private SendActivity getSActivity() {
        return (SendActivity)getBaseActivity();
    }
}
