package wannabit.io.cosmostaion.fragment.txs.common;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.VoteActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.VoteGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;

public class VoteStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mOpinion;
    private TextView mFeeAmount, mDenomFeeType;
    private TextView mMemo;
    private Button mBeforeBtn, mConfirmBtn;
    private int mDpDecimal = 6;

    public static VoteStep3Fragment newInstance(Bundle bundle) {
        VoteStep3Fragment fragment = new VoteStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vote_step3, container, false);
        mOpinion = rootView.findViewById(R.id.my_opinion);
        mFeeAmount = rootView.findViewById(R.id.vote_fees);
        mDenomFeeType = rootView.findViewById(R.id.vote_fees_type);
        mMemo = rootView.findViewById(R.id.memo);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mDpDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, mDpDecimal, mDpDecimal));
        WDp.setGasDenomTv(getSActivity(), getSActivity().mBaseChain, getSActivity().mTxFee.amount.get(0).denom, mDenomFeeType);

        List<String> texts = Lists.newArrayList();
        getSActivity().mSelectedOpinion.forEach((key, value) -> {
            texts.add(String.format("# %s - %s", key, voteOptionConvert(value)));
        });
        String opinionText = StringUtils.join(texts, "\n");

        mOpinion.setText(opinionText);
        mMemo.setText(getSActivity().mTxMemo);
    }

    private String voteOptionConvert(String value) {
        switch (value) {
            case "VOTE_OPTION_YES":
                value = "Yes";
                break;
            case "VOTE_OPTION_NO":
                value = "No";
                break;
            case "VOTE_OPTION_NO_WITH_VETO":
                value = "NoWithVeto";
                break;
            case "VOTE_OPTION_ABSTAIN":
                value = "Abstain";
                break;
            default:
                break;
        }
        return value;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartVote();

        }
    }

    private VoteActivity getSActivity() {
        return (VoteActivity) getBaseActivity();
    }
}