package wannabit.io.cosmostaion.activities.txs.common;

import static cosmos.tx.v1beta1.ServiceGrpc.newBlockingStub;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_DELEGATE;
import static wannabit.io.cosmostaion.utils.WUtil.integerToBytes;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.ledger.live.ble.app.BleCosmosHelper;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.SignatureDecodeException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

import cosmos.tx.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.activities.TxDetailgRPCActivity;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment;
import wannabit.io.cosmostaion.fragment.StepMemoFragment;
import wannabit.io.cosmostaion.fragment.txs.common.DelegateStep0Fragment;
import wannabit.io.cosmostaion.fragment.txs.common.DelegateStep3Fragment;
import wannabit.io.cosmostaion.model.StdSignMsg;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.DelegateGrpcTask;
import wannabit.io.cosmostaion.utils.LedgerManager;
import wannabit.io.cosmostaion.utils.WKey;

public class DelegateActivity extends BaseBroadCastActivity {

    private RelativeLayout mRootView;
    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mIvStep;
    private TextView mTvStep;
    private ViewPager mViewPager;
    private DelegatePageAdapter mPageAdapter;

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
        mTitle.setText(getString(R.string.str_delegate_c));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mIvStep.setImageDrawable(ContextCompat.getDrawable(DelegateActivity.this, R.drawable.step_4_img_1));
        mTvStep.setText(getString(R.string.str_delegate_step_1));

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mTxType = CONST_PW_TX_SIMPLE_DELEGATE;

        mValAddress = getIntent().getStringExtra("valOpAddress");

        mPageAdapter = new DelegatePageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(DelegateActivity.this, R.drawable.step_4_img_1));
                    mTvStep.setText(getString(R.string.str_delegate_step_1));
                } else if (i == 1) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(DelegateActivity.this, R.drawable.step_4_img_2));
                    mTvStep.setText(getString(R.string.str_tx_step_memo));
                } else if (i == 2) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(DelegateActivity.this, R.drawable.step_4_img_3));
                    mTvStep.setText(getString(R.string.str_tx_step_fee));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 3) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(DelegateActivity.this, R.drawable.step_4_img_4));
                    mTvStep.setText(getString(R.string.str_tx_step_confirm));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mViewPager.setCurrentItem(0);

        mRootView.setOnClickListener(v -> onHideKeyboard());
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
        if (mViewPager.getCurrentItem() < mPageAdapter.getCount() - 1) {
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

    public void onStartDelegate() {
        if (mAccount.isLedger()) {
            onDelegateByLedger();
        } else if (getBaseDao().isAutoPass()) {
            onBroadCastTx();
        } else {
            Intent intent = new Intent(this, PasswordCheckActivity.class);
            activityResultLauncher.launch(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    private void onDelegateByLedger() {
        new Thread(() -> {
            ArrayList<Serializable> authInfo = Signer.onParseAuthGrpc(WKey.onAuthResponse(mBaseChain, mAccount));
            ArrayList<Msg> msgs = new ArrayList<>();
            Msg delgateMsg = MsgGenerator.genDelegateMsg(mAccount.address, mValAddress, mAmount);
            msgs.add(delgateMsg);

            StdSignMsg tosign = MsgGenerator.genToSignMsg(getBaseDao().getChainIdGrpc(), "" + authInfo.get(1), "" + authInfo.get(2), msgs, mTxFee, mTxMemo);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
            String message = null;
            try {
                message = mapper.writeValueAsString(mapper.readValue(new Gson().toJson(tosign), TreeMap.class));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            String finalMessage = message;
            runOnUiThread(() -> LedgerManager.getInstance().connect(this, new LedgerManager.ConnectListener() {
                @Override
                public void error(@NonNull LedgerManager.ErrorType errorType) {
                    runOnUiThread(() -> CommonAlertDialog.showDoubleButton(DelegateActivity.this, "Ledger Error", errorType.name(), "Cancel", null, "Retry", view -> onStartDelegate()));
                }

                @Override
                public void connected() {
                    String path = mAccount.path;
                    if (path == null) {
                        path = "44'/118'/0'/0/0";
                    }
                    String finalPath = path;
                    BleCosmosHelper.Companion.getAddress(LedgerManager.Companion.getInstance().getBleManager(), mChainConfig.addressPrefix(), path, new BleCosmosHelper.GetAddressListener() {
                        @Override
                        public void success(@NonNull String s, @NonNull byte[] bytes) {
                            LedgerManager.getInstance().setCurrentPubKey(bytes);
                            if (!mAccount.address.equals(s)) {
                                //TODO not matched address with ledger
                                return;
                            }
                            BleCosmosHelper.Companion.sign(LedgerManager.Companion.getInstance().getBleManager(), finalPath, finalMessage, new BleCosmosHelper.SignListener() {
                                @Override
                                public void success(@NonNull byte[] bytes) {
                                    try {
                                        byte[] sigData = new byte[64];
                                        ECKey.ECDSASignature Signature = ECKey.ECDSASignature.decodeFromDER(bytes);
                                        System.arraycopy(integerToBytes(Signature.r, 32), 0, sigData, 0, 32);
                                        System.arraycopy(integerToBytes(Signature.s, 32), 0, sigData, 32, 32);
                                        new Thread(() -> {
                                            ServiceGrpc.ServiceBlockingStub txService = newBlockingStub(ChannelBuilder.getChain(mBaseChain));
                                            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcLedgerDelegateReq(WKey.onAuthResponse(mBaseChain, mAccount), mValAddress, mAmount, mTxFee, mTxMemo, LedgerManager.Companion.getInstance().getCurrentPubKey(), sigData);
                                            ServiceOuterClass.BroadcastTxResponse response = txService.broadcastTx(broadcastTxRequest);
                                            TaskResult mResult = new TaskResult();
                                            mResult.resultData = response.getTxResponse().getTxhash();
                                            if (response.getTxResponse().getCode() > 0) {
                                                mResult.errorCode = response.getTxResponse().getCode();
                                                mResult.errorMsg = response.getTxResponse().getRawLog();
                                                mResult.isSuccess = false;
                                            } else {
                                                mResult.isSuccess = true;
                                            }
                                            onIntentTx(mResult);
                                        }).start();

                                    } catch (SignatureDecodeException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void error(@NonNull String s, @NonNull String s1) {
                                }
                            });
                        }

                        @Override
                        public void error(@NonNull String s, @NonNull String s1) {

                        }
                    });
                }
            }));
        }).start();
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            onShowWaitDialog();
            onBroadCastTx();
        }
    });

    private void onBroadCastTx() {
        new DelegateGrpcTask(getBaseApplication(), result -> {
            onIntentTx(result);
        }, mBaseChain, mAccount, mValAddress, mAmount, mTxMemo, mTxFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void onIntentTx(TaskResult result) {
        Intent txIntent = new Intent(DelegateActivity.this, TxDetailgRPCActivity.class);;
        txIntent.putExtra("isGen", true);
        txIntent.putExtra("isSuccess", result.isSuccess);
        txIntent.putExtra("errorCode", result.errorCode);
        txIntent.putExtra("errorMsg", result.errorMsg);
        String hash = String.valueOf(result.resultData);
        if (!TextUtils.isEmpty(hash)) txIntent.putExtra("txHash", hash);
        startActivity(txIntent);
    }

    private class DelegatePageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public DelegatePageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(DelegateStep0Fragment.newInstance());
            mFragments.add(StepMemoFragment.newInstance());
            mFragments.add(StepFeeSetFragment.newInstance());
            mFragments.add(DelegateStep3Fragment.newInstance());
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
