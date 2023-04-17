package wannabit.io.cosmostaion.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;

import cosmos.auth.v1beta1.QueryGrpc;
import cosmos.auth.v1beta1.QueryOuterClass;
import cosmos.base.abci.v1beta1.Abci;
import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.distribution.v1beta1.Distribution;
import cosmos.staking.v1beta1.Staking;
import cosmos.tx.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.DelegateActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.FeeInfo;
import wannabit.io.cosmostaion.fragment.ValidatorAllFragment;
import wannabit.io.cosmostaion.fragment.ValidatorMyFragment;
import wannabit.io.cosmostaion.fragment.ValidatorOtherFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.FetchCallBack;
import wannabit.io.cosmostaion.utils.LedgerManager;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

public class ValidatorListActivity extends BaseActivity implements FetchCallBack {

    private final int EASY_MODE_CLAIM_REWARDS = 0;
    private final int EASY_MODE_COMPOUNDING = 1;

    private Toolbar mToolbar;
    private ViewPager mValidatorPager;
    private TabLayout mValidatorTapLayer;
    private ValidatorPageAdapter mPageAdapter;

    private BigDecimal feeGasAmount = new BigDecimal("500000");
    private Coin feeCoin;
    private Fee fee;

    private int mEasyMode = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validator_list);
        mToolbar = findViewById(R.id.tool_bar);
        mValidatorTapLayer = findViewById(R.id.validator_tab);
        mValidatorPager = findViewById(R.id.validator_view_pager);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);

        mPageAdapter = new ValidatorPageAdapter(getSupportFragmentManager());
        mValidatorPager.setAdapter(mPageAdapter);
        mValidatorTapLayer.setupWithViewPager(mValidatorPager);
        mValidatorTapLayer.setTabRippleColor(null);

        createTab(mChainConfig, R.string.str_my_validators, 0);
        createTab(mChainConfig, R.string.str_top_100_validators, 1);
        createTab(mChainConfig, R.string.str_other_validators, 2);

        mValidatorPager.setOffscreenPageLimit(3);
        mValidatorPager.setCurrentItem(0, false);

        mValidatorPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }

            @Override
            public void onPageSelected(int i) {
                mPageAdapter.mFragments.get(i).onRefreshTab();
            }
        });
    }

    private void createTab(ChainConfig chainConfig, int stringResourceId, int index) {
        View tab = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText = tab.findViewById(R.id.tabItemText);
        tabItemText.setText(stringResourceId);
        tabItemText.setTextColor(ContextCompat.getColorStateList(this, chainConfig.chainTabColor()));
        mValidatorTapLayer.getTabAt(index).setCustomView(tab);

        mValidatorTapLayer.setTabIconTint(ContextCompat.getColorStateList(this, chainConfig.chainColor()));
        mValidatorTapLayer.setSelectedTabIndicatorColor(ContextCompat.getColor(this, chainConfig.chainColor()));
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

    public void onStartValidatorDetail(Validator validator) {
        Intent intent = new Intent(ValidatorListActivity.this, ValidatorActivity.class);
        intent.putExtra("validator", validator);
        startActivity(intent);
    }

    public void onStartValidatorDetailV1(String opAddress) {
        Intent intent = new Intent(ValidatorListActivity.this, ValidatorActivity.class);
        intent.putExtra("valOpAddress", opAddress);
        startActivity(intent);
    }

    public void onStartDelegate() {
        if (!mAccount.hasPrivateKey && !mAccount.isLedger()) {
            onInsertKeyDialog();
            return;
        }
        if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
            Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
            return;
        }

        String cosmostation = "";
        BigDecimal delegatableAmount = getBaseDao().getDelegatable(mBaseChain, mChainConfig.mainDenom());
        if (BigDecimal.ZERO.compareTo(delegatableAmount) >= 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_to_delegate, Toast.LENGTH_SHORT).show();
            return;
        }
        for (Staking.Validator validator : getBaseDao().mGRpcAllValidators) {
            if ("Cosmostation".equalsIgnoreCase(validator.getDescription().getMoniker())) {
                cosmostation = validator.getOperatorAddress();
            }
        }
        if (!cosmostation.isEmpty()) {
            Intent toDelegate = new Intent(ValidatorListActivity.this, DelegateActivity.class);
            toDelegate.putExtra("valOpAddress", cosmostation);
            startActivity(toDelegate);
        }
    }

    public void onCheckEasyClaim() {
        if (!mAccount.hasPrivateKey && !mAccount.isLedger()) {
            onInsertKeyDialog();
            return;
        }

        if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
            Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<Distribution.DelegationDelegatorReward> toClaimRewards = getClaimableReward();
        if (toClaimRewards.size() <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
            return;
        }
        mEasyMode = EASY_MODE_CLAIM_REWARDS;
        onPasswordCheck();
    }

    public void onStartEasyClaim(int selectFee) {
        onShowWaitDialog();
        ArrayList<String> toClaimValAddr = targetClaimValidatorAddresses();
        FeeInfo.FeeData feeData = calculateFee(selectFee);
        new Thread(() -> {
            try {
                ServiceOuterClass.SimulateResponse simulateClaimResponse = ValidatorListActivity.this.simulateClaim(toClaimValAddr);
                ValidatorListActivity.this.runOnUiThread(ValidatorListActivity.this::onHideWaitDialog);
                if (simulateClaimResponse == null) {
                    return;
                }
                ServiceOuterClass.BroadcastTxResponse executeClaimResponse = ValidatorListActivity.this.executeClaim(simulateClaimResponse, feeData, toClaimValAddr);
                if (executeClaimResponse == null) {
                    return;
                }
                ValidatorListActivity.this.onProcessResponse(executeClaimResponse);
            } catch (Exception e) {

            }
        }).start();
    }

    private void onStartEasyClaimByLedger(int selectFee) {
        runOnUiThread(() -> LedgerManager.getInstance().signAndBroadcast(ValidatorListActivity.this, mAccount, new LedgerManager.LedgerSignListener() {
            @NonNull
            @Override
            public String getMessage() {
                ArrayList<String> toClaimValAddr = targetClaimValidatorAddresses();
                ArrayList<Msg> easyClaimMsgs = MsgGenerator.genWithdrawDeleMsgs(mAccount.address, toClaimValAddr);
                FeeInfo.FeeData feeData = calculateFee(selectFee);

                ServiceOuterClass.SimulateResponse simulateClaimResponse = ValidatorListActivity.this.simulateClaim(toClaimValAddr);
                Abci.GasInfo gasInfo = simulateClaimResponse.getGasInfo();
                if (mBaseChain.equals(BaseChain.IXO_MAIN)) {
                    feeGasAmount = new BigDecimal(gasInfo.getGasUsed()).multiply(new BigDecimal("1.5")).setScale(0, RoundingMode.UP);
                } else {
                    feeGasAmount = new BigDecimal(gasInfo.getGasUsed()).multiply(new BigDecimal("3")).setScale(0, RoundingMode.UP);
                }
                if (!mBaseChain.equals(BaseChain.SIF_MAIN) && !mBaseChain.equals(BaseChain.CHIHUAHUA_MAIN)) {
                    BigDecimal amount = feeData.gasRate.multiply(feeGasAmount).setScale(0, RoundingMode.UP);
                    feeCoin = new Coin(feeData.denom, amount.toPlainString());
                }
                fee = new Fee(feeGasAmount.toPlainString(), Lists.newArrayList(feeCoin));
                return WKey.onGetLedgerMessage(getBaseDao(), mChainConfig, mAccount, easyClaimMsgs, fee, "");
            }

            @NonNull
            @Override
            public ServiceOuterClass.BroadcastTxRequest makeBroadcastTxRequest(@NonNull byte[] currentPubKey) {
                ArrayList<String> toClaimValAddr = targetClaimValidatorAddresses();
                return Signer.getGrpcLedgerClaimRewardsReq(WKey.onAuthResponse(mBaseChain, mAccount), toClaimValAddr, fee, "", LedgerManager.Companion.getInstance().getCurrentPubKey(), WKey.getLedgerSigData(currentPubKey));
            }

            @Override
            public void processResponse(@NonNull TaskResult mResult, @NonNull ServiceOuterClass.BroadcastTxResponse response) {
                onProcessResponse(response);
            }
        }));
    }

    public void onCheckEasyCompounding() {
        if (!mAccount.hasPrivateKey && !mAccount.isLedger()) {
            onInsertKeyDialog();
            return;
        }

        if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
            Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<Distribution.DelegationDelegatorReward> toClaimRewards = getClaimableReward();
        if (toClaimRewards.size() <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
            return;
        }

        cosmos.distribution.v1beta1.QueryGrpc.QueryBlockingStub mStub = cosmos.distribution.v1beta1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
        cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorWithdrawAddressRequest request = cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorWithdrawAddressRequest.newBuilder().setDelegatorAddress(mAccount.address).build();
        cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorWithdrawAddressResponse response = mStub.delegatorWithdrawAddress(request);
        if (response.getWithdrawAddress() == null || !response.getWithdrawAddress().equals(mAccount.address)) {
            Toast.makeText(getBaseContext(), R.string.error_reward_address_changed_msg, Toast.LENGTH_SHORT).show();
            return;
        }

        mEasyMode = EASY_MODE_COMPOUNDING;
        onPasswordCheck();
    }

    public void onStartEasyCompounding(int selectFee) {
        onShowWaitDialog();
        FeeInfo.FeeData feeData = calculateFee(selectFee);

        new Thread(() -> {
            try {
                if (getClaimableReward() != null && mBaseChain != null) {
                    ServiceOuterClass.SimulateResponse simulateCompoundingResponse = ValidatorListActivity.this.simulateCompounding(getClaimableReward(), mBaseChain);
                    ValidatorListActivity.this.runOnUiThread(ValidatorListActivity.this::onHideWaitDialog);
                    if (simulateCompoundingResponse == null) {
                        return;
                    }

                    ServiceOuterClass.BroadcastTxResponse executeCompoundingResponse = ValidatorListActivity.this.executeCompounding(simulateCompoundingResponse, feeData, getClaimableReward(), mBaseChain);
                    if (executeCompoundingResponse == null) {
                        return;
                    }
                    ValidatorListActivity.this.onProcessResponse(executeCompoundingResponse);
                }
            } catch (Exception e) {

            }
        }).start();
    }

    private void onStartEasyCompoundingByLedger(int selectFee) {
        runOnUiThread(() -> LedgerManager.getInstance().signAndBroadcast(ValidatorListActivity.this, mAccount, new LedgerManager.LedgerSignListener() {
            @NonNull
            @Override
            public String getMessage() {
                ArrayList<Msg> easyCompoundingMsgs = MsgGenerator.genCompoundingMsgs(mAccount.address, getClaimableReward(), mChainConfig);
                FeeInfo.FeeData feeData = calculateFee(selectFee);

                ServiceOuterClass.SimulateResponse simulateClaimResponse = ValidatorListActivity.this.simulateCompounding(getClaimableReward(), mChainConfig.baseChain());
                Abci.GasInfo gasInfo = simulateClaimResponse.getGasInfo();
                if (mBaseChain.equals(BaseChain.IXO_MAIN)) {
                    feeGasAmount = new BigDecimal(gasInfo.getGasUsed()).multiply(new BigDecimal("1.5")).setScale(0, RoundingMode.UP);
                } else {
                    feeGasAmount = new BigDecimal(gasInfo.getGasUsed()).multiply(new BigDecimal("3")).setScale(0, RoundingMode.UP);
                }
                if (!mBaseChain.equals(BaseChain.SIF_MAIN) && !mBaseChain.equals(BaseChain.CHIHUAHUA_MAIN)) {
                    BigDecimal amount = feeData.gasRate.multiply(feeGasAmount).setScale(0, RoundingMode.UP);
                    feeCoin = new Coin(feeData.denom, amount.toPlainString());
                }
                fee = new Fee(feeGasAmount.toPlainString(), Lists.newArrayList(feeCoin));
                return WKey.onGetLedgerMessage(getBaseDao(), mChainConfig, mAccount, easyCompoundingMsgs, fee, "");
            }

            @NonNull
            @Override
            public ServiceOuterClass.BroadcastTxRequest makeBroadcastTxRequest(@NonNull byte[] currentPubKey) {
                return Signer.getGrpcLedgerCompoundingReq(WKey.onAuthResponse(mBaseChain, mAccount), getClaimableReward(), mChainConfig.baseChain(), fee, "", LedgerManager.Companion.getInstance().getCurrentPubKey(), WKey.getLedgerSigData(currentPubKey));
            }

            @Override
            public void processResponse(@NonNull TaskResult mResult, @NonNull ServiceOuterClass.BroadcastTxResponse response) {
                onProcessResponse(response);
            }
        }));
    }

    @NonNull
    private FeeInfo.FeeData calculateFee(int selectFee) {
        ArrayList<FeeInfo> feeInfo = WDp.getFeeInfos(this, getBaseDao());
        FeeInfo.FeeData feeData = feeInfo.get(selectFee).feeDatas.get(0);

        if (mBaseChain.equals(BaseChain.SIF_MAIN)) {
            feeCoin = new Coin(feeData.denom, "100000000000000000");
        } else if (mBaseChain.equals(BaseChain.CHIHUAHUA_MAIN)) {
            if (selectFee == 0) feeCoin = new Coin(feeData.denom, "1000000");
            else if (selectFee == 1) feeCoin = new Coin(feeData.denom, "5000000");
            else feeCoin = new Coin(feeData.denom, "10000000");
        } else {
            BigDecimal amount = feeData.gasRate.multiply(feeGasAmount).setScale(0, RoundingMode.UP);
            feeCoin = new Coin(feeData.denom, amount.toPlainString());
        }
        fee = new Fee(feeGasAmount.toPlainString(), Lists.newArrayList(feeCoin));
        return feeData;
    }

    private ServiceOuterClass.SimulateResponse simulateClaim(ArrayList<String> toClaimValaddr) {
        ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
        ServiceOuterClass.SimulateRequest simulateTxRequest = Signer.getGrpcClaimRewardsSimulateReq(getAuthResponse(), toClaimValaddr, fee, "", mAccount.customPath, mBaseChain);
        return txService.simulate(simulateTxRequest);
    }

    private ServiceOuterClass.BroadcastTxResponse executeClaim(ServiceOuterClass.SimulateResponse response, FeeInfo.FeeData feeData, ArrayList<String> toClaimValaddr) {
        Abci.GasInfo gasInfo = response.getGasInfo();
        feeGasAmount = new BigDecimal(gasInfo.getGasUsed()).multiply(new BigDecimal("1.1")).setScale(0, RoundingMode.UP);
        if (!mBaseChain.equals(BaseChain.SIF_MAIN) && !mBaseChain.equals(BaseChain.CHIHUAHUA_MAIN)) {
            BigDecimal amount = feeData.gasRate.multiply(feeGasAmount).setScale(0, RoundingMode.UP);
            feeCoin = new Coin(feeData.denom, amount.toPlainString());
        }

        fee = new Fee(feeGasAmount.toPlainString(), Lists.newArrayList(feeCoin));
        ServiceGrpc.ServiceBlockingStub broadcastTx = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
        ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcClaimRewardsReq(getAuthResponse(), toClaimValaddr, fee, "", getEcKey(), getBaseDao().getChainIdGrpc(), mAccount.customPath, mBaseChain);
        return broadcastTx.broadcastTx(broadcastTxRequest);
    }

    private ServiceOuterClass.SimulateResponse simulateCompounding(ArrayList<Distribution.DelegationDelegatorReward> rewards, BaseChain baseChain) {
        ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
        ServiceOuterClass.SimulateRequest simulateTxRequest = Signer.getGrpcCompoundingSimulateReq(getAuthResponse(), rewards, fee, "", mAccount.customPath, baseChain);
        return txService.simulate(simulateTxRequest);
    }

    private ServiceOuterClass.BroadcastTxResponse executeCompounding(ServiceOuterClass.SimulateResponse response, FeeInfo.FeeData feeData, ArrayList<Distribution.DelegationDelegatorReward> rewards, BaseChain baseChain) {
        Abci.GasInfo gasInfo = response.getGasInfo();
        feeGasAmount = new BigDecimal(gasInfo.getGasUsed()).multiply(new BigDecimal("1.1")).setScale(0, RoundingMode.UP);
        if (!mBaseChain.equals(BaseChain.SIF_MAIN)) {
            BigDecimal amount = feeData.gasRate.multiply(feeGasAmount).setScale(0, RoundingMode.UP);
            feeCoin = new Coin(feeData.denom, amount.toPlainString());
        }

        fee = new Fee(feeGasAmount.toPlainString(), Lists.newArrayList(feeCoin));
        ServiceGrpc.ServiceBlockingStub broadcastTx = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
        ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcCompoundingReq(getAuthResponse(), rewards, baseChain, fee, "", getEcKey(), getBaseDao().getChainIdGrpc(), mAccount.customPath);
        return broadcastTx.broadcastTx(broadcastTxRequest);
    }

    private void onProcessResponse(ServiceOuterClass.BroadcastTxResponse broadcastTxResponse) {
        runOnUiThread(() -> {
            if (StringUtils.isEmpty(broadcastTxResponse.getTxResponse().getTxhash())) {
                return;
            }

            String txHash = broadcastTxResponse.getTxResponse().getTxhash();
            if (broadcastTxResponse.getTxResponse().getCode() > 0) {
                startTxDetailGRPCActivity(txHash, broadcastTxResponse.getTxResponse().getCode(), broadcastTxResponse.getTxResponse().getRawLog(), false);
            } else {
                startTxDetailGRPCActivity(txHash, 0, "", true);
            }

        });
    }

    private void startTxDetailGRPCActivity(String txHash, int errorCode, String errorMsg, boolean isSuccess) {
        Intent txIntent = new Intent(ValidatorListActivity.this, TxDetailgRPCActivity.class);
        txIntent.putExtra("isGen", true);
        txIntent.putExtra("isSuccess", isSuccess);
        txIntent.putExtra("errorCode", errorCode);
        txIntent.putExtra("errorMsg", errorMsg);
        txIntent.putExtra("txHash", txHash);
        startActivity(txIntent);
    }

    public ArrayList<Distribution.DelegationDelegatorReward> getClaimableReward() {
        ArrayList<Distribution.DelegationDelegatorReward> result = new ArrayList<>();
        if (getBaseDao().mGrpcRewards != null) {
            WUtil.onSortRewardAmount(getBaseDao().mGrpcRewards, mChainConfig.mainDenom());

            CoinOuterClass.DecCoin stakeCoin = null;
            for (Distribution.DelegationDelegatorReward reward : getBaseDao().mGrpcRewards) {
                for (CoinOuterClass.DecCoin coin : reward.getRewardList()) {
                    if (coin.getDenom().equalsIgnoreCase(mChainConfig.mainDenom())) {
                        stakeCoin = coin;
                    }
                }
                if (stakeCoin != null) {
                    BigDecimal rewardAmount = new BigDecimal(stakeCoin.getAmount()).movePointLeft(18).movePointLeft(WDp.getDenomDecimal(getBaseDao(), mChainConfig, stakeCoin.getDenom()));
                    if (new BigDecimal("0.01").compareTo(rewardAmount) < 0) {
                        result.add(reward);
                    }
                }
            }
        }
        if (result.size() > 10) {
            result = new ArrayList(result.subList(0, 10));
        }
        return result;
    }

    @NonNull
    private ArrayList<String> targetClaimValidatorAddresses() {
        ArrayList<String> toClaimValAddr = new ArrayList<>();
        for (Distribution.DelegationDelegatorReward reward : getClaimableReward()) {
            toClaimValAddr.add(reward.getValidatorAddress());
        }
        return toClaimValAddr;
    }

    private void onPasswordCheck() {
        if (getBaseDao().isAutoPass() || mAccount.isLedger()) {
            onShowFeeDialog();
        } else {
            Intent intent = new Intent(ValidatorListActivity.this, PasswordCheckActivity.class);
            passwordCheckResultLauncher.launch(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    private void onShowFeeDialog() {
        ArrayList<FeeInfo> feeInfo = WDp.getFeeInfos(this, getBaseDao());
        if (feeInfo.size() > 1) {
            new AlertDialog.Builder(this, R.style.DialogTheme).setItems(feeInfo.stream().map(item -> item.title).toArray(String[]::new), (DialogInterface dialogInterface, int i) -> {
                if (mEasyMode == EASY_MODE_CLAIM_REWARDS) {
                    if (mAccount.isLedger()) onStartEasyClaimByLedger(i);
                    else onStartEasyClaim(i);
                } else {
                    if (mAccount.isLedger()) onStartEasyCompoundingByLedger(i);
                    else onStartEasyCompounding(i);
                }
            }).setTitle(R.string.str_tx_step_fee).setNegativeButton(R.string.str_cancel, null).create().show();

        } else {
            if (mEasyMode == EASY_MODE_CLAIM_REWARDS) {
                if (mAccount.isLedger()) onStartEasyClaimByLedger(0);
                else onStartEasyClaim(0);
            } else {
                if (mAccount.isLedger()) onStartEasyCompoundingByLedger(0);
                else onStartEasyCompounding(0);
            }
        }
    }

    public ECKey getEcKey() {
        if (mAccount.fromMnemonic) {
            String entropy = CryptoHelper.doDecryptData(getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
            DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(mAccount, entropy);
            return ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
        } else {
            String privateKey = CryptoHelper.doDecryptData(getString(R.string.key_private) + mAccount.uuid, mAccount.resource, mAccount.spec);
            return ECKey.fromPrivate(new BigInteger(privateKey, 16));
        }
    }

    public QueryOuterClass.QueryAccountResponse getAuthResponse() {
        QueryGrpc.QueryBlockingStub authStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
        QueryOuterClass.QueryAccountRequest request = QueryOuterClass.QueryAccountRequest.newBuilder().setAddress(mAccount.address).build();
        return authStub.account(request);
    }

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private final ActivityResultLauncher<Intent> passwordCheckResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            mHandler.postDelayed(() -> onShowFeeDialog(), 300);
        }
    });

    public void onFetchAllData() {
        onFetchAccountInfo(this);
    }

    @Override
    public void fetchFinished() {
        if (!isFinishing()) {
            onHideWaitDialog();
            mPageAdapter.mCurrentFragment.onRefreshTab();
        }

    }

    @Override
    public void fetchBusy() {
        if (!isFinishing()) {
            onHideWaitDialog();
            mPageAdapter.mCurrentFragment.onBusyFetch();
        }

    }

    private class ValidatorPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public ValidatorPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(ValidatorMyFragment.newInstance());
            mFragments.add(ValidatorAllFragment.newInstance());
            mFragments.add(ValidatorOtherFragment.newInstance());
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
