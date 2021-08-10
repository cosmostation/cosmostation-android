package wannabit.io.cosmostaion.fragment.chains.rizon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;


import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.rizon.EventHorizonActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class EventHorizonStep1Fragment extends BaseFragment implements View.OnClickListener{

    private TextView                        mHdacFromAddress, mHdacBurnAmount, mHdacTxFee;
    private TextView                        mRizonToAddress, mRizonMintAmount;

    private RelativeLayout                  mBtnBack;
    private RelativeLayout                  mBtnTokenSwap;


    public static EventHorizonStep1Fragment newInstance(Bundle bundle) {
        EventHorizonStep1Fragment fragment = new EventHorizonStep1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_horizon_step1, container, false);

        mHdacFromAddress            = rootView.findViewById(R.id.burn_from_address);
        mHdacBurnAmount             = rootView.findViewById(R.id.burn_amount);
        mHdacTxFee                  = rootView.findViewById(R.id.horizon_tx_fee_amount);
        mRizonToAddress             = rootView.findViewById(R.id.rizon_to_address);
        mRizonMintAmount            = rootView.findViewById(R.id.rizon_mint_amount);

        mBtnBack                    = rootView.findViewById(R.id.btn_back);
        mBtnTokenSwap               = rootView.findViewById(R.id.btn_token_swap);

        mBtnBack.setOnClickListener(this);
        mBtnTokenSwap.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        super.onRefreshTab();
        String HdacAddress = getSActivity().mHdacAddress;
        BigDecimal HdacBalance = getSActivity().mHdacBalance;

        mHdacFromAddress.setText(HdacAddress);
        mHdacBurnAmount.setText("" + WDp.getDpAmount2(getSActivity(), HdacBalance, 8, 8));
        mHdacTxFee.setText("" + WDp.getDpAmount2(getSActivity(), new BigDecimal("0.1"), 0, 8));

        mRizonToAddress.setText(getSActivity().mAccount.address);
//        mRizonMintAmount.setText("" + WDp.getDpAmount2(getSActivity());
    }

    private EventHorizonActivity getSActivity() { return (EventHorizonActivity)getBaseActivity(); }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnBack)) {
            getSActivity().onBackPressed();
            return;

        } else if (v.equals(mBtnTokenSwap)) {
            getSActivity();
            return;

        }
    }
}
