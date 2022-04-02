package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.utils.WDp;

public class SendStep4Fragment extends BaseFragment implements View.OnClickListener, IRefreshTabListener {

    private TextView mSendAmount;
    private TextView mFeeAmount;
    private RelativeLayout mTotalSpendLayer;
    private TextView mTotalSpendAmount, mTotalPrice;
    private TextView mCurrentBalance, mRemainingBalance, mRemainingPrice;
    private TextView mRecipientAddress, mRecipientStartName, mMemo;
    private Button mBeforeBtn, mConfirmBtn;
    private TextView mDenomSendAmount, mDenomFeeType, mDenomTotalSpend, mDenomCurrentAmount, mDenomRemainAmount;
    private int mDisplayDecimal = 6;        //bnb == 8
    private int mDivideDecimal = 6;         //bnb == 0

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
        View rootView = inflater.inflate(R.layout.fragment_send_step4, container, false);
        mSendAmount = rootView.findViewById(R.id.send_amount);
        mFeeAmount = rootView.findViewById(R.id.send_fees);
        mTotalSpendLayer = rootView.findViewById(R.id.spend_total_layer);
        mTotalSpendAmount = rootView.findViewById(R.id.spend_total);
        mTotalPrice = rootView.findViewById(R.id.spend_total_price);
        mCurrentBalance = rootView.findViewById(R.id.current_available_atom);
        mRemainingBalance = rootView.findViewById(R.id.remaining_available_atom);
        mRemainingPrice = rootView.findViewById(R.id.remaining_price);
        mRecipientAddress = rootView.findViewById(R.id.recipient_address);
        mRecipientStartName = rootView.findViewById(R.id.recipient_starname);
        mMemo = rootView.findViewById(R.id.memo);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);
        mDenomSendAmount = rootView.findViewById(R.id.send_amount_title);
        mDenomFeeType = rootView.findViewById(R.id.send_fees_type);
        mDenomTotalSpend = rootView.findViewById(R.id.spend_total_type);
        mDenomCurrentAmount = rootView.findViewById(R.id.current_available_title);
        mDenomRemainAmount = rootView.findViewById(R.id.remaining_available_title);

        String baseChain = getSActivity().mAccount.baseChain;

        WDp.DpMainDenom(getContext(), baseChain, mDenomFeeType);
        WDp.DpMainDenom(getContext(), baseChain, mDenomTotalSpend);
        WDp.DpMainDenom(getContext(), baseChain, mDenomSendAmount);
        WDp.DpMainDenom(getContext(), baseChain, mDenomCurrentAmount);
        WDp.DpMainDenom(getContext(), baseChain, mDenomRemainAmount);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onRefreshTab() {
        BigDecimal toSendAmount = new BigDecimal(getSActivity().mAmounts.get(0).amount);
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        final String mainDenom = getSActivity().mBaseChain.getMainDenom();
        final String toSendDenom = getSActivity().mDenom;

        if (getSActivity().mBaseChain.isGRPC()) {
            mDivideDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
            mDisplayDecimal = WDp.mainDisplayDecimal(getSActivity().mBaseChain);

            mFeeAmount.setText(WDp.getDpAmount2(feeAmount, mDivideDecimal, mDisplayDecimal));

            if (getSActivity().mDenom.equals(getSActivity().mBaseChain.getMainDenom())) {
                mSendAmount.setText(WDp.getDpAmount2(toSendAmount, mDivideDecimal, mDisplayDecimal));
                mTotalSpendAmount.setText(WDp.getDpAmount2(feeAmount.add(toSendAmount), mDivideDecimal, mDisplayDecimal));
                mTotalPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), getSActivity().mBaseChain.getMainDenom(), feeAmount.add(toSendAmount), mDivideDecimal));

                BigDecimal currentAvai = getBaseDao().getAvailable(getSActivity().mDenom);
                mCurrentBalance.setText(WDp.getDpAmount2(currentAvai, mDivideDecimal, mDisplayDecimal));
                BigDecimal subtract = currentAvai.subtract(toSendAmount).subtract(feeAmount);
                mRemainingBalance.setText(WDp.getDpAmount2(subtract, mDivideDecimal, mDisplayDecimal));
                mRemainingPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), getSActivity().mBaseChain.getMainDenom(), subtract, mDivideDecimal));

            } else {
                // not staking denom send
                int textColor = ContextCompat.getColor(requireContext(), R.color.colorWhite);
                mDenomSendAmount.setTextColor(textColor);
                mDenomCurrentAmount.setTextColor(textColor);
                mDenomRemainAmount.setTextColor(textColor);
                mTotalSpendLayer.setVisibility(View.GONE);
                mTotalPrice.setVisibility(View.GONE);
                mRemainingPrice.setVisibility(View.GONE);

                BigDecimal currentAvai = getBaseDao().getAvailable(toSendDenom);
                WDp.showCoinDp(getContext(), getBaseDao(), getSActivity().mDenom, toSendAmount.toPlainString(), mDenomSendAmount, mSendAmount, getSActivity().mBaseChain);
                WDp.showCoinDp(getContext(), getBaseDao(), getSActivity().mDenom, currentAvai.toPlainString(), mDenomCurrentAmount, mCurrentBalance, getSActivity().mBaseChain);
                WDp.showCoinDp(getContext(), getBaseDao(), getSActivity().mDenom, currentAvai.subtract(toSendAmount).toPlainString(), mDenomRemainAmount, mRemainingBalance, getSActivity().mBaseChain);
            }

        } else {
            if (getSActivity().mBaseChain.equals(BNB_MAIN)) {
                mDivideDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
                mDisplayDecimal = WDp.mainDisplayDecimal(getSActivity().mBaseChain);

                mDenomSendAmount.setText(getSActivity().mBnbToken.original_symbol.toUpperCase());
                mDenomCurrentAmount.setText(getSActivity().mBnbToken.original_symbol.toUpperCase());
                mDenomRemainAmount.setText(getSActivity().mBnbToken.original_symbol.toUpperCase());

                mSendAmount.setText(WDp.getDpAmount2(toSendAmount, mDivideDecimal, mDisplayDecimal));
                mFeeAmount.setText(WDp.getDpAmount2(feeAmount, mDivideDecimal, mDisplayDecimal));

                if (getSActivity().mBnbToken.symbol.equals(BNB_MAIN.getMainDenom())) {
                    int textColor = ContextCompat.getColor(requireContext(), R.color.colorBnb);
                    mDenomSendAmount.setTextColor(textColor);
                    mDenomCurrentAmount.setTextColor(textColor);
                    mDenomRemainAmount.setTextColor(textColor);

                    mTotalSpendAmount.setText(WDp.getDpAmount2(feeAmount.add(toSendAmount), mDivideDecimal, mDisplayDecimal));
                    mTotalPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), BNB_MAIN.getMainDenom(), feeAmount.add(toSendAmount), 0));

                    BigDecimal currentAvai = getSActivity().mAccount.getBnbBalance();
                    mCurrentBalance.setText(WDp.getDpAmount2(currentAvai, mDivideDecimal, mDisplayDecimal));
                    BigDecimal subtract = currentAvai.subtract(toSendAmount).subtract(feeAmount);
                    mRemainingBalance.setText(WDp.getDpAmount2(subtract, mDivideDecimal, mDisplayDecimal));
                    mRemainingPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), BNB_MAIN.getMainDenom(), subtract, 0));
                } else {
                    int textColor = ContextCompat.getColor(requireContext(), R.color.colorWhite);
                    mDenomSendAmount.setTextColor(textColor);
                    mDenomCurrentAmount.setTextColor(textColor);
                    mDenomRemainAmount.setTextColor(textColor);
                    mTotalSpendLayer.setVisibility(View.GONE);
                    mTotalPrice.setVisibility(View.GONE);
                    mRemainingPrice.setVisibility(View.GONE);

                    BigDecimal currentAvai = getSActivity().mAccount.getBnbTokenBalance(getSActivity().mBnbToken.symbol);
                    mCurrentBalance.setText(WDp.getDpAmount2(currentAvai, mDivideDecimal, mDisplayDecimal));
                    mRemainingBalance.setText(WDp.getDpAmount2(currentAvai.subtract(toSendAmount), mDivideDecimal, mDisplayDecimal));
                }


            } else if (getSActivity().mBaseChain.equals(OKEX_MAIN)) {
                mDivideDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
                mDisplayDecimal = WDp.mainDisplayDecimal(getSActivity().mBaseChain);

                mSendAmount.setText(WDp.getDpAmount2(toSendAmount, mDivideDecimal, mDisplayDecimal));
                mFeeAmount.setText(WDp.getDpAmount2(feeAmount, mDivideDecimal, mDisplayDecimal));

                mDenomSendAmount.setText(toSendDenom.toUpperCase());
                mDenomCurrentAmount.setText(toSendDenom.toUpperCase());
                mDenomRemainAmount.setText(toSendDenom.toUpperCase());

                if (toSendDenom.equals(mainDenom)) {
                    int textColor = ContextCompat.getColor(requireContext(), R.color.colorOK);
                    mDenomSendAmount.setTextColor(textColor);
                    mDenomCurrentAmount.setTextColor(textColor);
                    mDenomRemainAmount.setTextColor(textColor);
                    mTotalSpendAmount.setText(WDp.getDpAmount2(feeAmount.add(toSendAmount), mDivideDecimal, mDisplayDecimal));
                    mTotalPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), OKEX_MAIN.getMainDenom(), feeAmount.add(toSendAmount), mDivideDecimal));

                    BigDecimal currentAvai = getBaseDao().availableAmount(toSendDenom);
                    mCurrentBalance.setText(WDp.getDpAmount2(currentAvai, mDivideDecimal, mDisplayDecimal));
                    BigDecimal subtract = currentAvai.subtract(toSendAmount).subtract(feeAmount);
                    mRemainingBalance.setText(WDp.getDpAmount2(subtract, mDivideDecimal, mDisplayDecimal));
                    mRemainingPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), OKEX_MAIN.getMainDenom(), subtract, mDivideDecimal));

                } else {
                    int textColor = ContextCompat.getColor(requireContext(), R.color.colorWhite);
                    mDenomSendAmount.setTextColor(textColor);
                    mDenomCurrentAmount.setTextColor(textColor);
                    mDenomRemainAmount.setTextColor(textColor);
                    mTotalSpendLayer.setVisibility(View.GONE);
                    mTotalPrice.setVisibility(View.GONE);
                    mRemainingPrice.setVisibility(View.GONE);

                    BigDecimal currentAvai = getBaseDao().availableAmount(toSendDenom);
                    mCurrentBalance.setText(WDp.getDpAmount2(currentAvai, mDivideDecimal, mDisplayDecimal));
                    mRemainingBalance.setText(WDp.getDpAmount2(currentAvai.subtract(toSendAmount), mDivideDecimal, mDisplayDecimal));

                }

            } else {
                mDivideDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
                mDisplayDecimal = WDp.mainDisplayDecimal(getSActivity().mBaseChain);

                mSendAmount.setText(WDp.getDpAmount2(toSendAmount, mDivideDecimal, mDisplayDecimal));
                mFeeAmount.setText(WDp.getDpAmount2(feeAmount, mDivideDecimal, mDisplayDecimal));

                mSendAmount.setText(WDp.getDpAmount2(toSendAmount, mDivideDecimal, mDisplayDecimal));
                mFeeAmount.setText(WDp.getDpAmount2(feeAmount, mDivideDecimal, mDisplayDecimal));
                mTotalSpendAmount.setText(WDp.getDpAmount2(feeAmount.add(toSendAmount), mDivideDecimal, mDisplayDecimal));
                mTotalPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), toSendDenom, feeAmount.add(toSendAmount), mDivideDecimal));

                BigDecimal currentAvai = getBaseDao().availableAmount(toSendDenom);
                BigDecimal subtract = currentAvai.subtract(toSendAmount).subtract(feeAmount);

                mCurrentBalance.setText(WDp.getDpAmount2(currentAvai, mDivideDecimal, mDisplayDecimal));
                mRemainingBalance.setText(WDp.getDpAmount2(subtract, mDivideDecimal, mDisplayDecimal));
                mRemainingPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), toSendDenom, subtract, mDivideDecimal));

            }
        }

        mRecipientAddress.setText(getSActivity().mToAddress);
        mRecipientStartName.setVisibility(View.GONE);
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();
        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartSend();
        }
    }

    private SendActivity getSActivity() {
        return (SendActivity) getBaseActivity();
    }
}
