package wannabit.io.cosmostaion.ui.main.setting

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.toMoveBack
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ActivityNoticeBinding
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModelProviderFactory

class NoticeActivity : BaseActivity() {

    private lateinit var binding: ActivityNoticeBinding

    private lateinit var walletViewModel: WalletViewModel

    private lateinit var noticeAdapter: NoticeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.parentLayout.setBackgroundResource(Prefs.background)

        initViewModel()
        initRecyclerView()
        setUpClickAction()
    }

    private fun initViewModel() {
        val walletRepository = WalletRepositoryImpl()
        val walletViewModelProviderFactory = WalletViewModelProviderFactory(walletRepository)
        walletViewModel =
            ViewModelProvider(this, walletViewModelProviderFactory)[WalletViewModel::class.java]

        walletViewModel.notice()
    }

    private fun initRecyclerView() {
        binding.apply {
            walletViewModel.noticeDataResult.observe(this@NoticeActivity) { response ->
                loading.visibility = View.GONE
                emptyLayout.visibility = View.GONE
                recycler.visibility = View.VISIBLE
                noticeAdapter = NoticeAdapter()
                recycler.setHasFixedSize(true)
                recycler.layoutManager = LinearLayoutManager(this@NoticeActivity)
                recycler.adapter = noticeAdapter
                noticeAdapter.submitList(response?.list)
            }

            walletViewModel.noticeErrorMessage.observe(this@NoticeActivity) {
                emptyLayout.visibility = View.VISIBLE
                recycler.visibility = View.GONE
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                toMoveBack()
            }

            btnChrome.setOnClickListener {

            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        toMoveBack()
    }
}