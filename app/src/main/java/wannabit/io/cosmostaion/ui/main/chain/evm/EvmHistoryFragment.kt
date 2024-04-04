package wannabit.io.cosmostaion.ui.main.chain.evm

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentEvmHistoryBinding

class EvmHistoryFragment : Fragment() {

    private var _binding: FragmentEvmHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedEvmChain: EthereumLine

    companion object {
        @JvmStatic
        fun newInstance(selectedEvmChain: EthereumLine): EvmHistoryFragment {
            val args = Bundle().apply {
                putParcelable("selectedEvmChain", selectedEvmChain)
            }
            val fragment = EvmHistoryFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEvmHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedEvmChain", EthereumLine::class.java)
                    ?.let { selectedEvmChain = it }
            } else {
                (arguments?.getParcelable("selectedEvmChain") as? EthereumLine)?.let {
                    selectedEvmChain = it
                }
            }
            loading.visibility = View.GONE
        }
    }

    private fun setUpClickAction() {
        binding.btnCheckHistory.setOnClickListener {
            selectedEvmChain.explorerAccount()?.let { url ->
                startActivity(Intent(Intent.ACTION_VIEW, url))
                Prefs.foreToBack = false

            } ?: run {
                return@setOnClickListener
            }
        }
    }
}