package wannabit.io.cosmostaion.fragment.txs.kava;

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

import cosmos.base.v1beta1.CoinOuterClass;
import kava.swap.v1beta1.QueryOuterClass;
import kava.swap.v1beta1.Swap;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.DAppsList5Activity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.SwapCoinListDialog;
import wannabit.io.cosmostaion.utils.WDp;

public class ListKavaSwapFragment extends BaseFragment implements View.OnClickListener {

    public final static int SELECT_INPUT_CHAIN = 8500;
    public final static int SELECT_OUTPUT_CHAIN = 8501;

    private RelativeLayout mBtnInputCoinList, mBtnOutputCoinList;
    private ImageView mInputImg;
    private TextView mInputCoin, mInputAmount;
    private TextView mSwapFee;
    private TextView mSwapTitle, mSwapSlippage;
    private ImageView mOutputImg;
    private TextView mOutputCoin;
    private TextView mSwapInputCoinRate, mSwapInputCoinSymbol, mSwapOutputCoinRate, mSwapOutputCoinSymbol;
    private TextView mSwapInputCoinExRate, mSwapInputCoinExSymbol, mSwapOutputCoinExRate, mSwapOutputCoinExSymbol;

    private ImageButton mBtnToggle;
    private Button mBtnSwapStart;

    public Swap.Params mSwapParams;
    public ArrayList<String> mAllDenoms = new ArrayList<>();
    public ArrayList<QueryOuterClass.PoolResponse> mSwapPoolList = new ArrayList<>();
    public QueryOuterClass.PoolResponse mSelectedPool;

    public ArrayList<QueryOuterClass.PoolResponse> mSwapablePools = new ArrayList<>();
    public ArrayList<String> mSwapableDenoms = new ArrayList<>();

    public String mInputCoinDenom;
    public String mOutputCoinDenom;

    public static ListKavaSwapFragment newInstance(Bundle bundle) {
        ListKavaSwapFragment fragment = new ListKavaSwapFragment();
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

        mSwapFee = rootView.findViewById(R.id.token_swap_fee);
        mSwapSlippage = rootView.findViewById(R.id.swap_slippage);
        mBtnToggle = rootView.findViewById(R.id.btn_toggle);
        mBtnSwapStart = rootView.findViewById(R.id.btn_start_swap);

        mBtnInputCoinList.setOnClickListener(this);
        mBtnOutputCoinList.setOnClickListener(this);
        mBtnToggle.setOnClickListener(this);
        mBtnSwapStart.setOnClickListener(this);

        mBtnToggle.setBackgroundTintList(ContextCompat.getColorStateList(getSActivity(), R.color.color_kava));
        mSwapTitle.setText(getString(R.string.str_swap_kava));
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mSwapParams = getBaseDao().mSwapParams;
        mSwapPoolList = getSActivity().mSwapPoolList;
        mAllDenoms = getSActivity().mAllDenoms;

        if (mSwapPoolList != null && mSwapParams != null) {
            for (QueryOuterClass.PoolResponse pool : mSwapPoolList) {
                if (pool.getCoins(0).getDenom().equalsIgnoreCase("ukava") && pool.getCoins(1).getDenom().equalsIgnoreCase("usdx")) {
                    mSelectedPool = pool;
                    mInputCoinDenom = "ukava";
                    mOutputCoinDenom = "usdx";
                }
            }
        }

        onUpdateView();
    }

    private void onUpdateView() {
        if (mSelectedPool == null) {
            getSActivity().onBackPressed();
        }
        if (!mInputCoinDenom.isEmpty() && !mOutputCoinDenom.isEmpty()) {
            WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, mInputCoinDenom, mInputImg);
            WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, mOutputCoinDenom, mOutputImg);
            WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mInputCoinDenom, mInputCoin);
            WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mOutputCoinDenom, mOutputCoin);
            int inputDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, mInputCoinDenom);
            int outputDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, mOutputCoinDenom);

            BigDecimal availableMaxAmount = getBaseDao().getAvailable(mInputCoinDenom);
            BigDecimal swapFee = new BigDecimal(mSwapParams.getSwapFee()).movePointLeft(18);
            mSwapFee.setText(WDp.getPercentDp(swapFee.movePointLeft(16)));
            mSwapSlippage.setText(WDp.getPercentDp(new BigDecimal("3")));
            mInputAmount.setText(WDp.getDpAmount2(getSActivity(), availableMaxAmount, inputDecimal, inputDecimal));

            BigDecimal inputAmount = BigDecimal.ZERO;
            BigDecimal outputAmount = BigDecimal.ZERO;

            if (mSelectedPool.getCoins(0).getDenom().equalsIgnoreCase(mInputCoinDenom)) {
                inputAmount = new BigDecimal(mSelectedPool.getCoins(0).getAmount());
                outputAmount = new BigDecimal(mSelectedPool.getCoins(1).getAmount());
            } else {
                inputAmount = new BigDecimal(mSelectedPool.getCoins(1).getAmount());
                outputAmount = new BigDecimal(mSelectedPool.getCoins(0).getAmount());
            }

            inputAmount = inputAmount.movePointLeft(inputDecimal);
            outputAmount = outputAmount.movePointLeft(outputDecimal);

            BigDecimal swapRate = outputAmount.divide(inputAmount, 16, RoundingMode.DOWN);

            mSwapInputCoinRate.setText(WDp.getDpAmount2(getContext(), BigDecimal.ONE, 0, inputDecimal));
            mSwapOutputCoinRate.setText(WDp.getDpAmount2(getContext(), swapRate, 0, outputDecimal));
            WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mInputCoinDenom, mSwapInputCoinSymbol);
            WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mOutputCoinDenom, mSwapOutputCoinSymbol);

            WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mInputCoinDenom, mSwapInputCoinExSymbol);
            WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mOutputCoinDenom, mSwapOutputCoinExSymbol);

            BigDecimal priceInput = WDp.perUsdValue(getBaseDao(), getBaseDao().getBaseDenom(getSActivity().mChainConfig, mInputCoinDenom));
            BigDecimal priceOutput = WDp.perUsdValue(getBaseDao(), getBaseDao().getBaseDenom(getSActivity().mChainConfig, mOutputCoinDenom));
            BigDecimal priceRate = BigDecimal.ZERO;
            if (priceInput.compareTo(BigDecimal.ZERO) == 0 || priceOutput.compareTo(BigDecimal.ZERO) == 0) {
                mSwapOutputCoinExRate.setText("?.??????");
            } else {
                priceRate = priceInput.divide(priceOutput, 6, RoundingMode.DOWN);
                mSwapOutputCoinExRate.setText(WDp.getDpAmount2(getContext(), priceRate, 0, outputDecimal));
            }
            mSwapInputCoinExRate.setText(WDp.getDpAmount2(getContext(), BigDecimal.ONE, 0, inputDecimal));
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
            mSwapablePools.clear();
            mSwapableDenoms.clear();

            for (QueryOuterClass.PoolResponse pool : mSwapPoolList) {
                for (CoinOuterClass.Coin coin : pool.getCoinsList()) {
                    if (coin.getDenom().equalsIgnoreCase(mInputCoinDenom)) {
                        mSwapablePools.add(pool);
                    }
                }
            }
            for (QueryOuterClass.PoolResponse pool : mSwapablePools) {
                if (pool.getCoins(0).getDenom().equalsIgnoreCase(mInputCoinDenom)) {
                    mSwapableDenoms.add(pool.getCoins(1).getDenom());
                } else {
                    mSwapableDenoms.add(pool.getCoins(0).getDenom());
                }
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
            getSActivity().onCheckStartSwap(mInputCoinDenom, mOutputCoinDenom, mSelectedPool);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_INPUT_CHAIN && resultCode == Activity.RESULT_OK) {
            mInputCoinDenom = mAllDenoms.get(data.getIntExtra("selectedDenom", 0));
            loop : for (QueryOuterClass.PoolResponse pool : mSwapPoolList) {
                for (CoinOuterClass.Coin coin : pool.getCoinsList()) {
                    if (coin.getDenom().equalsIgnoreCase(mInputCoinDenom)) {
                        mSelectedPool = pool;
                        break loop;
                    }
                }
            }
            if (mSelectedPool.getCoins(0).getDenom().equalsIgnoreCase(mInputCoinDenom)) {
                mOutputCoinDenom = mSelectedPool.getCoins(1).getDenom();
            } else {
                mOutputCoinDenom = mSelectedPool.getCoins(0).getDenom();
            }
            onUpdateView();

        } else if (requestCode == SELECT_OUTPUT_CHAIN && resultCode == Activity.RESULT_OK) {
            mOutputCoinDenom = mSwapableDenoms.get(data.getIntExtra("selectedDenom", 0));
            loop : for (QueryOuterClass.PoolResponse pool : mSwapablePools) {
                for (CoinOuterClass.Coin coin : pool.getCoinsList()) {
                    if (coin.getDenom().equalsIgnoreCase(mOutputCoinDenom)) {
                        mSelectedPool = pool;
                        break loop;
                    }
                }
            }
            onUpdateView();
        }
    }

    private DAppsList5Activity getSActivity() {
        return (DAppsList5Activity) getBaseActivity();
    }
}
