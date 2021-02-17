package wannabit.io.cosmostaion.fragment.chains.starname;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.starname.RegisterStarNameAccountActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqStarNameResolve;
import wannabit.io.cosmostaion.network.res.ResIovStarNameResolve;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV_TEST;

public class RegisterAccount0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mCancelBtn, mConfirmBtn;
    private EditText mAccountInput;
    private TextView mStarNameFee;
    private TextView mFixedDomain;


    private BigDecimal mMyBalance = BigDecimal.ZERO;
    private BigDecimal mStarnameFee = BigDecimal.ZERO;

    public static RegisterAccount0Fragment newInstance(Bundle bundle) {
        RegisterAccount0Fragment fragment = new RegisterAccount0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register_account0, container, false);
        mCancelBtn = rootView.findViewById(R.id.btn_cancel);
        mConfirmBtn = rootView.findViewById(R.id.btn_next);
        mAccountInput = rootView.findViewById(R.id.et_user_input);
        mFixedDomain = rootView.findViewById(R.id.fixed_domain);
        mStarNameFee = rootView.findViewById(R.id.starname_fee_amount);
        mFixedDomain.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        //now support only open "iov" domain!!
        mStarnameFee = getBaseDao().mStarNameFee.getAccountFee(true);
        mStarNameFee.setText(WDp.getDpAmount2(getContext(), mStarnameFee, 6, 6));

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getSActivity().mBaseChain.equals(IOV_MAIN)) {
            mMyBalance  = getSActivity().mAccount.getTokenBalance(TOKEN_IOV).subtract(new BigDecimal("300000"));
        } else if (getSActivity().mBaseChain.equals(IOV_TEST)) {
            mMyBalance  = getSActivity().mAccount.getTokenBalance(TOKEN_IOV_TEST).subtract(new BigDecimal("300000"));
        }
    }


    private RegisterStarNameAccountActivity getSActivity() {
        return (RegisterStarNameAccountActivity)getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mFixedDomain)) {
            Toast.makeText(getBaseActivity(), R.string.error_only_fixed_domain, Toast.LENGTH_SHORT).show();

        } if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            String userInput = mAccountInput.getText().toString().trim();
            if (!WUtil.isValidAccount(userInput)) {
                Toast.makeText(getBaseActivity(), R.string.error_invalid_account_format, Toast.LENGTH_SHORT).show();
                return;
            }
            if (mStarnameFee.compareTo(mMyBalance) > 0) {
                Toast.makeText(getBaseActivity(), R.string.error_not_enough_starname_fee, Toast.LENGTH_SHORT).show();
                return;

            }

            onCheckAccountInfo(userInput + "*iov");
        }

    }

    private void onNextStep() {
        getSActivity().mToRegAccount = mAccountInput.getText().toString().trim();
        getSActivity().mToRegDomain = "iov";
        getSActivity().onNextStep();
    }


    private void onCheckAccountInfo(String starname) {
        getSActivity().onShowWaitDialog();
        ReqStarNameResolve req = new ReqStarNameResolve(starname);
        if (getSActivity().mBaseChain.equals(IOV_MAIN)) {
            ApiClient.getIovChain(getSActivity()).getStarnameAddress(req).enqueue(new Callback<ResIovStarNameResolve>() {
                @Override
                public void onResponse(Call<ResIovStarNameResolve> call, Response<ResIovStarNameResolve> response) {
                    getSActivity().onHideWaitDialog();
                    if (response.isSuccessful() && TextUtils.isEmpty(response.body().error)) {
                        Toast.makeText(getBaseActivity(), R.string.error_already_registered_account, Toast.LENGTH_SHORT).show();
                    } else {
                        onNextStep();
                    }
                }
                @Override
                public void onFailure(Call<ResIovStarNameResolve> call, Throwable t) {
                    getSActivity().onHideWaitDialog();
                    Toast.makeText(getBaseActivity(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                }
            });

        } else if (getSActivity().mBaseChain.equals(IOV_TEST)) {
            ApiClient.getIovTestChain(getSActivity()).getStarnameAddress(req).enqueue(new Callback<ResIovStarNameResolve>() {
                @Override
                public void onResponse(Call<ResIovStarNameResolve> call, Response<ResIovStarNameResolve> response) {
                    getSActivity().onHideWaitDialog();
                    if (response.isSuccessful() && TextUtils.isEmpty(response.body().error)) {
                        Toast.makeText(getBaseActivity(), R.string.error_already_registered_account, Toast.LENGTH_SHORT).show();
                    } else {
                        onNextStep();
                    }
                }

                @Override
                public void onFailure(Call<ResIovStarNameResolve> call, Throwable t) {
                    getSActivity().onHideWaitDialog();
                    Toast.makeText(getBaseActivity(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}