package wannabit.io.cosmostaion.ui.main

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NavigationUI.setupWithNavController(binding.navBar, findNavController(R.id.nav_host))
    }

    fun onNextHideBottomNavi() {
        val nextAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_nav_slide_out_right)
        binding.navBar.visibility = View.GONE
        binding.navBar.startAnimation(nextAnimation)
    }

    fun onBackVisibleBottomNavi() {
        val backAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_nav_slide_in_leff)
        binding.navBar.visibility = View.VISIBLE
        binding.navBar.startAnimation(backAnimation)
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}