package com.tutor.mycalculator.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tutor.mycalculator.database.Converters
import com.tutor.mycalculator.database.dao.CalculatorDao
import com.tutor.mycalculator.database.entity.Calculator

@Database(
	entities = [Calculator::class],
	version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
	abstract fun calculatorDao(): CalculatorDao

	companion object {
		const val NAME = "Calculate_DB"
	}

}