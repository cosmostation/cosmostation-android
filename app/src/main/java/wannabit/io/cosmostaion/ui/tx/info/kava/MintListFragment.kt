package wannabit.io.cosmostaion.ui.tx.info.kava

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kava.cdp.v1beta1.GenesisProto
import com.kava.cdp.v1beta1.GenesisProto.CollateralParam
import com.kava.cdp.v1beta1.QueryProto.CDPResponse
import com.kava.pricefeed.v1beta1.QueryProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.data.repository.chain.KavaRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentMintListBinding
import wannabit.io.cosmostaion.ui.dialog.tx.kava.MintOptionFragment
import wannabit.io.cosmostaion.ui.tx.step.kava.CreateMintFragment
import wannabit.io.cosmostaion.ui.tx.step.kava.MintActionFragment
import wannabit.io.cosmostaion.ui.tx.step.kava.MintActionType
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModelProviderFactory

class MintListFragment(
    private val selectedChain: CosmosLine,
    private val priceFeed: QueryProto.QueryPricesResponse?
) : Fragment() {

    private var _binding: FragmentMintListBinding? = null
    private val binding get() = _binding!!

    private lateinit var kavaViewModel: KavaViewModel

    private lateinit var mintListAdapter: MintListAdapter

    private var mintParam: GenesisProto.Params? = null
    private val myCollateralParams: MutableList<CollateralParam> = mutableListOf()
    private val otherCollateralParams: MutableList<CollateralParam> = mutableListOf()
    private var myCdps: MutableList<CDPResponse>? = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMintListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
        clickAction()
    }

    private fun initViewModel() {
        val kavaRepository = KavaRepositoryImpl()
        val kavaViewModelProviderFactory = KavaViewModelProviderFactory(kavaRepository)
        kavaViewModel =
            ViewModelProvider(this, kavaViewModelProviderFactory)[KavaViewModel::class.java]

        kavaViewModel.mintParam(getChannel(selectedChain))
    }

    private fun initView() {
        binding.apply {
            kavaViewModel.mintParamResult.observe(viewLifecycleOwner) { response ->
                response?.let { params ->
                    mintParam = params.params
                    kavaViewModel.myCdp(getChannel(selectedChain), selectedChain.address)
                }
            }

            kavaViewModel.myCdpResult.observe(viewLifecycleOwner) { response ->
                loading.visibility = View.GONE
                myCdps = response?.cdpsList
                mintParam?.collateralParamsList?.forEach { collateralParam ->
                    if ((response?.cdpsList?.count { it.type == collateralParam.type }
                            ?: 0) > 0
                    ) {
                        myCollateralParams.add(collateralParam)
                    } else {
                        otherCollateralParams.add(collateralParam)
                    }
                }
                mintListAdapter = MintListAdapter(
                    requireContext(),
                    priceFeed,
                    myCollateralParams,
                    otherCollateralParams,
                    response?.cdpsList,
                    mintClickAction
                )
                recycler.setHasFixedSize(true)
                recycler.layoutManager = LinearLayoutManager(requireContext())
                recycler.adapter = mintListAdapter
                mintListAdapter.submitList(mintParam?.collateralParamsList)
            }
        }
    }

    private fun clickAction() {
        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private val mintClickAction = object : MintListAdapter.ClickListener {
        var isClickable = true
        override fun myMintClick(mintType: String?) {
            if (isClickable) {
                isClickable = false
                val bottomSheet = MintOptionFragment(selectedChain, mintType, null, mintOptionClickAction, null)
                bottomSheet.show(requireActivity().supportFragmentManager, MintOptionFragment::class.java.name)

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)
            }
        }

        override fun otherMintClick(mintType: String?) {
            if (!selectedChain.isTxFeePayable(requireContext())) {
                requireContext().makeToast(R.string.error_not_enough_fee)
                return
            }

            if (isClickable) {
                isClickable = false
                val bottomSheet = CreateMintFragment(
                    selectedChain,
                    mintParam?.collateralParamsList?.firstOrNull { it.type == mintType },
                    priceFeed
                )
                bottomSheet.show(requireActivity().supportFragmentManager, CreateMintFragment::class.java.name)

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)
            }
        }
    }

    private val mintOptionClickAction = object : MintClickListener {
        var isClickable = true
        override fun mintDeposit(mintType: String?) {
            if (!selectedChain.isTxFeePayable(requireContext())) {
                requireContext().makeToast(R.string.error_not_enough_fee)
                return
            }

            if (isClickable) {
                isClickable = false
                val bottomSheet = MintActionFragment(
                    selectedChain,
                    MintActionType.DEPOSIT,
                    mintParam?.collateralParamsList?.firstOrNull { it.type == mintType },
                    myCdps?.firstOrNull { it.type == mintType }
                )
                bottomSheet.show(requireActivity().supportFragmentManager, MintActionFragment::class.java.name)

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)
            }
        }

        override fun mintWithdraw(mintType: String?) {
            if (!selectedChain.isTxFeePayable(requireContext())) {
                requireContext().makeToast(R.string.error_not_enough_fee)
                return
            }

            if (isClickable) {
                isClickable = false
                val bottomSheet = MintActionFragment(
                    selectedChain,
                    MintActionType.WITHDRAW,
                    mintParam?.collateralParamsList?.firstOrNull { it.type == mintType },
                    myCdps?.firstOrNull { it.type == mintType }
                )
                bottomSheet.show(requireActivity().supportFragmentManager, MintActionFragment::class.java.name)

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)
            }
        }

        override fun mintBorrow(mintType: String?) {
            if (!selectedChain.isTxFeePayable(requireContext())) {
                requireContext().makeToast(R.string.error_not_enough_fee)
                return
            }

            if (isClickable) {
                isClickable = false
                val bottomSheet = MintActionFragment(
                    selectedChain,
                    MintActionType.BORROW,
                    mintParam?.collateralParamsList?.firstOrNull { it.type == mintType },
                    myCdps?.firstOrNull { it.type == mintType }
                )
                bottomSheet.show(requireActivity().supportFragmentManager, MintActionFragment::class.java.name)

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)
            }
        }

        override fun mintRepay(mintType: String?) {
            if (!selectedChain.isTxFeePayable(requireContext())) {
                requireContext().makeToast(R.string.error_not_enough_fee)
                return
            }

            if (isClickable) {
                isClickable = false
                val bottomSheet = MintActionFragment(
                    selectedChain,
                    MintActionType.REPAY,
                    mintParam?.collateralParamsList?.firstOrNull { it.type == mintType },
                    myCdps?.firstOrNull { it.type == mintType }
                )
                bottomSheet.show(requireActivity().supportFragmentManager, MintActionFragment::class.java.name)

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

interface MintClickListener {
    fun mintDeposit(mintType: String?)
    fun mintWithdraw(mintType: String?)
    fun mintBorrow(mintType: String?)
    fun mintRepay(mintType: String?)
}