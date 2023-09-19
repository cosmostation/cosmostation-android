package wannabit.io.cosmostaion.ui.main

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private var dashboardFragment: DashboardFragment? = null
    private var swapFragment: SwapFragment? = null
    private var dappFragment: DappFragment? = null
    private var settingFragment: SettingFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DashboardFragment())
                .commit()
        }
        initView()
    }

    private fun initView() {
        dashboardFragment = DashboardFragment()
        showFragment(dashboardFragment!!)

        binding.navBar.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.dashboardFragment -> {
                    if (dashboardFragment == null) {
                        dashboardFragment = DashboardFragment()
                        supportFragmentManager.beginTransaction().add(R.id.fragment_container, dashboardFragment!!).commitAllowingStateLoss()
                    }
                    if (dashboardFragment != null) supportFragmentManager.beginTransaction().show(dashboardFragment!!).commitAllowingStateLoss()
                    if (swapFragment != null) supportFragmentManager.beginTransaction().hide(swapFragment!!).commitAllowingStateLoss()
                    if (dappFragment != null) supportFragmentManager.beginTransaction().hide(dappFragment!!).commitAllowingStateLoss()
                    if (settingFragment != null) supportFragmentManager.beginTransaction().hide(settingFragment!!).commitAllowingStateLoss()
                }

                R.id.swapFragment -> {
                    if (swapFragment == null) {
                        swapFragment = SwapFragment()
                        supportFragmentManager.beginTransaction().add(R.id.fragment_container, swapFragment!!).commitAllowingStateLoss()
                    }
                    if (dashboardFragment != null) supportFragmentManager.beginTransaction().hide(dashboardFragment!!).commitAllowingStateLoss()
                    if (swapFragment != null) supportFragmentManager.beginTransaction().show(swapFragment!!).commitAllowingStateLoss()
                    if (dappFragment != null) supportFragmentManager.beginTransaction().hide(dappFragment!!).commitAllowingStateLoss()
                    if (settingFragment != null) supportFragmentManager.beginTransaction().hide(settingFragment!!).commitAllowingStateLoss()
                }

                R.id.dappFragment -> {
                    if (dappFragment == null) {
                        dappFragment = DappFragment()
                        supportFragmentManager.beginTransaction().add(R.id.fragment_container, dappFragment!!).commitAllowingStateLoss()
                    }
                    if (dashboardFragment != null) supportFragmentManager.beginTransaction().hide(dashboardFragment!!).commitAllowingStateLoss()
                    if (swapFragment != null) supportFragmentManager.beginTransaction().hide(swapFragment!!).commitAllowingStateLoss()
                    if (dappFragment != null) supportFragmentManager.beginTransaction().show(dappFragment!!).commitAllowingStateLoss()
                    if (settingFragment != null) supportFragmentManager.beginTransaction().hide(settingFragment!!).commitAllowingStateLoss()
                }

                R.id.settingFragment -> {
                    if (settingFragment == null) {
                        settingFragment = SettingFragment()
                        supportFragmentManager.beginTransaction().add(R.id.fragment_container, settingFragment!!).commitAllowingStateLoss()
                    }
                    if (dashboardFragment != null) supportFragmentManager.beginTransaction().hide(dashboardFragment!!).commitAllowingStateLoss()
                    if (swapFragment != null) supportFragmentManager.beginTransaction().hide(swapFragment!!).commitAllowingStateLoss()
                    if (dappFragment != null) supportFragmentManager.beginTransaction().hide(dappFragment!!).commitAllowingStateLoss()
                    if (settingFragment != null) supportFragmentManager.beginTransaction().show(settingFragment!!).commitAllowingStateLoss()
                }
            }
            true
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragment_container, fragment)
                    .commitAllowingStateLoss()
            }
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
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        } else {
            moveTaskToBack(true)
        }
    }
}