package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.databinding.FragmentReceiveBinding

class ReceiveFragment : Fragment() {

    private var _binding: FragmentReceiveBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var receiveAdapter: ReceiveAdapter

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): ReceiveFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = ReceiveFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReceiveBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", BaseChain::class.java)
                ?.let { selectedChain = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                selectedChain = it
            }
        }

        binding.apply {
            BaseData.baseAccount?.let { account ->
                receiveAdapter = ReceiveAdapter(requireContext(), account, selectedChain)
                recycler.setHasFixedSize(true)
                recycler.layoutManager = LinearLayoutManager(requireContext())
                recycler.adapter = receiveAdapter
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}