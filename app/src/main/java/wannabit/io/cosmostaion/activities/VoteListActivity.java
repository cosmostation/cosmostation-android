package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MINTSCAN_PROPOSAL_LIST;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.res.ResProposal;
import wannabit.io.cosmostaion.task.FetchTask.MintScanProposalListTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class VoteListActivity extends BaseActivity implements TaskListener {

    private Toolbar mToolbar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private TextView mEmptyProposal;
    private RelativeLayout mLoadingLayer;

    private GrpcProposalsAdapter mGrpcProposalsAdapter;

    // proposal api
    private ArrayList<ResProposal> mApiProposalList = new ArrayList<>();

    private String mChain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_list);
        mToolbar = findViewById(R.id.tool_bar);
        mSwipeRefreshLayout = findViewById(R.id.layer_refresher);
        mRecyclerView = findViewById(R.id.recycler);
        mEmptyProposal = findViewById(R.id.empty_proposal);
        mLoadingLayer = findViewById(R.id.loadingLayer);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChain = WDp.getChainNameByBaseChain(mBaseChain);

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
        mGrpcProposalsAdapter = new GrpcProposalsAdapter();
        mRecyclerView.setAdapter(mGrpcProposalsAdapter);

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
        mApiProposalList.clear();
        new MintScanProposalListTask(getBaseApplication(), this, mChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        if (result.taskType == TASK_FETCH_MINTSCAN_PROPOSAL_LIST) {
            mLoadingLayer.setVisibility(View.GONE);
            if (result.isSuccess) {
                ArrayList<ResProposal> temp = (ArrayList<ResProposal>) result.resultData;
                if (temp != null && temp.size() > 0) {
                    mApiProposalList = temp;
                    onSortingProposal(mApiProposalList, mBaseChain);
                    mGrpcProposalsAdapter.notifyDataSetChanged();
                    mRecyclerView.setVisibility(View.VISIBLE);
                }
            } else {
                mEmptyProposal.setVisibility(View.VISIBLE);
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
            final ResProposal proposal = mApiProposalList.get(position);
            voteHolder.proposal_id.setText("# " + proposal.id);
            if (proposal.proposal_status.contains("DEPOSIT")) {
                voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_deposit_img));
                voteHolder.proposal_status.setText("DepositPeriod");
            } else if (proposal.proposal_status.contains("VOTING")) {
                voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_voting_img));
                voteHolder.proposal_status.setText("VotingPeriod");
            } else if (proposal.proposal_status.contains("REJECTED")) {
                voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_rejected_img));
                voteHolder.proposal_status.setText("Rejected");
            } else if (proposal.proposal_status.contains("PASSED")) {
                voteHolder.proposal_status_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_passed_img));
                voteHolder.proposal_status.setText("Passed");
            } else {
                voteHolder.proposal_status_img.setVisibility(View.GONE);
                voteHolder.proposal_status.setText(R.string.str_unknown);
            }

            voteHolder.proposal_title.setText(proposal.title);
            voteHolder.proposal_details.setText(proposal.description);

            voteHolder.card_proposal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (proposal.proposal_status.contains("PASSED") ||
                            proposal.proposal_status.contains("REJECTED")) {
                        String url = WUtil.getExplorer(mBaseChain) + "proposals/" + proposal.id;
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    } else {
                        Intent voteIntent = new Intent(VoteListActivity.this, VoteDetailsActivity.class);
                        voteIntent.putExtra("proposalId", String.valueOf(proposal.id));
                        startActivity(voteIntent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mApiProposalList.size();
        }

        public class VoteHolder extends RecyclerView.ViewHolder {
            private CardView card_proposal;
            private TextView proposal_id, proposal_status, proposal_title, proposal_details;
            private ImageView proposal_status_img;

            public VoteHolder(@NonNull View itemView) {
                super(itemView);
                card_proposal = itemView.findViewById(R.id.card_proposal);
                proposal_id = itemView.findViewById(R.id.proposal_id);
                proposal_status = itemView.findViewById(R.id.proposal_status);
                proposal_title = itemView.findViewById(R.id.proposal_title);
                proposal_details = itemView.findViewById(R.id.proposal_details);
                proposal_status_img = itemView.findViewById(R.id.proposal_status_img);

            }
        }
    }

    public void onSortingProposal(ArrayList<ResProposal> proposals, BaseChain chain) {
        Collections.sort(proposals, new Comparator<ResProposal>() {
            @Override
            public int compare(ResProposal o1, ResProposal o2) {
                if (o1.id < o2.id) return 1;
                else if (o1.id > o2.id) return -1;
                else return 0;
            }
        });
    }
}
