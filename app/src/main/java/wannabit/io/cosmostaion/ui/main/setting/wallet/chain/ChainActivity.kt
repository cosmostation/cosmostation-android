package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.os.Bundle
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.toMoveBack
import wannabit.io.cosmostaion.databinding.ActivityAccountBinding

class ChainActivity : BaseActivity() {

    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ChainManageFragment())
                .commitAllowingStateLoss()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        toMoveBack()
    }
}