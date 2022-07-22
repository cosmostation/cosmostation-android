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
import wannabit.io.cosmostaion.activities.txs.osmosis.JoinPoolActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class JoinPoolStep3Fragment extends BaseFragment implements View.OnClickListener{

    private TextView        mFeeAmount;
    private TextView        mFeeAmountSymbol;
    private TextView        mJoinInput0Amount, mJoinInput0Symbol;
    private TextView        mJoinInput1Amount, mJoinInput1Symbol;
    private TextView        mJoinOutAmount, mJoinOutAmountSymbol;
    private LinearLayout    mLplayer;
    private RelativeLayout  mSlippageLayer;
    private TextView        mMemo;

    private Button          mBeforeBtn, mConfirmBtn;

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
        mFeeAmount                  = rootView.findViewById(R.id.join_fee_amount);
        mFeeAmountSymbol            = rootView.findViewById(R.id.join_fee_amount_symbol);
        mJoinInput0Amount           = rootView.findViewById(R.id.join_input0_amount);
        mJoinInput0Symbol           = rootView.findViewById(R.id.join_input0_symbol);
        mJoinInput1Amount           = rootView.findViewById(R.id.join_input1_amount);
        mJoinInput1Symbol           = rootView.findViewById(R.id.join_input1_symbol);
        mJoinOutAmount              = rootView.findViewById(R.id.join_out_amount);
        mJoinOutAmountSymbol        = rootView.findViewById(R.id.join_out_symbol);
        mLplayer                    = rootView.findViewById(R.id.lp_layer);
        mSlippageLayer              = rootView.findViewById(R.id.slippage_layer);
        mMemo                       = rootView.findViewById(R.id.memo);
        mBeforeBtn                  = rootView.findViewById(R.id.btn_before);
        mConfirmBtn                 = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mLplayer.setVisibility(View.VISIBLE);
        mSlippageLayer.setVisibility(View.GONE);
        String InputAmount0 = getSActivity().mPoolCoin0.amount;
        String InputDenom0 = getSActivity().mPoolCoin0.denom;
        String InputAmount1 = getSActivity().mPoolCoin1.amount;
        String InputDenom1 = getSActivity().mPoolCoin1.denom;
        String LpTokenAmount = getSActivity().mLpToken.amount;
        String LpTokenSymbol = getSActivity().mLpToken.denom;

        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeAmountSymbol, mFeeAmount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, InputDenom0, InputAmount0,  mJoinInput0Symbol, mJoinInput0Amount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, InputDenom1, InputAmount1, mJoinInput1Symbol, mJoinInput1Amount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, LpTokenSymbol, LpTokenAmount, mJoinOutAmountSymbol, mJoinOutAmount);
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartJoinPool();
        }
    }

    private JoinPoolActivity getSActivity() {
        return (JoinPoolActivity)getBaseActivity();
    }
}
