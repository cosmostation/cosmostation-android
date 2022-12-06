package com.ledger.live.ble.service

import android.annotation.SuppressLint
import android.app.Service
import android.bluetooth.*
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.ledger.live.ble.model.BleError
import com.ledger.live.ble.service.model.BleServiceEvent
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber

@SuppressLint("MissingPermission")
class BleService : Service() {

    //Service related
    inner class LocalBinder : Binder() {
        val service: BleService
            get() = this@BleService
    }

    private var listenningJob: Job? = null
    private val binder: IBinder = LocalBinder()
    var isBound = false
    override fun onBind(intent: Intent): IBinder {
        isBound = true
        initialize()
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        isBound = false
        Timber.d("Unbind service")
        disconnectService()
        return super.onUnbind(intent)
    }

    //Bluetooth related
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO + Job())
    private lateinit var bluetoothAdapter: BluetoothAdapter
    private var bluetoothDeviceAddress: String? = null

    private val gattCallback = BleGattCallbackFlow()
    private var stateMachine: BleServiceStateMachine? = null

    private val events: MutableSharedFlow<BleServiceEvent> = MutableSharedFlow(0, 1)

    fun initialize(): Boolean {
        return try {
            val bluetoothManager = getSystemService(BLUETOOTH_SERVICE) as BluetoothManager
            bluetoothAdapter = bluetoothManager.adapter
            true
        } catch (exception: Exception) {
            false
        }
    }

    fun disconnectService(bleError: BleError? = null) {
        listenningJob?.cancel()
        stateMachine?.clear()
        stateMachine = null
        gattCallback.clear()

        stopSelf()
        notify(BleServiceEvent.BleDeviceDisconnected(bleError))
    }


    fun connect(address: String): Boolean {
        // Previously connected to the given device.
        // Try to reconnect.
        Timber.d("Connect to device address => $address.")
        if (bluetoothDeviceAddress != null && address == bluetoothDeviceAddress && stateMachine != null) {
            stateMachine?.clear()
        }

        val device: BluetoothDevice = bluetoothAdapter.getRemoteDevice(address)

        // We want to directly connect to the device, so we are setting the autoConnect
        // parameter to false.
        stateMachine = BleServiceStateMachine(
            gattCallback,
            address,
            device
        )
        observeStateMachine()
        stateMachine?.build(this.applicationContext)
        bluetoothDeviceAddress = address

        return true
    }

    var isReady = false
    private fun observeStateMachine() {
        listenningJob = stateMachine?.stateFlow?.onEach {
            Timber.d("State changed >>>> $it")
            when (it) {
                is BleServiceStateMachine.BleServiceState.Ready -> {
                    if (isReady == false) {
                        isReady = true
                        notify(BleServiceEvent.BleDeviceConnected)
                    }

                    it.answer?.let { answer ->
                        notify(
                            BleServiceEvent.SendAnswer(
                                sendId = answer.id,
                                answer = answer.answer
                            )
                        )
                    }
                }
                is BleServiceStateMachine.BleServiceState.Error -> {
                    disconnectService(it.error)
                }
            }
        }
            ?.flowOn(Dispatchers.IO)
            ?.launchIn(scope)
    }

    private fun notify(event: BleServiceEvent) {
        events.tryEmit(event)
    }

    fun listenEvents(): Flow<BleServiceEvent> {
        return events
    }

    @Synchronized
    fun sendApdu(apdu: ByteArray): String {
        Timber.d("Send APDU")
        if (bluetoothDeviceAddress == null) {
            disconnectService(BleError.NO_DEVICE_ADDRESS)
        }

        return stateMachine!!.sendApdu(apdu)
    }

    companion object {
        internal const val MTU_HANDSHAKE_COMMAND = "0800000000"
        private const val APP_VERSION_COMMAND = "b001000000"

        private const val ERROR_CODE_NO_ERROR = -1
    }
}