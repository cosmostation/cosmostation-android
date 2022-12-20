package wannabit.io.cosmostaion.utils

import android.Manifest
import android.bluetooth.BluetoothManager
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.ledger.live.ble.BleManager
import com.ledger.live.ble.BleManagerFactory
import com.ledger.live.ble.model.BleDeviceModel
import wannabit.io.cosmostaion.base.BaseApplication

class LedgerUtils {
    companion object {
        val instance = LedgerUtils()
    }

    val bleManager: BleManager = BleManagerFactory.newInstance(BaseApplication.getInstance())
    var connectedDevice: BleDeviceModel? = null

    fun isConnected(): Boolean {
        return connectedDevice != null && bleManager.isConnected
    }

    fun connect(listener: () -> Unit) {
        if (ActivityCompat.checkSelfPermission(
                BaseApplication.getInstance(), Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        val pairedDevices = BaseApplication.getInstance()
            .getSystemService(BluetoothManager::class.java).adapter.bondedDevices
        if (pairedDevices.size > 0) {
            for (d in pairedDevices) {
                val deviceName = d.name
                val macAddress = d.address
                if (deviceName.contains("Nano X")) {
                    bleManager.connect(address = macAddress,
                        onConnectError = {},
                        onConnectSuccess = {
                            connectedDevice = it
                            listener()
                        })
                }
            }
        }
    }
}