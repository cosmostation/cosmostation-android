package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

import cosmos.staking.v1beta1.Staking;
import de.hdodenhof.circleimageview.CircleImageView;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.ValidatorListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IBusyFetchListener;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.dialog.Dialog_My_ValidatorSorting;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class ValidatorMyFragment extends BaseFragment implements View.OnClickListener, IRefreshTabListener, IBusyFetchListener {

    public final static int SELECT_MY_VALIDATOR_SORTING = 6003;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private MyValidatorAdapter mMyValidatorAdapter;
    private TextView mValidatorSize, mSortType;
    private LinearLayout mBtnSort;

    public static ValidatorMyFragment newInstance(Bundle bundle) {
        ValidatorMyFragment fragment = new ValidatorMyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_validator_my, container, false);
        mSwipeRefreshLayout = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mValidatorSize = rootView.findViewById(R.id.validator_cnt);
        mSortType = rootView.findViewById(R.id.token_sort_type);
        mBtnSort = rootView.findViewById(R.id.btn_validator_sort);

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(rootView.getContext(), R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            getMainActivity().onFetchAllData();
            mMyValidatorAdapter.notifyDataSetChanged();
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mMyValidatorAdapter = new MyValidatorAdapter();
        mRecyclerView.setAdapter(mMyValidatorAdapter);
        mBtnSort.setOnClickListener(this);
        onRefreshTab();
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if (!isAdded()) return;
        if (getMainActivity().baseChain.isGRPC()) {
            mValidatorSize.setText("" + getBaseDao().mGRpcMyValidators.size());
        } else {
            mValidatorSize.setText("" + getBaseDao().mMyValidators.size());
        }
        onSortValidator();
        mMyValidatorAdapter.notifyDataSetChanged();
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
            onShowMyValidatorSort();
        }
    }


    private class MyValidatorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_MY_VALIDATOR = 1;
        private static final int TYPE_PROMOTION = 2;
        private static final int TYPE_HEADER_WITHDRAW_ALL = 3;


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_MY_VALIDATOR) {
                return new RewardMyValidatorHolder(getLayoutInflater().inflate(R.layout.item_reward_my_validator, viewGroup, false));
            } else if (viewType == TYPE_PROMOTION) {
                return new RewardPromotionHolder(getLayoutInflater().inflate(R.layout.item_reward_promotion, viewGroup, false));
            } else if (viewType == TYPE_HEADER_WITHDRAW_ALL) {
                return new RewardWithdrawHolder(getLayoutInflater().inflate(R.layout.item_reward_withdraw_all, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
            if (getItemViewType(position) == TYPE_PROMOTION) {
                RewardPromotionHolder holder = (RewardPromotionHolder) viewHolder;
                holder.itemRoot.setCardBackgroundColor(WDp.getChainBgColor(getMainActivity(), getMainActivity().baseChain));

                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getMainActivity().onStartDelegate();
                    }
                });

            } else if (getItemViewType(position) == TYPE_HEADER_WITHDRAW_ALL) {
                final RewardWithdrawHolder holder = (RewardWithdrawHolder) viewHolder;
                WDp.DpMainDenom(getMainActivity().account.baseChain, holder.itemTvDenom);
                final int dpDecimal = WDp.mainDivideDecimal(getMainActivity().baseChain);
                if (getMainActivity().baseChain.isGRPC()) {
                    final BigDecimal allRewardAmount = getBaseDao().getRewardSum(getMainActivity().baseChain.getMainDenom());
                    holder.itemTvAllRewards.setText(WDp.getDpAmount2(allRewardAmount, dpDecimal, 6));

                } else {
                    final BigDecimal allRewardAmount = getBaseDao().rewardAmount(getMainActivity().baseChain.getMainDenom());
                    holder.itemTvAllRewards.setText(WDp.getDpAmount2(allRewardAmount, dpDecimal, 6));
                }

                holder.itemBtnWithdrawAll.setOnClickListener(v -> getMainActivity().onStartRewardAll());

            } else if (getItemViewType(position) == TYPE_MY_VALIDATOR) {
                final RewardMyValidatorHolder holder = (RewardMyValidatorHolder) viewHolder;
                holder.itemBandOracleOff.setVisibility(View.INVISIBLE);
                final int dpDecimal = WDp.mainDivideDecimal(getMainActivity().baseChain);
                if (getMainActivity().baseChain.isGRPC()) {
                    final Staking.Validator validator = getBaseDao().mGRpcMyValidators.get(position);
                    final BigDecimal delegationAmount = getBaseDao().getDelegation(validator.getOperatorAddress());
                    final BigDecimal undelegationAmount = getBaseDao().getUndelegation(validator.getOperatorAddress());
                    final BigDecimal rewardAmount = getBaseDao().getReward(getMainActivity().baseChain.getMainDenom(), validator.getOperatorAddress());
                    try {
                        Picasso.get().load(WDp.getMonikerImgUrl(getMainActivity().baseChain, validator.getOperatorAddress())).fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(holder.itemAvatar);
                    } catch (Exception e) {
                    }

                    holder.itemTvMoniker.setText(validator.getDescription().getMoniker());
                    holder.itemRoot.setCardBackgroundColor(WDp.getChainBgColor(getMainActivity(), getMainActivity().baseChain));
                    holder.itemTvDelegateAmount.setText(WDp.getDpAmount2(delegationAmount, dpDecimal, 6));
                    holder.itemTvUndelegateAmount.setText(WDp.getDpAmount2(undelegationAmount, dpDecimal, 6));
                    holder.itemTvReward.setText(WDp.getDpAmount2(rewardAmount, dpDecimal, 6));

                    if (validator.getJailed()) {
                        holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorRed));
                        holder.itemRevoked.setVisibility(View.VISIBLE);
                    } else {
                        holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorGray3));
                        holder.itemRevoked.setVisibility(View.GONE);
                    }
                    holder.itemRoot.setOnClickListener(v -> getMainActivity().onStartValidatorDetailV1(validator.getOperatorAddress()));

                    if (getMainActivity().baseChain.equals(BAND_MAIN)) {
                        if (getBaseDao().mChainParam != null && !getBaseDao().mChainParam.isOracleEnable(validator.getOperatorAddress())) {
                            holder.itemBandOracleOff.setVisibility(View.VISIBLE);
                        } else {
                            holder.itemBandOracleOff.setVisibility(View.INVISIBLE);
                        }
                    }

                } else {
                    final Validator validator = getBaseDao().mMyValidators.get(position);
                    final BigDecimal delegationAmount = getBaseDao().delegatedAmountByValidator(validator.operator_address);
                    final BigDecimal undelegationAmount = getBaseDao().unbondingAmountByValidator(validator.operator_address);
                    final BigDecimal rewardAmount = getBaseDao().rewardAmountByValidator(getMainActivity().baseChain.getMainDenom(), validator.operator_address);
                    try {
                        Picasso.get().load(WDp.getMonikerImgUrl(getMainActivity().baseChain, validator.operator_address)).fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(holder.itemAvatar);
                    } catch (Exception e) {
                    }

                    holder.itemRoot.setCardBackgroundColor(WDp.getChainBgColor(getMainActivity(), getMainActivity().baseChain));
                    holder.itemTvMoniker.setText(validator.description.moniker);
                    holder.itemTvDelegateAmount.setText(WDp.getDpAmount2(delegationAmount, dpDecimal, 6));
                    holder.itemTvUndelegateAmount.setText(WDp.getDpAmount2(undelegationAmount, dpDecimal, 6));
                    holder.itemTvReward.setText(WDp.getDpAmount2(rewardAmount, dpDecimal, 6));

                    if (validator.jailed) {
                        holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorRed));
                        holder.itemRevoked.setVisibility(View.VISIBLE);
                    } else {
                        holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorGray3));
                        holder.itemRevoked.setVisibility(View.GONE);
                    }
                    holder.itemRoot.setOnClickListener(v -> getMainActivity().onStartValidatorDetail(validator));
                }
            }
        }

        @Override
        public int getItemCount() {
            if (getMainActivity().baseChain.isGRPC()) {
                if (getBaseDao().mGRpcMyValidators == null || getBaseDao().mGRpcMyValidators.size() < 1) {
                    return 1;
                } else if (getBaseDao().mGRpcMyValidators.size() == 1) {
                    return 1;
                } else if (getBaseDao().mGRpcMyValidators.size() >= 1) {
                    return getBaseDao().mGRpcMyValidators.size() + 1;
                }

            } else {
                if (getBaseDao().mMyValidators == null || getBaseDao().mMyValidators.size() < 1) {
                    return 1;
                } else if (getBaseDao().mMyValidators.size() == 1) {
                    return 1;
                } else if (getBaseDao().mMyValidators.size() >= 1) {
                    return getBaseDao().mMyValidators.size() + 1;
                }
            }
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            if (getMainActivity().baseChain.isGRPC()) {
                if (getBaseDao().mGRpcMyValidators == null || getBaseDao().mGRpcMyValidators.size() < 1) {
                    return TYPE_PROMOTION;
                } else if (getBaseDao().mGRpcMyValidators.size() > 1 && position == getBaseDao().mGRpcMyValidators.size()) {
                    return TYPE_HEADER_WITHDRAW_ALL;
                } else {
                    return TYPE_MY_VALIDATOR;
                }

            } else {
                if (getBaseDao().mMyValidators == null || getBaseDao().mMyValidators.size() < 1) {
                    return TYPE_PROMOTION;
                } else if (getBaseDao().mMyValidators.size() > 1 && position == getBaseDao().mMyValidators.size()) {
                    return TYPE_HEADER_WITHDRAW_ALL;
                } else {
                    return TYPE_MY_VALIDATOR;
                }

            }
        }


        public class RewardMyValidatorHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            CircleImageView itemAvatar;
            ImageView itemFree;
            ImageView itemRevoked, itemBandOracleOff;
            TextView itemTvMoniker;
            TextView itemTvDelegateAmount;
            TextView itemTvUndelegateAmount;
            TextView itemTvReward;

            public RewardMyValidatorHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot = itemView.findViewById(R.id.card_validator);
                itemAvatar = itemView.findViewById(R.id.avatar_validator);
                itemFree = itemView.findViewById(R.id.avatar_validator_free);
                itemRevoked = itemView.findViewById(R.id.avatar_validator_revoke);
                itemTvMoniker = itemView.findViewById(R.id.moniker_validator);
                itemBandOracleOff = itemView.findViewById(R.id.band_oracle_off);
                itemTvDelegateAmount = itemView.findViewById(R.id.delegate_amount_validator);
                itemTvUndelegateAmount = itemView.findViewById(R.id.undelegate_amount_validator);
                itemTvReward = itemView.findViewById(R.id.my_reward_validator);
            }
        }

        public class RewardPromotionHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            Button itemBtnDelegateStart;

            public RewardPromotionHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot = itemView.findViewById(R.id.card_validator);
                itemBtnDelegateStart = itemView.findViewById(R.id.btn_start_delegate);
            }
        }

        public class RewardWithdrawHolder extends RecyclerView.ViewHolder {
            TextView itemTvAllRewards, itemTvDenom;
            Button itemBtnWithdrawAll;

            public RewardWithdrawHolder(@NonNull View itemView) {
                super(itemView);
                itemTvAllRewards = itemView.findViewById(R.id.tx_all_rewards);
                itemTvDenom = itemView.findViewById(R.id.tx_demon);
                itemBtnWithdrawAll = itemView.findViewById(R.id.btn_withdraw_all);
            }
        }

    }

    public void onSortValidator() {
        if (getMainActivity().baseChain.isGRPC()) {
            if (getBaseDao().getMyValSorting() == 2) {
                mSortType.setText(getString(R.string.str_sorting_by_reward));
                WUtil.onSortByRewardV1(getBaseDao().mGRpcMyValidators, getMainActivity().baseChain.getMainDenom(), getBaseDao());

            } else if (getBaseDao().getMyValSorting() == 0) {
                WUtil.onSortByValidatorNameV1(getBaseDao().mGRpcMyValidators);
                mSortType.setText(getString(R.string.str_sorting_by_name));

            } else {
                WUtil.onSortByDelegateV1(getBaseDao().mGRpcMyValidators, getBaseDao());
                mSortType.setText(getString(R.string.str_sorting_by_my_delegated));

            }

        } else {
            if (getBaseDao().getMyValSorting() == 2) {
                WUtil.onSortByReward(getBaseDao().mMyValidators, getMainActivity().baseChain.getMainDenom(), getBaseDao());
                mSortType.setText(getString(R.string.str_sorting_by_reward));

            } else if (getBaseDao().getMyValSorting() == 0) {
                WUtil.onSortByValidatorName(getBaseDao().mMyValidators);
                mSortType.setText(getString(R.string.str_sorting_by_name));

            } else {
                WUtil.onSortByDelegate(getBaseDao().mMyValidators, getBaseDao());
                mSortType.setText(getString(R.string.str_sorting_by_my_delegated));
            }
        }

    }

    public void onShowMyValidatorSort() {
        Dialog_My_ValidatorSorting bottomSheetDialog = Dialog_My_ValidatorSorting.getInstance();
        bottomSheetDialog.setArguments(null);
        bottomSheetDialog.setTargetFragment(ValidatorMyFragment.this, SELECT_MY_VALIDATOR_SORTING);
        bottomSheetDialog.show(getFragmentManager(), "dialog");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_MY_VALIDATOR_SORTING && resultCode == Activity.RESULT_OK) {
            getBaseDao().setMyValSorting(data.getIntExtra("sorting", 1));
            onRefreshTab();
        }
    }

}
