package wannabit.io.cosmostaion.ui.main.chain.major

import android.animation.ObjectAnimator
import android.os.Build
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
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModelProviderFactory
import wannabit.io.cosmostaion.data.viewmodel.tx.TxViewModel
import wannabit.io.cosmostaion.data.viewmodel.tx.TxViewModelProviderFactory
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ActivityMajorBinding

class MajorActivity : BaseActivity() {

    private lateinit var binding: ActivityMajorBinding

    private lateinit var txViewModel: TxViewModel
    private lateinit var walletViewModel: WalletViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMajorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.parentLayout.setBackgroundResource(Prefs.background)

        if (savedInstanceState == null) {
            var selectedChain = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("selectedChain", BaseChain::class.java)
            } else {
                (intent.getParcelableExtra("selectedChain")) as? BaseChain
            }

            BaseData.baseAccount?.allChains?.firstOrNull {
                it.tag == selectedChain?.tag
            }?.let { chain ->
                selectedChain = chain
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container, MajorDetailFragment.newInstance(chain)
                ).commit()
            }
            selectedChain?.let { initChainImage(it) }
            initViewModel()
        }
        setUpBg()
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

    private fun initChainImage(chain: BaseChain) {
        binding.chainLogo.apply {
            val width = resources.displayMetrics.widthPixels
            val height = resources.displayMetrics.heightPixels
            val x = (0..width - 150 - 150).random().toFloat()
            val y = (800..height - 150).random().toFloat()

            setImageResource(chain.logo)
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

    private fun setUpBg() {
        ApplicationViewModel.shared.changeBgResult.observe(this) {
            binding.parentLayout.setBackgroundResource(Prefs.background)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        toMoveBack()
    }
}