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
import com.fulldive.wallet.presentation.lockscreen.SetPasswordActivity
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
            warningTextView.visibility = View.VISIBLE
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
            mnemonicsCardView.setCardBackgroundColor(
                ContextCompat.getColor(
                    baseContext,
                    chain.chainBackground
                )
            )
            mnemonicsCardView.visibility = View.VISIBLE
        }
        for (i in 0 until MnemonicUtils.MNEMONIC_WORDS_COUNT) {
            resources
                .getIdentifier("mnemonicLayout$i", "id", packageName)
                .let { id -> findViewById<View>(id) }
                .setBackgroundResource(chain.mnemonicBackground)
            resources
                .getIdentifier("mnemonicTextView$i", "id", packageName)
                .let { id -> findViewById<TextView>(id) }
                .text = getString(R.string.str_mnemonic_counder_template, i)
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
            resources
                .getIdentifier("mnemonicEditText$i", "id", packageName)
                .let { id -> findViewById<TextView>(id) }
                .text = mnemonicWords[i]
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
            Intent(this, SetPasswordActivity::class.java),
            ActivityOptionsCompat.makeCustomAnimation(this, R.anim.slide_in_bottom, R.anim.fade_out)
        )
    }

    override fun showMainActivity() {
        Intent(this, MainActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .let(::startActivity)
    }

    companion object {
        const val KEY_CHAIN = "chain"
    }
}