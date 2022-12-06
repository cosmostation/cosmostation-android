package com.ledger.live.ble.extension

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Extensions Test")
class ExtensionsTest {

    @Nested
    @DisplayName("Byte")
    inner class Byte {

        @Test
        fun `toHexString() 15L value in Byte should be 0f when needZeroPadding==false`() {

            val byte: kotlin.Byte = 15L.toByte()
            val answer = byte.toHexString(false)

            assertEquals("f", answer)
        }

        @Test
        fun `toHexString() 15L value in Byte should be 0f when needZeroPadding==true`() {

            val byte: kotlin.Byte = 15L.toByte()
            val answer = byte.toHexString(true)

            assertEquals("0f", answer)
        }


        @Test
        fun `toHexString() highest value of Byte should be ff when needZeroPadding==true`() {

            val byte: kotlin.Byte = 255L.toByte()
            val answer = byte.toHexString(true)

            assertEquals("ff", answer)
        }

        @Test
        fun `toHexString() 256 in Byte should do 0 when needZeroPadding==false`() {

            val byte: kotlin.Byte = 256L.toByte()
            val answer = byte.toHexString(false)

            assertEquals("0", answer)
        }

        @Test
        fun `toHexString() 256 in Byte should do 00 when needZeroPadding==true`() {

            val byte: kotlin.Byte = 256L.toByte()
            val answer = byte.toHexString(true)

            assertEquals("00", answer)
        }

        @Test
        fun `toHexString() 0 in Byte should do 0 when needZeroPadding==false`() {

            val byte: kotlin.Byte = 0.toByte()
            val answer = byte.toHexString(false)

            assertEquals("0", answer)
        }

    }

    @Nested
    @DisplayName("ByteArray")
    inner class ByteArray {
        @Test
        fun `toHexString() with short Byte Array`() {

            val startValue = "05"
            val bytes = startValue.fromHexStringToBytes()
            val answer = bytes.toHexString()

            assertEquals(startValue, answer)
        }

        @Test
        fun `toHexString() with long Byte Array`() {

            val startValue = "44732309b2d8d54f80a9766234abad45d4001068ae702ae6f260ce31c070be8a821e3b9ed772830c8dc79d6c061f41e464399471aefc84e59107581508367e5921a8171d9a8e8f621a4f047896e2ff57bd90b858baa145f96dbbe78e5ecdad56d8ff595788b9c000b144baaaa8c6a5a2a9308f37be2537839bfdd29a0d04c5e79909d83b83d3ebdf80af45461f8481be4dcc7a32b150d9fb99cd5bb8be4233a10ad3192da1ff56d198c28b1f49c144a2348319136f4d58c756a34a988702d50a0016bb1f74691aae07b0b1881de3c5a78fb557e08d8d84e0e8d8acdb6e29332510c5aa0723ea925383f9f93c06808e898dc4d658727a9e1afbfa7f04097c9000"
            val bytes = startValue.fromHexStringToBytes()
            val answer = bytes.toHexString()

            assertEquals(startValue, answer)
        }


        @Test
        fun `toHexString() with ffff03 Byte Array`() {

            val startValue = "ffff03"
            val bytes = startValue.fromHexStringToBytes()
            val answer = bytes.toHexString()

            assertEquals(startValue, answer)
        }

        @Test
        fun `toHexString() with 0ffff3 Byte Array`() {
            val startValue = "0ffff3"
            val bytes = startValue.fromHexStringToBytes()
            val answer = bytes.toHexString()

            assertEquals(startValue, answer)
        }
    }

    @Nested
    @DisplayName("String")
    inner class Int {
        @Test
        fun `toHexString() with Int MinValue`() {
            val expectedValue = "80000000"
            val hexResult = Integer.MIN_VALUE.toHexString()

            assertEquals(expectedValue, hexResult)
        }

        @Test
        fun `toHexString() with small Int 10`() {
            val expectedValue = "0000000a"
            val hexResult = 10.toHexString()

            assertEquals(expectedValue, hexResult)
        }

        @Test
        fun `toHexString() with medium Int 512`() {
            val expectedValue = "00000200"
            val hexResult = 512.toHexString()

            assertEquals(expectedValue, hexResult)
        }

        @Test
        fun `toHexString() with Int MAX Value`() {
            val expectedValue = "7fffffff"
            val hexResult = Integer.MAX_VALUE.toHexString()

            assertEquals(expectedValue, hexResult)
        }
    }
}