package wannabit.io.cosmostaion.fragment.chains.nft;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.nft.NFTSendActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class NFTSendStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mFeeAmount;
    private TextView mFeeAmountSymbol;
    private TextView mNftAddress;
    private TextView mNftDenomId;
    private TextView mNffTokenId;
    private TextView mMemo;
    private int mDpDecimal = 6;

    private Button mBeforeBtn, mConfirmBtn;

    public static NFTSendStep3Fragment newInstance(Bundle bundle) {
        NFTSendStep3Fragment fragment = new NFTSendStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_send_nft_step3, container, false);
        mFeeAmount = rootView.findViewById(R.id.tx_fee_amount);
        mFeeAmountSymbol = rootView.findViewById(R.id.tx_fee_symbol);
        mNftAddress = rootView.findViewById(R.id.nft_recipient_address);
        mNftDenomId = rootView.findViewById(R.id.nft_denom_id);
        mNffTokenId = rootView.findViewById(R.id.nft_token_id);
        mMemo = rootView.findViewById(R.id.memo);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mFeeAmountSymbol);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mDpDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);

        mFeeAmount.setText(WDp.getDpAmount2(feeAmount, mDpDecimal, mDpDecimal));
        mNftAddress.setText(getSActivity().mToAddress);
        mNftDenomId.setText(getSActivity().mNftDenomId);
        mNffTokenId.setText(getSActivity().mNftTokenId);
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onSendNFT();
        }
    }

    private NFTSendActivity getSActivity() {
        return (NFTSendActivity) getBaseActivity();
    }
}
