package wannabit.io.cosmostaion.activities.txs.common;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_VOTE;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.activities.TxDetailgRPCActivity;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment;
import wannabit.io.cosmostaion.fragment.StepMemoFragment;
import wannabit.io.cosmostaion.fragment.txs.common.VoteStep0Fragment;
import wannabit.io.cosmostaion.fragment.txs.common.VoteStep3Fragment;
import wannabit.io.cosmostaion.network.res.ResProposal;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.VoteGrpcTask;

public class VoteActivity extends BaseBroadCastActivity {

    private RelativeLayout mRootView;
    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mIvStep;
    private TextView mTvStep;
    private ViewPager2 mViewPager;
    private VotePageAdapter mPageAdapter;

    public List<ResProposal> mProposal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        mRootView = findViewById(R.id.root_view);
        mToolbar = findViewById(R.id.tool_bar);
        mTitle = findViewById(R.id.toolbar_title);
        mIvStep = findViewById(R.id.send_step);
        mTvStep = findViewById(R.id.send_step_msg);
        mViewPager = findViewById(R.id.view_pager);
        mTitle.setText(getString(R.string.str_vote_c));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mIvStep.setImageDrawable(ContextCompat.getDrawable(VoteActivity.this, R.drawable.step_4_img_1));
        mTvStep.setText(getString(R.string.str_vote_step_0));

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mTxType = CONST_PW_TX_VOTE;

        mProposal = new Gson().fromJson(getIntent().getStringExtra("proposal"), new TypeToken<List<ResProposal>>() {
        }.getType());

        mPageAdapter = new VotePageAdapter(getSupportFragmentManager(), getLifecycle());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPageAdapter);
        mViewPager.setUserInputEnabled(false);
        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int i) {
                super.onPageSelected(i);
                if (i == 0) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(VoteActivity.this, R.drawable.step_4_img_1));
                    mTvStep.setText(getString(R.string.str_vote_step_0));
                } else if (i == 1) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(VoteActivity.this, R.drawable.step_4_img_2));
                    mTvStep.setText(getString(R.string.str_vote_step_1));
                } else if (i == 2) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(VoteActivity.this, R.drawable.step_4_img_3));
                    mTvStep.setText(getString(R.string.str_vote_step_2));
                    mPageAdapter.mFragments.get(i).onRefreshTab();
                } else if (i == 3) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(VoteActivity.this, R.drawable.step_4_img_4));
                    mTvStep.setText(getString(R.string.str_vote_step_3));
                    mPageAdapter.mFragments.get(i).onRefreshTab();
                }
            }
        });
        mViewPager.setCurrentItem(0);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAccount == null) finish();
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

    @Override
    public void onBackPressed() {
        onHideKeyboard();
        if (mViewPager.getCurrentItem() > 0) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        } else {
            super.onBackPressed();
        }
    }

    public void onNextStep() {
        if (mViewPager.getCurrentItem() < mPageAdapter.getItemCount()) {
            onHideKeyboard();
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
        }
    }

    public void onBeforeStep() {
        if (mViewPager.getCurrentItem() > 0) {
            onHideKeyboard();
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        } else {
            onBackPressed();
        }
    }

    public void onStartVote() {
        if (getBaseDao().isAutoPass()) {
            onBroadCastTx();
        } else {
            Intent intent = new Intent(VoteActivity.this, PasswordCheckActivity.class);
            activityResultLauncher.launch(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            onShowWaitDialog();
            onBroadCastTx();
        }
    });

    private void onBroadCastTx() {
        new VoteGrpcTask(getBaseApplication(), result -> {
            Intent txIntent = new Intent(VoteActivity.this, TxDetailgRPCActivity.class);
            txIntent.putExtra("isGen", true);
            txIntent.putExtra("isSuccess", result.isSuccess);
            txIntent.putExtra("errorCode", result.errorCode);
            txIntent.putExtra("errorMsg", result.errorMsg);
            String hash = String.valueOf(result.resultData);
            if (!TextUtils.isEmpty(hash)) txIntent.putExtra("txHash", hash);
            startActivity(txIntent);
        }, mBaseChain, mAccount, mSelectedOpinion, mTxMemo, mTxFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private static class VotePageAdapter extends FragmentStateAdapter {
        private final ArrayList<BaseFragment> mFragments = new ArrayList<>();

        public VotePageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
            mFragments.clear();
            mFragments.add(VoteStep0Fragment.newInstance());
            mFragments.add(StepMemoFragment.newInstance());
            mFragments.add(StepFeeSetFragment.newInstance());
            mFragments.add(VoteStep3Fragment.newInstance());
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getItemCount() {
            return mFragments.size();
        }
    }
}
