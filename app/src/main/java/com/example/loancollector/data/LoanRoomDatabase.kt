package com.example.loancollector.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.loancollector.model.Loan

@Database(entities = [Loan::class],version = 1,exportSchema = false)
@TypeConverters(typeConverter::class)
abstract class LoanRoomDatabase :RoomDatabase() {
    abstract fun loanDao():LoanDao

    companion object {
        private const val DATABASE_NAME = "RECIPE_DATABASE"


        @Volatile
        private var loanRoomDatabaseInstance: LoanRoomDatabase? = null

        fun getDatabase(context: Context): LoanRoomDatabase? {
            if (loanRoomDatabaseInstance == null) {
                synchronized(LoanRoomDatabase::class.java) {
                    if (loanRoomDatabaseInstance == null) {
                        loanRoomDatabaseInstance = Room.databaseBuilder(context.applicationContext, LoanRoomDatabase::class.java, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                    }
                }
            }
            return loanRoomDatabaseInstance;
        }
    }
}