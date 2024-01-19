package wannabit.io.cosmostaion.ui.dialog.account

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
import wannabit.io.cosmostaion.ui.main.setting.account.AccountManageListener
import wannabit.io.cosmostaion.ui.main.setting.account.ChangeNameFragment
import wannabit.io.cosmostaion.ui.main.setting.account.DeleteFragment

class AccountManageOptionFragment(
    private val account: BaseAccount, private val listener: AccountManageListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentAccountManageOptionBinding? = null
    private val binding get() = _binding!!

    private var isClickable = true

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
                ChangeNameFragment(account).show(
                    requireActivity().supportFragmentManager, ChangeNameFragment::class.java.name
                )
                setClickableOnce(isClickable)
            }

            checkMnemonicLayout.setOnClickListener {
                listener.checkMnemonic(account)
            }

            checkPrivateLayout.setOnClickListener {
                listener.checkPrivate(account)
            }

            deleteLayout.setOnClickListener {
                DeleteFragment(account).show(
                    requireActivity().supportFragmentManager, DeleteFragment::class.java.name
                )
                setClickableOnce(isClickable)
            }
        }
    }

    private fun setClickableOnce(clickable: Boolean) {
        if (clickable) {
            isClickable = false

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 1000)
        }
        dismiss()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}