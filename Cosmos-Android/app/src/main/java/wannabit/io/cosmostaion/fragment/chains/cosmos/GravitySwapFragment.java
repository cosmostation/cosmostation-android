package wannabit.io.cosmostaion.fragment.chains.cosmos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import osmosis.gamm.v1beta1.PoolOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseFragment;

public class GravitySwapFragment extends BaseFragment implements View.OnClickListener{
    public final static int SELECT_INPUT_CHAIN = 8500;
    public final static int SELECT_OUTPUT_CHAIN = 8501;

    private RelativeLayout  mBtnInputCoinList, mBtnOutputCoinList;
    private ImageView       mInputImg;
    private TextView        mInputCoin, mInputAmount;
    private TextView        mSwapFee;
    private ImageView       mOutputImg;
    private TextView        mOutputCoin;
    private TextView        mSwapInputCoinRate, mSwapInputCoinSymbol, mSwapOutputCoinRate, mSwapOutputCoinSymbol;
    private TextView        mSwapInputCoinExRate, mSwapInputCoinExSymbol, mSwapOutputCoinExRate, mSwapOutputCoinExSymbol;

    private FloatingActionButton    mBtnToggle;
    private Button                  mBtnSwapStart;

    public ArrayList<gravity.v1.Pool>           mPoolList = new ArrayList<>();
    public ArrayList<String>                    mAllDenoms = new ArrayList<>();
    public ArrayList<PoolOuterClass.Pool>       mSwapablePools = new ArrayList<>();
    public ArrayList<String>                    mSwapableDenoms = new ArrayList<>();
    public PoolOuterClass.Pool                  mSelectedPool;
    public String                               mInputCoinDenom;
    public String                               mOutputCoinDenom;

    public static GravitySwapFragment newInstance(Bundle bundle) {
        GravitySwapFragment fragment = new GravitySwapFragment();
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

        mSwapInputCoinRate          = rootView.findViewById(R.id.inputs_rate);
        mSwapInputCoinSymbol        = rootView.findViewById(R.id.inputs_rate_symbol);
        mSwapOutputCoinRate         = rootView.findViewById(R.id.outputs_rate);
        mSwapOutputCoinSymbol       = rootView.findViewById(R.id.outputs_rate_symbol);

        mSwapInputCoinExRate        = rootView.findViewById(R.id.global_inputs_rate);
        mSwapInputCoinExSymbol      = rootView.findViewById(R.id.global_inputs_rate_symbol);
        mSwapOutputCoinExRate       = rootView.findViewById(R.id.global_outputs_rate);
        mSwapOutputCoinExSymbol     = rootView.findViewById(R.id.global_outputs_rate_symbol);

        mSwapFee                    = rootView.findViewById(R.id.token_swap_fee);
        mBtnToggle                  = rootView.findViewById(R.id.btn_toggle);
        mBtnSwapStart               = rootView.findViewById(R.id.btn_start_swap);

        mBtnInputCoinList.setOnClickListener(this);
        mBtnOutputCoinList.setOnClickListener(this);
        mBtnToggle.setOnClickListener(this);
        mBtnSwapStart.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

    }
}
