package com.fulldive.wallet.presentation.security

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.fulldive.wallet.presentation.base.BaseMvpActivity
import com.fulldive.wallet.presentation.system.keyboard.KeyboardPagerAdapter
import com.fulldive.wallet.presentation.system.keyboard.KeyboardType
import com.joom.lightsaber.getInstance
import moxy.ktx.moxyPresenter
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.ActivityPasswordSetBinding
import com.fulldive.wallet.presentation.system.keyboard.KeyboardListener

class CheckPasswordActivity : BaseMvpActivity<ActivityPasswordSetBinding>(),
    CheckPasswordMoxyView,
    KeyboardListener {

    private val presenter by moxyPresenter {
        appInjector.getInstance<CheckPasswordPresenter>()
    }

    override fun getViewBinding() = ActivityPasswordSetBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
        super.onCreate(savedInstanceState)
        binding {
            titleTextView.setText(R.string.str_init_password)

            keyboardPager.offscreenPageLimit = 2
            keyboardPager.adapter = KeyboardPagerAdapter(
                supportFragmentManager,
                this@CheckPasswordActivity
            )
            hintTextView.visibility = View.INVISIBLE
            subtitleTextView.visibility = View.INVISIBLE
        }
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun onStop() {
        finishWithResult(RESULT_CANCELED)
        super.onStop()
    }

    override fun shakeView() {
        binding {
            layerContents.clearAnimation()
            val animation = AnimationUtils.loadAnimation(
                this@CheckPasswordActivity, R.anim.shake
            )
            animation.reset()
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    presenter.onShakeEnded()
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })
            layerContents.startAnimation(animation)
        }
    }

    override fun userInsertKey(input: Char) {
        presenter.onUserInsertKey(input)
    }

    override fun userDeleteKey() {
        presenter.userDeleteKey()
    }

    override fun switchKeyboard(type: KeyboardType) {
        binding {
            keyboardPager.setCurrentItem(type.id, true)
        }
    }

    override fun updatePasswordField(inputLength: Int) {
        binding {
            listOf(circleImage0, circleImage1, circleImage2, circleImage3, circleImage4)
                .forEachIndexed { index, imageView ->
                    imageView.setImageResource(
                        if (index < inputLength) R.drawable.ic_pass_pu else R.drawable.ic_pass_gr
                    )
                }
        }
    }

    override fun clear() {
        binding {
            listOf(circleImage0, circleImage1, circleImage2, circleImage3, circleImage4)
                .forEach { imageView ->
                    imageView.setBackgroundResource(R.drawable.ic_pass_gr)
                }
        }
    }

    override fun finishWithResult(resultCode: Int) {
        setResult(resultCode)
        finish()
        if (resultCode == RESULT_OK) {
            overridePendingTransition(R.anim.fade_in, R.anim.slide_out_bottom)
        }
    }
}