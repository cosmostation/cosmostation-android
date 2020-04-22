package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import com.binance.dex.api.client.BinanceDexApiClientFactory;
import com.binance.dex.api.client.BinanceDexApiRestClient;
import com.binance.dex.api.client.BinanceDexEnvironment;
import com.binance.dex.api.client.Wallet;
import com.binance.dex.api.client.domain.TransactionMetadata;
import com.binance.dex.api.client.domain.broadcast.HtltReq;
import com.binance.dex.api.client.domain.broadcast.TransactionOption;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.bitcoinj.crypto.DeterministicKey;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResBnbAccountInfo;
import wannabit.io.cosmostaion.network.res.ResBnbSwapInfo;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResKavaSwapInfo;
import wannabit.io.cosmostaion.network.res.ResLcdKavaAccountInfo;
import wannabit.io.cosmostaion.task.CommonProgressTask;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.ProgressTaskListener;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.BNB_TEST_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.IS_SHOWLOG;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_TEST_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GEN_TX_HTLC_SWAP;

public class HtlcSwapTask extends CommonProgressTask {

    private Account         mSendAccount;
    private Account         mReceiveAccount;
    private BaseChain       mSendChain;
    private BaseChain       mReceiveChain;
    private ArrayList<Coin> mToSendCoins;
    private Fee             mSendFee;
    private Fee             mClaimFee;

    private byte[]          mRandomNumberHash;
    private String          mRandomNumber;
    private String          mExpectedSwapId;

    public HtlcSwapTask(BaseApplication app, ProgressTaskListener listener, Account sendAccount, Account receiveAccount,
                        ArrayList<Coin> sendCoins, Fee sendFee, Fee claimFee) {
        super(app, listener);
        this.mSendAccount = sendAccount;
        this.mReceiveAccount = receiveAccount;
        this.mToSendCoins = sendCoins;
        this.mSendFee = sendFee;
        this.mClaimFee = claimFee;
        this.mSendChain = BaseChain.getChain(mSendAccount.baseChain);
        this.mReceiveChain = BaseChain.getChain(mReceiveAccount.baseChain);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {

            mResult = onCreateHtlcSwap();
            if (mResult.isSuccess) {
                publishProgress(1);
                if (mReceiveChain.equals(BaseChain.KAVA_MAIN)) {
                    for (int i = 0; i < 8; i++) {
                        Response<ResKavaSwapInfo> response = ApiClient.getKavaTestChain(mApp).getSwapById(mExpectedSwapId).execute();
                        if (response.isSuccessful() && response.body() != null && response.body().result.status != null) {
                            break;
                        } else {
                            Thread.sleep(5000);
                        }
                    }
                } else if (mReceiveChain.equals(BaseChain.KAVA_TEST)) {
                    for (int i = 0; i < 8; i++) {
                        Response<ResKavaSwapInfo> response = ApiClient.getKavaChain(mApp).getSwapById(mExpectedSwapId).execute();
                        if (response.isSuccessful() && response.body() != null && response.body().result.status != null) {
                            break;
                        } else {
                            Thread.sleep(5000);
                        }
                    }
                } else if (mReceiveChain.equals(BaseChain.BNB_MAIN)) {
                    for (int i = 0; i < 8; i++) {
                        Response<ResBnbSwapInfo> response = ApiClient.getBnbChain(mApp).getSwapById(mExpectedSwapId).execute();
                        if (response.isSuccessful() && response.body() != null && response.body().swapId != null) {
                            break;
                        } else {
                            Thread.sleep(5000);
                        }
                    }

                } else if (mReceiveChain.equals(BaseChain.BNB_TEST)) {
                    for (int i = 0; i < 8; i++) {
                        Response<ResBnbSwapInfo> response = ApiClient.getBnbTestChain(mApp).getSwapById(mExpectedSwapId).execute();
                        if (response.isSuccessful() && response.body() != null && response.body().swapId != null) {
                            break;
                        } else {
                            Thread.sleep(5000);
                        }
                    }

                }
                Thread.sleep(3000);
                return onClaimHtlcSwap();
            }

        } catch (Exception e) {
            if(IS_SHOWLOG) e.printStackTrace();
        }
        return mResult;
    }

    private TaskResult onCreateHtlcSwap() throws Exception {
        TaskResult result = new TaskResult();
        result.taskType = TASK_GEN_TX_HTLC_SWAP;
        if (mSendChain.equals(BaseChain.BNB_MAIN)) {

        } else if (mSendChain.equals(BaseChain.BNB_TEST)) {
            Response<ResBnbAccountInfo> response = ApiClient.getBnbTestChain(mApp).getAccountInfo(mSendAccount.address).execute();
            if(!response.isSuccessful()) {
                result.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                return result;
            }
            mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromBnbLcd(mSendAccount.id, response.body()));
            mApp.getBaseDao().onUpdateBalances(mSendAccount.id, WUtil.getBalancesFromBnbLcd(mSendAccount.id, response.body()));
            mSendAccount = mApp.getBaseDao().onSelectAccount(""+mSendAccount.id);

            String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mSendAccount.uuid, mSendAccount.resource, mSendAccount.spec);
            DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(BaseChain.getChain(mSendAccount.baseChain), entropy, Integer.parseInt(mSendAccount.path), mSendAccount.newBip44);

            Wallet wallet = new Wallet(deterministicKey.getPrivateKeyAsHex(), BinanceDexEnvironment.TEST_NET);
            wallet.setAccountNumber(mSendAccount.accountNumber);
            wallet.setSequence(Long.valueOf(mSendAccount.sequenceNumber));

            long timestamp = Calendar.getInstance().getTimeInMillis() / 1000;
            byte[] randomNumber = RandomUtils.nextBytes(32);
            byte[] originData = ArrayUtils.addAll(randomNumber, WUtil.long2Bytes(timestamp));

            HtltReq htltReq = MsgGenerator.getBnbHtlcCreateMsg(mSendChain, mReceiveChain, mSendAccount, mReceiveAccount, mToSendCoins, timestamp, originData);
            mRandomNumber = WUtil.ByteArrayToHexString(randomNumber).toUpperCase();
            mRandomNumberHash = htltReq.getRandomNumberHash();
            WLog.w("mRandomNumberHash " + mRandomNumberHash);
            mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, KAVA_TEST_DEPUTY, mSendAccount.address).toUpperCase();
            WLog.w("mRandomNumber " + mRandomNumber);
            WLog.w("BNB Send mExpectedSwapId " + mExpectedSwapId);

            TransactionOption options = new TransactionOption(mApp.getString(R.string.str_create_swap_memo_c)  , 82, null);
            BinanceDexApiRestClient client = BinanceDexApiClientFactory.newInstance().newRestClient(BinanceDexEnvironment.TEST_NET.getBaseUrl());
            List<TransactionMetadata> resp = client.htlt(htltReq, wallet, options, true);
            if (resp.get(0).isOk()) {
                if (IS_SHOWLOG) WLog.w("Send suceess txhash " + resp.get(0).getHash());
                result.resultData2 = resp.get(0).getHash();
                result.isSuccess = true;

            } else {
                if (IS_SHOWLOG) WLog.w("Send error " + resp.get(0).getCode() + "  " + resp.get(0).getLog());
                result.errorCode = resp.get(0).getCode();
                result.errorMsg = resp.get(0).getLog();
                result.isSuccess = false;
            }


        } else if (mSendChain.equals(BaseChain.KAVA_MAIN)) {

        } else if (mSendChain.equals(BaseChain.KAVA_TEST)) {
            Response<ResLcdKavaAccountInfo> response = ApiClient.getKavaTestChain(mApp).getAccountInfo(mSendAccount.address).execute();
            if(!response.isSuccessful()) {
                mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                return mResult;
            }
            mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromKavaLcd(mSendAccount.id, response.body()));
            mApp.getBaseDao().onUpdateBalances(mSendAccount.id, WUtil.getBalancesFromKavaLcd(mSendAccount.id, response.body()));
            mSendAccount = mApp.getBaseDao().onSelectAccount(""+mSendAccount.id);

            String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mSendAccount.uuid, mSendAccount.resource, mSendAccount.spec);
            DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(BaseChain.getChain(mSendAccount.baseChain), entropy, Integer.parseInt(mSendAccount.path), mSendAccount.newBip44);

            long timestamp = Calendar.getInstance().getTimeInMillis() / 1000;
            byte[] randomNumber  = RandomUtils.nextBytes(32);
            byte[] originData = ArrayUtils.addAll(randomNumber, WUtil.long2Bytes(timestamp));

            Msg createSwapMsg = MsgGenerator.genCreateSwapMsg(mSendChain, mReceiveChain, mSendAccount, mReceiveAccount, mToSendCoins, timestamp, originData);
            ArrayList<Msg> msgs= new ArrayList<>();
            msgs.add(createSwapMsg);

            mRandomNumber = WUtil.ByteArrayToHexString(randomNumber).toUpperCase();
            mRandomNumberHash = WUtil.HexStringToByteArray(createSwapMsg.value.random_number_hash);
            mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, BNB_TEST_DEPUTY, mSendAccount.address).toUpperCase();
            WLog.w("mRandomNumber " + mRandomNumber);
            WLog.w("KAVA Send mExpectedSwapId " + mExpectedSwapId);


            ReqBroadCast reqBroadCast = MsgGenerator.getBraodcaseReq(mSendAccount, msgs, mSendFee, mApp.getString(R.string.str_create_swap_memo_c), deterministicKey);
            WLog.w("reqBroadCast : " +  WUtil.prettyPrinter(reqBroadCast));

            Response<ResBroadTx> response2 = ApiClient.getKavaTestChain(mApp).broadTx(reqBroadCast).execute();
            if(response2.isSuccessful() && response2.body() != null) {
                if (response2.body().txhash != null) {
                    if (IS_SHOWLOG) WLog.w("Send suceess txhash " + response2.body().txhash);
                    result.resultData2 = response2.body().txhash;
                    result.isSuccess = true;
                }
                if(response2.body().code != null) {
                    if (IS_SHOWLOG) WLog.w("Send error " + response2.body().code + "  " + response2.body().raw_log);
                    result.errorCode = response2.body().code;
                    result.errorMsg = response2.body().raw_log;
                    result.isSuccess = false;
                }

            } else {
                result.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                result.isSuccess = false;
            }
        }
        return result;

    }

    private TaskResult onClaimHtlcSwap() throws Exception {
        publishProgress(2);
        if (mReceiveChain.equals(BaseChain.BNB_MAIN)) {

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
                if (IS_SHOWLOG) WLog.w("Claim suceess txhash " + resp.get(0).getHash());
                mResult.resultData = resp.get(0).getHash();

            } else {
                if (IS_SHOWLOG) WLog.w("Claim error " + resp.get(0).getCode() + "  " + resp.get(0).getLog());
                mResult.errorCode = resp.get(0).getCode();
                mResult.errorMsg = resp.get(0).getLog();
                mResult.isSuccess = false;
            }


        } else if (mReceiveChain.equals(BaseChain.KAVA_MAIN)) {

        } else if (mReceiveChain.equals(BaseChain.KAVA_TEST)) {
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

            ReqBroadCast reqBroadCast = MsgGenerator.getBraodcaseReq(mReceiveAccount, msgs, mClaimFee, mApp.getString(R.string.str_claim_swap_memo_c), deterministicKey);
            Response<ResBroadTx> claimRes = ApiClient.getKavaTestChain(mApp).broadTx(reqBroadCast).execute();
            if(claimRes.isSuccessful() && claimRes.body() != null) {
                if (claimRes.body().txhash != null) {
                    if (IS_SHOWLOG) WLog.w("Claim suceess txhash " + claimRes.body().txhash);
                    mResult.resultData = claimRes.body().txhash;
                }
                if(claimRes.body().code != null) {
                    if (IS_SHOWLOG) WLog.w("Claim error " + mResult.errorCode + "  " + mResult.errorMsg);
                    mResult.errorCode = claimRes.body().code;
                    mResult.errorMsg = claimRes.body().raw_log;
                    mResult.isSuccess = false;
                }

            } else {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
            }
        }
        return mResult;
    }
}
