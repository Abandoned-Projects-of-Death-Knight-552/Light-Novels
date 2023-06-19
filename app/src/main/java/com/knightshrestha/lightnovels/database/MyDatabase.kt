package com.knightshrestha.lightnovels.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [SeriesItem::class], version = 1, exportSchema = false)
@TypeConverters(DateConverters::class, ListConverters::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun dao(): DAO

    companion object {

        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, MyDatabase::class.java, "Origin"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}