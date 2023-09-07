package wannabit.io.cosmostaion.ui.line.ethereum

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.databinding.ActivityLineEthereumBinding
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel

class EthereumLineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLineEthereumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLineEthereumBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setupViews()
    }

//    private fun setupViews() {
//        val chain = intent.getSerializableExtra("chain") as? EthereumLine
//        chain?.let { chain ->
//            binding.chain.text = chain.chainName
//            ApplicationViewModel.shared.currentWalletLiveData.value?.let { wallet ->
//                wallet.seed?.let { binding.address.text = chain.getAddress(it) }
//            }
//        } ?: run { finish() }
//    }
}