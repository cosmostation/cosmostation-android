package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cosmos.gov.v1beta1.Gov;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Proposal;
import wannabit.io.cosmostaion.model.type.Tally;
import wannabit.io.cosmostaion.model.type.Vote;
import wannabit.io.cosmostaion.task.FetchTask.MyVoteCheckTask;
import wannabit.io.cosmostaion.task.FetchTask.ProposalDetailTask;
import wannabit.io.cosmostaion.task.FetchTask.ProposalProposerTask;
import wannabit.io.cosmostaion.task.FetchTask.ProposalTallyTask;
import wannabit.io.cosmostaion.task.FetchTask.ProposalVotedListTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.ProposalDetailGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.ProposalMyVoteGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.ProposalTallyGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.ProposalVoterListGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MY_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_PROPOSAL_DETAIL;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_PROPOSAL_PROPOSER;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_PROPOSAL_TALLY;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_PROPOSAL_VOTED;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_PROPOSAL_DETAIL;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_PROPOSAL_MY_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_PROPOSAL_TALLY;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_PROPOSAL_VOTER_LIST;
import static wannabit.io.cosmostaion.model.type.Proposal.PROPOSAL_VOTING;

public class VoteDetailsActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar             mToolbar;
    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;
    private RelativeLayout      mLoadingLayer;
    private Button              mVoteBtn;

    private VoteDetailsAdapter  mVoteDetailsAdapter;


    private String              mProposalId;

    private Proposal            mProposal;
    private String              mProposer;
    private Tally               mTally;
    private ArrayList<Vote>     mVotes = new ArrayList<>();
    private Vote                mMyVote;

    //gRPC
    private Gov.Proposal        mProposalDetail_gRPC;
    private Gov.TallyResult     mTally_gRPC;
    private ArrayList<Gov.Vote> mAllVoter_gRPC = new ArrayList<>();
    private Gov.Vote            mMyVote_gRPC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_details);
        mToolbar            = findViewById(R.id.tool_bar);
        mSwipeRefreshLayout = findViewById(R.id.layer_refresher);
        mRecyclerView       = findViewById(R.id.recycler);
        mLoadingLayer       = findViewById(R.id.loadingLayer);
        mVoteBtn            = findViewById(R.id.btn_action);
        mVoteBtn.setOnClickListener(this);

        mProposalId         = getIntent().getStringExtra("proposalId");
        mAccount            = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain          = BaseChain.getChain(mAccount.baseChain);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onFetch();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mVoteDetailsAdapter = new VoteDetailsAdapter();
        mRecyclerView.setAdapter(mVoteDetailsAdapter);

        onFetch();
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

    private void onUpdateView() {
        mSwipeRefreshLayout.setRefreshing(false);
        mVoteDetailsAdapter.notifyDataSetChanged();
        mLoadingLayer.setVisibility(View.GONE);
        mVoteBtn.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }



    @Override
    public void onClick(View v) {
        if (v.equals(mVoteBtn)) {
            if (!mAccount.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                add.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                return;
            }
            if (isGRPC(mBaseChain)) {
                BigDecimal mainDenomAvailable = getBaseDao().getAvailable(WDp.mainDenom(mBaseChain));
                BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_VOTE, 0);

                if (!mProposalDetail_gRPC.getStatus().equals(Gov.ProposalStatus.PROPOSAL_STATUS_VOTING_PERIOD)) {
                    Toast.makeText(getBaseContext(), getString(R.string.error_not_voting_period), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (getBaseDao().getDelegationSum().compareTo(BigDecimal.ZERO) <= 0) {
                    Toast.makeText(getBaseContext(), getString(R.string.error_no_bonding_no_vote), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mainDenomAvailable.compareTo(feeAmount) < 0) {
                    Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                    return;
                }


            } else {
                BigDecimal mainDenomAvailable = getBaseDao().availableAmount(WDp.mainDenom(mBaseChain));
                BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_VOTE, 0);
                if (!mProposal.proposal_status.equals(PROPOSAL_VOTING)) {
                    Toast.makeText(getBaseContext(), getString(R.string.error_not_voting_period), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (getBaseDao().delegatedSumAmount().compareTo(BigDecimal.ZERO) <= 0) {
                    Toast.makeText(getBaseContext(), getString(R.string.error_no_bonding_no_vote), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mainDenomAvailable.compareTo(feeAmount) < 0) {
                    Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            Intent intent = new Intent(VoteDetailsActivity.this, VoteActivity.class);
            intent.putExtra("proposalId", mProposalId);
            intent.putExtra("title", getProposalTitle());
            intent.putExtra("proposer", getProposer());
            startActivity(intent);
        }
    }

    private String getProposalTitle() {
        if (isGRPC(mBaseChain)) {
            return  "# " + mProposalDetail_gRPC.getProposalId() + ". " + WDp.getProposalTitle(mProposalDetail_gRPC);
        } else {
            return mProposal.getTitle();
        }
    }

    private String getProposer() {
        if (isGRPC(mBaseChain)) {
            return "";
        } else {
            return mProposer;
        }
    }

    public void onFetch() {
        if (isGRPC(mBaseChain)) {
            this.mTaskCount = 4;
            new ProposalDetailGrpcTask(getBaseApplication(), this, mBaseChain, mProposalId).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ProposalTallyGrpcTask(getBaseApplication(), this, mBaseChain, mProposalId).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ProposalVoterListGrpcTask(getBaseApplication(), this, mBaseChain, mProposalId).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ProposalMyVoteGrpcTask(getBaseApplication(), this, mBaseChain, mProposalId, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else {
            this.mTaskCount = 5;
            new ProposalDetailTask(getBaseApplication(), this, mProposalId, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ProposalTallyTask(getBaseApplication(), this, mProposalId, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ProposalVotedListTask(getBaseApplication(), this, mProposalId, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new MyVoteCheckTask(getBaseApplication(), this, mProposalId, mAccount.address, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ProposalProposerTask(getBaseApplication(), this, mProposalId, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if (result.taskType == TASK_FETCH_PROPOSAL_DETAIL) {
            mProposal = (Proposal)result.resultData;

        } else if (result.taskType == TASK_FETCH_PROPOSAL_TALLY) {
            mTally = (Tally) result.resultData;

        } else if (result.taskType == TASK_FETCH_PROPOSAL_VOTED) {
            mVotes = (ArrayList<Vote>)result.resultData;

        } else if (result.taskType == TASK_FETCH_MY_VOTE) {
            mMyVote = (Vote)result.resultData;

        } else if (result.taskType == TASK_FETCH_PROPOSAL_PROPOSER) {
            mProposer = (String)result.resultData;

        }

        // gRPC
        else if (result.taskType == TASK_GRPC_FETCH_PROPOSAL_DETAIL) {
            if (result.resultData != null) {
                mProposalDetail_gRPC = (Gov.Proposal)result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_PROPOSAL_TALLY) {
            if (result.resultData != null) {
                mTally_gRPC = (Gov.TallyResult)result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_PROPOSAL_VOTER_LIST) {
            if (result.resultData != null) {
                mAllVoter_gRPC = new ArrayList((List<Gov.Vote>)result.resultData);
            }

        } else if (result.taskType == TASK_GRPC_FETCH_PROPOSAL_MY_VOTE) {
            if (result.resultData != null) {
                mMyVote_gRPC = (Gov.Vote)result.resultData;
            }
        }
        if (mTaskCount == 0) {
            onUpdateView();
        }
    }



    private class VoteDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_TX_INFO = 0;
        private static final int TYPE_TX_TALLY = 1;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_TX_INFO) {
                return new VoteInfoHolder(getLayoutInflater().inflate(R.layout.item_vote_info, viewGroup, false));
            } else {
                return new VoteTallyHolder(getLayoutInflater().inflate(R.layout.item_vote_tally, viewGroup, false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (position == 0) {
                onBindVoteInfo(viewHolder);
            } else if (position == 1) {
                onBindVoteTally(viewHolder);
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_TX_INFO;
            } else {
                return TYPE_TX_TALLY;
            }
        }

        private void onBindVoteInfo(RecyclerView.ViewHolder viewHolder) {
            final VoteInfoHolder holder = (VoteInfoHolder)viewHolder;
            if (isGRPC(mBaseChain) && mProposalDetail_gRPC != null) {
                holder.itemStatusImg.setImageDrawable(WDp.getProposalStatusImg(getBaseContext(), mProposalDetail_gRPC));
                holder.itemStatusTxt.setText(WDp.getProposalStatusTxt(getBaseContext(), mProposalDetail_gRPC));
                holder.itemTitle.setText(WDp.getProposalTitle(mProposalDetail_gRPC));
                holder.itemProposerLayer.setVisibility(View.GONE);
                holder.itemType.setText(WDp.getProposalType(mProposalDetail_gRPC));
                holder.itemStartTime.setText(WDp.getProposalStartTime(getBaseContext(), mProposalDetail_gRPC));
                holder.itemFinishTime.setText(WDp.geProposalEndTime(getBaseContext(), mProposalDetail_gRPC));
                holder.itemMsg.setText(WDp.getProposalDescription(mProposalDetail_gRPC));

            } else if (mProposal != null) {
                holder.itemStatusImg.setImageDrawable(mProposal.getStatusImg(getBaseContext()));
                holder.itemStatusTxt.setText(mProposal.proposal_status);
                holder.itemTitle.setText(mProposal.getTitle());
                holder.itemProposer.setText(mProposer);
                holder.itemType.setText(mProposal.getType());
                holder.itemStartTime.setText(mProposal.getStartTime(getBaseContext()));
                holder.itemFinishTime.setText(mProposal.getEndTime(getBaseContext()));
                holder.itemMsg.setText(mProposal.content.value.description);
                if (mProposal.content.value.amount != null) {
                    ArrayList<Coin> requestCoin = mProposal.content.value.amount;
                    WDp.showCoinDp(getBaseContext(), requestCoin.get(0), holder.itemRequestAmountDenom, holder.itemRequestAmount, mBaseChain);
                    holder.itemRequestLayer.setVisibility(View.VISIBLE);
                }
            }

            holder.itemWebBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onExplorerLink();
                }
            });

            holder.itemExpendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.itemMsg.getMaxLines() == 500) {
                        holder.itemMsg.setMaxLines(3);
                        holder.itemExpendBtn.setImageDrawable(getDrawable(R.drawable.arrow_down_gr));

                    } else {
                        holder.itemMsg.setMaxLines(500);
                        holder.itemExpendBtn.setImageDrawable(getDrawable(R.drawable.arrow_up_gr));
                    }
                    mVoteDetailsAdapter.notifyDataSetChanged();
                }
            });

        }

        private void onBindVoteTally(RecyclerView.ViewHolder viewHolder) {
            final VoteTallyHolder holder = (VoteTallyHolder)viewHolder;
            if (isGRPC(mBaseChain) && mTally_gRPC != null) {
                holder.itemYesProgress.setProgress(WDp.getYesPer(mTally_gRPC).intValue());
                holder.itemNoProgress.setProgress(WDp.getNoPer(mTally_gRPC).intValue());
                holder.itemVetoProgress.setProgress(WDp.getVetoPer(mTally_gRPC).intValue());
                holder.itemAbstainProgress.setProgress(WDp.getAbstainPer(mTally_gRPC).intValue());

                holder.itemYesRate.setText(WDp.getDpString(WDp.getYesPer(mTally_gRPC).toPlainString() + "%", 3));
                holder.itemNoRate.setText(WDp.getDpString(WDp.getNoPer(mTally_gRPC).toPlainString() + "%", 3));
                holder.itemVetoRate.setText(WDp.getDpString(WDp.getVetoPer(mTally_gRPC).toPlainString() + "%", 3));
                holder.itemAbstainRate.setText(WDp.getDpString(WDp.getAbstainPer(mTally_gRPC).toPlainString() + "%", 3));

                if (mProposalDetail_gRPC != null && mProposalDetail_gRPC.getStatus().equals(Gov.ProposalStatus.PROPOSAL_STATUS_VOTING_PERIOD)) {
                    onDisplayVote_V1(holder);
                    holder.itemTurnoutLayer.setVisibility(View.VISIBLE);
                    holder.itemQuorum.setText(WDp.getDpString(WDp.systemQuorum(mBaseChain) + "%", 3));
                    holder.itemTurnout.setText(WDp.getDpString(WDp.getTurnout(getBaseDao(), mTally_gRPC).setScale(2).toPlainString() + "%", 3));
                }

                if (mMyVote_gRPC != null) {
                    if (mMyVote_gRPC.getOption().equals(Gov.VoteOption.VOTE_OPTION_YES)) {
                        holder.itemYesDone.setVisibility(View.VISIBLE);
                        holder.itemYesCard.setBackground(getDrawable(R.drawable.box_vote_voted));

                    } else if (mMyVote_gRPC.getOption().equals(Gov.VoteOption.VOTE_OPTION_NO)) {
                        holder.itemNoDone.setVisibility(View.VISIBLE);
                        holder.itemNoCard.setBackground(getDrawable(R.drawable.box_vote_voted));

                    } else if (mMyVote_gRPC.getOption().equals(Gov.VoteOption.VOTE_OPTION_NO_WITH_VETO)) {
                        holder.itemVetoDone.setVisibility(View.VISIBLE);
                        holder.itemVetoCard.setBackground(getDrawable(R.drawable.box_vote_voted));

                    } else if (mMyVote_gRPC.getOption().equals(Gov.VoteOption.VOTE_OPTION_ABSTAIN)) {
                        holder.itemAbstainDone.setVisibility(View.VISIBLE);
                        holder.itemAbstainCard.setBackground(getDrawable(R.drawable.box_vote_voted));
                    }
                }

            }  else if (mTally != null) {
                holder.itemYesProgress.setProgress(mTally.getYesPer().intValue());
                holder.itemNoProgress.setProgress(mTally.getNoPer().intValue());
                holder.itemVetoProgress.setProgress(mTally.getVetoPer().intValue());
                holder.itemAbstainProgress.setProgress(mTally.getAbstainPer().intValue());

                holder.itemYesRate.setText(WDp.getDpString(mTally.getYesPer().toPlainString() + "%", 3));
                holder.itemNoRate.setText(WDp.getDpString(mTally.getNoPer().toPlainString() + "%", 3));
                holder.itemVetoRate.setText(WDp.getDpString(mTally.getVetoPer().toPlainString() + "%", 3));
                holder.itemAbstainRate.setText(WDp.getDpString(mTally.getAbstainPer().toPlainString() + "%", 3));

                if (mProposal != null && mProposal.proposal_status.equals(PROPOSAL_VOTING)) {
                    onDisplayVote(holder);
                    holder.itemTurnoutLayer.setVisibility(View.VISIBLE);
                    holder.itemQuorum.setText(WDp.getDpString(WDp.systemQuorum(mBaseChain) + "%", 3));
                    holder.itemTurnout.setText(WDp.getDpString(mTally.getTurnout(getBaseDao().mStakingPool).toPlainString() + "%", 3));
                }

                if (mMyVote != null) {
                    holder.itemYesCard.setBackground(getDrawable(R.drawable.box_vote_quorum));
                    holder.itemNoCard.setBackground(getDrawable(R.drawable.box_vote_quorum));
                    holder.itemVetoCard.setBackground(getDrawable(R.drawable.box_vote_quorum));
                    holder.itemAbstainCard.setBackground(getDrawable(R.drawable.box_vote_quorum));

                    if (mMyVote.option.equals(Vote.OPTION_YES)) {
                        holder.itemYesDone.setVisibility(View.VISIBLE);
                        holder.itemYesCard.setBackground(getDrawable(R.drawable.box_vote_voted));

                    } else if (mMyVote.option.equals(Vote.OPTION_NO)) {
                        holder.itemNoDone.setVisibility(View.VISIBLE);
                        holder.itemNoCard.setBackground(getDrawable(R.drawable.box_vote_voted));

                    } else if (mMyVote.option.equals(Vote.OPTION_VETO)) {
                        holder.itemVetoDone.setVisibility(View.VISIBLE);
                        holder.itemVetoCard.setBackground(getDrawable(R.drawable.box_vote_voted));

                    } else if (mMyVote.option.equals(Vote.OPTION_ABSTAIN)) {
                        holder.itemAbstainDone.setVisibility(View.VISIBLE);
                        holder.itemAbstainCard.setBackground(getDrawable(R.drawable.box_vote_voted));

                    }
                }
            }
        }

        private void onExplorerLink() {
            String url;
            if (mBaseChain.equals(BaseChain.CERTIK_MAIN) || mBaseChain.equals(BaseChain.SECRET_MAIN) || mBaseChain.equals(BaseChain.CERTIK_TEST)) {
                url = WUtil.getExplorer(mBaseChain) + "governance/proposals/" + mProposalId;
            } else {
                url  = WUtil.getExplorer(mBaseChain) + "proposals/" + mProposalId;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }

        private void onDisplayVote(VoteTallyHolder holder) {
            holder.itemYesCnt.setText(""+ WUtil.getVoterTypeCnt(mVotes, Vote.OPTION_YES));
            holder.itemNoCnt.setText(""+WUtil.getVoterTypeCnt(mVotes, Vote.OPTION_NO));
            holder.itemVetoCnt.setText(""+WUtil.getVoterTypeCnt(mVotes, Vote.OPTION_VETO));
            holder.itemAbstainCnt.setText(""+WUtil.getVoterTypeCnt(mVotes, Vote.OPTION_ABSTAIN));
            holder.itemYesCnt.setVisibility(View.VISIBLE);
            holder.itemNoCnt.setVisibility(View.VISIBLE);
            holder.itemVetoCnt.setVisibility(View.VISIBLE);
            holder.itemAbstainCnt.setVisibility(View.VISIBLE);
            holder.itemYesCntImg.setVisibility(View.VISIBLE);
            holder.itemNoCntImg.setVisibility(View.VISIBLE);
            holder.itemVetoCntImg.setVisibility(View.VISIBLE);
            holder.itemAbstainCntImg.setVisibility(View.VISIBLE);
        }

        private void onDisplayVote_V1(VoteTallyHolder holder) {
            holder.itemYesCnt.setText(""+ WUtil.getVoterTypeCnt_gRPC(mAllVoter_gRPC, Gov.VoteOption.VOTE_OPTION_YES));
            holder.itemNoCnt.setText(""+WUtil.getVoterTypeCnt_gRPC(mAllVoter_gRPC, Gov.VoteOption.VOTE_OPTION_NO));
            holder.itemVetoCnt.setText(""+WUtil.getVoterTypeCnt_gRPC(mAllVoter_gRPC, Gov.VoteOption.VOTE_OPTION_NO_WITH_VETO));
            holder.itemAbstainCnt.setText(""+WUtil.getVoterTypeCnt_gRPC(mAllVoter_gRPC, Gov.VoteOption.VOTE_OPTION_ABSTAIN));
            holder.itemYesCnt.setVisibility(View.VISIBLE);
            holder.itemNoCnt.setVisibility(View.VISIBLE);
            holder.itemVetoCnt.setVisibility(View.VISIBLE);
            holder.itemAbstainCnt.setVisibility(View.VISIBLE);
            holder.itemYesCntImg.setVisibility(View.VISIBLE);
            holder.itemNoCntImg.setVisibility(View.VISIBLE);
            holder.itemVetoCntImg.setVisibility(View.VISIBLE);
            holder.itemAbstainCntImg.setVisibility(View.VISIBLE);

        }

        public class VoteInfoHolder extends RecyclerView.ViewHolder {
            private ImageView itemStatusImg;
            private TextView itemStatusTxt;
            private RelativeLayout itemProposerLayer;
            private ImageView itemWebBtn;
            private TextView itemTitle, itemProposer, itemType, itemStartTime, itemFinishTime, itemMsg;
            private ImageView itemExpendBtn;
            private RelativeLayout itemRequestLayer;
            private TextView itemRequestAmount, itemRequestAmountDenom;

            public VoteInfoHolder(@NonNull View itemView) {
                super(itemView);
                itemStatusImg = itemView.findViewById(R.id.vote_status_img);
                itemStatusTxt = itemView.findViewById(R.id.vote_status);
                itemProposerLayer = itemView.findViewById(R.id.vote_proposer_layer);
                itemWebBtn = itemView.findViewById(R.id.vote_detail);
                itemTitle = itemView.findViewById(R.id.vote_title);
                itemProposer = itemView.findViewById(R.id.vote_proposer);
                itemType = itemView.findViewById(R.id.vote_type);
                itemStartTime = itemView.findViewById(R.id.vote_startTime);
                itemFinishTime = itemView.findViewById(R.id.vote_endTime);
                itemMsg = itemView.findViewById(R.id.vote_msg);
                itemExpendBtn = itemView.findViewById(R.id.vote_btn_expend);
                itemRequestLayer = itemView.findViewById(R.id.request_amount_layer);
                itemRequestAmount = itemView.findViewById(R.id.request_amount);
                itemRequestAmountDenom = itemView.findViewById(R.id.request_amount_denom);
            }
        }

        public class VoteTallyHolder extends RecyclerView.ViewHolder {
            private RelativeLayout itemYesCard, itemNoCard, itemVetoCard, itemAbstainCard;
            private ImageView itemYesDone, itemNoDone, itemVetoDone, itemAbstainDone;
            private ProgressBar itemYesProgress, itemNoProgress, itemVetoProgress, itemAbstainProgress;
            private TextView itemYesRate, itemYesCnt, itemNoRate, itemNoCnt, itemVetoRate, itemVetoCnt, itemAbstainRate, itemAbstainCnt;
            private ImageView itemYesCntImg, itemNoCntImg, itemVetoCntImg, itemAbstainCntImg;
            private LinearLayout itemTurnoutLayer;
            private TextView itemTurnout, itemQuorum;

            public VoteTallyHolder(@NonNull View itemView) {
                super(itemView);
                itemYesCard = itemView.findViewById(R.id.card_yes);
                itemNoCard = itemView.findViewById(R.id.card_no);
                itemVetoCard = itemView.findViewById(R.id.card_veto);
                itemAbstainCard = itemView.findViewById(R.id.card_abstain);
                itemYesDone = itemView.findViewById(R.id.vote_yes_voted);
                itemNoDone = itemView.findViewById(R.id.vote_no_voted);
                itemVetoDone = itemView.findViewById(R.id.vote_veto_voted);
                itemAbstainDone = itemView.findViewById(R.id.vote_abstain_voted);
                itemYesProgress = itemView.findViewById(R.id.vote_yes_progress);
                itemNoProgress = itemView.findViewById(R.id.vote_no_progress);
                itemVetoProgress = itemView.findViewById(R.id.vote_veto_progress);
                itemAbstainProgress = itemView.findViewById(R.id.vote_abstain_progress);
                itemYesRate = itemView.findViewById(R.id.vote_yes_rate);
                itemYesCnt = itemView.findViewById(R.id.vote_yes_cnt);
                itemNoRate = itemView.findViewById(R.id.vote_no_rate);
                itemNoCnt = itemView.findViewById(R.id.vote_no_cnt);
                itemVetoRate = itemView.findViewById(R.id.vote_vetos_rate);
                itemVetoCnt = itemView.findViewById(R.id.vote_veto_cnt);
                itemAbstainRate = itemView.findViewById(R.id.vote_abstain_rate);
                itemAbstainCnt = itemView.findViewById(R.id.vote_abstain_cnt);
                itemYesCntImg = itemView.findViewById(R.id.vote_yes_cnt_img);
                itemNoCntImg = itemView.findViewById(R.id.vote_no_cnt_img);
                itemVetoCntImg = itemView.findViewById(R.id.vote_veto_cnt_img);
                itemAbstainCntImg = itemView.findViewById(R.id.vote_abstain_cnt_img);
                itemTurnoutLayer = itemView.findViewById(R.id.turnout_layer);
                itemTurnout = itemView.findViewById(R.id.current_turnout);
                itemQuorum = itemView.findViewById(R.id.current_quorum);
            }
        }
    }

}
