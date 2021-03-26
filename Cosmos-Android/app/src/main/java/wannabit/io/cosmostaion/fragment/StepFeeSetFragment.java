package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.addisonelliott.segmentedbutton.SegmentedButtonGroup;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class StepFeeSetFragment extends BaseFragment implements View.OnClickListener {

    private CardView                mFeeTotalCard;
    private TextView                mFeeDenom, mFeeAmount, mFeeValue;

    private CardView                mRateControlCard;
    private TextView                mGasAmount, mGasRate, mGasFee;
    private SegmentedButtonGroup    mButtonGroup;

    private LinearLayout            mSpeedLayer;
    private ImageView               mSpeedImg;
    private TextView                mSpeedTxt;

    private LinearLayout            mBottomControlCard;
    private RelativeLayout          mBtnGasCheck;
    private Button                  mBtnBefore, mBtnNext;


    private int                     mSelectedGasPosition    = 1;
    private BigDecimal              mSelectedGasRate        = BigDecimal.ZERO;
    private BigDecimal              mEstimateGasAmount      = BigDecimal.ZERO;
    private BigDecimal              mFee                    = BigDecimal.ZERO;

    public static StepFeeSetFragment newInstance(Bundle bundle) {
        StepFeeSetFragment fragment = new StepFeeSetFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tx_step_fee_grpc, container, false);
        mFeeTotalCard = rootView.findViewById(R.id.card_fee_total);
        mFeeDenom = rootView.findViewById(R.id.fee_denom);
        mFeeAmount = rootView.findViewById(R.id.fee_amount);
        mFeeValue = rootView.findViewById(R.id.fee_value);

        mRateControlCard = rootView.findViewById(R.id.rate_control_layer);
        mGasAmount = rootView.findViewById(R.id.gas_amount);
        mGasRate = rootView.findViewById(R.id.gas_rate);
        mGasFee = rootView.findViewById(R.id.gas_fee);
        mButtonGroup = rootView.findViewById(R.id.btns_segmented);

        mSpeedLayer = rootView.findViewById(R.id.speed_layer);
        mSpeedImg = rootView.findViewById(R.id.speed_img);
        mSpeedTxt = rootView.findViewById(R.id.speed_txt);

        mBottomControlCard = rootView.findViewById(R.id.bottom_control_layer);
        mBtnGasCheck = rootView.findViewById(R.id.btn_gas_check);
        mBtnBefore = rootView.findViewById(R.id.btn_before);
        mBtnNext = rootView.findViewById(R.id.btn_next);

        WDp.DpMainDenom(getContext(), getSActivity().mBaseChain, mFeeDenom);
        mFeeTotalCard.setCardBackgroundColor(WDp.getChainBgColor(getContext(), getSActivity().mBaseChain));
        mButtonGroup.setSelectedBackground(WDp.getChainColor(getContext(), getSActivity().mBaseChain));
        mButtonGroup.setRipple(WDp.getChainColor(getContext(), getSActivity().mBaseChain));
        onUpdateView();

        mButtonGroup.setOnPositionChangedListener(new SegmentedButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(int position) {
                mSelectedGasPosition = position;
                onUpdateView();
            }
        });
        mBtnGasCheck.setOnClickListener(this);
        mBtnBefore.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        super.onRefreshTab();
        mFeeTotalCard.setVisibility(View.VISIBLE);
        mRateControlCard.setVisibility(View.VISIBLE);
        mSpeedLayer.setVisibility(View.VISIBLE);
        mBottomControlCard.setVisibility(View.VISIBLE);
    }

    private void onCalculateFees() {
        mSelectedGasRate = WUtil.getGasRate(getSActivity().mBaseChain, mSelectedGasPosition);
        mEstimateGasAmount = WUtil.getEstimateGasAmount(getContext(), getSActivity().mBaseChain, getSActivity().mTxType, (getSActivity().mValAddresses.size()));
        mFee = mSelectedGasRate.multiply(mEstimateGasAmount).setScale(0, RoundingMode.UP);
    }

    private void onUpdateView() {
        onCalculateFees();

        mFeeAmount.setText(WDp.getDpAmount2(getContext(), mFee, 6, 6));
        mFeeValue.setText(WDp.getDpMainAssetValue(getSActivity(), getBaseDao(), mFee, getSActivity().mBaseChain));

        mGasRate.setText(WDp.getDpGasRate(mSelectedGasRate.toPlainString()));
        mGasAmount.setText(mEstimateGasAmount.toPlainString());
        mGasFee.setText(mFee.toPlainString());

        if (mSelectedGasPosition == 0) {
            mSpeedImg.setImageDrawable(getResources().getDrawable(R.drawable.bycicle_img));
            mSpeedTxt.setText(getString(R.string.str_fee_speed_title_0));
        } else if (mSelectedGasPosition == 1) {
            mSpeedImg.setImageDrawable(getResources().getDrawable(R.drawable.car_img));
            mSpeedTxt.setText(getString(R.string.str_fee_speed_title_1));
        } else {
            mSpeedImg.setImageDrawable(getResources().getDrawable(R.drawable.rocket_img));
            mSpeedTxt.setText(getString(R.string.str_fee_speed_title_2));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnGasCheck)) {

        } else if (v.equals(mBtnBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            if (onCheckValidate()) {
                Fee fee = new Fee();
                Coin gasCoin = new Coin();
                gasCoin.denom = WDp.mainDenom(getSActivity().mBaseChain);
                gasCoin.amount = mFee.toPlainString();
                ArrayList<Coin> amount = new ArrayList<>();
                amount.add(gasCoin);
                fee.amount = amount;
                fee.gas = mEstimateGasAmount.toPlainString();
                getSActivity().mTxFee = fee;
            }
        }
    }


    private boolean onCheckValidate() {
        return true;
    }

    private BaseBroadCastActivity getSActivity() {
        return (BaseBroadCastActivity)getBaseActivity();
    }
}
