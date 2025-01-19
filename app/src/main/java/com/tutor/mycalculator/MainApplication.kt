package com.tutor.mycalculator

import android.app.Application
import androidx.room.Room
import com.tutor.mycalculator.database.database.AppDatabase

class MainApplication : Application() {
	companion object {
		lateinit var calculateDataBase: AppDatabase
	}

	override fun onCreate() {
		super.onCreate()
		calculateDataBase = Room.databaseBuilder(
			applicationContext,
			AppDatabase::class.java,
			AppDatabase.NAME
		).build()
	}
}