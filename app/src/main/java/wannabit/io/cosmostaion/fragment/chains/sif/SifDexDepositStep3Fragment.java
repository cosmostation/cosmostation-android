package wannabit.io.cosmostaion.fragment.chains.sif;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.sif.SifDepositPoolActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SIF;

public class SifDexDepositStep3Fragment extends BaseFragment implements View.OnClickListener{

    private TextView        mFeeAmount;
    private TextView        mFeeAmountSymbol;
    private TextView        mJoinInput0Amount, mJoinInput0AmountSymbol;
    private TextView        mJoinInput1Amount, mJoinInput1AmountSymbol;

    private TextView        mLpAmountTitle;
    private LinearLayout    mLpAmountLayer;
    private TextView        mMemo;
    private int             mDpDecimal = 6;

    private Button          mBeforeBtn, mConfirmBtn;

    public static SifDexDepositStep3Fragment newInstance(Bundle bundle) {
        SifDexDepositStep3Fragment fragment = new SifDexDepositStep3Fragment();
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
        mJoinInput0AmountSymbol     = rootView.findViewById(R.id.join_input0_amount_symbol);
        mJoinInput1Amount           = rootView.findViewById(R.id.join_input1_amount);
        mJoinInput1AmountSymbol     = rootView.findViewById(R.id.join_input1_amount_symbol);

        mLpAmountTitle              = rootView.findViewById(R.id.lp_amount_title);
        mLpAmountLayer              = rootView.findViewById(R.id.lp_amount);
        mMemo                       = rootView.findViewById(R.id.memo);
        mBeforeBtn                  = rootView.findViewById(R.id.btn_before);
        mConfirmBtn                 = rootView.findViewById(R.id.btn_confirm);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mFeeAmountSymbol);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mLpAmountTitle.setVisibility(View.GONE);
        mLpAmountLayer.setVisibility(View.GONE);
        mDpDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);

        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);

        mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, mDpDecimal, mDpDecimal));
        WDp.showCoinDp(getSActivity(), getBaseDao(), getSActivity().mSifDepositCoin0, mJoinInput0AmountSymbol, mJoinInput0Amount, BaseChain.SIF_MAIN);
        WDp.showCoinDp(getSActivity(), getBaseDao(), getSActivity().mSifDepositCoin1, mJoinInput1AmountSymbol, mJoinInput1Amount, BaseChain.SIF_MAIN);
        mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, mDpDecimal, mDpDecimal));
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

    private SifDepositPoolActivity getSActivity() {
        return (SifDepositPoolActivity)getBaseActivity();
    }
}
