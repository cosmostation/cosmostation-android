package wannabit.io.cosmostaion.ui.dialog.tx.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.data.model.res.OktValidatorResponse
import wannabit.io.cosmostaion.databinding.FragmentOktValidatorBinding
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment

class OktValidatorFragment(
    private val selectedChain: ChainOkt60,
    private val myValidators: MutableList<OktValidatorResponse>,
    val listener: OkValidatorListener
) : BaseTxFragment() {

    private var _binding: FragmentOktValidatorBinding? = null
    private val binding get() = _binding!!

    private lateinit var oktValidatorAdapter: OktValidatorAdapter

    private var tempValidators: MutableList<OktValidatorResponse> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOktValidatorBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        clickAction()
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
            oktValidatorAdapter.submitList(selectedChain.oktValidatorInfo)

            updateView()
        }
    }

    private fun updateView() {
        binding.selectCnt.text = "(" + tempValidators.size + "/" + 30 + ")"
    }

    private fun clickAction() {
        oktValidatorAdapter.setOnItemClickListener { position ->
            val selectValidator = selectedChain.oktValidatorInfo[position]
            if (tempValidators.map { it.operatorAddress }.contains(selectValidator.operatorAddress)) {
                if (tempValidators.size <= 1) {
                    requireContext().makeToast(R.string.error_min_1_validator_msg)
                    return@setOnItemClickListener
                }
                tempValidators.removeIf { it.operatorAddress == selectValidator.operatorAddress }

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
    fun select(selectValidators: MutableList<OktValidatorResponse>)
}