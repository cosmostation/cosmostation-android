package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.HtlcRefundActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class HtlcRefundStep0Fragment extends BaseFragment implements View.OnClickListener, IRefreshTabListener {

    private Button mBtnCancel, mBtnNext;
    private TextView mSwapId, mRefundAddress, mRefundAmount, mRefundAmountDenom;

    public static HtlcRefundStep0Fragment newInstance(Bundle bundle) {
        HtlcRefundStep0Fragment fragment = new HtlcRefundStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_htlc_refund_0, container, false);
        mBtnCancel = rootView.findViewById(R.id.btn_cancel);
        mBtnNext = rootView.findViewById(R.id.nextButton);
        mSwapId = rootView.findViewById(R.id.refund_swap_id);
        mRefundAddress = rootView.findViewById(R.id.refund_address);
        mRefundAmount = rootView.findViewById(R.id.refund_amount);
        mRefundAmountDenom = rootView.findViewById(R.id.refund_amount_denom);
        mBtnCancel.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if (getSActivity().baseChain.equals(BaseChain.BNB_MAIN)) {
            mSwapId.setText(getSActivity().mSwapId);
            mRefundAddress.setText(getSActivity().mResBnbSwapInfo.fromAddr);
            Coin coin = getSActivity().mResBnbSwapInfo.getSendCoin();
            WDp.showCoinDp(getContext(), getBaseDao(), coin, mRefundAmountDenom, mRefundAmount, getSActivity().baseChain);

        } else if (getSActivity().baseChain.equals(BaseChain.KAVA_MAIN)) {
            mSwapId.setText(getSActivity().mSwapId);
            mRefundAddress.setText(getSActivity().mResKavaSwapInfo.result.sender);
            Coin coin = getSActivity().mResKavaSwapInfo.result.amount.get(0);
            WDp.showCoinDp(getContext(), getBaseDao(), coin, mRefundAmountDenom, mRefundAmount, getSActivity().baseChain);

        } else {
            getSActivity().onBeforeStep();
        }

    }


    private HtlcRefundActivity getSActivity() {
        return (HtlcRefundActivity) getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnCancel)) {
            getSActivity().onBeforeStep();
        } else if (v.equals(mBtnNext)) {
            getSActivity().onNextStep();
        }
    }

}
