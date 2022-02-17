package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_TEST_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_TEST_BTC;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_XRPB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_TEST_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_TEST_BTC;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_XRPB;

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

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.HtlcSendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_Htlc_Warning;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class HtlcSendStep3Fragment extends BaseFragment implements View.OnClickListener {
    public final static int SELECT_HTLC_CONFIRM = 9104;

    private CardView    mSendCard;
    private ImageView   mSendIcon;
    private TextView    mSendAmountTv, mSendDenomTv;
    private TextView    mSendFeeAmountTv, mSendFeeDenomTv;
    private TextView    mReceiveChainTv, mReceiveAddressTv;
    private CardView    mClaimCard;
    private ImageView   mClaimIcon;
    private TextView    mReceiveAmountTv, mReceiveAmountDenomTv;
    private TextView    mRelayFeeAmountTv, mRelayFeeAmountDenomTv;
    private TextView    mClaimFeeAmountTv, mClaimFeeDenomTv;
    private TextView    mClaimAddressTv;
    private Button      mBeforeBtn, mConfirmBtn;

    private int         mDecimal = 8;
    public String       mToSwapDenom;

    public static HtlcSendStep3Fragment newInstance(Bundle bundle) {
        HtlcSendStep3Fragment fragment = new HtlcSendStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
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
        mToSwapDenom = getSActivity().mToSwapDenom;
        Fee sendFee = getSActivity().onInitSendFee();
        Fee claimFee = getSActivity().onInitClaimFee();

        BigDecimal toSendAmount         = new BigDecimal(getSActivity().mToSendCoins.get(0).amount);
        BigDecimal sendFeeAmount        = new BigDecimal(sendFee.amount.get(0).amount);
        BigDecimal claimFeeAmount       = new BigDecimal(claimFee.amount.get(0).amount);

        // set send card view
        mSendIcon.setColorFilter(WDp.getChainColor(getContext(), getSActivity().mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        if (getSActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
            if (mToSwapDenom.equals(TOKEN_HTLC_BINANCE_BNB) || mToSwapDenom.equals(TOKEN_HTLC_BINANCE_TEST_BNB)) {
                mSendDenomTv.setText(getString(R.string.str_bnb_c));
                mSendDenomTv.setTextColor(getResources().getColor(R.color.colorBnb));
            } else if (mToSwapDenom.equals(TOKEN_HTLC_BINANCE_BTCB) || mToSwapDenom.equals(TOKEN_HTLC_BINANCE_TEST_BTC)) {
                mSendDenomTv.setText(getString(R.string.str_btc_c));
                mSendDenomTv.setTextColor(getResources().getColor(R.color.colorWhite));
            } else if (mToSwapDenom.equals(TOKEN_HTLC_BINANCE_XRPB)) {
                mSendDenomTv.setText("XRP");
                mSendDenomTv.setTextColor(getResources().getColor(R.color.colorWhite));
            } else if (mToSwapDenom.equals(TOKEN_HTLC_BINANCE_BUSD)) {
                mSendDenomTv.setText("BUSD");
                mSendDenomTv.setTextColor(getResources().getColor(R.color.colorWhite));
            }
            WDp.DpMainDenom(getContext(), getSActivity().mBaseChain.getChain(), mSendFeeDenomTv);

            mSendAmountTv.setText(WDp.getDpAmount2(getContext(), toSendAmount, 0, 8));
            mSendFeeAmountTv.setText(WDp.getDpAmount2(getContext(), sendFeeAmount, 0, 8));
            mReceiveChainTv.setText(WDp.getDpChainName(getContext(), getSActivity().mRecipientChain));
            mReceiveAddressTv.setText(getSActivity().mRecipientAccount.address);

        } else if (getSActivity().mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            mDecimal = WUtil.getKavaCoinDecimal(getBaseDao(), getSActivity().mToSwapDenom);
            mSendDenomTv.setText(getSActivity().mToSwapDenom.toUpperCase());
            WDp.DpMainDenom(getContext(), getSActivity().mBaseChain.getChain(), mSendFeeDenomTv);

            mSendAmountTv.setText(WDp.getDpAmount2(getContext(), toSendAmount, mDecimal, mDecimal));
            mSendFeeAmountTv.setText(WDp.getDpAmount2(getContext(), sendFeeAmount, 6, 6));
            mReceiveChainTv.setText(WDp.getDpChainName(getContext(), getSActivity().mRecipientChain));
            mReceiveAddressTv.setText(getSActivity().mRecipientAccount.address);
        }

        //set claim card view
        mClaimIcon.setColorFilter(WDp.getChainColor(getContext(), getSActivity().mRecipientChain), android.graphics.PorterDuff.Mode.SRC_IN);
        if (getSActivity().mRecipientChain.equals(BaseChain.BNB_MAIN)) {
            mReceiveAmountDenomTv.setText(getSActivity().mToSwapDenom.toUpperCase());
            mRelayFeeAmountDenomTv.setText(getSActivity().mToSwapDenom.toUpperCase());
            if (mToSwapDenom.equals(TOKEN_HTLC_KAVA_BNB) || mToSwapDenom.equals(TOKEN_HTLC_KAVA_TEST_BNB)) {
                mReceiveAmountDenomTv.setTextColor(getResources().getColor(R.color.colorBnb));
                mRelayFeeAmountDenomTv.setTextColor(getResources().getColor(R.color.colorBnb));
            } else if (mToSwapDenom.equals(TOKEN_HTLC_KAVA_BTCB) || mToSwapDenom.equals(TOKEN_HTLC_KAVA_TEST_BTC)) {
                mReceiveAmountDenomTv.setTextColor(getResources().getColor(R.color.colorWhite));
                mRelayFeeAmountDenomTv.setTextColor(getResources().getColor(R.color.colorWhite));
            } else if (mToSwapDenom.equals(TOKEN_HTLC_KAVA_XRPB)) {
                mReceiveAmountDenomTv.setTextColor(getResources().getColor(R.color.colorWhite));
                mRelayFeeAmountDenomTv.setTextColor(getResources().getColor(R.color.colorWhite));
            } else if (mToSwapDenom.equals(TOKEN_HTLC_KAVA_BUSD)) {
                mReceiveAmountDenomTv.setTextColor(getResources().getColor(R.color.colorWhite));
                mRelayFeeAmountDenomTv.setTextColor(getResources().getColor(R.color.colorWhite));
            }
            WDp.DpMainDenom(getContext(), getSActivity().mRecipientChain.getChain(), mClaimFeeDenomTv);

            BigDecimal relayFee = getSActivity().mKavaBep3Param2.getSupportedSwapAssetFee(getSActivity().mToSwapDenom);
            mReceiveAmountTv.setText(WDp.getDpAmount2(getContext(), toSendAmount.subtract(relayFee), mDecimal, mDecimal));
            mRelayFeeAmountTv.setText(WDp.getDpAmount2(getContext(), relayFee, mDecimal, mDecimal));
            mClaimFeeAmountTv.setText(WDp.getDpAmount2(getContext(), claimFeeAmount, 0, mDecimal));
            mClaimAddressTv.setText(getSActivity().mRecipientAccount.address);


        } else if (getSActivity().mRecipientChain.equals(BaseChain.KAVA_MAIN)) {
            if (mToSwapDenom.equals(TOKEN_HTLC_BINANCE_BNB) || mToSwapDenom.equals(TOKEN_HTLC_BINANCE_TEST_BNB)) {
                mReceiveAmountDenomTv.setText(getString(R.string.str_bnb_c));
                mRelayFeeAmountDenomTv.setText(getString(R.string.str_bnb_c));
            } else if (mToSwapDenom.equals(TOKEN_HTLC_BINANCE_BTCB) || mToSwapDenom.equals(TOKEN_HTLC_BINANCE_TEST_BTC)) {
                mReceiveAmountDenomTv.setText(getString(R.string.str_btc_c));
                mRelayFeeAmountDenomTv.setText(getString(R.string.str_btc_c));
            } else if (mToSwapDenom.equals(TOKEN_HTLC_BINANCE_XRPB)) {
                mReceiveAmountDenomTv.setText("XRP");
                mRelayFeeAmountDenomTv.setText("XRP");
            } else if (mToSwapDenom.equals(TOKEN_HTLC_BINANCE_BUSD)) {
                mReceiveAmountDenomTv.setText("BUSD");
                mRelayFeeAmountDenomTv.setText("BUSD");
            }
            WDp.DpMainDenom(getContext(), getSActivity().mRecipientChain.getChain(), mClaimFeeDenomTv);

            BigDecimal relayFee = getSActivity().mKavaBep3Param2.getSupportedSwapAssetFee(getSActivity().mToSwapDenom).movePointLeft(8);
            mReceiveAmountTv.setText(WDp.getDpAmount2(getContext(), toSendAmount.subtract(relayFee), 0, 8));
            mRelayFeeAmountTv.setText(WDp.getDpAmount2(getContext(), relayFee, 0, 8));
            mClaimFeeAmountTv.setText(WDp.getDpAmount2(getContext(), claimFeeAmount, 6, 6));
            mClaimAddressTv.setText(getSActivity().mRecipientAccount.address);

        }

    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            Dialog_Htlc_Warning dialog = Dialog_Htlc_Warning.newInstance();
            dialog.setTargetFragment(HtlcSendStep3Fragment.this, SELECT_HTLC_CONFIRM);
            getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_HTLC_CONFIRM && resultCode == Activity.RESULT_OK) {
            getSActivity().onStartHtlcSend();

        }
    }

    private HtlcSendActivity getSActivity() {
        return (HtlcSendActivity)getBaseActivity();
    }
}
