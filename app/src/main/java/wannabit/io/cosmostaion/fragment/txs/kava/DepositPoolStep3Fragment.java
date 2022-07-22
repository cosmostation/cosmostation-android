package wannabit.io.cosmostaion.fragment.txs.kava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.DepositPoolActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class DepositPoolStep3Fragment extends BaseFragment implements View.OnClickListener{

    private TextView        mFeeAmount;
    private TextView        mFeeAmountSymbol;
    private TextView        mJoinInput0Amount, mJoinInput0Symbol;
    private TextView        mJoinInput1Amount, mJoinInput1Symbol;
    private LinearLayout    mLplayer;
    private RelativeLayout  mSlippageLayer;
    private TextView        mJoinSlippage;
    private TextView        mMemo;

    private Button          mBeforeBtn, mConfirmBtn;

    public static DepositPoolStep3Fragment newInstance(Bundle bundle) {
        DepositPoolStep3Fragment fragment = new DepositPoolStep3Fragment();
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
        mLplayer                    = rootView.findViewById(R.id.lp_layer);
        mSlippageLayer              = rootView.findViewById(R.id.slippage_layer);
        mJoinSlippage               = rootView.findViewById(R.id.tx_slippage);
        mMemo                       = rootView.findViewById(R.id.memo);
        mBeforeBtn                  = rootView.findViewById(R.id.btn_before);
        mConfirmBtn                 = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mLplayer.setVisibility(View.GONE);
        mSlippageLayer.setVisibility(View.VISIBLE);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeAmountSymbol, mFeeAmount);

        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mKavaPoolTokenA, mJoinInput0Symbol, mJoinInput0Amount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mKavaPoolTokenB, mJoinInput1Symbol, mJoinInput1Amount);
        mJoinSlippage.setText(WDp.getPercentDp(new BigDecimal(3)));
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

    private DepositPoolActivity getSActivity() {
        return (DepositPoolActivity)getBaseActivity();
    }
}
