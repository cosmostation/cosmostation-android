package wannabit.io.cosmostaion.ui.main.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.FragmentStyleBinding
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment

class StyleFragment : BaseTxFragment() {

    private var _binding: FragmentStyleBinding? = null
    private val binding get() = _binding!!

    private lateinit var styleAdapter: StyleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStyleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.apply {
            selectTitle.text = getString(R.string.title_select_style)

            styleAdapter =
                StyleAdapter(requireContext())
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = styleAdapter
            styleAdapter.submitList(listOf("1. Simple style", "2. Pro style"))

            styleAdapter.setOnItemClickListener {
                val bundle = Bundle()
                bundle.putInt("style", it)
                parentFragmentManager.setFragmentResult("style", bundle)
                dismiss()
            }
        }
    }
}