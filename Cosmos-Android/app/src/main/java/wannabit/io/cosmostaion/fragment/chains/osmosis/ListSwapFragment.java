package wannabit.io.cosmostaion.fragment.chains.osmosis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import osmosis.gamm.v1beta1.PoolOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.LabsListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_Swap_Coin_List;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class ListSwapFragment extends BaseFragment implements View.OnClickListener {
    public final static int SELECT_INPUT_CHAIN = 8500;
    public final static int SELECT_OUTPUT_CHAIN = 8501;

    private RelativeLayout mBtnInputCoinList, mBtnOutputCoinList;
    private ImageView mInputImg;
    private TextView mInputCoin;
    private ImageView mOutputImg;
    private TextView mOutputCoin;
    private TextView mSwapPoolId, mSwapInputCoinRate, mSwapInputCoinSymbol, mSwapOutputCoinRate, mSwapOutputCoinSymbol, mSwapFee;
    private Button mBtnSwapStart;

    public ArrayList<PoolOuterClass.Pool>       mPoolList = new ArrayList<>();
    public ArrayList<String>                    mAllDenoms = new ArrayList<>();
    public ArrayList<PoolOuterClass.Pool>       mSwapablePools = new ArrayList<>();
    public ArrayList<String>                    mSwapableDenoms = new ArrayList<>();
    public PoolOuterClass.Pool                  mSelectedPool;
    public String                               mInputCoinDenom;
    public String                               mOutputCoinDenom;

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
        mOutputImg                  = rootView.findViewById(R.id.img_output_coin);
        mOutputCoin                  = rootView.findViewById(R.id.txt_output_coin);

        mSwapPoolId                 = rootView.findViewById(R.id.str_token_swap_pool_id);
        mSwapInputCoinRate          = rootView.findViewById(R.id.token_swap_input_coin_rate);
        mSwapInputCoinSymbol        = rootView.findViewById(R.id.token_swap_input_coin_symbol);
        mSwapOutputCoinRate         = rootView.findViewById(R.id.token_swap_output_coin_rate);
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
        mPoolList = getSActivity().mPoolList;
        mAllDenoms = getSActivity().mAllDenoms;

        if (mSelectedPool == null || mInputCoinDenom.isEmpty() || mOutputCoinDenom.isEmpty()) {
            mSelectedPool = mPoolList.get(0);
            mInputCoinDenom = "uosmo";
            mOutputCoinDenom = "ibc/27394FB092D2ECCD56123C74F36E4C1F926001CEADA9CA97EA622B25F41E5EB2";
        }
        onUpdateView();
    }

    private void onUpdateView() {
        WUtil.dpOsmosisTokenName(getSActivity(), mInputCoin, mInputCoinDenom);
        WUtil.DpOsmosisTokenImg(mInputImg, mInputCoinDenom);
        WUtil.dpOsmosisTokenName(getSActivity(), mOutputCoin, mOutputCoinDenom);
        WUtil.DpOsmosisTokenImg(mOutputImg, mOutputCoinDenom);

        mSwapPoolId.setText("" + mSelectedPool.getId());
        BigDecimal swapFee = new BigDecimal(mSelectedPool.getPoolParams().getSwapFee());
        mSwapFee.setText(WDp.getPercentDp(swapFee.movePointLeft(16)));

        final int inputCoinDecimal = WUtil.getOsmosisCoinDecimal(mInputCoinDenom);
        final int outCoinDecimal = WUtil.getOsmosisCoinDecimal(mOutputCoinDenom);

        BigDecimal inputAssetAmount = BigDecimal.ZERO;
        BigDecimal inputAssetWeight = BigDecimal.ZERO;
        BigDecimal outputAssetAmount = BigDecimal.ZERO;
        BigDecimal outputAssetWeight = BigDecimal.ZERO;

        for (PoolOuterClass.PoolAsset asset: mSelectedPool.getPoolAssetsList()) {
            if (asset.getToken().getDenom().equals(mInputCoinDenom)) {
                inputAssetAmount = new BigDecimal(asset.getToken().getAmount());
                inputAssetWeight = new BigDecimal(asset.getWeight());
            }
            if (asset.getToken().getDenom().equals(mOutputCoinDenom)) {
                outputAssetAmount = new BigDecimal(asset.getToken().getAmount());
                outputAssetWeight = new BigDecimal(asset.getWeight());
            }
        }
        inputAssetAmount = inputAssetAmount.movePointLeft(WUtil.getOsmosisCoinDecimal(mInputCoinDenom));
        outputAssetAmount = outputAssetAmount.movePointLeft(WUtil.getOsmosisCoinDecimal(mOutputCoinDenom));
        BigDecimal swapRate = outputAssetAmount.multiply(inputAssetWeight).divide(inputAssetAmount, 16, RoundingMode.DOWN).divide(outputAssetWeight, 16, RoundingMode.DOWN);
        WLog.w("swapRate " + swapRate);

        mSwapInputCoinRate.setText(WDp.getDpAmount2(getContext(), BigDecimal.ONE, 0, inputCoinDecimal));
        WUtil.dpOsmosisTokenName(getSActivity(), mSwapInputCoinSymbol, mInputCoinDenom);
        mSwapOutputCoinRate.setText(WDp.getDpAmount2(getContext(), swapRate, 0, outCoinDecimal));
        WUtil.dpOsmosisTokenName(getSActivity(), mSwapOutputCoinSymbol, mOutputCoinDenom);
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
            for (PoolOuterClass.Pool pool: mPoolList) {
                for (PoolOuterClass.PoolAsset asset: pool.getPoolAssetsList()) {
                    if (asset.getToken().getDenom().equals(mInputCoinDenom)) {
                        mSwapablePools.add(pool);
                        break;
                    }
                }
            }
            WLog.w("mSwapablePools " +  mSwapablePools.size());
            for (PoolOuterClass.Pool sPool: mSwapablePools) {
                for (PoolOuterClass.PoolAsset asset: sPool.getPoolAssetsList()) {
                    if (!asset.getToken().getDenom().equals(mInputCoinDenom)) {
                        mSwapableDenoms.add(asset.getToken().getDenom());
                    }
                }
            }
            WLog.w("mSwapableDenoms " +  mSwapableDenoms.size());

            Bundle bundle = new Bundle();
            bundle.putStringArrayList("denoms", mSwapableDenoms);
            Dialog_Swap_Coin_List dialog = Dialog_Swap_Coin_List.newInstance(bundle);
            dialog.setCancelable(true);
            dialog.setTargetFragment(this, SELECT_OUTPUT_CHAIN);
            getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mBtnSwapStart)) {
            getSActivity().onStartSwap(mInputCoinDenom, mOutputCoinDenom, mSelectedPool.getId());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SELECT_INPUT_CHAIN && resultCode == Activity.RESULT_OK) {
            mInputCoinDenom = mAllDenoms.get(data.getIntExtra("selectedDenom", 0));
            loop : for (PoolOuterClass.Pool pool: mPoolList) {
                for (PoolOuterClass.PoolAsset asset: pool.getPoolAssetsList()) {
                    if (asset.getToken().getDenom().equals(mInputCoinDenom)) {
                        mSelectedPool = pool;
                        break loop;
                    }
                }
            }
            for (PoolOuterClass.PoolAsset asset: mSelectedPool.getPoolAssetsList()) {
                if (!asset.getToken().getDenom().equals(mInputCoinDenom)) {
                    mOutputCoinDenom = asset.getToken().getDenom();
                    break;
                }
            }
            onUpdateView();

        } else if (requestCode == SELECT_OUTPUT_CHAIN && resultCode == Activity.RESULT_OK) {
            mOutputCoinDenom = mSwapableDenoms.get(data.getIntExtra("selectedDenom", 0));
            loop : for (PoolOuterClass.Pool pool: mSwapablePools) {
                for (PoolOuterClass.PoolAsset asset: pool.getPoolAssetsList()) {
                    if (asset.getToken().getDenom().equals(mOutputCoinDenom)) {
                        mSelectedPool = pool;
                        break loop;
                    }
                }
            }
            onUpdateView();
        }
    }

    private LabsListActivity getSActivity() { return (LabsListActivity)getBaseActivity(); }
}
