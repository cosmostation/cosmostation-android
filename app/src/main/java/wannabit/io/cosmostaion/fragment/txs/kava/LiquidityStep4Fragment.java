package wannabit.io.cosmostaion.fragment.txs.kava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.LiquidityActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class LiquidityStep4Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mFeeAmount, mFeeType;
    private TextView mLiquidityAmount, mLiquidityDenom;
    private TextView mValidator;
    private TextView mMemo;
    private Button mBeforeBtn, mConfirmBtn;

    public static LiquidityStep4Fragment newInstance() {
        return new LiquidityStep4Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_liquidity_step4, container, false);
        mFeeAmount = rootView.findViewById(R.id.tx_fee);
        mFeeType = rootView.findViewById(R.id.tx_fee_type);
        mLiquidityAmount = rootView.findViewById(R.id.liquidity_amount);
        mLiquidityDenom = rootView.findViewById(R.id.liquidity_denom);

        mValidator = rootView.findViewById(R.id.tx_validator);
        mMemo = rootView.findViewById(R.id.memo);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeType, mFeeAmount);
        mValidator.setText(getSActivity().getBaseDao().getValidatorInfo(getSActivity().mToValAddress).getDescription().getMoniker());
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mChainConfig.mainDenom(), getSActivity().mAmount.amount, mLiquidityDenom, mLiquidityAmount);
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartLiquidity();
        }
    }

    private LiquidityActivity getSActivity() {
        return (LiquidityActivity) getBaseActivity();
    }
}
