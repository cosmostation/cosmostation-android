package wannabit.io.cosmostaion.ui.main.chain.evm

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentEcoSystemBinding
import wannabit.io.cosmostaion.ui.main.dapp.DappActivity
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModelProviderFactory

class EvmEcoSystemFragment : Fragment() {

    private var _binding: FragmentEcoSystemBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedEvmChain: EthereumLine

    private lateinit var walletViewModel: WalletViewModel

    private lateinit var evmEcoSystemAdapter: EvmEcoSystemAdapter

    companion object {
        @JvmStatic
        fun newInstance(selectedEvmChain: EthereumLine): EvmEcoSystemFragment {
            val args = Bundle().apply {
                putParcelable("selectedEvmChain", selectedEvmChain)
            }
            val fragment = EvmEcoSystemFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEcoSystemBinding.inflate(layoutInflater, container, false)
        initViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpEcoSystemInfo()
    }

    private fun initViewModel() {
        val walletRepository = WalletRepositoryImpl()
        val walletViewModelProviderFactory = WalletViewModelProviderFactory(walletRepository)
        walletViewModel =
            ViewModelProvider(this, walletViewModelProviderFactory)[WalletViewModel::class.java]

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedEvmChain", EthereumLine::class.java)
                ?.let { selectedEvmChain = it }
        } else {
            (arguments?.getParcelable("selectedEvmChain") as? EthereumLine)?.let {
                selectedEvmChain = it
            }
        }

        walletViewModel.ecoSystemList(selectedEvmChain.apiName)
    }

    private fun updateView(infos: MutableList<JsonObject>) {
        binding.apply {
            loading.visibility = View.GONE
            recycler.visibility = View.VISIBLE

            evmEcoSystemAdapter = EvmEcoSystemAdapter(selectedEvmChain)
            recycler.setHasFixedSize(true)
            recycler.layoutManager = GridLayoutManager(requireContext(), 2)
            recycler.adapter = evmEcoSystemAdapter
            evmEcoSystemAdapter.submitList(infos)

            evmEcoSystemAdapter.setOnItemClickListener {
                Intent(requireActivity(), DappActivity::class.java).apply {
                    putExtra("dapp", it)
                    startActivity(this)
                }
            }
        }
    }

    private fun setUpEcoSystemInfo() {
        walletViewModel.ecoSystemListResult.observe(viewLifecycleOwner) { infos ->
            runCatching {
                if (infos?.isNotEmpty() == true) {
                    updateView(infos)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}