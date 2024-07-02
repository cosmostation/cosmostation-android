package wannabit.io.cosmostaion.ui.tx.step.service

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
//                    if (account.sortedDisplayEvmLines()
//                            .none { !it.fetched } && account.sortedDisplayCosmosLines()
//                            .none { !it.fetched }
//                    ) {
//                        for (chain in account.sortedDisplayCosmosLines()
//                            .filter { it.rewardAddress == it.address }) {
//                            val compoundAble = chain.compoundAbleRewards()
//                            val txFee = chain.getInitPayableFee(requireContext())
//                            if (compoundAble.isNotEmpty() && txFee != null) {
//                                compoundAbleRewards.add(
//                                    ClaimAllModel(
//                                        chain, compoundAble
//                                    )
//                                )
//                            }
//                        }
//
//                        for (evmChain in account.sortedDisplayEvmLines()
//                            .filter { it.rewardAddress == it.address && it.supportCosmos }) {
//                            val compoundAble = evmChain.compoundAbleRewards()
//                            val txFee = evmChain.getInitPayableFee(requireContext())
//                            if (compoundAble.isNotEmpty() && txFee != null) {
//                                compoundAbleRewards.add(
//                                    ClaimAllModel(
//                                        evmChain, compoundAble
//                                    )
//                                )
//                            }
//                        }
//
//                        withContext(Dispatchers.Main) {
//                            loading.visibility = View.GONE
//
//                            if (compoundAbleRewards.isEmpty()) {
//                                emptyLayout.visibility = View.VISIBLE
//                                btnCompoundingAll.visibility = View.GONE
//
//                            } else {
//                                recycler.visibility = View.VISIBLE
//                                rewardCnt.text = compoundAbleRewards.size.toString()
//                                initRecyclerView()
//
//                                txSimulate()
//                            }
//                        }
//
//                    } else {
//                        emptyLayout.visibility = View.VISIBLE
//                        btnCompoundingAll.visibility = View.GONE
//                    }
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
                        if (!compoundAbleRewards[i].cosmosLine.isGasSimulable()) {
                            compoundAbleRewards[i].fee =
                                compoundAbleRewards[i].cosmosLine.getInitPayableFee(it)

                        } else {
                            val compoundAbleReward = compoundAbleRewards[i]
                            var txFee = compoundAbleReward.cosmosLine.getInitPayableFee(it)
                            simulateCompoundingTx(
                                compoundAbleReward.cosmosLine,
                                compoundAbleReward.rewards
                            ) { gasUsed ->
                                gasUsed?.let { toGas ->
//                                    val txGasLimit =
//                                        (toGas.toDouble() * compoundAbleReward.cosmosLine.gasMultiply()).toLong()
//                                            .toBigDecimal()
//                                    compoundAbleReward.cosmosLine.getBaseFeeInfo(it).feeDatas.firstOrNull { feeData ->
//                                        feeData.denom == txFee?.getAmount(0)?.denom
//                                    }?.let { gasRate ->
//                                        val feeCoinAmount = gasRate.gasRate?.multiply(txGasLimit)
//                                            ?.setScale(0, RoundingMode.UP)
//                                        val feeCoin = CoinProto.Coin.newBuilder()
//                                            .setDenom(txFee?.getAmount(0)?.denom)
//                                            .setAmount(feeCoinAmount.toString()).build()
//
//                                        txFee = TxProto.Fee.newBuilder()
//                                            .setGasLimit(txGasLimit.toLong())
//                                            .addAmount(feeCoin).build()
//                                    }
//                                    compoundAbleRewards[i].fee = txFee
//                                    compoundAbleRewards[i].isBusy = false
//                                    lifecycleScope.launch(Dispatchers.Main) {
//                                        allChainCompoundingAdapter.notifyItemChanged(i)
//                                        if (compoundAbleRewards.count { reward -> reward.fee == null } == 0) {
//                                            binding?.btnCompoundingAll?.updateButtonView(true)
//                                        }
//                                    }
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
            btnCompoundingAll.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    compoundingAllResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                    )
                }
            }

            btnConfirm.setOnClickListener {
                dismiss()
                BaseData.baseAccount?.let { account ->
//                    for (evmChain in account.sortedDisplayEvmLines()) {
//                        evmChain.fetched = false
//                        initDisplayData(account)
//                    }
//
//                    for (chain in account.sortedDisplayCosmosLines()) {
//                        chain.fetched = false
//                        initDisplayData(account)
//                    }
                }
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
//                    broadCastCompoundingTx(compoundingAbleReward) {
//                        val channel = getChannel(compoundingAbleReward.cosmosLine)
//                        checkTx(i, channel, it?.txResponse)
//                    }
                }
            }
        }

    private fun initDisplayData(baseAccount: BaseAccount) {
        baseAccount.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                if (type == BaseAccountType.MNEMONIC) {
//                    for (line in sortedDisplayEvmLines()) {
//                        if (line.address?.isEmpty() == true) {
//                            line.setInfoWithSeed(seed, line.setParentPath, lastHDPath)
//                        }
//                        if (!line.fetched) {
//                            ApplicationViewModel.shared.loadEvmChainData(line, id, false)
//                        }
//                    }
//
//                    for (line in sortedDisplayCosmosLines()) {
//                        if (line.address?.isEmpty() == true) {
//                            line.setInfoWithSeed(seed, line.setParentPath, lastHDPath)
//
//                        }
//                        if (!line.fetched) {
//                            ApplicationViewModel.shared.loadChainData(line, id, false)
//                        }
//                    }

                } else if (type == BaseAccountType.PRIVATE_KEY) {
//                    for (line in sortedDisplayEvmLines()) {
//                        if (line.address?.isEmpty() == true) {
//                            line.setInfoWithPrivateKey(privateKey)
//
//                        }
//                        if (!line.fetched) {
//                            ApplicationViewModel.shared.loadEvmChainData(line, id, false)
//                        }
//                    }
//
//                    for (line in sortedDisplayCosmosLines()) {
//                        if (line.address?.isEmpty() == true) {
//                            line.setInfoWithPrivateKey(privateKey)
//
//                        }
//                        if (!line.fetched) {
//                            ApplicationViewModel.shared.loadChainData(line, id, false)
//                        }
//                    }
                }
            }
        }
    }

    private fun checkTx(
        index: Int,
        managedChannel: ManagedChannel,
        txResponse: AbciProto.TxResponse?
    ) {
        lifecycleScope.launch(Dispatchers.IO) {
            loadTx(managedChannel, txResponse?.txhash) { response ->
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
//                    val simulateTx = Signer.genCompoundingSimulate(
//                        loadAuth(channel, chain.address),
//                        claimableRewards,
//                        chain.stakeDenom,
//                        chain.getInitPayableFee(it),
//                        "",
//                        chain
//                    )
//
//                    try {
//                        val gasUsed = simulateStub.simulate(simulateTx).gasInfo.gasUsed
//                        onComplete(gasUsed.toString())
//                    } catch (e: Exception) {
//
//                    }
                }
            }
        }
    }

    private fun broadCastCompoundingTx(
        valueAbleReward: ClaimAllModel, onComplete: (ServiceProto.BroadcastTxResponse?) -> Unit
    ) {
        lifecycleScope.launch(Dispatchers.IO) {
            val channel = getChannel(valueAbleReward.cosmosLine)
            val txStub =
                ServiceGrpc.newBlockingStub(channel).withDeadlineAfter(8L, TimeUnit.SECONDS)
//            val broadcastTx = Signer.genCompoundingBroadcast(
//                loadAuth(channel, valueAbleReward.cosmosLine.address),
//                valueAbleReward.rewards,
//                valueAbleReward.cosmosLine.stakeDenom,
//                valueAbleReward.fee,
//                "",
//                valueAbleReward.cosmosLine
//            )
//            try {
//                onComplete(txStub.broadcastTx(broadcastTx))
//            } catch (e: Exception) {
//
//            }
        }
    }

    private fun loadAuth(
        managedChannel: ManagedChannel, address: String?
    ): QueryProto.QueryAccountResponse? {
        val stub = QueryGrpc.newBlockingStub(managedChannel).withDeadlineAfter(8L, TimeUnit.SECONDS)
        val request = QueryProto.QueryAccountRequest.newBuilder().setAddress(address).build()
        return try {
            stub.account(request)
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun loadTx(
        managedChannel: ManagedChannel,
        txHash: String?,
        onComplete: (ServiceProto.GetTxResponse?) -> Unit
    ) {
        try {
            val stub = ServiceGrpc.newStub(managedChannel)
            val request = ServiceProto.GetTxRequest.newBuilder().setHash(txHash).build()

            val response = withContext(Dispatchers.IO) {
                suspendCoroutine { continuation ->
                    val observer = object : StreamObserver<ServiceProto.GetTxResponse> {
                        override fun onNext(value: ServiceProto.GetTxResponse?) {
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}