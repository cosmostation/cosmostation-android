package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_VOTE;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
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
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResProposal;
import wannabit.io.cosmostaion.network.res.ResVoteStatus;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class VoteListActivity extends BaseActivity implements Serializable, View.OnClickListener {
    private Toolbar mToolbar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private Button mMultiVote, mCancelBtn, mNextBtn;

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
        mChain = WDp.getChainNameByBaseChain(mBaseChain);
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mVoteListAdapter = new VoteListAdapter();
        mRecyclerView.setAdapter(mVoteListAdapter);
    }

    private void initView() {
        mToolbar = findViewById(R.id.tool_bar);
        mSwipeRefreshLayout = findViewById(R.id.layer_refresher);
        mRecyclerView = findViewById(R.id.recycler);
        mMultiVote = findViewById(R.id.btn_multiVote);
        mCancelBtn = findViewById(R.id.btn_cancel);
        mNextBtn = findViewById(R.id.btn_next);

        mMultiVote.setOnClickListener(this);
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
                    if (mVotingPeriodProposalsList.size() > 1) {
                        mMultiVote.setVisibility(View.VISIBLE);
                    } else {
                        mMultiVote.setVisibility(View.GONE);
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

    private void loadStatus(ResProposal proposal, int position) {
        ApiClient.getMintscan(VoteListActivity.this).getVoteStatus(mChain, proposal.id, mAccount.address).enqueue(new Callback<ArrayList<ResVoteStatus>>() {
            @Override
            public void onResponse(Call<ArrayList<ResVoteStatus>> call, Response<ArrayList<ResVoteStatus>> response) {
                if (response.body() != null && response.isSuccessful()) {
                    statusMap.put(proposal.id, response.body().stream().map(item -> item.option).collect(Collectors.toSet()));
                    mVoteListAdapter.notifyItemChanged(position);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResVoteStatus>> call, Throwable t) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mMultiVote)) {
            mMultiVote.setVisibility(View.GONE);
            mCancelBtn.setVisibility(View.VISIBLE);
            mNextBtn.setVisibility(View.VISIBLE);
            multiVoteSelectionMode = true;
            mVoteListAdapter.notifyDataSetChanged();
        } else if (v.equals(mCancelBtn)) {
            mMultiVote.setVisibility(View.VISIBLE);
            mCancelBtn.setVisibility(View.GONE);
            mNextBtn.setVisibility(View.GONE);
            multiVoteSelectionMode = false;
            mVoteListAdapter.notifyDataSetChanged();
        } else if (v.equals(mNextBtn)) {
            if(selectedSet.size() > 0){
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
        private final int VIEW_TYPE_EMPTY = -1;
        private final int VIEW_TYPE_VOTING_PERIOD_PROPOSAL_ITEM = 0;
        private final int VIEW_TYPE_VOTING_PERIOD_PROPOSAL_HEADER = 1;
        private final int VIEW_TYPE_PROPOSAL_HEADER = 2;
        private final int VIEW_TYPE_PROPOSAL_ITEM = 3;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == VIEW_TYPE_VOTING_PERIOD_PROPOSAL_ITEM || viewType == VIEW_TYPE_PROPOSAL_ITEM) {
                return new VoteListViewHolder(getLayoutInflater().inflate(R.layout.item_proposal, viewGroup, false));
            } else {
                return new VoteListHeaderViewHolder(getLayoutInflater().inflate(R.layout.item_vote_header, viewGroup, false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position) == VIEW_TYPE_VOTING_PERIOD_PROPOSAL_ITEM) {
                onBindPeriodProposalItemViewHolder((VoteListViewHolder) holder, position);
            } else if (getItemViewType(position) == VIEW_TYPE_PROPOSAL_ITEM) {
                onBindProposalItemViewHolder((VoteListViewHolder) holder, position);
            } else if (getItemViewType(position) == VIEW_TYPE_PROPOSAL_HEADER) {
                onBindHeaderItemViewHolder((VoteListHeaderViewHolder) holder, position);
            } else if (getItemViewType(position) == VIEW_TYPE_VOTING_PERIOD_PROPOSAL_HEADER) {
                onBindVotingPeriodHeaderItemViewHolder((VoteListHeaderViewHolder) holder, position);
            }
        }

        public void onBindPeriodProposalItemViewHolder(VoteListViewHolder voteListViewHolder, int position) {
            ResProposal item = mVotingPeriodProposalsList.get(position - (mVotingPeriodProposalsList.isEmpty() ? 0 : 1));
            voteListViewHolder.proposal_status_img.setVisibility(View.GONE);
            voteListViewHolder.proposal_status.setVisibility(View.GONE);
            voteListViewHolder.proposal_id.setText("# " + item.id);
            voteListViewHolder.proposal_title.setText(item.title);
            voteListViewHolder.proposal_deadline.setVisibility(View.VISIBLE);
            voteListViewHolder.proposal_deadline.setText(WDp.getTimeVoteformat(VoteListActivity.this, item.voting_end_time)
                    + " " + WDp.getGapTime(VoteListActivity.this, WDp.dateToLong3(VoteListActivity.this, item.voting_end_time)));

            if (multiVoteSelectionMode) {
                voteListViewHolder.card_proposal.setEnabled(false);
                bindVoteSelect(voteListViewHolder, position, item);
            } else {
                voteListViewHolder.card_proposal.setEnabled(true);
                bindVoteStatus(voteListViewHolder, position, item);
            }

            voteListViewHolder.card_proposal.setOnClickListener(v -> {
                Intent voteIntent = new Intent(VoteListActivity.this, VoteDetailsActivity.class);
                voteIntent.putExtra("proposalId", String.valueOf(item.id));
                startActivity(voteIntent);
            });
        }

        public void onBindProposalItemViewHolder(VoteListViewHolder holder, int position) {
            ResProposal item = mExtraProposalsList.get(position - mVotingPeriodProposalsList.size() - (mVotingPeriodProposalsList.isEmpty() ? 0 : 1) - (mExtraProposalsList.isEmpty() ? 0 : 1));
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
                String url = WUtil.getExplorer(mBaseChain) + "proposals/" + item.id;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            });
        }

        private void bindVoteSelect(VoteListViewHolder voteListViewHolder, int position, ResProposal item) {
            voteListViewHolder.vote_status.setVisibility(View.INVISIBLE);
            if (selectedSet.contains(item)) {
                voteListViewHolder.vote_select.setColorFilter(WDp.getChainColor(VoteListActivity.this, mBaseChain));
                voteListViewHolder.vote_select.setVisibility(View.VISIBLE);
                voteListViewHolder.vote_not_select.setVisibility(View.INVISIBLE);
            } else {
                voteListViewHolder.vote_select.setVisibility(View.INVISIBLE);
                voteListViewHolder.vote_not_select.setVisibility(View.VISIBLE);
            }

            voteListViewHolder.vote_select.setOnClickListener(v -> {
                selectedSet.remove(item);
                mVoteListAdapter.notifyItemChanged(position);
            });

            voteListViewHolder.vote_not_select.setOnClickListener(v -> {
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
                } else if (status.contains("VOTE_OPTION_ABSTATIN")) {
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
                loadStatus(item, position);
                statusMap.put(item.id, Sets.newHashSet());
            }
        }

        public void onBindVotingPeriodHeaderItemViewHolder(VoteListHeaderViewHolder holder, int position) {
            setHeader(holder, "Voting Period", mVotingPeriodProposalsList.size());
        }

        public void onBindHeaderItemViewHolder(VoteListHeaderViewHolder holder, int position) {
            setHeader(holder, "Proposals", mExtraProposalsList.size());
        }

        private void setHeader(VoteListHeaderViewHolder holder, String title, int count) {
            holder.mHeaderTitle.setText(title);
            holder.mItemCnt.setText(String.valueOf(count));
        }

        @Override
        public int getItemCount() {
            int count = mVotingPeriodProposalsList.size() + mExtraProposalsList.size();
            count += mVotingPeriodProposalsList.isEmpty() ? 0 : 1;
            count += mExtraProposalsList.isEmpty() ? 0 : 1;
            return count;
        }

        @Override
        public int getItemViewType(int position) {
            if (mVotingPeriodProposalsList.isEmpty() && mExtraProposalsList.isEmpty()) {
                return VIEW_TYPE_EMPTY;
            } else if (mVotingPeriodProposalsList.isEmpty()) {
                if (position == 0) {
                    return VIEW_TYPE_PROPOSAL_HEADER;
                } else {
                    return VIEW_TYPE_PROPOSAL_ITEM;
                }
            } else if (mExtraProposalsList.isEmpty()) {
                if (position == 0) {
                    return VIEW_TYPE_VOTING_PERIOD_PROPOSAL_HEADER;
                } else {
                    return VIEW_TYPE_VOTING_PERIOD_PROPOSAL_ITEM;
                }
            } else {
                if (position == 0) {
                    return VIEW_TYPE_VOTING_PERIOD_PROPOSAL_HEADER;
                } else if (mVotingPeriodProposalsList.size() + 1 == position) {
                    return VIEW_TYPE_PROPOSAL_HEADER;
                } else if (position <= mVotingPeriodProposalsList.size()) {
                    return VIEW_TYPE_VOTING_PERIOD_PROPOSAL_ITEM;
                } else {
                    return VIEW_TYPE_PROPOSAL_ITEM;
                }
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

        class VoteListHeaderViewHolder extends RecyclerView.ViewHolder {
            public TextView mHeaderTitle;
            public TextView mItemCnt;

            public VoteListHeaderViewHolder(@NonNull View itemView) {
                super(itemView);
                mHeaderTitle = itemView.findViewById(R.id.header_title);
                mItemCnt = itemView.findViewById(R.id.recycler_cnt);
            }
        }
    }

//    public interface OnItemClickListener {
//        void onItemClick(View v, int position);
//    }
//
//    private OnItemClickListener mListener = null;
//
//    public void setOnItemClickListener(OnItemClickListener listener){
//        this.mListener = listener;
//    }


//
//    // Section Header
//    public class VoteHeaderDecoration extends RecyclerView.ItemDecoration {
//        private View headerView;
//        private TextView mHeaderTitle;
//        private TextView mItemCnt;
//
//        // dp -> pixel 단위로 변경
//        private int dpToPx(Context context, int dp) {
//            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
//        }
//
//        @Override
//        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
//            super.onDrawOver(c, parent, state);
//
//            if (headerView == null) {
//                headerView = inflateHeaderView(parent);
//                mHeaderTitle = (TextView) headerView.findViewById(R.id.header_title);
//                mItemCnt = (TextView) headerView.findViewById(R.id.recycler_cnt);
//                fixLayoutSize(headerView, parent);
//            }
//
//            String previousHeader = "";
//            for (int i = 0; i < parent.getChildCount(); i++) {
//                View child = parent.getChildAt(i);
//                final int position = parent.getChildAdapterPosition(child);
//                if (position == RecyclerView.NO_POSITION) {
//                    return;
//                }
//
//                String title = "";
//                mSection = parent.getAdapter().getItemViewType(position);
//                if (mSection == SECTION_VOTING_PERIOD) {
//                    title = sectionCallback.SectionHeader(mVotingPeriodProposalsList, mSection);
//                    mItemCnt.setText("" + mVotingPeriodProposalsList.size());
//                } else if (mSection == SECTION_PROPOSALS) {
//                    title = sectionCallback.SectionHeader(mExtraProposalsList, mSection);
//                    mItemCnt.setText("" + mExtraProposalsList.size());
//                }
//                mHeaderTitle.setText(title);
//                if (!previousHeader.equals(title) || sectionCallback.isSection(position)) {
//                    drawHeader(c, child, headerView);
//                    previousHeader = title;
//                }
//            }
//        }
//
//        @Override
//        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//            super.getItemOffsets(outRect, view, parent, state);
//
//            int position = parent.getChildAdapterPosition(view);
//            if (sectionCallback.isSection(position)) {
//                outRect.top = topPadding;
//            }
//        }
//
//        private void drawHeader(Canvas c, View child, View headerView) {
//            c.save();
//            if (sticky) {
//                c.translate(0, Math.max(0, child.getTop() - headerView.getHeight()));
//            } else {
//                c.translate(0, child.getTop() - headerView.getHeight());
//            }
//            headerView.draw(c);
//            c.restore();
//        }
//
//        private View inflateHeaderView(RecyclerView parent) {
//            return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vote_header, parent, false);
//        }
//
//        private void fixLayoutSize(View view, ViewGroup parent) {
//            int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(),
//                    View.MeasureSpec.EXACTLY);
//            int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(),
//                    View.MeasureSpec.UNSPECIFIED);
//
//            int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
//                    parent.getPaddingLeft() + parent.getPaddingRight(),
//                    view.getLayoutParams().width);
//            int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
//                    parent.getPaddingTop() + parent.getPaddingBottom(),
//                    view.getLayoutParams().height);
//
//            view.measure(childWidth, childHeight);
//
//            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
//        }
//    }
//
//    public interface SectionCallback {
//        boolean isSection(int position);
//
//        String SectionHeader(ArrayList<ResProposal> proposalArrayList, int section);
//
//    }

//    private SectionCallback getSectionCall() {
//        return new SectionCallback() {
//            @Override
//            public boolean isSection(int position) {
//                return position == 0 || position == mVotingPeriodProposalsList.size() || position == mVotingPeriodProposalsList.size() + mExtraProposalsList.size();
//            }
//
//            @Override
//            public String SectionHeader(ArrayList<ResProposal> proposalArrayList, int section) {
//                if (section == SECTION_VOTING_PERIOD) {
//                    return getString(R.string.str_voting_period);
//                } else if (section == SECTION_PROPOSALS) {
//                    return getString(R.string.str_vote_proposals);
//                }
//                return getString(R.string.str_voting_period);
//            }
//        };
//    }
}
