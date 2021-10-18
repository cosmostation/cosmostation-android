package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import android.os.AsyncTask;

import com.binance.dex.api.client.BinanceDexApiClientFactory;
import com.binance.dex.api.client.BinanceDexApiRestClient;
import com.binance.dex.api.client.BinanceDexEnvironment;
import com.binance.dex.api.client.Wallet;
import com.binance.dex.api.client.domain.TransactionMetadata;
import com.binance.dex.api.client.domain.broadcast.TransactionOption;

import org.bitcoinj.crypto.DeterministicKey;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.NodeInfo;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResBnbAccountInfo;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResLcdKavaAccountInfo;
import wannabit.io.cosmostaion.network.res.ResNodeInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.FetchTask.NodeInfoTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;


import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GEN_TX_HTLC_CLAIM;

public class HtlcClaimTask extends CommonTask {

    private Account         mReceiveAccount;
    private BaseChain       mReceiveChain;
    private Fee             mClaimFee;
    private String          mExpectedSwapId;
    private String          mRandomNumber;

    private String          mReceiveChainId;

    public HtlcClaimTask(BaseApplication app, TaskListener listener, Account recipient, BaseChain receiveChain, Fee claimFee, String expectedSwapId, String randomNumber) {
        super(app, listener);
        this.mReceiveAccount = recipient;
        this.mReceiveChain = receiveChain;
        this.mClaimFee = claimFee;
        this.mExpectedSwapId = expectedSwapId;
        this.mRandomNumber = randomNumber;
        this.mResult.taskType = TASK_GEN_TX_HTLC_CLAIM;
    }


    @Override
    protected TaskResult doInBackground(String... strings) {

        try {
            if (mReceiveChain.equals(BaseChain.BNB_MAIN)) {
                Response<ResBnbAccountInfo> response = ApiClient.getBnbChain(mApp).getAccountInfo(mReceiveAccount.address).execute();
                if(!response.isSuccessful()) {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                    return mResult;
                }
                mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromBnbLcd(mReceiveAccount.id, response.body()));
                mApp.getBaseDao().onUpdateBalances(mReceiveAccount.id, WUtil.getBalancesFromBnbLcd(mReceiveAccount.id, response.body()));
                mReceiveAccount = mApp.getBaseDao().onSelectAccount(""+mReceiveAccount.id);

                String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mReceiveAccount.uuid, mReceiveAccount.resource, mReceiveAccount.spec);
                DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(BaseChain.getChain(mReceiveAccount.baseChain), entropy, Integer.parseInt(mReceiveAccount.path), mReceiveAccount.newBip44);

                Wallet wallet = new Wallet(deterministicKey.getPrivateKeyAsHex(), BinanceDexEnvironment.PROD);
                wallet.setAccountNumber(mReceiveAccount.accountNumber);
                wallet.setSequence(Long.valueOf(mReceiveAccount.sequenceNumber));

                mExpectedSwapId = mExpectedSwapId.toLowerCase();
                mRandomNumber = mRandomNumber.toLowerCase();
                BinanceDexApiRestClient client = BinanceDexApiClientFactory.newInstance().newRestClient(BinanceDexEnvironment.PROD.getBaseUrl());
                TransactionOption options = new TransactionOption(mApp.getString(R.string.str_claim_swap_memo_c)  , 82, null);
                List<TransactionMetadata> resp = client.claimHtlt(mExpectedSwapId, WUtil.HexStringToByteArray(mRandomNumber), wallet, options, true);
                if (resp.get(0).isOk()) {
                    if (BuildConfig.DEBUG) WLog.w("BNB_MAIN Claim suceess txhash " + resp.get(0).getHash());
                    mResult.resultData = resp.get(0).getHash();
                    mResult.isSuccess = true;

                } else {
                    if (BuildConfig.DEBUG) WLog.w("BNB_MAIN Claim error " + resp.get(0).getCode() + "  " + resp.get(0).getLog());
                    mResult.errorCode = resp.get(0).getCode();
                    mResult.errorMsg = resp.get(0).getLog();
                    mResult.isSuccess = false;
                }

            } else if (mReceiveChain.equals(BaseChain.BNB_TEST)) {
                Response<ResBnbAccountInfo> response = ApiClient.getBnbTestChain(mApp).getAccountInfo(mReceiveAccount.address).execute();
                if(!response.isSuccessful()) {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                    return mResult;
                }
                mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromBnbLcd(mReceiveAccount.id, response.body()));
                mApp.getBaseDao().onUpdateBalances(mReceiveAccount.id, WUtil.getBalancesFromBnbLcd(mReceiveAccount.id, response.body()));
                mReceiveAccount = mApp.getBaseDao().onSelectAccount(""+mReceiveAccount.id);

                String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mReceiveAccount.uuid, mReceiveAccount.resource, mReceiveAccount.spec);
                DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(BaseChain.getChain(mReceiveAccount.baseChain), entropy, Integer.parseInt(mReceiveAccount.path), mReceiveAccount.newBip44);

                Wallet wallet = new Wallet(deterministicKey.getPrivateKeyAsHex(), BinanceDexEnvironment.TEST_NET);
                wallet.setAccountNumber(mReceiveAccount.accountNumber);
                wallet.setSequence(Long.valueOf(mReceiveAccount.sequenceNumber));

                mExpectedSwapId = mExpectedSwapId.toLowerCase();
                mRandomNumber = mRandomNumber.toLowerCase();
                BinanceDexApiRestClient client = BinanceDexApiClientFactory.newInstance().newRestClient(BinanceDexEnvironment.TEST_NET.getBaseUrl());
                TransactionOption options = new TransactionOption(mApp.getString(R.string.str_claim_swap_memo_c)  , 82, null);
                List<TransactionMetadata> resp = client.claimHtlt(mExpectedSwapId, WUtil.HexStringToByteArray(mRandomNumber), wallet, options, true);
                if (resp.get(0).isOk()) {
                    if (BuildConfig.DEBUG) WLog.w("BNB_TEST Claim suceess txhash " + resp.get(0).getHash());
                    mResult.resultData = resp.get(0).getHash();
                    mResult.isSuccess = true;

                } else {
                    if (BuildConfig.DEBUG) WLog.w("BNB_TEST Claim error " + resp.get(0).getCode() + "  " + resp.get(0).getLog());
                    mResult.errorCode = resp.get(0).getCode();
                    mResult.errorMsg = resp.get(0).getLog();
                    mResult.isSuccess = false;
                }


            } else if (mReceiveChain.equals(BaseChain.KAVA_MAIN)) {
                NodeInfo receiveNodeInfo = null;
                Response<ResNodeInfo> nodeCheck = ApiClient.getKavaChain(mApp).getNodeInfo().execute();
                if (nodeCheck.isSuccessful() && nodeCheck.body() != null&& nodeCheck.body().node_info != null) {
                    receiveNodeInfo = nodeCheck.body().node_info;
                } else {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                    return mResult;
                }

                Response<ResLcdKavaAccountInfo> response = ApiClient.getKavaChain(mApp).getAccountInfo(mReceiveAccount.address).execute();
                if(!response.isSuccessful()) {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                    return mResult;
                }
                mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromKavaLcd(mReceiveAccount.id, response.body()));
                mReceiveAccount = mApp.getBaseDao().onSelectAccount(""+mReceiveAccount.id);

                String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mReceiveAccount.uuid, mReceiveAccount.resource, mReceiveAccount.spec);
                DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(BaseChain.getChain(mReceiveAccount.baseChain), entropy, Integer.parseInt(mReceiveAccount.path), mReceiveAccount.newBip44);

                Msg claimSwapMsg = MsgGenerator.genClaimAtomicSwap(mReceiveAccount.address, mExpectedSwapId, mRandomNumber, mReceiveChain);
                ArrayList<Msg> msgs= new ArrayList<>();
                msgs.add(claimSwapMsg);

                ReqBroadCast reqBroadCast = MsgGenerator.getBroadcaseReq(mReceiveAccount, msgs, mClaimFee, mApp.getString(R.string.str_claim_swap_memo_c), deterministicKey, receiveNodeInfo.network);
//            WLog.w("reqBroadCast : " +  WUtil.prettyPrinter(reqBroadCast));
                Response<ResBroadTx> claimRes = ApiClient.getKavaChain(mApp).broadTx(reqBroadCast).execute();
                if(claimRes.isSuccessful() && claimRes.body() != null) {
                    if (claimRes.body().txhash != null) {
                        if (BuildConfig.DEBUG) WLog.w("KAVA_MAIN Claim suceess txhash " + claimRes.body().txhash);
                        mResult.resultData = claimRes.body().txhash;
                        mResult.isSuccess = true;
                    }
                    if(claimRes.body().code != null) {
                        if (BuildConfig.DEBUG) WLog.w("KAVA_MAIN Claim error " + mResult.errorCode + "  " + mResult.errorMsg);
                        mResult.errorCode = claimRes.body().code;
                        mResult.errorMsg = claimRes.body().raw_log;
                        mResult.isSuccess = false;
                    }

                } else {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                }

            } else if (mReceiveChain.equals(BaseChain.KAVA_TEST)) {
                NodeInfo receiveNodeInfo = null;
                Response<ResNodeInfo> nodeCheck = ApiClient.getKavaTestChain(mApp).getNodeInfo().execute();
                if (nodeCheck.isSuccessful() && nodeCheck.body() != null&& nodeCheck.body().node_info != null) {
                    receiveNodeInfo = nodeCheck.body().node_info;
                } else {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                    return mResult;
                }

                Response<ResLcdKavaAccountInfo> response = ApiClient.getKavaTestChain(mApp).getAccountInfo(mReceiveAccount.address).execute();
                if(!response.isSuccessful()) {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                    return mResult;
                }
                mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromKavaLcd(mReceiveAccount.id, response.body()));
                mReceiveAccount = mApp.getBaseDao().onSelectAccount(""+mReceiveAccount.id);

                String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mReceiveAccount.uuid, mReceiveAccount.resource, mReceiveAccount.spec);
                DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(BaseChain.getChain(mReceiveAccount.baseChain), entropy, Integer.parseInt(mReceiveAccount.path), mReceiveAccount.newBip44);

                Msg claimSwapMsg = MsgGenerator.genClaimAtomicSwap(mReceiveAccount.address, mExpectedSwapId, mRandomNumber, mReceiveChain);
                ArrayList<Msg> msgs= new ArrayList<>();
                msgs.add(claimSwapMsg);

                ReqBroadCast reqBroadCast = MsgGenerator.getBroadcaseReq(mReceiveAccount, msgs, mClaimFee, mApp.getString(R.string.str_claim_swap_memo_c), deterministicKey, receiveNodeInfo.network);
//            WLog.w("reqBroadCast : " +  WUtil.prettyPrinter(reqBroadCast));
                Response<ResBroadTx> claimRes = ApiClient.getKavaTestChain(mApp).broadTx(reqBroadCast).execute();
                if(claimRes.isSuccessful() && claimRes.body() != null) {
                    if (claimRes.body().txhash != null) {
                        if (BuildConfig.DEBUG) WLog.w("KAVA_TEST Claim suceess txhash " + claimRes.body().txhash);
                        mResult.resultData = claimRes.body().txhash;
                        mResult.isSuccess = true;
                    }
                    if(claimRes.body().code != null) {
                        if (BuildConfig.DEBUG) WLog.w("KAVA_TEST Claim error " + mResult.errorCode + "  " + mResult.errorMsg);
                        mResult.errorCode = claimRes.body().code;
                        mResult.errorMsg = claimRes.body().raw_log;
                        mResult.isSuccess = false;
                    }

                } else {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                }
            }

        } catch (Exception e) {
            if(BuildConfig.DEBUG) e.printStackTrace();
        }
        return mResult;
    }
}
