package wannabit.io.cosmostaion.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.ValidatorActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.utils.WLog;

public class MainRewardFragment extends BaseFragment {

    private ViewPager                   mValidatorPager;
    private TabLayout                   mValidatorTapLayer;
    private ValidatorPageAdapter        mPageAdapter;


    public static MainRewardFragment newInstance(Bundle bundle) {
        MainRewardFragment fragment = new MainRewardFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_reward, container, false);
        mValidatorTapLayer = rootView.findViewById(R.id.validator_tab);
        mValidatorPager = rootView.findViewById(R.id.validator_view_pager);

        mPageAdapter = new ValidatorPageAdapter(getChildFragmentManager());
        mValidatorPager.setAdapter(mPageAdapter);
        mValidatorTapLayer.setupWithViewPager(mValidatorPager);
        mValidatorTapLayer.setTabRippleColor(null);

        View tab0 = LayoutInflater.from(getContext()).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText0  = tab0.findViewById(R.id.tabItemText);
        tabItemText0.setText(getString(R.string.str_my_validators) + "(" + getMainActivity().mMyValidators.size() + ")");
        mValidatorTapLayer.getTabAt(0).setCustomView(tab0);

        View tab1 = LayoutInflater.from(getContext()).inflate(R.layout.view_tab_validator, null);
        TextView            tabItemText1  = tab1.findViewById(R.id.tabItemText);
        tabItemText1.setText(getString(R.string.str_all_validators)+ "(" + getMainActivity().mAllValidators.size() + ")");
        mValidatorTapLayer.getTabAt(1).setCustomView(tab1);
        mValidatorPager.setCurrentItem(0, false);

        mValidatorPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }

            @Override
            public void onPageScrollStateChanged(int i) { }

            @Override
            public void onPageSelected(int i) {
                WLog.w("onPageSelected : " + i);
                mPageAdapter.mFragments.get(i).onRefreshTab();
            }
        });

        return rootView;
    }


    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
        mPageAdapter.getCurrentFragment().onRefreshTab();
        onUpdateTab();
    }

    private void onUpdateTab() {
        TabLayout.Tab tab0 = mValidatorTapLayer.getTabAt(0);
        View view0 = tab0.getCustomView();
        TextView tabItemText0 = view0.findViewById(R.id.tabItemText);
        tabItemText0.setText(getString(R.string.str_my_validators) + " (" + getMainActivity().mMyValidators.size() + ")");

        TabLayout.Tab tab1 = mValidatorTapLayer.getTabAt(1);
        View view1 = tab1.getCustomView();
        TextView tabItemText1 = view1.findViewById(R.id.tabItemText);
        tabItemText1.setText(getString(R.string.str_all_validators)+ " (" + getMainActivity().mAllValidators.size() + ")");
    }

    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
    }


    private class ValidatorPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public ValidatorPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(ValidatorMyFragment.newInstance(null));
            mFragments.add(ValidatorAllFragment.newInstance(null));
        }

        @Override
        public BaseFragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            if (getCurrentFragment() != object) {
                mCurrentFragment = ((BaseFragment) object);
            }
            super.setPrimaryItem(container, position, object);
        }

        public BaseFragment getCurrentFragment() {
            return mCurrentFragment;
        }

        public ArrayList<BaseFragment> getFragments() {
            return mFragments;
        }
    }
}

/*
public class MainRewardFragment extends BaseFragment {

    private SwipeRefreshLayout          mSwipeRefreshLayout;
    private RecyclerView                mRecyclerView;

    private RewardAdapter               mRewardAdapter;

    private ArrayList<Validator>        mMyValidators = new ArrayList<>();
    private ArrayList<Validator>        mElseValidators = new ArrayList<>();
    private ArrayList<BondingState>     mBondings = new ArrayList<>();
    private ArrayList<Reward>           mRewards = new ArrayList<>();

    public static MainRewardFragment newInstance(Bundle bundle) {
        MainRewardFragment fragment = new MainRewardFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_reward, container, false);
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
        mRewardAdapter = new RewardAdapter();
        mRecyclerView.setAdapter(mRewardAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy >0) {
                    if (getMainActivity().mFloatBtn.isShown()) {
                        getMainActivity().mFloatBtn.hide();
                    }
                }
                else if (dy <0) {
                    if (!getMainActivity().mFloatBtn.isShown()) {
                        getMainActivity().mFloatBtn.show();
                    }
                }
            }
        });


        return rootView;
    }


    @Override
    public void onRefreshTab() {
//        WLog.w("MainRewardFragment onRefreshTab");
        if(!isAdded()) return;
        mSwipeRefreshLayout.setRefreshing(false);
        mMyValidators   = getMainActivity().mMyValidators;
        mElseValidators = getMainActivity().mElseValidators;
        mBondings       = getMainActivity().mBondings;
        mRewards        = getMainActivity().mRewards;
//        WLog.w("MainRewardFragment onRefreshTab : " + mMyValidators.size());
//        WLog.w("MainRewardFragment onRefreshTab : " + mElseValidators.size());
//        WLog.w("MainRewardFragment onRefreshTab : " + mRewards.size());
//        WLog.w("MainRewardFragment onRefreshTab  : " + mRewards.get(0).amount.get(0).denom);
//        WLog.w("MainRewardFragment onRefreshTab  : " + mRewards.get(0).amount.get(0).amount);
        if(mMyValidators.size() <= 0 && mElseValidators.size() <= 0) return;
        mRewardAdapter.notifyDataSetChanged();
    }


    public void onStartValidatorDetail(Validator validator) {
        Intent intent = new Intent(getMainActivity(), ValidatorActivity.class);
        intent.putExtra("valAddr", validator.operator_address);
        startActivity(intent);
    }

    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
    }



    private class RewardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final int TYPE_HEADER_MY_VALIDATOR       = 0;
        private static final int TYPE_MY_VALIDATOR              = 1;
        private static final int TYPE_HEADER_VALIDATOR          = 2;
        private static final int TYPE_VALIDATOR                 = 3;
        private static final int TYPE_PROMOTION                 = 4;
        private static final int TYPE_HEADER_WITHDRAW_ALL       = 5;



        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if(viewType == TYPE_HEADER_MY_VALIDATOR) {
                View v = getLayoutInflater().inflate(R.layout.item_reward_my_validator_header, viewGroup, false);
                return new RewardHeaderHolder(v);

            } else if(viewType == TYPE_MY_VALIDATOR) {
                View v = getLayoutInflater().inflate(R.layout.item_reward_my_validator, viewGroup, false);
                return  new RewardMyValidatorHolder(v);

            } else if(viewType == TYPE_HEADER_VALIDATOR) {
                View v = getLayoutInflater().inflate(R.layout.item_reward_validator_header, viewGroup, false);
                return  new RewardHeaderHolder(v);

            } else if(viewType == TYPE_VALIDATOR) {
                View v = getLayoutInflater().inflate(R.layout.item_reward_validator, viewGroup, false);
                return  new RewardValidatorHolder(v);

            } else if(viewType == TYPE_PROMOTION) {
                View v = getLayoutInflater().inflate(R.layout.item_reward_promotion, viewGroup, false);
                return  new RewardPromotionHolder(v);

            } else if(viewType == TYPE_HEADER_WITHDRAW_ALL) {
                View v = getLayoutInflater().inflate(R.layout.item_reward_withdraw_all, viewGroup, false);
                return  new RewardWithdrawHolder(v);

            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
//            WLog.w(" "+ position + " " + getItemViewType(position));
            if (getItemViewType(position) == TYPE_PROMOTION) {
                final RewardPromotionHolder holder = (RewardPromotionHolder)viewHolder;
                holder.itemBtnDelegateStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WLog.w("START DELEGATE");
                    }
                });

            } else if (getItemViewType(position) == TYPE_HEADER_WITHDRAW_ALL) {
                final RewardWithdrawHolder holder = (RewardWithdrawHolder)viewHolder;
                holder.itemTvAllRewards.setText(WDp.getDpAllAtomRewardAmount(getContext(), mRewards));
                holder.itemBtnWithdrawAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WLog.w("WITHDRAW ALL");
                        Intent claimReward = new Intent(getMainActivity(), ClaimRewardActivity.class);
                        claimReward.putExtra("isAll", true);
                        startActivity(claimReward);
                    }
                });

            } else if (getItemViewType(position) == TYPE_MY_VALIDATOR) {
                final RewardMyValidatorHolder holder = (RewardMyValidatorHolder)viewHolder;
                final Validator validator = mMyValidators.get(position - 1);
                holder.itemTvMoniker.setText(validator.description.moniker);
                if(!TextUtils.isEmpty(validator.description.details)) {
                    holder.itemTvDescription.setText(validator.description.details);
                    holder.itemTvDescription.setVisibility(View.VISIBLE);
                } else {
                    holder.itemTvDescription.setVisibility(View.GONE);
                }
                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WLog.w("SHOW Detail");
                        getBaseDao().setValidator(validator);
                        onStartValidatorDetail(validator);
                    }
                });
                holder.itemAvatar.setImageDrawable(getResources().getDrawable(R.drawable.validator_none_img));
                if(!TextUtils.isEmpty(validator.description.identity)) {
                    ApiClient.getKeybaseService(getMainActivity()).getUserInfo("pictures", validator.description.identity).enqueue(new Callback<ResKeyBaseUser>() {
                        @Override
                        public void onResponse(Call<ResKeyBaseUser> call, Response<ResKeyBaseUser> response) {
                            if(isAdded()) {
                                Picasso.with(getContext())
                                        .load(response.body().getUrl())
                                        .fit()
                                        .into(holder.itemAvatar);
                            }
                        }
                        @Override
                        public void onFailure(Call<ResKeyBaseUser> call, Throwable t) {}
                    });
                }


            } else if (getItemViewType(position) == TYPE_VALIDATOR) {
                final RewardValidatorHolder holder = (RewardValidatorHolder)viewHolder;
                final Validator validator;
                if(mMyValidators.size() == 0) {
                    validator = mElseValidators.get(position - 1);
                } else {
                    validator = mElseValidators.get(position - 2 - mMyValidators.size());
                }
                holder.itemTvMoniker.setText(validator.description.moniker);
                if(!TextUtils.isEmpty(validator.description.details)) {
                    holder.itemTvDescription.setText(validator.description.details);
                    holder.itemTvDescription.setVisibility(View.VISIBLE);
                } else {
                    holder.itemTvDescription.setVisibility(View.GONE);
                }
                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WLog.w("SHOW Detail");
                        getBaseDao().setValidator(validator);
                        onStartValidatorDetail(validator);
                    }
                });
                holder.itemAvatar.setImageDrawable(getResources().getDrawable(R.drawable.validator_none_img));
                if(!TextUtils.isEmpty(validator.description.identity)) {
                    ApiClient.getKeybaseService(getMainActivity()).getUserInfo("pictures", validator.description.identity).enqueue(new Callback<ResKeyBaseUser>() {
                        @Override
                        public void onResponse(Call<ResKeyBaseUser> call, Response<ResKeyBaseUser> response) {
                            if(isAdded()) {
                                Picasso.with(getContext())
                                        .load(response.body().getUrl())
                                        .fit()
                                        .into(holder.itemAvatar);
                            }
                        }
                        @Override
                        public void onFailure(Call<ResKeyBaseUser> call, Throwable t) {}
                    });
                }
            }

        }

        @Override
        public int getItemCount() {
            int result = mMyValidators.size() + mElseValidators.size();
            if(mMyValidators.size() > 0 ) result ++;
            if(mElseValidators.size() > 0 ) result ++;

            return result;
        }

        @Override
        public int getItemViewType(int position) {
            if (mMyValidators.size() > 0 && mElseValidators.size() > 0) {
                if(position == 0) {
                    return TYPE_HEADER_MY_VALIDATOR;

                } else if (position < mMyValidators.size() + 1) {
                    return TYPE_MY_VALIDATOR;

                } else if (position == mMyValidators.size() + 1) {
                    return TYPE_HEADER_VALIDATOR;
                } else {
                    return TYPE_VALIDATOR;
                }

            } else if (mMyValidators.size() == 0) {
                if(position == 0) {
                    return TYPE_PROMOTION ;
                } else if (position == 1) {
                    return TYPE_HEADER_VALIDATOR;
                } else  {
                    return TYPE_VALIDATOR;
                }
            } else if (mElseValidators.size() == 0) {
                if(position == 0) {
                    return TYPE_HEADER_MY_VALIDATOR;
                } else {
                    return TYPE_MY_VALIDATOR;
                }
            }
            return TYPE_VALIDATOR;
        }



        public class RewardHeaderHolder extends RecyclerView.ViewHolder {
            public RewardHeaderHolder(View v) {
                super(v);
            }
        }

        public class RewardPromotionHolder extends RecyclerView.ViewHolder {
            Button      itemBtnDelegateStart;

            public RewardPromotionHolder(@NonNull View itemView) {
                super(itemView);
                itemBtnDelegateStart    = itemView.findViewById(R.id.btn_start_delegate);
            }
        }

        public class RewardWithdrawHolder extends RecyclerView.ViewHolder {
            TextView    itemTvAllRewards;
            Button      itemBtnWithdrawAll;

            public RewardWithdrawHolder(@NonNull View itemView) {
                super(itemView);
                itemTvAllRewards        = itemView.findViewById(R.id.tx_all_rewards);
                itemBtnWithdrawAll      = itemView.findViewById(R.id.btn_withdraw_all);
            }
        }

        public class RewardValidatorHolder extends RecyclerView.ViewHolder {
            CardView    itemRoot;
            CircleImageView  itemAvatar;
            TextView    itemTvMoniker;
            TextView    itemTvDescription;

            public RewardValidatorHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot            = itemView.findViewById(R.id.card_validator);
                itemAvatar          = itemView.findViewById(R.id.avatar_validator);
                itemTvMoniker       = itemView.findViewById(R.id.moniker_validator);
                itemTvDescription   = itemView.findViewById(R.id.description_validator);
            }
        }

        public class RewardMyValidatorHolder extends RecyclerView.ViewHolder {
            CardView    itemRoot;
//            AvatarView  itemAvatar;
            CircleImageView itemAvatar;
            TextView    itemTvMoniker;
            TextView    itemTvDescription;
//            TextView    itemTvDelegateAmount;
//            TextView    itemTvRewardAtom;
//            TextView    itemTvRewardPhoton;

            public RewardMyValidatorHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot            = itemView.findViewById(R.id.card_validator);
                itemAvatar          = itemView.findViewById(R.id.avatar_validator);
                itemTvMoniker       = itemView.findViewById(R.id.moniker_validator);
                itemTvDescription   = itemView.findViewById(R.id.description_validator);
//                itemTvDelegateAmount = itemView.findViewById(R.id.delegate_amount_validator);
//                itemTvRewardAtom    = itemView.findViewById(R.id.reward_atom_validator);
//                itemTvRewardPhoton  = itemView.findViewById(R.id.reward_photon_validator);
            }
        }
    }
}
*/