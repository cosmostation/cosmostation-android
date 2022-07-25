package wannabit.io.cosmostaion.fragment.txs.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.SendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class SendStep4Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mSendAmount;
    private TextView mFeeAmount;
    private TextView mCurrentBalance, mRemainingBalance, mRemainingPrice;
    private TextView mRecipientAddress, mMemo;
    private Button mBeforeBtn, mConfirmBtn;
    private TextView mSendDenom, mFeeDenom, mCurrentDenom, mRemainDenom;

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
        mCurrentBalance = rootView.findViewById(R.id.current_available);
        mRemainingBalance = rootView.findViewById(R.id.remaining_available);
        mRemainingPrice = rootView.findViewById(R.id.remaining_price);
        mRecipientAddress = rootView.findViewById(R.id.recipient_address);

        mSendDenom = rootView.findViewById(R.id.send_denom);
        mFeeDenom = rootView.findViewById(R.id.send_fees_type);
        mCurrentDenom = rootView.findViewById(R.id.current_denom);
        mRemainDenom = rootView.findViewById(R.id.remaining_denom);
        mMemo = rootView.findViewById(R.id.memo);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        final BigDecimal toSendAmount = new BigDecimal(getSActivity().mAmounts.get(0).amount);
        final BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        final String mainDenom = getSActivity().mChainConfig.mainDenom();
        final String toSendDenom = getSActivity().mDenom;

        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, toSendDenom, toSendAmount.toPlainString(), mSendDenom, mSendAmount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeDenom, mFeeAmount);

        if (BaseChain.isGRPC(getSActivity().mBaseChain)) {
            BigDecimal currentAvai = getBaseDao().getAvailable(toSendDenom);
            WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, toSendDenom, currentAvai.toPlainString(), mCurrentDenom, mCurrentBalance);

            BigDecimal remainAmount = BigDecimal.ZERO;
            if (toSendDenom.equalsIgnoreCase(getSActivity().mTxFee.amount.get(0).denom)) {
                remainAmount = currentAvai.subtract(toSendAmount).subtract(feeAmount);
            } else {
                remainAmount = currentAvai.subtract(toSendAmount);
            }
            WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, toSendDenom, remainAmount.toPlainString(), mRemainDenom, mRemainingBalance);

            if (toSendDenom.equals(mainDenom)) {
                mRemainingPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), toSendDenom, remainAmount, WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, toSendDenom)));
            } else {
                mRemainingPrice.setVisibility(View.GONE);
            }

        } else {
            BigDecimal currentAvai = getBaseDao().availableAmount(toSendDenom);
            WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, toSendDenom, currentAvai.toPlainString(), mCurrentDenom, mCurrentBalance);

            BigDecimal remainAmount = BigDecimal.ZERO;
            if (toSendDenom.equalsIgnoreCase(getSActivity().mTxFee.amount.get(0).denom)) {
                remainAmount = currentAvai.subtract(toSendAmount).subtract(feeAmount);
            } else {
                remainAmount = currentAvai.subtract(toSendAmount);
            }
            WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, toSendDenom, remainAmount.toPlainString(), mRemainDenom, mRemainingBalance);

            if (toSendDenom.equals(mainDenom)) {
                mRemainingPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), toSendDenom, remainAmount, WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, toSendDenom)));
            } else {
                mRemainingPrice.setVisibility(View.GONE);
            }
        }
        mRecipientAddress.setText(getSActivity().mToAddress);
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
