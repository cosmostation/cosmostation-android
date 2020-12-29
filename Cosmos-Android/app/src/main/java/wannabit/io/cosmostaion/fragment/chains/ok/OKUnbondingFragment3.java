package wannabit.io.cosmostaion.fragment.chains.ok;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.ok.OKUnbondingActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;

public class OKUnbondingFragment3 extends BaseFragment implements View.OnClickListener {

    private TextView        mWithdrawAmount;
    private TextView        mFeeAmount;
    private TextView        mMemo;
    private Button          mBeforeBtn, mConfirmBtn;
    private TextView        mWithdrawDenom, mFeeDenom;

    public static OKUnbondingFragment3 newInstance(Bundle bundle) {
        OKUnbondingFragment3 fragment = new OKUnbondingFragment3();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stake_withdraw_3, container, false);
        mWithdrawAmount      = rootView.findViewById(R.id.withdraw_amount);
        mWithdrawDenom       = rootView.findViewById(R.id.withdraw_amount_denom);
        mFeeAmount          = rootView.findViewById(R.id.fees_amount);
        mFeeDenom           = rootView.findViewById(R.id.fees_denom);
        mMemo               = rootView.findViewById(R.id.memo);
        mBeforeBtn          = rootView.findViewById(R.id.btn_before);
        mConfirmBtn         = rootView.findViewById(R.id.btn_confirm);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mWithdrawDenom);
        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mFeeDenom);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        BigDecimal toDeleagteAmount = new BigDecimal(getSActivity().mToWithdrawCoin.amount);
        BigDecimal feeAmount = new BigDecimal(getSActivity().mFee.amount.get(0).amount);

        if (getSActivity().mBaseChain.equals(OK_TEST)) {
            mWithdrawAmount.setText(WDp.getDpAmount2(getContext(), toDeleagteAmount, 0, 8));
            mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, 0, 8));

        }
        mMemo.setText(getSActivity().mMemo);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartWithdraw();

        }
    }

    private OKUnbondingActivity getSActivity() {
        return (OKUnbondingActivity)getBaseActivity();
    }
}