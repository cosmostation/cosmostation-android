package wannabit.io.cosmostaion.fragment.chains.starname;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.starname.RegisterAccountActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.StarNameResource;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;

public class RegisterAccount4Fragment extends BaseFragment implements View.OnClickListener {

    private Button mBeforeBtn, mConfirmBtn;
    private TextView mFeeAmount, mStarnameFeeAmount;
    private TextView mAccount, mExpireTime, mAddresses, mMemo;

    public static RegisterAccount4Fragment newInstance(Bundle bundle) {
        RegisterAccount4Fragment fragment = new RegisterAccount4Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register_account4, container, false);
        mBeforeBtn          = rootView.findViewById(R.id.btn_before);
        mConfirmBtn         = rootView.findViewById(R.id.btn_confirm);
        mFeeAmount          = rootView.findViewById(R.id.tx_fee_amount);
        mStarnameFeeAmount  = rootView.findViewById(R.id.starname_fee_amount);
        mAccount            = rootView.findViewById(R.id.starname_account);
        mExpireTime         = rootView.findViewById(R.id.expire_time);
        mAddresses          = rootView.findViewById(R.id.addresses);
        mMemo               = rootView.findViewById(R.id.memo);
        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        BigDecimal starnameFeeAmount = getBaseDao().mStarNameFee.getAccountFee(true);
        BigDecimal feeAmount = new BigDecimal(getSActivity().mFee.amount.get(0).amount);

        if (getSActivity().mBaseChain.equals(IOV_MAIN) || getSActivity().mBaseChain.equals(IOV_TEST)) {
            mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, 6, 6));
            mStarnameFeeAmount.setText(WDp.getDpAmount2(getContext(), starnameFeeAmount, 6, 6));

        }

        mExpireTime.setText(getBaseDao().mStarNameConfig.getRegisterDomainExpireTime(getContext()));
        mAccount.setText(getSActivity().mToRegAccount+ "*" + getSActivity().mToRegDomain);
        String addresses = "";
        for (StarNameResource resource:getSActivity().mResources) {
            addresses = addresses + resource.uri + "\n" + resource.resource + "\n\n";
        }
        mAddresses.setText(addresses);
        mMemo.setText(getSActivity().mMemo);
    }


    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartRegAccount();
        }
    }

    private RegisterAccountActivity getSActivity() {
        return (RegisterAccountActivity)getBaseActivity();
    }
}