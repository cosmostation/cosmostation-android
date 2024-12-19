package wannabit.io.cosmostaion.ui.tx.info.major

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.majorClass.ChainNamada
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.databinding.FragmentStakingInfoBinding
import wannabit.io.cosmostaion.ui.tx.info.OptionType

class NamadaUnbondingInfoFragment : Fragment() {

    private var _binding: FragmentStakingInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var namadaBondingAdapter: NamadaBondingInfoAdapter

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): NamadaUnbondingInfoFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = NamadaUnbondingInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStakingInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setUpUnBondingInfo()
        refreshData()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", BaseChain::class.java)
                ?.let { selectedChain = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                selectedChain = it
            }
        }

        binding.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                val validators = (selectedChain as ChainNamada).namadaFetcher?.namadaValidators
                    ?: mutableListOf()
                val unBondings =
                    (selectedChain as ChainNamada).namadaFetcher?.namadaUnBond ?: mutableListOf()
                val withdraws =
                    (selectedChain as ChainNamada).namadaFetcher?.namadaWithdraw ?: mutableListOf()

                withContext(Dispatchers.Main) {
                    refresher.isRefreshing = false
                    if (unBondings.isEmpty() && withdraws.isEmpty()) {
                        recycler.visibility = View.GONE
                        emptyLayout.visibility = View.VISIBLE

                    } else {
                        recycler.visibility = View.VISIBLE
                        emptyLayout.visibility = View.GONE
                        namadaBondingAdapter = NamadaBondingInfoAdapter(
                            selectedChain,
                            validators,
                            mutableListOf(),
                            unBondings,
                            withdraws,
                            OptionType.UNSTAKE,
                            selectClickAction
                        )
                        recycler.setHasFixedSize(true)
                        recycler.layoutManager = LinearLayoutManager(requireContext())
                        recycler.adapter = namadaBondingAdapter
                    }
                }
            }
        }
    }

    private fun refreshData() {
        binding.refresher.setOnRefreshListener {
            if (selectedChain.fetchState == FetchState.BUSY) {
                binding.refresher.isRefreshing = false
            } else {
                BaseData.baseAccount?.let { account ->
                    selectedChain.fetchState = FetchState.IDLE
                    ApplicationViewModel.shared.loadChainData(selectedChain, account.id, false)
                }
            }
        }
    }

    private val selectClickAction = object : NamadaBondingInfoAdapter.ClickListener {
        override fun selectStakingAction() {
            TODO("Not yet implemented")
        }

        override fun selectUnStakingCancelAction() {
            TODO("Not yet implemented")
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

    private fun setUpUnBondingInfo() {

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}