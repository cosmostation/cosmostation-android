package wannabit.io.cosmostaion.fragment.txs.common;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.VoteActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.databinding.FragmentVoteStep0Binding;
import wannabit.io.cosmostaion.databinding.ItemProposalSelectionBinding;
import wannabit.io.cosmostaion.network.res.ResProposal;
import wannabit.io.cosmostaion.utils.WDp;

public class VoteStep0Fragment extends BaseFragment implements View.OnClickListener {

    private FragmentVoteStep0Binding fragmentVoteStep0Binding;

    private Map<Integer, String> selectedMap = Maps.newHashMap();

    public List<ResProposal> mProposalList;

    public static VoteStep0Fragment newInstance() {
        return new VoteStep0Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentVoteStep0Binding = FragmentVoteStep0Binding.inflate(inflater, container, false);
        initView();

        return fragmentVoteStep0Binding.getRoot();
    }

    private void initView() {
        mProposalList = new Gson().fromJson(getActivity().getIntent().getStringExtra("proposal"), new TypeToken<List<ResProposal>>() {
        }.getType());

        mProposalList.sort((o1, o2) -> o2.id - o1.id);
        fragmentVoteStep0Binding.recycler.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));

        fragmentVoteStep0Binding.recycler.setHasFixedSize(true);
        fragmentVoteStep0Binding.recycler.setItemViewCacheSize(20);
        fragmentVoteStep0Binding.recycler.setDrawingCacheEnabled(true);
        fragmentVoteStep0Binding.recycler.setAdapter(new ProposalSelectionAdapter());
        fragmentVoteStep0Binding.recycler.setItemAnimator(null);
        fragmentVoteStep0Binding.recycler.getAdapter().notifyDataSetChanged();

        fragmentVoteStep0Binding.btnCancel.setOnClickListener(this);
        fragmentVoteStep0Binding.btnNext.setOnClickListener(this);
    }


    public VoteActivity getSActivity() {
        return (VoteActivity) getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(fragmentVoteStep0Binding.btnCancel)) {
            getSActivity().onBeforeStep();
        } else if (v.equals(fragmentVoteStep0Binding.btnNext)) {
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
            return new ProposalSelectionHolder(ItemProposalSelectionBinding.inflate(getLayoutInflater()));
        }

        @Override
        public void onBindViewHolder(@NonNull final ProposalSelectionHolder proposalSelectionHolder, int position) {
            ResProposal item = mProposalList.get(position);
            proposalSelectionHolder.itemProposalSelectionBinding.proposalId.setText("# " + item.id);
            proposalSelectionHolder.itemProposalSelectionBinding.proposalTitle.setText(item.title);
            proposalSelectionHolder.itemProposalSelectionBinding.proposalDeadline.setText(WDp.getTimeVoteformat(getActivity(), item.voting_end_time)
                    + " " + WDp.getGapTime(WDp.convertDateToLong(getString(R.string.str_vote_time_format), item.voting_end_time)));

            bindVoteSelect(proposalSelectionHolder, position, item);
        }

        private void bindVoteSelect(ProposalSelectionHolder holder, int position, ResProposal item) {
            holder.itemProposalSelectionBinding.checkBtnYes.setAlpha(0.5f);
            holder.itemProposalSelectionBinding.checkBtnNo.setAlpha(0.5f);
            holder.itemProposalSelectionBinding.checkBtnNowithveto.setAlpha(0.5f);
            holder.itemProposalSelectionBinding.checkBtnAbstain.setAlpha(0.5f);
            holder.itemProposalSelectionBinding.checkBtnYes.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_vote_quorum));
            holder.itemProposalSelectionBinding.checkBtnNo.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_vote_quorum));
            holder.itemProposalSelectionBinding.checkBtnNowithveto.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_vote_quorum));
            holder.itemProposalSelectionBinding.checkBtnAbstain.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_vote_quorum));
            holder.itemProposalSelectionBinding.voteYesTitle.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorGrayDayNight));
            holder.itemProposalSelectionBinding.voteNoTitle.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorGrayDayNight));
            holder.itemProposalSelectionBinding.voteNowithvetoTitle.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorGrayDayNight));
            holder.itemProposalSelectionBinding.voteAbstainTitle.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorGrayDayNight));
            holder.itemProposalSelectionBinding.checkImgSelectedYes.clearColorFilter();
            holder.itemProposalSelectionBinding.checkImgSelectedNo.clearColorFilter();
            holder.itemProposalSelectionBinding.checkImgSelectedNowithveto.clearColorFilter();
            holder.itemProposalSelectionBinding.checkImgSelectedAbstain.clearColorFilter();

            if (selectedMap.containsKey(item.id)) {
                String selected = selectedMap.get(item.id);
                switch (selected) {
                    case "VOTE_OPTION_YES":
                        settingSelectedLayout(holder.itemProposalSelectionBinding.checkBtnYes, holder.itemProposalSelectionBinding.voteYesTitle, holder.itemProposalSelectionBinding.checkImgSelectedYes);
                        break;
                    case "VOTE_OPTION_NO":
                        settingSelectedLayout(holder.itemProposalSelectionBinding.checkBtnNo, holder.itemProposalSelectionBinding.voteNoTitle, holder.itemProposalSelectionBinding.checkImgSelectedNo);
                        break;
                    case "VOTE_OPTION_NO_WITH_VETO":
                        settingSelectedLayout(holder.itemProposalSelectionBinding.checkBtnNowithveto, holder.itemProposalSelectionBinding.voteNowithvetoTitle, holder.itemProposalSelectionBinding.checkImgSelectedNowithveto);
                        break;
                    case "VOTE_OPTION_ABSTAIN":
                        settingSelectedLayout(holder.itemProposalSelectionBinding.checkBtnAbstain, holder.itemProposalSelectionBinding.voteAbstainTitle, holder.itemProposalSelectionBinding.checkImgSelectedAbstain);
                        break;
                    default:
                        break;
                }
            }

            holder.itemProposalSelectionBinding.checkBtnYes.setOnClickListener(v -> {
                if (selectedMap.containsKey(item.id) && "VOTE_OPTION_YES".equals(selectedMap.get(item.id))) {
                    selectedMap.remove(item.id);
                } else {
                    selectedMap.put(item.id, "VOTE_OPTION_YES");
                }
                fragmentVoteStep0Binding.recycler.getAdapter().notifyItemChanged(position);
            });

            holder.itemProposalSelectionBinding.checkBtnNo.setOnClickListener(v -> {
                if (selectedMap.containsKey(item.id) && "VOTE_OPTION_NO".equals(selectedMap.get(item.id))) {
                    selectedMap.remove(item.id);
                } else {
                    selectedMap.put(item.id, "VOTE_OPTION_NO");
                }
                fragmentVoteStep0Binding.recycler.getAdapter().notifyItemChanged(position);
            });

            holder.itemProposalSelectionBinding.checkBtnNowithveto.setOnClickListener(v -> {
                if (selectedMap.containsKey(item.id) && "VOTE_OPTION_NO_WITH_VETO".equals(selectedMap.get(item.id))) {
                    selectedMap.remove(item.id);
                } else {
                    selectedMap.put(item.id, "VOTE_OPTION_NO_WITH_VETO");
                }
                fragmentVoteStep0Binding.recycler.getAdapter().notifyItemChanged(position);
            });

            holder.itemProposalSelectionBinding.checkBtnAbstain.setOnClickListener(v -> {
                if (selectedMap.containsKey(item.id) && "VOTE_OPTION_ABSTAIN".equals(selectedMap.get(item.id))) {
                    selectedMap.remove(item.id);
                } else {
                    selectedMap.put(item.id, "VOTE_OPTION_ABSTAIN");
                }
                fragmentVoteStep0Binding.recycler.getAdapter().notifyItemChanged(position);
            });
        }

        private void settingSelectedLayout(RelativeLayout yesBtnLayout, TextView titleYesTv, ImageView selectedYesImage) {
            int chainColor = getSActivity().mChainConfig.chainColor();
            Drawable roundBackground = ContextCompat.getDrawable(getActivity(), R.drawable.box_round_multi_vote);
            roundBackground = DrawableCompat.wrap(roundBackground);
            DrawableCompat.setTint(roundBackground, ContextCompat.getColor(getActivity(), chainColor));
            yesBtnLayout.setAlpha(1f);
            yesBtnLayout.setBackground(roundBackground);
            titleYesTv.setTextColor(ContextCompat.getColor(getActivity(), chainColor));
            selectedYesImage.setColorFilter(ContextCompat.getColor(getActivity(), chainColor), android.graphics.PorterDuff.Mode.SRC_IN);
        }

        @Override
        public int getItemCount() {
            return mProposalList.size();
        }

        public class ProposalSelectionHolder extends RecyclerView.ViewHolder {

            private ItemProposalSelectionBinding itemProposalSelectionBinding;

            public ProposalSelectionHolder(@NonNull ItemProposalSelectionBinding binding) {
                super(binding.getRoot());
                itemProposalSelectionBinding = binding;
            }
        }
    }
}
