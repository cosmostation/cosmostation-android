package wannabit.io.cosmostaion.fragment;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.VoteActivity;
import wannabit.io.cosmostaion.activities.VoteDetailsActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.network.res.ResProposal;
import wannabit.io.cosmostaion.utils.WDp;

public class VoteStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mCancelBtn, mNextBtn;

    private RecyclerView mRecyclerView;
    private ProposalSelectionAdapter mProposalSelectionAdapter;
    private Map<Integer, String> selectedMap = Maps.newHashMap();

    public ArrayList<ResProposal> mProposalList;

    public static VoteStep0Fragment newInstance(Bundle bundle) {
        VoteStep0Fragment fragment = new VoteStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vote_step0, container, false);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mCancelBtn = rootView.findViewById(R.id.btn_cancel);
        mNextBtn = rootView.findViewById(R.id.btn_next);

        initView();

        return rootView;
    }

    private void initView() {
        mProposalList = new Gson().fromJson(getActivity().getIntent().getStringExtra("proposal"), new TypeToken<List<ResProposal>>() {
        }.getType());

        mProposalList.sort((o1, o2) -> o2.id - o1.id);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mProposalSelectionAdapter = new ProposalSelectionAdapter();
        mRecyclerView.setAdapter(mProposalSelectionAdapter);
        mProposalSelectionAdapter.notifyDataSetChanged();

        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

    }


    public VoteActivity getSActivity() {
        return (VoteActivity) getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();
        } else if (v.equals(mNextBtn)) {
            if (selectedMap.size() == mProposalList.size()) {
                getSActivity().mSelectedOpinion = selectedMap;
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getSActivity(), R.string.error_no_option, Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private class ProposalSelectionAdapter extends RecyclerView.Adapter<ProposalSelectionAdapter.ProposalSelectionHolder> {
        @NonNull
        @Override
        public ProposalSelectionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ProposalSelectionHolder(getLayoutInflater().inflate(R.layout.item_proposal_selection, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final ProposalSelectionHolder proposalSelectionHolder, int position) {
            ResProposal item = mProposalList.get(position);
            proposalSelectionHolder.proposalId.setText("# " + String.valueOf(item.id));
            proposalSelectionHolder.proposalTitle.setText(item.title);
            proposalSelectionHolder.proposalDeadLine.setText(WDp.getTimeVoteformat(getActivity(), item.voting_end_time)
                    + " " + WDp.getGapTime(getActivity(), WDp.dateToLong3(getActivity(), item.voting_end_time)));

            bindVoteSelect(proposalSelectionHolder, position, item);

        }

        private void bindVoteSelect(ProposalSelectionHolder holder, int position, ResProposal item) {
            holder.yesBtnLayout.setAlpha(0.5f);
            holder.noBtnLayout.setAlpha(0.5f);
            holder.noWithVetoBtnLayout.setAlpha(0.5f);
            holder.abstainBtnLayout.setAlpha(0.5f);

            if (selectedMap.containsKey(item.id)) {
                String selected = selectedMap.get(item.id);
                switch (selected) {
                    case "VOTE_OPTION_YES":
                        holder.yesBtnLayout.setAlpha(1f);
                        holder.selectedYesImage.setColorFilter(WDp.getChainColor(getSActivity(), getSActivity().mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
                        holder.selectedNoImage.clearColorFilter();
                        holder.selectedNoWithVetoImage.clearColorFilter();
                        holder.selectedAbstainImage.clearColorFilter();
                        break;
                    case "VOTE_OPTION_NO":
                        holder.noBtnLayout.setAlpha(1f);
                        holder.selectedNoImage.setColorFilter(WDp.getChainColor(getSActivity(), getSActivity().mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
                        holder.selectedYesImage.clearColorFilter();
                        holder.selectedNoWithVetoImage.clearColorFilter();
                        holder.selectedAbstainImage.clearColorFilter();
                        break;
                    case "VOTE_OPTION_NO_WITH_VETO":
                        holder.noWithVetoBtnLayout.setAlpha(1f);
                        holder.selectedNoWithVetoImage.setColorFilter(WDp.getChainColor(getSActivity(), getSActivity().mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
                        holder.selectedYesImage.clearColorFilter();
                        holder.selectedNoImage.clearColorFilter();
                        holder.selectedAbstainImage.clearColorFilter();
                        break;
                    case "VOTE_OPTION_ABSTAIN":
                        holder.abstainBtnLayout.setAlpha(1f);
                        holder.selectedAbstainImage.setColorFilter(WDp.getChainColor(getSActivity(), getSActivity().mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
                        holder.selectedYesImage.clearColorFilter();
                        holder.selectedNoWithVetoImage.clearColorFilter();
                        holder.selectedNoImage.clearColorFilter();
                        break;
                    default:
                        break;
                }
            }

            holder.yesBtnLayout.setOnClickListener(v -> {
                if (selectedMap.containsKey(item.id) && "VOTE_OPTION_YES".equals(selectedMap.get(item.id))) {
                    selectedMap.remove(item.id);
                } else {
                    selectedMap.put(item.id, "VOTE_OPTION_YES");
                }
                mProposalSelectionAdapter.notifyItemChanged(position);
            });

            holder.noBtnLayout.setOnClickListener(v -> {
                if (selectedMap.containsKey(item.id) && "VOTE_OPTION_NO".equals(selectedMap.get(item.id))) {
                    selectedMap.remove(item.id);
                } else {
                    selectedMap.put(item.id, "VOTE_OPTION_NO");
                }
                mProposalSelectionAdapter.notifyItemChanged(position);
            });

            holder.noWithVetoBtnLayout.setOnClickListener(v -> {
                if (selectedMap.containsKey(item.id) && "VOTE_OPTION_NO_WITH_VETO".equals(selectedMap.get(item.id))) {
                    selectedMap.remove(item.id);
                } else {
                    selectedMap.put(item.id, "VOTE_OPTION_NO_WITH_VETO");
                }
                mProposalSelectionAdapter.notifyItemChanged(position);
            });

            holder.abstainBtnLayout.setOnClickListener(v -> {
                if (selectedMap.containsKey(item.id) && "VOTE_OPTION_ABSTAIN".equals(selectedMap.get(item.id))) {
                    selectedMap.remove(item.id);
                } else {
                    selectedMap.put(item.id, "VOTE_OPTION_ABSTAIN");
                }
                mProposalSelectionAdapter.notifyItemChanged(position);
            });
        }

        @Override
        public int getItemCount() {
            return mProposalList.size();
        }

        public class ProposalSelectionHolder extends RecyclerView.ViewHolder {
            CardView cardProposal;
            TextView proposalId, proposalTitle, proposalDeadLine;
            RelativeLayout yesBtnLayout, noBtnLayout, noWithVetoBtnLayout, abstainBtnLayout;
            ImageView selectedYesImage, selectedNoImage, selectedNoWithVetoImage, selectedAbstainImage;

            public ProposalSelectionHolder(@NonNull View itemView) {
                super(itemView);
                cardProposal = itemView.findViewById(R.id.card_proposal);
                proposalId = itemView.findViewById(R.id.proposal_id);
                proposalTitle = itemView.findViewById(R.id.proposal_title);
                proposalDeadLine = itemView.findViewById(R.id.proposal_deadline);
                yesBtnLayout = itemView.findViewById(R.id.check_btn_yes);
                noBtnLayout = itemView.findViewById(R.id.check_btn_no);
                noWithVetoBtnLayout = itemView.findViewById(R.id.check_btn_nowithveto);
                abstainBtnLayout = itemView.findViewById(R.id.check_btn_abstain);
                selectedYesImage = itemView.findViewById(R.id.checkImg_selected_yes);
                selectedNoImage = itemView.findViewById(R.id.checkImg_selected_no);
                selectedNoWithVetoImage = itemView.findViewById(R.id.checkImg_selected_nowithveto);
                selectedAbstainImage = itemView.findViewById(R.id.checkImg_selected_abstain);
            }
        }
    }
}
