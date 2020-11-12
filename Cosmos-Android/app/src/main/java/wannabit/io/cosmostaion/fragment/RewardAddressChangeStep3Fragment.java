package wannabit.io.cosmostaion.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.RewardAddressChangeActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_RedelegateConfirm;
import wannabit.io.cosmostaion.dialog.Dialog_RewardAddressChangeConfirm;
import wannabit.io.cosmostaion.utils.WDp;

public class RewardAddressChangeStep3Fragment extends BaseFragment implements View.OnClickListener {

    public final static int CHANGE_REWARD_ADDRESS_CONFIRM_DIALOG = 6016;

    private TextView        mFeeAmount, mFeeType;
    private TextView        mCurrentAddress, mNewAddress, mMemo;
    private Button          mBeforeBtn, mConfirmBtn;

    public static RewardAddressChangeStep3Fragment newInstance(Bundle bundle) {
        RewardAddressChangeStep3Fragment fragment = new RewardAddressChangeStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reward_address_change_step3, container, false);
        mFeeAmount              = rootView.findViewById(R.id.change_reward_address_fees);
        mFeeType                = rootView.findViewById(R.id.change_reward_address_type);
        mCurrentAddress         = rootView.findViewById(R.id.current_reward_address);
        mNewAddress             = rootView.findViewById(R.id.new_reward_address);
        mMemo                   = rootView.findViewById(R.id.memo);
        mBeforeBtn              = rootView.findViewById(R.id.btn_before);
        mConfirmBtn             = rootView.findViewById(R.id.btn_confirm);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mFeeType);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        BigDecimal feeAmount = new BigDecimal(getSActivity().mFee.amount.get(0).amount);
        if (getSActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            mFeeAmount.setText(WDp.getDpAmount(getContext(), feeAmount, 6, getSActivity().mBaseChain));
        } else if (getSActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            mFeeAmount.setText(WDp.getDpAmount(getContext(), feeAmount, 18, getSActivity().mBaseChain));
        } else if (getSActivity().mBaseChain.equals(BaseChain.KAVA_MAIN) || getSActivity().mBaseChain.equals(BaseChain.KAVA_TEST)) {
            mFeeAmount.setText(WDp.getDpAmount(getContext(), feeAmount, 6, getSActivity().mBaseChain));
        } else if (getSActivity().mBaseChain.equals(BaseChain.BAND_MAIN)) {
            mFeeAmount.setText(WDp.getDpAmount(getContext(), feeAmount, 6, getSActivity().mBaseChain));
        } else if (getSActivity().mBaseChain.equals(BaseChain.IOV_MAIN) || getSActivity().mBaseChain.equals(BaseChain.IOV_TEST) || getSActivity().mBaseChain.equals(BaseChain.CERTIK_MAIN) || getSActivity().mBaseChain.equals(BaseChain.CERTIK_TEST)) {
            mFeeAmount.setText(WDp.getDpAmount(getContext(), feeAmount, 6, getSActivity().mBaseChain));
        } else if (getSActivity().mBaseChain.equals(BaseChain.AKASH_MAIN)) {
            mFeeAmount.setText(WDp.getDpAmount(getContext(), feeAmount, 6, getSActivity().mBaseChain));
        }
        mCurrentAddress.setText(getSActivity().mCurrentRewardAddress);
        mNewAddress.setText(getSActivity().mNewRewardAddress);
        mMemo.setText(getSActivity().mMemo);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            Dialog_RewardAddressChangeConfirm dialog = Dialog_RewardAddressChangeConfirm.newInstance();
            dialog.setCancelable(true);
            dialog.setTargetFragment(this, CHANGE_REWARD_ADDRESS_CONFIRM_DIALOG);
            dialog.show(getFragmentManager().beginTransaction(), "dialog");
        }
    }

    private RewardAddressChangeActivity getSActivity() {
        return (RewardAddressChangeActivity)getBaseActivity();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CHANGE_REWARD_ADDRESS_CONFIRM_DIALOG && resultCode == Activity.RESULT_OK) {
            getSActivity().onStartRewardAddressChange();
        }
    }

}