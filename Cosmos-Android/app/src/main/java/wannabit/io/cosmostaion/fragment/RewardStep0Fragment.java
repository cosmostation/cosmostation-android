package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.ClaimRewardActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class RewardStep0Fragment extends BaseFragment implements View.OnClickListener {

    private CardView    mCardReward;
    private TextView    mTvAtomReward, mTvPhotonReward;
    private TextView    mTvAtomTitle, mTvPhotonTitle;
    private TextView    mTvFromValidators;
    private TextView    mTvGoalAddress, mTvSelf;
    private ProgressBar mProgressBar;
    private Button      mCancelBtn, mNextBtn;


    public static RewardStep0Fragment newInstance(Bundle bundle) {
        RewardStep0Fragment fragment = new RewardStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reward_step0, container, false);
        mCardReward             = rootView.findViewById(R.id.reward_card);
        mTvAtomReward           = rootView.findViewById(R.id.reward_atom);
        mTvPhotonReward         = rootView.findViewById(R.id.reward_photon);
        mTvFromValidators       = rootView.findViewById(R.id.reward_moniker);
        mTvGoalAddress          = rootView.findViewById(R.id.reward_receive_address);
        mTvSelf                 = rootView.findViewById(R.id.reward_receive_address_self);
        mProgressBar            = rootView.findViewById(R.id.reward_progress);
        mCancelBtn              = rootView.findViewById(R.id.btn_cancel);
        mNextBtn                = rootView.findViewById(R.id.btn_next);
        mTvAtomTitle            = rootView.findViewById(R.id.reward_atom_title);
        mTvPhotonTitle          = rootView.findViewById(R.id.reward_photon_title);
        mTvAtomTitle.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
        mTvPhotonTitle.setText(WDp.DpPoton(getContext(), getSActivity().mAccount.baseChain));

        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mNextBtn.setClickable(false);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if(getSActivity().isAll) {
            // get all reward
            mTvAtomReward.setText(WDp.getDpAmount(getContext(), getSActivity().mTotalReward.getAtomAmount(), 6));
            mTvPhotonReward.setText(WDp.getDpAmount(getContext(), getSActivity().mTotalReward.getPhotonAmount(), 6));
            mTvFromValidators.setText("from " + getSActivity().mBondings.size() + "Validators");

        } else {
            // get single reward
            if(getSActivity().mRewards == null) {
                getSActivity().onBackPressed();
            }
            mTvAtomReward.setText(WDp.getDpAmount(getContext(), getSActivity().mRewards.getAtomAmount(), 6));
            mTvPhotonReward.setText(WDp.getDpAmount(getContext(), getSActivity().mRewards.getPhotonAmount(), 6));
            mTvFromValidators.setText(getSActivity().mValidator.description.moniker);
        }


        mTvGoalAddress.setText(getSActivity().mWithdrawAddress);
        if(getSActivity().mWithdrawAddress.equals(getSActivity().mAccount.address)) mTvSelf.setVisibility(View.VISIBLE);
        else mTvSelf.setVisibility(View.GONE);
        mCardReward.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        mNextBtn.setClickable(true);
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            getSActivity().onNextStep();
        }
    }

    private ClaimRewardActivity getSActivity() {
        return (ClaimRewardActivity)getBaseActivity();
    }
}
