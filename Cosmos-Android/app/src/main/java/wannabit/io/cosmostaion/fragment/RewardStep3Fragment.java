package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.ClaimRewardActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class RewardStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView        mTvAtomReward, mTvPhotonReward;
    private TextView        mFeeAmount, mFeeType;
    private TextView        mTvFromValidators;
    private TextView        mTvGoalAddress, mTvSelf, mMemo;
    private TextView        mRemindAtom, mRemindPhoton;
    private Button          mBeforeBtn, mConfirmBtn;

    public static RewardStep3Fragment newInstance(Bundle bundle) {
        RewardStep3Fragment fragment = new RewardStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reward_step3, container, false);
        mTvAtomReward       = rootView.findViewById(R.id.reward_atom);
        mTvPhotonReward     = rootView.findViewById(R.id.reward_photon);
        mFeeAmount          = rootView.findViewById(R.id.reward_fees);
        mFeeType            = rootView.findViewById(R.id.reward_fees_type);
        mTvFromValidators   = rootView.findViewById(R.id.reward_moniker);
        mTvGoalAddress      = rootView.findViewById(R.id.reward_receive_address);
        mTvSelf             = rootView.findViewById(R.id.reward_receive_address_self);
        mMemo               = rootView.findViewById(R.id.memo);
        mRemindAtom         = rootView.findViewById(R.id.remind_atom);
        mRemindPhoton       = rootView.findViewById(R.id.remind_photon);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);
        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onRefreshTab() {
        BigDecimal rewardAtom = BigDecimal.ZERO;
        BigDecimal rewardPhoton = BigDecimal.ZERO;
        BigDecimal remindAtom = getSActivity().mAccount.getAtomBalance();
        BigDecimal remindPhoton = getSActivity().mAccount.getPhotonBalance();

        if(getSActivity().isAll) {
            rewardAtom = getSActivity().mTotalReward.getAtomAmount();
            rewardPhoton = getSActivity().mTotalReward.getPhotonAmount();
            mTvFromValidators.setText("from " + getSActivity().mBondings.size() + "Validators");

        } else {
            rewardAtom = getSActivity().mRewards.getAtomAmount();
            rewardPhoton = getSActivity().mRewards.getPhotonAmount();
            mTvFromValidators.setText(getSActivity().mValidator.description.moniker);
        }
        mTvAtomReward.setText(WDp.getDpAmount(getContext(), rewardAtom, 0));
        mTvPhotonReward.setText(WDp.getDpAmount(getContext(), rewardPhoton, 0));

        remindAtom  =remindAtom.add(rewardAtom);
        remindPhoton = remindPhoton.add(rewardPhoton);

        if(getSActivity().mRewardFee.denom.equals(BaseConstant.COSMOS_ATOM)) {
            mFeeType.setText(BaseConstant.COSMOS_ATOM);
            mFeeType.setTextColor(getResources().getColor(R.color.colorAtom));
            remindAtom.subtract(new BigDecimal(getSActivity().mRewardFee.amount));
        } else {
            mFeeType.setText(BaseConstant.COSMOS_PHOTON);
            mFeeType.setTextColor(getResources().getColor(R.color.colorPhoton));
            remindPhoton.subtract(new BigDecimal(getSActivity().mRewardFee.amount));
        }
        mFeeAmount.setText(WDp.getDpAmount(getContext(), new BigDecimal(getSActivity().mRewardFee.amount), 6));
        mTvGoalAddress.setText(getSActivity().mWithdrawAddress);
        if(getSActivity().mWithdrawAddress.equals(getSActivity().mAccount.address)) mTvSelf.setVisibility(View.VISIBLE);
        mMemo.setText(getSActivity().mRewardMemo);

        mRemindAtom.setText(WDp.getDpAmount(getContext(), remindAtom, 6));
        mRemindPhoton.setText(WDp.getDpAmount(getContext(), remindPhoton, 6));

    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            WLog.w("mConfirmBtn");

        }

    }


    private ClaimRewardActivity getSActivity() {
        return (ClaimRewardActivity)getBaseActivity();
    }
}