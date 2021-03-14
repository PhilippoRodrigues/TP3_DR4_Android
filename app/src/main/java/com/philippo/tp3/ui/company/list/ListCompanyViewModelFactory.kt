package com.philippo.tp3.ui.company.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.philippo.tp3.dao.CompanyDao

class ListCompanyViewModelFactory(private val companyDao: CompanyDao): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListCompanyViewModel::class.java))
            return ListCompanyViewModel(companyDao) as T
        throw IllegalArgumentException("Classe ViewModel deve ser ListCarroViewModel")
    }
}