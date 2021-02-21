package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.ReInvestActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;

public class ReInvestStep0Fragment extends BaseFragment implements View.OnClickListener {

    private CardView        mCardReward;
    private TextView        mTvRewardAmount, mTvRewardDenom;
    private TextView        mTvFromValidators;

    private RelativeLayout  mProgressBar;
    private Button          mCancelBtn, mNextBtn;

    public static ReInvestStep0Fragment newInstance(Bundle bundle) {
        ReInvestStep0Fragment fragment = new ReInvestStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reinvest_step0, container, false);
        mCardReward             = rootView.findViewById(R.id.reward_card);
        mTvRewardAmount         = rootView.findViewById(R.id.reward_amount);
        mTvRewardDenom          = rootView.findViewById(R.id.reward_amount_title);
        mTvFromValidators       = rootView.findViewById(R.id.reward_moniker);
        mProgressBar            = rootView.findViewById(R.id.reward_progress);
        mCancelBtn              = rootView.findViewById(R.id.btn_cancel);
        mNextBtn                = rootView.findViewById(R.id.btn_next);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mTvRewardDenom);

        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mNextBtn.setClickable(false);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if (getSActivity().mBaseChain.equals(COSMOS_MAIN) || getSActivity().mBaseChain.equals(COSMOS_TEST) || getSActivity().mBaseChain.equals(IRIS_TEST)) {
            if (getSActivity().mReinvestCoin != null) {
                BigDecimal rewardSum = new BigDecimal(getSActivity().mReinvestCoin.amount).setScale(0, BigDecimal.ROUND_DOWN);
                mTvRewardAmount.setText(WDp.getDpAmount2(getContext(), rewardSum, 6, 6));
            }
            mTvFromValidators.setText(WDp.getValidatorInfo(getSActivity().getBaseDao(), getSActivity().mValOpAddress).description.moniker);

            mProgressBar.setVisibility(View.GONE);
            mNextBtn.setClickable(true);

        } else  if (getSActivity().mBaseChain.equals(COSMOS_MAIN) || getSActivity().mBaseChain.equals(COSMOS_TEST) || getSActivity().mBaseChain.equals(IRIS_TEST)) {
            if (getSActivity().mReinvestCoin != null) {
                BigDecimal rewardSum = new BigDecimal(getSActivity().mReinvestCoin.amount).setScale(0, BigDecimal.ROUND_DOWN);
                mTvRewardAmount.setText(WDp.getDpAmount2(getContext(), rewardSum, 6, 6));
            }
            mTvFromValidators.setText(getSActivity().getBaseDao().getValidatorInfo(getSActivity().mValOpAddress).getDescription().getMoniker());

            mProgressBar.setVisibility(View.GONE);
            mNextBtn.setClickable(true);

        } else {
            if(getSActivity().mReinvestCoin != null) {
                BigDecimal rewardSum = new BigDecimal(getSActivity().mReinvestCoin.amount).setScale(0, BigDecimal.ROUND_DOWN);
                if (getSActivity().mBaseChain.equals(KAVA_MAIN) || getSActivity().mBaseChain.equals(KAVA_TEST) ||
                        getSActivity().mBaseChain.equals(BAND_MAIN) || getSActivity().mBaseChain.equals(IOV_MAIN) || getSActivity().mBaseChain.equals(IOV_TEST) ||
                        getSActivity().mBaseChain.equals(CERTIK_MAIN) || getSActivity().mBaseChain.equals(CERTIK_TEST) || getSActivity().mBaseChain.equals(AKASH_MAIN) || getSActivity().mBaseChain.equals(SECRET_MAIN)) {
                    mTvRewardAmount.setText(WDp.getDpAmount2(getContext(), rewardSum, 6, 6));

                } else if (getSActivity().mBaseChain.equals(IRIS_MAIN)) {
                    mTvRewardAmount.setText(WDp.getDpAmount2(getContext(), rewardSum, 18, 18));
                }
                mTvFromValidators.setText(getSActivity().mValidator.description.moniker);

                mProgressBar.setVisibility(View.GONE);
                mNextBtn.setClickable(true);
            }
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

    private ReInvestActivity getSActivity() {
        return (ReInvestActivity)getBaseActivity();
    }
}
