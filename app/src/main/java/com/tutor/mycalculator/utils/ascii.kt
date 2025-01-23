package com.tutor.mycalculator.utils

import com.tutor.mycalculator.assets.CharacterInfo
import com.tutor.mycalculator.assets.characterList

fun splitInputString(input: String): List<String> {
	return input.split("/x").filter { it.isNotEmpty() }
}

fun sliceParts(parts: List<String>): List<String> {
	val processedParts = mutableListOf<String>()
	for (part in parts) {
		if (part.length == 3) {
			processedParts.add(part.substring(0, 2))
			processedParts.add(part.substring(2, 3))
		} else {
			processedParts.add(part)
		}
	}
	return processedParts
}

fun convertToDecimal(part: String): Int {
	return when {
		part.length == 2 -> part.toInt(16)

		part.length == 1 -> {
			val matchingCharacter = characterList.find { it.Character == part }
			matchingCharacter?.Decimal ?: -1
		}

		else -> -1
	}
}

fun filterInvalidResults(decimalValues: List<Int>): List<Int> {
	return decimalValues.filter { it != -1 }
}

fun AsciiToNumber(myValue: String): String? {
	try {
//		val myValue = "/xa3/xbcQ/xaf"
		val parts = splitInputString(myValue)
		val processedParts = sliceParts(parts)
		val decimalValues = processedParts.map { convertToDecimal(it) }
		val validDecimalValues = filterInvalidResults(decimalValues)
		return validDecimalValues.joinToString(",")
	} catch (_e: Exception) {
		return null
	}
}

//fun NumberToBytes(myValue: String): String {
//	val myList: List<Int> = myValue
//	val list = listOf(myList)
//	return list.joinToString("") { number ->
//		if (number in 32..126) {
//			number.toChar().toString() // For printable ASCII characters
//		} else {
//			"\\x${number.toString(16).padStart(2, '0')}" // For non-printable characters
//		}
//	}
//}
fun NumberToBytes(myValue: String): String? {
	try {
		// Split the input string by commas, assuming the input is something like "163,188,81,175"
		val myList: List<Int> = myValue.split(",").mapNotNull { it.trim().toIntOrNull() }

		return myList.joinToString("") { number ->
			if (number in 32..126) {
				number.toChar().toString() // For printable ASCII characters
			} else {
				"/x${number.toString(16).padStart(2, '0')}" // For non-printable characters
			}
		}
	} catch (e: Exception) {
		return null
	}
}

fun NumberToASCII(): String {
	val list = listOf(163, 188, 81, 175)
	val characterList = list.map { number ->
		CharacterInfo(
			Decimal = number,
			Hex = number.toString(16), // Convert to hexadecimal
			Character = number.toChar().toString() // Convert to ASCII character
		)
	}
	// Convert the list of characters to a single string representation
	return characterList.joinToString(", ") { "${it.Character} (Dec: ${it.Decimal}, Hex: ${it.Hex})" }
}
