package com.ledger.live.ble.service

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattService
import android.content.Context
import app.cash.turbine.test
import com.ledger.live.ble.BleManager
import com.ledger.live.ble.extension.fromHexStringToBytes
import com.ledger.live.ble.extension.toHexString
import com.ledger.live.ble.extension.toUUID
import com.ledger.live.ble.service.model.GattCallbackEvent
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Ble State Machine Test")
class BleServiceStateMachineTest {

    private lateinit var stateMachine: BleServiceStateMachine
    private lateinit var mockedFlow: MutableSharedFlow<GattCallbackEvent>
    private val gatt: BluetoothGatt = mockk()
    private val callbackFlow: BleGattCallbackFlow = mockk()
    private val mtuSize = 153

    @BeforeEach
    fun setup() {
        val device: BluetoothDevice = mockk()

        every { device.connectGatt(any(), any(), any()) } returns gatt
        val mockContext: Context = mockk()

        mockedFlow = MutableSharedFlow()
        every { callbackFlow.gattFlow } returns mockedFlow

        stateMachine = BleServiceStateMachine(callbackFlow, "address", device)
        stateMachine.build(mockContext)

        val mockCharacteristic = mockk<BluetoothGattCharacteristic>() {
            every { setValue(any<ByteArray>()) } returns true
        }
        stateMachine.deviceService = mockk {
            every { writeNoAnswerCharacteristic } returns mockCharacteristic
            every { writeCharacteristic } returns mockCharacteristic
            every { notifyCharacteristic } returns mockCharacteristic
        }
        every { gatt.writeCharacteristic(any()) } returns true

        stateMachine.mtuSize = mtuSize
    }

    @Nested
    @DisplayName("State::Created")
    inner class CreatedState {

        @Test
        fun `Given state is created when waiting more than 5 seconds we should have a timeout error`() =
            runTest {
                //Given
                stateMachine.currentState = BleServiceStateMachine.BleServiceState.Created

                //When
                delay(5000)

                //Then
                stateMachine.stateFlow.test {
                    assertEquals(
                        BleServiceStateMachine.BleServiceState.Error(BleServiceStateMachine.ERROR_TIMEOUT),
                        awaitItem()
                    )
                }
            }

        @Test
        fun `Given state is not Connected when receiving Connected Event try to discover services`() =
            runTest {
                //Given
                every { gatt.discoverServices() } returns true

                //When
                mockedFlow.emit(GattCallbackEvent.ConnectionState.Connected)

                //Then
                stateMachine.stateFlow.test {
                    assertEquals(
                        BleServiceStateMachine.BleServiceState.WaitingServices,
                        awaitItem()
                    )
                    verify { gatt.discoverServices() }
                }
            }
    }

    @Nested
    @DisplayName("State::WaitingServices")
    inner class WaitingServices {
        @Test
        fun `Given state is NOT Waiting Services when receiving ServicesDiscovered Event then send Error State`() =
            runTest {
                //Given
                stateMachine.currentState = BleServiceStateMachine.BleServiceState.Created
                //When
                mockedFlow.emit(GattCallbackEvent.ServicesDiscovered(emptyList()))

                //Then
                stateMachine.stateFlow.test {
                    assertEquals(
                        BleServiceStateMachine.BleServiceState.Error(BleServiceStateMachine.ERROR_WRONG_STATE_FOR_SERVICES_DISCOVERED),
                        awaitItem()
                    )
                }
            }

        @Test
        fun `Given state is Waiting Services when receiving ServicesDiscovered Event try to Negotiate MTU`() =
            runTest {
                //Given
                stateMachine.currentState = BleServiceStateMachine.BleServiceState.WaitingServices

                every { gatt.requestMtu(any()) } returns true
                val mockedService = mockk<BluetoothGattService>()
                val writeCharacteristic: BluetoothGattCharacteristic = mockk()
                every { writeCharacteristic.uuid } returns BleManager.nanoXWriteWithResponseCharacteristicUUID.toUUID()

                val writeNoAnswerCharacteristic: BluetoothGattCharacteristic = mockk()
                every { writeNoAnswerCharacteristic.uuid } returns BleManager.nanoXWriteWithoutResponseCharacteristicUUID.toUUID()

                val notifyCharacteristic: BluetoothGattCharacteristic = mockk()
                every { notifyCharacteristic.uuid } returns BleManager.nanoXNotifyCharacteristicUUID.toUUID()

                every { mockedService.uuid } returns BleManager.NANO_X_SERVICE_UUID.toUUID()
                every { mockedService.characteristics } returns listOf(
                    writeNoAnswerCharacteristic,
                    writeCharacteristic,
                    notifyCharacteristic
                )

                //When
                mockedFlow.emit(GattCallbackEvent.ServicesDiscovered(listOf(mockedService)))

                //Then
                stateMachine.stateFlow.test {
                    assertEquals(BleServiceStateMachine.BleServiceState.NegotiatingMtu, awaitItem())
                    verify { gatt.requestMtu(any()) }
                }
            }

        @Test
        fun `Given state is Waiting Services when receiving ServicesDiscovered Event with empty services Then send Error State`() =
            runTest {
                //Given
                stateMachine.currentState = BleServiceStateMachine.BleServiceState.WaitingServices
                every { gatt.requestMtu(any()) } returns true

                //When
                mockedFlow.emit(GattCallbackEvent.ServicesDiscovered(emptyList()))

                //Then
                stateMachine.stateFlow.test {
                    assertEquals(
                        BleServiceStateMachine.BleServiceState.Error(BleServiceStateMachine.ERROR_NO_SERVICES_FOUND),
                        awaitItem()
                    )
                }
            }
    }

    @Nested
    @DisplayName("State::NegotiatingMtu")
    inner class NegotiatingMtu {
        @Test
        fun `Given state is NOT Negotiating Mtu when receiving Mtu Negotiated Event then send Error State`() =
            runTest {
                //Given
                stateMachine.currentState = BleServiceStateMachine.BleServiceState.WaitingServices

                //When
                mockedFlow.emit(GattCallbackEvent.MtuNegociated(153))

                //Then
                stateMachine.stateFlow.test {
                    assertEquals(
                        BleServiceStateMachine.BleServiceState.Error(BleServiceStateMachine.ERROR_WRONG_STATE_FOR_MTU_NEGOTIATED),
                        awaitItem()
                    )
                }
            }

        @Test
        fun `Given state is Negotiating Mtu when receiving Mtu Negotiated Event then send try to enable notification`() =
            runTest {
                //Given
                stateMachine.currentState = BleServiceStateMachine.BleServiceState.NegotiatingMtu
                stateMachine.deviceService = mockk()
                every { stateMachine.deviceService.notifyCharacteristic } returns mockk {
                    every { descriptors } returns listOf(
                        mockk {
                            every { setValue(any()) } returns true
                        }
                    )
                }
                every { gatt.setCharacteristicNotification(any(), true) } returns true
                every { gatt.writeDescriptor(any()) } returns true

                //When
                mockedFlow.emit(GattCallbackEvent.MtuNegociated(153))

                //Then
                stateMachine.stateFlow.test {
                    assertEquals(
                        BleServiceStateMachine.BleServiceState.WaitingNotificationEnable,
                        awaitItem()
                    )
                    assertEquals(153, stateMachine.negotiatedMtu)
                    verify { gatt.setCharacteristicNotification(any(), true) }
                    verify { gatt.writeDescriptor(any()) }
                }
            }
    }

    @Nested
    @DisplayName("State::WaitingNotificationEnable")
    inner class WaitingNotificationEnable {
        @Test
        fun `Given state is NOT Waiting Notification Enable when receiving Mtu Negotiated Event then send Error State`() =
            runTest {
                //Given
                stateMachine.currentState = BleServiceStateMachine.BleServiceState.WaitingServices

                //When
                mockedFlow.emit(GattCallbackEvent.WriteDescriptorAck(true))

                //Then
                stateMachine.stateFlow.test {
                    assertEquals(
                        BleServiceStateMachine.BleServiceState.Error(BleServiceStateMachine.ERROR_WRONG_STATE_FOR_WRITE_DESCRIPTOR_ACK),
                        awaitItem()
                    )
                }
            }

        @Test
        fun `Given state is Waiting Notification Enable when receiving Write Descriptor Ack Event then send CheckingMtu State`() =
            runTest {
                //Given
                stateMachine.currentState =
                    BleServiceStateMachine.BleServiceState.WaitingNotificationEnable

                //When
                mockedFlow.emit(GattCallbackEvent.WriteDescriptorAck(true))

                //Then
                stateMachine.stateFlow.test {
                    assertEquals(BleServiceStateMachine.BleServiceState.CheckingMtu, awaitItem())
                }
            }
    }

    @Nested
    @DisplayName("State::CheckingMtu")
    inner class CheckingMtu {

        @Test
        fun `Given state is NOT Checking MTU when receiving Characteristic Changed Event then send Ready State`() =
            runTest {
                //Given
                stateMachine.currentState = BleServiceStateMachine.BleServiceState.NegotiatingMtu

                //When
                mockedFlow.emit(GattCallbackEvent.CharacteristicChanged("${BleService.MTU_HANDSHAKE_COMMAND}${mtuSize.toByte().toHexString()}".fromHexStringToBytes()))

                //Then
                stateMachine.stateFlow.test {
                    assertEquals(
                        BleServiceStateMachine.BleServiceState.Error(BleServiceStateMachine.ERROR_WRONG_STATE_FOR_CHARACTERISTIC_CHANGED),
                        awaitItem()
                    )
                }
            }

        @Test
        fun `Given state is Checking MTU when receiving Characteristic Changed Event then send Ready State`() =
            runTest {
                //Given
                stateMachine.currentState = BleServiceStateMachine.BleServiceState.CheckingMtu
                stateMachine.negotiatedMtu = mtuSize

                //When
                mockedFlow.emit(GattCallbackEvent.CharacteristicChanged("${BleService.MTU_HANDSHAKE_COMMAND}${mtuSize.toByte().toHexString()}".fromHexStringToBytes()))

                //Then
                stateMachine.stateFlow.test {
                    assertEquals(
                        BleServiceStateMachine.BleServiceState.Ready(
                            stateMachine.deviceService,
                            mtuSize,
                            null
                        ), awaitItem()
                    )
                }
            }
    }

    @Nested
    @DisplayName("State::Ready")
    inner class Ready {
        @Test
        fun `Given state is Ready when sending APDU then send Waiting Response`() =
            runTest {
                //Given
                stateMachine.bleSender.initialized(mtuSize, stateMachine.deviceService)
                stateMachine.negotiatedMtu = mtuSize
                stateMachine.currentState = BleServiceStateMachine.BleServiceState.Ready(
                    stateMachine.deviceService,
                    mtuSize,
                    null
                )

                //When
                val id = stateMachine.sendApdu("mysuperApdu".toByteArray())

                //Then
                stateMachine.stateFlow.test {
                    assertEquals(
                        BleServiceStateMachine.BleServiceState.WaitingResponse(id), awaitItem()
                    )
                }
            }

        @Test
        fun `Given state is NOT Ready when sending APDU then try initialize state machine by discovering services and queue APDU`() =
            runTest {
                //Given
                stateMachine.currentState = BleServiceStateMachine.BleServiceState.CheckingMtu
                every { gatt.discoverServices() } returns true

                //When
                val id = stateMachine.sendApdu("mysuperApdu".toByteArray())

                //Then
                assertEquals(1, stateMachine.bleSender.pendingApdu.size)
                stateMachine.stateFlow.test {
                    assertEquals(
                        BleServiceStateMachine.BleServiceState.WaitingServices, awaitItem()
                    )
                }
            }
    }

    @Nested
    @DisplayName("State::WaitingResponse")
    inner class WaitingResponse {
        @Test
        fun `Given state is WaitingResponse when calling send APDU then send queue apdu`() =
            runTest {
                //Given
                stateMachine.bleSender.initialized(mtuSize, stateMachine.deviceService)
                stateMachine.negotiatedMtu = mtuSize
                stateMachine.currentState = BleServiceStateMachine.BleServiceState.WaitingResponse("id")

                assertEquals(0, stateMachine.bleSender.pendingApdu.size)
                assertNull(stateMachine.bleSender.pendingCommand)

                //When
                val id1 = stateMachine.sendApdu("mysuperApdu".toByteArray())

                //Try to send first one APDU directly so no queue increasing
                assertEquals(0, stateMachine.bleSender.pendingApdu.size)
                assertNotNull(stateMachine.bleSender.pendingCommand)
                assertEquals(id1, stateMachine.bleSender.pendingCommand?.id)

                val id2 = stateMachine.sendApdu("mysuperApdu2".toByteArray())

                //Still Waiting response so queue second APDU
                assertEquals(1, stateMachine.bleSender.pendingApdu.size)
                assertEquals(id2, stateMachine.bleSender.pendingApdu.element().id)

            }
    }
}