package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;

import agency.tango.android.avatarview.IImageLoader;
import agency.tango.android.avatarview.loader.PicassoLoader;
import agency.tango.android.avatarview.views.AvatarView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dao.Reward;
import wannabit.io.cosmostaion.dao.UnBondingState;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKeyBaseUser;
import wannabit.io.cosmostaion.network.res.ResLcdBondings;
import wannabit.io.cosmostaion.task.SingleBondingStateTask;
import wannabit.io.cosmostaion.task.SingleRewardTask;
import wannabit.io.cosmostaion.task.SingleSelfBondingStateTask;
import wannabit.io.cosmostaion.task.SingleUnBondingStateTask;
import wannabit.io.cosmostaion.task.SingleValidatorInfoTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

public class ValidatorActivity extends BaseActivity implements TaskListener {

    private Toolbar                     mToolbar;
    private TextView                    mToolbarNickName;
    private TextView                    mToolbarAddress;
    private SwipeRefreshLayout          mSwipeRefreshLayout;
    private RecyclerView                mRecyclerView;
    private LinearLayoutManager         mLinearLayoutManager;

    private ValidatorAdapter            mValidatorAdapter;

    private Account                     mAccount;
    private Validator                   mValidator;
    private BondingState                mBondingState;
    private UnBondingState              mUnBondingState;
    private Reward                      mReward;
    //TODO this is temp
    private ArrayList<String>           mTx = new ArrayList<>();

    private String                      mValidatorPicture;
    private String                      mSelfBondingRate;

    private int                         mTaskCount;
    private boolean                     mExpended = true;

    private IImageLoader                mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validator);
        mToolbar                    = findViewById(R.id.tool_bar);
        mToolbarNickName            = findViewById(R.id.toolbar_nickName);
        mToolbarAddress             = findViewById(R.id.toolbar_Address);
        mSwipeRefreshLayout         = findViewById(R.id.layer_refresher);
        mRecyclerView               = findViewById(R.id.recycler);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mImageLoader = new PicassoLoader();

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                onFetchAccounts();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        WLog.w("Address : " + getIntent().getStringExtra("valAddr"));

    }

    @Override
    protected void onResume() {
        super.onResume();

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        if(mAccount == null) {
            WLog.w("NO Account ERROR");
        }

        if(getBaseDao().getValidator() == null) {
            WLog.w("NO Validator ERROR");
        }

        mValidator      = getBaseDao().getValidator();
        mBondingState   = getBaseDao().onSelectBondingState(mAccount.id, mValidator.operator_address);
        mUnBondingState = getBaseDao().onSelectUnbondingState(mAccount.id, mValidator.operator_address);
//        mReward         = getBaseDao().getValidatorDetail().mReward;

        if(TextUtils.isEmpty(mAccount.nickName)) {
            mToolbarNickName.setText("Wallet " + mAccount.id);
        } else {
            mToolbarNickName.setText(mAccount.nickName);
        }
        mToolbarAddress.setText(mAccount.address);


        WLog.w("Validator : " + mValidator.description.moniker);
        WLog.w("Validator : " + mValidator.operator_address);
        mValidatorAdapter = new ValidatorAdapter(mImageLoader);
        mRecyclerView.setAdapter(mValidatorAdapter);

        onInitFetch();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onUpdateView() {

    }



    private void onInitFetch() {
        if(mTaskCount > 0) return;
        mTaskCount = 4;
        new SingleValidatorInfoTask(getBaseApplication(), this, mValidator.operator_address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new SingleBondingStateTask(getBaseApplication(), this, mAccount, mValidator.operator_address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new SingleSelfBondingStateTask(getBaseApplication(), this, WKey.convertDpOpAddressToDpAddress(mValidator.operator_address), mValidator.operator_address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new SingleUnBondingStateTask(getBaseApplication(), this, mAccount, mValidator.operator_address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        if(mBondingState != null) {
            mTaskCount = mTaskCount + 1;
            new SingleRewardTask(getBaseApplication(), this, mAccount, mValidator.operator_address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
//        WLog.w("TaskResult : " + result.taskType);
        mTaskCount--;
        if (result.taskType == BaseConstant.TASK_FETCH_SINGLE_VALIDATOR) {
            mValidator = (Validator)result.resultData;
            if(mValidator == null) {
                WLog.w("Show network error!!!");
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_SINGLE_BONDING) {
            mBondingState   = getBaseDao().onSelectBondingState(mAccount.id, mValidator.operator_address);
            if(mBondingState != null && mValidator != null) {
                mTaskCount = mTaskCount + 1;
                new SingleRewardTask(getBaseApplication(), this, mAccount, mValidator.operator_address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_SINGLE_UNBONDING) {
            mUnBondingState = getBaseDao().onSelectUnbondingState(mAccount.id, mValidator.operator_address);

        } else if (result.taskType == BaseConstant.TASK_FETCH_SINGLE_SELF_BONDING) {
            ResLcdBondings temp = (ResLcdBondings)result.resultData;
            if(temp != null)
                mSelfBondingRate = WDp.getSelfBondRate(mValidator.tokens, temp.shares);


        } else if (result.taskType == BaseConstant.TASK_FETCH_SINGLE_REWARD) {
            mReward         = (Reward)result.resultData;
        }

        if(mTaskCount == 0) {
            WLog.w("mTaskFinished");
            mValidatorAdapter.notifyDataSetChanged();
        }
    }


    private class ValidatorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_VALIDATOR             = 0;
        private static final int TYPE_MY_VALIDATOR          = 1;
        private static final int TYPE_ACTION                = 2;
        private static final int TYPE_HISTORY_HEADER        = 3;
        private static final int TYPE_HISTORY               = 4;
        private static final int TYPE_HISTORY_EMPTY         = 5;
        IImageLoader    mImageLoader;

        public ValidatorAdapter(IImageLoader mImageLoader) {
            this.mImageLoader = mImageLoader;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if(viewType == TYPE_VALIDATOR) {
                return new ValidatorHolder(getLayoutInflater().inflate(R.layout.item_validator_detail, viewGroup, false));
            } else if(viewType == TYPE_MY_VALIDATOR) {
                return new MyValidatorHolder(getLayoutInflater().inflate(R.layout.item_validator_my_detail, viewGroup, false));
            } else if(viewType == TYPE_ACTION) {
                return new MyActionHolder(getLayoutInflater().inflate(R.layout.item_validator_my_action, viewGroup, false));
            } else if(viewType == TYPE_HISTORY_HEADER) {
                return new HistoryHeaderHolder(getLayoutInflater().inflate(R.layout.item_validator_history_header, viewGroup, false));
            } else if(viewType == TYPE_HISTORY) {
                return new HistoryHolder(getLayoutInflater().inflate(R.layout.item_validator_history, viewGroup, false));
            } else if(viewType == TYPE_HISTORY_EMPTY) {
                return new HistoryEmptyHolder(getLayoutInflater().inflate(R.layout.item_validator_history_empty, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_VALIDATOR) {
                final ValidatorHolder holder = (ValidatorHolder)viewHolder;
                holder.itemTvMoniker.setText(mValidator.description.moniker);
                holder.itemTvAddress.setText(mValidator.operator_address);
                if(!TextUtils.isEmpty(mValidator.description.website)) holder.itemTvWebsite.setText(mValidator.description.website);
                else holder.itemTvWebsite.setVisibility(View.GONE);
                if(!TextUtils.isEmpty(mValidator.description.details)) holder.itemTvDescription.setText(mValidator.description.details);
                else holder.itemTvDescription.setVisibility(View.GONE);

                mImageLoader.loadImage(holder.itemAvatar, "error", mValidator.description.moniker);
                if(!TextUtils.isEmpty(mValidator.description.identity)) {
                    ApiClient.getKeybaseService(getBaseContext()).getUserInfo("pictures", mValidator.description.identity).enqueue(new Callback<ResKeyBaseUser>() {
                        @Override
                        public void onResponse(Call<ResKeyBaseUser> call, Response<ResKeyBaseUser> response) {
                            mImageLoader.loadImage(holder.itemAvatar, response.body().getUrl(), mValidator.description.moniker);
                        }
                        @Override
                        public void onFailure(Call<ResKeyBaseUser> call, Throwable t) { }
                    });
                }

                holder.itemTvTotalBondAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(mValidator.tokens), 6));
                holder.itemTvCommissionRate.setText(WDp.getCommissionRate(mValidator.commission.rate));
                if(!TextUtils.isEmpty(mSelfBondingRate)) holder.itemTvSelfBondRate.setText(mSelfBondingRate); else holder.itemTvSelfBondRate.setText("");
                holder.itemBtnDelegate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WLog.w("Start Delegate");
                        getBaseDao().setValidator(mValidator);
                        Intent toDelegate = new Intent(ValidatorActivity.this, DelegateActivity.class);
                        startActivity(toDelegate);
                    }
                });


            } else if (getItemViewType(position) == TYPE_MY_VALIDATOR) {
                final MyValidatorHolder holder = (MyValidatorHolder)viewHolder;
                holder.itemTvMoniker.setText(mValidator.description.moniker);
                holder.itemTvAddress.setText(mValidator.operator_address);
                if(!TextUtils.isEmpty(mValidator.description.website)) holder.itemTvWebsite.setText(mValidator.description.website);
                else holder.itemTvWebsite.setVisibility(View.GONE);
                if(!TextUtils.isEmpty(mValidator.description.details)) holder.itemTvDescription.setText(mValidator.description.details);
                else holder.itemTvDescription.setVisibility(View.GONE);

                mImageLoader.loadImage(holder.itemAvatar, "error", mValidator.description.moniker);
                if(!TextUtils.isEmpty(mValidator.description.identity)) {
                    ApiClient.getKeybaseService(getBaseContext()).getUserInfo("pictures", mValidator.description.identity).enqueue(new Callback<ResKeyBaseUser>() {
                        @Override
                        public void onResponse(Call<ResKeyBaseUser> call, Response<ResKeyBaseUser> response) {
                            mImageLoader.loadImage(holder.itemAvatar, response.body().getUrl(), mValidator.description.moniker);
                        }
                        @Override
                        public void onFailure(Call<ResKeyBaseUser> call, Throwable t) { }
                    });
                }

                holder.itemHideShow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WLog.w("TOGGLE");
                    }
                });

                holder.itemTvTotalBondAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(mValidator.tokens), 6));
                holder.itemTvCommissionRate.setText(WDp.getCommissionRate(mValidator.commission.rate));
                if(!TextUtils.isEmpty(mSelfBondingRate)) holder.itemTvSelfBondRate.setText(mSelfBondingRate); else holder.itemTvSelfBondRate.setText("");

            } else if (getItemViewType(position) == TYPE_ACTION) {
                final MyActionHolder holder = (MyActionHolder)viewHolder;
                holder.itemTvDelegatedAmount.setText(WDp.getDpAmount(getBaseContext(), mBondingState.shares, 6));
                if(mUnBondingState != null && mUnBondingState.balance != null) {
                    holder.itemTvUnbondingAmount.setText(WDp.getDpAmount(getBaseContext(), mUnBondingState.balance, 6));
                } else {
                    holder.itemTvUnbondingAmount.setText(WDp.getDpAmount(getBaseContext(), BigDecimal.ZERO, 6));
                    holder.itemTvUnbondingTime.setVisibility(View.INVISIBLE);
                }
                if(mReward != null) {
                    holder.itemTvAtomReward.setText(WDp.getDpAmount(getBaseContext(), mReward.getAtomAmount(), 6));
                    holder.itemTvPhotonReward.setText(WDp.getDpAmount(getBaseContext(), mReward.getPhotonAmount(), 6));
                } else {
                    holder.itemTvAtomReward.setText(WDp.getDpAmount(getBaseContext(), BigDecimal.ZERO, 6));
                    holder.itemTvPhotonReward.setText(WDp.getDpAmount(getBaseContext(), BigDecimal.ZERO, 6));
                }
                holder.itemBtnDelegate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WLog.w("Start Delegate");
                        getBaseDao().setValidator(mValidator);
                        Intent toDelegate = new Intent(ValidatorActivity.this, DelegateActivity.class);
                        startActivity(toDelegate);
                    }
                });
                holder.itemBtnUndelegate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WLog.w("Start Undelegate");
                        getBaseDao().setValidator(mValidator);
                        Intent unDelegate = new Intent(ValidatorActivity.this, UndelegateActivity.class);
                        startActivity(unDelegate);

                    }
                });
                holder.itemBtnReward.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WLog.w("Start Reward");

                    }
                });

            } else if (getItemViewType(position) == TYPE_HISTORY_HEADER) {
                final HistoryHeaderHolder holder = (HistoryHeaderHolder)viewHolder;

            } else if (getItemViewType(position) == TYPE_HISTORY) {
                final HistoryHolder holder = (HistoryHolder)viewHolder;

            } else if (getItemViewType(position) == TYPE_HISTORY_EMPTY) {
                final HistoryEmptyHolder holder = (HistoryEmptyHolder)viewHolder;

            }
        }

        @Override
        public int getItemViewType(int position) {
            if(mBondingState == null) {
                if(position == 0) {
                    return TYPE_VALIDATOR;
                } else if (position == 1) {
                    return TYPE_HISTORY_HEADER;
                }

            } else {
                if(position == 0) {
                    return TYPE_MY_VALIDATOR;
                } else if (position == 1) {
                    return TYPE_ACTION;
                } else if (position == 2) {
                    return TYPE_HISTORY_HEADER;
                }
            }
            if(mTx.size() > 0) {
                return TYPE_HISTORY;
            }
            return TYPE_HISTORY_EMPTY;
        }


        @Override
        public int getItemCount() {
            if(mBondingState == null) {
                if(mTx.size() > 0) {
                    return mTx.size() + 2;
                } else {
                    return 3;
                }

            } else {
                if(mTx.size() > 0) {
                    return mTx.size() + 3;
                } else {
                    return 4;
                }
            }
        }



        public class ValidatorHolder extends RecyclerView.ViewHolder {
            AvatarView  itemAvatar;
            TextView    itemTvMoniker;
            TextView    itemTvAddress;
            TextView    itemTvWebsite;
            TextView    itemTvDescription;
            TextView    itemTvTotalBondAmount;
            TextView    itemTvSelfBondRate;
            TextView    itemTvCommissionRate;
            Button      itemBtnDelegate;

            public ValidatorHolder(View v) {
                super(v);
                itemAvatar              = itemView.findViewById(R.id.validator_avatar);
                itemTvMoniker           = itemView.findViewById(R.id.validator_moniker);
                itemTvAddress           = itemView.findViewById(R.id.validator_address);
                itemTvWebsite           = itemView.findViewById(R.id.validator_site);
                itemTvDescription       = itemView.findViewById(R.id.validator_description);
                itemTvTotalBondAmount   = itemView.findViewById(R.id.validator_total_bonded);
                itemTvSelfBondRate      = itemView.findViewById(R.id.validator_self_bond_rate);
                itemTvCommissionRate    = itemView.findViewById(R.id.validator_commission);
                itemBtnDelegate         = itemView.findViewById(R.id.validator_btn_delegate);
            }
        }

        public class MyValidatorHolder extends RecyclerView.ViewHolder {
            AvatarView  itemAvatar;
            ImageView   itemHideShow;
            TextView    itemTvMoniker;
            TextView    itemTvAddress;
            TextView    itemTvWebsite;
            TextView    itemTvDescription;
            TextView    itemTvTotalBondAmount;
            TextView    itemTvSelfBondRate;
            TextView    itemTvCommissionRate;

            public MyValidatorHolder(View v) {
                super(v);
                itemAvatar              = itemView.findViewById(R.id.validator_avatar);
                itemHideShow            = itemView.findViewById(R.id.validator_hide_show);
                itemTvMoniker           = itemView.findViewById(R.id.validator_moniker);
                itemTvAddress           = itemView.findViewById(R.id.validator_address);
                itemTvWebsite           = itemView.findViewById(R.id.validator_site);
                itemTvDescription       = itemView.findViewById(R.id.validator_description);
                itemTvTotalBondAmount   = itemView.findViewById(R.id.validator_total_bonded);
                itemTvSelfBondRate      = itemView.findViewById(R.id.validator_self_bond_rate);
                itemTvCommissionRate    = itemView.findViewById(R.id.validator_commission);
            }
        }

        public class MyActionHolder extends RecyclerView.ViewHolder {
            TextView    itemTvDelegatedAmount, itemTvUnbondingAmount, itemTvUnbondingTime, itemTvAtomReward, itemTvPhotonReward;
            Button      itemBtnDelegate, itemBtnUndelegate, itemBtnReward;

            public MyActionHolder(View v) {
                super(v);
                itemTvDelegatedAmount   = itemView.findViewById(R.id.validator_delegated);
                itemTvUnbondingAmount   = itemView.findViewById(R.id.validator_unbonding);
                itemTvUnbondingTime     = itemView.findViewById(R.id.validator_unbonding_time);
                itemTvAtomReward        = itemView.findViewById(R.id.validator_atom_reward);
                itemTvPhotonReward      = itemView.findViewById(R.id.validator_photon_reward);
                itemBtnDelegate         = itemView.findViewById(R.id.validator_btn_delegate);
                itemBtnUndelegate       = itemView.findViewById(R.id.validator_btn_undelegate);
                itemBtnReward           = itemView.findViewById(R.id.validator_btn_claim_reward);
            }
        }

        public class HistoryHeaderHolder extends RecyclerView.ViewHolder {

            public HistoryHeaderHolder(View v) {
                super(v);
            }
        }


        public class HistoryHolder extends RecyclerView.ViewHolder {

            public HistoryHolder(View v) {
                super(v);
            }
        }

        public class HistoryEmptyHolder extends RecyclerView.ViewHolder {

            public HistoryEmptyHolder(View v) {
                super(v);
            }
        }

    }

}
