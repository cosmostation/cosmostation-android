package com.ledger.live.ble.model

sealed class BleState {
    object Idle: BleState()
    data class Scanning(
        val scannedDevices: List<BleDeviceModel>
    ): BleState()

    data class Connected (
        val connectedDevice: BleDeviceModel
    ): BleState()

    data class Disconnected(val error: BleError? = null): BleState()
}
