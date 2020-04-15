package com.example.loancollector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.loancollector.model.Loan
import kotlinx.android.synthetic.main.activity_loan_detail.*
import kotlinx.android.synthetic.main.loan_item.*

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


//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return try {
//            finish()
//            true
//        } catch (exception: Exception) {
//            false
//        }
//    }
}
