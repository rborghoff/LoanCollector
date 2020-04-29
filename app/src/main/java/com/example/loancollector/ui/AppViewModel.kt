package com.example.loancollector.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.loancollector.data.LoanRepository
import com.example.loancollector.model.Loan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppViewModel (application: Application) : AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val loanRepository = LoanRepository(application.applicationContext)

    val loan: LiveData<List<Loan>> = loanRepository.getAllLoanes()

    fun insertLoan(loan: Loan) {
        ioScope.launch {
            loanRepository.insertLoan(loan)
        }
    }

    fun deleteLoan(loan: Loan) {
        ioScope.launch {
            loanRepository.deleteLoan(loan )
        }
    }

    // wel er in gezet maar het nut niet gezien om alles in een keer te willen verwijderen
    fun deleteAllLoanes() {
        ioScope.launch {
            loanRepository.deleteAllLoanes()
        }
    }
}