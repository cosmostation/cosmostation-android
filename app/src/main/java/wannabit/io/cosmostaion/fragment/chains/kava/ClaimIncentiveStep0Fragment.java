package wannabit.io.cosmostaion.fragment.chains.kava;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SWP;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.ClaimIncentiveActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.kava.IncentiveParam;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.utils.WDp;

public class ClaimIncentiveStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mCancelBtn, mNextBtn;
    private TextView mIncentiveKavaAmount, mIncentiveHardAmount, mIncentiveSwpAmount;
    private TextView mLockTime;

    private RelativeLayout BtnOption1, BtnOption2;

    private BigDecimal mKavaIncetiveAmount = BigDecimal.ZERO;
    private BigDecimal mHardIncetiveAmount = BigDecimal.ZERO;
    private BigDecimal mSwpIncetiveAmount = BigDecimal.ZERO;

    private IncentiveParam mIncentiveParam;
    private IncentiveReward mIncentiveReward;

    public static ClaimIncentiveStep0Fragment newInstance(Bundle bundle) {
        ClaimIncentiveStep0Fragment fragment = new ClaimIncentiveStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_claim_incentive_0, container, false);
        mCancelBtn = rootView.findViewById(R.id.btn_cancel);
        mNextBtn = rootView.findViewById(R.id.btn_next);

        mIncentiveKavaAmount = rootView.findViewById(R.id.tx_incentive_kava_amount);
        mIncentiveHardAmount = rootView.findViewById(R.id.tx_incentive_hard_amount);
        mIncentiveSwpAmount = rootView.findViewById(R.id.tx_incentive_swp_amount);
        mLockTime = rootView.findViewById(R.id.lockup_time);
        BtnOption1 = rootView.findViewById(R.id.btn_option1);
        BtnOption2 = rootView.findViewById(R.id.btn_option2);

        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        BtnOption1.setOnClickListener(this);
        BtnOption2.setOnClickListener(this);

        onUpdateView();
        return rootView;
    }

    private void onUpdateView() {
        mIncentiveParam = getBaseDao().mIncentiveParam5;
        mIncentiveReward = getBaseDao().mIncentiveRewards;

        if (mIncentiveReward != null) {
            mKavaIncetiveAmount = mIncentiveReward.getRewardSum(TOKEN_KAVA);
            mHardIncetiveAmount = mIncentiveReward.getRewardSum(TOKEN_HARD);
            mSwpIncetiveAmount = mIncentiveReward.getRewardSum(TOKEN_SWP);
        }

        mIncentiveKavaAmount.setText(WDp.getDpAmount2(getSActivity(), mKavaIncetiveAmount, 6, 6));
        mIncentiveHardAmount.setText(WDp.getDpAmount2(getSActivity(), mHardIncetiveAmount, 6, 6));
        mIncentiveSwpAmount.setText(WDp.getDpAmount2(getSActivity(), mSwpIncetiveAmount, 6, 6));
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if (getSActivity().mIncentiveMultiplier != null) {
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_no_incentive_claim_option, Toast.LENGTH_SHORT).show();
            }

        } else if (v.equals(BtnOption1)) {
            onInitBtnBg();
            BtnOption1.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_round_selected));
            BigDecimal kavaIncentiveCal = mKavaIncetiveAmount.multiply(mIncentiveParam.getFactor(TOKEN_KAVA, 0)).setScale(0, RoundingMode.DOWN);
            BigDecimal hardIncentiveCal = mHardIncetiveAmount.multiply(mIncentiveParam.getFactor(TOKEN_HARD, 0)).setScale(0, RoundingMode.DOWN);
            BigDecimal swpIncentiveCal = mSwpIncetiveAmount.multiply(mIncentiveParam.getFactor(TOKEN_SWP, 0)).setScale(0, RoundingMode.DOWN);
            mIncentiveKavaAmount.setText(WDp.getDpAmount2(getSActivity(), kavaIncentiveCal, 6, 6));
            mIncentiveHardAmount.setText(WDp.getDpAmount2(getSActivity(), hardIncentiveCal, 6, 6));
            mIncentiveSwpAmount.setText(WDp.getDpAmount2(getSActivity(), swpIncentiveCal, 6, 6));

            mLockTime.setText("1 Month");
            getSActivity().mIncentiveMultiplier = "small";

        } else if (v.equals(BtnOption2)) {
            onInitBtnBg();
            BtnOption2.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_round_selected));
            BigDecimal kavaIncentiveCal = mKavaIncetiveAmount.multiply(mIncentiveParam.getFactor(TOKEN_KAVA, 1)).setScale(0, RoundingMode.DOWN);
            BigDecimal hardIncentiveCal = mHardIncetiveAmount.multiply(mIncentiveParam.getFactor(TOKEN_HARD, 1)).setScale(0, RoundingMode.DOWN);
            BigDecimal swpIncentiveCal = mSwpIncetiveAmount.multiply(mIncentiveParam.getFactor(TOKEN_SWP, 1)).setScale(0, RoundingMode.DOWN);
            mIncentiveKavaAmount.setText(WDp.getDpAmount2(getSActivity(), kavaIncentiveCal, 6, 6));
            mIncentiveHardAmount.setText(WDp.getDpAmount2(getSActivity(), hardIncentiveCal, 6, 6));
            mIncentiveSwpAmount.setText(WDp.getDpAmount2(getSActivity(), swpIncentiveCal, 6, 6));

            mLockTime.setText("12 Month");
            getSActivity().mIncentiveMultiplier = "large";

        }
    }

    private void onInitBtnBg() {
        BtnOption1.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_round_unselected));
        BtnOption2.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_round_unselected));
    }

    private ClaimIncentiveActivity getSActivity() {
        return (ClaimIncentiveActivity) getBaseActivity();
    }
}
