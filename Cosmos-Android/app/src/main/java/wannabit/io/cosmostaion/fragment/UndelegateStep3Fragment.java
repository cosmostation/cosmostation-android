package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.UndelegateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;

public class UndelegateStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView        mTvUndelegateAmount;
    private TextView        mFeeAmount;
    private TextView        mValidatorName, mMemo, mTime;
    private TextView        mDenomUndelegateAmount, mDenomFeeType;
    private Button          mBeforeBtn, mConfirmBtn;

    public static UndelegateStep3Fragment newInstance(Bundle bundle) {
        UndelegateStep3Fragment fragment = new UndelegateStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_undelegate_step3, container, false);
        mTvUndelegateAmount     = rootView.findViewById(R.id.undelegate_amount);
        mDenomUndelegateAmount  = rootView.findViewById(R.id.undelegate_amount_title);
        mFeeAmount              = rootView.findViewById(R.id.undelegate_fees);
        mDenomFeeType           = rootView.findViewById(R.id.undelegate_fees_type);
        mValidatorName          = rootView.findViewById(R.id.undelegate_moniker);
        mMemo                   = rootView.findViewById(R.id.memo);
        mTime                   = rootView.findViewById(R.id.undelegate_time);
        mBeforeBtn              = rootView.findViewById(R.id.btn_before);
        mConfirmBtn             = rootView.findViewById(R.id.btn_confirm);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomUndelegateAmount);
        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomFeeType);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        BigDecimal toUnDeleagteAmount = new BigDecimal(getSActivity().mAmount.amount);
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        if (isGRPC(getSActivity().mBaseChain)) {
            mTvUndelegateAmount.setText(WDp.getDpAmount2(getContext(), toUnDeleagteAmount, 6, 6));
            mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, 6, 6));
            mTime.setText(WDp.getUnbondTime(getContext(), getSActivity().mBaseChain));
            mValidatorName.setText(getBaseDao().getValidatorInfo(getSActivity().mValAddress).getDescription().getMoniker());
            mMemo.setText(getSActivity().mTxMemo);

        } else {
            mTvUndelegateAmount.setText(WDp.getDpAmount2(getContext(), toUnDeleagteAmount, 6, 6));
            mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, 6, 6));
            mTime.setText(WDp.getUnbondTime(getContext(), getSActivity().mBaseChain));
            mValidatorName.setText(getSActivity().mValidator.description.moniker);
            mMemo.setText(getSActivity().mTxMemo);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartUndelegate();

        }
    }

    private UndelegateActivity getSActivity() {
        return (UndelegateActivity)getBaseActivity();
    }

}
