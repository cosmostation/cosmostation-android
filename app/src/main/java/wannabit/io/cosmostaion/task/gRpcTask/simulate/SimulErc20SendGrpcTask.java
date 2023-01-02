package wannabit.io.cosmostaion.task.gRpcTask.simulate;

import org.bitcoinj.core.ECKey;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.MintscanToken;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

public class SimulErc20SendGrpcTask extends CommonTask {
    private Account         mAccount;
    private BaseChain       mBaseChain;
    private MintscanToken   mMintscanToken;
    private String          mToAddress;
    private ArrayList<Coin> mAmounts;

    public SimulErc20SendGrpcTask(BaseApplication app, TaskListener listener, Account mAccount, BaseChain basechain, MintscanToken mintscanToken, String toAddress, ArrayList<Coin> amounts) {
        super(app, listener);
        this.mAccount = mAccount;
        this.mBaseChain = basechain;
        this.mMintscanToken = mintscanToken;
        this.mToAddress = toAddress;
        this.mAmounts = amounts;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ECKey ecKey = WKey.getECKey(mApp, mAccount);
            ChainConfig chainConfig = ChainFactory.getChain(mBaseChain);
            String url = chainConfig.rpcUrl();
            String fromEthAddress = "";
            String toEthAddress = "";

            if (!url.isEmpty()) {
                if (chainConfig.baseChain().equals(BaseChain.OKEX_MAIN)) {
                    fromEthAddress = mAccount.address;
                    toEthAddress = mToAddress;
                } else {
                    fromEthAddress = WKey.generateEthAddressFromPrivateKey(ecKey.getPrivateKeyAsHex());
                    toEthAddress = WKey.convertBech32ToEvm(mToAddress);
                }
                BigInteger value = new BigInteger(mAmounts.get(0).amount);
                
                Web3j web3 = Web3j.build(new HttpService(url));
                Credentials credentials = Credentials.create(ecKey.getPrivateKeyAsHex());
                EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
                BigInteger nonce = ethGetTransactionCount.getTransactionCount();

                List<Type> params = new ArrayList<>();
                params.add(new Address(toEthAddress));
                params.add(new Uint256(value));

                List<TypeReference<?>> returnTypes = Collections.emptyList();

                Function function = new Function(
                        "transfer",
                        params,
                        returnTypes
                );
                String txData = FunctionEncoder.encode(function);

                long chainID = web3.ethChainId().send().getChainId().longValue();
                Transaction transaction = new Transaction(fromEthAddress, nonce, BigInteger.ZERO, BigInteger.ZERO, toEthAddress, BigInteger.ZERO, txData);
                RawTransaction rawTransaction;
                if (BaseChain.isGRPC(chainConfig.baseChain())) {
                    rawTransaction = RawTransaction.createTransaction(
                            chainID,
                            nonce,
                            BigInteger.valueOf(900000L),
                            mMintscanToken.address,
                            BigInteger.ZERO,
                            transaction.getData(),
                            BigInteger.valueOf(500000000L),
                            BigInteger.valueOf(27500000000L)
                    );

                } else {
                    rawTransaction = RawTransaction.createTransaction(
                            nonce,
                            web3.ethGasPrice().send().getGasPrice(),
                            BigInteger.valueOf(900000L),
                            mMintscanToken.address,
                            BigInteger.ZERO,
                            transaction.getData()
                    );
                }

                byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, chainID, credentials);
                String hexValue = Numeric.toHexString(signedMessage);
                BigDecimal newGasLimit = new BigDecimal(web3.ethEstimateGas(transaction).send().getAmountUsed().toString()).multiply(new BigDecimal("1.1"));
                BigDecimal gasPrice = new BigDecimal(web3.ethGasPrice().send().getGasPrice().toString());
                BigDecimal availAmount;

                if (BaseChain.isGRPC(chainConfig.baseChain())) availAmount = mApp.getBaseDao().getAvailable(chainConfig.mainDenom());
                else availAmount = mApp.getBaseDao().availableAmount(chainConfig.mainDenom()).movePointRight(WDp.mainDisplayDecimal(chainConfig.baseChain()));
                if (newGasLimit.multiply(gasPrice).compareTo(availAmount) < 0) {
                    mResult.isSuccess = true;
                    mResult.resultData = newGasLimit.toPlainString();
                    mResult.resultData2 = gasPrice.toPlainString();
                    mResult.resultData3 = hexValue;
                } else {
                    mResult.isSuccess = false;
                    mResult.errorMsg = mApp.getString(R.string.error_not_enough_fee);
                }
            }

        } catch (Exception e) {
            WLog.e( "SimulErc20SendGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
            mResult.errorMsg = e.getMessage();
        }
        return mResult;
    }
}
