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
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class ExitPoolStep3Fragment extends BaseFragment implements View.OnClickListener{

    private TextView        mFeeAmount;
    private TextView        mFeeAmountSymbol;
    private TextView        mExitOutAmount, mExitOutAmountSymbol;
    private TextView        mExitInput0Amount, mExitInput0AmountSymbol;
    private TextView        mExitInput1Amount, mExitInput1AmountSymbol;
    private TextView        mMemo;
    private int             mDpDecimal = 6;

    private Button          mBeforeBtn, mConfirmBtn;

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
        mFeeAmount                  = rootView.findViewById(R.id.exit_fee_amount);
        mFeeAmountSymbol            = rootView.findViewById(R.id.exit_fee_amount_symbol);
        mExitOutAmount              = rootView.findViewById(R.id.exit_out_amount);
        mExitOutAmountSymbol        = rootView.findViewById(R.id.exit_out_amount_symbol);
        mExitInput0Amount           = rootView.findViewById(R.id.exit_input0_amount);
        mExitInput0AmountSymbol     = rootView.findViewById(R.id.exit_input0_amount_symbol);
        mExitInput1Amount           = rootView.findViewById(R.id.exit_input1_amount);
        mExitInput1AmountSymbol     = rootView.findViewById(R.id.exit_input1_amount_symbol);
        mMemo                       = rootView.findViewById(R.id.memo);
        mBeforeBtn                  = rootView.findViewById(R.id.btn_before);
        mConfirmBtn                 = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mDpDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
        String OutputAmount = getSActivity().mLpCoin.amount;
        String OutputDenom = getSActivity().mLpCoin.denom;
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);

        mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, mDpDecimal, mDpDecimal));
        WDp.showCoinDp(getSActivity(), WUtil.dpOsmosisTokenName(getSActivity(), mExitOutAmountSymbol, OutputDenom), OutputAmount,  mExitOutAmountSymbol, mExitOutAmount, BaseChain.OSMOSIS_MAIN);
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
//            getSActivity().onStartSwap();
        }
    }

    private ExitPoolActivity getSActivity() {
        return (ExitPoolActivity)getBaseActivity();
    }
}
