package wannabit.io.cosmostaion.ui.tx.step

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmos.auth.v1beta1.QueryGrpc
import com.cosmos.auth.v1beta1.QueryProto
import com.cosmos.auth.v1beta1.QueryProto.QueryAccountResponse
import com.cosmos.base.abci.v1beta1.AbciProto.TxResponse
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.tx.v1beta1.ServiceGrpc
import com.cosmos.tx.v1beta1.ServiceProto
import com.cosmos.tx.v1beta1.ServiceProto.GetTxResponse
import com.cosmos.tx.v1beta1.TxProto.Fee
import io.grpc.ManagedChannel
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.repository.tx.TxRepositoryImpl
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentAllChainClaimBinding
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModel
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModelProviderFactory
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

    private var valueAbleRewards: MutableList<ValueAbleReward> = mutableListOf()

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
                    if (account.sortedDisplayEvmLines()
                            .none { !it.fetched } && account.sortedDisplayCosmosLines()
                            .none { !it.fetched }
                    ) {
                        for (evmChain in account.sortedDisplayEvmLines()
                            .filter { it.supportCosmos }) {
                            val valueAbleReward = evmChain.valueAbleRewards()
                            val txFee = evmChain.getInitFee(requireContext())
                            if (valueAbleReward.isNotEmpty() && txFee != null) {
                                valueAbleRewards.add(
                                    ValueAbleReward(
                                        evmChain, valueAbleReward, null, false, null
                                    )
                                )
                            }
                        }

                        for (chain in account.sortedDisplayCosmosLines()) {
                            val valueAbleReward = chain.valueAbleRewards()
                            val txFee = chain.getInitFee(requireContext())
                            if (valueAbleReward.isNotEmpty() && txFee != null) {
                                valueAbleRewards.add(
                                    ValueAbleReward(
                                        chain, valueAbleReward, null, false, null
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

    private fun initRecyclerView() {
        binding?.recycler?.apply {
            allChainClaimAdapter = AllChainClaimAdapter()
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = allChainClaimAdapter
            allChainClaimAdapter.submitList(valueAbleRewards)
        }
    }

    private fun txSimulate() {
        lifecycleScope.launch(Dispatchers.IO) {
            for (i in 0 until valueAbleRewards.size) {
                if (isAdded) {
                    activity?.let {
                        if (!valueAbleRewards[i].cosmosLine.isGasSimulable()) {
                            valueAbleRewards[i].fee =
                                valueAbleRewards[i].cosmosLine.getInitFee(it)

                        } else {
                            val valueAbleReward = valueAbleRewards[i]
                            var txFee = valueAbleReward.cosmosLine.getInitFee(it)
                            simulateClaimTx(
                                valueAbleReward.cosmosLine, valueAbleReward.rewards
                            ) { gasUsed ->
                                gasUsed?.let { toGas ->
                                    val txGasLimit =
                                        (toGas.toDouble() * valueAbleReward.cosmosLine.gasMultiply()).toLong()
                                            .toBigDecimal()
                                    valueAbleReward.cosmosLine.getBaseFeeInfo(it).feeDatas.firstOrNull { feeData ->
                                        feeData.denom == txFee?.getAmount(0)?.denom
                                    }?.let { gasRate ->
                                        val feeCoinAmount = gasRate.gasRate?.multiply(txGasLimit)
                                            ?.setScale(0, RoundingMode.UP)
                                        val feeCoin =
                                            CoinProto.Coin.newBuilder()
                                                .setDenom(txFee?.getAmount(0)?.denom)
                                                .setAmount(feeCoinAmount.toString()).build()

                                        txFee = Fee.newBuilder().setGasLimit(txGasLimit.toLong())
                                            .addAmount(feeCoin).build()
                                    }
                                    valueAbleRewards[i].fee = txFee
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

    private fun setUpClickAction() {
        binding?.apply {
            btnClaimAll.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    claimAllResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                    )
                }
            }

            btnConfirm.setOnClickListener {
                dismiss()
                BaseData.baseAccount?.let { account ->
                    for (evmChain in account.sortedDisplayEvmLines()) {
                        evmChain.fetched = false
                        initDisplayData(account)
                    }

                    for (chain in account.sortedDisplayCosmosLines()) {
                        chain.fetched = false
                        initDisplayData(account)
                    }
                }
            }
        }
    }

    private val claimAllResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                for (i in 0 until valueAbleRewards.size) {
                    valueAbleRewards[i].isSuccess = true
                }
                allChainClaimAdapter.notifyDataSetChanged()
                binding?.btnClaimAll?.visibility = View.GONE
                binding?.btnConfirm?.visibility = View.VISIBLE

                for (i in 0 until valueAbleRewards.size) {
                    val valueAbleReward = valueAbleRewards[i]
                    broadCastClaimTx(valueAbleReward) {
                        val channel = getChannel(valueAbleReward.cosmosLine)
                        checkTx(i, channel, it?.txResponse)
                    }
                }
            }
        }

    private fun checkTx(index: Int, managedChannel: ManagedChannel, txResponse: TxResponse?) {
        lifecycleScope.launch(Dispatchers.IO) {
            loadTx(managedChannel, txResponse?.txhash) { response ->
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
        chain: CosmosLine,
        claimableRewards: MutableList<DelegationDelegatorReward?>,
        onComplete: (String?) -> Unit
    ) {
        lifecycleScope.launch(Dispatchers.IO) {
            if (isAdded) {
                activity?.let {
                    val channel = getChannel(chain)
                    val simulateStub =
                        ServiceGrpc.newBlockingStub(channel).withDeadlineAfter(8L, TimeUnit.SECONDS)
                    val simulateTx = Signer.genClaimRewardsSimulate(
                        loadAuth(channel, chain.address),
                        claimableRewards,
                        chain.getInitFee(it),
                        ""
                    )
                    val gasUsed = simulateStub.simulate(simulateTx).gasInfo.gasUsed
                    onComplete(gasUsed.toString())
                }
            }
        }
    }

    private fun broadCastClaimTx(
        valueAbleReward: ValueAbleReward, onComplete: (ServiceProto.BroadcastTxResponse?) -> Unit
    ) {
        lifecycleScope.launch(Dispatchers.IO) {
            val channel = getChannel(valueAbleReward.cosmosLine)
            val txStub =
                ServiceGrpc.newBlockingStub(channel).withDeadlineAfter(8L, TimeUnit.SECONDS)
            val broadcastTx = Signer.genClaimRewardsBroadcast(
                loadAuth(channel, valueAbleReward.cosmosLine.address),
                valueAbleReward.rewards,
                valueAbleReward.fee,
                "",
                valueAbleReward.cosmosLine
            )
            onComplete(txStub.broadcastTx(broadcastTx))
        }
    }

    private fun loadAuth(
        managedChannel: ManagedChannel, address: String?
    ): QueryAccountResponse? {
        val stub = QueryGrpc.newBlockingStub(managedChannel).withDeadlineAfter(8L, TimeUnit.SECONDS)
        val request = QueryProto.QueryAccountRequest.newBuilder().setAddress(address).build()
        return try {
            stub.account(request)
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun loadTx(
        managedChannel: ManagedChannel, txHash: String?, onComplete: (GetTxResponse?) -> Unit
    ) {
        try {
            val stub = ServiceGrpc.newStub(managedChannel)
            val request = ServiceProto.GetTxRequest.newBuilder().setHash(txHash).build()

            val response = withContext(Dispatchers.IO) {
                suspendCoroutine { continuation ->
                    val observer = object : StreamObserver<GetTxResponse> {
                        override fun onNext(value: GetTxResponse?) {
                            continuation.resume(value)
                        }

                        override fun onError(t: Throwable?) {
                            continuation.resumeWithException(t ?: RuntimeException("Unknown error"))
                        }

                        override fun onCompleted() {}
                    }
                    stub.getTx(request, observer)
                }
            }
            onComplete(response)
        } catch (e: Throwable) {
            delay(3000)
            loadTx(managedChannel, txHash, onComplete)
        }
    }

    private fun initDisplayData(baseAccount: BaseAccount) {
        baseAccount.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                if (type == BaseAccountType.MNEMONIC) {
                    for (line in sortedDisplayEvmLines()) {
                        if (line.address?.isEmpty() == true) {
                            line.setInfoWithSeed(seed, line.setParentPath, lastHDPath)
                        }
                        if (!line.fetched) {
                            ApplicationViewModel.shared.loadEvmChainData(line, id, false)
                        }
                    }

                    for (line in sortedDisplayCosmosLines()) {
                        if (line.address?.isEmpty() == true) {
                            line.setInfoWithSeed(seed, line.setParentPath, lastHDPath)

                        }
                        if (!line.fetched) {
                            ApplicationViewModel.shared.loadChainData(line, id, false)
                        }
                    }

                } else if (type == BaseAccountType.PRIVATE_KEY) {
                    for (line in sortedDisplayEvmLines()) {
                        if (line.address?.isEmpty() == true) {
                            line.setInfoWithPrivateKey(privateKey)

                        }
                        if (!line.fetched) {
                            ApplicationViewModel.shared.loadEvmChainData(line, id, false)
                        }
                    }

                    for (line in sortedDisplayCosmosLines()) {
                        if (line.address?.isEmpty() == true) {
                            line.setInfoWithPrivateKey(privateKey)

                        }
                        if (!line.fetched) {
                            ApplicationViewModel.shared.loadChainData(line, id, false)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

data class ValueAbleReward(
    val cosmosLine: CosmosLine,
    val rewards: MutableList<DelegationDelegatorReward?>,
    var fee: Fee?,
    var isSuccess: Boolean = false,
    var txResponse: GetTxResponse?,
)