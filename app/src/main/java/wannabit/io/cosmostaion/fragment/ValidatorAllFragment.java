package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import wannabit.io.cosmostaion.dialog.PaddedVerticalButtonAlertDialog;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class ValidatorAllFragment extends BaseFragment implements View.OnClickListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private AllValidatorAdapter mAllValidatorAdapter;
    private TextView mValidatorSize, mSortType;
    private LinearLayout mBtnSort;

    public static ValidatorAllFragment newInstance() {
        return new ValidatorAllFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_validator_all, container, false);
        mSwipeRefreshLayout = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mValidatorSize = rootView.findViewById(R.id.validator_cnt);
        mSortType = rootView.findViewById(R.id.token_sort_type);
        mBtnSort = rootView.findViewById(R.id.btn_validator_sort);
        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getMainActivity(), R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            getMainActivity().onFetchAllData();
            mAllValidatorAdapter.notifyDataSetChanged();
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(50);
        mRecyclerView.setDrawingCacheEnabled(true);
        mAllValidatorAdapter = new AllValidatorAdapter();
        mRecyclerView.setAdapter(mAllValidatorAdapter);
        mBtnSort.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if (!isAdded()) return;
        if (isGRPC(getMainActivity().mBaseChain)) {
            mValidatorSize.setText("" + getBaseDao().mGRpcTopValidators.size());
        } else {
            mValidatorSize.setText("" + getBaseDao().mTopValidators.size());
        }
        onSortValidator();
        mAllValidatorAdapter.notifyDataSetChanged();
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

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnSort)) {
            onShowAllValidatorSort();
        }
    }


    private class AllValidatorAdapter extends RecyclerView.Adapter<AllValidatorAdapter.AllValidatorHolder> {

        @NonNull
        @Override
        public AllValidatorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new AllValidatorHolder(getLayoutInflater().inflate(R.layout.item_reward_validator, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final AllValidatorHolder holder, final int position) {
            holder.itemBandOracleOff.setVisibility(View.INVISIBLE);
            ChainConfig chainConfig = getMainActivity().mChainConfig;
            final int dpDecimal = WDp.getDenomDecimal(getBaseDao(), chainConfig, chainConfig.mainDenom());

            if (isGRPC(getMainActivity().mBaseChain)) {
                final Staking.Validator validator = getBaseDao().mGRpcTopValidators.get(position);
                holder.itemTvVotingPower.setText(WDp.getDpAmount2(getContext(), new BigDecimal(validator.getTokens()), dpDecimal, 6));
                holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getMainActivity().mBaseChain, new BigDecimal(validator.getCommission().getCommissionRates().getRate()).movePointLeft(18)));
                try {
                    Picasso.get().load(chainConfig.monikerUrl() + validator.getOperatorAddress() + ".png").fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(holder.itemAvatar);
                } catch (Exception e) {
                }

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

                if (getMainActivity().mBaseChain.equals(BAND_MAIN)) {
                    holder.itemTvCommission.setTextColor(ContextCompat.getColor(getMainActivity(), R.color.colorGray1));
                    if (getBaseDao().mChainParam != null && !getBaseDao().mChainParam.isOracleEnable(validator.getOperatorAddress())) {
                        holder.itemBandOracleOff.setVisibility(View.VISIBLE);
                        holder.itemTvCommission.setTextColor(ContextCompat.getColor(getMainActivity(), R.color.colorRed));
                    } else {
                        holder.itemBandOracleOff.setVisibility(View.INVISIBLE);
                    }
                }

                holder.itemRoot.setOnClickListener(v -> getMainActivity().onStartValidatorDetailV1(validator.getOperatorAddress()));

            } else {
                final Validator validator = getBaseDao().mTopValidators.get(position);
                holder.itemTvVotingPower.setText(WDp.getDpAmount2(getContext(), new BigDecimal(validator.tokens), dpDecimal, 6));
                holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getMainActivity().mBaseChain, validator.getCommission()));
                holder.itemTvMoniker.setText(validator.description.moniker);
                holder.itemRoot.setOnClickListener(v -> getMainActivity().onStartValidatorDetail(validator));
                try {
                    Picasso.get().load(chainConfig.monikerUrl() + validator.operator_address + ".png").fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(holder.itemAvatar);
                } catch (Exception e) {
                }

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
            return FluentIterable.from(userList).anyMatch(new Predicate<Validator>() {
                @Override
                public boolean apply(@Nullable Validator input) {
                    return input.description.moniker.equals(targetName);
                }
            });
        }

        @Override
        public int getItemCount() {
            if (isGRPC(getMainActivity().mBaseChain)) {
                return getBaseDao().mGRpcTopValidators.size();
            } else {
                return getBaseDao().mTopValidators.size();
            }
        }

        public class AllValidatorHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            CircleImageView itemAvatar;
            ImageView itemRevoked, itemBandOracleOff;
            TextView itemTvMoniker;
            TextView itemTvVotingPower;
            TextView itemTvCommission;

            public AllValidatorHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot = itemView.findViewById(R.id.card_validator);
                itemAvatar = itemView.findViewById(R.id.avatar_validator);
                itemRevoked = itemView.findViewById(R.id.avatar_validator_revoke);
                itemTvMoniker = itemView.findViewById(R.id.moniker_validator);
                itemBandOracleOff = itemView.findViewById(R.id.band_oracle_off);
                itemTvVotingPower = itemView.findViewById(R.id.delegate_power_validator);
                itemTvCommission = itemView.findViewById(R.id.delegate_commission_validator);
            }
        }
    }


    public void onSortValidator() {
        if (isGRPC(getMainActivity().mBaseChain)) {
            if (getBaseDao().getValSorting() == 2) {
                WUtil.onSortingByCommissionV1(getBaseDao().mGRpcTopValidators);
                mSortType.setText(getString(R.string.str_sorting_by_yield));
            } else if (getBaseDao().getValSorting() == 0) {
                WUtil.onSortByValidatorNameV1(getBaseDao().mGRpcTopValidators);
                mSortType.setText(getString(R.string.str_sorting_by_name));
            } else {
                WUtil.onSortByValidatorPowerV1(getBaseDao().mGRpcTopValidators);
                mSortType.setText(getString(R.string.str_sorting_by_power));
            }

        } else {
            if (getBaseDao().getValSorting() == 2) {
                WUtil.onSortingByCommission(getBaseDao().mTopValidators, getMainActivity().mBaseChain);
                mSortType.setText(getString(R.string.str_sorting_by_yield));
            } else if (getBaseDao().getValSorting() == 0) {
                WUtil.onSortByValidatorName(getBaseDao().mTopValidators);
                mSortType.setText(getString(R.string.str_sorting_by_name));
            } else {
                WUtil.onSortByValidatorPower(getBaseDao().mTopValidators);
                mSortType.setText(getString(R.string.str_sorting_by_power));
            }
        }
    }

    public void onShowAllValidatorSort() {
        PaddedVerticalButtonAlertDialog.showTripleButton(getMainActivity(), getString(R.string.str_sorting_s), null,
                getString(R.string.str_sorting_by_name), View -> {
                    getBaseDao().setValSorting(0);
                    onRefreshTab();
                },
                getString(R.string.str_sorting_by_power), View -> {
                    getBaseDao().setValSorting(1);
                    onRefreshTab();
                },
                getString(R.string.str_sorting_by_yield), View -> {
                    getBaseDao().setValSorting(2);
                    onRefreshTab();
                });
    }
}