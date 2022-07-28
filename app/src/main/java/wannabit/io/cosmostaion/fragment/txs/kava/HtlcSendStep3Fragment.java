package wannabit.io.cosmostaion.fragment.txs.kava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.HtlcSendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.utils.WDp;

public class HtlcSendStep3Fragment extends BaseFragment implements View.OnClickListener {
    public final static int SELECT_HTLC_CONFIRM = 9104;

    private CardView mSendCard;
    private ImageView mSendIcon;
    private TextView mSendAmountTv, mSendDenomTv;
    private TextView mSendFeeAmountTv, mSendFeeDenomTv;
    private TextView mReceiveChainTv, mReceiveAddressTv;
    private CardView mClaimCard;
    private ImageView mClaimIcon;
    private TextView mReceiveAmountTv, mReceiveAmountDenomTv;
    private TextView mRelayFeeAmountTv, mRelayFeeAmountDenomTv;
    private TextView mClaimFeeAmountTv, mClaimFeeDenomTv;
    private TextView mClaimAddressTv;
    private Button mBeforeBtn, mConfirmBtn;

    private int mDecimal = 8;
    public String mToSwapDenom;

    public static HtlcSendStep3Fragment newInstance() {
        return new HtlcSendStep3Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_htlc_send_step3, container, false);
        mSendCard = rootView.findViewById(R.id.send_swap_card);
        mSendIcon = rootView.findViewById(R.id.send_icon);
        mSendAmountTv = rootView.findViewById(R.id.send_amount);
        mSendDenomTv = rootView.findViewById(R.id.send_amount_denom);
        mSendFeeAmountTv = rootView.findViewById(R.id.send_fee);
        mSendFeeDenomTv = rootView.findViewById(R.id.send_fee_denom);
        mReceiveChainTv = rootView.findViewById(R.id.recipient_chain);
        mReceiveAddressTv = rootView.findViewById(R.id.recipient_address);
        mClaimCard = rootView.findViewById(R.id.claim_swap_card);
        mClaimIcon = rootView.findViewById(R.id.claim_icon);
        mReceiveAmountTv = rootView.findViewById(R.id.receive_amount);
        mReceiveAmountDenomTv = rootView.findViewById(R.id.receive_amount_denom);
        mRelayFeeAmountTv = rootView.findViewById(R.id.relay_fee);
        mRelayFeeAmountDenomTv = rootView.findViewById(R.id.relay_fee_denom);
        mClaimFeeAmountTv = rootView.findViewById(R.id.claim_fee);
        mClaimFeeDenomTv = rootView.findViewById(R.id.claim_fee_denom);
        mClaimAddressTv = rootView.findViewById(R.id.claim_address);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);
        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onRefreshTab() {
        final ChainConfig fromChainConfig = getSActivity().mChainConfig;
        final ChainConfig toChainConfig = ChainFactory.getChain(getSActivity().mRecipientChain);

        mToSwapDenom = getSActivity().mToSwapDenom;
        Fee sendFee = getSActivity().onInitSendFee();
        Fee claimFee = getSActivity().onInitClaimFee();

        BigDecimal toSendAmount = new BigDecimal(getSActivity().mToSendCoins.get(0).amount);
        BigDecimal sendFeeAmount = new BigDecimal(sendFee.amount.get(0).amount);
        BigDecimal claimFeeAmount = new BigDecimal(claimFee.amount.get(0).amount);

        // set send card view
        mSendIcon.setColorFilter(ContextCompat.getColor(getSActivity(), fromChainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);
        WDp.setDpCoin(getSActivity(), getBaseDao(), fromChainConfig, getSActivity().mToSwapDenom, toSendAmount.toPlainString(), mSendDenomTv, mSendAmountTv);
        WDp.setDpCoin(getSActivity(), getBaseDao(), fromChainConfig, fromChainConfig.mainDenom(), sendFeeAmount.toPlainString(), mSendFeeDenomTv, mSendFeeAmountTv);
        mReceiveChainTv.setText(StringUtils.capitalize(toChainConfig.chainName()));
        mReceiveAddressTv.setText(getSActivity().mRecipientAccount.address);

        //set claim card view
        mClaimIcon.setColorFilter(ContextCompat.getColor(getSActivity(), toChainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);
        if (getSActivity().mRecipientChain.equals(BaseChain.BNB_MAIN)) {
            BigDecimal relayFee = getSActivity().mKavaBep3Param2.getSupportedSwapAssetFee(getSActivity().mToSwapDenom);
            mReceiveAmountTv.setText(WDp.getDpAmount2(getContext(), toSendAmount.subtract(relayFee), mDecimal, mDecimal));
            WDp.setDpSymbol(getSActivity(), getBaseDao(), toChainConfig, mToSwapDenom, mReceiveAmountDenomTv);

            mRelayFeeAmountTv.setText(WDp.getDpAmount2(getContext(), relayFee, mDecimal, mDecimal));
            WDp.setDpSymbol(getSActivity(), getBaseDao(), toChainConfig, mToSwapDenom, mRelayFeeAmountDenomTv);

            WDp.setDpCoin(getSActivity(), getBaseDao(), toChainConfig, toChainConfig.mainDenom(), claimFeeAmount.toPlainString(), mClaimFeeDenomTv, mClaimFeeAmountTv);
            mClaimAddressTv.setText(getSActivity().mRecipientAccount.address);

        } else if (getSActivity().mRecipientChain.equals(BaseChain.KAVA_MAIN)) {
            BigDecimal relayFee = getSActivity().mKavaBep3Param2.getSupportedSwapAssetFee(getSActivity().mToSwapDenom).movePointLeft(8);
            mReceiveAmountTv.setText(WDp.getDpAmount2(getContext(), toSendAmount.subtract(relayFee), 0, 8));
            WDp.setDpSymbol(getSActivity(), getBaseDao(), toChainConfig, mToSwapDenom, mReceiveAmountDenomTv);

            mRelayFeeAmountTv.setText(WDp.getDpAmount2(getContext(), relayFee, 0, 8));
            WDp.setDpSymbol(getSActivity(), getBaseDao(), toChainConfig, mToSwapDenom, mRelayFeeAmountDenomTv);

            WDp.setDpCoin(getSActivity(), getBaseDao(), toChainConfig, toChainConfig.mainDenom(), claimFeeAmount.toPlainString(), mClaimFeeDenomTv, mClaimFeeAmountTv);
            mClaimAddressTv.setText(getSActivity().mRecipientAccount.address);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            AlertDialogUtils.showHeaderImageDoubleButtonDialog(getSActivity(), getString(R.string.str_htlc_warn_title), getString(R.string.str_htlc_warn_msg),
                    getString(R.string.str_cancel), null,
                    getString(R.string.str_confirm), View -> {
                        Intent resultIntent = new Intent();
                        onActivityResult(SELECT_HTLC_CONFIRM, Activity.RESULT_OK, resultIntent);
                    }, R.drawable.img_bep_3_available);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_HTLC_CONFIRM && resultCode == Activity.RESULT_OK) {
            getSActivity().onStartHtlcSend();
        }
    }

    private HtlcSendActivity getSActivity() {
        return (HtlcSendActivity) getBaseActivity();
    }
}
