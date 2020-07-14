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

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class SendStep4Fragment extends BaseFragment implements View.OnClickListener {

    private TextView        mSendAmount;
    private TextView        mFeeAmount;
    private RelativeLayout  mTotalSpendLayer;
    private TextView        mTotalSpendAmount, mTotalPrice;
    private TextView        mCurrentBalance, mRemainingBalance, mRemainingPrice;
    private TextView        mRecipientAddress, mRecipientStartName, mMemo;
    private Button          mBeforeBtn, mConfirmBtn;
    private TextView        mDenomSendAmount, mDenomFeeType, mDenomTotalSpend, mDenomCurrentAmount, mDenomRemainAmount;
    private int             mDpDecimal = 6;

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
        mRecipientStartName = rootView.findViewById(R.id.recipient_starname);
        mMemo               = rootView.findViewById(R.id.memo);
        mBeforeBtn          = rootView.findViewById(R.id.btn_before);
        mConfirmBtn         = rootView.findViewById(R.id.btn_confirm);
        mDenomSendAmount    = rootView.findViewById(R.id.send_amount_title);
        mDenomFeeType       = rootView.findViewById(R.id.send_fees_type);
        mDenomTotalSpend    = rootView.findViewById(R.id.spend_total_type);
        mDenomCurrentAmount = rootView.findViewById(R.id.current_available_title);
        mDenomRemainAmount  = rootView.findViewById(R.id.remaining_available_title);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomFeeType);
        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomTotalSpend);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onRefreshTab() {
        BigDecimal toSendAmount   = new BigDecimal(getSActivity().mTargetCoins.get(0).amount);
        BigDecimal feeAmount      = new BigDecimal(getSActivity().mTargetFee.amount.get(0).amount);
        if (getSActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            mDpDecimal = 6;
            mDenomSendAmount.setTextColor(getResources().getColor(R.color.colorAtom));
            mDenomCurrentAmount.setTextColor(getResources().getColor(R.color.colorAtom));
            mDenomRemainAmount.setTextColor(getResources().getColor(R.color.colorAtom));

            mSendAmount.setText(WDp.getDpAmount(getContext(), toSendAmount, 6, getSActivity().mBaseChain));
            mFeeAmount.setText(WDp.getDpAmount(getContext(), feeAmount, 6, getSActivity().mBaseChain));
            mTotalSpendAmount.setText(WDp.getDpAmount(getContext(), feeAmount.add(toSendAmount), 6, getSActivity().mBaseChain));
            mTotalPrice.setText(WDp.getValueOfAtom(getContext(), getBaseDao(), feeAmount.add(toSendAmount)));

            BigDecimal currentAvai  = getSActivity().mAccount.getAtomBalance();
            mCurrentBalance.setText(WDp.getDpAmount(getContext(), currentAvai, 6, getSActivity().mBaseChain));
            mRemainingBalance.setText(WDp.getDpAmount(getContext(), currentAvai.subtract(toSendAmount).subtract(feeAmount), 6, getSActivity().mBaseChain));
            mRemainingPrice.setText(WDp.getValueOfAtom(getContext(), getBaseDao(), currentAvai.subtract(toSendAmount).subtract(feeAmount)));

        } else if (getSActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            mDpDecimal = getSActivity().mIrisToken.base_token.decimal;
            mDenomSendAmount.setText(getSActivity().mIrisToken.base_token.symbol.toUpperCase());
            mDenomCurrentAmount.setText(getSActivity().mIrisToken.base_token.symbol.toUpperCase());
            mDenomRemainAmount.setText(getSActivity().mIrisToken.base_token.symbol.toUpperCase());

            mSendAmount.setText(WDp.getDpAmount(getContext(), toSendAmount, getSActivity().mIrisToken.base_token.decimal, getSActivity().mBaseChain));
            mFeeAmount.setText(WDp.getDpAmount(getContext(), feeAmount, 18, getSActivity().mBaseChain));

            if (getSActivity().mIrisToken.base_token.id.equals(TOKEN_IRIS)) {
                mDenomSendAmount.setTextColor(getResources().getColor(R.color.colorIris));
                mDenomCurrentAmount.setTextColor(getResources().getColor(R.color.colorIris));
                mDenomRemainAmount.setTextColor(getResources().getColor(R.color.colorIris));

                mTotalSpendAmount.setText(WDp.getDpAmount(getContext(), feeAmount.add(toSendAmount), getSActivity().mIrisToken.base_token.decimal, getSActivity().mBaseChain));
                mTotalPrice.setText(WDp.getValueOfIris(getContext(), getBaseDao(), feeAmount.add(toSendAmount)));

                BigDecimal currentAvai  = getSActivity().mAccount.getIrisBalance();
                mCurrentBalance.setText(WDp.getDpAmount(getContext(), currentAvai, getSActivity().mIrisToken.base_token.decimal, getSActivity().mBaseChain));
                mRemainingBalance.setText(WDp.getDpAmount(getContext(), currentAvai.subtract(toSendAmount).subtract(feeAmount), getSActivity().mIrisToken.base_token.decimal, getSActivity().mBaseChain));
                mRemainingPrice.setText(WDp.getValueOfIris(getContext(), getBaseDao(), currentAvai.subtract(toSendAmount).subtract(feeAmount)));

            } else {
                mTotalSpendLayer.setVisibility(View.GONE);
                mTotalPrice.setVisibility(View.GONE);
                mRemainingPrice.setVisibility(View.GONE);

                BigDecimal currentAvai  = getSActivity().mAccount.getIrisTokenBalance(getSActivity().mIrisToken.base_token.symbol);
                mCurrentBalance.setText(WDp.getDpAmount(getContext(), currentAvai, getSActivity().mIrisToken.base_token.decimal, getSActivity().mBaseChain));
                mRemainingBalance.setText(WDp.getDpAmount(getContext(), currentAvai.subtract(toSendAmount), getSActivity().mIrisToken.base_token.decimal, getSActivity().mBaseChain));
            }

        } else if (getSActivity().mBaseChain.equals(BaseChain.BNB_MAIN) || getSActivity().mBaseChain.equals(BaseChain.BNB_TEST)) {
            mDpDecimal = 8;
            mDenomSendAmount.setText(getSActivity().mBnbToken.original_symbol.toUpperCase());
            mDenomCurrentAmount.setText(getSActivity().mBnbToken.original_symbol.toUpperCase());
            mDenomRemainAmount.setText(getSActivity().mBnbToken.original_symbol.toUpperCase());

            mSendAmount.setText(WDp.getDpAmount(getContext(), toSendAmount, 8, getSActivity().mBaseChain));
            mFeeAmount.setText(WDp.getDpAmount(getContext(), feeAmount, 8, getSActivity().mBaseChain));

            if (getSActivity().mBnbToken.symbol.equals(TOKEN_BNB)) {
                mDenomSendAmount.setTextColor(getResources().getColor(R.color.colorBnb));
                mDenomCurrentAmount.setTextColor(getResources().getColor(R.color.colorBnb));
                mDenomRemainAmount.setTextColor(getResources().getColor(R.color.colorBnb));

                mTotalSpendAmount.setText(WDp.getDpAmount(getContext(), feeAmount.add(toSendAmount), 8, getSActivity().mBaseChain));
                mTotalPrice.setText(WDp.getValueOfBnb(getContext(), getBaseDao(), feeAmount.add(toSendAmount)));

                BigDecimal currentAvai  = getSActivity().mAccount.getBnbBalance();
                mCurrentBalance.setText(WDp.getDpAmount(getContext(), currentAvai, 8, getSActivity().mBaseChain));
                mRemainingBalance.setText(WDp.getDpAmount(getContext(), currentAvai.subtract(toSendAmount).subtract(feeAmount), 8, getSActivity().mBaseChain));
                mRemainingPrice.setText(WDp.getValueOfBnb(getContext(), getBaseDao(), currentAvai.subtract(toSendAmount).subtract(feeAmount)));

            } else {
                mTotalSpendLayer.setVisibility(View.GONE);
                mTotalPrice.setVisibility(View.GONE);
                mRemainingPrice.setVisibility(View.GONE);

                BigDecimal currentAvai  = getSActivity().mAccount.getBnbTokenBalance(getSActivity().mBnbToken.symbol);
                mCurrentBalance.setText(WDp.getDpAmount(getContext(), currentAvai, 8, getSActivity().mBaseChain));
                mRemainingBalance.setText(WDp.getDpAmount(getContext(), currentAvai.subtract(toSendAmount), 8, getSActivity().mBaseChain));
            }

        } else if (getSActivity().mBaseChain.equals(BaseChain.KAVA_MAIN) || getSActivity().mBaseChain.equals(BaseChain.KAVA_TEST)) {
            mDpDecimal = WUtil.getKavaCoinDecimal(getSActivity().mKavaDenom);
            mFeeAmount.setText(WDp.getDpAmount(getContext(), feeAmount, 6, getSActivity().mBaseChain));
            if (getSActivity().mKavaDenom.equals(TOKEN_KAVA)) {
                WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomSendAmount);
                WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomCurrentAmount);
                WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomRemainAmount);

                mSendAmount.setText(WDp.getDpAmount(getContext(), toSendAmount, 6, getSActivity().mBaseChain));
                mTotalSpendAmount.setText(WDp.getDpAmount(getContext(), feeAmount.add(toSendAmount), 6, getSActivity().mBaseChain));
                mTotalPrice.setText(WDp.getValueOfKava(getContext(), getBaseDao(), feeAmount.add(toSendAmount)));

                BigDecimal currentAvai  = getSActivity().mAccount.getKavaBalance();
                mCurrentBalance.setText(WDp.getDpAmount(getContext(), currentAvai, 6, getSActivity().mBaseChain));
                mRemainingBalance.setText(WDp.getDpAmount(getContext(), currentAvai.subtract(toSendAmount).subtract(feeAmount), 6, getSActivity().mBaseChain));
                mRemainingPrice.setText(WDp.getValueOfKava(getContext(), getBaseDao(), currentAvai.subtract(toSendAmount).subtract(feeAmount)));

            } else {
                mTotalSpendLayer.setVisibility(View.GONE);
                mTotalPrice.setVisibility(View.GONE);
                mRemainingPrice.setVisibility(View.GONE);

                BigDecimal currentAvai  = getSActivity().mAccount.getTokenBalance(getSActivity().mKavaDenom);
                WDp.showCoinDp(getContext(), getSActivity().mKavaDenom, toSendAmount.toPlainString(), mDenomSendAmount, mSendAmount, getSActivity().mBaseChain);
                WDp.showCoinDp(getContext(), getSActivity().mKavaDenom, currentAvai.toPlainString(), mDenomCurrentAmount, mCurrentBalance, getSActivity().mBaseChain);
                WDp.showCoinDp(getContext(), getSActivity().mKavaDenom, currentAvai.subtract(toSendAmount).toPlainString(), mDenomRemainAmount, mRemainingBalance, getSActivity().mBaseChain);
            }

        } else if (getSActivity().mBaseChain.equals(BaseChain.IOV_MAIN)) {
            mDpDecimal = 9;
            WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomSendAmount);
            WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomCurrentAmount);
            WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomRemainAmount);

            mSendAmount.setText(WDp.getDpAmount2(getContext(), toSendAmount, mDpDecimal, mDpDecimal));
            mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, mDpDecimal, mDpDecimal));
            mTotalSpendAmount.setText(WDp.getDpAmount2(getContext(), feeAmount.add(toSendAmount), mDpDecimal, mDpDecimal));
            mTotalPrice.setText(WDp.getPriceApproximatelyDp(getSActivity(), BigDecimal.ZERO, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));

            BigDecimal currentAvai  = getSActivity().mAccount.getIovBalance();
            mCurrentBalance.setText(WDp.getDpAmount2(getContext(), currentAvai, mDpDecimal, mDpDecimal));
            mRemainingBalance.setText(WDp.getDpAmount2(getContext(), currentAvai.subtract(toSendAmount).subtract(feeAmount), mDpDecimal, mDpDecimal));
            mRemainingPrice.setText(WDp.getPriceApproximatelyDp(getSActivity(), BigDecimal.ZERO, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));

        } else if (getSActivity().mBaseChain.equals(BaseChain.BAND_MAIN)) {
            mDpDecimal = 6;
            mDenomSendAmount.setTextColor(getResources().getColor(R.color.colorBand));
            mDenomCurrentAmount.setTextColor(getResources().getColor(R.color.colorBand));
            mDenomRemainAmount.setTextColor(getResources().getColor(R.color.colorBand));

            mSendAmount.setText(WDp.getDpAmount2(getContext(), toSendAmount, 6, 6));
            mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, 6, 6));
            mTotalSpendAmount.setText(WDp.getDpAmount2(getContext(), feeAmount.add(toSendAmount), 6, 6));
            mTotalPrice.setText(WDp.getValueOfBand(getContext(), getBaseDao(), feeAmount.add(toSendAmount)));

            BigDecimal currentAvai  = getSActivity().mAccount.getBandBalance();
            mCurrentBalance.setText(WDp.getDpAmount2(getContext(), currentAvai, 6, 6));
            mRemainingBalance.setText(WDp.getDpAmount2(getContext(), currentAvai.subtract(toSendAmount).subtract(feeAmount), 6, 6));
            mRemainingPrice.setText(WDp.getValueOfBand(getContext(), getBaseDao(), currentAvai.subtract(toSendAmount).subtract(feeAmount)));

        }



        mRecipientAddress.setText(getSActivity().mTagetAddress);
        if (TextUtils.isEmpty(getSActivity().mStarName)) {
            mRecipientStartName.setVisibility(View.GONE);
        } else {
            mRecipientStartName.setVisibility(View.VISIBLE);
            mRecipientStartName.setText(getSActivity().mStarName);
        }

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
