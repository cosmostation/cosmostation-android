package wannabit.io.cosmostaion.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.ClaimRewardActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_GasType;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class RewardStep2Fragment extends BaseFragment implements View.OnClickListener {

    public final static int SELECT_GAS_DIALOG = 6001;
    public final static int SELECT_FREE_DIALOG = 6002;

    private RelativeLayout  mBtnGasType;
    private TextView        mTvGasType;
    private TextView        mTvGasAmount;
    private TextView        mTvGasPrice;
    private SeekBar         mSeekBarGas;
    private LinearLayout    mNotice;
    private Button          mBeforeBtn, mNextBtn;

    private BigDecimal      mAvailable      = BigDecimal.ZERO;
    private BigDecimal      mGasAmount      = BigDecimal.ZERO;
    private BigDecimal      mGasMinFee      = BigDecimal.ZERO;
    private BigDecimal      mFeeAmount      = BigDecimal.ZERO;


    public static RewardStep2Fragment newInstance(Bundle bundle) {
        RewardStep2Fragment fragment = new RewardStep2Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reward_step2, container, false);
        mBtnGasType     = rootView.findViewById(R.id.btn_gas_type);
        mTvGasType      = rootView.findViewById(R.id.gas_type);
        mTvGasAmount    = rootView.findViewById(R.id.fee_amount);
        mTvGasPrice     = rootView.findViewById(R.id.fee_price);
        mSeekBarGas     = rootView.findViewById(R.id.gas_price_seekbar);
        mNotice         = rootView.findViewById(R.id.multi_notice_layer);

        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mBtnGasType.setOnClickListener(this);
        mBeforeBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        mTvGasType.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
        Rect bounds = mSeekBarGas.getProgressDrawable().getBounds();
        mSeekBarGas.setProgressDrawable(getResources().getDrawable(R.drawable.gas_atom_seekbar_style));
        mSeekBarGas.getProgressDrawable().setBounds(bounds);
        mTvGasType.setTextColor(getResources().getColor(R.color.colorAtom));

        mSeekBarGas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    onUpdateGasAmountDp();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        mSeekBarGas.setProgress(0);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        super.onRefreshTab();
        mAvailable  = getSActivity().mAccount.getAtomBalance();
        ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.gas_multi_reward)));
        mGasAmount = new BigDecimal(rewardGasFees.get(getSActivity().mValidators.size() - 1));
        BigDecimal mGasConstant = new BigDecimal(getString(R.string.gas_constant));
        mGasMinFee = mGasConstant.multiply(mGasAmount).setScale(0);

        WLog.w("need mGasAmount : "+ mGasAmount.toPlainString());
        WLog.w("need mGasConstant : "+ mGasConstant.toPlainString());
        WLog.w("mAvailable : "+ mAvailable.toPlainString());
        WLog.w("Total min : "+ mGasConstant.multiply(mGasAmount).setScale(0).toPlainString());
        onUpdateGasAmountDp();

    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            Fee fee = new Fee();
            ArrayList<Coin> amount = new ArrayList<>();
            Coin gasCoin = new Coin();
            gasCoin.amount = mFeeAmount.toPlainString();
            gasCoin.denom = BaseConstant.COSMOS_ATOM;
            amount.add(gasCoin);
            fee.amount = amount;
            fee.gas = mGasAmount.toPlainString();
            getSActivity().mRewardFee = fee;
            getSActivity().onNextStep();


        } else if (v.equals(mBtnGasType)) {
            Toast.makeText(getContext(), getString(R.string.error_only_atom_for_fee), Toast.LENGTH_SHORT).show();
//            if(getSActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
//                return;
//            }
//            Bundle bundle = new Bundle();
//            bundle.putString("chain", getSActivity().mAccount.baseChain);
//            Dialog_GasType dialog = Dialog_GasType.newInstance(bundle);
//            dialog.setCancelable(true);
//            dialog.setTargetFragment(this, SELECT_GAS_DIALOG);
//            dialog.show(getFragmentManager().beginTransaction(), "dialog");
        }
    }

    private void onUpdateGasAmountDp() {
        mFeeAmount = mGasMinFee.multiply(new BigDecimal(mSeekBarGas.getProgress() + 1)).setScale(0);
        WLog.w("user mFeeAmount : " + mFeeAmount);

        if(mFeeAmount.compareTo(mAvailable) > 0) {
            Toast.makeText(getContext(), getString(R.string.error_not_enough_fee), Toast.LENGTH_SHORT).show();
            mSeekBarGas.setProgress(mSeekBarGas.getProgress() - 1);
            onUpdateGasAmountDp();
        }
        mTvGasAmount.setText(mFeeAmount.divide(new BigDecimal("1000000"), 6, BigDecimal.ROUND_HALF_UP).setScale(6).toPlainString());
        BigDecimal total = new BigDecimal(""+mTvGasAmount.getText().toString().trim()).multiply(new BigDecimal(""+getBaseDao().getLastAtomTic())).setScale(2, RoundingMode.DOWN);
        mTvGasPrice.setText(getString(R.string.str_approximately)+ " $" +  WDp.getDolor(getContext(), total));

    }

    private ClaimRewardActivity getSActivity() {
        return (ClaimRewardActivity)getBaseActivity();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == SELECT_FREE_DIALOG && resultCode == Activity.RESULT_OK) {
            Fee result = new Fee();
            ArrayList<Coin> amount = new ArrayList<>();
            Coin testCoin = new Coin("uatom", "0");
            amount.add(testCoin);
            result.amount = amount;
            result.gas = "200000";
            getSActivity().mRewardFee = result;
            getSActivity().onNextStep();
        }
    }

}
