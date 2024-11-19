package wannabit.io.cosmostaion.ui.tx.genTx.service

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmos.auth.v1beta1.QueryGrpc
import com.cosmos.auth.v1beta1.QueryProto
import com.cosmos.base.abci.v1beta1.AbciProto.TxResponse
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.tx.v1beta1.ServiceGrpc
import com.cosmos.tx.v1beta1.ServiceProto
import com.cosmos.tx.v1beta1.ServiceProto.GetTxResponse
import com.cosmos.tx.v1beta1.TxProto.Fee
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.grpc.ManagedChannel
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.bouncycastle.util.encoders.Base64
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.fetcher.accountInfos
import wannabit.io.cosmostaion.chain.fetcher.accountNumber
import wannabit.io.cosmostaion.chain.fetcher.sequence
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.sign.Signer
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.req.BroadcastTxReq
import wannabit.io.cosmostaion.data.model.req.SimulateTxReq
import wannabit.io.cosmostaion.data.repository.tx.TxRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentAllChainClaimBinding
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.data.viewmodel.tx.TxViewModel
import wannabit.io.cosmostaion.data.viewmodel.tx.TxViewModelProviderFactory
import java.math.RoundingMode
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AllChainClaimFragment : BaseTxFragment() {

    private var _binding: FragmentAllChainClaimBinding? = null
    private val binding: FragmentAllChainClaimBinding? get() = _binding

    private lateinit var viewModel: TxViewModel

    private lateinit var allChainClaimAdapter: AllChainClaimAdapter

    private var valueAbleRewards: MutableList<ClaimAllModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllChainClaimBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
        setUpClickAction()
    }

    private fun initViewModel() {
        val txRepository = TxRepositoryImpl()
        val txViewModelProviderFactory = TxViewModelProviderFactory(txRepository)
        viewModel = ViewModelProvider(
            this, txViewModelProviderFactory
        )[TxViewModel::class.java]
    }

    private fun initView() {
        binding?.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                BaseData.baseAccount?.let { account ->
                    if (account.sortedDisplayChains().none { it.fetchState == FetchState.BUSY }) {
                        for (chain in account.sortedDisplayChains()
                            .filter { !it.isTestnet && it.supportCosmos() }) {
                            val valueAbleReward = chain.cosmosFetcher?.valueAbleRewards()
                            val txFee = chain.getInitPayableFee(requireContext())
                            if (valueAbleReward?.isNotEmpty() == true && txFee != null) {
                                valueAbleRewards.add(
                                    ClaimAllModel(
                                        chain, valueAbleReward
                                    )
                                )
                            }
                        }

                        withContext(Dispatchers.Main) {
                            loading.visibility = View.GONE

                            if (valueAbleRewards.isEmpty()) {
                                emptyLayout.visibility = View.VISIBLE
                                btnClaimAll.visibility = View.GONE

                            } else {
                                recycler.visibility = View.VISIBLE
                                rewardCnt.text = valueAbleRewards.size.toString()
                                initRecyclerView()

                                txSimulate()
                            }
                        }

                    } else {
                        emptyLayout.visibility = View.VISIBLE
                        btnClaimAll.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun deleteUpdateView(reward: ClaimAllModel) {
        valueAbleRewards.remove(reward)
        allChainClaimAdapter.submitList(valueAbleRewards)
        allChainClaimAdapter.notifyDataSetChanged()

        binding?.apply {
            rewardCnt.text = valueAbleRewards.size.toString()
            if (valueAbleRewards.isEmpty()) {
                recycler.visibility = View.GONE
                btnClaimAll.visibility = View.GONE
                emptyLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun initRecyclerView() {
        binding?.recycler?.apply {
            allChainClaimAdapter = AllChainClaimAdapter()
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = allChainClaimAdapter
            allChainClaimAdapter.submitList(valueAbleRewards)

            val allChainClaimTouchAdapter = AllChainClaimTouchAdapter(valueAbleRewards) { reward ->
                deleteUpdateView(reward)

            }.apply {
                setClamp(resources.displayMetrics.widthPixels.toFloat() / 6)
            }
            val itemTouchHelper = ItemTouchHelper(allChainClaimTouchAdapter)
            itemTouchHelper.attachToRecyclerView(this)

            allChainClaimAdapter.setOnItemClickListener { reward ->
                allChainClaimTouchAdapter.initializeCurrentPosition(this)
                deleteUpdateView(reward)
            }

            setOnTouchListener { _, _ ->
                allChainClaimTouchAdapter.removePreviousClamp(this)
                false
            }
        }
    }

    private fun txSimulate() {
        lifecycleScope.launch(Dispatchers.IO) {
            for (i in 0 until valueAbleRewards.size) {
                if (isAdded) {
                    activity?.let {
                        if (!valueAbleRewards[i].baseChain.isSimulable()) {
                            valueAbleRewards[i].fee =
                                valueAbleRewards[i].baseChain.getInitPayableFee(it)

                        } else {
                            val valueAbleReward = valueAbleRewards[i]
                            var txFee = valueAbleReward.baseChain.getInitPayableFee(it)
                            simulateClaimTx(
                                valueAbleReward.baseChain, valueAbleReward.rewards
                            ) { gasUsed ->
                                gasUsed?.let { toGas ->
                                    val txGasLimit =
                                        (toGas.toDouble() * valueAbleReward.baseChain.simulatedGasMultiply()).toLong()
                                            .toBigDecimal()
                                    valueAbleReward.baseChain.getBaseFeeInfo(it).feeDatas.firstOrNull { feeData ->
                                        feeData.denom == txFee?.getAmount(0)?.denom
                                    }?.let { gasRate ->
                                        val feeCoinAmount = gasRate.gasRate?.multiply(txGasLimit)
                                            ?.setScale(0, RoundingMode.UP)
                                        val feeCoin = CoinProto.Coin.newBuilder()
                                            .setDenom(txFee?.getAmount(0)?.denom)
                                            .setAmount(feeCoinAmount.toString()).build()

                                        txFee = Fee.newBuilder().setGasLimit(txGasLimit.toLong())
                                            .addAmount(feeCoin).build()
                                    }
                                    valueAbleRewards[i].fee = txFee
                                    valueAbleRewards[i].isBusy = false
                                    lifecycleScope.launch(Dispatchers.Main) {
                                        allChainClaimAdapter.notifyItemChanged(i)
                                        if (valueAbleRewards.count { reward -> reward.fee == null } == 0) {
                                            binding?.btnClaimAll?.updateButtonView(true)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("WrongConstant")
    private fun setUpClickAction() {
        binding?.apply {
            btnClaimAll.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    claimAllResultLauncher.launch(this)
                    if (Build.VERSION.SDK_INT >= 34) {
                        requireActivity().overrideActivityTransition(
                            Activity.OVERRIDE_TRANSITION_OPEN,
                            R.anim.anim_slide_in_bottom,
                            R.anim.anim_fade_out
                        )
                    } else {
                        requireActivity().overridePendingTransition(
                            R.anim.anim_slide_in_bottom,
                            R.anim.anim_fade_out
                        )
                    }
                }
            }

            btnConfirm.setOnClickListener {
                ApplicationViewModel.shared.serviceTx()
                dismiss()
            }
        }
    }

    private val claimAllResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                for (i in 0 until valueAbleRewards.size) {
                    valueAbleRewards[i].isBusy = true
                }
                allChainClaimAdapter.notifyDataSetChanged()
                binding?.btnClaimAll?.visibility = View.GONE
                binding?.btnConfirm?.visibility = View.VISIBLE

                for (i in 0 until valueAbleRewards.size) {
                    val valueAbleReward = valueAbleRewards[i]
                    broadCastClaimTx(valueAbleReward) {
                        checkTx(i, valueAbleReward.baseChain, it)
                    }
                }
            }
        }

    private fun checkTx(index: Int, chain: BaseChain, txResponse: TxResponse?) {
        lifecycleScope.launch(Dispatchers.IO) {
            loadTx(chain, txResponse?.txhash) { response ->
                valueAbleRewards[index].txResponse = response
                lifecycleScope.launch(Dispatchers.Main) {
                    allChainClaimAdapter.notifyItemChanged(index)
                    if (valueAbleRewards.count { it.txResponse == null } == 0) {
                        binding?.btnConfirm?.updateButtonView(true)
                    }
                }
            }
        }
    }

    private fun simulateClaimTx(
        chain: BaseChain,
        claimableRewards: MutableList<DelegationDelegatorReward?>,
        onComplete: (String?) -> Unit
    ) {
        lifecycleScope.launch(Dispatchers.IO) {
            if (isAdded) {
                activity?.let {
                    val simulateTx = Signer.genSimulate(
                        Signer.claimStakingRewardMsg(chain, claimableRewards),
                        chain.getInitPayableFee(it),
                        "",
                        chain
                    )
                    val gasUsed = if (chain.supportCosmos()) {
                        chain.cosmosFetcher()?.getChannel()?.let { channel ->
                            loadAuth(channel, chain)
                            val simulStub =
                                ServiceGrpc.newBlockingStub(channel)
                                    .withDeadlineAfter(8L, TimeUnit.SECONDS)
                            simulStub.simulate(simulateTx).gasInfo.gasUsed.toString()
                        }

                    } else {
                        val txByte = Base64.toBase64String(simulateTx?.txBytes?.toByteArray())
                        val response =
                            RetrofitInstance.lcdApi(chain).lcdSimulateTx(SimulateTxReq(txByte))
                        if (response.isSuccessful) {
                            response.body()?.getAsJsonObject("gas_info")
                                ?.get("gas_used")?.asString
                        } else {
                            val errorMessageJsonObject = Gson().fromJson(
                                response.errorBody()?.string(), JsonObject::class.java
                            )
                            errorMessageJsonObject["message"].asString
                        }
                    }
                    onComplete(gasUsed)
                }
            }
        }
    }

    private fun broadCastClaimTx(
        valueAbleReward: ClaimAllModel, onComplete: (TxResponse?) -> Unit
    ) {
        lifecycleScope.launch(Dispatchers.IO) {
            val chain = valueAbleReward.baseChain
            val txFee = valueAbleReward.fee
            val broadcastTx = Signer.genBroadcast(
                Signer.claimStakingRewardMsg(chain, valueAbleReward.rewards),
                txFee,
                "",
                chain
            )

            val txResponse = if (chain.supportCosmos()) {
                val channel = chain.cosmosFetcher?.getChannel()
                val txStub =
                    ServiceGrpc.newBlockingStub(channel).withDeadlineAfter(8L, TimeUnit.SECONDS)
                txStub.broadcastTx(broadcastTx).txResponse

            } else {
                val txByte = Base64.toBase64String(broadcastTx?.txBytes?.toByteArray())
                val mode = ServiceProto.BroadcastMode.BROADCAST_MODE_SYNC.number
                val response =
                    RetrofitInstance.lcdApi(chain).lcdBroadcastTx(BroadcastTxReq(mode, txByte))
                if (!response["tx_response"].isJsonNull && !response["tx_response"].asJsonObject.isJsonNull) {
                    val result = response["tx_response"].asJsonObject
                    TxResponse.newBuilder().setTxhash(result["txhash"].asString)
                        .setCode(result["code"].asInt).setRawLog(result["raw_log"].asString)
                        .build()
                } else {
                    null
                }
            }

            try {
                onComplete(txResponse)
            } catch (_: Exception) {

            }
        }
    }

    private suspend fun loadAuth(
        managedChannel: ManagedChannel, chain: BaseChain
    ) {
        return if (chain.supportCosmos()) {
            val stub =
                QueryGrpc.newBlockingStub(managedChannel).withDeadlineAfter(8L, TimeUnit.SECONDS)
            val request =
                QueryProto.QueryAccountRequest.newBuilder().setAddress(chain.address).build()
            chain.cosmosFetcher()?.cosmosAuth = stub.account(request).account
            chain.cosmosFetcher()?.cosmosAccountNumber =
                stub.account(request).account.accountInfos().second
            chain.cosmosFetcher()?.cosmosSequence =
                stub.account(request).account.accountInfos().third

        } else {
            val response = RetrofitInstance.lcdApi(chain)
                .lcdAuthInfo(chain.address).asJsonObject["account"].asJsonObject
            chain.cosmosFetcher()?.cosmosLcdAuth = response
            chain.cosmosFetcher()?.cosmosAccountNumber = response.accountNumber()
            chain.cosmosFetcher()?.cosmosSequence = response.sequence()
        }
    }

    private suspend fun loadTx(
        chain: BaseChain, txHash: String?, onComplete: (GetTxResponse?) -> Unit
    ) {
        try {
            if (chain.supportCosmos()) {
                val channel = chain.cosmosFetcher?.getChannel()
                val stub = ServiceGrpc.newStub(channel)
                val request = ServiceProto.GetTxRequest.newBuilder().setHash(txHash).build()

                val response = withContext(Dispatchers.IO) {
                    suspendCoroutine { continuation ->
                        val observer = object : StreamObserver<GetTxResponse> {
                            override fun onNext(value: GetTxResponse?) {
                                continuation.resume(value)
                            }

                            override fun onError(t: Throwable?) {
                                continuation.resumeWithException(
                                    t ?: RuntimeException("Unknown error")
                                )
                            }

                            override fun onCompleted() {}
                        }
                        stub.getTx(request, observer)
                    }
                }
                onComplete(response)

            } else {
                val response = RetrofitInstance.lcdApi(chain).lcdTxInfo(txHash)
                if (response.isSuccessful && response.body()
                        ?.get("tx_response")?.isJsonNull == false && response.body()
                        ?.get("tx_response")?.asJsonObject?.isJsonNull == false
                ) {
                    val result = response.body()?.get("tx_response")?.asJsonObject
                    val txResultResponse =
                        TxResponse.newBuilder().setTxhash(result?.get("txhash")?.asString)
                            .setCode(result?.get("code")?.asInt ?: 0)
                            .setRawLog(result?.get("raw_log")?.asString).build()
                    val txResponse =
                        GetTxResponse.newBuilder().setTxResponse(txResultResponse).build()
                    onComplete(txResponse)

                } else {
                    delay(3000)
                    loadTx(chain, txHash, onComplete)
                }
            }

        } catch (e: Throwable) {
            delay(3000)
            loadTx(chain, txHash, onComplete)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

data class ClaimAllModel(
    val baseChain: BaseChain,
    val rewards: MutableList<DelegationDelegatorReward?>,
    var fee: Fee? = null,
    var isBusy: Boolean = true,
    var txResponse: GetTxResponse? = null,
)