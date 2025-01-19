package com.tutor.mycalculator.persentation.calculate

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.tutor.mycalculator.database.entity.Calculator

data class CalculateState(
	val data: List<Calculator> = emptyList(),
	val isLoading: Boolean = false,
	val isError: Boolean = false,
	val num1: MutableState<String> = mutableStateOf(""),
	val num2: MutableState<String> = mutableStateOf("")
)
