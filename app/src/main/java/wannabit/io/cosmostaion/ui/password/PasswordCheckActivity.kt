package wannabit.io.cosmostaion.ui.password

import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseUtils.checkPasscodePattern
import wannabit.io.cosmostaion.common.KeyboardListener
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.databinding.ActivityPasswordCheckBinding
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModelProviderFactory

class PasswordCheckActivity : BaseActivity(), KeyboardListener {

    private lateinit var binding: ActivityPasswordCheckBinding

    private lateinit var passwordPageAdapter: PasswordPageAdapter

    private lateinit var walletViewModel: WalletViewModel

    private val ivCircle = arrayOfNulls<ImageView>(5)
    private var isSetPw = false
    private var isConfirm = false
    private var userInput = ""
    private var confirmInput = ""
    private var askQuite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initView()
        onUpdateView()
        checkPwObserve()
    }

    private fun initViewModel() {
        val walletRepository = WalletRepositoryImpl()
        val walletViewModelProviderFactory = WalletViewModelProviderFactory(walletRepository)
        walletViewModel =
            ViewModelProvider(this, walletViewModelProviderFactory)[WalletViewModel::class.java]
    }

    private fun initView() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE
        )
        for (i in ivCircle.indices) {
            ivCircle[i] = findViewById(resources.getIdentifier("pin_code_img$i", "id", packageName))
        }

        passwordPageAdapter = PasswordPageAdapter(this)
        binding.apply {
            pagerKeyboard.adapter = passwordPageAdapter
            pagerKeyboard.isUserInputEnabled = false
            pagerKeyboard.offscreenPageLimit = 2

            walletViewModel.hasPassword()
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
            if (isSetPw) {
                setPassword()
            } else {
                walletViewModel.checkPassword(userInput)
            }

        } else if (userInput.length == 5 && !checkPasscodePattern(userInput)) {
            onUpdateView()
            return
        }
        askQuite = false
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

    private fun setPassword() {
        if (isConfirm) {
            if (confirmInput == userInput) {
                walletViewModel.insertPassword(userInput)

            } else {
                isConfirm = false
                binding.enterMsg.text = getString(R.string.str_pincode_init_msg)
                makeToast(R.string.error_set_password_differ)
                onUpdateView()
            }

        } else {
            confirmInput = userInput
            userInput = ""
            isConfirm = true
            onUpdateView()
            binding.enterMsg.text = getString(R.string.str_pincode_init_confirm_msg)
        }
    }

    private fun checkPwObserve() {
        walletViewModel.hasPassword.observe(this) { hasPassword ->
            if (!hasPassword) {
                isSetPw = true
                binding.enterMsg.text = getString(R.string.str_pincode_init_msg)
            } else {
                binding.enterMsg.text = getString(R.string.str_enter_pin_msg)
            }
        }

        walletViewModel.pwCheckResult.observe(this) { result ->
            if (result == BaseConstant.SUCCESS) {
                setResult(RESULT_OK, intent)
                finish()
                overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_slide_out_bottom)

            } else {
                onUpdateView()
                makeToast(R.string.error_invalid_password)
            }
        }

        walletViewModel.insertPasswordResult.observe(this) { response ->
            if (response.resource.isNotEmpty()) {
                setResult(RESULT_OK, intent)
                finish()
                overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_slide_out_bottom)

            } else {
                isConfirm = false
                binding.enterMsg.text = getString(R.string.str_pincode_init_msg)
                makeToast(R.string.error_set_password_insert)
                onUpdateView()
            }
        }
    }

    override fun onBackPressed() {
        if (userInput.isNotEmpty()) {
            userDeletePassword()
        } else {
            if (askQuite) {
                setResult(RESULT_CANCELED, intent)
                finish()
            } else {
                askQuite = true
                makeToast(R.string.str_ready_to_quite)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    class PasswordPageAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        private val fragmentList = mutableListOf<KeyboardFragment>()

        init {
            fragmentList.clear()
            val number = NumberKeyBoardFragment()
            number.setKeyboardListener(fragmentActivity as PasswordCheckActivity)
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