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
import wannabit.io.cosmostaion.activities.chains.nft.NFTCreateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.utils.WDp;

public class NFTCreateStep3Fragment extends BaseFragment implements View.OnClickListener, IRefreshTabListener {

    private TextView mFeeAmount;
    private TextView mFeeAmountSymbol;
    private TextView mNftName;
    private TextView mNftDescription;
    private TextView mNftUri;
    private TextView mNftId;
    private TextView mMemo;
    private int mDpDecimal = 6;

    private Button mBeforeBtn, mConfirmBtn;

    public static NFTCreateStep3Fragment newInstance(Bundle bundle) {
        NFTCreateStep3Fragment fragment = new NFTCreateStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_issue_nft_step3, container, false);
        mFeeAmount = rootView.findViewById(R.id.tx_fee_amount);
        mFeeAmountSymbol = rootView.findViewById(R.id.tx_fee_symbol);
        mNftName = rootView.findViewById(R.id.nft_name);
        mNftDescription = rootView.findViewById(R.id.nft_description);
        mNftUri = rootView.findViewById(R.id.nft_uri);
        mNftId = rootView.findViewById(R.id.nft_id);
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
        mNftName.setText(getSActivity().mNftName);
        mNftDescription.setText(getSActivity().mNftDescription);
        mNftUri.setText(getSActivity().mNftHash);
        mNftId.setText(getSActivity().mNftHash);
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onCreateNFT();
        }
    }

    private NFTCreateActivity getSActivity() {
        return (NFTCreateActivity) getBaseActivity();
    }
}
