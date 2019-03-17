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
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.UndelegateActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_GasType;
import wannabit.io.cosmostaion.dialog.Dialog_free_fee;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class UndelegateStep2Fragment extends BaseFragment implements View.OnClickListener {

    public final static int SELECT_GAS_DIALOG = 6001;
    public final static int SELECT_FREE_DIALOG = 6002;

    private RelativeLayout mBtnGasType;
    private TextView mTvGasType;
    private TextView        mTvGasAmount;
    private SeekBar mSeekBarGas;
    private Button mBeforeBtn, mNextBtn;

    private Coin mGas = new Coin();
    private ArrayList<String>   mAtomFees = new ArrayList<>();
    private ArrayList<String>   mMuonFees = new ArrayList<>();
    private ArrayList<String>   mPhotinoFees = new ArrayList<>();

    public static UndelegateStep2Fragment newInstance(Bundle bundle) {
        UndelegateStep2Fragment fragment = new UndelegateStep2Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAtomFees = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.atom_fee)));
        mMuonFees = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.muon_fee)));
        mPhotinoFees = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.photino_fee)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_undelegate_step2, container, false);
        mBtnGasType     = rootView.findViewById(R.id.btn_gas_type);
        mTvGasType      = rootView.findViewById(R.id.gas_type);
        mTvGasAmount    = rootView.findViewById(R.id.fee_amount);
        mSeekBarGas     = rootView.findViewById(R.id.gas_price_seekbar);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mBtnGasType.setOnClickListener(this);
        mBeforeBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        if(getSActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
            mGas = new Coin(BaseConstant.COSMOS_ATOM, mAtomFees.get(1));
            mTvGasType.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
            Rect bounds = mSeekBarGas.getProgressDrawable().getBounds();
            mSeekBarGas.setProgressDrawable(getResources().getDrawable(R.drawable.gas_atom_seekbar_style));
            mSeekBarGas.getProgressDrawable().setBounds(bounds);
            mTvGasType.setTextColor(getResources().getColor(R.color.colorAtom));
        } else {
            mGas = new Coin(BaseConstant.COSMOS_PHOTINO, mPhotinoFees.get(1));
            mTvGasType.setText(WDp.DpPoton(getContext(), getSActivity().mAccount.baseChain));
        }

        mSeekBarGas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    if (mGas.denom.equals(BaseConstant.COSMOS_ATOM)) {
                        mGas.amount = mAtomFees.get(progress);
                    } else if (mGas.denom.equals(BaseConstant.COSMOS_MUON)){
                        mGas.amount = mMuonFees.get(progress);
                    } else {
                        mGas.amount = mPhotinoFees.get(progress);
                    }
                    onUpdateGasAmountDp();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        mTvGasType.setText(WDp.DpPoton(getContext(), getSActivity().mAccount.baseChain));
        onUpdateGasAmountDp();
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        super.onRefreshTab();
        if(getSActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain()) &&
                getSActivity().mFreeEvent.contains(getSActivity().mValidator.operator_address)) {
            Dialog_free_fee dialog = Dialog_free_fee.newInstance(null);
            dialog.setCancelable(false);
            dialog.setTargetFragment(this, SELECT_FREE_DIALOG);
            dialog.show(getFragmentManager().beginTransaction(), "dialog");
        }

    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if(getSActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
                Coin gas = mGas;
                gas.amount = new BigDecimal(gas.amount).multiply(new BigDecimal("1000000")).setScale(0).toPlainString();

                Fee fee = new Fee();
                ArrayList<Coin> amount = new ArrayList<>();
                amount.add(gas);
                fee.amount = amount;
                fee.gas = "200000";
                getSActivity().mUnDelegateFee = fee;
                getSActivity().onNextStep();

            } else {
                Fee fee = new Fee();
                ArrayList<Coin> amount = new ArrayList<>();
                amount.add(mGas);
                fee.amount = amount;
                fee.gas = "200000";
                getSActivity().mUnDelegateFee = fee;
                getSActivity().onNextStep();
            }
//            Fee fee = new Fee();
//            ArrayList<Coin> amount = new ArrayList<>();
//            amount.add(mGas);
//            fee.amount = amount;
//            fee.gas = "200000";
//            getSActivity().mUnDelegateFee = fee;
//            getSActivity().onNextStep();

        } else if (v.equals(mBtnGasType)) {
            Bundle bundle = new Bundle();
            bundle.putString("chain", getSActivity().mAccount.baseChain);
            Dialog_GasType dialog = Dialog_GasType.newInstance(bundle);
            dialog.setCancelable(true);
            dialog.setTargetFragment(this, SELECT_GAS_DIALOG);
            dialog.show(getFragmentManager().beginTransaction(), "dialog");
        }
    }

    private void onUpdateGasType(String type) {
        WLog.w("onUpdateGasType : " + type);
        mGas.denom = type;
        if (type.equals(BaseConstant.COSMOS_ATOM)) {
            mGas.amount = mAtomFees.get(mSeekBarGas.getProgress());
            Rect bounds = mSeekBarGas.getProgressDrawable().getBounds();
            mSeekBarGas.setProgressDrawable(getResources().getDrawable(R.drawable.gas_atom_seekbar_style));
            mSeekBarGas.getProgressDrawable().setBounds(bounds);
            mTvGasType.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
            mTvGasType.setTextColor(getResources().getColor(R.color.colorAtom));

        } else if (type.equals(BaseConstant.COSMOS_MUON)) {
            mGas.amount = mMuonFees.get(mSeekBarGas.getProgress());
            Rect bounds = mSeekBarGas.getProgressDrawable().getBounds();
            mSeekBarGas.setProgressDrawable(getResources().getDrawable(R.drawable.gas_atom_seekbar_style));
            mSeekBarGas.getProgressDrawable().setBounds(bounds);
            mTvGasType.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
            mTvGasType.setTextColor(getResources().getColor(R.color.colorPhoton));

        } else if (type.equals(BaseConstant.COSMOS_PHOTINO)) {
            mGas.amount = mPhotinoFees.get(mSeekBarGas.getProgress());
            Rect bounds = mSeekBarGas.getProgressDrawable().getBounds();
            mSeekBarGas.setProgressDrawable(getResources().getDrawable(R.drawable.gas_photon_seekbar_style));
            mSeekBarGas.getProgressDrawable().setBounds(bounds);
            mTvGasType.setText(WDp.DpPoton(getContext(), getSActivity().mAccount.baseChain));
            mTvGasType.setTextColor(getResources().getColor(R.color.colorPhoton));
        }
        onUpdateGasAmountDp();
    }

    private void onUpdateGasAmountDp() {
        WLog.w("onUpdateGasAmountDp : " + mGas.amount + " " + mGas.denom);
        mTvGasAmount.setText(new BigDecimal(mGas.amount).setScale(6).toPlainString());
    }

    private UndelegateActivity getSActivity() {
        return (UndelegateActivity)getBaseActivity();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        WLog.w("onActivityResult : " + requestCode + " " + resultCode);
        if(requestCode == SELECT_GAS_DIALOG && resultCode == Activity.RESULT_OK) {
            onUpdateGasType(data.getStringExtra("coin"));
        } else if(requestCode == SELECT_FREE_DIALOG && resultCode == Activity.RESULT_OK) {
//            Fee fee = new Fee();
//            fee.amount = null;
//            fee.gas = "200000";
//            getSActivity().mUnDelegateFee = fee;
//            getSActivity().onNextStep();

            Fee result = new Fee();
            ArrayList<Coin> amount = new ArrayList<>();
            Coin testCoin = new Coin("uatom", "0");
            amount.add(testCoin);
            result.amount = amount;
            result.gas = "200000";

            getSActivity().mUnDelegateFee = result;
            getSActivity().onNextStep();
        }
    }
}
