package com.philippo.tp3.ui.company.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.philippo.tp3.dao.CompanyDao

class CompanyViewModelFactory(private val companyDao: CompanyDao, private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CompanyViewModel::class.java))
            return CompanyViewModel(companyDao, application) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }
}