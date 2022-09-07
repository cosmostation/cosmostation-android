package wannabit.io.cosmostaion.fragment.txs.common;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_EXECUTE_CONTRACT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_IBC_CONTRACT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_IBC_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.SendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Asset;
import wannabit.io.cosmostaion.dao.Cw20Asset;
import wannabit.io.cosmostaion.utils.WDp;

public class SendStep4Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mSendAmount;
    private TextView mFeeAmount;
    private TextView mCurrentBalance, mRemainingBalance, mRemainingPrice;
    private LinearLayout mRecipientLayer, mIbcLayer;
    private TextView mRecipientChain, mRecipientChannel, mRecipientAddress, mMemo;
    private Button mBeforeBtn, mConfirmBtn;
    private TextView mSendDenom, mFeeDenom, mCurrentDenom, mRemainDenom;

    public static SendStep4Fragment newInstance() {
        return new SendStep4Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_send_step4, container, false);
        mSendAmount = rootView.findViewById(R.id.send_amount);
        mFeeAmount = rootView.findViewById(R.id.send_fees);
        mCurrentBalance = rootView.findViewById(R.id.current_available);
        mRemainingBalance = rootView.findViewById(R.id.remaining_available);
        mRemainingPrice = rootView.findViewById(R.id.remaining_price);
        mRecipientLayer = rootView.findViewById(R.id.recipient_layer);
        mRecipientChain = rootView.findViewById(R.id.recipient_chain);
        mRecipientChannel = rootView.findViewById(R.id.recipient_channel);
        mRecipientAddress = rootView.findViewById(R.id.recipient_address);
        mIbcLayer = rootView.findViewById(R.id.ibc_layer);

        mSendDenom = rootView.findViewById(R.id.send_denom);
        mFeeDenom = rootView.findViewById(R.id.send_fees_type);
        mCurrentDenom = rootView.findViewById(R.id.current_denom);
        mRemainDenom = rootView.findViewById(R.id.remaining_denom);
        mMemo = rootView.findViewById(R.id.memo);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        final BigDecimal toSendAmount = new BigDecimal(getSActivity().mAmounts.get(0).amount);
        final BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        final String mainDenom = getSActivity().mChainConfig.mainDenom();
        final String toSendDenom = getSActivity().mDenom;

        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, toSendDenom, toSendAmount.toPlainString(), mSendDenom, mSendAmount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeDenom, mFeeAmount);

        Asset msAsset = getSActivity().mAsset;
        Cw20Asset msCw20Asset = getSActivity().mCw20Asset;

        if (BaseChain.isGRPC(getSActivity().mBaseChain)) {
            BigDecimal currentAvai = BigDecimal.ZERO;
            BigDecimal remainAvailable = BigDecimal.ZERO;
            if (msAsset != null) {
                currentAvai = getBaseDao().getAvailable(toSendDenom);

                if (toSendDenom.equalsIgnoreCase(getSActivity().mTxFee.amount.get(0).denom)) {
                    remainAvailable = currentAvai.subtract(toSendAmount).subtract(feeAmount);
                } else {
                    remainAvailable = currentAvai.subtract(toSendAmount);
                }

            } else if (msCw20Asset != null) {
                currentAvai = new BigDecimal(msCw20Asset.amount);
                remainAvailable = currentAvai.subtract(toSendAmount);
            }
            WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, toSendDenom, currentAvai.toPlainString(), mCurrentDenom, mCurrentBalance);
            WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, toSendDenom, remainAvailable.toPlainString(), mRemainDenom, mRemainingBalance);

            if (toSendDenom.equals(mainDenom)) {
                mRemainingPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), toSendDenom, remainAvailable, WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, toSendDenom)));
            } else {
                mRemainingPrice.setVisibility(View.GONE);
            }

            if (getSActivity().mTxType == CONST_PW_TX_IBC_TRANSFER || getSActivity().mTxType == CONST_PW_TX_IBC_CONTRACT) {
                mRecipientLayer.setVisibility(View.VISIBLE);
                mIbcLayer.setVisibility(View.VISIBLE);
                ChainConfig chainConfig = ChainFactory.getChain(WDp.getChainsFromAddress(getSActivity().mToAddress).get(0));
                if (chainConfig != null) {
                    mRecipientChain.setText(chainConfig.chainTitleToUp());
                    mRecipientChain.setTextColor(ContextCompat.getColor(getActivity(), chainConfig.chainColor()));
                    mRecipientChannel.setText("(" + getSActivity().mAssetPath.channel + ")");
                }

            } else {
                mRecipientLayer.setVisibility(View.GONE);
                mIbcLayer.setVisibility(View.GONE);
            }

        } else {
            BigDecimal currentAvai = getBaseDao().availableAmount(toSendDenom);
            WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, toSendDenom, currentAvai.toPlainString(), mCurrentDenom, mCurrentBalance);

            BigDecimal remainAmount = BigDecimal.ZERO;
            if (toSendDenom.equalsIgnoreCase(getSActivity().mTxFee.amount.get(0).denom)) {
                remainAmount = currentAvai.subtract(toSendAmount).subtract(feeAmount);
            } else {
                remainAmount = currentAvai.subtract(toSendAmount);
            }
            WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, toSendDenom, remainAmount.toPlainString(), mRemainDenom, mRemainingBalance);

            if (toSendDenom.equals(mainDenom)) {
                mRemainingPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), toSendDenom, remainAmount, WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, toSendDenom)));
            } else {
                mRemainingPrice.setVisibility(View.GONE);
            }
        }
        mRecipientAddress.setText(getSActivity().mToAddress);
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_SEND) {
                getSActivity().onStartSend();
            } else if (getSActivity().mTxType == CONST_PW_TX_IBC_TRANSFER) {
                getSActivity().onStartIbcSend();
            } else if (getSActivity().mTxType == CONST_PW_TX_EXECUTE_CONTRACT) {
                getSActivity().onStartSendContract();
            } else {
                getSActivity().onStartIBCContract();
            }
        }
    }

    private SendActivity getSActivity() {
        return (SendActivity) getBaseActivity();
    }
}
