package com.tutor.mycalculator

import com.tutor.mycalculator.utils.byteArrayToInt
import com.tutor.mycalculator.utils.byteArrayToIntFromHex
import com.tutor.mycalculator.utils.byteStringToIntFromHexx
import com.tutor.mycalculator.utils.formatByteArray
import com.tutor.mycalculator.utils.intToByteArray
import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
	@Test
	fun addition_isCorrect() {
		assertEquals(4, 2 + 2)
	}


	@Test
	fun byteArray() {
		val byteArray = intToByteArray(12345)
		assertEquals("/x30/x39", formatByteArray(byteArray))
	}

	@Test
	fun convertedNumber() {
		val byteArray = intToByteArray(12345)
		val convertedNumber = byteArrayToInt(byteArray)
		assertEquals(12345, convertedNumber)
	}

	@Test
	fun specificConvertedNumber() {
		val specificConvertedNumber = byteArrayToIntFromHex(0x30, 0x39)
		assertEquals("12345", specificConvertedNumber)
	}

	@Test
	fun byteStringToIntFromHex() {
		val convertedNumberXX = byteStringToIntFromHexx("/x30/x39")
//		println("Converted Number from hex string: $convertedNumberXX")
		assertEquals(12345, convertedNumberXX)
	}

}