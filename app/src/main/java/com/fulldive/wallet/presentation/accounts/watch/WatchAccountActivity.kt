package com.fulldive.wallet.presentation.accounts.watch

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.fulldive.wallet.extensions.hideKeyboard
import com.fulldive.wallet.presentation.base.BaseMvpActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.joom.lightsaber.getInstance
import moxy.ktx.moxyPresenter
import wannabit.io.cosmostaion.activities.MainActivity
import wannabit.io.cosmostaion.databinding.ActivityWatchAccountBinding

class WatchAccountActivity : BaseMvpActivity<ActivityWatchAccountBinding>(),
    WatchAccountMoxyView {
    private val userInput: String
        get() {
            return binding?.addressEditText?.text?.toString()?.trim().orEmpty()
        }

    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        IntentIntegrator
            .parseActivityResult(result.resultCode, result.data)
            .let(presenter::onScanQRResultsReceived)
    }

    private val presenter by moxyPresenter {
        appInjector.getInstance<WatchAccountPresenter>()
    }

    override fun getViewBinding() = ActivityWatchAccountBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            setSupportActionBar(toolbar)
            cancelButton.setOnClickListener {
                presenter.onCancelClicked()
            }
            nextButton.setOnClickListener {
                presenter.onNextClicked(userInput)
            }
            scanQRButton.setOnClickListener {
                onScanQRClicked()
            }
            pasteButton.setOnClickListener {
                presenter.onPasteClicked()
            }
        }
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
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

    override fun setAddress(address: String) {
        binding {
            addressEditText.setText(address)
            addressEditText.setSelection(addressEditText.text.length)
        }
    }

    override fun showMainActivity() {
        hideKeyboard()
        Intent(this, MainActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .let(::startActivity)
    }

    override fun finish() {
        hideKeyboard()
        super.finish()
    }

    private fun onScanQRClicked() {
        launcher.launch(
            IntentIntegrator(this@WatchAccountActivity)
                .setOrientationLocked(true)
                .createScanIntent()
        )
    }
}
