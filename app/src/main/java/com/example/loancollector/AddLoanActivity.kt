package com.example.loancollector

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.loancollector.model.Loan
import com.example.loancollector.ui.AppViewModel
import kotlinx.android.synthetic.main.activity_add_loan.*
import kotlinx.android.synthetic.main.loan_item.*


class AddLoanActivity : AppCompatActivity() {
    private lateinit var addLoanViewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loan)
//initViewModel()
initViews()
    }

    private fun initViews(){
        btnAddLoan.setOnClickListener {
onClick()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish();
                // Fading animation when returning to previous activity.
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun onClick() {
        if (etLoanTitle.text.isNotBlank() && etLoanPerson.text.isNotBlank() && etLoanAmount.text.isNotBlank()&&etLoanDescription.text.isNotBlank()) {

            val loan = Loan(
             etLoanTitle.text.toString(),
             etLoanPerson.text.toString(),
             etLoanAmount.text.toString().toDouble(),
             etLoanDescription.text.toString()
            )
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_ITEM,loan)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        } else {
            Toast.makeText(this, "One of the fields or multiple are empty.", Toast.LENGTH_LONG).show()
        }
        // Animation to fade into the IngredientsActivity.
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    companion object {
        const val EXTRA_ITEM = "EXTRA_ITEM"
    }
}
