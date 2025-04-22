package wannabit.io.cosmostaion.ui.main.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.FragmentStyleBinding
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment

class ThemeFragment : BaseTxFragment() {

    private var _binding: FragmentStyleBinding? = null
    private val binding get() = _binding!!

    private lateinit var themeAdapter: ThemeAdapter

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
            selectTitle.text = getString(R.string.title_select_theme)

            themeAdapter =
                ThemeAdapter(requireContext())
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = themeAdapter
            themeAdapter.submitList(listOf("1. " + getString(R.string.title_dark_theme) + " Theme", "2. " + getString(
                R.string.title_cosmic_theme) + " Theme"))

            themeAdapter.setOnItemClickListener {
                val bundle = Bundle()
                bundle.putInt("theme", it)
                parentFragmentManager.setFragmentResult("theme", bundle)
                dismiss()
            }
        }
    }
}