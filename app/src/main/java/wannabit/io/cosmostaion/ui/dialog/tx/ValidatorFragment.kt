package wannabit.io.cosmostaion.ui.dialog.tx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmos.staking.v1beta1.StakingProto.Validator
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.FragmentCommonBottomBinding

class ValidatorFragment(
    private val selectedChain: CosmosLine,
    val listener: ValidatorListener
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

            val validators: MutableList<Validator> = mutableListOf()
            val delegations = selectedChain.cosmosDelegations
            delegations.forEach { delegation ->
                selectedChain.cosmosValidators.firstOrNull { it.operatorAddress == delegation.delegation.validatorAddress }?.let { validator ->
                    validators.add(validator)
                }
            }

            validatorAdapter = ValidatorAdapter(selectedChain)
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = validatorAdapter
            validatorAdapter.submitList(validators)

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