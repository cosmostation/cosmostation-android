package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dialog.PaddedVerticalButtonAlertDialog;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class ValidatorMyFragment extends BaseFragment implements View.OnClickListener {

    public final static int SELECT_MY_VALIDATOR_SORTING = 6003;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private MyValidatorAdapter mMyValidatorAdapter;
    private TextView mValidatorSize, mSortType;
    private LinearLayout mBtnSort;

    private ChainConfig mChainConfig;

    public static ValidatorMyFragment newInstance() {
        return new ValidatorMyFragment();
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

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getMainActivity(), R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMainActivity().onFetchAllData();
                mMyValidatorAdapter.notifyDataSetChanged();
            }
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
        mChainConfig = ChainFactory.getChain(getMainActivity().mBaseChain);

        mValidatorSize.setText("" + getBaseDao().mGRpcMyValidators.size());
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
                return new RewardWithdrawHolder(getLayoutInflater().inflate(R.layout.item_reward_all_info, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
            if (getItemViewType(position) == TYPE_PROMOTION) {
                RewardPromotionHolder holder = (RewardPromotionHolder) viewHolder;
                holder.itemRoot.setCardBackgroundColor(ContextCompat.getColor(getActivity(), mChainConfig.chainBgColor()));

                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getMainActivity().onStartDelegate();
                    }
                });

            } else if (getItemViewType(position) == TYPE_HEADER_WITHDRAW_ALL) {
                final RewardWithdrawHolder holder = (RewardWithdrawHolder) viewHolder;
                final int dpDecimal = WDp.getDenomDecimal(getBaseDao(), mChainConfig, mChainConfig.mainDenom());

                WDp.setDpSymbol(getActivity(), getBaseDao(), mChainConfig, mChainConfig.mainDenom(), holder.itemTvDenom);
                final BigDecimal allRewardAmount = getBaseDao().getRewardSum(mChainConfig.mainDenom());
                holder.itemTvAllRewards.setText(WDp.getDpAmount2(getContext(), allRewardAmount, dpDecimal, 6));

                holder.itemBtnAllRewards.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getMainActivity().onCheckEasyClaim();
                    }
                });

                holder.itemBtnCompounding.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getMainActivity().onCheckEasyCompounding();
                    }
                });

            } else if (getItemViewType(position) == TYPE_MY_VALIDATOR) {
                final RewardMyValidatorHolder holder = (RewardMyValidatorHolder) viewHolder;
                holder.itemBandOracleOff.setVisibility(View.INVISIBLE);
                final int dpDecimal = WDp.getDenomDecimal(getBaseDao(), mChainConfig, mChainConfig.mainDenom());

                final Staking.Validator validator = getBaseDao().mGRpcMyValidators.get(position);
                final BigDecimal delegationAmount = getBaseDao().getDelegation(validator.getOperatorAddress());
                final BigDecimal undelegationAmount = getBaseDao().getUndelegation(validator.getOperatorAddress());
                final BigDecimal rewardAmount = getBaseDao().getReward(mChainConfig.mainDenom(), validator.getOperatorAddress());
                try {
                    Picasso.get().load(mChainConfig.monikerUrl() + validator.getOperatorAddress() + ".png").fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(holder.itemAvatar);
                } catch (Exception e) { }

                holder.itemTvMoniker.setText(validator.getDescription().getMoniker());
                holder.itemRoot.setCardBackgroundColor(ContextCompat.getColor(getActivity(), mChainConfig.chainBgColor()));
                holder.itemTvDelegateAmount.setText(WDp.getDpAmount2(getContext(), delegationAmount, dpDecimal, 6));
                holder.itemTvUndelegateAmount.setText(WDp.getDpAmount2(getContext(), undelegationAmount, dpDecimal, 6));
                holder.itemTvReward.setText(WDp.getDpAmount2(getContext(), rewardAmount, dpDecimal, 6));

                if (validator.getJailed()) {
                    holder.itemAvatar.setBorderColor(ContextCompat.getColor(getMainActivity(), R.color.colorRed));
                    holder.itemRevoked.setVisibility(View.VISIBLE);
                } else {
                    holder.itemAvatar.setBorderColor(ContextCompat.getColor(getMainActivity(), R.color.colorGray3));
                    holder.itemRevoked.setVisibility(View.GONE);
                }

                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getMainActivity().onStartValidatorDetailV1(validator.getOperatorAddress());
                    }
                });

                if (getMainActivity().mBaseChain.equals(BAND_MAIN)) {
                    if (getBaseDao().mChainParam != null && !getBaseDao().mChainParam.isOracleEnable(validator.getOperatorAddress())) {
                        holder.itemBandOracleOff.setVisibility(View.VISIBLE);
                    } else {
                        holder.itemBandOracleOff.setVisibility(View.INVISIBLE);
                    }
                }
            }
        }

        @Override
        public int getItemCount() {
            if (getBaseDao().mGRpcMyValidators == null || getBaseDao().mGRpcMyValidators.size() < 1) {
                return 1;
            } else {
                return getBaseDao().mGRpcMyValidators.size() + 1;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (getBaseDao().mGRpcMyValidators == null || getBaseDao().mGRpcMyValidators.size() < 1) {
                return TYPE_PROMOTION;
            } else if (getBaseDao().mGRpcMyValidators.size() >= 1 && position == getBaseDao().mGRpcMyValidators.size()) {
                return TYPE_HEADER_WITHDRAW_ALL;
            } else {
                return TYPE_MY_VALIDATOR;
            }
        }


        public class RewardMyValidatorHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            CircleImageView itemAvatar;
            ImageView itemRevoked, itemBandOracleOff;
            TextView itemTvMoniker;
            TextView itemTvDelegateAmount;
            TextView itemTvUndelegateAmount;
            TextView itemTvReward;

            public RewardMyValidatorHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot = itemView.findViewById(R.id.card_validator);
                itemAvatar = itemView.findViewById(R.id.avatar_validator);
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
            RelativeLayout itemBtnAllRewards, itemBtnCompounding;

            public RewardWithdrawHolder(@NonNull View itemView) {
                super(itemView);
                itemTvAllRewards = itemView.findViewById(R.id.tx_all_rewards);
                itemTvDenom = itemView.findViewById(R.id.tx_demon);
                itemBtnAllRewards = itemView.findViewById(R.id.btn_claim_all_reward);
                itemBtnCompounding = itemView.findViewById(R.id.btn_compounding);
            }
        }

    }

    public void onSortValidator() {
        if (isGRPC(getMainActivity().mBaseChain)) {
            if (getBaseDao().getMyValSorting() == 2) {
                mSortType.setText(getString(R.string.str_sorting_by_reward));
                WUtil.onSortByRewardV1(getBaseDao().mGRpcMyValidators, mChainConfig.mainDenom(), getBaseDao());

            } else if (getBaseDao().getMyValSorting() == 0) {
                WUtil.onSortByValidatorNameV1(getBaseDao().mGRpcMyValidators);
                mSortType.setText(getString(R.string.str_sorting_by_name));

            } else {
                WUtil.onSortByDelegateV1(getBaseDao().mGRpcMyValidators, getBaseDao());
                mSortType.setText(getString(R.string.str_sorting_by_my_delegated));

            }

        } else {
            if (getBaseDao().getMyValSorting() == 2) {
                WUtil.onSortByReward(getBaseDao().mMyValidators, mChainConfig.mainDenom(), getBaseDao());
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
        PaddedVerticalButtonAlertDialog.showTripleButton(getMainActivity(), getString(R.string.str_sorting_s), null,
                getString(R.string.str_sorting_by_name), View -> {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("sorting", 0);
                    onActivityResult(SELECT_MY_VALIDATOR_SORTING, Activity.RESULT_OK, resultIntent);
                },
                getString(R.string.str_sorting_by_my_delegated), View -> {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("sorting", 1);
                    onActivityResult(SELECT_MY_VALIDATOR_SORTING, Activity.RESULT_OK, resultIntent);
                },
                getString(R.string.str_sorting_by_reward), View -> {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("sorting", 2);
                    onActivityResult(SELECT_MY_VALIDATOR_SORTING, Activity.RESULT_OK, resultIntent);
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_MY_VALIDATOR_SORTING && resultCode == Activity.RESULT_OK) {
            getBaseDao().setMyValSorting(data.getIntExtra("sorting", 1));
            onRefreshTab();
        }
    }

}
