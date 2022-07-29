package wannabit.io.cosmostaion.fragment.txs.sif;

import android.app.Activity;
import android.content.Intent;
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
import androidx.core.content.ContextCompat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import sifnode.clp.v1.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.sif.SifDexListActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.SwapCoinListDialog;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class SifDexSwapFragment extends BaseFragment implements View.OnClickListener {
    public final static int SELECT_INPUT_CHAIN = 8500;
    public final static int SELECT_OUTPUT_CHAIN = 8501;

    private RelativeLayout mBtnInputCoinList, mBtnOutputCoinList;
    private ImageView mInputImg;
    private TextView mInputCoin, mInputAmount;
    private TextView mSwapSlippage;
    private TextView mSwapTitle;
    private ImageView mOutputImg;
    private TextView mOutputCoin;
    private TextView mSwapInputCoinRate, mSwapInputCoinSymbol, mSwapOutputCoinRate, mSwapOutputCoinSymbol;
    private TextView mSwapInputCoinExRate, mSwapInputCoinExSymbol, mSwapOutputCoinExRate, mSwapOutputCoinExSymbol;

    private ImageButton mBtnToggle;
    private Button mBtnSwapStart;

    public ArrayList<Types.Pool> mPoolList = new ArrayList<>();
    public ArrayList<String> mAllDenoms = new ArrayList<>();
    public Types.Pool mSelectedPool;
    public ArrayList<String> mSwapableDenoms = new ArrayList<>();

    public String mInputCoinDenom;
    public String mOutputCoinDenom;
    public int mInPutDecimal = 18;
    public int mOutPutDecimal = 18;

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
        mBtnInputCoinList = rootView.findViewById(R.id.btn_to_input_coin);
        mBtnOutputCoinList = rootView.findViewById(R.id.btn_to_output_coin);

        mInputImg = rootView.findViewById(R.id.img_input_coin);
        mInputCoin = rootView.findViewById(R.id.txt_input_coin);
        mInputAmount = rootView.findViewById(R.id.inpus_amount);
        mOutputImg = rootView.findViewById(R.id.img_output_coin);
        mOutputCoin = rootView.findViewById(R.id.txt_output_coin);

        mSwapTitle = rootView.findViewById(R.id.swap_title);
        mSwapInputCoinRate = rootView.findViewById(R.id.inputs_rate);
        mSwapInputCoinSymbol = rootView.findViewById(R.id.inputs_rate_symbol);
        mSwapOutputCoinRate = rootView.findViewById(R.id.outputs_rate);
        mSwapOutputCoinSymbol = rootView.findViewById(R.id.outputs_rate_symbol);

        mSwapInputCoinExRate = rootView.findViewById(R.id.global_inputs_rate);
        mSwapInputCoinExSymbol = rootView.findViewById(R.id.global_inputs_rate_symbol);
        mSwapOutputCoinExRate = rootView.findViewById(R.id.global_outputs_rate);
        mSwapOutputCoinExSymbol = rootView.findViewById(R.id.global_outputs_rate_symbol);

        mSwapSlippage = rootView.findViewById(R.id.swap_slippage);
        mBtnToggle = rootView.findViewById(R.id.btn_toggle);
        mBtnSwapStart = rootView.findViewById(R.id.btn_start_swap);

        mBtnInputCoinList.setOnClickListener(this);
        mBtnOutputCoinList.setOnClickListener(this);
        mBtnToggle.setOnClickListener(this);
        mBtnSwapStart.setOnClickListener(this);

        mBtnToggle.setBackgroundTintList(ContextCompat.getColorStateList(getSActivity(), R.color.color_sif));
        mSwapTitle.setText(getString(R.string.str_sif_swap_rate));
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mPoolList = getSActivity().mPoolList;
        mAllDenoms = getSActivity().mAllDenoms;

        if (mSelectedPool == null || mInputCoinDenom.isEmpty() || mOutputCoinDenom.isEmpty()) {
            if (mPoolList != null && mPoolList.size() > 0) {
                mSelectedPool = mPoolList.get(0);
                mInputCoinDenom = WDp.mainDenom(getSActivity().mBaseChain);
                mOutputCoinDenom = mSelectedPool.getExternalAsset().getSymbol();
            }
        }
        if (mInputCoinDenom != null && mOutputCoinDenom != null) {
            onUpdateView();
        }
    }

    private void onUpdateView() {
        mInPutDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, mInputCoinDenom);
        mOutPutDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, mOutputCoinDenom);

        mInputAmount.setText(WDp.getDpAmount2(getSActivity(), getBaseDao().getAvailable(mInputCoinDenom), mInPutDecimal, mInPutDecimal));
        mSwapSlippage.setText(WDp.getPercentDp(new BigDecimal("2")));

        WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, mInputCoinDenom, mInputImg);
        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mInputCoinDenom, mInputCoin);
        WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, mOutputCoinDenom, mOutputImg);
        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mOutputCoinDenom, mOutputCoin);

        mSwapInputCoinRate.setText(WDp.getDpAmount2(getContext(), BigDecimal.ONE, 0, 6));
        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mInputCoinDenom, mSwapInputCoinSymbol);
        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mOutputCoinDenom, mSwapOutputCoinSymbol);
        mSwapOutputCoinRate.setText(WDp.getDpAmount2(getContext(), WUtil.getSifPoolPrice(mSelectedPool, mInputCoinDenom).movePointLeft(18), 0, 6));

        mSwapInputCoinExRate.setText(WDp.getDpAmount2(getContext(), BigDecimal.ONE, 0, 6));
        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mInputCoinDenom, mSwapInputCoinExSymbol);
        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mOutputCoinDenom, mSwapOutputCoinExSymbol);

        BigDecimal priceInput = WDp.perUsdValue(getBaseDao(), getBaseDao().getBaseDenom(getSActivity().mChainConfig, mInputCoinDenom));
        BigDecimal priceOutput = WDp.perUsdValue(getBaseDao(), getBaseDao().getBaseDenom(getSActivity().mChainConfig, mOutputCoinDenom));
        BigDecimal priceRate = BigDecimal.ZERO;
        if (priceInput.compareTo(BigDecimal.ZERO) == 0 || priceOutput.compareTo(BigDecimal.ZERO) == 0) {
            mSwapOutputCoinExRate.setText("??????");
        } else {
            priceRate = priceInput.divide(priceOutput, 6, RoundingMode.DOWN);
            mSwapOutputCoinExRate.setText(WDp.getDpAmount2(getContext(), priceRate, 0, 6));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnInputCoinList)) {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("denoms", mAllDenoms);
            SwapCoinListDialog dialog = SwapCoinListDialog.newInstance(bundle);
            dialog.setTargetFragment(this, SELECT_INPUT_CHAIN);
            getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mBtnOutputCoinList)) {
            mSwapableDenoms.clear();
            if (mInputCoinDenom.equals(getSActivity().mChainConfig.mainDenom())) {
                mSwapableDenoms = (ArrayList<String>) mAllDenoms.clone();
            } else {
                mSwapableDenoms.add(getSActivity().mChainConfig.mainDenom());
            }
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("denoms", mSwapableDenoms);
            SwapCoinListDialog dialog = SwapCoinListDialog.newInstance(bundle);
            dialog.setCancelable(true);
            dialog.setTargetFragment(this, SELECT_OUTPUT_CHAIN);
            getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mBtnToggle)) {
            String temp = mInputCoinDenom;
            mInputCoinDenom = mOutputCoinDenom;
            mOutputCoinDenom = temp;
            onUpdateView();

        } else if (v.equals(mBtnSwapStart)) {
            getSActivity().onStartSwap(mInputCoinDenom, mOutputCoinDenom, mSelectedPool);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SELECT_INPUT_CHAIN && resultCode == Activity.RESULT_OK) {
            mInputCoinDenom = mAllDenoms.get(data.getIntExtra("selectedDenom", 0));
            if (mInputCoinDenom.equals(getSActivity().mChainConfig.mainDenom())) {
                mSelectedPool = mPoolList.get(0);
                mOutputCoinDenom = mSelectedPool.getExternalAsset().getSymbol();
            } else {
                for (Types.Pool pool : mPoolList) {
                    if (pool.getExternalAsset().getSymbol().equals(mInputCoinDenom)) {
                        mSelectedPool = pool;
                        mOutputCoinDenom = BaseConstant.TOKEN_SIF;
                    }
                }
            }
            onUpdateView();

        } else if (requestCode == SELECT_OUTPUT_CHAIN && resultCode == Activity.RESULT_OK) {
            mOutputCoinDenom = mSwapableDenoms.get(data.getIntExtra("selectedDenom", 0));
            if (mOutputCoinDenom.equals(getSActivity().mChainConfig.mainDenom())) {
                mSelectedPool = mPoolList.get(0);
                mInputCoinDenom = mSelectedPool.getExternalAsset().getSymbol();
            } else {
                for (Types.Pool pool : mPoolList) {
                    if (pool.getExternalAsset().getSymbol().equals(mOutputCoinDenom)) {
                        mSelectedPool = pool;
                    }
                }
            }
            onUpdateView();
        }
    }

    private SifDexListActivity getSActivity() {
        return (SifDexListActivity) getBaseActivity();
    }
}

