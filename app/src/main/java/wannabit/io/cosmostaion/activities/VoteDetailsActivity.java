package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MINTSCAN_PROPOSAL;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_PROPOSAL_MY_VOTE;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
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

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cosmos.gov.v1beta1.Gov;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.res.ResMyProposal;
import wannabit.io.cosmostaion.network.res.ResProposal;
import wannabit.io.cosmostaion.task.FetchTask.MintScanProposalTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.ProposalMyVoteGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class VoteDetailsActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar mToolbar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RelativeLayout mLoadingLayer;
    private Button mVoteBtn;

    private VoteDetailsAdapter mVoteDetailsAdapter;

    private String mChain;
    private String mProposalId;

    // proposal api
    private ResProposal mApiProposal;
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
        mVoteBtn.setOnClickListener(this);

        mProposalId = getIntent().getStringExtra("proposalId");
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChain = WDp.getChainNameByBaseChain(mBaseChain);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorPrimary));
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
                AlertDialogUtils.showDoubleButtonDialog(this, getString(R.string.str_only_observe_title), getString(R.string.str_only_observe_msg),
                        Html.fromHtml("<font color=\"#9C6CFF\">" + getString(R.string.str_add_mnemonics) + "</font>"), view -> onAddMnemonicForAccount(),
                        getString(R.string.str_close), null);
                return;
            }

            if (mApiProposal != null && !mApiProposal.proposal_status.isEmpty() && !mApiProposal.proposal_status.contains("VOTING")) {
                Toast.makeText(getBaseContext(), getString(R.string.error_not_voting_period), Toast.LENGTH_SHORT).show();
                return;
            }

            if (getBaseDao().getDelegationSum().compareTo(BigDecimal.ZERO) <= 0) {
                Toast.makeText(getBaseContext(), getString(R.string.error_no_bonding_no_vote), Toast.LENGTH_SHORT).show();
                return;
            }

            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_VOTE, 0);
            List<String> availableFeeDenomList = Lists.newArrayList();
            for (String denom : WDp.getGasDenomList(mBaseChain)) {
                if (getBaseDao().getAvailable(denom).compareTo(feeAmount) >= 0) {
                    availableFeeDenomList.add(denom);
                }
            }
            if (availableFeeDenomList.isEmpty()) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(VoteDetailsActivity.this, VoteActivity.class);
            intent.putExtra("proposalId", mProposalId);
            intent.putExtra("title", "# " + mApiProposal.id + ". " + mApiProposal.title);
            intent.putExtra("proposer", mApiProposal.proposer);
            startActivity(intent);
        }
    }

    public void onFetch() {
        mTaskCount = 2;
        new ProposalMyVoteGrpcTask(getBaseApplication(), this, mBaseChain, mProposalId, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new MintScanProposalTask(getBaseApplication(), this, mChain, mProposalId).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
                if (mApiProposal.proposal_status.equalsIgnoreCase("PROPOSAL_STATUS_DEPOSIT_PERIOD") || mApiProposal.proposal_status.equalsIgnoreCase("DepositPeriod")) {
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
                        WDp.showCoinDp(getBaseContext(), getBaseDao(), requestCoin.get(0), holder.itemRequestAmountDenom, holder.itemRequestAmount, mBaseChain);
                    } else {
                        holder.itemRequestLayer.setVisibility(View.GONE);
                    }
                } else {
                    if (mApiProposal.content != null && mApiProposal.content.recipients != null && mApiProposal.content.recipients.get(0).amount != null) {
                        holder.itemRequestLayer.setVisibility(View.VISIBLE);
                        ArrayList<Coin> requestCoin = mApiProposal.content.recipients.get(0).amount;
                        WDp.showCoinDp(getBaseContext(), getBaseDao(), requestCoin.get(0), holder.itemRequestAmountDenom, holder.itemRequestAmount, mBaseChain);
                    } else {
                        holder.itemRequestLayer.setVisibility(View.GONE);
                    }
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
                        holder.itemExpendBtn.setImageDrawable(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.arrow_down_gr));

                    } else {
                        holder.itemMsg.setMaxLines(500);
                        holder.itemExpendBtn.setImageDrawable(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.arrow_up_gr));
                    }
                    mVoteDetailsAdapter.notifyDataSetChanged();
                }
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
                    String voteOption = mResMyProposal.vote.options.get(0).option;
                    if (voteOption.equalsIgnoreCase("VOTE_OPTION_YES")) {
                        holder.itemYesDone.setVisibility(View.VISIBLE);
                        holder.itemYesCard.setBackground(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.box_vote_voted));
                    } else if (voteOption.equals("VOTE_OPTION_NO")) {
                        holder.itemNoDone.setVisibility(View.VISIBLE);
                        holder.itemNoCard.setBackground(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.box_vote_voted));

                    } else if (voteOption.equals("VOTE_OPTION_NO_WITH_VETO")) {
                        holder.itemVetoDone.setVisibility(View.VISIBLE);
                        holder.itemVetoCard.setBackground(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.box_vote_voted));

                    } else if (voteOption.equals("VOTE_OPTION_ABSTAIN")) {
                        holder.itemAbstainDone.setVisibility(View.VISIBLE);
                        holder.itemAbstainCard.setBackground(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.box_vote_voted));
                    }

                } else if (mMyVote_gRPC != null) {
                    Gov.VoteOption voteOption = mMyVote_gRPC.getOption();
                    if (voteOption.equals(Gov.VoteOption.VOTE_OPTION_YES)) {
                        holder.itemYesDone.setVisibility(View.VISIBLE);
                        holder.itemYesCard.setBackground(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.box_vote_voted));

                    } else if (voteOption.equals(Gov.VoteOption.VOTE_OPTION_NO)) {
                        holder.itemNoDone.setVisibility(View.VISIBLE);
                        holder.itemNoCard.setBackground(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.box_vote_voted));

                    } else if (voteOption.equals(Gov.VoteOption.VOTE_OPTION_NO_WITH_VETO)) {
                        holder.itemVetoDone.setVisibility(View.VISIBLE);
                        holder.itemVetoCard.setBackground(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.box_vote_voted));

                    } else if (voteOption.equals(Gov.VoteOption.VOTE_OPTION_ABSTAIN)) {
                        holder.itemAbstainDone.setVisibility(View.VISIBLE);
                        holder.itemAbstainCard.setBackground(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.box_vote_voted));
                    }
                }
            }
        }

        private void onExplorerLink() {
            String url = WUtil.getExplorer(mBaseChain) + "proposals/" + mProposalId;
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
