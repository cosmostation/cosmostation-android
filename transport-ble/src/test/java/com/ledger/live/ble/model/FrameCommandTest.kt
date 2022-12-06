package com.ledger.live.ble.model

import com.ledger.live.ble.extension.fromHexStringToBytes
import com.ledger.live.ble.extension.toHexString
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("FrameCommand Test")
class FrameCommandTest {

    @Nested
    @DisplayName("FromHex")
    inner class FromHex {

        @Test
        fun `when hex represent a first frame with all content`() {
            //Given
            val hexCommand = "05000000130105424f4c4f5309322e302e322d696c309000"
            val hexApdu = "0105424f4c4f5309322e302e322d696c309000"
            //When
            val frame = FrameCommand.fromHex("id", hexCommand)

            //Then
            Assertions.assertEquals(0, frame.index)
            Assertions.assertEquals(19, frame.size) //0013 value
            Assertions.assertTrue(hexApdu.fromHexStringToBytes() contentEquals frame.apdu)
        }

        @Test
        fun `when hex represent a first frame with a part of the content`() {
            //Given
            val hexCommand =
                "05000000f3e0000000eee2668742357150eba372ffee3d6b7c49ce71bcae3aa0d5fb0f17712062cbe41109d9c1db3613d9223e469e126286c61850b9e9b8b13638ca6cfe1b0453a2c5faff223c6cb605f61db48152e728715299dc4dff8b122c5789e8b0e1517f5979f47a301d51e435580dd7e11b7134249a56505c2239b3de1623e704a828732518d3b1801469648269fdbd79e5988c0458"
            val hexApdu =
                "e0000000eee2668742357150eba372ffee3d6b7c49ce71bcae3aa0d5fb0f17712062cbe41109d9c1db3613d9223e469e126286c61850b9e9b8b13638ca6cfe1b0453a2c5faff223c6cb605f61db48152e728715299dc4dff8b122c5789e8b0e1517f5979f47a301d51e435580dd7e11b7134249a56505c2239b3de1623e704a828732518d3b1801469648269fdbd79e5988c0458"

            //When
            val frame = FrameCommand.fromHex("id", hexCommand)

            //Then
            Assertions.assertEquals(0, frame.index)
            Assertions.assertEquals(243, frame.size) //00f3 value
            Assertions.assertTrue(hexApdu.fromHexStringToBytes() contentEquals frame.apdu)
        }


        @Test
        fun `when hex represent a second frame with a part of the content`() {
            //Given
            val hexCommand =
                "05000123d42b992a45d79ddf09b30d52c8bb3dfcfbb357cfa65be182045f973d9a85084356c40c495864d0c16bb996969775927f6f75df205c2e4e2dac4acf52d72cb92955544d9fcd87efea1d65bcdb35684ef64f0cce5925285b8cab443a61d5b4"
            val hexApdu =
                "23d42b992a45d79ddf09b30d52c8bb3dfcfbb357cfa65be182045f973d9a85084356c40c495864d0c16bb996969775927f6f75df205c2e4e2dac4acf52d72cb92955544d9fcd87efea1d65bcdb35684ef64f0cce5925285b8cab443a61d5b4"

            //When
            val frame = FrameCommand.fromHex("id", hexCommand)

            //Then
            Assertions.assertEquals(1, frame.index) //0001 value
            Assertions.assertEquals(
                0,
                frame.size
            ) //no size given for the frame different from frame with index 0
            Assertions.assertTrue(hexApdu.fromHexStringToBytes() contentEquals frame.apdu)
        }
    }

    @Nested
    @DisplayName("BuildCommandHeader")
    inner class BuildCommandHeader {

        @Test
        fun `when frame command represent a frame of index`() {
            //Given
            val hexCommand =
                "05000123d42b992a45d79ddf09b30d52c8bb3dfcfbb357cfa65be182045f973d9a85084356c40c495864d0c16bb996969775927f6f75df205c2e4e2dac4acf52d72cb92955544d9fcd87efea1d65bcdb35684ef64f0cce5925285b8cab443a61d5b4"
            val hexApdu =
                "23d42b992a45d79ddf09b30d52c8bb3dfcfbb357cfa65be182045f973d9a85084356c40c495864d0c16bb996969775927f6f75df205c2e4e2dac4acf52d72cb92955544d9fcd87efea1d65bcdb35684ef64f0cce5925285b8cab443a61d5b4"

            //When
            val frame = FrameCommand.fromHex("id", hexCommand)

            //Then
            Assertions.assertEquals(1, frame.index) //0001 value
            Assertions.assertEquals(
                0,
                frame.size
            ) //no size given for the frame different from frame with index 0
            Assertions.assertTrue(hexApdu.fromHexStringToBytes() contentEquals frame.apdu)
        }

    }
}