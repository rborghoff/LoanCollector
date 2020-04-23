package com.example.loancollector.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loancollector.AddLoanActivity
import com.example.loancollector.LoanDetailActivity

import com.example.loancollector.R
import com.example.loancollector.model.Loan
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val loanList = arrayListOf<Loan>();
    private val loansAdapter = LoanAdapter(loanList) { loan -> onMovieClick(loan) }


    private lateinit var myView: View
    private lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_home, container, false)

        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabAddContent.setOnClickListener { onAddClick() }
        initViews()
        initViewModel()
    }

    private fun initViews() {
        // Reverse Layout is true.
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, true)
        rvLoans.layoutManager = layoutManager
        layoutManager.stackFromEnd = true
        rvLoans.adapter = loansAdapter

        createItemTouchHelper().attachToRecyclerView(rvLoans)
    }

    @SuppressLint("FragmentLiveDataObserve")
    fun initViewModel() {
        viewModel = ViewModelProvider(this).get(AppViewModel::class.java)


        viewModel.loan.observe(this, Observer { loanes ->
            this@HomeFragment.loanList.clear()
            this@HomeFragment.loanList.addAll(loanes)
            loansAdapter.notifyDataSetChanged()
        })
    }

    private fun onMovieClick(loan: Loan) {
        val intent = Intent(getActivity(), LoanDetailActivity::class.java)
        val item = Bundle()
        item.putParcelable("Loan", loan)
        intent.putExtras(item)
        startActivity(intent)
    }


    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val recipeToDelete = loanList[position]


                viewModel.deleteLoan(recipeToDelete)
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun onAddClick() {
        // From HomeActivity to AddActivity.
        val nextIntent = Intent(getActivity(), AddLoanActivity::class.java)
        startActivityForResult(nextIntent, REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE -> {
                    val loan = data!!.getParcelableExtra<Loan>(AddLoanActivity.EXTRA_ITEM)
                    viewModel.insertLoan(loan)

                }
            }
        }

    }
}
