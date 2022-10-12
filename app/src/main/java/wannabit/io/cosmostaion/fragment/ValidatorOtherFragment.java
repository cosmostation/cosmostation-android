package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;

import cosmos.staking.v1beta1.Staking;
import de.hdodenhof.circleimageview.CircleImageView;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.ValidatorListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class ValidatorOtherFragment extends BaseFragment {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private OtherValidatorAdapter mOtherValidatorAdapter;
    private TextView mValidatorSize;

    public static ValidatorOtherFragment newInstance() {
        return new ValidatorOtherFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_validator_other, container, false);
        mSwipeRefreshLayout = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mValidatorSize = rootView.findViewById(R.id.validator_cnt);

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getMainActivity(), R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            getMainActivity().onFetchAllData();
            mOtherValidatorAdapter.notifyDataSetChanged();
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mOtherValidatorAdapter = new OtherValidatorAdapter();
        mRecyclerView.setAdapter(mOtherValidatorAdapter);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if (!isAdded()) return;
        if (isGRPC(getMainActivity().mBaseChain)) {
            mValidatorSize.setText("" + getBaseDao().mGRpcOtherValidators.size());
            WUtil.onSortByValidatorPowerV1(getBaseDao().mGRpcOtherValidators);
        } else {
            mValidatorSize.setText("" + getBaseDao().mOtherValidators.size());
            WUtil.onSortByValidatorPower(getBaseDao().mOtherValidators);
        }

        mOtherValidatorAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onBusyFetch() {
        if (!isAdded()) return;
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public ValidatorListActivity getMainActivity() {
        return (ValidatorListActivity) getBaseActivity();
    }

    private class OtherValidatorAdapter extends RecyclerView.Adapter<OtherValidatorAdapter.OtherValidatorHolder> {

        @NonNull
        @Override
        public OtherValidatorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new OtherValidatorHolder(getLayoutInflater().inflate(R.layout.item_reward_validator, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final OtherValidatorHolder holder, final int position) {
            holder.itemBandOracleOff.setVisibility(View.INVISIBLE);
            ChainConfig chainConfig = getMainActivity().mChainConfig;
            final int dpDecimal = WDp.getDenomDecimal(getBaseDao(), chainConfig, chainConfig.mainDenom());
            if (isGRPC(getMainActivity().mBaseChain)) {
                final Staking.Validator validator = getBaseDao().mGRpcOtherValidators.get(position);
                try {
                    Picasso.get().load(chainConfig.monikerUrl() + validator.getOperatorAddress() + ".png").fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(holder.itemAvatar);
                } catch (Exception e) { }

                holder.itemTvVotingPower.setText(WDp.getDpAmount2(getContext(), new BigDecimal(validator.getTokens()), dpDecimal, 6));
                holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getMainActivity().mBaseChain, BigDecimal.ONE));
                holder.itemTvMoniker.setText(validator.getDescription().getMoniker());

                if (validator.getJailed()) {
                    holder.itemAvatar.setBorderColor(ContextCompat.getColor(getMainActivity(), R.color.colorRed));
                    holder.itemRevoked.setVisibility(View.VISIBLE);
                } else {
                    holder.itemAvatar.setBorderColor(ContextCompat.getColor(getMainActivity(), R.color.colorGray3));
                    holder.itemRevoked.setVisibility(View.GONE);
                }

                if (getBaseDao().mGRpcMyValidators.contains(validator)) {
                    holder.itemRoot.setCardBackgroundColor(ContextCompat.getColor(getActivity(), chainConfig.chainBgColor()));
                } else {
                    holder.itemRoot.setCardBackgroundColor(ContextCompat.getColor(getMainActivity(), R.color.colorTransBg));
                }
                holder.itemRoot.setOnClickListener(v -> getMainActivity().onStartValidatorDetailV1(validator.getOperatorAddress()));

            } else {
                final Validator validator = getBaseDao().mOtherValidators.get(position);
                holder.itemTvVotingPower.setText(WDp.getDpAmount2(getContext(), new BigDecimal(validator.tokens), dpDecimal, 6));
                holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getMainActivity().mBaseChain, BigDecimal.ONE));
                holder.itemTvMoniker.setText(validator.description.moniker);
                holder.itemRoot.setOnClickListener(v -> getMainActivity().onStartValidatorDetail(validator));
                try {
                    Picasso.get().load(chainConfig.monikerUrl() + validator.operator_address + ".png").fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(holder.itemAvatar);
                } catch (Exception e) { }

                if (validator.jailed) {
                    holder.itemAvatar.setBorderColor(ContextCompat.getColor(getMainActivity(), R.color.colorRed));
                    holder.itemRevoked.setVisibility(View.VISIBLE);
                } else {
                    holder.itemAvatar.setBorderColor(ContextCompat.getColor(getMainActivity(), R.color.colorGray3));
                    holder.itemRevoked.setVisibility(View.GONE);
                }

                if (checkIsMyValidator(getBaseDao().mMyValidators, validator.description.moniker)) {
                    holder.itemRoot.setCardBackgroundColor(ContextCompat.getColor(getActivity(), chainConfig.chainBgColor()));
                } else {
                    holder.itemRoot.setCardBackgroundColor(ContextCompat.getColor(getMainActivity(), R.color.colorTransBg));
                }
            }
        }


        private boolean checkIsMyValidator(ArrayList<Validator> userList, final String targetName) {
            return FluentIterable.from(userList).anyMatch(input -> input.description.moniker.equals(targetName));
        }

        @Override
        public int getItemCount() {
            if (isGRPC(getMainActivity().mBaseChain)) {
                return getBaseDao().mGRpcOtherValidators.size();
            } else {
                return getBaseDao().mOtherValidators.size();
            }
        }

        public class OtherValidatorHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            CircleImageView itemAvatar;
            ImageView itemRevoked, itemBandOracleOff;
            TextView itemTvMoniker;
            TextView itemTvVotingPower;
            TextView itemTvSubtitle;
            TextView itemTvCommission;

            public OtherValidatorHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot = itemView.findViewById(R.id.card_validator);
                itemAvatar = itemView.findViewById(R.id.avatar_validator);
                itemRevoked = itemView.findViewById(R.id.avatar_validator_revoke);
                itemTvMoniker = itemView.findViewById(R.id.moniker_validator);
                itemBandOracleOff = itemView.findViewById(R.id.band_oracle_off);
                itemTvVotingPower = itemView.findViewById(R.id.delegate_power_validator);
                itemTvSubtitle = itemView.findViewById(R.id.subTitle2);
                itemTvCommission = itemView.findViewById(R.id.delegate_commission_validator);
            }
        }
    }
}
