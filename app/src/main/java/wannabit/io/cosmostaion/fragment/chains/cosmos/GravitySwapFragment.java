package wannabit.io.cosmostaion.fragment.chains.cosmos;

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

import tendermint.liquidity.v1beta1.Liquidity;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.cosmos.GravityListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_Swap_Coin_List;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class GravitySwapFragment extends BaseFragment implements View.OnClickListener {
    public final static int SELECT_INPUT_CHAIN = 8500;
    public final static int SELECT_OUTPUT_CHAIN = 8501;

    private RelativeLayout mBtnInputCoinList, mBtnOutputCoinList;
    private ImageView mInputImg;
    private TextView mInputCoin, mInputAmount;
    private TextView mSwapFee, mSwapSlippage;
    private TextView mSwapTitle;
    private ImageView mOutputImg;
    private TextView mOutputCoin;
    private TextView mSwapInputCoinRate, mSwapInputCoinSymbol, mSwapOutputCoinRate, mSwapOutputCoinSymbol;
    private TextView mSwapInputCoinExRate, mSwapInputCoinExSymbol, mSwapOutputCoinExRate, mSwapOutputCoinExSymbol;

    private ImageButton mBtnToggle;
    private Button mBtnSwapStart;

    public Liquidity.Params mParms;
    public ArrayList<Liquidity.Pool> mPoolList = new ArrayList<>();
    public ArrayList<String> mAllDenoms = new ArrayList<>();
    public ArrayList<Liquidity.Pool> mSwapablePools = new ArrayList<>();
    public ArrayList<String> mSwapableDenoms = new ArrayList<>();
    public Liquidity.Pool mSelectedPool;

    public String mInputCoinDenom;
    public String mOutputCoinDenom;
    public BigDecimal mPoolSwapRate;
    public int mInPutDecimal = 6;
    public int mOutPutDecimal = 6;

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

        mBtnToggle.setBackgroundTintList(ContextCompat.getColorStateList(getSActivity(), R.color.color_atom));
        mSwapTitle.setText(getString(R.string.str_swap_gravity));
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mPoolList = getSActivity().mPoolList;
        mParms = getBaseDao().mParams;
        mAllDenoms = getSActivity().mAllDenoms;

        if (mSelectedPool == null || mInputCoinDenom.isEmpty() || mOutputCoinDenom.isEmpty()) {
            mSelectedPool = mPoolList.get(0);
            mInputCoinDenom = mSelectedPool.getReserveCoinDenoms(1);
            mOutputCoinDenom = mSelectedPool.getReserveCoinDenoms(0);
        }
        onUpdateView();
    }

    private void onUpdateView() {
        BigDecimal availableMaxAmount = getBaseDao().getAvailable(mInputCoinDenom);
        mInPutDecimal = WUtil.getCosmosCoinDecimal(getBaseDao(), mInputCoinDenom);
        mOutPutDecimal = WUtil.getCosmosCoinDecimal(getBaseDao(), mOutputCoinDenom);

        mSwapFee.setText(WDp.getPercentDp(new BigDecimal(mParms.getSwapFeeRate()).movePointLeft(16)));
        mSwapSlippage.setText(WDp.getPercentDp(new BigDecimal("3")));
        mInputAmount.setText(WDp.getDpAmount2(getSActivity(), availableMaxAmount, mInPutDecimal, mInPutDecimal));

        WUtil.dpCosmosTokenName(getSActivity(), getBaseDao(), mInputCoin, mInputCoinDenom);
        WUtil.DpCosmosTokenImg(getBaseDao(), mInputImg, mInputCoinDenom);
        WUtil.dpCosmosTokenName(getSActivity(), getBaseDao(), mOutputCoin, mOutputCoinDenom);
        WUtil.DpCosmosTokenImg(getBaseDao(), mOutputImg, mOutputCoinDenom);
        WUtil.dpCosmosTokenName(getSActivity(), getBaseDao(), mSwapInputCoinSymbol, mInputCoinDenom);
        WUtil.dpCosmosTokenName(getSActivity(), getBaseDao(), mSwapOutputCoinSymbol, mOutputCoinDenom);
        WUtil.dpCosmosTokenName(getSActivity(), getBaseDao(), mSwapInputCoinExSymbol, mInputCoinDenom);
        WUtil.dpCosmosTokenName(getSActivity(), getBaseDao(), mSwapOutputCoinExSymbol, mOutputCoinDenom);

        mSwapInputCoinRate.setText(WDp.getDpAmount2(getContext(), BigDecimal.ONE, 0, mInPutDecimal));
        mSwapInputCoinExRate.setText(WDp.getDpAmount2(getContext(), BigDecimal.ONE, 0, mInPutDecimal));

        BigDecimal lpInputAmount = getSActivity().getLpAmount(mSelectedPool.getReserveAccountAddress(), mInputCoinDenom);
        BigDecimal lpOutputAmount = getSActivity().getLpAmount(mSelectedPool.getReserveAccountAddress(), mOutputCoinDenom);

        mPoolSwapRate = BigDecimal.ZERO;
        if (lpInputAmount != BigDecimal.ZERO && lpOutputAmount != BigDecimal.ZERO) {
            mPoolSwapRate = lpOutputAmount.divide(lpInputAmount, 6, RoundingMode.DOWN).movePointRight(mInPutDecimal - mOutPutDecimal);
        }
        mSwapOutputCoinRate.setText(WDp.getDpAmount2(getContext(), mPoolSwapRate, 0, mOutPutDecimal));

        BigDecimal priceInput = WDp.perUsdValue(getBaseDao(), getBaseDao().getBaseDenom(mInputCoinDenom));
        BigDecimal priceOutput = WDp.perUsdValue(getBaseDao(), getBaseDao().getBaseDenom(mOutputCoinDenom));
        BigDecimal priceRate = BigDecimal.ZERO;

        if (priceInput.compareTo(BigDecimal.ZERO) == 0 || priceOutput.compareTo(BigDecimal.ZERO) == 0) {
            mSwapOutputCoinExRate.setText("?.??????");
        } else {
            priceRate = priceInput.divide(priceOutput, 6, RoundingMode.DOWN);
            mSwapOutputCoinExRate.setText(WDp.getDpAmount2(getContext(), priceRate, 0, mOutPutDecimal));
        }
        mSwapInputCoinExRate.setText(WDp.getDpAmount2(getContext(), BigDecimal.ONE, 0, mInPutDecimal));
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnInputCoinList)) {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("denoms", mAllDenoms);
            Dialog_Swap_Coin_List dialog = Dialog_Swap_Coin_List.newInstance(bundle);
            dialog.setTargetFragment(this, SELECT_INPUT_CHAIN);
            getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mBtnOutputCoinList)) {
            mSwapablePools.clear();
            mSwapableDenoms.clear();
            for (Liquidity.Pool pool : mPoolList) {
                for (String denom : pool.getReserveCoinDenomsList()) {
                    if (denom.contains(mInputCoinDenom)) {
                        mSwapablePools.add(pool);
                    }
                }
            }

            for (Liquidity.Pool pool : mSwapablePools) {
                for (String denom : pool.getReserveCoinDenomsList()) {
                    if (!denom.equalsIgnoreCase(mInputCoinDenom)) {
                        mSwapableDenoms.add(denom);
                    }
                }
            }

            Bundle bundle = new Bundle();
            bundle.putStringArrayList("denoms", mSwapableDenoms);
            Dialog_Swap_Coin_List dialog = Dialog_Swap_Coin_List.newInstance(bundle);
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
            loop:
            for (Liquidity.Pool pool : mPoolList) {
                for (String denom : pool.getReserveCoinDenomsList()) {
                    if (denom.equalsIgnoreCase(mInputCoinDenom)) {
                        mSelectedPool = pool;
                        break loop;
                    }
                }
            }
            for (String denom : mSelectedPool.getReserveCoinDenomsList()) {
                if (!denom.equalsIgnoreCase(mInputCoinDenom)) {
                    mOutputCoinDenom = denom;
                    break;
                }
            }
            onUpdateView();

        } else if (requestCode == SELECT_OUTPUT_CHAIN && resultCode == Activity.RESULT_OK) {
            mOutputCoinDenom = mSwapableDenoms.get(data.getIntExtra("selectedDenom", 0));
            loop:
            for (Liquidity.Pool pool : mSwapablePools) {
                for (String denom : pool.getReserveCoinDenomsList()) {
                    if (denom.equalsIgnoreCase(mOutputCoinDenom)) {
                        mSelectedPool = pool;
                        break loop;
                    }
                }
            }
            onUpdateView();
        }
    }

    private GravityListActivity getSActivity() {
        return (GravityListActivity) getBaseActivity();
    }

}
