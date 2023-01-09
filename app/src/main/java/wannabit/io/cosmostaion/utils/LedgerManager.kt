package wannabit.io.cosmostaion.utils

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
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
                onSetBluetoothList(context, context.getString(R.string.str_pairing_ledger_title), nanoDevices, listener)
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

    fun onSetBluetoothList(context: Context, title: CharSequence, nanoDevices: List<BluetoothDevice>, listener: ConnectListener) {
        val dialog = FilledVerticalButtonAlertDialog(context)

        if (StringUtils.isEmpty(title)) {
            dialog.title2TextView.visibility = View.GONE
        } else {
            dialog.title2TextView.text = title
            dialog.title2TextView.visibility = View.VISIBLE
        }
        dialog.firstButton.visibility = View.GONE
        dialog.secondButton.visibility = View.GONE

        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        dialog.hiddenView.visibility = View.VISIBLE
        dialog.hiddenView.removeAllViews()

        nanoDevices.map {
            val device = it
            val ledgerLayout = inflater.inflate(R.layout.item_ledger_list, null)
            dialog.hiddenView.addView(ledgerLayout)

            val ledgerCard: FrameLayout = ledgerLayout.findViewById(R.id.ledgerCard)
            val ledgerName: TextView = ledgerLayout.findViewById(R.id.ledger_name)
            val ledgerStatus: TextView = ledgerLayout.findViewById(R.id.ledger_status)

            ledgerName.text = device.name
            bleManager.connect(address = device.address, onConnectError = {
                WLog.w("test12345 : " + it.name)
                WLog.w("test12345 : " + it.message)
            }, onConnectSuccess = {
                WLog.w("test1234 : " + it.name)
                WLog.w("test1234 : " + it.id)
                WLog.w("test1234 : " + it.serviceId)
                WLog.w("test1234 : " + it.rssi)
                WLog.w("test1234 : " + it)
                WLog.w("test1234 : " + bleManager.isConnected)
            })

            ledgerCard.setOnClickListener {
                bleManager.connect(address = device.address, onConnectError = {
                    if (BleError.CONNECTION_TIMEOUT == it) {
                        listener.error(ErrorType.CONNECT_TIMEOUT)
                    } else {
                        listener.error(ErrorType.CONNECT_ERROR)
                    }
                }, onConnectSuccess = {
                    connectedDevice = it
                    listener.connected()
                })
            }
        }

        dialog.setCancelable(true)
        dialog.create()
        dialog.show()
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

    fun connectLedger(context: Context) {
        CommonAlertDialog.showSecondImageDoubleButton(
            context,
            context.getString(R.string.str_ledger),
            Html.fromHtml(context.getString(R.string.str_connect_ledger_msg)),
            context.getString(R.string.str_cancel),
            null,
            Html.fromHtml("<font color=\"#05D2DD\">" + context.getString(R.string.str_connect) + "</font>"),
            { checkedBluetoothConnect(context) },
            R.drawable.icon_ledger
        )
    }

    fun checkedBluetoothConnect(context: Context) {
        connect(context, object : ConnectListener {
            override fun error(errorType: ErrorType) {
                WLog.w("여기 : " + errorType.name)
            }

            override fun connected() {
                context.startActivity(Intent(context, LedgerSelectActivity::class.java))
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