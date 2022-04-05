package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GEN_TX_HTLC_CLAIM;

import com.binance.dex.api.client.BinanceDexApiClientFactory;
import com.binance.dex.api.client.BinanceDexApiRestClient;
import com.binance.dex.api.client.BinanceDexEnvironment;
import com.binance.dex.api.client.Wallet;
import com.binance.dex.api.client.domain.TransactionMetadata;
import com.binance.dex.api.client.domain.broadcast.TransactionOption;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigInteger;
import java.util.List;

import cosmos.auth.v1beta1.QueryGrpc;
import cosmos.auth.v1beta1.QueryOuterClass;
import cosmos.base.tendermint.v1beta1.Query;
import cosmos.base.tendermint.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
import retrofit2.Response;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
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

public class HtlcClaimTask extends CommonTask {

    private Account mReceiveAccount;
    private final BaseChain mReceiveChain;
    private final Fee mClaimFee;
    private String mExpectedSwapId;
    private String mRandomNumber;

    private ECKey ecKey;

    public HtlcClaimTask(BaseApplication app, TaskListener listener, Account recipient, BaseChain receiveChain, Fee claimFee, String expectedSwapId, String randomNumber) {
        super(app, listener);
        this.mReceiveAccount = recipient;
        this.mReceiveChain = receiveChain;
        this.mClaimFee = claimFee;
        this.mExpectedSwapId = expectedSwapId;
        this.mRandomNumber = randomNumber;
        this.result.taskType = TASK_GEN_TX_HTLC_CLAIM;
    }


    @Override
    protected TaskResult doInBackground(String... strings) {

        try {
            if (mReceiveChain.equals(BaseChain.BNB_MAIN)) {
                Response<ResBnbAccountInfo> response = ApiClient.getBnbChain(context).getAccountInfo(mReceiveAccount.address).execute();
                if (!response.isSuccessful()) {
                    result.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                    return result;
                }
                context.getBaseDao().onUpdateAccount(WUtil.getAccountFromBnbLcd(mReceiveAccount.id, response.body()));
                context.getBaseDao().onUpdateBalances(mReceiveAccount.id, WUtil.getBalancesFromBnbLcd(mReceiveAccount.id, response.body()));
                mReceiveAccount = context.getBaseDao().onSelectAccount("" + mReceiveAccount.id);

                if (mReceiveAccount.fromMnemonic) {
                    String entropy = CryptoHelper.doDecryptData(context.getString(R.string.key_mnemonic) + mReceiveAccount.uuid, mReceiveAccount.resource, mReceiveAccount.spec);
                    DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(mReceiveAccount, entropy);
                    ecKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
                } else {
                    String privateKey = CryptoHelper.doDecryptData(context.getString(R.string.key_private) + mReceiveAccount.uuid, mReceiveAccount.resource, mReceiveAccount.spec);
                    ecKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
                }

                Wallet wallet = new Wallet(ecKey.getPrivateKeyAsHex(), BinanceDexEnvironment.PROD);
                wallet.setAccountNumber(mReceiveAccount.accountNumber);
                wallet.setSequence(Long.valueOf(mReceiveAccount.sequenceNumber));

                mExpectedSwapId = mExpectedSwapId.toLowerCase();
                mRandomNumber = mRandomNumber.toLowerCase();
                BinanceDexApiRestClient client = BinanceDexApiClientFactory.newInstance().newRestClient(BinanceDexEnvironment.PROD.getBaseUrl());
                TransactionOption options = new TransactionOption(context.getString(R.string.str_claim_swap_memo_c), 82, null);
                List<TransactionMetadata> resp = client.claimHtlt(mExpectedSwapId, WUtil.HexStringToByteArray(mRandomNumber), wallet, options, true);
                if (resp.get(0).isOk()) {
                    if (BuildConfig.DEBUG)
                        WLog.w("BNB_MAIN Claim suceess txhash " + resp.get(0).getHash());
                    result.resultData = resp.get(0).getHash();
                    result.isSuccess = true;

                } else {
                    if (BuildConfig.DEBUG)
                        WLog.w("BNB_MAIN Claim error " + resp.get(0).getCode() + "  " + resp.get(0).getLog());
                    result.errorCode = resp.get(0).getCode();
                    result.errorMsg = resp.get(0).getLog();
                    result.isSuccess = false;
                }

            } else if (mReceiveChain.equals(BaseChain.KAVA_MAIN)) {
                ServiceGrpc.ServiceBlockingStub nodeInfoStub = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mReceiveChain));
                Query.GetNodeInfoRequest receiveNodeInfo = Query.GetNodeInfoRequest.newBuilder().build();
                Query.GetNodeInfoResponse receiveInfo = nodeInfoStub.getNodeInfo(receiveNodeInfo);

                if (mReceiveAccount.fromMnemonic) {
                    String entropy = CryptoHelper.doDecryptData(context.getString(R.string.key_mnemonic) + mReceiveAccount.uuid, mReceiveAccount.resource, mReceiveAccount.spec);
                    DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(mReceiveAccount, entropy);
                    ecKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
                } else {
                    String privateKey = CryptoHelper.doDecryptData(context.getString(R.string.key_private) + mReceiveAccount.uuid, mReceiveAccount.resource, mReceiveAccount.spec);
                    ecKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
                }

                QueryGrpc.QueryBlockingStub authStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mReceiveChain));
                QueryOuterClass.QueryAccountRequest request = QueryOuterClass.QueryAccountRequest.newBuilder().setAddress(mReceiveAccount.address).build();
                QueryOuterClass.QueryAccountResponse mAuthResponse = authStub.account(request);

                //broadCast
                cosmos.tx.v1beta1.ServiceGrpc.ServiceBlockingStub txService = cosmos.tx.v1beta1.ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mReceiveChain));
                ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcKavaClaimHTLCSwapReq(mAuthResponse, mReceiveAccount.address, mExpectedSwapId, mRandomNumber, mClaimFee, context.getString(R.string.str_claim_swap_memo_c), ecKey, receiveInfo.getNodeInfo().getNetwork());
                ServiceOuterClass.BroadcastTxResponse response = txService.broadcastTx(broadcastTxRequest);
                result.resultData = response.getTxResponse().getTxhash();
                if (response.getTxResponse().getCode() > 0) {
                    result.errorCode = response.getTxResponse().getCode();
                    result.errorMsg = response.getTxResponse().getRawLog();
                    result.isSuccess = false;
                } else {
                    result.isSuccess = true;
                }
            }

        } catch (Exception e) {
            if (BuildConfig.DEBUG) e.printStackTrace();
        }
        return result;
    }
}