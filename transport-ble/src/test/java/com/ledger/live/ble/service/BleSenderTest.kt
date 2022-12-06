package com.ledger.live.ble.service

import com.ledger.live.ble.extension.fromHexStringToBytes
import com.ledger.live.ble.model.BleDeviceService
import io.mockk.*
import org.junit.jupiter.api.*

@DisplayName("BleSender Test")
class BleSenderTest {

    private lateinit var mockedCallback: (String) -> Unit
    private lateinit var bleSender: BleSender
    private lateinit var gattInteractor: GattInteractor

    private val bleDeviceService: BleDeviceService = mockk()

    @BeforeEach
    fun setup() {
        gattInteractor = mockk()
        mockedCallback = mockk()
        bleSender = BleSender(gattInteractor, "address", mockedCallback)
    }

    @Nested
    @DisplayName("initialized()")
    inner class Initialized {

        @Test
        fun `Dequeu APDU without being initialized should throw IllegalStateException`() {
            assertThrows<IllegalStateException> {
                bleSender.dequeuApdu()
            }
        }

        @Test
        fun `Dequeu APDU being initialized shouldn't throw exception`() {
            assertDoesNotThrow {
                bleSender.initialized(153, bleDeviceService)
                bleSender.dequeuApdu()
            }
        }
    }

    @Nested
    @DisplayName("queueApdu()")
    inner class QueueApdu {

        val apdu = "b001000000".fromHexStringToBytes()

        @BeforeEach
        fun setup() {
            gattInteractor = mockk()
            bleSender = BleSender(gattInteractor, "address", mockedCallback)
            bleSender.initialized(153, bleDeviceService)
        }

        @Test
        fun `queue Apdu should increase queue size of request`() {
            assert(bleSender.pendingApdu.size == 0)
            val id = bleSender.queuApdu(apdu)
            assert(bleSender.pendingApdu.size == 1)

            val bleRequest = bleSender.pendingApdu.peek()
            assertAll(
                "bleRequest is from the given APDU",
                {
                    Assertions.assertEquals(
                        id,
                        bleRequest.id,
                        "Id received when queuing apdu should be the same than the first pending request"
                    )
                },
                { Assertions.assertArrayEquals(apdu, bleRequest.apdu, "Apdu is still the same") },
            )
        }
    }

    @Nested
    @DisplayName("dequeueApdu()")
    inner class DequeueApdu {
        val apdu = "b001000000".fromHexStringToBytes()

        @BeforeEach
        fun setup() {
            gattInteractor = mockk()
            bleSender = BleSender(gattInteractor, "address", mockedCallback)
            bleSender.initialized(153, bleDeviceService)
        }

        @Test
        fun `dequeue Apdu should not do anything if queue is empty`() {
            //Given
            assert(bleSender.pendingApdu.size == 0)
            justRun { gattInteractor.sendBytes(bleDeviceService, any()) }
            justRun { mockedCallback.invoke(any()) }

            //When
            bleSender.dequeuApdu()

            //Then
            assert(bleSender.pendingApdu.size == 0)
            verify {
                gattInteractor wasNot Called
                mockedCallback wasNot Called
            }
        }

        @Test
        fun `dequeue Apdu should decrease queue size of request and call sendCommands`() {
            //Given
            bleSender.queuApdu(apdu)
            assert(bleSender.pendingApdu.size == 1)
            justRun { gattInteractor.sendBytes(bleDeviceService, any()) }
            justRun { mockedCallback.invoke(any()) }

            //When
            bleSender.dequeuApdu()

            //Then
            assert(bleSender.pendingApdu.size == 0)
            verifyOrder {
                gattInteractor.sendBytes(bleDeviceService, any())
                mockedCallback.invoke(any())
            }
        }
    }

}