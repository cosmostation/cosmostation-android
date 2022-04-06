package com.fulldive.wallet.presentation.accounts.create

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import com.fulldive.wallet.interactors.secret.MnemonicUtils
import com.fulldive.wallet.presentation.base.BaseMvpActivity
import com.fulldive.wallet.presentation.lockscreen.CheckPasswordActivity
import com.joom.lightsaber.getInstance
import moxy.ktx.moxyPresenter
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.MainActivity
import wannabit.io.cosmostaion.activities.PasswordSetActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.databinding.ActivityCreateBinding

class CreateAccountActivity : BaseMvpActivity<ActivityCreateBinding>(), CreateAccountMoxyView {

    private val launcher = registerForActivityResult(
        StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            presenter.onCheckPasswordSuccessfully()
        }
    }

    private val launcherSetPassword = registerForActivityResult(
        StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            presenter.onSetPasswordSuccessfully()
        }
    }

    private val presenter by moxyPresenter {
        appInjector.getInstance<CreateAccountPresenter>()
            .also {
                it.chain = intent?.getStringExtra(KEY_CHAIN)?.let(BaseChain::getChain)
            }
    }

    override fun getViewBinding() = ActivityCreateBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
        binding {
            setSupportActionBar(toolbar)
        }
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun showAccountAddress(address: String) {
        binding {
            addressTextView.text = address
            addressCardView.visibility = View.VISIBLE
            warningLayout.visibility = View.VISIBLE
            warningTextView.setText(R.string.str_create_warn0)

            nextButton.setText(R.string.str_show_mnemonic)
            nextButton.setOnClickListener {
                presenter.onShowMnemonicClicked()
            }
            nextButton.visibility = View.VISIBLE
        }
    }

    override fun showChain(chain: BaseChain) {
        binding {
            mnemonicsCardView.setCardBackgroundColor(ContextCompat.getColor(baseContext, chain.chainBackground))
            mnemonicsCardView.visibility = View.VISIBLE
        }
        for (i in 0 until MnemonicUtils.MNEMONIC_WORDS_COUNT) {
            findViewById<View>(resources.getIdentifier("layer_mnemonic_$i", "id", packageName))
                .setBackgroundResource(chain.mnemonicBackground)
        }
    }

    override fun showMnemonic(mnemonicWords: List<String>) {
        binding {
            warningTextView.setText(R.string.str_create_warn1)
            nextButton.setText(R.string.str_create_wallet)
            nextButton.setOnClickListener {
                presenter.onCreateAccountClicked()
            }
        }
        for (i in 0 until MnemonicUtils.MNEMONIC_WORDS_COUNT) {
            val viewId = resources.getIdentifier("tv_mnemonic_$i", "id", packageName)
            findViewById<TextView>(viewId).text = mnemonicWords[i]
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun requestCheckPassword() {
        launcher.launch(
            Intent(this, CheckPasswordActivity::class.java),
            ActivityOptionsCompat.makeCustomAnimation(this, R.anim.slide_in_bottom, R.anim.fade_out)
        )
    }

    override fun requestCreatePassword() {
        launcherSetPassword.launch(
            Intent(this, PasswordSetActivity::class.java),
            ActivityOptionsCompat.makeCustomAnimation(this, R.anim.slide_in_bottom, R.anim.fade_out)
        )
    }

    override fun showMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    companion object {
        const val KEY_CHAIN = "chain"
    }
}