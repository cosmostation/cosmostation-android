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
import wannabit.io.cosmostaion.activities.DelegateActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_GasType;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class DelegateStep2Fragment extends BaseFragment implements View.OnClickListener {

    public final static int SELECT_GAS_DIALOG = 6001;

    private RelativeLayout mBtnGasType;
    private TextView mTvGasType;
    private TextView        mTvGasAmount;
    private SeekBar         mSeekBarGas;
    private Button mBeforeBtn, mNextBtn;

    private Coin                mGas = new Coin();
    private ArrayList<String>   mAtomFees = new ArrayList<>();
    private ArrayList<String>   mPhotonFees = new ArrayList<>();

    public static DelegateStep2Fragment newInstance(Bundle bundle) {
        DelegateStep2Fragment fragment = new DelegateStep2Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAtomFees = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.atom_fee)));
        mPhotonFees = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.photon_fee)));
        mGas = new Coin(BaseConstant.COSMOS_PHOTON, mPhotonFees.get(1));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_delegate_step2, container, false);
        mBtnGasType     = rootView.findViewById(R.id.btn_gas_type);
        mTvGasType      = rootView.findViewById(R.id.gas_type);
        mTvGasAmount    = rootView.findViewById(R.id.fee_amount);
        mSeekBarGas     = rootView.findViewById(R.id.gas_price_seekbar);

        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mBtnGasType.setOnClickListener(this);
        mBeforeBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);


        mSeekBarGas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    if (mGas.denom.equals(BaseConstant.COSMOS_ATOM)) {
                        mGas.amount = mAtomFees.get(progress);
                    } else {
                        mGas.amount = mPhotonFees.get(progress);
                    }
                    onUpdateGasAmountDp();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        onUpdateGasAmountDp();
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            Fee fee = new Fee();
            ArrayList<Coin> amount = new ArrayList<>();
            amount.add(mGas);
            fee.amount = amount;
            fee.gas = "200000";
            getSActivity().mToDelegateFee = fee;
            getSActivity().onNextStep();

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
        } else if (type.equals(BaseConstant.COSMOS_PHOTON)) {
            mGas.amount = mPhotonFees.get(mSeekBarGas.getProgress());
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
        mTvGasAmount.setText(WDp.getDpAmount(getContext(), new BigDecimal(mGas.amount), 6));
    }

    private DelegateActivity getSActivity() {
        return (DelegateActivity)getBaseActivity();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        WLog.w("onActivityResult : " + requestCode + " " + resultCode);
        if(requestCode == SELECT_GAS_DIALOG && resultCode == Activity.RESULT_OK) {
            onUpdateGasType(data.getStringExtra("coin"));
        }
    }
}
