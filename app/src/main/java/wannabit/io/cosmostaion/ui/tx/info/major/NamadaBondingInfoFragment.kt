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
import com.google.gson.JsonObject
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

class NamadaBondingInfoFragment : Fragment() {

    private var _binding: FragmentStakingInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var namadaBondingAdapter: NamadaBondingAdapter

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): NamadaBondingInfoFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = NamadaBondingInfoFragment()
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
        refreshData()
        setUpStakeInfo()
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
                var delegations =
                    (selectedChain as ChainNamada).namadaFetcher?.namadaBond?.get("results")?.asJsonArray?.toMutableList()
                        ?: mutableListOf()
                val validators = (selectedChain as ChainNamada).namadaFetcher?.namadaValidators
                    ?: mutableListOf()
                val cosmostationValAddress =
                    validators.firstOrNull { !it["name"].isJsonNull && it["name"].asString.lowercase() == "cosmostation" }
                        ?.get("address")?.asString

                val addressMap = mutableMapOf<String, JsonObject>()
                delegations.forEach { delegation ->
                    val address =
                        delegation.asJsonObject["validator"].asJsonObject["address"].asString
                    val delegationAmount =
                        delegation.asJsonObject["minDenomAmount"].asString.toDouble()

                    if (addressMap.containsKey(address)) {
                        addressMap[address]?.let { exist ->
                            val existAmount = exist["minDenomAmount"].asString.toDouble()
                            exist.addProperty(
                                "minDenomAmount",
                                (existAmount + delegationAmount).toString()
                            )
                        }
                    } else {
                        val addObject = JsonObject()
                        addObject.addProperty("minDenomAmount", delegationAmount.toString())
                        addObject.add("validator", delegation.asJsonObject["validator"])
                        addressMap[address] = addObject
                    }
                }
                delegations = addressMap.values.toMutableList()
                val sortedDelegations = delegations.sortedWith { o1, o2 ->
                    val o1Address = o1.asJsonObject["validator"].asJsonObject["address"].asString
                    val o2Address = o2.asJsonObject["validator"].asJsonObject["address"].asString

                    val o1VotingPower =
                        o1.asJsonObject["validator"].asJsonObject["votingPower"].asString.toDoubleOrNull()
                            ?: 0.0
                    val o2VotingPower =
                        o2.asJsonObject["validator"].asJsonObject["votingPower"].asString.toDoubleOrNull()
                            ?: 0.0

                    when {
                        o1Address == cosmostationValAddress -> -1
                        o2Address == cosmostationValAddress -> 1

                        o1VotingPower > o2VotingPower -> -1
                        else -> 0
                    }
                }
                delegations = sortedDelegations.toMutableList()

                withContext(Dispatchers.Main) {
                    refresher.isRefreshing = false
                    if (delegations.isNotEmpty()) {
                        recycler.visibility = View.VISIBLE
                        emptyLayout.visibility = View.GONE
                        namadaBondingAdapter = NamadaBondingAdapter(
                            selectedChain,
                            validators,
                            delegations,
                            OptionType.STAKE,
                            selectClickAction
                        )
                        recycler.setHasFixedSize(true)
                        recycler.layoutManager = LinearLayoutManager(requireContext())
                        recycler.adapter = namadaBondingAdapter

                    } else {
                        recycler.visibility = View.GONE
                        emptyLayout.visibility = View.VISIBLE
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

    private val selectClickAction = object : NamadaBondingAdapter.ClickListener {
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

    private fun setUpStakeInfo() {

    }
}