package wannabit.io.cosmostaion.ui.main.chain.evm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.databinding.FragmentAssetBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.CoinAdapter
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel

class AssetFragment : Fragment() {

    private var _binding: FragmentAssetBinding? = null
    private val binding get() = _binding!!

    private lateinit var coinAdapter: CoinAdapter

    private lateinit var selectedEvmChain: EthereumLine

    private val walletViewModel: WalletViewModel by activityViewModels()

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedEvmChain: EthereumLine): AssetFragment {
            val args = Bundle().apply {
                putParcelable("selectedEvmChain", selectedEvmChain)
            }
            val fragment = AssetFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAssetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}