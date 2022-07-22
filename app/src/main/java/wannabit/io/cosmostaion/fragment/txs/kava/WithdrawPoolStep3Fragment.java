package wannabit.io.cosmostaion.fragment.txs.kava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.WithDrawPoolActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class WithdrawPoolStep3Fragment extends BaseFragment implements View.OnClickListener{

    private TextView        mFeeAmount;
    private TextView        mFeeSymbol;
    private LinearLayout    mLpLayer;
    private RelativeLayout  mShareLayer;
    private TextView        mExitMyShare;
    private TextView        mExitOutput0Amount, mExitOutput0Symbol;
    private TextView        mExitOutput1Amount, mExitOutput1Symbol;
    private TextView        mMemo;

    private Button          mBeforeBtn, mConfirmBtn;

    public static WithdrawPoolStep3Fragment newInstance(Bundle bundle) {
        WithdrawPoolStep3Fragment fragment = new WithdrawPoolStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exit_pool_step3, container, false);
        mFeeAmount                  = rootView.findViewById(R.id.exit_fee_amount);
        mFeeSymbol                  = rootView.findViewById(R.id.exit_fee_symbol);
        mLpLayer                    = rootView.findViewById(R.id.lp_layer);
        mShareLayer                 = rootView.findViewById(R.id.share_layer);
        mExitMyShare                = rootView.findViewById(R.id.tx_my_share);
        mExitOutput0Amount          = rootView.findViewById(R.id.exit_output0_amount);
        mExitOutput0Symbol          = rootView.findViewById(R.id.exit_output0_symbol);
        mExitOutput1Amount          = rootView.findViewById(R.id.exit_output1_amount);
        mExitOutput1Symbol          = rootView.findViewById(R.id.exit_output1_symbol);
        mMemo                       = rootView.findViewById(R.id.memo);
        mBeforeBtn                  = rootView.findViewById(R.id.btn_before);
        mConfirmBtn                 = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mLpLayer.setVisibility(View.GONE);
        mShareLayer.setVisibility(View.VISIBLE);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeSymbol, mFeeAmount);
        mExitMyShare.setText(WDp.getDpAmount2(getSActivity(), getSActivity().mKavaShareAmount, 6, 6));

        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mKavaPoolTokenA, mExitOutput0Symbol, mExitOutput0Amount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mKavaPoolTokenB, mExitOutput1Symbol, mExitOutput1Amount);

        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartExitPool();
        }
    }

    private WithDrawPoolActivity getSActivity() {
        return (WithDrawPoolActivity)getBaseActivity();
    }
}
