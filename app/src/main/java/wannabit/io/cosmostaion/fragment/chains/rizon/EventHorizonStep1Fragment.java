package wannabit.io.cosmostaion.fragment.chains.rizon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;


import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.rizon.EventHorizonActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.hdac.HdacUtxo;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.hdac.HdacUtil;

public class EventHorizonStep1Fragment extends BaseFragment implements View.OnClickListener{

    private TextView                        mHdacTitle, mRizonTitle;
    private TextView                        mHdacFromAddress, mHdacBurnAmount, mHdacTxFee;
    private TextView                        mRizonToAddress, mRizonMintAmount;

    private RelativeLayout                  mBtnBack;
    private RelativeLayout                  mBtnTokenSwap;

    private HdacUtil                        mHdacUtil;
    private ArrayList<String>               mHdacWords;
    private ArrayList<HdacUtxo>             mHdacUtxo = new ArrayList<>();
    private BigDecimal                      mSendAmount;


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
        mHdacTitle                  = rootView.findViewById(R.id.hdac_title);
        mRizonTitle                 = rootView.findViewById(R.id.rizon_title);

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
        if (getSActivity().mBaseChain.equals(BaseChain.RIZON_TEST)){
            mHdacTitle.setText("Hdac Testnet");
            mRizonTitle.setText("Rizon Testnet");
        } else {
            mHdacTitle.setText("Hdac Mainnet");
            mRizonTitle.setText("Rizon Mainnet");
        }
        mHdacWords = getSActivity().mHdacWords;
        mHdacUtil = new HdacUtil(mHdacWords);
        mHdacUtxo = getSActivity().mHdacUtxo;
        BigDecimal HdacBalance = getSActivity().mHdacBalance;

        mHdacFromAddress.setText(mHdacUtil.getAddress(getSActivity().mBaseChain));
        mHdacBurnAmount.setText("" + WDp.getDpAmount2(getSActivity(), HdacBalance, 8, 8));
        mHdacTxFee.setText("" + WDp.getDpAmount2(getSActivity(), new BigDecimal("0.1"), 0, 8));

        mRizonToAddress.setText(getSActivity().mAccount.address);
        mSendAmount = HdacBalance.subtract(new BigDecimal("0.1").movePointRight(8));
        mRizonMintAmount.setText("" + WDp.getDpAmount2(getSActivity(), mSendAmount, 8, 6));
    }

    private EventHorizonActivity getSActivity() { return (EventHorizonActivity)getBaseActivity(); }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnBack)) {
            getSActivity().onBackPressed();
            return;

        } else if (v.equals(mBtnTokenSwap)) {
            if (mSendAmount.compareTo(BigDecimal.ZERO) < 0) {
                Toast.makeText(getSActivity(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }
            String hdacTxString = mHdacUtil.genSignedTxSwap(getSActivity().mBaseChain, mHdacUtxo, getSActivity().mAccount.address);
            getSActivity().onStartSwap(hdacTxString);
        }
    }
}
