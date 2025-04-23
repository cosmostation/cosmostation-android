package wannabit.io.cosmostaion.ui.tx.option.validator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmos.staking.v1beta1.StakingProto.Validator
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.initia.mstaking.v1.StakingProto
import com.zrchain.validation.HybridValidationProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainInitia
import wannabit.io.cosmostaion.chain.cosmosClass.ChainZenrock
import wannabit.io.cosmostaion.databinding.FragmentCommonBottomBinding

class ValidatorFragment(
    private val selectedChain: BaseChain, val listener: ValidatorListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentCommonBottomBinding? = null
    private val binding get() = _binding!!

    private lateinit var validatorAdapter: ValidatorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommonBottomBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.apply {
            selectTitle.text = getString(R.string.title_select_validator)
            validatorAdapter = ValidatorAdapter(selectedChain)
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = validatorAdapter

            when (selectedChain) {
                is ChainInitia -> {
                    val validators: MutableList<StakingProto.Validator> = mutableListOf()
                    val delegations = selectedChain.initiaFetcher()?.initiaDelegations
                    delegations?.forEach { delegation ->
                        selectedChain.initiaFetcher()?.initiaValidators?.firstOrNull { it.operatorAddress == delegation.delegation.validatorAddress }
                            ?.let { validator ->
                                validators.add(validator)
                            }
                    }
                    validatorAdapter.submitList(validators as List<Any>)
                }

                is ChainZenrock -> {
                    val validators: MutableList<HybridValidationProto.ValidatorHV> = mutableListOf()
                    val delegations = selectedChain.zenrockFetcher()?.zenrockDelegations
                    delegations?.forEach { delegation ->
                        selectedChain.zenrockFetcher()?.zenrockValidators?.firstOrNull { it.operatorAddress == delegation.delegation.validatorAddress }
                            ?.let { validator ->
                                validators.add(validator)
                            }
                    }
                    validatorAdapter.submitList(validators as List<Any>)
                }

                else -> {
                    val validators: MutableList<Validator> = mutableListOf()
                    val delegations = selectedChain.cosmosFetcher?.cosmosDelegations
                    delegations?.forEach { delegation ->
                        selectedChain.cosmosFetcher?.cosmosValidators?.firstOrNull { it.operatorAddress == delegation.delegation.validatorAddress }
                            ?.let { validator ->
                                validators.add(validator)
                            }
                    }
                    validatorAdapter.submitList(validators as List<Any>)
                }
            }

            validatorAdapter.setOnItemClickListener {
                listener.select(it)
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

interface ValidatorListener {
    fun select(validatorAddress: String)
}