package wannabit.io.cosmostaion.fragment.chains.osmosis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.ExitPoolActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class ExitPoolStep3Fragment extends BaseFragment implements View.OnClickListener, IRefreshTabListener {

    private TextView mFeeAmount;
    private TextView mFeeAmountSymbol;
    private TextView mExitInAmount, mExitInAmountSymbol;
    private TextView mExitOutput0Amount, mExitOutput0AmountSymbol;
    private TextView mExitOutput1Amount, mExitOutput1AmountSymbol;
    private TextView mMemo;
    private int mDpDecimal = 6;

    private Button mBeforeBtn, mConfirmBtn;

    public static ExitPoolStep3Fragment newInstance(Bundle bundle) {
        ExitPoolStep3Fragment fragment = new ExitPoolStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exit_pool_step3, container, false);
        mFeeAmount = rootView.findViewById(R.id.exit_fee_amount);
        mFeeAmountSymbol = rootView.findViewById(R.id.exit_fee_amount_symbol);
        mExitInAmount = rootView.findViewById(R.id.exit_in_amount);
        mExitInAmountSymbol = rootView.findViewById(R.id.exit_in_amount_symbol);
        mExitOutput0Amount = rootView.findViewById(R.id.exit_output0_amount);
        mExitOutput0AmountSymbol = rootView.findViewById(R.id.exit_output0_amount_symbol);
        mExitOutput1Amount = rootView.findViewById(R.id.exit_output1_amount);
        mExitOutput1AmountSymbol = rootView.findViewById(R.id.exit_output1_amount_symbol);
        mMemo = rootView.findViewById(R.id.memo);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mFeeAmountSymbol);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mDpDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        String InputAmount = getSActivity().mLpToken.amount;
        String InputDenom = getSActivity().mLpToken.denom;
        String OutputAmount0 = getSActivity().mPoolCoin0.amount;
        String OutputDenom0 = getSActivity().mPoolCoin0.denom;
        String OutputAmount1 = getSActivity().mPoolCoin1.amount;
        String OutputDenom1 = getSActivity().mPoolCoin1.denom;

        mFeeAmount.setText(WDp.getDpAmount2(feeAmount, mDpDecimal, mDpDecimal));
        WDp.showCoinDp(getSActivity(), getBaseDao(), WUtil.dpOsmosisTokenName(getSActivity(), getBaseDao(), mExitInAmountSymbol, InputDenom), InputAmount, mExitInAmountSymbol, mExitInAmount, BaseChain.OSMOSIS_MAIN);
        WDp.showCoinDp(getSActivity(), getBaseDao(), WUtil.dpOsmosisTokenName(getSActivity(), getBaseDao(), mExitOutput0AmountSymbol, OutputDenom0), OutputAmount0, mExitOutput0AmountSymbol, mExitOutput0Amount, BaseChain.OSMOSIS_MAIN);
        WDp.showCoinDp(getSActivity(), getBaseDao(), WUtil.dpOsmosisTokenName(getSActivity(), getBaseDao(), mExitOutput1AmountSymbol, OutputDenom1), OutputAmount1, mExitOutput1AmountSymbol, mExitOutput1Amount, BaseChain.OSMOSIS_MAIN);
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartExitPool();
        }
    }

    private ExitPoolActivity getSActivity() {
        return (ExitPoolActivity) getBaseActivity();
    }
}
