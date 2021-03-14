package com.philippo.tp3.ui.company.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.philippo.tp3.dao.CompanyDao
import com.philippo.tp3.model.Company

class ListCompanyViewModel(private val companyDao: CompanyDao) : ViewModel() {

    private val _companies = MutableLiveData<List<Company>>()
    val companies: LiveData<List<Company>> = _companies

    fun updateCompaniesOnList(){
        companyDao.all().addSnapshotListener { snapshot, error ->
            if(error != null) Log.i("CompanyList", "${error.message}")
            else
                if (snapshot != null && !snapshot.isEmpty)
                    _companies.value = snapshot.toObjects(Company::class.java)
        }
    }
}