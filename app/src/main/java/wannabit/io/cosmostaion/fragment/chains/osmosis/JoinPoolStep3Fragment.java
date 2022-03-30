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
import wannabit.io.cosmostaion.activities.chains.osmosis.JoinPoolActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class JoinPoolStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mFeeAmount;
    private TextView mFeeAmountSymbol;
    private TextView mJoinInput0Amount, mJoinInput0AmountSymbol;
    private TextView mJoinInput1Amount, mJoinInput1AmountSymbol;
    private TextView mJoinOutAmount, mJoinOutAmountSymbol;
    private TextView mMemo;
    private int mDpDecimal = 6;

    private Button mBeforeBtn, mConfirmBtn;

    public static JoinPoolStep3Fragment newInstance(Bundle bundle) {
        JoinPoolStep3Fragment fragment = new JoinPoolStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_join_pool_step3, container, false);
        mFeeAmount = rootView.findViewById(R.id.join_fee_amount);
        mFeeAmountSymbol = rootView.findViewById(R.id.join_fee_amount_symbol);
        mJoinInput0Amount = rootView.findViewById(R.id.join_input0_amount);
        mJoinInput0AmountSymbol = rootView.findViewById(R.id.join_input0_amount_symbol);
        mJoinInput1Amount = rootView.findViewById(R.id.join_input1_amount);
        mJoinInput1AmountSymbol = rootView.findViewById(R.id.join_input1_amount_symbol);
        mJoinOutAmount = rootView.findViewById(R.id.join_out_amount);
        mJoinOutAmountSymbol = rootView.findViewById(R.id.join_out_amount_symbol);
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
        String InputAmount0 = getSActivity().mPoolCoin0.amount;
        String InputDenom0 = getSActivity().mPoolCoin0.denom;
        String InputAmount1 = getSActivity().mPoolCoin1.amount;
        String InputDenom1 = getSActivity().mPoolCoin1.denom;
        String LpTokenAmount = getSActivity().mLpToken.amount;
        String LpTokenSymbol = getSActivity().mLpToken.denom;
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);

        mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, mDpDecimal, mDpDecimal));
        WDp.showCoinDp(getSActivity(), getBaseDao(), WUtil.dpOsmosisTokenName(getSActivity(), getBaseDao(), mJoinInput0AmountSymbol, InputDenom0), InputAmount0, mJoinInput0AmountSymbol, mJoinInput0Amount, BaseChain.OSMOSIS_MAIN);
        WDp.showCoinDp(getSActivity(), getBaseDao(), WUtil.dpOsmosisTokenName(getSActivity(), getBaseDao(), mJoinInput1AmountSymbol, InputDenom1), InputAmount1, mJoinInput1AmountSymbol, mJoinInput1Amount, BaseChain.OSMOSIS_MAIN);
        WDp.showCoinDp(getSActivity(), getBaseDao(), WUtil.dpOsmosisTokenName(getSActivity(), getBaseDao(), mJoinOutAmountSymbol, LpTokenSymbol), LpTokenAmount, mJoinOutAmountSymbol, mJoinOutAmount, BaseChain.OSMOSIS_MAIN);
        mMemo.setText(getSActivity().mTxMemo);
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartJoinPool();
        }
    }

    private JoinPoolActivity getSActivity() {
        return (JoinPoolActivity) getBaseActivity();
    }
}
