package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.os.Bundle
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.toMoveBack
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ActivityAccountBinding
import wannabit.io.cosmostaion.databinding.ActivityChainBinding

class ChainActivity : BaseActivity() {

    private lateinit var binding: ActivityChainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.parentLayout.setBackgroundResource(Prefs.background)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ChainManageFragment())
                .commit()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        toMoveBack()
    }
}