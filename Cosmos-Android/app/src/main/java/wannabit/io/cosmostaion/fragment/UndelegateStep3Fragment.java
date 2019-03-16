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
import wannabit.io.cosmostaion.activities.UndelegateActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKeyBaseUser;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class UndelegateStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView        mTvUndelegateAmount, mTotalDeleagteAmount;
    private TextView        mFeeAmount, mFeeType;
    private TextView        mValidatorName, mMemo, mTime;
    private CircleImageView mAvator;
    private TextView        mRemindAtom;
    private Button          mBeforeBtn, mConfirmBtn;
    private TextView        mUnDelegateAtomTitle, mRemindAtomTitle;

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
        mTvUndelegateAmount       = rootView.findViewById(R.id.undelegate_atom);
        mTotalDeleagteAmount    = rootView.findViewById(R.id.alldelegate_atom);
        mFeeAmount              = rootView.findViewById(R.id.undelegate_fees);
        mFeeType                = rootView.findViewById(R.id.undelegate_fees_type);
        mAvator                 = rootView.findViewById(R.id.undelegate_avatar);
        mValidatorName          = rootView.findViewById(R.id.undelegate_moniker);
        mMemo                   = rootView.findViewById(R.id.memo);
        mTime                   = rootView.findViewById(R.id.undelegate_time);
        mRemindAtom             = rootView.findViewById(R.id.remind_atom);
        mBeforeBtn              = rootView.findViewById(R.id.btn_before);
        mConfirmBtn             = rootView.findViewById(R.id.btn_confirm);
        mUnDelegateAtomTitle    = rootView.findViewById(R.id.undelegate_atom_title);
        mRemindAtomTitle        = rootView.findViewById(R.id.remind_atom_title);
        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        BigDecimal toUnDeleagteAtom = new BigDecimal(getSActivity().mUnDelegateAmount);
        WLog.w("toUnDeleagteAtom : " + toUnDeleagteAtom.toPlainString());

        if(getSActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
            mTvUndelegateAmount.setText(WDp.getDpAmount(getContext(), toUnDeleagteAtom, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
            mTotalDeleagteAmount.setText(WDp.getDpAmount(getContext(), getSActivity().mBondingState.shares, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
            if(getSActivity().mUnDelegateFee.amount == null) {
                mFeeType.setVisibility(View.GONE);
                mFeeAmount.setText("FREE");
                mFeeAmount.setTextColor(getResources().getColor(R.color.colorRed));
            } else {
                mFeeType.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
                mFeeType.setTextColor(getResources().getColor(R.color.colorAtom));
                mFeeAmount.setText(new BigDecimal(getSActivity().mUnDelegateFee.amount.get(6).amount).toPlainString());
            }
            mTime.setText(WDp.getUnbondTime(getContext(), BaseChain.COSMOS_MAIN));

        } else {
            mTvUndelegateAmount.setText(WDp.getDpAmount(getContext(), toUnDeleagteAtom, 0, BaseChain.getChain(getSActivity().mAccount.baseChain)));
            mTotalDeleagteAmount.setText(WDp.getDpAmount(getContext(), getSActivity().mBondingState.shares, 0, BaseChain.getChain(getSActivity().mAccount.baseChain)));
            if(getSActivity().mUnDelegateFee.amount.get(0).denom.equals(BaseConstant.COSMOS_MUON)) {
                mFeeType.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
                mFeeType.setTextColor(getResources().getColor(R.color.colorAtom));
            } else {
                mFeeType.setText(WDp.DpPoton(getContext(), getSActivity().mAccount.baseChain));
                mFeeType.setTextColor(getResources().getColor(R.color.colorPhoton));
            }
            mFeeAmount.setText(new BigDecimal(getSActivity().mUnDelegateFee.amount.get(0).amount).toPlainString());
            mTime.setText(WDp.getUnbondTime(getContext(), BaseChain.GAIA_13K));
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
                        }catch (Exception e){
                            WLog.w("ex " + e.getMessage());
                            e.printStackTrace();
                        }

                    }
                }
                @Override
                public void onFailure(Call<ResKeyBaseUser> call, Throwable t) {}
            });
        }
        mValidatorName.setText(getSActivity().mValidator.description.moniker);
        mMemo.setText(getSActivity().mUnDelegateMemo);
        mUnDelegateAtomTitle.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));

//        BigDecimal toUnDeleagteAtom = new BigDecimal(getSActivity().mUnDelegateAmount);
//        BigDecimal remindAtom = getSActivity().mAccount.getAtomBalance().subtract(toUnDeleagteAtom);
//        BigDecimal remindPhoton = BigDecimal.ZERO;
//
//        mUndelegateAmount.setText(WDp.getDpAmount(getContext(), toUnDeleagteAtom, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
//        mTotalDeleagteAmount.setText(WDp.getDpAmount(getContext(), getSActivity().mBondingState.shares, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
//
//        if(getSActivity().mUnDelegateFee.amount.get(0).denom.equals(BaseConstant.COSMOS_ATOM)) {
//            mFeeType.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
//            mFeeType.setTextColor(getResources().getColor(R.color.colorAtom));
//            remindAtom.subtract(new BigDecimal(getSActivity().mUnDelegateFee.amount.get(0).amount));
//        } else {
//            mFeeType.setText(WDp.DpPoton(getContext(), getSActivity().mAccount.baseChain));
//            mFeeType.setTextColor(getResources().getColor(R.color.colorPhoton));
//            remindPhoton.subtract(new BigDecimal(getSActivity().mUnDelegateFee.amount.get(0).amount));
//        }
//        mFeeAmount.setText(WDp.getDpAmount(getContext(), new BigDecimal(getSActivity().mUnDelegateFee.amount.get(0).amount), 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
//        mValidatorName.setText(getSActivity().mValidator.description.moniker);
//        mMemo.setText(getSActivity().mUnDelegateMemo);
//        mTime.setText(WDp.getUnbondTime(getContext()));
//        mRemindAtom.setText(WDp.getDpAmount(getContext(), getSActivity().mBondingState.shares.subtract(toUnDeleagteAtom), 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
//
//
//        mUnDelegateAtomTitle.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
//        mRemindAtomTitle.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));

    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            WLog.w("mConfirmBtn");
            getSActivity().onStartUndelegate();

        }
    }

    private UndelegateActivity getSActivity() {
        return (UndelegateActivity)getBaseActivity();
    }

}
