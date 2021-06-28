package wannabit.io.cosmostaion.fragment.chains.starname;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.Calendar;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.starname.RegisterStarNameDomainActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class RegisterDomain3Fragment extends BaseFragment implements View.OnClickListener {

    private Button mBeforeBtn, mConfirmBtn;
    private TextView mFeeAmount, mStarnameFeeAmount;
    private TextView mDomain, mExpireTime, mDomainType, mMemo;

    public static RegisterDomain3Fragment newInstance(Bundle bundle) {
        RegisterDomain3Fragment fragment = new RegisterDomain3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView       = inflater.inflate(R.layout.fragment_register_domain3, container, false);
        mBeforeBtn          = rootView.findViewById(R.id.btn_before);
        mConfirmBtn         = rootView.findViewById(R.id.btn_confirm);
        mFeeAmount          = rootView.findViewById(R.id.tx_fee_amount);
        mStarnameFeeAmount  = rootView.findViewById(R.id.starname_fee_amount);
        mDomain             = rootView.findViewById(R.id.domain_name);
        mExpireTime         = rootView.findViewById(R.id.expire_time);
        mDomainType         = rootView.findViewById(R.id.domain_type);
        mMemo               = rootView.findViewById(R.id.memo);
        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        BigDecimal starnameFeeAmount = getBaseDao().getStarNameRegisterDomainFee(getSActivity().mToRegDomain, getSActivity().mType);
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);

        mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, 6, 6));
        mStarnameFeeAmount.setText(WDp.getDpAmount2(getContext(), starnameFeeAmount, 6, 6));
        mExpireTime.setText(WDp.getDpTime(getContext(), getBaseDao().getStarNameRegisterDomainExpireTime()));

        mDomain.setText("*" + getSActivity().mToRegDomain);
        mDomainType.setText(getSActivity().mType);
        mMemo.setText(getSActivity().mTxMemo);
    }


    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartRegDomain();
        }
    }

    private RegisterStarNameDomainActivity getSActivity() {
        return (RegisterStarNameDomainActivity)getBaseActivity();
    }
}