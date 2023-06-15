package wannabit.io.cosmostaion.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cosmos.bank.v1beta1.QueryGrpc
import com.cosmos.bank.v1beta1.QueryProto
import com.cosmos.base.query.v1beta1.PaginationProto
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.i2p.crypto.eddsa.Utils
import org.bitcoinj.core.Bech32
import org.bitcoinj.core.ECKey
import org.bitcoinj.crypto.ChildNumber
import org.bitcoinj.crypto.DeterministicHierarchy
import org.bitcoinj.crypto.HDKeyDerivation
import org.bitcoinj.crypto.MnemonicCode
import org.bouncycastle.crypto.digests.RIPEMD160Digest
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.CryptoHelper
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.Chain
import wannabit.io.cosmostaion.database.model.ChainConfig
import wannabit.io.cosmostaion.network.MintscanService
import wannabit.io.cosmostaion.network.model.Price
import java.io.ByteArrayOutputStream
import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

class ApplicationViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        val shared
            get() = CosmostationApp.instance.applicationViewModel
    }

    var pricesLiveData = MutableLiveData<List<Price>>()

    fun loadPrices() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val result = MintscanService.create().price()
            pricesLiveData.postValue(result)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadBalances() = CoroutineScope(Dispatchers.IO).launch {
        val wallet = AppDatabase.getInstance().walletDao().selectById(Prefs.lastUserId)!!
        val entropy = CryptoHelper.doDecryptData(CosmostationConstants.ENCRYPT_MNEMONIC_KEY + wallet.uuid, wallet.resource, wallet.spec)
        val seed = MnemonicCode.toSeed(MnemonicCode.INSTANCE.toMnemonic(Utils.hexToBytes(entropy)), "")
        val masterKey = HDKeyDerivation.createMasterPrivateKey(seed)

        Chain.allChains().forEach {
            when (it.chainConfig) {
                is ChainConfig.Cosmos -> {
                    val targetKey =
                        DeterministicHierarchy(masterKey).deriveChild(listOf(ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO), true, true, ChildNumber(0))
                    val key = ECKey.fromPrivate(BigInteger(targetKey.privateKeyAsHex, 16))
                    val digest = MessageDigest.getInstance("SHA-256")
                    val hash = digest.digest(key.pubKey)
                    val rDigest = RIPEMD160Digest()
                    rDigest.update(hash, 0, hash.size)
                    val digestResult = ByteArray(rDigest.digestSize)
                    rDigest.doFinal(digestResult, 0)
                    val bitConvertedHash = convertBits(digestResult, 8, 5, true)
                    val channel = ManagedChannelBuilder.forAddress(it.chainConfig.grpcUrl, 443).useTransportSecurity().build()
                    val stub = QueryGrpc.newBlockingStub(channel).withDeadlineAfter(5, TimeUnit.SECONDS)
                    val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(2000).build()
                    val address = Bech32.encode(Bech32.Encoding.BECH32, "cosmos", bitConvertedHash)
                    val request = QueryProto.QueryAllBalancesRequest.newBuilder().setPagination(pageRequest).setAddress(address).build()
                    val response = stub.allBalances(request)
                    print(response)
                }

                is ChainConfig.Ethereum -> {
                    val web3 = Web3j.build(HttpService("https://rpc.flashbots.net"))
                    val targetKey =
                        DeterministicHierarchy(masterKey).deriveChild(listOf(ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO), true, true, ChildNumber(0))
                    val key = ECKey.fromPrivate(BigInteger(targetKey.privateKeyAsHex, 16))
                    val credentials = Credentials.create(key.privateKeyAsHex)
                    val response = web3.ethGetBalance(credentials.address, DefaultBlockParameterName.LATEST).sendAsync().get()
                    print(response)
                }

                else -> {}
            }
        }
    }

    fun convertBits(data: ByteArray, fromBits: Int, toBits: Int, pad: Boolean): ByteArray {
        var acc = 0
        var bits = 0
        val baos = ByteArrayOutputStream()
        val maxv = (1 shl toBits) - 1
        for (i in data.indices) {
            val value = data[i].toInt() and 0xff
            if (value ushr fromBits != 0) {
                throw Exception("invalid data range: data[$i]=$value (fromBits=$fromBits)")
            }
            acc = (acc shl fromBits) or value
            bits += fromBits
            while (bits >= toBits) {
                bits -= toBits
                baos.write((acc ushr bits) and maxv)
            }
        }
        if (pad) {
            if (bits > 0) {
                baos.write((acc shl (toBits - bits)) and maxv)
            }
        } else if (bits >= fromBits) {
            throw Exception("illegal zero padding")
        } else if ((acc shl (toBits - bits)) and maxv != 0) {
            throw Exception("non-zero padding")
        }
        return baos.toByteArray()
    }
}