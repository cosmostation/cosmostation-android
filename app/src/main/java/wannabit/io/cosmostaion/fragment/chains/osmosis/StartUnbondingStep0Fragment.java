package wannabit.io.cosmostaion.fragment.chains.osmosis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import osmosis.lockup.Lock;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.StartUnbondingActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class StartUnbondingStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mCancelBtn, mNextBtn;

    private TextView mToUnbondingIds;
    private TextView mToUnbondingAmount, mToUnbondingDenom;
    private TextView mToUnbondingDuration;

    public static StartUnbondingStep0Fragment newInstance(Bundle bundle) {
        StartUnbondingStep0Fragment fragment = new StartUnbondingStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_start_unbonding_step0, container, false);
        mCancelBtn = rootView.findViewById(R.id.btn_cancel);
        mNextBtn = rootView.findViewById(R.id.nextButton);

        mToUnbondingIds = rootView.findViewById(R.id.unbonding_ids);
        mToUnbondingAmount = rootView.findViewById(R.id.unbonding_amount);
        mToUnbondingDenom = rootView.findViewById(R.id.unbonding_amount_symbol);
        mToUnbondingDuration = rootView.findViewById(R.id.unbonding_duration);

        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        onInitView();
        return rootView;
    }

    private void onInitView() {
        long toUnbondingDuration = getSActivity().mOsmosisLockups.get(0).getDuration().getSeconds();
        String toUnbondingDenom = getSActivity().mOsmosisLockups.get(0).getCoins(0).getDenom();
        String ids = "";
        BigDecimal toUnbondingAmount = BigDecimal.ZERO;
        for (Lock.PeriodLock lockup : getSActivity().mOsmosisLockups) {
            ids = ids + "# " + lockup.getID() + "  ";
            toUnbondingAmount = toUnbondingAmount.add(new BigDecimal(lockup.getCoins(0).getAmount()));
        }

        mToUnbondingIds.setText(ids);
        WDp.showCoinDp(getSActivity(), getBaseDao(), toUnbondingDenom, toUnbondingAmount.toPlainString(), mToUnbondingDenom, mToUnbondingAmount, BaseChain.OSMOSIS_MAIN);
        if (toUnbondingDuration == 86400) {
            mToUnbondingDuration.setText("1 Day");
        } else if (toUnbondingDuration == 604800) {
            mToUnbondingDuration.setText("7 Days");
        } else if (toUnbondingDuration == 1209600) {
            mToUnbondingDuration.setText("14 Days");
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            getSActivity().onNextStep();
        }
    }

    private StartUnbondingActivity getSActivity() {
        return (StartUnbondingActivity) getBaseActivity();
    }
}
