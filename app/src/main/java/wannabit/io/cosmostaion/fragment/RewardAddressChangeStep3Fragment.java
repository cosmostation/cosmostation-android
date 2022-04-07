package wannabit.io.cosmostaion.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.RewardAddressChangeActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.dialog.Dialog_RewardAddressChangeConfirm;
import wannabit.io.cosmostaion.utils.WDp;

public class RewardAddressChangeStep3Fragment extends BaseFragment implements View.OnClickListener, IRefreshTabListener {

    public final static int CHANGE_REWARD_ADDRESS_CONFIRM_DIALOG = 6016;

    private TextView mFeeAmount, mFeeType;
    private TextView mCurrentAddress, mNewAddress, mMemo;
    private Button mBeforeBtn, mConfirmBtn;
    private int mDpDecimal = 6;

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
        mFeeAmount = rootView.findViewById(R.id.change_reward_address_fees);
        mFeeType = rootView.findViewById(R.id.change_reward_address_type);
        mCurrentAddress = rootView.findViewById(R.id.current_reward_address);
        mNewAddress = rootView.findViewById(R.id.new_reward_address);
        mMemo = rootView.findViewById(R.id.memo);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.confirmButton);

        WDp.DpMainDenom(getSActivity().account.baseChain, mFeeType);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mDpDecimal = WDp.mainDivideDecimal(getSActivity().baseChain);
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        mFeeAmount.setText(WDp.getDpAmount2(feeAmount, mDpDecimal, mDpDecimal));
        mCurrentAddress.setText(getSActivity().mCurrentRewardAddress);
        mNewAddress.setText(getSActivity().mNewRewardAddress);
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            Dialog_RewardAddressChangeConfirm dialog = Dialog_RewardAddressChangeConfirm.newInstance();
            dialog.setTargetFragment(this, CHANGE_REWARD_ADDRESS_CONFIRM_DIALOG);
            showDialog(dialog);
        }
    }

    private RewardAddressChangeActivity getSActivity() {
        return (RewardAddressChangeActivity) getBaseActivity();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHANGE_REWARD_ADDRESS_CONFIRM_DIALOG && resultCode == Activity.RESULT_OK) {
            getSActivity().onStartRewardAddressChange();
        }
    }

}