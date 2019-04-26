package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class SendStep4Fragment extends BaseFragment implements View.OnClickListener {

    private TextView        mSendAmount;
    private TextView        mFeeAmount;
    private TextView        mTotalSpendAmount, mTotalPrice;
    private TextView        mCurrentBalance, mRemainingBalance, mRemainingPrice;
    private TextView        mRecipientAddress, mMemo;
    private Button          mBeforeBtn, mConfirmBtn;

    public static SendStep4Fragment newInstance(Bundle bundle) {
        SendStep4Fragment fragment = new SendStep4Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView       = inflater.inflate(R.layout.fragment_send_step4, container, false);
        mSendAmount         = rootView.findViewById(R.id.send_atom);
        mFeeAmount          = rootView.findViewById(R.id.send_fees);
        mTotalSpendAmount   = rootView.findViewById(R.id.spend_total);
        mTotalPrice         = rootView.findViewById(R.id.spend_total_price);
        mCurrentBalance     = rootView.findViewById(R.id.current_available_atom);
        mRemainingBalance   = rootView.findViewById(R.id.remaining_available_atom);
        mRemainingPrice     = rootView.findViewById(R.id.remaining_price);
        mRecipientAddress   = rootView.findViewById(R.id.recipient_address);
        mMemo               = rootView.findViewById(R.id.memo);


        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);
        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onRefreshTab() {
        BigDecimal toSendAtom   = new BigDecimal(getSActivity().mTargetCoins.get(0).amount);
        BigDecimal feeAtom      = new BigDecimal(getSActivity().mTargetFee.amount.get(0).amount);
        BigDecimal currentAvai  = getSActivity().mAccount.getAtomBalance();

        mSendAmount.setText(WDp.getDpAmount(getContext(), toSendAtom, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
        mFeeAmount.setText(WDp.getDpAmount(getContext(), feeAtom, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
        mTotalSpendAmount.setText(WDp.getDpAmount(getContext(), feeAtom.add(toSendAtom), 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
        BigDecimal spendTotal = new BigDecimal(""+mTotalSpendAmount.getText().toString().trim().replace(",","")).multiply(new BigDecimal(""+getBaseDao().getLastAtomTic())).setScale(2, RoundingMode.DOWN);
        mTotalPrice.setText(getString(R.string.str_approximately)+ " $" +  WDp.getDolor(getContext(), spendTotal));

        mCurrentBalance.setText(WDp.getDpAmount(getContext(), currentAvai, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
        mRemainingBalance.setText(WDp.getDpAmount(getContext(), currentAvai.subtract(toSendAtom).subtract(feeAtom), 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
        BigDecimal remainTotal = new BigDecimal(""+mRemainingBalance.getText().toString().trim().replace(",","")).multiply(new BigDecimal(""+getBaseDao().getLastAtomTic())).setScale(2, RoundingMode.DOWN);
        mRemainingPrice.setText(getString(R.string.str_approximately)+ " $" +  WDp.getDolor(getContext(), remainTotal));

        mRecipientAddress.setText(getSActivity().mTagetAddress);
        mMemo.setText(getSActivity().mTargetMemo);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartSend();

        }

    }

    private SendActivity getSActivity() {
        return (SendActivity)getBaseActivity();
    }
}
/*
public class SendStep4Fragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout  mSendAtomLayer, mSendPhotonLayer;
    private TextView        mSendAtomAmount, mSendPhotonAmount;
    private TextView        mFeeAmount, mFeeType;

    private TextView        mFromNickName, mFromAddress, mToAddress, mMemo;
    private TextView        mRemindAtom, mRemindPhoton;
    private TextView        mSendAtomTitle, mSendPhotonTitle, mRemindAtomTitle, mRemindPhotonTitle;

    private Button          mBeforeBtn, mConfirmBtn;
//    private BigDecimal      mToSendAtom, mToSendPhoton;

    public static SendStep4Fragment newInstance(Bundle bundle) {
        SendStep4Fragment fragment = new SendStep4Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView       = inflater.inflate(R.layout.fragment_send_step4, container, false);
        mSendAtomLayer      = rootView.findViewById(R.id.send_atom_layer);
        mSendPhotonLayer    = rootView.findViewById(R.id.send_photon_layer);
        mSendAtomAmount     = rootView.findViewById(R.id.send_atom);
        mSendPhotonAmount   = rootView.findViewById(R.id.send_photon);
        mFeeAmount          = rootView.findViewById(R.id.send_fees);
        mFeeType            = rootView.findViewById(R.id.send_fees_type);
        mFromNickName       = rootView.findViewById(R.id.from_nickname);
        mFromAddress        = rootView.findViewById(R.id.from_address);
        mToAddress          = rootView.findViewById(R.id.to_address);
        mMemo               = rootView.findViewById(R.id.memo);
        mRemindAtom         = rootView.findViewById(R.id.remind_atom);
        mRemindPhoton       = rootView.findViewById(R.id.remind_photon);
        mSendAtomTitle      = rootView.findViewById(R.id.send_atom_title);
        mSendPhotonTitle    = rootView.findViewById(R.id.send_photon_title);
        mRemindAtomTitle    = rootView.findViewById(R.id.remind_atom_title);
        mRemindPhotonTitle  = rootView.findViewById(R.id.remind_photon_title);


        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);
        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onRefreshTab() {
        BigDecimal toSendAtom = BigDecimal.ZERO;
        BigDecimal toSendPhoton = BigDecimal.ZERO;
        BigDecimal remindAtom = BigDecimal.ZERO;
        BigDecimal remindPhoton = BigDecimal.ZERO;

        for(Coin coin:getSActivity().mTargetCoins) {
            if(coin.denom.equals(BaseConstant.COSMOS_ATOM) || coin.denom.equals(BaseConstant.COSMOS_MUON)) {
                mSendAtomLayer.setVisibility(View.VISIBLE);
                toSendAtom = new BigDecimal(coin.amount);
                mSendAtomAmount.setText(WDp.getDpAmount(getContext(), toSendAtom, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
            }
            if(coin.denom.equals(BaseConstant.COSMOS_PHOTON)) {
                mSendPhotonLayer.setVisibility(View.VISIBLE);
                toSendPhoton = new BigDecimal(coin.amount);
                mSendPhotonAmount.setText(WDp.getDpAmount(getContext(), toSendPhoton, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
            }
        }

        remindAtom   = getSActivity().mAccount.getAtomBalance().subtract(toSendAtom);
        remindPhoton = getSActivity().mAccount.getPhotonBalance().subtract(toSendPhoton);

        if(getSActivity().mTargetFee.amount.get(0).denom.equals(BaseConstant.COSMOS_ATOM) ||
                getSActivity().mTargetFee.amount.get(0).denom.equals(BaseConstant.COSMOS_MUON)) {
            mFeeType.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
            mFeeType.setTextColor(getResources().getColor(R.color.colorAtom));
            remindAtom.subtract(new BigDecimal(getSActivity().mTargetFee.amount.get(0).amount));
        } else {
            mFeeType.setText(WDp.DpPoton(getContext(), getSActivity().mAccount.baseChain));
            mFeeType.setTextColor(getResources().getColor(R.color.colorPhoton));
            remindPhoton.subtract(new BigDecimal(getSActivity().mTargetFee.amount.get(0).amount));
        }
        mFeeAmount.setText(WDp.getDpAmount(getContext(), new BigDecimal(getSActivity().mTargetFee.amount.get(0).amount), 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));

        if(TextUtils.isEmpty(getSActivity().mAccount.nickName)) {
            mFromNickName.setText(getString(R.string.str_my_wallet) + getSActivity().mAccount.id);
        } else {
            mFromNickName.setText(getSActivity().mAccount.nickName);
        }

        mFromAddress.setText(getSActivity().mAccount.address);
        mToAddress.setText(getSActivity().mTagetAddress);
        mMemo.setText(getSActivity().mTargetMemo);


        mSendAtomTitle.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
        mSendPhotonTitle.setText(WDp.DpPoton(getContext(), getSActivity().mAccount.baseChain));
        mRemindAtomTitle.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
        mRemindPhotonTitle.setText(WDp.DpPoton(getContext(), getSActivity().mAccount.baseChain));

        mRemindAtom.setText(WDp.getDpAmount(getContext(), remindAtom, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
        mRemindPhoton.setText(WDp.getDpAmount(getContext(), remindPhoton, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));

    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            WLog.w("mConfirmBtn");
            getSActivity().onStartSend();

        }

    }

    private SendActivity getSActivity() {
        return (SendActivity)getBaseActivity();
    }
}
*/