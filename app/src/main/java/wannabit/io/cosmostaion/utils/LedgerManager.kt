package wannabit.io.cosmostaion.utils

import android.Manifest
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager
import android.preference.PreferenceManager
import androidx.core.app.ActivityCompat
import com.ledger.live.ble.BleManager
import com.ledger.live.ble.BleManagerFactory
import com.ledger.live.ble.extension.fromHexStringToBytes
import com.ledger.live.ble.extension.toHexString
import com.ledger.live.ble.model.BleDeviceModel
import com.trustwallet.walletconnect.extensions.hexStringToByteArray
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.base.BaseApplication
import wannabit.io.cosmostaion.base.BaseConstant

class LedgerManager {
    companion object {
        val instance = LedgerManager()
    }

    private val bleManager: BleManager =
        BleManagerFactory.newInstance(BaseApplication.getInstance())
    var device: BleDeviceModel? = null

    fun isConnect(listener: (address: String, pubKey: ByteArray) -> Void) {
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
                            device = it
                            getAddress(listener)
                        })
                }
            }
        }
    }


    val CHUNK_SIZE = 250;
    val CLA = 0x55;
    val APP_KEY = "CSM";
    val INS_GET_VERSION = 0x00;
    val INS_SIGN_SECP256K1 = 0x02;
    val INS_GET_ADDR_SECP256K1 = 0x04;
    val PAYLOAD_TYPE_INIT = 0x00;
    val PAYLOAD_TYPE_ADD = 0x01;
    val PAYLOAD_TYPE_LAST = 0x02;
    val SW_OK = 0x9000;
    val SW_CANCEL = 0x6986;

    fun serializePath(): ByteArray {
        return byteArrayOf(
            44,
            0,
            0,
            128.toByte(),
            118,
            0,
            0,
            128.toByte(),
            0,
            0,
            0,
            128.toByte(),
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0
        )
    }

    fun serializeHRP(hrp: String): ByteArray {
        var t = byteArrayOf()
        t += hrp.length.toByte()
        t += hrp.toByteArray()
        return t
    }

    fun getAddress(listener: (address: String, pubKey: ByteArray) -> Void) {
        val serializedPath = this.serializePath()
        val data = serializeHRP("osmo") + serializedPath
        val byteArray = byteArrayOf(
            CLA.toByte(), INS_GET_ADDR_SECP256K1.toByte(), 0.toByte(), 0.toByte()
        ) + data.size.toByte() + data
        bleManager.send(apduHex = byteArray.toHexString(), onError = {}, onSuccess = {
            try {
                val address = String(it.substring(66, it.length - 4).fromHexStringToBytes())
                val pubKey = it.substring(0, 66).fromHexStringToBytes()
                val result = it.substring(it.length - 4, it.length)
                listener(address, pubKey)
            } catch (e: Exception) {
            }
        })
    }

    fun sign(message: String, listener: (signature: ByteArray) -> Void) {
        val serializedPath = this.serializePath()
        val chunks = mutableListOf<ByteArray>()
        chunks.add(serializedPath)
        val buffer = message.toByteArray()
        buffer.iterator().asSequence().chunked(CHUNK_SIZE).forEach {
            chunks.add(it.toByteArray())
        }

        for ((index, value) in chunks.withIndex()) {
            if (index == 0) {
                var byteArray = byteArrayOf(
                    CLA.toByte(),
                    INS_SIGN_SECP256K1.toByte(),
                    PAYLOAD_TYPE_INIT.toByte(),
                    0.toByte()
                ) + value.size.toByte() + value
                bleManager.send(byteArray)
            } else if (index + 1 == chunks.count()) {
                val byteArray = byteArrayOf(
                    CLA.toByte(),
                    INS_SIGN_SECP256K1.toByte(),
                    PAYLOAD_TYPE_LAST.toByte(),
                    0.toByte()
                ) + value.size.toByte() + value
                bleManager.send(apduHex = byteArray.toHexString(), onError = {}, onSuccess = {
                    listener(it.substring(0, it.length - 4).fromHexStringToBytes())
                })
            } else {
                val byteArray = byteArrayOf(
                    CLA.toByte(), INS_SIGN_SECP256K1.toByte(), PAYLOAD_TYPE_ADD.toByte(), 0.toByte()
                ) + value.size.toByte() + value
                bleManager.send(byteArray)
            }

        }
    }
}