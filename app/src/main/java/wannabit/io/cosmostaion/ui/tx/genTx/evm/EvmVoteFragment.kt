package wannabit.io.cosmostaion.ui.tx.genTx.evm

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmos.gov.v1beta1.GovProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.databinding.FragmentVoteBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TransferTxResultActivity
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import wannabit.io.cosmostaion.ui.tx.genTx.TransferStyle
import java.math.BigInteger
import java.math.RoundingMode

class EvmVoteFragment : BaseTxFragment() {

    private var _binding: FragmentVoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain
    private var proposals: MutableList<CosmosProposal>? = mutableListOf()

    private lateinit var evmVoteAdapter: EvmVoteAdapter

    private var selectedFeePosition = 0

    private val evmGasPrices: List<BigInteger> = listOf(
        BigInteger.valueOf(1500000000),
        BigInteger.valueOf(1500000000),
        BigInteger.valueOf(1500000000)
    )
    private var evmFeeAmount: BigInteger? = null
    private val evmGasLimit = BigInteger.valueOf(21000)
    private var evmHexValue = ""

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: BaseChain, proposals: MutableList<CosmosProposal>?
        ): EvmVoteFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putParcelableArrayList("proposals", proposals?.let { ArrayList(it) })
            }
            val fragment = EvmVoteFragment()
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
                arguments?.getParcelable("selectedChain", BaseChain::class.java)
                    ?.let { selectedChain = it }

            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
            }
            proposals = arguments?.getParcelableArrayList("proposals")

            listOf(
                memoView, feeView
            ).forEach { it.setBackgroundResource(R.drawable.cell_bg) }
            memoView.visibility = View.GONE
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            evmVoteAdapter = EvmVoteAdapter(listener = selectOption)
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = evmVoteAdapter
            evmVoteAdapter.submitList(proposals)
        }
    }

    private val selectOption = object : EvmVoteAdapter.ClickListener {
        override fun selectOption(position: Int, tag: Int) {
            when (tag) {
                0 -> {
                    proposals?.get(position)?.toVoteOption = GovProto.VoteOption.VOTE_OPTION_YES
                }

                1 -> {
                    proposals?.get(position)?.toVoteOption = GovProto.VoteOption.VOTE_OPTION_NO
                }

                2 -> {
                    proposals?.get(position)?.toVoteOption =
                        GovProto.VoteOption.VOTE_OPTION_NO_WITH_VETO
                }

                3 -> {
                    proposals?.get(position)?.toVoteOption = GovProto.VoteOption.VOTE_OPTION_ABSTAIN
                }

                else -> {
                    proposals?.get(position)?.toVoteOption = null
                }
            }
            evmVoteAdapter.notifyDataSetChanged()
            txSimulate()
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
            val evmGasTitle = listOf(
                getString(R.string.str_low),
                getString(R.string.str_average),
                getString(R.string.str_high)
            )
            for (i in evmGasTitle.indices) {
                val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                feeSegment.addView(
                    segmentView.root,
                    i,
                    LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                )
                segmentView.btnTitle.text = evmGasTitle[i]
            }
            feeSegment.setPosition(1, false)
            selectedFeePosition = 1
            feeTokenImg.setImageResource(selectedChain.coinLogo)
            feeToken.text = selectedChain.coinSymbol

            val feePrice = BaseData.getPrice(selectedChain.coinGeckoId)
            val totalGasPrice = evmGasPrices[selectedFeePosition]
            val amount = totalGasPrice.multiply(evmGasLimit).toBigDecimal()
            val dpAmount = amount.movePointLeft(18).setScale(18, RoundingMode.DOWN)
            val value = feePrice.multiply(dpAmount)
            feeAmount.text = formatAmount(dpAmount.toPlainString(), 18)
            feeValue.text = formatAssetValue(value)
        }
    }

    private fun updateFeeView() {
        binding.apply {
            val feePrice = BaseData.getPrice(selectedChain.coinGeckoId)
            if (evmFeeAmount == null) {
                evmFeeAmount = evmGasPrices[selectedFeePosition].multiply(evmGasLimit)
            }
            val dpAmount =
                evmFeeAmount?.toBigDecimal()?.movePointLeft(18)?.setScale(18, RoundingMode.DOWN)
            val value = feePrice.multiply(dpAmount)
            dpAmount?.let { amount ->
                feeAmount.text = formatAmount(amount.toPlainString(), 18)
                feeValue.text = formatAssetValue(value)
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            feeSegment.setOnPositionChangedListener { position ->
                selectedFeePosition = position
                updateFeeView()
                txSimulate()
            }

            btnVote.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    voteResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                    )
                }
            }
        }
    }

    private val voteResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
//                val web3j = Web3j.build(HttpService(selectedChain.getEvmRpc()))
//                txViewModel.broadcastEvmVote(web3j, evmHexValue)
            }
        }

    private fun txSimulate() {
        if (proposals?.any { it.toVoteOption == null } == true) {
            return
        }
        binding.apply {
            btnVote.updateButtonView(false)
            backdropLayout.visibility = View.VISIBLE
            proposals?.let {
                txViewModel.simulateEvmVote(
                    it[0].id?.toLong()!!,
                    it[0].toVoteOption?.ordinal?.toLong()!!,
                    selectedChain,
                    selectedFeePosition
                )
            }
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulateEvmVote.observe(viewLifecycleOwner) { response ->
            response.first?.let { hexValue ->
                evmHexValue = hexValue
                response.second?.let { evmFeeAmount ->
                    this.evmFeeAmount = evmFeeAmount.toBigInteger()
                    updateFeeView()
                    isBroadCastTx(true)
                }
            }
        }

        txViewModel.erc20ErrorMessage.observe(viewLifecycleOwner) {
            isBroadCastTx(false)
            requireContext().makeToast(R.string.error_evm_simul)
            return@observe
        }
    }

    private fun setUpBroadcast() {
        txViewModel.broadcastEvmVoteTx.observe(viewLifecycleOwner) { txResponse ->
            Intent(requireContext(), TransferTxResultActivity::class.java).apply {
                if (txResponse?.isNotEmpty() == true) {
                    putExtra("isSuccess", true)
                    putExtra("txHash", txResponse)
                } else {
                    putExtra("isSuccess", false)
                    putExtra("errorMsg", txResponse)
                }
                putExtra("fromChainTag", selectedChain.tag)
                putExtra("toChainTag", selectedChain.tag)
                putExtra("transferStyle", TransferStyle.WEB3_STYLE.ordinal)
                startActivity(this)
            }
            dismiss()
        }
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.backdropLayout.visibility = View.GONE
        binding.btnVote.updateButtonView(isSuccess)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        txViewModel.broadcastEvmReDelegateTx.removeObservers(viewLifecycleOwner)
    }
}