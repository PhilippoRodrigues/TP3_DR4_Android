package com.philippo.tp3.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.philippo.tp3.R

import com.philippo.tp3.model.Company
import com.philippo.tp3.model.Question
import kotlinx.android.synthetic.main.fragment_question.view.*

class RecyclerListCompany(
    private val companies: List<Company>,
    private val actionClick: (Company) -> Unit
) : RecyclerView.Adapter<RecyclerListCompany.CompanyViewHolder>() {

    class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val companyName: TextView = view.textViewCompanyName
//        val companyScore: TextView = view.textViewCompanyScore
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_question, parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        val company = companies[position]
        holder.companyName.text = company.name
//        holder.companyScore.text = company.score.toString()

        holder.itemView.setOnClickListener {
            actionClick(company)
        }
    }

    override fun getItemCount(): Int = companies.size
}