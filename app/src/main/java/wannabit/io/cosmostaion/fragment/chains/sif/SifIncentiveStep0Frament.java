package wannabit.io.cosmostaion.fragment.chains.sif;

import android.content.Intent;
import android.net.Uri;
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
import wannabit.io.cosmostaion.utils.WDp;

public class SifIncentiveStep0Frament extends BaseFragment implements View.OnClickListener {
    private TextView mIncentiveAmount;
    private TextView mIncentiveType;
    private Button mCheckReward, mCancel, mNextBtn;

    public static SifIncentiveStep0Frament newInstance(Bundle bundle) {
        SifIncentiveStep0Frament fragment = new SifIncentiveStep0Frament();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sif_incentive_step0, container, false);
        mIncentiveAmount = rootView.findViewById(R.id.incentive_amount);
        mIncentiveType = rootView.findViewById(R.id.incentive_type);
        mCheckReward = rootView.findViewById(R.id.btn_reward_program);
        mCancel = rootView.findViewById(R.id.btn_cancel);
        mNextBtn = rootView.findViewById(R.id.btn_next);

        mCheckReward.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        if (getBaseDao().mSifLmIncentive != null) {
            BigDecimal lmCurrentAmount = new BigDecimal(getBaseDao().mSifLmIncentive.totalClaimableCommissionsAndClaimableRewards);
            mIncentiveAmount.setText(WDp.getDpAmount2(getSActivity(), lmCurrentAmount, 0, 6));
            mIncentiveType.setText("liquidityMining");
        }
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCheckReward)) {
            String url = "https://docs.sifchain.finance/resources/rewards-programs#special-programs";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
        if (v.equals(mCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            getSActivity().onNextStep();
        }
    }

    private SifIncentiveActivity getSActivity() {
        return (SifIncentiveActivity) getBaseActivity();
    }
}
