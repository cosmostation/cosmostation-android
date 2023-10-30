package wannabit.io.cosmostaion.ui.main.chain

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.toMoveBack
import wannabit.io.cosmostaion.data.repository.tx.SendRepositoryImpl
import wannabit.io.cosmostaion.databinding.ActivityCosmosBinding
import wannabit.io.cosmostaion.ui.viewmodel.tx.SendViewModel
import wannabit.io.cosmostaion.ui.viewmodel.tx.SendViewModelProviderFactory

class CosmosActivity : BaseActivity() {

    private lateinit var binding: ActivityCosmosBinding

    private lateinit var sendViewModel: SendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCosmosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CosmosDetailFragment(intent.getIntExtra("selectPosition", -1)))
                .commitAllowingStateLoss()
        }

        initViewModel()
    }

    private fun initViewModel() {
        val sendRepository = SendRepositoryImpl()
        val sendViewModelProviderFactory = SendViewModelProviderFactory(sendRepository)
        sendViewModel = ViewModelProvider(
            this,
            sendViewModelProviderFactory
        )[SendViewModel::class.java]
    }

    override fun onBackPressed() {
        super.onBackPressed()
        toMoveBack()
    }
}