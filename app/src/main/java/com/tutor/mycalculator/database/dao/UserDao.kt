package com.tutor.mycalculator.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tutor.mycalculator.database.entity.Calculator
import kotlinx.coroutines.flow.Flow

@Dao
interface CalculatorDao {

	@Query("SELECT * FROM Calculator")
	fun findAll(): Flow<List<Calculator>>

	@Query("SELECT * FROM Calculator WHERE id IN (:ids)")
	fun findAllByIds(ids: Int): Flow<List<Calculator>>

	@Query(
		"SELECT * FROM calculator WHERE num1 " +
				"LIKE :num1" +
				" LIMIT 1"
	)
	fun findByNum1(num1: String): Flow<Calculator>

	@Insert
	fun insert(calculator: Calculator)

	@Delete
	fun delete(calculator: Calculator)

}