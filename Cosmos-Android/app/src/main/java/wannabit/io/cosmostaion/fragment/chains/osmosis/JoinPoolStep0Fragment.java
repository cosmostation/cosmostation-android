package wannabit.io.cosmostaion.fragment.chains.osmosis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.JoinPoolActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class JoinPoolStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button          mCancelBtn, mNextBtn;

    private ImageView       mJoinPoolInputImg;
    private TextView        mJoinPoolInputSymbol;
    private EditText        mJoinPoolInput;
    private ImageView       mBtnJoinPoolInputClear;
    private TextView        mJoinPoolInputAmount, mJoinPoolIntputDenom;
    private Button          mBtnJoinPoolInput1_4, mBtnJoinPoolInputHalf, mBtnJoinPoolInput3_4, mBtnJoinPoolInputMax;

    private ImageView       mJoinPoolOutputImg;
    private TextView        mJoinPoolOutputSymbol;
    private EditText        mJoinPoolOutput;
    private ImageView       mBtnJoinPoolOutputClear;
    private TextView        mJoinPoolOutputAmount, mJoinPoolOutputDenom;
    private Button          mBtnJoinPoolOutput1_4, mBtnJoinPoolOutputHalf, mBtnJoinPoolOutput3_4, mBtnJoinPoolOutputMax;


    public static JoinPoolStep0Fragment newInstance(Bundle bundle) {
        JoinPoolStep0Fragment fragment = new JoinPoolStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_join_pool0, container, false);
        mCancelBtn                  = rootView.findViewById(R.id.btn_cancel);
        mNextBtn                    = rootView.findViewById(R.id.btn_next);

        mJoinPoolInputImg           = rootView.findViewById(R.id.join_pool_input_icon);
        mJoinPoolInputSymbol        = rootView.findViewById(R.id.join_pool_input_symbol);
        mJoinPoolInput              = rootView.findViewById(R.id.join_pool_input);
        mBtnJoinPoolInputClear      = rootView.findViewById(R.id.join_pool_input_clear);
        mJoinPoolInputAmount        = rootView.findViewById(R.id.join_pool_input_amount);
        mJoinPoolIntputDenom        = rootView.findViewById(R.id.join_pool_input_amount_denom);
        mBtnJoinPoolInput1_4        = rootView.findViewById(R.id.join_pool_input_1_4);
        mBtnJoinPoolInputHalf       = rootView.findViewById(R.id.join_pool_input_half);
        mBtnJoinPoolInput3_4        = rootView.findViewById(R.id.join_pool_input_3_4);
        mBtnJoinPoolInputMax        = rootView.findViewById(R.id.join_pool_input_max);

        mJoinPoolOutputImg          = rootView.findViewById(R.id.join_pool_output_icon);
        mJoinPoolOutputSymbol       = rootView.findViewById(R.id.join_pool_output_symbol);
        mJoinPoolOutput             = rootView.findViewById(R.id.join_pool_output);
        mBtnJoinPoolOutputClear     = rootView.findViewById(R.id.join_pool_output_clear);
        mJoinPoolOutputAmount       = rootView.findViewById(R.id.join_pool_output_amount);
        mJoinPoolOutputDenom        = rootView.findViewById(R.id.join_pool_output_amount_denom);
        mBtnJoinPoolOutput1_4       = rootView.findViewById(R.id.join_pool_output_1_4);
        mBtnJoinPoolOutputHalf      = rootView.findViewById(R.id.join_pool_output_half);
        mBtnJoinPoolOutput3_4       = rootView.findViewById(R.id.join_pool_output_3_4);
        mBtnJoinPoolOutputMax       = rootView.findViewById(R.id.join_pool_output_max);

        mBtnJoinPoolInputClear.setOnClickListener(this);
        mBtnJoinPoolInput1_4.setOnClickListener(this);
        mBtnJoinPoolInputHalf.setOnClickListener(this);
        mBtnJoinPoolInput3_4.setOnClickListener(this);
        mBtnJoinPoolInputMax.setOnClickListener(this);
        mBtnJoinPoolOutputClear.setOnClickListener(this);
        mBtnJoinPoolOutput1_4.setOnClickListener(this);
        mBtnJoinPoolOutputHalf.setOnClickListener(this);
        mBtnJoinPoolOutput3_4.setOnClickListener(this);
        mBtnJoinPoolOutputMax.setOnClickListener(this);

        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
        }
    }


    private JoinPoolActivity getSActivity() { return (JoinPoolActivity)getBaseActivity(); }
}
