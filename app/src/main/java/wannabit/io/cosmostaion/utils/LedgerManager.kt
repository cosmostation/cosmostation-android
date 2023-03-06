package wannabit.io.cosmostaion.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothManager
import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.ledger.live.ble.BleManager
import com.ledger.live.ble.BleManagerFactory
import com.ledger.live.ble.app.BleCosmosHelper
import com.ledger.live.ble.app.BleCosmosHelper.Companion.getAddress
import com.ledger.live.ble.app.BleCosmosHelper.Companion.sign
import com.ledger.live.ble.model.BleDeviceModel
import com.ledger.live.ble.model.BleError
import cosmos.tx.v1beta1.ServiceOuterClass.BroadcastTxRequest
import cosmos.tx.v1beta1.ServiceOuterClass.BroadcastTxResponse
import org.apache.commons.lang3.StringUtils
import org.bouncycastle.asn1.x500.style.RFC4519Style.title
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseApplication
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.dialog.CommonAlertDialog
import wannabit.io.cosmostaion.dialog.FilledVerticalButtonAlertDialog
import wannabit.io.cosmostaion.task.TaskResult
import java.util.*
import kotlin.concurrent.timerTask


class LedgerManager {
    companion object {
        @JvmStatic
        val instance = LedgerManager()
    }

    val bleManager: BleManager = BleManagerFactory.newInstance(BaseApplication.getInstance())
    var connectedDevice: BleDeviceModel? = null
    var currentPubKey: ByteArray? = null
    var currentAddress: String? = null

    fun isConnected(): Boolean {
        return connectedDevice != null && bleManager.isConnected
    }

    fun pickLedgerDevice(context: Context, listener: ConnectListener) {
        if (isConnected()) {
            listener.connected()
            return
        }

        val btAdapter = context.getSystemService(BluetoothManager::class.java).adapter
        if (!btAdapter.isEnabled) {
            listener.error(ErrorType.BLUETOOTH_OFF)
            return
        }

        CommonAlertDialog.showSecondImageDoubleButton(
            context,
            context.getString(R.string.str_ledger),
            Html.fromHtml(context.getString(R.string.str_connect_ledger_msg)),
            context.getString(R.string.str_cancel),
            null,
            Html.fromHtml("<font color=\"#05D2DD\">" + context.getString(R.string.str_connect) + "</font>"),
            {
                TedPermission(context).setPermissionListener(object : PermissionListener {
                    @SuppressLint("MissingPermission")
                    override fun onPermissionGranted() {
                        val pairedDevices = btAdapter.bondedDevices

                        val nanoDevices = pairedDevices.filter { it.name.contains("Nano X") }
                        val bluetoothDevices = mutableListOf<BleDeviceModel>()

                        if (nanoDevices.isEmpty()) {
                            val dialog = showDevicePicker(
                                context, context.getString(R.string.str_pairing_ledger_title)
                            )
                            bleManager.startScanning {
                                it.forEach { blueToothDevice ->
                                    val matched =
                                        nanoDevices.find { it.address == blueToothDevice.id }
                                    val bleMatched =
                                        bluetoothDevices.find { it.id == blueToothDevice.id }
                                    if (matched != null || bleMatched != null) {
                                        return@forEach
                                    }
                                    bluetoothDevices.add(blueToothDevice)
                                    if (bluetoothDevices.isNotEmpty()) {
                                        showDialog(dialog, blueToothDevice.name, blueToothDevice.id)
                                    }
                                }
                            }

                        } else {
                            val dialog = showDevicePicker(
                                context, context.getString(R.string.str_pairing_ledger_title)
                            )
                            nanoDevices.forEach { blueToothDevice ->
                                showDialog(dialog, blueToothDevice.name, blueToothDevice.address)
                            }

                            bleManager.startScanning {
                                it.forEach { blueToothDevice ->
                                    val matched =
                                        nanoDevices.find { it.address == blueToothDevice.id }
                                    val bleMatched =
                                        bluetoothDevices.find { it.id == blueToothDevice.id }
                                    if (matched != null || bleMatched != null) {
                                        return@forEach
                                    }
                                    bluetoothDevices.add(blueToothDevice)
                                    showDialog(dialog, blueToothDevice.name, blueToothDevice.id)
                                }
                            }
                        }
                    }

                    private fun showDialog(
                        dialog: FilledVerticalButtonAlertDialog, name: String, id: String
                    ) {
                        val inflater: LayoutInflater =
                            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                        val ledgerLayout = inflater.inflate(R.layout.item_ledger_list, null)
                        dialog.filledVerticalBinding.hiddenView.addView(ledgerLayout)
                        val ledgerCard: FrameLayout = ledgerLayout.findViewById(R.id.ledgerCard)
                        val ledgerName: TextView = ledgerLayout.findViewById(R.id.ledger_name)
                        val ledgerStatus: TextView = ledgerLayout.findViewById(R.id.ledger_status)
                        ledgerName.text = name
                        ledgerCard.setOnClickListener {
                            ledgerStatus.text = context.getString(R.string.str_connecting)
                            ledgerStatus.setTextColor(
                                ContextCompat.getColor(context, R.color.colorGray1)
                            )
                            onPairingLedger(context, id, ledgerStatus, listener, dialog)
                        }
                    }

                    override fun onPermissionDenied(deniedPermissions: ArrayList<String?>?) {
                        listener.error(ErrorType.PERMISSION_DENIED)
                    }
                }).setPermissions(
                    Manifest.permission.BLUETOOTH_CONNECT,
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_ADVERTISE,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ).check()
            },
            R.drawable.icon_ledger
        )
    }

    @SuppressLint("MissingPermission")
    fun showDevicePicker(
        context: Context, title: CharSequence?
    ): FilledVerticalButtonAlertDialog {
        val dialog = FilledVerticalButtonAlertDialog(context)

        if (StringUtils.isEmpty(title)) {
            dialog.filledVerticalBinding.dialogTitle2.visibility = View.GONE
        } else {
            dialog.filledVerticalBinding.dialogTitle2.text = title
            dialog.filledVerticalBinding.dialogTitle2.visibility = View.VISIBLE
        }
        dialog.filledVerticalBinding.btnOne.visibility = View.GONE
        dialog.filledVerticalBinding.btnTwo.visibility = View.GONE

        dialog.filledVerticalBinding.hiddenView.visibility = View.VISIBLE
        dialog.setCancelable(true)
        dialog.create()
        (context as? Activity)?.runOnUiThread {
            dialog.show()
        }

        return dialog
    }

    private fun onPairingLedger(
        context: Context,
        devideId: String,
        ledgerStatus: TextView,
        listener: ConnectListener,
        dialog: FilledVerticalButtonAlertDialog,
        retryCount: Int = 3
    ) {
        try {
            if (bleManager.isConnected) {
                bleManager.disconnect {
                    Timer().schedule(timerTask {
                        onConnectLeger(devideId, context, ledgerStatus, listener, dialog, retryCount)
                    }, 1500)
                }
            } else {
                onConnectLeger(devideId, context, ledgerStatus, listener, dialog, retryCount)
            }
        } catch (e: Exception) {
            ledgerStatus.text = context.getString(R.string.str_ledger_error)
            ledgerStatus.setTextColor(
                ContextCompat.getColor(
                    context, R.color.colorRed
                )
            )
        }
    }

    private fun onConnectLeger(
        deviceId: String,
        context: Context,
        ledgerStatus: TextView,
        listener: ConnectListener,
        dialog: FilledVerticalButtonAlertDialog,
        retryCount: Int
    ) {
        bleManager.connect(address = deviceId, onConnectError = { bleError ->
            if (retryCount > 0) {
                onPairingLedger(context, deviceId, ledgerStatus, listener, dialog, retryCount - 1)
            } else {
                val activity: Activity = context as Activity
                activity.runOnUiThread {
                    if (bleError.message == BleError.PAIRING_FAILED.message) {
                        ledgerStatus.text = context.getString(R.string.error_ledger_open_msg)
                    } else {
                        dialog.dismiss()
                        CommonAlertDialog.showSingleButton(
                            context,
                            context.getString(R.string.str_ledger_pairing_guide_title),
                            context.getString(R.string.str_ledger_pairing_guide_msg),
                            context.getString(R.string.str_confirm),
                            null,
                            true
                        )
                    }
                    ledgerStatus.setTextColor(
                        ContextCompat.getColor(
                            context, R.color.colorRed
                        )
                    )
                }
            }
        }, onConnectSuccess = { bleDeviceModel ->
            connectedDevice = bleDeviceModel
            onCheckOpenLedgerApp(context, ledgerStatus, listener, dialog)
        })
    }

    private fun onCheckOpenLedgerApp(
        context: Context,
        ledgerStatus: TextView,
        listener: ConnectListener,
        dialog: FilledVerticalButtonAlertDialog
    ) {
        getAddress(bleManager, listener = object : BleCosmosHelper.GetAddressListener {
            override fun error(code: String, message: String) {
                val activity: Activity = context as Activity
                activity.runOnUiThread {
                    ledgerStatus.text = context.getString(R.string.error_ledger_open_msg)
                    ledgerStatus.setTextColor(
                        ContextCompat.getColor(
                            context, R.color.colorRed
                        )
                    )
                }
            }

            override fun success(address: String, pubKey: ByteArray) {
                listener.connected()
                dialog.dismiss()
            }
        })
    }

    fun signAndBroadcast(activity: Activity, account: Account, ledgerSignListener: LedgerSignListener) {
        pickLedgerDevice(activity, object : ConnectListener {
            var dialog = CommonAlertDialog.makeSecondImageSingleButton(
                activity,
                activity.getString(R.string.str_ledger_approve_title),
                activity.getString(R.string.str_ledger_approve_msg),
                activity.getString(R.string.str_cancel),
                { activity.finish() },
                R.drawable.icon_ledger
            )

            override fun connected() {
                dialog.setCancelable(false)
                dialog.create()
                processGetAddress()
            }

            private fun processGetAddress() {
                getAddress(bleManager,
                    ChainFactory.getChain(account.baseChain).addressPrefix(),
                    account.path,
                    object : BleCosmosHelper.GetAddressListener {
                        override fun success(address: String, pubKey: ByteArray) {
                            processLedgerSign(
                                activity,
                                pubKey,
                                account,
                                address,
                                dialog,
                                ledgerSignListener
                            )
                        }

                        override fun error(code: String, message: String) {
                            dialog.dismiss()
                            processLedgerError(activity, code)
                        }
                    })
            }

            override fun error(errorType: ErrorType) {
                if (activity.isFinishing) {
                    return
                }
                activity.runOnUiThread {
                    CommonAlertDialog.showDoubleButton(
                        activity,
                        activity.getString(R.string.str_ledger_error),
                        activity.getString(errorType.descriptionResourceId),
                        activity.getString(R.string.str_cancel),
                        null,
                        activity.getString(R.string.str_retry)
                    ) { signAndBroadcast(activity, account, ledgerSignListener) }
                }
            }
        })
    }

    private fun processLedgerSign(
        activity: Activity,
        pubKey: ByteArray,
        account: Account,
        address: String,
        dialog: CommonAlertDialog,
        ledgerSignListener: LedgerSignListener
    ) {
        if (activity.isFinishing) {
            return
        }
        currentPubKey = pubKey
        if (!account.address.equals(address)) {
            return
        } else {
            activity.runOnUiThread {
                dialog.show()
            }
        }

        sign(bleManager, account.path, ledgerSignListener.getMessage(), object : BleCosmosHelper.SignListener {
            override fun success(signature: ByteArray) {
                if (activity.isFinishing) {
                    return
                }

                Thread {
                    val broadcastTxRequest = ledgerSignListener.makeBroadcastTxRequest(signature)
                    val response = Signer.getGrpcLedgerBroadcastResponse(broadcastTxRequest, ChainFactory.getChain(account.baseChain))
                    val mResult = TaskResult()
                    mResult.resultData = response.txResponse.txhash

                    if (response.txResponse.code > 0) {
                        mResult.errorCode = response.txResponse.code
                        mResult.errorMsg = response.txResponse.rawLog
                        mResult.isSuccess = false
                    } else {
                        mResult.isSuccess = true
                    }
                    ledgerSignListener.processResponse(mResult, response)
                }.start()
            }

            override fun error(code: String, message: String) {
                dialog.dismiss()
                processLedgerError(activity, code)
            }
        })
    }

    private fun processLedgerError(activity: Activity, code: String) {
        if (activity.isFinishing) {
            return
        }
        activity.runOnUiThread {
            if (code.equals("6986", true)) {
                Toast.makeText(activity, R.string.str_ledger_tx_reject_msg, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, R.string.str_ledger_error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    enum class ErrorType(val descriptionResourceId: Int) {
        PERMISSION_DENIED(R.string.error_permission),
        BLUETOOTH_OFF(R.string.str_pairing_connect_bluetooth_msg),
        UNKNOWN(R.string.str_ledger_error)
    }

    interface ConnectListener {
        fun error(errorType: ErrorType)
        fun connected()
    }

    interface LedgerSignListener {
        fun getMessage(): String
        fun makeBroadcastTxRequest(currentPubKey: ByteArray): BroadcastTxRequest
        fun processResponse(mResult: TaskResult, response: BroadcastTxResponse)
    }
}