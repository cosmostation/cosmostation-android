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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.WebActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.IrisProposal;
import wannabit.io.cosmostaion.model.type.Proposal;
import wannabit.io.cosmostaion.task.FetchTask.IrisProposalTask;
import wannabit.io.cosmostaion.task.FetchTask.ProposalTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WUtil;

public class MainVoteFragment extends BaseFragment implements TaskListener {

    private SwipeRefreshLayout          mSwipeRefreshLayout;
    private RecyclerView                mRecyclerView;
    private TextView                    mEmptyProposal;
    private VoteAdapter                 mVoteAdapter;
    private IrisVoteAdapter             mIrisVoteAdapter;

    private ArrayList<Proposal>         mProposals = new ArrayList<>();
    private ArrayList<IrisProposal>     mIrisProposals = new ArrayList<>();

    public static MainVoteFragment newInstance(Bundle bundle) {
        MainVoteFragment fragment = new MainVoteFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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

        onFetchProposals();
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_accounts :
                getMainActivity().onShowTopAccountsView();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
        onFetchProposals();
    }

    private void onFetchProposals() {
        if(getMainActivity() == null || getMainActivity().mAccount == null) return;

        if (getMainActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            mVoteAdapter = new VoteAdapter();
            mRecyclerView.setAdapter(mVoteAdapter);
            new ProposalTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getMainActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            mIrisVoteAdapter = new IrisVoteAdapter();
            mRecyclerView.setAdapter(mIrisVoteAdapter);
            new IrisProposalTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }

    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(!isAdded()) return;
        if (result.taskType == BaseConstant.TASK_FETCH_ALL_PROPOSAL) {
            if(result.isSuccess) {
                ArrayList<Proposal> temp = (ArrayList<Proposal>)result.resultData;
                if(temp != null && temp.size() > 0) {
                    mProposals = temp;
                    WUtil.onSortingProposal(mProposals);
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

        } else if (result.taskType == BaseConstant.TASK_IRIS_PROPOSAL) {
            if(result.isSuccess) {
                ArrayList<IrisProposal> temp = (ArrayList<IrisProposal>)result.resultData;
                if(temp != null && temp.size() > 0) {
                    mIrisProposals = temp;
                    WUtil.onSortingIrisProposal(mIrisProposals);
                    mIrisVoteAdapter.notifyDataSetChanged();
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
            voteHolder.proposal_id.setText("# " + proposal.id);
            voteHolder.proposal_status.setText(proposal.proposal_status);
            voteHolder.proposal_title.setText(proposal.content.value.title);
            voteHolder.proposal_details.setText(proposal.content.value.description);
            if (proposal.proposal_status.equals("DepositPeriod")) {
                voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_deposit_img));
            } else if (proposal.proposal_status.equals("VotingPeriod")) {
                voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_voting_img));
            } else if (proposal.proposal_status.equals("Rejected")) {
                voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_rejected_img));
            } else if (proposal.proposal_status.equals("Passed")) {
                voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_passed_img));
            } else {
                voteHolder.proposal_status_img.setVisibility(View.GONE);
            }

            voteHolder.card_proposal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent webintent = new Intent(getBaseActivity(), WebActivity.class);
                    webintent.putExtra("voteId", proposal.id);
                    webintent.putExtra("chain", getMainActivity().mBaseChain.getChain());
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
            private TextView proposal_id, proposal_status, proposal_title, proposal_details;
            private ImageView proposal_status_img;

            public VoteHolder(@NonNull View itemView) {
                super(itemView);
                card_proposal               = itemView.findViewById(R.id.card_proposal);
                proposal_id                 = itemView.findViewById(R.id.proposal_id);
                proposal_status             = itemView.findViewById(R.id.proposal_status);
                proposal_title              = itemView.findViewById(R.id.proposal_title);
                proposal_details            = itemView.findViewById(R.id.proposal_details);
                proposal_status_img         = itemView.findViewById(R.id.proposal_status_img);

            }
        }
    }

    private class IrisVoteAdapter extends RecyclerView.Adapter<IrisVoteAdapter.VoteHolder> {

        @NonNull
        @Override
        public IrisVoteAdapter.VoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new IrisVoteAdapter.VoteHolder(getLayoutInflater().inflate(R.layout.item_proposal, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull IrisVoteAdapter.VoteHolder voteHolder, int position) {
            final IrisProposal proposal = mIrisProposals.get(position);
            voteHolder.proposal_id.setText("# " + proposal.value.BasicProposal.proposal_id);
            voteHolder.proposal_status.setText(proposal.value.BasicProposal.proposal_status);
            voteHolder.proposal_title.setText(proposal.value.BasicProposal.title);
            voteHolder.proposal_details.setText(proposal.value.BasicProposal.description);
            if (proposal.value.BasicProposal.proposal_status.equals("DepositPeriod")) {
                voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_deposit_img));
            } else if (proposal.value.BasicProposal.proposal_status.equals("VotingPeriod")) {
                voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_voting_img));
            } else if (proposal.value.BasicProposal.proposal_status.equals("Rejected")) {
                voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_rejected_img));
            } else if (proposal.value.BasicProposal.proposal_status.equals("Passed")) {
                voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_passed_img));
            } else {
                voteHolder.proposal_status_img.setVisibility(View.GONE);
            }

            voteHolder.card_proposal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent webintent = new Intent(getBaseActivity(), WebActivity.class);
                    webintent.putExtra("voteId", proposal.value.BasicProposal.proposal_id);
                    webintent.putExtra("chain", getMainActivity().mBaseChain.getChain());
                    startActivity(webintent);
                }
            });
        }


        @Override
        public int getItemCount() {
            return mIrisProposals.size();
        }

        public class VoteHolder extends RecyclerView.ViewHolder {
            private CardView card_proposal;
            private TextView proposal_id, proposal_status, proposal_title, proposal_details;
            private ImageView proposal_status_img;

            public VoteHolder(@NonNull View itemView) {
                super(itemView);
                card_proposal               = itemView.findViewById(R.id.card_proposal);
                proposal_id                 = itemView.findViewById(R.id.proposal_id);
                proposal_status             = itemView.findViewById(R.id.proposal_status);
                proposal_title              = itemView.findViewById(R.id.proposal_title);
                proposal_details            = itemView.findViewById(R.id.proposal_details);
                proposal_status_img         = itemView.findViewById(R.id.proposal_status_img);

            }
        }
    }



    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
    }



}