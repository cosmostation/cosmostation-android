package wannabit.io.cosmostaion.fragment.chains.osmosis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseFragment;

public class CoinSwapStep0Fragment extends BaseFragment implements View.OnClickListener{

    private Button              mCancelBtn, mNextBtn;

    private TextView            mSwapAvailAmount, mSwapAvailAmountSymbol;

    private ImageView           mSwapInputImg;
    private TextView            mSwapInputSymbol;
    private EditText            mSwapInputAmount;
    private ImageView           mBtnSwapInputClear;
    private Button              mBtnSwapInput1_4, mBtnSwapInputHalf, mBtnSwapInput3_4, mBtnSwapInputMax;

    private ImageView           mSwapOutputImg;
    private TextView            mSwapOutputSymbol;
    private TextView            mSwapOutputAmount;

    public static CoinSwapStep0Fragment newInstance(Bundle bundle) {
        CoinSwapStep0Fragment fragment = new CoinSwapStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_swap_step0, container, false);
        mCancelBtn                  = rootView.findViewById(R.id.btn_cancel);
        mNextBtn                    = rootView.findViewById(R.id.btn_next);

        mSwapAvailAmount            = rootView.findViewById(R.id.swap_available_amount);
        mSwapAvailAmountSymbol      = rootView.findViewById(R.id.swap_available_amount_symbol);
        mSwapInputImg               = rootView.findViewById(R.id.swap_input_icon);
        mSwapInputSymbol            = rootView.findViewById(R.id.swap_input_symbol);
        mBtnSwapInputClear          = rootView.findViewById(R.id.swap_input_clear);
        mSwapInputAmount            = rootView.findViewById(R.id.swap_input_amount);
        mBtnSwapInput1_4            = rootView.findViewById(R.id.swap_input_1_4);
        mBtnSwapInputHalf           = rootView.findViewById(R.id.swap_input_half);
        mBtnSwapInput3_4            = rootView.findViewById(R.id.swap_input_3_4);
        mBtnSwapInputMax            = rootView.findViewById(R.id.swap_input_max);

        mSwapOutputImg              = rootView.findViewById(R.id.swap_output_icon);
        mSwapOutputSymbol           = rootView.findViewById(R.id.swap_output_symbol);
        mSwapOutputAmount           = rootView.findViewById(R.id.swap_pool_output);

        mBtnSwapInputClear.setOnClickListener(this);
        mBtnSwapInput1_4.setOnClickListener(this);
        mBtnSwapInputHalf.setOnClickListener(this);
        mBtnSwapInput3_4.setOnClickListener(this);
        mBtnSwapInputMax.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

    }
}
