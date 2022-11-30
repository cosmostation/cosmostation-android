package wannabit.io.cosmostaion.fragment.txs.liquidstaking;

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
import java.util.ArrayList;

import stride.stakeibc.EpochTrackerOuterClass;
import stride.stakeibc.HostZoneOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.liquidstaking.StrideLSActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.SelectChainListDialog;
import wannabit.io.cosmostaion.utils.WDp;

public class StrideLSFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout mBtnInputCoinList;
    private ImageView mInputImg;
    private TextView mInputCoin, mInputAmount;
    private ImageView mOutputImg;
    private TextView mOutputCoin;

    private Button mBtnStartStake;

    private ArrayList<HostZoneOuterClass.HostZone> mHostZones = new ArrayList<>();
    private EpochTrackerOuterClass.EpochTracker mDayEpoch;

    private String mInputCoinDenom;
    private String mOutputCoinDenom;
    private int mSelectedPosition = 0;
    private BigDecimal mAvailableMaxAmount = BigDecimal.ZERO;

    public static StrideLSFragment newInstance() {
        return new StrideLSFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stride_ls, container, false);
        mBtnInputCoinList = rootView.findViewById(R.id.btn_to_input_coin);
        mInputImg = rootView.findViewById(R.id.img_input_coin);
        mInputCoin = rootView.findViewById(R.id.txt_input_coin);
        mInputAmount = rootView.findViewById(R.id.input_amount);
        mOutputImg = rootView.findViewById(R.id.img_output_coin);
        mOutputCoin = rootView.findViewById(R.id.txt_output_coin);
        mBtnStartStake = rootView.findViewById(R.id.btn_start_stake);

        mBtnInputCoinList.setOnClickListener(this);
        mBtnStartStake.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mHostZones = getSActivity().mHostZones;
        mDayEpoch = getSActivity().mDayEpoch;
        onUpdateView();
    }

    private void onUpdateView() {
        if (mHostZones == null || mDayEpoch == null) getSActivity().onBackPressed();

        mInputCoinDenom = mHostZones.get(mSelectedPosition).getIbcDenom();
        mOutputCoinDenom = "st" + mHostZones.get(mSelectedPosition).getHostDenom();
        int inputDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, mInputCoinDenom);

        WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, mInputCoinDenom, mInputImg);
        WDp.setDpSymbol(getActivity(), getBaseDao(), getSActivity().mChainConfig, mInputCoinDenom, mInputCoin);
        WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, mOutputCoinDenom, mOutputImg);
        WDp.setDpSymbol(getActivity(), getBaseDao(), getSActivity().mChainConfig, mOutputCoinDenom, mOutputCoin);

        mAvailableMaxAmount = getBaseDao().getAvailable(mInputCoinDenom);
        mInputAmount.setText(WDp.getDpAmount2(getSActivity(), mAvailableMaxAmount, inputDecimal, inputDecimal));
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnInputCoinList) && !getSActivity().isFinishing()) {
            Bundle bundleData = new Bundle();
            bundleData.putSerializable(SelectChainListDialog.SELECT_LIQUIDITY_STAKE_BUNDLE_KEY, mHostZones);
            bundleData.putInt(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, SelectChainListDialog.SELECT_LIQUIDITY_STAKING_COIN_VALUE);
            SelectChainListDialog dialog = SelectChainListDialog.newInstance(bundleData);
            dialog.show(getParentFragmentManager(), SelectChainListDialog.class.getName());
            getParentFragmentManager().setFragmentResultListener(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, this, (requestKey, bundle) -> {
                mSelectedPosition = bundle.getInt(BaseConstant.POSITION);
                onUpdateView();
            });
        
        } else if (v.equals(mBtnStartStake)) {
            getSActivity().onStartStake(mHostZones.get(mSelectedPosition), mAvailableMaxAmount);
        }
    }

    private StrideLSActivity getSActivity() {
        return (StrideLSActivity) getBaseActivity();
    }
}
