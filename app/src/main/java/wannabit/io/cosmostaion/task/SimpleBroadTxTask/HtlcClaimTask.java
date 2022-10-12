package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GEN_TX_HTLC_CLAIM;

import com.binance.dex.api.client.BinanceDexApiClientFactory;
import com.binance.dex.api.client.BinanceDexApiRestClient;
import com.binance.dex.api.client.BinanceDexEnvironment;
import com.binance.dex.api.client.Wallet;
import com.binance.dex.api.client.domain.TransactionMetadata;
import com.binance.dex.api.client.domain.broadcast.TransactionOption;

import java.util.List;

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

    private Account         mReceiveAccount;
    private BaseChain       mReceiveChain;
    private Fee             mClaimFee;
    private String          mExpectedSwapId;
    private String          mRandomNumber;

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
                Response<ResBnbAccountInfo> response = ApiClient.getBnbChain().getAccountInfo(mReceiveAccount.address).execute();
                if(!response.isSuccessful()) {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                    return mResult;
                }
                mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromBnbLcd(mReceiveAccount.id, response.body()));
                mApp.getBaseDao().onUpdateBalances(mReceiveAccount.id, WUtil.getBalancesFromBnbLcd(mReceiveAccount.id, response.body()));
                mReceiveAccount = mApp.getBaseDao().onSelectAccount(""+mReceiveAccount.id);

                Wallet wallet = new Wallet(WKey.getECKey(mApp, mReceiveAccount).getPrivateKeyAsHex(), BinanceDexEnvironment.PROD);
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

            } else if (mReceiveChain.equals(BaseChain.KAVA_MAIN)) {
                ServiceGrpc.ServiceBlockingStub nodeInfoStub = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mReceiveChain));
                Query.GetNodeInfoRequest receiveNodeInfo = Query.GetNodeInfoRequest.newBuilder().build();
                Query.GetNodeInfoResponse receiveInfo = nodeInfoStub.getNodeInfo(receiveNodeInfo);

                cosmos.tx.v1beta1.ServiceGrpc.ServiceBlockingStub txService = cosmos.tx.v1beta1.ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mReceiveChain));
                ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcKavaClaimHTLCSwapReq(WKey.onAuthResponse(mReceiveChain, mReceiveAccount), mReceiveAccount.address, mExpectedSwapId, mRandomNumber, mClaimFee, mApp.getString(R.string.str_claim_swap_memo_c), WKey.getECKey(mApp, mReceiveAccount), receiveInfo.getNodeInfo().getNetwork());
                ServiceOuterClass.BroadcastTxResponse response = txService.broadcastTx(broadcastTxRequest);
                mResult.resultData = response.getTxResponse().getTxhash();
                if (response.getTxResponse().getCode() > 0) {
                    mResult.errorCode = response.getTxResponse().getCode();
                    mResult.errorMsg = response.getTxResponse().getRawLog();
                    mResult.isSuccess = false;
                } else {
                    mResult.isSuccess = true;
                }
            }

        } catch (Exception e) {
            if(BuildConfig.DEBUG) e.printStackTrace();
        }
        return mResult;
    }
}