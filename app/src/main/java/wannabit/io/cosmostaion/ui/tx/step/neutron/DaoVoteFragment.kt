package wannabit.io.cosmostaion.ui.tx.step.neutron

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.tx.v1beta1.TxProto
import com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.google.protobuf.Any
import com.google.protobuf.ByteString
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.getdAmount
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.model.req.MultiVote
import wannabit.io.cosmostaion.data.model.req.MultiVoteReq
import wannabit.io.cosmostaion.data.model.req.Vote
import wannabit.io.cosmostaion.data.model.req.VoteReq
import wannabit.io.cosmostaion.data.model.req.WeightVote
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.data.model.res.ProposalData
import wannabit.io.cosmostaion.databinding.FragmentVoteBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.option.tx.general.AssetFragment
import wannabit.io.cosmostaion.ui.option.tx.general.AssetSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.BaseFeeAssetFragment
import wannabit.io.cosmostaion.ui.option.tx.general.BaseFeeAssetSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.MemoFragment
import wannabit.io.cosmostaion.ui.option.tx.general.MemoListener
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import java.math.RoundingMode

class DaoVoteFragment : BaseTxFragment() {

    private var _binding: FragmentVoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: ChainNeutron
    private var toSingleProposals: MutableList<ProposalData>? = mutableListOf()
    private var toMultipleProposals: MutableList<ProposalData>? = mutableListOf()
    private var toOverruleProposals: MutableList<ProposalData>? = mutableListOf()

    private lateinit var daoVoteAdapter: DaoVoteAdapter

    private var feeInfos: MutableList<FeeInfo> = mutableListOf()
    private var selectedFeeInfo = 0
    private var txFee: TxProto.Fee? = null
    private var txMemo = ""

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: ChainNeutron,
            toSingleProposals: MutableList<ProposalData?>?,
            toMultipleProposals: MutableList<ProposalData?>?,
            toOverruleProposals: MutableList<ProposalData?>?
        ): DaoVoteFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putParcelableArrayList(
                    "toSingleProposals",
                    toSingleProposals?.let { ArrayList(it) })

                putParcelableArrayList(
                    "toMultipleProposals",
                    toMultipleProposals?.let { ArrayList(it) })

                putParcelableArrayList(
                    "toOverruleProposals",
                    toOverruleProposals?.let { ArrayList(it) })
            }
            val fragment = DaoVoteFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initFee()
        setUpClickAction()
        setUpSimulate()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.apply {
                    getParcelable("selectedChain", ChainNeutron::class.java)?.let {
                        selectedChain = it
                    }
                }

            } else {
                arguments?.apply {
                    (getParcelable("selectedChain") as? ChainNeutron)?.let {
                        selectedChain = it
                    }
                }
            }
            toSingleProposals = arguments?.getParcelableArrayList("toSingleProposals")
            toMultipleProposals = arguments?.getParcelableArrayList("toMultipleProposals")
            toOverruleProposals = arguments?.getParcelableArrayList("toOverruleProposals")

            voteTitle.text = if (toSingleProposals?.isNotEmpty() == true) {
                "Dao vote (Single)"
            } else if (toMultipleProposals?.isNotEmpty() == true) {
                "Dao vote (Multiple)"
            } else {
                "Dao vote (Overrule)"
            }

            memoView.setBackgroundResource(R.drawable.cell_bg)
            feeView.setBackgroundResource(R.drawable.cell_bg)
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            daoVoteAdapter = DaoVoteAdapter(
                toSingleProposals, toMultipleProposals, toOverruleProposals, selectOption
            )
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = daoVoteAdapter
        }
    }

    private val selectOption = object : DaoVoteAdapter.ClickListener {

        override fun selectOption(position: Int, module: String, tag: Int) {
            when (module) {
                "Single" -> {
                    when (tag) {
                        0 -> toSingleProposals?.get(position)?.myVote = "yes"
                        1 -> toSingleProposals?.get(position)?.myVote = "no"
                        2 -> toSingleProposals?.get(position)?.myVote = "abstain"
                    }
                    daoVoteAdapter.notifyDataSetChanged()
                    txSimulate()
                }

                "Multiple" -> {
                    toMultipleProposals?.get(position)?.myVote = tag.toString()
                    daoVoteAdapter.notifyDataSetChanged()
                    txSimulate()
                }

                "Overrule" -> {
                    when (tag) {
                        0 -> toOverruleProposals?.get(position)?.myVote = "yes"
                        1 -> toOverruleProposals?.get(position)?.myVote = "no"
                        2 -> toOverruleProposals?.get(position)?.myVote = "abstain"
                    }
                    daoVoteAdapter.notifyDataSetChanged()
                    txSimulate()
                }
            }
        }
    }

    private fun initFee() {
        binding.apply {
            feeSegment.setSelectedBackground(
                ContextCompat.getColor(
                    requireContext(), R.color.color_accent_purple
                )
            )
            feeSegment.setRipple(
                ContextCompat.getColor(
                    requireContext(), R.color.color_accent_purple
                )
            )

            if (selectedChain.cosmosFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                val tipTitle = listOf(
                    "Default", "Fast", "Faster", "Instant"
                )
                for (i in tipTitle.indices) {
                    val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                    feeSegment.addView(
                        segmentView.root,
                        i,
                        LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                    )
                    segmentView.btnTitle.text = tipTitle[i]
                }
                feeSegment.setPosition(selectedFeeInfo, false)
                val baseFee = selectedChain.cosmosFetcher?.cosmosBaseFees?.get(0)
                val gasAmount = selectedChain.getFeeBaseGasAmount().toBigDecimal()
                val feeDenom = baseFee?.denom
                val feeAmount =
                    baseFee?.getdAmount()?.multiply(gasAmount)?.setScale(0, RoundingMode.DOWN)
                txFee = TxProto.Fee.newBuilder().setGasLimit(gasAmount.toLong()).addAmount(
                    CoinProto.Coin.newBuilder().setDenom(feeDenom).setAmount(feeAmount.toString())
                        .build()
                ).build()

            } else {
                feeInfos = selectedChain.getFeeInfos(requireContext())
                for (i in feeInfos.indices) {
                    val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                    feeSegment.addView(
                        segmentView.root,
                        i,
                        LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                    )
                    segmentView.btnTitle.text = feeInfos[i].title
                }
                feeSegment.setPosition(selectedChain.getFeeBasePosition(), false)
                selectedFeeInfo = selectedChain.getFeeBasePosition()
                txFee = selectedChain.getInitFee(requireContext())
            }
        }
        updateFeeView()
    }

    private fun updateMemoView(memo: String) {
        binding.apply {
            txMemo = memo
            if (txMemo.isEmpty()) {
                tabMemoMsg.text = getString(R.string.str_tap_for_add_memo_msg)
                tabMemoMsg.setTextColor(
                    ContextCompat.getColorStateList(
                        requireContext(), R.color.color_base03
                    )
                )
            } else {
                tabMemoMsg.text = txMemo
                tabMemoMsg.setTextColor(
                    ContextCompat.getColorStateList(
                        requireContext(), R.color.color_base01
                    )
                )
            }
        }
        txSimulate()
    }

    private fun updateFeeView() {
        binding.apply {
            txFee?.getAmount(0)?.let { fee ->
                BaseData.getAsset(selectedChain.apiName, fee.denom)?.let { asset ->
                    feeTokenImg.setTokenImg(asset)
                    feeToken.text = asset.symbol

                    val amount = fee.amount.toBigDecimal().amountHandlerLeft(asset.decimals ?: 6)
                    val price = BaseData.getPrice(asset.coinGeckoId)
                    val value = price.multiply(amount)

                    feeAmount.text = formatAmount(amount.toPlainString(), asset.decimals ?: 6)
                    feeValue.text = formatAssetValue(value)
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            memoView.setOnClickListener {
                handleOneClickWithDelay(
                    MemoFragment.newInstance(txMemo, object : MemoListener {
                        override fun memo(memo: String) {
                            updateMemoView(memo)
                        }
                    })
                )
            }

            feeTokenLayout.setOnClickListener {
                txFee?.let { fee ->
                    if (selectedChain.cosmosFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                        handleOneClickWithDelay(
                            BaseFeeAssetFragment(selectedChain,
                                selectedChain.cosmosFetcher?.cosmosBaseFees,
                                object : BaseFeeAssetSelectListener {
                                    override fun select(denom: String) {
                                        selectedChain.cosmosFetcher?.cosmosBaseFees?.firstOrNull { it.denom == denom }
                                            ?.let { baseFee ->
                                                val feeAmount = baseFee.getdAmount()
                                                    .multiply(fee.gasLimit.toBigDecimal())
                                                    ?.setScale(0, RoundingMode.DOWN)
                                                val updateFeeCoin =
                                                    CoinProto.Coin.newBuilder().setDenom(denom)
                                                        .setAmount(feeAmount.toString()).build()
                                                txFee = TxProto.Fee.newBuilder()
                                                    .setGasLimit(fee.gasLimit)
                                                    .addAmount(updateFeeCoin).build()
                                                txFee = Signer.setFee(selectedFeeInfo, txFee)

                                                updateFeeView()
                                                txSimulate()
                                            }
                                    }
                                })
                        )

                    } else {
                        handleOneClickWithDelay(
                            AssetFragment.newInstance(selectedChain,
                                feeInfos[selectedFeeInfo].feeDatas.toMutableList(),
                                object : AssetSelectListener {
                                    override fun select(denom: String) {
                                        feeInfos[selectedFeeInfo].feeDatas.firstOrNull { it.denom == denom }
                                            ?.let { feeCoin ->
                                                val gasAmount = selectedChain.getFeeBaseGasAmount()
                                                    .toBigDecimal()
                                                val updateFeeCoin =
                                                    CoinProto.Coin.newBuilder().setDenom(denom)
                                                        .setAmount(
                                                            feeCoin.gasRate?.multiply(
                                                                gasAmount
                                                            )?.setScale(0, RoundingMode.UP)
                                                                .toString()
                                                        ).build()

                                                txFee = TxProto.Fee.newBuilder().setGasLimit(
                                                    selectedChain.getFeeBaseGasAmount()
                                                ).addAmount(updateFeeCoin).build()

                                                updateFeeView()
                                                txSimulate()
                                            }
                                    }
                                })
                        )
                    }
                }
            }

            feeSegment.setOnPositionChangedListener { position ->
                selectedFeeInfo = position
                txFee = if (selectedChain.cosmosFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                    val baseFee = selectedChain.cosmosFetcher?.cosmosBaseFees?.firstOrNull {
                        it.denom == txFee?.getAmount(0)?.denom
                    }
                    val gasAmount = txFee?.gasLimit?.toBigDecimal()
                    val feeDenom = baseFee?.denom
                    val feeAmount =
                        baseFee?.getdAmount()?.multiply(gasAmount)?.setScale(0, RoundingMode.DOWN)
                    txFee = TxProto.Fee.newBuilder().setGasLimit(gasAmount!!.toLong()).addAmount(
                        CoinProto.Coin.newBuilder().setDenom(feeDenom)
                            .setAmount(feeAmount.toString()).build()
                    ).build()
                    Signer.setFee(selectedFeeInfo, txFee)

                } else {
                    selectedChain.getBaseFee(
                        requireContext(), selectedFeeInfo, txFee?.getAmount(0)?.denom
                    )
                }
                updateFeeView()
                txSimulate()
            }

            btnVote.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    daoVoteResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                    )
                }
            }
        }
    }

    private fun handleOneClickWithDelay(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        if (isClickable) {
            isClickable = false

            bottomSheetDialogFragment.show(
                requireActivity().supportFragmentManager, bottomSheetDialogFragment::class.java.name
            )

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 300)
        }
    }

    private val daoVoteResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                txViewModel.broadcast(
                    selectedChain.cosmosFetcher?.getChannel(),
                    onBindWasmVoteMsg(),
                    txFee,
                    txMemo,
                    selectedChain
                )
            }
        }

    private fun txSimulate() {
        binding.apply {
            if (toSingleProposals?.any { it.myVote == null } == true || toMultipleProposals?.any { it.myVote == null } == true || toOverruleProposals?.any { it.myVote == null } == true) {
                return
            }
            if (!selectedChain.isGasSimulable()) {
                return updateFeeViewWithSimulate(null)
            }
            backdropLayout.visibility = View.VISIBLE
            txViewModel.simulate(
                selectedChain.cosmosFetcher?.getChannel(),
                onBindWasmVoteMsg(),
                txFee,
                txMemo,
                selectedChain
            )
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulate.observe(viewLifecycleOwner) { gasUsed ->
            updateFeeViewWithSimulate(gasUsed)
        }

        txViewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            isBroadCastTx(false)
            requireContext().showToast(view, response, true)
            return@observe
        }
    }

    private fun updateFeeViewWithSimulate(gasUsed: String?) {
        txFee?.let { fee ->
            gasUsed?.toLong()?.let { gas ->
                val gasLimit =
                    (gas.toDouble() * selectedChain.gasMultiply()).toLong().toBigDecimal()
                if (selectedChain.cosmosFetcher?.cosmosBaseFees?.isNotEmpty() == true) {
                    selectedChain.cosmosFetcher?.cosmosBaseFees?.firstOrNull {
                        it.denom == fee.getAmount(
                            0
                        ).denom
                    }?.let { baseFee ->
                        val feeCoinAmount =
                            baseFee.getdAmount().multiply(gasLimit).setScale(0, RoundingMode.UP)
                        val feeCoin = CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0).denom)
                            .setAmount(feeCoinAmount.toString()).build()
                        txFee = TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong())
                            .addAmount(feeCoin).build()
                        txFee = Signer.setFee(selectedFeeInfo, txFee)
                    }

                } else {
                    val selectedFeeData =
                        feeInfos[selectedFeeInfo].feeDatas.firstOrNull { it.denom == fee.getAmount(0).denom }
                    val gasRate = selectedFeeData?.gasRate

                    val feeCoinAmount = gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)
                    val feeCoin = CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0).denom)
                        .setAmount(feeCoinAmount.toString()).build()
                    txFee =
                        TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong()).addAmount(feeCoin)
                            .build()
                }
            }
        }
        updateFeeView()
        isBroadCastTx(true)
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.backdropLayout.visibility = View.GONE
        binding.btnVote.updateButtonView(isSuccess)
    }

    private fun setUpBroadcast() {
        txViewModel.broadcast.observe(viewLifecycleOwner) { response ->
            Intent(requireContext(), TxResultActivity::class.java).apply {
                response?.let { txResponse ->
                    if (txResponse.code > 0) {
                        putExtra("isSuccess", false)
                    } else {
                        putExtra("isSuccess", true)
                    }
                    putExtra("errorMsg", txResponse.rawLog)
                    putExtra("selectedChain", selectedChain.tag)
                    val hash = txResponse.txhash
                    if (!TextUtils.isEmpty(hash)) putExtra("txHash", hash)
                    startActivity(this)
                }
            }
            dismiss()
        }
    }

    private fun onBindWasmVoteMsg(): MutableList<Any> {
        val wasmMsgs: MutableList<MsgExecuteContract?> = mutableListOf()
        toSingleProposals?.forEach { single ->
            val req = VoteReq(Vote(single.id?.toInt(), single.myVote))
            val jsonData = Gson().toJson(req)
            val msg = ByteString.copyFromUtf8(jsonData)
            wasmMsgs.add(
                MsgExecuteContract.newBuilder().setSender(selectedChain.address).setContract(
                    selectedChain.getChainListParam()?.getAsJsonArray("daos")
                        ?.get(0)?.asJsonObject?.getAsJsonArray("proposal_modules")
                        ?.get(0)?.asJsonObject?.get("address")?.asString
                ).setMsg(msg).build()
            )
        }

        toMultipleProposals?.forEach { multi ->
            val req = MultiVoteReq(
                MultiVote(multi.id?.toInt(), WeightVote(multi.myVote?.toInt()))
            )
            val jsonData = Gson().toJson(req)
            val msg = ByteString.copyFromUtf8(jsonData)
            wasmMsgs.add(
                MsgExecuteContract.newBuilder().setSender(selectedChain.address).setContract(
                    selectedChain.getChainListParam()?.getAsJsonArray("daos")
                        ?.get(0)?.asJsonObject?.getAsJsonArray("proposal_modules")
                        ?.get(1)?.asJsonObject?.get("address")?.asString
                ).setMsg(msg).build()
            )
        }

        toOverruleProposals?.forEach { overrule ->
            val req = VoteReq(Vote(overrule.id?.toInt(), overrule.myVote))
            val jsonData = Gson().toJson(req)
            val msg = ByteString.copyFromUtf8(jsonData)
            wasmMsgs.add(
                MsgExecuteContract.newBuilder().setSender(selectedChain.address).setContract(
                    selectedChain.getChainListParam()?.getAsJsonArray("daos")
                        ?.get(0)?.asJsonObject?.getAsJsonArray("proposal_modules")
                        ?.get(2)?.asJsonObject?.get("address")?.asString
                ).setMsg(msg).build()
            )
        }
        return Signer.wasmMsg(wasmMsgs)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}