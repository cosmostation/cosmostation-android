package wannabit.io.cosmostaion.fragment.chains.kava;

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

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.DAppsList5Activity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_Swap_Coin_List;
import wannabit.io.cosmostaion.model.kava.SwapParam;
import wannabit.io.cosmostaion.model.kava.SwapPool;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_USDX;

public class ListKavaSwapFragment extends BaseFragment implements View.OnClickListener{
    public final static int     SELECT_INPUT_CHAIN = 8500;
    public final static int     SELECT_OUTPUT_CHAIN = 8501;

    private RelativeLayout      mBtnInputCoinList, mBtnOutputCoinList;
    private ImageView           mInputImg;
    private TextView            mInputCoin, mInputAmount;
    private TextView            mSwapFee;
    private TextView            mSwapTitle, mSwapSlippage;
    private ImageView           mOutputImg;
    private TextView            mOutputCoin;
    private TextView            mSwapInputCoinRate, mSwapInputCoinSymbol, mSwapOutputCoinRate, mSwapOutputCoinSymbol;
    private TextView            mSwapInputCoinExRate, mSwapInputCoinExSymbol, mSwapOutputCoinExRate, mSwapOutputCoinExSymbol;

    private ImageButton         mBtnToggle;
    private Button              mBtnSwapStart;

    public SwapParam                        mSwapParam;
    public ArrayList<SwapPool>              mSwapPoolList = new ArrayList<>();
    public ArrayList<String>                mAllDenoms = new ArrayList<>();
    public ArrayList<SwapPool>              mSwapablePools = new ArrayList<>();
    public ArrayList<String>                mSwapableDenoms = new ArrayList<>();

    public SwapPool                         mSelectedPool;
    public String                           mInputCoinDenom;
    public String                           mOutputCoinDenom;

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

        mBtnToggle.setBackgroundTintList(getResources().getColorStateList(R.color.colorKava));
        mSwapTitle.setText(getString(R.string.str_swap_kava));
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mSwapParam = getBaseDao().mSwapParam;
        mSwapPoolList = getSActivity().mSwapPoolList;
        mAllDenoms = getSActivity().mAllDenoms;

        if (mSwapPoolList != null && mSwapParam != null) {
            mSelectedPool = mSwapPoolList.get(5);
            mInputCoinDenom = "ukava";
            mOutputCoinDenom = "usdx";
        }

        onUpdateView();
    }

    private void onUpdateView() {
        Picasso.get().load(KAVA_COIN_IMG_URL+mInputCoinDenom+".png") .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic) .into(mInputImg);
        Picasso.get().load(KAVA_COIN_IMG_URL+mOutputCoinDenom+".png") .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic) .into(mOutputImg);
        WUtil.dpKavaTokenName(getSActivity(), mInputCoin, mInputCoinDenom);
        WUtil.dpKavaTokenName(getSActivity(), mOutputCoin, mOutputCoinDenom);
        int InPutDecimal = WUtil.getKavaCoinDecimal(mInputCoinDenom);
        int OutPutDecimal = WUtil.getKavaCoinDecimal(mOutputCoinDenom);

        BigDecimal availableMaxAmount = getBaseDao().availableAmount(mInputCoinDenom);
        BigDecimal swapFee = new BigDecimal(mSwapParam.swap_fee);
        mSwapFee.setText(WDp.getPercentDp(swapFee.movePointLeft(16)));
        mSwapSlippage.setText(WDp.getPercentDp(new BigDecimal("3")));
        mInputAmount.setText(WDp.getDpAmount2(getSActivity(), availableMaxAmount, InPutDecimal, OutPutDecimal));

        final int inputCoinDecimal = WUtil.getKavaCoinDecimal(mInputCoinDenom);
        final int outCoinDecimal = WUtil.getKavaCoinDecimal(mOutputCoinDenom);

        BigDecimal inputAmount = BigDecimal.ZERO;
        BigDecimal outputAmount = BigDecimal.ZERO;

        if (mSelectedPool.coins.get(0).denom.equalsIgnoreCase(mInputCoinDenom)) {
            inputAmount = new BigDecimal(mSelectedPool.coins.get(0).amount);
            outputAmount = new BigDecimal(mSelectedPool.coins.get(1).amount);
        } else {
            inputAmount = new BigDecimal(mSelectedPool.coins.get(1).amount);
            outputAmount = new BigDecimal(mSelectedPool.coins.get(0).amount);
        }

        inputAmount = inputAmount.movePointLeft(WUtil.getKavaCoinDecimal(mInputCoinDenom));
        outputAmount = outputAmount.movePointLeft(WUtil.getKavaCoinDecimal(mOutputCoinDenom));
        BigDecimal swapRate = outputAmount.divide(inputAmount, 16, RoundingMode.DOWN);

        mSwapInputCoinRate.setText(WDp.getDpAmount2(getContext(), BigDecimal.ONE, 0, inputCoinDecimal));
        WUtil.dpKavaTokenName(getSActivity(), mSwapInputCoinSymbol, mInputCoinDenom);
        mSwapOutputCoinRate.setText(WDp.getDpAmount2(getContext(), swapRate, 0, outCoinDecimal));
        WUtil.dpKavaTokenName(getSActivity(), mSwapOutputCoinSymbol, mOutputCoinDenom);

        WUtil.dpKavaTokenName(getSActivity(), mSwapInputCoinExSymbol, mInputCoinDenom);
        WUtil.dpKavaTokenName(getSActivity(), mSwapOutputCoinExSymbol, mOutputCoinDenom);

        BigDecimal priceInput = WDp.perUsdValue(getBaseDao(), getBaseDao().getBaseDenom(mInputCoinDenom));
        BigDecimal priceOutput = WDp.perUsdValue(getBaseDao(), getBaseDao().getBaseDenom(mOutputCoinDenom));
        BigDecimal priceRate = BigDecimal.ZERO;
        if (priceInput.compareTo(BigDecimal.ZERO) == 0 || priceOutput.compareTo(BigDecimal.ZERO) == 0) {
            mSwapOutputCoinExRate.setText("?.??????");
        } else {
            priceRate = priceInput.divide(priceOutput, 6, RoundingMode.DOWN);
            mSwapOutputCoinExRate.setText(WDp.getDpAmount2(getContext(), priceRate, 0, outCoinDecimal));
        }
        mSwapInputCoinExRate.setText(WDp.getDpAmount2(getContext(), BigDecimal.ONE, 0, inputCoinDecimal));
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

            for (SwapPool swapPool: mSwapPoolList) {
                for (Coin coin: swapPool.coins) {
                    if (coin.denom.equalsIgnoreCase(mInputCoinDenom)) {
                        mSwapablePools.add(swapPool);
                    }
                }
            }
            for (SwapPool swapPool: mSwapablePools) {
                if (swapPool.coins.get(0).denom.equalsIgnoreCase(mInputCoinDenom)) {
                    mSwapableDenoms.add(swapPool.coins.get(1).denom);
                } else {
                    mSwapableDenoms.add(swapPool.coins.get(0).denom);
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
            getSActivity().onCheckStartSwap(mInputCoinDenom, mOutputCoinDenom, mSelectedPool);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_INPUT_CHAIN && resultCode == Activity.RESULT_OK) {
            mInputCoinDenom = mAllDenoms.get(data.getIntExtra("selectedDenom", 0));
            loop : for (SwapPool swapPool: mSwapPoolList) {
                for (Coin coin : swapPool.coins) {
                    if (coin.denom.equalsIgnoreCase(mInputCoinDenom)) {
                        mSelectedPool = swapPool;
                        break loop;
                    }
                }
            }
            if (mSelectedPool.coins.get(0).denom.equalsIgnoreCase(mInputCoinDenom)) {
                mOutputCoinDenom = mSelectedPool.coins.get(1).denom;
            } else {
                mOutputCoinDenom = mSelectedPool.coins.get(0).denom;
            }
            onUpdateView();

        } else if (requestCode == SELECT_OUTPUT_CHAIN && resultCode == Activity.RESULT_OK) {
            mOutputCoinDenom = mSwapableDenoms.get(data.getIntExtra("selectedDenom", 0));
            loop : for (SwapPool swapPool: mSwapablePools) {
                for (Coin coin : swapPool.coins) {
                    if (coin.denom.equalsIgnoreCase(mOutputCoinDenom)) {
                        mSelectedPool = swapPool;
                    }
                }
            }
            onUpdateView();
        }
    }

    private DAppsList5Activity getSActivity() { return (DAppsList5Activity)getBaseActivity(); }
}
