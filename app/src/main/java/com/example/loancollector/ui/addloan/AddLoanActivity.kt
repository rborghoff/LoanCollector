package com.example.loancollector.ui.addloan

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.loancollector.R
import com.example.loancollector.model.Loan
import com.example.loancollector.ui.AppViewModel
import kotlinx.android.synthetic.main.activity_add_loan.*


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
                return true;
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun onClick() {
        if (etLoanTitle.text.isNotBlank() && etLoanPerson.text.isNotBlank()&&etLoanDescription.text.isNotBlank()) {

            val loan = Loan(
             etLoanTitle.text.toString(),
             etLoanPerson.text.toString(),
             etAmount.text.toString().toDouble(),
             etLoanDescription.text.toString()
            )
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_ITEM,loan)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        } else {
            Toast.makeText(this, "One or multiple fields are empty.", Toast.LENGTH_LONG).show()
        }
   
    }

    companion object {
        const val EXTRA_ITEM = "EXTRA_ITEM"
    }
}
