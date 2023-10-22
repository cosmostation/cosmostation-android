package wannabit.io.cosmostaion.ui.main.setting.account

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.toMoveBack
import wannabit.io.cosmostaion.data.repository.account.AccountRepositoryImpl
import wannabit.io.cosmostaion.databinding.ActivityAccountBinding
import wannabit.io.cosmostaion.ui.viewmodel.account.AccountViewModel
import wannabit.io.cosmostaion.ui.viewmodel.account.AccountViewModelProviderFactory

class AccountActivity : BaseActivity() {

    private lateinit var binding: ActivityAccountBinding

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AccountListFragment())
                .commitAllowingStateLoss()
        }
        initViewModel()
    }

    private fun initViewModel() {
        val accountRepository = AccountRepositoryImpl()
        val accountViewModelProviderFactory = AccountViewModelProviderFactory(accountRepository)
        accountViewModel = ViewModelProvider(
            this,
            accountViewModelProviderFactory
        )[AccountViewModel::class.java]
    }

    override fun onBackPressed() {
        super.onBackPressed()
        toMoveBack()
    }
}