package wannabit.io.cosmostaion.fragment.txs.kava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.ClaimIncentiveActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class ClaimIncentiveStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mCancelBtn, mNextBtn;
    private LinearLayout mIncen0Layer, mIncen1Layer, mIncen2Layer, mIncen3Layer, mIncen4Layer;
    private TextView mIncen0Amount, mIncen1Amount, mIncen2Amount, mIncen3Amount, mIncen4Amount;
    private TextView mIncen0Denom, mIncen1Denom, mIncen2Denom, mIncen3Denom, mIncen4Denom;

    private IncentiveReward mIncentiveReward;

    public static ClaimIncentiveStep0Fragment newInstance() {
        return new ClaimIncentiveStep0Fragment();
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
        mIncen0Layer = rootView.findViewById(R.id.incen0Layer);
        mIncen0Amount = rootView.findViewById(R.id.incen0_amount);
        mIncen0Denom = rootView.findViewById(R.id.incen0_denom);
        mIncen1Layer = rootView.findViewById(R.id.incen1Layer);
        mIncen1Amount = rootView.findViewById(R.id.incen1_amount);
        mIncen1Denom = rootView.findViewById(R.id.incen1_denom);
        mIncen2Layer = rootView.findViewById(R.id.incen2Layer);
        mIncen2Amount = rootView.findViewById(R.id.incen2_amount);
        mIncen2Denom = rootView.findViewById(R.id.incen2_denom);
        mIncen3Layer = rootView.findViewById(R.id.incen3Layer);
        mIncen3Amount = rootView.findViewById(R.id.incen3_amount);
        mIncen3Denom = rootView.findViewById(R.id.incen3_denom);
        mIncen4Layer = rootView.findViewById(R.id.incen4Layer);
        mIncen4Amount = rootView.findViewById(R.id.incen4_amount);
        mIncen4Denom = rootView.findViewById(R.id.incen4_denom);

        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        onUpdateView();
        return rootView;
    }

    private void onUpdateView() {
        mIncentiveReward = getBaseDao().mIncentiveRewards;

        ArrayList<Coin> IncentiveCoins = mIncentiveReward.getAllIncentives();
        if (IncentiveCoins.size() > 0) {
            mIncen0Layer.setVisibility(View.VISIBLE);
            WDp.setDpCoin(getActivity(), getBaseDao(), getSActivity().mChainConfig, IncentiveCoins.get(0), mIncen0Denom, mIncen0Amount);
        }
        if (IncentiveCoins.size() > 1) {
            mIncen1Layer.setVisibility(View.VISIBLE);
            WDp.setDpCoin(getActivity(), getBaseDao(), getSActivity().mChainConfig, IncentiveCoins.get(1), mIncen1Denom, mIncen1Amount);
        }
        if (IncentiveCoins.size() > 2) {
            mIncen2Layer.setVisibility(View.VISIBLE);
            WDp.setDpCoin(getActivity(), getBaseDao(), getSActivity().mChainConfig, IncentiveCoins.get(2), mIncen2Denom, mIncen2Amount);
        }
        if (IncentiveCoins.size() > 3) {
            mIncen3Layer.setVisibility(View.VISIBLE);
            WDp.setDpCoin(getActivity(), getBaseDao(), getSActivity().mChainConfig, IncentiveCoins.get(3), mIncen3Denom, mIncen3Amount);
        }
        if (IncentiveCoins.size() > 4) {
            mIncen4Layer.setVisibility(View.VISIBLE);
            WDp.setDpCoin(getActivity(), getBaseDao(), getSActivity().mChainConfig, IncentiveCoins.get(4), mIncen4Denom, mIncen4Amount);
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

    private ClaimIncentiveActivity getSActivity() {
        return (ClaimIncentiveActivity) getBaseActivity();
    }
}
