package com.fulldive.wallet.presentation.lockscreen

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isInvisible
import com.fulldive.wallet.presentation.base.BaseMvpActivity
import com.joom.lightsaber.getInstance
import moxy.ktx.moxyPresenter
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.ITimelessActivity
import wannabit.io.cosmostaion.databinding.ActivityPasswordSetBinding
import wannabit.io.cosmostaion.fragment.KeyboardFragment
import wannabit.io.cosmostaion.utils.KeyboardListener

class SetPasswordActivity : BaseMvpActivity<ActivityPasswordSetBinding>(),
    SetPasswordMoxyView,
    ITimelessActivity, KeyboardListener {

    private var adapter: KeyboardPagerAdapter? = null

    private val presenter by moxyPresenter {
        appInjector.getInstance<SetPasswordPresenter>()
    }

    override fun getViewBinding() = ActivityPasswordSetBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            titleTextView.setText(R.string.str_password_init)

            keyboardPager.offscreenPageLimit = 2
            keyboardPager.adapter = KeyboardPagerAdapter(
                supportFragmentManager,
                this@SetPasswordActivity
            ).also {
                adapter = it
            }
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

    override fun setCheckPasswordHintVisible(isVisible: Boolean) {
        binding {
            subtitleTextView.isInvisible = !isVisible
        }
    }

    override fun shakeView() {
        binding {
            layerContents.clearAnimation()
            val animation = AnimationUtils.loadAnimation(
                this@SetPasswordActivity, R.anim.shake
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
            listOf(imgCircle0, imgCircle1, imgCircle2, imgCircle3, imgCircle4)
                .forEachIndexed { index, imageView ->
                    imageView.setImageResource(
                        if (index < inputLength) R.drawable.ic_pass_pu else R.drawable.ic_pass_gr
                    )
                }
        }
    }

    override fun shuffleKeyboard() {
        adapter?.fragments?.forEach(KeyboardFragment::shuffleKeyboard)
    }

    override fun clear() {
        binding {
            listOf(imgCircle0, imgCircle1, imgCircle2, imgCircle3, imgCircle4)
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
