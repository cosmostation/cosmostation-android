package wannabit.io.cosmostaion.fragment.chains.sif;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.sif.SifIncentiveActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.utils.WDp;

public class SifIncentiveStep3Frament extends BaseFragment implements View.OnClickListener, IRefreshTabListener {

    private TextView mFeeAmount;
    private TextView mFeeAmountSymbol;
    private TextView mClaimAddress;
    private TextView mClaimType;
    private TextView mMemo;

    private Button mBeforeBtn, mConfirmBtn;

    public static SifIncentiveStep3Frament newInstance(Bundle bundle) {
        SifIncentiveStep3Frament fragment = new SifIncentiveStep3Frament();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sif_incentive_step3, container, false);
        mFeeAmount = rootView.findViewById(R.id.tx_fees);
        mFeeAmountSymbol = rootView.findViewById(R.id.tx_fees_type);
        mClaimAddress = rootView.findViewById(R.id.claim_address);
        mClaimType = rootView.findViewById(R.id.incentive_type);
        mMemo = rootView.findViewById(R.id.memo);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);

        WDp.DpMainDenom(getSActivity().mAccount.baseChain, mFeeAmountSymbol);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);

        mFeeAmount.setText(WDp.getDpAmount2(feeAmount, 18, 18));
        mClaimAddress.setText(getSActivity().mAccount.address);
        mClaimType.setText("liquidityMining");

        mMemo.setText(getSActivity().mTxMemo);
    }

    private SifIncentiveActivity getSActivity() {
        return (SifIncentiveActivity) getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartIncentiveClaim();
        }
    }
}
