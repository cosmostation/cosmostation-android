package wannabit.io.cosmostaion.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dao.Reward;
import wannabit.io.cosmostaion.dao.UnBondingState;
import wannabit.io.cosmostaion.dialog.Dialog_My_ValidatorSorting;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKeyBaseUser;
import wannabit.io.cosmostaion.network.res.ResLcdIrisReward;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class ValidatorMyFragment extends BaseFragment {

    public final static int SELECT_MY_VALIDATOR_SORTING = 6003;

    private SwipeRefreshLayout      mSwipeRefreshLayout;
    private RecyclerView            mRecyclerView;
    private MyValidatorAdapter      mMyValidatorAdapter;

    private ArrayList<Validator>        mMyValidators = new ArrayList<>();
    private ArrayList<Reward>           mRewards = new ArrayList<>();
    private ResLcdIrisReward            mIrisRewards;

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

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!getMainActivity().onFetchAccountInfo()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mMyValidatorAdapter = new MyValidatorAdapter();
        mRecyclerView.setAdapter(mMyValidatorAdapter);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
        mMyValidators   = getMainActivity().mMyValidators;
        mRewards        = getMainActivity().mRewards;
        mIrisRewards   = getMainActivity().mIrisReward;
        onSortValidator();

        mMyValidatorAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }


    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
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
                if (getMainActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg2));

                } else if (getMainActivity().mAccount.baseChain.equals(BaseChain.IRIS_MAIN.getChain())) {
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg4));
                }


            } else if (getItemViewType(position) == TYPE_HEADER_WITHDRAW_ALL) {
                final RewardWithdrawHolder holder       = (RewardWithdrawHolder)viewHolder;
                WDp.DpMainDenom(getContext(), getMainActivity().mAccount.baseChain, holder.itemTvDenom);
                if (getMainActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
                    holder.itemTvAllRewards.setText(WDp.getDpAllAtomRewardAmount(getContext(), mRewards, BaseChain.getChain(getMainActivity().mAccount.baseChain)));

                } else if (getMainActivity().mAccount.baseChain.equals(BaseChain.IRIS_MAIN.getChain())) {
                    holder.itemTvAllRewards.setText(WDp.getDpAllIrisRewardAmount(getContext(), mIrisRewards, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
                }

                holder.itemBtnWithdrawAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getMainActivity().onStartRewardAll();
                    }
                });


            } else if (getItemViewType(position) == TYPE_MY_VALIDATOR) {
                final RewardMyValidatorHolder holder    = (RewardMyValidatorHolder)viewHolder;
                final Validator validator               = mMyValidators.get(position);

                BondingState bonding = getBaseDao().onSelectBondingState(getMainActivity().mAccount.id, validator.operator_address);
                if(bonding != null && bonding.getBondingAmount(validator) != null) {
                    holder.itemTvDelegateAmount.setText(WDp.getDpAmount(getContext(), bonding.getBondingAmount(validator), 6, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
                } else {
                    holder.itemTvDelegateAmount.setText(WDp.getDpAmount(getContext(), BigDecimal.ZERO, 6, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
                }

                if (getMainActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg2));
                    holder.itemTvReward.setText(WDp.getValidatorReward(getContext(), mRewards, validator.operator_address , BaseChain.getChain(getMainActivity().mAccount.baseChain)));

                } else if (getMainActivity().mAccount.baseChain.equals(BaseChain.IRIS_MAIN.getChain())) {
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg4));
                    holder.itemTvReward.setText(WDp.getIrisValidatorReward(getContext(), mIrisRewards, validator.operator_address , BaseChain.getChain(getMainActivity().mAccount.baseChain)));
                }

                holder.itemTvMoniker.setText(validator.description.moniker);
                holder.itemFree.setVisibility(View.GONE);
                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getMainActivity().onStartValidatorDetail(validator);
                    }
                });


                holder.itemAvatar.setTag("imgv" + position);
                if(validator.keybaseInfo == null) {
                    holder.itemAvatar.setImageDrawable(getResources().getDrawable(R.drawable.validator_none_img));
                    if(!TextUtils.isEmpty(validator.description.identity)) {
                        ApiClient.getKeybaseService(getMainActivity()).getUserInfo("pictures", validator.description.identity).enqueue(new Callback<ResKeyBaseUser>() {
                            @Override
                            public void onResponse(Call<ResKeyBaseUser> call, final Response<ResKeyBaseUser> response) {
                                validator.keybaseInfo = response.body();
                                if(isAdded() && holder.itemAvatar.getTag().equals("imgv" + position)) {
                                    try {
                                        Picasso.get()
                                                .load(response.body().getUrl())
                                                .fit()
                                                .placeholder(R.drawable.validator_none_img)
                                                .into(holder.itemAvatar);
                                    }catch (Exception e) {}
                                }
                            }
                            @Override
                            public void onFailure(Call<ResKeyBaseUser> call, Throwable t) {}
                        });
                    }

                } else {
                    if(isAdded() && holder.itemAvatar.getTag().equals("imgv" + position)) {
                        try {
                            Picasso.get()
                                    .load(validator.keybaseInfo.getUrl())
                                    .fit()
                                    .placeholder(R.drawable.validator_none_img)
                                    .into(holder.itemAvatar);
                        }catch (Exception e) {}
                    }
                }

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
                holder.itemTvUndelegateAmount.setText(WDp.getDpAmount(getContext(), unBondSum, 6, BaseChain.getChain(getMainActivity().mAccount.baseChain)));

            }
        }

        @Override
        public int getItemCount() {
            if(mMyValidators == null || mMyValidators.size() < 1) {
                return 1;
            } else if (mMyValidators.size() == 1) {
                return 1;
            } else if (mMyValidators.size() >= 1) {
                return mMyValidators.size() + 1;
            }
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            if(mMyValidators == null || mMyValidators.size() < 1) {
                return TYPE_PROMOTION;
            } else if (mMyValidators.size() > 1 && position == mMyValidators.size()) {
                return TYPE_HEADER_WITHDRAW_ALL;
            } else {
                return TYPE_MY_VALIDATOR;
            }
        }



        public class RewardMyValidatorHolder extends RecyclerView.ViewHolder {
            CardView        itemRoot;
            CircleImageView itemAvatar;
            ImageView       itemFree;
            ImageView       itemRevoked;
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
        if(getBaseDao().getMyValSorting() == 2){
            if (getMainActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
                WUtil.onSortByReward(mMyValidators, mRewards);
            } else if (getMainActivity().mAccount.baseChain.equals(BaseChain.IRIS_MAIN.getChain())) {
                WUtil.onSortIrisByReward(mMyValidators, mIrisRewards);
            }


        } else if (getBaseDao().getMyValSorting() == 0){
            WUtil.onSortByValidatorName(mMyValidators);

        } else {
            WUtil.onSortByDelegate(getMainActivity().mAccount.id, mMyValidators, getBaseDao());
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
        if(requestCode == SELECT_MY_VALIDATOR_SORTING && resultCode == Activity.RESULT_OK) {
            getBaseDao().setMyValSorting(data.getIntExtra("sorting", 1));
            onRefreshTab();
        }
    }

}
