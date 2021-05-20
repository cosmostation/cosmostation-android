package wannabit.io.cosmostaion.fragment.chains.ok;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.ok.OKUnbondingActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.OK_GAS_AMOUNT_STAKE_MUX;
import static wannabit.io.cosmostaion.base.BaseConstant.OK_GAS_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK;

public class OKUnbondingFragment2 extends BaseFragment implements View.OnClickListener {

    private RelativeLayout mBtnGasType;
    private TextView        mTvGasType;

    private LinearLayout    mFeeLayer1;
    private TextView        mMinFeeAmount;
    private TextView        mMinFeePrice;

    private LinearLayout    mFeeLayer2;
    private TextView        mGasAmount;
    private TextView        mGasRate;
    private TextView        mGasFeeAmount;
    private TextView        mGasFeePrice;

    private LinearLayout    mFeeLayer3;
    private SeekBar         mSeekBarGas;

    private LinearLayout    mSpeedLayer;
    private ImageView       mSpeedImg;
    private TextView        mSpeedMsg;

    private Button          mBeforeBtn, mNextBtn;

    private BigDecimal      mAvailable      = BigDecimal.ZERO;
    private BigDecimal      mFeeAmount      = BigDecimal.ZERO;
    private BigDecimal      mFeePrice       = BigDecimal.ZERO;
    private BigDecimal      mEstimateGasAmount  = BigDecimal.ZERO;

    public static OKUnbondingFragment2 newInstance(Bundle bundle) {
        OKUnbondingFragment2 fragment = new OKUnbondingFragment2();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tx_step_fee, container, false);
        mBtnGasType     = rootView.findViewById(R.id.btn_gas_type);
        mTvGasType      = rootView.findViewById(R.id.gas_type);

        mFeeLayer1      = rootView.findViewById(R.id.fee_dp_layer1);
        mMinFeeAmount   = rootView.findViewById(R.id.min_fee_amount);
        mMinFeePrice    = rootView.findViewById(R.id.min_fee_price);

        mFeeLayer2      = rootView.findViewById(R.id.fee_dp_layer2);
        mGasAmount      = rootView.findViewById(R.id.gas_amount);
        mGasRate        = rootView.findViewById(R.id.gas_rate);
        mGasFeeAmount   = rootView.findViewById(R.id.gas_fee);
        mGasFeePrice    = rootView.findViewById(R.id.gas_fee_price);

        mFeeLayer3      = rootView.findViewById(R.id.fee_dp_layer3);
        mSeekBarGas     = rootView.findViewById(R.id.gas_price_seekbar);

        mSpeedLayer     = rootView.findViewById(R.id.speed_layer);
        mSpeedImg       = rootView.findViewById(R.id.speed_img);
        mSpeedMsg       = rootView.findViewById(R.id.speed_txt);

        mBeforeBtn      = rootView.findViewById(R.id.btn_before);
        mNextBtn        = rootView.findViewById(R.id.btn_next);
        mBeforeBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mTvGasType);

        if (getSActivity().mBaseChain.equals(OKEX_MAIN) || getSActivity().mBaseChain.equals(OK_TEST)) {
            mFeeLayer1.setVisibility(View.GONE);
            mFeeLayer2.setVisibility(View.VISIBLE);
            mFeeLayer3.setVisibility(View.GONE);

            mSpeedImg.setImageDrawable(getResources().getDrawable(R.drawable.fee_img));
            mSpeedMsg.setText(getString(R.string.str_fee_speed_title_ok));

            int myValidatorCnt = 0;
            if (getBaseDao().mOkStaking != null && getBaseDao().mOkStaking.validator_address != null) {
                myValidatorCnt = getBaseDao().mOkStaking.validator_address.size();
            }
            mEstimateGasAmount = (new BigDecimal(OK_GAS_AMOUNT_STAKE_MUX).multiply(new BigDecimal(""+myValidatorCnt))).add(new BigDecimal(BaseConstant.OK_GAS_AMOUNT_STAKE));
            mGasAmount.setText(mEstimateGasAmount.toPlainString());
            mGasRate.setText(WDp.getDpString(OK_GAS_RATE_AVERAGE, 9));
            mFeeAmount = mEstimateGasAmount.multiply(new BigDecimal(OK_GAS_RATE_AVERAGE)).setScale(18);

            mGasFeeAmount.setText(WDp.getDpAmount2(getContext(), mFeeAmount, 0, 6));
            mGasFeePrice.setText(WDp.getPriceApproximatelyDp(getSActivity(), BigDecimal.ZERO, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));

        }
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        super.onRefreshTab();
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if (getSActivity().mBaseChain.equals(OKEX_MAIN) || getSActivity().mBaseChain.equals(OK_TEST)) {
                Fee fee = new Fee();
                Coin gasCoin = new Coin();
                gasCoin.denom = TOKEN_OK;
                gasCoin.amount = mFeeAmount.toPlainString();
                ArrayList<Coin> amount = new ArrayList<>();
                amount.add(gasCoin);
                fee.amount = amount;
                fee.gas = mEstimateGasAmount.toPlainString();
                getSActivity().mFee = fee;

            }
            getSActivity().onNextStep();
        }

    }

    private OKUnbondingActivity getSActivity() {
        return (OKUnbondingActivity)getBaseActivity();
    }
}