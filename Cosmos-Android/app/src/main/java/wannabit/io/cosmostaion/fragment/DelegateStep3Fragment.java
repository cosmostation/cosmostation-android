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
    private TextView        mFeeAmount, mFeeType;
    private TextView        mValidatorName, mMemo;
    private CircleImageView mAvator;
    private TextView        mRemindAtom, mRemindPhoton;
    private Button          mBeforeBtn, mConfirmBtn;
    private TextView        mDelegateAtomTitle, mRemindAtomTitle, mRemindPhotonTitle;


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
        mFeeAmount              = rootView.findViewById(R.id.delegate_fees);
        mFeeType                = rootView.findViewById(R.id.delegate_fees_type);
        mAvator                 = rootView.findViewById(R.id.to_delegate_avatar);
        mValidatorName          = rootView.findViewById(R.id.to_delegate_moniker);
        mMemo                   = rootView.findViewById(R.id.memo);
        mRemindAtom             = rootView.findViewById(R.id.remind_atom);
        mRemindPhoton           = rootView.findViewById(R.id.remind_photon);
        mBeforeBtn              = rootView.findViewById(R.id.btn_before);
        mConfirmBtn             = rootView.findViewById(R.id.btn_confirm);
        mDelegateAtomTitle      = rootView.findViewById(R.id.delegate_atom_title);
        mRemindAtomTitle        = rootView.findViewById(R.id.remind_atom_title);
        mRemindPhotonTitle      = rootView.findViewById(R.id.remind_photon_title);
        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        BigDecimal toDeleagteAtom = new BigDecimal(getSActivity().mToDelegateAmount.amount);
        BigDecimal remindAtom = getSActivity().mAccount.getAtomBalance().subtract(toDeleagteAtom);
        BigDecimal remindPhoton = getSActivity().mAccount.getPhotonBalance();

        mDelegateAmount.setText(WDp.getDpAmount(getContext(), toDeleagteAtom, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));

        if(getSActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
            if(getSActivity().mToDelegateFee.amount == null) {
                mFeeType.setVisibility(View.GONE);
                mFeeAmount.setText("FREE");
                mFeeAmount.setTextColor(getResources().getColor(R.color.colorRed));
            } else {
                mFeeType.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
                mFeeType.setTextColor(getResources().getColor(R.color.colorAtom));
                remindAtom.subtract(new BigDecimal(getSActivity().mToDelegateFee.amount.get(0).amount));
            }
            mRemindPhoton.setVisibility(View.GONE);

        } else {
            if(getSActivity().mToDelegateFee.amount.get(0).denom.equals(BaseConstant.COSMOS_MUON)) {
                mFeeType.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
                mFeeType.setTextColor(getResources().getColor(R.color.colorAtom));
                remindAtom.subtract(new BigDecimal(getSActivity().mToDelegateFee.amount.get(0).amount));
            } else {
                mFeeType.setText(WDp.DpPoton(getContext(), getSActivity().mAccount.baseChain));
                mFeeType.setTextColor(getResources().getColor(R.color.colorPhoton));
                remindPhoton.subtract(new BigDecimal(getSActivity().mToDelegateFee.amount.get(0).amount));
            }
//            mFeeAmount.setText(WDp.getDpAmount(getContext(), new BigDecimal(getSActivity().mToDelegateFee.amount.get(0).amount), 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
            mFeeAmount.setText(new BigDecimal(getSActivity().mToDelegateFee.amount.get(0).amount).toPlainString());
            mRemindPhoton.setVisibility(View.VISIBLE);
        }


        if(!TextUtils.isEmpty(getSActivity().mValidator.description.identity)) {
            ApiClient.getKeybaseService(getSActivity()).getUserInfo("pictures", getSActivity().mValidator.description.identity).enqueue(new Callback<ResKeyBaseUser>() {
                @Override
                public void onResponse(Call<ResKeyBaseUser> call, final Response<ResKeyBaseUser> response) {
                    if(isAdded()) {
                        try {
                            Picasso.get()
                                    .load(response.body().getUrl())
                                    .placeholder(R.drawable.validator_none_img)
                                    .into(mAvator);
                        }catch (Exception e){}

                    }
                }
                @Override
                public void onFailure(Call<ResKeyBaseUser> call, Throwable t) {}
            });
        }
        mValidatorName.setText(getSActivity().mValidator.description.moniker);
        mMemo.setText(getSActivity().mToDelegateMemo);

        mDelegateAtomTitle.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
        mRemindAtomTitle.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
        mRemindPhotonTitle.setText(WDp.DpPoton(getContext(), getSActivity().mAccount.baseChain));

        mRemindAtom.setText(WDp.getDpAmount(getContext(), remindAtom, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
        mRemindPhoton.setText(WDp.getDpAmount(getContext(), remindPhoton, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            if(getSActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
                getSActivity().onBeforeStep2();
            } else {
                getSActivity().onBeforeStep();
            }


        } else if (v.equals(mConfirmBtn)) {
            WLog.w("mConfirmBtn");
            getSActivity().onStartDelegate();

        }

    }

    private DelegateActivity getSActivity() {
        return (DelegateActivity)getBaseActivity();
    }


}
