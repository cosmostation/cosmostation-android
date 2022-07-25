package wannabit.io.cosmostaion.fragment.txs.osmosis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.osmosis.ExitPoolActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class ExitPoolStep3Fragment extends BaseFragment implements View.OnClickListener{

    private TextView        mFeeAmount;
    private TextView        mFeeSymbol;
    private LinearLayout    mLpLayer;
    private RelativeLayout  mShareLayer;
    private TextView        mExitInAmount, mExitInAmountSymbol;
    private TextView        mExitOutput0Amount, mExitOutput0Symbol;
    private TextView        mExitOutput1Amount, mExitOutput1Symbol;
    private TextView        mMemo;

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
        mFeeSymbol                  = rootView.findViewById(R.id.exit_fee_symbol);
        mLpLayer                    = rootView.findViewById(R.id.lp_layer);
        mShareLayer                 = rootView.findViewById(R.id.share_layer);
        mExitInAmount               = rootView.findViewById(R.id.exit_in_amount);
        mExitInAmountSymbol         = rootView.findViewById(R.id.exit_in_amount_symbol);
        mExitOutput0Amount          = rootView.findViewById(R.id.exit_output0_amount);
        mExitOutput0Symbol          = rootView.findViewById(R.id.exit_output0_symbol);
        mExitOutput1Amount          = rootView.findViewById(R.id.exit_output1_amount);
        mExitOutput1Symbol          = rootView.findViewById(R.id.exit_output1_symbol);
        mMemo                       = rootView.findViewById(R.id.memo);
        mBeforeBtn                  = rootView.findViewById(R.id.btn_before);
        mConfirmBtn                 = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mLpLayer.setVisibility(View.VISIBLE);
        mShareLayer.setVisibility(View.GONE);
        String InputAmount = getSActivity().mLpToken.amount;
        String InputDenom = getSActivity().mLpToken.denom;
        String OutputAmount0 = getSActivity().mPoolCoin0.amount;
        String OutputDenom0 = getSActivity().mPoolCoin0.denom;
        String OutputAmount1 = getSActivity().mPoolCoin1.amount;
        String OutputDenom1 = getSActivity().mPoolCoin1.denom;

        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeSymbol, mFeeAmount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, InputDenom, InputAmount,  mExitInAmountSymbol, mExitInAmount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, OutputDenom0, OutputAmount0,  mExitOutput0Symbol, mExitOutput0Amount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, OutputDenom1, OutputAmount1,  mExitOutput1Symbol, mExitOutput1Amount);
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartExitPool();
        }
    }

    private ExitPoolActivity getSActivity() {
        return (ExitPoolActivity)getBaseActivity();
    }
}
