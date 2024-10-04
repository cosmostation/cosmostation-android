package wannabit.io.cosmostaion.ui.tx.step.service

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
import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.tx.v1beta1.ServiceGrpc
import com.cosmos.tx.v1beta1.ServiceProto
import com.cosmos.tx.v1beta1.TxProto
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
import wannabit.io.cosmostaion.chain.accountInfos
import wannabit.io.cosmostaion.chain.accountNumber
import wannabit.io.cosmostaion.chain.sequence
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.req.BroadcastTxReq
import wannabit.io.cosmostaion.data.model.req.SimulateTxReq
import wannabit.io.cosmostaion.data.repository.tx.TxRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentAllChainCompoundingBinding
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModel
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModelProviderFactory
import java.math.RoundingMode
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AllChainCompoundingFragment : BaseTxFragment() {

    private var _binding: FragmentAllChainCompoundingBinding? = null
    private val binding: FragmentAllChainCompoundingBinding? get() = _binding

    private lateinit var viewModel: TxViewModel

    private lateinit var allChainCompoundingAdapter: AllChainCompoundingAdapter

    private var compoundAbleRewards: MutableList<ClaimAllModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllChainCompoundingBinding.inflate(layoutInflater, container, false)
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
                    if (account.sortedDisplayChains().none { it.fetchState == FetchState.FAIL }) {
                        account.sortedDisplayChains().filter { !it.isTestnet && it.supportCosmos() }
                            .forEach { chain ->
                                val compoundAble = chain.cosmosFetcher?.compoundAbleRewards()
                                val txFee = chain.getInitPayableFee(requireContext())
                                if (chain.cosmosFetcher?.rewardAddress == chain.address) {
                                    if (compoundAble?.isNotEmpty() == true && txFee != null) {
                                        compoundAbleRewards.add(
                                            ClaimAllModel(
                                                chain, compoundAble
                                            )
                                        )
                                    }
                                }
                            }

                        withContext(Dispatchers.Main) {
                            loading.visibility = View.GONE

                            if (compoundAbleRewards.isEmpty()) {
                                emptyLayout.visibility = View.VISIBLE
                                btnCompoundingAll.visibility = View.GONE

                            } else {
                                recycler.visibility = View.VISIBLE
                                rewardCnt.text = compoundAbleRewards.size.toString()
                                initRecyclerView()

                                txSimulate()
                            }
                        }

                    } else {
                        emptyLayout.visibility = View.VISIBLE
                        btnCompoundingAll.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding?.recycler?.apply {
            allChainCompoundingAdapter = AllChainCompoundingAdapter()
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = allChainCompoundingAdapter
            allChainCompoundingAdapter.submitList(compoundAbleRewards)

            val allChainCompoundingTouchAdapter =
                AllChainCompoundingTouchAdapter(compoundAbleRewards) { reward ->
                    deleteUpdateView(reward)

                }.apply {
                    setClamp(resources.displayMetrics.widthPixels.toFloat() / 6)
                }
            val itemTouchHelper = ItemTouchHelper(allChainCompoundingTouchAdapter)
            itemTouchHelper.attachToRecyclerView(this)

            allChainCompoundingAdapter.setOnItemClickListener { reward ->
                allChainCompoundingTouchAdapter.initializeCurrentPosition(this)
                deleteUpdateView(reward)
            }

            setOnTouchListener { _, _ ->
                allChainCompoundingTouchAdapter.removePreviousClamp(this)
                false
            }
        }
    }

    private fun deleteUpdateView(reward: ClaimAllModel) {
        compoundAbleRewards.remove(reward)
        allChainCompoundingAdapter.submitList(compoundAbleRewards)
        allChainCompoundingAdapter.notifyDataSetChanged()

        binding?.apply {
            rewardCnt.text = compoundAbleRewards.size.toString()
            if (compoundAbleRewards.isEmpty()) {
                recycler.visibility = View.GONE
                btnCompoundingAll.visibility = View.GONE
                emptyLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun txSimulate() {
        lifecycleScope.launch(Dispatchers.IO) {
            for (i in 0 until compoundAbleRewards.size) {
                if (isAdded) {
                    activity?.let {
                        if (!compoundAbleRewards[i].baseChain.isGasSimulable()) {
                            compoundAbleRewards[i].fee =
                                compoundAbleRewards[i].baseChain.getInitPayableFee(it)

                        } else {
                            val compoundAbleReward = compoundAbleRewards[i]
                            var txFee = compoundAbleReward.baseChain.getInitPayableFee(it)
                            simulateCompoundingTx(
                                compoundAbleReward.baseChain, compoundAbleReward.rewards
                            ) { gasUsed ->
                                gasUsed?.let { toGas ->
                                    val txGasLimit =
                                        (toGas.toDouble() * compoundAbleReward.baseChain.gasMultiply()).toLong()
                                            .toBigDecimal()
                                    compoundAbleReward.baseChain.getBaseFeeInfo(it).feeDatas.firstOrNull { feeData ->
                                        feeData.denom == txFee?.getAmount(0)?.denom
                                    }?.let { gasRate ->
                                        val feeCoinAmount = gasRate.gasRate?.multiply(txGasLimit)
                                            ?.setScale(0, RoundingMode.UP)
                                        val feeCoin = CoinProto.Coin.newBuilder()
                                            .setDenom(txFee?.getAmount(0)?.denom)
                                            .setAmount(feeCoinAmount.toString()).build()

                                        txFee = TxProto.Fee.newBuilder()
                                            .setGasLimit(txGasLimit.toLong()).addAmount(feeCoin)
                                            .build()
                                    }
                                    compoundAbleRewards[i].fee = txFee
                                    compoundAbleRewards[i].isBusy = false
                                    lifecycleScope.launch(Dispatchers.Main) {
                                        allChainCompoundingAdapter.notifyItemChanged(i)
                                        if (compoundAbleRewards.count { reward -> reward.fee == null } == 0) {
                                            binding?.btnCompoundingAll?.updateButtonView(true)
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
            btnCompoundingAll.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    compoundingAllResultLauncher.launch(this)
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

    private val compoundingAllResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                for (i in 0 until compoundAbleRewards.size) {
                    compoundAbleRewards[i].isBusy = true
                }
                allChainCompoundingAdapter.notifyDataSetChanged()
                binding?.btnCompoundingAll?.visibility = View.GONE
                binding?.btnConfirm?.visibility = View.VISIBLE

                for (i in 0 until compoundAbleRewards.size) {
                    val compoundingAbleReward = compoundAbleRewards[i]
                    broadCastCompoundingTx(compoundingAbleReward) {
                        checkTx(i, compoundingAbleReward.baseChain, it)
                    }
                }
            }
        }

    private fun checkTx(
        index: Int, chain: BaseChain, txResponse: AbciProto.TxResponse?
    ) {
        lifecycleScope.launch(Dispatchers.IO) {
            loadTx(chain, txResponse?.txhash) { response ->
                compoundAbleRewards[index].txResponse = response
                lifecycleScope.launch(Dispatchers.Main) {
                    allChainCompoundingAdapter.notifyItemChanged(index)
                    if (compoundAbleRewards.count { it.txResponse == null } == 0) {
                        binding?.btnConfirm?.updateButtonView(true)
                    }
                }
            }
        }
    }

    private fun simulateCompoundingTx(
        chain: BaseChain,
        claimableRewards: MutableList<DelegationDelegatorReward?>,
        onComplete: (String?) -> Unit
    ) {
        lifecycleScope.launch(Dispatchers.IO) {
            if (isAdded) {
                activity?.let {
                    val simulateTx = Signer.genSimulate(
                        Signer.compoundingMsg(chain, claimableRewards, chain.stakeDenom),
                        chain.getInitPayableFee(it),
                        "",
                        chain
                    )

                    val gasUsed = if (chain.supportCosmos()) {
                        chain.cosmosFetcher()?.getChannel()?.let { channel ->
                            loadAuth(channel, chain)
                            val simulStub = ServiceGrpc.newBlockingStub(channel)
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

    private fun broadCastCompoundingTx(
        valueAbleReward: ClaimAllModel, onComplete: (AbciProto.TxResponse?) -> Unit
    ) {
        lifecycleScope.launch(Dispatchers.IO) {
            val chain = valueAbleReward.baseChain
            val txFee = valueAbleReward.fee
            val broadcastTx = Signer.genBroadcast(
                Signer.compoundingMsg(
                    chain, valueAbleReward.rewards, valueAbleReward.baseChain.stakeDenom
                ), txFee, "", chain

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
                    AbciProto.TxResponse.newBuilder().setTxhash(result["txhash"].asString)
                        .setCode(result["code"].asInt).setRawLog(result["raw_log"].asString).build()
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
        chain: BaseChain, txHash: String?, onComplete: (ServiceProto.GetTxResponse?) -> Unit
    ) {
        try {
            if (chain.supportCosmos()) {
                val channel = chain.cosmosFetcher?.getChannel()
                val stub = ServiceGrpc.newStub(channel)
                val request = ServiceProto.GetTxRequest.newBuilder().setHash(txHash).build()

                val response = withContext(Dispatchers.IO) {
                    suspendCoroutine { continuation ->
                        val observer = object : StreamObserver<ServiceProto.GetTxResponse> {
                            override fun onNext(value: ServiceProto.GetTxResponse?) {
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
                        AbciProto.TxResponse.newBuilder().setTxhash(result?.get("txhash")?.asString)
                            .setCode(result?.get("code")?.asInt ?: 0)
                            .setRawLog(result?.get("raw_log")?.asString).build()
                    val txResponse =
                        ServiceProto.GetTxResponse.newBuilder().setTxResponse(txResultResponse)
                            .build()
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