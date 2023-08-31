package wannabit.io.cosmostaion.chain

import org.bitcoinj.core.ECKey
import org.bitcoinj.crypto.ChildNumber
import org.bitcoinj.crypto.DeterministicHierarchy
import org.bitcoinj.crypto.HDKeyDerivation
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.model.Balance
import wannabit.io.cosmostaion.database.model.Wallet
import java.math.BigInteger
import java.util.Date

class EthereumLine(chainName: String, imageUrl: String, bip44: String, val config: BaseChain.Ethereum) : Line(chainName, imageUrl, bip44) {
    override suspend fun loadBalances(wallet: Wallet) {
        wallet.seed?.let {
            val address = getAddress(it)
            val web3 = Web3j.build(HttpService(config.rpcUrl))
            val response = web3.ethGetBalance(address, DefaultBlockParameterName.LATEST).sendAsync().get()
            AppDatabase.getInstance().balanceDao().insert(Balance(wallet.id, chainName, config.displayDenom, response.balance.toString(), Date().time))
        }
    }

    override fun getAddress(seed: ByteArray): String {
        val masterKey = HDKeyDerivation.createMasterPrivateKey(seed)
        val targetKey = DeterministicHierarchy(masterKey).deriveChild(listOf(ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO), true, true, ChildNumber(0))
        val key = ECKey.fromPrivate(BigInteger(targetKey.privateKeyAsHex, 16))
        val credentials = Credentials.create(key.privateKeyAsHex)
        return credentials.address
    }
}