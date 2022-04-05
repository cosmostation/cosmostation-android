package wannabit.io.cosmostaion.fragment.chains.ok;

import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.ok.OKVoteDirectActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.utils.WDp;

public class DirectVoteFragment3 extends BaseFragment implements View.OnClickListener, IRefreshTabListener {

    private TextView mFeeAmount;
    private TextView mMemo;
    private Button mBeforeBtn, mConfirmBtn;
    private TextView mToVoteValidator, mFeeDenom;

    public static DirectVoteFragment3 newInstance(Bundle bundle) {
        DirectVoteFragment3 fragment = new DirectVoteFragment3();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_direct_vote_3, container, false);
        mFeeAmount = rootView.findViewById(R.id.fees_amount);
        mFeeDenom = rootView.findViewById(R.id.fees_denom);
        mToVoteValidator = rootView.findViewById(R.id.to_vote_validators);
        mMemo = rootView.findViewById(R.id.memo);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);

        WDp.DpMainDenom(getSActivity().account.baseChain, mFeeDenom);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onRefreshTab() {
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);

        if (getSActivity().baseChain.equals(OKEX_MAIN) || getSActivity().baseChain.equals(OK_TEST)) {
            mFeeAmount.setText(WDp.getDpAmount2(feeAmount, 0, 18));

            String monikers = "";
            for (String valOp : getSActivity().mValAddesses) {
                for (Validator validator : getBaseDao().mAllValidators) {
                    if (validator.operator_address.equals(valOp)) {
                        monikers = monikers + validator.description.moniker + "\n";
                    }
                }
            }
            mToVoteValidator.setText(monikers);

        }
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartDirectVote();

        }
    }

    private OKVoteDirectActivity getSActivity() {
        return (OKVoteDirectActivity) getBaseActivity();
    }
}

