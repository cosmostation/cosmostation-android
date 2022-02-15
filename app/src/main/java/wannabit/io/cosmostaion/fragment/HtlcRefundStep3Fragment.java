package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.HtlcRefundActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class HtlcRefundStep3Fragment extends BaseFragment implements View.OnClickListener  {

    private Button mBtnBack, mBtnConfirm ;
    private TextView mFeeAmount, mFeeDenom;
    private TextView mSwapId, mRefundAddress, mRefundAmount, mRefundAmountDenom;

    public static HtlcRefundStep3Fragment newInstance(Bundle bundle) {
        HtlcRefundStep3Fragment fragment = new HtlcRefundStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_htlc_refund_3, container, false);
        mBtnBack = rootView.findViewById(R.id.btn_before);
        mBtnConfirm = rootView.findViewById(R.id.btn_confirm);
        mFeeAmount = rootView.findViewById(R.id.fee_amount);
        mFeeDenom = rootView.findViewById(R.id.fee_denom);
        mSwapId = rootView.findViewById(R.id.refund_swap_id);
        mRefundAddress = rootView.findViewById(R.id.refund_address);
        mRefundAmount = rootView.findViewById(R.id.refund_amount);
        mRefundAmountDenom = rootView.findViewById(R.id.refund_amount_denom);
        mBtnBack.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        WDp.DpMainDenom(getContext(), getSActivity().mBaseChain.getChain(), mFeeDenom);
        if (getSActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
            mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, 0, 8));
            mSwapId.setText(getSActivity().mSwapId);
            mRefundAddress.setText(getSActivity().mResBnbSwapInfo.fromAddr);
            Coin coin = getSActivity().mResBnbSwapInfo.getSendCoin();
            WDp.showCoinDp(getContext(), getBaseDao(), coin, mRefundAmountDenom, mRefundAmount, getSActivity().mBaseChain);

        } else if (getSActivity().mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, 6, 6));
            mSwapId.setText(getSActivity().mSwapId);
            mRefundAddress.setText(getSActivity().mResKavaSwapInfo.result.sender);
            Coin coin = getSActivity().mResKavaSwapInfo.result.amount.get(0);
            WDp.showCoinDp(getContext(), getBaseDao(), coin, mRefundAmountDenom, mRefundAmount, getSActivity().mBaseChain);
        }


    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnBack)) {
            getSActivity().onBeforeStep();
        } else if (v.equals(mBtnConfirm)) {
            getSActivity().onStartHtlcRefund();
        }
    }


    private HtlcRefundActivity getSActivity() {
        return (HtlcRefundActivity)getBaseActivity();
    }
}
