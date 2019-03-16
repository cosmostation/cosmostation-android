package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.ClaimRewardActivity;
import wannabit.io.cosmostaion.base.BaseChain;
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
    private TextView        mRewardAtomTitle, mRewardPhotonTitle, mRemindAtomTitle, mRemindPhotonTitle;
    private RelativeLayout  mPhotonRewardLayer;

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
        mBeforeBtn          = rootView.findViewById(R.id.btn_before);
        mConfirmBtn         = rootView.findViewById(R.id.btn_confirm);
        mRewardAtomTitle    = rootView.findViewById(R.id.reward_atom_title);
        mRewardPhotonTitle  = rootView.findViewById(R.id.reward_photon_title);
        mRemindAtomTitle    = rootView.findViewById(R.id.remind_atom_title);
        mRemindPhotonTitle  = rootView.findViewById(R.id.remind_photon_title);
        mPhotonRewardLayer  = rootView.findViewById(R.id.reward_photon_layer);


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

            mTvFromValidators.setText("from " + getSActivity().mBondings.size() + "Validators");

        } else {
            if(getSActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
                rewardAtom = getSActivity().mRewards.getAtomAmount();
                mTvAtomReward.setText(WDp.getDpAmount(getContext(), rewardAtom, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
                mPhotonRewardLayer.setVisibility(View.GONE);
                if(getSActivity().mRewardFee.amount == null) {
                    mFeeType.setVisibility(View.GONE);
                    mFeeAmount.setText("FREE");
                    mFeeAmount.setTextColor(getResources().getColor(R.color.colorRed));
                } else {
                    mFeeType.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
                    mFeeType.setTextColor(getResources().getColor(R.color.colorAtom));
                    mFeeAmount.setText(new BigDecimal(getSActivity().mRewardFee.amount.get(6).amount).toPlainString());
                }

            } else {
                rewardAtom = getSActivity().mRewards.getAtomAmount();
                rewardPhoton = getSActivity().mRewards.getPhotonAmount();
                mTvAtomReward.setText(WDp.getDpAmount(getContext(), rewardAtom, 0, BaseChain.getChain(getSActivity().mAccount.baseChain)));
                mTvPhotonReward.setText(WDp.getDpAmount(getContext(), rewardPhoton, 0, BaseChain.getChain(getSActivity().mAccount.baseChain)));
                if(getSActivity().mRewardFee.amount.get(0).denom.equals(BaseConstant.COSMOS_MUON)) {
                    mFeeType.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
                    mFeeType.setTextColor(getResources().getColor(R.color.colorAtom));
                    remindAtom.subtract(new BigDecimal(getSActivity().mRewardFee.amount.get(0).amount));
                } else {
                    mFeeType.setText(WDp.DpPoton(getContext(), getSActivity().mAccount.baseChain));
                    mFeeType.setTextColor(getResources().getColor(R.color.colorPhoton));
                    remindPhoton.subtract(new BigDecimal(getSActivity().mRewardFee.amount.get(0).amount));
                }
            }
            mTvFromValidators.setText(getSActivity().mValidator.description.moniker);
        }
        mTvGoalAddress.setText(getSActivity().mWithdrawAddress);
        if(getSActivity().mWithdrawAddress.equals(getSActivity().mAccount.address)) mTvSelf.setVisibility(View.VISIBLE);
        mMemo.setText(getSActivity().mRewardMemo);

//        if(getSActivity().isAll) {
//            rewardAtom = getSActivity().mTotalReward.getAtomAmount();
//            rewardPhoton = getSActivity().mTotalReward.getPhotonAmount();
//            mTvFromValidators.setText("from " + getSActivity().mBondings.size() + "Validators");
//
//        } else {
//            rewardAtom = getSActivity().mRewards.getAtomAmount();
//            rewardPhoton = getSActivity().mRewards.getPhotonAmount();
//            mTvFromValidators.setText(getSActivity().mValidator.description.moniker);
//        }
//        mTvAtomReward.setText(WDp.getDpAmount(getContext(), rewardAtom, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
//        mTvPhotonReward.setText(WDp.getDpAmount(getContext(), rewardPhoton, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
//
//        remindAtom  =remindAtom.add(rewardAtom);
//        remindPhoton = remindPhoton.add(rewardPhoton);
//
//        if(getSActivity().mRewardFee.amount.get(0).denom.equals(BaseConstant.COSMOS_ATOM)) {
//            mFeeType.setText(BaseConstant.COSMOS_ATOM);
//            mFeeType.setTextColor(getResources().getColor(R.color.colorAtom));
//            remindAtom.subtract(new BigDecimal(getSActivity().mRewardFee.amount.get(0).amount));
//        } else {
//            mFeeType.setText(BaseConstant.COSMOS_PHOTON);
//            mFeeType.setTextColor(getResources().getColor(R.color.colorPhoton));
//            remindPhoton.subtract(new BigDecimal(getSActivity().mRewardFee.amount.get(0).amount));
//        }
//        mFeeAmount.setText(WDp.getDpAmount(getContext(), new BigDecimal(getSActivity().mRewardFee.amount.get(0).amount), 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
//        mTvGoalAddress.setText(getSActivity().mWithdrawAddress);
//        if(getSActivity().mWithdrawAddress.equals(getSActivity().mAccount.address)) mTvSelf.setVisibility(View.VISIBLE);
//        mMemo.setText(getSActivity().mRewardMemo);
//
//
//        mRewardAtomTitle.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
//        mRewardPhotonTitle.setText(WDp.DpPoton(getContext(), getSActivity().mAccount.baseChain));
//        mRemindAtomTitle.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
//        mRemindPhotonTitle.setText(WDp.DpPoton(getContext(), getSActivity().mAccount.baseChain));

        mRemindAtom.setText(WDp.getDpAmount(getContext(), remindAtom, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
        mRemindPhoton.setText(WDp.getDpAmount(getContext(), remindPhoton, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));

    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            WLog.w("mConfirmBtn");
            getSActivity().onStartReward();

        }

    }


    private ClaimRewardActivity getSActivity() {
        return (ClaimRewardActivity)getBaseActivity();
    }
}