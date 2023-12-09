package wannabit.io.cosmostaion.ui.dialog.tx.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmos.base.v1beta1.CoinProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.databinding.FragmentCommonBottomBinding
import wannabit.io.cosmostaion.ui.dialog.tx.AssetSelectListener

class AssetSelectFragment(
    private val swapAssets: MutableList<Asset>,
    private val swapBalance: MutableList<CoinProto.Coin>?,
    private val assetSelectType: AssetSelectType,
    val listener: AssetSelectListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentCommonBottomBinding? = null
    private val binding get() = _binding!!

    private lateinit var assetSelectAdapter: AssetSelectAdapter

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
            if (assetSelectType == AssetSelectType.SWAP_INPUT) {
                selectTitle.text = getString(R.string.title_select_input_asset)
            } else {
                selectTitle.text = getString(R.string.title_select_output_asset)
            }

            assetSelectAdapter = AssetSelectAdapter(swapBalance)
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = assetSelectAdapter
            swapAssets.sortWith { o1, o2 ->
                when {
                    o1.symbol == "ATOM" -> -1
                    o2.symbol == "ATOM" -> 1
                    o1.symbol.toString() < o2.symbol.toString() -> -1
                    o1.symbol.toString() > o2.symbol.toString() -> 1
                    else -> 0
                }
            }
            assetSelectAdapter.submitList(swapAssets)

            assetSelectAdapter.setOnItemClickListener {
                listener.select(it)
                dismiss()
            }
        }
    }
}

enum class AssetSelectType { SWAP_INPUT, SWAP_OUTPUT }