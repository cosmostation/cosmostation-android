package wannabit.io.cosmostaion.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.LedgerSelectActivity
import wannabit.io.cosmostaion.activities.setting.MnemonicCreateActivity
import wannabit.io.cosmostaion.activities.setting.MnemonicRestoreActivity
import wannabit.io.cosmostaion.activities.setting.PrivateKeyRestoreActivity
import wannabit.io.cosmostaion.activities.setting.WatchingWalletAddActivity
import wannabit.io.cosmostaion.databinding.DialogAddAccountBinding
import wannabit.io.cosmostaion.utils.LedgerManager
import wannabit.io.cosmostaion.utils.LedgerManager.Companion.instance
import wannabit.io.cosmostaion.utils.LedgerManager.ConnectListener

class AddAccountDialog : DialogFragment() {
    private var dialogAddAccountBinding: DialogAddAccountBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.color.colorTrans)
        dialogAddAccountBinding = DialogAddAccountBinding.inflate(inflater, container, false)
        dialogAddAccountBinding!!.importKeyLayer.setOnClickListener {
            val restoreIntent = Intent(activity, PrivateKeyRestoreActivity::class.java)
            startActivity(restoreIntent)
            dismiss()
        }
        dialogAddAccountBinding!!.importMnemonicLayer.setOnClickListener {
            val restoreIntent = Intent(activity, MnemonicRestoreActivity::class.java)
            startActivity(restoreIntent)
            dismiss()
        }
        dialogAddAccountBinding!!.btnWatchAddress.setOnClickListener {
            startActivity(Intent(activity, WatchingWalletAddActivity::class.java))
            dismiss()
        }
        dialogAddAccountBinding!!.btnCreate.setOnClickListener {
            val createIntent = Intent(activity, MnemonicCreateActivity::class.java)
            startActivity(createIntent)
            dismiss()
        }
        dialogAddAccountBinding!!.btnLedger.setOnClickListener {
            if (instance.isConnected()) {
                instance.bleManager.disconnect {
                    showLedgerPicker()
                    null
                }
            } else {
                showLedgerPicker()
            }
        }
        return dialogAddAccountBinding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogAddAccountBinding = null
    }

    private fun showLedgerPicker() {
        activity!!.runOnUiThread {
            instance.pickLedgerDevice(requireContext(), object : ConnectListener {
                override fun error(errorType: LedgerManager.ErrorType) {
                    FilledVerticalButtonAlertDialog.showNoButton(
                        context,
                        getString(R.string.str_pairing_ledger_title),
                        getString(errorType.descriptionResourceId),
                        true
                    )
                }

                override fun connected() {
                    startActivity(Intent(activity, LedgerSelectActivity::class.java))
                }
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle?): AddAccountDialog {
            val frag = AddAccountDialog()
            frag.arguments = bundle
            return frag
        }
    }
}