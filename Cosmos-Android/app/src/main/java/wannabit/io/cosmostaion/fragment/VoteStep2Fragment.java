package wannabit.io.cosmostaion.fragment;

import android.graphics.Rect;
import android.os.Build;
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
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.VoteActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class VoteStep2Fragment extends BaseFragment implements View.OnClickListener {

    public final static int SELECT_GAS_DIALOG = 6001;
    public final static int SELECT_FREE_DIALOG = 6002;

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

    private Button mBeforeBtn, mNextBtn;

    private BigDecimal      mAvailable      = BigDecimal.ZERO;      // uatom scale
    private BigDecimal      mFeeAmount      = BigDecimal.ZERO;      // uatom scale
    private BigDecimal      mFeePrice       = BigDecimal.ZERO;

    public static VoteStep2Fragment newInstance(Bundle bundle) {
        VoteStep2Fragment fragment = new VoteStep2Fragment();
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

        if (getSActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            mBtnGasType.setOnClickListener(this);
            mSpeedLayer.setOnClickListener(this);
            Rect bounds = mSeekBarGas.getProgressDrawable().getBounds();
            mSeekBarGas.setProgressDrawable(getResources().getDrawable(R.drawable.gas_atom_seekbar_style));
            mSeekBarGas.getProgressDrawable().setBounds(bounds);
            mTvGasType.setTextColor(getResources().getColor(R.color.colorAtom));
            mSeekBarGas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(fromUser) {
                        onUpdateFeeLayer();
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) { }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    onPayableFee();
                }
            });
            mSeekBarGas.setProgress(0);

        } else if (getSActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            mFeeLayer1.setVisibility(View.GONE);
            mFeeLayer2.setVisibility(View.VISIBLE);
            mFeeLayer3.setVisibility(View.GONE);

            mSpeedImg.setImageDrawable(getResources().getDrawable(R.drawable.fee_img));
            mSpeedMsg.setText(getString(R.string.str_fee_speed_title_iris));

            mGasAmount.setText(BaseConstant.FEE_IRIS_GAS_AMOUNT_VOTE);
            mGasRate.setText(WDp.getDpString(BaseConstant.FEE_IRIS_GAS_RATE_AVERAGE, 6));
            mFeeAmount = new BigDecimal(BaseConstant.FEE_IRIS_GAS_AMOUNT_VOTE).multiply(new BigDecimal(BaseConstant.FEE_IRIS_GAS_RATE_AVERAGE)).movePointRight(18).setScale(0);
            if(getBaseDao().getCurrency() != 5) {
                mFeePrice = WDp.attoToIris(mFeeAmount).multiply(new BigDecimal(""+getBaseDao().getLastIrisTic())).setScale(2, RoundingMode.DOWN);
            } else {
                mFeePrice = WDp.attoToIris(mFeeAmount).multiply(new BigDecimal(""+getBaseDao().getLastIrisTic())).setScale(8, RoundingMode.DOWN);
            }
            mGasFeeAmount.setText(WDp.getDpString(WDp.attoToIris(mFeeAmount).setScale(2, RoundingMode.DOWN).toPlainString(), 3));
            mGasFeePrice.setText(WDp.getPriceApproximatelyDp(getSActivity(), mFeePrice, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));

        } else if (getSActivity().mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            mBtnGasType.setOnClickListener(this);
            mSpeedLayer.setOnClickListener(this);
            Rect bounds = mSeekBarGas.getProgressDrawable().getBounds();
            mSeekBarGas.setProgressDrawable(getResources().getDrawable(R.drawable.gas_kava_seekbar_style));
            mSeekBarGas.getProgressDrawable().setBounds(bounds);
            mTvGasType.setTextColor(getResources().getColor(R.color.colorKava));
            mSeekBarGas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(fromUser) {
                        onUpdateFeeLayer();
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) { }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    onPayableFee();
                }
            });
            mSeekBarGas.setProgress(0);

        }
        return rootView;
    }


    @Override
    public void onRefreshTab() {
        super.onRefreshTab();
        if (getSActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            mAvailable  = getSActivity().mAccount.getAtomBalance();
            onUpdateFeeLayer();

        } else if (getSActivity().mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            mAvailable  = getSActivity().mAccount.getKavaBalance();
            onUpdateFeeLayer();

        }
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if (getSActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                Fee fee = new Fee();
                Coin gasCoin = new Coin();
                gasCoin.denom = BaseConstant.TOKEN_ATOM;
                gasCoin.amount = mFeeAmount.toPlainString();
                ArrayList<Coin> amount = new ArrayList<>();
                amount.add(gasCoin);
                fee.amount = amount;
                fee.gas = BaseConstant.FEE_GAS_AMOUNT_HALF;
                getSActivity().mFee = fee;

            } else if (getSActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                Fee fee = new Fee();
                Coin gasCoin = new Coin();
                gasCoin.denom = BaseConstant.COSMOS_IRIS_ATTO;
                gasCoin.amount = mFeeAmount.toPlainString();
                ArrayList<Coin> amount = new ArrayList<>();
                amount.add(gasCoin);
                fee.amount = amount;
                fee.gas = BaseConstant.FEE_IRIS_GAS_AMOUNT_VOTE;
                getSActivity().mFee = fee;

            } else if (getSActivity().mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                Fee fee = new Fee();
                Coin gasCoin = new Coin();
                gasCoin.denom = BaseConstant.COSMOS_KAVA;
                gasCoin.amount = mFeeAmount.toPlainString();
                ArrayList<Coin> amount = new ArrayList<>();
                amount.add(gasCoin);
                fee.amount = amount;
                fee.gas = BaseConstant.FEE_KAVA_GAS_AMOUNT_LOW;
                getSActivity().mFee = fee;

            }
            getSActivity().onNextStep();

        }
    }

    private void onUpdateFeeLayer() {
        if (getSActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            if (mSeekBarGas.getProgress() == 0) {
                mSpeedImg.setImageDrawable(getResources().getDrawable(R.drawable.bycicle_img));
                mSpeedMsg.setText(getString(R.string.str_fee_speed_title_0));
                mFeeLayer1.setVisibility(View.VISIBLE);
                mFeeLayer2.setVisibility(View.GONE);

                mFeeAmount  = BigDecimal.ZERO;
                if(getBaseDao().getCurrency() != 5) {
                    mFeePrice = WDp.uAtomToAtom(mFeeAmount).multiply(new BigDecimal(""+getBaseDao().getLastAtomTic())).setScale(2, RoundingMode.DOWN);
                } else {
                    mFeePrice = WDp.uAtomToAtom(mFeeAmount).multiply(new BigDecimal(""+getBaseDao().getLastAtomTic())).setScale(8, RoundingMode.DOWN);
                }

                mMinFeeAmount.setText(WDp.getDpString(WDp.uAtomToAtom(mFeeAmount).toPlainString(), 6));
                mMinFeePrice.setText(WDp.getPriceApproximatelyDp(getSActivity(), mFeePrice, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));

            } else if (mSeekBarGas.getProgress() == 1) {
                mSpeedImg.setImageDrawable(getResources().getDrawable(R.drawable.car_img));
                mSpeedMsg.setText(getString(R.string.str_fee_speed_title_1));
                mFeeLayer1.setVisibility(View.GONE);
                mFeeLayer2.setVisibility(View.VISIBLE);

                mGasAmount.setText(BaseConstant.FEE_GAS_AMOUNT_HALF);
                mGasRate.setText(WDp.getDpString(BaseConstant.FEE_GAS_RATE_LOW, 4));

                mFeeAmount = new BigDecimal(BaseConstant.FEE_GAS_AMOUNT_HALF).multiply(new BigDecimal(BaseConstant.FEE_GAS_RATE_LOW)).setScale(0);
                if(getBaseDao().getCurrency() != 5) {
                    mFeePrice = WDp.uAtomToAtom(mFeeAmount).multiply(new BigDecimal(""+getBaseDao().getLastAtomTic())).setScale(2, RoundingMode.DOWN);
                } else {
                    mFeePrice = WDp.uAtomToAtom(mFeeAmount).multiply(new BigDecimal(""+getBaseDao().getLastAtomTic())).setScale(8, RoundingMode.DOWN);
                }

                mGasFeeAmount.setText(WDp.getDpString(WDp.uAtomToAtom(mFeeAmount).toPlainString(), 6));
                mGasFeePrice.setText(WDp.getPriceApproximatelyDp(getSActivity(), mFeePrice, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));


            } else if (mSeekBarGas.getProgress() == 2) {
                mSpeedImg.setImageDrawable(getResources().getDrawable(R.drawable.rocket_img));
                mSpeedMsg.setText(getString(R.string.str_fee_speed_title_2));
                mFeeLayer1.setVisibility(View.GONE);
                mFeeLayer2.setVisibility(View.VISIBLE);

                mGasAmount.setText(BaseConstant.FEE_GAS_AMOUNT_HALF);
                mGasRate.setText(WDp.getDpString(BaseConstant.FEE_GAS_RATE_AVERAGE, 3));

                mFeeAmount = new BigDecimal(BaseConstant.FEE_GAS_AMOUNT_HALF).multiply(new BigDecimal(BaseConstant.FEE_GAS_RATE_AVERAGE)).setScale(0);
                if(getBaseDao().getCurrency() != 5) {
                    mFeePrice = WDp.uAtomToAtom(mFeeAmount).multiply(new BigDecimal(""+getBaseDao().getLastAtomTic())).setScale(2, RoundingMode.DOWN);
                } else {
                    mFeePrice = WDp.uAtomToAtom(mFeeAmount).multiply(new BigDecimal(""+getBaseDao().getLastAtomTic())).setScale(8, RoundingMode.DOWN);
                }

                mGasFeeAmount.setText(WDp.getDpString(WDp.uAtomToAtom(mFeeAmount).toPlainString(), 6));
                mGasFeePrice.setText(WDp.getPriceApproximatelyDp(getSActivity(), mFeePrice, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));
            }

        } else if (getSActivity().mBaseChain.equals(BaseChain.KAVA_MAIN) || getSActivity().mBaseChain.equals(BaseChain.KAVA_TEST)) {
            if (mSeekBarGas.getProgress() == 0) {
                mSpeedImg.setImageDrawable(getResources().getDrawable(R.drawable.bycicle_img));
                mSpeedMsg.setText(getString(R.string.str_fee_speed_title_0));
                mFeeLayer1.setVisibility(View.VISIBLE);
                mFeeLayer2.setVisibility(View.GONE);

                mFeeAmount  = BigDecimal.ZERO;
                if(getBaseDao().getCurrency() != 5) {
                    mFeePrice = WDp.uAtomToAtom(mFeeAmount).multiply(new BigDecimal(""+getBaseDao().getLastKavaTic())).setScale(2, RoundingMode.DOWN);
                } else {
                    mFeePrice = WDp.uAtomToAtom(mFeeAmount).multiply(new BigDecimal(""+getBaseDao().getLastKavaTic())).setScale(8, RoundingMode.DOWN);
                }

                mMinFeeAmount.setText(WDp.getDpString(WDp.uAtomToAtom(mFeeAmount).toPlainString(), 6));
                mMinFeePrice.setText(WDp.getPriceApproximatelyDp(getSActivity(), mFeePrice, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));

            } else if (mSeekBarGas.getProgress() == 1) {
                mSpeedImg.setImageDrawable(getResources().getDrawable(R.drawable.car_img));
                mSpeedMsg.setText(getString(R.string.str_fee_speed_title_1));
                mFeeLayer1.setVisibility(View.GONE);
                mFeeLayer2.setVisibility(View.VISIBLE);

                mGasAmount.setText(BaseConstant.FEE_KAVA_GAS_AMOUNT_LOW);
                mGasRate.setText(WDp.getDpString(BaseConstant.FEE_GAS_RATE_LOW, 4));

                mFeeAmount = new BigDecimal(BaseConstant.FEE_KAVA_GAS_AMOUNT_LOW).multiply(new BigDecimal(BaseConstant.FEE_GAS_RATE_LOW)).setScale(0);
                if(getBaseDao().getCurrency() != 5) {
                    mFeePrice = WDp.uAtomToAtom(mFeeAmount).multiply(new BigDecimal(""+getBaseDao().getLastKavaTic())).setScale(2, RoundingMode.DOWN);
                } else {
                    mFeePrice = WDp.uAtomToAtom(mFeeAmount).multiply(new BigDecimal(""+getBaseDao().getLastKavaTic())).setScale(8, RoundingMode.DOWN);
                }

                mGasFeeAmount.setText(WDp.getDpString(WDp.uAtomToAtom(mFeeAmount).toPlainString(), 6));
                mGasFeePrice.setText(WDp.getPriceApproximatelyDp(getSActivity(), mFeePrice, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));


            } else if (mSeekBarGas.getProgress() == 2) {
                mSpeedImg.setImageDrawable(getResources().getDrawable(R.drawable.rocket_img));
                mSpeedMsg.setText(getString(R.string.str_fee_speed_title_2));
                mFeeLayer1.setVisibility(View.GONE);
                mFeeLayer2.setVisibility(View.VISIBLE);

                mGasAmount.setText(BaseConstant.FEE_KAVA_GAS_AMOUNT_LOW);
                mGasRate.setText(WDp.getDpString(BaseConstant.FEE_GAS_RATE_AVERAGE, 3));

                mFeeAmount = new BigDecimal(BaseConstant.FEE_KAVA_GAS_AMOUNT_LOW).multiply(new BigDecimal(BaseConstant.FEE_GAS_RATE_AVERAGE)).setScale(0);
                if(getBaseDao().getCurrency() != 5) {
                    mFeePrice = WDp.uAtomToAtom(mFeeAmount).multiply(new BigDecimal(""+getBaseDao().getLastKavaTic())).setScale(2, RoundingMode.DOWN);
                } else {
                    mFeePrice = WDp.uAtomToAtom(mFeeAmount).multiply(new BigDecimal(""+getBaseDao().getLastKavaTic())).setScale(8, RoundingMode.DOWN);
                }

                mGasFeeAmount.setText(WDp.getDpString(WDp.uAtomToAtom(mFeeAmount).toPlainString(), 6));
                mGasFeePrice.setText(WDp.getPriceApproximatelyDp(getSActivity(), mFeePrice, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));
            }

        }

    }

    private void onPayableFee() {
        if (mFeeAmount.compareTo(mAvailable) > 0) {
            Toast.makeText(getContext(), getString(R.string.error_not_enough_fee), Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mSeekBarGas.setProgress(0, true);
            } else {
                mSeekBarGas.setProgress(0);
            }
            onUpdateFeeLayer();
        }
    }

    private VoteActivity getSActivity() {
        return (VoteActivity)getBaseActivity();
    }
}
