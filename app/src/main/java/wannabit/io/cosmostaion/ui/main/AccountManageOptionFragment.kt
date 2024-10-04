package wannabit.io.cosmostaion.ui.main

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentAccountManageOptionBinding
import wannabit.io.cosmostaion.ui.main.setting.wallet.account.ChangeNameFragment
import wannabit.io.cosmostaion.ui.main.setting.wallet.account.DeleteFragment

interface AccountManageListener {
    fun checkMnemonic(account: BaseAccount)

    fun checkPrivate(account: BaseAccount)
}

class AccountManageOptionFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAccountManageOptionBinding? = null
    private val binding get() = _binding!!

    private lateinit var account: BaseAccount

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            baseAccount: BaseAccount, listener: AccountManageListener
        ): AccountManageOptionFragment {
            val args = Bundle().apply {
                putParcelable("baseAccount", baseAccount)
            }
            val fragment = AccountManageOptionFragment()
            fragment.arguments = args
            fragment.accountManageListener = listener
            return fragment
        }
    }

    private var accountManageListener: AccountManageListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountManageOptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("baseAccount", BaseAccount::class.java)
                    ?.let { account = it }

            } else {
                (arguments?.getParcelable("baseAccount") as? BaseAccount)?.let {
                    account = it
                }
            }

            if (account.type == BaseAccountType.MNEMONIC) {
                checkMnemonicLayout.visibility = View.VISIBLE
            } else {
                checkMnemonicLayout.visibility = View.GONE
                view0.visibility = View.GONE
                checkPrivate.text = getString(R.string.str_only_private)
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            nameLayout.setOnClickListener {
                handleOneClickWithDelay(ChangeNameFragment.newInstance(account))
            }

            checkMnemonicLayout.setOnClickListener {
                accountManageListener?.checkMnemonic(account)
            }

            checkPrivateLayout.setOnClickListener {
                accountManageListener?.checkPrivate(account)
            }

            deleteLayout.setOnClickListener {
                handleOneClickWithDelay(DeleteFragment.newInstance(account))
            }
        }
    }

    private fun handleOneClickWithDelay(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        if (isClickable) {
            isClickable = false

            bottomSheetDialogFragment.show(
                requireActivity().supportFragmentManager, bottomSheetDialogFragment::class.java.name
            )

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 300)
        }
        dismiss()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}