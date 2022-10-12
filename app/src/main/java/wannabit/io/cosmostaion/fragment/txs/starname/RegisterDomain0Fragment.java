package wannabit.io.cosmostaion.fragment.txs.starname;

import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import io.grpc.stub.StreamObserver;
import starnamed.x.starname.v1beta1.QueryGrpc;
import starnamed.x.starname.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.starname.RegisterStarNameDomainActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class RegisterDomain0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mCancelBtn, mConfirmBtn;
    private EditText mDomainInput;
    private TextView mStarNameFeeTv;
    private TextView mDomainValid, mDomainType, mTypeDescription;
    private SwitchCompat mTypeSwitch;

    public static RegisterDomain0Fragment newInstance() {
        return new RegisterDomain0Fragment();
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
        mDomainValid = rootView.findViewById(R.id.domain_valid_msg);
        mStarNameFeeTv = rootView.findViewById(R.id.starname_fee_amount);
        mDomainType = rootView.findViewById(R.id.domain_type_txt);
        mTypeSwitch = rootView.findViewById(R.id.switch_domain_type);
        mTypeDescription = rootView.findViewById(R.id.domain_type_description);
        mCancelBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        mStarNameFeeTv.setText(WDp.getDpAmount2(getContext(), BigDecimal.ZERO, 6, 6));
        mDomainInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String userInput = String.valueOf(s).trim();
                if (TextUtils.isEmpty(userInput) || WUtil.isValidDomain(userInput)) {
                    mDomainValid.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorGray1));
                } else {
                    mDomainValid.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorRed));
                }
                BigDecimal starNameFee = getBaseDao().getStarNameRegisterDomainFee(userInput, mTypeSwitch.isChecked() ? "open" : "closed");
                mStarNameFeeTv.setText(WDp.getDpAmount2(getContext(), starNameFee, 6, 6));

            }
        });

        mTypeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (buttonView.isPressed()) {
                if (isChecked) {
                    mDomainType.setText("Open".toUpperCase());
                    mDomainType.setTextColor(ContextCompat.getColor(getSActivity(), R.color.color_starname));
                    mTypeDescription.setText(getString(R.string.str_description_open_domain));
                } else {
                    mDomainType.setText("Closed".toUpperCase());
                    mDomainType.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorBlackDayNight));
                    mTypeDescription.setText(getString(R.string.str_description_closed_domain));
                }

                String userInput = String.valueOf(mDomainInput.getText()).trim();
                BigDecimal starNameFee = getBaseDao().getStarNameRegisterDomainFee(userInput, mTypeSwitch.isChecked() ? "open" : "closed");
                mStarNameFeeTv.setText(WDp.getDpAmount2(getContext(), starNameFee, 6, 6));
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private RegisterStarNameDomainActivity getSActivity() {
        return (RegisterStarNameDomainActivity) getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            String userInput = String.valueOf(mDomainInput.getText()).trim();
            if (!WUtil.isValidDomain(userInput)) {
                Toast.makeText(getBaseActivity(), R.string.error_invalid_domain_format, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!WDp.isTxFeePayable(getActivity(), getBaseDao(), getSActivity().mChainConfig)) {
                Toast.makeText(getActivity(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            BigDecimal available = getBaseDao().getAvailable(getSActivity().mChainConfig.mainDenom());
            BigDecimal starNameFee = getBaseDao().getStarNameRegisterDomainFee(userInput, mTypeSwitch.isChecked() ? "open" : "closed");
            if (available.compareTo(starNameFee) < 0) {
                Toast.makeText(getBaseActivity(), R.string.error_not_enough_starname_fee, Toast.LENGTH_SHORT).show();
                return;
            }
            onCheckDomainInfo(userInput);
        }
    }

    private void onNextStep() {
        getSActivity().mStarNameDomain = String.valueOf(mDomainInput.getText()).trim();
        getSActivity().mStarNameDomainType = mTypeSwitch.isChecked() ? "open" : "closed";
        getSActivity().onNextStep();
    }


    private void onCheckDomainInfo(String domain) {
        getSActivity().onShowWaitDialog();

        QueryGrpc.QueryStub mStub = QueryGrpc.newStub(ChannelBuilder.getChain(getSActivity().mBaseChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
        QueryOuterClass.QueryDomainRequest request = QueryOuterClass.QueryDomainRequest.newBuilder().setName(domain).build();
        mStub.domain(request, new StreamObserver<QueryOuterClass.QueryDomainResponse>() {
            @Override
            public void onNext(QueryOuterClass.QueryDomainResponse value) {
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    getSActivity().onHideWaitDialog();
                    Toast.makeText(getBaseActivity(), R.string.error_already_registered_domain, Toast.LENGTH_SHORT).show();
                }, 500);
            }

            @Override
            public void onError(Throwable t) {
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    getSActivity().onHideWaitDialog();
                    onNextStep();
                }, 500);
            }

            @Override
            public void onCompleted() {
                getSActivity().onHideWaitDialog();
            }
        });
    }
}
