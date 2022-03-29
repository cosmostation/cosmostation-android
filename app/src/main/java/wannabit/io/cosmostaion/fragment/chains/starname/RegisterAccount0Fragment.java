package wannabit.io.cosmostaion.fragment.chains.starname;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import io.grpc.stub.StreamObserver;
import starnamed.x.starname.v1beta1.QueryGrpc;
import starnamed.x.starname.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.starname.RegisterStarNameAccountActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_Starname_Domain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REGISTER_ACCOUNT;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

public class RegisterAccount0Fragment extends BaseFragment implements View.OnClickListener {
    public final static int             SELECT_POPUP_STARNAME_DOMAIN = 1000;

    private Button mCancelBtn, mConfirmBtn;
    private EditText mAccountInput;
    private TextView mStarNameFeeTv;
    private RelativeLayout mDomainLayer;
    private TextView mSelectDomain;
    private String mSelectedDomain = "iov";

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
        mStarNameFeeTv = rootView.findViewById(R.id.starname_fee_amount);
        mDomainLayer = rootView.findViewById(R.id.domain_layer);
        mSelectDomain = rootView.findViewById(R.id.selected_domain);

        mCancelBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        mDomainLayer.setOnClickListener(this);

        BigDecimal starNameFee = getBaseDao().getStarNameRegisterAccountFee("open");
        mSelectDomain.setText(mSelectedDomain);
        mStarNameFeeTv.setText(WDp.getDpAmount2(getContext(), starNameFee, 6, 6));
        return rootView;
    }

    private RegisterStarNameAccountActivity getSActivity() {
        return (RegisterStarNameAccountActivity)getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mDomainLayer)) {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("domain", getBaseDao().mChainParam.mStarnameDomains);
            Dialog_Starname_Domain dialog = Dialog_Starname_Domain.newInstance(bundle);
            dialog.setCancelable(true);
            dialog.setTargetFragment(this, SELECT_POPUP_STARNAME_DOMAIN);
            getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

        } if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            String userInput = mAccountInput.getText().toString().trim();
            if (!WUtil.isValidAccount(userInput)) {
                Toast.makeText(getBaseActivity(), R.string.error_invalid_account_format, Toast.LENGTH_SHORT).show();
                return;
            }
            BigDecimal available = getBaseDao().getAvailable(getSActivity().mBaseChain.getMainDenom());
            BigDecimal starNameFee = getBaseDao().getStarNameRegisterAccountFee("open");
            BigDecimal txFee = WUtil.getEstimateGasFeeAmount(getSActivity(), getSActivity().mBaseChain, CONST_PW_TX_REGISTER_ACCOUNT, 0);
            if (available.compareTo(starNameFee.add(txFee)) < 0) {
                Toast.makeText(getBaseActivity(), R.string.error_not_enough_starname_fee, Toast.LENGTH_SHORT).show();
                return;
            }
            onCheckAccountInfo(userInput, mSelectedDomain);
        }
    }

    private void onNextStep() {
        getSActivity().mStarNameAccount = mAccountInput.getText().toString().trim();
        getSActivity().mStarNameDomain = mSelectedDomain;
        getSActivity().onNextStep();
    }

    private void onCheckAccountInfo(String starnameAccount, String starnameDomain) {
        getSActivity().onShowWaitDialog();

        QueryGrpc.QueryStub mStub = QueryGrpc.newStub(ChannelBuilder.getChain(getSActivity().mBaseChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
        QueryOuterClass.QueryStarnameRequest request = QueryOuterClass.QueryStarnameRequest.newBuilder().setStarname(starnameAccount + "*" + starnameDomain).build();
        mStub.starname(request, new StreamObserver<QueryOuterClass.QueryStarnameResponse>() {
            @Override
            public void onNext(QueryOuterClass.QueryStarnameResponse value) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getSActivity().onHideWaitDialog();
                        Toast.makeText(getBaseActivity(), R.string.error_already_registered_domain, Toast.LENGTH_SHORT).show();
                    }
                }, 500);

            }

            @Override
            public void onError(Throwable t) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getSActivity().onHideWaitDialog();
                        onNextStep();
                    }
                }, 500);
            }

            @Override
            public void onCompleted() {
                getSActivity().onHideWaitDialog();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SELECT_POPUP_STARNAME_DOMAIN && resultCode == Activity.RESULT_OK) {
            mSelectedDomain = getBaseDao().mChainParam.mStarnameDomains.get(data.getIntExtra("position", 0));
            mSelectDomain.setText(mSelectedDomain);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}