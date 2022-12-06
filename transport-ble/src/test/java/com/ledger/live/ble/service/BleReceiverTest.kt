package com.ledger.live.ble.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("BleReceiver Test")
class BleReceiverTest {

    private lateinit var bleReceiver: BleReceiver

    @BeforeEach
    fun setup() {
        bleReceiver = BleReceiver()
    }

    @Test
    fun `handleAnswers singlepart`(){
        //Given
        val expectedPayload = "3300000405322e302e3204a6000000"
        val answer = "050000000f3300000405322e302e3204a6000000"

        //When
        val resultFirstAnswer = bleReceiver.handleAnswer("id", answer)

        //Then
        Assertions.assertNotNull(resultFirstAnswer)
        Assertions.assertEquals(expectedPayload, resultFirstAnswer!!.answer)
    }

    @Test
    fun `handleAnswers multipart`(){
        //Given
        val expectedPayload = "3300000405322e302e3204a600000004322e333004312e313601009000"
        val frameOne = "050000001d3300000405322e302e3204a6000000"
        val frameTwo = "05000104322e333004312e313601009000"

        //When
        val resultFirstAnswer = bleReceiver.handleAnswer("id", frameOne)
        val resultSecondAnswer = bleReceiver.handleAnswer("id", frameTwo)

        //Then
        Assertions.assertNull(resultFirstAnswer)
        Assertions.assertNotNull(resultSecondAnswer)
        Assertions.assertEquals(expectedPayload, resultSecondAnswer!!.answer)
    }
}