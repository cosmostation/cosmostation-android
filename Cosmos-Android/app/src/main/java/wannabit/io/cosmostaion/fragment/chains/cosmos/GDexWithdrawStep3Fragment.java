package wannabit.io.cosmostaion.fragment.chains.cosmos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import tendermint.liquidity.v1beta1.Liquidity;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.cosmos.GravityWithdrawPoolActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class GDexWithdrawStep3Fragment extends BaseFragment implements View.OnClickListener{

    private TextView        mFeeAmount;
    private TextView        mFeeAmountSymbol;
    private TextView        mWithdrawAmount, mWithdrawSymbol;
    private TextView        mMemo;
    private int             mDpDecimal = 6;

    private Button          mBeforeBtn, mConfirmBtn;

    public static GDexWithdrawStep3Fragment newInstance(Bundle bundle) {
        GDexWithdrawStep3Fragment fragment = new GDexWithdrawStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gdex_withdraw_pool_step3, container, false);
        mFeeAmount                  = rootView.findViewById(R.id.withdraw_fee_amount);
        mFeeAmountSymbol            = rootView.findViewById(R.id.withdraw_fee_amount_symbol);
        mWithdrawAmount             = rootView.findViewById(R.id.withdraw_lp_amount);
        mWithdrawSymbol             = rootView.findViewById(R.id.withdraw_lp_symbol);

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
        mDpDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);

        String lpDenom = getSActivity().mLpToken.denom;
        String lpAmount = getSActivity().mLpToken.amount;

        WUtil.dpCosmosTokenName(getSActivity(), getBaseDao(), mWithdrawSymbol, lpDenom);
        mWithdrawAmount.setText(WDp.getDpAmount2(getSActivity(), new BigDecimal(lpAmount), mDpDecimal, mDpDecimal));

        mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, mDpDecimal, mDpDecimal));
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

    private GravityWithdrawPoolActivity getSActivity() {
        return (GravityWithdrawPoolActivity)getBaseActivity();
    }
}
