package wannabit.io.cosmostaion.ui.intro

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.toMoveBack
import wannabit.io.cosmostaion.data.repository.account.AccountRepositoryImpl
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ActivityAccountBinding
import wannabit.io.cosmostaion.ui.viewmodel.account.AccountViewModel
import wannabit.io.cosmostaion.ui.viewmodel.account.AccountViewModelProviderFactory
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModelProviderFactory
import wannabit.io.cosmostaion.ui.wallet.CreateMnemonicFragment
import wannabit.io.cosmostaion.ui.wallet.RestoreMnemonicFragment
import wannabit.io.cosmostaion.ui.wallet.RestorePrivateFragment

class AccountInitActivity : BaseActivity() {

    private lateinit var binding: ActivityAccountBinding

    private lateinit var accountViewModel: AccountViewModel

    private lateinit var walletViewModel: WalletViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.parentLayout.setBackgroundResource(Prefs.background)
        if (savedInstanceState == null) {
            replaceFragment()
        }
        initViewModel()
    }

    private fun replaceFragment() {
        val fragment = when (intent.getIntExtra("initType", -1)) {
            BaseConstant.CONST_NEW_ACCOUNT -> CreateMnemonicFragment.newInstance(0)
            BaseConstant.CONST_RESTORE_MNEMONIC_ACCOUNT -> RestoreMnemonicFragment(0)
            BaseConstant.CONST_RESTORE_PRIVATE_ACCOUNT -> RestorePrivateFragment(0)
            else -> null
        }

        fragment?.let {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, it)
                .commitAllowingStateLoss()
        }
    }

    private fun initViewModel() {
        val accountRepository = AccountRepositoryImpl()
        val accountViewModelProviderFactory = AccountViewModelProviderFactory(accountRepository)
        accountViewModel = ViewModelProvider(
            this, accountViewModelProviderFactory
        )[AccountViewModel::class.java]

        val walletRepository = WalletRepositoryImpl()
        val walletViewModelProviderFactory = WalletViewModelProviderFactory(walletRepository)
        walletViewModel = ViewModelProvider(
            this, walletViewModelProviderFactory
        )[WalletViewModel::class.java]
    }

    override fun onBackPressed() {
        super.onBackPressed()
        toMoveBack()
    }
}