package wannabit.io.cosmostaion.fragment.txs.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.IBCSendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.utils.WDp;

public class IBCSendStep4Fragment extends BaseFragment implements View.OnClickListener{

    private TextView        mFeeAmount;
    private TextView        mFeeAmountSymbol;
    private TextView        mSendAmount;
    private TextView        mSendAmountSymbol;
    private TextView        mRecipientChain;
    private TextView        mRecipientAddress;

    private Button mBeforeBtn, mConfirmBtn;

    public static IBCSendStep4Fragment newInstance(Bundle bundle) {
        IBCSendStep4Fragment fragment = new IBCSendStep4Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ibc_send_step4, container, false);
        mFeeAmount              = rootView.findViewById(R.id.send_fees);
        mFeeAmountSymbol        = rootView.findViewById(R.id.send_fees_type);
        mSendAmount             = rootView.findViewById(R.id.send_amount);
        mSendAmountSymbol       = rootView.findViewById(R.id.send_amount_symbol);
        mRecipientChain         = rootView.findViewById(R.id.send_recipient_chain);
        mRecipientAddress       = rootView.findViewById(R.id.send_recipient_address);
        mBeforeBtn              = rootView.findViewById(R.id.btn_before);
        mConfirmBtn             = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        BigDecimal toSendAmount = new BigDecimal(getSActivity().mAmounts.get(0).amount);

        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeAmountSymbol, mFeeAmount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mToIbcDenom, toSendAmount.toPlainString(), mSendAmountSymbol, mSendAmount);

        BaseChain toChain = WDp.getChainTypeByChainId(getSActivity().mIbcSelectedRelayer.chain_id);
        ChainConfig chainConfig = ChainFactory.getChain(toChain);
        mRecipientChain.setText(chainConfig.chainTitle());
        mRecipientChain.setTextColor(ContextCompat.getColor(getSActivity(), chainConfig.chainColor()));
        mRecipientAddress.setText(getSActivity().mToAddress);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
//            getSActivity().onStartIbcSend();
        }
    }

    private IBCSendActivity getSActivity() {
        return (IBCSendActivity)getBaseActivity();
    }
}
