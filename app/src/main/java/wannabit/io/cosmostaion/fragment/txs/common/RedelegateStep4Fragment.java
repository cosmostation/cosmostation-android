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
import wannabit.io.cosmostaion.activities.txs.common.RedelegateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.utils.WDp;

public class RedelegateStep4Fragment extends BaseFragment implements View.OnClickListener {

    public final static int REDELEGATE_CONFIRM_DIALOG = 6016;

    private TextView mTvRedelegateAmount, mTvRedelegateDenom;
    private TextView mFeeAmount, mFeeDenom;
    private TextView mFromValidatorName, mToValidatorName, mMemo;
    private Button mBeforeBtn, mConfirmBtn;

    public static RedelegateStep4Fragment newInstance() {
        return new RedelegateStep4Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_redelegate_step4, container, false);
        mTvRedelegateAmount = rootView.findViewById(R.id.redelegate_amount);
        mTvRedelegateDenom = rootView.findViewById(R.id.redelegate_denom);
        mFeeAmount = rootView.findViewById(R.id.redelegate_fees);
        mFeeDenom = rootView.findViewById(R.id.redelegate_fees_type);
        mFromValidatorName = rootView.findViewById(R.id.redelegate_from_moniker);
        mToValidatorName = rootView.findViewById(R.id.redelegate_to_moniker);
        mMemo = rootView.findViewById(R.id.memo);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mChainConfig.mainDenom(), getSActivity().mAmount.amount, mTvRedelegateDenom, mTvRedelegateAmount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeDenom, mFeeAmount);

        mFromValidatorName.setText(getSActivity().getBaseDao().getValidatorInfo(getSActivity().mValAddress).getDescription().getMoniker());
        mToValidatorName.setText(getSActivity().getBaseDao().getValidatorInfo(getSActivity().mToValAddress).getDescription().getMoniker());
        mMemo.setText(getSActivity().mTxMemo);
    }


    private RedelegateActivity getSActivity() {
        return (RedelegateActivity) getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            CommonAlertDialog.showDoubleButton(getSActivity(), getString(R.string.str_redelegation_warnning_title), getString(R.string.str_redelegation_warnning_msg),
                    getString(R.string.str_no), null,
                    getString(R.string.str_yes), view -> {
                        Intent resultIntent = new Intent();
                        onActivityResult(REDELEGATE_CONFIRM_DIALOG, Activity.RESULT_OK, resultIntent);
                    });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REDELEGATE_CONFIRM_DIALOG && resultCode == Activity.RESULT_OK) {
            getSActivity().onStartRedelegate();
        }
    }
}
