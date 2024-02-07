package wannabit.io.cosmostaion.ui.password

import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import androidx.core.os.CancellationSignal
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils.checkPasscodePattern
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ActivityPasswordCheckBinding
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModelProviderFactory

class AppLockActivity : BaseActivity(), KeyboardListener {

    private lateinit var binding: ActivityPasswordCheckBinding

    private lateinit var passwordPageAdapter: PasswordPageAdapter

    private lateinit var walletViewModel: WalletViewModel

    private val ivCircle = arrayOfNulls<ImageView>(5)
    private var userInput = ""

    private val cancellationSignal: CancellationSignal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initView()
        onUpdateView()
        checkPwObserve()

        if (Prefs.usingBio) {
            checkFingerPrint()
        }
    }

    private fun initViewModel() {
        val walletRepository = WalletRepositoryImpl()
        val walletViewModelProviderFactory = WalletViewModelProviderFactory(walletRepository)
        walletViewModel = ViewModelProvider(this, walletViewModelProviderFactory)[WalletViewModel::class.java]
    }

    private fun initView() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
        for (i in ivCircle.indices) {
            ivCircle[i] = findViewById(resources.getIdentifier("pin_code_img$i", "id", packageName))
        }

        passwordPageAdapter = PasswordPageAdapter(this)
        binding.apply {
            pagerKeyboard.adapter = passwordPageAdapter
            pagerKeyboard.isUserInputEnabled = false
            pagerKeyboard.offscreenPageLimit = 2
        }
    }

    private fun onUpdateView() {
        userInput = ""
        for (i in ivCircle.indices) {
            ivCircle[i]?.setImageResource(R.drawable.icon_pin_code_disable)
        }
        binding.pagerKeyboard.setCurrentItem(0, true)
    }

    override fun userInsertPassword(input: Char) {
        if (userInput.isEmpty()) {
            userInput = input.toString()
        } else if (userInput.length < 5) {
            userInput += input
        }

        if (userInput.length == 4) {
            binding.pagerKeyboard.setCurrentItem(1, true)
        } else if (userInput.length == 5 && checkPasscodePattern(userInput)) {
            walletViewModel.checkPassword(userInput)
        } else if (userInput.length == 5 && !checkPasscodePattern(userInput)) {
            onUpdateView()
            return
        }
        updatePin()
    }

    override fun userDeletePassword() {
        if (userInput.isEmpty()) {
            onBackPressed()
        } else if (userInput.length == 4) {
            userInput = userInput.substring(0, userInput.length - 1)
            binding.pagerKeyboard.setCurrentItem(0, true)
        } else {
            userInput = userInput.substring(0, userInput.length - 1)
        }
        updatePin()
    }

    private fun updatePin() {
        if (userInput.isEmpty()) userInput = ""

        val inputLength: Int = userInput.length
        for (i in ivCircle.indices) {
            if (i < inputLength) ivCircle[i]?.setImageResource(R.drawable.icon_pin_code_active)
            else ivCircle[i]?.setImageResource(R.drawable.icon_pin_code_disable)
        }
    }

    private fun checkPwObserve() {
        walletViewModel.pwCheckResult.observe(this) { result ->
            if (result == BaseConstant.SUCCESS) {
                BaseData.setLastTime()
                cancellationSignal?.cancel()
                finish()
                overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_slide_out_bottom)

            } else {
                onUpdateView()
                makeToast(R.string.error_invalid_password)
            }
        }
    }

    private fun checkFingerPrint() {
        val fingerprintManagerCompat = FingerprintManagerCompat.from(this)
        val cancellationSignal = CancellationSignal()
        if (fingerprintManagerCompat.isHardwareDetected && fingerprintManagerCompat.hasEnrolledFingerprints() && Prefs.usingBio) {
            fingerprintManagerCompat.authenticate(
                null,
                0,
                cancellationSignal,
                object : FingerprintManagerCompat.AuthenticationCallback() {
                    override fun onAuthenticationError(errMsgId: Int, errString: CharSequence) {
                        super.onAuthenticationError(errMsgId, errString)
                    }

                    override fun onAuthenticationHelp(helpMsgId: Int, helpString: CharSequence) {
                        super.onAuthenticationHelp(helpMsgId, helpString)
                    }

                    override fun onAuthenticationSucceeded(result: FingerprintManagerCompat.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        cancellationSignal.cancel()
                        setResult(RESULT_OK, intent)
                        finish()
                        overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_slide_out_bottom)
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                    }
                },
                null
            )
        }
    }

    override fun onBackPressed() {
        cancellationSignal?.cancel()
        moveTaskToBack(true)
    }

    override fun onStop() {
        super.onStop()
        cancellationSignal?.cancel()
    }

    class PasswordPageAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

        private val fragmentList = mutableListOf<KeyboardFragment>()

        init {
            fragmentList.clear()
            val number = NumberKeyBoardFragment()
            number.setKeyboardListener(fragmentActivity as AppLockActivity)
            fragmentList.add(number)

            val alphabet = AlphabetKeyBoardFragment()
            alphabet.setKeyboardListener(fragmentActivity)
            fragmentList.add(alphabet)
        }

        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }
    }
}