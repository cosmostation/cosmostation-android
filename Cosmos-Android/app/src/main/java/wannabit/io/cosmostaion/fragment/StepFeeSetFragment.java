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

import com.addisonelliott.segmentedbutton.SegmentedButton;
import com.addisonelliott.segmentedbutton.SegmentedButtonGroup;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_DELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;

public class StepFeeSetFragment extends BaseFragment {

    //for Send and Delegate tx case visiable
    private CardView                mSpendCard;
    private TextView                mAvailableDenom, mAvailableAmount, mAvailableValue;
    private TextView                mSpendTitle,  mSpendDenom, mSpendAmount, mSpendValue;
    private TextView                mFeeDenom, mFeeAmount, mFeeValue;
    private TextView                mRemainDenom, mRemainAmount, mRemainValue;

    //for Other tx case visiable (display ony fee)
    private CardView                mFeeCard;
    private TextView                mOnlyFeeDenom, mOnlyFeeAmount, mOnlyFeeValue;

    private CardView                mRateControlCard;
    private TextView                mGasAmount, mGasRate, mGasFee;
    private SegmentedButtonGroup    mButtonGroup;
    private SegmentedButton         mTiny, mLow, mAverage;

    private LinearLayout            mSpeedLayer;
    private ImageView               mSpeedImg;
    private TextView                mSpeedTxt;

    private CardView                mBottomControlCard;
    private RelativeLayout          mBtnGasCheck;
    private Button                  mBtnBefore, mBtnNExt;


    private BigDecimal              mAvailable      = BigDecimal.ZERO;
    private BigDecimal              mSpend          = BigDecimal.ZERO;

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
        mSpendCard = rootView.findViewById(R.id.card_total_spend);
        mAvailableDenom = rootView.findViewById(R.id.spend_available_denom);
        mAvailableAmount = rootView.findViewById(R.id.spend_available_amount);
        mAvailableValue = rootView.findViewById(R.id.spend_available_value);
        mSpendTitle = rootView.findViewById(R.id.spend_spend_title);
        mSpendDenom = rootView.findViewById(R.id.spend_spend_denom);
        mSpendAmount = rootView.findViewById(R.id.spend_spend_amount);
        mSpendValue = rootView.findViewById(R.id.spend_spend_value);
        mFeeDenom = rootView.findViewById(R.id.spend_fee_denom);
        mFeeAmount = rootView.findViewById(R.id.spend_fee_amount);
        mFeeValue = rootView.findViewById(R.id.spend_fee_value);
        mRemainDenom = rootView.findViewById(R.id.spend_remain_denom);
        mRemainAmount = rootView.findViewById(R.id.spend_remain_amount);
        mRemainValue = rootView.findViewById(R.id.spend_remain_value);

        mFeeCard = rootView.findViewById(R.id.card_only_fee);
        mOnlyFeeDenom = rootView.findViewById(R.id.fee_fee_denom);
        mOnlyFeeAmount = rootView.findViewById(R.id.fee_fee_amount);
        mOnlyFeeValue = rootView.findViewById(R.id.fee_fee_value);

        mRateControlCard = rootView.findViewById(R.id.rate_control_layer);
        mGasAmount = rootView.findViewById(R.id.gas_amount);
        mGasRate = rootView.findViewById(R.id.gas_rate);
        mGasFee = rootView.findViewById(R.id.gas_fee);
        mButtonGroup = rootView.findViewById(R.id.btns_segmented);
        mTiny = rootView.findViewById(R.id.btn_tiny);
        mLow = rootView.findViewById(R.id.btn_low);
        mAverage = rootView.findViewById(R.id.btn_average);

        mSpeedLayer = rootView.findViewById(R.id.speed_layer);
        mSpeedImg = rootView.findViewById(R.id.speed_img);
        mSpeedTxt = rootView.findViewById(R.id.speed_txt);

        mBottomControlCard = rootView.findViewById(R.id.bottom_control_layer);
        mBtnGasCheck = rootView.findViewById(R.id.btn_gas_check);
        mBtnBefore = rootView.findViewById(R.id.btn_before);
        mBtnNExt = rootView.findViewById(R.id.btn_next);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        super.onRefreshTab();
        if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_SEND || getSActivity().mTxType == CONST_PW_TX_SIMPLE_DELEGATE) {
            mSpendCard.setVisibility(View.VISIBLE);
            mRateControlCard.setVisibility(View.VISIBLE);
            mSpeedLayer.setVisibility(View.VISIBLE);
            mBottomControlCard.setVisibility(View.VISIBLE);

        } else {
            mFeeCard.setVisibility(View.VISIBLE);
            mRateControlCard.setVisibility(View.VISIBLE);
            mSpeedLayer.setVisibility(View.VISIBLE);
            mBottomControlCard.setVisibility(View.VISIBLE);
        }
    }

    private BaseBroadCastActivity getSActivity() {
        return (BaseBroadCastActivity)getBaseActivity();
    }
}
