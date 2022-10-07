package wannabit.io.cosmostaion.fragment.txs.starname;

import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

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
import wannabit.io.cosmostaion.activities.txs.starname.RegisterStarNameAccountActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.StarNameDomainDialog;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class RegisterAccount0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mCancelBtn, mConfirmBtn;
    private EditText mAccountInput;
    private TextView mStarNameFeeTv;
    private RelativeLayout mDomainLayer;
    private TextView mSelectDomain;
    private String mSelectedDomain = "iov";

    public static RegisterAccount0Fragment newInstance() {
        return new RegisterAccount0Fragment();
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
        return (RegisterStarNameAccountActivity) getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mDomainLayer) && !getSActivity().isFinishing()) {
            Bundle bundleData = new Bundle();
            bundleData.putStringArrayList("domain", getBaseDao().mChainParam.mStarnameDomains);
            StarNameDomainDialog dialog = StarNameDomainDialog.newInstance(bundleData);
            dialog.show(getParentFragmentManager(), StarNameDomainDialog.class.getName());
            getParentFragmentManager().setFragmentResultListener(StarNameDomainDialog.STAR_NAME_DOMAIN_BUNDLE_KEY, this, (requestKey, bundle) -> {
                int result = bundle.getInt(BaseConstant.POSITION);
                mSelectedDomain = getBaseDao().mChainParam.mStarnameDomains.get(result);
                mSelectDomain.setText(mSelectedDomain);
            });
        }
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            String userInput = String.valueOf(mAccountInput.getText()).trim();
            if (!WUtil.isValidAccount(userInput)) {
                Toast.makeText(getBaseActivity(), R.string.error_invalid_account_format, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!WDp.isTxFeePayable(getActivity(), getBaseDao(), getSActivity().mChainConfig)) {
                Toast.makeText(getActivity(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            BigDecimal available = getBaseDao().getAvailable(getSActivity().mChainConfig.mainDenom());
            BigDecimal starNameFee = getBaseDao().getStarNameRegisterAccountFee("open");
            if (available.compareTo(starNameFee) < 0) {
                Toast.makeText(getBaseActivity(), R.string.error_not_enough_starname_fee, Toast.LENGTH_SHORT).show();
                return;
            }
            onCheckAccountInfo(userInput, mSelectedDomain);
        }
    }

    private void onNextStep() {
        getSActivity().mStarNameAccount = String.valueOf(mAccountInput.getText()).trim();
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