package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;

import android.app.Activity;
import android.content.Intent;
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
import java.util.List;

import cosmos.staking.v1beta1.Staking;
import de.hdodenhof.circleimageview.CircleImageView;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.ValidatorListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IBusyFetchListener;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.dialog.Dialog_ValidatorSorting;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class ValidatorAllFragment extends BaseFragment implements View.OnClickListener, IRefreshTabListener, IBusyFetchListener {

    public final static int SELECT_All_VALIDATOR_SORTING = 6002;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private AllValidatorAdapter mAllValidatorAdapter;
    private TextView mValidatorSize, mSortType;
    private LinearLayout mBtnSort;

    public static ValidatorAllFragment newInstance(Bundle bundle) {
        ValidatorAllFragment fragment = new ValidatorAllFragment();
        fragment.setArguments(bundle);
        return fragment;
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
        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(rootView.getContext(), R.color.colorPrimary));
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
        if (getMainActivity().baseChain.isGRPC()) {
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
            final int dpDecimal = WDp.mainDivideDecimal(getMainActivity().baseChain);
            if (getMainActivity().baseChain.isGRPC()) {
                final Staking.Validator validator = getBaseDao().mGRpcTopValidators.get(position);
                holder.itemTvVotingPower.setText(WDp.getDpAmount2(new BigDecimal(validator.getTokens()), dpDecimal, 6));
                holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getMainActivity().baseChain, new BigDecimal(validator.getCommission().getCommissionRates().getRate()).movePointLeft(18)));
                try {
                    Picasso.get().load(WDp.getMonikerImgUrl(getMainActivity().baseChain, validator.getOperatorAddress())).fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(holder.itemAvatar);
                } catch (Exception e) {
                }

                holder.itemTvMoniker.setText(validator.getDescription().getMoniker());
                if (validator.getJailed()) {
                    holder.itemAvatar.setBorderColor(ContextCompat.getColor(requireContext(), R.color.colorRed));
                    holder.itemRevoked.setVisibility(View.VISIBLE);
                } else {
                    holder.itemAvatar.setBorderColor(ContextCompat.getColor(requireContext(), R.color.colorGray3));
                    holder.itemRevoked.setVisibility(View.GONE);
                }
                if (getBaseDao().mGRpcMyValidators.contains(validator)) {
                    holder.itemRoot.setCardBackgroundColor(WDp.getChainBgColor(getMainActivity(), getMainActivity().baseChain));
                } else {
                    holder.itemRoot.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorTransBg));
                }

                if (getMainActivity().baseChain.equals(BAND_MAIN)) {
                    holder.itemTvCommission.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray1));
                    if (getBaseDao().mChainParam != null && !getBaseDao().mChainParam.isOracleEnable(validator.getOperatorAddress())) {
                        holder.itemBandOracleOff.setVisibility(View.VISIBLE);
                        holder.itemTvCommission.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorRed));
                    } else {
                        holder.itemBandOracleOff.setVisibility(View.INVISIBLE);
                    }
                }

                if (getMainActivity().baseChain.equals(ALTHEA_TEST)) {
                    holder.itemTvCommission.setText("--");
                }

                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getMainActivity().onStartValidatorDetailV1(validator.getOperatorAddress());
                    }
                });

            } else {
                final Validator validator = getBaseDao().mTopValidators.get(position);
                holder.itemTvVotingPower.setText(WDp.getDpAmount2(new BigDecimal(validator.tokens), dpDecimal, 6));
                holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getMainActivity().baseChain, validator.getCommission()));
                holder.itemTvMoniker.setText(validator.description.moniker);
                holder.itemFree.setVisibility(View.GONE);
                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getMainActivity().onStartValidatorDetail(validator);
                    }
                });
                try {
                    Picasso.get().load(WDp.getMonikerImgUrl(getMainActivity().baseChain, validator.operator_address)).fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(holder.itemAvatar);
                } catch (Exception e) {
                }

                if (validator.jailed) {
                    holder.itemAvatar.setBorderColor(ContextCompat.getColor(requireContext(), R.color.colorRed));
                    holder.itemRevoked.setVisibility(View.VISIBLE);
                } else {
                    holder.itemAvatar.setBorderColor(ContextCompat.getColor(requireContext(), R.color.colorGray3));
                    holder.itemRevoked.setVisibility(View.GONE);
                }

                if (checkIsMyValidator(getBaseDao().getMyValidators(), validator.description.moniker)) {
                    holder.itemRoot.setCardBackgroundColor(WDp.getChainBgColor(getMainActivity(), getMainActivity().baseChain));
                } else {
                    holder.itemRoot.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorTransBg));
                }
            }
        }

        private boolean checkIsMyValidator(List<Validator> userList, final String targetName) {
            return FluentIterable.from(userList).anyMatch((Predicate<Validator>) input -> input.description.moniker.equals(targetName));
        }

        @Override
        public int getItemCount() {
            if (getMainActivity().baseChain.isGRPC()) {
                return getBaseDao().mGRpcTopValidators.size();
            } else {
                return getBaseDao().mTopValidators.size();
            }
        }

        public class AllValidatorHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            CircleImageView itemAvatar;
            ImageView itemRevoked, itemBandOracleOff;
            ImageView itemFree;
            TextView itemTvMoniker;
            TextView itemTvVotingPower;
            TextView itemTvCommission;

            public AllValidatorHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot = itemView.findViewById(R.id.card_validator);
                itemAvatar = itemView.findViewById(R.id.avatar_validator);
                itemRevoked = itemView.findViewById(R.id.avatar_validator_revoke);
                itemFree = itemView.findViewById(R.id.avatar_validator_free);
                itemTvMoniker = itemView.findViewById(R.id.moniker_validator);
                itemBandOracleOff = itemView.findViewById(R.id.band_oracle_off);
                itemTvVotingPower = itemView.findViewById(R.id.delegate_power_validator);
                itemTvCommission = itemView.findViewById(R.id.delegate_commission_validator);
            }
        }
    }


    public void onSortValidator() {
        if (getMainActivity().baseChain.isGRPC()) {
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
                WUtil.onSortingByCommission(getBaseDao().mTopValidators, getMainActivity().baseChain);
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
        Dialog_ValidatorSorting bottomSheetDialog = Dialog_ValidatorSorting.getInstance();
        bottomSheetDialog.setArguments(null);
        bottomSheetDialog.setTargetFragment(ValidatorAllFragment.this, SELECT_All_VALIDATOR_SORTING);
        bottomSheetDialog.show(getFragmentManager(), "dialog");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_All_VALIDATOR_SORTING && resultCode == Activity.RESULT_OK) {
            getBaseDao().setValSorting(data.getIntExtra("sorting", 1));
            onRefreshTab();
        }
    }

}