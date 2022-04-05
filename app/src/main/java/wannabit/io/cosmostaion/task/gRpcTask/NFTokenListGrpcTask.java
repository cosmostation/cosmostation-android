package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_NFTOKEN_LIST;

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

public class NFTokenListGrpcTask extends CommonTask {
    private final BaseChain mChain;
    private final Account mAccount;
    private final ByteString mPageKey;

    private final ArrayList<Nft.IDCollection> mIrisResultData = new ArrayList<>();
    private final QueryGrpc.QueryBlockingStub mIrisStub;

    private final ArrayList<chainmain.nft.v1.Nft.IDCollection> mCryptoResultData = new ArrayList<>();
    private final chainmain.nft.v1.QueryGrpc.QueryBlockingStub mCryptoStub;

    public NFTokenListGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, Account account, ByteString pageKey) {
        super(app, listener);
        this.mChain = chain;
        this.mAccount = account;
        this.mPageKey = pageKey;
        this.result.taskType = TASK_GRPC_FETCH_NFTOKEN_LIST;
        this.mIrisStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
        this.mCryptoStub = chainmain.nft.v1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Pagination.PageRequest pageRequest = null;
            if (mPageKey != null) {
                pageRequest = Pagination.PageRequest.newBuilder().setCountTotal(true).setLimit(1000).setKey(mPageKey).build();
            } else {
                pageRequest = Pagination.PageRequest.newBuilder().setCountTotal(true).setLimit(1000).build();
            }
            if (mChain.equals(IRIS_MAIN)) {
                QueryOuterClass.QueryOwnerRequest request = QueryOuterClass.QueryOwnerRequest.newBuilder().setOwner(mAccount.address).setPagination(pageRequest).build();
                QueryOuterClass.QueryOwnerResponse response = mIrisStub.owner(request);
                mIrisResultData.addAll(response.getOwner().getIdCollectionsList());

                result.isSuccess = true;
                result.resultData = mIrisResultData;
                result.resultByteData = response.getPagination().getNextKey();

            } else if (mChain.equals(CRYPTO_MAIN)) {
                chainmain.nft.v1.QueryOuterClass.QueryOwnerRequest request = chainmain.nft.v1.QueryOuterClass.QueryOwnerRequest.newBuilder().setOwner(mAccount.address).setPagination(pageRequest).build();
                chainmain.nft.v1.QueryOuterClass.QueryOwnerResponse response = mCryptoStub.owner(request);
                mCryptoResultData.addAll(response.getOwner().getIdCollectionsList());

                result.isSuccess = true;
                result.resultData = mCryptoResultData;
                result.resultByteData = response.getPagination().getNextKey();
            }

        } catch (Exception e) {
            WLog.e("NFTokenListGrpcTask " + e.getMessage());
        }
        return result;
    }

}
