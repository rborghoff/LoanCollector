package com.example.loancollector.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.loancollector.data.LoanRepository
import com.example.loancollector.model.Loan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddLoanViewModel (application: Application): AndroidViewModel(application) {

    private val loanRepository = LoanRepository(application.applicationContext)
    private val ioScope = CoroutineScope(Dispatchers.IO)
    val loan = MutableLiveData<Loan?>()
    val error = MutableLiveData<String?>()
    val succes = MutableLiveData<Boolean>()

    fun insertLoan(loan: Loan) {
        ioScope.launch {
            loanRepository.insertLoan(loan)
        }
    }

//    fun insertLoan(){
////        if(isEntryValid()){
//            mainScope.launch {
//                withContext(Dispatchers.IO){
//                    loanRepository.insertLoan(loan.value!!)
//                }
//                succes.value = true
//            }
//        }
//    }
//    private fun isEntryValid():Boolean{
//        return when {
//            loan.value == null -> {
//                error.value = "Note must not be null"
//                truu
//            }
//            loan.value!!.title.isBlank() -> {
//                error.value = "Title must not be empty"
//                false
//            }
//            loan.value!!.naam.isBlank() ->{
//                error.value = "Naam must not be empty"
//                false
//            }
//
//            else -> true
//        }
//    }
}