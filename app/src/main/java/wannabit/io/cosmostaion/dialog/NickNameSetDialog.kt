package wannabit.io.cosmostaion.dialog

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.databinding.DialogChangeNicknameBinding

class NickNameSetDialog : DialogFragment() {
    var listener: NickNameSetListener? = null
    private var dialogChangeNicknameBinding: DialogChangeNicknameBinding? = null
    private var keyValue = 0
    fun setNickNameListener(listener: NickNameSetListener?) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.drawable.layout_trans_with_border)
        dialogChangeNicknameBinding =
            DialogChangeNicknameBinding.inflate(inflater, container, false)
        keyValue = arguments!!.getInt(CHANGE_NICK_NAME_BUNDLE_KEY)
        when (keyValue) {
            ACCOUNT_CHANGE_NICKNAME -> {
                dialogChangeNicknameBinding!!.dialogTitle.text =
                    getString(R.string.str_change_account_nickname)
                val account = sActivity!!.baseDao.onSelectAccount(
                    arguments!!.getLong("id").toString()
                )
                dialogChangeNicknameBinding!!.etNickname.setText(account.getName(activity))
            }
            MNEMONIC_CHANGE_NICKNAME -> {
                dialogChangeNicknameBinding!!.dialogTitle.text =
                    getString(R.string.str_change_mnemonic_nickname)
                val mWords = sActivity!!.baseDao.onSelectMnemonicById(arguments!!.getLong("id"))
                dialogChangeNicknameBinding!!.etNickname.setText(mWords.name)
            }
            else -> {
                dialogChangeNicknameBinding!!.dialogTitle.text =
                    getString(R.string.str_set_mnemonic_nickname)
            }
        }
        dialogChangeNicknameBinding!!.etNickname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (!TextUtils.isEmpty(dialogChangeNicknameBinding!!.etNickname.text.toString())) {
                    dialogChangeNicknameBinding!!.etNickname.background = ContextCompat.getDrawable(
                        sActivity!!, R.drawable.box_vote_voted
                    )
                } else {
                    dialogChangeNicknameBinding!!.etNickname.background = ContextCompat.getDrawable(
                        sActivity!!, R.drawable.box_round_unselected
                    )
                }
            }
        })
        dialogChangeNicknameBinding!!.btnNega.setOnClickListener {
            val imm = dialogChangeNicknameBinding!!.etNickname.context.getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            if (imm.isActive) {
                imm.hideSoftInputFromWindow(dialogChangeNicknameBinding!!.etNickname.windowToken, 0)
            }
            if (keyValue == ACCOUNT_CHANGE_NICKNAME || keyValue == MNEMONIC_CHANGE_NICKNAME) {
                dismiss()
            } else {
                sActivity!!.finish()
            }
        }
        dialogChangeNicknameBinding!!.btnPosi.setOnClickListener {
            val imm = dialogChangeNicknameBinding!!.etNickname.context.getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            if (imm.isActive) {
                imm.hideSoftInputFromWindow(dialogChangeNicknameBinding!!.etNickname.windowToken, 0)
            }
            if (!TextUtils.isEmpty(dialogChangeNicknameBinding!!.etNickname.text.toString()) && listener != null) {
                listener!!.confirm(
                    dialogChangeNicknameBinding!!.etNickname.text.toString().trim { it <= ' ' })
                dismiss()
            } else {
                dialogChangeNicknameBinding!!.etNickname.background = ContextCompat.getDrawable(
                    sActivity!!, R.drawable.box_round_unselected
                )
            }
        }
        return dialogChangeNicknameBinding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogChangeNicknameBinding = null
    }

    interface NickNameSetListener {
        fun confirm(nickName: String)
    }

    private val sActivity: BaseActivity?
        get() = activity as BaseActivity?

    companion object {
        @JvmField
        var ACCOUNT_CHANGE_NICKNAME = 8507

        @JvmField
        var MNEMONIC_CHANGE_NICKNAME = 8508

        @JvmField
        var MNEMONIC_CREATE_VALUE = 8509
        const val CHANGE_NICK_NAME_BUNDLE_KEY = "changeNickName"

        @JvmStatic
        fun newInstance(bundle: Bundle?): NickNameSetDialog {
            val dialog = NickNameSetDialog()
            dialog.arguments = bundle
            dialog.isCancelable = false
            return dialog
        }
    }
}