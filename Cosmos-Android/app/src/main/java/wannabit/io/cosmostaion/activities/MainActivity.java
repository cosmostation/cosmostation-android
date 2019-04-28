package wannabit.io.cosmostaion.activities;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dao.Reward;
import wannabit.io.cosmostaion.dao.UnBondingState;
import wannabit.io.cosmostaion.dialog.Dialog_AddAccount;
import wannabit.io.cosmostaion.dialog.Dialog_ShareType;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.dialog.TopSheetBehavior;
import wannabit.io.cosmostaion.fragment.MainHistoryFragment;
import wannabit.io.cosmostaion.fragment.MainRewardFragment;
import wannabit.io.cosmostaion.fragment.MainSendFragment;
import wannabit.io.cosmostaion.fragment.MainSettingFragment;
import wannabit.io.cosmostaion.fragment.MainVoteFragment;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResAtomTic;
import wannabit.io.cosmostaion.task.FetchTask.AccountInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.AllValidatorInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.BondingStateTask;
import wannabit.io.cosmostaion.task.FetchTask.UnBondingStateTask;
import wannabit.io.cosmostaion.task.SingleFetchTask.SingleRewardTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.FadePageTransformer;
import wannabit.io.cosmostaion.widget.StopViewPager;
import wannabit.io.cosmostaion.widget.TintableImageView;

public class MainActivity extends BaseActivity implements TaskListener {

    private Toolbar                     mToolbar;
    private ImageView                   mToolbarChainImg;
    private TextView                    mToolbarTitle;
    private TextView                    mToolbarChainName;

    private StopViewPager               mContentsPager;
    private TabLayout                   mTabLayer;
    private MainViewPageAdapter         mPageAdapter;
    private FrameLayout                 mDimLayer;
    public FloatingActionButton         mFloatBtn;

    public Account                     mAccount;
    private ArrayList<Account>         mAccounts = new ArrayList<>();
    public ArrayList<Validator>        mAllValidators = new ArrayList<>();
    public ArrayList<Validator>        mMyValidators = new ArrayList<>();
    public ArrayList<Validator>        mElseValidators = new ArrayList<>();
    public ArrayList<Balance>          mBalances = new ArrayList<>();
    public ArrayList<BondingState>     mBondings = new ArrayList<>();
    public ArrayList<UnBondingState>   mUnbondings = new ArrayList<>();
    public ArrayList<Reward>           mRewards = new ArrayList<>();

    private int                         mTaskCount;
    private TopSheetBehavior            mTopSheetBehavior;

    private RecyclerView                mRecyclerView;
    private AccountAdapter              mAccountAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar                    = findViewById(R.id.tool_bar);
        mToolbarTitle               = findViewById(R.id.toolbar_title);
        mToolbarChainImg            = findViewById(R.id.toolbar_net_image);
        mToolbarChainName           = findViewById(R.id.toolbar_net_name);
        mContentsPager              = findViewById(R.id.view_pager);
        mTabLayer                   = findViewById(R.id.bottom_tab);
        mDimLayer                   = findViewById(R.id.dim_layer);


        mRecyclerView               = findViewById(R.id.account_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAccountAdapter = new AccountAdapter();
        mRecyclerView.setAdapter(mAccountAdapter);

        mDimLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTopSheetBehavior.getState() != TopSheetBehavior.STATE_COLLAPSED)
                    mTopSheetBehavior.setState(TopSheetBehavior.STATE_COLLAPSED);
                mDimLayer.setVisibility(View.GONE);
            }
        });

        mFloatBtn                   = findViewById(R.id.btn_floating);
        mFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartSendActivity();
            }
        });

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mPageAdapter = new MainViewPageAdapter(getSupportFragmentManager());
        mContentsPager.setPageTransformer(false, new FadePageTransformer());
        mContentsPager.setOffscreenPageLimit(3);
        mContentsPager.setAdapter(mPageAdapter);
        mTabLayer.setupWithViewPager(mContentsPager);
        mTabLayer.setTabRippleColor(null);

        View tab0 = LayoutInflater.from(this).inflate(R.layout.view_tab_item, null);
        TintableImageView tabItemIcon0  = tab0.findViewById(R.id.tabItemIcon);
        TextView tabItemText0  = tab0.findViewById(R.id.tabItemText);
        tabItemIcon0.setImageResource(R.drawable.send_ic);
        tabItemText0.setText(R.string.str_main_wallet);
        mTabLayer.getTabAt(0).setCustomView(tab0);

        View tab1 = LayoutInflater.from(this).inflate(R.layout.view_tab_item, null);
        TintableImageView   tabItemIcon1  = tab1.findViewById(R.id.tabItemIcon);
        TextView            tabItemText1  = tab1.findViewById(R.id.tabItemText);
        tabItemIcon1.setImageResource(R.drawable.reward_ic);
        tabItemText1.setText(R.string.str_main_reward);
        mTabLayer.getTabAt(1).setCustomView(tab1);

        View tab2 = LayoutInflater.from(this).inflate(R.layout.view_tab_item, null);
        TintableImageView   tabItemIcon2  = tab2.findViewById(R.id.tabItemIcon);
        TextView            tabItemText2  = tab2.findViewById(R.id.tabItemText);
        tabItemIcon2.setImageResource(R.drawable.ts_ic);
        tabItemText2.setText(R.string.str_main_history);
        mTabLayer.getTabAt(2).setCustomView(tab2);

        View tab3 = LayoutInflater.from(this).inflate(R.layout.view_tab_item, null);
        TintableImageView   tabItemIcon3  = tab3.findViewById(R.id.tabItemIcon);
        TextView            tabItemText3  = tab3.findViewById(R.id.tabItemText);
        tabItemIcon3.setImageResource(R.drawable.vote_ic);
        tabItemText3.setText(R.string.str_main_vote);
        mTabLayer.getTabAt(3).setCustomView(tab3);

        View tab4 = LayoutInflater.from(this).inflate(R.layout.view_tab_item, null);
        TintableImageView   tabItemIcon4  = tab4.findViewById(R.id.tabItemIcon);
        TextView            tabItemText4  = tab4.findViewById(R.id.tabItemText);
        tabItemIcon4.setImageResource(R.drawable.setting_ic);
        tabItemText4.setText(R.string.str_main_set);
        mTabLayer.getTabAt(4).setCustomView(tab4);

        mContentsPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }

            @Override
            public void onPageScrollStateChanged(int i) { }

            @Override
            public void onPageSelected(int position) {
                if(mPageAdapter != null && mPageAdapter.mCurrentFragment != null) {
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                    if(position != 0) mFloatBtn.hide();
                    else if (!mFloatBtn.isShown()) mFloatBtn.show();
                }
            }
        });

        mContentsPager.setCurrentItem(0, false);

        View sheet = findViewById(R.id.top_sheet);
        mTopSheetBehavior = TopSheetBehavior.from(sheet);
        mTopSheetBehavior.setState(TopSheetBehavior.STATE_HIDDEN);
        mTopSheetBehavior.setTopSheetCallback(new TopSheetBehavior.TopSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState == TopSheetBehavior.STATE_COLLAPSED) {
                    mDimLayer.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset, Boolean isOpening) { }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAccounts = getBaseDao().onSelectAccounts();
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        if(mAccount == null) {
            return;
        }
        if (mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.cosmos_wh_main));
            mToolbarChainName.setText(getString(R.string.str_cosmos_hub));
            mToolbarChainName.setVisibility(View.VISIBLE);

        } else if (mAccount.baseChain.equals(BaseChain.IRIS_MAIN.getChain())) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.irisnet));
            mToolbarChainName.setVisibility(View.INVISIBLE);

        }

        onFetchAccountInfo();
        mBalances = getBaseDao().onSelectBalance(mAccount.id);
        mBondings = getBaseDao().onSelectBondingStates(mAccount.id);
        mUnbondings = getBaseDao().onSelectUnbondingStates(mAccount.id);
        mAccountAdapter.notifyDataSetChanged();
        onUpdateTitle();

    }


    private void onUpdateTitle() {
        if(TextUtils.isEmpty(mAccount.nickName)) mToolbarTitle.setText(getString(R.string.str_my_wallet) + mAccount.id);
        else mToolbarTitle.setText(mAccount.nickName);
    }

    public void onStartSendActivity() {
        if(mAccount == null) return;
        if(!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        ArrayList<Balance> balances = getBaseDao().onSelectBalance(mAccount.id);
        boolean hasbalance = false;
        for (Balance balance:balances) {
            if(BaseConstant.IS_TEST) {
                if(balance.symbol.equals(BaseConstant.COSMOS_MUON) && ((balance.balance.compareTo(BigDecimal.ZERO)) > 0)) {
                    hasbalance  = true;
                }
            } else {
                if(balance.symbol.equals(BaseConstant.COSMOS_ATOM) && ((balance.balance.compareTo(new BigDecimal("500"))) >= 0)) {
                    hasbalance  = true;
                }
            }
        }
        if(!hasbalance){
            Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
            return;
        }

        startActivity(new Intent(MainActivity.this, SendActivity.class));
    }


    public void onStartValidatorDetail(Validator validator) {
        Intent intent = new Intent(MainActivity.this, ValidatorActivity.class);
        intent.putExtra("valAddr", validator.operator_address);
        startActivity(intent);
    }

    public void onStartRewardAll() {
        if(!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }
        if(mRewards == null) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<Validator> myValidators = new ArrayList<>();
        ArrayList<Validator> toClaimValidators = new ArrayList<>();
        for(Validator validator:mAllValidators) {
            for(BondingState bond:mBondings) {
                if(bond.validatorAddress.equals(validator.operator_address)) {
                    myValidators.add(validator);
                    break;
                }
            }
        }

        if(myValidators.size() < 17) {
            toClaimValidators = myValidators;
        } else {
            onSortingByReward(myValidators);
            toClaimValidators =  new ArrayList<>(myValidators.subList(0,16));
        }

        ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.gas_multi_reward)));
        BigDecimal mGasMinFee = new BigDecimal(getString(R.string.gas_constant)).multiply(new BigDecimal(rewardGasFees.get(toClaimValidators.size() - 1))).setScale(0);

        ArrayList<Balance> balances = getBaseDao().onSelectBalance(mAccount.id);
        boolean hasbalance = false;
        for (Balance balance:balances) {
            if(BaseConstant.IS_TEST) {
                if(balance.symbol.equals(BaseConstant.COSMOS_MUON) && ((balance.balance.compareTo(BigDecimal.ZERO)) > 0)) {
                    hasbalance  = true;
                }
            } else {
                if(balance.symbol.equals(BaseConstant.COSMOS_ATOM) && ((balance.balance.compareTo(mGasMinFee)) >= 0)) {
                    hasbalance  = true;
                }
            }
        }
        if(!hasbalance){
            Toast.makeText(getBaseContext(), R.string.error_not_enough_reward_all, Toast.LENGTH_SHORT).show();
            return;
        }

        BigDecimal rewardSum = BigDecimal.ZERO;
        for (Reward reward:mRewards) {
            rewardSum = rewardSum.add(new BigDecimal(reward.amount.get(0).amount));
        }
        if (rewardSum.compareTo(mGasMinFee) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_small_reward, Toast.LENGTH_SHORT).show();
            return;
        }


        if(myValidators.size() > 16) {
            Toast.makeText(getBaseContext(), R.string.str_multi_reward_max_16, Toast.LENGTH_SHORT).show();
        }

        Intent claimReward = new Intent(MainActivity.this, ClaimRewardActivity.class);
        claimReward.putExtra("opAddresses", toClaimValidators);
        startActivity(claimReward);
    }

    @Override
    public void onBackPressed() {
        if(mTopSheetBehavior.getState() != TopSheetBehavior.STATE_COLLAPSED) {
            mTopSheetBehavior.setState(TopSheetBehavior.STATE_COLLAPSED);
            return;
        } else {
            moveTaskToBack(true);
        }

    }


    @Override
    public void onShareType() {
        super.onShareType();
        Dialog_ShareType add = Dialog_ShareType.newInstance(null);
        add.setCancelable(true);
        getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
    }


    @Override
    public void onShare(boolean isText) {
        if(isText) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, mAccount.address);
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "send"));

        } else {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            try {
                final Bitmap mBitmap = WUtil.toBitmap(qrCodeWriter.encode(mAccount.address, BarcodeFormat.QR_CODE, 480, 480));
                new TedPermission(this)
                        .setPermissionListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted() {
                                try {
                                    ContentValues values = new ContentValues();
                                    values.put(MediaStore.Images.Media.TITLE, mAccount.address);
                                    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                                    Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                                    OutputStream outstream = getContentResolver().openOutputStream(uri);
                                    mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
                                    outstream.close();

                                    Intent shareIntent = new Intent();
                                    shareIntent.setAction(Intent.ACTION_SEND);
                                    shareIntent.putExtra(Intent.EXTRA_TEXT, mAccount.address);
                                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                                    shareIntent.setType("image/jpeg");
                                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    startActivity(Intent.createChooser(shareIntent, "send"));

                                } catch (Exception e) {
                                    if(BaseConstant.IS_SHOWLOG) e.printStackTrace();
                                }
                            }

                            @Override
                            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                                Toast.makeText(getBaseContext(), R.string.error_permission, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .setRationaleMessage(getString(R.string.str_permission_qr))
                        .check();

            } catch (WriterException e) {
                if(BaseConstant.IS_SHOWLOG) e.printStackTrace();
            }
        }

    }

    private void onFetchCurrentPage() {
        if(!isFinishing()) {
            mPageAdapter.mCurrentFragment.onRefreshTab();
        }

    }

    public void onShowTopAccountsView() {
        mDimLayer.setVisibility(View.VISIBLE);
        mTopSheetBehavior.setState(TopSheetBehavior.STATE_EXPANDED);
    }

    private void onHideTopAccountsView() {
        if(mTopSheetBehavior.getState() != TopSheetBehavior.STATE_COLLAPSED)
            mTopSheetBehavior.setState(TopSheetBehavior.STATE_COLLAPSED);
        mDimLayer.setVisibility(View.GONE);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if(isFinishing()) return;


        if (result.taskType == BaseConstant.TASK_FETCH_ACCOUNT) {
            mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
            mBalances = getBaseDao().onSelectBalance(mAccount.id);

        } else if (result.taskType == BaseConstant.TASK_FETCH_ALL_VALIDATOR) {
            ArrayList<Validator> temp = (ArrayList<Validator>)result.resultData;
            if(temp != null) {
                mAllValidators = temp;
            }
            if(!result.isSuccess) { Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show(); }

        } else if(result.taskType == BaseConstant.TASK_FETCH_BONDING_STATE) {
            mBondings = getBaseDao().onSelectBondingStates(mAccount.id);
            mTaskCount = mTaskCount + mBondings.size();
            mRewards.clear();
            for(BondingState bonding:mBondings) {
                new SingleRewardTask(getBaseApplication(), this, mAccount, bonding.validatorAddress).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_UNBONDING_STATE) {
            mUnbondings = getBaseDao().onSelectUnbondingStates(mAccount.id);

        } else if (result.taskType == BaseConstant.TASK_FETCH_SINGLE_REWARD) {
            Reward reward = (Reward)result.resultData;
            if(reward != null)
                onUpdateReward(reward);
        }
        if(mTaskCount == 0) {
            mMyValidators.clear();
            mElseValidators.clear();
            for(Validator all:mAllValidators) {
                boolean already = false;
                for(BondingState bond:mBondings) {
                    if(bond.validatorAddress.equals(all.operator_address)) {
                        already = true;
                        break;
                    }
                }
                for(UnBondingState unbond:mUnbondings) {
                    if(unbond.validatorAddress.equals(all.operator_address) && !already) {
                        already = true;
                        break;
                    }
                }

                if(already) mMyValidators.add(all);
                else  mElseValidators.add(all);
            }
            onFetchCurrentPage();
        }
    }


    private class MainViewPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public MainViewPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(MainSendFragment.newInstance(null));
            mFragments.add(MainRewardFragment.newInstance(null));
            mFragments.add(MainHistoryFragment.newInstance(null));
            mFragments.add(MainVoteFragment.newInstance(null));
            mFragments.add(MainSettingFragment.newInstance(null));
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


    private class AccountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_ACCOUNT       = 0;
        private static final int TYPE_ADD           = 1;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if(viewType == TYPE_ACCOUNT) {
                return  new AccountHolder(getLayoutInflater().inflate(R.layout.item_account, viewGroup, false));
            } else if (viewType == TYPE_ADD) {
                return  new AccountAddHolder(getLayoutInflater().inflate(R.layout.item_account_add, viewGroup, false));
            } else {
                return null;
            }

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_ACCOUNT) {
                final AccountHolder holder = (AccountHolder)viewHolder;
                final Account account = mAccounts.get(position);
                if(account.id == mAccount.id) {
                    holder.card_account.setBackground(getResources().getDrawable(R.drawable.box_accout_selected));

                } else {
                    holder.card_account.setBackground(getResources().getDrawable(R.drawable.box_accout_unselected));
                }

                if (account.hasPrivateKey) holder.img_account.setImageDrawable(getResources().getDrawable(R.drawable.key_on));
                else holder.img_account.setImageDrawable(getResources().getDrawable(R.drawable.key_off));

                if(TextUtils.isEmpty(account.nickName)) holder.img_name.setText(getString(R.string.str_my_wallet) + account.id);
                else holder.img_name.setText(account.nickName);

                holder.img_address.setText(account.address);
                holder.card_account.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(account.id == mAccount.id) {
                            onHideTopAccountsView();
                            return;
                        } else {
                            onHideTopAccountsView();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    getBaseDao().setLastUser(account.id);
                                    onResume();
                                }
                            },250);

                        }
                    }
                });

                if (account.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
                    holder.img_chain.setImageDrawable(getResources().getDrawable(R.drawable.cosmos_wh_main));
                    holder.tv_chain.setText(getString(R.string.str_cosmos_hub));
                    holder.tv_chain.setVisibility(View.VISIBLE);

                } else if (account.baseChain.equals(BaseChain.IRIS_MAIN.getChain())) {
                    holder.img_chain.setImageDrawable(getResources().getDrawable(R.drawable.irisnet));
                    holder.tv_chain.setVisibility(View.INVISIBLE);

                }

            } else if (getItemViewType(position) == TYPE_ADD) {
                final AccountAddHolder holder = (AccountAddHolder)viewHolder;
                holder.btn_account_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onHideTopAccountsView();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Dialog_AddAccount add = Dialog_AddAccount.newInstance(null);
                                add.setCancelable(true);
                                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();

                            }
                        },250);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if(mAccounts.size() >= 5) {
                return 5;
            } else {
                return mAccounts.size() + 1;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if(mAccounts.size() >= 5) {
                return TYPE_ACCOUNT;
            } else {
                if (position < mAccounts.size()) {
                    return TYPE_ACCOUNT;
                } else {
                    return TYPE_ADD;
                }
            }
        }

        public class AccountHolder extends RecyclerView.ViewHolder {
            FrameLayout card_account;
            ImageView img_account, img_chain;
            TextView img_name, img_address, tv_chain;
            public AccountHolder(View v) {
                super(v);
                card_account        = itemView.findViewById(R.id.card_account);
                img_chain  = itemView.findViewById(R.id.btn_account_chain_img);
                img_account         = itemView.findViewById(R.id.img_account);
                img_name            = itemView.findViewById(R.id.img_name);
                img_address         = itemView.findViewById(R.id.img_address);
                tv_chain            = itemView.findViewById(R.id.tv_chain);
            }
        }

        public class AccountAddHolder extends RecyclerView.ViewHolder {
            Button btn_account_add;
            public AccountAddHolder(@NonNull View itemView) {
                super(itemView);
                btn_account_add    = itemView.findViewById(R.id.btn_account_add);
            }
        }
    }


    public boolean onFetchAccountInfo() {
        if(mTaskCount > 0) return false;
        mTaskCount = 4;
        ArrayList<Account> accounts = new ArrayList<Account>();
        accounts.add(mAccount);
        new AllValidatorInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new AccountInfoTask(getBaseApplication(), this, accounts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new BondingStateTask(getBaseApplication(), this, accounts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new UnBondingStateTask(getBaseApplication(), this, accounts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        onAtomTic();
        return true;
    }


    private void onUpdateReward(Reward reward) {
        if(mRewards == null) mRewards = new ArrayList<>();
        if(mRewards.size() == 0) {
            mRewards.add(reward);
        } else {
            int match = -1;
            for(int i = 0; i < mRewards.size(); i++) {
                if(mRewards.get(i).validatorAddress.equals(reward.validatorAddress)) {
                    match = i; break;
                }
            }
            if(match > 0) {
                mRewards.remove(match);
            }
            mRewards.add(reward);
        }
    }


    private void onAtomTic() {
        ApiClient.getCMCClient(getBaseContext()).getAtomTic(3794,"USD").enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(isFinishing()) return;
                try {
                    if(response.isSuccessful()) {
                        ResAtomTic mResAtomTic = new Gson().fromJson(response.body(), ResAtomTic.class);
                        getBaseDao().setLastAtomTic(mResAtomTic.getData().getQuotesMap().get("USD").getPrice());
                        getBaseDao().setLastAtomUpDown(mResAtomTic.getData().getQuotesMap().get("USD").getPercent_change_24h());
                        onFetchCurrentPage();
                    }
                }catch (Exception e) {}

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public void onSortingByReward(final ArrayList<Validator> validators) {
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                BigDecimal rewardO1 = WDp.getValidatorReward(mRewards, o1.operator_address);
                BigDecimal rewardO2 = WDp.getValidatorReward(mRewards, o2.operator_address);
                return rewardO2.compareTo(rewardO1);
            }
        });
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if (o1.jailed && !o2.jailed) return 1;
                else if (!o1.jailed && o2.jailed) return -1;
                else return 0;
            }
        });
    }
}
