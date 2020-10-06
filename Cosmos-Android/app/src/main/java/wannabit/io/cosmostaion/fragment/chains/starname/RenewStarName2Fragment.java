package wannabit.io.cosmostaion.fragment.chains.starname;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.starname.ReNewStarNameActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_IOV_GAS_AMOUNT_RENEW;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_IOV_GAS_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV_TEST;

public class RenewStarName2Fragment extends BaseFragment implements View.OnClickListener {

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

    private BigDecimal      mFeeAmount      = BigDecimal.ZERO;      // uatom scale
    private BigDecimal      mFeePrice       = BigDecimal.ZERO;

    public static RenewStarName2Fragment newInstance(Bundle bundle) {
        RenewStarName2Fragment fragment = new RenewStarName2Fragment();
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

        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mBeforeBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mTvGasType);

        if (getSActivity().mBaseChain.equals(IOV_MAIN) || getSActivity().mBaseChain.equals(IOV_TEST)) {
            mFeeLayer1.setVisibility(View.VISIBLE);
            mFeeLayer2.setVisibility(View.GONE);
            mFeeLayer3.setVisibility(View.GONE);

            mSpeedImg.setImageDrawable(getResources().getDrawable(R.drawable.fee_img));
            mSpeedMsg.setText(getString(R.string.str_fee_speed_title_iov));

            mFeeAmount = new BigDecimal(FEE_IOV_GAS_AMOUNT_RENEW).multiply(new BigDecimal(FEE_IOV_GAS_RATE_AVERAGE)).setScale(0);
            if(getBaseDao().getCurrency() != 5) {
                mFeePrice = WDp.uAtomToAtom(mFeeAmount).multiply(new BigDecimal(""+getBaseDao().getLastIovTic())).setScale(2, RoundingMode.DOWN);
            } else {
                mFeePrice = WDp.uAtomToAtom(mFeeAmount).multiply(new BigDecimal(""+getBaseDao().getLastIovTic())).setScale(8, RoundingMode.DOWN);
            }
            mMinFeeAmount.setText(WDp.getDpAmount2(getContext(), mFeeAmount, 6, 6));
            mMinFeePrice.setText(WDp.getPriceApproximatelyDp(getSActivity(), mFeePrice, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));

        }
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if (getSActivity().mBaseChain.equals(IOV_MAIN)) {
                Fee fee = new Fee();
                Coin gasCoin = new Coin();
                gasCoin.denom = TOKEN_IOV;
                gasCoin.amount = mFeeAmount.toPlainString();
                ArrayList<Coin> amount = new ArrayList<>();
                amount.add(gasCoin);
                fee.amount = amount;
                fee.gas = FEE_IOV_GAS_AMOUNT_RENEW;
                getSActivity().mFee = fee;

            } else if (getSActivity().mBaseChain.equals(IOV_TEST)) {
                Fee fee = new Fee();
                Coin gasCoin = new Coin();
                gasCoin.denom = TOKEN_IOV_TEST;
                gasCoin.amount = mFeeAmount.toPlainString();
                ArrayList<Coin> amount = new ArrayList<>();
                amount.add(gasCoin);
                fee.amount = amount;
                fee.gas = FEE_IOV_GAS_AMOUNT_RENEW;
                getSActivity().mFee = fee;

            }
            getSActivity().onNextStep();
        }
    }


    private ReNewStarNameActivity getSActivity() {
        return (ReNewStarNameActivity)getBaseActivity();
    }
}
