package com.example.loancollector.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.loancollector.model.Loan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.chrono.HijrahChronology.INSTANCE

@Database(entities = [Loan::class],version = 1,exportSchema = false)
@TypeConverters(typeConverter::class)
abstract class LoanRoomDatabase :RoomDatabase() {
    abstract fun loanDao():LoanDao

    companion object {
        private const val DATABASE_NAME = "RECIPE_DATABASE"


        @Volatile
        private var INSTANCE: LoanRoomDatabase? = null

        fun getDatabase(context: Context): LoanRoomDatabase?{
            if (INSTANCE == null){
                synchronized(LoanRoomDatabase::class.java){
                    if(INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,LoanRoomDatabase::class.java,DATABASE_NAME
                        )
                            .allowMainThreadQueries()
                            .addCallback(object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    INSTANCE?.let { database ->
                                        CoroutineScope(Dispatchers.IO).launch {
                                            database.loanDao().insertLoan(Loan("Title", "title",2.0, "maandag",3))
                                        }
                                    }
                                }
                            })

                            .build()
                    }

                }
            }
            return INSTANCE
        }
    }

}