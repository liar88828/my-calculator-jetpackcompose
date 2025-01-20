package com.tutor.mycalculator.utils

fun intToByteArray(number: Int): ByteArray {
	val byteLength = (Integer.SIZE - Integer.numberOfLeadingZeros(number) + 7) / 8
	val byteArray = ByteArray(byteLength)

	for (i in byteArray.indices) {
		byteArray[byteArray.size - 1 - i] = (number shr (i * 8)).toByte()
	}

	return byteArray
}

// Function to format a byte array into a readable string
fun formatByteArray(byteArray: ByteArray): String {
	return byteArray.joinToString("") { "/x" + String.format("%02x", it) }
}

fun byteArrayToIntFromHexx(hexValuesList: List<Int>): Int {
	// Converting list of hex values to an integer
	var result = 0
	for (i in hexValuesList) {
		result = (result shl 8) or (i and 0xFF)
	}
	return result
}

fun byteStringToIntFromHexx(hexString: String): Int {
	// Extracting the hex values from the string
	val hexValuesList: List<Int> =
		hexString.split("/").filter { it.isNotEmpty() && it.startsWith("x") }
			.map { it.drop(1).toInt(16) }  // Converting hex strings to integers
	return byteArrayToIntFromHexx(hexValuesList)
}

// Function to convert a string of digits to a hex representation
fun myConverty(num: String): String {
	return num.map { char -> "/x" + String.format("%02X", char.toByte()) }.joinToString("")
}

// Function to convert a byte array back to an integer
fun byteArrayToInt(byteArray: ByteArray): Int {
	var convertedNumber = 0
	for (byte in byteArray) {
		convertedNumber = (convertedNumber shl 8) or (byte.toInt() and 0xFF)
	}
	return convertedNumber
}

// Function to convert a specific byte array back to an integer from hexadecimal values
fun byteArrayToIntFromHex(vararg hexValues: Int): Int {
	val byteArray = hexValues.map { it.toByte() }.toByteArray()
	return byteArrayToInt(byteArray)
}

