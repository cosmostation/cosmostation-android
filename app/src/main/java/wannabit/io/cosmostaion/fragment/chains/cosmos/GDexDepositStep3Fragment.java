package wannabit.io.cosmostaion.fragment.chains.cosmos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.cosmos.GravityDepositPoolActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class GDexDepositStep3Fragment extends BaseFragment implements View.OnClickListener, IRefreshTabListener {

    private TextView mFeeAmount;
    private TextView mFeeAmountSymbol;
    private TextView mJoinInput0Amount, mJoinInput0AmountSymbol;
    private TextView mJoinInput1Amount, mJoinInput1AmountSymbol;

    private TextView mLpAmount, mLpAmountSymbol;
    private TextView mMemo;
    private int mDpDecimal = 6;

    private Button mBeforeBtn, mConfirmBtn;

    public static GDexDepositStep3Fragment newInstance(Bundle bundle) {
        GDexDepositStep3Fragment fragment = new GDexDepositStep3Fragment();
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

        mLpAmount = rootView.findViewById(R.id.join_out_amount);
        mLpAmountSymbol = rootView.findViewById(R.id.join_out_amount_symbol);
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

        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);

        mFeeAmount.setText(WDp.getDpAmount2(feeAmount, mDpDecimal, mDpDecimal));
        WDp.showCoinDp(getSActivity(), getBaseDao(), WUtil.dpCosmosTokenName(getSActivity(), getBaseDao(), mJoinInput0AmountSymbol, InputDenom0), InputAmount0, mJoinInput0AmountSymbol, mJoinInput0Amount, BaseChain.COSMOS_MAIN);
        WDp.showCoinDp(getSActivity(), getBaseDao(), WUtil.dpCosmosTokenName(getSActivity(), getBaseDao(), mJoinInput1AmountSymbol, InputDenom1), InputAmount1, mJoinInput1AmountSymbol, mJoinInput1Amount, BaseChain.COSMOS_MAIN);
        mLpAmountSymbol.setText("GDEX-" + getSActivity().mGDexPoolId);
        mLpAmount.setText(WDp.getDpAmount2(new BigDecimal(getSActivity().mLpToken.amount), 6, 6));
        mFeeAmount.setText(WDp.getDpAmount2(feeAmount, mDpDecimal, mDpDecimal));
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

    private GravityDepositPoolActivity getSActivity() {
        return (GravityDepositPoolActivity) getBaseActivity();
    }
}
