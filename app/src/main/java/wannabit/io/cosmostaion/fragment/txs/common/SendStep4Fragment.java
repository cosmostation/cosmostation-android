package wannabit.io.cosmostaion.fragment.txs.common;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_EXECUTE_CONTRACT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_IBC_CONTRACT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_IBC_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.activities.txs.common.SendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Asset;
import wannabit.io.cosmostaion.dao.MintscanToken;
import wannabit.io.cosmostaion.databinding.FragmentSendStep4Binding;
import wannabit.io.cosmostaion.utils.WDp;

public class SendStep4Fragment extends BaseFragment implements View.OnClickListener {

    private FragmentSendStep4Binding fragmentSendStep4Binding;

    public static SendStep4Fragment newInstance() {
        return new SendStep4Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentSendStep4Binding = FragmentSendStep4Binding.inflate(getLayoutInflater());
        fragmentSendStep4Binding.btnBefore.setOnClickListener(this);
        fragmentSendStep4Binding.btnConfirm.setOnClickListener(this);
        return fragmentSendStep4Binding.getRoot();
    }

    @Override
    public void onRefreshTab() {
        final BigDecimal toSendAmount = new BigDecimal(getSActivity().mAmounts.get(0).amount);
        final BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        final String toSendDenom = getSActivity().mDenom;

        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), fragmentSendStep4Binding.sendFeesType, fragmentSendStep4Binding.sendFees);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, toSendDenom, toSendAmount.toPlainString(), fragmentSendStep4Binding.sendDenom, fragmentSendStep4Binding.sendAmount);

        Asset msAsset = getSActivity().mAsset;
        MintscanToken msMintscanToken = getSActivity().mMintscanToken;

        BigDecimal currentAvai = BigDecimal.ZERO;
        BigDecimal remainAvailable = BigDecimal.ZERO;

        if (msMintscanToken != null) {
            currentAvai = new BigDecimal(msMintscanToken.amount);
            remainAvailable = currentAvai.subtract(toSendAmount);

        } else {
            if (BaseChain.isGRPC(getSActivity().mChainConfig.baseChain())) {
                if (msAsset != null) {
                    currentAvai = getBaseDao().getAvailable(toSendDenom);

                    if (toSendDenom.equalsIgnoreCase(getSActivity().mTxFee.amount.get(0).denom)) {
                        remainAvailable = currentAvai.subtract(toSendAmount).subtract(feeAmount);
                    } else {
                        remainAvailable = currentAvai.subtract(toSendAmount);
                    }
                }

                if (getSActivity().mTxType == CONST_PW_TX_IBC_TRANSFER || getSActivity().mTxType == CONST_PW_TX_IBC_CONTRACT) {
                    fragmentSendStep4Binding.recipientLayer.setVisibility(View.VISIBLE);
                    fragmentSendStep4Binding.ibcLayer.setVisibility(View.VISIBLE);
                    ChainConfig chainConfig = ChainFactory.getChain(WDp.getChainsFromAddress(getSActivity().mToAddress).get(0));
                    if (chainConfig != null) {
                        fragmentSendStep4Binding.recipientChain.setText(chainConfig.chainTitleToUp());
                        fragmentSendStep4Binding.recipientChain.setTextColor(ContextCompat.getColor(getActivity(), chainConfig.chainColor()));
                    }

                } else {
                    fragmentSendStep4Binding.recipientLayer.setVisibility(View.GONE);
                    fragmentSendStep4Binding.ibcLayer.setVisibility(View.GONE);
                }

            } else {
                currentAvai = getBaseDao().availableAmount(toSendDenom);
                if (toSendDenom.equalsIgnoreCase(getSActivity().mTxFee.amount.get(0).denom)) {
                    remainAvailable = currentAvai.subtract(toSendAmount).subtract(feeAmount);
                } else {
                    remainAvailable = currentAvai.subtract(toSendAmount);
                }
                fragmentSendStep4Binding.recipientLayer.setVisibility(View.GONE);
            }
        }
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, toSendDenom, currentAvai.toPlainString(), fragmentSendStep4Binding.currentDenom, fragmentSendStep4Binding.currentAvailable);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, toSendDenom, remainAvailable.toPlainString(), fragmentSendStep4Binding.remainingDenom, fragmentSendStep4Binding.remainingAvailable);
        fragmentSendStep4Binding.recipientAddress.setText(getSActivity().mToAddress);
        fragmentSendStep4Binding.memo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentSendStep4Binding = null;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(fragmentSendStep4Binding.btnBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(fragmentSendStep4Binding.btnConfirm)) {
            if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_SEND) {
                getSActivity().onStartSend();
            } else if (getSActivity().mTxType == CONST_PW_TX_IBC_TRANSFER) {
                getSActivity().onStartIbcSend();
            } else if (getSActivity().mTxType == CONST_PW_TX_EXECUTE_CONTRACT) {
                getSActivity().onStartSendContract();
            } else if (getSActivity().mTxType == CONST_PW_TX_IBC_CONTRACT) {
                getSActivity().onStartIBCContract();
            } else {
                getSActivity().onStartEVMSend();
            }
        }
    }

    private SendActivity getSActivity() {
        return (SendActivity) getBaseActivity();
    }
}
