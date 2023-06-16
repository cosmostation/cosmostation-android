package wannabit.io.cosmostaion.chain

import com.cosmos.bank.v1beta1.QueryGrpc
import com.cosmos.bank.v1beta1.QueryProto
import com.cosmos.base.query.v1beta1.PaginationProto
import io.grpc.ManagedChannelBuilder
import org.bitcoinj.core.Bech32
import org.bitcoinj.core.ECKey
import org.bitcoinj.crypto.ChildNumber
import org.bitcoinj.crypto.DeterministicHierarchy
import org.bitcoinj.crypto.HDKeyDerivation
import org.bouncycastle.crypto.digests.RIPEMD160Digest
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.model.Balance
import wannabit.io.cosmostaion.database.model.Wallet
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Date
import java.util.concurrent.TimeUnit

class CosmosLine(chainName: String, imageUrl: String, bip44: String, val config: ChainConfig.Cosmos) : Line(chainName, imageUrl, bip44) {
    override suspend fun loadBalances(wallet: Wallet) {
        val channel = ManagedChannelBuilder.forAddress(config.grpcUrl, 443).useTransportSecurity().build()
        val stub = QueryGrpc.newBlockingStub(channel).withDeadlineAfter(5, TimeUnit.SECONDS)
        val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(2000).build()
        wallet.seed?.let {
            val request = QueryProto.QueryAllBalancesRequest.newBuilder().setPagination(pageRequest).setAddress(getAddress(it)).build()
            val response = stub.allBalances(request)
            response.balancesList.forEach {
                AppDatabase.getInstance().balanceDao().insert(Balance(wallet.id, config.chainName, it.denom, it.amount, Date().time))
            }
        }
    }

    override fun getAddress(seed: ByteArray): String {
        val masterKey = HDKeyDerivation.createMasterPrivateKey(seed)
        val targetKey = DeterministicHierarchy(masterKey).deriveChild(listOf(ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO), true, true, ChildNumber(0))
        val key = ECKey.fromPrivate(BigInteger(targetKey.privateKeyAsHex, 16))
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(key.pubKey)
        val rDigest = RIPEMD160Digest()
        rDigest.update(hash, 0, hash.size)
        val digestResult = ByteArray(rDigest.digestSize)
        rDigest.doFinal(digestResult, 0)
        val bitConvertedHash = ByteUtils.convertBits(digestResult, 8, 5, true)
        return Bech32.encode(Bech32.Encoding.BECH32, config.bech32Prefix, bitConvertedHash)
    }
}