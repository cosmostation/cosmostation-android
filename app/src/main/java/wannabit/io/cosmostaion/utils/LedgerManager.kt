package wannabit.io.cosmostaion.utils

import android.Manifest
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.common.util.CollectionUtils
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.ledger.live.ble.BleManager
import com.ledger.live.ble.BleManagerFactory
import com.ledger.live.ble.model.BleDeviceModel
import com.ledger.live.ble.model.BleError
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.LedgerSelectActivity
import wannabit.io.cosmostaion.base.BaseApplication
import wannabit.io.cosmostaion.dialog.CommonAlertDialog


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
                    return
                }

                if (CollectionUtils.isEmpty(pairedDevices)) {
                    listener.error(ErrorType.NO_DEVICE)
                    return
                }

                val nanoDevices = pairedDevices.filter { it.name.contains("Nano X") }
                AlertDialog.Builder(context, R.style.DialogTheme).setTitle("Pairing Ledger")
                    .setItems(
                        nanoDevices.map { it.name }.toTypedArray()
                    ) { dialog, which ->
                        bleManager.connect(address = nanoDevices[which].address, onConnectError = {
                            if (BleError.CONNECTION_TIMEOUT == it) {
                                listener.error(ErrorType.CONNECT_TIMEOUT)
                            } else {
                                listener.error(ErrorType.CONNECT_ERROR)
                            }
                        }, onConnectSuccess = {
                            connectedDevice = it
                            listener.connected()
                        })
                    }.create().show()
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

    fun connectLedger(context: Context) {
        CommonAlertDialog.showDoubleButton(
            context,
            "Connect Ledger",
            "1. Unlock your Ledger\n2. Open Cosmos app on Ledger",
            "Cancel",
            null,
            "Ok"
        ) { context.startActivity(Intent(context, LedgerSelectActivity::class.java)) }
    }

    enum class ErrorType {
        PERMISSION_DENIED, CONNECT_TIMEOUT, CONNECT_ERROR, NO_DEVICE, BLUETOOTH_OFF
    }

    interface ConnectListener {
        fun error(errorType: ErrorType)
        fun connected()
    }
}