package wannabit.io.cosmostaion.ui.tx.info

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmos.staking.v1beta1.StakingProto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.databinding.DialogChangeRewardAddressBinding
import wannabit.io.cosmostaion.databinding.FragmentStakeInfoBinding
import wannabit.io.cosmostaion.ui.dialog.tx.StakingOptionFragment
import wannabit.io.cosmostaion.ui.tx.step.ChangeRewardAddressFragment
import wannabit.io.cosmostaion.ui.tx.step.StakingFragment

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
                    if (stakingInfoList.isNotEmpty()) {
                        recycler.visibility = View.VISIBLE
                        emptyStake.visibility = View.GONE

                        stakingInfoAdapter =
                            StakingInfoAdapter(requireContext(), selectedChain, rewardAddress, validators, delegations, unBondings, selectClickAction)
                        recycler.setHasFixedSize(true)
                        recycler.layoutManager = LinearLayoutManager(requireContext())
                        recycler.adapter = stakingInfoAdapter

                    } else {
                        emptyStake.visibility = View.VISIBLE
                        recycler.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun clickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            btnChangeRewardAddress.setOnClickListener {
                if (!selectedChain.isTxFeePayable(requireContext())) {
                    requireContext().makeToast(R.string.error_not_enough_fee)
                    return@setOnClickListener
                }
                showChangeRewardAddress()
            }

            btnStake.setOnClickListener {
                if (!selectedChain.isTxFeePayable(requireContext())) {
                    requireContext().makeToast(R.string.error_not_enough_fee)
                    return@setOnClickListener
                }
                val bottomSheet = StakingFragment(selectedChain, null)
                bottomSheet.show(requireActivity().supportFragmentManager, StakingFragment::class.java.name)
            }
        }
    }

    private fun showChangeRewardAddress() {
        var isClickable = true
        val binding = DialogChangeRewardAddressBinding.inflate(requireActivity().layoutInflater)
        val alertDialog = AlertDialog.Builder(requireContext(), R.style.AppTheme_AlertDialogTheme)
            .setView(binding.root)

        val dialog = alertDialog.create()
        dialog.show()

        binding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        binding.btnContinue.setOnClickListener {
            if (isClickable) {
                isClickable = false
                val bottomSheet = ChangeRewardAddressFragment(selectedChain)
                bottomSheet.show(requireActivity().supportFragmentManager, ChangeRewardAddressFragment::class.java.name)

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)
            }
            dialog.dismiss()
        }
    }

    private val selectClickAction = object : StakingInfoAdapter.ClickListener {
        var isClickable = true
        override fun selectStakingAction(validator: StakingProto.Validator?) {
            val bottomSheet = StakingOptionFragment(selectedChain, validator, null, OptionType.STAKE)
            if (isClickable) {
                isClickable = false
                bottomSheet.show(requireActivity().supportFragmentManager, StakingOptionFragment::class.java.name)

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)
            }
        }

        override fun selectUnStakingCancelAction(unBondingEntry: UnBondingEntry?) {
            val bottomSheet = StakingOptionFragment(selectedChain, null, unBondingEntry, OptionType.UNSTAKE)
            if (isClickable) {
                isClickable = false
                bottomSheet.show(requireActivity().supportFragmentManager, StakingOptionFragment::class.java.name)

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

data class UnBondingEntry(
    val validatorAddress: String?,
    val entry: StakingProto.UnbondingDelegationEntry?
)

enum class OptionType { STAKE, UNSTAKE }