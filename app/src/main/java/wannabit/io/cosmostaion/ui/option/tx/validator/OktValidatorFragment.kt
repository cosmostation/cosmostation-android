package wannabit.io.cosmostaion.ui.option.tx.validator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.OktFetcher
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.databinding.FragmentOktValidatorBinding
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment

class OktValidatorFragment(
    private val selectedChain: BaseChain,
    private val myValidators: MutableList<JsonObject>,
    val listener: OkValidatorListener
) : BaseTxFragment() {

    private var _binding: FragmentOktValidatorBinding? = null
    private val binding get() = _binding!!

    private lateinit var oktValidatorAdapter: OktValidatorAdapter

    private var tempValidators: MutableList<JsonObject> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOktValidatorBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    override fun bottomSheetDialogDefaultHeight(windowHeight: Int): Int {
        return windowHeight * 18 / 20
    }

    private fun initView() {
        binding.apply {
            tempValidators.clear()
            tempValidators.addAll(myValidators)

            selectTitle.text = getString(R.string.title_select_validator)
            oktValidatorAdapter = OktValidatorAdapter(selectedChain, tempValidators)
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = oktValidatorAdapter
            oktValidatorAdapter.submitList((selectedChain as ChainOktEvm).oktFetcher?.oktValidatorInfo)
            updateView()
        }
    }

    private fun updateView() {
        binding.selectCnt.text = "(" + tempValidators.size + "/" + 30 + ")"
    }

    private fun setClickData(oktFetcher: OktFetcher?) {
        binding.apply {
            oktValidatorAdapter.setOnItemClickListener { position ->
                oktFetcher?.oktValidatorInfo?.get(position)?.let { selectValidator ->
                    if (tempValidators.map { it["operator_address"].asString }
                            .contains(selectValidator["operator_address"].asString)) {
                        if (tempValidators.size <= 1) {
                            requireContext().makeToast(R.string.error_min_1_validator_msg)
                            return@setOnItemClickListener
                        }
                        tempValidators.removeIf { it["operator_address"].asString == selectValidator["operator_address"].asString }

                    } else {
                        if (tempValidators.size >= 30) {
                            requireContext().makeToast(R.string.error_max_30_validator_msg)
                            return@setOnItemClickListener
                        }
                        tempValidators.add(selectValidator)
                    }
                    updateView()
                    oktValidatorAdapter.notifyItemChanged(position)
                }
            }
        }
    }

    private fun setUpClickAction() {
        when (selectedChain) {
            is ChainOkt996Keccak -> setClickData(selectedChain.oktFetcher)
            is ChainOktEvm -> setClickData(selectedChain.oktFetcher)
        }

        binding.btnConfirm.setOnClickListener {
            listener.select(tempValidators)
            dismiss()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

interface OkValidatorListener {
    fun select(selectValidators: MutableList<JsonObject>)
}