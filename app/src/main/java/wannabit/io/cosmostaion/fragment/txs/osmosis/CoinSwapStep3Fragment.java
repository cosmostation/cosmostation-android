package wannabit.io.cosmostaion.fragment.txs.osmosis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.osmosis.SwapActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class CoinSwapStep3Fragment extends BaseFragment implements View.OnClickListener{

    private TextView        mFeeAmount;
    private TextView        mFeeAmountSymbol;
    private RelativeLayout  mSwapFeeLayer;
    private TextView        mSwapInAmount, mSwapInAmountSymbol;
    private TextView        mSwapOutAmount, mSwapOutAmountSymbol;
    private RelativeLayout  mSlippageLayer;
    private TextView        mMemo;

    private Button          mBeforeBtn, mConfirmBtn;

    public static CoinSwapStep3Fragment newInstance() {
        return new CoinSwapStep3Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_swap_step3, container, false);
        mSwapFeeLayer           = rootView.findViewById(R.id.swap_fee_layer);
        mFeeAmount              = rootView.findViewById(R.id.swap_fee_amount);
        mFeeAmountSymbol        = rootView.findViewById(R.id.swap_fee_amount_symbol);
        mSwapInAmount           = rootView.findViewById(R.id.swap_in_amount);
        mSwapInAmountSymbol     = rootView.findViewById(R.id.swap_in_amount_symbol);
        mSwapOutAmount          = rootView.findViewById(R.id.swap_out_amount);
        mSwapOutAmountSymbol    = rootView.findViewById(R.id.swap_out_amount_symbol);
        mSlippageLayer          = rootView.findViewById(R.id.slippage_layer);
        mMemo                   = rootView.findViewById(R.id.memo);
        mBeforeBtn              = rootView.findViewById(R.id.btn_before);
        mConfirmBtn             = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mSwapFeeLayer.setVisibility(View.GONE);
        mSlippageLayer.setVisibility(View.GONE);

        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeAmountSymbol, mFeeAmount);
        WDp.setDpCoin(getContext(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mSwapInCoin, mSwapInAmountSymbol, mSwapInAmount);
        WDp.setDpCoin(getContext(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mSwapOutCoin, mSwapOutAmountSymbol, mSwapOutAmount);

        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartSwap();
        }
    }

    private SwapActivity getSActivity() {
        return (SwapActivity)getBaseActivity();
    }
}
