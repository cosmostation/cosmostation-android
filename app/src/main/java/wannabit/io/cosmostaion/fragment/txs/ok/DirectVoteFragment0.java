package wannabit.io.cosmostaion.fragment.txs.ok;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

import de.hdodenhof.circleimageview.CircleImageView;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.ok.OKVoteDirectActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.utils.WDp;

public class DirectVoteFragment0 extends BaseFragment implements View.OnClickListener {

    private Button mBefore, mNextBtn;
    private RecyclerView mRecyclerView;
    private TextView mSelectedValidatorCnt;
    private ToValidatorAdapter mToValidatorAdapter;

    public static DirectVoteFragment0 newInstance(Bundle bundle) {
        DirectVoteFragment0 fragment = new DirectVoteFragment0();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_direct_vote_0, container, false);
        mBefore = rootView.findViewById(R.id.btn_before);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mSelectedValidatorCnt = rootView.findViewById(R.id.selected_cnt);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mBefore.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(100);
        mRecyclerView.setDrawingCacheEnabled(true);
        mToValidatorAdapter = new ToValidatorAdapter();
        mRecyclerView.setAdapter(mToValidatorAdapter);

        onUpdateCnt();
        return rootView;
    }

    private void onUpdateCnt() {
        mSelectedValidatorCnt.setText("" + getSActivity().mValAddesses.size());
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            getSActivity().onNextStep();
        }
    }

    private OKVoteDirectActivity getSActivity() {
        return (OKVoteDirectActivity) getBaseActivity();
    }


    private class ToValidatorAdapter extends RecyclerView.Adapter<ToValidatorAdapter.ToValidatorHolder> {

        @NonNull
        @Override
        public ToValidatorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ToValidatorHolder(getLayoutInflater().inflate(R.layout.item_redelegate_validator, viewGroup, false));

        }

        @Override
        public void onBindViewHolder(@NonNull ToValidatorHolder holder, @SuppressLint("RecyclerView") int position) {
            final Validator validator = getBaseDao().mAllValidators.get(position);
            final ChainConfig chainConfig = ChainFactory.getChain(getSActivity().mBaseChain);
            holder.itemTvVotingPower.setText(WDp.getDpAmount2(getContext(), new BigDecimal(validator.delegator_shares), 0, 8));
            holder.itemTvCommission.setText(WDp.getCommissionRate("0"));
            try {
                Picasso.get().load(chainConfig.monikerUrl() + validator.operator_address + ".png").fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(holder.itemAvatar);
            } catch (Exception e) { }

            holder.itemTvMoniker.setText(validator.description.moniker);

            if (validator.jailed) {
                holder.itemAvatar.setBorderColor(ContextCompat.getColor(getSActivity(), R.color.colorRed));
                holder.itemRevoked.setVisibility(View.VISIBLE);
            } else {
                holder.itemAvatar.setBorderColor(ContextCompat.getColor(getSActivity(), R.color.colorGray3));
                holder.itemRevoked.setVisibility(View.GONE);
            }

            holder.itemChecked.setColorFilter(null);
            if (getSActivity().mValAddesses.contains(validator.operator_address)) {
                holder.itemChecked.setColorFilter(ContextCompat.getColor(getSActivity(), R.color.color_okx), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.itemCheckedBorder.setVisibility(View.VISIBLE);
            } else {
                holder.itemCheckedBorder.setVisibility(View.GONE);
            }

            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getSActivity().mValAddesses.contains(validator.operator_address)) {
                        if (getSActivity().mValAddesses.size() == 1) {
                            Toast.makeText(getContext(), R.string.error_min_1_validator, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        getSActivity().mValAddesses.remove(validator.operator_address);
                    } else {
                        if (getSActivity().mValAddesses.size() > 29) {
                            Toast.makeText(getContext(), R.string.error_max_30_validator, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        getSActivity().mValAddesses.add(validator.operator_address);
                    }
                    onUpdateCnt();
                    notifyItemChanged(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return getBaseDao().mAllValidators.size();
        }

        public class ToValidatorHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            CircleImageView itemAvatar;
            ImageView itemRevoked;
            ImageView itemChecked;
            TextView itemTvMoniker;
            TextView itemTvVotingPower;
            TextView itemTvCommission;
            View itemCheckedBorder;

            public ToValidatorHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot = itemView.findViewById(R.id.card_validator);
                itemAvatar = itemView.findViewById(R.id.avatar_validator);
                itemRevoked = itemView.findViewById(R.id.avatar_validator_revoke);
                itemChecked = itemView.findViewById(R.id.checked_validator);
                itemTvMoniker = itemView.findViewById(R.id.moniker_validator);
                itemTvVotingPower = itemView.findViewById(R.id.delegate_power_validator);
                itemTvCommission = itemView.findViewById(R.id.delegate_yield_commission);
                itemCheckedBorder = itemView.findViewById(R.id.check_border);
            }
        }

    }
}
