package wannabit.io.cosmostaion.ui.line.cosmos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cosmos.auth.v1beta1.AuthProto
import com.cosmos.auth.v1beta1.QueryGrpc
import com.cosmos.auth.v1beta1.QueryProto
import com.cosmos.bank.v1beta1.TxProto.MsgSend
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.crypto.secp256k1.KeysProto.PubKey
import com.cosmos.tx.signing.v1beta1.SigningProto
import com.cosmos.tx.v1beta1.ServiceGrpc
import com.cosmos.tx.v1beta1.ServiceProto
import com.cosmos.tx.v1beta1.TxProto
import com.google.protobuf.Any
import com.google.protobuf.ByteString
import io.grpc.ManagedChannelBuilder
import org.bitcoinj.core.ECKey
import org.bitcoinj.core.Sha256Hash
import org.bitcoinj.crypto.ChildNumber
import org.bitcoinj.crypto.DeterministicHierarchy
import org.bitcoinj.crypto.HDKeyDerivation
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.databinding.ActivityLineCosmosBinding
import wannabit.io.cosmostaion.ui.main.ApplicationViewModel
import java.math.BigInteger
import java.security.MessageDigest

class CosmosLineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLineCosmosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLineCosmosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        val chain = intent.getSerializableExtra("chain") as? CosmosLine
        val wallet = ApplicationViewModel.shared.currentWalletLiveData.value
        chain?.let { chain ->
            binding.chain.text = chain.chainName
            wallet?.seed?.let { binding.address.text = chain.getAddress(it) }
        } ?: run { finish() }
        binding.send.setOnClickListener {
            val channel = ManagedChannelBuilder.forAddress(chain!!.config.grpcUrl, 443).useTransportSecurity().build()
            val authStub = QueryGrpc.newBlockingStub(channel)
            val request = QueryProto.QueryAccountRequest.newBuilder().setAddress(chain.getAddress(wallet!!.seed!!)).build()
            val authResponse = authStub.account(request)
            val account = AuthProto.BaseAccount.parseFrom(authResponse.account.value)

            val toSendCoin = CoinProto.Coin.newBuilder().setAmount("1").setDenom(chain.config.baseDenom).build()
            val msgSend = MsgSend.newBuilder().setFromAddress(account.address).setToAddress(account.address).addAmount(toSendCoin).build()
            val msgList = listOf(Any.newBuilder().setTypeUrl("/cosmos.bank.v1beta1.MsgSend").setValue(msgSend.toByteString()).build())

            val txBuilder = TxProto.TxBody.newBuilder()
            msgList.forEach { txBuilder.addMessages(it) }
            val txBody = txBuilder.setMemo("").build()

            val masterKey = HDKeyDerivation.createMasterPrivateKey(wallet.seed!!)
            val targetKey =
                DeterministicHierarchy(masterKey).deriveChild(listOf(ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO), true, true, ChildNumber(0))
            val key = ECKey.fromPrivate(BigInteger(targetKey.privateKeyAsHex, 16))
            val cosmosPubKey = PubKey.newBuilder().setKey(ByteString.copyFrom(key.pubKey)).build()
            val pubKey = Any.newBuilder().setTypeUrl("/cosmos.crypto.secp256k1.PubKey").setValue(cosmosPubKey.toByteString()).build()
            val singleMode = TxProto.ModeInfo.Single.newBuilder().setMode(SigningProto.SignMode.SIGN_MODE_DIRECT).build()
            val modeInfo = TxProto.ModeInfo.newBuilder().setSingle(singleMode).build()
            val signerInfo = TxProto.SignerInfo.newBuilder().setPublicKey(pubKey).setModeInfo(modeInfo).setSequence(account.accountNumber).build()

            val feeCoin = CoinProto.Coin.newBuilder().setAmount("fee").setDenom("denom").build()
            val txFee = TxProto.Fee.newBuilder().addAmount(feeCoin).setGasLimit(1000).build()
            val authInfo = TxProto.AuthInfo.newBuilder().setFee(txFee).addSignerInfos(signerInfo).build()

            val signDoc =
                TxProto.SignDoc.newBuilder().setBodyBytes(txBody.toByteString()).setAuthInfoBytes(authInfo.toByteString()).setChainId(chain.config.chainId).setAccountNumber(account.accountNumber)
                    .build()

            val digest: MessageDigest = Sha256Hash.newDigest()
            val toSignHash = digest.digest(signDoc.toByteArray())
            val signature = key.sign(Sha256Hash.wrap(toSignHash))
            val sigData = ByteArray(64)
            System.arraycopy(ByteUtils.integerToBytes(signature.r, 32), 0, sigData, 0, 32)
            System.arraycopy(ByteUtils.integerToBytes(signature.s, 32), 0, sigData, 32, 32)
            val rawTx = TxProto.TxRaw.newBuilder().setBodyBytes(txBody.toByteString()).setAuthInfoBytes(authInfo.toByteString()).addSignatures(ByteString.copyFrom(sigData)).build()
            val broadcastTxRequest = ServiceProto.BroadcastTxRequest.newBuilder().setModeValue(ServiceProto.BroadcastMode.BROADCAST_MODE_SYNC.number).setTxBytes(rawTx.toByteString()).build()
            val stub = ServiceGrpc.newBlockingStub(channel)
            val response = stub.broadcastTx(broadcastTxRequest)
        }
    }
}