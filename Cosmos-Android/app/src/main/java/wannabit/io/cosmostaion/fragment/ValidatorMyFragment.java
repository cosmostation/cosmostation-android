package wannabit.io.cosmostaion.fragment;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;

import cosmos.staking.v1beta1.Staking;
import de.hdodenhof.circleimageview.CircleImageView;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.ValidatorListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dao.UnBondingState;
import wannabit.io.cosmostaion.dialog.Dialog_My_ValidatorSorting;
import wannabit.io.cosmostaion.model.Validator_V1;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.AKASH_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.BAND_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.CERTIK_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.IOV_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.SECRET_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_AKASH;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BAND;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CERTIK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SECRET;

public class ValidatorMyFragment extends BaseFragment implements View.OnClickListener {

    public final static int SELECT_MY_VALIDATOR_SORTING = 6003;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private MyValidatorAdapter          mMyValidatorAdapter;
    private TextView                    mValidatorSize, mSortType;
    private LinearLayout                mBtnSort;

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
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mValidatorSize          = rootView.findViewById(R.id.validator_cnt);
        mSortType               = rootView.findViewById(R.id.token_sort_type);
        mBtnSort                = rootView.findViewById(R.id.btn_validator_sort);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
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
        if (getMainActivity().mBaseChain.equals(COSMOS_MAIN) || getMainActivity().mBaseChain.equals(COSMOS_TEST) || getMainActivity().mBaseChain.equals(IRIS_TEST)) {
            mValidatorSize.setText(""+getBaseDao().mGRpcMyValidators.size());
        } else {
            mValidatorSize.setText(""+getBaseDao().mMyValidators.size());
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
        return (ValidatorListActivity)getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnSort)) {
            onShowMyValidatorSort();
        }
    }


    private class MyValidatorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_MY_VALIDATOR              = 1;
        private static final int TYPE_PROMOTION                 = 2;
        private static final int TYPE_HEADER_WITHDRAW_ALL       = 3;


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if(viewType == TYPE_MY_VALIDATOR) {
                return new RewardMyValidatorHolder(getLayoutInflater().inflate(R.layout.item_reward_my_validator, viewGroup, false));
            } else if(viewType == TYPE_PROMOTION) {
                return  new RewardPromotionHolder(getLayoutInflater().inflate(R.layout.item_reward_promotion, viewGroup, false));
            } else if(viewType == TYPE_HEADER_WITHDRAW_ALL) {
                return  new RewardWithdrawHolder(getLayoutInflater().inflate(R.layout.item_reward_withdraw_all, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
            if (getItemViewType(position) == TYPE_PROMOTION) {
                RewardPromotionHolder holder = (RewardPromotionHolder)viewHolder;
                holder.itemRoot.setCardBackgroundColor(WDp.getChainBgColor(getMainActivity(), getMainActivity().mBaseChain));

            } else if (getItemViewType(position) == TYPE_HEADER_WITHDRAW_ALL) {
                final RewardWithdrawHolder holder = (RewardWithdrawHolder)viewHolder;
                WDp.DpMainDenom(getContext(), getMainActivity().mAccount.baseChain, holder.itemTvDenom);
                if (getMainActivity().mBaseChain.equals(COSMOS_MAIN) || getMainActivity().mBaseChain.equals(COSMOS_TEST) || getMainActivity().mBaseChain.equals(IRIS_TEST)) {
                    final BigDecimal allRewardAmount = getBaseDao().getRewardSum(WDp.mainDenom(getMainActivity().mBaseChain));
                    holder.itemTvAllRewards.setText(WDp.getDpAmount2(getContext(), allRewardAmount, 6, 6));

                } else {
                    if (getMainActivity().mBaseChain.equals(IRIS_MAIN)) {
                        holder.itemTvAllRewards.setText(WDp.getDpAllIrisRewardAmount(getContext(), getBaseDao().mIrisReward, getChain(getMainActivity().mAccount.baseChain)));

                    } else if (getMainActivity().mBaseChain.equals(KAVA_MAIN) || getMainActivity().mBaseChain.equals(KAVA_TEST)) {
                        holder.itemTvAllRewards.setText(WDp.getDpAllRewardAmount(getContext(), getBaseDao().mRewards, getChain(getMainActivity().mAccount.baseChain), TOKEN_KAVA));

                    } else if (getMainActivity().mBaseChain.equals(BAND_MAIN)) {
                        holder.itemTvAllRewards.setText(WDp.getDpAllRewardAmount(getContext(), getBaseDao().mRewards, getChain(getMainActivity().mAccount.baseChain), TOKEN_BAND));

                    } else if (getMainActivity().mBaseChain.equals(IOV_MAIN)) {
                        holder.itemTvAllRewards.setText(WDp.getDpAllRewardAmount(getContext(), getBaseDao().mRewards, getChain(getMainActivity().mAccount.baseChain), TOKEN_IOV));

                    } else if (getMainActivity().mBaseChain.equals(IOV_TEST)) {
                        holder.itemTvAllRewards.setText(WDp.getDpAllRewardAmount(getContext(), getBaseDao().mRewards, getChain(getMainActivity().mAccount.baseChain), TOKEN_IOV_TEST));

                    } else if (getMainActivity().mBaseChain.equals(CERTIK_MAIN) || getMainActivity().mBaseChain.equals(CERTIK_TEST)) {
                        holder.itemTvAllRewards.setText(WDp.getDpAllRewardAmount(getContext(), getBaseDao().mRewards, getChain(getMainActivity().mAccount.baseChain), TOKEN_CERTIK));

                    } else if (getMainActivity().mBaseChain.equals(SECRET_MAIN)) {
                        holder.itemTvAllRewards.setText(WDp.getDpAllRewardAmount(getContext(), getBaseDao().mRewards, getChain(getMainActivity().mAccount.baseChain), TOKEN_SECRET));

                    } else if (getMainActivity().mBaseChain.equals(AKASH_MAIN)) {
                        holder.itemTvAllRewards.setText(WDp.getDpAllRewardAmount(getContext(), getBaseDao().mRewards, getChain(getMainActivity().mAccount.baseChain), TOKEN_AKASH));

                    }
                }

                holder.itemBtnWithdrawAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getMainActivity().onStartRewardAll();
                    }
                });

            } else if (getItemViewType(position) == TYPE_MY_VALIDATOR) {
                final RewardMyValidatorHolder holder    = (RewardMyValidatorHolder)viewHolder;
                holder.itemBandOracleOff.setVisibility(View.INVISIBLE);
                if (getMainActivity().mBaseChain.equals(COSMOS_MAIN) || getMainActivity().mBaseChain.equals(COSMOS_TEST) || getMainActivity().mBaseChain.equals(IRIS_TEST)) {
                    final Staking.Validator validator = getBaseDao().mGRpcMyValidators.get(position);
                    final BigDecimal delegationAmount = getBaseDao().getDelegation(validator.getOperatorAddress());
                    final BigDecimal undelegationAmount = getBaseDao().getUndelegation(validator.getOperatorAddress());
                    final BigDecimal rewardAmount = getBaseDao().getReward(WDp.mainDenom(getMainActivity().mBaseChain), validator.getOperatorAddress());
                    String monikerUrl = "";
                    if (getMainActivity().mBaseChain.equals(COSMOS_MAIN) || getMainActivity().mBaseChain.equals(COSMOS_TEST)) {
                        monikerUrl = COSMOS_VAL_URL + validator.getOperatorAddress() + ".png";
                    } else if (getMainActivity().mBaseChain.equals(IRIS_TEST)) {
                        monikerUrl = IRIS_VAL_URL + validator.getOperatorAddress() + ".png";
                    }
                    try {
                        Picasso.get().load(monikerUrl).fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img) .into(holder.itemAvatar);
                    } catch (Exception e){}

                    holder.itemTvMoniker.setText(validator.getDescription().getMoniker());
                    holder.itemRoot.setCardBackgroundColor(WDp.getChainBgColor(getMainActivity(), getMainActivity().mBaseChain));
                    holder.itemTvDelegateAmount.setText(WDp.getDpAmount2(getContext(), delegationAmount, 6, 6));
                    holder.itemTvUndelegateAmount.setText(WDp.getDpAmount2(getContext(), undelegationAmount, 6, 6));
                    holder.itemTvReward.setText(WDp.getDpAmount2(getContext(), rewardAmount, 6, 6));

                    if (validator.getJailed()) {
                        holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorRed));
                        holder.itemRevoked.setVisibility(View.VISIBLE);
                    } else {
                        holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorGray3));
                        holder.itemRevoked.setVisibility(View.GONE);
                    }
                    holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getMainActivity().onStartValidatorDetailV1(validator.getOperatorAddress());
                        }
                    });

                } else {
                    final Validator validator = getBaseDao().mMyValidators.get(position);
                    BondingState bonding = getBaseDao().onSelectBondingState(getMainActivity().mAccount.id, validator.operator_address);
                    if(bonding != null && bonding.getBondingAmount(validator) != null) {
                        holder.itemTvDelegateAmount.setText(WDp.getDpAmount(getContext(), bonding.getBondingAmount(validator), 6, getChain(getMainActivity().mAccount.baseChain)));
                    } else {
                        holder.itemTvDelegateAmount.setText(WDp.getDpAmount(getContext(), BigDecimal.ZERO, 6, getChain(getMainActivity().mAccount.baseChain)));
                    }

                    if (getMainActivity().mBaseChain.equals(IRIS_MAIN)) {
                        holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgIris));
                        holder.itemTvReward.setText(WDp.getIrisValidatorReward(getContext(), getBaseDao().mIrisReward, validator.operator_address , getChain(getMainActivity().mAccount.baseChain)));
                        try {
                            Picasso.get().load(IRIS_VAL_URL + validator.operator_address + ".png")
                                    .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                                    .into(holder.itemAvatar);
                        } catch (Exception e){}

                    } else if (getMainActivity().mBaseChain.equals(KAVA_MAIN) || getMainActivity().mBaseChain.equals(KAVA_TEST)) {
                        holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgKava));
                        holder.itemTvReward.setText(WDp.getValidatorReward(getContext(), getBaseDao().mRewards, validator.operator_address , getChain(getMainActivity().mAccount.baseChain), TOKEN_KAVA));
                        try {
                            Picasso.get().load(KAVA_VAL_URL + validator.operator_address + ".png")
                                    .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                                    .into(holder.itemAvatar);
                        } catch (Exception e){}

                    } else if (getMainActivity().mBaseChain.equals(BAND_MAIN)) {
                        holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgBand));
                        holder.itemTvReward.setText(WDp.getValidatorReward(getContext(), getBaseDao().mRewards, validator.operator_address , getChain(getMainActivity().mAccount.baseChain), TOKEN_BAND));
                        if (getBaseDao().mBandOracles != null && !getBaseDao().mBandOracles.isEnable(validator.operator_address)) {
                            holder.itemBandOracleOff.setVisibility(View.VISIBLE);
                        } else {
                            holder.itemBandOracleOff.setVisibility(View.INVISIBLE);
                        }
                        try {
                            Picasso.get().load(BAND_VAL_URL + validator.operator_address + ".png")
                                    .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                                    .into(holder.itemAvatar);
                        } catch (Exception e){}

                    } else if (getMainActivity().mBaseChain.equals(IOV_MAIN)) {
                        holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgStarname));
                        holder.itemTvReward.setText(WDp.getValidatorReward(getContext(), getBaseDao().mRewards, validator.operator_address , getChain(getMainActivity().mAccount.baseChain), TOKEN_IOV));
                        try {
                            Picasso.get().load(IOV_VAL_URL + validator.operator_address + ".png")
                                    .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                                    .into(holder.itemAvatar);
                        } catch (Exception e){}

                    } else if (getMainActivity().mBaseChain.equals(IOV_TEST)) {
                        holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgStarname));
                        holder.itemTvReward.setText(WDp.getValidatorReward(getContext(), getBaseDao().mRewards, validator.operator_address , getChain(getMainActivity().mAccount.baseChain), TOKEN_IOV_TEST));

                    } else if (getMainActivity().mBaseChain.equals(CERTIK_MAIN) || getMainActivity().mBaseChain.equals(CERTIK_TEST)) {
                        holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgCertik));
                        holder.itemTvReward.setText(WDp.getValidatorReward(getContext(), getBaseDao().mRewards, validator.operator_address , getChain(getMainActivity().mAccount.baseChain), TOKEN_CERTIK));
                        try {
                            Picasso.get().load(CERTIK_VAL_URL + validator.operator_address + ".png")
                                    .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                                    .into(holder.itemAvatar);
                        } catch (Exception e){}

                    } else if (getMainActivity().mBaseChain.equals(SECRET_MAIN)) {
                        holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgSecret));
                        holder.itemTvReward.setText(WDp.getValidatorReward(getContext(), getBaseDao().mRewards, validator.operator_address , getChain(getMainActivity().mAccount.baseChain), TOKEN_SECRET));
                        try {
                            Picasso.get().load(SECRET_VAL_URL + validator.operator_address + ".png")
                                    .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                                    .into(holder.itemAvatar);
                        } catch (Exception e){}

                    } else if (getMainActivity().mBaseChain.equals(AKASH_MAIN)) {
                        holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgAkash));
                        holder.itemTvReward.setText(WDp.getValidatorReward(getContext(), getBaseDao().mRewards, validator.operator_address , getChain(getMainActivity().mAccount.baseChain), TOKEN_AKASH));
                        try {
                            Picasso.get().load(AKASH_VAL_URL + validator.operator_address + ".png")
                                    .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                                    .into(holder.itemAvatar);
                        } catch (Exception e){}

                    }

                    holder.itemTvMoniker.setText(validator.description.moniker);
                    holder.itemFree.setVisibility(View.GONE);
                    holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getMainActivity().onStartValidatorDetail(validator);
                        }
                    });

                    if(validator.jailed) {
                        holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorRed));
                        holder.itemRevoked.setVisibility(View.VISIBLE);
                    } else {
                        holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorGray3));
                        holder.itemRevoked.setVisibility(View.GONE);
                    }

                    BigDecimal unBondSum = BigDecimal.ZERO;
                    ArrayList<UnBondingState> unBondingStates = getBaseDao().onSelectUnbondingStates(getMainActivity().mAccount.id, validator.operator_address);
                    for(UnBondingState unbond:unBondingStates) {
                        unBondSum = unBondSum.add(unbond.balance);
                    }
                    holder.itemTvUndelegateAmount.setText(WDp.getDpAmount(getContext(), unBondSum, 6, getChain(getMainActivity().mAccount.baseChain)));

                }
            }
        }

        @Override
        public int getItemCount() {
            if (getMainActivity().mBaseChain.equals(COSMOS_MAIN) || getMainActivity().mBaseChain.equals(COSMOS_TEST) || getMainActivity().mBaseChain.equals(IRIS_TEST)) {
                if(getBaseDao().mGRpcMyValidators == null || getBaseDao().mGRpcMyValidators.size() < 1) {
                    return 1;
                } else if (getBaseDao().mGRpcMyValidators.size() == 1) {
                    return 1;
                } else if (getBaseDao().mGRpcMyValidators.size() >= 1) {
                    return getBaseDao().mGRpcMyValidators.size() + 1;
                }

            } else {
                if(getBaseDao().mMyValidators == null || getBaseDao().mMyValidators.size() < 1) {
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
            if (getMainActivity().mBaseChain.equals(COSMOS_MAIN) || getMainActivity().mBaseChain.equals(COSMOS_TEST) || getMainActivity().mBaseChain.equals(IRIS_TEST)) {
                if (getBaseDao().mGRpcMyValidators == null ||getBaseDao().mGRpcMyValidators.size() < 1) {
                    return TYPE_PROMOTION;
                } else if (getBaseDao().mGRpcMyValidators.size() > 1 && position == getBaseDao().mGRpcMyValidators.size()) {
                    return TYPE_HEADER_WITHDRAW_ALL;
                } else {
                    return TYPE_MY_VALIDATOR;
                }

            } else {
                if (getBaseDao().mMyValidators == null ||getBaseDao().mMyValidators.size() < 1) {
                    return TYPE_PROMOTION;
                } else if (getBaseDao().mMyValidators.size() > 1 && position == getBaseDao().mMyValidators.size()) {
                    return TYPE_HEADER_WITHDRAW_ALL;
                } else {
                    return TYPE_MY_VALIDATOR;
                }

            }
        }



        public class RewardMyValidatorHolder extends RecyclerView.ViewHolder {
            CardView        itemRoot;
            CircleImageView itemAvatar;
            ImageView       itemFree;
            ImageView       itemRevoked, itemBandOracleOff;
            TextView        itemTvMoniker;
            TextView        itemTvDelegateAmount;
            TextView        itemTvUndelegateAmount;
            TextView        itemTvReward;

            public RewardMyValidatorHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot            = itemView.findViewById(R.id.card_validator);
                itemAvatar          = itemView.findViewById(R.id.avatar_validator);
                itemFree            = itemView.findViewById(R.id.avatar_validator_free);
                itemRevoked         = itemView.findViewById(R.id.avatar_validator_revoke);
                itemTvMoniker       = itemView.findViewById(R.id.moniker_validator);
                itemBandOracleOff   = itemView.findViewById(R.id.band_oracle_off);
                itemTvDelegateAmount = itemView.findViewById(R.id.delegate_amount_validator);
                itemTvUndelegateAmount = itemView.findViewById(R.id.undelegate_amount_validator);
                itemTvReward        = itemView.findViewById(R.id.my_reward_validator);
            }
        }

        public class RewardPromotionHolder extends RecyclerView.ViewHolder {
            CardView    itemRoot;
            Button      itemBtnDelegateStart;

            public RewardPromotionHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot                = itemView.findViewById(R.id.card_validator);
                itemBtnDelegateStart    = itemView.findViewById(R.id.btn_start_delegate);
            }
        }

        public class RewardWithdrawHolder extends RecyclerView.ViewHolder {
            TextView itemTvAllRewards, itemTvDenom;
            Button itemBtnWithdrawAll;

            public RewardWithdrawHolder(@NonNull View itemView) {
                super(itemView);
                itemTvAllRewards        = itemView.findViewById(R.id.tx_all_rewards);
                itemTvDenom             = itemView.findViewById(R.id.tx_demon);
                itemBtnWithdrawAll      = itemView.findViewById(R.id.btn_withdraw_all);
            }
        }

    }

    public void onSortValidator() {
        if (getMainActivity().mBaseChain.equals(COSMOS_MAIN) || getMainActivity().mBaseChain.equals(COSMOS_TEST) || getMainActivity().mBaseChain.equals(IRIS_TEST)) {
            if (getBaseDao().getMyValSorting() == 2) {
                mSortType.setText(getString(R.string.str_sorting_by_reward));
                WUtil.onSortByRewardV1(getBaseDao().mGRpcMyValidators, WDp.mainDenom(getMainActivity().mBaseChain), getBaseDao());

            } else if (getBaseDao().getMyValSorting() == 0) {
                WUtil.onSortByValidatorNameV1(getBaseDao().mGRpcMyValidators);
                mSortType.setText(getString(R.string.str_sorting_by_name));

            } else {
                WUtil.onSortByDelegateV1(getMainActivity().mAccount.id, getBaseDao().mGRpcMyValidators, getBaseDao());
                mSortType.setText(getString(R.string.str_sorting_by_my_delegated));

            }

        } else {
            if (getBaseDao().getMyValSorting() == 2) {
                if (getMainActivity().mBaseChain.equals(IRIS_MAIN)) {
                    WUtil.onSortIrisByReward(getBaseDao().mMyValidators, getBaseDao().mIrisReward);

                } else if (getMainActivity().mBaseChain.equals(KAVA_MAIN) || getMainActivity().mBaseChain.equals(KAVA_TEST)) {
                    WUtil.onSortByReward(getBaseDao().mMyValidators, getBaseDao().mRewards, TOKEN_KAVA);

                } else if (getMainActivity().mBaseChain.equals(BAND_MAIN)) {
                    WUtil.onSortByReward(getBaseDao().mMyValidators, getBaseDao().mRewards, TOKEN_BAND);

                } else if (getMainActivity().mBaseChain.equals(IOV_MAIN)) {
                    WUtil.onSortByReward(getBaseDao().mMyValidators, getBaseDao().mRewards, TOKEN_IOV);

                } else if (getMainActivity().mBaseChain.equals(IOV_TEST)) {
                    WUtil.onSortByReward(getBaseDao().mMyValidators, getBaseDao().mRewards, TOKEN_IOV_TEST);

                } else if (getMainActivity().mBaseChain.equals(CERTIK_MAIN) || getMainActivity().mBaseChain.equals(CERTIK_TEST)) {
                    WUtil.onSortByReward(getBaseDao().mMyValidators, getBaseDao().mRewards, TOKEN_CERTIK);

                } else if (getMainActivity().mBaseChain.equals(SECRET_MAIN)) {
                    WUtil.onSortByReward(getBaseDao().mMyValidators, getBaseDao().mRewards, TOKEN_SECRET);

                } else if (getMainActivity().mBaseChain.equals(AKASH_MAIN)) {
                    WUtil.onSortByReward(getBaseDao().mMyValidators, getBaseDao().mRewards, TOKEN_AKASH);

                }
                mSortType.setText(getString(R.string.str_sorting_by_reward));

            } else if (getBaseDao().getMyValSorting() == 0) {
                WUtil.onSortByValidatorName(getBaseDao().mMyValidators);
                mSortType.setText(getString(R.string.str_sorting_by_name));

            } else {
                WUtil.onSortByDelegate(getMainActivity().mAccount.id, getBaseDao().mMyValidators, getBaseDao());
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
