package wannabit.io.cosmostaion.ui.tx.option.general

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.data.model.res.FeeData
import wannabit.io.cosmostaion.databinding.FragmentCommonBottomBinding
import wannabit.io.cosmostaion.ui.tx.genTx.SendAssetType

class FeeAssetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCommonBottomBinding? = null
    private val binding get() = _binding!!

    private lateinit var fromChain: BaseChain
    private var feeDatas: MutableList<FeeData>? = mutableListOf()
    private lateinit var sendAssetType: SendAssetType

    private lateinit var feeAssetAdapter: FeeAssetAdapter

    companion object {
        @JvmStatic
        fun newInstance(
            fromChain: BaseChain,
            feeDatas: MutableList<FeeData>,
            sendAssetType: SendAssetType,
            listener: AssetSelectListener
        ): FeeAssetFragment {
            val args = Bundle().apply {
                putParcelable("fromChain", fromChain)
                putParcelableArrayList("feeDatas", ArrayList(feeDatas))
                putSerializable("sendAssetType", sendAssetType)
            }
            val fragment = FeeAssetFragment()
            fragment.arguments = args
            fragment.assetSelectListener = listener
            return fragment
        }
    }

    private var assetSelectListener: AssetSelectListener? = null

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
            arguments?.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getParcelable(
                        "fromChain", BaseChain::class.java
                    )?.let { fromChain = it }
                    getSerializable(
                        "sendAssetType", SendAssetType::class.java
                    )?.let { sendAssetType = it }

                } else {
                    (getParcelable("fromChain") as? BaseChain)?.let {
                        fromChain = it
                    }
                    (getSerializable("sendAssetType") as? SendAssetType)?.let {
                        sendAssetType = it
                    }
                }
                feeDatas = getParcelableArrayList("feeDatas")
            }

            feeAssetAdapter = FeeAssetAdapter(fromChain)
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = feeAssetAdapter
            feeAssetAdapter.submitList(feeDatas)

            feeAssetAdapter.setOnItemClickListener {
                assetSelectListener?.select(it)
                dismiss()
            }
        }
    }
}