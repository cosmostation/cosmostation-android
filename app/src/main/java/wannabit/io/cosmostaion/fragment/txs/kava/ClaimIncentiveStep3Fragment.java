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
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class ClaimIncentiveStep3Fragment extends BaseFragment implements View.OnClickListener {

    private Button mBackBtn, mConfirmBtn;
    private TextView mFee, mFeeDenom;
    private LinearLayout mIncen0Layer, mIncen1Layer, mIncen2Layer, mIncen3Layer, mIncen4Layer;
    private TextView mIncen0Amount, mIncen1Amount, mIncen2Amount, mIncen3Amount, mIncen4Amount;
    private TextView mIncen0Denom, mIncen1Denom, mIncen2Denom, mIncen3Denom, mIncen4Denom;

    public static ClaimIncentiveStep3Fragment newInstance() {
        return new ClaimIncentiveStep3Fragment();
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

        mBackBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeDenom, mFee);

        ArrayList<Coin> IncentiveCoins = getBaseDao().mIncentiveRewards.getAllIncentives();
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