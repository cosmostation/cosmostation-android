package wannabit.io.cosmostaion.fragment.chains.sif;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.sif.SifDexListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;

public class SifDexSwapFragment extends BaseFragment implements View.OnClickListener{

    private RelativeLayout  mBtnInputCoinList, mBtnOutputCoinList;
    private ImageView       mInputImg;
    private TextView        mInputCoin, mInputAmount;
    private TextView        mSwapFee, mSwapSlippage;
    private TextView        mSwapTitle;
    private ImageView       mOutputImg;
    private TextView        mOutputCoin;
    private TextView        mSwapInputCoinRate, mSwapInputCoinSymbol, mSwapOutputCoinRate, mSwapOutputCoinSymbol;
    private TextView        mSwapInputCoinExRate, mSwapInputCoinExSymbol, mSwapOutputCoinExRate, mSwapOutputCoinExSymbol;

    private ImageButton     mBtnToggle;
    private Button          mBtnSwapStart;

    public String                               mInputCoinDenom;
    public String                               mOutputCoinDenom;
    public BigDecimal                           mPoolSwapRate;
    public int                                  mInPutDecimal = 18;
    public int                                  mOutPutDecimal = 18;

    public static SifDexSwapFragment newInstance(Bundle bundle) {
        SifDexSwapFragment fragment = new SifDexSwapFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_swap_list, container, false);
        mBtnInputCoinList           = rootView.findViewById(R.id.btn_to_input_coin);
        mBtnOutputCoinList          = rootView.findViewById(R.id.btn_to_output_coin);

        mInputImg                   = rootView.findViewById(R.id.img_input_coin);
        mInputCoin                  = rootView.findViewById(R.id.txt_input_coin);
        mInputAmount                = rootView.findViewById(R.id.inpus_amount);
        mOutputImg                  = rootView.findViewById(R.id.img_output_coin);
        mOutputCoin                 = rootView.findViewById(R.id.txt_output_coin);

        mSwapTitle                  = rootView.findViewById(R.id.swap_title);
        mSwapInputCoinRate          = rootView.findViewById(R.id.inputs_rate);
        mSwapInputCoinSymbol        = rootView.findViewById(R.id.inputs_rate_symbol);
        mSwapOutputCoinRate         = rootView.findViewById(R.id.outputs_rate);
        mSwapOutputCoinSymbol       = rootView.findViewById(R.id.outputs_rate_symbol);

        mSwapInputCoinExRate        = rootView.findViewById(R.id.global_inputs_rate);
        mSwapInputCoinExSymbol      = rootView.findViewById(R.id.global_inputs_rate_symbol);
        mSwapOutputCoinExRate       = rootView.findViewById(R.id.global_outputs_rate);
        mSwapOutputCoinExSymbol     = rootView.findViewById(R.id.global_outputs_rate_symbol);

        mSwapFee                    = rootView.findViewById(R.id.token_swap_fee);
        mSwapSlippage               = rootView.findViewById(R.id.swap_slippage);
        mBtnToggle                  = rootView.findViewById(R.id.btn_toggle);
        mBtnSwapStart               = rootView.findViewById(R.id.btn_start_swap);

        mBtnInputCoinList.setOnClickListener(this);
        mBtnOutputCoinList.setOnClickListener(this);
        mBtnToggle.setOnClickListener(this);
        mBtnSwapStart.setOnClickListener(this);

        mBtnToggle.setBackgroundTintList(getResources().getColorStateList(R.color.colorSif));
        mSwapTitle.setText(getString(R.string.str_sif_swap_rate));
        return rootView;
    }

    @Override
    public void onClick(View v) {

    }

    private SifDexListActivity getSActivity() { return (SifDexListActivity)getBaseActivity(); }
}

