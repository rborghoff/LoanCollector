package com.example.loancollector.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loancollector.R
import com.example.loancollector.model.Loan
import kotlinx.android.synthetic.main.loan_item.view.*

class LoanAdapter (private val loanes: List<Loan>, private val onClick: (Loan) -> Unit) : RecyclerView.Adapter<LoanAdapter.ViewHolder>() {

    lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        context = parent.context;
//        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.loan_item, parent, false))
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loan_item, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return loanes.size;
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(loanes[position])
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {

                onClick(loanes[adapterPosition])
            }
        }

        fun bind(loan: Loan) {
            itemView.tvTitle.text = loan.title
            itemView.tvNaam.text = loan.naam
            itemView.tvAmount.text = loan.amount.toString()
        }
    }
}