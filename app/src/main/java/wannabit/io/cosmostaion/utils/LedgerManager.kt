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
import androidx.core.content.ContextCompat
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.ledger.live.ble.BleManager
import com.ledger.live.ble.BleManagerFactory
import com.ledger.live.ble.app.BleCosmosHelper
import com.ledger.live.ble.model.BleDeviceModel
import com.ledger.live.ble.model.BleError
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseApplication
import wannabit.io.cosmostaion.dialog.CommonAlertDialog
import wannabit.io.cosmostaion.dialog.FilledVerticalButtonAlertDialog


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

    fun pickLedgerDevice(
        context: Context, listener: ConnectListener
    ) {
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
                                    val matched = nanoDevices.find { it.address == blueToothDevice.id }
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
                                context, context.getString(R.string.str_pairing_ledger_title))
                            nanoDevices.forEach { blueToothDevice ->
                                showDialog(dialog, blueToothDevice.name, blueToothDevice.address)
                            }

                            bleManager.startScanning {
                                it.forEach { blueToothDevice ->
                                    val matched = nanoDevices.find { it.address == blueToothDevice.id }
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
                        dialog.hiddenView.addView(ledgerLayout)
                        val ledgerCard: FrameLayout = ledgerLayout.findViewById(R.id.ledgerCard)
                        val ledgerName: TextView = ledgerLayout.findViewById(R.id.ledger_name)
                        val ledgerStatus: TextView = ledgerLayout.findViewById(R.id.ledger_status)
                        ledgerName.text = name
                        ledgerCard.setOnClickListener {
                            ledgerStatus.text = context.getString(R.string.str_connecting)
                            ledgerStatus.setTextColor(
                                ContextCompat.getColor(context, R.color.colorGray1)
                            )
                            onConnectLedger(context, id, ledgerStatus, listener, dialog)
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
        context: Context, title: CharSequence
    ): FilledVerticalButtonAlertDialog {
        val dialog = FilledVerticalButtonAlertDialog(context)

        if (StringUtils.isEmpty(title)) {
            dialog.title2TextView.visibility = View.GONE
        } else {
            dialog.title2TextView.text = title
            dialog.title2TextView.visibility = View.VISIBLE
        }
        dialog.firstButton.visibility = View.GONE
        dialog.secondButton.visibility = View.GONE

        dialog.hiddenView.visibility = View.VISIBLE
        dialog.setCancelable(true)
        dialog.create()
        dialog.show()

        return dialog
    }

    fun onCheckOpenLedgerApp(
        context: Context,
        ledgerStatus: TextView,
        listener: ConnectListener,
        dialog: FilledVerticalButtonAlertDialog
    ) {
        BleCosmosHelper.getAddress(bleManager,
            listener = object : BleCosmosHelper.GetAddressListener {
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

    private fun onConnectLedger(
        context: Context,
        devideId: String,
        ledgerStatus: TextView,
        listener: ConnectListener,
        dialog: FilledVerticalButtonAlertDialog
    ) {
        if (bleManager.isConnected) {
            bleManager.disconnect {
                connect(devideId, context, ledgerStatus, listener, dialog, 0)
            }
        } else {
            connect(devideId, context, ledgerStatus, listener, dialog, 0)
        }
    }

    private fun connect(
        devideId: String,
        context: Context,
        ledgerStatus: TextView,
        listener: ConnectListener,
        dialog: FilledVerticalButtonAlertDialog,
        retryCount: Int
    ) {
        bleManager.connect(address = devideId, onConnectError = { bleError ->
            if (retryCount == 0) {
                connect(devideId, context, ledgerStatus, listener, dialog, 1)
            } else {
                val activity: Activity = context as Activity
                activity.runOnUiThread {
                    if (bleError.message.equals(BleError.PAIRING_FAILED)) {
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

    enum class ErrorType {
        PERMISSION_DENIED, CONNECT_TIMEOUT, CONNECT_ERROR, NO_DEVICE, BLUETOOTH_OFF
    }

    interface ConnectListener {
        fun error(errorType: ErrorType)
        fun connected()
    }
}