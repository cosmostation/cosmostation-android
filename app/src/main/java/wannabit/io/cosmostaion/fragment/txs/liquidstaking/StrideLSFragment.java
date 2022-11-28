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

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.liquidstaking.StrideLSActivity;
import wannabit.io.cosmostaion.base.BaseFragment;

public class StrideLSFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout mBtnInputCoinList;
    private ImageView mInputImg;
    private TextView mInputCoin, mInputAmount;
    private ImageView mOutputImg;
    private TextView mOutputCoin;

    private Button mBtnStartStake;

    public String mInputCoinDenom;
    public String mOutputCoinDenom;

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
        mInputAmount = rootView.findViewById(R.id.inpus_amount);
        mOutputImg = rootView.findViewById(R.id.img_output_coin);
        mOutputCoin = rootView.findViewById(R.id.txt_output_coin);
        mBtnStartStake = rootView.findViewById(R.id.btn_start_stake);

        mBtnInputCoinList.setOnClickListener(this);
        mBtnStartStake.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onRefreshTab() {

    }

    @Override
    public void onClick(View v) {

    }

    private StrideLSActivity getSActivity() {
        return (StrideLSActivity) getBaseActivity();
    }
}
