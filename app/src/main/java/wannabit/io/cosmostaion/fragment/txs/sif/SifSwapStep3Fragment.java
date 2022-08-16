package wannabit.io.cosmostaion.fragment.txs.sif;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.sif.SifSwapActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class SifSwapStep3Fragment extends BaseFragment implements View.OnClickListener{
    private TextView        mFeeAmount;
    private TextView        mFeeAmountSymbol;
    private TextView        mSwapFee, mSwapFeeSymbol;
    private TextView        mSwapInAmount, mSwapInAmountSymbol;
    private TextView        mSwapOutAmount, mSwapOutAmountSymbol;
    private RelativeLayout  mSlippageLayer;
    private TextView        mMemo;

    private Button          mBeforeBtn, mConfirmBtn;

    public static SifSwapStep3Fragment newInstance() {
        return new SifSwapStep3Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_swap_step3, container, false);
        mFeeAmount              = rootView.findViewById(R.id.swap_fee_amount);
        mFeeAmountSymbol        = rootView.findViewById(R.id.swap_fee_amount_symbol);
        mSwapFee                = rootView.findViewById(R.id.swap_fee);
        mSwapFeeSymbol          = rootView.findViewById(R.id.swap_fee_symbol);
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
        mSlippageLayer.setVisibility(View.GONE);
        mSwapFeeSymbol.setVisibility(View.VISIBLE);

        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeAmountSymbol, mFeeAmount);
        BigDecimal lpInputAmount = WUtil.getPoolLpAmount(getSActivity().mSifPool, getSActivity().mInputDenom);
        BigDecimal lpOutputAmount = WUtil.getPoolLpAmount(getSActivity().mSifPool, getSActivity().mOutputDenom);
        BigDecimal input = new BigDecimal(getSActivity().mSifSwapInCoin.amount);
        BigDecimal numerator = input.multiply(input).multiply(lpOutputAmount);
        BigDecimal divider = input.add(lpInputAmount);
        BigDecimal denominator = divider.multiply(divider);
        BigDecimal lpFee = numerator.divide(denominator, 0, RoundingMode.DOWN);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mOutputDenom, lpFee.toPlainString(), mSwapFeeSymbol, mSwapFee);

        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mSifSwapInCoin, mSwapInAmountSymbol, mSwapInAmount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mSifSwapOutCoin, mSwapOutAmountSymbol, mSwapOutAmount);
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

    private SifSwapActivity getSActivity() {
        return (SifSwapActivity)getBaseActivity();
    }
}
