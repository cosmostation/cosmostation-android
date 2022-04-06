package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_BNB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_BTCB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_BUSD_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_XRPB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_BROADCAST;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_BNB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_BTCB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_BUSD_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_XRPB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GEN_TX_HTLC_CREATE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_XRPB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_XRPB;

import com.binance.dex.api.client.BinanceDexApiClientFactory;
import com.binance.dex.api.client.BinanceDexApiRestClient;
import com.binance.dex.api.client.BinanceDexEnvironment;
import com.binance.dex.api.client.Wallet;
import com.binance.dex.api.client.domain.TransactionMetadata;
import com.binance.dex.api.client.domain.broadcast.HtltReq;
import com.binance.dex.api.client.domain.broadcast.TransactionOption;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cosmos.auth.v1beta1.QueryGrpc;
import cosmos.auth.v1beta1.QueryOuterClass;
import cosmos.tx.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
import retrofit2.Response;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.crypto.Sha256;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.network.res.ResBnbAccountInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class HtlcCreateTask extends CommonTask {

    private Account mSendAccount;
    private final Account mReceiveAccount;
    private final BaseChain mSendChain;
    private final BaseChain mReceiveChain;
    private final ArrayList<Coin> mToSendCoins;
    private final Fee mSendFee;

    private byte[] mRandomNumberHash;
    private String mRandomNumber;
    private String mExpectedSwapId;

    private ECKey ecKey;

    public HtlcCreateTask(BaseApplication app, TaskListener listener, Account sender, Account recipient, BaseChain sendChain, BaseChain receiveChain, ArrayList<Coin> toSendCoins, Fee sendFee) {
        super(app, listener);
        this.mSendAccount = sender;
        this.mReceiveAccount = recipient;
        this.mSendChain = sendChain;
        this.mReceiveChain = receiveChain;
        this.mToSendCoins = toSendCoins;
        this.mSendFee = sendFee;
        this.result.taskType = TASK_GEN_TX_HTLC_CREATE;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mSendChain.equals(BaseChain.BNB_MAIN)) {
                Response<ResBnbAccountInfo> response = ApiClient.getBnbChain(context).getAccountInfo(mSendAccount.address).execute();
                if (!response.isSuccessful()) {
                    result.errorCode = ERROR_CODE_BROADCAST;
                    return result;
                }
                context.getBaseDao().onUpdateAccount(WUtil.getAccountFromBnbLcd(mSendAccount.id, response.body()));
                context.getBaseDao().onUpdateBalances(mSendAccount.id, WUtil.getBalancesFromBnbLcd(mSendAccount.id, response.body()));
                mSendAccount = context.getBaseDao().onSelectAccount("" + mSendAccount.id);

                if (mSendAccount.fromMnemonic) {
                    String entropy = CryptoHelper.doDecryptData(context.getString(R.string.key_mnemonic) + mSendAccount.uuid, mSendAccount.resource, mSendAccount.spec);
                    DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(mSendAccount, entropy);
                    ecKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
                } else {
                    String privateKey = CryptoHelper.doDecryptData(context.getString(R.string.key_private) + mSendAccount.uuid, mSendAccount.resource, mSendAccount.spec);
                    ecKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
                }

                Wallet wallet = new Wallet(ecKey.getPrivateKeyAsHex(), BinanceDexEnvironment.PROD);
                wallet.setAccountNumber(mSendAccount.accountNumber);
                wallet.setSequence(Long.valueOf(mSendAccount.sequenceNumber));

                long timestamp = Calendar.getInstance().getTimeInMillis() / 1000;
                byte[] randomNumber = RandomUtils.nextBytes(32);
                byte[] originData = ArrayUtils.addAll(randomNumber, WUtil.long2Bytes(timestamp));

                HtltReq htltReq = MsgGenerator.getBnbHtlcCreateMsg(mSendChain, mReceiveChain, mSendAccount, mReceiveAccount, mToSendCoins, timestamp, originData);
                mRandomNumber = WUtil.byteArrayToHexString(randomNumber).toUpperCase();
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

                TransactionOption options = new TransactionOption(context.getString(R.string.str_create_swap_memo_c), 82, null);
                BinanceDexApiRestClient client = BinanceDexApiClientFactory.newInstance().newRestClient(BinanceDexEnvironment.PROD.getBaseUrl());
                List<TransactionMetadata> resp = client.htlt(htltReq, wallet, options, true);
                if (resp.get(0).isOk()) {
                    if (BuildConfig.DEBUG) WLog.w("Send suceess txhash " + resp.get(0).getHash());
                    result.resultData = resp.get(0).getHash();
                    result.resultData2 = mExpectedSwapId;
                    result.resultData3 = mRandomNumber;
                    result.isSuccess = true;

                } else {
                    if (BuildConfig.DEBUG)
                        WLog.w("Send error " + resp.get(0).getCode() + "  " + resp.get(0).getLog());
                    result.errorCode = resp.get(0).getCode();
                    result.errorMsg = resp.get(0).getLog();
                    result.isSuccess = false;
                }


            } else if (mSendChain.equals(BaseChain.KAVA_MAIN)) {
                if (mSendAccount.fromMnemonic) {
                    String entropy = CryptoHelper.doDecryptData(context.getString(R.string.key_mnemonic) + mSendAccount.uuid, mSendAccount.resource, mSendAccount.spec);
                    DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(mSendAccount, entropy);
                    ecKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
                } else {
                    String privateKey = CryptoHelper.doDecryptData(context.getString(R.string.key_private) + mSendAccount.uuid, mSendAccount.resource, mSendAccount.spec);
                    ecKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
                }

                QueryGrpc.QueryBlockingStub authStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mSendChain));
                QueryOuterClass.QueryAccountRequest request = QueryOuterClass.QueryAccountRequest.newBuilder().setAddress(mSendAccount.address).build();
                QueryOuterClass.QueryAccountResponse mAuthResponse = authStub.account(request);

                long timestamp = Calendar.getInstance().getTimeInMillis() / 1000;
                byte[] randomNumber = RandomUtils.nextBytes(32);
                byte[] originData = ArrayUtils.addAll(randomNumber, WUtil.long2Bytes(timestamp));

                mRandomNumber = WUtil.byteArrayToHexString(randomNumber).toUpperCase();
                mRandomNumberHash = WUtil.hexStringToByteArray(WUtil.byteArrayToHexString(Sha256.getSha256Digest().digest(originData)).toUpperCase());
                if (mToSendCoins.get(0).denom.equals(TOKEN_HTLC_KAVA_BNB)) {
                    mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, BINANCE_MAIN_BNB_DEPUTY, mSendAccount.address).toUpperCase();

                } else if (mToSendCoins.get(0).denom.equals(TOKEN_HTLC_KAVA_BTCB)) {
                    mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, BINANCE_MAIN_BTCB_DEPUTY, mSendAccount.address).toUpperCase();

                } else if (mToSendCoins.get(0).denom.equals(TOKEN_HTLC_KAVA_XRPB)) {
                    mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, BINANCE_MAIN_XRPB_DEPUTY, mSendAccount.address).toUpperCase();

                } else if (mToSendCoins.get(0).denom.equals(TOKEN_HTLC_KAVA_BUSD)) {
                    mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, BINANCE_MAIN_BUSD_DEPUTY, mSendAccount.address).toUpperCase();

                }

                //broadCast
                ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mSendChain));
                ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcKavaCreateHTLCSwapReq(mAuthResponse, mSendAccount.address, mReceiveAccount.address, mToSendCoins, timestamp, WUtil.byteArrayToHexString(Sha256.getSha256Digest().digest(originData)).toUpperCase(), mSendFee, context.getString(R.string.str_create_swap_memo_c), ecKey, context.getBaseDao().getChainIdGrpc());
                ServiceOuterClass.BroadcastTxResponse response = txService.broadcastTx(broadcastTxRequest);
                result.resultData = response.getTxResponse().getTxhash();
                if (response.getTxResponse().getCode() > 0) {
                    result.errorCode = response.getTxResponse().getCode();
                    result.errorMsg = response.getTxResponse().getRawLog();
                    result.isSuccess = false;
                } else {
                    result.isSuccess = true;
                    result.resultData2 = mExpectedSwapId;
                    result.resultData3 = mRandomNumber;
                }
            }

        } catch (Exception e) {
            if (BuildConfig.DEBUG) e.printStackTrace();
        }
        return result;
    }
}