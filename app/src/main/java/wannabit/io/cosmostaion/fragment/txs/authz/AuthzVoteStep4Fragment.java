package wannabit.io.cosmostaion.fragment.txs.authz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.authz.AuthzVoteActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class AuthzVoteStep4Fragment extends BaseFragment implements View.OnClickListener{

    private TextView mOpinion;
    private TextView mFeeAmount, mDenomFeeType;
    private TextView mMemo;
    private Button mBeforeBtn, mConfirmBtn;

    public static AuthzVoteStep4Fragment newInstance() {
        return new AuthzVoteStep4Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_authz_vote_step4, container, false);
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
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mDenomFeeType, mFeeAmount);

        List<String> texts = Lists.newArrayList();
        getSActivity().mSelectedOpinion.forEach((key, value) -> {
            texts.add(String.format("# %s - %s", key, voteOptionConvert(value)));
        });
        String opinionText = StringUtils.join(texts, "\n");

        mOpinion.setText(opinionText);
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onAuthzVote();

        }
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

    private AuthzVoteActivity getSActivity() {
        return (AuthzVoteActivity) getBaseActivity();
    }
}
