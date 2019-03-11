package wannabit.io.cosmostaion.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.WebActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Proposal;
import wannabit.io.cosmostaion.task.FetchTask.AllProposalTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class MainVoteFragment extends BaseFragment implements TaskListener {

    private SwipeRefreshLayout      mSwipeRefreshLayout;
    private RecyclerView            mRecyclerView;
    private TextView                mEmptyProposal;
    private VoteAdapter             mVoteAdapter;

    private ArrayList<Proposal>     mProposals = new ArrayList<>();

    public static MainVoteFragment newInstance(Bundle bundle) {
        MainVoteFragment fragment = new MainVoteFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_vote, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mEmptyProposal           = rootView.findViewById(R.id.empty_proposal);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onFetchProposals();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mVoteAdapter = new VoteAdapter();
        mRecyclerView.setAdapter(mVoteAdapter);
        onFetchProposals();
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
        onFetchProposals();
    }

    private void onFetchProposals() {
        if(getMainActivity() == null || getMainActivity().mAccount == null) return;
        WLog.w("onFetchProposals : " + getMainActivity().mAccount.address);
        WLog.w("onFetchProposals : " + getMainActivity().mAccount.baseChain);
        new AllProposalTask(getBaseApplication(), this, BaseChain.getChain(getMainActivity().mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(!isAdded()) return;
        if (result.taskType == BaseConstant.TASK_FETCH_ALL_PROPOSAL) {
            if(result.isSuccess) {
                ArrayList<Proposal> temp = (ArrayList<Proposal>)result.resultData;
                if(temp != null && temp.size() > 0) {
                    mProposals = temp;
                    mVoteAdapter.notifyDataSetChanged();
                    mEmptyProposal.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                } else {
                    mEmptyProposal.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                }
            } else {
                mEmptyProposal.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            }

        }
        mSwipeRefreshLayout.setRefreshing(false);

    }

    private class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.VoteHolder> {
        @NonNull
        @Override
        public VoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new VoteHolder(getLayoutInflater().inflate(R.layout.item_proposal, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull VoteHolder voteHolder, int position) {
            final Proposal proposal = mProposals.get(position);
            voteHolder.proposal_id.setText(proposal.value.proposal_id);
            voteHolder.proposal_status.setText(proposal.value.proposal_status);
            voteHolder.proposal_title.setText(proposal.value.title);
            voteHolder.proposal_details.setText(proposal.value.description);
            voteHolder.proposal_submit_time.setText(WDp.getTimeformat(getContext(), proposal.value.submit_time));
            if(!proposal.value.voting_start_time.startsWith("0")) {
                voteHolder.proposal_voting_start_time.setText(WDp.getTimeformat(getContext(), proposal.value.voting_start_time));
            } else {
                voteHolder.proposal_voting_start_time.setText("n/a");
            }
            if(!proposal.value.voting_end_time.startsWith("0")) {
                voteHolder.proposal_voting_end_time.setText(WDp.getTimeformat(getContext(), proposal.value.voting_end_time));
            } else {
                voteHolder.proposal_voting_end_time.setText("n/a");
            }
            voteHolder.card_proposal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent webintent = new Intent(getBaseActivity(), WebActivity.class);
                    webintent.putExtra("voteId", proposal.value.proposal_id);
                    startActivity(webintent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mProposals.size();
        }

        public class VoteHolder extends RecyclerView.ViewHolder {
            private CardView card_proposal;
            private TextView proposal_id, proposal_status, proposal_title, proposal_details, proposal_submit_time,
                    proposal_voting_start_time, proposal_voting_end_time;

            public VoteHolder(@NonNull View itemView) {
                super(itemView);
                card_proposal               = itemView.findViewById(R.id.card_proposal);
                proposal_id                 = itemView.findViewById(R.id.proposal_id);
                proposal_status             = itemView.findViewById(R.id.proposal_status);
                proposal_title              = itemView.findViewById(R.id.proposal_title);
                proposal_details            = itemView.findViewById(R.id.proposal_details);
                proposal_submit_time        = itemView.findViewById(R.id.proposal_submit_time);
                proposal_voting_start_time  = itemView.findViewById(R.id.proposal_voting_start_time);
                proposal_voting_end_time    = itemView.findViewById(R.id.proposal_voting_end_time);

            }
        }
    }

    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
    }
}


/*
public class MainVoteFragment extends BaseFragment {

    public static MainVoteFragment newInstance(Bundle bundle) {
        MainVoteFragment fragment = new MainVoteFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_vote, container, false);
        return rootView;
    }
}
*/