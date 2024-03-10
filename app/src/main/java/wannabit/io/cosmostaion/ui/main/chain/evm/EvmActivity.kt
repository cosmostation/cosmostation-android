package wannabit.io.cosmostaion.ui.main.chain.evm

import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.toMoveBack
import wannabit.io.cosmostaion.data.repository.tx.TxRepositoryImpl
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ActivityEvmBinding
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModelProviderFactory
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModel
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModelProviderFactory

class EvmActivity : BaseActivity() {

    private lateinit var binding: ActivityEvmBinding

    private lateinit var txViewModel: TxViewModel
    private lateinit var walletViewModel: WalletViewModel

    private lateinit var selectedChain: BaseChain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEvmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.parentLayout.setBackgroundResource(Prefs.background)

        if (savedInstanceState == null) {
            BaseData.baseAccount?.allEvmLineChains?.firstOrNull {
                it.tag == intent.getStringExtra(
                    "selectedChainTag"
                )
            }?.let { chain ->
                selectedChain = chain
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container, EvmDetailFragment.newInstance(chain)
                ).commitAllowingStateLoss()
            }
        }
        initViewModel()
        initChainImage()
    }

    private fun initViewModel() {
        val txRepository = TxRepositoryImpl()
        val txViewModelProviderFactory = TxViewModelProviderFactory(txRepository)
        txViewModel = ViewModelProvider(
            this, txViewModelProviderFactory
        )[TxViewModel::class.java]

        val walletRepository = WalletRepositoryImpl()
        val walletViewModelProviderFactory = WalletViewModelProviderFactory(walletRepository)
        walletViewModel =
            ViewModelProvider(this, walletViewModelProviderFactory)[WalletViewModel::class.java]
    }

    private fun initChainImage() {
        binding.chainLogo.apply {
            val width = resources.displayMetrics.widthPixels
            val height = resources.displayMetrics.heightPixels
            val x = (0..width - 150 - 150).random().toFloat()
            val y = (800..height - 150).random().toFloat()

            setImageResource(selectedChain.logo)
            alpha = 0f
            this.x = x
            this.y = y
            layoutParams = FrameLayout.LayoutParams(800, 800)

            ObjectAnimator.ofFloat(this, "alpha", 0f, 0.1f).apply {
                duration = 3000
                start()
            }
            ObjectAnimator.ofFloat(this, "scaleX", 1.05f).apply {
                duration = 3000
                start()
            }
            ObjectAnimator.ofFloat(this, "scaleY", 1.05f).apply {
                duration = 3000
                start()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        toMoveBack()
    }
}