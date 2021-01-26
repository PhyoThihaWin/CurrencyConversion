package com.nanolabs.currencyconversion.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nanolabs.currencyconversion.model.Currency
import com.nanolabs.currencyconversion.model.Rate

@Database(entities = [Currency::class, Rate::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java,
                    "currency_database.db"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

//                val instance=Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "dream_thousand.db")
//                    .createFromAsset("database/dream_thousand.db")
//                    .fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance
            }
        }
    }
}