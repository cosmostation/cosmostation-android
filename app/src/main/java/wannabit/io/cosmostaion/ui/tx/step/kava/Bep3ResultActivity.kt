package wannabit.io.cosmostaion.ui.tx.step.kava

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.binance.dex.api.client.BinanceDexApiClientFactory
import com.binance.dex.api.client.BinanceDexEnvironment
import com.binance.dex.api.client.Wallet
import com.binance.dex.api.client.domain.broadcast.HtltReq
import com.binance.dex.api.client.domain.broadcast.TransactionOption
import com.binance.dex.api.client.encoding.message.Token
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.tx.v1beta1.TxProto.Fee
import com.kava.bep3.v1beta1.TxProto
import com.kava.bep3.v1beta1.TxProto.MsgClaimAtomicSwap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.lang3.ArrayUtils
import org.apache.commons.lang3.RandomUtils
import org.bitcoinj.core.Bech32
import org.bitcoinj.core.ECKey
import org.bitcoinj.core.Sha256Hash
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava459
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.BaseConstant.BASE_GAS_AMOUNT
import wannabit.io.cosmostaion.common.BaseConstant.BINANCE_MAIN_BNB_DEPUTY
import wannabit.io.cosmostaion.common.BaseConstant.BINANCE_MAIN_BTCB_DEPUTY
import wannabit.io.cosmostaion.common.BaseConstant.BINANCE_MAIN_BUSD_DEPUTY
import wannabit.io.cosmostaion.common.BaseConstant.BINANCE_MAIN_XRPB_DEPUTY
import wannabit.io.cosmostaion.common.BaseConstant.KAVA_MAIN_BNB_DEPUTY
import wannabit.io.cosmostaion.common.BaseConstant.KAVA_MAIN_BTCB_DEPUTY
import wannabit.io.cosmostaion.common.BaseConstant.KAVA_MAIN_BUSD_DEPUTY
import wannabit.io.cosmostaion.common.BaseConstant.KAVA_MAIN_XRPB_DEPUTY
import wannabit.io.cosmostaion.common.BaseConstant.TOKEN_HTLC_BINANCE_BNB
import wannabit.io.cosmostaion.common.BaseConstant.TOKEN_HTLC_BINANCE_BTCB
import wannabit.io.cosmostaion.common.BaseConstant.TOKEN_HTLC_BINANCE_BUSD
import wannabit.io.cosmostaion.common.BaseConstant.TOKEN_HTLC_BINANCE_XRPB
import wannabit.io.cosmostaion.common.BaseConstant.TOKEN_HTLC_KAVA_BNB
import wannabit.io.cosmostaion.common.BaseConstant.TOKEN_HTLC_KAVA_BTCB
import wannabit.io.cosmostaion.common.BaseConstant.TOKEN_HTLC_KAVA_BUSD
import wannabit.io.cosmostaion.common.BaseConstant.TOKEN_HTLC_KAVA_XRPB
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.ByteUtils.hexStringToByteArray
import wannabit.io.cosmostaion.common.CosmostationConstants.SWAP_MEMO_CLAIM
import wannabit.io.cosmostaion.common.CosmostationConstants.SWAP_MEMO_CREATE
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.historyToMintscan
import wannabit.io.cosmostaion.common.toHex
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.AccountResponse
import wannabit.io.cosmostaion.data.repository.chain.KavaRepositoryImpl
import wannabit.io.cosmostaion.data.repository.tx.TxRepositoryImpl
import wannabit.io.cosmostaion.databinding.ActivityBep3ResultBinding
import wannabit.io.cosmostaion.databinding.DialogErrorBinding
import wannabit.io.cosmostaion.databinding.DialogWaitBinding
import wannabit.io.cosmostaion.ui.main.MainActivity
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModelProviderFactory
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModel
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModelProviderFactory
import java.math.BigDecimal
import java.nio.charset.Charset
import java.util.Locale

class Bep3ResultActivity : BaseActivity() {

    private lateinit var binding: ActivityBep3ResultBinding

    private lateinit var kavaViewModel: KavaViewModel
    private lateinit var txViewModel: TxViewModel

    private var fromChain: CosmosLine? = null
    private var toChain: CosmosLine? = null
    private var recipientAddress: String? = ""
    private var toSendDenom: String? = ""
    private var toSendAmount: BigDecimal? = BigDecimal.ZERO

    private var randomNumber: String? = ""
    private var expectedSwapId: String? = ""
    private var createTxHash: String? = ""
    private var claimTxHash: String? = ""

    private var swapFetchCnt = 0
    private var claimFetchCnt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBep3ResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initView()
        setUpBroadcast()
        clickAction()
    }

    private fun initViewModel() {
        val txRepository = TxRepositoryImpl()
        val txViewModelProviderFactory = TxViewModelProviderFactory(txRepository)
        txViewModel = ViewModelProvider(
            this,
            txViewModelProviderFactory
        )[TxViewModel::class.java]

        val kavaRepository = KavaRepositoryImpl()
        val kavaViewModelProviderFactory = KavaViewModelProviderFactory(kavaRepository)
        kavaViewModel = ViewModelProvider(
            this,
            kavaViewModelProviderFactory
        )[KavaViewModel::class.java]
    }

    private fun initView() {
        binding.apply {
            loadingMsg.text = getString(R.string.str_htls_msg0)

            fromChain = BaseData.baseAccount?.displayCosmosLineChains?.firstOrNull {
                it.tag == intent.getStringExtra("fromChain")
            }
            toChain = BaseData.baseAccount?.displayCosmosLineChains?.firstOrNull {
                it.tag == intent.getStringExtra("toChain")
            }
            recipientAddress = intent.getStringExtra("toAddress")
            toSendDenom = intent.getStringExtra("toSendDenom")
            toSendAmount = intent.getStringExtra("toSendAmount")?.toBigDecimal()

            if (fromChain is ChainBinanceBeacon) {
                bToKCreateSend()

            } else {
                txViewModel.broadCreateSwap(
                    getChannel(ChainKava459()),
                    fromChain?.address,
                    kToBCreateSend(),
                    txFee(),
                    SWAP_MEMO_CREATE,
                    fromChain
                )
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startMainActivity()
    }

    private fun updateView() {
        binding.apply {
            loading.visibility = View.GONE
            loadingMsg.visibility = View.GONE
            successLayout.visibility = View.VISIBLE
            btnConfirm.updateButtonView(true)

            fromChain?.let { checkSendImg.setImageResource(it.logo) }
            toChain?.let { checkClaimImg.setImageResource(it.logo) }
        }
    }

    private fun updateProgress(progress: Int) {
        binding.apply {
            when (progress) {
                1 -> { loadingMsg.text = getString(R.string.str_htls_msg1) }
                2 -> { loadingMsg.text = getString(R.string.str_htls_msg2) }
                3 -> { loadingMsg.text = getString(R.string.str_htls_msg3) }
            }
        }
    }

    private fun kToBCreateSend(): TxProto.MsgCreateAtomicSwap? {
        val timeStamp = System.currentTimeMillis() / 1000
        val randomBytes = RandomUtils.nextBytes(32)
        val originData = ArrayUtils.addAll(randomBytes, *ByteUtils.longToBytes(timeStamp))

        randomNumber = randomBytes.toHex().uppercase()
        val randomNumberHash = hexStringToByteArray(Sha256Hash.hash(originData).toHex().uppercase())
        expectedSwapId = expectedSwapId(fromChain, toSendDenom, randomNumberHash)

        return TxProto.MsgCreateAtomicSwap.newBuilder()
            .setFrom(fromChain?.address)
            .setTo(duputyKavaAddress(toSendDenom))
            .setSenderOtherChain(duputyBnbAddress(toSendDenom))
            .setRecipientOtherChain(recipientAddress)
            .setRandomNumberHash(Sha256Hash.hash(originData).toHex().uppercase())
            .setTimestamp(timeStamp)
            .addAmount(CoinProto.Coin.newBuilder().setDenom(toSendDenom).setAmount(toSendAmount.toString()).build())
            .setHeightSpan(24686)
            .build()
    }

    private fun kToBClaimSend(account: AccountResponse) {
        CoroutineScope(Dispatchers.IO).launch {
            ECKey.fromPrivate(toChain?.privateKey)?.let {
                val wallet = Wallet(it.privateKeyAsHex, BinanceDexEnvironment.PROD)
                wallet.accountNumber = account.accountNumber
                wallet.sequence = account.sequence.toLong()

                val client = BinanceDexApiClientFactory.newInstance()
                    .newRestClient(BinanceDexEnvironment.PROD.baseUrl)
                val options =
                    TransactionOption(SWAP_MEMO_CLAIM, 82, null)
                val resp = client.claimHtlt(
                    expectedSwapId?.lowercase(),
                    hexStringToByteArray(randomNumber!!),
                    wallet,
                    options,
                    true
                )

                withContext(Dispatchers.Main) {
                    updateProgress(3)
                    if (resp[0].isOk) {
                        updateView()
                        claimTxHash = resp[0].hash
                    } else {
                        if (claimFetchCnt < 20) {
                            Handler(Looper.getMainLooper()).postDelayed({
                                claimFetchCnt++
                                CoroutineScope(Dispatchers.IO).launch {
                                    kToBClaimSend(account)
                                }
                            }, 6000)

                        } else {
                            showError(resp[0].log)
                        }
                    }
                }
            }
        }
    }

    private fun bToKCreateSend() {
        ECKey.fromPrivate(fromChain?.privateKey)?.let {
            val wallet = Wallet(it.privateKeyAsHex, BinanceDexEnvironment.PROD)
            fromChain?.lcdAccountInfo?.let { account ->
                wallet.accountNumber = account.accountNumber
                wallet.sequence = account.sequence.toLong()
            }

            val timeStamp = System.currentTimeMillis() / 1000
            val randomBytes = RandomUtils.nextBytes(32)
            val originData = ArrayUtils.addAll(randomBytes, *ByteUtils.longToBytes(timeStamp))

            val htltReq = htltReqMsg(timeStamp, originData)
            randomNumber = randomBytes.toHex().uppercase()
            val randomNumberHash = htltReq.randomNumberHash
            expectedSwapId = expectedSwapId(fromChain, toSendDenom, randomNumberHash)

            val options =
                TransactionOption(SWAP_MEMO_CLAIM, 82, null)

            txViewModel.broadcastBnbCreateSwap(htltReq, wallet, options)
        }
    }

    private fun bToKClaimSend(): MsgClaimAtomicSwap? {
        return MsgClaimAtomicSwap.newBuilder().setFrom(toChain?.address).setSwapId(expectedSwapId).setRandomNumber(randomNumber).build()
    }

    private fun txFee(): Fee? {
        val feeCoin = CoinProto.Coin.newBuilder()
            .setDenom("ukava")
            .setAmount("12500")
            .build()

        return Fee.newBuilder()
            .setGasLimit(BASE_GAS_AMOUNT.toLong())
            .addAmount(feeCoin)
            .build()
    }

    private fun clickAction() {
        binding.apply {
            checkSendView.setOnClickListener {
                historyToMintscan(fromChain, createTxHash)
            }

            checkClaimView.setOnClickListener {
                historyToMintscan(toChain, claimTxHash)
            }

            btnConfirm.setOnClickListener {
                startMainActivity()
            }
        }
    }

    private fun setUpBroadcast() {
        txViewModel.broadCreateSwap.observe(this) { response ->
            if (response.code > 0) {
                showError(response.rawLog)
            } else {
                updateProgress(1)
                kavaViewModel.bep3SwapId(getChannel(ChainKava459()), expectedSwapId, toChain)
                createTxHash = response.txhash
            }
        }

        kavaViewModel.bnbSwapIdResult.observe(this) { response ->
            if (response != null && response.swapId.isNotEmpty()) {
                updateProgress(2)
                kavaViewModel.accountInfo(recipientAddress)
            }
        }

        kavaViewModel.bep3SwapIdErrorMessage.observe(this) {
            if (swapFetchCnt < 15) {
                Handler(Looper.getMainLooper()).postDelayed({
                    swapFetchCnt++
                    kavaViewModel.bep3SwapId(getChannel(ChainKava459()), expectedSwapId, toChain)
                }, 6000)
            } else {
                showMoreWait()
            }
        }

        kavaViewModel.bep3AccountInfoResult.observe(this) { response ->
            if (response != null) {
                kToBClaimSend(response)
            }
        }

        txViewModel.broadBnbCreateSwap.observe(this) { response ->
            response?.let {
                if (it[0].isOk) {
                    updateProgress(1)
                    kavaViewModel.bep3SwapId(getChannel(ChainKava459()), expectedSwapId, toChain)
                    createTxHash = it[0].hash
                } else {
                    showError(it[0].log)
                }
            }
        }

        kavaViewModel.kavaSwapIdResult.observe(this) { response ->
            if (response != null && response.atomicSwap.id.isNotEmpty()) {
                txViewModel.broadClaimSwap(
                    getChannel(ChainKava459()),
                    toChain?.address,
                    bToKClaimSend(),
                    txFee(),
                    SWAP_MEMO_CREATE,
                    toChain
                )
            }
        }

        txViewModel.broadClaimSwap.observe(this) { response ->
            updateProgress(3)
            if (response.code > 0) {
                if (claimFetchCnt < 20) {
                    Handler().postDelayed({
                        claimFetchCnt++
                        CoroutineScope(Dispatchers.IO).launch {
                            txViewModel.broadClaimSwap(
                                getChannel(ChainKava459()),
                                toChain?.address,
                                bToKClaimSend(),
                                txFee(),
                                SWAP_MEMO_CREATE,
                                toChain
                            )
                        }
                    }, 6000)

                } else {
                    showError(response.rawLog)
                }
            } else {
                updateView()
                claimTxHash = response.txhash
            }
        }
    }

    private fun swapId(randomNumberHash: ByteArray, sender: String?, otherChainSender: String): String {
        val s: ByteArray = ByteUtils.convertBits(Bech32.decode(sender).data, 5, 8, false)
        val rhs = ByteArray(randomNumberHash.size + s.size)
        System.arraycopy(randomNumberHash, 0, rhs, 0, randomNumberHash.size)
        System.arraycopy(s, 0, rhs, randomNumberHash.size, s.size)
        val o =
            otherChainSender.lowercase(Locale.getDefault()).toByteArray(Charset.forName("UTF-8"))
        val expectedSwapId = ByteArray(rhs.size + o.size)
        System.arraycopy(rhs, 0, expectedSwapId, 0, rhs.size)
        System.arraycopy(o, 0, expectedSwapId, rhs.size, o.size)

        val expectedSwapIdSha = Sha256Hash.hash(expectedSwapId)
        return expectedSwapIdSha.toHex()
    }

    private fun expectedSwapId(fromChain: CosmosLine?, toSendDenom: String?, randomNumberHash: ByteArray): String {
        if (fromChain is ChainKava459) {
            fromChain.address?.let { address ->
                when (toSendDenom) {
                    TOKEN_HTLC_KAVA_BNB -> {
                        return swapId(randomNumberHash, BINANCE_MAIN_BNB_DEPUTY, address)
                    }
                    TOKEN_HTLC_KAVA_BTCB -> {
                        return swapId(randomNumberHash, BINANCE_MAIN_BTCB_DEPUTY, address)
                    }
                    TOKEN_HTLC_KAVA_XRPB -> {
                        return swapId(randomNumberHash, BINANCE_MAIN_XRPB_DEPUTY, address)
                    }
                    TOKEN_HTLC_KAVA_BUSD -> {
                        return swapId(randomNumberHash, BINANCE_MAIN_BUSD_DEPUTY, address)
                    }
                    else -> ""
                }
            }

        } else {
            fromChain?.address?.let { address ->
                when (toSendDenom) {
                    TOKEN_HTLC_BINANCE_BNB -> {
                        return swapId(randomNumberHash, KAVA_MAIN_BNB_DEPUTY, address)
                    }
                    TOKEN_HTLC_BINANCE_BTCB -> {
                        return swapId(randomNumberHash, KAVA_MAIN_BTCB_DEPUTY, address)
                    }
                    TOKEN_HTLC_BINANCE_XRPB -> {
                        return swapId(randomNumberHash, KAVA_MAIN_XRPB_DEPUTY, address)
                    }
                    TOKEN_HTLC_BINANCE_BUSD -> {
                        return swapId(randomNumberHash, KAVA_MAIN_BUSD_DEPUTY, address)
                    }
                    else -> ""
                }
            }
        }
        return ""
    }

    private fun duputyKavaAddress(denom: String?): String {
        if (denom.equals(TOKEN_HTLC_KAVA_BNB, ignoreCase = true)) {
            return KAVA_MAIN_BNB_DEPUTY
        } else if (denom.equals(TOKEN_HTLC_KAVA_BTCB, ignoreCase = true)) {
            return KAVA_MAIN_BTCB_DEPUTY
        } else if (denom.equals(TOKEN_HTLC_KAVA_XRPB, ignoreCase = true)) {
            return KAVA_MAIN_XRPB_DEPUTY
        } else if (denom.equals(TOKEN_HTLC_KAVA_BUSD, ignoreCase = true)) {
            return KAVA_MAIN_BUSD_DEPUTY
        }
        return ""
    }

    private fun duputyBnbAddress(denom: String?): String {
        if (denom.equals(TOKEN_HTLC_KAVA_BNB, ignoreCase = true)) {
            return BINANCE_MAIN_BNB_DEPUTY
        } else if (denom.equals(TOKEN_HTLC_KAVA_BTCB, ignoreCase = true)) {
            return BINANCE_MAIN_BTCB_DEPUTY
        } else if (denom.equals(TOKEN_HTLC_KAVA_XRPB, ignoreCase = true)) {
            return BINANCE_MAIN_XRPB_DEPUTY
        } else if (denom.equals(TOKEN_HTLC_KAVA_BUSD, ignoreCase = true)) {
            return BINANCE_MAIN_BUSD_DEPUTY
        }
        return ""
    }

    private fun htltReqMsg(timeStamp: Long, originData: ByteArray): HtltReq {
        val htltReq = HtltReq()
        when (toSendDenom) {
            TOKEN_HTLC_BINANCE_BNB -> {
                htltReq.recipient = BINANCE_MAIN_BNB_DEPUTY
                htltReq.senderOtherChain = KAVA_MAIN_BNB_DEPUTY
            }
            TOKEN_HTLC_BINANCE_BTCB -> {
                htltReq.recipient = BINANCE_MAIN_BTCB_DEPUTY
                htltReq.senderOtherChain = KAVA_MAIN_BTCB_DEPUTY
            }
            TOKEN_HTLC_BINANCE_XRPB -> {
                htltReq.recipient = BINANCE_MAIN_XRPB_DEPUTY
                htltReq.senderOtherChain = KAVA_MAIN_XRPB_DEPUTY
            }
            TOKEN_HTLC_BINANCE_BUSD -> {
                htltReq.recipient = BINANCE_MAIN_BUSD_DEPUTY
                htltReq.senderOtherChain = KAVA_MAIN_BUSD_DEPUTY
            }
        }

        htltReq.recipientOtherChain = toChain?.address
        htltReq.timestamp = timeStamp
        htltReq.randomNumberHash = Sha256Hash.hash(originData)
        val token = Token()
        token.denom = toSendDenom
        token.amount = toSendAmount?.movePointRight(8)?.toLong()
        htltReq.outAmount = listOf(token)
        htltReq.expectedIncome = toSendAmount?.movePointRight(8)?.toPlainString() + ":" + toSendDenom
        htltReq.heightSpan = 407547
        htltReq.isCrossChain = true
        return htltReq
    }

    private fun showError(errorMsg: String) {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DialogErrorBinding.inflate(inflater)
        val alertDialog = AlertDialog.Builder(this, R.style.AppTheme_AlertDialogTheme)
            .setView(binding.root)

        val dialog = alertDialog.create()
        dialog.show()

        binding.errorMsg.text = errorMsg

        binding.btnConfirm.setOnClickListener {
            startMainActivity()
            dialog.dismiss()
        }
    }

    private fun showMoreWait() {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DialogWaitBinding.inflate(inflater)
        val alertDialog = AlertDialog.Builder(this, R.style.AppTheme_AlertDialogTheme)
            .setView(binding.root)

        val dialog = alertDialog.create()
        dialog.show()
        binding.dialogTitle.text = getString(R.string.title_more_wait_swap)
        binding.dialogMsg.text = getString(R.string.error_more_wait_swap_msg)

        binding.btnClose.setOnClickListener {
            startMainActivity()
            dialog.dismiss()
        }

        binding.btnWait.setOnClickListener {
            swapFetchCnt = 0
            kavaViewModel.bep3SwapId(getChannel(ChainKava459()), expectedSwapId, toChain)
            dialog.dismiss()
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this@Bep3ResultActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        ApplicationViewModel.shared.txRecreate()
    }
}