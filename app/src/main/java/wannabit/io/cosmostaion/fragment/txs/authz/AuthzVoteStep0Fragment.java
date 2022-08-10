package wannabit.io.cosmostaion.fragment.txs.authz;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.authz.AuthzVoteActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResProposal;
import wannabit.io.cosmostaion.network.res.ResVoteStatus;
import wannabit.io.cosmostaion.utils.WDp;

public class AuthzVoteStep0Fragment extends BaseFragment implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private VoteListAdapter mVoteListAdapter;

    private Button mCancel, mNextBtn;
    private RelativeLayout mLoadingLayer;
    private LinearLayout mEmptyLayer;

    private String mGranter;
    private List<ResProposal> mVotingPeriodProposalsList = Lists.newArrayList();
    private Map<Integer, Set<String>> statusMap = Maps.newHashMap();
    private ArrayList<String> mSelectedProposalIds = new ArrayList<>();

    public static AuthzVoteStep0Fragment newInstance() {
        return new AuthzVoteStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_authz_vote_step0, container, false);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mCancel = rootView.findViewById(R.id.btn_cancel);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mLoadingLayer = rootView.findViewById(R.id.loadingLayer);
        mEmptyLayer = rootView.findViewById(R.id.empty_proposal);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mVoteListAdapter = new VoteListAdapter();
        mRecyclerView.setAdapter(mVoteListAdapter);

        mGranter = getSActivity().mGranter;

        mCancel.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        loadProposals();
        loadStatus();
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if (mSelectedProposalIds.size() > 0) {
                ArrayList<ResProposal> proposals = new ArrayList<>();
                for (String id : mSelectedProposalIds) {
                    proposals.add(mVotingPeriodProposalsList.stream().filter(item -> String.valueOf(item.id) == id).findFirst().get());
                }
                getSActivity().mProposalsList = proposals;
                getSActivity().onNextStep();

            } else {
                Toast.makeText(getActivity(), R.string.error_not_selected_vote, Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private void loadProposals() {
        mVotingPeriodProposalsList.clear();

        ApiClient.getMintscan(getActivity()).getProposalList(getSActivity().mChainConfig.chainName()).enqueue(new Callback<ArrayList<ResProposal>>() {
            @Override
            public void onResponse(Call<ArrayList<ResProposal>> call, Response<ArrayList<ResProposal>> response) {
                if (response.body() != null && response.isSuccessful()) {
                    List<ResProposal> proposals = response.body();
                    proposals.sort((o1, o2) -> o2.id - o1.id);
                    mVotingPeriodProposalsList.addAll(proposals.stream().filter(item -> "PROPOSAL_STATUS_VOTING_PERIOD".equals(item.proposal_status)).collect(Collectors.toList()));
                    getSActivity().runOnUiThread(() -> {
                        if (mVotingPeriodProposalsList.size() > 0) {
                            mEmptyLayer.setVisibility(View.GONE);
                        } else {
                            mEmptyLayer.setVisibility(View.VISIBLE);
                        }
                        mLoadingLayer.setVisibility(View.GONE);
                        mVoteListAdapter.notifyDataSetChanged();
                    });
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResProposal>> call, Throwable t) {
            }
        });
    }

    private void loadStatus() {
        ApiClient.getMintscan(getActivity()).getVoteStatus(getSActivity().mChainConfig.chainName(), mGranter).enqueue(new Callback<ResVoteStatus>() {
            @Override
            public void onResponse(Call<ResVoteStatus> call, Response<ResVoteStatus> response) {
                if (response.body() != null && response.isSuccessful() && response.body().votes != null) {
                    try {
                        response.body().votes.forEach(votesData -> statusMap.put(votesData.id, votesData.voteDetails.stream().map(detail -> detail.option).collect(Collectors.toSet())));
                        mVoteListAdapter.notifyDataSetChanged();
                    } catch (Exception e) { }
                }
            }

            @Override
            public void onFailure(Call<ResVoteStatus> call, Throwable t) {
            }
        });
    }

    public class VoteListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new VoteListViewHolder(getLayoutInflater().inflate(R.layout.item_proposal, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            onBindPeriodProposalItemViewHolder((VoteListViewHolder) holder, position);
        }

        public void onBindPeriodProposalItemViewHolder(VoteListViewHolder holder, int position) {
            ResProposal proposal = mVotingPeriodProposalsList.get(position);
            holder.card_proposal.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorTransBg));
            holder.proposal_status_img.setVisibility(View.GONE);
            holder.proposal_status.setVisibility(View.GONE);
            holder.proposal_id.setText("# " + proposal.id);
            holder.proposal_title.setText(proposal.title);
            holder.proposal_deadline.setVisibility(View.VISIBLE);
            holder.proposal_deadline.setText(WDp.getTimeVoteformat(getActivity(), proposal.voting_end_time) + " " + WDp.getGapTime(WDp.dateToLong3(getActivity(), proposal.voting_end_time)));

            if (statusMap.containsKey(proposal.id)) {
                Set<String> status = statusMap.get(proposal.id);
                if (status.contains("VOTE_OPTION_YES")) {
                    holder.vote_status.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.icon_vote_yes));
                } else if (status.contains("VOTE_OPTION_NO")) {
                    holder.vote_status.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.icon_vote_no));
                } else if (status.contains("VOTE_OPTION_NO_WITH_VETO")) {
                    holder.vote_status.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.icon_vote_nowithveto));
                } else if (status.contains("VOTE_OPTION_ABSTAIN")) {
                    holder.vote_status.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.icon_vote_abstain));
                } else if (status.size() > 1) {
                    holder.vote_status.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.icon_vote_weight));
                } else {
                    holder.vote_status.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.icon_vote_not_voted));
                }
            } else {
                holder.vote_status.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.icon_vote_not_voted));
                statusMap.put(proposal.id, Sets.newHashSet());
            }

            holder.card_proposal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mSelectedProposalIds.contains(String.valueOf(proposal.id))) {
                        mSelectedProposalIds.remove(String.valueOf(proposal.id));
                    } else {
                        mSelectedProposalIds.add(String.valueOf(proposal.id));
                    }
                    mVoteListAdapter.notifyItemChanged(position);
                }
            });

            if (mSelectedProposalIds.contains(String.valueOf(proposal.id))) {
                Drawable roundBackground = ContextCompat.getDrawable(getActivity(), R.drawable.box_round_multi_vote);
                roundBackground = DrawableCompat.wrap(roundBackground);
                DrawableCompat.setTint(roundBackground, ContextCompat.getColor(getActivity(), getSActivity().mChainConfig.chainColor()));
                holder.card_proposal.setBackground(roundBackground);
            } else {
                holder.card_proposal.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorTransBg));
            }
        }

        @Override
        public int getItemCount() {
            return mVotingPeriodProposalsList.size();
        }

        public class VoteListViewHolder extends RecyclerView.ViewHolder {
            private RelativeLayout card_proposal;
            private TextView proposal_id, proposal_status, proposal_title, proposal_deadline;
            private ImageView proposal_status_img, vote_status;

            public VoteListViewHolder(@NonNull View itemView) {
                super(itemView);
                card_proposal = itemView.findViewById(R.id.card_background);
                proposal_id = itemView.findViewById(R.id.proposal_id);
                proposal_status = itemView.findViewById(R.id.proposal_status);
                proposal_title = itemView.findViewById(R.id.proposal_title);
                proposal_status_img = itemView.findViewById(R.id.proposal_status_img);
                proposal_deadline = itemView.findViewById(R.id.proposal_deadline);
                vote_status = itemView.findViewById(R.id.vote_status);
            }
        }
    }

    private AuthzVoteActivity getSActivity() {
        return (AuthzVoteActivity) getBaseActivity();
    }
}
