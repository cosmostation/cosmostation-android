package wannabit.io.cosmostaion.task.gRpcTask;

import org.bitcoinj.core.ECKey;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.tx.Contract;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

public class Erc20BalanceGrpcTask extends CommonTask {

    private Account     mAccount;
    private Web3j       mWeb3j;
    private String      mContAddress;

    public Erc20BalanceGrpcTask(BaseApplication app, TaskListener listener, Account account, Web3j web3j, String contAddress) {
        super(app, listener);
        this.mAccount = account;
        this.mWeb3j = web3j;
        this.mContAddress = contAddress;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ECKey ecKey = WKey.getECKey(mApp, mAccount);
            String ethAddress = WKey.generateEthAddressFromPrivateKey(ecKey.getPrivateKeyAsHex());
            List<Type> params = new ArrayList<>();
            params.add(new Address(ethAddress));

            List<TypeReference<?>> returnTypes = Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {});
            Function function = new Function("balanceOf", params, returnTypes);

            String txData = FunctionEncoder.encode(function);
            org.web3j.protocol.core.methods.response.EthCall response = mWeb3j.ethCall(Transaction.createEthCallTransaction(ethAddress, mContAddress, txData), DefaultBlockParameterName.LATEST).sendAsync().get();
            List<Type> results = FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());
            BigInteger balance = (BigInteger)results.get(0).getValue();

            mApp.getBaseDao().setMyTokenBalance(mContAddress, balance.toString());

        } catch (Exception e) {
            WLog.e("Erc20BalanceGrpcTask " + e.getMessage());
        }
        return mResult;

    }
}
