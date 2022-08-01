package wannabit.io.cosmostaion.activities.txs.common;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_VOTE;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResProposal;
import wannabit.io.cosmostaion.network.res.ResVoteStatus;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class VoteListActivity extends BaseActivity implements Serializable, View.OnClickListener {
    private static final int SECTION_VOTING_PERIOD = 0;
    private static final int SECTION_PROPOSALS = 1;

    private int mSection;

    private Toolbar mToolbar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private VoteHeaderRecyclerView mVoteHeaderRecyclerView;
    private Button mMultiVoteBtn, mCancelBtn, mNextBtn;
    private TextView mEmptyProposalText;

    private VoteListAdapter mVoteListAdapter;

    // proposal api
    private List<ResProposal> mVotingPeriodProposalsList = Lists.newArrayList();
    private List<ResProposal> mExtraProposalsList = Lists.newArrayList();
    private Map<Integer, Set<String>> statusMap = Maps.newHashMap();
    private Set<ResProposal> selectedSet = Sets.newHashSet();

    private String mChain;

    private boolean multiVoteSelectionMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_list);

        initView();
        initRecyclerView();
        loadAccount();
        loadProposals();
    }

    private void loadAccount() {
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mChain = mChainConfig.chainName();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mVoteListAdapter = new VoteListAdapter();
        mRecyclerView.setAdapter(mVoteListAdapter);

        mVoteHeaderRecyclerView = new VoteHeaderRecyclerView(this, true, getSectionCall());
        mRecyclerView.addItemDecoration(mVoteHeaderRecyclerView);

    }

    private void initView() {
        mToolbar = findViewById(R.id.tool_bar);
        mSwipeRefreshLayout = findViewById(R.id.layer_refresher);
        mRecyclerView = findViewById(R.id.recycler);
        mMultiVoteBtn = findViewById(R.id.btn_multiVote);
        mCancelBtn = findViewById(R.id.btn_cancel);
        mNextBtn = findViewById(R.id.btn_next);
        mEmptyProposalText = findViewById(R.id.empty_proposal);

        mMultiVoteBtn.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(VoteListActivity.this, R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(this::loadProposals);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadProposals() {
        if (mAccount == null) return;
        onShowWaitDialog();
        if(mVotingPeriodProposalsList.isEmpty() && mExtraProposalsList.isEmpty()){
            mEmptyProposalText.setVisibility(View.VISIBLE);
            onHideWaitDialog();
        }
        ApiClient.getMintscan(VoteListActivity.this).getProposalList(mChain).enqueue(new Callback<ArrayList<ResProposal>>() {
            @Override
            public void onResponse(Call<ArrayList<ResProposal>> call, Response<ArrayList<ResProposal>> response) {
                if (response.body() != null && response.isSuccessful()) {
                    mVotingPeriodProposalsList.clear();
                    mExtraProposalsList.clear();
                    List<ResProposal> proposals = response.body();
                    proposals.sort((o1, o2) -> o2.id - o1.id);
                    mVotingPeriodProposalsList.addAll(proposals.stream().filter(item -> "PROPOSAL_STATUS_VOTING_PERIOD".equals(item.proposal_status)).collect(Collectors.toList()));
                    mExtraProposalsList.addAll(proposals.stream().filter(item -> !"PROPOSAL_STATUS_VOTING_PERIOD".equals(item.proposal_status)).collect(Collectors.toList()));
                    if(!selectedSet.isEmpty()){
                        mMultiVoteBtn.setVisibility(View.GONE);
                    } else{
                        mMultiVoteBtn.setVisibility(View.VISIBLE);
                    }
                    runOnUiThread(() -> {
                        mSwipeRefreshLayout.setRefreshing(false);
                        mVoteListAdapter.notifyDataSetChanged();
                        onHideWaitDialog();
                    });
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResProposal>> call, Throwable t) {
            }
        });
    }

    private void loadStatus(int position) {
        ApiClient.getMintscan(VoteListActivity.this).getVoteStatus(mChain, mAccount.address).enqueue(new Callback<ResVoteStatus>() {
            @Override
            public void onResponse(Call<ResVoteStatus> call, Response<ResVoteStatus> response) {
                if (response.body() != null && response.isSuccessful()) {
                    try{
                        statusMap.put(response.body().votes.get(position).id, response.body().votes.get(position).voteDetails.stream().map(item->item.option).collect(Collectors.toSet()));
                        mVoteListAdapter.notifyDataSetChanged();
                    }catch (Exception e){
                    }
                }
            }

            @Override
            public void onFailure(Call<ResVoteStatus> call, Throwable t) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mMultiVoteBtn)) {
            mMultiVoteBtn.setVisibility(View.GONE);
            mCancelBtn.setVisibility(View.VISIBLE);
            mNextBtn.setVisibility(View.VISIBLE);
            multiVoteSelectionMode = true;
            mVoteListAdapter.notifyDataSetChanged();
        } else if (v.equals(mCancelBtn)) {
            mMultiVoteBtn.setVisibility(View.VISIBLE);
            mCancelBtn.setVisibility(View.GONE);
            mNextBtn.setVisibility(View.GONE);
            multiVoteSelectionMode = false;
            mVoteListAdapter.notifyDataSetChanged();
        } else if (v.equals(mNextBtn)) {
            if (selectedSet.size() > 0) {
                Intent voteIntent = new Intent(VoteListActivity.this, VoteActivity.class);
                voteIntent.putExtra("proposal", new Gson().toJson(selectedSet));
                startActivity(voteIntent);
            } else {
                Toast.makeText(getBaseContext(), getString(R.string.error_not_selected_vote), Toast.LENGTH_SHORT).show();
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
        }
    }

    public class VoteListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == SECTION_VOTING_PERIOD || viewType == SECTION_PROPOSALS) {
                return new VoteListViewHolder(getLayoutInflater().inflate(R.layout.item_proposal, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position) == SECTION_VOTING_PERIOD) {
                onBindPeriodProposalItemViewHolder((VoteListViewHolder) holder, position);
            } else if (getItemViewType(position) == SECTION_PROPOSALS) {
                onBindProposalItemViewHolder((VoteListViewHolder) holder, position - mVotingPeriodProposalsList.size());
            }
        }

        public void onBindPeriodProposalItemViewHolder(VoteListViewHolder holder, int position) {
            ResProposal item = mVotingPeriodProposalsList.get(position);
            holder.proposal_status_img.setVisibility(View.GONE);
            holder.proposal_status.setVisibility(View.GONE);
            holder.proposal_id.setText("# " + item.id);
            holder.proposal_title.setText(item.title);
            holder.proposal_deadline.setVisibility(View.VISIBLE);
            holder.proposal_deadline.setText(WDp.getTimeVoteformat(VoteListActivity.this, item.voting_end_time)
                    + " " + WDp.getGapTime(VoteListActivity.this, WDp.dateToLong3(VoteListActivity.this, item.voting_end_time)));

            if (multiVoteSelectionMode) {
                holder.card_proposal.setEnabled(false);
                bindVoteSelect(holder, position, item);
            } else {
                holder.card_proposal.setEnabled(true);
                bindVoteStatus(holder, position, item);
            }

            holder.card_proposal.setOnClickListener(v -> {
                Intent voteIntent = new Intent(VoteListActivity.this, VoteDetailsActivity.class);
                voteIntent.putExtra("proposalId", String.valueOf(item.id));
                startActivity(voteIntent);
            });
        }

        public void onBindProposalItemViewHolder(VoteListViewHolder holder, int position) {
            ResProposal item = mExtraProposalsList.get(position);
            holder.proposal_id.setText("# " + item.id);
            holder.proposal_title.setText(item.title);
            if (item.proposal_status.contains("DEPOSIT")) {
                holder.proposal_status_img.setImageDrawable(ContextCompat.getDrawable(VoteListActivity.this, R.drawable.ic_deposit_img));
                holder.proposal_status.setText("DepositPeriod");
            } else if (item.proposal_status.contains("REJECTED")) {
                holder.proposal_status_img.setImageDrawable(ContextCompat.getDrawable(VoteListActivity.this, R.drawable.ic_rejected_img));
                holder.proposal_status.setText("Rejected");
            } else if (item.proposal_status.contains("PASSED")) {
                holder.proposal_status_img.setImageDrawable(ContextCompat.getDrawable(VoteListActivity.this, R.drawable.ic_passed_img));
                holder.proposal_status.setText("Passed");
            } else if (item.proposal_status.contains("FAILED")) {
                holder.proposal_status_img.setImageDrawable(ContextCompat.getDrawable(VoteListActivity.this, R.drawable.failed));
                holder.proposal_status.setText("Failed");
            }

            holder.proposal_id.setText("# " + item.id);
            holder.proposal_title.setText(item.title);

            bindVoteStatus(holder, position, item);

            holder.card_proposal.setOnClickListener(v -> {
                String url = mChainConfig.explorerUrl() + "proposals/" + item.id;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            });
        }

        private void bindVoteSelect(VoteListViewHolder holder, int position, ResProposal item) {
            holder.vote_status.setVisibility(View.INVISIBLE);
            if (selectedSet.contains(item)) {
                holder.vote_select.setColorFilter(WDp.getChainColor(VoteListActivity.this, mBaseChain));
                holder.vote_select.setVisibility(View.VISIBLE);
                holder.vote_not_select.setVisibility(View.INVISIBLE);
            } else {
                holder.vote_select.setVisibility(View.INVISIBLE);
                holder.vote_not_select.setVisibility(View.VISIBLE);
            }

            holder.vote_select.setOnClickListener(v -> {
                selectedSet.remove(item);
                mVoteListAdapter.notifyItemChanged(position);
            });

            holder.vote_not_select.setOnClickListener(v -> {
                selectedSet.add(item);
                mVoteListAdapter.notifyItemChanged(position);
            });
        }

        private void bindVoteStatus(VoteListViewHolder holder, int position, ResProposal item) {
            holder.vote_status.setVisibility(View.INVISIBLE);
            holder.vote_select.setVisibility(View.INVISIBLE);
            holder.vote_not_select.setVisibility(View.INVISIBLE);

            if (statusMap.containsKey(item.id)) {
                Set<String> status = statusMap.get(item.id);
                if (status.contains("VOTE_OPTION_YES")) {
                    holder.vote_status.setVisibility(View.VISIBLE);
                    holder.vote_status.setImageDrawable(ContextCompat.getDrawable(VoteListActivity.this, R.drawable.icon_vote_yes));
                } else if (status.contains("VOTE_OPTION_NO")) {
                    holder.vote_status.setVisibility(View.VISIBLE);
                    holder.vote_status.setImageDrawable(ContextCompat.getDrawable(VoteListActivity.this, R.drawable.icon_vote_no));
                } else if (status.contains("VOTE_OPTION_NO_WITH_VETO")) {
                    holder.vote_status.setVisibility(View.VISIBLE);
                    holder.vote_status.setImageDrawable(ContextCompat.getDrawable(VoteListActivity.this, R.drawable.icon_vote_nowithveto));
                } else if (status.contains("VOTE_OPTION_ABSTAIN")) {
                    holder.vote_status.setVisibility(View.VISIBLE);
                    holder.vote_status.setImageDrawable(ContextCompat.getDrawable(VoteListActivity.this, R.drawable.icon_vote_abstain));
                } else if (status.size() > 1) {
                    holder.vote_status.setVisibility(View.VISIBLE);
                    holder.vote_status.setImageDrawable(ContextCompat.getDrawable(VoteListActivity.this, R.drawable.icon_vote_weight));
                } else {
                    holder.vote_status.setVisibility(View.INVISIBLE);
                }
            } else {
                holder.vote_status.setVisibility(View.INVISIBLE);
                loadStatus(position);
                statusMap.put(item.id, Sets.newHashSet());
            }
        }

        @Override
        public int getItemCount() {
            return mVotingPeriodProposalsList.size() + mExtraProposalsList.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (position < mVotingPeriodProposalsList.size()) {
                return SECTION_VOTING_PERIOD;
            } else {
                return SECTION_PROPOSALS;
            }
        }

        public class VoteListViewHolder extends RecyclerView.ViewHolder {
            private CardView card_proposal;
            private TextView proposal_id, proposal_status, proposal_title, proposal_deadline;
            private ImageView proposal_status_img, vote_status, vote_select, vote_not_select;

            public VoteListViewHolder(@NonNull View itemView) {
                super(itemView);
                card_proposal = itemView.findViewById(R.id.card_proposal);
                proposal_id = itemView.findViewById(R.id.proposal_id);
                proposal_status = itemView.findViewById(R.id.proposal_status);
                proposal_title = itemView.findViewById(R.id.proposal_title);
                proposal_status_img = itemView.findViewById(R.id.proposal_status_img);
                proposal_deadline = itemView.findViewById(R.id.proposal_deadline);
                vote_status = itemView.findViewById(R.id.vote_status);
                vote_select = itemView.findViewById(R.id.vote_select);
                vote_not_select = itemView.findViewById(R.id.vote_not_select);
            }
        }
    }

    // Section Header
    public class VoteHeaderRecyclerView extends RecyclerView.ItemDecoration {
        private final int topPadding;

        private final boolean sticky;
        private final SectionCallback sectionCallback;

        private View headerView;
        private TextView mHeaderTitle;
        private TextView mItemCnt;

        public VoteHeaderRecyclerView(Context context, boolean sticky, @NonNull SectionCallback sectionCallback) {
            this.sticky = sticky;
            this.sectionCallback = sectionCallback;

            topPadding = dpToPx(context, 26);
        }

        // dp -> pixel 단위로 변경
        private int dpToPx(Context context, int dp) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);

            if (headerView == null) {
                headerView = inflateHeaderView(parent);
                mHeaderTitle = (TextView) headerView.findViewById(R.id.header_title);
                mItemCnt = (TextView) headerView.findViewById(R.id.recycler_cnt);
                fixLayoutSize(headerView, parent);
            }

            Set<String> headerTitleSet = Sets.newHashSet();
            for (int i = 0; i < parent.getChildCount(); i++) {
                View child = parent.getChildAt(i);
                final int position = parent.getChildAdapterPosition(child);
                if (position == RecyclerView.NO_POSITION) {
                    return;
                }

                String title = "";
                mSection = parent.getAdapter().getItemViewType(position);
                if (mSection == SECTION_VOTING_PERIOD) {
                    title = sectionCallback.SectionHeader(mVotingPeriodProposalsList, mSection);
                    mItemCnt.setText("" + mVotingPeriodProposalsList.size());
                } else if (mSection == SECTION_PROPOSALS) {
                    title = sectionCallback.SectionHeader(mExtraProposalsList, mSection);
                    mItemCnt.setText("" + mExtraProposalsList.size());
                }
                mHeaderTitle.setText(title);
                if (!headerTitleSet.contains(title) || sectionCallback.isSection(position)) {
                    drawHeader(c, child, headerView);
                    headerTitleSet.add(title);
                }
            }
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            int position = parent.getChildAdapterPosition(view);
            if (sectionCallback.isSection(position)) {
                outRect.top = topPadding;
            }
        }

        private void drawHeader(Canvas c, View child, View headerView) {
            c.save();
            if (sticky) {
                c.translate(0, Math.max(0, child.getTop() - headerView.getHeight()));
            } else {
                c.translate(0, child.getTop() - headerView.getHeight());
            }
            headerView.draw(c);
            c.restore();
        }

        private View inflateHeaderView(RecyclerView parent) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vote_header, parent, false);
        }

        private void fixLayoutSize(View view, ViewGroup parent) {
            int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(),
                    View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(),
                    View.MeasureSpec.UNSPECIFIED);

            int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
                    parent.getPaddingLeft() + parent.getPaddingRight(),
                    view.getLayoutParams().width);
            int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
                    parent.getPaddingTop() + parent.getPaddingBottom(),
                    view.getLayoutParams().height);

            view.measure(childWidth, childHeight);

            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    public interface SectionCallback {
        boolean isSection(int position);

        String SectionHeader(List<ResProposal> proposalList, int section);
    }

    private SectionCallback getSectionCall() {
        return new SectionCallback() {
            @Override
            public boolean isSection(int position) {
                return position == 0 || position == mVotingPeriodProposalsList.size() || position == mVotingPeriodProposalsList.size() + mExtraProposalsList.size();
            }

            @Override
            public String SectionHeader(List<ResProposal> proposalArrayList, int section) {
                if (section == SECTION_VOTING_PERIOD) {
                    return getString(R.string.str_voting_period);
                } else if (section == SECTION_PROPOSALS) {
                    return getString(R.string.str_vote_proposals);
                }
                return null;
            }
        };
    }
}
