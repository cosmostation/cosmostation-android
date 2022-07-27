package wannabit.io.cosmostaion.fragment.txs.starname;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import starnamed.x.starname.v1beta1.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.starname.RegisterStarNameAccountActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class RegisterAccount4Fragment extends BaseFragment implements View.OnClickListener {

    private Button mBeforeBtn, mConfirmBtn;
    private TextView mFeeAmount, mStarnameFeeAmount;
    private TextView mAccount, mExpireTime, mAddresses, mMemo;

    public static RegisterAccount4Fragment newInstance() {
        return new RegisterAccount4Fragment();
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
        BigDecimal starNameFee = getBaseDao().getStarNameRegisterAccountFee("open");

        mFeeAmount.setText(WDp.getDpAmount2(getContext(), new BigDecimal(getSActivity().mTxFee.amount.get(0).amount), 6, 6));
        mStarnameFeeAmount.setText(WDp.getDpAmount2(getContext(), starNameFee, 6, 6));
        mExpireTime.setText(WDp.getDpTime(getContext(), getBaseDao().getStarNameRegisterDomainExpireTime()));

        mAccount.setText(getSActivity().mStarNameAccount + "*" + getSActivity().mStarNameDomain);
        String addresses = "";
        for (Types.Resource resource: getSActivity().mStarNameResources) {
            addresses = addresses + resource.getUri() + "\n" + resource.getResource() + "\n\n";
        }
        mAddresses.setText(addresses);
        mMemo.setText(getSActivity().mTxMemo);
    }


    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartRegAccount();
        }
    }

    private RegisterStarNameAccountActivity getSActivity() {
        return (RegisterStarNameAccountActivity)getBaseActivity();
    }
}