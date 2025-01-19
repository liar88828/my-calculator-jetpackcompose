package com.tutor.mycalculator.repository

import androidx.activity.result.launch
import com.tutor.mycalculator.database.dao.CalculatorDao
import com.tutor.mycalculator.database.entity.Calculator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CalculatorRepository(private val calculatorDao: CalculatorDao) {
	fun getAllCalculators(): Flow<List<Calculator>> {
		return calculatorDao.findAll()
	}

	fun getCalculatorsByIds(ids: Int): Flow<List<Calculator>> {
		return calculatorDao.findAllByIds(ids)
	}

	fun getCalculatorByNum1(num1: String): Flow<Calculator> {
		return calculatorDao.findByNum1(num1)
	}

	fun insertCalculator(calculator: Calculator) {
		CoroutineScope(Dispatchers.IO).launch {
			calculatorDao.insert(calculator)
		}
	}

	fun deleteCalculator(calculator: Calculator) {
		CoroutineScope(Dispatchers.IO).launch {
			calculatorDao.delete(calculator)
		}
	}
}
