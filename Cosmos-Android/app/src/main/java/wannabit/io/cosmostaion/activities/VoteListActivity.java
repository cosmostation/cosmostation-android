package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.model.type.IrisProposal;
import wannabit.io.cosmostaion.model.type.Proposal;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.task.FetchTask.IrisProposalTask;
import wannabit.io.cosmostaion.task.FetchTask.ProposalTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WUtil;

public class VoteListActivity extends BaseActivity implements TaskListener {

    private Toolbar             mToolbar;

    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;
    private TextView            mEmptyProposal;
    private VoteAdapter         mVoteAdapter;
    private IrisVoteAdapter     mIrisVoteAdapter;


    private ArrayList<Validator> mTopValidators = new ArrayList<>();
    private ArrayList<Proposal> mProposals = new ArrayList<>();
    private ArrayList<IrisProposal> mIrisProposals = new ArrayList<>();
    private String mBondedToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_list);
        mToolbar                = findViewById(R.id.tool_bar);
        mSwipeRefreshLayout     = findViewById(R.id.layer_refresher);
        mRecyclerView           = findViewById(R.id.recycler);
        mEmptyProposal          = findViewById(R.id.empty_proposal);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTopValidators = getIntent().getParcelableArrayListExtra("topValidators");
        mBondedToken = getIntent().getStringExtra("bondedToken");
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onFetchProposals();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        onFetchProposals();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onFetchProposals() {
        if(mAccount == null) return;
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN) || mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            mVoteAdapter = new VoteAdapter();
            mRecyclerView.setAdapter(mVoteAdapter);
            new ProposalTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            mIrisVoteAdapter = new IrisVoteAdapter();
            mRecyclerView.setAdapter(mIrisVoteAdapter);
            new IrisProposalTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }

    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_FETCH_ALL_PROPOSAL) {
            if(result.isSuccess) {
                ArrayList<Proposal> temp = (ArrayList<Proposal>)result.resultData;
                if(temp != null && temp.size() > 0) {
                    mProposals = temp;
                    WUtil.onSortingProposal(mProposals, mBaseChain);
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
        public VoteAdapter.VoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new VoteAdapter.VoteHolder(getLayoutInflater().inflate(R.layout.item_proposal, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull VoteAdapter.VoteHolder voteHolder, int position) {
            final Proposal proposal = mProposals.get(position);
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
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
                        if (Integer.parseInt(proposal.id) >= 25) {
                            Intent voteIntent = new Intent(VoteListActivity.this, VoteDetailsActivity.class);
                            voteIntent.putExtra("proposalId", proposal.id);
                            startActivity(voteIntent);

                        } else {
                            Intent webintent = new Intent(VoteListActivity.this, WebActivity.class);
                            webintent.putExtra("voteId", proposal.id);
                            webintent.putExtra("chain", mAccount.baseChain);
                            startActivity(webintent);
                        }
                    }
                });


            } else if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
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
                        if (Integer.parseInt(proposal.id) >= 3) {
                            Intent voteIntent = new Intent(VoteListActivity.this, VoteDetailsActivity.class);
                            voteIntent.putExtra("proposalId", proposal.id);
                            startActivity(voteIntent);

                        } else {
                            Intent webintent = new Intent(VoteListActivity.this, WebActivity.class);
                            webintent.putExtra("voteId", proposal.id);
                            webintent.putExtra("chain", mAccount.baseChain);
                            startActivity(webintent);

                        }
                    }
                });

            }
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
            voteHolder.proposal_id.setText("# " + proposal.value.basicProposal.proposal_id);
            voteHolder.proposal_status.setText(proposal.value.basicProposal.proposal_status);
            voteHolder.proposal_title.setText(proposal.value.basicProposal.title);
            voteHolder.proposal_details.setText(proposal.value.basicProposal.description);
            if (proposal.value.basicProposal.proposal_status.equals("DepositPeriod")) {
                voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_deposit_img));
            } else if (proposal.value.basicProposal.proposal_status.equals("VotingPeriod")) {
                voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_voting_img));
            } else if (proposal.value.basicProposal.proposal_status.equals("Rejected")) {
                voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_rejected_img));
            } else if (proposal.value.basicProposal.proposal_status.equals("Passed")) {
                voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_passed_img));
            } else {
                voteHolder.proposal_status_img.setVisibility(View.GONE);
            }

            voteHolder.card_proposal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent voteIntent = new Intent(VoteListActivity.this, VoteDetailsActivity.class);
                    voteIntent.putExtra("proposalId", proposal.value.basicProposal.proposal_id);
                    startActivity(voteIntent);
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

}
