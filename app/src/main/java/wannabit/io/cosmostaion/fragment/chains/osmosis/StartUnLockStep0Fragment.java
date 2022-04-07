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
import wannabit.io.cosmostaion.activities.chains.osmosis.StartUnlockActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class StartUnLockStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mCancelBtn, mNextBtn;

    private TextView mToUnLockIds;
    private TextView mToUnLockAmount, mToUnLockDenom;

    public static StartUnLockStep0Fragment newInstance(Bundle bundle) {
        StartUnLockStep0Fragment fragment = new StartUnLockStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_start_unlock_step0, container, false);
        mCancelBtn = rootView.findViewById(R.id.cancelButton);
        mNextBtn = rootView.findViewById(R.id.nextButton);

        mToUnLockIds = rootView.findViewById(R.id.unlock_ids);
        mToUnLockAmount = rootView.findViewById(R.id.unlock_amount);
        mToUnLockDenom = rootView.findViewById(R.id.unlock_amount_symbol);

        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        onInitView();
        return rootView;
    }

    private void onInitView() {
        String toUnlockDenom = getSActivity().mOsmosisLockups.get(0).getCoins(0).getDenom();
        String ids = "";
        BigDecimal toUnlockAmount = BigDecimal.ZERO;
        for (Lock.PeriodLock lockup : getSActivity().mOsmosisLockups) {
            ids = ids + "# " + lockup.getID() + "  ";
            toUnlockAmount = toUnlockAmount.add(new BigDecimal(lockup.getCoins(0).getAmount()));
        }
        mToUnLockIds.setText(ids);
        WDp.showCoinDp(getSActivity(), getBaseDao(), toUnlockDenom, toUnlockAmount.toPlainString(), mToUnLockDenom, mToUnLockAmount, BaseChain.OSMOSIS_MAIN);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            getSActivity().onNextStep();
        }
    }

    private StartUnlockActivity getSActivity() {
        return (StartUnlockActivity) getBaseActivity();
    }
}
