package wannabit.io.cosmostaion.ui.option.tx.general

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

interface AssetSelectListener {
    fun select(denom: String)
}

class AssetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCommonBottomBinding? = null
    private val binding get() = _binding!!

    private lateinit var fromChain: BaseChain
    private var feeDatas: MutableList<FeeData>? = mutableListOf()

    private lateinit var assetAdapter: AssetAdapter

    companion object {
        @JvmStatic
        fun newInstance(
            fromChain: BaseChain,
            feeDatas: MutableList<FeeData>,
            listener: AssetSelectListener,
        ): AssetFragment {
            val args = Bundle().apply {
                putParcelable("fromChain", fromChain)
                putParcelableArrayList("feeDatas", ArrayList(feeDatas))
            }
            val fragment = AssetFragment()
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
            arguments?.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getParcelable("fromChain", BaseChain::class.java)?.let {
                        fromChain = it
                    }

                } else {
                    (getParcelable("fromChain") as? BaseChain)?.let {
                        fromChain = it
                    }
                }
                feeDatas = getParcelableArrayList("feeDatas")
            }
            selectTitle.text = getString(R.string.title_fee_select)

            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            assetAdapter = AssetAdapter(fromChain)
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = assetAdapter
            assetAdapter.submitList(feeDatas)

            assetAdapter.setOnItemClickListener {
                assetSelectListener?.select(it)
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}