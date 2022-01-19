package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.protobuf2.Any;

import java.util.ArrayList;
import java.util.List;

import cosmos.gov.v1beta1.Gov;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.ProposalsGrpcTask;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_PROPOSALS;

public class VoteListActivity extends BaseActivity implements TaskListener {

    private Toolbar             mToolbar;
    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;
    private TextView            mEmptyProposal;
    private RelativeLayout      mLoadingLayer;

    private GrpcProposalsAdapter        mGrpcProposalsAdapter;

    private ArrayList<Gov.Proposal>                         mGrpcProposals = new ArrayList<>();
    private ArrayList<shentu.gov.v1alpha1.Gov.Proposal>     mCtkGrpcProposals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_list);
        mToolbar                = findViewById(R.id.tool_bar);
        mSwipeRefreshLayout     = findViewById(R.id.layer_refresher);
        mRecyclerView           = findViewById(R.id.recycler);
        mEmptyProposal          = findViewById(R.id.empty_proposal);
        mLoadingLayer           = findViewById(R.id.loadingLayer);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        if (mAccount == null) return;
        mGrpcProposalsAdapter = new GrpcProposalsAdapter();
        mRecyclerView.setAdapter(mGrpcProposalsAdapter);
        new ProposalsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        if (result.taskType == TASK_GRPC_FETCH_PROPOSALS) {
            mGrpcProposals.clear();
            mCtkGrpcProposals.clear();
            if (mBaseChain.equals(CERTIK_MAIN)) {
                List<shentu.gov.v1alpha1.Gov.Proposal> temp = (List<shentu.gov.v1alpha1.Gov.Proposal>) result.resultData;
                mLoadingLayer.setVisibility(View.GONE);
                if (temp != null && temp.size() > 0) {
                    mCtkGrpcProposals.addAll(temp);
                    WUtil.onSortingCtkGrpcProposals(mCtkGrpcProposals);
                    mGrpcProposalsAdapter.notifyDataSetChanged();
                    mRecyclerView.setVisibility(View.VISIBLE);
                }
            } else {
                List<Gov.Proposal> temp = (List<Gov.Proposal>)result.resultData;
                mLoadingLayer.setVisibility(View.GONE);
                if (temp != null && temp.size() > 0) {
                    mGrpcProposals.addAll(temp);
                    WUtil.onSortingGrpcProposals(mGrpcProposals);
                    mGrpcProposalsAdapter.notifyDataSetChanged();
                    mRecyclerView.setVisibility(View.VISIBLE);
                } else {
                    mEmptyProposal.setVisibility(View.VISIBLE);
                }
            }
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private class GrpcProposalsAdapter extends RecyclerView.Adapter<GrpcProposalsAdapter.VoteHolder> {
        @NonNull
        @Override
        public VoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new GrpcProposalsAdapter.VoteHolder(getLayoutInflater().inflate(R.layout.item_proposal, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull VoteHolder voteHolder, int position) {
            if (mBaseChain.equals(CERTIK_MAIN)) {
                final shentu.gov.v1alpha1.Gov.Proposal proposal = mCtkGrpcProposals.get(position);
                voteHolder.proposal_id.setText("# " + proposal.getProposalId());
                if (proposal.getStatus().equals(shentu.gov.v1alpha1.Gov.ProposalStatus.PROPOSAL_STATUS_DEPOSIT_PERIOD)) {
                    voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_deposit_img));
                    voteHolder.proposal_status.setText("DepositPeriod");
                } else if (proposal.getStatus().equals(shentu.gov.v1alpha1.Gov.ProposalStatus.PROPOSAL_STATUS_CERTIFIER_VOTING_PERIOD) ||
                            proposal.getStatus().equals(shentu.gov.v1alpha1.Gov.ProposalStatus.PROPOSAL_STATUS_VALIDATOR_VOTING_PERIOD)) {
                    voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_voting_img));
                    voteHolder.proposal_status.setText("VotingPeriod");
                } else if (proposal.getStatus().equals(shentu.gov.v1alpha1.Gov.ProposalStatus.PROPOSAL_STATUS_REJECTED)) {
                    voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_rejected_img));
                    voteHolder.proposal_status.setText("Rejected");
                } else if (proposal.getStatus().equals(shentu.gov.v1alpha1.Gov.ProposalStatus.PROPOSAL_STATUS_PASSED)) {
                    voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_passed_img));
                    voteHolder.proposal_status.setText("Passed");
                } else {
                    voteHolder.proposal_status_img.setVisibility(View.GONE);
                    voteHolder.proposal_status.setText("unKnown");
                }

            } else {
                final Gov.Proposal proposal = mGrpcProposals.get(position);
                voteHolder.proposal_id.setText("# " + proposal.getProposalId());
                if (proposal.getStatus().equals(Gov.ProposalStatus.PROPOSAL_STATUS_DEPOSIT_PERIOD)) {
                    voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_deposit_img));
                    voteHolder.proposal_status.setText("DepositPeriod");
                } else if (proposal.getStatus().equals(Gov.ProposalStatus.PROPOSAL_STATUS_VOTING_PERIOD)) {
                    voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_voting_img));
                    voteHolder.proposal_status.setText("VotingPeriod");
                } else if (proposal.getStatus().equals(Gov.ProposalStatus.PROPOSAL_STATUS_REJECTED)) {
                    voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_rejected_img));
                    voteHolder.proposal_status.setText("Rejected");
                } else if (proposal.getStatus().equals(Gov.ProposalStatus.PROPOSAL_STATUS_PASSED)) {
                    voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_passed_img));
                    voteHolder.proposal_status.setText("Passed");
                } else {
                    voteHolder.proposal_status_img.setVisibility(View.GONE);
                    voteHolder.proposal_status.setText("unKnown");
                }

            }
            Any proposalContent = null;
            if (mBaseChain.equals(CERTIK_MAIN)) {
                proposalContent = mCtkGrpcProposals.get(position).getContent();
            } else {
                proposalContent = mGrpcProposals.get(position).getContent();
            }
            WUtil.getVoteListType(proposalContent, voteHolder.proposal_title, voteHolder.proposal_details);

            voteHolder.card_proposal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mBaseChain.equals(CERTIK_MAIN)) {
                        final shentu.gov.v1alpha1.Gov.Proposal proposal = mCtkGrpcProposals.get(position);
                        if (proposal.getStatus().equals(shentu.gov.v1alpha1.Gov.ProposalStatus.PROPOSAL_STATUS_DEPOSIT_PERIOD) ||
                                proposal.getStatus().equals(shentu.gov.v1alpha1.Gov.ProposalStatus.PROPOSAL_STATUS_VALIDATOR_VOTING_PERIOD)) {
                            Intent voteIntent = new Intent(VoteListActivity.this, VoteDetailsActivity.class);
                            voteIntent.putExtra("proposalId", String.valueOf(proposal.getProposalId()));
                            startActivity(voteIntent);
                        } else {
                            String url  = WUtil.getExplorer(mBaseChain) + "proposals/" + proposal.getProposalId();
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(intent);
                        }

                    } else {
                        final Gov.Proposal proposal = mGrpcProposals.get(position);
                        if (proposal.getStatus().equals(Gov.ProposalStatus.PROPOSAL_STATUS_DEPOSIT_PERIOD) ||
                                proposal.getStatus().equals(Gov.ProposalStatus.PROPOSAL_STATUS_VOTING_PERIOD) ) {
                            Intent voteIntent = new Intent(VoteListActivity.this, VoteDetailsActivity.class);
                            voteIntent.putExtra("proposalId", String.valueOf(proposal.getProposalId()));
                            startActivity(voteIntent);
                        } else {
                            String url  = WUtil.getExplorer(mBaseChain) + "proposals/" + proposal.getProposalId();
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(intent);
                        }
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            if (mBaseChain.equals(CERTIK_MAIN)) { return mCtkGrpcProposals.size(); }
            else { return mGrpcProposals.size(); }
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
