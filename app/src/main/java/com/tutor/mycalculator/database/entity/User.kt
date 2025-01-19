package com.tutor.mycalculator.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Calculator(
	@PrimaryKey(autoGenerate = true) val id: Int=0,
	@ColumnInfo(name = "num1") val num1: String,
	@ColumnInfo(name = "num2") val num2: String,
)
