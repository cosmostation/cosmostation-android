package wannabit.io.cosmostaion.fragment.chains.starname;

import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.IOV_MSG_TYPE_RENEW_DOMAIN;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.starname.ReNewStarNameActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.utils.WDp;

public class RenewStarName3Fragment extends BaseFragment implements View.OnClickListener, IRefreshTabListener {

    private Button mBeforeBtn, mConfirmBtn;
    private TextView mFeeAmount, mStarnameFeeAmount;
    private TextView mStarName, mCurrentExpireTime, mToExpireTime, mMemo;

    public static RenewStarName3Fragment newInstance(Bundle bundle) {
        RenewStarName3Fragment fragment = new RenewStarName3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_renew_starname_3, container, false);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);
        mFeeAmount = rootView.findViewById(R.id.tx_fee_amount);
        mStarnameFeeAmount = rootView.findViewById(R.id.starname_fee_amount);
        mStarName = rootView.findViewById(R.id.to_renew_starname);
        mCurrentExpireTime = rootView.findViewById(R.id.current_expire_time);
        mToExpireTime = rootView.findViewById(R.id.to_renew_expire_time);
        mMemo = rootView.findViewById(R.id.memo);
        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);

        if (getSActivity().mBaseChain.equals(IOV_MAIN)) {
            mFeeAmount.setText(WDp.getDpAmount2(feeAmount, 6, 6));
        }

        BigDecimal starnameFee = BigDecimal.ZERO;
        if (getSActivity().mRenewType.equals(IOV_MSG_TYPE_RENEW_DOMAIN)) {
            mStarName.setText("*" + getSActivity().mStarNameDomain);
            starnameFee = getBaseDao().getStarNameRenewDomainFee(getSActivity().mStarNameDomain, getSActivity().mStarNameDomainType);
        } else {
            mStarName.setText(getSActivity().mStarNameAccount + "*" + getSActivity().mStarNameDomain);
            starnameFee = getBaseDao().getStarNameRenewAccountFee(getSActivity().mStarNameDomainType);
        }
        mCurrentExpireTime.setText(WDp.getDpTime(getContext(), getSActivity().mValidTime * 1000));
        mToExpireTime.setText(WDp.getDpTime(getContext(), getBaseDao().getRenewExpireTime(getSActivity().mRenewType, getSActivity().mValidTime * 1000)));
        mStarnameFeeAmount.setText(WDp.getDpAmount2(starnameFee, 6, 6));
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onRenewStarName();
        }
    }

    private ReNewStarNameActivity getSActivity() {
        return (ReNewStarNameActivity) getBaseActivity();
    }
}
