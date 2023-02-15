package wannabit.io.cosmostaion.fragment.txs.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import wannabit.io.cosmostaion.activities.txs.common.VoteActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.databinding.FragmentVoteStep3Binding;
import wannabit.io.cosmostaion.utils.WDp;

public class VoteStep3Fragment extends BaseFragment implements View.OnClickListener {

    private FragmentVoteStep3Binding fragmentVoteStep3Binding;

    public static VoteStep3Fragment newInstance() {
        return new VoteStep3Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentVoteStep3Binding = FragmentVoteStep3Binding.inflate(inflater, container, false);
        fragmentVoteStep3Binding.btnBefore.setOnClickListener(this);
        fragmentVoteStep3Binding.btnConfirm.setOnClickListener(this);
        return fragmentVoteStep3Binding.getRoot();
    }

    @Override
    public void onRefreshTab() {
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), fragmentVoteStep3Binding.voteFeesType, fragmentVoteStep3Binding.voteFees);

        List<String> texts = Lists.newArrayList();
        getSActivity().mSelectedOpinion.forEach((key, value) -> texts.add(String.format("# %s - %s", key, voteOptionConvert(value))));
        String opinionText = StringUtils.join(texts, "\n");

        fragmentVoteStep3Binding.myOpinion.setText(opinionText);
        fragmentVoteStep3Binding.memo.setText(getSActivity().mTxMemo);
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
        if (v.equals(fragmentVoteStep3Binding.btnBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(fragmentVoteStep3Binding.btnConfirm)) {
            getSActivity().onStartVote();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentVoteStep3Binding = null;
    }

    private VoteActivity getSActivity() {
        return (VoteActivity) getBaseActivity();
    }
}