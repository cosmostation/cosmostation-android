package com.fulldive.wallet.presentation.main.intro

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.app.ActivityOptionsCompat
import com.fulldive.wallet.presentation.base.BaseMvpActivity
import com.joom.lightsaber.getInstance
import moxy.ktx.moxyPresenter
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.AppLockActivity
import wannabit.io.cosmostaion.activities.MainActivity
import wannabit.io.cosmostaion.base.ITimelessActivity
import wannabit.io.cosmostaion.databinding.ActivityIntroBinding

class IntroActivity : BaseMvpActivity<ActivityIntroBinding>(), IntroMoxyView, ITimelessActivity {

    private val presenter by moxyPresenter {
        appInjector.getInstance<IntroPresenter>()
            .also {
                it.intent = intent
            }
    }

    private val launcher = registerForActivityResult(
        StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            showMainActivity(0)
        }
    }

    override fun getViewBinding() = ActivityIntroBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            startButton.setOnClickListener {
                presenter.onStartButtonClicked()
            }
        }
    }

    override fun showButtonsPanel() {
        binding {
            AnimationUtils
                .loadAnimation(introBgGr.context, R.anim.fade_in5)
                .let(introBgGr::startAnimation)
            AnimationUtils
                .loadAnimation(introBg.context, R.anim.fade_out5)
                .let(introBg::startAnimation)
            AnimationUtils
                .loadAnimation(bottomLayer1.context, R.anim.fade_out2)
                .apply {
                    setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation) {}
                        override fun onAnimationRepeat(animation: Animation) {}
                        override fun onAnimationEnd(animation: Animation) {
                            bottomLayer2.visibility = View.VISIBLE
                            AnimationUtils.loadAnimation(bottomLayer2.context, R.anim.fade_in2)
                                .let(bottomLayer2::startAnimation)
                        }
                    })
                }
                .let(bottomLayer1::startAnimation)
        }
    }

    override fun showLockScreen() {
        launcher.launch(
            Intent(this@IntroActivity, AppLockActivity::class.java),
            ActivityOptionsCompat.makeCustomAnimation(this, R.anim.slide_in_bottom, R.anim.fade_out)
        )
    }

    override fun showMainActivity(tabIndex: Int) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.putExtra("page", tabIndex)
        startActivity(intent)
    }
}