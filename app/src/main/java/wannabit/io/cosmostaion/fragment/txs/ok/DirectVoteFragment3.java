package wannabit.io.cosmostaion.fragment.txs.ok;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.ok.OKVoteDirectActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.utils.WDp;

public class DirectVoteFragment3 extends BaseFragment implements View.OnClickListener {

    private TextView            mFeeAmount;
    private TextView            mMemo;
    private Button              mBeforeBtn, mConfirmBtn;
    private TextView            mToVoteValidator, mFeeDenom;

    public static DirectVoteFragment3 newInstance() {
        return new DirectVoteFragment3();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_direct_vote_3, container, false);
        mFeeAmount          = rootView.findViewById(R.id.fees_amount);
        mFeeDenom           = rootView.findViewById(R.id.fees_denom);
        mToVoteValidator    = rootView.findViewById(R.id.to_vote_validators);
        mMemo               = rootView.findViewById(R.id.memo);
        mBeforeBtn          = rootView.findViewById(R.id.btn_before);
        mConfirmBtn         = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onRefreshTab() {
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeDenom, mFeeAmount);

        String monikers = "";
        for (String valOp:getSActivity().mValAddesses) {
            for (Validator validator:getBaseDao().mAllValidators) {
                if (validator.operator_address.equals(valOp)) {
                    monikers = monikers + validator.description.moniker + "\n";
                }
            }
        }
        mToVoteValidator.setText(monikers);
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartDirectVote();

        }
    }

    private OKVoteDirectActivity getSActivity() {
        return (OKVoteDirectActivity)getBaseActivity();
    }
}

