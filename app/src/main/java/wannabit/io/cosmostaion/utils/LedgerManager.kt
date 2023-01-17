package wannabit.io.cosmostaion.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.gms.common.util.CollectionUtils
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.ledger.live.ble.BleManager
import com.ledger.live.ble.BleManagerFactory
import com.ledger.live.ble.app.BleCosmosHelper
import com.ledger.live.ble.model.BleDeviceModel
import com.ledger.live.ble.model.BleError
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.LedgerSelectActivity
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

    fun connect(context: Context, listener: ConnectListener) {
        TedPermission(context).setPermissionListener(object : PermissionListener {
            @SuppressLint("MissingPermission")
            override fun onPermissionGranted() {
                val btAdapter = context.getSystemService(BluetoothManager::class.java).adapter
                val pairedDevices = btAdapter.bondedDevices
                if (isConnected()) {
                    listener.connected()
                    return
                }

                if (!btAdapter.isEnabled) {
                    listener.error(ErrorType.BLUETOOTH_OFF)
                    connectBluetooth(context)
                    return
                }

                if (CollectionUtils.isEmpty(pairedDevices)) {
                    connectBluetoothDevice(context)
                    listener.error(ErrorType.NO_DEVICE)
                    return
                }

                val nanoDevices = pairedDevices.filter { it.name.contains("Nano X") }
                onSetBluetoothList(
                    context,
                    context.getString(R.string.str_pairing_ledger_title),
                    nanoDevices,
                    listener
                )
            }

            override fun onPermissionDenied(deniedPermissions: ArrayList<String?>?) {
                listener.error(ErrorType.PERMISSION_DENIED)
            }
        }).setPermissions(
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_ADVERTISE
        ).check()
    }

    @SuppressLint("MissingPermission")
    fun onSetBluetoothList(
        context: Context,
        title: CharSequence,
        nanoDevices: List<BluetoothDevice>,
        listener: ConnectListener
    ) {
        val dialog = FilledVerticalButtonAlertDialog(context)

        if (StringUtils.isEmpty(title)) {
            dialog.title2TextView.visibility = View.GONE
        } else {
            dialog.title2TextView.text = title
            dialog.title2TextView.visibility = View.VISIBLE
        }
        dialog.firstButton.visibility = View.GONE
        dialog.secondButton.visibility = View.GONE

        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        dialog.hiddenView.visibility = View.VISIBLE
        dialog.hiddenView.removeAllViews()

        nanoDevices.map { blueToothDevice ->
            val ledgerLayout = inflater.inflate(R.layout.item_ledger_list, null)
            dialog.hiddenView.addView(ledgerLayout)

            val ledgerCard: FrameLayout = ledgerLayout.findViewById(R.id.ledgerCard)
            val ledgerName: TextView = ledgerLayout.findViewById(R.id.ledger_name)
            val ledgerStatus: TextView = ledgerLayout.findViewById(R.id.ledger_status)

            ledgerName.text = blueToothDevice.name
            ledgerCard.setOnClickListener {
                ledgerStatus.text = context.getString(R.string.str_connecting)
                ledgerStatus.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorGray1
                    )
                )
                onCheckConnectLedger(context, blueToothDevice, ledgerStatus, listener, dialog)
            }
        }
        dialog.setCancelable(true)
        dialog.create()
        dialog.show()
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
                                context,
                                R.color.colorRed
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

    fun onCheckConnectLedger(
        context: Context,
        bluetoothDevice: BluetoothDevice,
        ledgerStatus: TextView,
        listener: ConnectListener,
        dialog: FilledVerticalButtonAlertDialog
    ) {
        bleManager.connect(address = bluetoothDevice.address, onConnectError = { bleError ->
            val activity: Activity = context as Activity
            activity.runOnUiThread {
                if (BleError.CONNECTION_TIMEOUT == bleError) {
                    ledgerStatus.text = context.getString(R.string.error_ledger_open_msg)
                    ledgerStatus.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorRed
                        )
                    )
                } else {
                    onCheckConnectLedger(context, bluetoothDevice, ledgerStatus, listener, dialog)
                }
            }

        }, onConnectSuccess = { bleDeviceModel ->
            connectedDevice = bleDeviceModel
            onCheckOpenLedgerApp(context, ledgerStatus, listener, dialog)
        })
    }

    fun connectBluetooth(context: Context) {
        FilledVerticalButtonAlertDialog.showNoButton(
            context,
            context.getString(R.string.str_pairing_ledger_title),
            context.getString(R.string.str_pairing_connect_bluetooth_msg),
            true
        )
    }

    fun connectBluetoothDevice(context: Context) {
        FilledVerticalButtonAlertDialog.showNoButton(
            context,
            context.getString(R.string.str_pairing_ledger_title),
            context.getString(R.string.str_pairing_ledger_msg),
            true
        )
    }

    fun connectLedger(context: Context, isLedger: Boolean) {
        CommonAlertDialog.showSecondImageDoubleButton(
            context,
            context.getString(R.string.str_ledger),
            Html.fromHtml(context.getString(R.string.str_connect_ledger_msg)),
            context.getString(R.string.str_cancel),
            null,
            Html.fromHtml("<font color=\"#05D2DD\">" + context.getString(R.string.str_connect) + "</font>"),
            { checkedBluetoothConnect(context, isLedger) },
            R.drawable.icon_ledger
        )
    }

    fun checkedBluetoothConnect(context: Context, isLedger: Boolean) {
        connect(context, object : ConnectListener {
            override fun error(errorType: ErrorType) {
            }

            override fun connected() {
                if (isLedger) {
                    val activity: Activity = context as Activity
                    activity.runOnUiThread {
                        Toast.makeText(context, context.getString(R.string.str_ledger_pairing_completed_msg), Toast.LENGTH_SHORT).show()
                    }
                } else {
                    context.startActivity(Intent(context, LedgerSelectActivity::class.java))
                }
            }
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