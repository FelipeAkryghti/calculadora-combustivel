package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.model.Calculo

@Database(entities = [Calculo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun calculoDao(): CalculoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "calculo_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
