package wannabit.io.cosmostaion.fragment.chains.starname;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import java.util.concurrent.TimeUnit;

import io.grpc.stub.StreamObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import starnamed.x.starname.v1beta1.QueryGrpc;
import starnamed.x.starname.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.starname.RegisterStarNameAccountActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.network.req.ReqStarNameResolve;
import wannabit.io.cosmostaion.network.res.ResIovStarNameResolve;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REGISTER_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REGISTER_DOMAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV_TEST;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

public class RegisterAccount0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mCancelBtn, mConfirmBtn;
    private EditText mAccountInput;
    private TextView mStarNameFeeTv;
    private TextView mFixedDomain;

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
        mStarNameFeeTv = rootView.findViewById(R.id.starname_fee_amount);
        mFixedDomain.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        BigDecimal starNameFee = getBaseDao().getStarNameRegisterAccountFee("open");
        mStarNameFeeTv.setText(WDp.getDpAmount2(getContext(), starNameFee, 6, 6));

        return rootView;
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
            BigDecimal available = getBaseDao().getAvailable(WDp.mainDenom(getSActivity().mBaseChain));
            BigDecimal starNameFee = getBaseDao().getStarNameRegisterAccountFee("open");
            BigDecimal txFee = WUtil.getEstimateGasFeeAmount(getSActivity(), getSActivity().mBaseChain, CONST_PW_TX_REGISTER_ACCOUNT, 0);
            if (available.compareTo(starNameFee.add(txFee)) < 0) {
                Toast.makeText(getBaseActivity(), R.string.error_not_enough_starname_fee, Toast.LENGTH_SHORT).show();
                return;
            }
            onCheckAccountInfo(userInput, "iov");
        }
    }

    private void onNextStep() {
        getSActivity().mToRegAccount = mAccountInput.getText().toString().trim();
        getSActivity().mToRegDomain = "iov";
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
}