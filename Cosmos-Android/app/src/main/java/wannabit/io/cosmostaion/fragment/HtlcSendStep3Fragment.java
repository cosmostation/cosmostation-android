package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.HtlcSendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_KAVA;

public class HtlcSendStep3Fragment extends BaseFragment implements View.OnClickListener {

    private CardView    mSendCard;
    private TextView    mSendAmountTv, mSendDenomTv;
    private TextView    mSendFeeAmountTv, mSendFeeDenomTv;
    private TextView    mReceiveChainTv, mReceiveAddressTv;
    private CardView    mClaimCard;
    private TextView    mClaimFeeAmountTv, mClaimFeeDenomTv;
    private TextView    mClaimAddressTv;
    private Button      mBeforeBtn, mConfirmBtn;

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
        mSendAmountTv = rootView.findViewById(R.id.send_amount);
        mSendDenomTv = rootView.findViewById(R.id.send_amount_denom);
        mSendFeeAmountTv = rootView.findViewById(R.id.send_fee);
        mSendFeeDenomTv = rootView.findViewById(R.id.send_fee_denom);
        mReceiveChainTv = rootView.findViewById(R.id.recipient_chain);
        mReceiveAddressTv = rootView.findViewById(R.id.recipient_address);
        mClaimCard = rootView.findViewById(R.id.claim_swap_card);
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
        Fee sendFee = getSActivity().onInitSendFee();
        Fee claimFee = getSActivity().onInitClaimFee();


        BigDecimal toSendAmount   = new BigDecimal(getSActivity().mToSendCoins.get(0).amount);
        BigDecimal sendFeeAmount      = new BigDecimal(sendFee.amount.get(0).amount);
        BigDecimal claimFeeAmount      = new BigDecimal(claimFee.amount.get(0).amount);

        // set send card view
        if (getSActivity().mBaseChain.equals(BaseChain.BNB_MAIN) || getSActivity().mBaseChain.equals(BaseChain.BNB_TEST)) {
            mSendCard.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg5));
            WDp.DpMainDenom(getContext(), getSActivity().mBaseChain.getChain(), mSendDenomTv);
            WDp.DpMainDenom(getContext(), getSActivity().mBaseChain.getChain(), mSendFeeDenomTv);

            mSendAmountTv.setText(WDp.getDpAmount2(getContext(), toSendAmount, 0, 8));
            mSendFeeAmountTv.setText(WDp.getDpAmount2(getContext(), sendFeeAmount, 0, 8));

            WDp.onDpChain(getContext(), getSActivity().mRecipientChain, null, mReceiveChainTv);
            mReceiveAddressTv.setText(getSActivity().mRecipientAccount.address);

        } else if (getSActivity().mBaseChain.equals(BaseChain.KAVA_MAIN) || getSActivity().mBaseChain.equals(BaseChain.KAVA_TEST)) {
            mSendCard.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg7));

        }


        //set claim card view
        if (getSActivity().mRecipientChain.equals(BaseChain.BNB_MAIN) || getSActivity().mRecipientChain.equals(BaseChain.BNB_TEST)) {
            mClaimCard.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg5));

        } else if (getSActivity().mRecipientChain.equals(BaseChain.KAVA_MAIN) || getSActivity().mRecipientChain.equals(BaseChain.KAVA_TEST)) {
            mClaimCard.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg7));
            WDp.DpMainDenom(getContext(), getSActivity().mRecipientChain.getChain(), mClaimFeeDenomTv);

            mClaimFeeAmountTv.setText(WDp.getDpAmount2(getContext(), claimFeeAmount, 6, 6));
            mClaimAddressTv.setText(getSActivity().mRecipientAccount.address);

        }

    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
//            getSActivity().onStartDrawDebtCdp();

        }
    }

    private HtlcSendActivity getSActivity() {
        return (HtlcSendActivity)getBaseActivity();
    }
}
