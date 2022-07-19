package wannabit.io.cosmostaion.fragment.txs.kava;

import static wannabit.io.cosmostaion.base.chains.Kava.KAVA_HARD_DENOM;
import static wannabit.io.cosmostaion.base.chains.Kava.KAVA_SWP_DENOM;

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
import wannabit.io.cosmostaion.activities.txs.kava.ClaimIncentiveActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.kava.IncentiveParam;
import wannabit.io.cosmostaion.utils.WDp;

public class ClaimIncentiveStep3Fragment extends BaseFragment implements View.OnClickListener {

    private Button mBackBtn, mConfirmBtn;
    private TextView mFee, mFeeDenom;
    private TextView mKavaAmount, mHardAmount, mSwpAmount;
    private TextView mLockTime, mMemo;

    private IncentiveParam  mIncentiveParam;


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
        mBackBtn                = rootView.findViewById(R.id.btn_before);
        mConfirmBtn             = rootView.findViewById(R.id.btn_confirm);
        mFee                    = rootView.findViewById(R.id.fee_amount);
        mFeeDenom               = rootView.findViewById(R.id.fee_denom);
        mKavaAmount             = rootView.findViewById(R.id.tx_incentive_kava_amount);
        mHardAmount             = rootView.findViewById(R.id.tx_incentive_hard_amount);
        mSwpAmount              = rootView.findViewById(R.id.tx_incentive_swp_amount);
        mLockTime               = rootView.findViewById(R.id.lockup_time);
        mMemo                   = rootView.findViewById(R.id.memo);

        mBackBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mIncentiveParam = getBaseDao().mIncentiveParam;
        WDp.dpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeDenom, mFee);

        BigDecimal kavaIncentiveAmount = getBaseDao().mIncentiveRewards.getRewardSum(getSActivity().mChainConfig.mainDenom());
        BigDecimal hardIncentiveAmount = getBaseDao().mIncentiveRewards.getRewardSum(KAVA_HARD_DENOM);
        BigDecimal swpIncentiveAmount  = getBaseDao().mIncentiveRewards.getRewardSum(KAVA_SWP_DENOM);

        if (getSActivity().mIncentiveMultiplier.equalsIgnoreCase("small")) {
            mLockTime.setText("1 Month");
            kavaIncentiveAmount = kavaIncentiveAmount.multiply(mIncentiveParam.getFactor(getSActivity().mChainConfig.mainDenom(), 0)).setScale(0, RoundingMode.DOWN);
            hardIncentiveAmount = hardIncentiveAmount.multiply(mIncentiveParam.getFactor(KAVA_HARD_DENOM, 0)).setScale(0, RoundingMode.DOWN);
            swpIncentiveAmount = swpIncentiveAmount.multiply(mIncentiveParam.getFactor(KAVA_SWP_DENOM, 0)).setScale(0, RoundingMode.DOWN);

        } else {
            mLockTime.setText("12 Month");
            kavaIncentiveAmount = kavaIncentiveAmount.multiply(mIncentiveParam.getFactor(getSActivity().mChainConfig.mainDenom(), 1)).setScale(0, RoundingMode.DOWN);
            hardIncentiveAmount = hardIncentiveAmount.multiply(mIncentiveParam.getFactor(KAVA_HARD_DENOM, 1)).setScale(0, RoundingMode.DOWN);
            swpIncentiveAmount = swpIncentiveAmount.multiply(mIncentiveParam.getFactor(KAVA_SWP_DENOM, 1)).setScale(0, RoundingMode.DOWN);
        }

        mKavaAmount.setText(WDp.getDpAmount2(getSActivity(), kavaIncentiveAmount, 6, 6));
        mHardAmount.setText(WDp.getDpAmount2(getSActivity(), hardIncentiveAmount, 6, 6));
        mSwpAmount.setText(WDp.getDpAmount2(getSActivity(), swpIncentiveAmount, 6, 6));
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
        return (ClaimIncentiveActivity)getBaseActivity();
    }
}