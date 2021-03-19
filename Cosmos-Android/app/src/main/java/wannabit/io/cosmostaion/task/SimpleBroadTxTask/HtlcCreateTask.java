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
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResBnbAccountInfo;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResLcdKavaAccountInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_BNB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_BTCB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_BUSD_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_XRPB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_TEST_BNB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_TEST_BTC_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_BROADCAST;
import static wannabit.io.cosmostaion.base.BaseConstant.IS_SHOWLOG;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_BNB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_BTCB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_BUSD_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_XRPB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_TEST_BNB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_TEST_BTC_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GEN_TX_HTLC_CREATE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_TEST_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_TEST_BTC;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_XRPB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_TEST_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_TEST_BTC;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_XRPB;

public class HtlcCreateTask extends CommonTask {

    private Account         mSendAccount;
    private Account         mReceiveAccount;
    private BaseChain       mSendChain;
    private BaseChain       mReceiveChain;
    private ArrayList<Coin> mToSendCoins;
    private Fee             mSendFee;

    private byte[]          mRandomNumberHash;
    private String          mRandomNumber;
    private String          mExpectedSwapId;

    public HtlcCreateTask(BaseApplication app, TaskListener listener, Account sender, Account recipient, BaseChain sendChain, BaseChain receiveChain, ArrayList<Coin> toSendCoins, Fee sendFee) {
        super(app, listener);
        this.mSendAccount = sender;
        this.mReceiveAccount = recipient;
        this.mSendChain = sendChain;
        this.mReceiveChain = receiveChain;
        this.mToSendCoins = toSendCoins;
        this.mSendFee = sendFee;
        this.mResult.taskType = TASK_GEN_TX_HTLC_CREATE;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mSendChain.equals(BaseChain.BNB_MAIN)) {
                Response<ResBnbAccountInfo> response = ApiClient.getBnbChain(mApp).getAccountInfo(mSendAccount.address).execute();
                if(!response.isSuccessful()) {
                    mResult.errorCode = ERROR_CODE_BROADCAST;
                    return mResult;
                }
                mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromBnbLcd(mSendAccount.id, response.body()));
                mApp.getBaseDao().onUpdateBalances(mSendAccount.id, WUtil.getBalancesFromBnbLcd(mSendAccount.id, response.body()));
                mSendAccount = mApp.getBaseDao().onSelectAccount(""+mSendAccount.id);

                String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mSendAccount.uuid, mSendAccount.resource, mSendAccount.spec);
                DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(BaseChain.getChain(mSendAccount.baseChain), entropy, Integer.parseInt(mSendAccount.path), mSendAccount.newBip44);

                Wallet wallet = new Wallet(deterministicKey.getPrivateKeyAsHex(), BinanceDexEnvironment.PROD);
                wallet.setAccountNumber(mSendAccount.accountNumber);
                wallet.setSequence(Long.valueOf(mSendAccount.sequenceNumber));

                long timestamp = Calendar.getInstance().getTimeInMillis() / 1000;
                byte[] randomNumber = RandomUtils.nextBytes(32);
                byte[] originData = ArrayUtils.addAll(randomNumber, WUtil.long2Bytes(timestamp));

                HtltReq htltReq = MsgGenerator.getBnbHtlcCreateMsg(mSendChain, mReceiveChain, mSendAccount, mReceiveAccount, mToSendCoins, timestamp, originData);
                mRandomNumber = WUtil.ByteArrayToHexString(randomNumber).toUpperCase();
                mRandomNumberHash = htltReq.getRandomNumberHash();
                if (mToSendCoins.get(0).denom.equals(TOKEN_HTLC_BINANCE_BNB)) {
                    mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, KAVA_MAIN_BNB_DEPUTY, mSendAccount.address).toUpperCase();

                } else if (mToSendCoins.get(0).denom.equals(TOKEN_HTLC_BINANCE_BTCB)) {
                    mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, KAVA_MAIN_BTCB_DEPUTY, mSendAccount.address).toUpperCase();

                } else if (mToSendCoins.get(0).denom.equals(TOKEN_HTLC_BINANCE_XRPB)) {
                    mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, KAVA_MAIN_XRPB_DEPUTY, mSendAccount.address).toUpperCase();

                } else if (mToSendCoins.get(0).denom.equals(TOKEN_HTLC_BINANCE_BUSD)) {
                    mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, KAVA_MAIN_BUSD_DEPUTY, mSendAccount.address).toUpperCase();

                }

                WLog.w("BNB_MAIN mRandomNumberHash " + mRandomNumberHash);
                WLog.w("BNB_MAIN mRandomNumber " + mRandomNumber);
                WLog.w("BNB_MAIN Send mExpectedSwapId " + mExpectedSwapId);

                TransactionOption options = new TransactionOption(mApp.getString(R.string.str_create_swap_memo_c)  , 82, null);
                BinanceDexApiRestClient client = BinanceDexApiClientFactory.newInstance().newRestClient(BinanceDexEnvironment.PROD.getBaseUrl());
                List<TransactionMetadata> resp = client.htlt(htltReq, wallet, options, true);
                if (resp.get(0).isOk()) {
                    if (IS_SHOWLOG) WLog.w("Send suceess txhash " + resp.get(0).getHash());
                    mResult.resultData = resp.get(0).getHash();
                    mResult.resultData2 = mExpectedSwapId;
                    mResult.resultData3 = mRandomNumber;
                    mResult.isSuccess = true;

                } else {
                    if (IS_SHOWLOG) WLog.w("Send error " + resp.get(0).getCode() + "  " + resp.get(0).getLog());
                    mResult.errorCode = resp.get(0).getCode();
                    mResult.errorMsg = resp.get(0).getLog();
                    mResult.isSuccess = false;
                }


            } else if (mSendChain.equals(BaseChain.BNB_TEST)) {
                Response<ResBnbAccountInfo> response = ApiClient.getBnbTestChain(mApp).getAccountInfo(mSendAccount.address).execute();
                if(!response.isSuccessful()) {
                    mResult.errorCode = ERROR_CODE_BROADCAST;
                    return mResult;
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
                if (mToSendCoins.get(0).denom.equals(TOKEN_HTLC_BINANCE_TEST_BNB)) {
                    mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, KAVA_TEST_BNB_DEPUTY, mSendAccount.address).toUpperCase();
                } else if (mToSendCoins.get(0).denom.equals(TOKEN_HTLC_BINANCE_TEST_BTC)) {
                    mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, KAVA_TEST_BTC_DEPUTY, mSendAccount.address).toUpperCase();
                }
                WLog.w("BNB_TEST mRandomNumberHash " + mRandomNumberHash);
                WLog.w("BNB_TEST mRandomNumber " + mRandomNumber);
                WLog.w("BNB_TEST Send mExpectedSwapId " + mExpectedSwapId);

                TransactionOption options = new TransactionOption(mApp.getString(R.string.str_create_swap_memo_c)  , 82, null);
                BinanceDexApiRestClient client = BinanceDexApiClientFactory.newInstance().newRestClient(BinanceDexEnvironment.TEST_NET.getBaseUrl());
                List<TransactionMetadata> resp = client.htlt(htltReq, wallet, options, true);
                if (resp.get(0).isOk()) {
                    if (IS_SHOWLOG) WLog.w("Send suceess txhash " + resp.get(0).getHash());
                    mResult.resultData = resp.get(0).getHash();
                    mResult.resultData2 = mExpectedSwapId;
                    mResult.resultData3 = mRandomNumber;
                    mResult.isSuccess = true;

                } else {
                    if (IS_SHOWLOG) WLog.w("Send error " + resp.get(0).getCode() + "  " + resp.get(0).getLog());
                    mResult.errorCode = resp.get(0).getCode();
                    mResult.errorMsg = resp.get(0).getLog();
                    mResult.isSuccess = false;
                }


            } else if (mSendChain.equals(BaseChain.KAVA_MAIN)) {
                Response<ResLcdKavaAccountInfo> response = ApiClient.getKavaChain(mApp).getAccountInfo(mSendAccount.address).execute();
                if (!response.isSuccessful()) {
                    mResult.errorCode = ERROR_CODE_BROADCAST;
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
                if (mToSendCoins.get(0).denom.equals(TOKEN_HTLC_KAVA_BNB)) {
                    mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, BINANCE_MAIN_BNB_DEPUTY, mSendAccount.address).toUpperCase();

                } else if (mToSendCoins.get(0).denom.equals(TOKEN_HTLC_KAVA_BTCB)) {
                    mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, BINANCE_MAIN_BTCB_DEPUTY, mSendAccount.address).toUpperCase();

                } else if (mToSendCoins.get(0).denom.equals(TOKEN_HTLC_KAVA_XRPB)) {
                    mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, BINANCE_MAIN_XRPB_DEPUTY, mSendAccount.address).toUpperCase();

                } else if (mToSendCoins.get(0).denom.equals(TOKEN_HTLC_KAVA_BUSD)) {
                    mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, BINANCE_MAIN_BUSD_DEPUTY, mSendAccount.address).toUpperCase();

                }

                ReqBroadCast reqBroadCast = MsgGenerator.getBroadcaseReq(mSendAccount, msgs, mSendFee, mApp.getString(R.string.str_create_swap_memo_c), deterministicKey, mApp.getBaseDao().getChainId());
                WLog.w("KAVA_MAIN mRandomNumber " + mRandomNumber);
                WLog.w("KAVA_MAIN Send mExpectedSwapId " + mExpectedSwapId);
                WLog.w("KAVA_MAIN reqBroadCast : " +  WUtil.prettyPrinter(reqBroadCast));

                Response<ResBroadTx> response2 = ApiClient.getKavaChain(mApp).broadTx(reqBroadCast).execute();
                if (response2.isSuccessful() && response2.body() != null) {
                    if (response2.body().txhash != null) {
                        WLog.w("KAVA_MAIN Gen HTLC suceess txhash " + response2.body().txhash);
                        mResult.resultData = response2.body().txhash;
                        mResult.resultData2 = mExpectedSwapId;
                        mResult.resultData3 = mRandomNumber;
                        mResult.isSuccess = true;
                    }
                    if(response2.body().code != null) {
                        WLog.w("KAVA_MAIN Gen HTLC error " + response2.body().code + "  " + response2.body().raw_log);
                        mResult.errorCode = response2.body().code;
                        mResult.errorMsg = response2.body().raw_log;
                        mResult.isSuccess = false;
                    }

                } else {
                    WLog.w("KAVA_MAIN Gen HTLC Fail " + response2.errorBody().string());
                    mResult.errorCode = ERROR_CODE_BROADCAST;
                    mResult.isSuccess = false;
                }



            } else if (mSendChain.equals(BaseChain.KAVA_TEST)) {
                Response<ResLcdKavaAccountInfo> response = ApiClient.getKavaTestChain(mApp).getAccountInfo(mSendAccount.address).execute();
                if(!response.isSuccessful()) {
                    mResult.errorCode = ERROR_CODE_BROADCAST;
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
                if (mToSendCoins.get(0).denom.equals(TOKEN_HTLC_KAVA_TEST_BNB)) {
                    mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, BINANCE_TEST_BNB_DEPUTY, mSendAccount.address).toUpperCase();
                } else if (mToSendCoins.get(0).denom.equals(TOKEN_HTLC_KAVA_TEST_BTC)) {
                    mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, BINANCE_TEST_BTC_DEPUTY, mSendAccount.address).toUpperCase();
                }

                ReqBroadCast reqBroadCast = MsgGenerator.getBroadcaseReq(mSendAccount, msgs, mSendFee, mApp.getString(R.string.str_create_swap_memo_c), deterministicKey, mApp.getBaseDao().getChainId());
                WLog.w("KAVA_TEST mRandomNumber " + mRandomNumber);
                WLog.w("KAVA_TEST Send mExpectedSwapId " + mExpectedSwapId);
                WLog.w("KAVA_TEST reqBroadCast : " +  WUtil.prettyPrinter(reqBroadCast));

                Response<ResBroadTx> response2 = ApiClient.getKavaTestChain(mApp).broadTx(reqBroadCast).execute();
                if (response2.isSuccessful() && response2.body() != null) {
                    if (response2.body().txhash != null) {
                        WLog.w("KAVA_TEST Gen HTLC suceess txhash " + response2.body().txhash);
                        mResult.resultData = response2.body().txhash;
                        mResult.resultData2 = mExpectedSwapId;
                        mResult.resultData3 = mRandomNumber;
                        mResult.isSuccess = true;
                    }
                    if(response2.body().code != null) {
                        WLog.w("KAVA_TEST Gen HTLC error " + response2.body().code + "  " + response2.body().raw_log);
                        mResult.errorCode = response2.body().code;
                        mResult.errorMsg = response2.body().raw_log;
                        mResult.isSuccess = false;
                    }

                } else {
                    WLog.w("KAVA_TEST Gen HTLC Fail " + response2.errorBody().string());
                    mResult.errorCode = ERROR_CODE_BROADCAST;
                    mResult.isSuccess = false;
                }
            }

        } catch (Exception e) {
            if(IS_SHOWLOG) e.printStackTrace();
        }
        return mResult;
    }
}
