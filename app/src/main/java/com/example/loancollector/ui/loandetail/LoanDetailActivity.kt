package com.example.loancollector.ui.loandetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loancollector.R
import com.example.loancollector.model.Loan
import kotlinx.android.synthetic.main.activity_loan_detail.*

class LoanDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_detail)
        initViews()
    }


    private fun initViews(){
        val item = intent.extras
        if(item != null){
            val loan: Loan = item.get("Loan") as Loan
           tvdetailtitle.text = loan.title
            tvdetailnaam.text = loan.naam
            tvdetailamount.text= loan.amount.toString()
            tvdetaildescription.text= loan.omschrijving
        }
    }

}
