package com.example.loancollector.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.loancollector.model.Loan

class LoanRepository(context: Context) {
private val loanDao:LoanDao
    init {
        val loanRoomDatabase = LoanRoomDatabase.getDatabase(context)
       loanDao = loanRoomDatabase!!.loanDao()
    }

    fun getAllLoanes() : LiveData<List<Loan>> = loanDao.getAllLoanes()
    suspend fun insertLoan(loan: Loan)  = loanDao.insertLoan(loan)
    suspend fun deleteLoan(loan: Loan) = loanDao.deleteLoan(loan)
    suspend fun deleteAllLoanes() = loanDao.deleteAllLoanes()
    suspend fun updateLoan(loan: Loan)= loanDao.updateLoan(loan)
}