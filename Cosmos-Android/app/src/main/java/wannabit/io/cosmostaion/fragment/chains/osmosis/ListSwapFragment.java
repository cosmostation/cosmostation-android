package wannabit.io.cosmostaion.fragment.chains.osmosis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import osmosis.gamm.v1beta1.PoolOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.LabsListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.IbcToken;
import wannabit.io.cosmostaion.dialog.Dialog_Swap_Coin_List;
import wannabit.io.cosmostaion.model.type.Coin;

public class ListSwapFragment extends BaseFragment implements View.OnClickListener {
    public final static int SELECT_INPUT_CHAIN = 8500;
    public final static int SELECT_OUTPUT_CHAIN = 8501;

    private RelativeLayout mBtnInputCoinList, mBtnOutputCoinList;
    private ImageView mInputImg;
    private TextView mInputCoin;
    private TextView mSwapPoolId, mSwapInputCoinAmount, mSwapInputCoinSymbol, mSwapOutputCoinAmount, mSwapOutputCoinSymbol, mSwapFee;
    private Button mBtnSwapStart;

    public ArrayList<PoolOuterClass.Pool>       mPoolList = new ArrayList<>();
    public ArrayList<String>                    mAllDenoms = new ArrayList<>();

    public static ListSwapFragment newInstance(Bundle bundle) {
        ListSwapFragment fragment = new ListSwapFragment();
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

        mSwapPoolId                 = rootView.findViewById(R.id.str_token_swap_pool_id);
        mSwapInputCoinAmount        = rootView.findViewById(R.id.token_swap_input_coin_amount);
        mSwapInputCoinSymbol        = rootView.findViewById(R.id.token_swap_input_coin_symbol);
        mSwapOutputCoinAmount       = rootView.findViewById(R.id.token_swap_output_coin_amount);
        mSwapOutputCoinSymbol       = rootView.findViewById(R.id.token_swap_output_coin_symbol);
        mSwapFee                    = rootView.findViewById(R.id.token_swap_fee);
        mBtnSwapStart               = rootView.findViewById(R.id.btn_start_swap);

        mBtnInputCoinList.setOnClickListener(this);
        mBtnOutputCoinList.setOnClickListener(this);
        mBtnSwapStart.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mPoolList = getBaseDao().mPoolList;
        mAllDenoms = getBaseDao().mAllDenoms;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnInputCoinList)) {
            Bundle bundle = new Bundle();
            Dialog_Swap_Coin_List dialog = Dialog_Swap_Coin_List.newInstance(bundle);
            dialog.setTargetFragment(this, SELECT_INPUT_CHAIN);
            getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mBtnOutputCoinList)) {
            Bundle bundle = new Bundle();
            Dialog_Swap_Coin_List dialog = Dialog_Swap_Coin_List.newInstance(bundle);
            dialog.setCancelable(true);
            dialog.setTargetFragment(this, SELECT_OUTPUT_CHAIN);
            getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mBtnSwapStart)) {
            getSActivity().onStartSwap();
        }
    }


    private LabsListActivity getSActivity() { return (LabsListActivity)getBaseActivity(); }
}
