package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.DelegateActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKeyBaseUser;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class DelegateStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView        mDelegateAmount;
    private TextView        mFeeAmount;
    private TextView        mValidatorName, mMemo;
    private Button          mBeforeBtn, mConfirmBtn;
    private TextView        mDenomDelegateAmount, mDenomFeeType;


    public static DelegateStep3Fragment newInstance(Bundle bundle) {
        DelegateStep3Fragment fragment = new DelegateStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_delegate_step3, container, false);
        mDelegateAmount         = rootView.findViewById(R.id.delegate_atom);
        mDenomDelegateAmount    = rootView.findViewById(R.id.delegate_amount_title);
        mFeeAmount              = rootView.findViewById(R.id.delegate_fees);
        mDenomFeeType           = rootView.findViewById(R.id.delegate_fees_type);
        mValidatorName          = rootView.findViewById(R.id.to_delegate_moniker);
        mMemo                   = rootView.findViewById(R.id.memo);
        mBeforeBtn              = rootView.findViewById(R.id.btn_before);
        mConfirmBtn             = rootView.findViewById(R.id.btn_confirm);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomDelegateAmount);
        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomFeeType);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        BigDecimal toDeleagteAmount = new BigDecimal(getSActivity().mToDelegateAmount.amount);
        BigDecimal feeAmount = new BigDecimal(getSActivity().mToDelegateFee.amount.get(0).amount);
        if (getSActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            mDelegateAmount.setText(WDp.getDpAmount(getContext(), toDeleagteAmount, 6, getSActivity().mBaseChain));
            mFeeAmount.setText(WDp.getDpAmount(getContext(), feeAmount, 6, getSActivity().mBaseChain));

        } else if (getSActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            mDelegateAmount.setText(WDp.getDpAmount(getContext(), toDeleagteAmount, 18, getSActivity().mBaseChain));
            mFeeAmount.setText(WDp.getDpAmount(getContext(), feeAmount, 18, getSActivity().mBaseChain));

        }
        mValidatorName.setText(getSActivity().mValidator.description.moniker);
        mMemo.setText(getSActivity().mToDelegateMemo);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();


        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartDelegate();

        }

    }

    private DelegateActivity getSActivity() {
        return (DelegateActivity)getBaseActivity();
    }


}
