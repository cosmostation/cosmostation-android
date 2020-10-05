package wannabit.io.cosmostaion.fragment.chains.starname;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.starname.RegisterStarNameDomainActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqStarNameDomainInfo;
import wannabit.io.cosmostaion.network.res.ResIovStarNameDomainInfo;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV_TEST;

public class RegisterDomain0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mCancelBtn, mConfirmBtn;
    private EditText mDomainInput;
    private TextView mStarNameFee;
    private TextView mDomainType, mTypeDescription;
    private SwitchCompat mTypeSwitch;

    private BigDecimal  mMyBalance = BigDecimal.ZERO;
    private BigDecimal  mStarnameFee = BigDecimal.ZERO;

    public static RegisterDomain0Fragment newInstance(Bundle bundle) {
        RegisterDomain0Fragment fragment = new RegisterDomain0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register_domain0, container, false);
        mCancelBtn = rootView.findViewById(R.id.btn_cancel);
        mConfirmBtn = rootView.findViewById(R.id.btn_next);
        mDomainInput = rootView.findViewById(R.id.et_user_input);
        mStarNameFee = rootView.findViewById(R.id.starname_fee_amount);
        mDomainType = rootView.findViewById(R.id.domain_type_txt);
        mTypeSwitch = rootView.findViewById(R.id.switch_domain_type);
        mTypeDescription = rootView.findViewById(R.id.domain_type_description);
        mCancelBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        mStarNameFee.setText(WDp.getDpAmount2(getContext(), BigDecimal.ZERO, 6, 6));
        mDomainInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String userInput = s.toString().trim();
                if (WUtil.isValidDomain(userInput)) {

                }

                mStarnameFee = getBaseDao().mStarNameFee.getDomainFee(userInput, mTypeSwitch.isChecked() ? "open" : "closed");
                mStarNameFee.setText(WDp.getDpAmount2(getContext(), mStarnameFee, 6, 6));

            }
        });

        mTypeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isPressed()) {
                    if (isChecked) {
                        mDomainType.setText("Open");
                        mTypeDescription.setText(getString(R.string.str_description_open_domain));
                    } else {
                        mDomainType.setText("Closed");
                        mTypeDescription.setText(getString(R.string.str_description_closed_domain));
                    }

                    String userInput = mDomainInput.getText().toString().trim();
                    mStarnameFee = getBaseDao().mStarNameFee.getDomainFee(userInput, isChecked ? "open" : "closed");
                    mStarNameFee.setText(WDp.getDpAmount2(getContext(), mStarnameFee, 6, 6));
                }
            }
        });
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


    private RegisterStarNameDomainActivity getSActivity() {
        return (RegisterStarNameDomainActivity)getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            String userInput = mDomainInput.getText().toString().trim();
            if (!WUtil.isValidDomain(userInput)) {
                Toast.makeText(getBaseActivity(), R.string.error_invalid_domain_format, Toast.LENGTH_SHORT).show();
                return;
            }
            if (mStarnameFee.compareTo(mMyBalance) > 0) {
                Toast.makeText(getBaseActivity(), R.string.error_not_enough_starname_fee, Toast.LENGTH_SHORT).show();
                return;

            }

            onCheckDomainInfo(userInput);
        }
    }

    private void onNextStep() {
        getSActivity().mToRegDomain = mDomainInput.getText().toString().trim();
        getSActivity().mType = mTypeSwitch.isChecked() ? "open" : "closed";
        getSActivity().onNextStep();
    }


    private void onCheckDomainInfo(String domain) {
        getSActivity().onShowWaitDialog();
        ReqStarNameDomainInfo req = new ReqStarNameDomainInfo(domain);
        if (getSActivity().mBaseChain.equals(IOV_MAIN)) {
            ApiClient.getIovChain(getSActivity()).getStarnameDomainInfo(req).enqueue(new Callback<ResIovStarNameDomainInfo>() {
                @Override
                public void onResponse(Call<ResIovStarNameDomainInfo> call, Response<ResIovStarNameDomainInfo> response) {
                    getSActivity().onHideWaitDialog();
                    if (response.isSuccessful() && TextUtils.isEmpty(response.body().error)) {
                        Toast.makeText(getBaseActivity(), R.string.error_already_registered_domain, Toast.LENGTH_SHORT).show();
                    } else {
                        onNextStep();
                    }
                }
                @Override
                public void onFailure(Call<ResIovStarNameDomainInfo> call, Throwable t) {
                    getSActivity().onHideWaitDialog();
                    Toast.makeText(getBaseActivity(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                }
            });

        } else if (getSActivity().mBaseChain.equals(IOV_TEST)) {
            ApiClient.getIovTestChain(getSActivity()).getStarnameDomainInfo(req).enqueue(new Callback<ResIovStarNameDomainInfo>() {
                @Override
                public void onResponse(Call<ResIovStarNameDomainInfo> call, Response<ResIovStarNameDomainInfo> response) {
                    getSActivity().onHideWaitDialog();
                    if (response.isSuccessful() && TextUtils.isEmpty(response.body().error)) {
                        Toast.makeText(getBaseActivity(), R.string.error_already_registered_domain, Toast.LENGTH_SHORT).show();
                    } else {
                        onNextStep();
                    }
                }

                @Override
                public void onFailure(Call<ResIovStarNameDomainInfo> call, Throwable t) {
                    getSActivity().onHideWaitDialog();
                    Toast.makeText(getBaseActivity(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
