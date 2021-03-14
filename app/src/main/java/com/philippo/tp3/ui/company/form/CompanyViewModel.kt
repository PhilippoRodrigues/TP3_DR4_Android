package com.philippo.tp3.ui.company.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.storage.FirebaseStorage
import com.philippo.tp3.dao.CompanyDao
import com.philippo.tp3.model.Company
import com.google.firebase.storage.StorageReference as StorageReference

class CompanyViewModel(private val companyDao: CompanyDao, application: Application): AndroidViewModel(application) {

    private val app = application

    private val _msg = MutableLiveData<String?>()
    val msg: LiveData<String?> = _msg

    init {
        _msg.value = null
    }

    private fun getFileReference(name: String): StorageReference {

        return FirebaseStorage
            .getInstance()
            .reference
            .child("companies/$name")
    }

    fun store(name: String, district: String){
        val company = Company(name, district)
        companyDao.insert(company)
            .addOnSuccessListener {
                _msg.value = "Persistência realizada com sucesso."
            }
            .addOnFailureListener {
                _msg.value = "Problemas ao persistir os dados."
            }
    }
}