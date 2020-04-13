package com.example.loancollector.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.loancollector.model.Loan

@Dao
interface LoanDao {

    @Query("SELECT*FROM loan_table")
    fun getAllLoanes(): LiveData<List<Loan>>

    @Query("DELETE FROM loan_table")
    suspend fun deleteAllLoanes()

    @Insert
    suspend fun insertLoan(loan: Loan)

    @Delete
    suspend fun deleteLoan(loan: Loan)
}