package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.databinding.FragmentChainManageBinding

class ChainManageFragment : Fragment() {

    private var _binding: FragmentChainManageBinding? = null
    private val binding get() = _binding!!

    private lateinit var chainManageAdapter: ChainManageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChainManageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        clickAction()
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            CoroutineScope(Dispatchers.IO).launch {
                val dpCosmosLines: MutableList<CosmosLine> = mutableListOf()
                dpCosmosLines.clear()

                dpCosmosLines.addAll(allCosmosLines().filter { it.isDefault }
                    .distinctBy { it.name })
                binding.headerCnt.text = dpCosmosLines.count().toString()

                withContext(Dispatchers.Main) {
                    chainManageAdapter = ChainManageAdapter()
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = chainManageAdapter
                    chainManageAdapter.submitList(dpCosmosLines)
                }
            }
        }
    }

    private fun clickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}