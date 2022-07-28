package wannabit.io.cosmostaion.fragment.txs.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.RewardAddressChangeActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.utils.WDp;

public class RewardAddressChangeStep3Fragment extends BaseFragment implements View.OnClickListener {

    public final static int CHANGE_REWARD_ADDRESS_CONFIRM_DIALOG = 6016;

    private TextView mFeeAmount, mFeeType;
    private TextView mCurrentAddress, mNewAddress, mMemo;
    private Button mBeforeBtn, mConfirmBtn;

    public static RewardAddressChangeStep3Fragment newInstance() {
        return new RewardAddressChangeStep3Fragment();
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

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeType, mFeeAmount);
        mCurrentAddress.setText(getSActivity().mCurrentRewardAddress);
        mNewAddress.setText(getSActivity().mNewRewardAddress);
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            AlertDialogUtils.showDoubleButtonDialog(getSActivity(), getString(R.string.str_reward_address_change_confirm_title), getString(R.string.str_reward_address_change_confirm_msg),
                    getString(R.string.str_cancel), null,
                    getString(R.string.str_continue), View -> {
                        Intent resultIntent = new Intent();
                        onActivityResult(CHANGE_REWARD_ADDRESS_CONFIRM_DIALOG, Activity.RESULT_OK, resultIntent);
                        }, true);
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