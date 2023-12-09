package wannabit.io.cosmostaion.ui.dialog.tx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.FragmentCommonBottomBinding

class ChainFragment(
    private val recipientAbleChains: MutableList<CosmosLine>,
    private val chainListType: ChainListType,
    val listener: ChainSelectListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentCommonBottomBinding? = null
    private val binding get() = _binding!!

    private lateinit var chainAdapter: ChainAdapter

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
            when (chainListType) {
                ChainListType.SELECT_INPUT_SWAP -> {
                    selectTitle.text = getString(R.string.title_select_input_chain)
                    recipientAbleChains.sortWith { o1, o2 ->
                        when {
                            o1.tag == "cosmos118" -> -1
                            o2.tag == "cosmos118" -> 1
                            o1.name < o2.name -> -1
                            o1.name > o2.name -> 1
                            else -> 0
                        }
                    }

                }

                ChainListType.SELECT_OUTPUT_SWAP -> {
                    selectTitle.text = getString(R.string.title_select_output_chain)
                    recipientAbleChains.sortWith { o1, o2 ->
                        when {
                            o1.tag == "cosmos118" -> -1
                            o2.tag == "cosmos118" -> 1
                            o1.name < o2.name -> -1
                            o1.name > o2.name -> 1
                            else -> 0
                        }
                    }

                }

                else -> {
                    selectTitle.text = getString(R.string.title_select_recipient_chain)
                }
            }

            chainAdapter = ChainAdapter()
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = chainAdapter
            chainAdapter.submitList(recipientAbleChains)

            chainAdapter.setOnItemClickListener {
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

interface ChainSelectListener {
    fun select(chainId: String)
}

enum class ChainListType { SELECT_TRANSFER, SELECT_INPUT_SWAP, SELECT_OUTPUT_SWAP }