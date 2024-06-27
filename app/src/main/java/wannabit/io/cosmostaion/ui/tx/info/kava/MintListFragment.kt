package wannabit.io.cosmostaion.ui.tx.info.kava

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kava.cdp.v1beta1.GenesisProto
import com.kava.cdp.v1beta1.GenesisProto.CollateralParam
import com.kava.cdp.v1beta1.QueryProto.CDPResponse
import com.kava.pricefeed.v1beta1.QueryProto
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.data.repository.chain.KavaRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentMintListBinding
import wannabit.io.cosmostaion.ui.option.tx.kava.MintOptionFragment
import wannabit.io.cosmostaion.ui.tx.step.kava.CreateMintFragment
import wannabit.io.cosmostaion.ui.tx.step.kava.MintActionFragment
import wannabit.io.cosmostaion.ui.tx.step.kava.MintActionType
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModelProviderFactory

class MintListFragment : Fragment() {

    private var _binding: FragmentMintListBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: CosmosLine
    private lateinit var priceFeed: QueryProto.QueryPricesResponse

    private lateinit var kavaViewModel: KavaViewModel

    private lateinit var mintListAdapter: MintListAdapter

    private var mintParam: GenesisProto.Params? = null
    private val myCollateralParams: MutableList<CollateralParam> = mutableListOf()
    private val otherCollateralParams: MutableList<CollateralParam> = mutableListOf()
    private var myCdps: MutableList<CDPResponse>? = mutableListOf()

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            selectedChain: CosmosLine, priceFeed: QueryProto.QueryPricesResponse?
        ): MintListFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putSerializable("priceFeed", priceFeed)
            }
            val fragment = MintListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMintListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initViewModel()
        setUpMintParamObserve()
        setUpMyCdpObserve()
        setUpClickAction()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.apply {
                getParcelable("selectedChain", CosmosLine::class.java)?.let { selectedChain = it }
                getSerializable(
                    "priceFeed", QueryProto.QueryPricesResponse::class.java
                )?.let { priceFeed = it }
            }

        } else {
            arguments?.apply {
                (getParcelable("selectedChain") as? CosmosLine)?.let {
                    selectedChain = it
                }
                (getSerializable("priceFeed") as? QueryProto.QueryPricesResponse)?.let {
                    priceFeed = it
                }
            }
        }
    }

    private fun initViewModel() {
        val kavaRepository = KavaRepositoryImpl()
        val kavaViewModelProviderFactory = KavaViewModelProviderFactory(kavaRepository)
        kavaViewModel =
            ViewModelProvider(this, kavaViewModelProviderFactory)[KavaViewModel::class.java]

//        kavaViewModel.mintParam(getChannel(selectedChain))
    }

    private fun setUpMintParamObserve() {
        kavaViewModel.mintParamResult.observe(viewLifecycleOwner) { response ->
            response?.let { params ->
                mintParam = params.params
//                kavaViewModel.myCdp(getChannel(selectedChain), selectedChain.address)
            }
        }
    }

    private fun setUpMyCdpObserve() {
        binding.apply {
            kavaViewModel.myCdpResult.observe(viewLifecycleOwner) { response ->
                loading.visibility = View.GONE
                myCdps = response?.cdpsList
                mintParam?.collateralParamsList?.forEach { collateralParam ->
                    if ((response?.cdpsList?.count { it.type == collateralParam.type } ?: 0) > 0) {
                        myCollateralParams.add(collateralParam)
                    } else {
                        otherCollateralParams.add(collateralParam)
                    }
                }
                initRecyclerView()
            }
        }
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            mintListAdapter = MintListAdapter(
                requireContext(),
                priceFeed,
                myCollateralParams,
                otherCollateralParams,
                myCdps,
                mintClickAction
            )
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mintListAdapter
            mintListAdapter.submitList(mintParam?.collateralParamsList)
        }
    }

    private fun setUpClickAction() {
        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private val mintClickAction = object : MintListAdapter.ClickListener {
        override fun myMintClick(mintType: String?) {
            if (isClickable) {
                isClickable = false

                MintOptionFragment(selectedChain, mintType, null, mintOptionClickAction, null).show(
                    requireActivity().supportFragmentManager, MintOptionFragment::class.java.name
                )

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 300)
            }
        }

        override fun otherMintClick(mintType: String?) {
            handleOneClickWithDelay(
                CreateMintFragment.newInstance(
                    selectedChain,
                    mintParam?.collateralParamsList?.firstOrNull { it.type == mintType },
                    priceFeed
                )
            )
        }
    }

    private val mintOptionClickAction = object : MintClickListener {
        override fun mintAction(mintType: String?, mintActionType: MintActionType) {
            handleOneClickWithDelay(
                MintActionFragment.newInstance(selectedChain,
                    mintActionType,
                    mintParam?.collateralParamsList?.firstOrNull { it.type == mintType },
                    myCdps?.firstOrNull { it.type == mintType })
            )
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

interface MintClickListener {
    fun mintAction(mintType: String?, mintActionType: MintActionType)
}