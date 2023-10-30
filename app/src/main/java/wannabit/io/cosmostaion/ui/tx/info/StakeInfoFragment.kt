package wannabit.io.cosmostaion.ui.tx.info

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmos.staking.v1beta1.StakingProto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.FragmentStakeInfoBinding
import wannabit.io.cosmostaion.ui.dialog.tx.StakingOptionFragment
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel

class StakeInfoFragment(private val selectedChain: CosmosLine) : Fragment() {

    private var _binding: FragmentStakeInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var stakingInfoAdapter: StakingInfoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStakeInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        clickAction()
    }

    private fun initView() {
        binding.apply {
            CoroutineScope(Dispatchers.IO).launch {
                val rewardAddress = selectedChain.rewardAddress
                var delegations = selectedChain.cosmosDelegations
                val validators = selectedChain.cosmosValidators
                val unBondings = mutableListOf<UnBondingEntry>()
                selectedChain.cosmosUnbondings.forEach { unbonding ->
                    unbonding.entriesList.forEach { entry ->
                        unBondings.add(UnBondingEntry(unbonding.validatorAddress, entry))
                    }
                }

                val cosmostationValAddress = validators.firstOrNull { it.description.moniker == "Cosmostation" }?.operatorAddress
                val tempDelegations = delegations.toMutableList()
                tempDelegations.sortWith { o1, o2 ->
                    when {
                        o1.delegation.validatorAddress == cosmostationValAddress -> -1
                        o2.delegation.validatorAddress == cosmostationValAddress -> 1
                        o1.balance.amount.toDouble() > o2.balance.amount.toDouble() -> -1
                        else -> 1
                    }
                }
                delegations = tempDelegations

                unBondings.sortWith { o1, o2 ->
                    when {
                        o1.entry!!.creationHeight < o2.entry!!.creationHeight -> -1
                        else -> 1
                    }
                }

                val stakingInfoList = mutableListOf<Any>()
                stakingInfoList.addAll(delegations)
                stakingInfoList.addAll(unBondings)

                withContext(Dispatchers.Main) {
                    stakingInfoAdapter =
                        StakingInfoAdapter(requireContext(), selectedChain, rewardAddress, validators, delegations, unBondings, selectClickAction)
                    recycler.setHasFixedSize(true)
                    recycler.layoutManager = LinearLayoutManager(requireContext())
                    recycler.adapter = stakingInfoAdapter
                }
            }
        }
    }

    private fun clickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    private val selectClickAction = object : StakingInfoAdapter.ClickListener {
        var isClickable = true
        override fun selectStakingAction(validator: StakingProto.Validator?) {
            val bottomSheet = StakingOptionFragment(selectedChain, validator)
            if (isClickable) {
                isClickable = false
                bottomSheet.show(requireActivity().supportFragmentManager, StakingOptionFragment(selectedChain, validator)::class.java.name)

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)
            }
        }

        override fun checkPrivateAction(account: BaseAccount) {
            ApplicationViewModel.shared.checkPwPrivateResult.observe(viewLifecycleOwner) {
                lifecycleScope.launch {
                    delay(300)
                    withContext(Dispatchers.Main) {

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

data class UnBondingEntry(
    val validatorAddress: String?,
    val entry: StakingProto.UnbondingDelegationEntry?
)