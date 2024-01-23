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
import com.cosmos.base.v1beta1.CoinProto.Coin
import com.cosmos.staking.v1beta1.StakingProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.data.repository.chain.KavaRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentEarnListBinding
import wannabit.io.cosmostaion.ui.dialog.tx.kava.EarnClickListener
import wannabit.io.cosmostaion.ui.dialog.tx.kava.EarnOptionFragment
import wannabit.io.cosmostaion.ui.tx.step.kava.DepositEarningFragment
import wannabit.io.cosmostaion.ui.tx.step.kava.WithdrawEarningFragment
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModelProviderFactory

class EarnListFragment : Fragment() {

    private var _binding: FragmentEarnListBinding? = null
    private val binding get() = _binding!!

    private lateinit var kavaViewModel: KavaViewModel

    private lateinit var earnListAdapter: EarnListAdapter

    private var selectedChain: CosmosLine? = null

    private val myDeposits: MutableList<Coin> = mutableListOf()

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: CosmosLine): EarnListFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = EarnListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEarnListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        setUpDepositsDataObserve()
        setUpClickAction()
    }

    private fun initViewModel() {
        selectedChain = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", CosmosLine::class.java)
        } else {
            arguments?.getParcelable("selectedChain") as? CosmosLine
        }

        val kavaRepository = KavaRepositoryImpl()
        val kavaViewModelProviderFactory = KavaViewModelProviderFactory(kavaRepository)
        kavaViewModel =
            ViewModelProvider(this, kavaViewModelProviderFactory)[KavaViewModel::class.java]

        selectedChain?.let { chain ->
            kavaViewModel.myDeposits(getChannel(chain), chain.address)
        }
    }

    private fun setUpDepositsDataObserve() {
        binding.apply {
            kavaViewModel.myDepositsResult.observe(viewLifecycleOwner) { response ->
                response?.let { myDepositsResponse ->
                    loading.visibility = View.GONE

                    myDepositsResponse.depositsList.forEach { deposit ->
                        deposit.valueList.forEach { rawCoin ->
                            if (rawCoin.denom.startsWith("bkava-")) {
                                myDeposits.add(
                                    Coin.newBuilder().setDenom(rawCoin.denom)
                                        .setAmount(rawCoin.amount).build()
                                )
                            }
                        }
                    }

                    if (myDeposits.isEmpty()) {
                        emptyLayout.visibility = View.VISIBLE
                    } else {
                        recycler.visibility = View.VISIBLE
                        initRecyclerView()
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            earnListAdapter = EarnListAdapter(requireContext(), selectedChain)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = earnListAdapter
            earnListAdapter.submitList(myDeposits)

            earnListAdapter.setOnItemClickListener { deposit ->
                val valOpAddress = deposit.denom.replace("bkava-", "")
                selectedChain?.cosmosValidators?.firstOrNull { it.operatorAddress == valOpAddress }
                    ?.let { toValidator ->
                        EarnOptionFragment.newInstance(toValidator, deposit, earnClickAction).show(
                            requireActivity().supportFragmentManager,
                            EarnOptionFragment::class.java.name
                        )
                    }
            }
        }
    }

    private val earnClickAction = object : EarnClickListener {
        override fun earnDeposit(toValidator: StakingProto.Validator) {
            setOneClickAction(DepositEarningFragment.newInstance(selectedChain, toValidator))
        }

        override fun earnWithdraw(withdrawCoin: Coin) {
            setOneClickAction(WithdrawEarningFragment.newInstance(selectedChain, withdrawCoin))
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            binding.btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            binding.btnStartEarning.setOnClickListener {
                setOneClickAction(DepositEarningFragment.newInstance(selectedChain, null))
            }
        }
    }

    private fun setOneClickAction(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        if (isClickable) {
            isClickable = false

            bottomSheetDialogFragment.show(
                requireActivity().supportFragmentManager, bottomSheetDialogFragment::class.java.name
            )

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 1000)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}