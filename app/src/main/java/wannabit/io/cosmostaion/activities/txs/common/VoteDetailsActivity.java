package wannabit.io.cosmostaion.activities.txs.common;

import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.EVMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MINTSCAN_PROPOSAL;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_PROPOSAL_MY_VOTE;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.collect.Sets;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import cosmos.gov.v1beta1.Gov;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.databinding.ActivityVoteDetailsBinding;
import wannabit.io.cosmostaion.databinding.ItemVoteInfoBinding;
import wannabit.io.cosmostaion.databinding.ItemVoteMemoBinding;
import wannabit.io.cosmostaion.databinding.ItemVoteMemoListBinding;
import wannabit.io.cosmostaion.databinding.ItemVoteTallyBinding;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.res.ResMyProposal;
import wannabit.io.cosmostaion.network.res.ResProposal;
import wannabit.io.cosmostaion.task.FetchTask.MintScanProposalTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.ProposalMyVoteGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;

public class VoteDetailsActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private ActivityVoteDetailsBinding voteDetailsBinding;

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

        voteDetailsBinding = ActivityVoteDetailsBinding.inflate(getLayoutInflater());
        setContentView(voteDetailsBinding.getRoot());

        initView();
    }

    private void initView() {
        mProposalId = getIntent().getStringExtra("proposalId");
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);

        setSupportActionBar(voteDetailsBinding.toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        voteDetailsBinding.recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        voteDetailsBinding.recycler.setHasFixedSize(true);
        voteDetailsBinding.recycler.setAdapter(new VoteDetailsAdapter());

        onFetch();

        voteDetailsBinding.btnVote.setOnClickListener(this);
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
        voteDetailsBinding.recycler.getAdapter().notifyDataSetChanged();
        voteDetailsBinding.loadingLayer.setVisibility(View.GONE);
        voteDetailsBinding.btnVote.setVisibility(View.VISIBLE);
        voteDetailsBinding.recycler.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        voteDetailsBinding = null;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(voteDetailsBinding.btnVote)) {
            if (!mAccount.hasPrivateKey && !mAccount.isLedger()) {
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
        private static final int TYPE_TX_MEMO = 1;
        private static final int TYPE_TX_TALLY = 2;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_TX_INFO) {
                return new VoteInfoHolder(ItemVoteInfoBinding.inflate(getLayoutInflater()));
            } else if (viewType == TYPE_TX_MEMO) {
                return new VoteMemoListHolder(ItemVoteMemoListBinding.inflate(getLayoutInflater()));
            } else {
                return new VoteTallyHolder(ItemVoteTallyBinding.inflate(getLayoutInflater()));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (position == 0) {
                onBindVoteInfo(viewHolder);
            } else if (position == 1) {
                onBindVoteMemo(viewHolder);
            } else {
                onBindVoteTally(viewHolder);
            }
        }

        @Override
        public int getItemCount() {
            return 3;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_TX_INFO;
            } else if (position == 1) {
                return TYPE_TX_MEMO;
            } else {
                return TYPE_TX_TALLY;
            }
        }

        private void onBindVoteInfo(RecyclerView.ViewHolder viewHolder) {
            final VoteInfoHolder holder = (VoteInfoHolder) viewHolder;
            if (mApiProposal != null) {
                WDp.getProposalStatus(VoteDetailsActivity.this, mApiProposal, holder.voteInfoBinding.voteStatusImg, holder.voteInfoBinding.voteStatus);
                if (mApiProposal.proposer == null) {
                    holder.voteInfoBinding.voteProposerLayer.setVisibility(View.GONE);
                } else {
                    holder.voteInfoBinding.voteProposerLayer.setVisibility(View.VISIBLE);
                    if (mApiProposal.moniker.isEmpty()) {
                        holder.voteInfoBinding.voteProposer.setText(mApiProposal.proposer);
                    } else {
                        holder.voteInfoBinding.voteProposer.setText(mApiProposal.moniker);
                    }
                }
                holder.voteInfoBinding.voteTitle.setText(Html.fromHtml("<b>" + "# " + mApiProposal.id + "</b>" + "&nbsp;&nbsp;" + mApiProposal.title, Html.FROM_HTML_MODE_COMPACT));
                holder.voteInfoBinding.voteType.setText(mApiProposal.proposal_type);
                if ("PROPOSAL_STATUS_DEPOSIT_PERIOD".equalsIgnoreCase(mApiProposal.proposal_status) || "DepositPeriod".equalsIgnoreCase(mApiProposal.proposal_status)) {
                    holder.voteInfoBinding.voteStartTime.setText(R.string.str_vote_wait_deposit);
                    holder.voteInfoBinding.voteEndTime.setText(R.string.str_vote_wait_deposit);
                } else {
                    holder.voteInfoBinding.voteStartTime.setText(WDp.getTimeVoteformat(VoteDetailsActivity.this, mApiProposal.voting_start_time));
                    holder.voteInfoBinding.voteEndTime.setText(WDp.getTimeVoteformat(VoteDetailsActivity.this, mApiProposal.voting_end_time));
                }
                holder.voteInfoBinding.voteMsg.setText(mApiProposal.description);
                if (isGRPC(mBaseChain)) {
                    if (mApiProposal.messages != null && mApiProposal.messages.get(0).amount != null) {
                        holder.voteInfoBinding.requestAmountLayer.setVisibility(View.VISIBLE);
                        Coin requestCoin = mApiProposal.getAmounts();
                        if (requestCoin != null) {
                            WDp.setDpCoin(getBaseContext(), getBaseDao(), mChainConfig, requestCoin, holder.voteInfoBinding.requestAmountDenom, holder.voteInfoBinding.requestAmount);
                        } else {
                            holder.voteInfoBinding.requestAmountDenom.setText("N/A");
                            holder.voteInfoBinding.requestAmount.setVisibility(View.GONE);
                        }
                    } else {
                        holder.voteInfoBinding.requestAmountLayer.setVisibility(View.GONE);
                    }
                } else {
                    if (mApiProposal.messages != null && mApiProposal.messages.get(0).recipient != null && mApiProposal.messages.get(0).amount != null) {
                        holder.voteInfoBinding.requestAmountLayer.setVisibility(View.VISIBLE);
                        List<Coin> requestCoin = mApiProposal.messages.get(0).amount;
                        WDp.setDpCoin(getBaseContext(), getBaseDao(), mChainConfig, requestCoin.get(0), holder.voteInfoBinding.requestAmountDenom, holder.voteInfoBinding.requestAmount);
                    } else {
                        holder.voteInfoBinding.requestAmountLayer.setVisibility(View.GONE);
                    }
                }
            }

            holder.voteInfoBinding.voteDetail.setOnClickListener(v -> onExplorerLink());

            holder.voteInfoBinding.voteBtnExpend.setOnClickListener(v -> {
                if (holder.voteInfoBinding.voteMsg.getMaxLines() == 500) {
                    holder.voteInfoBinding.voteMsg.setMaxLines(3);
                    holder.voteInfoBinding.voteBtnExpend.setImageDrawable(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.arrow_down_gr));

                } else {
                    holder.voteInfoBinding.voteMsg.setMaxLines(500);
                    holder.voteInfoBinding.voteBtnExpend.setImageDrawable(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.arrow_up_gr));
                }
                voteDetailsBinding.recycler.getAdapter().notifyDataSetChanged();
            });

        }

        private void onBindVoteMemo(RecyclerView.ViewHolder viewHolder) {
            final VoteMemoListHolder holder = (VoteMemoListHolder) viewHolder;
            holder.voteMemoListBinding.memoTitle.setText("Messages" + " " + "(" + mApiProposal.messages.size() + ")");
            holder.voteMemoListBinding.recycler.setLayoutManager(new LinearLayoutManager(VoteDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
            holder.voteMemoListBinding.recycler.setHasFixedSize(true);
            holder.voteMemoListBinding.recycler.setItemViewCacheSize(20);
            holder.voteMemoListBinding.recycler.setDrawingCacheEnabled(true);
            holder.voteMemoListBinding.recycler.setAdapter(new VoteMemoListAdapter());
            holder.voteMemoListBinding.recycler.setItemAnimator(null);
            holder.voteMemoListBinding.recycler.getAdapter().notifyDataSetChanged();
        }

        private void onBindVoteTally(RecyclerView.ViewHolder viewHolder) {
            final VoteTallyHolder holder = (VoteTallyHolder) viewHolder;
            if (mApiProposal != null) {
                holder.voteTallyBinding.voteYesProgress.setProgress(WDp.getYesPer(mApiProposal).intValue());
                holder.voteTallyBinding.voteNoProgress.setProgress(WDp.getNoPer(mApiProposal).intValue());
                holder.voteTallyBinding.voteVetoProgress.setProgress(WDp.getVetoPer(mApiProposal).intValue());
                holder.voteTallyBinding.voteAbstainProgress.setProgress(WDp.getAbstainPer(mApiProposal).intValue());

                holder.voteTallyBinding.voteYesRate.setText(WDp.getDpString(WDp.getYesPer(mApiProposal).toPlainString() + "%", 3));
                holder.voteTallyBinding.voteNoRate.setText(WDp.getDpString(WDp.getNoPer(mApiProposal).toPlainString() + "%", 3));
                holder.voteTallyBinding.voteVetosRate.setText(WDp.getDpString(WDp.getVetoPer(mApiProposal).toPlainString() + "%", 3));
                holder.voteTallyBinding.voteAbstainRate.setText(WDp.getDpString(WDp.getAbstainPer(mApiProposal).toPlainString() + "%", 3));

                if (mApiProposal.proposal_status.equalsIgnoreCase("PROPOSAL_STATUS_VOTING_PERIOD") ||
                        mApiProposal.proposal_status.equalsIgnoreCase("PROPOSAL_STATUS_VALIDATOR_VOTING_PERIOD") ||
                        mApiProposal.proposal_status.equalsIgnoreCase("PROPOSAL_STATUS_CERTIFIER_VOTING_PERIOD") ||
                        mApiProposal.proposal_status.equalsIgnoreCase("VotingPeriod")) {
                    onDisplayVote(holder);
                    holder.voteTallyBinding.turnoutLayer.setVisibility(View.VISIBLE);
                    holder.voteTallyBinding.currentTurnout.setText(WDp.getDpString(WDp.getTurnout(getBaseDao(), mApiProposal).setScale(2).toPlainString() + "%", 3));
                    if (getBaseDao().mParam != null)
                        holder.voteTallyBinding.currentQuorum.setText(WDp.getPercentDp(getBaseDao().mParam.getQuorum(mChainConfig)));
                }

                if (mBaseChain.equals(CERTIK_MAIN) && mResMyProposal != null) {
                    initBgColor(holder);
                    String voteOption = mResMyProposal.vote.options.get(0).option;
                    if ("VOTE_OPTION_YES".equals(voteOption)) {
                        onVoteCheck(VoteDetailsActivity.this, holder.voteTallyBinding.cardYes, holder.voteTallyBinding.voteYesVoted, holder.voteTallyBinding.voteYesTitle, holder.voteTallyBinding.voteYesProgress, R.color.colorVoteYes);

                    } else if ("VOTE_OPTION_NO".equals(voteOption)) {
                        onVoteCheck(VoteDetailsActivity.this, holder.voteTallyBinding.cardNo, holder.voteTallyBinding.voteNoVoted, holder.voteTallyBinding.voteNoTitle, holder.voteTallyBinding.voteNoProgress, R.color.colorVoteNo);

                    } else if ("VOTE_OPTION_NO_WITH_VETO".equals(voteOption)) {
                        onVoteCheck(VoteDetailsActivity.this, holder.voteTallyBinding.cardVeto, holder.voteTallyBinding.voteVetoVoted, holder.voteTallyBinding.voteVetoTitle, holder.voteTallyBinding.voteVetoProgress, R.color.colorVoteVeto);

                    } else if ("VOTE_OPTION_ABSTAIN".equals(voteOption)) {
                        onVoteCheck(VoteDetailsActivity.this, holder.voteTallyBinding.cardAbstain, holder.voteTallyBinding.voteAbstainVoted, holder.voteTallyBinding.voteAbstainTitle, holder.voteTallyBinding.voteAbstainProgress, R.color.colorVoteAbstain);

                    }

                } else if (mMyVote_gRPC != null) {
                    initBgColor(holder);
                    Gov.VoteOption voteOption = null;
                    for (Gov.WeightedVoteOption option : mMyVote_gRPC.getOptionsList()) {
                        if (option != null) {
                            voteOption = option.getOption();
                        }
                    }
                    if (voteOption != null) {
                        if (voteOption.equals(Gov.VoteOption.VOTE_OPTION_YES)) {
                            onVoteCheck(VoteDetailsActivity.this, holder.voteTallyBinding.cardYes, holder.voteTallyBinding.voteYesVoted, holder.voteTallyBinding.voteYesTitle, holder.voteTallyBinding.voteYesProgress, R.color.colorVoteYes);

                        } else if (voteOption.equals(Gov.VoteOption.VOTE_OPTION_NO)) {
                            onVoteCheck(VoteDetailsActivity.this, holder.voteTallyBinding.cardNo, holder.voteTallyBinding.voteNoVoted, holder.voteTallyBinding.voteNoTitle, holder.voteTallyBinding.voteNoProgress, R.color.colorVoteNo);

                        } else if (voteOption.equals(Gov.VoteOption.VOTE_OPTION_NO_WITH_VETO)) {
                            onVoteCheck(VoteDetailsActivity.this, holder.voteTallyBinding.cardVeto, holder.voteTallyBinding.voteVetoVoted, holder.voteTallyBinding.voteVetoTitle, holder.voteTallyBinding.voteVetoProgress, R.color.colorVoteVeto);

                        } else if (voteOption.equals(Gov.VoteOption.VOTE_OPTION_ABSTAIN)) {
                            onVoteCheck(VoteDetailsActivity.this, holder.voteTallyBinding.cardAbstain, holder.voteTallyBinding.voteAbstainVoted, holder.voteTallyBinding.voteAbstainTitle, holder.voteTallyBinding.voteAbstainProgress, R.color.colorVoteAbstain);
                        }
                    }
                }
            }
        }

        private void onVoteCheck(Context context, RelativeLayout relativeLayout, ImageView imageView, TextView textView, ProgressBar progressBar, int colorRes) {
            relativeLayout.setAlpha(1f);
            relativeLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.box_vote_voted));
            imageView.setVisibility(View.VISIBLE);
            textView.setTextColor(ContextCompat.getColor(context, colorRes));
            progressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(context, colorRes)));
        }

        private void onExplorerLink() {
            String url = mChainConfig.explorerUrl() + "proposals/" + mProposalId;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }

        private void onDisplayVote(VoteTallyHolder holder) {
            if (mApiProposal != null) {
                holder.voteTallyBinding.voteYesCnt.setText("" + mApiProposal.voteMeta.yes);
                holder.voteTallyBinding.voteNoCnt.setText("" + mApiProposal.voteMeta.no);
                holder.voteTallyBinding.voteVetoCnt.setText("" + mApiProposal.voteMeta.no_with_veto);
                holder.voteTallyBinding.voteAbstainCnt.setText("" + mApiProposal.voteMeta.abstain);
            }
            holder.voteTallyBinding.voteYesCnt.setVisibility(View.VISIBLE);
            holder.voteTallyBinding.voteNoCnt.setVisibility(View.VISIBLE);
            holder.voteTallyBinding.voteVetoCnt.setVisibility(View.VISIBLE);
            holder.voteTallyBinding.voteAbstainCnt.setVisibility(View.VISIBLE);
            holder.voteTallyBinding.voteYesCntImg.setVisibility(View.VISIBLE);
            holder.voteTallyBinding.voteNoCntImg.setVisibility(View.VISIBLE);
            holder.voteTallyBinding.voteVetoCntImg.setVisibility(View.VISIBLE);
            holder.voteTallyBinding.voteAbstainCntImg.setVisibility(View.VISIBLE);

        }

        private void initBgColor(VoteTallyHolder holder) {
            holder.voteTallyBinding.voteYesTitle.setTextColor(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteBg));
            holder.voteTallyBinding.voteNoTitle.setTextColor(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteBg));
            holder.voteTallyBinding.voteVetoTitle.setTextColor(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteBg));
            holder.voteTallyBinding.voteAbstainTitle.setTextColor(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteBg));
            holder.voteTallyBinding.voteYesProgress.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteAbstain)));
            holder.voteTallyBinding.voteNoProgress.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteAbstain)));
            holder.voteTallyBinding.voteVetoProgress.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteAbstain)));
            holder.voteTallyBinding.voteAbstainProgress.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(VoteDetailsActivity.this, R.color.colorVoteAbstain)));
            holder.voteTallyBinding.cardYes.setAlpha(0.5f);
            holder.voteTallyBinding.cardNo.setAlpha(0.5f);
            holder.voteTallyBinding.cardVeto.setAlpha(0.5f);
            holder.voteTallyBinding.cardAbstain.setAlpha(0.5f);
        }

        public class VoteInfoHolder extends RecyclerView.ViewHolder {

            private ItemVoteInfoBinding voteInfoBinding;

            public VoteInfoHolder(@NonNull ItemVoteInfoBinding binding) {
                super(binding.getRoot());
                voteInfoBinding = binding;
            }
        }

        public class VoteTallyHolder extends RecyclerView.ViewHolder {

            private ItemVoteTallyBinding voteTallyBinding;

            public VoteTallyHolder(@NonNull ItemVoteTallyBinding binding) {
                super(binding.getRoot());
                voteTallyBinding = binding;

            }
        }

        public class VoteMemoListHolder extends RecyclerView.ViewHolder {

            private ItemVoteMemoListBinding voteMemoListBinding;

            public VoteMemoListHolder(@NonNull ItemVoteMemoListBinding binding) {
                super(binding.getRoot());
                voteMemoListBinding = binding;
            }
        }
    }

    private class VoteMemoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new VoteMemoHolder(ItemVoteMemoBinding.inflate(getLayoutInflater()));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            final VoteMemoHolder holder = (VoteMemoHolder) viewHolder;
            if (position > 0) {
                holder.voteMemoBinding.viewLine.setVisibility(View.VISIBLE);
            }

            if (mApiProposal.messages != null && mApiProposal.messages.get(position) != null && mApiProposal.messages.get(position).content != null) {
                if (mChainConfig.baseChain().equals(EVMOS_MAIN)) {
                    holder.voteMemoBinding.voteMessagesTitle.setText(mApiProposal.messages.get(position).content.title);
                    holder.voteMemoBinding.voteMessages.setText(mApiProposal.messages.get(position).content.description);
                } else {
                    holder.voteMemoBinding.voteMessagesTitle.setText(mApiProposal.messages.get(position).title);
                    holder.voteMemoBinding.voteMessages.setText(mApiProposal.messages.get(position).description);
                }
                holder.voteMemoBinding.voteMemoBtnExpend.setOnClickListener(v -> {
                    if (holder.voteMemoBinding.voteMessages.getMaxLines() == 500) {
                        holder.voteMemoBinding.voteMessages.setMaxLines(1);
                        holder.voteMemoBinding.voteMemoBtnExpend.setImageDrawable(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.arrow_down_gr));

                    } else {
                        holder.voteMemoBinding.voteMessages.setMaxLines(500);
                        holder.voteMemoBinding.voteMemoBtnExpend.setImageDrawable(ContextCompat.getDrawable(VoteDetailsActivity.this, R.drawable.arrow_up_gr));
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mApiProposal.messages.size();
        }

        public class VoteMemoHolder extends RecyclerView.ViewHolder {

            private ItemVoteMemoBinding voteMemoBinding;

            public VoteMemoHolder(@NonNull ItemVoteMemoBinding binding) {
                super(binding.getRoot());
                voteMemoBinding = binding;
            }
        }
    }
}
