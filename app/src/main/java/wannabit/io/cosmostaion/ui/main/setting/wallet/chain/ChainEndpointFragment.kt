package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.majorClass.ChainAptos
import wannabit.io.cosmostaion.chain.majorClass.ChainSolana
import wannabit.io.cosmostaion.chain.testnetClass.ChainGnoTestnet
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentChainEndpointBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding

class ChainEndpointFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentChainEndpointBinding? = null
    private val binding get() = _binding!!

    private var fromChain: BaseChain? = null
    private lateinit var endPointType: EndPointType

    private lateinit var endpointAdapter: EndpointAdapter

    companion object {
        @JvmStatic
        fun newInstance(
            fromChain: BaseChain?, endPointType: EndPointType
        ): ChainEndpointFragment {
            val args = Bundle().apply {
                putParcelable("fromChain", fromChain)
                putSerializable("endPointType", endPointType)
            }
            val fragment = ChainEndpointFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChainEndpointBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            arguments?.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getParcelable("fromChain", BaseChain::class.java)?.let {
                        fromChain = it
                    }
                    getSerializable(
                        "endPointType", EndPointType::class.java
                    )?.let { endPointType = it }

                } else {
                    (getParcelable("fromChain") as? BaseChain)?.let {
                        fromChain = it
                    }
                    (getSerializable("endPointType") as? EndPointType)?.let {
                        endPointType = it
                    }
                }
            }

            fromChain?.let { chain ->
                chainImg.setChainLogo(chain)
                selectTitle.text =
                    getString(R.string.title_select_end_point, fromChain?.getChainName())

                when (endPointType) {
                    EndPointType.END_POINT_RPC -> {
                        val rpcEndpoints: MutableList<Any> = ArrayList()
                        when (fromChain) {
                            is ChainGnoTestnet -> {
                                fromChain?.getChainListParam()
                                    ?.getAsJsonArray("cosmos_rpc_endpoint")
                                    ?.let {
                                        for (jsonElement in it) {
                                            rpcEndpoints.add(jsonElement.asJsonObject)
                                        }
                                    }
                                endpointAdapter = EndpointAdapter(
                                    fromChain,
                                    rpcEndpoints,
                                    mutableListOf(),
                                    EndPointType.END_POINT_RPC,
                                    endpointClickAction
                                )
                            }

                            is ChainSolana -> {
                                fromChain?.getChainListParam()
                                    ?.getAsJsonArray("solana_rpc_endpoint")?.let {
                                    for (jsonElement in it) {
                                        rpcEndpoints.add(jsonElement.asJsonObject)
                                    }
                                }
                                endpointAdapter = EndpointAdapter(
                                    fromChain,
                                    rpcEndpoints,
                                    mutableListOf(),
                                    EndPointType.END_POINT_RPC,
                                    endpointClickAction
                                )
                            }

                            else -> {
                                fromChain?.getChainListParam()?.getAsJsonArray("rpc_endpoint")
                                    ?.let {
                                        for (jsonElement in it) {
                                            rpcEndpoints.add(jsonElement.asJsonObject)
                                        }
                                    }
                                endpointAdapter = EndpointAdapter(
                                    fromChain,
                                    rpcEndpoints,
                                    mutableListOf(),
                                    EndPointType.END_POINT_RPC,
                                    endpointClickAction
                                )
                            }
                        }
                        recycler.setHasFixedSize(true)
                        recycler.layoutManager = LinearLayoutManager(requireActivity())
                        recycler.adapter = endpointAdapter
                        endpointAdapter.submitList(rpcEndpoints)
                    }

                    EndPointType.END_POINT_EVM -> {
                        setEndpointWithEvm()
                    }

                    EndPointType.END_POINT_MOVE -> {
                        segmentView.visibility = View.VISIBLE
                        view.visibility = View.GONE

                        val recyclerViewLayoutParams =
                            recycler.layoutParams as RelativeLayout.LayoutParams
                        recyclerViewLayoutParams.addRule(
                            RelativeLayout.BELOW, R.id.segment_view
                        )
                        recycler.layoutParams = recyclerViewLayoutParams

                        initSegmentView()

                        setEndpointWithMove()
                    }

                    EndPointType.END_POINT_COSMOS -> {
                        if (fromChain?.isEvmCosmos() == true) {
                            segmentView.visibility = View.VISIBLE
                            view.visibility = View.GONE

                            val recyclerViewLayoutParams =
                                recycler.layoutParams as RelativeLayout.LayoutParams
                            recyclerViewLayoutParams.addRule(
                                RelativeLayout.BELOW, R.id.segment_view
                            )
                            recycler.layoutParams = recyclerViewLayoutParams

                            initSegmentView()

                        } else {
                            segmentView.visibility = View.GONE
                            view.visibility = View.VISIBLE
                        }

                        setEndpointWithCosmos()
                    }
                }
            }
        }
    }

    private fun initSegmentView() {
        binding.apply {
            segmentView.setBackgroundResource(R.drawable.cell_search_bg)
            rpcSegment.apply {
                setSelectedBackground(
                    ContextCompat.getColor(
                        requireActivity(), R.color.color_accent_purple
                    )
                )
                setRipple(
                    ContextCompat.getColor(
                        requireActivity(), R.color.color_accent_purple
                    )
                )
            }

            for (i in 0 until 2) {
                val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                rpcSegment.addView(
                    segmentView.root,
                    i,
                    LinearLayout.LayoutParams(0, dpToPx(requireActivity(), 32), 1f)
                )

                if (endPointType == EndPointType.END_POINT_MOVE) {
                    when (i) {
                        0 -> segmentView.btnTitle.text = "REST Endpoint"
                        else -> segmentView.btnTitle.text = "GRAPHQL Endpoint"
                    }
                } else {
                    when (i) {
                        0 -> segmentView.btnTitle.text = "Cosmos Endpoint"
                        else -> segmentView.btnTitle.text = "EVM RPC Endpoint"
                    }
                }
            }
        }
    }

    private fun setEndpointWithCosmos() {
        binding.apply {
            val grpcEndpoints: MutableList<Any> = ArrayList()
            fromChain?.getChainListParam()?.getAsJsonArray("grpc_endpoint")?.let {
                for (jsonElement in it) {
                    grpcEndpoints.add(jsonElement.asJsonObject)
                }
            }
            val lcdEndpoints: MutableList<Any> = ArrayList()
            fromChain?.getChainListParam()?.getAsJsonArray("lcd_endpoint")?.let {
                for (jsonElement in it) {
                    lcdEndpoints.add(jsonElement.asJsonObject)
                }
            }

            endpointAdapter = EndpointAdapter(
                fromChain,
                grpcEndpoints,
                lcdEndpoints,
                EndPointType.END_POINT_COSMOS,
                endpointClickAction
            )
            if (isAdded) {
                recycler.setHasFixedSize(true)
                recycler.layoutManager = LinearLayoutManager(requireActivity())
                recycler.adapter = endpointAdapter
                endpointAdapter.submitList(grpcEndpoints + lcdEndpoints)
            }
        }
    }

    private fun setEndpointWithEvm() {
        binding.apply {
            val rpcEndpoints: MutableList<Any> = ArrayList()
            fromChain?.getChainListParam()?.getAsJsonArray("evm_rpc_endpoint")?.let {
                for (jsonElement in it) {
                    rpcEndpoints.add(jsonElement.asJsonObject)
                }
            }

            endpointAdapter = EndpointAdapter(
                fromChain,
                rpcEndpoints,
                mutableListOf(),
                EndPointType.END_POINT_EVM,
                endpointClickAction
            )
            if (isAdded) {
                recycler.setHasFixedSize(true)
                recycler.layoutManager = LinearLayoutManager(requireActivity())
                recycler.adapter = endpointAdapter
                endpointAdapter.submitList(rpcEndpoints)
            }
        }
    }

    private fun setEndpointWithMove() {
        binding.apply {
            val restEndpoints: MutableList<Any> = ArrayList()
            fromChain?.getChainListParam()?.getAsJsonArray("rest_endpoint")?.let {
                for (jsonElement in it) {
                    restEndpoints.add(jsonElement.asJsonObject)
                }
            }

            endpointAdapter = EndpointAdapter(
                fromChain,
                restEndpoints,
                mutableListOf(),
                EndPointType.END_POINT_MOVE,
                endpointClickAction
            )
            if (isAdded) {
                recycler.setHasFixedSize(true)
                recycler.layoutManager = LinearLayoutManager(requireActivity())
                recycler.adapter = endpointAdapter
                endpointAdapter.submitList(restEndpoints)
            }
        }
    }

    private fun setEndpointWithGraphQL() {
        binding.apply {
            val graphQlEndpoints: MutableList<Any> = ArrayList()
            fromChain?.getChainListParam()?.getAsJsonArray("graphql_endpoint")?.let {
                for (jsonElement in it) {
                    graphQlEndpoints.add(jsonElement.asJsonObject)
                }
            }

            endpointAdapter = EndpointAdapter(
                fromChain,
                mutableListOf(),
                graphQlEndpoints,
                EndPointType.END_POINT_MOVE,
                endpointClickAction
            )
            if (isAdded) {
                recycler.setHasFixedSize(true)
                recycler.layoutManager = LinearLayoutManager(requireActivity())
                recycler.adapter = endpointAdapter
                endpointAdapter.submitList(graphQlEndpoints)
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            rpcSegment.setOnPositionChangedListener { position ->
                when (position) {
                    0 -> {
                        if (fromChain is ChainAptos) {
                            setEndpointWithMove()
                        } else {
                            setEndpointWithCosmos()
                        }
                    }

                    else -> {
                        if (fromChain is ChainAptos) {
                            setEndpointWithGraphQL()
                        } else {
                            setEndpointWithEvm()
                        }
                    }
                }
            }
        }
    }

    private val endpointClickAction = object : EndpointAdapter.EndpointListener {
        override fun select(endpoint: String, gapTime: Double?) {
            if (gapTime != null) {
                Prefs.setGrpcEndpoint(fromChain, endpoint)
                Prefs.setEndpointType(fromChain, CosmosEndPointType.USE_GRPC)
                dismiss()

                val bundle = Bundle()
                bundle.putString("endpoint", endpoint)
                parentFragmentManager.setFragmentResult("endpoint", bundle)
                dismiss()

            } else {
                requireActivity().makeToast(R.string.error_useless_end_point)
                return
            }
        }

        override fun rpcSelect(endpoint: String, gapTime: Double?) {
            if (gapTime != null) {
                Prefs.setEvmRpcEndpoint(fromChain, endpoint)
                dismiss()

                val bundle = Bundle()
                bundle.putString("endpoint", endpoint)
                parentFragmentManager.setFragmentResult("endpoint", bundle)
                dismiss()

            } else {
                requireActivity().makeToast(R.string.error_useless_end_point)
                return
            }
        }

        override fun lcdSelect(endpoint: String, gapTime: Double?) {
            if (gapTime != null) {
                Prefs.setLcdEndpoint(fromChain, endpoint)
                Prefs.setEndpointType(fromChain, CosmosEndPointType.USE_LCD)
                dismiss()

                val bundle = Bundle()
                bundle.putString("endpoint", endpoint)
                parentFragmentManager.setFragmentResult("endpoint", bundle)
                dismiss()

            } else {
                requireActivity().makeToast(R.string.error_useless_end_point)
                return
            }
        }

        override fun restSelect(endpoint: String, gapTime: Double?) {
            if (gapTime != null) {
                Prefs.setLcdEndpoint(fromChain, endpoint)
                dismiss()

                val bundle = Bundle()
                bundle.putString("endpoint", endpoint)
                parentFragmentManager.setFragmentResult("endpoint", bundle)
                dismiss()

            } else {
                requireActivity().makeToast(R.string.error_useless_end_point)
                return
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

enum class EndPointType { END_POINT_EVM, END_POINT_COSMOS, END_POINT_RPC, END_POINT_MOVE }