package wannabit.io.cosmostaion.ui.dialog.tx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.data.model.res.FeeData
import wannabit.io.cosmostaion.databinding.FragmentCommonBottomBinding

class AssetFragment(
    private val selectedChain: CosmosLine,
    private val feeData: List<FeeData>,
    val listener: AssetSelectListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentCommonBottomBinding? = null
    private val binding get() = _binding!!

    private lateinit var assetAdapter: AssetAdapter

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
            selectTitle.text = getString(R.string.title_fee_select)

            assetAdapter = AssetAdapter(selectedChain)
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = assetAdapter
            assetAdapter.submitList(feeData)

            assetAdapter.setOnItemClickListener {
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

interface AssetSelectListener {
    fun select(denom: String)
}