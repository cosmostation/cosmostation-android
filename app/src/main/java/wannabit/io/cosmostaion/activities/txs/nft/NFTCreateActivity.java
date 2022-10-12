package wannabit.io.cosmostaion.activities.txs.nft;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_MINT_NFT;
import static wannabit.io.cosmostaion.base.BaseConstant.NFT_INFURA;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.activities.TxDetailgRPCActivity;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.StationNFTData;
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment;
import wannabit.io.cosmostaion.fragment.StepMemoFragment;
import wannabit.io.cosmostaion.fragment.txs.nft.NFTCreateStep0Fragment;
import wannabit.io.cosmostaion.fragment.txs.nft.NFTCreateStep3Fragment;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.MintNFTGrpcTask;

public class NFTCreateActivity extends BaseBroadCastActivity {

    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mIvStep;
    private TextView mTvStep;
    private ViewPager mViewPager;
    private NFTCreateAdapter mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        mToolbar = findViewById(R.id.tool_bar);
        mTitle = findViewById(R.id.toolbar_title);
        mIvStep = findViewById(R.id.send_step);
        mTvStep = findViewById(R.id.send_step_msg);
        mViewPager = findViewById(R.id.view_pager);
        mTitle.setText(getString(R.string.str_create_nfts));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mIvStep.setImageDrawable(ContextCompat.getDrawable(NFTCreateActivity.this, R.drawable.step_4_img_1));
        mTvStep.setText(getString(R.string.str_issue_nft_step_0));

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mTxType = CONST_PW_TX_MINT_NFT;

        mPageAdapter = new NFTCreateAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(NFTCreateActivity.this, R.drawable.step_4_img_1));
                    mTvStep.setText(getString(R.string.str_issue_nft_step_0));
                } else if (i == 1) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(NFTCreateActivity.this, R.drawable.step_4_img_2));
                    mTvStep.setText(getString(R.string.str_issue_nft_step_1));
                } else if (i == 2) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(NFTCreateActivity.this, R.drawable.step_4_img_3));
                    mTvStep.setText(getString(R.string.str_issue_nft_step_2));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 3) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(NFTCreateActivity.this, R.drawable.step_4_img_4));
                    mTvStep.setText(getString(R.string.str_issue_nft_step_3));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mViewPager.setCurrentItem(0);
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
        if (mViewPager.getCurrentItem() < mViewPager.getChildCount()) {
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

    public void onCreateNFT() {
        if (getBaseDao().isAutoPass()) {
            onBroadCastTx();
        } else {
            Intent intent = new Intent(NFTCreateActivity.this, PasswordCheckActivity.class);
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
        StationNFTData nftData = new StationNFTData(mAccount.address, mNftName, mNftDescription, mNftDenomId, NFT_INFURA + mNftHash);
        Gson gson = new Gson();
        String jsonData = gson.toJson(nftData);
        new MintNFTGrpcTask(getBaseApplication(), new TaskListener() {
            @Override
            public void onTaskResponse(TaskResult result) {
                if (result.isSuccess) {
                    Intent txIntent = new Intent(NFTCreateActivity.this, TxDetailgRPCActivity.class);
                    txIntent.putExtra("isGen", true);
                    txIntent.putExtra("isSuccess", result.isSuccess);
                    txIntent.putExtra("errorCode", result.errorCode);
                    txIntent.putExtra("errorMsg", result.errorMsg);
                    String hash = String.valueOf(result.resultData);
                    if (!TextUtils.isEmpty(hash)) txIntent.putExtra("txHash", hash);
                    startActivity(txIntent);
                }
            }
        }, mAccount, mBaseChain, mAccount.address, mNftDenomId, mNftDenomName, mNftHash.toLowerCase(), mNftName, NFT_INFURA + mNftHash, jsonData,
                    mTxMemo, mTxFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private class NFTCreateAdapter extends FragmentPagerAdapter {
        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public NFTCreateAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(NFTCreateStep0Fragment.newInstance());
            mFragments.add(StepMemoFragment.newInstance());
            mFragments.add(StepFeeSetFragment.newInstance());
            mFragments.add(NFTCreateStep3Fragment.newInstance());
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
