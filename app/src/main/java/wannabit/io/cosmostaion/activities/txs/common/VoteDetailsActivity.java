package wannabit.io.cosmostaion.activities.txs.common;

import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MINTSCAN_PROPOSAL;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_PROPOSAL_MY_VOTE;

import android.content.Intent;
import android.content.res.ColorStateList;
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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.common.collect.Sets;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Set;

import cosmos.gov.v1beta1.Gov;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.res.ResMyProposal;
import wannabit.io.cosmostaion.network.res.ResProposal;
import wannabit.io.cosmostaion.task.FetchTask.MintScanProposalTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.ProposalMyVoteGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;

public class VoteDetailsActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar mToolbar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RelativeLayout mLoadingLayer;
    private Button mVoteBtn;

    private VoteDetailsAdapter mVoteDetailsAdapter;

    private String mProposalId;

    // proposal api
    private ResProposal mApiProposal;
    private Set<ResProposal> selectedSet = Sets.newHashSet();

    //gRPC
    private Gov.Vote mMyVote_gRPC;
    //Certik
    private ResMyProposal mResMyProposal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_details);
        mToolbar = findViewById(R.id.tool_bar);
        mSwipeRefreshLayout = findViewById(R.id.layer_refresher);
        mRecyclerView = findViewById(R.id.recycler);
        mLoadingLayer = findViewById(R.id.loadingLayer);
        mVoteBtn = findViewById(R.id.btn_action);

        initView();
    }

    private void initView() {
        mProposalId = getIntent().getStringExtra("proposalId");
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(() -> onFetch());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mVoteDetailsAdapter = new VoteDetailsAdapter();
        mRecyclerView.setAdapter(mVoteDetailsAdapter);

        onFetch();

        mVoteBtn.setOnClickListener(this);
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
                onInsertKeyDialog();
                return;
            }
            if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
                Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            if (mApiProposal != null && !mApiProposal.proposal_status.isEmpty() && !mApiProposal.proposal_status.contains("VOTING")) {
                Toast.makeText(getBaseContext(), getString(R.string.error_not_voting_period), Toast.LENGTH_SHORT).show();
                return;
            }

            if (BigDecimal.ZERO.compareTo(getBaseDao().getDelegationSum()) >= 0) {
                Toast.makeText(getBaseContext(), getString(R.string.error_no_bonding_no_vote), Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(VoteDetailsActivity.this, VoteActivity.class);
            intent.putExtra("proposal", new Gson().toJson(selectedSet));
            startActivity(intent);
        }
    }

    public void onFetch() {
        mTaskCount = 2;
        new ProposalMyVoteGrpcTask(getBaseApplication(), this, mBaseChain, mProposalId, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new MintScanProposalTask(getBaseApplication(), this, mChainConfig.chainName(), mProposalId).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if (result.taskType == TASK_GRPC_FETCH_PROPOSAL_MY_VOTE) {
            if (result.resultData != null) {
                if (mBaseChain.equals(CERTIK_MAIN)) {
                    mResMyProposal = (ResMyProposal) result.resultData;
                } else {
                    mMyVote_gRPC = (Gov.Vote) result.resultData;
                }
            }

        } else if (result.taskType == TASK_FETCH_MINTSCAN_PROPOSAL) {
            if (result.resultData != null) {
                mApiProposal = (ResProposal) result.resultData;
                selectedSet.add(mApiProposal);
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
            final VoteInfoHolder holder = (VoteInfoHolder) viewHolder;
            if (mApiProposal != null) {
                WDp.getProposalStatus(VoteDetailsActivity.this, mApiProposal, holder.itemStatusImg, holder.itemStatusTxt);
                if (mApiProposal.proposer == null) {
                    holder.itemProposerLayer.setVisibility(View.GONE);
                } else {
                    holder.itemProposerLayer.setVisibility(View.VISIBLE);
                    if (mApiProposal.moniker.isEmpty()) {
                        holder.itemProposer.setText(mApiProposal.proposer);
                    } else {
                        holder.itemProposer.setText(mApiProposal.moniker);
                    }
                }
                holder.itemTitle.setText("# " + mApiProposal.id + ". " + mApiProposal.title);
                holder.itemType.setText(mApiProposal.proposal_type);
                if ("PROPOSAL_STATUS_DEPOSIT_PERIOD".equalsIgnoreCase(mApiProposal.proposal_status) || "DepositPeriod".equalsIgnoreCase(mApiProposal.proposal_status)) {
                    holder.itemStartTime.setText(R.string.str_vote_wait_deposit);
                    holder.itemFinishTime.setText(R.string.str_vote_wait_deposit);
                } else {
                    holder.itemStartTime.setText(WDp.getTimeVoteformat(VoteDetailsActivity.this, mApiProposal.voting_start_time));
                    holder.itemFinishTime.setText(WDp.getTimeVoteformat(VoteDetailsActivity.this, mApiProposal.voting_end_time));
                }
                holder.itemMsg.setText(mApiProposal.description);
                if (isGRPC(mBaseChain)) {
                    if (mApiProposal.content != null && mApiProposal.content.amount != null && mApiProposal.content.amount.size() != 0) {
                        holder.itemRequestLayer.setVisibility(View.VISIBLE);
                        ArrayList<Coin> requestCoin = mApiProposal.content.amount;
                        WDp.setDpCoin(getBaseContext(), getBaseDao(), mChainConfig, requestCoin.get(0), holder.itemRequestAmountDenom, holder.itemRequestAmount);
                    } else {
                        holder.itemRequestLayer.setVisibility(View.GONE);
                    }
                } else {
                    if (mApiProposal.content != null && mApiProposal.content.recipients != null && mApiProposal.content.recipients.get(0).amount != null) {
                        holder.itemRequestLayer.setVisibility(View.VISIBLE);
                        ArrayList<Coin> requestCoin = mApiProposal.content.recipients.get(0).amount;
                        WDp.setDpCoin(getBaseContext(), getBaseDao(), mChainConfig, requestCoin.get(0), holder.itemRequestAmountDenom, holder.itemRequestAmount);
                    } else {
                        holder.itemRequestLayer.setVisibility(View.GONE);
                    }
                }
            }

            holder.itemWebBtn.setOnClickListener(v -> onExplorerLink());

            holder.itemExpendBtn.setOnClickListener(v -> {
                if (holder.itemMsg.getMaxLines() == 500) {
                    holder.itemMsg.setMaxLines(3);
                    holder.itemExpendBtn.setImageDrawable(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.arrow_down_gr));

                } else {
                    holder.itemMsg.setMaxLines(500);
                    holder.itemExpendBtn.setImageDrawable(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.arrow_up_gr));
                }
                mVoteDetailsAdapter.notifyDataSetChanged();
            });

        }

        private void onBindVoteTally(RecyclerView.ViewHolder viewHolder) {
            final VoteTallyHolder holder = (VoteTallyHolder) viewHolder;
            if (mApiProposal != null) {
                holder.itemYesProgress.setProgress(WDp.getYesPer(mApiProposal).intValue());
                holder.itemNoProgress.setProgress(WDp.getNoPer(mApiProposal).intValue());
                holder.itemVetoProgress.setProgress(WDp.getVetoPer(mApiProposal).intValue());
                holder.itemAbstainProgress.setProgress(WDp.getAbstainPer(mApiProposal).intValue());

                holder.itemYesRate.setText(WDp.getDpString(WDp.getYesPer(mApiProposal).toPlainString() + "%", 3));
                holder.itemNoRate.setText(WDp.getDpString(WDp.getNoPer(mApiProposal).toPlainString() + "%", 3));
                holder.itemVetoRate.setText(WDp.getDpString(WDp.getVetoPer(mApiProposal).toPlainString() + "%", 3));
                holder.itemAbstainRate.setText(WDp.getDpString(WDp.getAbstainPer(mApiProposal).toPlainString() + "%", 3));

                if (mApiProposal.proposal_status.equalsIgnoreCase("PROPOSAL_STATUS_VOTING_PERIOD") ||
                        mApiProposal.proposal_status.equalsIgnoreCase("PROPOSAL_STATUS_VALIDATOR_VOTING_PERIOD") ||
                        mApiProposal.proposal_status.equalsIgnoreCase("PROPOSAL_STATUS_CERTIFIER_VOTING_PERIOD") ||
                        mApiProposal.proposal_status.equalsIgnoreCase("VotingPeriod")) {
                    onDisplayVote(holder);
                    holder.itemTurnoutLayer.setVisibility(View.VISIBLE);
                    holder.itemTurnout.setText(WDp.getDpString(WDp.getTurnout(mBaseChain, getBaseDao(), mApiProposal).setScale(2).toPlainString() + "%", 3));
                    if (getBaseDao().mChainParam != null && getBaseDao().mChainParam.getQuorum(mBaseChain) != null) {
                        holder.itemQuorum.setText(WDp.getPercentDp(getBaseDao().mChainParam.getQuorum(mBaseChain)));
                    }
                }

                if (mBaseChain.equals(CERTIK_MAIN) && mResMyProposal != null) {
                    initBgColor(holder);
                    String voteOption = mResMyProposal.vote.options.get(0).option;
                    if ("VOTE_OPTION_YES".equals(voteOption)) {
                        holder.itemYesCard.setAlpha(1f);
                        holder.itemYesDone.setVisibility(View.VISIBLE);
                        holder.itemYesTitle.setTextColor(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteYes));
                        holder.itemYesCard.setBackground(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.box_vote_voted));
                        holder.itemYesProgress.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteYes)));

                    } else if ("VOTE_OPTION_NO".equals(voteOption)) {
                        holder.itemNoCard.setAlpha(1f);
                        holder.itemNoDone.setVisibility(View.VISIBLE);
                        holder.itemNoTitle.setTextColor(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteNo));
                        holder.itemNoCard.setBackground(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.box_vote_voted));
                        holder.itemNoProgress.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteNo)));

                    } else if ("VOTE_OPTION_NO_WITH_VETO".equals(voteOption)) {
                        holder.itemVetoCard.setAlpha(1f);
                        holder.itemVetoDone.setVisibility(View.VISIBLE);
                        holder.itemVetoTitle.setTextColor(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteVeto));
                        holder.itemVetoCard.setBackground(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.box_vote_voted));
                        holder.itemVetoProgress.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteVeto)));

                    } else if ("VOTE_OPTION_ABSTAIN".equals(voteOption)) {
                        holder.itemAbstainCard.setAlpha(1f);
                        holder.itemAbstainDone.setVisibility(View.VISIBLE);
                        holder.itemAbstainTitle.setTextColor(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteAbstain));
                        holder.itemAbstainCard.setBackground(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.box_vote_voted));
                        holder.itemAbstainProgress.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteAbstain)));

                    }

                } else if (mMyVote_gRPC != null) {
                    initBgColor(holder);
                    Gov.VoteOption voteOption = mMyVote_gRPC.getOption();
                    if (voteOption.equals(Gov.VoteOption.VOTE_OPTION_YES)) {
                        holder.itemYesCard.setAlpha(1f);
                        holder.itemYesDone.setVisibility(View.VISIBLE);
                        holder.itemYesTitle.setTextColor(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteYes));
                        holder.itemYesCard.setBackground(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.box_vote_voted));
                        holder.itemYesProgress.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteYes)));

                    } else if (voteOption.equals(Gov.VoteOption.VOTE_OPTION_NO)) {
                        holder.itemNoCard.setAlpha(1f);
                        holder.itemNoDone.setVisibility(View.VISIBLE);
                        holder.itemNoTitle.setTextColor(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteNo));
                        holder.itemNoCard.setBackground(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.box_vote_voted));
                        holder.itemNoProgress.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteNo)));

                    } else if (voteOption.equals(Gov.VoteOption.VOTE_OPTION_NO_WITH_VETO)) {
                        holder.itemVetoCard.setAlpha(1f);
                        holder.itemVetoDone.setVisibility(View.VISIBLE);
                        holder.itemVetoTitle.setTextColor(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteVeto));
                        holder.itemVetoCard.setBackground(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.box_vote_voted));
                        holder.itemVetoProgress.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteVeto)));

                    } else if (voteOption.equals(Gov.VoteOption.VOTE_OPTION_ABSTAIN)) {
                        holder.itemAbstainCard.setAlpha(1f);
                        holder.itemAbstainDone.setVisibility(View.VISIBLE);
                        holder.itemAbstainTitle.setTextColor(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteAbstain));
                        holder.itemAbstainCard.setBackground(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.box_vote_voted));
                        holder.itemAbstainProgress.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteAbstain)));

                    }
                }
            }
        }

        private void onExplorerLink() {
            String url = mChainConfig.explorerUrl() + "proposals/" + mProposalId;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }

        private void onDisplayVote(VoteTallyHolder holder) {
            if (mApiProposal != null) {
                holder.itemYesCnt.setText("" + mApiProposal.voteMeta.yes);
                holder.itemNoCnt.setText("" + mApiProposal.voteMeta.no);
                holder.itemVetoCnt.setText("" + mApiProposal.voteMeta.no_with_veto);
                holder.itemAbstainCnt.setText("" + mApiProposal.voteMeta.abstain);
            }
            holder.itemYesCnt.setVisibility(View.VISIBLE);
            holder.itemNoCnt.setVisibility(View.VISIBLE);
            holder.itemVetoCnt.setVisibility(View.VISIBLE);
            holder.itemAbstainCnt.setVisibility(View.VISIBLE);
            holder.itemYesCntImg.setVisibility(View.VISIBLE);
            holder.itemNoCntImg.setVisibility(View.VISIBLE);
            holder.itemVetoCntImg.setVisibility(View.VISIBLE);
            holder.itemAbstainCntImg.setVisibility(View.VISIBLE);

        }

        private void initBgColor(VoteTallyHolder holder) {
            holder.itemYesTitle.setTextColor(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteBg));
            holder.itemNoTitle.setTextColor(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteBg));
            holder.itemVetoTitle.setTextColor(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteBg));
            holder.itemAbstainTitle.setTextColor(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteBg));
            holder.itemYesProgress.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteAbstain)));
            holder.itemNoProgress.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteAbstain)));
            holder.itemVetoProgress.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteAbstain)));
            holder.itemAbstainProgress.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteAbstain)));
            holder.itemYesCard.setAlpha(0.5f);
            holder.itemNoCard.setAlpha(0.5f);
            holder.itemVetoCard.setAlpha(0.5f);
            holder.itemAbstainCard.setAlpha(0.5f);
        }

        public class VoteInfoHolder extends RecyclerView.ViewHolder {
            private ImageView itemStatusImg;
            private TextView itemStatusTxt;
            private RelativeLayout itemProposerLayer;
            private ImageView itemWebBtn;
            private TextView itemProposer, itemTitle, itemType, itemStartTime, itemFinishTime, itemMsg;
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
            private TextView itemYesRate, itemYesCnt, itemNoRate, itemNoCnt, itemVetoRate, itemVetoCnt, itemAbstainRate, itemAbstainCnt, itemYesTitle, itemNoTitle, itemVetoTitle, itemAbstainTitle;
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
                itemYesTitle = itemView.findViewById(R.id.vote_yes_title);
                itemNoTitle = itemView.findViewById(R.id.vote_no_title);
                itemVetoTitle = itemView.findViewById(R.id.vote_veto_title);
                itemAbstainTitle = itemView.findViewById(R.id.vote_abstain_title);
            }
        }
    }

}
