package com.tutor.mycalculator.persentation.calculate

import com.tutor.mycalculator.database.entity.Calculator

sealed interface CalculateEvent {
	data object Data : CalculateEvent
	data class Search(val value: String) : CalculateEvent
	data class Delete(val calculator: Calculator) : CalculateEvent
	data class Save(val calculator: Calculator) : CalculateEvent
}