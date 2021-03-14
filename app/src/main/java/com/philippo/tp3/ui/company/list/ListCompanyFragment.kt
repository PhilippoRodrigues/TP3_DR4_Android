package com.philippo.tp3.ui.company.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.philippo.tp3.R
import com.philippo.tp3.adapter.RecyclerListCompany
import com.philippo.tp3.dao.CompanyDaoFirestore
import com.philippo.tp3.model.Company
import com.philippo.tp3.model.`object`.CompanyUtil
import kotlinx.android.synthetic.main.list_company_fragment.*

class ListCompanyFragment : Fragment() {

    private lateinit var viewModel: ListCompanyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null)
            findNavController().popBackStack()

        val view= inflater.inflate(R.layout.list_company_fragment, container, false)

        val listCompanyViewModelFactory = ListCompanyViewModelFactory(CompanyDaoFirestore())
        viewModel = ViewModelProvider(this, listCompanyViewModelFactory).get(ListCompanyViewModel::class.java)

        viewModel.companies.observe(viewLifecycleOwner) {
            setupListViewCompanies(it)
        }
        viewModel.updateCompaniesOnList()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogout.setOnClickListener {

            val firebaseAuth = FirebaseAuth.getInstance()

            firebaseAuth.signOut()
            findNavController().navigate(R.id.loginFragment)
        }
    }

    private fun setupListViewCompanies(companies: List<Company>) {
        recyclerListCompany.adapter = RecyclerListCompany(companies) {
            CompanyUtil.companySelected = it
            findNavController().navigate(R.id.companyRegisterFragment)
        }
        recyclerListCompany.layoutManager = LinearLayoutManager(requireContext())
    }
}