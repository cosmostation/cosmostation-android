package wannabit.io.cosmostaion.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.FragmentEmptyWalletBinding
import wannabit.io.cosmostaion.ui.common.selector.SelectorFragment
import wannabit.io.cosmostaion.ui.wallet.WalletCreateFragment

class EmptyWalletFragment : Fragment() {
    private lateinit var binding: FragmentEmptyWalletBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEmptyWalletBinding.inflate(layoutInflater, container, false)
        setupViews()
        return binding.root
    }

    private fun setupViews() {
        binding.createWallet.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragment_container, WalletCreateFragment()).commit()
        }

        binding.loadWallet.setOnClickListener {
            SelectorFragment("Load Wallet", listOf("Load PrivateKey", "Load Mnemonic")) { position, item ->
                when (position) {
                    0 -> {}
                    else -> {}
                }
            }.show(parentFragmentManager, SelectorFragment::class.java.name)
        }
    }
}