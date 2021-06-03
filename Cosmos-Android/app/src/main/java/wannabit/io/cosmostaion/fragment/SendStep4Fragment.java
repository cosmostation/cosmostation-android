package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BAND;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CERTIK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_DVPN;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_FET;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KI;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SECRET;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SIF;

public class SendStep4Fragment extends BaseFragment implements View.OnClickListener {

    private TextView        mSendAmount;
    private TextView        mFeeAmount;
    private RelativeLayout  mTotalSpendLayer;
    private TextView        mTotalSpendAmount, mTotalPrice;
    private TextView        mCurrentBalance, mRemainingBalance, mRemainingPrice;
    private TextView        mRecipientAddress, mRecipientStartName, mMemo;
    private Button          mBeforeBtn, mConfirmBtn;
    private TextView        mDenomSendAmount, mDenomFeeType, mDenomTotalSpend, mDenomCurrentAmount, mDenomRemainAmount;
    private int             mDisplayDecimal = 6;        //bnb == 8
    private int             mDivideDecimal = 6;         //bnb == 0

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
        mSendAmount         = rootView.findViewById(R.id.send_amount);
        mFeeAmount          = rootView.findViewById(R.id.send_fees);
        mTotalSpendLayer    = rootView.findViewById(R.id.spend_total_layer);
        mTotalSpendAmount   = rootView.findViewById(R.id.spend_total);
        mTotalPrice         = rootView.findViewById(R.id.spend_total_price);
        mCurrentBalance     = rootView.findViewById(R.id.current_available_atom);
        mRemainingBalance   = rootView.findViewById(R.id.remaining_available_atom);
        mRemainingPrice     = rootView.findViewById(R.id.remaining_price);
        mRecipientAddress   = rootView.findViewById(R.id.recipient_address);
        mRecipientStartName = rootView.findViewById(R.id.recipient_starname);
        mMemo               = rootView.findViewById(R.id.memo);
        mBeforeBtn          = rootView.findViewById(R.id.btn_before);
        mConfirmBtn         = rootView.findViewById(R.id.btn_confirm);
        mDenomSendAmount    = rootView.findViewById(R.id.send_amount_title);
        mDenomFeeType       = rootView.findViewById(R.id.send_fees_type);
        mDenomTotalSpend    = rootView.findViewById(R.id.spend_total_type);
        mDenomCurrentAmount = rootView.findViewById(R.id.current_available_title);
        mDenomRemainAmount  = rootView.findViewById(R.id.remaining_available_title);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomFeeType);
        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomTotalSpend);
        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomSendAmount);
        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomCurrentAmount);
        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomRemainAmount);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onRefreshTab() {
        BigDecimal toSendAmount   = new BigDecimal(getSActivity().mAmounts.get(0).amount);
        BigDecimal feeAmount      = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        final String mainDenom = WDp.mainDenom(getSActivity().mBaseChain);
        final String toSendDenom = getSActivity().mDenom;

        if (isGRPC(getSActivity().mBaseChain)) {
            mDivideDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
            mDisplayDecimal = WDp.mainDisplayDecimal(getSActivity().mBaseChain);

            mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, mDivideDecimal, mDisplayDecimal));

            if (getSActivity().mDenom.equals(WDp.mainDenom(getSActivity().mBaseChain))) {
                mSendAmount.setText(WDp.getDpAmount2(getContext(), toSendAmount, mDivideDecimal, mDisplayDecimal));
                mTotalSpendAmount.setText(WDp.getDpAmount2(getContext(), feeAmount.add(toSendAmount), mDivideDecimal, mDisplayDecimal));
                mTotalPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), WDp.mainDenom(getSActivity().mBaseChain), feeAmount.add(toSendAmount), mDivideDecimal));

                BigDecimal currentAvai = getBaseDao().getAvailable(getSActivity().mDenom);
                mCurrentBalance.setText(WDp.getDpAmount2(getContext(), currentAvai, mDivideDecimal, mDisplayDecimal));
                mRemainingBalance.setText(WDp.getDpAmount2(getContext(), currentAvai.subtract(toSendAmount).subtract(feeAmount), mDivideDecimal, mDisplayDecimal));
                mRemainingPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), WDp.mainDenom(getSActivity().mBaseChain), currentAvai.subtract(toSendAmount).subtract(feeAmount), mDivideDecimal));

            } else {
                // not staking denom send
                mDenomSendAmount.setTextColor(getResources().getColor(R.color.colorWhite));
                mDenomCurrentAmount.setTextColor(getResources().getColor(R.color.colorWhite));
                mDenomRemainAmount.setTextColor(getResources().getColor(R.color.colorWhite));
                mTotalSpendLayer.setVisibility(View.GONE);
                mTotalPrice.setVisibility(View.GONE);
                mRemainingPrice.setVisibility(View.GONE);

                BigDecimal currentAvai  = getBaseDao().getAvailable(toSendDenom);
                WDp.showCoinDp(getContext(), getSActivity().mDenom, toSendAmount.toPlainString(), mDenomSendAmount, mSendAmount, getSActivity().mBaseChain);
                WDp.showCoinDp(getContext(), getSActivity().mDenom, currentAvai.toPlainString(), mDenomCurrentAmount, mCurrentBalance, getSActivity().mBaseChain);
                WDp.showCoinDp(getContext(), getSActivity().mDenom, currentAvai.subtract(toSendAmount).toPlainString(), mDenomRemainAmount, mRemainingBalance, getSActivity().mBaseChain);
            }

        } else {
            if (getSActivity().mBaseChain.equals(BNB_MAIN) || getSActivity().mBaseChain.equals(BNB_TEST)) {
                mDivideDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
                mDisplayDecimal = WDp.mainDisplayDecimal(getSActivity().mBaseChain);

                mDenomSendAmount.setText(getSActivity().mBnbToken.original_symbol.toUpperCase());
                mDenomCurrentAmount.setText(getSActivity().mBnbToken.original_symbol.toUpperCase());
                mDenomRemainAmount.setText(getSActivity().mBnbToken.original_symbol.toUpperCase());

                mSendAmount.setText(WDp.getDpAmount2(getContext(), toSendAmount, mDivideDecimal, mDisplayDecimal));
                mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, mDivideDecimal, mDisplayDecimal));

                if (getSActivity().mBnbToken.symbol.equals(TOKEN_BNB)) {
                    mDenomSendAmount.setTextColor(getResources().getColor(R.color.colorBnb));
                    mDenomCurrentAmount.setTextColor(getResources().getColor(R.color.colorBnb));
                    mDenomRemainAmount.setTextColor(getResources().getColor(R.color.colorBnb));

                    mTotalSpendAmount.setText(WDp.getDpAmount2(getContext(), feeAmount.add(toSendAmount), mDivideDecimal, mDisplayDecimal));
                    mTotalPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), TOKEN_BNB, feeAmount.add(toSendAmount), 0));

                    BigDecimal currentAvai  = getSActivity().mAccount.getBnbBalance();
                    mCurrentBalance.setText(WDp.getDpAmount2(getContext(), currentAvai, mDivideDecimal, mDisplayDecimal));
                    mRemainingBalance.setText(WDp.getDpAmount2(getContext(), currentAvai.subtract(toSendAmount).subtract(feeAmount), mDivideDecimal, mDisplayDecimal));
                    mRemainingPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), TOKEN_BNB, currentAvai.subtract(toSendAmount).subtract(feeAmount), 0));

                } else {
                    mDenomSendAmount.setTextColor(getResources().getColor(R.color.colorWhite));
                    mDenomCurrentAmount.setTextColor(getResources().getColor(R.color.colorWhite));
                    mDenomRemainAmount.setTextColor(getResources().getColor(R.color.colorWhite));
                    mTotalSpendLayer.setVisibility(View.GONE);
                    mTotalPrice.setVisibility(View.GONE);
                    mRemainingPrice.setVisibility(View.GONE);

                    BigDecimal currentAvai  = getSActivity().mAccount.getBnbTokenBalance(getSActivity().mBnbToken.symbol);
                    mCurrentBalance.setText(WDp.getDpAmount2(getContext(), currentAvai, mDivideDecimal, mDisplayDecimal));
                    mRemainingBalance.setText(WDp.getDpAmount2(getContext(), currentAvai.subtract(toSendAmount), mDivideDecimal, mDisplayDecimal));
                }


            } else if (getSActivity().mBaseChain.equals(OKEX_MAIN) || getSActivity().mBaseChain.equals(OK_TEST)) {
                mDivideDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
                mDisplayDecimal = WDp.mainDisplayDecimal(getSActivity().mBaseChain);

                mSendAmount.setText(WDp.getDpAmount2(getContext(), toSendAmount, mDivideDecimal, mDisplayDecimal));
                mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, mDivideDecimal, mDisplayDecimal));

                mDenomSendAmount.setText(toSendDenom.toUpperCase());
                mDenomCurrentAmount.setText(toSendDenom.toUpperCase());
                mDenomRemainAmount.setText(toSendDenom.toUpperCase());

                if (toSendDenom.equals(mainDenom)) {
                    mDenomSendAmount.setTextColor(getResources().getColor(R.color.colorOK));
                    mDenomCurrentAmount.setTextColor(getResources().getColor(R.color.colorOK));
                    mDenomRemainAmount.setTextColor(getResources().getColor(R.color.colorOK));
                    mTotalSpendAmount.setText(WDp.getDpAmount2(getContext(), feeAmount.add(toSendAmount), mDivideDecimal, mDisplayDecimal));
                    mTotalPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), TOKEN_OK, feeAmount.add(toSendAmount), mDivideDecimal));

                    BigDecimal currentAvai  = getBaseDao().availableAmount(toSendDenom);
                    mCurrentBalance.setText(WDp.getDpAmount2(getContext(), currentAvai, mDivideDecimal, mDisplayDecimal));
                    mRemainingBalance.setText(WDp.getDpAmount2(getContext(), currentAvai.subtract(toSendAmount).subtract(feeAmount), mDivideDecimal, mDisplayDecimal));
                    mRemainingPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), TOKEN_OK, currentAvai.subtract(toSendAmount).subtract(feeAmount), mDivideDecimal));

                } else {
                    mDenomSendAmount.setTextColor(getResources().getColor(R.color.colorWhite));
                    mDenomCurrentAmount.setTextColor(getResources().getColor(R.color.colorWhite));
                    mDenomRemainAmount.setTextColor(getResources().getColor(R.color.colorWhite));
                    mTotalSpendLayer.setVisibility(View.GONE);
                    mTotalPrice.setVisibility(View.GONE);
                    mRemainingPrice.setVisibility(View.GONE);

                    BigDecimal currentAvai = getBaseDao().availableAmount(toSendDenom);
                    mCurrentBalance.setText(WDp.getDpAmount2(getContext(), currentAvai, mDivideDecimal, mDisplayDecimal));
                    mRemainingBalance.setText(WDp.getDpAmount2(getContext(), currentAvai.subtract(toSendAmount), mDivideDecimal, mDisplayDecimal));

                }

            } else if (getSActivity().mBaseChain.equals(KAVA_MAIN) || getSActivity().mBaseChain.equals(KAVA_TEST)) {
                mDivideDecimal = WUtil.getKavaCoinDecimal(toSendDenom);
                mDisplayDecimal = WUtil.getKavaCoinDecimal(toSendDenom);

                mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, 6, 6));
                if (toSendDenom.equals(TOKEN_KAVA)) {
                    mSendAmount.setText(WDp.getDpAmount2(getContext(), toSendAmount, mDivideDecimal, mDisplayDecimal));
                    mTotalSpendAmount.setText(WDp.getDpAmount2(getContext(), feeAmount.add(toSendAmount), mDivideDecimal, mDisplayDecimal));
                    mTotalPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), TOKEN_KAVA, feeAmount.add(toSendAmount), mDivideDecimal));

                    BigDecimal currentAvai  = getBaseDao().availableAmount(toSendDenom);
                    mCurrentBalance.setText(WDp.getDpAmount2(getContext(), currentAvai, mDivideDecimal, mDisplayDecimal));
                    mRemainingBalance.setText(WDp.getDpAmount2(getContext(), currentAvai.subtract(toSendAmount).subtract(feeAmount), mDivideDecimal, mDisplayDecimal));
                    mRemainingPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), TOKEN_KAVA, currentAvai.subtract(toSendAmount).subtract(feeAmount), mDivideDecimal));

                } else {
                    mDenomSendAmount.setTextColor(getResources().getColor(R.color.colorWhite));
                    mDenomCurrentAmount.setTextColor(getResources().getColor(R.color.colorWhite));
                    mDenomRemainAmount.setTextColor(getResources().getColor(R.color.colorWhite));
                    mTotalSpendLayer.setVisibility(View.GONE);
                    mTotalPrice.setVisibility(View.GONE);
                    mRemainingPrice.setVisibility(View.GONE);

                    BigDecimal currentAvai  = getBaseDao().availableAmount(toSendDenom);
                    WDp.showCoinDp(getContext(), getSActivity().mDenom, toSendAmount.toPlainString(), mDenomSendAmount, mSendAmount, getSActivity().mBaseChain);
                    WDp.showCoinDp(getContext(), getSActivity().mDenom, currentAvai.toPlainString(), mDenomCurrentAmount, mCurrentBalance, getSActivity().mBaseChain);
                    WDp.showCoinDp(getContext(), getSActivity().mDenom, currentAvai.subtract(toSendAmount).toPlainString(), mDenomRemainAmount, mRemainingBalance, getSActivity().mBaseChain);
                }

            } else if (getSActivity().mBaseChain.equals(SIF_MAIN)) {
                mDivideDecimal = WUtil.getSifCoinDecimal(toSendDenom);
                mDisplayDecimal = WUtil.getSifCoinDecimal(toSendDenom);

                mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, 18, 18));
                if (toSendDenom.equals(TOKEN_SIF)) {
                    mSendAmount.setText(WDp.getDpAmount2(getContext(), toSendAmount, mDivideDecimal, mDisplayDecimal));
                    mTotalSpendAmount.setText(WDp.getDpAmount2(getContext(), feeAmount.add(toSendAmount), mDivideDecimal, mDisplayDecimal));
                    mTotalPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), TOKEN_SIF, feeAmount.add(toSendAmount), mDivideDecimal));

                    BigDecimal currentAvai  = getBaseDao().availableAmount(toSendDenom);
                    mCurrentBalance.setText(WDp.getDpAmount2(getContext(), currentAvai, mDivideDecimal, mDisplayDecimal));
                    mRemainingBalance.setText(WDp.getDpAmount2(getContext(), currentAvai.subtract(toSendAmount).subtract(feeAmount), mDivideDecimal, mDisplayDecimal));
                    mRemainingPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), TOKEN_SIF, currentAvai.subtract(toSendAmount).subtract(feeAmount), mDivideDecimal));

                } else {
                    mDenomSendAmount.setTextColor(getResources().getColor(R.color.colorWhite));
                    mDenomCurrentAmount.setTextColor(getResources().getColor(R.color.colorWhite));
                    mDenomRemainAmount.setTextColor(getResources().getColor(R.color.colorWhite));
                    mTotalSpendLayer.setVisibility(View.GONE);
                    mTotalPrice.setVisibility(View.GONE);
                    mRemainingPrice.setVisibility(View.GONE);

                    BigDecimal currentAvai  = getBaseDao().availableAmount(toSendDenom);
                    WDp.showCoinDp(getContext(), getSActivity().mDenom, toSendAmount.toPlainString(), mDenomSendAmount, mSendAmount, getSActivity().mBaseChain);
                    WDp.showCoinDp(getContext(), getSActivity().mDenom, currentAvai.toPlainString(), mDenomCurrentAmount, mCurrentBalance, getSActivity().mBaseChain);
                    WDp.showCoinDp(getContext(), getSActivity().mDenom, currentAvai.subtract(toSendAmount).toPlainString(), mDenomRemainAmount, mRemainingBalance, getSActivity().mBaseChain);

                }
            }

            else {
                mDivideDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
                mDisplayDecimal = WDp.mainDisplayDecimal(getSActivity().mBaseChain);

                mSendAmount.setText(WDp.getDpAmount2(getContext(), toSendAmount, mDivideDecimal, mDisplayDecimal));
                mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, mDivideDecimal, mDisplayDecimal));

                mSendAmount.setText(WDp.getDpAmount2(getContext(), toSendAmount, mDivideDecimal, mDisplayDecimal));
                mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, mDivideDecimal, mDisplayDecimal));
                mTotalSpendAmount.setText(WDp.getDpAmount2(getContext(), feeAmount.add(toSendAmount), mDivideDecimal, mDisplayDecimal));
                mTotalPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), toSendDenom, feeAmount.add(toSendAmount), mDivideDecimal));

                BigDecimal currentAvai = getBaseDao().availableAmount(toSendDenom);
                mCurrentBalance.setText(WDp.getDpAmount2(getContext(), currentAvai, mDivideDecimal, mDisplayDecimal));
                mRemainingBalance.setText(WDp.getDpAmount2(getContext(), currentAvai.subtract(toSendAmount).subtract(feeAmount), mDivideDecimal, mDisplayDecimal));
                mRemainingPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), toSendDenom, currentAvai.subtract(toSendAmount).subtract(feeAmount), mDivideDecimal));

            }
        }

        if (getSActivity().mBaseChain.equals(OKEX_MAIN)) {
            try {
                mRecipientAddress.setText(WKey.convertAddressOkexToEth(getSActivity().mToAddress));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mRecipientAddress.setText(getSActivity().mToAddress);
        }
        mRecipientStartName.setVisibility(View.GONE);
//        if (TextUtils.isEmpty(getSActivity().mStarName)) {
//            mRecipientStartName.setVisibility(View.GONE);
//        } else {
//            mRecipientStartName.setVisibility(View.VISIBLE);
//            mRecipientStartName.setText(getSActivity().mStarName);
//        }

        mMemo.setText(getSActivity().mTxMemo);
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
