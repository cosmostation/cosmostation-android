package wannabit.io.cosmostaion.ui.tx.option.general

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmos.base.v1beta1.CoinProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.databinding.FragmentCommonBottomBinding

interface BaseFeeAssetSelectListener {
    fun select(denom: String)
}

class BaseFeeAssetFragment(
    private val fromChain: BaseChain,
    private val baseFeeDatas: MutableList<CoinProto.DecCoin>?,
    val listener: BaseFeeAssetSelectListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentCommonBottomBinding? = null
    private val binding get() = _binding!!

    private lateinit var baseFeeAssetAdapter: BaseFeeAssetAdapter

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
            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            val filteredFeeDatas = baseFeeDatas?.mapNotNull { feeData ->
                feeData.denom?.let {
                    BaseData.getAsset(fromChain.apiName, it)?.let { feeData }
                }
            }?.toMutableList()
            baseFeeAssetAdapter = BaseFeeAssetAdapter(fromChain)
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = baseFeeAssetAdapter
            baseFeeAssetAdapter.submitList(filteredFeeDatas)

            baseFeeAssetAdapter.setOnItemClickListener {
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