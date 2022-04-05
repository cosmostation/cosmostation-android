package wannabit.io.cosmostaion.fragment.chains.kava;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SWP;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.ClaimIncentiveActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.model.kava.IncentiveParam;
import wannabit.io.cosmostaion.utils.WDp;

public class ClaimIncentiveStep3Fragment extends BaseFragment implements View.OnClickListener, IRefreshTabListener {

    private Button mBackBtn, mConfirmBtn;
    private TextView mFee, mFeeDenom;
    private TextView mKavaAmount, mHardAmount, mSwpAmount;
    private TextView mLockTime, mMemo;

    private IncentiveParam mIncentiveParam;


    public static ClaimIncentiveStep3Fragment newInstance(Bundle bundle) {
        ClaimIncentiveStep3Fragment fragment = new ClaimIncentiveStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_claim_incentive_3, container, false);
        mBackBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);
        mFee = rootView.findViewById(R.id.fee_amount);
        mFeeDenom = rootView.findViewById(R.id.fee_denom);
        mKavaAmount = rootView.findViewById(R.id.tx_incentive_kava_amount);
        mHardAmount = rootView.findViewById(R.id.tx_incentive_hard_amount);
        mSwpAmount = rootView.findViewById(R.id.tx_incentive_swp_amount);
        mLockTime = rootView.findViewById(R.id.lockup_time);
        mMemo = rootView.findViewById(R.id.memo);

        WDp.DpMainDenom(getSActivity().account.baseChain, mFeeDenom);

        mBackBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mIncentiveParam = getBaseDao().mIncentiveParam5;
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        mFee.setText(WDp.getDpAmount2(feeAmount, 6, 6));

        BigDecimal kavaIncentiveAmount = getBaseDao().mIncentiveRewards.getRewardSum(TOKEN_KAVA);
        BigDecimal hardIncentiveAmount = getBaseDao().mIncentiveRewards.getRewardSum(TOKEN_HARD);
        BigDecimal swpIncentiveAmount = getBaseDao().mIncentiveRewards.getRewardSum(TOKEN_SWP);

        if (getSActivity().mIncentiveMultiplier.equalsIgnoreCase("small")) {
            mLockTime.setText("1 Month");
            kavaIncentiveAmount = kavaIncentiveAmount.multiply(mIncentiveParam.getFactor(TOKEN_KAVA, 0)).setScale(0, RoundingMode.DOWN);
            hardIncentiveAmount = hardIncentiveAmount.multiply(mIncentiveParam.getFactor(TOKEN_HARD, 0)).setScale(0, RoundingMode.DOWN);
            swpIncentiveAmount = swpIncentiveAmount.multiply(mIncentiveParam.getFactor(TOKEN_SWP, 0)).setScale(0, RoundingMode.DOWN);

        } else {
            mLockTime.setText("12 Month");
            kavaIncentiveAmount = kavaIncentiveAmount.multiply(mIncentiveParam.getFactor(TOKEN_KAVA, 1)).setScale(0, RoundingMode.DOWN);
            hardIncentiveAmount = hardIncentiveAmount.multiply(mIncentiveParam.getFactor(TOKEN_HARD, 1)).setScale(0, RoundingMode.DOWN);
            swpIncentiveAmount = swpIncentiveAmount.multiply(mIncentiveParam.getFactor(TOKEN_SWP, 1)).setScale(0, RoundingMode.DOWN);
        }

        mKavaAmount.setText(WDp.getDpAmount2(kavaIncentiveAmount, 6, 6));
        mHardAmount.setText(WDp.getDpAmount2(hardIncentiveAmount, 6, 6));
        mSwpAmount.setText(WDp.getDpAmount2(swpIncentiveAmount, 6, 6));
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBackBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartIncentiveClaim();
        }

    }

    private ClaimIncentiveActivity getSActivity() {
        return (ClaimIncentiveActivity) getBaseActivity();
    }
}