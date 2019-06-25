package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import org.bitcoinj.crypto.DeterministicKey;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Password;
import wannabit.io.cosmostaion.model.StdSignMsg;
import wannabit.io.cosmostaion.model.StdTx;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Pub_key;
import wannabit.io.cosmostaion.model.type.Signature;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class SimpleSendTask extends CommonTask {

    private Account         mAccount;
    private String          mToAddress;
    private ArrayList<Coin> mToSendAmount;
    private String          mToSendMemo;
    private Fee             mToFees;

    public SimpleSendTask(BaseApplication app, TaskListener listener, Account account,
                          String toAddress, ArrayList<Coin> toSendAmount, String toSendMemo,
                          Fee toFees) {
        super(app, listener);
        WLog.w("SimpleSendTask");
        this.mAccount           = account;
        this.mToAddress         = toAddress;
        this.mToSendAmount      = toSendAmount;
        this.mToSendMemo        = toSendMemo;
        this.mToFees            = toFees;
        this.mResult.taskType   = BaseConstant.TASK_GEN_TX_SIMPLE_SEND;
    }


    /**
     *
     * @param strings
     *  strings[0] : password
     *
     * @return
     */
    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Password checkPw = mApp.getBaseDao().onSelectPassword();
            if(!CryptoHelper.verifyData(strings[0], checkPw.resource, mApp.getString(R.string.key_password))) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_INVALID_PASSWORD;
                return mResult;
            }

            Response<ResLcdAccountInfo> accountResponse = ApiClient.getWannabitChain(mApp, BaseChain.getChain(mAccount.baseChain)).getAccountInfo(mAccount.address).execute();
            if(!accountResponse.isSuccessful()) {
                mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                return mResult;
            }
            mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromLcd(mAccount.id, accountResponse.body()));
            mApp.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromLcd(mAccount.id, accountResponse.body()));
            mAccount = mApp.getBaseDao().onSelectAccount(""+mAccount.id);


            String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
            DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(entropy, Integer.parseInt(mAccount.path));

            Msg singleSendMsg = MsgGenerator.genTransferMsg(mAccount.address, mToAddress, mToSendAmount, BaseChain.getChain(mAccount.baseChain));

            ArrayList<Msg> msgs= new ArrayList<>();
            msgs.add(singleSendMsg);

            StdSignMsg tosign = MsgGenerator.genToSignMsgWithType(
                    mAccount.baseChain,
                    ""+mAccount.accountNumber,
                    ""+mAccount.sequenceNumber,
                    msgs,
                    mToFees,
                    mToSendMemo);

            String signatureTx = MsgGenerator.getSignature(deterministicKey, tosign.getToSignByte());

            // build Signature object
            Signature signature = new Signature();
            Pub_key pubKey = new Pub_key();
            pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
            pubKey.value = WKey.getPubKeyValue(deterministicKey);
            signature.pub_key = pubKey;
            signature.signature = signatureTx;

            ArrayList<Signature> signatures = new ArrayList<>();
            signatures.add(signature);


            // build complete tx type
            StdTx signedTx = MsgGenerator.genSignedTransferTx(msgs, mToFees, mToSendMemo, signatures);
            ReqBroadCast reqBroadCast = new ReqBroadCast();
            reqBroadCast.returns = "sync";
            reqBroadCast.tx = signedTx.value;

            Response<ResBroadTx> response = ApiClient.getWannabitChain(mApp, BaseChain.getChain(mAccount.baseChain)).broadTx(reqBroadCast).execute();
            if(response.isSuccessful() && response.body() != null) {
                WLog.w("response.body() hash: " + response.body().txhash);
                if (response.body().txhash != null) {
                    mResult.resultData = response.body().txhash;
                }

                if(response.body().code != null) {
                    WLog.w("response.code() : " + response.body().code);
                    mResult.errorCode = response.body().code;
                    mResult.errorMsg = response.body().raw_log;
                    return mResult;
                }
                mResult.isSuccess = true;



            } else {
                WLog.w("SimpleSendTask not success!!");
                mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
            }


            /*
            signedTx.value.signatures = signatures;
            WLog.w("SimpleSendTask signed1 : " +  WUtil.getPresentor().toJson(signedTx));
//            WLog.w("SimpleSendTask signed2 : " +  WUtil.prettyPrinter(signedTx));

            String gentx = WUtil.str2Hex(WUtil.getPresentor().toJson(signedTx));
            WLog.w("SimpleSendTask gentx : " +  gentx);
            Response<ResBroadTx> response = ApiClient.getCSService(mApp, BaseChain.getChain(mAccount.baseChain)).broadcastTx(gentx).execute();
            if(response.isSuccessful() && response.body() != null) {
                WLog.w("SimpleSendTask result: " + response.body().hash + " " + response.body().isAllSuccess());
                mResult.resultData = response.body();
                mResult.isSuccess = true;
//                ResBroadTx result = response.body();
////                WLog.w("SimpleSendTask result errorMsg : " + result.errorMsg);
////                WLog.w("SimpleSendTask result errorCode : " + result.errorCode);
////                WLog.w("SimpleSendTask result hash : " + result.hash);
////                if(!TextUtils.isEmpty(result.hash) && result.errorCode == 0) {
////                    mResult.resultData = result.hash;
////                    mResult.isSuccess = true;
////                }
//                WLog.w("SimpleSendTask result hash : " + result.hash);
//                WLog.w("SimpleSendTask result height : " + response.body());
//                WLog.w("SimpleSendTask result height : " + response.body().toString());
            }
            */


        } catch (Exception e) {
            WLog.w("e : " + e.getMessage());
            if(BaseConstant.IS_SHOWLOG) e.printStackTrace();

        }
        return mResult;
    }
}
