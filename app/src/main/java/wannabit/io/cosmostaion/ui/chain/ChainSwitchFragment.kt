package wannabit.io.cosmostaion.ui.chain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.databinding.BottomSheetSelectorBinding

class ChainSwitchFragment(private val title: String, private val items: List<String>, private val listener: (position: Int, item: String) -> Unit) : BottomSheetDialogFragment() {
    private lateinit var adapter: ChainSwitchAdapter
    private lateinit var binding: BottomSheetSelectorBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = BottomSheetSelectorBinding.inflate(layoutInflater)
        setupViews()
        setupRecyclerView()
        return binding.root
    }

    private fun setupViews() {
        binding.title.text = title
    }

    private fun setupRecyclerView() {
        adapter = ChainSwitchAdapter(requireContext(), items) {
            dismiss()
            listener(items.indexOf(it), it)
        }
        binding.recycler.layoutManager = LinearLayoutManager(activity)
        binding.recycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}