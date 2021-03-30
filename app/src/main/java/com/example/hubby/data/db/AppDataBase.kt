package com.example.hubby.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(
        entities = [Habit::class],
        version = 1
)
abstract  class AppDataBase : RoomDatabase() {

    abstract fun habitDao(): Dao

    companion object {

        @Volatile
        var INSTANCE : AppDataBase?  = null
        fun getAppDB(context: Context) : AppDataBase?
        {
            INSTANCE ?:

            synchronized( AppDataBase::class) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDataBase::class.java,
                        "habby.db"
                ).build()
            }

            return INSTANCE
        }

        // destroy the DB
        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}