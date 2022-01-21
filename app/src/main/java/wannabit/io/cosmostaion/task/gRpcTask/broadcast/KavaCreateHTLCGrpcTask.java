package wannabit.io.cosmostaion.task.gRpcTask.broadcast;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;

import cosmos.auth.v1beta1.QueryGrpc;
import cosmos.auth.v1beta1.QueryOuterClass;
import cosmos.tx.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_BNB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_BTCB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_BUSD_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_XRPB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_GEN_TX_KAVA_CREATE_HTLC;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_XRPB;

public class KavaCreateHTLCGrpcTask extends CommonTask {

    private Account                 mAccount;
    private BaseChain               mBaseChain;
    private String                  mFrom, mTo;
    private ArrayList<Coin>         mSendTo = new ArrayList<>();
    private long                    mTimeStamp;
    private String                  mRandomNumberHash;
    private String                  mMemo;
    private Fee                     mFees;
    private String                  mChainId;

    private String                  mRandomNumber;
    private String                  mExpectedSwapId;


    private QueryOuterClass.QueryAccountResponse mAuthResponse;
    private ECKey ecKey;

    public KavaCreateHTLCGrpcTask(BaseApplication app, TaskListener listener, Account account, BaseChain basechain, String from, String to,
                                  ArrayList<Coin> sendTo, Fee fee, String chainId) {
        super(app, listener);
        this.mAccount = account;
        this.mBaseChain = basechain;
        this.mFrom = from;
        this.mTo = to;
        this.mSendTo = sendTo;
        this.mTimeStamp = Calendar.getInstance().getTimeInMillis() / 1000;
        this.mRandomNumberHash = "";
        this.mMemo = mApp.getString(R.string.str_create_swap_memo_c);
        this.mFees = fee;
        this.mChainId = chainId;
        this.mResult.taskType = TASK_GRPC_GEN_TX_KAVA_CREATE_HTLC;

    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mAccount.fromMnemonic) {
                String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
                DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(getChain(mAccount.baseChain), entropy, Integer.parseInt(mAccount.path), mAccount.newBip44, mAccount.customPath);
                ecKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
            } else {
                String privateKey = CryptoHelper.doDecryptData(mApp.getString(R.string.key_private) + mAccount.uuid, mAccount.resource, mAccount.spec);
                ecKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
            }

            QueryGrpc.QueryBlockingStub authStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            QueryOuterClass.QueryAccountRequest request = QueryOuterClass.QueryAccountRequest.newBuilder().setAddress(mAccount.address).build();
            mAuthResponse = authStub.account(request);

            byte[] randomNumber  = RandomUtils.nextBytes(32);
            mRandomNumber = WUtil.ByteArrayToHexString(randomNumber).toUpperCase();


//            if (mSendTo.get(0).denom.equals(TOKEN_HTLC_KAVA_BNB)) {
//                mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, BINANCE_MAIN_BNB_DEPUTY, mFrom).toUpperCase();
//
//            } else if (mSendTo.get(0).denom.equals(TOKEN_HTLC_KAVA_BTCB)) {
//                mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, BINANCE_MAIN_BTCB_DEPUTY, mFrom).toUpperCase();
//
//            } else if (mSendTo.get(0).denom.equals(TOKEN_HTLC_KAVA_XRPB)) {
//                mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, BINANCE_MAIN_XRPB_DEPUTY, mFrom).toUpperCase();
//
//            } else if (mSendTo.get(0).denom.equals(TOKEN_HTLC_KAVA_BUSD)) {
//                mExpectedSwapId = WKey.getSwapId(mRandomNumberHash, BINANCE_MAIN_BUSD_DEPUTY, mFrom).toUpperCase();
//
//            }

            //broadCast
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcKavaCreateHTLCSwapReq(mAuthResponse, mFrom, mTo, mSendTo, mTimeStamp, mRandomNumberHash, mFees, mMemo, ecKey, mChainId);
            ServiceOuterClass.BroadcastTxResponse response = txService.broadcastTx(broadcastTxRequest);
            mResult.resultData = response.getTxResponse().getTxhash();
            if (response.getTxResponse().getCode() > 0) {
                mResult.errorCode = response.getTxResponse().getCode();
                mResult.errorMsg = response.getTxResponse().getRawLog();
                mResult.isSuccess = false;
            } else {
                mResult.isSuccess = true;
                mResult.resultData2 = mExpectedSwapId;
                mResult.resultData3 = mRandomNumber;
            }

        } catch (Exception e) {
            WLog.e( "KavaCreateHTLCGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }
}

