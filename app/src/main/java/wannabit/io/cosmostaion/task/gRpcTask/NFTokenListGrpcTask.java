package wannabit.io.cosmostaion.task.gRpcTask;

import com.google.protobuf.ByteString;

import java.util.ArrayList;

import cosmos.base.query.v1beta1.Pagination;
import irismod.nft.Nft;
import irismod.nft.QueryGrpc;
import irismod.nft.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_NFTOKEN_LIST;

public class NFTokenListGrpcTask extends CommonTask {
    private BaseChain   mChain;
    private Account     mAccount;
    private ByteString  mPageKey;

    private ArrayList<Nft.IDCollection> mIrisResultData = new ArrayList<>();
    private QueryGrpc.QueryBlockingStub mIrisStub;

    private ArrayList<chainmain.nft.v1.Nft.IDCollection> mCryptoResultData = new ArrayList<>();
    private chainmain.nft.v1.QueryGrpc.QueryBlockingStub mCryptoStub;

    public NFTokenListGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, Account account, ByteString pageKey) {
        super(app, listener);
        this.mChain = chain;
        this.mAccount = account;
        this.mPageKey = pageKey;
        this.mResult.taskType = TASK_GRPC_FETCH_NFTOKEN_LIST;
        this.mIrisStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
        this.mCryptoStub = chainmain.nft.v1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Pagination.PageRequest pageRequest = null;
            if (mPageKey != null) {
                pageRequest = Pagination.PageRequest.newBuilder().setCountTotal(true).setLimit(100).setKey(mPageKey).build();
            } else {
                pageRequest = Pagination.PageRequest.newBuilder().setCountTotal(true).setLimit(100).build();
            }
            if (mChain.equals(IRIS_MAIN)) {
                QueryOuterClass.QueryOwnerRequest request = QueryOuterClass.QueryOwnerRequest.newBuilder().setOwner(mAccount.address).setPagination(pageRequest).build();
                QueryOuterClass.QueryOwnerResponse response = mIrisStub.owner(request);
                mIrisResultData.addAll(response.getOwner().getIdCollectionsList());

                mResult.isSuccess = true;
                mResult.resultData = mIrisResultData;
                if (mPageKey == null) {
                    mResult.resultData2 = String.valueOf(response.getPagination().getTotal());
                }
                mResult.resultByteData = response.getPagination().getNextKey();

            } else if (mChain.equals(CRYPTO_MAIN)) {
                chainmain.nft.v1.QueryOuterClass.QueryOwnerRequest request = chainmain.nft.v1.QueryOuterClass.QueryOwnerRequest.newBuilder().setOwner(mAccount.address).setPagination(pageRequest).build();
                chainmain.nft.v1.QueryOuterClass.QueryOwnerResponse response = mCryptoStub.owner(request);
                mCryptoResultData.addAll(response.getOwner().getIdCollectionsList());

                mResult.isSuccess = true;
                mResult.resultData = mCryptoResultData;
                if (mPageKey == null) {
                    mResult.resultData2 = String.valueOf(response.getPagination().getTotal());
                }
                mResult.resultByteData = response.getPagination().getNextKey();
            }

        } catch (Exception e) { WLog.e( "NFTokenListGrpcTask "+ e.getMessage()); }
        return mResult;
    }

}
